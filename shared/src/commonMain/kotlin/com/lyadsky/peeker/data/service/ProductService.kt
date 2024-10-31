package com.lyadsky.peeker.data.service

import com.lyadsky.peeker.data.mapper.toMap
import com.lyadsky.peeker.data.network.ProductRepository
import com.lyadsky.peeker.data.storage.SearchStorageRepository
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.models.SortingType
import kotlin.coroutines.cancellation.CancellationException

class ProductService(
    private val productRepository: ProductRepository,
    private val marketService: MarketService,
    private val searchStorageRepository: SearchStorageRepository
) {

    suspend fun getProducts(page: Int): List<Product> {
        val markets = marketService.getMarkets()
        return productRepository.getProducts(page).items.map { product ->
            product.toMap(markets.first { it.id == product.market })
        }
    }

    suspend fun getProducts(
        page: Int,
        query: String,
        sortingType: SortingType,
        priceFrom: String?,
        priceTo: String?,
        marketsFilter: String?
    ): List<Product> {
        return try {
            val markets = marketService.getMarkets()
            productRepository.searchProducts(
                page,
                query,
                sortingType,
                priceFrom,
                priceTo,
                marketsFilter
            ).items.map { product ->
                product.toMap(markets.first { it.id == product.market })
            }
        } catch (e: CancellationException) {
            listOf(Product()) // Костыль. При отмене джобы выбрасывается исключение, которое устанавливает ErrorLayout
        }
    }

    suspend fun getSearchedProduct(): Boolean = searchStorageRepository.getSearchedProduct()

    suspend fun setSearchedProduct(value: Boolean) =
        searchStorageRepository.setSearchedProduct(value)
}