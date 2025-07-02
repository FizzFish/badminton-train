package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface ISMethodDecl4<R, P0, P1, P2, P3> : ISMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl4.CheckBuilder<Any, Any, Any, Any, Any> {
   }

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (ISMethodDecl4.CheckBuilder<Any, Any, Any, Any, Any>, IParameterT<Any>, IParameterT<Any>, IParameterT<Any>, IParameterT<Any>) -> Unit
   ): ISMethodDecl4<Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (ISMethodDecl4.CheckBuilder<Any, Any, Any, Any, Any>) -> Unit): ISMethodDecl4<
         Any,
         Any,
         Any,
         Any,
         Any
      > {
   }

   public interface CheckBuilder<R, P0, P1, P2, P3> : ISMethodDecl.CheckBuilder<R> {
      public val method: ISMethodDecl4<Any, Any, Any, Any, Any>

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
         fun <R, P0, P1, P2, P3> getP0(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> getP1(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> getP2(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> getP3(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>): IParameterT<P3> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> plus(
            `$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> plus(
            `$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> minus(
            `$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> minus(
            `$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> startsWith(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> endsWith(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> contains(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> stringEquals(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> taintOf(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> getEmptyTaint(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> getEmptyVia(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> containsAll(
            `$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, P0, P1, P2, P3, T1 extends R, T2 extends R, R> anyOr(
            `$this`: ISMethodDecl4CheckBuilder<R_I1, P0, P1, P2, P3>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, T> field(`$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, T, FieldType> field(
            `$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, T> field(
            `$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3> check(
            `$this`: ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit
         ) {
            ISMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nStaticMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StaticMethodInterface.kt\ncom/feysh/corax/config/api/ISMethodDecl4$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,577:1\n1#2:578\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, P0, P1, P2, P3> model(
         `$this`: ISMethodDecl4<R, P0, P1, P2, P3>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?) -> Unit
      ): ISMethodDecl4<R, P0, P1, P2, P3> {
         val var3: ISMethodDecl4.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(var3, var3.getP0(), var3.getP1(), var3.getP2(), var3.getP3());
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3> modelNoArg(
         `$this`: ISMethodDecl4<R, P0, P1, P2, P3>, config: (MethodConfig?) -> Unit, block: (ISMethodDecl4CheckBuilder<R, P0, P1, P2, P3>?) -> Unit
      ): ISMethodDecl4<R, P0, P1, P2, P3> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3> getArgumentCnt(`$this`: ISMethodDecl4<R, P0, P1, P2, P3>): Int? {
         return ISMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3> getActualType(`$this`: ISMethodDecl4<R, P0, P1, P2, P3>): KFunctionType? {
         return ISMethodDecl.DefaultImpls.getActualType(`$this`);
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
