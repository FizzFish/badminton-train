package com.feysh.corax.config.api

import com.feysh.corax.config.api.BugMessage.Env
import com.feysh.corax.config.api.ISootMethodDecl.CheckBuilder
import com.feysh.corax.config.api.baseimpl.ConfigException
import kotlin.jvm.functions.Function1

public final val feyshRules: Set<IRule>
   public final get() {
      return `$this$feyshRules`.getRulesByName("feysh");
   }


public final val cweRules: Set<IRule>
   public final get() {
      return `$this$cweRules`.getRulesByName("cwe");
   }


public final val certRules: Set<IRule>
   public final get() {
      return `$this$certRules`.getRulesByName("cert");
   }


private inline fun <T> T.tryApply(block: (T) -> Unit): T {
   try {
      block.invoke(`$this$tryApply`);
   } catch (var4: ConfigException) {
      System.err.println(var4);
   }

   return (T)`$this$tryApply`;
}

public fun quotedIfNotNull(s: Any?): String {
   return if (s == null) "" else "`$s`";
}

public fun msgGenerator(msg: (Env) -> String): BugMessage {
   return new BugMessage(msg);
}

context(AIAnalysisApi)
public fun <R> IMethodDecl<R>.modelNoArgSoot(config: (MethodConfig) -> Unit = ..., block: (CheckBuilder<R>) -> Unit): IMethodDecl<R> {
   for (ISootMethodDecl sootMethodDecl : $context_receiver_0.getSootDecl($this$modelNoArgSoot)) {
      sootMethodDecl.modelNoArg(config, block);
   }

   return `$this$modelNoArgSoot`;
}

@JvmSynthetic
fun `modelNoArgSoot$default`(var0: AIAnalysisApi, var1: IMethodDecl, var2: Function1, var3: Function1, var4: Int, var5: Any): IMethodDecl {
   if ((var4 and 2) != 0) {
      var2 = AIAnalysisApiKt::modelNoArgSoot$lambda$0;
   }

   return modelNoArgSoot(var0, var1, var2, var3);
}

fun `modelNoArgSoot$lambda$0`(var0: MethodConfig): Unit {
   return Unit.INSTANCE;
}
