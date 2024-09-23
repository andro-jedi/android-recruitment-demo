package com.toptal.test

import com.toptal.core.common.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Base class for unit test classes
 * Simplifies setting up and tearing down test coroutines
 */
open class CoroutineTest(testDispatcher: TestDispatcher = StandardTestDispatcher()) {

    val dispatchers = TestDispatchersProvider(testDispatcher)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(dispatchers)
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val dispatchers: DispatchersProvider
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatchers.main)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}
