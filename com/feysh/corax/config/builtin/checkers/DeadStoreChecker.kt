package com.feysh.corax.config.builtin.checkers

import com.feysh.corax.config.api.AIAnalysisApiKt
import com.feysh.corax.config.api.BugMessage
import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IChecker
import com.feysh.corax.config.api.IRule
import com.feysh.corax.config.api.Language
import com.feysh.corax.config.builtin.standard.BuiltinCWERules
import com.feysh.corax.config.builtin.standard.BuiltinFeyshRules

public object DeadStoreChecker : IChecker {
   public open val report: IRule = BuiltinFeyshRules.DeadStore as IRule
   public open val standards: Set<IRule> = SetsKt.setOf(BuiltinCWERules.CWE563_UnusedVariable)

   override fun getSimpleName(): java.lang.String {
      return IChecker.DefaultImpls.getSimpleName(this);
   }

   override fun getDesc(): java.lang.String {
      return IChecker.DefaultImpls.getDesc(this);
   }

   override fun validate() {
      IChecker.DefaultImpls.validate(this);
   }

   public object DeadLocalStore : CheckType {
      public open val checker: IChecker = DeadStoreChecker.INSTANCE as IChecker
      public open val bugMessage: Map<Language, BugMessage> =
         MapsKt.mapOf(
            new Pair[]{
               TuplesKt.to(Language.ZH, AIAnalysisApiKt.msgGenerator(DeadStoreChecker.DeadLocalStore::bugMessage$lambda$0)),
               TuplesKt.to(Language.EN, AIAnalysisApiKt.msgGenerator(DeadStoreChecker.DeadLocalStore::bugMessage$lambda$1))
            }
         )

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$0`(): java.lang.String {
         return "赋值给变量，但后续并未使用该值";
      }

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$1`(): java.lang.String {
         return "Assign a value to a variable, but the value is not used subsequently";
      }
   }
}
