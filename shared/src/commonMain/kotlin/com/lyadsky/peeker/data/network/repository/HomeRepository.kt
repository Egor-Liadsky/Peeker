package com.lyadsky.peeker.data.network.repository

import com.lyadsky.peeker.data.model.MarketResponse
import com.lyadsky.peeker.data.model.ProductResponse
import com.lyadsky.peeker.data.network.BaseRepository
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class HomeRepository: BaseRepository() {

    suspend fun searchProducts(name: String): List<ProductResponse> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "product",
            parameters = mapOf("name" to name),
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