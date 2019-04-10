package fr.tcleard.numberslight.scene.main

import javax.inject.Inject
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView

class MainPresenter @Inject constructor(

) : APresenter<MainPresenter.MainView>() {

    interface MainView : IView {

    }

}