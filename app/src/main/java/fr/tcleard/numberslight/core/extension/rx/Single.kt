package fr.tcleard.numberslight.core.extension.rx

import fr.tcleard.numberslight.core.error.AError
import io.reactivex.Single
import io.reactivex.disposables.Disposable

fun <T> Single<T>.sub(
        onSuccess: (T) -> Unit,
        onError: ((AError) -> Unit)? = null
): Disposable =
        subscribe({
            onSuccess.invoke(it)
        }, {
            it.printStackTrace()
            onError?.invoke(AError.create(it))
        })