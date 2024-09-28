package com.toptal.core.common

import com.toptal.domain.exception.DomainError
import com.toptal.domain.exception.ExceptionMapper
import com.toptal.domain.exception.GeneralError

class GeneralExceptionMapper : ExceptionMapper {

    override fun map(throwable: Throwable): DomainError {
        return GeneralError.Unknown(throwable.message)
    }
}
