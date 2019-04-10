package fr.tcleard.numberslight.core.repo.remote.retrofit.model

import com.fasterxml.jackson.annotation.JsonProperty

class ApiItem {

    @JsonProperty
    var name: String = ""

    @JsonProperty
    var text: String = ""

    @JsonProperty
    var image: String = ""

}