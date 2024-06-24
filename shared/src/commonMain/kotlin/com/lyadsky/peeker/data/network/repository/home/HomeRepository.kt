package com.lyadsky.peeker.data.network.repository.home

import com.lyadsky.peeker.data.model.ProductResponse
import com.lyadsky.peeker.data.network.BaseRepository
import com.lyadsky.peeker.data.storage.Storage
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class HomeRepository : BaseRepository() {

    suspend fun getProducts(offset: Int, page: Int): ProductResponse {
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
}