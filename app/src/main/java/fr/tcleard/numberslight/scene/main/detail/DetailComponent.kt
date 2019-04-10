package fr.tcleard.numberslight.scene.main.detail

import dagger.Component
import fr.tcleard.numberslight.di.SceneScope
import fr.tcleard.numberslight.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [DetailModule::class]
)
interface DetailComponent {

    fun inject(fragment: DetailFragment)

}