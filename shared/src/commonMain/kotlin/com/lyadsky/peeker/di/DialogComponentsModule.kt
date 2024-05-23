package com.lyadsky.peeker.di

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponentImpl
import com.lyadsky.peeker.utils.ComponentFactory
import org.koin.core.component.get

fun ComponentFactory.createSearchDialogComponent(
    componentContext: ComponentContext,
    searchTextFieldValueChanged: (String) -> Unit,
    clearedSearchTextField: () -> Unit,
    searchTextFieldValue: String,
    onDismissed: () -> Unit,
): SearchDialogComponent =
    SearchDialogComponentImpl(
        componentContext = componentContext,
        homeService = get(),
        searchTextFieldValue = searchTextFieldValue,
        searchTextFieldValueChanged = searchTextFieldValueChanged,
        clearedSearchTextField = clearedSearchTextField,
        onDismissed = onDismissed
    )