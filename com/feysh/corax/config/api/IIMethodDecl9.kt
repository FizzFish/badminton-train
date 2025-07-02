package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface IIMethodDecl9<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> : IIMethodDecl<R, This> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): IIMethodDecl9.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (
               IIMethodDecl9.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>,
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
   ): IIMethodDecl9<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public open fun modelNoArg(
      config: (MethodConfig) -> Unit = ...,
      block: (IIMethodDecl9.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>) -> Unit
   ): IIMethodDecl9<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
   }

   public interface CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> : IIMethodDecl.CheckBuilder<R, This> {
      public val method: IIMethodDecl9<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any, Any>

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


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP0(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP1(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP2(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P2> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP3(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P3> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP4(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P4> {
            return `$this`.paramAt(4);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP5(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P5> {
            return `$this`.paramAt(5);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP6(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P6> {
            return `$this`.paramAt(6);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP7(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P7> {
            return `$this`.paramAt(7);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getP8(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IParameterT<P8> {
            return `$this`.paramAt(8);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> plus(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> plus(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> minus(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> minus(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
            `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>,
            single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> startsWith(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> endsWith(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> contains(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> stringEquals(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>, `$receiver`: IStringExpr, str: java.lang.String
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> taintOf(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>, vararg type: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getEmptyTaint(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getEmptyVia(`$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> containsAll(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
            `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
            taint: ITaintType
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, T1 extends R, T2 extends R, R> anyOr(
            `$this`: IIMethodDecl9CheckBuilder<R_I1, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, T> field(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>, `$receiver`: ILocalT<T>, field: SootField
         ): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, T, FieldType> field(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8, T> field(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> check(
            `$this`: IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
            expr: ILocalT<java.lang.Boolean>,
            checkType: CheckType,
            env: (BugMessage.Env?) -> Unit
         ) {
            IIMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nInstanceMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstanceMethodInterface.kt\ncom/feysh/corax/config/api/IIMethodDecl9$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,582:1\n1#2:583\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> model(
         `$this`: IIMethodDecl9<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
         config: (MethodConfig?) -> Unit,
         block: (IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?, IParameterT<P4>?, IParameterT<P5>?, IParameterT<P6>?, IParameterT<P7>?, IParameterT<P8>?) -> Unit
      ): IIMethodDecl9<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> {
         val var3: IIMethodDecl9.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(var3, var3.getP0(), var3.getP1(), var3.getP2(), var3.getP3(), var3.getP4(), var3.getP5(), var3.getP6(), var3.getP7(), var3.getP8());
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> modelNoArg(
         `$this`: IIMethodDecl9<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
         config: (MethodConfig?) -> Unit,
         block: (IIMethodDecl9CheckBuilder<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>?) -> Unit
      ): IIMethodDecl9<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getArgumentCnt(`$this`: IIMethodDecl9<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): Int? {
         return IIMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8> getActualType(`$this`: IIMethodDecl9<R, This, P0, P1, P2, P3, P4, P5, P6, P7, P8>): KFunctionType? {
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
