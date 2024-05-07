package com.lyadsky.peeker.components.screen.home

import com.lyadsky.peeker.data.model.Market
import com.lyadsky.peeker.data.model.Product
import com.lyadsky.peeker.utils.LoadingState

data class HomeState(
    val products: List<Product>? = null,
    val productsLoadingState: LoadingState = LoadingState.Loading,
    val markets: List<Market>? = null,
    val searchTextField: String = "вино",
)