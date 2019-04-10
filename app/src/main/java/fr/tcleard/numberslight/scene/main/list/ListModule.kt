package fr.tcleard.numberslight.scene.main.list

import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.core.manager.IImageManager
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.di.SceneScope
import fr.tcleard.numberslight.scene.main.list.adapter.ItemAdapter
import fr.tcleard.numberslight.scene.main.list.adapter.vh.ItemViewHolderFactory

@Module
class ListModule {

    @Provides
    @SceneScope
    fun providePresenter(itemService: IItemService): ListPresenter =
            ListPresenter(itemService)

    @Provides
    @SceneScope
    fun provideAdapter(imageManager: IImageManager): ItemAdapter =
            ItemAdapter(ItemViewHolderFactory(imageManager))

}