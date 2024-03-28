package com.lyadsky.peeker.android.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.ui.views.topBar.HomeTopBar
import com.lyadsky.peeker.components.home.HomeComponent

@Composable
fun HomeScreen(component: HomeComponent) {

    Column(Modifier.fillMaxSize()) {

        HomeTopBar()
    }
}