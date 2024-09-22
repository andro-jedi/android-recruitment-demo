package com.toptal.core.common

import kotlinx.coroutines.CancellationException

/**
 * Helper extension function for simplifying result handling
 */
inline fun <R> resultOf(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}
