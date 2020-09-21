package com.example.anfcodetest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class MainPresenter : MvpBasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun fetchJson(url: String) = ifViewAttached { view ->
        val client = OkHttpClient()
        val request = CardRequest.buildRequest(url)
        var cardList: List<Card>

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val mapper = jacksonObjectMapper()
                cardList = mapper.readValue(body.toString())
                view.displayCards(cardList)
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute JSON request, $e")
                //TODO: Perhaps build an error screen of some kind in here and call it i.e. view.showOnError()?
            }
        })
    }


    override fun destroy() {
        super.destroy()
    }

    override fun attachView(view: MainContract.View) {
        super.attachView(view)
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
    }

    override fun detachView() {
        super.detachView()
    }

}