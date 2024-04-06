package com.lyadsky.peeker.utils

sealed class LoadingState {
    data object Loading: LoadingState()

    data object Success: LoadingState()

    data object Empty: LoadingState()

    data class Error(val error: String): LoadingState()
}