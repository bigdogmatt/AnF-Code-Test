package com.example.anfcodetest

import android.content.Intent
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.View.GONE
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_row.view.*

// Android's RecyclerView/Adapter/ViewHolder components do not lend themselves to the traditional
// MVP pattern.  Following this example, the Model is the Card, the View is the CustomViewHolder, and the
// Presenter is the MainPresenter.  We don't want to pass the View into the Presenter because the Presenter
// should be attached directly to the View.  There are libraries that help with this.  Abercrombie
// uses Mosby.  See http://hannesdorfmann.com/mosby/getting-started/.  I highly recommend following that
// Getting Started as a guide to changing this code.
// The View will end up making calls to the Presenter to do work.  That work can have lots of logic.
// The Presenter will then end up making calls back to the view.  MVP works well for Android because
// components have to honor certain lifecycles that are out of the code's control.
class MainPresenter : MvpBasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun cardSetup(holder: CustomViewHolder, position: Int, cardList: List<Card>) {
        val card = cardList[position]


        // generally, we want if/else to be parallel operations.
        // e.g., if (this) { make it visible } else { make it invisible }
        // Because of how RecyclerViews reuse views, that will be necessary in this class.
        // Without making things visible, once something is made GONE, it will never come back :)

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
            // here is another example where we would need to make the buttons visible in the else
            val content = card.content[0]
            holder.view.topButton?.text = content.title

            //Set up the button target action
            holder.view.topButton.setOnClickListener {
                val intent =
                    Intent(holder.view.context, ButtonTargetActivity::class.java)
                intent.putExtra("title", content.title)
                intent.putExtra("target", content.target)
                holder.view.context.startActivity(intent)
            }
        }
        if (card.content?.count() == 2) {
            // here is an example where we would need to make the button visible since the else hides it
            val content = card.content[1]
            holder.view.bottomButton?.text = content.title

            //Set up the button target action
            holder.view.bottomButton.setOnClickListener {
                val intent =
                    Intent(holder.view.context, ButtonTargetActivity::class.java)
                intent.putExtra("title", content.title)
                intent.putExtra("target", content.target)
                holder.view.context.startActivity(intent)
            }
            // We like to avoid blocks of code that is the same.  (Don't Repeat Yourself - DRY).
            // You could make the card content a nested RecyclerView or you could extract a class
            // that is an OnClickListener as I have done so below.  It eliminates the duplication.
        } else {
            //get rid of second button
            holder.view.bottomButton.visibility = GONE

        }
    }

    // by using MvpBasePresenter<MainContract.View>() the methods can disappear.
}

class ContentClickListener(private val content: Item) : View.OnClickListener {
    override fun onClick(view: View?) {
        val intent =
            Intent(view?.context, ButtonTargetActivity::class.java)
        intent.putExtra("title", content.title)
        intent.putExtra("target", content.target)
        view?.context?.startActivity(intent)
    }
}
