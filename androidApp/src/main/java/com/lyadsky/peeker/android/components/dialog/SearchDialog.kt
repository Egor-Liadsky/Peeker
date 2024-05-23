package com.lyadsky.peeker.android.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.components.dialog.layout.OnboardingSearchLayout
import com.lyadsky.peeker.android.components.dialog.layout.SearchLayout
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.textField
import com.lyadsky.peeker.android.ui.views.textField.CommonTextField
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent

@Composable
fun SearchDialog(
    component: SearchDialogComponent,
//    searchTextInput: String,
) {
    val state = component.viewStates.subscribeAsState()

    Dialog(
        onDismissRequest = { component.onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Base.white),
        ) {

            CommonTextField(Modifier.padding(top = 10.dp, bottom = 20.dp, end = 16.dp),
                textInput = state.value.searchTextField,
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    component.onSearchClick(state.value.searchTextField)
                }),
                onBackButtonClick = { component.onDismiss() }) {
                component.onSearchTextFieldValueChanged(it)
            }

            when (state.value.searchedProduct) {
                true -> SearchLayout(component = component)
                false -> OnboardingSearchLayout(component = component)
            }
        }
    }
}

