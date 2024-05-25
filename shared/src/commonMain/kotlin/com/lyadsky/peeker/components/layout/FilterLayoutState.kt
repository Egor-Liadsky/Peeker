package com.lyadsky.peeker.components.layout

import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.utils.LoadingState

data class FilterLayoutState(
    val markets: List<Market>? = null,
    val marketsLoadingState: LoadingState = LoadingState.Loading,

    val rangeFromTextField: String = "",
    val rangeToTextField: String = "",

    val searchAllMarketplacesCheckbox: Boolean = false,
)