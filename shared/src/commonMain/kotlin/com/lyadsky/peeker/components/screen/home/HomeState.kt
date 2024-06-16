package com.lyadsky.peeker.components.screen.home

import app.cash.paging.PagingData
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.utils.LoadingState
import kotlinx.coroutines.flow.MutableStateFlow

data class HomeState(
    val products: MutableStateFlow<PagingData<Product>> = MutableStateFlow(value = PagingData.empty()),
    val productsLoadingState: LoadingState = LoadingState.Loading,
    val searchTextField: String = "",
)