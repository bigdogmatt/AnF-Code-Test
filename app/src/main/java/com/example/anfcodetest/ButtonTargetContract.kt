package com.example.anfcodetest

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

interface ButtonTargetContract {

    interface View : MvpView {

        fun updateNavName()
        fun targetLoad()
    }

    interface Presenter : MvpPresenter<View> {


    }
}