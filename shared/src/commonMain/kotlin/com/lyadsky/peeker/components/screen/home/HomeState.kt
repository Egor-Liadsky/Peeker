package com.lyadsky.peeker.components.screen.home

import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.utils.LoadingState

data class HomeState(
    val products: List<Product>? = null,
    val productsLoadingState: LoadingState = LoadingState.Loading,
    val searchTextField: String = "",
)