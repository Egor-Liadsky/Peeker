package com.lyadsky.peeker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform