package com.miguel_gallego.and_horoscope.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.miguel_gallego.and_horoscope.R
import com.miguel_gallego.and_horoscope.data.ZodiacSing
import com.miguel_gallego.and_horoscope.utils.Net
import com.miguel_gallego.and_horoscope.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    lateinit var imgVwSign: ImageView
    lateinit var tvSignName: TextView
    lateinit var tvSingDates: TextView
    lateinit var vwProgress: LinearProgressIndicator
    lateinit var tvDescription: TextView

    lateinit var zodiacSing: ZodiacSing
    lateinit var session: SessionManager // WTF Is This??
    lateinit var menuItemFavorite: MenuItem
    var isFavorite = false

    val kZodiacSingId = "ZODIAZ_SING_ID" //REVIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Get zodiacSing selected, previously: intent.putExtra(kZodiacSingId, zodiacSing.id)
        val singId = intent.getStringExtra(kZodiacSingId)!!  // WTF Is This? <<<---
        zodiacSing = ZodiacSing.getById(singId)

        // Views
        imgVwSign = findViewById(R.id.imgVwSign)
        tvSignName = findViewById(R.id.tvSignName)
        tvSingDates = findViewById(R.id.tvSignDates)
        vwProgress = findViewById(R.id.vwProgress)
        tvDescription = findViewById(R.id.tvDescription)

        // Set values to views
        imgVwSign.setImageResource(zodiacSing.idIcon)
        tvSignName.setText(zodiacSing.idName)
        tvSingDates.setText(zodiacSing.idDates)
        tvDescription.setText(R.string.detail_descrip)
        CoroutineScope(Dispatchers.Main).launch {
            val strDescr = Net().getHoroscopeTextAsync(zodiacSing.id, "TODAY").await()
            tvDescription.text = strDescr
            vwProgress.hide()
        }

        // Action Bar
        supportActionBar?.setTitle(zodiacSing.idName)
        supportActionBar?.setSubtitle(zodiacSing.idDates)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Back button, tap -> onOptionsItemSelected
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.outline_adb_24) // Change Back button icon

        session = SessionManager(this)  // WTF Is This?? <<<---
        isFavorite = session.isFavorite(singId)

        // TODO
        // navVwPeriod = findViewById(R.id...)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Create & configure MenuItems
        menuInflater.inflate(R.menu.activity_detail_menu, menu)
        menuItemFavorite = menu.findItem(R.id.item_menu_favorite)
        updateMenuItemFavorite()
        return true // false not hide menu, I don't know why
    }

    // When user tap on menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_menu_favorite -> {
                isFavorite = !isFavorite
                updateMenuItemFavorite()
                if (isFavorite) {
                    session.setFavorite(zodiacSing.id)
                } else {
                    session.setFavorite("")   //REVIEW: I don't like this
                }
                return true
            }
            // R from android system, not from our project
            android.R.id.home -> { // Back button
                finish() // Close this activity
                //// Bad Idea:
                //val intent = Intent(this, MainActivity::class.java)
                //startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateMenuItemFavorite() {
        if (isFavorite) {
            menuItemFavorite.setIcon(R.drawable.baseline_favorite_24)
        } else {
            menuItemFavorite.setIcon(R.drawable.baseline_favorite_border_24)
        }
    }


}