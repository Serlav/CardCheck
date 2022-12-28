package ru.serlav.cardcheck

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import ru.serlav.cardcheck.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[MainViewModel::class.java]

        vm.resultText.observe(this, Observer {
            with(binding) {
                scheme.text = vm.resultText.value?.scheme
                type.text = vm.resultText.value?.type
                brand.text = vm.resultText.value?.brand
                prepaid.text =
                    (if (vm.resultText.value?.prepaid == true) {
                        "Yes"
                    } else {
                        "No"
                    }).toString()
                length.text = vm.resultText.value?.length.toString()
                luhn.text =
                    (if (vm.resultText.value?.luhn == true) {
                        "Yes"
                    } else {
                        "No"
                    }).toString()
                alpha2.text = vm.resultText.value?.alpha2
                name.text = vm.resultText.value?.name
                location.text = (buildString {
                    append("(latitude: ")
                    append(vm.resultText.value?.latitude)
                    append(", longitude: ")
                    append(vm.resultText.value?.longitude)
                    append(")")
                }
                        )
                bankName.text = vm.resultText.value?.bankName
                url.text = vm.resultText.value?.url
                phone.text = vm.resultText.value?.phone
            }
        })

        with(binding) {
            search.setOnClickListener {
                val num = Integer.parseInt(bin.text.toString())
                cardCheck(num)
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
                vm.parseCardInfo(result)

            },
            { error ->
                Log.d("MyLog", "EroR: $error")
            }
        )
        queue.add(request)
    }
}