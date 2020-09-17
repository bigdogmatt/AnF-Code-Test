package com.example.anfcodetest

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface MainContract {

    interface View : MvpView {

        //Loads the cards by fetching and parsing the Json
        fun loadCards()
        fun fetchJson(url: String)
    }

    interface Presenter : MvpPresenter<View> {

        //Sets up the cards to be displayed by going through the card list and hiding unnecessary views
        fun cardSetup(holder: CustomViewHolder, position: Int,cardList: List<Card>)

    }
}