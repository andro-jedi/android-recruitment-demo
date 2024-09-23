package com.toptal.domain.exception

/**
 * Exception types for the domain layer
 *
 * This can be structured better by splitting it into a separate features and modules
 */
sealed class DomainException : Exception() {

    data class NetworkError(override val message: String? = null) : DomainException()
    data class DatabaseError(override val message: String? = null) : DomainException()
    data class UnknownError(override val message: String? = null) : DomainException()
}
