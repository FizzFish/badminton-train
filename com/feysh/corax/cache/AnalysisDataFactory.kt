package com.feysh.corax.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache
import java.util.concurrent.TimeUnit

public interface AnalysisDataFactory<T, K extends AnalysisKey<T>> {
   public val cache: LoadingCache<Any, XOptional<Any>>
   public val key: com.feysh.corax.cache.AnalysisDataFactory.Key<Any>

   public companion object {
      public final val defaultBuilder: Caffeine<Any?, Any?>

      @JvmStatic
      fun {
         val var10000: Caffeine = Caffeine.newBuilder().expireAfterAccess(15L, TimeUnit.SECONDS).softValues();
         defaultBuilder = var10000;
      }
   }

   public open class Key<T>
}
