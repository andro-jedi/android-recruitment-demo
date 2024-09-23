package com.toptal.test

import com.toptal.core.common.DispatchersProvider
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher

/**
 * Test dispatchers provider for unit tests
 */
class TestDispatchersProvider(
    private val testDispatcher: TestDispatcher = StandardTestDispatcher()
) : DispatchersProvider {

    override val default
        get() = testDispatcher
    override val io
        get() = testDispatcher
    override val main
        get() = testDispatcher
    override val unconfined
        get() = testDispatcher
}
