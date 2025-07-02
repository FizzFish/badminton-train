package com.feysh.corax.cache.coroutines

import com.github.benmanes.caffeine.cache.stats.CacheStats

public interface CoroutineCache {
   public val cacheStats: CacheStats
   public val size: Long
}
