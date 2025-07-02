package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> : ISMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl14.CheckBuilder<
         Any,
         Any,
         Any,
         Any,
         Any,
         Any,
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

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (
               ISMethodDecl14.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>
            ) -> Unit
   ): ISMethodDecl14<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(
      config: (MethodConfig) -> Unit = ...,
      block: (ISMethodDecl14.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>) -> Unit
   ): ISMethodDecl14<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public interface CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> : ISMethodDecl.CheckBuilder<R> {
      public val method: ISMethodDecl14<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>

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


      public open val p8: IParameterT<Any>
         public open get() {
         }


      public open val p9: IParameterT<Any>
         public open get() {
         }


      public open val p10: IParameterT<Any>
         public open get() {
         }


      public open val p11: IParameterT<Any>
         public open get() {
         }


      public open val p12: IParameterT<Any>
         public open get() {
         }


      public open val p13: IParameterT<Any>
         public open get() {
         }


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP0(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP1(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP2(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP3(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P3> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP4(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P4> {
            return `$this`.paramAt(4);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP5(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P5> {
            return `$this`.paramAt(5);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP6(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P6> {
            return `$this`.paramAt(6);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP7(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P7> {
            return `$this`.paramAt(7);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP8(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P8> {
            return `$this`.paramAt(8);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP9(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P9> {
            return `$this`.paramAt(9);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP10(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P10> {
            return `$this`.paramAt(10);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP11(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P11> {
            return `$this`.paramAt(11);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP12(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P12> {
            return `$this`.paramAt(12);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getP13(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IParameterT<P13> {
            return `$this`.paramAt(13);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> plus(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> plus(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> minus(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> minus(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> startsWith(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> endsWith(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> contains(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> stringEquals(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> taintOf(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>, vararg type: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getEmptyTaint(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getEmptyVia(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> containsAll(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            taint: ITaintType
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, T1 extends R, T2 extends R, R> anyOr(
            `$this`: ISMethodDecl14CheckBuilder<R_I1, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            `$receiver`: ILocalT<T1>,
            second: ILocalT<T2>
         ): ILocalValue<R> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, T> field(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>, `$receiver`: ILocalT<T>, field: SootField
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, T, FieldType> field(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, T> field(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> check(
            `$this`: ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
            expr: ILocalT<java.lang.Boolean>,
            checkType: CheckType,
            env: (BugMessage.Env?) -> Unit
         ) {
            ISMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nStaticMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StaticMethodInterface.kt\ncom/feysh/corax/config/api/ISMethodDecl14$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,577:1\n1#2:578\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> model(
         `$this`: ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?, IParameterT<P4>?, IParameterT<P5>?, IParameterT<P6>?, IParameterT<P7>?, IParameterT<P8>?, IParameterT<P9>?, IParameterT<P10>?, IParameterT<P11>?, IParameterT<P12>?, IParameterT<P13>?) -> Unit
      ): ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
         val var3: ISMethodDecl14.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(
            var3,
            var3.getP0(),
            var3.getP1(),
            var3.getP2(),
            var3.getP3(),
            var3.getP4(),
            var3.getP5(),
            var3.getP6(),
            var3.getP7(),
            var3.getP8(),
            var3.getP9(),
            var3.getP10(),
            var3.getP11(),
            var3.getP12(),
            var3.getP13()
         );
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> modelNoArg(
         `$this`: ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl14CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>?) -> Unit
      ): ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getArgumentCnt(
         `$this`: ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
      ): Int? {
         return ISMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13> getActualType(
         `$this`: ISMethodDecl14<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13>
      ): KFunctionType? {
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
