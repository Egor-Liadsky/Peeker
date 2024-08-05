package com.lyadsky.peeker.android.components.bottomSheet.sortingBottomSheet

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.Typography
import com.lyadsky.peeker.android.ui.views.button.CommonButton
import com.lyadsky.peeker.android.ui.views.divider.CommonDivider
import com.lyadsky.peeker.components.bottomSheet.sorting.SortingBottomSheetComponent
import com.lyadsky.peeker.models.Sorting
import com.lyadsky.peeker.models.SortingType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingBottomSheetView(component: SortingBottomSheetComponent) {

    val state by component.viewStates.subscribeAsState()

    val sortingTypesList = listOf(
        Sorting(name = stringResource(id = R.string.filter_by_rating), type = SortingType.Rating),
        Sorting(name = stringResource(id = R.string.filter_by_price), type = SortingType.Price),
        Sorting(name = stringResource(id = R.string.filter_by_buy), type = SortingType.Buy),
    )

    ModalBottomSheet(
        onDismissRequest = { component.onDismissClick() },
        containerColor = Color.Base.white
    ) {
        LazyColumn(
            Modifier.fillMaxWidth()
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.sorting),
                    style = Typography.SortingBottomSheet.title,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                CommonDivider(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            items(items = sortingTypesList) { sorting ->
                SortingTypeItemView(sorting = sorting, selectedSortingType = state.selectSorting) {
                    component.onSelectSortingClick(sorting.type)
                }
            }
            item {
                CommonButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    title = stringResource(id = R.string.apply)
                ) {
                    component.onApplyClick()
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
private fun SortingTypeItemView(
    sorting: Sorting,
    selectedSortingType: SortingType,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(),
                onClick = { onClick() }
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = sorting.type == selectedSortingType,
            onClick = { onClick() },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.RadioButton.selectedSorting,
                unselectedColor = Color.RadioButton.unselectedSorting
            )
        )
        Text(text = sorting.name, style = Typography.SortingBottomSheet.type)
    }
}
