package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.reflect.KClass
import soot.SootField

public interface IIMethodDecl<R, This> : IMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): IIMethodDecl.CheckBuilder<Any, Any> {
   }

   public interface CheckBuilder<R, This> : IMethodDecl.CheckBuilder<R> {
      public val `this`: IParameterT<Any>

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R, This> plus(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This> plus(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This> minus(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This> minus(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R, This> startsWith(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This> endsWith(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This> contains(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This> stringEquals(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R, This> taintOf(`$this`: IIMethodDeclCheckBuilder<R, This>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R, This> getEmptyTaint(`$this`: IIMethodDeclCheckBuilder<R, This>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R, This> getEmptyVia(`$this`: IIMethodDeclCheckBuilder<R, This>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R, This> containsAll(
            `$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType
         ): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, This, T1 extends R, T2 extends R, R> anyOr(`$this`: IIMethodDeclCheckBuilder<R_I1, This>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, This, T> field(`$this`: IIMethodDeclCheckBuilder<R, This>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, This, T, FieldType> field(
            `$this`: IIMethodDeclCheckBuilder<R, This>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>?,
            fieldName: java.lang.String,
            type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, This, T> field(
            `$this`: IIMethodDeclCheckBuilder<R, This>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R, This> check(`$this`: IIMethodDeclCheckBuilder<R, This>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <R, This> getArgumentCnt(`$this`: IIMethodDecl<R, This>): Int? {
         return IMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R, This> getActualType(`$this`: IIMethodDecl<R, This>): KFunctionType? {
         return IMethodDecl.DefaultImpls.getActualType(`$this`);
      }
   }
}
