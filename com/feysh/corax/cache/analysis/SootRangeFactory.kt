package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.LoadingCache

public object SootRangeFactory : AnalysisDataFactory<Pair<? extends Integer, ? extends Integer>, SootRangeKey> {
   public open val cache: LoadingCache<SootRangeKey, XOptional<Pair<Int, Int>?>> =
      AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), <unrepresentable>.INSTANCE)
      public open val key: Key<Pair<Int, Int>?> = (new AnalysisDataFactory.Key<Pair<? extends Integer, ? extends Integer>>() {}) as AnalysisDataFactory.Key

   @JvmStatic
   fun {
      AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
   }
}
