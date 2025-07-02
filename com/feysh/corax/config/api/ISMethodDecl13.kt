package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface ISMethodDecl13<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> : ISMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl13.CheckBuilder<
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
               ISMethodDecl13.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>,
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
   ): ISMethodDecl13<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(
      config: (MethodConfig) -> Unit = ...,
      block: (ISMethodDecl13.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>) -> Unit
   ): ISMethodDecl13<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public interface CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> : ISMethodDecl.CheckBuilder<R> {
      public val method: ISMethodDecl13<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>

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


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP0(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP1(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP2(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP3(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P3> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP4(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P4> {
            return `$this`.paramAt(4);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP5(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P5> {
            return `$this`.paramAt(5);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP6(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P6> {
            return `$this`.paramAt(6);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP7(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P7> {
            return `$this`.paramAt(7);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP8(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P8> {
            return `$this`.paramAt(8);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP9(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P9> {
            return `$this`.paramAt(9);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP10(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P10> {
            return `$this`.paramAt(10);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP11(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P11> {
            return `$this`.paramAt(11);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getP12(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IParameterT<P12> {
            return `$this`.paramAt(12);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> plus(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> plus(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> minus(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> minus(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> startsWith(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> endsWith(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> contains(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> stringEquals(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> taintOf(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>, vararg type: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getEmptyTaint(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getEmptyVia(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> containsAll(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            taint: ITaintType
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, T1 extends R, T2 extends R, R> anyOr(
            `$this`: ISMethodDecl13CheckBuilder<R_I1, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, T> field(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>, `$receiver`: ILocalT<T>, field: SootField
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, T, FieldType> field(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, T> field(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> check(
            `$this`: ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
            expr: ILocalT<java.lang.Boolean>,
            checkType: CheckType,
            env: (BugMessage.Env?) -> Unit
         ) {
            ISMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nStaticMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StaticMethodInterface.kt\ncom/feysh/corax/config/api/ISMethodDecl13$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,577:1\n1#2:578\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> model(
         `$this`: ISMethodDecl13<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?, IParameterT<P4>?, IParameterT<P5>?, IParameterT<P6>?, IParameterT<P7>?, IParameterT<P8>?, IParameterT<P9>?, IParameterT<P10>?, IParameterT<P11>?, IParameterT<P12>?) -> Unit
      ): ISMethodDecl13<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> {
         val var3: ISMethodDecl13.CheckBuilder = `$this`.checkBuilder(config);
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
            var3.getP12()
         );
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> modelNoArg(
         `$this`: ISMethodDecl13<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl13CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>?) -> Unit
      ): ISMethodDecl13<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getArgumentCnt(
         `$this`: ISMethodDecl13<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
      ): Int? {
         return ISMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12> getActualType(
         `$this`: ISMethodDecl13<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12>
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
