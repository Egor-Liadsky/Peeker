package com.lyadsky.peeker.data.network.repository

import com.lyadsky.peeker.data.model.Marketplace
import com.lyadsky.peeker.data.model.Product
import com.lyadsky.peeker.data.network.BaseRepository
import io.ktor.http.HttpMethod
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class HomeRepository: BaseRepository() {

    suspend fun searchProducts(name: String): List<Product> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "product",
            parameters = mapOf("name" to name),
        )
        return Json.decodeFromString(response)
    }

    suspend fun getMarketplaces(): List<Marketplace> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "marketplace"
        )
        return Json.decodeFromString(response)
    }
}