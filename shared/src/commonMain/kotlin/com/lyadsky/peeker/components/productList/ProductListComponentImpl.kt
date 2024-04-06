package com.lyadsky.peeker.components.productList

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.data.model.Product

class ProductListComponentImpl(
    componentContext: ComponentContext,
    products: List<Product>
) : BaseComponent<ProductListState>(componentContext, ProductListState()), ProductListComponent {

}