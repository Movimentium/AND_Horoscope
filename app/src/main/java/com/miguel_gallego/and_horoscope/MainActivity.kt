package com.miguel_gallego.and_horoscope

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var vwRecycler: RecyclerView
    val zodiacSingList: List<ZodiacSing> = ZodiacSing.getAll()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = ZodiacAdapter(zodiacSingList)
        vwRecycler = findViewById(R.id.recyclerVw)
        vwRecycler.adapter = adapter
        vwRecycler.layoutManager = LinearLayoutManager(this)

    }

    /*
    fun goToDetail(horoscope: Horoscope) {
        val intent = Intent(this, DetailActivity::class.java) // to request things to the OS
        intent.putExtra("HOROSCOPE_ID", horoscope.id)
        startActivity(intent) // open DetailActivity
    }
    */

}