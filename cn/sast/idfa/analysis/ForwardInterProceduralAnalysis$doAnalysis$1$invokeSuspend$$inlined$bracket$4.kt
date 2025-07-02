package cn.sast.idfa.analysis

import java.time.LocalDateTime
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import org.utbot.common.LoggingKt

// $VF: Class flags could not be determined
@SourceDebugExtension(["SMAP\nLogging.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Logging.kt\norg/utbot/common/LoggingKt$bracket$3\n+ 2 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis$doAnalysis$1\n*L\n1#1,64:1\n549#2:65\n*E\n"])
internal class `ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$4` : Function0<Object> {
   fun `ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$4`(
      `$startTime`: LocalDateTime, `$msg`: java.lang.String, `$t`: java.lang.Throwable
   ) {
      this.$startTime = `$startTime`;
      this.$msg = `$msg`;
      this.$t = `$t`;
   }

   fun invoke(): Any {
      val var1: LocalDateTime = this.$startTime;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${this.$msg} :: EXCEPTION :: ${Result.box-impl(
         Result.constructor-impl(ResultKt.createFailure(this.$t))
      )}";
   }
}
