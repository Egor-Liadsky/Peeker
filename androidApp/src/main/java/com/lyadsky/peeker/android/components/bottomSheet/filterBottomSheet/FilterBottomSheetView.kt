package com.lyadsky.peeker.android.components.bottomSheet.filterBottomSheet

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.components.layout.filterLayout.FilterLayout
import com.lyadsky.peeker.components.bottomSheet.filter.FilterBottomSheetComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheetView(component: FilterBottomSheetComponent) {

    val state by component.viewStates.subscribeAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        sheetState = sheetState,
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = { component.onDismissClick() },
    ) {

        FilterLayout(component = component.filterLayoutComponent)
    }
}