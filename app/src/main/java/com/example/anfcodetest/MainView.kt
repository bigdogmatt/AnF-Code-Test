package com.example.anfcodetest

import android.content.Intent
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_row.view.*

interface MainView {

    fun fetchJson()

    fun setupRecyclerView(cardList: List<Card>)

    fun viewSetup(holder: CustomViewHolder, position: Int, cardList: List<Card>) { }

    fun hideView(view: View) { }

    fun buttonTargetSetup(view: View, content: Item) { }

}