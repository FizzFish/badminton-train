package cn.sast.framework.plugin

import java.util.Comparator
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 SAConfiguration.kt\ncn/sast/framework/plugin/SAConfiguration\n*L\n1#1,102:1\n204#2:103\n*E\n"])
internal class `SAConfiguration$sort$$inlined$compareBy$2`<T> : Comparator {
   // QF: local property
internal fun <T> `<anonymous>`(a: T, b: T): Int {
      return ComparisonsKt.compareValues(a as CheckersConfig, b as CheckersConfig);
   }
}
