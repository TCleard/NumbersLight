package fr.tcleard.numberslight.ui.viewController

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.annotation.StringRes
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.View
import fr.tcleard.numberslight.App
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.di.component.AppComponent
import javax.inject.Inject

abstract class AActivity<P : APresenter<*>> : AppCompatActivity(), IViewController {

    @Inject
    protected lateinit var presenter: P

    val appComponent: AppComponent
        get() = (application as App).component

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    fun fragmentTransaction(block: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        block.invoke(fragmentTransaction)
        fragmentTransaction.commit()
    }

    override fun provideContext(): Context = this

    override fun provideFragmentManager(): FragmentManager = supportFragmentManager

    abstract class Builder constructor(
            private val viewController: IViewController
    ) {

        private val context: Context
            get() = viewController.provideContext()

        protected val intent: Intent by lazy {
            Intent(context, activityClass)
        }

        abstract val activityClass: Class<*>

        private val transitions = arrayListOf<Pair<View, String>>()

        fun addSharedElement(view: View, @StringRes transitionNameRes: Int): Builder =
                addSharedElement(view, context.resources.getString(transitionNameRes))

        fun addSharedElement(view: View, transitionName: String): Builder {
            transitions.add(Pair(view, transitionName))
            return this
        }

        fun start() {
            if (context is Activity && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && transitions.isNotEmpty()) {
                val options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitions.toTypedArray())
                viewController.startActivity(intent, options.toBundle())
            } else {
                viewController.startActivity(intent)
            }
        }

        fun startForResult(requestCode: Int) {
            viewController.startActivityForResult(intent, requestCode)
        }

    }

}