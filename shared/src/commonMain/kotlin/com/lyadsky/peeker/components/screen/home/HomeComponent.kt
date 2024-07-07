package com.lyadsky.peeker.components.screen.home

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.data.network.repository.home.HomePageContext
import com.lyadsky.peeker.models.Product
import kotlinx.coroutines.flow.StateFlow
import ru.astrainteractive.klibs.paging.state.PagingState

interface HomeComponent {

    val viewStates: Value<HomeState>

    val slotStack: Value<ChildSlot<*, SlotChild>>

    val searchDialogComponent: SlotChild

    val products: StateFlow<PagingState<Product, HomePageContext>>

    suspend fun loadNextPage()

    fun onProductsReloadClick()

    fun onSearchTextFieldClick()

    sealed class SlotChild {
        data class SearchDialogChild(val component: SearchDialogComponent) : SlotChild()
    }
}