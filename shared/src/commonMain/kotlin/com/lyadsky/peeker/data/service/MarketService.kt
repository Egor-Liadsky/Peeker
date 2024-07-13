package com.lyadsky.peeker.data.service

import com.lyadsky.peeker.data.mapper.toMap
import com.lyadsky.peeker.data.network.MarketRepository
import com.lyadsky.peeker.models.Market

class MarketService(
    private val marketRepository: MarketRepository
) {

    private val markets = mutableListOf<Market>()

    suspend fun getMarkets(): List<Market> {
        if (markets.isEmpty()) markets.addAll(marketRepository.getMarkets().map { it.toMap() })
        return markets
    }
}