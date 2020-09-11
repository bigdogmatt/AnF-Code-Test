package com.example.anfcodetest

import android.view.View

interface MainView {

    fun fetchJson()

    fun setupRecyclerView(cardList: List<Card>)

    fun viewSetup(holder: CustomViewHolder, position: Int, cardList: List<Card>) { }

    fun hideView(view: View) { }

    fun buttonTargetSetup(view: View, content: Item) { }

}