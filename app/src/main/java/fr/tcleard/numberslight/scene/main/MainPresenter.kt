package fr.tcleard.numberslight.scene.main

import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView
import javax.inject.Inject

class MainPresenter @Inject constructor(

) : APresenter<MainPresenter.MainView>() {

    private enum class State {
        LIST,
        DETAIL,
        TWO_PANES
    }

    private var state = State.LIST

    fun inPortrait() {
        state = State.LIST
        view?.showListFragment(true)
        view?.showDetailFragment(false)
    }

    fun inLandscape() {
        state = State.TWO_PANES
        view?.showListFragment(true)
        view?.showDetailFragment(true)
    }

    fun onItemClicked(item: Item) {
        if (state == State.LIST) {
            state = State.DETAIL
            view?.showListFragment(false)
            view?.showDetailFragment(true)
        }
        view?.showItemDetail(item)
    }

    fun onBackPressed() {
        if (state == State.DETAIL) {
            state = State.LIST
            view?.showListFragment(true)
            view?.showDetailFragment(false)
        } else {
            view?.quit()
        }
    }

    interface MainView : IView {

        fun showListFragment(show: Boolean)
        fun showDetailFragment(show: Boolean)

        fun showItemDetail(item: Item)

        fun quit()

    }

}