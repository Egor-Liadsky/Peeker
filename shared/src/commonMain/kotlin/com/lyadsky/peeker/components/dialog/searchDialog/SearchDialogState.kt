package com.lyadsky.peeker.components.dialog.searchDialog

import com.lyadsky.peeker.models.SortingType

data class SearchDialogState(
    val searchTextField: String = "",
    val rangeFromTextField: String = "",
    val rangeToTextField: String = "",
    val selectSortingType: SortingType = SortingType.Rating,

    val searchedProduct: Boolean = true,
    val isRefreshing: Boolean = false,
    val searchAllMarketplacesCheckbox: Boolean = false,
)