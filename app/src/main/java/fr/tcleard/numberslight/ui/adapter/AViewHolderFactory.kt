package fr.tcleard.numberslight.ui.adapter

import android.view.ViewGroup

abstract class AViewHolderFactory<VH : AViewHolder<*>> {

    open fun create(parent: ViewGroup, viewType: Int): VH {
        throw Throwable("viewType $viewType not handled")
    }

}