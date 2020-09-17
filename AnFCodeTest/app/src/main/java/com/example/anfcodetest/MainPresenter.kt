package com.example.anfcodetest

import android.content.Intent
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View.GONE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_row.view.*


class MainPresenter : MainContract.Presenter {

    override fun cardSetup(holder: CustomViewHolder, position: Int, cardList: List<Card>) {
        val card = cardList[position]

        //Check if there is a card image for the current card and remove the view if not
        if (card.backgroundImage.isNullOrBlank()) {
            holder.view.cardImage.visibility = GONE
        } else {
            Picasso.get().load(card.backgroundImage).into(holder.view.cardImage)
        }

        //Check if there is a title for the current card and remove the view if not
        if (card.title.isNullOrEmpty()) {
            holder.view.title.visibility = GONE
        } else {
            holder.view.title?.text = card.title
        }

        //Check if there is a promo message for the current card and remove the view if not
        if (card.promoMessage.isNullOrEmpty()) {
            holder.view.promoMessage.visibility = GONE
        } else {
            holder.view.promoMessage?.text = card.promoMessage
        }

        //Check if there is a top description for the current card and remove the view if not
        if (card.topDescription.isNullOrEmpty()) {
            holder.view.topDescription.visibility = GONE
        } else {
            holder.view.topDescription?.text = card.topDescription
        }

        //Check if there is a bottom description for the current card and remove the view if not
        if (card.bottomDescription.isNullOrEmpty()) {
            holder.view.bottomDescription.visibility = GONE
        } else {
            holder.view.bottomDescription?.text =
                (Html.fromHtml("<font color='#939393'>" + card.bottomDescription))
            holder.view.bottomDescription?.movementMethod = LinkMovementMethod.getInstance()
        }

        //Check how many content items there are for the current card and remove the button(s) accordingly
        if (card.content.isNullOrEmpty()) {
            //get rid of both buttons
            holder.view.topButton.visibility = GONE
            holder.view.bottomButton.visibility = GONE
        } else {
            val content = card.content[0]
            holder.view.topButton?.text = content.title

            //Set up the button target action
            holder.view.topButton.setOnClickListener {
                val intent =
                    Intent(holder.view.topButton.context, ButtonTargetActivity::class.java)
                intent.putExtra("title", content.title)
                intent.putExtra("target", content.target)
                holder.view.topButton.context.startActivity(intent)
            }
        }
        if (card.content?.count() == 2) {
            val content = card.content[1]
            holder.view.bottomButton?.text = content.title

            //Set up the button target action
            holder.view.bottomButton.setOnClickListener {
                val intent =
                    Intent(holder.view.bottomButton.context, ButtonTargetActivity::class.java)
                intent.putExtra("title", content.title)
                intent.putExtra("target", content.target)
                holder.view.bottomButton.context.startActivity(intent)
            }
        } else {
            //get rid of second button
            holder.view.bottomButton.visibility = GONE

        }
    }


    override fun destroy() {
    }

    override fun attachView(view: MainContract.View) {
    }

    override fun detachView(retainInstance: Boolean) {
    }

    override fun detachView() {
    }

}