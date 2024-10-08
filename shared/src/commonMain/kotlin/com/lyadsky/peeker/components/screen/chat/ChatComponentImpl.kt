package com.lyadsky.peeker.components.screen.chat

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent

class ChatComponentImpl(
    componentContext: ComponentContext
) : ChatComponent, BaseComponent<ChatState>(componentContext, ChatState()) {

    override fun chatTextFieldValueChanged(value: String) {
        viewState = viewState.copy(chatTextFieldValue = value)
    }
}