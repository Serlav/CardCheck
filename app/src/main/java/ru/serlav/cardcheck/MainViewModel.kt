package ru.serlav.cardcheck

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject


class MainViewModel : ViewModel() {

    val resultText = MutableLiveData<CardInfo>()

    fun parseCardInfo(result: String) {
        val mainObject = JSONObject(result)
        resultText.value = CardInfo(
            mainObject.getJSONObject("number").getInt("length"),
            mainObject.getJSONObject("number").getBoolean("luhn"),
            mainObject.getString("scheme"),
            mainObject.getString("type"),
            mainObject.getString("brand"),
            mainObject.getBoolean("prepaid"),
            mainObject.getJSONObject("country").getInt("numeric"),
            mainObject.getJSONObject("country").getString("alpha2"),
            mainObject.getJSONObject("country").getString("name"),
            mainObject.getJSONObject("country").getString("emoji"),
            mainObject.getJSONObject("country").getString("currency"),
            mainObject.getJSONObject("country").getInt("latitude"),
            mainObject.getJSONObject("country").getInt("longitude"),

            mainObject.getJSONObject("bank").getString("name"),
            mainObject.getJSONObject("bank").getString("url"),
            mainObject.getJSONObject("bank").getString("phone")
        )
    }
}