package com.lyadsky.peeker.android.components.screen.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.views.topBar.CommonTopBar
import com.lyadsky.peeker.components.screen.chat.ChatComponent

@Composable
fun ChatScreen(component: ChatComponent) {

    Column(Modifier.fillMaxSize()) {

        CommonTopBar(title = stringResource(id = R.string.ai_assistant_top_bar))
    }
}