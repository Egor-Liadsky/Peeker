package com.lyadsky.peeker.components.screen.feedback

interface FeedbackComponent {

    fun onBackButtonClick()

    fun getFeedbackEmail(): String
}