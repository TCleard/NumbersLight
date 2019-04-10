package fr.tcleard.numberslight.scene.main

import dagger.Component
import fr.tcleard.numberslight.di.SceneScope
import fr.tcleard.numberslight.di.component.AppComponent

@SceneScope
@Component(
        dependencies = [AppComponent::class],
        modules = [MainModule::class]
)
interface MainComponent {

    fun inject(activity: MainActivity)

}