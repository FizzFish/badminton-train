package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface IIMethodDecl8<R, This, P0, P1, P2, P3, P4, P5, P6, P7> : IIMethodDecl<R, This> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): IIMethodDecl8.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (
               IIMethodDecl8.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>,
               IParameterT<Any>
            ) -> Unit
   ): IIMethodDecl8<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(
      config: (MethodConfig) -> Unit = ...,
      block: (IIMethodDecl8.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>) -> Unit
   ): IIMethodDecl8<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public interface CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7> : IIMethodDecl.CheckBuilder<R, This> {
      public val method: IIMethodDecl8<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>

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
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getP0(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getP1(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getP2(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getP3(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P3> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getP4(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P4> {
            return `$this`.paramAt(4);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getP5(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P5> {
            return `$this`.paramAt(5);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getP6(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P6> {
            return `$this`.paramAt(6);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getP7(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IParameterT<P7> {
            return `$this`.paramAt(7);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> plus(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> plus(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> minus(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> minus(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> startsWith(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> endsWith(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> contains(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> stringEquals(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> taintOf(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>, vararg type: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getEmptyTaint(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getEmptyVia(`$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> containsAll(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            taint: ITaintType
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, This, P0, P1, P2, P3, P4, P5, P6, P7, T1 extends R, T2 extends R, R> anyOr(
            `$this`: IIMethodDecl8CheckBuilder<R_I1, This, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, T> field(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>, `$receiver`: ILocalT<T>, field: SootField
         ): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, T, FieldType> field(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, T> field(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> check(
            `$this`: IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
            expr: ILocalT<java.lang.Boolean>,
            checkType: CheckType,
            env: (BugMessage.Env?) -> Unit
         ) {
            IIMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nInstanceMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstanceMethodInterface.kt\ncom/feysh/corax/config/api/IIMethodDecl8$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,582:1\n1#2:583\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> model(
         `$this`: IIMethodDecl8<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
         config: (MethodConfig?) -> Unit,
         block: (IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?, IParameterT<P4>?, IParameterT<P5>?, IParameterT<P6>?, IParameterT<P7>?) -> Unit
      ): IIMethodDecl8<R, This, P0, P1, P2, P3, P4, P5, P6, P7> {
         val var3: IIMethodDecl8.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(var3, var3.getP0(), var3.getP1(), var3.getP2(), var3.getP3(), var3.getP4(), var3.getP5(), var3.getP6(), var3.getP7());
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> modelNoArg(
         `$this`: IIMethodDecl8<R, This, P0, P1, P2, P3, P4, P5, P6, P7>,
         config: (MethodConfig?) -> Unit,
         block: (IIMethodDecl8CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7>?) -> Unit
      ): IIMethodDecl8<R, This, P0, P1, P2, P3, P4, P5, P6, P7> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getArgumentCnt(`$this`: IIMethodDecl8<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): Int? {
         return IIMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7> getActualType(`$this`: IIMethodDecl8<R, This, P0, P1, P2, P3, P4, P5, P6, P7>): KFunctionType? {
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
