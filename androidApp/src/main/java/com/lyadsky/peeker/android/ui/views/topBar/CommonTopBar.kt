package com.lyadsky.peeker.android.ui.views.topBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.titleDespairDisplay

@Composable
fun CommonTopBar(
    title: String,
    onBackClick: (() -> Unit)? = null
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(Color.Base.white)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            onBackClick?.let {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { onBackClick() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "back icon",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Text(
                text = title,
                style = titleDespairDisplay,
                color = Color.Base.black,
            )
        }
    }
}