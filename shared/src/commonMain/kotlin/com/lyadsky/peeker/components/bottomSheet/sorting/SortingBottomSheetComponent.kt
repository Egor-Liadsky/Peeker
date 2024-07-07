package com.lyadsky.peeker.components.bottomSheet.sorting

import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.models.SortingType

interface SortingBottomSheetComponent {

    val viewStates: Value<SortingBottomSheetState>

    fun onSelectSortingClick(sortingType: SortingType)

    fun onApplyClick()

    fun onDismissClick()
}