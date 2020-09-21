package com.example.anfcodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.fasterxml.jackson.module.kotlin.*
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

val url = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json"

class MainActivity : AppCompatActivity(), MainContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    loadCards()
  }

  override fun loadCards() {
    // We generally want to fetch data after the UI is configured and do that fetching in the Presenter.
    // I would probably move the fetching of remote data to after all of the UI calls with the RecyclerView.
    fetchJson(url)
    recyclerView_main.layoutManager = LinearLayoutManager(this)
    // This line is generally unnecessary.  We want the RecyclerView to recycle views for performance reasons.
    recyclerView_main.recycledViewPool.setMaxRecycledViews(0,0)
  }

  override fun fetchJson(url: String) {

    // Android has logging builtin.  See android.util.Log.  This is preferred.
    // We use a Logging framework called Timber.  It has additional capabilities over Log.
    // See https://github.com/JakeWharton/timber
    println("Attempting to fetch JSON file...")

    val client = OkHttpClient()
    val cardRequest = CardRequest(url)
    val request = cardRequest.request

    client.newCall(request).enqueue(object: Callback {
      override fun onResponse(call: Call, response: Response) {
        val body = response.body?.string()

        val mapper = jacksonObjectMapper()
        // the initializer can be done where it is used
        val cardList: List<Card> = mapper.readValue(body.toString())
        runOnUiThread {
          recyclerView_main.adapter = MainAdapter(cardList)
        }
      }

      override fun onFailure(call: Call, e: IOException) {
        // we oftentimes want to communicate errors back to the user.  possibilities include
        // android.widget.Toast and https://material.io/develop/android/components/snackbars/
        println("Failed to execute")
      }
    })
  }
}
