package com.lyadsky.peeker.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MarketResponse(
    val id: Long,
    val code: String,
    val name: String,
    val icon: String,
)

data class Market(
    val id: Long,
    val code: String,
    val name: String,
    val icon: String,
)