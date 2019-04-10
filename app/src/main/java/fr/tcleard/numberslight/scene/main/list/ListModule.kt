package fr.tcleard.numberslight.scene.main.list

import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.di.SceneScope

@Module
class ListModule {

    @Provides
    @SceneScope
    fun providePresenter(): ListPresenter {
        return ListPresenter()
    }

}