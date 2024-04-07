package com.lyadsky.peeker.android.components.screen.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lyadsky.peeker.components.screen.chat.ChatComponent

@Composable
fun ChatScreen(component: ChatComponent) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Chat Screen")
    }
}