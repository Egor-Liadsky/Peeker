package com.lyadsky.peeker.components.productList

import com.arkivanov.decompose.value.Value

interface ProductListComponent {

    val viewStates: Value<ProductListState>
}