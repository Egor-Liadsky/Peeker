package com.lyadsky.peeker.android.components.dialog.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.headerBold

@Composable
fun SearchAllMarketplacesView(
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
                checkedColor = Color.Checkbox.checked, uncheckedColor = Color.Checkbox.unchecked
            )
        )
    }
}