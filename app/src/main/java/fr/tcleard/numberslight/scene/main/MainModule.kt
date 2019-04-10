package fr.tcleard.numberslight.scene.main

import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.di.SceneScope

@Module
class MainModule {

    @Provides
    @SceneScope
    fun providePresenter(): MainPresenter {
        return MainPresenter()
    }

}