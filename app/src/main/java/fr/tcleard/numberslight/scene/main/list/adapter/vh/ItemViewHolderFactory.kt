package fr.tcleard.numberslight.scene.main.list.adapter.vh

import android.view.ViewGroup
import fr.tcleard.numberslight.core.manager.IImageManager
import fr.tcleard.numberslight.scene.main.list.adapter.vm.ItemViewModel

class ItemViewHolderFactory(
        private val imageManager: IImageManager
) : AItemViewHolderFactory() {

    private val TYPE_ITEM = 1
    private val TYPE_LOADING = 2

    override fun create(parent: ViewGroup, viewType: Int): AItemViewHolder =
            when (viewType) {
                TYPE_ITEM -> ItemViewHolder(parent, imageManager)
                TYPE_LOADING -> LoadingItemViewHolder(parent)
                else -> super.create(parent, viewType)
            }

    override fun getViewType(itemViewModel: ItemViewModel): Int =
            TYPE_ITEM

    override fun getLoadingViewType(): Int =
            TYPE_LOADING

}