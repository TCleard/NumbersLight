package fr.tcleard.numberslight.ui.viewController

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import fr.tcleard.numberslight.App
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.di.component.AppComponent
import javax.inject.Inject

abstract class AFragment<P : APresenter<*>> : Fragment(), IViewController {

    @Inject
    protected lateinit var presenter: P

    val isViewCreated: Boolean
        get() = view != null

    val appComponent: AppComponent
        get() = (activity?.application as App).component

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isFirstCreation) {
            isFirstCreation = false
            onFirstCreate()
        } else {
            onRecreate(savedInstanceState)
        }
    }

    open fun onFirstCreate() {

    }

    open fun onRecreate(savedInstanceState: Bundle?) {

    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    override fun provideContext(): Context = requireContext()

    override fun provideFragmentManager(): FragmentManager = requireFragmentManager()

    abstract class Builder<F : AFragment<*>> {

        protected abstract val fragment: F

        open fun build(): F = fragment

    }

}