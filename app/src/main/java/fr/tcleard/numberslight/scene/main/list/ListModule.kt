package fr.tcleard.numberslight.scene.main.list

import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.di.SceneScope

@Module
class ListModule {

    @Provides
    @SceneScope
    fun providePresenter(itemService: IItemService): ListPresenter {
        return ListPresenter(itemService)
    }

}