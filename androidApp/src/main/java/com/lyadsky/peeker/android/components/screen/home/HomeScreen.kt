package com.lyadsky.peeker.android.components.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.components.dialog.SearchDialog
import com.lyadsky.peeker.android.ui.theme.headerBold
import com.lyadsky.peeker.android.ui.views.card.ProductCardView
import com.lyadsky.peeker.android.ui.views.layout.SearchBannerLayout
import com.lyadsky.peeker.android.ui.views.topBar.HomeTopBar
import com.lyadsky.peeker.components.screen.home.HomeComponent

@OptIn(ExperimentalLayoutApi::class)
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
                    onClearedSearchTextField = { component.onClearedSearchTextField() },
                    rangeFromTextInput = state.rangeFromTextField,
                    rangeFromTextFieldValueChanged = { component.onRangeFromTextFieldValueChanged(it) },
                    rangeToTextInput = state.rangeToTextField,
                    rangeToTextFieldValueChanged = { component.onRangeToTextFieldValueChanged(it) },
                    searchAllMarketplacesCheckbox = state.searchAllMarketplacesCheckbox,
                    searchAllMarketplacesCheckboxValueChanged = { component.onSearchAllMarketplacesCheckboxValueChanged() },
                    markets = state.markets ?: listOf()
                )
            }
        }

        HomeTopBar(
            searchTextInput = state.searchTextField,
            onSearchTextFieldClick = { component.onSearchTextFieldClick() }
        )

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            item {
                SearchBannerLayout()
            }

            item {
                Text(
                    text = stringResource(id = R.string.personal_selection),
                    style = headerBold,
                    modifier = Modifier.padding(top = 30.dp)
                )
            }

            item {
                FlowRow(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    state.products?.forEach { product ->
                        ProductCardView(Modifier.weight(1f), product = product)
                    }
                }
            }
        }
    }
}