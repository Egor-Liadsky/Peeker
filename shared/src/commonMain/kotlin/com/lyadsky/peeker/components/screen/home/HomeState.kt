package com.lyadsky.peeker.components.screen.home

import com.lyadsky.peeker.utils.LoadingState

data class HomeState(
    val productsLoadingState: LoadingState = LoadingState.Loading,
    val searchTextField: String = "",
)