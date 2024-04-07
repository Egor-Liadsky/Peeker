package com.lyadsky.peeker.android.ui.theme

import androidx.compose.ui.graphics.Color

object Color {

    object Base {
        val white = Color(0xFFFFFFFF)
        val black = Color(0xFF2A2F33)
        val purplePrimary = Color(0xFF8338EC)
    }

    object BottomBar {
        val selectedNavigationItem = Color(0xFF5D00F5)
        val unselectedNavigationItem = Color(0xFF9E9C9F)
        val background = Color(0xFFFFFFFF)
        val stroke = Color(0xFFEDEDED)
    }

    object TextField {
        val placeholder = Color(0xFF9E9C9F)
        val input = Color(0xFF2A2F33)
        val background = Color(0x05395673)
    }

    object SearchBanner {
        val defaultText = Color(0xFF2A2F33)
        val highlightText = Color(0xFF8338EC)
        val background = Color(0xFFE7DFFF)
    }
}