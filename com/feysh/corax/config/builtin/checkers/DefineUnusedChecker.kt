package com.feysh.corax.config.builtin.checkers

import com.feysh.corax.config.api.AIAnalysisApiKt
import com.feysh.corax.config.api.BugMessage
import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IChecker
import com.feysh.corax.config.api.IRule
import com.feysh.corax.config.api.Language
import com.feysh.corax.config.builtin.standard.BuiltinCWERules
import com.feysh.corax.config.builtin.standard.BuiltinFeyshRules

public object DefineUnusedChecker : IChecker {
   public open val report: IRule = BuiltinFeyshRules.DefineUnused as IRule
   public open val standards: Set<IRule> = SetsKt.setOf(BuiltinCWERules.CWE561_DeadCode)

   override fun getSimpleName(): java.lang.String {
      return IChecker.DefaultImpls.getSimpleName(this);
   }

   override fun getDesc(): java.lang.String {
      return IChecker.DefaultImpls.getDesc(this);
   }

   override fun validate() {
      IChecker.DefaultImpls.validate(this);
   }

   public object UnusedMethod : CheckType {
      public open val bugMessage: Map<Language, BugMessage> =
         MapsKt.mapOf(
            new Pair[]{
               TuplesKt.to(Language.ZH, AIAnalysisApiKt.msgGenerator(DefineUnusedChecker.UnusedMethod::bugMessage$lambda$0)),
               TuplesKt.to(Language.EN, AIAnalysisApiKt.msgGenerator(DefineUnusedChecker.UnusedMethod::bugMessage$lambda$1))
            }
         )
         public open val checker: IChecker = DefineUnusedChecker.INSTANCE as IChecker

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$0`(): java.lang.String {
         return "方法 `${`$this$msgGenerator`.getMethod()}` 从未被使用过";
      }

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$1`(): java.lang.String {
         return "This method `${`$this$msgGenerator`.getMethod()}` is never used";
      }
   }

   public object UrfUnreadField : CheckType {
      public open val bugMessage: Map<Language, BugMessage> =
         MapsKt.mapOf(
            new Pair[]{
               TuplesKt.to(Language.ZH, AIAnalysisApiKt.msgGenerator(DefineUnusedChecker.UrfUnreadField::bugMessage$lambda$0)),
               TuplesKt.to(Language.EN, AIAnalysisApiKt.msgGenerator(DefineUnusedChecker.UrfUnreadField::bugMessage$lambda$1))
            }
         )
         public open val checker: IChecker = DefineUnusedChecker.INSTANCE as IChecker

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$0`(): java.lang.String {
         return "字段 `${`$this$msgGenerator`.getField()}` 从未被读取";
      }

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$1`(): java.lang.String {
         return "Unread field: `${`$this$msgGenerator`.getField()}`";
      }
   }
}
