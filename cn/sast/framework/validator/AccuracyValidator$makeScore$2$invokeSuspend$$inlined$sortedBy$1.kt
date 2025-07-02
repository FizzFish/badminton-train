package cn.sast.framework.validator

import java.util.Comparator
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 PrecisionMeasurement.kt\ncn/sast/framework/validator/AccuracyValidator$makeScore$2\n*L\n1#1,102:1\n276#2:103\n*E\n"])
internal class `AccuracyValidator$makeScore$2$invokeSuspend$$inlined$sortedBy$1`<T> : Comparator {
   // QF: local property
internal fun <T> `<anonymous>`(a: T, b: T): Int {
      return ComparisonsKt.compareValues((a as RowType).toString(), (b as RowType).toString());
   }
}
