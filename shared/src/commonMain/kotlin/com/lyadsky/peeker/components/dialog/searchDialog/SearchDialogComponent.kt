package com.lyadsky.peeker.components.dialog.searchDialog

import com.arkivanov.decompose.value.Value

interface SearchDialogComponent {

    val viewStates: Value<SearchDialogState>

    fun onRangeFromTextFieldValueChanged(value: String)

    fun onRangeToTextFieldValueChanged(value: String)

    fun onSearchAllMarketplacesCheckboxValueChanged()

    fun onSearchClick(value: String)

    fun onProductRefreshClick(value: String)

    fun onMarketsRefreshClick()

    fun onSearchTextFieldValueChanged(value: String)

    fun onClearedSearchTextField()

    fun onDismiss()
}