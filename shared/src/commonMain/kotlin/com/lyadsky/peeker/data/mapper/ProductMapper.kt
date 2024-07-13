package com.lyadsky.peeker.data.mapper

import com.lyadsky.peeker.data.model.ProductItem
import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.Product

fun ProductItem.toMap(market: Market) =
    Product(
        market = Market(market.id, market.code, market.name, market.icon),
        item_id = this.item_id,
        name = this.name,
        url = this.url,
        price = this.price,
        rating = this.rating,
        review_count = this.review_count,
        buy_count = this.buy_count,
        image = this.picture,
        time_ship = this.time_ship,
        datetime_ship = this.datetime_ship,
        geo = this.geo
    )