package com.lyadsky.peeker.components.bottomSheet.sorting

import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.models.Sorting

interface SortingBottomSheetComponent {

    val viewStates: Value<SortingBottomSheetState>

    fun onSelectSortingClick(sorting: Sorting)

    fun onDismissClick()
}