package com.lyadsky.peeker.data.network.services

import com.lyadsky.peeker.data.model.Market
import com.lyadsky.peeker.data.model.Product
import com.lyadsky.peeker.data.network.repository.HomeRepository
import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.data.storage.Storage

class HomeService(
    private val homeRepository: HomeRepository,
    private val marketRepository: MarketRepository,
    private val storage: Storage
) {

    suspend fun searchProducts(name: String): List<Product> {
        val markets = marketRepository.getMarkets()
        return homeRepository.searchProducts(name).map { product ->
            val market = markets.first { it.id == product.market }
            Product(
                market = Market(market.id, market.code, market.name, market.icon),
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
        val markets = homeRepository.getMarketplaces().map { market ->
            Market(market.id, market.code, market.name, market.icon)
        }
        marketRepository.saveMarkets(markets)
    }

    fun getMarkets(): List<Market> = marketRepository.getMarkets()

    suspend fun getSearchedProduct(): Boolean = storage.getSearchedProduct()

    suspend fun setSearchedProduct(value: Boolean) = storage.setSearchedProduct(value)
}