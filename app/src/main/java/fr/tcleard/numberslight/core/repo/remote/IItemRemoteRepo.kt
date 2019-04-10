package fr.tcleard.numberslight.core.repo.remote

import fr.tcleard.numberslight.core.model.Item
import io.reactivex.Single

interface IItemRemoteRepo: IRemoteRepo {

    fun getItems(): Single<List<Item>>

    fun getItem(name: String): Single<Item>

}