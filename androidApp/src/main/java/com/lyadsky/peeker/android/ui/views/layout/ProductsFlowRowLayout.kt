package com.lyadsky.peeker.android.ui.views.layout

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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