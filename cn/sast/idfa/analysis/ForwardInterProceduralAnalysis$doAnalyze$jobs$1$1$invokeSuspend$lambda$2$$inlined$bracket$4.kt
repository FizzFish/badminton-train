package cn.sast.idfa.analysis

import java.time.LocalDateTime
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import org.utbot.common.LoggingKt

// $VF: Class flags could not be determined
@SourceDebugExtension(["SMAP\nLogging.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Logging.kt\norg/utbot/common/LoggingKt$bracket$3\n+ 2 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1\n*L\n1#1,64:1\n518#2,6:65\n*E\n"])
internal class `ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$4` : Function0<Object> {
   fun `ForwardInterProceduralAnalysis$doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$4`(
      `$startTime`: LocalDateTime, `$msg`: java.lang.String, `$t`: java.lang.Throwable, var4: Any
   ) {
      this.$startTime = `$startTime`;
      this.$msg = `$msg`;
      this.$t = `$t`;
      this.$method$inlined = var4;
   }

   fun invoke(): Any {
      val var1: LocalDateTime = this.$startTime;
      var var10000: java.lang.String = LoggingKt.elapsedSecFrom(var1);
      val var10001: java.lang.String = this.$msg;
      val it: Any = Result.constructor-impl(ResultKt.createFailure(this.$t));
      if (Result.isFailure-impl(it)) {
         ForwardInterProceduralAnalysis.access$getLogger$cp().error(Result.exceptionOrNull-impl(it), new Function0<Object>(this.$method$inlined) {
            {
               this.$method = (M)`$method`;
            }

            public final Object invoke() {
               return "An error occurred: analyze ${this.$method}.";
            }
         });
         val var7: java.lang.Throwable = Result.exceptionOrNull-impl(it);
         var10000 = if (var7 != null) var7.getMessage() else null;
      } else {
         var10000 = Result.box-impl(it);
      }

      return "Finished (in $var10000): $var10001 :: EXCEPTION :: $var10000";
   }
}
