package com.lyadsky.peeker.data.network.services

import com.lyadsky.peeker.data.model.Market
import com.lyadsky.peeker.data.model.Product
import com.lyadsky.peeker.data.network.repository.HomeRepository
import com.lyadsky.peeker.data.storage.MarketRepository

class HomeService(
    private val homeRepository: HomeRepository,
    private val marketRepository: MarketRepository
) {

    suspend fun searchProducts(name: String): List<Product> {
        val markets = marketRepository.getMarkets()
        return homeRepository.searchProducts(name).map { product ->
            val market = markets.first { it.id == product.market }
            Product(
                market = Market(id = market.id, name = market.name, icon = market.icon),
                item_id = product.item_id,
                name = product.name,
                url = product.url,
                price = product.price,
                photo = product.photo,
                time_ship = product.time_ship,
                datetime_ship = product.datetime_ship,
                geo = product.geo
            )
        }
    }

    suspend fun saveMarkets() {
        val markets = homeRepository.getMarketplaces().markets.map { market ->
            Market(market.id, market.name, market.photo)
        }
        marketRepository.saveMarkets(markets)
    }

    fun getMarkets(): List<Market> = marketRepository.getMarkets()
}