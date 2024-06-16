package com.lyadsky.peeker.data.network.repository

import com.lyadsky.peeker.data.model.ProductResponse
import com.lyadsky.peeker.data.network.BaseRepository
import com.lyadsky.peeker.data.storage.Storage
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class HomeRepository(
    private val storage: Storage
) : BaseRepository() {

    suspend fun getProducts(): ProductResponse {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "main",
            parameters = mapOf("offset" to "30")
        )
        return Json.decodeFromString(response)
    }

    suspend fun getProductsPaging(offset: Int, page: Int): ProductResponse {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "main",
            parameters = mapOf(
                "offset" to offset.toString(),
                "page_number" to page.toString()
            )
        )
        return Json.decodeFromString(response)
    }

    suspend fun searchProducts(name: String): ProductResponse {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "product",
            parameters = mapOf(
                "name" to name,
                "offset" to "30"
            ),
        )
        return Json.decodeFromString(response)
    }

    suspend fun getSearchedProduct(): Boolean = storage.getSearchedProduct()

    suspend fun setSearchedProduct(value: Boolean) = storage.setSearchedProduct(value)
}