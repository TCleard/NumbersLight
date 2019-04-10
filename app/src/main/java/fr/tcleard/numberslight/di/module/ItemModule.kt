package fr.tcleard.numberslight.di.module

import dagger.Module
import dagger.Provides
import fr.tcleard.numberslight.core.repo.remote.IItemRemoteRepo
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.core.service.impl.ItemService
import fr.tcleard.numberslight.di.ApplicationScope

@Module
class ItemModule {

    @Provides
    @ApplicationScope
    fun provideItemService(remoteModule: IItemRemoteRepo): IItemService =
            ItemService(remoteModule)

}