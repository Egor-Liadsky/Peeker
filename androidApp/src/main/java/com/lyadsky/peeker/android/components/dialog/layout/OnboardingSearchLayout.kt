package com.lyadsky.peeker.android.components.dialog.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.components.dialog.view.MarketplaceItemView
import com.lyadsky.peeker.android.components.dialog.view.OrItemView
import com.lyadsky.peeker.android.components.dialog.view.RangePriceView
import com.lyadsky.peeker.android.components.dialog.view.SearchAllMarketplacesView
import com.lyadsky.peeker.android.ui.views.button.CommonButton
import com.lyadsky.peeker.android.ui.views.layout.EmptyLayout
import com.lyadsky.peeker.android.ui.views.layout.ErrorLayout
import com.lyadsky.peeker.android.ui.views.layout.LoadingLayout
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.utils.LoadingState

@Composable
fun OnboardingSearchLayout(component: SearchDialogComponent) {

    val state by component.viewStates.subscribeAsState()

    Column {

        RangePriceView(
            Modifier.padding(horizontal = 16.dp),
            rangeFromTextInput = state.rangeFromTextField,
            rangeFromTextFieldValueChanged = { component.onRangeFromTextFieldValueChanged(it) },
            rangeToTextInput = state.rangeToTextField,
            rangeToTextFieldValueChanged = { component.onRangeToTextFieldValueChanged(it) }
        )

        SearchAllMarketplacesView(
            Modifier.padding(start = 16.dp, end = 16.dp),
            searchAllMarketplacesCheckbox = state.searchAllMarketplacesCheckbox,
            searchAllMarketplacesCheckboxValueChanged = { component.onSearchAllMarketplacesCheckboxValueChanged() }
        )

        OrItemView(Modifier.padding(horizontal = 16.dp))

        when (state.marketsLoadingState) {
            LoadingState.Success -> LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
            ) {
                item {
                    state.markets?.forEach {
                        MarketplaceItemView(Modifier.padding(bottom = 10.dp), market = it)
                    }
                }
            }

            LoadingState.Loading -> LoadingLayout(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            is LoadingState.Empty -> EmptyLayout(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            is LoadingState.Error -> ErrorLayout(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                component.onMarketsRefreshClick()
            }
        }

        CommonButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            title = stringResource(id = R.string.search)
        ) {
            component.onSearchClick(state.searchTextField)
        }
    }
}