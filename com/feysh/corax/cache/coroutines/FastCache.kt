package com.feysh.corax.cache.coroutines

import kotlin.coroutines.Continuation
import kotlinx.coroutines.CoroutineScope

public interface FastCache {
   public abstract fun <K, V> buildRecCoroutineCache(scope: CoroutineScope, weakKeyAssociateByValue: (V) -> Array<Any?>): RecCoroutineCache<K, V> {
   }

   public abstract fun <K, V> buildRecCoroutineLoadingCache(
      scope: CoroutineScope,
      weakKeyAssociateByValue: (V) -> Array<Any?>,
      mappingFunction: (RecCoroutineLoadingCache<K, V>, K, Continuation<V>) -> Any?
   ): RecCoroutineLoadingCache<K, V> {
   }
}
