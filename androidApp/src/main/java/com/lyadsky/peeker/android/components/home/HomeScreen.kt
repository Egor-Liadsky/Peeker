package com.lyadsky.peeker.android.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.ui.views.layout.SearchBannerLayout
import com.lyadsky.peeker.android.ui.views.topBar.HomeTopBar
import com.lyadsky.peeker.components.home.HomeComponent

@Composable
fun HomeScreen(component: HomeComponent) {

    val state = component.viewStates.subscribeAsState()

    Column(Modifier.fillMaxSize()) {

        HomeTopBar()

        LazyColumn(Modifier.fillMaxSize()) {

            item {
                SearchBannerLayout(Modifier.padding(horizontal = 16.dp))
            }

            items(items = state.value.products ?: listOf()) { product ->
                AsyncImage(
                    model = product.photo,
                    contentDescription = "product image",
                    modifier = Modifier.size(104.dp),
                    onError = { println("COIL  " + it.result.throwable) }
                )
            }
        }
    }
}