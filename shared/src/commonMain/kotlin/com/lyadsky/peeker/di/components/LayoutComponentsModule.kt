package com.lyadsky.peeker.di.components

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.layout.FilterLayoutComponent
import com.lyadsky.peeker.components.layout.FilterLayoutComponentImpl
import com.lyadsky.peeker.utils.ComponentFactory
import org.koin.core.component.get

fun ComponentFactory.createFilterLayoutComponent(
    componentContext: ComponentContext,
    onApplyClick: (priceFrom: String, priceTo: String) -> Unit
): FilterLayoutComponent =
    FilterLayoutComponentImpl(
        componentContext = componentContext,
        marketService = get(),
        onApplyClicked = onApplyClick
    )