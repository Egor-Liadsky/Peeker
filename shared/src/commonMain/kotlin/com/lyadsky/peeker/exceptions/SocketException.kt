package com.lyadsky.peeker.exceptions

data class SocketException(
    private val _message: String? = null
): Exception(_message)