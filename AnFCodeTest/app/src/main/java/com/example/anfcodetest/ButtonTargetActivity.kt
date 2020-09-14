package com.example.anfcodetest

import android.os.Build
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.button_web_view.*

class ButtonTargetActivity : AppCompatActivity(), ButtonTargetContract.View {

    //VIEW
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.button_web_view)


        //Change navigation bar title
        updateNavName()

        //Get target link and load
        targetLoad()
    }
    override fun updateNavName() {
        val navBarTitle = intent.getStringExtra("title")
        supportActionBar?.title = navBarTitle
    }

    override fun targetLoad() {
        val targetLink = intent.getStringExtra("target")
        if (targetLink != null) {
            web_view_button_target.loadUrl(targetLink)
            web_view_button_target.webViewClient = WebViewClient()
        }
    }
}