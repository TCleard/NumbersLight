package fr.tcleard.numberslight.di.module

import android.app.Application
import fr.tcleard.numberslight.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: Application) {

    @Provides
    @ApplicationScope
    fun provideApplication(): Application = app

}