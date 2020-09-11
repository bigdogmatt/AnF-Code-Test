package com.example.anfcodetest

import android.content.Intent
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_row.view.*


class MainPresenter(val mainView: MainView){

    fun viewSetup(holder: CustomViewHolder, position: Int, cardList: List<Card>) {
        val card = cardList[position]

        //Check if there is a card image for the current card and remove the view if not
        if (card.backgroundImage.isNullOrBlank()) {
            hideView(holder.view.cardImage)
        } else {
            Picasso.get().load(card.backgroundImage).into(holder.view.cardImage)
        }

        //Check if there is a title for the current card and remove the view if not
        if (card.title.isNullOrEmpty()) {
            hideView(holder.view.title)
        } else {
            holder.view.title?.text = card.title
        }

        //Check if there is a promo message for the current card and remove the view if not
        if (card.promoMessage.isNullOrEmpty()) {
            hideView(holder.view.promoMessage)
        } else {
            holder.view.promoMessage?.text = card.promoMessage
        }

        //Check if there is a top description for the current card and remove the view if not
        if (card.topDescription.isNullOrEmpty()) {
            hideView(holder.view.topDescription)
        } else {
            holder.view.topDescription?.text = card.topDescription
        }

        //Check if there is a bottom description for the current card and remove the view if not
        if (card.bottomDescription.isNullOrEmpty()) {
            hideView(holder.view.bottomDescription)
        } else {
            holder.view.bottomDescription?.text =
                (Html.fromHtml("<font color='#939393'>" + card.bottomDescription))
            holder.view.bottomDescription?.movementMethod = LinkMovementMethod.getInstance()
        }

        //Check how many content items there are for the current card and remove the button(s) accordingly
        if (card.content.isNullOrEmpty()) {
            //get rid of both buttons
            hideView(holder.view.topButton)
            hideView(holder.view.bottomButton)
        } else {
            val content = card.content[0]
            holder.view.topButton?.text = content.title

            //Set up the button target action
            buttonTargetSetup(holder.view.topButton, content)
        }
        if (card.content?.count() == 2) {
            val content = card.content[1]
            holder.view.bottomButton?.text = content.title

            //Set up the button target action
            buttonTargetSetup(holder.view.bottomButton, content)
        } else {
            //get rid of second button
            hideView(holder.view.bottomButton)
        }
    }

    fun hideView(view: View) {
        view.visibility = View.GONE
    }

    fun buttonTargetSetup(view: View, content: Item) {
        view.setOnClickListener {
            val intent = Intent(view.context, ButtonTargetActivity::class.java)
            intent.putExtra("title", content.title)
            intent.putExtra("target", content.target)
            view.context.startActivity(intent)
        }
    }

}