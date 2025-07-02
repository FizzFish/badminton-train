package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.LoadingCache
import soot.SootMethod

public object ResolveAbstractDispatchFactory : AnalysisDataFactory<java.util.Set<? extends SootMethod>, ResolveAbstractDispatchKey> {
   public open val cache: LoadingCache<ResolveAbstractDispatchKey, XOptional<Set<SootMethod>>> =
      AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), <unrepresentable>.INSTANCE)
      public open val key: Key<Set<SootMethod>> = (new AnalysisDataFactory.Key<java.util.Set<? extends SootMethod>>() {}) as AnalysisDataFactory.Key

   @JvmStatic
   fun {
      AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
   }
}
