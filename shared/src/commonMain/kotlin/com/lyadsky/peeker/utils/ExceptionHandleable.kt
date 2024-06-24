package com.lyadsky.peeker.utils

import androidx.paging.PagingSource
import kotlin.coroutines.cancellation.CancellationException

suspend fun exceptionHandleable(
    executionBlock: suspend () -> Unit,
    failureBlock: (suspend (exception: Throwable) -> Unit)? = null,
    completionBlock: (suspend () -> Unit)? = null
) {
    try {
        executionBlock()
    } catch (exception: Exception) {
        if (exception is CancellationException) throw exception
        println("Throwable caught, cause: ${exception.cause}, message: ${exception.message}")
        failureBlock?.invoke(exception)
    } finally {
        completionBlock?.invoke()
    }
}

suspend fun <K : Any, T : Any> exceptionHandleablePaging(
    executionBlock: suspend () -> PagingSource.LoadResult<K, T>,
    failureBlock: suspend (exception: Throwable) -> PagingSource.LoadResult<K, T>,
): PagingSource.LoadResult<K, T> {
    return try {
        executionBlock()
    } catch (exception: Exception) {
        if (exception is CancellationException) throw exception
        println("Throwable caught, cause: ${exception.cause}, message: ${exception.message}")
        failureBlock(exception)
    }
}