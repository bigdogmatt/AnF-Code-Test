package com.example.anfcodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fasterxml.jackson.module.kotlin.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

/**
 * Displays main screen
 * Fetches Json
 * Parses Json
 */

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)


    fetchJson()
  }

  //Helper Functions

  private fun setupRecyclerView(cardList: List<Card>) {
    recyclerView_main.layoutManager = LinearLayoutManager(this)
    recyclerView_main.recycledViewPool.setMaxRecycledViews(0,0)
    recyclerView_main.adapter = MainAdapter(cardList)
  }

  private fun fetchJson() {
    println("Attempting to fetch JSON file...")

    val url = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json"

    val client = OkHttpClient()
    val request = Request.Builder()
      .addHeader("Source", "mobileApps_Android")
      .addHeader("Content-Type", "application/json")
      .addHeader("Accept", "application/json")
      .addHeader("User-Agent", "agent")
      .addHeader("Accept-Language", "en-US")
      .get().url(url).build()

    client.newCall(request).enqueue(object: Callback {
      override fun onResponse(call: Call, response: Response) {
        val body = response.body?.string()
        println(body)

        //Parse Json
        val mapper = jacksonObjectMapper()
        val cardList: List<Card> = mapper.readValue(body.toString())
        cardList.forEach { println(it) }

        runOnUiThread {
          setupRecyclerView(cardList)
        }
      }

      override fun onFailure(call: Call, e: IOException) {
        println("Failed to execute")
      }
    })
  }
}
