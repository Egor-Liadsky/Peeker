package com.lyadsky.peeker.components.layout.productList

import com.lyadsky.peeker.data.model.Product

data class ProductListState(
    val products: List<Product>? = null
)