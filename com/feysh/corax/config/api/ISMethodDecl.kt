package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.reflect.KClass
import soot.SootField

public interface ISMethodDecl<R> : IMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl.CheckBuilder<Any> {
   }

   public interface CheckBuilder<R> : IMethodDecl.CheckBuilder<R> {
      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R> plus(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> plus(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> startsWith(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> endsWith(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> contains(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> stringEquals(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> taintOf(`$this`: ISMethodDeclCheckBuilder<R>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R> getEmptyTaint(`$this`: ISMethodDeclCheckBuilder<R>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R> getEmptyVia(`$this`: ISMethodDeclCheckBuilder<R>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R> containsAll(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, T1 extends R, T2 extends R, R> anyOr(`$this`: ISMethodDeclCheckBuilder<R_I1>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, T> field(`$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, T, FieldType> field(
            `$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, T> field(
            `$this`: ISMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R> check(`$this`: ISMethodDeclCheckBuilder<R>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <R> getArgumentCnt(`$this`: ISMethodDecl<R>): Int? {
         return IMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R> getActualType(`$this`: ISMethodDecl<R>): KFunctionType? {
         return IMethodDecl.DefaultImpls.getActualType(`$this`);
      }
   }
}
