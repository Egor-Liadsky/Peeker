package com.lyadsky.peeker.data.mapper

import com.lyadsky.peeker.data.model.MarketResponse
import com.lyadsky.peeker.models.Market

fun MarketResponse.toMap(): Market =
    Market(
        id = this.id,
        code = this.code,
        name = this.name,
        icon = this.icon
    )

fun com.lyadsky.Market.toMap(): Market =
    Market(
        id = this.id,
        code = this.code,
        name = this.name,
        icon = this.icon
    )