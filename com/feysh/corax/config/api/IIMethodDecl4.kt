package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface IIMethodDecl4<R, This, P0, P1, P2, P3> : IIMethodDecl<R, This> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): IIMethodDecl4.CheckBuilder<Any, Any, Any, Any, Any, Any> {
   }

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (IIMethodDecl4.CheckBuilder<Any, Any, Any, Any, Any, Any>, IParameterT<Any>, IParameterT<Any>, IParameterT<Any>, IParameterT<Any>) -> Unit
   ): IIMethodDecl4<Any, Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (IIMethodDecl4.CheckBuilder<Any, Any, Any, Any, Any, Any>) -> Unit): IIMethodDecl4<
         Any,
         Any,
         Any,
         Any,
         Any,
         Any
      > {
   }

   public interface CheckBuilder<R, This, P0, P1, P2, P3> : IIMethodDecl.CheckBuilder<R, This> {
      public val method: IIMethodDecl4<Any, Any, Any, Any, Any, Any>

      public open val p0: IParameterT<Any>
         public open get() {
         }


      public open val p1: IParameterT<Any>
         public open get() {
         }


      public open val p2: IParameterT<Any>
         public open get() {
         }


      public open val p3: IParameterT<Any>
         public open get() {
         }


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, This, P0, P1, P2, P3> getP0(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> getP1(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> getP2(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> getP3(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>): IParameterT<P3> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> plus(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> plus(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> minus(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> minus(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> startsWith(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> endsWith(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> contains(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> stringEquals(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> taintOf(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> getEmptyTaint(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> getEmptyVia(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> containsAll(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, This, P0, P1, P2, P3, T1 extends R, T2 extends R, R> anyOr(
            `$this`: IIMethodDecl4CheckBuilder<R_I1, This, P0, P1, P2, P3>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, T> field(`$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, T, FieldType> field(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, T> field(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3> check(
            `$this`: IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit
         ) {
            IIMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nInstanceMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstanceMethodInterface.kt\ncom/feysh/corax/config/api/IIMethodDecl4$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,582:1\n1#2:583\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, This, P0, P1, P2, P3> model(
         `$this`: IIMethodDecl4<R, This, P0, P1, P2, P3>,
         config: (MethodConfig?) -> Unit,
         block: (IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?) -> Unit
      ): IIMethodDecl4<R, This, P0, P1, P2, P3> {
         val var3: IIMethodDecl4.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(var3, var3.getP0(), var3.getP1(), var3.getP2(), var3.getP3());
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3> modelNoArg(
         `$this`: IIMethodDecl4<R, This, P0, P1, P2, P3>, config: (MethodConfig?) -> Unit, block: (IIMethodDecl4CheckBuilder<R, This, P0, P1, P2, P3>?) -> Unit
      ): IIMethodDecl4<R, This, P0, P1, P2, P3> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3> getArgumentCnt(`$this`: IIMethodDecl4<R, This, P0, P1, P2, P3>): Int? {
         return IIMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3> getActualType(`$this`: IIMethodDecl4<R, This, P0, P1, P2, P3>): KFunctionType? {
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
