package com.example.anfcodetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter(val cardList: List<Card>): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return cardList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_row, parent, false)
        return CustomViewHolder(cellForRow)
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        MainActivity().viewSetup(holder,position,cardList)
    }


}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {


}