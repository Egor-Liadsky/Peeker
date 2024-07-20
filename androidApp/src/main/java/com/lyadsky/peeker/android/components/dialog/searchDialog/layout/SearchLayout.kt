package com.lyadsky.peeker.android.components.dialog.searchDialog.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.components.dialog.searchDialog.view.FilterView
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.views.card.ProductCardView
import com.lyadsky.peeker.android.ui.views.layout.EmptyLayout
import com.lyadsky.peeker.android.ui.views.layout.EnterTextForSearchLayout
import com.lyadsky.peeker.android.ui.views.layout.ErrorLayout
import com.lyadsky.peeker.android.ui.views.layout.LoadingLayout
import com.lyadsky.peeker.android.utils.OnEndReached
import com.lyadsky.peeker.android.utils.openUrl
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterialApi::class)
@Composable
fun SearchLayout(component: SearchDialogComponent) {

    val state by component.viewStates.subscribeAsState()
    val lazyListState = rememberLazyListState()
    val pagingState by component.pagingState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isRefreshing,
        onRefresh = { component.onRefresh() }
    )
    val context = LocalContext.current

    lazyListState.OnEndReached { component.loadNextPage() }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilterView(
                title = stringResource(id = R.string.sorting),
                icon = R.drawable.ic_sorting,
                color = Color.Base.black
            ) {
                component.onSortingButtonClick()
            }

            FilterView(
                title = stringResource(id = R.string.filter),
                icon = R.drawable.ic_filter,
                color = Color.Base.purplePrimary
            ) {
                component.onFilterButtonClick()
            }
        }

        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                state = lazyListState,
            ) {
                item {
                    FlowRow(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        pagingState.items.forEach { product ->
                            product.item_id?.let {
                                ProductCardView(Modifier.weight(1f), product = product) {
                                    product.url?.let {
                                        context.openUrl(it)
                                    }
                                }
                            }
                        }
                        if (pagingState.items.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            PullRefreshIndicator(
                refreshing = state.isRefreshing,
                state = pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )

            Box(modifier = Modifier.fillMaxHeight(0.5f)) {
                when {
                    pagingState.isLoading -> LoadingLayout(Modifier.fillMaxSize())
                    pagingState.isFailure -> ErrorLayout(Modifier.fillMaxSize()) {
                        component.onProductsReloadClick()
                    }

                    pagingState.isLastPage -> when {
                        pagingState.items.isEmpty() && state.searchTextField.isEmpty() ->
                            EnterTextForSearchLayout(Modifier.fillMaxSize())

                        pagingState.items.isEmpty() -> EmptyLayout(Modifier.fillMaxSize())
                    }
                }
            }
        }
    }
}

