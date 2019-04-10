package fr.tcleard.numberslight.scene.main.list

import dagger.Component
import fr.tcleard.numberslight.di.SceneScope
import fr.tcleard.numberslight.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [ListModule::class]
)
interface ListComponent {

    fun inject(fragment: ListFragment)

}