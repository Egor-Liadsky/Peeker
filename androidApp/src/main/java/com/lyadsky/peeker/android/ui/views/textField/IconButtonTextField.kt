package com.lyadsky.peeker.android.ui.views.textField

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonTextField(
    @DrawableRes icon: Int,
    color: Color = com.lyadsky.peeker.android.ui.theme.Color.TextField.icon,
    onClick: (() -> Unit)
) {
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "chat icon",
            tint = color,
            modifier = Modifier.size(24.dp)
        )
    }
}