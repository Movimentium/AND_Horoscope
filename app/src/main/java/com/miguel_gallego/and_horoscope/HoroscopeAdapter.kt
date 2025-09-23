package com.miguel_gallego.and_horoscope

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HoroscopeAdapter(val items: List<Horoscope>): RecyclerView.Adapter<HoroscopeViewHolder>() {

    // Cuál es la vista para los elementos
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HoroscopeViewHolder {
        TODO("Not yet implemented")
    }

    // Cuáles son los datos para el elemento
    override fun onBindViewHolder(
        holder: HoroscopeViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return items.size
    }


}

class HoroscopeViewHolder(view: View): RecyclerView.ViewHolder(view) {

}