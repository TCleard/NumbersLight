package fr.tcleard.numberslight.core.error

import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

open class AError : Throwable() {

    companion object {

        fun create(throwable: Throwable): AError =
                when (throwable) {
                    is UnknownHostException -> NoConnectionError()
                    is TimeoutException -> TimeOutError()
                    else -> AError()
                }

    }

    open fun accept(errorVisitor: IErrorVisitor) {
        errorVisitor.visit(this)
    }

}