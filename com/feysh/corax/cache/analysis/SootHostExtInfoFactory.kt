package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.CacheLoader
import com.github.benmanes.caffeine.cache.LoadingCache
import com.github.javaparser.JavaParser
import com.github.javaparser.ParseResult
import com.github.javaparser.ast.CompilationUnit
import soot.SootClass
import soot.SootField
import soot.SootMethod
import soot.tagkit.Host

public class SootHostExtInfoFactory(scFactory: CompilationUnitOfSCFactory) : AnalysisDataFactory<SootHostExtend, SootHostExtInfoKey> {
   public final val p: JavaParser = new JavaParser()
   public open val cache: LoadingCache<SootHostExtInfoKey, XOptional<SootHostExtend?>>
   public open val key: Key<SootHostExtend?>

   init {
      this.cache = AnalysisCacheKt.buildX(
         AnalysisDataFactory.Companion.getDefaultBuilder(),
         new CacheLoader(scFactory) {
            {
               this.$scFactory = `$scFactory`;
            }

            public final SootHostExtend load(SootHostExtInfoKey key) {
               val host: Host = key.getHost();
               var var10000: SootClass;
               if (host is SootClass) {
                  var10000 = host as SootClass;
               } else if (host is SootField) {
                  var10000 = (host as SootField).getDeclaringClass();
               } else {
                  if (host !is SootMethod) {
                     return null;
                  }

                  var10000 = (host as SootMethod).getDeclaringClass();
               }

               val var7: AnalysisCache.G = AnalysisCache.G.INSTANCE;
               val var8: ParseResult = var7.get(new CompilationUnitOfSCKey(var10000, this.$scFactory.getKey()));
               if (var8 == null) {
                  return null;
               } else if (!var8.getResult().isPresent()) {
                  return null;
               } else {
                  var10000 = (SootClass)var8.getResult().get();
                  return if (host is SootClass)
                     new SootClassExtend(host as SootClass, var10000 as CompilationUnit)
                     else
                     (
                        if (host is SootField)
                           new SootFieldExtend(host as SootField, var10000 as CompilationUnit)
                           else
                           (new SootMethodExtend(host as SootMethod, var10000 as CompilationUnit) as? SootHostExtend)
                     );
               }
            }
         }
      );
      this.key = new AnalysisDataFactory.Key<SootHostExtend>() {};
      AnalysisCache.G.INSTANCE.registerFactory(this as AnalysisDataFactory<SootHostExtend, SootHostExtInfoKey>);
   }
}
