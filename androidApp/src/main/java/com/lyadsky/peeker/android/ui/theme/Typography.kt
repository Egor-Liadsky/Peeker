package com.lyadsky.peeker.android.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object Typography {

    object AboutApp {

        val peekerTitle = TextStyle(
            fontSize = 16.sp,
            fontFamily = despairDisplay,
            fontWeight = FontWeight.Bold,
            color = Color.Base.black
        )

        val copyright = TextStyle(
            fontSize = 16.sp,
            fontFamily = gilroy,
            fontWeight = FontWeight.Medium,
            color = Color.AboutApp.copyright
        )

        val version = TextStyle(
            fontSize = 16.sp,
            fontFamily = gilroy,
            fontWeight = FontWeight.Normal,
            color = Color.AboutApp.version
        )
    }
}

val titleHomeTopBar = TextStyle(
    fontSize = 14.sp,
    fontFamily = despairDisplay,
    fontWeight = FontWeight.Bold
)

val titleTopBar = TextStyle(
    fontSize = 16.sp,
    fontFamily = despairDisplay,
    fontWeight = FontWeight.Bold
)

val headerBold = TextStyle(
    fontSize = 16.sp,
    fontFamily = gilroy,
    fontWeight = FontWeight.Bold,
)

val marketplaceHeader = TextStyle(
    fontSize = 14.sp,
    fontFamily = gilroy,
    fontWeight = FontWeight.Bold
)

val buttonTitle = TextStyle(
    fontSize = 14.sp,
    fontFamily = gilroy,
    fontWeight = FontWeight.Bold
)

val textField = TextStyle(
    fontSize = 14.sp,
    fontFamily = gilroy,
    fontWeight = FontWeight.SemiBold
)

val defaultMedium = TextStyle(
    fontSize = 12.sp,
//    fontFamily = ,
)

val defaultSemibold = TextStyle(
    fontSize = 12.sp,
    fontFamily = gilroy,
    fontWeight = FontWeight.SemiBold
)

val headerSemibold = TextStyle(
    fontSize = 14.sp,
    fontFamily = gilroy,
    fontWeight = FontWeight.SemiBold
)

val settingTitleItemView = TextStyle(
    fontSize = 14.sp,
    fontFamily = gilroy,
    fontWeight = FontWeight.SemiBold
)
