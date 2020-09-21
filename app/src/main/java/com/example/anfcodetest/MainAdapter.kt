package com.example.anfcodetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Matt Kleman 09/04/20
 */

class MainAdapter(private val cardList: List<Card>): RecyclerView.Adapter<CustomViewHolder>() {

    // Kotlin allows for a large reduction in code with some syntactic sugars.  one example of this is
    // an expression body.  Notice how there is no {} or type declaration needed since Kotlin can
    // figure it out.
    override fun getItemCount() = cardList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // Generally speaking, Adapters do not lend themselves to MVP.  I would move all of the code out of
        // Presenter.cardSetup and place it in here.
        MainPresenter().cardSetup(holder, position, cardList)
    }
}

// it is generally a good idea to define classes in their own files.
// this class is not needed unless it provides specific information
class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {


}
