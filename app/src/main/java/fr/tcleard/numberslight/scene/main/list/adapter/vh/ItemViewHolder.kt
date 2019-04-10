package fr.tcleard.numberslight.scene.main.list.adapter.vh

import android.view.View
import android.view.ViewGroup
import fr.tcleard.numberslight.R
import fr.tcleard.numberslight.core.manager.IImageManager
import fr.tcleard.numberslight.scene.main.list.adapter.vm.ItemViewModel
import fr.tcleard.numberslight.ui.adapter.AViewHolder
import kotlinx.android.synthetic.main.itemview_item.view.*

class ItemViewHolder(
        parent: ViewGroup,
        private val imageManager: IImageManager
) : AViewHolder<ItemViewModel>(parent, R.layout.itemview_item) {

    private var imageRequest: IImageManager.ImageRequest? = null

    init {
        itemView.setOnClickListener {
            item?.apply {
                onClicked()
                itemView.isSelected = isSelected
            }
        }
    }

    override fun bind(item: ItemViewModel) {
        super.bind(item)

        itemView.isSelected = item.isSelected

        if (imageRequest?.url != item.getImageUrl()) {
            imageRequest?.cancel()
            itemView.itemImage.setImageBitmap(null)
            itemView.itemImageProgress.visibility = View.VISIBLE
            imageRequest = if (item.getImageUrl().isNotBlank()) {
                imageManager.loadImage(item.getImageUrl(), itemView.itemImage, onFinished = {
                    itemView.itemImageProgress.visibility = View.GONE
                })
            } else {
                itemView.itemImageProgress.visibility = View.GONE
                null
            }
        }

        itemView.itemName.text = item.getName()

    }

}