package com.lyadsky.peeker.android.components.productList

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.components.productList.ProductListComponent

@Composable
fun ProductList(component: ProductListComponent) {
    
    val state = component.viewStates.subscribeAsState()
    
    LazyColumn {
        items(items = state.value.products ?: listOf()) { product ->
            
            Text(text = product.toString())
        }
    }
}