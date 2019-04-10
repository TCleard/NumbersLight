package fr.tcleard.numberslight.scene.main.detail

import javax.inject.Inject
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView

class DetailPresenter @Inject constructor(

) : APresenter<DetailPresenter.DetailView>() {

    interface DetailView : IView {

    }

}