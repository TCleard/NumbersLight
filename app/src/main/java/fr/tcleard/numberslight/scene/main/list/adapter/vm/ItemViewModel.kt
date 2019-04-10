package fr.tcleard.numberslight.scene.main.list.adapter.vm

import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.scene.main.list.adapter.vh.AItemViewHolderFactory
import fr.tcleard.numberslight.ui.adapter.AViewModel

class ItemViewModel(
        model: Item
) : AViewModel<Item, AItemViewHolderFactory>(model) {

    fun getName(): String = model.name

    fun getImageUrl(): String = model.imageUrl

    override fun accept(viewHolderFactory: AItemViewHolderFactory): Int =
            viewHolderFactory.getViewType(this)
}