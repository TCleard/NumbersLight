package fr.tcleard.numberslight.ui.adapter

import android.support.v7.widget.RecyclerView

abstract class AItemAdapter<I, VH : AViewHolder<I>> : RecyclerView.Adapter<VH>() {

    protected val items = arrayListOf<I>()

    protected var isLoading = false
    protected var isEndless = false
        set(value) {
            if (field != value) {
                field = value
                if (value) {
                    notifyItemInserted(itemCount - 1)
                } else {
                    notifyItemRemoved(itemCount)
                }
            }
        }

    protected var onLoadMoreListener: OnLoadMoreListener? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (position < items.size) {
            holder.bind(items[position])
        }
        onLoadMoreListener?.let {
            if (isEndless && it.loadingThreshold > itemCount - position) {
                it.onThresholdReached()
            }
        }
    }

    override fun getItemCount(): Int =
            when {
                isLoading -> 2
                isEndless -> items.size + 1
                else -> items.size
            }

    open fun getSpanSize(position: Int): Int {
        return getSpanCount()
    }

    open fun getSpanCount(): Int {
        return 1
    }

    fun setEndlessMode(isEndless: Boolean) {
        this.isEndless = isEndless
    }

    fun setOnLoadMoreListener(block: () -> Unit) {
        onLoadMoreListener = object : OnLoadMoreListener() {
            override fun onLoadMore() {
                block.invoke()
            }
        }
    }

    fun resetOnLoadMoreListener() {
        onLoadMoreListener?.reset()
    }

    open fun setItems(vararg items: I, isLoading: Boolean = false) {
        val previousCount = itemCount
        this.isLoading = isLoading
        this.items.clear()
        this.items.addAll(items)
        when {
            previousCount > itemCount -> {
                notifyItemRangeChanged(0, itemCount)
                notifyItemRangeRemoved(itemCount, previousCount - itemCount)
            }
            previousCount < itemCount -> {
                notifyItemRangeChanged(0, previousCount)
                notifyItemRangeInserted(previousCount, itemCount - previousCount)
            }
            else -> notifyItemRangeChanged(0, itemCount)
        }
    }

    open fun addItems(vararg items: I, position: Int? = null) {
        if (items.isNotEmpty()) {
            if (position != null) {
                this.items.addAll(position, items.toList())
                notifyItemRangeInserted(position, items.size)
            } else {
                val previousCount = itemCount
                this.items.addAll(items)
                notifyItemRangeInserted(previousCount, itemCount - previousCount)
            }
        }
    }

    fun removeAll(isLoading: Boolean = false) {
        val previousCount = itemCount
        this.isLoading = isLoading
        this.items.clear()
        when {
            previousCount > itemCount -> {
                notifyItemRangeChanged(0, itemCount)
                notifyItemRangeRemoved(itemCount, previousCount - itemCount)
            }
            previousCount < itemCount -> {
                notifyItemRangeChanged(0, previousCount)
                notifyItemRangeInserted(previousCount, itemCount - previousCount)
            }
            else -> notifyItemRangeChanged(0, itemCount)
        }
    }

}