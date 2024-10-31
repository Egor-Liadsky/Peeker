package com.lyadsky.peeker.models

import kotlinx.serialization.Serializable

@Serializable
data class MarketList(
    val markets: List<Long>? = null
)

@Serializable
data class Market(
    val id: Long,
    val code: String,
    val name: String,
    val icon: String,
)