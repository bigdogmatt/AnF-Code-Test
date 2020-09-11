package com.example.anfcodetest

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.button_web_view.*

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