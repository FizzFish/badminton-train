package com.feysh.corax.cache.analysis

import com.github.javaparser.ast.body.CallableDeclaration
import java.util.Comparator
import java.util.Optional
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 SootHostExtend.kt\ncom/feysh/corax/cache/analysis/SootMethodExtend\n*L\n1#1,102:1\n81#2:103\n*E\n"])
internal class `SootMethodExtend$getMethods$$inlined$sortedBy$1`<T> : Comparator {
   // QF: local property
internal fun <T> `<anonymous>`(a: T, b: T): Int {
      var var10000: Optional = (a as CallableDeclaration).getBegin();
      val var8: java.lang.Comparable = SootHostExtendKt.getLine(var10000);
      var10000 = (b as CallableDeclaration).getBegin();
      return ComparisonsKt.compareValues(var8, SootHostExtendKt.getLine(var10000));
   }
}
