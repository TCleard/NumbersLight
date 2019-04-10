package fr.tcleard.numberslight

import android.app.Application
import fr.tcleard.numberslight.di.component.AppComponent
import fr.tcleard.numberslight.di.module.AppModule
import fr.tcleard.numberslight.di.component.DaggerAppComponent

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}