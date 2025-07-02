package cn.sast.idfa.analysis

import java.time.LocalDateTime
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.Ref.ObjectRef
import org.utbot.common.LoggingKt
import org.utbot.common.Maybe

// $VF: Class flags could not be determined
@SourceDebugExtension(["SMAP\nLogging.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Logging.kt\norg/utbot/common/LoggingKt$bracket$4\n+ 2 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis$doAnalysis$1\n*L\n1#1,70:1\n549#2:71\n*E\n"])
internal class `ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$2` : Function0<Object> {
   fun `ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$2`(`$startTime`: LocalDateTime, `$msg`: java.lang.String, `$res`: ObjectRef) {
      this.$startTime = `$startTime`;
      this.$msg = `$msg`;
      this.$res = `$res`;
   }

   fun invoke(): Any {
      val var1: LocalDateTime = this.$startTime;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${this.$msg} ${Result.box-impl(
         Result.constructor-impl((this.$res.element as Maybe).getOrThrow())
      )}";
   }
}
