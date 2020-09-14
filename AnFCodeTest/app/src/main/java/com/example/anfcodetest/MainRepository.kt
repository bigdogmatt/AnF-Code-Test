package com.example.anfcodetest

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

var cardList: List<Card>? = null

//MODEL
class MainRepository {

    fun fetchJson(): List<Card>? {
        println("Attempting to fetch JSON file...")

        val client = OkHttpClient()
        val cardRequest = CardRequest("https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json")
        val request = cardRequest.request

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println(body)

                //Parse Json
                val mapper = jacksonObjectMapper()
                cardList = mapper.readValue(body.toString())
                cardList!!.forEach { println(it) }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute")
            }
        })
        return cardList
    }
}

data class Card(@JsonProperty("title") val title: String? = "",
                @JsonProperty("backgroundImage") val backgroundImage: String? = "",
                @JsonProperty("content") val content: List<Item>?,
                @JsonProperty("promoMessage") val promoMessage: String? = "",
                @JsonProperty("topDescription") val topDescription: String? = "",
                @JsonProperty("bottomDescription") val bottomDescription: String? = "")

data class Item(@JsonProperty("title") val title: String? = "",
                @JsonProperty("target") val target: String? = "",
                @JsonProperty("elementType") val elementType: String? = "")