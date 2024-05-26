package com.lyadsky.peeker.data.network.services

import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.data.model.ProductItem
import com.lyadsky.peeker.data.network.repository.HomeRepository
import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.Product

class HomeService(
    private val homeRepository: HomeRepository,
    private val marketRepository: MarketRepository,
) {

    suspend fun getProducts(): List<Product> {
        val markets = marketRepository.getMarkets()
        return homeRepository.getProducts().items.map { product ->
            val market = markets.first { it.id == product.market }
            product.toProduct(market)
        }
    }

    suspend fun searchProducts(name: String): List<Product> {
        val markets = marketRepository.getMarkets()
        return homeRepository.searchProducts(name).items.map { product ->
            val market = markets.first { it.id == product.market }
            product.toProduct(market)
        }
    }

    suspend fun saveMarkets() {
        marketRepository.saveMarkets()
    }

    suspend fun getMarkets(): List<Market> = marketRepository.getMarkets()

    suspend fun getSearchedProduct(): Boolean = homeRepository.getSearchedProduct()

    suspend fun setSearchedProduct(value: Boolean) = homeRepository.setSearchedProduct(value)

    private fun ProductItem.toProduct(market: Market) =
        Product(
            market = Market(market.id, market.code, market.name, market.icon),
            item_id = this.item_id,
            name = this.name,
            url = this.url,
            price = this.price,
            rating = this.rating,
            image = this.picture,
            time_ship = this.time_ship,
            datetime_ship = this.datetime_ship,
            geo = this.geo
        )
}