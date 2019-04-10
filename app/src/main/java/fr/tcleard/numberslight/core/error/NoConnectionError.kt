package fr.tcleard.numberslight.core.error

class NoConnectionError: AError() {

    override fun accept(errorVisitor: IErrorVisitor) {
        errorVisitor.visit(this)
    }

}