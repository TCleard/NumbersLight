package fr.tcleard.numberslight.core.service

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface IService {

    companion object {

        fun <T> onIoToMainForObs(): ObservableTransformer<T, T> =
                ObservableTransformer {
                    it.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }

        fun <T> onIoToMainForSingle(): SingleTransformer<T, T> =
                SingleTransformer {
                    it.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }

        fun onIoToMainForComp(): CompletableTransformer =
                CompletableTransformer {
                    it.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }

    }

}