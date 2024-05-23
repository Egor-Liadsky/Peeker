package com.lyadsky.peeker.components.dialog.searchDialog

import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.Product
import com.lyadsky.peeker.utils.EmptyType
import com.lyadsky.peeker.utils.LoadingState

data class SearchDialogState(
    val searchTextField: String = "",

    val searchedProduct: Boolean = true,

    val products: List<Product>? = null,
    val productsLoadingState: LoadingState = LoadingState.Empty(EmptyType.EmptyTextField),

    val markets: List<Market>? = null,
    val marketsLoadingState: LoadingState = LoadingState.Loading,

    val rangeFromTextField: String = "",
    val rangeToTextField: String = "",

    val searchAllMarketplacesCheckbox: Boolean = false,
)