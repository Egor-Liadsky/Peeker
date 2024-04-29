package com.lyadsky.peeker.android.ui.views.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.headerBold
import com.lyadsky.peeker.android.ui.views.button.CommonButton

@Composable
fun ErrorLayout(modifier: Modifier = Modifier, onRefreshClick: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "error icon",
            modifier.size(249.dp, 209.dp)
        )
        Text(
            text = stringResource(id = R.string.network_error_state),
            style = headerBold,
            color = Color.Base.black
        )
        CommonButton(Modifier.fillMaxWidth(), title = stringResource(id = R.string.refresh)) {
            onRefreshClick()
        }
    }
}