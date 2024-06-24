package com.lyadsky.peeker.data.network.repository.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.utils.exceptionHandleablePaging
import com.lyadsky.peeker.utils.toProduct
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.utils.io.errors.IOException

class SearchProductRepositoryPagingSource(
    private val query: String,
    private val searchRepository: SearchRepository,
    private val marketRepository: MarketRepository
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {

        if (query.isEmpty()) return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)

        val markets = marketRepository.getMarkets()
        val page = params.key ?: 0
        val pageSize = params.loadSize.coerceAtMost(30)
        val response =
            searchRepository.searchProducts(offset = pageSize, page = page, query = query)

        return exceptionHandleablePaging(
            executionBlock = {
                PagingSourceLoadResultPage(
                    data = response.items.map { product -> product.toProduct(market = markets.first { product.market == it.id }) },
                    prevKey = if (page > 0) page - 1 else null,
                    nextKey = if (response.items.size < pageSize) null else page + 1
                )
            },
            failureBlock = {
                PagingSourceLoadResultError(it)
            }
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}
