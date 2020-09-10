package com.example.anfcodetest

import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
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
//            if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                web_view_button_target.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//            }
            web_view_button_target.loadUrl(targetLink)
            web_view_button_target.webViewClient = WebViewClient()
        }
    }
}