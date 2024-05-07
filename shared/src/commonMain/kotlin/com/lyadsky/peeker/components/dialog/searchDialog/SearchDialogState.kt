package com.lyadsky.peeker.components.dialog.searchDialog

import com.lyadsky.peeker.data.model.Market
import com.lyadsky.peeker.data.model.Product
import com.lyadsky.peeker.utils.LoadingState

data class SearchDialogState(

    val searchedProduct: Boolean = true,

    val products: List<Product>? = null,
    val productsLoadingState: LoadingState = LoadingState.Loading,

    val markets: List<Market>? = null,
    val marketsLoadingState: LoadingState = LoadingState.Loading,

    val rangeFromTextField: String = "",
    val rangeToTextField: String = "",

    val searchAllMarketplacesCheckbox: Boolean = false,
)