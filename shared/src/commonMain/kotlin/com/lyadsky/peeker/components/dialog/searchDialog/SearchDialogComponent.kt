package com.lyadsky.peeker.components.dialog.searchDialog

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.bottomSheet.filter.FilterBottomSheetComponent
import com.lyadsky.peeker.components.bottomSheet.sorting.SortingBottomSheetComponent
import com.lyadsky.peeker.components.layout.FilterLayoutComponent
import com.lyadsky.peeker.data.paging.search.SearchPageContext
import com.lyadsky.peeker.models.Product
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.paging.state.PagingState

interface SearchDialogComponent {

    val viewStates: Value<SearchDialogState>

    val slotStack: Value<ChildSlot<*, SlotChild>>

    val pagingState: StateFlow<PagingState<Product, SearchPageContext>>

    val sortingBottomSheetComponent: SlotChild

    val filterBottomSheetComponent: SlotChild

    val filterLayoutComponent: FilterLayoutComponent

    suspend fun loadNextPage()

    fun onProductsReloadClick()

    fun onRefresh()

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