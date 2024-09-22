package com.toptal.core.common

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AndroidDispatchersProvider @Inject constructor() : DispatchersProvider {
    override val main = Dispatchers.Main
    override val io = Dispatchers.IO
    override val default = Dispatchers.Default
    override val unconfined = Dispatchers.Unconfined
}
