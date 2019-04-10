package fr.tcleard.numberslight.core.extension.rx

import fr.tcleard.numberslight.core.error.AError
import io.reactivex.Completable
import io.reactivex.disposables.Disposable

fun Completable.sub(
        onComplete: (() -> Unit)? = null,
        onError: ((AError) -> Unit)? = null
): Disposable =
        subscribe({
            onComplete?.invoke()
        }, {
            it.printStackTrace()
            onError?.invoke((it as? AError) ?: AError())
        })