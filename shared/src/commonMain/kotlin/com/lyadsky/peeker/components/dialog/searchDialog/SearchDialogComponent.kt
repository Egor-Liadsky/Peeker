package com.lyadsky.peeker.components.dialog.searchDialog

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.bottomSheet.filter.FilterBottomSheetComponent
import com.lyadsky.peeker.components.bottomSheet.sorting.SortingBottomSheetComponent

interface SearchDialogComponent {

    val viewStates: Value<SearchDialogState>

    val slotStack: Value<ChildSlot<*, SlotChild>>

    val sortingBottomSheetComponent: SearchDialogComponent.SlotChild

    val filterBottomSheetComponent: SearchDialogComponent.SlotChild

    fun onRangeFromTextFieldValueChanged(value: String)

    fun onRangeToTextFieldValueChanged(value: String)

    fun onSearchAllMarketplacesCheckboxValueChanged()

    fun onSearchClick(value: String)

    fun onProductRefreshClick()

    fun onMarketsRefreshClick()

    fun onSearchTextFieldValueChanged(value: String)

    fun onClearedSearchTextField()

    fun onSortingButtonClick()

    fun onFilterButtonClick()

    fun onDismissClick()

    sealed class SlotChild {

        data class SortingBottomSheetChild(val component: SortingBottomSheetComponent) : SlotChild()

        data class FilterBottomSheetChild(val component: FilterBottomSheetComponent) : SlotChild()
    }
}