package cn.sast.api.util

import java.util.Comparator
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 ComparatorUtils.kt\ncn/sast/api/util/ComparatorUtilsKt\n*L\n1#1,102:1\n23#2:103\n*E\n"])
internal class `ComparatorUtilsKt$compareToMap$$inlined$compareBy$2`<T> : Comparator {
   // QF: local property
internal fun <T> `<anonymous>`(a: T, b: T): Int {
      return ComparisonsKt.compareValues((a as Pair).getSecond() as java.lang.Comparable, (b as Pair).getSecond() as java.lang.Comparable);
   }
}
