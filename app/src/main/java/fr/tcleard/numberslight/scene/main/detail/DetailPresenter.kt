package fr.tcleard.numberslight.scene.main.detail

import fr.tcleard.numberslight.core.extension.rx.sub
import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.core.service.IService
import javax.inject.Inject

class DetailPresenter @Inject constructor(
        private val itemService: IItemService
) : APresenter<DetailPresenter.DetailView>() {

    private var item: Item? = null

    fun setItem(item: Item) {
        if (item.name != this.item?.name) {
            this.item = item
            view?.showImage("")
            view?.showText("")
            compositeDisposable.add(
                    itemService.getItem(item.name)
                            .compose(IService.onIoToMainForSingle())
                            .sub(onSuccess = {
                                view?.showImage(it.imageUrl)
                                view?.showText(it.text)
                            })
            )
        }
    }

    interface DetailView : IView {

        fun showImage(url: String)
        fun showText(text: String)

    }

}