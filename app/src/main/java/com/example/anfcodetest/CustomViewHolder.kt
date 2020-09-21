package com.example.anfcodetest

import android.content.Intent
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_row.view.*

class CustomViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(card: Card) = with(view) {
        loadBackgroundImage(card.backgroundImage)
        title.setTextAndVisibility(card.title)
        promoMessage.setTextAndVisibility(card.promoMessage)
        topDescription.setTextAndVisibility(card.topDescription)
        setUpBottomDescription(card.bottomDescription)
        topButton.setUpButton(card.content?.firstOrNull())
        if (card.content?.size == 2) {
            bottomButton.setUpButton(card.content.get(1))
        }
    }

    private fun onButtonClicked(title: String?, target: String?) = with(view) {
        val intent = Intent(bottomButton.context, ButtonTargetActivity::class.java)
            .putExtra("title", title)
            .putExtra("target", target)
        startActivity(view.context, intent, null)
    }

    private fun loadBackgroundImage(imageUrl: String?) {
        val isValidUrl = !imageUrl.isNullOrEmpty()
        view.cardImage.setVisibilityFor(isValidUrl)
        if (isValidUrl) Picasso.get().load(imageUrl).into(view.cardImage)
    }

    private fun setUpBottomDescription(description: String?) = with(view.bottomDescription) {
        if (!description.isNullOrEmpty()) {
            val formattedDescription = getFormattedBottomDescription(description)
            text = formattedDescription
            movementMethod = LinkMovementMethod.getInstance()
        } else {
            makeGone()
        }
    }

    private fun getFormattedBottomDescription(description: String?) =
        Html.fromHtml("<font color='#939393'>$description")

    private fun Button.setUpButton(content: Item?) {
        if (content != null) {
            setTextAndVisibility(content.title)
            setOnClickListener { onButtonClicked(content.title, content.target) }
        } else {
            makeGone()
        }
    }
}