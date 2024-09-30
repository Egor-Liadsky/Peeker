package com.lyadsky.peeker.components.screen.chat

import com.arkivanov.decompose.value.Value

interface ChatComponent {

    val viewStates: Value<ChatState>

    fun chatTextFieldValueChanged(value: String)
}