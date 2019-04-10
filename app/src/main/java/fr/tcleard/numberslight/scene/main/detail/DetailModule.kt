package fr.tcleard.numberslight.scene.main.detail

import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.di.SceneScope

@Module
class DetailModule {

    @Provides
    @SceneScope
    fun providePresenter(): DetailPresenter {
        return DetailPresenter()
    }

}