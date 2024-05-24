package com.lyadsky.peeker.models

data class Sorting(
    val name: String,
    val type: SortingType
)

enum class SortingType {
    Price,
    Marketplace,
    Rating
}