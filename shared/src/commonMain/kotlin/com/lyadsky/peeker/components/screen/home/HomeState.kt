package com.lyadsky.peeker.components.screen.home

import com.lyadsky.peeker.data.model.Product
import com.lyadsky.peeker.utils.LoadingState

data class HomeState(
    val products: List<Product>? = null,
    val productsLoadingState: LoadingState = LoadingState.Loading,

    val searchTextField: String = "",
    val rangeFromTextField: String = "",
    val rangeToTextField: String = "",

    val searchAllMarketplacesCheckbox: Boolean = false,
)