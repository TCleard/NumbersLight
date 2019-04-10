package fr.tcleard.numberslight.core.service.impl

import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.core.repo.remote.IItemRemoteRepo
import fr.tcleard.numberslight.core.service.IItemService
import io.reactivex.Single
import javax.inject.Inject

class ItemService @Inject constructor(
        private val remoteRepo: IItemRemoteRepo
) : AService(), IItemService {

    override fun getItems(): Single<List<Item>> =
            remoteRepo.getItems()

    override fun getItem(name: String): Single<Item> =
            remoteRepo.getItem(name)

}