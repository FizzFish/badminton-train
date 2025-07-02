package com.feysh.corax.config.api.validate

import com.feysh.corax.cache.coroutines.FastCache
import com.feysh.corax.config.api.AIAnalysisApi
import com.feysh.corax.config.api.AnalysisApiNotImplException
import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IBoolExpr
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IJDecl
import com.feysh.corax.config.api.IStmt
import com.feysh.corax.config.api.MethodConfig
import com.feysh.corax.config.api.PreAnalysisApi
import com.feysh.corax.config.api.AIAnalysisApi.Error
import com.feysh.corax.config.api.BugMessage.Env
import com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl
import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import mu.KLogger

@SourceDebugExtension(["SMAP\nAIAnalysisValidator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisValidator.kt\ncom/feysh/corax/config/api/validate/AIAnalysisValidator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,110:1\n1863#2,2:111\n1863#2,2:113\n381#3,7:115\n381#3,7:122\n381#3,7:129\n*S KotlinDebug\n*F\n+ 1 AIAnalysisValidator.kt\ncom/feysh/corax/config/api/validate/AIAnalysisValidator\n*L\n39#1:111,2\n47#1:113,2\n75#1:115,7\n92#1:122,7\n101#1:129,7\n*E\n"])
public open class AIAnalysisValidator(scope: CoroutineScope = GlobalScope.INSTANCE as CoroutineScope) : AIAnalysisBaseImpl {
   public open val scope: CoroutineScope
   public final val errors: MutableList<String>
   public final val warnings: MutableList<String>
   public final val stmts: MutableMap<IJDecl, MutableList<IStmt>>
   public final val checkPoint: MutableMap<IJDecl, MutableList<CheckInfo>>
   public final val eval: MutableMap<IJDecl, MutableList<IExpr>>

   public open val preAnalysis: PreAnalysisApi
      public open get() {
         throw new AnalysisApiNotImplException("just a test");
      }


   public open val fastCache: FastCache
      public open get() {
         throw new AnalysisApiNotImplException("just a test");
      }


   public open val error: Error

   init {
      this.scope = scope;
      this.errors = new ArrayList<>();
      this.warnings = new ArrayList<>();
      this.stmts = new LinkedHashMap<>();
      this.checkPoint = new LinkedHashMap<>();
      this.eval = new LinkedHashMap<>();
      this.error = new AIAnalysisApi.Error(this) {
         {
            this.this$0 = `$receiver`;
         }

         @Override
         public void error(java.lang.String msg) {
            this.getLogger().error(<unrepresentable>::error$lambda$0);
            this.this$0.getErrors().add(msg);
         }

         @Override
         public void warning(java.lang.String msg) {
            this.getLogger().warn(<unrepresentable>::warning$lambda$1);
            this.this$0.getWarnings().add(msg);
         }

         @Override
         public KLogger getLogger() {
            return AIAnalysisValidator.Companion.getLogger();
         }

         private static final Object error$lambda$0(java.lang.String $msg) {
            return `$msg`;
         }

         private static final Object warning$lambda$1(java.lang.String $msg) {
            return `$msg`;
         }
      };
   }

   public override fun validate() {
      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         this.getLogger().warn(AIAnalysisValidator::validate$lambda$1$lambda$0);
      }

      if (!this.check()) {
         this.getLogger().error(AIAnalysisValidator::validate$lambda$2);
         this.getLogger().error(AIAnalysisValidator::validate$lambda$3);
         this.getLogger().error(AIAnalysisValidator::validate$lambda$4);

         for (Object element$iv : $this$forEach$iv) {
            this.getLogger().error(AIAnalysisValidator::validate$lambda$6$lambda$5);
         }

         this.getLogger().error(AIAnalysisValidator::validate$lambda$7);
      }
   }

   public fun check(): Boolean {
      return this.errors.isEmpty();
   }

   public override fun addStmt(decl: IJDecl, config: (MethodConfig) -> Unit, stmt: IStmt) {
      val `$this$getOrPut$iv`: java.util.Map = this.stmts;
      val `value$iv`: Any = this.stmts.get(decl);
      val var10000: Any;
      if (`value$iv` == null) {
         this.getLogger().debug(AIAnalysisValidator::addStmt$lambda$10$lambda$8);
         this.getLogger().debug(AIAnalysisValidator::addStmt$lambda$10$lambda$9);
         val `answer$iv`: Any = new ArrayList();
         `$this$getOrPut$iv`.put(decl, `answer$iv`);
         var10000 = `answer$iv`;
      } else {
         var10000 = `value$iv`;
      }

      (var10000 as java.util.List).add(stmt);
      this.getLogger().debug(AIAnalysisValidator::addStmt$lambda$11);
   }

   public override fun check(decl: IJDecl, config: (MethodConfig) -> Unit, expr: IBoolExpr, checkType: CheckType, env: (Env) -> Unit) {
      val check: CheckInfo = new CheckInfo(expr, checkType);
      val `$this$getOrPut$iv`: java.util.Map = this.checkPoint;
      val `value$iv`: Any = this.checkPoint.get(decl);
      val var10000: Any;
      if (`value$iv` == null) {
         this.getLogger().debug(AIAnalysisValidator::check$lambda$14$lambda$12);
         this.getLogger().debug(AIAnalysisValidator::check$lambda$14$lambda$13);
         val `answer$iv`: Any = new ArrayList();
         `$this$getOrPut$iv`.put(decl, `answer$iv`);
         var10000 = `answer$iv`;
      } else {
         var10000 = `value$iv`;
      }

      (var10000 as java.util.List).add(check);
      this.getLogger().debug(AIAnalysisValidator::check$lambda$15);
   }

   public override fun eval(decl: IJDecl, config: (MethodConfig) -> Unit, expr: IExpr, accept: (Any) -> Unit) {
      val `$this$getOrPut$iv`: java.util.Map = this.eval;
      val `value$iv`: Any = this.eval.get(decl);
      val var10000: Any;
      if (`value$iv` == null) {
         this.getLogger().debug(AIAnalysisValidator::eval$lambda$18$lambda$16);
         this.getLogger().debug(AIAnalysisValidator::eval$lambda$18$lambda$17);
         val `answer$iv`: Any = new ArrayList();
         `$this$getOrPut$iv`.put(decl, `answer$iv`);
         var10000 = `answer$iv`;
      } else {
         var10000 = `value$iv`;
      }

      (var10000 as java.util.List).add(expr);
      this.getLogger().debug(AIAnalysisValidator::eval$lambda$19);
   }

   @JvmStatic
   fun `validate$lambda$1$lambda$0`(`$it`: java.lang.String): Any {
      return `$it`;
   }

   @JvmStatic
   fun `validate$lambda$2`(): Any {
      return "";
   }

   @JvmStatic
   fun `validate$lambda$3`(): Any {
      return "";
   }

   @JvmStatic
   fun `validate$lambda$4`(): Any {
      return "";
   }

   @JvmStatic
   fun `validate$lambda$6$lambda$5`(`$it`: java.lang.String): Any {
      return `$it`;
   }

   @JvmStatic
   fun `validate$lambda$7`(`this$0`: AIAnalysisValidator): Any {
      return "Please fix errors. num: ${`this$0`.errors.size()}";
   }

   @JvmStatic
   fun `addStmt$lambda$10$lambda$8`(): Any {
      return "";
   }

   @JvmStatic
   fun `addStmt$lambda$10$lambda$9`(`$decl`: IJDecl): Any {
      return "$`$decl` :";
   }

   @JvmStatic
   fun `addStmt$lambda$11`(`$stmt`: IStmt): Any {
      return "\t\tadd stmt: $`$stmt`";
   }

   @JvmStatic
   fun `check$lambda$14$lambda$12`(): Any {
      return "";
   }

   @JvmStatic
   fun `check$lambda$14$lambda$13`(`$decl`: IJDecl): Any {
      return "$`$decl` :";
   }

   @JvmStatic
   fun `check$lambda$15`(`$check`: CheckInfo): Any {
      return "\t\tadd check point: $`$check`";
   }

   @JvmStatic
   fun `eval$lambda$18$lambda$16`(): Any {
      return "";
   }

   @JvmStatic
   fun `eval$lambda$18$lambda$17`(`$decl`: IJDecl): Any {
      return "$`$decl` :";
   }

   @JvmStatic
   fun `eval$lambda$19`(`$expr`: IExpr): Any {
      return "\t\tadd eval expr: $`$expr`";
   }

   @JvmStatic
   fun `logger$lambda$20`(): Unit {
      return Unit.INSTANCE;
   }

   open fun AIAnalysisValidator() {
      this(null, 1, null);
   }

   public companion object {
      public final val logger: KLogger
   }
}
