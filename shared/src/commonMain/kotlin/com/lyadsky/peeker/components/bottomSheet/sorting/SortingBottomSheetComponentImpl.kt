package com.lyadsky.peeker.components.bottomSheet.sorting

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent

class SortingBottomSheetComponentImpl(
    componentContext: ComponentContext,
    private val onSelectSortingType: (String) -> Unit,
    private val onDismiss: () -> Unit
) : SortingBottomSheetComponent,
    BaseComponent<SortingBottomSheetState>(componentContext, SortingBottomSheetState()) {

    override fun onDismissClick() {
        onDismiss()
    }
}