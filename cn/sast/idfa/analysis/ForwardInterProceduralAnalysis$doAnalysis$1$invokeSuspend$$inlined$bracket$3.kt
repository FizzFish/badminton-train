package cn.sast.idfa.analysis

import java.time.LocalDateTime
import kotlin.jvm.functions.Function0
import org.utbot.common.LoggingKt

// $VF: Class flags could not be determined
internal class `ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$3` : Function0<Object> {
   fun `ForwardInterProceduralAnalysis$doAnalysis$1$invokeSuspend$$inlined$bracket$3`(`$startTime`: LocalDateTime, `$msg`: java.lang.String) {
      this.$startTime = `$startTime`;
      this.$msg = `$msg`;
   }

   fun invoke(): Any {
      val var1: LocalDateTime = this.$startTime;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${this.$msg} <Nothing>";
   }
}
