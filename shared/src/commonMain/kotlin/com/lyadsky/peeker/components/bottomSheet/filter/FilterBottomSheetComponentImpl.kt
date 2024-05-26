package com.lyadsky.peeker.components.bottomSheet.filter

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.childContext
import com.lyadsky.peeker.components.BaseComponent
import com.lyadsky.peeker.components.layout.FilterLayoutComponent
import com.lyadsky.peeker.di.components.createFilterLayoutComponent
import com.lyadsky.peeker.models.Sorting
import com.lyadsky.peeker.utils.ComponentFactory

class FilterBottomSheetComponentImpl(
    componentContext: ComponentContext,
    componentFactory: ComponentFactory,
    private val onDismiss: () -> Unit,
) : FilterBottomSheetComponent,
    BaseComponent<FilterBottomSheetState>(componentContext, FilterBottomSheetState()) {

    override val filterLayoutComponent: FilterLayoutComponent by lazy {
        componentFactory.createFilterLayoutComponent(
            componentContext = childContext(key = "FilterLayoutComponent"),
            onApplyClick = { onDismiss() }
        )
    }

    override fun onDismissClick() {
        onDismiss()
    }
}