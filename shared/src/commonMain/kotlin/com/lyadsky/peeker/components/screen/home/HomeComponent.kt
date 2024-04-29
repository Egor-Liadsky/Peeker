package com.lyadsky.peeker.components.screen.home

import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.components.screen.root.RootComponent

interface HomeComponent {

    val viewStates: Value<HomeState>

    val slotStack: Value<ChildSlot<*, SlotChild>>

    fun navigateToAboutApp()

    fun onSearchTextFieldClick()

    fun onSearchTextFieldValueChanged(value: String)

    fun onRangeFromTextFieldValueChanged(value: String)

    fun onRangeToTextFieldValueChanged(value: String)

    fun onSearchAllMarketplacesCheckboxValueChanged()

    fun onClearedSearchTextField()

    fun onSearchClick()

    fun onRefreshClick()

    sealed class SlotChild {
        data class SearchDialogChild(val component: SearchDialogComponent): SlotChild()
    }
}