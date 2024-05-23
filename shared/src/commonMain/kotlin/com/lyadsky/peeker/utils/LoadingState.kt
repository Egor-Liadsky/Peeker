package com.lyadsky.peeker.utils

sealed class LoadingState {
    data object Loading: LoadingState()

    data object Success: LoadingState()

    data class Empty(val type: EmptyType): LoadingState()

    data class Error(val error: String): LoadingState()
}

enum class EmptyType {
    NotFound,
    EmptyTextField
}