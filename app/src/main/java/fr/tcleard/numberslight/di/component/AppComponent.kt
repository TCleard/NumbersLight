package fr.tcleard.numberslight.di.component

import android.app.Application
import dagger.Component
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.di.ApplicationScope
import fr.tcleard.numberslight.di.module.AppModule
import fr.tcleard.numberslight.di.module.ItemModule
import fr.tcleard.numberslight.di.module.RemoteModule

@ApplicationScope
@Component(
        modules = [AppModule::class,
            RemoteModule::class,
            ItemModule::class]
)
interface AppComponent {

    fun inject(app: Application)

    /** Services **/
    fun itemService(): IItemService

}