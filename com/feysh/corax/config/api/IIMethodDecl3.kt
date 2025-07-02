package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface IIMethodDecl3<R, This, P0, P1, P2> : IIMethodDecl<R, This> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): IIMethodDecl3.CheckBuilder<Any, Any, Any, Any, Any> {
   }

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (IIMethodDecl3.CheckBuilder<Any, Any, Any, Any, Any>, IParameterT<Any>, IParameterT<Any>, IParameterT<Any>) -> Unit
   ): IIMethodDecl3<Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (IIMethodDecl3.CheckBuilder<Any, Any, Any, Any, Any>) -> Unit): IIMethodDecl3<
         Any,
         Any,
         Any,
         Any,
         Any
      > {
   }

   public interface CheckBuilder<R, This, P0, P1, P2> : IIMethodDecl.CheckBuilder<R, This> {
      public val method: IIMethodDecl3<Any, Any, Any, Any, Any>

      public open val p0: IParameterT<Any>
         public open get() {
         }


      public open val p1: IParameterT<Any>
         public open get() {
         }


      public open val p2: IParameterT<Any>
         public open get() {
         }


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, This, P0, P1, P2> getP0(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> getP1(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> getP2(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> plus(
            `$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> plus(
            `$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> minus(
            `$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> minus(
            `$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> startsWith(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> endsWith(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> contains(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> stringEquals(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> taintOf(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> getEmptyTaint(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> getEmptyVia(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> containsAll(
            `$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, This, P0, P1, P2, T1 extends R, T2 extends R, R> anyOr(
            `$this`: IIMethodDecl3CheckBuilder<R_I1, This, P0, P1, P2>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, T> field(`$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, T, FieldType> field(
            `$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, T> field(
            `$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2> check(
            `$this`: IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit
         ) {
            IIMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nInstanceMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstanceMethodInterface.kt\ncom/feysh/corax/config/api/IIMethodDecl3$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,582:1\n1#2:583\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, This, P0, P1, P2> model(
         `$this`: IIMethodDecl3<R, This, P0, P1, P2>,
         config: (MethodConfig?) -> Unit,
         block: (IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?) -> Unit
      ): IIMethodDecl3<R, This, P0, P1, P2> {
         val var3: IIMethodDecl3.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(var3, var3.getP0(), var3.getP1(), var3.getP2());
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1, P2> modelNoArg(
         `$this`: IIMethodDecl3<R, This, P0, P1, P2>, config: (MethodConfig?) -> Unit, block: (IIMethodDecl3CheckBuilder<R, This, P0, P1, P2>?) -> Unit
      ): IIMethodDecl3<R, This, P0, P1, P2> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1, P2> getArgumentCnt(`$this`: IIMethodDecl3<R, This, P0, P1, P2>): Int? {
         return IIMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, This, P0, P1, P2> getActualType(`$this`: IIMethodDecl3<R, This, P0, P1, P2>): KFunctionType? {
         return IIMethodDecl.DefaultImpls.getActualType(`$this`);
      }

      @JvmStatic
      fun `model$lambda$0`(var0: MethodConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `modelNoArg$lambda$2`(var0: MethodConfig): Unit {
         return Unit.INSTANCE;
      }
   }
}
