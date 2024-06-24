package com.lyadsky.peeker.data.network.service

import androidx.paging.Pager
import app.cash.paging.PagingConfig
import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.data.network.repository.home.HomeRepository
import com.lyadsky.peeker.data.network.repository.home.ProductRepositoryPagingSource
import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.Product

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
                initialLoadSize = BuildKonfig.PAGING_OFFSET,
//                maxSize = 30,
//                prefetchDistance = 30
            ),
            pagingSourceFactory = { productRepositoryPagingSource }
        )

    suspend fun getMarkets(): List<Market> = marketRepository.getMarkets()
}