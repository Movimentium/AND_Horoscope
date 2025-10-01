package com.miguel_gallego.and_horoscope

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.miguel_gallego.and_horoscope.data.ZodiacSing
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {

    lateinit var tvSignName: TextView
    lateinit var tvSingDates: TextView
    lateinit var imgVwSign: ImageView

    lateinit var sing: ZodiacSing
    //lateinit var session: SessionManager // WTF Is This??
    lateinit var menuFavorite: MenuItem
    var isFavorite = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Views
        imgVwSign = findViewById(R.id.imgVwSign)
        tvSignName = findViewById(R.id.tvSignName)
        tvSingDates = findViewById(R.id.tvSignDates)

        // session = SessionManager(this)  // WTF Is This??

        val id = intent.getStringExtra("HOROSCOPE_ID")!! // WTF Is This?

        //isFavorite = session
    }
}