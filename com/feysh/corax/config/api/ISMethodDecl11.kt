package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface ISMethodDecl11<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> : ISMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl11.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (
               ISMethodDecl11.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>,
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
   ): ISMethodDecl11<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(
      config: (MethodConfig) -> Unit = ...,
      block: (ISMethodDecl11.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>) -> Unit
   ): ISMethodDecl11<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public interface CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> : ISMethodDecl.CheckBuilder<R> {
      public val method: ISMethodDecl11<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>

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


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP0(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP1(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP2(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP3(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P3> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP4(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P4> {
            return `$this`.paramAt(4);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP5(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P5> {
            return `$this`.paramAt(5);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP6(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P6> {
            return `$this`.paramAt(6);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP7(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P7> {
            return `$this`.paramAt(7);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP8(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P8> {
            return `$this`.paramAt(8);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP9(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P9> {
            return `$this`.paramAt(9);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getP10(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IParameterT<P10> {
            return `$this`.paramAt(10);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> plus(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> plus(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> minus(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> minus(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> startsWith(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> endsWith(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> contains(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> stringEquals(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> taintOf(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>, vararg type: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getEmptyTaint(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getEmptyVia(`$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> containsAll(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            taint: ITaintType
         ): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, T1 extends R, T2 extends R, R> anyOr(
            `$this`: ISMethodDecl11CheckBuilder<R_I1, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, T> field(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>, `$receiver`: ILocalT<T>, field: SootField
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, T, FieldType> field(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, T> field(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> check(
            `$this`: ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
            expr: ILocalT<java.lang.Boolean>,
            checkType: CheckType,
            env: (BugMessage.Env?) -> Unit
         ) {
            ISMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nStaticMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StaticMethodInterface.kt\ncom/feysh/corax/config/api/ISMethodDecl11$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,577:1\n1#2:578\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> model(
         `$this`: ISMethodDecl11<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?, IParameterT<P4>?, IParameterT<P5>?, IParameterT<P6>?, IParameterT<P7>?, IParameterT<P8>?, IParameterT<P9>?, IParameterT<P10>?) -> Unit
      ): ISMethodDecl11<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> {
         val var3: ISMethodDecl11.CheckBuilder = `$this`.checkBuilder(config);
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
            var3.getP10()
         );
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> modelNoArg(
         `$this`: ISMethodDecl11<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>,
         config: (MethodConfig?) -> Unit,
         block: (ISMethodDecl11CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>?) -> Unit
      ): ISMethodDecl11<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getArgumentCnt(`$this`: ISMethodDecl11<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): Int? {
         return ISMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10> getActualType(`$this`: ISMethodDecl11<R, P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10>): KFunctionType? {
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
