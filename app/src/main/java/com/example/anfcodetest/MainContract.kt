package com.example.anfcodetest

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface MainContract {

    interface View : MvpView {

        //Loads the cards by fetching and parsing the Json
        fun loadCards() // remove this call as it isn't called by the Presenter.
        fun fetchJson(url: String) // move this call to the Presenter since it is logic
        // add a function like setCards(cardList: List<Card) that can be called from the Presenter.fetchJson code.
    }

    interface Presenter : MvpPresenter<View> {

        //Sets up the cards to be displayed by going through the card list and hiding unnecessary views
        fun cardSetup(holder: CustomViewHolder, position: Int,cardList: List<Card>) // this function shouldn't be needed.

    }
}
