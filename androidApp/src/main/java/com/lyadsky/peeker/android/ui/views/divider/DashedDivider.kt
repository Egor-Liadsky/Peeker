package com.lyadsky.peeker.android.ui.views.divider

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import com.lyadsky.peeker.android.ui.theme.Color

@Composable
fun DashedDivider(
    modifier: Modifier = Modifier,
    thickness: Dp,
    color: androidx.compose.ui.graphics.Color = Color.Base.black,
    phase: Float = 10f,
    intervals: FloatArray = floatArrayOf(10f, 10f),
) {
    Canvas(
        modifier = modifier
    ) {
        val dividerHeight = thickness.toPx()
        drawRoundRect(
            color = color,
            style = Stroke(
                width = dividerHeight,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = intervals,
                    phase = phase
                )
            )
        )
    }
}
