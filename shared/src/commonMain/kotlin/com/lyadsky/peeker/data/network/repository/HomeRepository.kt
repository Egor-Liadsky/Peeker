package com.lyadsky.peeker.data.network.repository

import com.lyadsky.peeker.data.model.MarketResponse
import com.lyadsky.peeker.data.model.ProductItem
import com.lyadsky.peeker.data.model.ProductResponse
import com.lyadsky.peeker.data.network.BaseRepository
import com.lyadsky.peeker.models.Product
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class HomeRepository : BaseRepository() {

    suspend fun getProducts(): ProductResponse {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "main",
            parameters = mapOf("offset" to "30")
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

    suspend fun getMarketplaces(): List<MarketResponse> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "market/list"
        )
        return Json.decodeFromString(response)
    }
}