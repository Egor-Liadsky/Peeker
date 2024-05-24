package com.lyadsky.peeker.android.ui.views.divider

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.ui.theme.Color

@Composable
fun CommonDivider(modifier: Modifier = Modifier) {

    HorizontalDivider(
        modifier
            .height(0.5.dp)
            .clip(RoundedCornerShape(8.dp)),
        color = Color.Divider.background
    )
}