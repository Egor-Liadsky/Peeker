package com.lyadsky.peeker.data.paging.home

import com.lyadsky.peeker.data.service.ProductService
import com.lyadsky.peeker.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import ru.astrainteractive.klibs.paging.data.LambdaPagedListDataSource
import ru.astrainteractive.klibs.paging.state.PagingState

class HomePaging(private val productService: ProductService) {

    private val pagingCollector = HomePagerCollector(
        pager = LambdaPagedListDataSource {
            runCatching { productService.getProducts(page = it.pageContext.page) }
                .onFailure(Throwable::printStackTrace)
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

    suspend fun loadNextPage() = withContext(Dispatchers.IO) {
        pagingCollector.loadNextPage()
    }
}