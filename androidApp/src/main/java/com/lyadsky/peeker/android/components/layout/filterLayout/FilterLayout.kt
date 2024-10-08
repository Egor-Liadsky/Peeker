package com.lyadsky.peeker.android.components.layout.filterLayout

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
import com.lyadsky.peeker.android.components.dialog.searchDialog.view.MarketplaceItemView
import com.lyadsky.peeker.android.components.dialog.searchDialog.view.OrItemView
import com.lyadsky.peeker.android.components.dialog.searchDialog.view.RangePriceView
import com.lyadsky.peeker.android.components.dialog.searchDialog.view.SearchAllMarketplacesView
import com.lyadsky.peeker.android.ui.views.button.CommonButton
import com.lyadsky.peeker.android.ui.views.layout.EmptyLayout
import com.lyadsky.peeker.android.ui.views.layout.ErrorLayout
import com.lyadsky.peeker.android.ui.views.layout.LoadingLayout
import com.lyadsky.peeker.components.layout.FilterLayoutComponent
import com.lyadsky.peeker.utils.LoadingState

@Composable
fun FilterLayout(component: FilterLayoutComponent) {

    val state by component.viewStates.subscribeAsState()

    Column(Modifier.padding(horizontal = 16.dp)) {


        RangePriceView(
            rangeFromTextInput = state.rangeFromTextField,
            rangeFromTextFieldValueChanged = { component.onRangeFromTextFieldValueChanged(it) },
            rangeToTextInput = state.rangeToTextField,
            rangeToTextFieldValueChanged = { component.onRangeToTextFieldValueChanged(it) }
        )

        SearchAllMarketplacesView(
            searchAllMarketplacesCheckbox = state.searchAllMarketplacesCheckbox,
            searchAllMarketplacesCheckboxValueChanged = { component.onSearchAllMarketplacesCheckboxValueChanged() }
        )

        OrItemView(Modifier.padding(horizontal = 16.dp))

        println("TAGTAG ${state.selectedMarkets}")

        when (state.marketsLoadingState) {
            LoadingState.Success -> LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                item {
                    state.markets?.forEach { market ->
                        MarketplaceItemView(
                            Modifier.padding(bottom = 10.dp),
                            market = market,
                            checked = state.selectedMarkets.contains(market)
                        ) {
                            component.onSelectMarketClick(market)
                        }
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
                .padding(bottom = 16.dp),
            title = stringResource(id = R.string.search)
        ) {
            component.onApplyClick()
        }
    }
}