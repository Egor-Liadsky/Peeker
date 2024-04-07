package com.lyadsky.peeker.components.dialog.searchDialog

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent

class SearchDialogComponentImpl(
    componentContext: ComponentContext,
    private val onDismissed: () -> Unit
) : SearchDialogComponent, BaseComponent<SearchDialogState>(componentContext, SearchDialogState()) {

    override fun onDismiss() {
        onDismissed()
    }
}