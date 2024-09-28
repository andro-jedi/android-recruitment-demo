package com.toptal.domain.exception

sealed interface GeneralError : DomainError {

    sealed interface Network : GeneralError {
        data class ServerError(val code: Int) : Network

        data object RequestTimeout : Network
        data object NoConnection : Network
        data object Unauthorized : Network
        data object BadRequest : Network
        data object Unknown : Network
    }

    sealed interface Local : GeneralError {
        data object NotFound : Local
        data object Unknown : Local
    }

    data class Unknown(val message: String?) : GeneralError
}
