package com.lyadsky.peeker.android.components.bottomSheet.sortingBottomSheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.components.bottomSheet.sorting.SortingBottomSheetComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortingBottomSheetView(component: SortingBottomSheetComponent) {

    val state by component.viewStates.subscribeAsState()

    ModalBottomSheet(onDismissRequest = { component.onDismissClick() }) {
        Text(text = "sorting")
    }
}