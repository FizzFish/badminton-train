package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface IIMethodDecl2<R, This, P0, P1> : IIMethodDecl<R, This> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): IIMethodDecl2.CheckBuilder<Any, Any, Any, Any> {
   }

   public open fun model(
      config: (MethodConfig) -> Unit = ...,
      block: (IIMethodDecl2.CheckBuilder<Any, Any, Any, Any>, IParameterT<Any>, IParameterT<Any>) -> Unit
   ): IIMethodDecl2<Any, Any, Any, Any> {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (IIMethodDecl2.CheckBuilder<Any, Any, Any, Any>) -> Unit): IIMethodDecl2<
         Any,
         Any,
         Any,
         Any
      > {
   }

   public interface CheckBuilder<R, This, P0, P1> : IIMethodDecl.CheckBuilder<R, This> {
      public val method: IIMethodDecl2<Any, Any, Any, Any>

      public open val p0: IParameterT<Any>
         public open get() {
         }


      public open val p1: IParameterT<Any>
         public open get() {
         }


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, This, P0, P1> getP0(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, This, P0, P1> getP1(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>): IParameterT<P1> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R, This, P0, P1> plus(
            `$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1> plus(
            `$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1> minus(
            `$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType
         ): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1> minus(
            `$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType
         ): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This, P0, P1> startsWith(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1> endsWith(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1> contains(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1> stringEquals(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This, P0, P1> taintOf(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, This, P0, P1> getEmptyTaint(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1> getEmptyVia(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, This, P0, P1> containsAll(
            `$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType
         ): IBoolExpr {
            return IIMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, This, P0, P1, T1 extends R, T2 extends R, R> anyOr(
            `$this`: IIMethodDecl2CheckBuilder<R_I1, This, P0, P1>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>
         ): ILocalValue<R> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, This, P0, P1, T> field(`$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, This, P0, P1, T, FieldType> field(
            `$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, This, P0, P1, T> field(
            `$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IIMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, This, P0, P1> check(
            `$this`: IIMethodDecl2CheckBuilder<R, This, P0, P1>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit
         ) {
            IIMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nInstanceMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InstanceMethodInterface.kt\ncom/feysh/corax/config/api/IIMethodDecl2$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,582:1\n1#2:583\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, This, P0, P1> model(
         `$this`: IIMethodDecl2<R, This, P0, P1>,
         config: (MethodConfig?) -> Unit,
         block: (IIMethodDecl2CheckBuilder<R, This, P0, P1>?, IParameterT<P0>?, IParameterT<P1>?) -> Unit
      ): IIMethodDecl2<R, This, P0, P1> {
         val var3: IIMethodDecl2.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(var3, var3.getP0(), var3.getP1());
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1> modelNoArg(
         `$this`: IIMethodDecl2<R, This, P0, P1>, config: (MethodConfig?) -> Unit, block: (IIMethodDecl2CheckBuilder<R, This, P0, P1>?) -> Unit
      ): IIMethodDecl2<R, This, P0, P1> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, This, P0, P1> getArgumentCnt(`$this`: IIMethodDecl2<R, This, P0, P1>): Int? {
         return IIMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, This, P0, P1> getActualType(`$this`: IIMethodDecl2<R, This, P0, P1>): KFunctionType? {
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
