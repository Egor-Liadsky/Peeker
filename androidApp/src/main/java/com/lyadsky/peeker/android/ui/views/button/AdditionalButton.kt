package com.lyadsky.peeker.android.ui.views.button

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.models.PrivacyPolicy
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.Typography
import com.lyadsky.peeker.android.ui.views.divider.CommonDivider
import com.lyadsky.peeker.android.utils.conditional

@Composable
fun AdditionalButton(privacyPolicy: PrivacyPolicy) {
    val showDescriptionState = remember { mutableStateOf(false) }
    Column(Modifier.animateContentSize()) {
        TextButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { showDescriptionState.value = !showDescriptionState.value },
            shape = RoundedCornerShape(0.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = privacyPolicy.title,
                    style = Typography.Additional.title,
                    modifier = Modifier.fillMaxWidth(0.9f),
                    color = Color.AdditionalInfoItem.text
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_bottom),
                    contentDescription = "ic arrow bottom",
                    modifier = Modifier
                        .size(12.dp)
                        .conditional(showDescriptionState.value) {
                            rotate(180f)
                        },
                    tint = Color.AdditionalInfoItem.arrow
                )
            }
        }
        AnimatedVisibility(visible = showDescriptionState.value) {
            Text(
                text = privacyPolicy.description,
                style = Typography.Additional.description,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
            )
        }
        CommonDivider(Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp))
    }
}