package com.example.anfcodetest

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

// We generally only want to use MVP if the View has an associated Presenter.  I think this MVP
// contract can be safely removed.
interface ButtonTargetContract {

    interface View : MvpView {

        fun updateNavName()
        fun targetLoad()
    }

    interface Presenter : MvpPresenter<View> {


    }
}
