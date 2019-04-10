package fr.tcleard.numberslight.scene.main.list.adapter.vm

import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.scene.main.list.adapter.vh.AItemViewHolderFactory
import fr.tcleard.numberslight.ui.adapter.AViewModel

class ItemViewModel(
        model: Item,
        private val onClickListener: (ItemViewModel) -> Unit
) : AViewModel<Item, AItemViewHolderFactory>(model) {

    var isSelected = false

    fun getName(): String = model.name

    fun getImageUrl(): String = model.imageUrl

    fun getModel(): Item = model

    override fun accept(viewHolderFactory: AItemViewHolderFactory): Int =
            viewHolderFactory.getViewType(this)

    fun onClicked() {
        isSelected = true
        onClickListener.invoke(this)
    }
}