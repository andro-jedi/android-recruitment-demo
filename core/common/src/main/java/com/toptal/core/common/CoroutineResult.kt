package com.toptal.core.common

import com.toptal.domain.exception.ExceptionMapper
import com.toptal.domain.helper.Result
import com.toptal.mappers.DefaultExceptionMapper
import kotlin.coroutines.cancellation.CancellationException

/**
 * Helper extension function for simplifying result handling
 */
inline fun <R> resultOf(
    exceptionMapper: ExceptionMapper = DefaultExceptionMapper(),
    block: () -> R
): Result<R> {
    return try {
        Result.Success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.Failure(exceptionMapper.map(e))
    }
}