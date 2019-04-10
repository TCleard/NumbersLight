package fr.tcleard.numberslight.core.model

class Item(
        val name: String,
        val imageUrl: String,
        var text: String = ""
) : AModel() {

    init {
        id = name
    }

}