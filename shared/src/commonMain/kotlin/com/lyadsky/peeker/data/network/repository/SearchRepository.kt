package com.lyadsky.peeker.data.network.repository

import com.lyadsky.peeker.data.model.Product
import com.lyadsky.peeker.data.network.BaseRepository
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class SearchRepository: BaseRepository() {

    suspend fun getProducts(name: String): List<Product> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "product",
            parameters = mapOf("name" to name),
        )
        return Json.decodeFromString(response)
    }
}