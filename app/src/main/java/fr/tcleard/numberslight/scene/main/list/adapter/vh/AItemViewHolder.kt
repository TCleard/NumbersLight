package fr.tcleard.numberslight.scene.main.list.adapter.vh

import android.view.ViewGroup
import fr.tcleard.numberslight.scene.main.list.adapter.vm.ItemViewModel
import fr.tcleard.numberslight.ui.adapter.AViewHolder

abstract class AItemViewHolder(
        parent: ViewGroup,
        layoutRes: Int
): AViewHolder<ItemViewModel>(parent, layoutRes)