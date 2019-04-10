package fr.tcleard.numberslight.scene.main.list

import fr.tcleard.numberslight.core.extension.rx.sub
import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.core.service.IService
import fr.tcleard.numberslight.scene.main.list.adapter.vm.ItemViewModel
import javax.inject.Inject

class ListPresenter @Inject constructor(
        private val itemService: IItemService
) : APresenter<ListPresenter.ListView>() {

    private var selectedItem: ItemViewModel? = null

    override fun attach(view: ListView) {
        super.attach(view)
        getItems()
    }

    fun getItems() {
        view?.showLoading(true)
        itemService.getItems()
                .compose(IService.onIoToMainForSingle())
                .doAfterTerminate { view?.showLoading(false) }
                .sub(onSuccess = { items ->
                    view?.showItems(items.map { item ->
                        ItemViewModel(item) { clickedItem ->
                            if (clickedItem != selectedItem) {
                                selectedItem?.let {
                                    it.isSelected = false
                                    view?.updateItem(it)
                                }
                                selectedItem = clickedItem
                            }
                            view?.onItemClicked(clickedItem.getModel())
                        }
                    })
                })
    }

    interface ListView : IView {

        fun showLoading(loading: Boolean)

        fun showItems(items: List<ItemViewModel>)
        fun updateItem(item: ItemViewModel)

        fun onItemClicked(item: Item)

    }

}