package fr.tcleard.numberslight.scene.main

import android.content.res.Configuration
import android.os.Bundle
import fr.tcleard.numberslight.R
import fr.tcleard.numberslight.scene.main.list.ListFragment
import fr.tcleard.numberslight.ui.viewController.AActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AActivity<MainPresenter>(), MainPresenter.MainView, ListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(MainModule())
                .build()
                .inject(this)

        presenter.attach(this)

        (mainListFragment as ListFragment).listener = this

    }

    override fun onStart() {
        super.onStart()
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            presenter.inLandscape()
        } else {
            presenter.inPortrait()
        }
    }

    override fun onBackPressed() {
        presenter.onBackPressed()
    }

    /** MainView **/

    override fun showListFragment(show: Boolean) {
        if (show) {
            fragmentTransaction {
                show(mainListFragment)
            }
        } else {
            fragmentTransaction {
                hide(mainListFragment)
            }
        }
    }

    override fun showDetailFragment(show: Boolean) {
        if (show) {
            fragmentTransaction {
                show(mainDetailFragment)
            }
        } else {
            fragmentTransaction {
                hide(mainDetailFragment)
            }
        }
    }

    override fun showItemDetail() {
    }

    override fun quit() {
        finish()
    }

    /** Listeners **/

    override fun onItemClicked() {
        presenter.onItemClicked()
    }

}