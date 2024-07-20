package com.lyadsky.peeker.data.paging.search

import com.lyadsky.peeker.data.service.ProductService
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.models.SortingType
import kotlinx.coroutines.flow.StateFlow
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
                        sortingType = it.pageContext.sortingType
                    )
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
}