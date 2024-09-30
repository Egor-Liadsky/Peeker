package com.lyadsky.peeker.di.components

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.bottomSheet.filter.FilterBottomSheetComponent
import com.lyadsky.peeker.components.bottomSheet.filter.FilterBottomSheetComponentImpl
import com.lyadsky.peeker.components.bottomSheet.sorting.SortingBottomSheetComponent
import com.lyadsky.peeker.components.bottomSheet.sorting.SortingBottomSheetComponentImpl
import com.lyadsky.peeker.models.Market
import com.lyadsky.peeker.models.SortingType
import com.lyadsky.peeker.utils.ComponentFactory
import org.koin.core.component.get

fun ComponentFactory.createSortingBottomSheetComponent(
    componentContext: ComponentContext,
    selectedSortingType: SortingType,
    onSelectSortingType: (SortingType) -> Unit,
    onDismiss: () -> Unit
): SortingBottomSheetComponent =
    SortingBottomSheetComponentImpl(
        componentContext = componentContext,
        selectedSortingType = selectedSortingType,
        onSelectSortingType = onSelectSortingType,
        onDismiss = onDismiss
    )

fun ComponentFactory.createFilterBottomSheetComponent(
    componentContext: ComponentContext,
    onDismiss: () -> Unit,
    onApply: (priceFrom: String, priceTo: String, marketsFilter: List<Market>) -> Unit
): FilterBottomSheetComponent =
    FilterBottomSheetComponentImpl(
        componentContext = componentContext,
        componentFactory = get(),
        onDismiss = onDismiss,
        onApplyClick = onApply
    )