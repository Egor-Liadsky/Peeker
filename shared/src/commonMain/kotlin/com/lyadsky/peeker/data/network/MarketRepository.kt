package com.lyadsky.peeker.data.network

import com.lyadsky.peeker.data.model.MarketResponse
import io.ktor.http.HttpMethod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class MarketRepository : BaseRepository() {

    suspend fun getMarkets(): List<MarketResponse> = withContext(Dispatchers.IO) {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "market/list/"
        )
        Json.decodeFromString(response)
    }
}