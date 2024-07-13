package com.lyadsky.peeker.android.components.screen.onboarding

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.lyadsky.peeker.android.R
import com.lyadsky.peeker.android.models.Onboarding
import com.lyadsky.peeker.android.ui.theme.Color
import com.lyadsky.peeker.android.ui.theme.titleDespairDisplay
import com.lyadsky.peeker.android.ui.views.button.CommonButton
import com.lyadsky.peeker.android.ui.views.pager.PageIndicator
import com.lyadsky.peeker.components.screen.onboarding.OnboardingComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(component: OnboardingComponent) {

    val scope = rememberCoroutineScope()


    val onboardingList = listOf(
        Onboarding(
            title = { OnboardingTitleTextItem() },
            image = painterResource(id = R.drawable.image_onboarding1_200x399),
        ),
        Onboarding(
            title = { OnboardingTextItem(title = stringResource(id = R.string.onboarding_title_2)) },
            image = painterResource(id = R.drawable.image_onboarding2_200x399)
        ),
        Onboarding(
            title = { OnboardingTextItem(title = stringResource(id = R.string.onboarding_title_3)) },
            image = painterResource(id = R.drawable.image_onboarding3_200x399)
        )
    )

    val pagerState = rememberPagerState { onboardingList.size }

    BackHandler(enabled = pagerState.currentPage != 0) {
        if (pagerState.currentPage > 0) {
            scope.launch {
                pagerState.animateScrollToPage(
                    pagerState.currentPage - 1,
                    animationSpec = spring(stiffness = Spring.StiffnessLow),
                )
            }
        }
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(state = pagerState) {
            OnboardingItem(onboarding = onboardingList[it])
        }
        PageIndicator(
            currentPage = pagerState.currentPage,
            count = onboardingList.size,
        )

        val textButton =
            if (pagerState.currentPage != onboardingList.lastIndex) stringResource(id = R.string.next)
            else stringResource(id = R.string.start)

        CommonButton(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(top = 40.dp),
            title = textButton
        ) {
            if (pagerState.currentPage == onboardingList.lastIndex) {
                component.onNextButtonClick()
            } else {
                scope.launch {
                    pagerState.animateScrollToPage(
                        pagerState.currentPage + 1,
                        animationSpec = spring(stiffness = Spring.StiffnessLow),
                    )
                }
            }
        }
    }
}

@Composable
private fun OnboardingTitleTextItem() {
    Row(Modifier.fillMaxWidth()) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        color = Color.Base.black,
                        fontFamily = titleDespairDisplay.fontFamily,
                        fontSize = titleDespairDisplay.fontSize
                    )
                ) {
                    withStyle(SpanStyle(color = Color.Base.purplePrimary)) {
                        append(stringResource(id = R.string.onboarding_title_1_1))
                    }
                    append(" ")
                    append(stringResource(id = R.string.onboarding_title_1_2))
                }
            },
        )
    }
}


@Composable
private fun OnboardingTextItem(
    title: String,
    color: androidx.compose.ui.graphics.Color = Color.Base.black
) {
    Text(
        text = title,
        style = titleDespairDisplay,
        color = color,
        textAlign = TextAlign.Start
    )
}

@Composable
private fun OnboardingItem(onboarding: Onboarding) {

    Column(
        Modifier
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            Modifier
                .fillMaxHeight(0.1f),
            verticalArrangement = Arrangement.Center
        ) {
            onboarding.title()
        }

        Image(
            painter = onboarding.image,
            contentDescription = "Onboarding image",
            Modifier
                .size(200.dp, 399.dp)
                .padding(vertical = 25.dp)
        )
    }
}