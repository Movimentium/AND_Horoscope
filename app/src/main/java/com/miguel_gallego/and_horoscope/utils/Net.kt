package com.miguel_gallego.and_horoscope.utils

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class Net {
    private val api = "https://horoscope-app-api.vercel.app/api/v1/get-horoscope"
    private val period = "daily"

    companion object {
        val kAPI = "API"
    }
    // sign accepted values: Aries, Taurus, Gemini, Cancer, Leo, Virgo, Libra, Scorpio,
    // Sagittarius, Capricorn, Aquarius, Pisces

    // day accepted values: Date in format (YYYY-MM-DD) OR "TODAY" OR "TOMORROW" OR "YESTERDAY".

    fun getHoroscopeTextAsync(sing: String, day: String?): Deferred<String> =
        CoroutineScope(Dispatchers.IO).async {
            var strUrl = "$api/$period?sign=$sing"
            day.let { strUrl += "&day=$it" }
            Log.i(kAPI,"strUrl: $strUrl")
            Log.i(Net.kAPI,"Thread.name: ${Thread.currentThread().name} >> CoroutineScope(Dispatchers.IO).async")


            val url = URL(strUrl)
            val connection = url.openConnection() as HttpsURLConnection
            connection.requestMethod = "GET"

            try {
                if (connection.responseCode == HttpsURLConnection.HTTP_OK) {
                    val response = readStream(connection.inputStream)
                    val json = JSONObject(response)
                    val text = json.getJSONObject("data").getString("horoscope_data")
                    return@async text
                } else {
                    Log.i(kAPI,"Response code: ${connection.responseCode}"); ""
                }
            } catch (e: Exception) {
                Log.e(kAPI,"Error", e)
                e.printStackTrace();  ""
            } finally {
                connection.disconnect();  ""
            }
        }



    private fun readStream(input: InputStream): String {
        val response = StringBuffer()
        val reader = BufferedReader(InputStreamReader(input))
        var inputLine: String?

        while ((reader.readLine().also { inputLine = it }) != null) {
            response.append(inputLine)
        }
        reader.close()
        return response.toString()
    }
}