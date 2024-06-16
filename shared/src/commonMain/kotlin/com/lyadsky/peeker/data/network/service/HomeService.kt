package com.lyadsky.peeker.data.network.service

import androidx.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.data.network.repository.HomeRepository
import com.lyadsky.peeker.data.network.repository.ProductRepositoryPagingSource
import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.utils.toProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class HomeService(
    private val homeRepository: HomeRepository,
    private val marketRepository: MarketRepository,
    private val productRepositoryPagingSource: ProductRepositoryPagingSource
) {

    val productPager: Pager<Int, Product> =
        Pager(
            initialKey = BuildKonfig.PAGING_INITIAL_PAGE,
            config = PagingConfig(
                pageSize = BuildKonfig.PAGING_OFFSET,
                initialLoadSize = BuildKonfig.PAGING_OFFSET
            ),
            pagingSourceFactory = { productRepositoryPagingSource }
        )

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
}