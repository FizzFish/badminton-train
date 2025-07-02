package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.CacheLoader
import com.github.benmanes.caffeine.cache.LoadingCache
import com.github.javaparser.ParseResult
import com.github.javaparser.ast.CompilationUnit
import java.nio.file.Path
import kotlin.io.path.PathsKt
import soot.SootClass

public class CompilationUnitOfSCFactory(locator: (SootClass) -> Path?) : AnalysisDataFactory<ParseResult<CompilationUnit>, CompilationUnitOfSCKey> {
   public final val locator: (SootClass) -> Path?
   public open val cache: LoadingCache<CompilationUnitOfSCKey, XOptional<ParseResult<CompilationUnit>?>>
   public open val key: Key<ParseResult<CompilationUnit>?>

   init {
      this.locator = locator;
      this.cache = AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), new CacheLoader(this) {
         {
            this.this$0 = `$receiver`;
         }

         public final ParseResult<CompilationUnit> load(CompilationUnitOfSCKey key) {
            val var10000: Path = this.this$0.getLocator().invoke(key.getSc()) as Path;
            if (var10000 == null) {
               return null;
            } else {
               return if (!(PathsKt.getExtension(var10000) == "java")) null else AnalysisCache.G.INSTANCE.get(new CompilationUnitAnalysisKey(var10000));
            }
         }
      });
      this.key = new AnalysisDataFactory.Key<ParseResult<CompilationUnit>>() {};
      AnalysisCache.G.INSTANCE.registerFactory(this as AnalysisDataFactory<ParseResult<CompilationUnit>, CompilationUnitOfSCKey>);
   }
}
