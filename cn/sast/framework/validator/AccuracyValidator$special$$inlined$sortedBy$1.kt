package cn.sast.framework.validator

import cn.sast.api.report.ExpectBugAnnotationData
import java.util.Comparator
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 PrecisionMeasurement.kt\ncn/sast/framework/validator/AccuracyValidator\n*L\n1#1,102:1\n81#2:103\n*E\n"])
internal class `AccuracyValidator$special$$inlined$sortedBy$1`<T> : Comparator {
   // QF: local property
internal fun <T> `<anonymous>`(a: T, b: T): Int {
      return ComparisonsKt.compareValues((a as ExpectBugAnnotationData).getLine(), (b as ExpectBugAnnotationData).getLine());
   }
}
