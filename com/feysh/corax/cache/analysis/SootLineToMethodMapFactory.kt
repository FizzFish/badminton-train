package com.feysh.corax.cache.analysis

import com.feysh.corax.cache.AnalysisCache
import com.feysh.corax.cache.AnalysisCacheKt
import com.feysh.corax.cache.AnalysisDataFactory
import com.feysh.corax.cache.XOptional
import com.feysh.corax.cache.AnalysisDataFactory.Key
import com.github.benmanes.caffeine.cache.LoadingCache
import java.util.TreeMap
import soot.SootClass

public object SootLineToMethodMapFactory : AnalysisDataFactory<SootLineToMethodMap, SootLineToMethodMapKey> {
   public open val cache: LoadingCache<SootLineToMethodMapKey, XOptional<SootLineToMethodMap?>> =
      AnalysisCacheKt.buildX(AnalysisDataFactory.Companion.getDefaultBuilder(), <unrepresentable>.INSTANCE)
      public open val key: Key<SootLineToMethodMap?> = (new AnalysisDataFactory.Key<SootLineToMethodMap>() {}) as AnalysisDataFactory.Key

   public fun getSootMethodAtLine(sc: SootClass, ln: Int, check: Boolean = true): SootMethodAndRange? {
      val lineToMethodMap: SootLineToMethodMap = AnalysisCache.G.INSTANCE.get(new SootLineToMethodMapKey(sc));
      if (lineToMethodMap != null) {
         val var10000: TreeMap = lineToMethodMap.getMap();
         if (var10000 != null) {
            val var9: Int = if (var10000.containsKey(ln)) ln else var10000.lowerKey(ln);
            if (var9 != null) {
               val var10: Any = lineToMethodMap.getMap().get(var9);
               val range: SootMethodAndRange = var10 as SootMethodAndRange;
               if (check && ln > ((var10 as SootMethodAndRange).getRange().getSecond() as java.lang.Number).intValue()) {
                  return null;
               }

               return range;
            }
         }
      }

      return null;
   }

   @JvmStatic
   fun {
      AnalysisCache.G.INSTANCE.registerFactory(INSTANCE);
   }
}
