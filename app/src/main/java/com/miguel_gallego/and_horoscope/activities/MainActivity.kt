package com.miguel_gallego.and_horoscope.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguel_gallego.and_horoscope.R
import com.miguel_gallego.and_horoscope.adapters.ZodiacAdapter
import com.miguel_gallego.and_horoscope.data.ZodiacSing
import com.miguel_gallego.and_horoscope.utils.Net
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val kZodiacSingId = "ZODIAZ_SING_ID" //REVIEW
    lateinit var vwRecycler: RecyclerView
    lateinit var adapter: ZodiacAdapter
    lateinit var viewModeMenu: MenuItem
    var zodiacSingList: List<ZodiacSing> = ZodiacSing.Companion.getAll()
    var isGridViewEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.setTitle(R.string.activity_main_title)
        vwRecycler = findViewById(R.id.recyclerVw)
        setupGridOrLinearLayout()

        Log.i(Net.kAPI,"Thread.name: ${Thread.currentThread().name} >> PRE CoroutineScope(Dispatchers.Main).launch")
        // TODO Test (Delete in the future)
        CoroutineScope(Dispatchers.Main).launch {
            val horoscopeText = Net().getHoroscopeTextAsync("Pisces", "TODAY").await()
            Log.i(Net.kAPI,"Thread.name: ${Thread.currentThread().name} >> CoroutineScope(Dispatchers.Main).launch")
            Log.i(Net.kAPI,horoscopeText)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.updateWith(zodiacSingList)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        viewModeMenu = menu.findItem(R.id.item_menu_view_mode)
        setupMenuMode()

        // Search functionality
        val vwSearch = menu.findItem(R.id.item_menu_search).actionView as SearchView
        vwSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener {  // WTF is this??
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("SEARCH", "onQueryTextSubmit query: $query")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.i("SEARCH", "onQueryTextChange query: $newText")
                zodiacSingList = ZodiacSing.getAll().filter {
                    val strZodiacName = getString(it.idName)
                    val strZodiacDates = getString(it.idDates)
                    strZodiacName.contains(newText, ignoreCase = true) ||
                            strZodiacDates.contains(newText, ignoreCase = true)
                }
                adapter.updateWith(zodiacSingList)
                return true
            }
        })
        return true
        //return super.onCreateOptionsMenu(menu) // Why do not call super??
    }

    // When user tap on menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_menu_view_mode -> {
                isGridViewEnabled = !isGridViewEnabled
                setupGridOrLinearLayout()
                setupMenuMode()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupMenuMode() {
        if (isGridViewEnabled) {
            viewModeMenu.setIcon(R.drawable.outline_view_list_24)
        } else {
            viewModeMenu.setIcon(R.drawable.outline_grid_view_24)
        }
    }

    private fun setupGridOrLinearLayout() {
        if (isGridViewEnabled) {    //REVIEW    WTF is ::??
            adapter = ZodiacAdapter(zodiacSingList, ::onItemClickListener, R.layout.item_horoscope_grid)
            vwRecycler.layoutManager = GridLayoutManager(this, 2)
        } else {
            adapter = ZodiacAdapter(zodiacSingList, ::onItemClickListener, R.layout.item_horoscope)
            vwRecycler.layoutManager = LinearLayoutManager(this)
        }
        vwRecycler.adapter = adapter
    }

    private fun onItemClickListener(idx: Int) {
        val zodiacSing = zodiacSingList[idx]
        goToDetail(zodiacSing)
    }

    private fun goToDetail(zodiacSing: ZodiacSing) {
        val intent = Intent(this, DetailActivity::class.java)  // to request things to the OS // WTF is this??
        intent.putExtra(kZodiacSingId, zodiacSing.id)  // send id to DetailActivity
        startActivity(intent)  // open DetailActivity
    }
}