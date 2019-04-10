package fr.tcleard.numberslight.scene.main.list

import javax.inject.Inject
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView

class ListPresenter @Inject constructor(

) : APresenter<ListPresenter.ListView>() {

    interface ListView : IView {

    }

}