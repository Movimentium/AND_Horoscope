package com.miguel_gallego.and_horoscope

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val arrStr: List<String> = listOf(
        "Aries", "Tauro", "Géminis", "Cáncer",
        "Leo", "Virgo", "Libra", "Escorpio",
        "Sagitario", "Capricornio", "Acuario", "Piscis")

    val horoscopeList: List<Horoscope> = listOf(
        Horoscope("Aries", name = 0, dates = 0, icon = 0),
        Horoscope("Piscis", 9, dates = 4, icon = 2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        for (v in arrStr) {
            println(v)
        }
    }

    fun goToDetail(horoscope: Horoscope) {
        val intent = Intent(this, DetailActivity::class.java) // to request things to the OS
        intent.putExtra("HOROSCOPE_ID", horoscope.id)
        startActivity(intent) // open DetailActivity
    }

}