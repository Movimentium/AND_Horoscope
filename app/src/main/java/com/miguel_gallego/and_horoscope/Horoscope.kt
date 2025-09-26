package com.miguel_gallego.and_horoscope

class Horoscope(    // Guardamos el identificador de cada dato
    var id: String,  // pe: "Aries"
    val name: Int,
    val dates: Int,
    val icon: Int
) {

    companion object {  // Para crear elementos static
        private val zodiacSignNames = listOf("aries", "taurus", "gemini", "cancer",
            "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "Pisces")



        /*
        fun getAll(): List<Horoscope> {

        }

         */
    }


}