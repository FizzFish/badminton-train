package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.AIAnalysisApi
import com.feysh.corax.config.api.AnalysisApiNotImplException
import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IBoolExpr
import com.feysh.corax.config.api.IClassDecl
import com.feysh.corax.config.api.IClassMatch
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IFieldDecl
import com.feysh.corax.config.api.IFieldMatch
import com.feysh.corax.config.api.IIMethodDecl
import com.feysh.corax.config.api.IIMethodDecl0
import com.feysh.corax.config.api.IIMethodDecl1
import com.feysh.corax.config.api.IIMethodDecl10
import com.feysh.corax.config.api.IIMethodDecl11
import com.feysh.corax.config.api.IIMethodDecl12
import com.feysh.corax.config.api.IIMethodDecl13
import com.feysh.corax.config.api.IIMethodDecl14
import com.feysh.corax.config.api.IIMethodDecl15
import com.feysh.corax.config.api.IIMethodDecl16
import com.feysh.corax.config.api.IIMethodDecl2
import com.feysh.corax.config.api.IIMethodDecl3
import com.feysh.corax.config.api.IIMethodDecl4
import com.feysh.corax.config.api.IIMethodDecl5
import com.feysh.corax.config.api.IIMethodDecl6
import com.feysh.corax.config.api.IIMethodDecl7
import com.feysh.corax.config.api.IIMethodDecl8
import com.feysh.corax.config.api.IIMethodDecl9
import com.feysh.corax.config.api.IJDecl
import com.feysh.corax.config.api.ILocalVarDecl
import com.feysh.corax.config.api.ILocalVarMatch
import com.feysh.corax.config.api.IMethodDecl
import com.feysh.corax.config.api.IMethodMatch
import com.feysh.corax.config.api.IRawMethodDecl
import com.feysh.corax.config.api.ISMethodDecl
import com.feysh.corax.config.api.ISMethodDecl0
import com.feysh.corax.config.api.ISMethodDecl1
import com.feysh.corax.config.api.ISMethodDecl10
import com.feysh.corax.config.api.ISMethodDecl11
import com.feysh.corax.config.api.ISMethodDecl12
import com.feysh.corax.config.api.ISMethodDecl13
import com.feysh.corax.config.api.ISMethodDecl14
import com.feysh.corax.config.api.ISMethodDecl15
import com.feysh.corax.config.api.ISMethodDecl16
import com.feysh.corax.config.api.ISMethodDecl2
import com.feysh.corax.config.api.ISMethodDecl3
import com.feysh.corax.config.api.ISMethodDecl4
import com.feysh.corax.config.api.ISMethodDecl5
import com.feysh.corax.config.api.ISMethodDecl6
import com.feysh.corax.config.api.ISMethodDecl7
import com.feysh.corax.config.api.ISMethodDecl8
import com.feysh.corax.config.api.ISMethodDecl9
import com.feysh.corax.config.api.ISootClassDecl
import com.feysh.corax.config.api.ISootFieldDecl
import com.feysh.corax.config.api.ISootLocalVarDecl
import com.feysh.corax.config.api.ISootMethodDecl
import com.feysh.corax.config.api.IStmt
import com.feysh.corax.config.api.MethodConfig
import com.feysh.corax.config.api.XDecl
import com.feysh.corax.config.api.BugMessage.Env
import com.feysh.corax.config.api.utils.KFunctionType
import java.util.ArrayList
import java.util.Collections
import java.util.LinkedHashMap
import java.util.LinkedList
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.Ref.IntRef
import kotlin.reflect.KProperty
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.sync.Semaphore
import mu.KLogger
import org.slf4j.event.Level
import soot.Scene
import soot.SootClass
import soot.SootMethod

public abstract class AIAnalysisBaseImpl : AIAnalysisApi {
   public open val state: MutableMap<String, Any> = (new LinkedHashMap()) as java.util.Map
   public final val eachClassCallBacks: MutableList<(SootClass) -> Unit>

   public open val sootDecl: Sequence<ISootMethodDecl<R>>
      public open get() {
         val var10000: IMethodMatch = `$this$sootDecl`.getMatch();
         val var10001: Scene = Scene.v();
         return SequencesKt.map(CollectionsKt.asSequence(var10000.matched(var10001)), AIAnalysisBaseImpl::_get_sootDecl_$lambda$5);
      }


   open fun AIAnalysisBaseImpl() {
      val var10001: java.util.List = Collections.synchronizedList(new ArrayList());
      this.eachClassCallBacks = var10001;
   }

   public override fun toDecl(target: Any): XDecl {
      return new XDecl() {};
   }

   public override infix fun XDecl.dependsOn(dep: XDecl) {
   }

   public override infix fun Collection<XDecl>.dependsOn(deps: Collection<XDecl>) {
   }

   public fun <R> match(method: () -> R, except: KFunctionType): IMethodMatch {
      val var3: SootSignatureMatch = SootSignatureMatch.Companion.invoke(method);
      val var10000: KFunctionType = var3.getActualType();
      if (var10000 != null) {
         if (var10000 != except) {
            this.getError().error("error: match function expected a \"$except\" type method but actual type: \"$var10000\" of $var3. ");
         }
      }

      return var3;
   }

   public open fun getMethodDecl(method: IMethodMatch): IMethodDecl<Any> {
      return new MethodDeclBase<>(this, method, null, 4, null);
   }

   public open fun <R> getMethodDecl(method: () -> R, except: KFunctionType): IMethodDecl<R> {
      return new MethodDeclBase(this, this.match(method, except), null, 4, null);
   }

   public override fun clazz(classMatch: IClassMatch): IClassDecl {
      return new BaseClassDecl(this, classMatch);
   }

   public override fun method(method: IMethodMatch): IRawMethodDecl<Any> {
      return new RawMethodDecl(this.getMethodDecl(method));
   }

   public override fun field(fieldMatch: IFieldMatch): IFieldDecl<Any, Any> {
      return new BaseFieldDecl<>(this, fieldMatch);
   }

   public override fun localVar(localVarMatch: ILocalVarMatch): ILocalVarDecl<Any> {
      return new BaseLocalVarDecl<>(this, localVarMatch);
   }

   public fun <R, This> getConstructorDecl(function: () -> R): IIMethodDecl<R, This> {
      return new InstanceMethodDecl(this.getMethodDecl(function, KFunctionType.Constructor));
   }

   public fun <R, This> getInstanceMethodDecl(function: () -> R): IIMethodDecl<R, This> {
      return new InstanceMethodDecl(this.getMethodDecl(function, KFunctionType.InstanceMethod));
   }

   public fun <R> getStaticMethodDecl(function: () -> R): ISMethodDecl<R> {
      return new StaticMethodDecl(this.getMethodDecl(function, KFunctionType.StaticMethod));
   }

   public abstract fun addStmt(decl: IJDecl, config: (MethodConfig) -> Unit, stmt: IStmt) {
   }

   public abstract fun check(decl: IJDecl, config: (MethodConfig) -> Unit, expr: IBoolExpr, checkType: CheckType, env: (Env) -> Unit) {
   }

   public abstract fun eval(decl: IJDecl, config: (MethodConfig) -> Unit, expr: IExpr, accept: (Any) -> Unit) {
   }

   public override fun <V> field(field: KProperty<V>): IFieldDecl<Any, V> {
      return new BaseFieldDecl<>(this, new SootFieldSignatureMatch(field));
   }

   public override fun eachMethod(block: (ISootMethodDecl<Any>) -> Unit) {
      this.eachClass(AIAnalysisBaseImpl::eachMethod$lambda$1);
   }

   public override fun eachField(block: (ISootFieldDecl<Any, Any>) -> Unit) {
      this.eachClass(AIAnalysisBaseImpl::eachField$lambda$2);
   }

   public override fun eachClass(block: (ISootClassDecl) -> Unit) {
      this.eachClassCallBacks.add(AIAnalysisBaseImpl::eachClass$lambda$3);
   }

   public fun initializeClassCallBacks(scope: CoroutineScope, semaphore: Semaphore) {
      val errCount: IntRef = new IntRef();

      do {
         val callBacksInClass: LinkedList = new LinkedList<>(this.eachClassCallBacks);
         this.eachClassCallBacks.clear();
         var var10000: java.util.Iterator = Scene.v().getClasses().snapshotIterator();
         val var5: java.util.Iterator = var10000;

         while (var5.hasNext()) {
            val sc: SootClass = var5.next() as SootClass;
            var10000 = callBacksInClass.iterator();
            val var7: java.util.Iterator = var10000;

            while (var7.hasNext()) {
               val cb: Function1 = var7.next() as Function1;
               BuildersKt.launch$default(
                  scope,
                  null,
                  null,
                  (
                     new Function2<CoroutineScope, Continuation<? super Unit>, Object>(semaphore, cb, sc, errCount, this, null) {
                        Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        Object L$4;
                        int label;

                        {
                           super(2, `$completionx`);
                           this.$semaphore = `$semaphore`;
                           this.$cb = `$cb`;
                           this.$sc = `$sc`;
                           this.$errCount = `$errCount`;
                           this.this$0 = `$receiver`;
                        }

                        public final Object invokeSuspend(Object $result) {
                           label40: {
                              val var13: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                              var `$this$withPermit$iv`: Semaphore;
                              var var3: Function1;
                              var var4: SootClass;
                              var var5: IntRef;
                              var var6: AIAnalysisBaseImpl;
                              switch (this.label) {
                                 case 0:
                                    ResultKt.throwOnFailure(`$result`);
                                    `$this$withPermit$iv` = this.$semaphore;
                                    var3 = this.$cb;
                                    var4 = this.$sc;
                                    var5 = this.$errCount;
                                    var6 = this.this$0;
                                    val var10001: Continuation = this as Continuation;
                                    this.L$0 = this.$semaphore;
                                    this.L$1 = var3;
                                    this.L$2 = var4;
                                    this.L$3 = var5;
                                    this.L$4 = var6;
                                    this.label = 1;
                                    if (`$this$withPermit$iv`.acquire(var10001) === var13) {
                                       return var13;
                                    }
                                    break;
                                 case 1:
                                    var6 = this.L$4 as AIAnalysisBaseImpl;
                                    var5 = this.L$3 as IntRef;
                                    var4 = this.L$2 as SootClass;
                                    var3 = this.L$1 as Function1;
                                    `$this$withPermit$iv` = this.L$0 as Semaphore;
                                    ResultKt.throwOnFailure(`$result`);
                                    break;
                                 default:
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                              }

                              try {
                                 try {
                                    var3.invoke(var4);
                                 } catch (var14: AnalysisApiNotImplException) {
                                 } catch (var15: Exception) {
                                    if (var5.element < 20) {
                                       var6.getLogger().error(var15, <unrepresentable>::invokeSuspend$lambda$1$lambda$0);
                                    }

                                    val var10: Int = var5.element++;
                                 }
                              } catch (var16: java.lang.Throwable) {
                                 `$this$withPermit$iv`.release();
                              }

                              `$this$withPermit$iv`.release();
                           }
                        }

                        public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                           return (new <anonymous constructor>(this.$semaphore, this.$cb, this.$sc, this.$errCount, this.this$0, `$completion`)) as Continuation<Unit>;
                        }

                        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> p2) {
                           return (this.create(p1, p2) as <unrepresentable>).invokeSuspend(Unit.INSTANCE);
                        }

                        private static final Object invokeSuspend$lambda$1$lambda$0() {
                           return "A error occurred when process initializeClassCallBacks! Please file this bug to us.";
                        }
                     }
                  ) as Function2,
                  3,
                  null
               );
            }
         }
      } while (!this.eachClassCallBacks.isEmpty());
   }

   public override fun sootClass(sootClass: SootClass): ISootClassDecl {
      return new BaseSootClassDecl(new BaseClassDecl(this, new FullClassMatch(sootClass)), this, sootClass);
   }

   public override fun eachLocalVariable(block: (ISootLocalVarDecl<Any>) -> Unit) {
      this.eachMethod(AIAnalysisBaseImpl::eachLocalVariable$lambda$4);
   }

   public override fun <This> constructor(at: () -> This): IIMethodDecl0<This, This> {
      return new InstanceMethodDecl0(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS> method(at: (THIS) -> R): IIMethodDecl0<R, THIS> {
      return new InstanceMethodDecl0(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R> staticMethod(at: () -> R): ISMethodDecl0<R> {
      return new StaticMethodDecl0(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0> constructor(at: (P0) -> This): IIMethodDecl1<This, This, P0> {
      return new InstanceMethodDecl1(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0> method(at: (THIS, P0) -> R): IIMethodDecl1<R, THIS, P0> {
      return new InstanceMethodDecl1(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0> staticMethod(at: (P0) -> R): ISMethodDecl1<R, P0> {
      return new StaticMethodDecl1(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1> constructor(at: (P0, P1) -> This): IIMethodDecl2<This, This, P0, P1> {
      return new InstanceMethodDecl2(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1> method(at: (THIS, P0, P1) -> R): IIMethodDecl2<R, THIS, P0, P1> {
      return new InstanceMethodDecl2(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1> staticMethod(at: (P0, P1) -> R): ISMethodDecl2<R, P0, P1> {
      return new StaticMethodDecl2(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2> constructor(at: (P0, P1, P2) -> This): IIMethodDecl3<This, This, P0, P1, P2> {
      return new InstanceMethodDecl3(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2> method(at: (THIS, P0, P1, P2) -> R): IIMethodDecl3<R, THIS, P0, P1, P2> {
      return new InstanceMethodDecl3(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2> staticMethod(at: (P0, P1, P2) -> R): ISMethodDecl3<R, P0, P1, P2> {
      return new StaticMethodDecl3(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3> constructor(at: (P0, P1, P2, P3) -> This): IIMethodDecl4<This, This, P0, P1, P2, P3> {
      return new InstanceMethodDecl4(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3> method(at: (THIS, P0, P1, P2, P3) -> R): IIMethodDecl4<R, THIS, P0, P1, P2, P3> {
      return new InstanceMethodDecl4(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3> staticMethod(at: (P0, P1, P2, P3) -> R): ISMethodDecl4<R, P0, P1, P2, P3> {
      return new StaticMethodDecl4(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4> constructor(at: (P0, P1, P2, P3, P4) -> This): IIMethodDecl5<This, This, P0, P1, P2, P3, P4> {
      return new InstanceMethodDecl5(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4> method(at: (THIS, P0, P1, P2, P3, P4) -> R): IIMethodDecl5<R, THIS, P0, P1, P2, P3, P4> {
      return new InstanceMethodDecl5(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4> staticMethod(at: (P0, P1, P2, P3, P4) -> R): ISMethodDecl5<R, P0, P1, P2, P3, P4> {
      return new StaticMethodDecl5(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5> constructor(at: (P0, P1, P2, P3, P4, P5) -> This): IIMethodDecl6<This, This, P0, P1, P2, P3, P4, P5> {
      return new InstanceMethodDecl6(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5> method(at: (THIS, P0, P1, P2, P3, P4, P5) -> R): IIMethodDecl6<R, THIS, P0, P1, P2, P3, P4, P5> {
      return new InstanceMethodDecl6(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5> staticMethod(at: (P0, P1, P2, P3, P4, P5) -> R): ISMethodDecl6<R, P0, P1, P2, P3, P4, P5> {
      return new StaticMethodDecl6(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6> constructor(at: (P0, P1, P2, P3, P4, P5, P6) -> This): IIMethodDecl7<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6
      > {
      return new InstanceMethodDecl7(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6) -> R): IIMethodDecl7<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6
      > {
      return new InstanceMethodDecl7(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6) -> R): ISMethodDecl7<R, P0, P1, P2, P3, P4, P5, P6> {
      return new StaticMethodDecl7(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7) -> This): IIMethodDecl8<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7
      > {
      return new InstanceMethodDecl8(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7) -> R): IIMethodDecl8<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7
      > {
      return new InstanceMethodDecl8(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7) -> R): ISMethodDecl8<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7
      > {
      return new StaticMethodDecl8(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8) -> This): IIMethodDecl9<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8
      > {
      return new InstanceMethodDecl9(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8) -> R): IIMethodDecl9<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8
      > {
      return new InstanceMethodDecl9(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8) -> R): ISMethodDecl9<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8
      > {
      return new StaticMethodDecl9(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> This): IIMethodDecl10<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9
      > {
      return new InstanceMethodDecl10(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R): IIMethodDecl10<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9
      > {
      return new InstanceMethodDecl10(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9) -> R): ISMethodDecl10<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9
      > {
      return new StaticMethodDecl10(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> This): IIMethodDecl11<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10
      > {
      return new InstanceMethodDecl11(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R): IIMethodDecl11<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10
      > {
      return new InstanceMethodDecl11(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10) -> R): ISMethodDecl11<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10
      > {
      return new StaticMethodDecl11(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> constructor(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> This): IIMethodDecl12<
         This,
         This,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10,
         P11
      > {
      return new InstanceMethodDecl12(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> method(at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R): IIMethodDecl12<
         R,
         THIS,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10,
         P11
      > {
      return new InstanceMethodDecl12(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11) -> R): ISMethodDecl12<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10,
         P11
      > {
      return new StaticMethodDecl12(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> constructor(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> This
   ): IIMethodDecl13<This, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> {
      return new InstanceMethodDecl13(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> method(
      at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R
   ): IIMethodDecl13<R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> {
      return new InstanceMethodDecl13(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> staticMethod(at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12) -> R): ISMethodDecl13<
         R,
         P0,
         P1,
         P2,
         P3,
         P4,
         P5,
         P6,
         P7,
         P8,
         P9,
         P10,
         P11,
         P12
      > {
      return new StaticMethodDecl13(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> constructor(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> This
   ): IIMethodDecl14<This, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
      return new InstanceMethodDecl14(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> method(
      at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R
   ): IIMethodDecl14<R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
      return new InstanceMethodDecl14(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> staticMethod(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13) -> R
   ): ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
      return new StaticMethodDecl14(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> constructor(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> This
   ): IIMethodDecl15<This, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> {
      return new InstanceMethodDecl15(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> method(
      at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R
   ): IIMethodDecl15<R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> {
      return new InstanceMethodDecl15(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> staticMethod(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14) -> R
   ): ISMethodDecl15<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14> {
      return new StaticMethodDecl15(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> constructor(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> This
   ): IIMethodDecl16<This, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> {
      return new InstanceMethodDecl16(this.getConstructorDecl(at as Function), 0, 2, null);
   }

   public override fun <R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> method(
      at: (THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R
   ): IIMethodDecl16<R, THIS, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> {
      return new InstanceMethodDecl16(this.getInstanceMethodDecl(at as Function), 0, 2, null);
   }

   public override fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> staticMethod(
      at: (P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15) -> R
   ): ISMethodDecl16<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15> {
      return new StaticMethodDecl16(this.getStaticMethodDecl(at as Function), 0, 2, null);
   }

   override fun getLogger(): KLogger {
      return AIAnalysisApi.DefaultImpls.getLogger(this);
   }

   override fun setVerbosity(verbosity: Level) {
      AIAnalysisApi.DefaultImpls.setVerbosity(this, verbosity);
   }

   @JvmStatic
   fun `eachMethod$lambda$1`(`$block`: Function1, it: ISootClassDecl): Unit {
      it.eachDeclaringMethod(`$block`);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `eachField$lambda$2`(`$block`: Function1, it: ISootClassDecl): Unit {
      it.eachDeclaringField(`$block`);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `eachClass$lambda$3`(`$block`: Function1, `this$0`: AIAnalysisBaseImpl, sc: SootClass): Unit {
      `$block`.invoke(`this$0`.sootClass(sc));
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `eachLocalVariable$lambda$4`(`$block`: Function1, it: ISootMethodDecl): Unit {
      it.eachLocalVar(`$block`);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `_get_sootDecl_$lambda$5`(`this$0`: AIAnalysisBaseImpl, sm: SootMethod): ISootMethodDecl {
      val var10001: SootClass = sm.getDeclaringClass();
      return `this$0`.sootClass(var10001).sootDeclaringMethod(sm);
   }
}
