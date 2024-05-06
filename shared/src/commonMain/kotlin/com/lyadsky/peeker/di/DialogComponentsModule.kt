package com.lyadsky.peeker.di

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponentImpl
import com.lyadsky.peeker.utils.ComponentFactory

fun ComponentFactory.createSearchDialogComponent(
    componentContext: ComponentContext,
    onDismissed: () -> Unit
): SearchDialogComponent =
    SearchDialogComponentImpl(
        componentContext = componentContext,
        onDismissed = onDismissed
    )