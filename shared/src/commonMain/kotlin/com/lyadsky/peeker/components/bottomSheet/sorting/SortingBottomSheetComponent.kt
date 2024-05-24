package com.lyadsky.peeker.components.bottomSheet.sorting

import com.arkivanov.decompose.value.Value

interface SortingBottomSheetComponent {

    val viewStates: Value<SortingBottomSheetState>

    fun onDismissClick()
}