package fr.tcleard.numberslight.scene.main.detail

import fr.tcleard.numberslight.core.model.Item
import javax.inject.Inject
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView

class DetailPresenter @Inject constructor(

) : APresenter<DetailPresenter.DetailView>() {

    fun setItem(item: Item) {

    }

    interface DetailView : IView {

    }

}