package com.lyadsky.peeker.android.components.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
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
import com.lyadsky.peeker.android.components.dialog.searchDialog.SearchDialog
import com.lyadsky.peeker.android.ui.theme.headerBold
import com.lyadsky.peeker.android.ui.views.card.ProductCardView
import com.lyadsky.peeker.android.ui.views.layout.ErrorLayout
import com.lyadsky.peeker.android.ui.views.layout.LoadingLayout
import com.lyadsky.peeker.android.ui.views.layout.SearchBannerLayout
import com.lyadsky.peeker.android.ui.views.topBar.HomeTopBar
import com.lyadsky.peeker.android.utils.OnEndReached
import com.lyadsky.peeker.android.utils.openUrl
import com.lyadsky.peeker.components.screen.home.HomeComponent

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(component: HomeComponent) {

    val state by component.viewStates.subscribeAsState()
    val slotNavigation by component.slotStack.subscribeAsState()
    val lazyListState = rememberLazyListState()
    val pageData by component.products.collectAsState()
    val context = LocalContext.current

    lazyListState.OnEndReached { component.loadNextPage() }

    Column(Modifier.fillMaxSize()) {

        slotNavigation.child?.instance?.let { instance ->
            when (instance) {

                is HomeComponent.SlotChild.SearchDialogChild -> SearchDialog(component = instance.component)
            }
        }

        HomeTopBar(
            searchTextInput = state.searchTextField,
            onSearchTextFieldClick = { component.onSearchTextFieldClick() }
        )

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            state = lazyListState,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                SearchBannerLayout()
            }
            item {
                Text(
                    text = stringResource(id = R.string.personal_selection),
                    style = headerBold,
                    modifier = Modifier.padding(vertical = 30.dp)
                )
            }
            item {
                FlowRow(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    pageData.items.forEach { product ->
                        ProductCardView(Modifier.weight(1f), product = product) {
                            context.openUrl(product.url)
                        }
                    }
                    if (pageData.items.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
            item {
                when {
                    pageData.isLoading -> LoadingLayout(Modifier.fillMaxSize())
                    pageData.isFailure -> ErrorLayout(Modifier.fillMaxSize()) {
                        component.onProductsReloadClick()
                    }
                }
            }
        }
    }
}