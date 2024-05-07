package com.lyadsky.peeker.android.components.dialog.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.ui.theme.Typography

@Composable
fun FilterView(
    title: String,
    @DrawableRes icon: Int,
    color: Color
) {
    TextButton(
        onClick = { /*TODO*/ },
    ) {
        Icon(
            painter = painterResource(id = icon), contentDescription = "filter icon",
            Modifier.size(24.dp),
            tint = color
        )

        Text(
            text = title,
            style = Typography.filterViewTitle,
            color = color,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}