package com.lyadsky.peeker.components.bottomSheet.filter

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent

class FilterBottomSheetComponentImpl(
    componentContext: ComponentContext,
    private val onDismiss: () -> Unit
) : FilterBottomSheetComponent,
    BaseComponent<FilterBottomSheetState>(componentContext, FilterBottomSheetState()) {

    override fun onDismissClick() {
        onDismiss()
    }
}