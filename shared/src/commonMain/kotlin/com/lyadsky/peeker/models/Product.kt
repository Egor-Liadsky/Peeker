package com.lyadsky.peeker.models

data class Product(
    val market: Market,
    val item_id: Int,
    val name: String,
    val url: String,
    val price: Double,
    val rating: Double? = null,
    val image: String,
    val time_ship: String? = null,
    val datetime_ship: String? = null,
    val geo: String? = null
)