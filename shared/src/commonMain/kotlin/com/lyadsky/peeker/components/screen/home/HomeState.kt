package com.lyadsky.peeker.components.screen.home

data class HomeState(
    val isRefreshing: Boolean = false,
    val searchTextField: String = "",
)