package com.toptal.core.common

import com.toptal.domain.exception.DataError
import com.toptal.domain.exception.DomainError
import com.toptal.domain.exception.ExceptionMapper
import java.io.IOException

class DefaultExceptionMapper : ExceptionMapper {

    override fun map(throwable: Throwable): DomainError {
        return when (throwable) {
            is IOException -> DataError.Network.NO_INTERNET
            else -> DataError.Network.UNKNOWN
        }
    }
}
