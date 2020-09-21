package com.example.anfcodetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Matt Kleman 09/04/20
 */

class MainAdapter(private val cardList: List<Card>): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount() = cardList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) = holder.bind(cardList[position])
}
