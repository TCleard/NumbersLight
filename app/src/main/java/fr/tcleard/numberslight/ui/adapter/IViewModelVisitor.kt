package  fr.tcleard.numberslight.ui.adapter

interface IViewModelVisitor<T> {

    fun getValue(): T?

}