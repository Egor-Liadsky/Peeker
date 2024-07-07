package com.lyadsky.peeker.components.bottomSheet.sorting

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.models.SortingType

class SortingBottomSheetComponentImpl(
    componentContext: ComponentContext,
    private val selectedSortingType: SortingType,
    private val onSelectSortingType: (SortingType) -> Unit,
    private val onDismiss: () -> Unit
) : SortingBottomSheetComponent,
    BaseComponent<SortingBottomSheetState>(componentContext, SortingBottomSheetState()) {

    init {
        viewState = viewState.copy(selectSorting = selectedSortingType)
    }

    override fun onSelectSortingClick(sortingType: SortingType) {
        viewState = viewState.copy(selectSorting = sortingType)
    }

    override fun onApplyClick() {
        onSelectSortingType(viewState.selectSorting)
        onDismiss()
    }

    override fun onDismissClick() {
        onDismiss()
    }
}