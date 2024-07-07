package com.lyadsky.peeker.data.network.service

import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.data.network.repository.ProductRepository
import com.lyadsky.peeker.data.network.repository.search.SearchPageContext
import com.lyadsky.peeker.data.network.repository.search.SearchPagerCollector
import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.models.SortingType
import com.lyadsky.peeker.utils.toProduct
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.paging.PagingCollectorExt.updatePageContext
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource
import ru.astrainteractive.klibs.paging.state.PagingState

class SearchService(
    private val marketRepository: MarketRepository,
    private val productRepository: ProductRepository
) {

    private val pagingCollector = SearchPagerCollector(
        pager = LambdaPagedListDataSource {
            runCatching {
                if (it.pageContext.query.isNotEmpty()) {
                    val markets = marketRepository.getMarkets()
                    productRepository.searchProducts(
                        page = it.pageContext.page,
                        query = it.pageContext.query,
                        sortingType = it.pageContext.sortingType
                    ).items.map { product ->
                        product.toProduct(markets.first { product.market == it.id })
                    }
                } else {
                    emptyList()
                }
            }.onFailure(Throwable::printStackTrace)
        }
    )

    val pagingState: StateFlow<PagingState<Product, SearchPageContext>> =
        pagingCollector.state

    suspend fun reload() {
        reset()
        loadNextPage()
    }

    fun reset() {
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

    fun updateQuery(query: String) {
        pagingCollector.updatePageContext { pageContext -> pageContext.copy(query = query) }
        reset()
    }

    fun updateSortingType(sortingType: SortingType) {
        pagingCollector.updatePageContext { pageContext -> pageContext.copy(sortingType = sortingType) }
    }

    suspend fun loadNextPage() {
        pagingCollector.loadNextPage()
    }


    suspend fun getMarkets(): List<Market> = marketRepository.getMarkets()

    suspend fun getSearchedProduct(): Boolean = productRepository.getSearchedProduct()

    suspend fun setSearchedProduct(value: Boolean) = productRepository.setSearchedProduct(value)
}