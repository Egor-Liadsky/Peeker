package com.lyadsky.peeker.android.ui.views.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.defaultSemibold
import com.lyadsky.peeker.android.ui.views.divider.DashedDivider

@Composable
fun SelectCityButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    TextButton(
        modifier = modifier,
        onClick = { onClick() },
        enabled = false
    ) { //TODO сделать кликабельной
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_map),
                contentDescription = "Select city",
                modifier = Modifier.size(20.dp),
                tint = Color.Base.black
            )

            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = "Москва",//TODO убрать костыль, когда будет реализация выбора города на бэке stringResource(id = R.string.select_city_default),
                    style = defaultSemibold,
                    color = Color.Base.black,
                )

                DashedDivider(
                    Modifier.fillMaxWidth(0.5f), //TODO на планшетах будет плохо смотреться
                    color = Color.Base.black,
                    thickness = 1.dp,
                    phase = 5f,
                    intervals = floatArrayOf(8f, 8f)
                )
            }
        }
    }
}