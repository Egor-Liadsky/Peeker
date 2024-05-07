package com.lyadsky.peeker.android.components.dialog.layout

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.components.dialog.view.FilterView
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.views.card.ProductCardView
import com.lyadsky.peeker.android.ui.views.layout.EmptyLayout
import com.lyadsky.peeker.android.ui.views.layout.ErrorLayout
import com.lyadsky.peeker.android.ui.views.layout.LoadingLayout
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.utils.LoadingState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchLayout(component: SearchDialogComponent, searchedText: String) {

    val state by component.viewStates.subscribeAsState()

    val context = LocalContext.current

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilterView(
                title = stringResource(id = R.string.sorting),
                icon = R.drawable.ic_sorting,
                color = Color.Base.black
            )

            FilterView(
                title = stringResource(id = R.string.filter),
                icon = R.drawable.ic_filter,
                color = Color.Base.purplePrimary
            )
        }

        when (state.productsLoadingState) {
            LoadingState.Success -> {
                LazyColumn(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Top
                ) {
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
                                ProductCardView(Modifier.weight(1f), product = product) {
                                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(product.url)))
                                }
                            }
                            if (state.products?.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            LoadingState.Loading -> LoadingLayout(Modifier.fillMaxSize())

            LoadingState.Empty -> EmptyLayout(Modifier.fillMaxSize())

            is LoadingState.Error -> ErrorLayout(Modifier.fillMaxSize()) {
                component.onProductRefreshClick(searchedText)
            }
        }
    }
}

