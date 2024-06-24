package com.lyadsky.peeker.data.network.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lyadsky.peeker.BuildKonfig
import com.lyadsky.peeker.data.database.MarketRepository
import com.lyadsky.peeker.data.network.repository.search.SearchProductRepositoryPagingSource
import com.lyadsky.peeker.data.network.repository.search.SearchRepository
import com.lyadsky.peeker.models.Product
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class SearchService(
    private val searchRepository: SearchRepository,
) : KoinComponent {

    suspend fun getSearchedProduct(): Boolean = searchRepository.getSearchedProduct()

    suspend fun setSearchedProduct(value: Boolean) = searchRepository.setSearchedProduct(value)
}