package com.lyadsky.peeker.data.network.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.utils.exceptionHandleable
import com.lyadsky.peeker.utils.toProduct

class ProductRepositoryPagingSource(
    private val homeRepository: HomeRepository,
    private val marketRepository: MarketRepository
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val markets = marketRepository.getMarkets()
        val page = params.key ?: 0
        val offset = params.loadSize
        val response = homeRepository.getProductsPaging(offset = offset, page = page)
        val prevKey = if (page > 1) page - 1 else null
        val nextKey = page + 1

        return try {
            PagingSourceLoadResultPage(
                data = response.items.map { product -> product.toProduct(market = markets.first { product.market == it.id }) },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (throwable: Throwable) {
            PagingSourceLoadResultError(throwable)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = state.anchorPosition
}