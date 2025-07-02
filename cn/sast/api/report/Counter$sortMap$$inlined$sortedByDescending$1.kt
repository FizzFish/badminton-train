package cn.sast.api.report

import java.util.Comparator
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1\n+ 2 Counter.kt\ncn/sast/api/report/Counter\n*L\n1#1,121:1\n26#2:122\n*E\n"])
internal class `Counter$sortMap$$inlined$sortedByDescending$1`<T> : Comparator {
   // QF: local property
internal fun <T> `<anonymous>`(a: T, b: T): Int {
      return ComparisonsKt.compareValues((b as Entry).getValue() as Int, (a as Entry).getValue() as Int);
   }
}
