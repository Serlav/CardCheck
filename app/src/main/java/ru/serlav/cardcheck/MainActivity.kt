package ru.serlav.cardcheck

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import ru.serlav.cardcheck.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fun bind(card: CardInfo) {
            with(binding) {
                search.setOnClickListener {
                    val num = Integer.parseInt(bin.text.toString())
                    cardCheck(num)
                    scheme.text = card.scheme
                    type.text = card.type
                }
            }
        }
    }


    private fun cardCheck(num: Int) {
        val url = "https://lookup.binlist.net/$num"
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result ->
                Log.d("MyLog", "Result: $result")
                parseCardInfo(result)
                // parseBankInfo(result)
            },
            { error ->
                Log.d("MyLog", "EroR: $error")
            }
        )
        queue.add(request)
    }

    private fun parseCardInfo(result: String): CardInfo {
        val mainObject = JSONObject(result)
        val item = CardInfo(
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
            mainObject.getJSONObject("bank").getString("phone"),
            mainObject.getJSONObject("bank").getString("city")
        )

        Log.d("MyLog", "length: ${item.length}")
        Log.d("MyLog", "luhn: ${item.luhn}")
        Log.d("MyLog", "scheme: ${item.scheme}")
        Log.d("MyLog", "type: ${item.type}")
        Log.d("MyLog", "brand: ${item.brand}")
        Log.d("MyLog", "prepaid: ${item.prepaid}")
        Log.d("MyLog", "numeric: ${item.numeric}")
        Log.d("MyLog", "alpha2: ${item.alpha2}")
        Log.d("MyLog", "name: ${item.name}")
        Log.d("MyLog", "emoji: ${item.emoji}")
        Log.d("MyLog", "currency: ${item.currency}")
        Log.d("MyLog", "latitude: ${item.latitude}")
        Log.d("MyLog", "longitude: ${item.longitude}")


        Log.d("MyLog", "name: ${item.name}")
        Log.d("MyLog", "url: ${item.url}")
        Log.d("MyLog", "phone: ${item.phone}")
        Log.d("MyLog", "city: ${item.city}")
        Log.d("MyLog", "ITEM: $item")
        return item
    }
}