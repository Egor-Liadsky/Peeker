package com.lyadsky.peeker.android.components.dialog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.headerBold
import com.lyadsky.peeker.android.ui.theme.headerSemibold
import com.lyadsky.peeker.android.ui.theme.marketplaceHeader
import com.lyadsky.peeker.android.ui.theme.textField
import com.lyadsky.peeker.android.ui.views.divider.CommonDivider
import com.lyadsky.peeker.android.ui.views.textField.CommonTextField
import com.lyadsky.peeker.components.dialog.searchDialog.SearchDialogComponent
import com.lyadsky.peeker.data.model.Marketplace

@Composable
fun SearchDialog(
    component: SearchDialogComponent,
    searchTextInput: String,
    searchTextFieldValueChanged: (String) -> Unit,
    onClearedSearchTextField: () -> Unit,
    rangeFromTextInput: String,
    rangeFromTextFieldValueChanged: (String) -> Unit,
    rangeToTextInput: String,
    rangeToTextFieldValueChanged: (String) -> Unit,
    searchAllMarketplacesCheckbox: Boolean,
    searchAllMarketplacesCheckboxValueChanged: () -> Unit
) {

    Dialog(
        onDismissRequest = { component.onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Base.white)
                .padding(horizontal = 16.dp)
        ) {

            CommonTextField(
                Modifier.padding(top = 10.dp),
                textInput = searchTextInput,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_product_placeholder),
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { onClearedSearchTextField() }) {
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
                    Log.e("TAGTAG", "ok")
                }),
                onBackButtonClick = { component.onDismiss() }
            ) {
                searchTextFieldValueChanged(it)
            }

            RangePriceView(
                Modifier.padding(top = 20.dp),
                rangeFromTextInput = rangeFromTextInput,
                rangeFromTextFieldValueChanged = rangeFromTextFieldValueChanged,
                rangeToTextInput = rangeToTextInput,
                rangeToTextFieldValueChanged = rangeToTextFieldValueChanged
            )

            SearchAllMarketplaces(
                Modifier.padding(top = 20.dp),
                searchAllMarketplacesCheckbox = searchAllMarketplacesCheckbox,
                searchAllMarketplacesCheckboxValueChanged = searchAllMarketplacesCheckboxValueChanged
            )

            SelectMarketplaces(
                Modifier.padding(top = 20.dp)
            )
        }
    }
}

@Composable
private fun RangePriceView(
    modifier: Modifier = Modifier,
    rangeFromTextInput: String,
    rangeFromTextFieldValueChanged: (String) -> Unit,
    rangeToTextInput: String,
    rangeToTextFieldValueChanged: (String) -> Unit,
) {
    Column(modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.set_range_price),
            style = headerBold,
            color = Color.Base.black
        )

        Row(
            Modifier.padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CommonTextField(
                Modifier.weight(1f),
                textInput = rangeFromTextInput,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.set_range_price_from),
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    Log.e("TAGTAG", "ok")
                }),
            ) {
                rangeFromTextFieldValueChanged(it)
            }

            CommonTextField(
                Modifier.weight(1f),
                textInput = rangeToTextInput,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.set_range_price_to),
                        style = textField,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    Log.e("TAGTAG", "ok")
                }),
            ) {
                rangeToTextFieldValueChanged(it)
            }
        }
    }
}

@Composable
private fun SearchAllMarketplaces(
    modifier: Modifier = Modifier,
    searchAllMarketplacesCheckbox: Boolean,
    searchAllMarketplacesCheckboxValueChanged: () -> Unit
) {

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = stringResource(id = R.string.search_all_marketplaces),
            style = headerBold,
            color = Color.Base.black
        )

        Checkbox(
            checked = searchAllMarketplacesCheckbox,
            onCheckedChange = { searchAllMarketplacesCheckboxValueChanged() },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Checkbox.checked,
                uncheckedColor = Color.Checkbox.unchecked
            )
        )
    }
}

@Composable
private fun SelectMarketplaces(modifier: Modifier = Modifier) {

    Column(modifier.fillMaxSize()) {

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            CommonDivider(Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.marketplace_or),
                style = headerSemibold,
                color = Color.Base.gray
            )

            CommonDivider(Modifier.weight(1f))
        }

        val marketplaces: List<Marketplace> = listOf(
            Marketplace(1, "Яндекс.Маркет", ""),
            Marketplace(2, "МегаМаркет", ""),
            Marketplace(3, "OZON", ""),
            Marketplace(4, "WILDBERRIES", ""),
        )

        marketplaces.forEach {
            MarketplaceItemView(Modifier.padding(top = 10.dp), marketplace = it)
        }
    }
}

@Composable
fun MarketplaceItemView(modifier: Modifier = Modifier, marketplace: Marketplace) {

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(Color.Base.black)
        )

        Column(
            Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = marketplace.name,
                    style = marketplaceHeader,
                    color = Color.Base.black
                )

                Checkbox(
                    checked = true,
                    onCheckedChange = { },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color.Checkbox.checked,
                        uncheckedColor = Color.Checkbox.unchecked
                    )
                )
            }

            CommonDivider(Modifier.fillMaxWidth())
        }
    }
}