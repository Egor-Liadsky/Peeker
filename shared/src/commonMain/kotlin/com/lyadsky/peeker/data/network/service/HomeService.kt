package com.lyadsky.peeker.data.network.service

import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.data.network.repository.ProductRepository
import com.lyadsky.peeker.data.network.repository.home.HomePageContext
import com.lyadsky.peeker.data.network.repository.home.HomePagerCollector
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.utils.toProduct
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource
import ru.astrainteractive.klibs.paging.state.PagingState

class HomeService(
    private val productRepository: ProductRepository,
    private val marketRepository: MarketRepository,
) {

    private val pagingCollector = HomePagerCollector(
        pager = LambdaPagedListDataSource {
            runCatching {
                val markets = marketRepository.getMarkets()
                productRepository.getProducts(it.pageContext.page).items.map { product ->
                    product.toProduct(markets.first { product.market == it.id })
                }
            }.onFailure(Throwable::printStackTrace)
        }
    )

    val pagingState: StateFlow<PagingState<Product, HomePageContext>> =
        pagingCollector.state

    suspend fun reload() {
        reset()
        loadNextPage()
    }

    private fun reset() {
        pagingCollector.update { pagingState ->
            pagingState.copy(
                pageContext = pagingState.pageContext.copy(page = 0),
                items = emptyList(),
                isLoading = false,
                isFailure = false,
                isLastPage = false
            )
        }
    }

    suspend fun loadNextPage() {
        pagingCollector.loadNextPage()
    }
}