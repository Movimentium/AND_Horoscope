package com.miguel_gallego.and_horoscope.utils

import android.content.Context
import android.content.SharedPreferences
import com.miguel_gallego.and_horoscope.data.ZodiacSing

class SessionManager(context: Context) {

    private var sharedPrefs: SharedPreferences = context.getSharedPreferences("horoscope_session",
        Context.MODE_PRIVATE)

    private val keyFavoriteSingId = "FAVORITE_HOROSCOPE_ID"

    fun setFavorite(zodiacSingId: String) {
        val prefsEditor = sharedPrefs.edit()
        prefsEditor.putString(keyFavoriteSingId, zodiacSingId)
        prefsEditor.apply()
    }

    fun getFavorite(): String {
        return sharedPrefs.getString(keyFavoriteSingId, "")!! // key, defaultValue
    }

    fun isFavorite(zodiacSingId: String): Boolean {
        return zodiacSingId == getFavorite()
    }
}