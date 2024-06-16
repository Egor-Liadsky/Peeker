package com.lyadsky.peeker.android.ui.views.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.lyadsky.peeker.android.ui.views.card.ProductCardView
import com.lyadsky.peeker.models.Product

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductsFlowRowLayout(
    modifier: Modifier = Modifier,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    FlowRow(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        products.forEach { product ->
            ProductCardView(Modifier.weight(1f), product = product) {
                onProductClick(product)
            }
        }
        if (products.size == 1) {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductsFlowRowLayoutPaging(
    modifier: Modifier = Modifier,
    paging: LazyPagingItems<Product>,
    onProductClick: (Product) -> Unit
) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        when (paging.loadState.refresh) {
            LoadState.Loading -> {
                item {
                    LoadingLayout()
                }
            }

            is LoadState.Error -> {
                item {
                    ErrorLayout {
                        paging.refresh()
                    }
                }
            }

            is LoadState.NotLoading -> {
                item {
                    FlowRow(
                        modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        repeat(paging.itemCount) { index ->
                            val product = paging[index]
                            if (product != null) {
                                ProductCardView(Modifier.weight(1f), product = product) {
                                    onProductClick(product)
                                }
                            }
                        }
                    }
                }
            }
//                item {
//                    FlowRow(
//                        modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.spacedBy(
//                            16.dp,
//                            alignment = Alignment.CenterHorizontally
//                        ),
//                        verticalArrangement = Arrangement.spacedBy(16.dp)
//                    ) {
//                        paging.itemSnapshotList.items.forEach { product ->
//                            ProductCardView(Modifier.weight(1f), product = product) {
//                                onProductClick(product)
//                            }
//                        }
//                        if (paging.itemSnapshotList.items.size == 1) {
//                            Spacer(modifier = Modifier.weight(1f))
//                        }
//                    }
//                }

            else -> item {
                LoadingLayout()
            }
        }
    }
}