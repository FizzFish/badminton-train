package com.feysh.corax.cache.coroutines

import java.util.concurrent.Executor
import kotlin.time.Duration
import kotlinx.coroutines.Deferred

public fun interface Scheduler {
   public abstract fun schedule(executor: Executor, command: () -> Unit, duration: Duration): Deferred<Unit> {
   }
}
