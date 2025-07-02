package cn.sast.idfa.analysis

import cn.sast.api.config.ExtSettings
import cn.sast.api.config.StaticFieldTrackingMode
import cn.sast.common.OS
import cn.sast.coroutines.caffine.impl.FastCacheImpl
import cn.sast.coroutines.caffine.impl.RecCoroutineCacheImplKt
import cn.sast.graph.HashMutableDirectedGraph
import cn.sast.idfa.progressbar.ProgressBarExt
import cn.sast.idfa.progressbar.ProgressBarExtKt
import cn.sast.idfa.progressbar.ProgressBarExt.DefaultProcessInfoRenderer
import com.feysh.corax.cache.coroutines.RecCoroutineLoadingCache
import com.google.common.base.Stopwatch
import com.google.common.collect.Sets
import java.util.ArrayList
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.atomic.AtomicLong
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.Ref.ObjectRef
import kotlinx.coroutines.AwaitKt
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineScopeKt
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExecutorsKt
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorKt
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.SemaphoreKt
import me.tongfei.progressbar.ProgressBar
import me.tongfei.progressbar.ProgressBarRenderer
import mu.KLogger
import soot.SootMethod
import soot.jimple.infoflow.collect.ConcurrentHashSet
import soot.jimple.infoflow.solver.executors.InterruptableExecutor
import soot.toolkits.graph.DirectedGraph
import java.time.LocalDateTime
import org.utbot.common.LoggingKt
import org.utbot.common.Maybe

@SourceDebugExtension(["SMAP\nForwardInterProceduralAnalysis.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 CoroutineScope.kt\nkotlinx/coroutines/CoroutineScopeKt\n+ 4 FixPointStatus.kt\ncn/sast/idfa/analysis/FixPointStatus$Companion\n*L\n1#1,678:1\n1279#2,2:679\n1293#2,2:681\n1296#2:684\n1557#2:685\n1628#2,3:686\n326#3:683\n10#4:689\n*S KotlinDebug\n*F\n+ 1 ForwardInterProceduralAnalysis.kt\ncn/sast/idfa/analysis/ForwardInterProceduralAnalysis\n*L\n341#1:679,2\n341#1:681,2\n341#1:684\n512#1:685\n512#1:686,3\n342#1:683\n663#1:689\n*E\n"])
public abstract class ForwardInterProceduralAnalysis<M, N, A, R, CTX extends Context<M, N, A>> : InterProceduralAnalysis<M, N, A> {
   public final val name: String
   private final var stopWatch: Stopwatch?

   protected final var timeOutDuration: Long
      internal set

   protected final var timeOutOn: Boolean
      internal set

   private final var usedTime: Double

   public final var isTimeout: Boolean
      private set

   private final var limitedAnalytics: Boolean
   public final val progressBarExt: ProgressBarExt

   public open var numberThreads: Int
      internal final set

   public open var staticFieldTrackingMode: StaticFieldTrackingMode
      internal final set

   public final val reachableMethods: ConcurrentHashSet<Any>

   public final var directedGraph: HashMutableDirectedGraph<Any>?
      internal set

   public open val progressBarVolume: Int
   private final val contextStateId: AtomicLong

   public final lateinit var cache: RecCoroutineLoadingCache<cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.FactKey<Any, Any, Any>, Any>
      internal set

   public final var dataFlowInterProceduralCalleeTimeOut: Int
      internal set

   public final var dataFlowInterProceduralCalleeDepChainMaxNum: Long
      internal set

   private final val bottom: Any
      private final get() {
         return this.bottomValue();
      }


   private final var transformStmtTotalCount: AtomicInteger
   private final val curAnalysingMethods: MutableSet<Any>

   open fun ForwardInterProceduralAnalysis(name: java.lang.String) {
      super(false);
      this.name = name;
      this.progressBarExt = new ProgressBarExt(0, 0, 3, null);
      this.numberThreads = OS.INSTANCE.getMaxThreadNum();
      this.staticFieldTrackingMode = StaticFieldTrackingMode.ContextFlowSensitive;
      this.reachableMethods = new ConcurrentHashSet();
      this.progressBarVolume = 100;
      this.contextStateId = new AtomicLong(0L);
      this.dataFlowInterProceduralCalleeTimeOut = -1;
      this.dataFlowInterProceduralCalleeDepChainMaxNum = -1L;
      this.transformStmtTotalCount = new AtomicInteger();
      val var10001: java.util.Set = Sets.newConcurrentHashSet();
      this.curAnalysingMethods = var10001;
   }

   public open fun ProgressBar.wrapperCustom(): DefaultProcessInfoRenderer {
      return ProgressBarExtKt.wrapper(`$this$wrapperCustom`, ForwardInterProceduralAnalysis::wrapperCustom$lambda$0);
   }

   public open fun cacheConfig() {
   }

   public abstract fun makeContext(method: Any, cfg: DirectedGraph<Any>, entryValue: Any, reverse: Boolean, isAnalyzable: Boolean): Any {
   }

   public open fun newContext(cfg: DirectedGraph<Any>, method: Any, entryValue: Any, isAnalyzable: Boolean): Any {
      this.programRepresentation().setOwnerStatement(cfg as MutableIterable<N>, (M)method);
      val context: Context = this.makeContext((M)method, cfg, (A)entryValue, false, isAnalyzable);
      context.setBottomValue(this.bottomValue());
      val heads: java.util.List = cfg.getHeads();
      val var10000: java.util.Iterator = cfg.iterator();
      val var7: java.util.Iterator = var10000;

      while (var7.hasNext()) {
         val node: Any = var7.next();
         if (heads.contains(node)) {
            val var10002: Any = context.getEntryValue();
            context.setValueBefore(node, var10002);
         } else {
            context.setValueBefore(node, this.bottomValue());
         }
      }

      context.initworklist();
      context.setExitValue(this.bottomValue());
      return (CTX)context;
   }

   public open suspend fun computeInValue(context: Any, node: Any): Any {
      return computeInValue$suspendImpl(this, (CTX)context, (N)node, `$completion`);
   }

   public open suspend fun computeEntryValue(context: Any): Any {
      return computeEntryValue$suspendImpl(this, (CTX)context, `$completion`);
   }

   public open fun computeExitValue(context: Any): Any {
      val cfg: DirectedGraph = context.getControlFlowGraph();
      var exitValue: Any = this.bottomValue();

      for (Object tailNode : cfg.getTails()) {
         exitValue = this.meet((A)exitValue, (A)context.getEdgeValue(tailNode, tailNode));
      }

      return (A)exitValue;
   }

   public open fun initCallEdgeValue(currentContext: Any, node: Any, callee: Any, callSiteValue: Any, inValue: Any): Any {
      return (A)inValue;
   }

   public open suspend fun evalCall(context: Any, callee: Any, node: Any, succ: Any, inValue: Any): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<
         Any,
         Any,
         Any?
      >? {
      return evalCall$suspendImpl(this, (CTX)context, (M)callee, (N)node, (N)succ, (A)inValue, `$completion`);
   }

   public abstract fun recursiveCallFlowFunction(context: Any, callee: Any, node: Any, succ: Any, inValue: Any, siteValue: Any, isAnalyzable: Boolean): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<
         Any,
         Any,
         Any?
      > {
   }

   public abstract fun failedInvokeResult(context: Any, callee: Any, node: Any, succ: Any, inValue: Any, siteValue: Any, isAnalyzable: Boolean): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<
         Any,
         Any,
         Any?
      > {
   }

   public fun init(scope: CoroutineScope) {
      this.setCache(
         FastCacheImpl.INSTANCE
            .buildRecCoroutineLoadingCache(
               scope,
               ForwardInterProceduralAnalysis::init$lambda$1,
               (
                  new Function3<RecCoroutineLoadingCache<ForwardInterProceduralAnalysis.FactKey<M, N, A>, CTX>, ForwardInterProceduralAnalysis.FactKey<M, N, A>, Continuation<? super CTX>, Object>(
                     this, null
                  ) {
                     int label;

                     {
                        super(3, `$completion`);
                        this.this$0 = `$receiver`;
                     }

                     public final Object invokeSuspend(Object $result) {
                        val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        var var10000: Any;
                        switch (this.label) {
                           case 0:
                              ResultKt.throwOnFailure(`$result`);
                              val key: ForwardInterProceduralAnalysis.FactKey = this.L$0 as ForwardInterProceduralAnalysis.FactKey;
                              val method: Any = (this.L$0 as ForwardInterProceduralAnalysis.FactKey).getMethod();
                              val currentContext: Context = this.this$0
                                 .newContext(this.this$0.getCfg((M)method, key.isAnalyzable()), (M)method, (A)key.getIn(), key.isAnalyzable());
                              var10000 = this.this$0;
                              val var10003: Continuation = this as Continuation;
                              this.label = 1;
                              var10000 = (ForwardInterProceduralAnalysis)var10000.interProceduralAnalyze(key, (CTX)currentContext, var10003);
                              if (var10000 === var7) {
                                 return var7;
                              }
                              break;
                           case 1:
                              ResultKt.throwOnFailure(`$result`);
                              var10000 = (ForwardInterProceduralAnalysis)`$result`;
                              break;
                           default:
                              throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }

                        return var10000;
                     }

                     public final Object invoke(
                        RecCoroutineLoadingCache<ForwardInterProceduralAnalysis.FactKey<M, N, A>, CTX> p1,
                        ForwardInterProceduralAnalysis.FactKey<M, N, A> p2,
                        Continuation<? super CTX> p3
                     ) {
                        val var4: Function3 = new <anonymous constructor>(this.this$0, p3);
                        var4.L$0 = p2;
                        return var4.invokeSuspend(Unit.INSTANCE);
                     }
                  }
               ) as (RecCoroutineLoadingCache<ForwardInterProceduralAnalysisFactKey<M, N, A>, CTX>?, ForwardInterProceduralAnalysisFactKey<M, N, A>?, Continuation<? super CTX>?) -> Any
            )
      );
   }

   public open suspend fun processCallCoroutine(currentContext: Any, callee: Any, node: Any, succ: Any, callSiteValue: Any): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<
         Any,
         Any,
         Any?
      > {
      return processCallCoroutine$suspendImpl(this, (CTX)currentContext, (M)callee, (N)node, (N)succ, (A)callSiteValue, `$completion`);
   }

   public abstract fun skip(callee: Any) {
   }

   public open suspend fun postCallAtCallSite(
      context: Any,
      node: Any,
      succ: Any,
      in1: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<Any, Any, Any?>
   ): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<Any, Any, Any?> {
      return postCallAtCallSite$suspendImpl(this, (CTX)context, (N)node, (N)succ, in1, `$completion`);
   }

   public open suspend fun prevCallFunction(context: Any, callee: Any, node: Any, succ: Any, callSiteValue: Any): Any {
      return prevCallFunction$suspendImpl(this, (CTX)context, (M)callee, (N)node, (N)succ, (A)callSiteValue, `$completion`);
   }

   public open suspend fun processAndReturnResult(context: Any, node: Any, succ: Any, callees: Set<Any>, callSiteValue: Any): Map<
         Any,
         Deferred<cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<Any, Any, Any?>>
      > {
      return processAndReturnResult$suspendImpl(this, (CTX)context, (N)node, (N)succ, callees, (A)callSiteValue, `$completion`);
   }

   public open suspend fun Any.processContent() {
      return processContent$suspendImpl(this, (CTX)`$this$processContent`, `$completion`);
   }

   public open suspend fun interProceduralAnalyze(key: cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.FactKey<Any, Any, Any>, currentContext: Any): Any {
      return interProceduralAnalyze$suspendImpl(this, key, (CTX)currentContext, `$completion`);
   }

   public suspend fun processMethod(method: Any) {
      val dataFlowInterProceduralCalleeTimeOut: Int = this.dataFlowInterProceduralCalleeTimeOut;
      val key: ForwardInterProceduralAnalysis.FactKey = new ForwardInterProceduralAnalysis.FactKey<>(this.boundaryValue((M)method), method, true);
      val var10000: Any = SupervisorKt.supervisorScope(
         (
            new Function2<CoroutineScope, Continuation<? super Object>, Object>(this, key, dataFlowInterProceduralCalleeTimeOut, (M)method, null)// $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.modules.decompiler.stats.Statement.getVarDefinitions()" because "stat" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1468)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingExprent(VarDefinitionHelper.java:1679)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1496)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1545)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.remapClashingNames(VarDefinitionHelper.java:1458)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarProcessor.rerunClashing(VarProcessor.java:99)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.invokeProcessors(ClassWriter.java:118)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeClass(ClassWriter.java:352)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.NewExprent.toJava(NewExprent.java:407)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.wrapOperandString(FunctionExprent.java:761)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.wrapOperandString(FunctionExprent.java:727)
      
         ) as Function2,
         `$completion`
      );
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public open fun doAnalyze(scope: CoroutineScope, entries: Collection<Any>): Pair<Job, DefaultProcessInfoRenderer?> {
      logger.info(ForwardInterProceduralAnalysis::doAnalyze$lambda$6);
      this.init(scope);
      val size: Int = entries.size();
      val counter: AtomicInteger = new AtomicInteger();
      val progressBarRenderer: ProgressBarExt.DefaultProcessInfoRenderer = if (!ExtSettings.INSTANCE.getEnableProcessBar())
         null
         else
         this.wrapperCustom(ProgressBarExt.getProgressBar$default(this.progressBarExt, "e", (long)size, null, null, 12, null));
      if (progressBarRenderer != null) {
         progressBarRenderer.setSplitLines(20L);
      }

      val semaphore: Semaphore = SemaphoreKt.Semaphore$default(Math.max(this.getNumberThreads() * 4, 4), 0, 2, null);
      val `$this$map$iv`: java.lang.Iterable = entries;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(
            BuildersKt.launch$default(
               scope,
               (new CoroutineName("process-$`item$iv$iv`")) as CoroutineContext,
               null,
               (
                  new Function2<CoroutineScope, Continuation<? super Unit>, Object>(semaphore, counter, this, (M)`item$iv$iv`, size, progressBarRenderer, null)// $VF: Couldn't be decompiled
         // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
         // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.modules.decompiler.stats.Statement.getVarDefinitions()" because "stat" is null
         //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1468)
         //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingExprent(VarDefinitionHelper.java:1679)
         //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1496)
         //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1545)
         //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.remapClashingNames(VarDefinitionHelper.java:1458)
         //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarProcessor.rerunClashing(VarProcessor.java:99)
         //   at org.jetbrains.java.decompiler.main.ClassWriter.invokeProcessors(ClassWriter.java:118)
         //   at org.jetbrains.java.decompiler.main.ClassWriter.writeClass(ClassWriter.java:352)
         //   at org.jetbrains.java.decompiler.modules.decompiler.exps.NewExprent.toJava(NewExprent.java:407)
         //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.wrapOperandString(FunctionExprent.java:761)
         //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.wrapOperandString(FunctionExprent.java:727)
         
               ) as Function2,
               2,
               null
            )
         );
      }

      val jobs: java.util.List = `destination$iv$iv` as java.util.List;
      return TuplesKt.to(
         BuildersKt.launch$default(
            scope, null, null, (new Function2<CoroutineScope, Continuation<? super Unit>, Object>(jobs, progressBarRenderer, this, null) {
               int label;

               {
                  super(2, `$completionx`);
                  this.$jobs = `$jobs`;
                  this.$progressBarRenderer = `$progressBarRenderer`;
                  this.this$0 = `$receiver`;
               }

               public final Object invokeSuspend(Object $result) {
                  val var2: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                  switch (this.label) {
                     case 0:
                        ResultKt.throwOnFailure(`$result`);
                        val var10000: java.util.Collection = this.$jobs;
                        val var10001: Continuation = this as Continuation;
                        this.label = 1;
                        if (AwaitKt.joinAll(var10000, var10001) === var2) {
                           return var2;
                        }
                        break;
                     case 1:
                        ResultKt.throwOnFailure(`$result`);
                        break;
                     default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                  }

                  if (this.$progressBarRenderer != null) {
                     this.$progressBarRenderer.close();
                  }

                  ForwardInterProceduralAnalysis.access$getLogger$cp().info(<unrepresentable>::invokeSuspend$lambda$0);
                  return Unit.INSTANCE;
               }

               public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                  return (new <anonymous constructor>(this.$jobs, this.$progressBarRenderer, this.this$0, `$completion`)) as Continuation<Unit>;
               }

               public final Object invoke(CoroutineScope p1, Continuation<? super Unit> p2) {
                  return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
               }

               private static final Object invokeSuspend$lambda$0(ForwardInterProceduralAnalysis this$0) {
                  return RecCoroutineCacheImplKt.pp(`this$0`.getCache().getCacheStats());
               }
            }) as Function2, 3, null
         ),
         progressBarRenderer
      );
   }

   public override fun doAnalysis(entries: Collection<Any>) {
      val executor: InterruptableExecutor = new InterruptableExecutor(
         this.getNumberThreads(), Integer.MAX_VALUE, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue()
      );
      val scope: CoroutineScope = CoroutineScopeKt.CoroutineScope(
         ExecutorsKt.from(executor as ExecutorService).plus((new CoroutineName("Scope-AI-analysis")) as CoroutineContext)
      );
      val size: Int = entries.size();
      val progressBarRenderer: ObjectRef = new ObjectRef();
      BuildersKt.runBlocking$default(
         null,
         (
            new Function2<CoroutineScope, Continuation<? super Unit>, Object>(size, this, scope, entries, progressBarRenderer, null)// $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.modules.decompiler.stats.Statement.getVarDefinitions()" because "stat" is null
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1468)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingExprent(VarDefinitionHelper.java:1679)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1496)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1545)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.remapClashingNames(VarDefinitionHelper.java:1458)
      //   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarProcessor.rerunClashing(VarProcessor.java:99)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.invokeProcessors(ClassWriter.java:118)
      //   at org.jetbrains.java.decompiler.main.ClassWriter.writeClass(ClassWriter.java:352)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.NewExprent.toJava(NewExprent.java:407)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.wrapOperandString(FunctionExprent.java:761)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.FunctionExprent.wrapOperandString(FunctionExprent.java:727)
      
         ) as Function2,
         1,
         null
      );
      if (!executor.isFinished()) {
         logger.warn(ForwardInterProceduralAnalysis::doAnalysis$lambda$8);
         executor.awaitCompletion(6000L, TimeUnit.SECONDS);
         logger.info(ForwardInterProceduralAnalysis::doAnalysis$lambda$9);
      }

      val var10000: ProgressBarExt.DefaultProcessInfoRenderer = progressBarRenderer.element as ProgressBarExt.DefaultProcessInfoRenderer;
      if (progressBarRenderer.element as ProgressBarExt.DefaultProcessInfoRenderer != null) {
         logger.warn(ForwardInterProceduralAnalysis::doAnalysis$lambda$11$lambda$10);
      }

      executor.interrupt();
      executor.shutdown();
      if (!this.getCache().validateAfterFinished() || UsefulMetrics.Companion.getMetrics().isMemoryThresholdTriggered()) {
         this.getCache().cleanUp();
      }

      System.gc();
      Thread.sleep(500L);
      logger.info(ForwardInterProceduralAnalysis::doAnalysis$lambda$12);
   }

   public abstract suspend fun normalFlowFunction(context: Any, node: Any, succ: Any, inValue: Any, isNegativeBranch: AtomicBoolean): Any {
   }

   public open fun callLocalFlowFunction(context: Context<SootMethod, soot.Unit, Any>, node: soot.Unit, succ: soot.Unit, callSiteValue: Any): Any {
      return this.bottomValue();
   }

   public abstract fun callEntryFlowFunction(context: Any, callee: Any, node: Any, succ: Any, inValue: Any): Any {
   }

   public abstract fun callExitFlowFunction(
      context: Any,
      siteValue: Any,
      callee: Any,
      callEdgeValue: Any,
      calleeCtx: Any,
      node: Any,
      succ: Any,
      isAnalyzable: Boolean
   ): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<Any, Any, Any?> {
   }

   public open suspend fun returnFlowFunction(context: Any, node: Any, returnValue: Any): Any {
      return returnFlowFunction$suspendImpl(this, (CTX)context, (N)node, (A)returnValue, `$completion`);
   }

   public open fun wideningFunction(context: Any, node: Any, succ: Any, `in`: Any): Any? {
      return null;
   }

   public open fun hasChange(context: Any, node: Any, succ: Any, old: Any, new: Any): FixPointStatus {
      val `this_$iv`: FixPointStatus.Companion = FixPointStatus.Companion;
      return if (old == var5) FixPointStatus.HasChange else FixPointStatus.Fixpoint;
   }

   public abstract fun isAnalyzable(callee: Any, in1: Any): Boolean {
   }

   public open fun resolveTargets(callerMethod: Any, node: Any): Set<Any> {
      return this.programRepresentation().getCalleesOfCallAt((M)callerMethod, (N)node);
   }

   public abstract fun getCfg(method: Any, isAnalyzable: Boolean): DirectedGraph<Any> {
   }

   @JvmStatic
   fun `wrapperCustom$lambda$0`(`this$0`: ForwardInterProceduralAnalysis, processBar: ProgressBar, render: ProgressBarRenderer): <unrepresentable> {
      return new ProgressBarExt.DefaultProcessInfoRenderer(processBar, render, `this$0`) {
         {
            super(`$processBar`, `$render`);
            this.this$0 = `$receiver`;
         }

         @Override
         public java.lang.String getExtraMessage() {
            return "${super.getExtraMessage()}${if (ForwardInterProceduralAnalysis.access$getLimitedAnalytics$p(this.this$0)) " !" else ""}";
         }
      };
   }

   @JvmStatic
   fun `init$lambda$1`(it: Context): Array<Any> {
      return new Object[]{it};
   }

   @JvmStatic
   fun `processCallCoroutine$lambda$3`(`$callee`: Any, `$currentContext`: Context, `$node`: Any): Any {
      return "skip recursive await $`$callee` at ${`$currentContext`.getMethod()}: at line $`$node`";
   }

   @JvmStatic
   fun `processCallCoroutine$lambda$4`(`$callee`: Any, `$node`: Any, `$currentContext`: Context): Any {
      return "An error occurred when analyzing callee method: $`$callee` at $`$node` in ${`$currentContext`.getMethod()}";
   }

   @JvmStatic
   fun `doAnalyze$lambda$6`(`this$0`: ForwardInterProceduralAnalysis): Any {
      return "num threads: ${`this$0`.getNumberThreads()}";
   }

   @JvmStatic
   fun `doAnalysis$lambda$8`(): Any {
      return "AIAnalysis: There are still tasks running in the executor. waiting ...";
   }

   @JvmStatic
   fun `doAnalysis$lambda$9`(): Any {
      return "AIAnalysis: Done";
   }

   @JvmStatic
   fun `doAnalysis$lambda$11$lambda$10`(`$this_run`: ProgressBarExt.DefaultProcessInfoRenderer): Any {
      return "Maximum memory usage: ${`$this_run`.getMaxUsedMemoryText()} G";
   }

   @JvmStatic
   fun `doAnalysis$lambda$12`(): Any {
      return "After Analyze: Process information: ${ProcessInfoView.Companion.getGlobalProcessInfo().getProcessInfoText()}";
   }

   @JvmStatic
   fun `logger$lambda$13`(): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `doAnalysis$1$invokeSuspend$$inlined$bracket$1`(`$msg`: java.lang.String): Any {
      return "Started: ${`$msg`}";
   }

   @JvmStatic
   fun `doAnalysis$1$invokeSuspend$$inlined$bracket$2`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String,
      `$res`: ObjectRef
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${`$msg`} ${Result.box-impl(
         Result.constructor-impl((`$res`.element as Maybe).getOrThrow())
      )}";
   }

   @JvmStatic
   fun `doAnalysis$1$invokeSuspend$$inlined$bracket$3`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${`$msg`} <Nothing>";
   }

   @JvmStatic
   fun `doAnalysis$1$invokeSuspend$$inlined$bracket$4`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String,
      `$t`: java.lang.Throwable
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${`$msg`} :: EXCEPTION :: ${Result.box-impl(
         Result.constructor-impl(ResultKt.createFailure(`$t`))
      )}";
   }

   @JvmStatic
   fun `doAnalysis$1$invokeSuspend$$inlined$bracket$5`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String,
      `$res`: ObjectRef
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${`$msg`} ${Result.box-impl(
         Result.constructor-impl((`$res`.element as Maybe).getOrThrow())
      )}";
   }

   @JvmStatic
   fun `doAnalysis$1$invokeSuspend$$inlined$bracket$6`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${`$msg`} <Nothing>";
   }

   @JvmStatic
   fun `doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$1`(`$msg`: java.lang.String): Any {
      return "Started: ${`$msg`}";
   }

   @JvmStatic
   fun `doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$2`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String,
      `$res`: ObjectRef,
      var4: Any
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      var var10000: java.lang.String = LoggingKt.elapsedSecFrom(var1);
      val var10001: java.lang.String = `$msg`;
      val it: Any = Result.constructor-impl((`$res`.element as Maybe).getOrThrow());
      if (Result.isFailure-impl(it)) {
         val var7: java.lang.Throwable = Result.exceptionOrNull-impl(it);
         var10000 = if (var7 != null) var7.getMessage() else null;
      } else {
         var10000 = Result.box-impl(it);
      }
      return "Finished (in $var10000): $var10001 $var10000";
   }

   @JvmStatic
   fun `doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$3`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${`$msg`} <Nothing>";
   }

   @JvmStatic
   fun `doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$4`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String,
      `$t`: java.lang.Throwable,
      var4: Any
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      var var10000: java.lang.String = LoggingKt.elapsedSecFrom(var1);
      val var10001: java.lang.String = `$msg`;
      val it: Any = Result.constructor-impl(ResultKt.createFailure(`$t`));
      if (Result.isFailure-impl(it)) {
         val var7: java.lang.Throwable = Result.exceptionOrNull-impl(it);
         var10000 = if (var7 != null) var7.getMessage() else null;
      } else {
         var10000 = Result.box-impl(it);
      }
      return "Finished (in $var10000): $var10001 :: EXCEPTION :: $var10000";
   }

   @JvmStatic
   fun `doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$5`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String,
      `$res`: ObjectRef,
      var4: Any
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      var var10000: java.lang.String = LoggingKt.elapsedSecFrom(var1);
      val var10001: java.lang.String = `$msg`;
      val it: Any = Result.constructor-impl((`$res`.element as Maybe).getOrThrow());
      if (Result.isFailure-impl(it)) {
         val var7: java.lang.Throwable = Result.exceptionOrNull-impl(it);
         var10000 = if (var7 != null) var7.getMessage() else null;
      } else {
         var10000 = Result.box-impl(it);
      }
      return "Finished (in $var10000): $var10001 $var10000";
   }

   @JvmStatic
   fun `doAnalyze$jobs$1$1$invokeSuspend$lambda$2$$inlined$bracket$6`(
      `$startTime`: LocalDateTime,
      `$msg`: java.lang.String
   ): Any {
      val var1: LocalDateTime = `$startTime`;
      return "Finished (in ${LoggingKt.elapsedSecFrom(var1)}): ${`$msg`} <Nothing>";
   }

   open fun ForwardInterProceduralAnalysis() {
      this(null, 1, null);
   }

   public companion object {
      private final var logger: KLogger
   }

   public data class FactKey<M, N, A>(`in`: Any, method: Any, isAnalyzable: Boolean) {
      public final val `in`: Any
      public final val method: Any
      public final val isAnalyzable: Boolean
      public final val accessTimes: AtomicInteger

      init {
         this.in = (A)`in`;
         this.method = (M)method;
         this.isAnalyzable = isAnalyzable;
         this.accessTimes = new AtomicInteger(0);
      }

      public operator fun component1(): Any {
         return this.in;
      }

      public operator fun component2(): Any {
         return this.method;
      }

      public operator fun component3(): Boolean {
         return this.isAnalyzable;
      }

      public fun copy(`in`: Any = this.in, method: Any = this.method, isAnalyzable: Boolean = this.isAnalyzable): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.FactKey<
            Any,
            Any,
            Any
         > {
         return new ForwardInterProceduralAnalysis.FactKey<>((A)`in`, (M)method, isAnalyzable);
      }

      public override fun toString(): String {
         return "FactKey(in=${this.in}, method=${this.method}, isAnalyzable=${this.isAnalyzable})";
      }

      public override fun hashCode(): Int {
         return ((if (this.in == null) 0 else this.in.hashCode()) * 31 + (if (this.method == null) 0 else this.method.hashCode())) * 31
            + java.lang.Boolean.hashCode(this.isAnalyzable);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ForwardInterProceduralAnalysis.FactKey) {
            return false;
         } else {
            val var2: ForwardInterProceduralAnalysis.FactKey = other as ForwardInterProceduralAnalysis.FactKey;
            if (!(this.in == (other as ForwardInterProceduralAnalysis.FactKey).in)) {
               return false;
            } else if (!(this.method == var2.method)) {
               return false;
            } else {
               return this.isAnalyzable == var2.isAnalyzable;
            }
         }
      }
   }

   public data class InvokeResult<M, A, R>(callee: Any, callSiteOutAbstract: Any, resultValue: Any?) {
      public final val callee: Any
      public final val callSiteOutAbstract: Any
      public final val resultValue: Any?

      init {
         this.callee = (M)callee;
         this.callSiteOutAbstract = (A)callSiteOutAbstract;
         this.resultValue = (R)resultValue;
      }

      public operator fun component1(): Any {
         return this.callee;
      }

      public operator fun component2(): Any {
         return this.callSiteOutAbstract;
      }

      public operator fun component3(): Any? {
         return this.resultValue;
      }

      public fun copy(callee: Any = this.callee, callSiteOutAbstract: Any = this.callSiteOutAbstract, resultValue: Any? = this.resultValue): cn.sast.idfa.analysis.ForwardInterProceduralAnalysis.InvokeResult<
            Any,
            Any,
            Any
         > {
         return new ForwardInterProceduralAnalysis.InvokeResult<>((M)callee, (A)callSiteOutAbstract, (R)resultValue);
      }

      public override fun toString(): String {
         return "InvokeResult(callee=${this.callee}, callSiteOutAbstract=${this.callSiteOutAbstract}, resultValue=${this.resultValue})";
      }

      public override fun hashCode(): Int {
         return (
                  (if (this.callee == null) 0 else this.callee.hashCode()) * 31
                     + (if (this.callSiteOutAbstract == null) 0 else this.callSiteOutAbstract.hashCode())
               )
               * 31
            + (if (this.resultValue == null) 0 else this.resultValue.hashCode());
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ForwardInterProceduralAnalysis.InvokeResult) {
            return false;
         } else {
            val var2: ForwardInterProceduralAnalysis.InvokeResult = other as ForwardInterProceduralAnalysis.InvokeResult;
            if (!(this.callee == (other as ForwardInterProceduralAnalysis.InvokeResult).callee)) {
               return false;
            } else if (!(this.callSiteOutAbstract == var2.callSiteOutAbstract)) {
               return false;
            } else {
               return this.resultValue == var2.resultValue;
            }
         }
      }
   }
}
