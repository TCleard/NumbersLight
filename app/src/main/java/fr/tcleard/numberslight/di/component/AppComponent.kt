package fr.tcleard.numberslight.di.component

import android.app.Application
import fr.tcleard.numberslight.di.ApplicationScope
import fr.tcleard.numberslight.di.module.AppModule
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: Application)

}