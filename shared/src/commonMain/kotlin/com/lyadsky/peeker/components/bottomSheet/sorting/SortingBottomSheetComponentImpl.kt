package com.lyadsky.peeker.components.bottomSheet.sorting

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.models.Sorting
import com.lyadsky.peeker.models.SortingType

class SortingBottomSheetComponentImpl(
    componentContext: ComponentContext,
    private val onSelectSortingType: (SortingType) -> Unit,
    private val onDismiss: () -> Unit
) : SortingBottomSheetComponent,
    BaseComponent<SortingBottomSheetState>(componentContext, SortingBottomSheetState()) {

    override fun onSelectSortingClick(sorting: Sorting) {
        viewState = viewState.copy(selectSorting = sorting)
    }

    override fun onApplyClick() {
        if (viewState.selectSorting != null) {
            onSelectSortingType(viewState.selectSorting!!.type)
            onDismiss()
        }
    }

    override fun onDismissClick() {
        onDismiss()
    }
}