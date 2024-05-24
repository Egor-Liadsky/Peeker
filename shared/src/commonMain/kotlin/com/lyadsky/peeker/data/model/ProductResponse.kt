package com.lyadsky.peeker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val items: List<ProductItem>,
    val page_number: Int,
    val pages: Int,
    val offset: Int,
)

@Serializable
data class ProductItem(
    val market: Long,
    val item_id: Long,
    val name: String,
    val url: String,
    val price: Double,
    val rating: Double,
    val picture: String,
    val time_ship: String? = null,
    val datetime_ship: String? = null,
    val geo: String? = null,
)
