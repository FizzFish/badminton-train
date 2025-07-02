package com.feysh.corax.config.api

import kotlin.reflect.KClass
import soot.SootField

public interface IFieldDecl<This, T> : IJDecl {
   public abstract fun atGet(config: (MethodConfig) -> Unit = ..., block: (com.feysh.corax.config.api.IFieldDecl.IGet<Any, Any>) -> Unit): IFieldDecl<Any, Any> {
   }

   public abstract fun atSet(config: (MethodConfig) -> Unit = ..., block: (com.feysh.corax.config.api.IFieldDecl.ISet<Any, Any>, IParameterT<Any>) -> Unit): IFieldDecl<
         Any,
         Any
      > {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun `atGet$lambda$0`(var0: MethodConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `atSet$lambda$1`(var0: MethodConfig): Unit {
         return Unit.INSTANCE;
      }
   }

   public interface IBuilder<This, T> : IOperatorFactory {
      public val decl: IFieldDecl<Any, Any>
      public val `this`: IParameterT<Any>
      public val field: IAccessPathT<Any>
      public val match: IFieldMatch
      public val config: (MethodConfig) -> Unit

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <This, T> plus(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> plus(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> minus(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> minus(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> startsWith(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> endsWith(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> contains(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> stringEquals(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IOperatorFactory.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> taintOf(`$this`: IFieldDeclIBuilder<This, T>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <This, T> getEmptyTaint(`$this`: IFieldDeclIBuilder<This, T>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IOperatorFactory.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <This, T> getEmptyVia(`$this`: IFieldDeclIBuilder<This, T>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IOperatorFactory.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <This, T> containsAll(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return IOperatorFactory.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <This, T, T1 extends R, T2 extends R, R> anyOr(`$this`: IFieldDeclIBuilder<This, T>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IOperatorFactory.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <This, T_I1, T> field(`$this`: IFieldDeclIBuilder<This, T_I1>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <This, T_I1, T, FieldType> field(
            `$this`: IFieldDeclIBuilder<This, T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <This, T_I1, T> field(
            `$this`: IFieldDeclIBuilder<This, T_I1>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <This, T> check(`$this`: IFieldDeclIBuilder<This, T>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IOperatorFactory.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   public interface IGet<This, T> : IFieldDecl.IBuilder<This, T> {
      public val `return`: IReturnT<Any>

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <This, T_I1, T> field(`$this`: IFieldDeclIGet<This, T_I1>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IFieldDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <This, T_I1, T, FieldType> field(
            `$this`: IFieldDeclIGet<This, T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IFieldDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <This, T_I1, T> field(
            `$this`: IFieldDeclIGet<This, T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IFieldDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <This, T> plus(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IFieldDecl.IBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> plus(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IFieldDecl.IBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> minus(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IFieldDecl.IBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> minus(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IFieldDecl.IBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> startsWith(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> endsWith(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> contains(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> stringEquals(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> taintOf(`$this`: IFieldDeclIGet<This, T>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IFieldDecl.IBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <This, T> getEmptyTaint(`$this`: IFieldDeclIGet<This, T>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IFieldDecl.IBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <This, T> getEmptyVia(`$this`: IFieldDeclIGet<This, T>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IFieldDecl.IBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <This, T> containsAll(`$this`: IFieldDeclIGet<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <This, T, T1 extends R, T2 extends R, R> anyOr(`$this`: IFieldDeclIGet<This, T>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IFieldDecl.IBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <This, T> check(`$this`: IFieldDeclIGet<This, T>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IFieldDecl.IBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   public interface ISet<This, T> : IFieldDecl.IBuilder<This, T> {
      public val value: IParameterT<Any>

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <This, T_I1, T> field(`$this`: IFieldDeclISet<This, T_I1>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IFieldDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <This, T_I1, T, FieldType> field(
            `$this`: IFieldDeclISet<This, T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IFieldDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <This, T_I1, T> field(
            `$this`: IFieldDeclISet<This, T_I1>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IFieldDecl.IBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <This, T> plus(`$this`: IFieldDeclISet<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IFieldDecl.IBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> plus(`$this`: IFieldDeclISet<This, T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IFieldDecl.IBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> minus(`$this`: IFieldDeclISet<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IFieldDecl.IBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> minus(`$this`: IFieldDeclISet<This, T>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IFieldDecl.IBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <This, T> startsWith(`$this`: IFieldDeclISet<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> endsWith(`$this`: IFieldDeclISet<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> contains(`$this`: IFieldDeclISet<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> stringEquals(`$this`: IFieldDeclISet<This, T>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <This, T> taintOf(`$this`: IFieldDeclISet<This, T>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IFieldDecl.IBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <This, T> getEmptyTaint(`$this`: IFieldDeclISet<This, T>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IFieldDecl.IBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <This, T> getEmptyVia(`$this`: IFieldDeclISet<This, T>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IFieldDecl.IBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <This, T> containsAll(`$this`: IFieldDeclISet<This, T>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return IFieldDecl.IBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <This, T, T1 extends R, T2 extends R, R> anyOr(`$this`: IFieldDeclISet<This, T>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IFieldDecl.IBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <This, T> check(`$this`: IFieldDeclISet<This, T>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IFieldDecl.IBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }
}
