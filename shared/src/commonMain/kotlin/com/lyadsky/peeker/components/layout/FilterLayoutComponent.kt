package com.lyadsky.peeker.components.layout

import com.arkivanov.decompose.value.Value

interface FilterLayoutComponent {

    val viewStates: Value<FilterLayoutState>

    fun onRangeFromTextFieldValueChanged(value: String)

    fun onRangeToTextFieldValueChanged(value: String)

    fun onSearchAllMarketplacesCheckboxValueChanged()

    fun onApplyClick()

    fun onMarketsRefreshClick()
}