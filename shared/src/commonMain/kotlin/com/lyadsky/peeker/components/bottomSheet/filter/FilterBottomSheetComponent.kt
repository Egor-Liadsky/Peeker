package com.lyadsky.peeker.components.bottomSheet.filter

import com.arkivanov.decompose.value.Value

interface FilterBottomSheetComponent {

    val viewStates: Value<FilterBottomSheetState>

    fun onDismissClick()
}