package com.lyadsky.peeker.di

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponentImpl
import com.lyadsky.peeker.data.network.services.HomeService
import com.lyadsky.peeker.utils.ComponentFactory
import org.koin.core.component.get

fun ComponentFactory.createSearchDialogComponent(
    componentContext: ComponentContext,
    searchTextFieldValueChanged: (String) -> Unit,
    clearedSearchTextField: () -> Unit,
    onDismissed: () -> Unit
): SearchDialogComponent =
    SearchDialogComponentImpl(
        componentContext = componentContext,
        homeService = get(),
        storage = get(),
        searchTextFieldValueChanged = searchTextFieldValueChanged,
        clearedSearchTextField = clearedSearchTextField,
        onDismissed = onDismissed
    )