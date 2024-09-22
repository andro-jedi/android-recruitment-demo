package com.toptal.core.common

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Interface to provide dispatchers for testing
 */
interface DispatchersProvider {

    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}
