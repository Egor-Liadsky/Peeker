package com.lyadsky.peeker.android.ui.theme

import androidx.compose.ui.graphics.Color

object Color {

    object Base {
        val white = Color(0xFFFFFFFF)
        val black = Color(0xFF2A2F33)
        val gray = Color(0xFFDBDBDB)
        val purplePrimary = Color(0xFF8338EC)
    }

    object Shimmer {
        val background1 = Color(0xFFDBDBDB).copy(alpha = 0.4f)
        val background2 = Color(0xFFDBDBDB).copy(alpha = 0.2f)
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
        val icon = Color(0xFF979797)
    }

    object ChatTextField {
        val placeholder = Color(0xFF9E9C9F)
        val input = Color(0xFF2A2F33)
        val background = Color(0xFFFFFFFF)
        val icon = Color(0xFF979797)
    }

    object SearchBanner {
        val defaultText = Color(0xFF2A2F33)
        val highlightText = Color(0xFF8338EC)
        val background = Color(0xFFE7DFFF)
    }

    object Checkbox {
        val checked = Color(0xFF7E2EFF)
        val unchecked = Color(0xFF2A2F33)
    }

    object Divider {
        val background = Color(0xFFDBDBDB)
    }

    object ProductCard {
        val imageBackground = Color(0x05395673)
        val background = Color(0xFFFFFFFF)
    }

    object CommonButton {
        val title = Color(0xFFFFFFFF)
        val background = Color(0xFF2A2F33)
    }

    object AboutApp {
        val copyright = Color(0xFF677178)
        val version = Color(0xFF9E9C9F)
    }

    object RadioButton {
        val selectedSorting = Color(0xFF5D00F5)
        val unselectedSorting = Color(0xFF2A2F33)
    }

    object PageIndicator {
        val background = Color(0xFF9E9C9F)
    }

    object AdditionalInfoItem {
        val text = Color(0xFF2A2F33)
        val arrow = Color(0xFF2A2F33)
    }
}