package com.example.anfcodetest

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.button_web_view.*

class ButtonTargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.button_web_view)
        updateNavName()
        targetLoad()
    }

    private fun updateNavName() {
        val navBarTitle = intent.getStringExtra("title").orEmpty()
        if (navBarTitle.isNotEmpty()) {
            supportActionBar?.title = navBarTitle
        }
    }

    private fun targetLoad() {
        val targetLink = intent.getStringExtra("target").orEmpty()
        if (targetLink.isNotEmpty()) {
            web_view_button_target.loadUrl(targetLink)
            web_view_button_target.webViewClient = WebViewClient()
        }
    }
}