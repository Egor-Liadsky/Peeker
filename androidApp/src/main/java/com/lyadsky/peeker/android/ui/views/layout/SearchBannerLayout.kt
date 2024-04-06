package com.lyadsky.peeker.android.ui.views.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.despairBold

@Composable
fun SearchBannerLayout(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.SearchBanner.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(Color.SearchBanner.defaultText)) {
                    append(stringResource(id = R.string.ai_banner1))
                }
                withStyle(style = SpanStyle(Color.SearchBanner.highlightText)) {
                    append(" ")
                    append(stringResource(id = R.string.ai_banner2))
                    append(" ")
                }
                withStyle(style = SpanStyle(Color.SearchBanner.defaultText)) {
                    append(stringResource(id = R.string.ai_banner3))
                }
                withStyle(style = SpanStyle(Color.SearchBanner.highlightText)) {
                    append(" ")
                    append(stringResource(id = R.string.ai_banner4))
                    append(" ")
                }
                withStyle(style = SpanStyle(Color.SearchBanner.defaultText)) {
                    append(stringResource(id = R.string.ai_banner5))
                }
            },
            style = despairBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            lineHeight = 20.sp
        )
    }
}