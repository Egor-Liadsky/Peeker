package com.lyadsky.peeker.android.components.dialog.layout

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
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
import com.lyadsky.peeker.android.ui.views.layout.EmptyLayout
import com.lyadsky.peeker.android.ui.views.layout.ErrorLayout
import com.lyadsky.peeker.android.ui.views.layout.LoadingLayout
import com.lyadsky.peeker.android.ui.views.layout.ProductsFlowRowLayout
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.utils.EmptyType
import com.lyadsky.peeker.utils.LoadingState

@Composable
fun SearchLayout(component: SearchDialogComponent) {

    val state by component.viewStates.subscribeAsState()

    val context = LocalContext.current

    Column(
        Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top
                ) {
                    item {
                        ProductsFlowRowLayout(
                            modifier = Modifier.padding(top = 20.dp, start = 16.dp, end = 16.dp),
                            products = state.products ?: listOf()) {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(it.url)
                                )
                            )
                        }
                    }
                }
            }

            LoadingState.Loading -> LoadingLayout(Modifier.fillMaxSize())

            is LoadingState.Empty -> {
                when ((state.productsLoadingState as LoadingState.Empty).type) {
                    EmptyType.EmptyTextField -> Text(text = "Введите название\nдля поиска")
                    EmptyType.NotFound -> EmptyLayout(Modifier.fillMaxSize())
                }
            }

            is LoadingState.Error -> ErrorLayout(Modifier.fillMaxSize()) {
                component.onProductRefreshClick()
            }
        }
    }
}

