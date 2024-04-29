package com.lyadsky.peeker.data.model

import kotlinx.serialization.Serializable

//@Serializable
//data class MarketResponse(
//    val name: String,
//    val id: Int,
//    val icon: String
//)

@Serializable
data class MarketResponse(
    val markets: List<MarketInfo> // FIXME сказать леше чтобы он убрал этот дата класс
)

@Serializable
data class MarketInfo(
    val id: Long,
    val name: String,
    val photo: String
)

data class Market(
    val id: Long,
    val name: String,
    val icon: String
)