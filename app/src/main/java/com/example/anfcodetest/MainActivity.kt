package com.example.anfcodetest

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.mosby3.mvp.MvpActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    loadCards()
  }

  override fun displayCards(cards: List<Card>) {
    val adapter = MainAdapter(cards)
    runOnUiThread {
      recyclerView_main.adapter = adapter
      adapter.notifyDataSetChanged()
    }
  }

  override fun loadCards() {
    getPresenter().fetchJson(PAGE_DATA_URL)
    recyclerView_main.layoutManager = LinearLayoutManager(this)
    recyclerView_main.recycledViewPool.setMaxRecycledViews(0,0)
    recyclerView_main.adapter = MainAdapter(emptyList())
  }

  override fun createPresenter(): MainContract.Presenter = MainPresenter()

  private companion object {
    private const val PAGE_DATA_URL = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json"
  }
}
