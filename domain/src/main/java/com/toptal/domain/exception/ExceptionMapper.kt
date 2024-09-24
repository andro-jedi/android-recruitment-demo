package com.toptal.domain.exception

/**
 * Used to keep code clean and delegate exception mapping to the mapper
 */
interface ExceptionMapper {
    fun map(throwable: Throwable) : DomainError
}
