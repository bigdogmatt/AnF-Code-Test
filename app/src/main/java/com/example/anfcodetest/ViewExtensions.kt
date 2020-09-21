package com.example.anfcodetest

import android.view.View
import android.widget.TextView

fun View.setVisibilityFor(flag: Boolean) {
    when {
        flag -> makeVisible()
        else -> makeGone()
    }
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun TextView.setTextAndVisibility(text: String?) {
    if (!text.isNullOrEmpty()) {
        this.text = text
        makeVisible()
    } else {
        makeGone()
    }
}