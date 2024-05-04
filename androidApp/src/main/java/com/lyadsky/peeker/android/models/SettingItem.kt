package com.lyadsky.peeker.android.models

import androidx.annotation.DrawableRes

data class SettingItem (
    val title: String,
    @DrawableRes
    val icon: Int,
    val onClick: () -> Unit
)