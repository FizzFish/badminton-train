package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.LoadingCache
import com.github.javaparser.ParseResult
import com.github.javaparser.ast.CompilationUnit

public object CompilationUnitAnalysisDataFactory : AnalysisDataFactory<ParseResult<CompilationUnit>, CompilationUnitAnalysisKey> {
   public open val cache: LoadingCache<CompilationUnitAnalysisKey, XOptional<ParseResult<CompilationUnit>>> =
      AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), <unrepresentable>.INSTANCE)
      public open val key: Key<ParseResult<CompilationUnit>> = (new AnalysisDataFactory.Key<ParseResult<CompilationUnit>>() {}) as AnalysisDataFactory.Key

   @JvmStatic
   fun {
      AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
   }
}
