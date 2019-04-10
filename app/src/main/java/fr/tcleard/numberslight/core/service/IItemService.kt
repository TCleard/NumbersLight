package fr.tcleard.numberslight.core.service

import fr.tcleard.numberslight.core.model.Item
import io.reactivex.Single

interface IItemService : IService {

    fun getItems(): Single<List<Item>>

    fun getItem(name: String): Single<Item>

}