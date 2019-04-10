package fr.tcleard.numberslight.core.error

interface IErrorVisitor {

    var type: ErrorType

    fun visit(error: NoConnectionError)
    fun visit(error: TimeOutError)
    fun visit(error: Throwable)

    fun getDisplayError(domain: ErrorDomain): DisplayError

}