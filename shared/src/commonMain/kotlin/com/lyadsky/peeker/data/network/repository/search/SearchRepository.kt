package com.lyadsky.peeker.data.network.repository.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.data.model.ProductResponse
import com.lyadsky.peeker.data.network.BaseRepository
import com.lyadsky.peeker.data.storage.Storage
import com.lyadsky.peeker.models.Product
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.Json

class SearchRepository (
    private val storage: Storage
) : BaseRepository() {

    suspend fun searchProducts(offset: Int, page: Int, query: String): ProductResponse {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "product",
            parameters = mapOf(
                "name" to query,
                "offset" to offset.toString(),
                "page_number" to page.toString(),
                "filter_by" to "asc" //TODO
            ),
        )
        return Json.decodeFromString(response)
    }

    suspend fun getSearchedProduct(): Boolean = storage.getSearchedProduct()

    suspend fun setSearchedProduct(value: Boolean) = storage.setSearchedProduct(value)
}