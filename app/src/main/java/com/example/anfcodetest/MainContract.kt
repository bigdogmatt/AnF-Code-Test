package com.example.anfcodetest

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface MainContract {

    interface View : MvpView {

        /**
         * Begins the Json request and initializes the recycler views.
         */
        fun loadCards()

        /**
         * Displays the cards once the presenter loads them up by passing the list of cards to the
         * recycler view adapter.
         */
        fun displayCards(cards: List<Card>)
    }

    interface Presenter : MvpPresenter<View> {

        /**
         * Initializes the JSON and displays the cards.
         */
        fun fetchJson(url: String)
    }
}
