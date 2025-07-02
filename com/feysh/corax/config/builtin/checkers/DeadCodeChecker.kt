package com.feysh.corax.config.builtin.checkers

import com.feysh.corax.config.api.AIAnalysisApiKt
import com.feysh.corax.config.api.BugMessage
import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IChecker
import com.feysh.corax.config.api.IRule
import com.feysh.corax.config.api.Language
import com.feysh.corax.config.builtin.standard.BuiltinCWERules
import com.feysh.corax.config.builtin.standard.BuiltinFeyshRules

public object DeadCodeChecker : IChecker {
   public open val report: IRule = BuiltinFeyshRules.DeadCode as IRule
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

   public object DeadCode : CheckType {
      public open val bugMessage: Map<Language, BugMessage> =
         MapsKt.mapOf(
            new Pair[]{
               TuplesKt.to(Language.ZH, AIAnalysisApiKt.msgGenerator(DeadCodeChecker.DeadCode::bugMessage$lambda$0)),
               TuplesKt.to(Language.EN, AIAnalysisApiKt.msgGenerator(DeadCodeChecker.DeadCode::bugMessage$lambda$1))
            }
         )
         public open val checker: IChecker = DeadCodeChecker.INSTANCE as IChecker

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$0`(): java.lang.String {
         return "该代码块下的代码永远无法访问";
      }

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$1`(): java.lang.String {
         return "The code under that block can never be reached";
      }
   }

   public object UnreachableBranch : CheckType {
      public open val bugMessage: Map<Language, BugMessage> =
         MapsKt.mapOf(
            new Pair[]{
               TuplesKt.to(Language.ZH, AIAnalysisApiKt.msgGenerator(DeadCodeChecker.UnreachableBranch::bugMessage$lambda$0)),
               TuplesKt.to(Language.EN, AIAnalysisApiKt.msgGenerator(DeadCodeChecker.UnreachableBranch::bugMessage$lambda$1))
            }
         )
         public open val checker: IChecker = DeadCodeChecker.INSTANCE as IChecker

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$0`(): java.lang.String {
         return "该条件${`$this$msgGenerator`.getArgs().get("guard")}永远不成立, 目标分支${`$this$msgGenerator`.getArgs().get("target")}永远无法到达";
      }

      @JvmStatic
      fun BugMessage.Env.`bugMessage$lambda$1`(): java.lang.String {
         return "The condition ${`$this$msgGenerator`.getArgs().get("guard")} is always false, and the target branch ${`$this$msgGenerator`.getArgs()
            .get("target")} can never be reached.";
      }
   }
}
