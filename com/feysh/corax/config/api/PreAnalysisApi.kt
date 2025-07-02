package com.feysh.corax.config.api

import com.feysh.corax.config.api.BugMessage.Env
import com.feysh.corax.config.api.baseimpl.MatchUtilsKt
import com.feysh.corax.config.api.report.Region
import com.feysh.corax.config.api.utils.UtilsKt
import com.github.javaparser.Position
import com.github.javaparser.ast.nodeTypes.NodeWithRange
import java.io.File
import java.nio.file.Path
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KCallable
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import soot.jimple.toolkits.callgraph.CallGraph
import soot.tagkit.Host

public interface PreAnalysisApi : AnalysisApi, IAnalysisDepends {
   public val cg: CallGraph
   public val outputPath: Path
   public val fullCanonicalPathString: String

   public abstract fun <T> com.feysh.corax.config.api.PreAnalysisApi.Result<T?>.nonNull(): com.feysh.corax.config.api.PreAnalysisApi.Result<T> {
   }

   context(CheckerUnit)
   public abstract fun <T> runInSceneAsync(block: (Continuation<T>) -> Any?): Deferred<T?> {
   }

   context(CheckerUnit)
   public open fun <T> runInScene(block: (Continuation<T>) -> Any?): Job {
   }

   context(CheckerUnit)
   public abstract fun <T> atClass(clazz: IClassMatch, config: (IPreAnalysisClassConfig) -> Unit = ..., block: (IClassCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public abstract fun <T> atAnyClass(config: (IPreAnalysisClassConfig) -> Unit = ..., block: (IClassCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public abstract fun <T> atMethod(method: IMethodMatch, config: (IPreAnalysisMethodConfig) -> Unit = ..., block: (IMethodCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public open fun <T> atMethod(method: KCallable<*>, config: (IPreAnalysisMethodConfig) -> Unit = ..., block: (IMethodCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public abstract fun <T> atAnyMethod(config: (IPreAnalysisMethodConfig) -> Unit = ..., block: (IMethodCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public abstract fun <T> atField(field: IFieldMatch, config: (IPreAnalysisFieldConfig) -> Unit = ..., block: (IFieldCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public abstract fun <T> atAnyField(config: (IPreAnalysisFieldConfig) -> Unit = ..., block: (IFieldCheckPoint) -> T): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public abstract fun <T> atInvoke(callee: IMethodMatch, config: (IPreAnalysisInvokeConfig) -> Unit = ..., block: (IInvokeCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public open fun <T> atInvoke(callee: KCallable<*>, config: (IPreAnalysisInvokeConfig) -> Unit = ..., block: (IInvokeCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public abstract fun <T> atAnyInvoke(config: (IPreAnalysisInvokeConfig) -> Unit = ..., block: (IInvokeCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   context(CheckerUnit)
   public abstract fun <T> atAnySourceFile(
      extension: String? = ...,
      filename: String? = ...,
      config: (IPreAnalysisFileConfig) -> Unit = ...,
      block: (ISourceFileCheckPoint, Continuation<T>) -> Any?
   ): com.feysh.corax.config.api.PreAnalysisApi.Result<T> {
   }

   context(CheckerUnit)
   public abstract fun <T> atSourceFile(path: Path, config: (IPreAnalysisFileConfig) -> Unit = ..., block: (ISourceFileCheckPoint, Continuation<T>) -> Any?): com.feysh.corax.config.api.PreAnalysisApi.Result<
         T
      > {
   }

   public abstract fun <P> P.report(checkType: CheckType, env: (Env) -> Unit = ...) where P : ICheckPoint, P : INodeWithRange {
   }

   public open fun <P> P.report(checkType: CheckType, region: Region, env: (Env) -> Unit = ...) where P : ICheckPoint, P : INodeWithRange {
   }

   public open fun ISourceFileCheckPoint.report(checkType: CheckType, region: Region, env: (Env) -> Unit = ...) {
   }

   public open fun ISourceFileCheckPoint.report(checkType: CheckType, cpgRegion: de.fraunhofer.aisec.cpg.sarif.Region, env: (Env) -> Unit = ...): Unit? {
   }

   public open fun ISourceFileCheckPoint.report(checkType: CheckType, jpsStart: Position, jpsEnd: Position?, env: (Env) -> Unit = ...): Unit? {
   }

   public open fun ISourceFileCheckPoint.report(checkType: CheckType, regionNode: NodeWithRange<*>, env: (Env) -> Unit = ...): Unit? {
   }

   public abstract fun report(checkType: CheckType, sootHost: Host, env: (Env) -> Unit = ...) {
   }

   public open fun report(checkType: CheckType, sootHost: Host, region: Region?, env: (Env) -> Unit = ...) {
   }

   public abstract fun report(checkType: CheckType, file: Path, region: Region, env: (Env) -> Unit = ...) {
   }

   public abstract fun archivePath(archiveFile: Path, entry: String): Path {
   }

   public abstract fun getZipEntry(innerFilePath: Path): Pair<Path, String>? {
   }

   public abstract fun Path.getShadowFile(copyDest: Path? = ...): File {
   }

   public abstract fun globPath(glob: String): List<Path>? {
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nPreAnalysisApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisApi.kt\ncom/feysh/corax/config/api/PreAnalysisApi$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,212:1\n1#2:213\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <T> runInScene(`$this`: PreAnalysisApi, `$context_receiver_0`: CheckerUnit, block: (Continuation<? super T>?) -> Any): Job {
         return `$this`.runInSceneAsync(`$context_receiver_0`, block) as Job;
      }

      @JvmStatic
      fun <T> atMethod(
         `$this`: PreAnalysisApi,
         `$context_receiver_0`: CheckerUnit,
         method: KCallable<?>,
         config: (IPreAnalysisMethodConfig?) -> Unit,
         block: (IMethodCheckPoint?, Continuation<? super T>?) -> Any
      ): PreAnalysisApiResult<T> {
         return `$this`.atMethod(`$context_receiver_0`, MatchUtilsKt.matchSoot(UtilsKt.getSootSignature(method)), config, block);
      }

      @JvmStatic
      fun <T> atInvoke(
         `$this`: PreAnalysisApi,
         `$context_receiver_0`: CheckerUnit,
         callee: KCallable<?>,
         config: (IPreAnalysisInvokeConfig?) -> Unit,
         block: (IInvokeCheckPoint?, Continuation<? super T>?) -> Any
      ): PreAnalysisApiResult<T> {
         return `$this`.atInvoke(`$context_receiver_0`, MatchUtilsKt.matchSoot(UtilsKt.getSootSignature(callee)), config, block);
      }

      @JvmStatic
      fun <P extends ICheckPoint & INodeWithRange> report(
         `$this`: PreAnalysisApi, `$receiver`: P, checkType: CheckType, region: Region, env: (BugMessage.Env?) -> Unit
      ) {
         `$this`.report((P)`$receiver`, checkType, PreAnalysisApi.DefaultImpls::report$lambda$14);
      }

      @JvmStatic
      fun report(`$this`: PreAnalysisApi, `$receiver`: ISourceFileCheckPoint, checkType: CheckType, region: Region, env: (BugMessage.Env?) -> Unit) {
         `$this`.report(checkType, `$receiver`.getPath(), region, env);
      }

      @JvmStatic
      fun report(
         `$this`: PreAnalysisApi,
         `$receiver`: ISourceFileCheckPoint,
         checkType: CheckType,
         cpgRegion: de.fraunhofer.aisec.cpg.sarif.Region,
         env: (BugMessage.Env?) -> Unit
      ): Unit? {
         val var10000: Region = Region.Companion.invoke(cpgRegion);
         val var7: Unit;
         if (var10000 != null) {
            `$this`.report(`$receiver`, checkType, var10000, env);
            var7 = Unit.INSTANCE;
         } else {
            var7 = null;
         }

         return var7;
      }

      @JvmStatic
      fun report(
         `$this`: PreAnalysisApi,
         `$receiver`: ISourceFileCheckPoint,
         checkType: CheckType,
         jpsStart: Position,
         jpsEnd: Position?,
         env: (BugMessage.Env?) -> Unit
      ): Unit? {
         val var10000: Region = Region.Companion.invoke(jpsStart, jpsEnd);
         val var8: Unit;
         if (var10000 != null) {
            `$this`.report(`$receiver`, checkType, var10000, env);
            var8 = Unit.INSTANCE;
         } else {
            var8 = null;
         }

         return var8;
      }

      @JvmStatic
      fun report(
         `$this`: PreAnalysisApi, `$receiver`: ISourceFileCheckPoint, checkType: CheckType, regionNode: NodeWithRange<?>, env: (BugMessage.Env?) -> Unit
      ): Unit? {
         val var10000: Region = Region.Companion.invoke(regionNode);
         val var7: Unit;
         if (var10000 != null) {
            `$this`.report(`$receiver`, checkType, var10000, env);
            var7 = Unit.INSTANCE;
         } else {
            var7 = null;
         }

         return var7;
      }

      @JvmStatic
      fun report(`$this`: PreAnalysisApi, checkType: CheckType, sootHost: Host, region: Region?, env: (BugMessage.Env?) -> Unit) {
         `$this`.report(checkType, sootHost, PreAnalysisApi.DefaultImpls::report$lambda$24);
      }

      @JvmStatic
      fun `atClass$lambda$0`(var0: IPreAnalysisClassConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atAnyClass$lambda$1`(var0: IPreAnalysisClassConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atMethod$lambda$2`(var0: IPreAnalysisMethodConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atMethod$lambda$3`(var0: IPreAnalysisMethodConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atAnyMethod$lambda$4`(var0: IPreAnalysisMethodConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atField$lambda$5`(var0: IPreAnalysisFieldConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atAnyField$lambda$6`(var0: IPreAnalysisFieldConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atInvoke$lambda$7`(var0: IPreAnalysisInvokeConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atInvoke$lambda$8`(var0: IPreAnalysisInvokeConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atAnyInvoke$lambda$9`(var0: IPreAnalysisInvokeConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atAnySourceFile$lambda$10`(var0: IPreAnalysisFileConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atSourceFile$lambda$11`(var0: IPreAnalysisFileConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$12`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$13`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$14`(`$region`: Region, `$env`: Function1, `$this$report`: BugMessage.Env): Unit {
         `$this$report`.setRegion(`$region`.getMutable());
         `$env`.invoke(`$this$report`);
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$15`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$16`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$18`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$20`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$22`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$23`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$24`(`$region`: Region, `$env`: Function1, `$this$report`: BugMessage.Env): Unit {
         if (`$region` != null) {
            `$this$report`.setRegion(`$region`.getMutable());
         }

         `$env`.invoke(`$this$report`);
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `report$lambda$25`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }
   }

   public interface Result<T> {
      public val asyncResult: Deferred<List<Any>>

      public open suspend fun await(): List<Any> {
      }

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <T> await(`$this`: PreAnalysisApiResult<T>, `$completion`: Continuation<? super java.utilList<? extends T>>): Any? {
            return `$this`.getAsyncResult().await(`$completion`);
         }
      }
   }
}
