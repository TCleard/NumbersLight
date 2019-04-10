package fr.tcleard.numberslight.core.extension.rx

import fr.tcleard.numberslight.core.error.AError
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

fun <T> Observable<T>.sub(
        onNext: (T) -> Unit,
        onComplete: (() -> Unit)? = null,
        onError: ((AError) -> Unit)? = null
): Disposable =
        subscribe({
            onNext.invoke(it)
        }, {
            it.printStackTrace()
            onError?.invoke((it as? AError) ?: AError())
        }, {
            onComplete?.invoke()
        })