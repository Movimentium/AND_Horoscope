package com.miguel_gallego.and_horoscope.data

import com.miguel_gallego.and_horoscope.R

class ZodiacSing (
    val id: String,  // id maybe "aries", "taurus", etc.
    val idName: Int,   // localized name from res/values/strings.xml
    val idDates: Int,
    val idIcon: Int
) {
    companion object {
        private val zodiacSignNames = listOf("aries", "taurus", "gemini", "cancer",
            "leo", "virgo", "libra", "scorpio", "sagittarius", "capricorn", "aquarius", "Pisces")

        /*
        private val zodiacSingList: List<ZodiacSing> = run {
            val list = mutableListOf<ZodiacSing>()
            zodiacSignNames.forEach { list.add(ZodiacSing(it, )) }
            list
        }
        */
        private val zodiacSingList: List<ZodiacSing> = listOf(
            ZodiacSing("aries", R.string.aries, R.string.date_aries, R.drawable.img_aries),
            ZodiacSing("taurus", R.string.taurus, R.string.date_taurus, R.drawable.img_taurus),
            ZodiacSing("gemini", R.string.gemini, R.string.date_gemini, R.drawable.img_gemini),
            ZodiacSing("cancer", R.string.cancer, R.string.date_cancer, R.drawable.img_cancer),
            ZodiacSing("leo", R.string.leo, R.string.date_leo, R.drawable.img_leo),
            ZodiacSing("virgo", R.string.virgo, R.string.date_virgo, R.drawable.img_virgo),
            ZodiacSing("libra", R.string.libra, R.string.date_libra, R.drawable.img_libra),
            ZodiacSing("scorpio", R.string.scorpio, R.string.date_scorpio, R.drawable.img_scorpio),
            ZodiacSing("sagittarius", R.string.sagittarius, R.string.date_sagittarius, R.drawable.img_sagittarius),
            ZodiacSing("capricorn", R.string.capricorn, R.string.date_capricorn, R.drawable.img_capricornus),
            ZodiacSing("aquarius", R.string.aquarius, R.string.date_aquarius, R.drawable.img_aquarius),
            ZodiacSing("pisces", R.string.pisces, R.string.date_pisces, R.drawable.img_pisces)
        )

        fun getAll(): List<ZodiacSing> {
            return zodiacSingList
        }

        fun getById(id: String): ZodiacSing {
            return zodiacSingList.first { it.id == id }
        }
    }

}