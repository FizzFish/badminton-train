package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.LoadingCache
import soot.toolkits.scalar.LocalDefs

public object LocalDefsAnalysisFactory : AnalysisDataFactory<LocalDefs, LocalDefsAnalysis> {
   public open val cache: LoadingCache<LocalDefsAnalysis, XOptional<LocalDefs>> =
      AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), <unrepresentable>.INSTANCE)
      public open val key: Key<LocalDefs> = (new AnalysisDataFactory.Key<LocalDefs>() {}) as AnalysisDataFactory.Key

   @JvmStatic
   fun {
      AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
   }
}
