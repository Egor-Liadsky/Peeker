package com.lyadsky.peeker.data.network.repository

import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.data.model.ProductResponse
import com.lyadsky.peeker.data.network.BaseRepository
import com.lyadsky.peeker.data.storage.Storage
import com.lyadsky.peeker.models.SortingType
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class ProductRepository(
    private val storage: Storage
) : BaseRepository() {

    suspend fun getProducts(page: Int): ProductResponse {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "main",
            parameters = mapOf(
                "offset" to BuildKonfig.PAGING_OFFSET.toString(),
                "page_number" to page.toString()
            )
        )
        return Json.decodeFromString(response)
    }

    suspend fun searchProducts(
        query: String,
        page: Int,
        sortingType: SortingType
    ): ProductResponse {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "product",
            parameters = mapOf(
                "name" to query,
                "offset" to BuildKonfig.PAGING_OFFSET.toString(),
                "page_number" to page.toString(),
                "filter_by" to "asc",
                "filter_name" to sortingType.name.lowercase()
            ),
        )
        return Json.decodeFromString(response)
    }

    suspend fun getSearchedProduct(): Boolean = storage.getSearchedProduct()

    suspend fun setSearchedProduct(value: Boolean) = storage.setSearchedProduct(value)
}