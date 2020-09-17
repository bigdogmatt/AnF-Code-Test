package com.example.anfcodetest

import okhttp3.Request

class CardRequest (val url: String) {
    val request = Request.Builder()
        .addHeader("Source", "mobileApps_Android")
        .addHeader("Content-Type", "application/json")
        .addHeader("Accept", "application/json")
        .addHeader("User-Agent", "agent")
        .addHeader("Accept-Language", "en-US")
        .get().url(url).build()
}