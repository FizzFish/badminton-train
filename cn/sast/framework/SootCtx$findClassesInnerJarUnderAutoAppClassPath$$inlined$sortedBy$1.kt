package cn.sast.framework

import cn.sast.common.IResFile
import java.util.Comparator
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 SootCtx.kt\ncn/sast/framework/SootCtx\n*L\n1#1,102:1\n315#2:103\n*E\n"])
internal class `SootCtx$findClassesInnerJarUnderAutoAppClassPath$$inlined$sortedBy$1`<T> : Comparator {
   fun `SootCtx$findClassesInnerJarUnderAutoAppClassPath$$inlined$sortedBy$1`(var1: SootCtx) {
      this.this$0 = var1;
   }

   // QF: local property
internal fun <T> `<anonymous>`(a: T, b: T): Int {
      return ComparisonsKt.compareValues(
         this.this$0.getMainConfig().tryGetRelativePath(a as IResFile).getRelativePath(),
         this.this$0.getMainConfig().tryGetRelativePath(b as IResFile).getRelativePath()
      );
   }
}
