package com.lyadsky.peeker.data.database

import com.lyadsky.AppDatabase
import com.lyadsky.peeker.data.model.MarketResponse
import com.lyadsky.peeker.data.network.BaseRepository
import com.lyadsky.peeker.models.Market
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.Json

class MarketRepository(database: AppDatabase): BaseRepository() {

    private val queries = database.appDatabaseQueries

    suspend fun saveMarkets() {
        queries.deleteMarkets()
        val markets = getMarketsFromNetwork()
        markets.forEach { market ->
            queries.insertMarket(market.id, market.code, market.name, market.icon)
        }
    }

    suspend fun getMarkets(): List<Market> {
        var markets = queries.getMarkets().executeAsList().map { it.toMarketFromStorage() }
        if (markets.isEmpty()) {
            markets = getMarketsFromNetwork().map { it.toMarketFromResponse() }
        }
        return markets
    }

    private suspend fun getMarketsFromNetwork(): List<MarketResponse> {
        val response = executeCall(
            type = HttpMethod.Get,
            path = "market/list"
        )
        return Json.decodeFromString(response)
    }

    private fun MarketResponse.toMarketFromResponse(): Market =
        Market(
            id = this.id,
            code = this.code,
            name = this.name,
            icon = this.icon
        )

    private fun com.lyadsky.Market.toMarketFromStorage(): Market =
        Market(
            id = this.id,
            code = this.code,
            name = this.name,
            icon = this.icon
        )
}