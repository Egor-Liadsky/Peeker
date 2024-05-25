package com.lyadsky.peeker.components.bottomSheet.filter

import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.layout.FilterLayoutComponent

interface FilterBottomSheetComponent {

    val viewStates: Value<FilterBottomSheetState>

    val filterLayoutComponent: FilterLayoutComponent

    fun onDismissClick()
}