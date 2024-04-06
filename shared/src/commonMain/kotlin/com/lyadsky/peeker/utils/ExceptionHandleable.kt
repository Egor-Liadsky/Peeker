package com.lyadsky.peeker.utils

import kotlin.coroutines.cancellation.CancellationException

suspend fun exceptionHandleable(
    executionBlock: suspend () -> Unit,
    failureBlock: (suspend (exception: Throwable) -> Unit)? = null,
    completionBlock: (suspend () -> Unit)? = null
) {
    try {
        executionBlock()
    } catch (exception: Throwable) {
        if (exception is CancellationException) throw exception
        println("Throwable caught, cause: ${exception.cause}, message: ${exception.message}")
        failureBlock?.invoke(exception)
    } finally {
        completionBlock?.invoke()
    }
}
