package com.lyadsky.peeker.android.components.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.components.dialog.SearchDialog
import com.lyadsky.peeker.android.ui.views.layout.SearchBannerLayout
import com.lyadsky.peeker.android.ui.views.topBar.HomeTopBar
import com.lyadsky.peeker.components.screen.home.HomeComponent

@Composable
fun HomeScreen(component: HomeComponent) {

    val state by component.viewStates.subscribeAsState()
    val slotNavigation by component.slotStack.subscribeAsState()

    Column(Modifier.fillMaxSize()) {


        slotNavigation.child?.instance?.let { instance ->
            when (instance) {

                is HomeComponent.SlotChild.SearchDialogChild -> SearchDialog(
                    component = instance.component,
                    searchTextInput = state.searchTextField,
                    searchTextFieldValueChanged = { component.onSearchTextFieldValueChanged(it) },
                    onClearedSearchTextField = { component.onClearedSearchTextField() }
                )
            }
        }

        HomeTopBar(
            searchTextInput = state.searchTextField,
            onSearchTextFieldClick = { component.onSearchTextFieldClick() }
        )

        LazyColumn(Modifier.fillMaxSize()) {

            item {
                SearchBannerLayout(Modifier.padding(horizontal = 16.dp))
            }

            items(items = state.products ?: listOf()) { product ->
                AsyncImage(
                    model = product.photo,
                    contentDescription = "product image",
                    modifier = Modifier.size(104.dp),
                    onError = { println("COIL  " + it.result.throwable) }
                )
            }
        }
    }
}