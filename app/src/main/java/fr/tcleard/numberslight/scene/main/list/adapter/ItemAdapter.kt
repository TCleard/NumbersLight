package fr.tcleard.numberslight.scene.main.list.adapter

import fr.tcleard.numberslight.scene.main.list.adapter.vh.AItemViewHolder
import fr.tcleard.numberslight.scene.main.list.adapter.vh.AItemViewHolderFactory
import fr.tcleard.numberslight.scene.main.list.adapter.vm.ItemViewModel
import fr.tcleard.numberslight.ui.adapter.AViewModelAdapter

class ItemAdapter(
        viewHolderFactory: AItemViewHolderFactory
) : AViewModelAdapter<ItemViewModel, AItemViewHolder, AItemViewHolderFactory>(
        viewHolderFactory
) {

    init {
        isLoading = true
    }

    override fun getItemViewType(position: Int): Int =
            if (isLoading) {
                viewHolderFactory.getLoadingViewType()
            } else {
                super.getItemViewType(position)
            }

}