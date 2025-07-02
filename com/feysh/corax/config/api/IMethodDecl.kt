package com.feysh.corax.config.api

import com.feysh.corax.config.api.AIAnalysisApi.Error
import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.reflect.KClass
import soot.SootField

public interface IMethodDecl<R> : IJDecl {
   public val match: IMethodMatch
   public val error: Error

   public open val argumentCnt: Int?
      public open get() {
      }


   public open val actualType: KFunctionType?
      public open get() {
      }


   public abstract fun eachLocalVar(block: (ISootLocalVarDecl<Any>) -> Unit) {
   }

   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): com.feysh.corax.config.api.IMethodDecl.CheckBuilder<Any> {
   }

   public interface CheckBuilder<R> : IOperatorFactory {
      public val method: IMethodDecl<Any>
      public val config: (MethodConfig) -> Unit
      public val global: ILocalT<Any>
      public val `return`: IReturnT<Any>

      public abstract fun <T> paramAt(index: Int): IParameterT<T> {
      }

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R> plus(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> plus(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> startsWith(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> endsWith(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> contains(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> stringEquals(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> taintOf(`$this`: IMethodDeclCheckBuilder<R>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R> getEmptyTaint(`$this`: IMethodDeclCheckBuilder<R>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R> getEmptyVia(`$this`: IMethodDeclCheckBuilder<R>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R> containsAll(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return IOperatorFactory.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, T1 extends R, T2 extends R, R> anyOr(`$this`: IMethodDeclCheckBuilder<R_I1>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IOperatorFactory.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, T> field(`$this`: IMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, T, FieldType> field(
            `$this`: IMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, T> field(
            `$this`: IMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R> check(`$this`: IMethodDeclCheckBuilder<R>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IOperatorFactory.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <R> getArgumentCnt(`$this`: IMethodDecl<R>): Int? {
         return `$this`.getMatch().getArgumentCnt();
      }

      @JvmStatic
      fun <R> getActualType(`$this`: IMethodDecl<R>): KFunctionType? {
         return `$this`.getMatch().getActualType();
      }
   }
}
