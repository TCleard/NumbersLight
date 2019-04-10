package fr.tcleard.numberslight.ui.adapter

abstract class OnLoadMoreListener(
        val loadingThreshold: Int = 3
) {

    private var thresholdReached: Boolean = false

    fun onThresholdReached() {
        if (!thresholdReached) {
            thresholdReached = true
            onLoadMore()
        }
    }

    fun reset() {
        thresholdReached = false
    }

    abstract fun onLoadMore()

}