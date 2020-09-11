package com.example.anfcodetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.webkit.WebViewClient
import androidx.recyclerview.widget.LinearLayoutManager
import com.fasterxml.jackson.module.kotlin.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.button_web_view.*
import kotlinx.android.synthetic.main.card_row.view.*
import okhttp3.*
import java.io.IOException

/**
 * Displays main screen
 * Fetches Json
 * Parses Json
 */

class MainActivity : AppCompatActivity(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fetchJson()
    }

    override fun setupRecyclerView(cardList: List<Card>) {
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.recycledViewPool.setMaxRecycledViews(0, 0)
        recyclerView_main.adapter = MainAdapter(cardList)
    }

    override fun fetchJson() {
        println("Attempting to fetch JSON file...")

        val url = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json"

        val client = OkHttpClient()
        val request = Request.Builder()
            .addHeader("Source", "mobileApps_Android")
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", "agent")
            .addHeader("Accept-Language", "en-US")
            .get().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                //Parse Json
                val mapper = jacksonObjectMapper()
                val cardList: List<Card> = mapper.readValue(body.toString())
                cardList.forEach { println(it) }

                runOnUiThread {
                    setupRecyclerView(cardList)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }
        })
    }

    override fun viewSetup(holder: CustomViewHolder, position: Int, cardList: List<Card>) {
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

    override fun hideView(view: View) {
        view.visibility = View.GONE
    }

    override fun buttonTargetSetup(view: View, content: Item) {
        view.setOnClickListener {
            val intent = Intent(view.context, ButtonTargetActivity::class.java)
            intent.putExtra("title", content.title)
            intent.putExtra("target", content.target)
            view.context.startActivity(intent)
        }
    }
}

