package fr.tcleard.numberslight.core.error

class ErrorVisitor : IErrorVisitor {

    override var type: ErrorType = ErrorType.DEFAULT

    override fun visit(error: NoConnectionError) {
        type = ErrorType.NO_CONNECTION
    }

    override fun visit(error: TimeOutError) {
        type = ErrorType.TIME_OUT
    }

    override fun visit(error: Throwable) {
        type = ErrorType.DEFAULT
    }

    override fun getDisplayError(domain: ErrorDomain): DisplayError =
            DisplayError(domain, type)

}