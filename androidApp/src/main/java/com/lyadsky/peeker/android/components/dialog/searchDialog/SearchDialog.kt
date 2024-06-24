package com.lyadsky.peeker.android.components.dialog.searchDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.paging.compose.collectAsLazyPagingItems
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.components.bottomSheet.filterBottomSheet.FilterBottomSheetView
import com.lyadsky.peeker.android.components.bottomSheet.sortingBottomSheet.SortingBottomSheetView
import com.lyadsky.peeker.android.components.dialog.searchDialog.layout.SearchLayout
import com.lyadsky.peeker.android.components.layout.filterLayout.FilterLayout
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.textField
import com.lyadsky.peeker.android.ui.views.textField.CommonTextField
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent

@Composable
fun SearchDialog(
    component: SearchDialogComponent,
) {
    val state by component.viewStates.subscribeAsState()
    val slotNavigation by component.slotStack.subscribeAsState()
    val paging = component.products.collectAsLazyPagingItems()

    Dialog(
        onDismissRequest = { component.onDismissClick() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Base.white),
        ) {
            slotNavigation.child?.instance?.let { instance ->
                when (instance) {
                    is SearchDialogComponent.SlotChild.SortingBottomSheetChild -> SortingBottomSheetView(
                        component = instance.component
                    )

                    is SearchDialogComponent.SlotChild.FilterBottomSheetChild -> FilterBottomSheetView(
                        component = instance.component
                    )
                }
            }

            CommonTextField(Modifier.padding(top = 10.dp, bottom = 20.dp, end = 16.dp),
                textInput = state.searchTextField,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_product_placeholder),
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                trailingIcon = {
                    Row(
                        Modifier.padding(end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { component.onClearedSearchTextField() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = "QrCodeScanner"
                            )
                        }

                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            tint = Color.Base.purplePrimary
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                onBackButtonClick = { component.onDismissClick() }) {
                component.onSearchTextFieldValueChanged(it)
                paging.refresh()
            }

            when (state.searchedProduct) {
                true -> SearchLayout(component = component, paging = paging)
                false -> FilterLayout(component = component.filterLayoutComponent)
            }
        }
    }
}

