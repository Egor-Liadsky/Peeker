package com.lyadsky.peeker.android.components.screen.feedback

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.Typography
import com.lyadsky.peeker.android.ui.views.button.CommonButton
import com.lyadsky.peeker.android.ui.views.topBar.CommonTopBar
import com.lyadsky.peeker.components.screen.feedback.FeedbackComponent

@Composable
fun FeedbackScreen(component: FeedbackComponent) {

    val context = LocalContext.current

    val emailIntent = Intent(Intent.ACTION_SENDTO)
    emailIntent.data = Uri.parse("mailto:")
    emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(component.getFeedbackEmail()))
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, stringResource(id = R.string.feedback))

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Base.white)
    ) {
        CommonTopBar(title = stringResource(id = R.string.feedback_top_bar)) {
            component.onBackButtonClick()
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.feedback_description),
                style = Typography.Additional.title,
                modifier = Modifier.width(280.dp),
                textAlign = TextAlign.Center
            )
            CommonButton(
                Modifier
                    .width(200.dp)
                    .padding(top = 16.dp),
                title = stringResource(id = R.string.write)
            ) {

                context.startActivity(
                    Intent.createChooser(
                        emailIntent,
                        context.resources.getResourceName(R.string.feedback)
                    )
                )
            }
        }
    }
}