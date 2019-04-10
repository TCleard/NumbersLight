package fr.tcleard.numberslight.core.error

class TimeOutError: AError() {

    override fun accept(errorVisitor: IErrorVisitor) {
        errorVisitor.visit(this)
    }

}