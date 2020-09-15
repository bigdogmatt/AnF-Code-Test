package com.example.anfcodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fasterxml.jackson.module.kotlin.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

//VIEW
class MainActivity : AppCompatActivity(), MainContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    loadCards()


  }

  override fun loadCards() {
    fetchJson("https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    recyclerView_main.layoutManager = LinearLayoutManager(this)
    recyclerView_main.recycledViewPool.setMaxRecycledViews(0,0)
  }

  override fun fetchJson(url: String) {
    println("Attempting to fetch JSON file...")

    val client = OkHttpClient()
    val cardRequest = CardRequest(url)
    val request = cardRequest.request
    var cardList: List<Card> = emptyList()

    client.newCall(request).enqueue(object: Callback {
      override fun onResponse(call: Call, response: Response) {
        val body = response.body?.string()
        println(body)

        //Parse Json
        val mapper = jacksonObjectMapper()
        cardList = mapper.readValue(body.toString())
        //cardList.forEach { println(it) }
        runOnUiThread {
          recyclerView_main.adapter = MainAdapter(cardList)
        }
      }

      override fun onFailure(call: Call, e: IOException) {
        println("Failed to execute")
      }
    })
  }
}
