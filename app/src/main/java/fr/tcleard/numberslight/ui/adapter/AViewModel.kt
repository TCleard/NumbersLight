package fr.tcleard.numberslight.ui.adapter

import fr.tcleard.numberslight.core.model.AModel

abstract class AViewModel<out M : AModel, F : AViewHolderFactory<*>>(
        protected val model: M
) {

    open fun getId(): String? = model.id

    abstract fun accept(viewHolderFactory: F): Int

}