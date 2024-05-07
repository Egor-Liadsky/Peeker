package com.lyadsky.peeker.components.screen.home

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent

interface HomeComponent {

    val viewStates: Value<HomeState>

    val slotStack: Value<ChildSlot<*, SlotChild>>

    fun onSearchTextFieldClick()

    fun onProductRefreshClick()

//    fun onSearchTextFieldValueChanged(value: String)
//
//    fun onClearedSearchTextField()

    sealed class SlotChild {
        data class SearchDialogChild(val component: SearchDialogComponent) : SlotChild()
    }
}