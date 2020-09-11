package com.example.anfcodetest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.button_web_view.*
import kotlinx.android.synthetic.main.card_row.*
import kotlinx.android.synthetic.main.card_row.view.*

class ButtonTargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.button_web_view)


        //Change navigation bar title
        val navBarTitle = intent.getStringExtra("title")
        supportActionBar?.title = navBarTitle

        //Get target link and load

        val targetLink = intent.getStringExtra("target")
        if (targetLink != null) {
            web_view_button_target.loadUrl(targetLink)
            web_view_button_target.webViewClient = WebViewClient()
        }

    }
}