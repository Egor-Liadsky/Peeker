package com.lyadsky.peeker.android.ui.views.pager

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.ui.theme.Color

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    currentPage: Int,
    count: Int
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        repeat(count) {
            val size = if (currentPage == it) 25f else 10f
            val width = remember { Animatable(size) }
            Canvas(modifier = Modifier
                .height(10.dp)
                .width(width.value.dp), onDraw = {
                drawRoundRect(
                    Color.PageIndicator.background,
                    cornerRadius = CornerRadius(100f, 100f)
                )
            })
            LaunchedEffect(key1 = currentPage, block = {
                width.animateTo(size)
            })
        }
    }
}