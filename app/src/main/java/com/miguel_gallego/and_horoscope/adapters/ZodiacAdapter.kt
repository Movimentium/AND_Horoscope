package com.miguel_gallego.and_horoscope.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miguel_gallego.and_horoscope.R
import com.miguel_gallego.and_horoscope.data.ZodiacSing

class ZodiacAdapter(val items: List<ZodiacSing>): RecyclerView.Adapter<ZodiacSignViewHolder>() {

    // Defines the view for each item of the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZodiacSignViewHolder {
        val vwItem = LayoutInflater.from(parent.context).inflate(
            R.layout.item_horoscope,
                                                                    parent, false)
        return ZodiacSignViewHolder(vwItem)
    }

    // Defines the data for each view item
    override fun onBindViewHolder(holder: ZodiacSignViewHolder, position: Int) {
        val item = items[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


// Related to item_horoscope.xml
class ZodiacSignViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tvSignName: TextView = view.findViewById(R.id.tvSignName)
    val tvSingDates: TextView = view.findViewById(R.id.tvSignDates)
    val imgVwSign: ImageView = view.findViewById(R.id.imgVwSign)

    fun render(zodiacSing: ZodiacSing) {
        tvSignName.setText(zodiacSing.name)
        tvSingDates.setText(zodiacSing.dates)
        imgVwSign.setImageResource(zodiacSing.icon)
    }
}