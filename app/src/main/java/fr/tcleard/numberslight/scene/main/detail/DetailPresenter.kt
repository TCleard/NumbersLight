package fr.tcleard.numberslight.scene.main.detail

import fr.tcleard.numberslight.core.error.DisplayError
import fr.tcleard.numberslight.core.error.ErrorDomain
import fr.tcleard.numberslight.core.error.IErrorVisitor
import fr.tcleard.numberslight.core.extension.rx.sub
import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.core.presenter.APresenter
import fr.tcleard.numberslight.core.presenter.IView
import fr.tcleard.numberslight.core.service.IItemService
import fr.tcleard.numberslight.core.service.IService
import javax.inject.Inject

class DetailPresenter @Inject constructor(
        private val itemService: IItemService,
        private val errorVisitor: IErrorVisitor
) : APresenter<DetailPresenter.DetailView>() {

    private var name = ""
    private var loading = false
    private var loaded = false

    fun setItem(item: Item) {
        if (item.name != name || (!loading && !loaded)) {
            compositeDisposable.clear()
            this.name = item.name
            view?.showImage("")
            view?.showText("")
            loadItem()
        }
    }

    fun retry() {
        loadItem()
    }

    private fun loadItem() {
        loaded = false
        loading = true
        view?.showLoading(true)
        compositeDisposable.add(
                itemService.getItem(name)
                        .compose(IService.onIoToMainForSingle())
                        .doAfterTerminate {
                            loading = false
                            view?.showLoading(false)
                        }
                        .sub(onSuccess = {
                            loaded = true
                            view?.showImage(it.imageUrl)
                            view?.showText(it.text)
                        }, onError = { error ->
                            error.accept(errorVisitor)
                            view?.showError(errorVisitor.getDisplayError(ErrorDomain.DETAIL))
                        })
        )
    }

    interface DetailView : IView {

        fun showLoading(loading: Boolean)

        fun showImage(url: String)
        fun showText(text: String)

        fun showError(error: DisplayError)

    }

}