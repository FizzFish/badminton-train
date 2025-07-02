package com.feysh.corax.config.api

import kotlin.reflect.KClass
import soot.SootField

public interface ILocalVarDecl<T> : IJDecl {
   public abstract fun atGet(config: (MethodConfig) -> Unit, block: (com.feysh.corax.config.api.ILocalVarDecl.IGet<Any>) -> Unit): ILocalVarDecl<Any> {
   }

   public abstract fun atSet(config: (MethodConfig) -> Unit, block: (com.feysh.corax.config.api.ILocalVarDecl.ISet<Any>, IParameterT<Any>) -> Unit): ILocalVarDecl<
         Any
      > {
   }

   public interface IBuilder<T> : IOperatorFactory {
      public val decl: ILocalVarDecl<Any>
      public val match: ILocalVarMatch
      public val config: (MethodConfig) -> Unit

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <T> plus(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> plus(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> minus(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> minus(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> startsWith(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> endsWith(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> contains(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> stringEquals(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> taintOf(`$this`: ILocalVarDeclIBuilder<T>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <T> getEmptyTaint(`$this`: ILocalVarDeclIBuilder<T>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <T> getEmptyVia(`$this`: ILocalVarDeclIBuilder<T>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <T> containsAll(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return IOperatorFactory.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <T, T1 extends R, T2 extends R, R> anyOr(`$this`: ILocalVarDeclIBuilder<T>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IOperatorFactory.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <T_I1, T> field(`$this`: ILocalVarDeclIBuilder<T_I1>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <T_I1, T, FieldType> field(
            `$this`: ILocalVarDeclIBuilder<T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <T_I1, T> field(
            `$this`: ILocalVarDeclIBuilder<T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <T> check(`$this`: ILocalVarDeclIBuilder<T>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IOperatorFactory.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   public interface IGet<T> : ILocalVarDecl.IBuilder<T> {
      public val `return`: IReturnT<Any>

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <T> plus(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> plus(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> minus(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> minus(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> startsWith(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> endsWith(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> contains(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> stringEquals(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> taintOf(`$this`: ILocalVarDeclIGet<T>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <T> getEmptyTaint(`$this`: ILocalVarDeclIGet<T>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <T> getEmptyVia(`$this`: ILocalVarDeclIGet<T>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <T> containsAll(`$this`: ILocalVarDeclIGet<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <T, T1 extends R, T2 extends R, R> anyOr(`$this`: ILocalVarDeclIGet<T>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return ILocalVarDecl.IBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <T_I1, T> field(`$this`: ILocalVarDeclIGet<T_I1>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return ILocalVarDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <T_I1, T, FieldType> field(
            `$this`: ILocalVarDeclIGet<T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ILocalVarDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <T_I1, T> field(
            `$this`: ILocalVarDeclIGet<T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ILocalVarDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <T> check(`$this`: ILocalVarDeclIGet<T>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            ILocalVarDecl.IBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   public interface ISet<T> : ILocalVarDecl.IBuilder<T> {
      public val value: IParameterT<Any>

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <T> plus(`$this`: ILocalVarDeclISet<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> plus(`$this`: ILocalVarDeclISet<T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> minus(`$this`: ILocalVarDeclISet<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> minus(`$this`: ILocalVarDeclISet<T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <T> startsWith(`$this`: ILocalVarDeclISet<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> endsWith(`$this`: ILocalVarDeclISet<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> contains(`$this`: ILocalVarDeclISet<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> stringEquals(`$this`: ILocalVarDeclISet<T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <T> taintOf(`$this`: ILocalVarDeclISet<T>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <T> getEmptyTaint(`$this`: ILocalVarDeclISet<T>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <T> getEmptyVia(`$this`: ILocalVarDeclISet<T>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ILocalVarDecl.IBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <T> containsAll(`$this`: ILocalVarDeclISet<T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return ILocalVarDecl.IBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <T, T1 extends R, T2 extends R, R> anyOr(`$this`: ILocalVarDeclISet<T>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return ILocalVarDecl.IBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <T_I1, T> field(`$this`: ILocalVarDeclISet<T_I1>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return ILocalVarDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <T_I1, T, FieldType> field(
            `$this`: ILocalVarDeclISet<T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ILocalVarDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <T_I1, T> field(
            `$this`: ILocalVarDeclISet<T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ILocalVarDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <T> check(`$this`: ILocalVarDeclISet<T>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            ILocalVarDecl.IBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }
}
