package com.lyadsky.peeker.android.components.screen.settings.layouts

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.Typography

@Composable
fun AboutAppView(appVersion: String, vkLink: String, telegramLink: String) {

    val context = LocalContext.current

    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name_full),
            style = Typography.AboutApp.peekerTitle
        )

        Text(
            text = stringResource(id = R.string.all_rights_reserved),
            style = Typography.AboutApp.copyright,
            modifier = Modifier.padding(top = 10.dp)
        )

        Row(
            Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(telegramLink))) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_telegram),
                    contentDescription = "telegram icon",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Base.black
                )
            }
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(vkLink))) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_vk),
                    contentDescription = "vk icon",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Base.black
                )
            }
        }

        Text(
            text = stringResource(id = R.string.app_version) + " $appVersion",
            style = Typography.AboutApp.version
        )
    }
}