package com.lyadsky.peeker.components.screen.home

import app.cash.paging.PagingData
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.data.model.ProductItem
import com.lyadsky.peeker.models.Product
import kotlinx.coroutines.flow.Flow

interface HomeComponent {

    val viewStates: Value<HomeState>

    val slotStack: Value<ChildSlot<*, SlotChild>>

    val searchDialogComponent: SlotChild

    val products: Flow<PagingData<Product>>

    fun onSearchTextFieldClick()

    fun onProductRefreshClick()

    sealed class SlotChild {
        data class SearchDialogChild(val component: SearchDialogComponent) : SlotChild()
    }
}