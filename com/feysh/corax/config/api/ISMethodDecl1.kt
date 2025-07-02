package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface ISMethodDecl1<R, P0> : ISMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl1.CheckBuilder<Any, Any> {
   }

   public open fun model(config: (MethodConfig) -> Unit = ..., block: (ISMethodDecl1.CheckBuilder<Any, Any>, IParameterT<Any>) -> Unit): ISMethodDecl1<
         Any,
         Any
      > {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (ISMethodDecl1.CheckBuilder<Any, Any>) -> Unit): ISMethodDecl1<Any, Any> {
   }

   public interface CheckBuilder<R, P0> : ISMethodDecl.CheckBuilder<R> {
      public val method: ISMethodDecl1<Any, Any>

      public open val p0: IParameterT<Any>
         public open get() {
         }


      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, P0> getP0(`$this`: ISMethodDecl1CheckBuilder<R, P0>): IParameterT<P0> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R, P0> plus(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0> plus(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0> minus(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0> minus(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, P0> startsWith(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0> endsWith(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0> contains(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0> stringEquals(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, P0> taintOf(`$this`: ISMethodDecl1CheckBuilder<R, P0>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, P0> getEmptyTaint(`$this`: ISMethodDecl1CheckBuilder<R, P0>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, P0> getEmptyVia(`$this`: ISMethodDecl1CheckBuilder<R, P0>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, P0> containsAll(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, P0, T1 extends R, T2 extends R, R> anyOr(`$this`: ISMethodDecl1CheckBuilder<R_I1, P0>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, P0, T> field(`$this`: ISMethodDecl1CheckBuilder<R, P0>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, P0, T, FieldType> field(
            `$this`: ISMethodDecl1CheckBuilder<R, P0>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, P0, T> field(
            `$this`: ISMethodDecl1CheckBuilder<R, P0>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, P0> check(`$this`: ISMethodDecl1CheckBuilder<R, P0>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            ISMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nStaticMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StaticMethodInterface.kt\ncom/feysh/corax/config/api/ISMethodDecl1$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,577:1\n1#2:578\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R, P0> model(`$this`: ISMethodDecl1<R, P0>, config: (MethodConfig?) -> Unit, block: (ISMethodDecl1CheckBuilder<R, P0>?, IParameterT<P0>?) -> Unit): ISMethodDecl1<R, P0> {
         val var3: ISMethodDecl1.CheckBuilder = `$this`.checkBuilder(config);
         block.invoke(var3, var3.getP0());
         return `$this`;
      }

      @JvmStatic
      fun <R, P0> modelNoArg(`$this`: ISMethodDecl1<R, P0>, config: (MethodConfig?) -> Unit, block: (ISMethodDecl1CheckBuilder<R, P0>?) -> Unit): ISMethodDecl1<R, P0> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R, P0> getArgumentCnt(`$this`: ISMethodDecl1<R, P0>): Int? {
         return ISMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, P0> getActualType(`$this`: ISMethodDecl1<R, P0>): KFunctionType? {
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
