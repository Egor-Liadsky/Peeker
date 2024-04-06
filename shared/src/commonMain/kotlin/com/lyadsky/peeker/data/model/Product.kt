package com.lyadsky.peeker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val market_id: Int,
    val item_id: Int,
    val name: String,
    val url: String,
    val price: Double,
    val photo: String,
    val time_ship: String? = null,
    val datetime_ship: String? = null,
    val geo: String? = null
)