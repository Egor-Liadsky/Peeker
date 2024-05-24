package com.lyadsky.peeker.di

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.bottomSheet.filter.FilterBottomSheetComponent
import com.lyadsky.peeker.components.bottomSheet.filter.FilterBottomSheetComponentImpl
import com.lyadsky.peeker.components.bottomSheet.sorting.SortingBottomSheetComponent
import com.lyadsky.peeker.components.bottomSheet.sorting.SortingBottomSheetComponentImpl
import com.lyadsky.peeker.models.Sorting
import com.lyadsky.peeker.models.SortingType
import com.lyadsky.peeker.utils.ComponentFactory

fun ComponentFactory.createSortingBottomSheetComponent(
    componentContext: ComponentContext,
    onSelectSortingType: (SortingType) -> Unit,
    onDismiss: () -> Unit
): SortingBottomSheetComponent =
    SortingBottomSheetComponentImpl(
        componentContext = componentContext,
        onSelectSortingType = onSelectSortingType,
        onDismiss = onDismiss
    )

fun ComponentFactory.createFilterBottomSheetComponent(
    componentContext: ComponentContext,
    onDismiss: () -> Unit
): FilterBottomSheetComponent =
    FilterBottomSheetComponentImpl(
        componentContext = componentContext,
        onDismiss = onDismiss
    )