package com.example.anfcodetest

import okhttp3.Request

object CardRequest {
    fun buildRequest(target: String): Request {
        return Request.Builder()
            .addHeader("Source", "mobileApps_Android")
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .addHeader("User-Agent", "agent")
            .addHeader("Accept-Language", "en-US")
            .get().url(target).build()
    }
}