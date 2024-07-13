package com.lyadsky.peeker.data.network

import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.data.model.ProductResponse
import com.lyadsky.peeker.models.SortingType
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class ProductRepository : BaseRepository() {

    suspend fun getProducts(page: Int): ProductResponse = withContext(Dispatchers.IO) {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "main/",
            parameters = mapOf(
                "offset" to BuildKonfig.PAGING_OFFSET.toString(),
                "page_number" to page.toString()
            )
        )
        Json.decodeFromString(response)
    }

    suspend fun searchProducts(
        page: Int,
        query: String,
        sortingType: SortingType
    ): ProductResponse = withContext(Dispatchers.IO) {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "product/",
            parameters = mapOf(
                "name" to query,
                "offset" to BuildKonfig.PAGING_OFFSET.toString(),
                "page_number" to page.toString(),
                "filter_by" to "asc",
                "filter_name" to sortingType.name.lowercase()
            ),
        )
        Json.decodeFromString(response)
    }
}