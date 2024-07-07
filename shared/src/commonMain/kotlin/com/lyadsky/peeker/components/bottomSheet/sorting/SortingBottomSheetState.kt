package com.lyadsky.peeker.components.bottomSheet.sorting

import com.lyadsky.peeker.models.SortingType

data class SortingBottomSheetState(
    val selectSorting: SortingType = SortingType.Rating
)
