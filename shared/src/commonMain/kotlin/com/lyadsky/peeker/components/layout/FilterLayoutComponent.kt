package com.lyadsky.peeker.components.layout

import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.models.Market

interface FilterLayoutComponent {

    val viewStates: Value<FilterLayoutState>

    fun onRangeFromTextFieldValueChanged(value: String)

    fun onRangeToTextFieldValueChanged(value: String)

    fun onSearchAllMarketplacesCheckboxValueChanged()

    fun onApplyClick()

    fun onMarketsRefreshClick()

    fun onSelectMarketClick(market: Market)
}