package com.toptal.domain.exception

sealed interface DataError : DomainError {
    enum class Network : DataError {
        REQUEST_TIMEOUT,
        NO_INTERNET,
        NOT_FOUND,
        UNKNOWN
    }

    enum class Local : DataError {
        DISK_FULL
    }
}
