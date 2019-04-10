package fr.tcleard.numberslight.scene.main.list

import fr.tcleard.numberslight.core.extension.rx.sub
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.core.service.IService
import fr.tcleard.numberslight.scene.main.list.adapter.vm.ItemViewModel
import javax.inject.Inject

class ListPresenter @Inject constructor(
        private val itemService: IItemService
) : APresenter<ListPresenter.ListView>() {

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
                    view?.showItems(items.map { ItemViewModel(it) })
                })
    }

    interface ListView : IView {

        fun showLoading(loading: Boolean)

        fun showItems(items: List<ItemViewModel>)

    }

}