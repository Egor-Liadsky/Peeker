package com.lyadsky.peeker.components.layout.productList

import com.arkivanov.decompose.value.Value

interface ProductListComponent {

    val viewStates: Value<ProductListState>
}