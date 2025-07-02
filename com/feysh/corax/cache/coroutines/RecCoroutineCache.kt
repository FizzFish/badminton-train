package com.feysh.corax.cache.coroutines

import kotlin.coroutines.Continuation
import kotlinx.coroutines.Deferred

public interface RecCoroutineCache<K, V> : CoroutineCache {
   public abstract suspend fun get(key: Any, mappingFunction: (RecCoroutineCache<Any, Any>, Any, Continuation<Any>) -> Any?): Deferred<Any>? {
   }

   public abstract suspend fun getEntry(key: Any, mappingFunction: (RecCoroutineCache<Any, Any>, Any, Continuation<Any>) -> Any?): Deferred<Any>? {
   }

   public abstract suspend fun getPredSize(): Int {
   }

   public abstract fun cleanUp() {
   }

   public abstract fun validateAfterFinished(): Boolean {
   }
}
