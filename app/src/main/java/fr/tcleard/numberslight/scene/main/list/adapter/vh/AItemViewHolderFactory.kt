package fr.tcleard.numberslight.scene.main.list.adapter.vh

import fr.tcleard.numberslight.scene.main.list.adapter.vm.ItemViewModel
import fr.tcleard.numberslight.ui.adapter.AViewHolderFactory

abstract class AItemViewHolderFactory : AViewHolderFactory<AItemViewHolder>() {

    abstract fun getViewType(itemViewModel: ItemViewModel): Int
    abstract fun getLoadingViewType(): Int

}