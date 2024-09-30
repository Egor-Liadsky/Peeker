package com.lyadsky.peeker.android.components.screen.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.headerBold
import com.lyadsky.peeker.android.ui.views.textField.ChatTextField
import com.lyadsky.peeker.android.ui.views.topBar.CommonTopBar
import com.lyadsky.peeker.components.screen.chat.ChatComponent

@Composable
fun ChatScreen(component: ChatComponent) {

    val state = component.viewStates.subscribeAsState()

    Column(Modifier.fillMaxSize()) {

        CommonTopBar(title = stringResource(id = R.string.ai_assistant_top_bar))

        LazyColumn(
            Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text(
                    text = "В РАЗРАБОТКЕ",
                    style = headerBold,
                    color = Color.Base.black
                )
            }
        }

        ChatTextField(
            textInput = state.value.chatTextFieldValue,
            placeholder = stringResource(id = R.string.what_looking_placeholder)
        ) {
            component.chatTextFieldValueChanged(it)
        }
    }
}