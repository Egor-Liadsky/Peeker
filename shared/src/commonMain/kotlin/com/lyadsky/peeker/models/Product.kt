package com.lyadsky.peeker.models

data class Product(
    val market: Market? = null,
    val item_id: Long? = null,
    val name: String? = null,
    val url: String? = null,
    val price: Double? = null,
    val rating: Double? = null,
    val review_count: Int? = null,
    val buy_count: Int? = null,
    val image: String? = null,
    val time_ship: String? = null,
    val datetime_ship: String? = null,
    val geo: String? = null
)