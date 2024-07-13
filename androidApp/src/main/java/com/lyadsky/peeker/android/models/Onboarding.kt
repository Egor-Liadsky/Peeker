package com.lyadsky.peeker.android.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

data class Onboarding(
    val title: @Composable () -> Unit,
    val image: Painter
)