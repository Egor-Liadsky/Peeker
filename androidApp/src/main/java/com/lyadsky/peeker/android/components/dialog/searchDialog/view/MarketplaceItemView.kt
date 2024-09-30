package com.lyadsky.peeker.android.components.dialog.searchDialog.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.marketplaceHeader
import com.lyadsky.peeker.android.ui.views.divider.CommonDivider
import com.lyadsky.peeker.android.utils.conditional
import com.lyadsky.peeker.android.utils.shimmerBackground
import com.lyadsky.peeker.models.Market

@Composable
fun MarketplaceItemView(
    modifier: Modifier = Modifier,
    market: Market,
    checked: Boolean,
    onClick: () -> Unit
) {

    val isLoadingState = remember { mutableStateOf(true) }

    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Box(
            modifier = Modifier
                .conditional(isLoadingState.value) {
                    shimmerBackground()
                },
        ) {
            AsyncImage(
                model = market.icon,
                contentDescription = "market icon",
                modifier = Modifier.size(24.dp),
                onLoading = { isLoadingState.value = it.painter == null },
                onSuccess = { isLoadingState.value = false },
            )
        }

        Column(
            Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = market.name, style = marketplaceHeader, color = Color.Base.black
                )

                Checkbox(
                    checked = checked,
                    onCheckedChange = {
                        onClick()
                    },
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
