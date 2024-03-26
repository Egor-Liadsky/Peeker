package com.lyadsky.peeker.components.chat

import com.arkivanov.decompose.ComponentContext
import com.lyadsky.peeker.components.BaseComponent

class ChatComponentImpl(
    componentContext: ComponentContext
) : ChatComponent, BaseComponent<Unit>(componentContext, Unit)