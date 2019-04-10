package fr.tcleard.numberslight.scene.main.detail

import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.di.SceneScope

@Module
class DetailModule {

    @Provides
    @SceneScope
    fun providePresenter(itemService: IItemService): DetailPresenter {
        return DetailPresenter(itemService)
    }

}