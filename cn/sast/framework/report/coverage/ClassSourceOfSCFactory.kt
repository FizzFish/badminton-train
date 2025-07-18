package cn.sast.framework.report.coverage

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.LoadingCache

public object ClassSourceOfSCFactory : AnalysisDataFactory<ClassSourceInfo, ClassSourceOfSCKey> {
   public open val cache: LoadingCache<ClassSourceOfSCKey, XOptional<ClassSourceInfo?>> =
      AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), <unrepresentable>.INSTANCE)
      public open val key: Key<ClassSourceInfo?> = (new AnalysisDataFactory.Key<ClassSourceInfo>() {}) as AnalysisDataFactory.Key

   @JvmStatic
   fun {
      AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
   }
}
