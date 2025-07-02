package com.feysh.corax.cache

import com.github.benmanes.caffeine.cache.CacheLoader
import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache

public fun <K1, V1> Caffeine<Any?, Any?>.buildX(loader: CacheLoader<K1, V1>): LoadingCache<K1, XOptional<V1>> {
   val x: LoadingCache = `$this$buildX`.build(new CacheLoader(AnalysisCacheKt::buildX$lambda$0) {
      {
         this.function = function;
      }
   });
   return x;
}

fun `buildX$lambda$0`(`$loader`: CacheLoader, k: Any): XOptional {
   return XOptional.Companion.of(`$loader`.load(k));
}
