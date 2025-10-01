package com.miguel_gallego.and_horoscope.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguel_gallego.and_horoscope.R
import com.miguel_gallego.and_horoscope.adapters.ZodiacAdapter
import com.miguel_gallego.and_horoscope.data.ZodiacSing

class MainActivity : AppCompatActivity() {
    val kZodiacSingId = "ZODIAZ_SING_ID" //REVIEW
    lateinit var vwRecycler: RecyclerView
    lateinit var adapter: ZodiacAdapter
    lateinit var viewModeMenu: MenuItem
    val zodiacSingList: List<ZodiacSing> = ZodiacSing.Companion.getAll()
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
    }

    override fun onResume() {
        super.onResume()
        adapter.updateWith(zodiacSingList) //WTF is this
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // TODO
        return super.onOptionsItemSelected(item)
    }

    private fun setupMenuMode() {
        // TODO
    }

    private fun setupGridOrLinearLayout() {
        if (isGridViewEnabled) {    //REVIEW    WTF is ::??
            adapter = ZodiacAdapter(zodiacSingList, ::onItemClickListener, R.layout.item_horoscope)
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
        intent.putExtra(kZodiacSingId, zodiacSing.id)  // WTF is this??
        startActivity(intent)  // open DetailActivity
    }
}