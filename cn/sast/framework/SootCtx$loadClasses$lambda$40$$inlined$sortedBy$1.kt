package cn.sast.framework

import java.util.Comparator
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 SootCtx.kt\ncn/sast/framework/SootCtx\n*L\n1#1,102:1\n586#2:103\n*E\n"])
internal class `SootCtx$loadClasses$lambda$40$$inlined$sortedBy$1`<T> : Comparator {
   // QF: local property
internal fun <T> `<anonymous>`(a: T, b: T): Int {
      return ComparisonsKt.compareValues(a as java.lang.String, b as java.lang.String);
   }
}
