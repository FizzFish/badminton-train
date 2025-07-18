package cn.sast.framework.result

import cn.sast.api.report.IResultCollector
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt

public interface IUTBotResultCollector : IResultCollector {
   public abstract fun addUtState() {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun flush(`$this`: IUTBotResultCollector, `$completion`: Continuation<? super Unit>): Any? {
         val var10000: Any = IResultCollector.DefaultImpls.flush(`$this`, `$completion`);
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }
}
