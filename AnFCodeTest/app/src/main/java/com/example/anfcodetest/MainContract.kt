package com.example.anfcodetest

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface MainContract {

    interface View : MvpView {
        fun loadCards(mainRepository: MainRepository)
    }

    interface Presenter : MvpPresenter<View> {

        fun cardSetup(holder: CustomViewHolder, position: Int,cardList: List<Card>?)

    }
}