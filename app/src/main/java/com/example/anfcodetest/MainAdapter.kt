package com.example.anfcodetest

import android.content.Intent
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_row.view.*

/**
 * Created by Matt Kleman 09/04/20
 */

class MainAdapter(val cardList: List<Card>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return cardList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_row, parent, false)
        return CustomViewHolder(cellForRow)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        MainActivity().viewSetup(holder, position, cardList)
    }


}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {


}