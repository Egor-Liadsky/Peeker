package com.lyadsky.peeker.android.components.dialog.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.headerSemibold
import com.lyadsky.peeker.android.ui.views.divider.CommonDivider

@Composable
fun OrItemView(modifier: Modifier = Modifier) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {

        CommonDivider(Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.marketplace_or),
            style = headerSemibold,
            color = Color.Base.gray
        )

        CommonDivider(Modifier.weight(1f))
    }
}
