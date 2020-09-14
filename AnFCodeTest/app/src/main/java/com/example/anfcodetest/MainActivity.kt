package com.example.anfcodetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fasterxml.jackson.module.kotlin.*
import com.hannesdorfmann.mosby3.mvp.MvpView
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
    val cardList = MainRepository().fetchJson("https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    runOnUiThread {
      recyclerView_main.layoutManager = LinearLayoutManager(this)
      recyclerView_main.recycledViewPool.setMaxRecycledViews(0,0)
      recyclerView_main.adapter = MainAdapter(cardList)
    }
  }
}
