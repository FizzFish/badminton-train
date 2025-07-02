package cn.sast.idfa.analysis

import java.time.LocalDateTime
import kotlin.jvm.functions.Function0
import org.utbot.common.LoggingKt

// $VF: Class flags could not be determined
internal class `ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$6` : Function0<Object> {
   fun `ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$6`(`$startTime`: LocalDateTime, `$msg`: java.lang.String) {
      this.$startTime = `$startTime`;
      this.$msg = `$msg`;
   }

   fun invoke(): Any {
      val var1: LocalDateTime = this.$startTime;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${this.$msg} <Nothing>";
   }
}
