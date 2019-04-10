package fr.tcleard.numberslight.core.repo.remote.retrofit

import fr.tcleard.numberslight.core.model.Item
import fr.tcleard.numberslight.core.repo.remote.IItemRemoteRepo
import fr.tcleard.numberslight.core.repo.remote.retrofit.model.ApiItem
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

class ItemRetrofitRemoteRepo(
        retrofit: Retrofit
) : ARetrofitRemoteRepo(), IItemRemoteRepo {

    private val client = retrofit.create(ItemClient::class.java)

    override fun getItems(): Single<List<Item>> =
            client.getItems()
                    .flatMap { apiItems ->
                        val items = apiItems.map { it.toItem() }
                        Single.just(items)
                    }

    override fun getItem(name: String): Single<Item> =
            client.getItem(name)
                    .flatMap { apiItem ->
                        Single.just(apiItem.toItem())
                    }

    private fun ApiItem.toItem(): Item =
            Item(name = name, imageUrl = image, text = text)

    interface ItemClient {

        @GET(".")
        fun getItems(): Single<List<ApiItem>>

        @GET(".")
        fun getItem(@Query("name") name: String): Single<ApiItem>

    }

}