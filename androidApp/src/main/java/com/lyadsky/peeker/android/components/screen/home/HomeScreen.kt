package com.lyadsky.peeker.android.components.screen.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.collectAsLazyPagingItems
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.components.dialog.searchDialog.SearchDialog
import com.lyadsky.peeker.android.ui.views.layout.ProductsFlowRowLayoutPaging
import com.lyadsky.peeker.android.ui.views.topBar.HomeTopBar
import com.lyadsky.peeker.components.screen.home.HomeComponent

@Composable
fun HomeScreen(component: HomeComponent) {

    val state by component.viewStates.subscribeAsState()
    val slotNavigation by component.slotStack.subscribeAsState()
    val paging = component.products.collectAsLazyPagingItems()

    val context = LocalContext.current

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

        Log.e("TAGTAG", paging.itemSnapshotList.items.toString())
        Log.e("TAGTAG", paging.itemCount.toString())

        ProductsFlowRowLayoutPaging(paging = paging) {

        }
    }

//        ProductsList(paging = paging)

//        when (state.productsLoadingState) {
//            LoadingState.Success -> {
//
//                LazyColumn(
//                    Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 16.dp),
//                    verticalArrangement = Arrangement.Top
//                ) {
//
//                    item {
//                        SearchBannerLayout()
//                    }
//
//                    item {
//                        Text(
//                            text = stringResource(id = R.string.personal_selection),
//                            style = headerBold,
//                            modifier = Modifier.padding(top = 30.dp)
//                        )
//                    }
//
//                    item {
//                        ProductsFlowRowLayout(
//                            modifier = Modifier.padding(vertical = 20.dp),
//                            products = state.products ?: listOf()
//                        ) {
//                            context.startActivity(
//                                Intent(
//                                    Intent.ACTION_VIEW,
//                                    Uri.parse(it.url)
//                                )
//                            )
//                        }
//                    }
//                }
//            }
//
//            LoadingState.Loading -> LoadingLayout(Modifier.fillMaxSize())
//
//            is LoadingState.Empty -> EmptyLayout(Modifier.fillMaxSize())
//
//            is LoadingState.Error -> ErrorLayout(Modifier.fillMaxSize()) {
//                component.onProductRefreshClick()
//            }
//        }
}