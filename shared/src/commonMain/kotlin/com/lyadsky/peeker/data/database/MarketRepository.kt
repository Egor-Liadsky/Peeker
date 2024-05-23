package com.lyadsky.peeker.data.database

import com.lyadsky.AppDatabase
import com.lyadsky.peeker.data.model.MarketResponse
import com.lyadsky.peeker.models.Market

class MarketRepository(database: AppDatabase) {

    private val queries = database.appDatabaseQueries

    fun saveMarkets(markets: List<Market>) {
        queries.deleteMarkets()
        markets.forEach { market ->
            queries.insertMarket(market.id, market.code, market.name, market.icon)
        }
    }

    fun getMarkets(): List<Market> = queries.getMarkets().executeAsList().map { market ->
        Market(market.id, market.code, market.name, market.icon)
    }
}