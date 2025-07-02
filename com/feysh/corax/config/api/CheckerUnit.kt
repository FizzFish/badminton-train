package com.feysh.corax.config.api

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.ContinuationImpl
import mu.KLogger
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

public abstract class CheckerUnit {
   public open val enableDefault: Boolean = true

   @JvmStatic
   fun `logger$lambda$0`(): Unit {
      return Unit.INSTANCE;
   }

   public companion object {
      private final val logger: KLogger

      public suspend fun <API> processUnit(api: API, checker: CheckerUnit, config: (API, Continuation<Unit>) -> Any?) {
         var `$continuation`: Continuation;
         label45: {
            if (`$completion` is <unrepresentable>) {
               `$continuation` = `$completion` as <unrepresentable>;
               if (((`$completion` as <unrepresentable>).label and Integer.MIN_VALUE) != 0) {
                  `$continuation`.label -= Integer.MIN_VALUE;
                  break label45;
               }
            }

            `$continuation` = new ContinuationImpl(this, `$completion`) {
               Object L$0;
               int label;

               {
                  super(`$completion`);
                  this.this$0 = `this$0`;
               }

               @Nullable
               public final Object invokeSuspend(@NotNull Object $result) {
                  this.result = `$result`;
                  this.label |= Integer.MIN_VALUE;
                  return this.this$0.processUnit(null, null, null, this as Continuation<? super Unit>);
               }
            };
         }

         val `$result`: Any = `$continuation`.result;
         val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);

               var var10000: Any;
               try {
                  `$continuation`.L$0 = checker;
                  `$continuation`.label = 1;
                  var10000 = config.invoke(api, `$continuation`);
               } catch (var11: AnalysisApiNotImplException) {
                  return Unit.INSTANCE;
               } catch (var12: Exception) {
                  CheckerUnit.access$getLogger$cp().error(var12, CheckerUnit.Companion::processUnit$lambda$0);
                  return Unit.INSTANCE;
               }

               if (var10000 === var8) {
                  return var8;
               }
               break;
            case 1:
               checker = `$continuation`.L$0 as CheckerUnit;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  break;
               } catch (var13: AnalysisApiNotImplException) {
               } catch (var14: Exception) {
                  CheckerUnit.access$getLogger$cp().error(var14, CheckerUnit.Companion::processUnit$lambda$0);
               }

               return Unit.INSTANCE;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            ;
         } catch (var9: AnalysisApiNotImplException) {
         } catch (var10: Exception) {
            CheckerUnit.access$getLogger$cp().error(var10, CheckerUnit.Companion::processUnit$lambda$0);
         }

         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `processUnit$lambda$0`(`$checker`: CheckerUnit): Any {
         return "A bug occurs when process $`$checker`[${`$checker`.getClass()}]::config. Please file this bug to us.";
      }
   }
}
