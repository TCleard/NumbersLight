package fr.tcleard.numberslight.scene.main

import android.os.Bundle
import fr.tcleard.numberslight.R
import fr.tcleard.numberslight.ui.viewController.AActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AActivity<MainPresenter>(), MainPresenter.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(MainModule())
                .build()
                .inject(this)

        presenter.attach(this)

    }

}