package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface ISMethodDecl8<R, P0, P1, P2, P3, P4, P5, P6, P7> : ISMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl8.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (
               ISMethodDecl8.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>
            ) -> Unit
   ): ISMethodDecl8<Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (ISMethodDecl8.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any>) -> Unit): ISMethodDecl8<
         Any,
         Any,
         Any,
         Any,
         Any,
         Any,
         Any,
         Any,
         Any
      > {
   }

   public interface CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7> : ISMethodDecl.CheckBuilder<R> {
      public val method: ISMethodDecl8<Any, Any, Any, Any, Any, Any, Any, Any, Any>

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


      public open val p4: IParameterT<Any>
         public open get() {
         }


      public open val p5: IParameterT<Any>
         public open get() {
         }


      public open val p6: IParameterT<Any>
         public open get() {
         }


      public open val p7: IParameterT<Any>
         public open get() {
         }


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getP0(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getP1(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getP2(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getP3(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P3> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getP4(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P4> {
            return `$this`.paramAt(4);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getP5(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P5> {
            return `$this`.paramAt(5);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getP6(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P6> {
            return `$this`.paramAt(6);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getP7(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P7> {
            return `$this`.paramAt(7);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> plus(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> plus(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> minus(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> minus(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> startsWith(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> endsWith(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> contains(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> stringEquals(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> taintOf(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getEmptyTaint(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getEmptyVia(`$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> containsAll(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            taint: ITaintType
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, P0, P1, P2, P3, P4, P5, P6, P7, T1 extends R, T2 extends R, R> anyOr(
            `$this`: ISMethodDecl8CheckBuilder<R_I1, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, T> field(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: ILocalT<T>, field: SootField
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, T, FieldType> field(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, T> field(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7> check(
            `$this`: ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>,
            expr: ILocalT<java.lang.Boolean>,
            checkType: CheckType,
            env: (BugMessage.Env?) -> Unit
         ) {
            ISMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nStaticMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StaticMethodInterface.kt\ncom/feysh/corax/config/api/ISMethodDecl8$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,577:1\n1#2:578\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7> model(
         `$this`: ISMethodDecl8<R, P0, P1, P2, P3, P4, P5, P6, P7>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?, IParameterT<P4>?, IParameterT<P5>?, IParameterT<P6>?, IParameterT<P7>?) -> Unit
      ): ISMethodDecl8<R, P0, P1, P2, P3, P4, P5, P6, P7> {
         val var3: ISMethodDecl8.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(var3, var3.getP0(), var3.getP1(), var3.getP2(), var3.getP3(), var3.getP4(), var3.getP5(), var3.getP6(), var3.getP7());
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7> modelNoArg(
         `$this`: ISMethodDecl8<R, P0, P1, P2, P3, P4, P5, P6, P7>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl8CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7>?) -> Unit
      ): ISMethodDecl8<R, P0, P1, P2, P3, P4, P5, P6, P7> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getArgumentCnt(`$this`: ISMethodDecl8<R, P0, P1, P2, P3, P4, P5, P6, P7>): Int? {
         return ISMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7> getActualType(`$this`: ISMethodDecl8<R, P0, P1, P2, P3, P4, P5, P6, P7>): KFunctionType? {
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
