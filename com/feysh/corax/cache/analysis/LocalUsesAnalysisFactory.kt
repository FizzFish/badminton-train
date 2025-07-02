package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.LoadingCache
import soot.toolkits.scalar.LocalUses

public object LocalUsesAnalysisFactory : AnalysisDataFactory<LocalUses, LocalUsesAnalysis> {
   public open val cache: LoadingCache<LocalUsesAnalysis, XOptional<LocalUses>> =
      AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), <unrepresentable>.INSTANCE)
      public open val key: Key<LocalUses> = (new AnalysisDataFactory.Key<LocalUses>() {}) as AnalysisDataFactory.Key

   @JvmStatic
   fun {
      AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
   }
}
