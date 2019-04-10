package fr.tcleard.numberslight.ui.viewController

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.FragmentManager

interface IViewController {

    fun provideContext(): Context
    fun provideFragmentManager(): FragmentManager

    fun startActivity(intent: Intent, options: Bundle? = null)
    fun startActivityForResult(intent: Intent, requestCode: Int)

}