package com.lyadsky.peeker.data.paging.search

import com.lyadsky.peeker.data.service.ProductService
import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.models.SortingType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.paging.PagingCollectorExt.updatePageContext
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource
import ru.astrainteractive.klibs.paging.state.PagingState

class SearchPaging(private val productService: ProductService) {

    private val pagingCollector = SearchPagerCollector(
        pager = LambdaPagedListDataSource {
            runCatching {
                if (it.pageContext.query.isNotEmpty()) {
                    productService.getProducts(
                        page = it.pageContext.page,
                        query = it.pageContext.query,
                        sortingType = it.pageContext.sortingType,
                        priceFrom = it.pageContext.priceFrom,
                        priceTo = it.pageContext.priceTo,
                        marketsFilter = it.pageContext.marketsFilter
                    )
                } else {
                    emptyList()
                }
            }.onFailure(Throwable::printStackTrace)
        },
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

    fun updatePriceFilter(priceFrom: String, priceTo: String) {
        pagingCollector.updatePageContext { pageContext ->
            pageContext.copy(
                priceFrom = priceFrom,
                priceTo = priceTo
            )
        }
    }

    fun updateMarketsFilter(marketsFilter: List<Market>) {
        pagingCollector.updatePageContext { pageContext ->
            pageContext.copy(
                marketsFilter = marketsFilter
            )
        }
    }

    suspend fun loadNextPage() = withContext(Dispatchers.IO) {
        pagingCollector.loadNextPage()
    }
}