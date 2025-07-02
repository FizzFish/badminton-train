package com.feysh.corax.cache.coroutines

import kotlinx.coroutines.Deferred

public interface RecCoroutineLoadingCache<K, V> : RecCoroutineCache<K, V> {
   public abstract suspend fun get(key: Any): Deferred<Any>? {
   }

   public abstract suspend fun getEntry(key: Any): Deferred<Any>? {
   }
}
