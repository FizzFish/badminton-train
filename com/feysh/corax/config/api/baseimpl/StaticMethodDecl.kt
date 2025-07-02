package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IAccessPathT
import com.feysh.corax.config.api.IAttribute
import com.feysh.corax.config.api.IBoolExpr
import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IIntExpr
import com.feysh.corax.config.api.ILocalT
import com.feysh.corax.config.api.ILocalValue
import com.feysh.corax.config.api.ILongExpr
import com.feysh.corax.config.api.IMethodDecl
import com.feysh.corax.config.api.IMethodMatch
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.IReturnT
import com.feysh.corax.config.api.ISMethodDecl
import com.feysh.corax.config.api.ISootLocalVarDecl
import com.feysh.corax.config.api.IStmt
import com.feysh.corax.config.api.IStringExpr
import com.feysh.corax.config.api.ITaintType
import com.feysh.corax.config.api.IViaType
import com.feysh.corax.config.api.IWithSubFieldsT
import com.feysh.corax.config.api.MethodConfig
import com.feysh.corax.config.api.TaintProperty
import com.feysh.corax.config.api.ViaProperty
import com.feysh.corax.config.api.AIAnalysisApi.Error
import com.feysh.corax.config.api.BugMessage.Env
import com.feysh.corax.config.api.IOperatorFactory.IAttributeGetSet
import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import soot.SootField

public class StaticMethodDecl<R>(delegate: IMethodDecl<Any>) : IMethodDecl<R>, ISMethodDecl<R> {
   private final val delegate: IMethodDecl<Any>

   public open val actualType: KFunctionType?
      public open get() {
         return this.delegate.getActualType();
      }


   public open val argumentCnt: Int?
      public open get() {
         return this.delegate.getArgumentCnt();
      }


   public open val error: Error
   public open val match: IMethodMatch

   init {
      this.delegate = delegate;
   }

   public override fun checkBuilder(config: (MethodConfig) -> Unit): com.feysh.corax.config.api.ISMethodDecl.CheckBuilder<Any> {
      return new StaticMethodDecl.CheckBuilder<>(this.delegate.checkBuilder(config));
   }

   public override fun eachLocalVar(block: (ISootLocalVarDecl<Any>) -> Unit) {
      this.delegate.eachLocalVar(block);
   }

   public class CheckBuilder<R>(delegate: IMethodDecl.CheckBuilder<Any>) : ISMethodDecl.CheckBuilder<R>, IMethodDecl.CheckBuilder<R> {
      private final val delegate: IMethodDecl.CheckBuilder<Any>
      public open val attr: IAttributeGetSet
      public open val config: (MethodConfig) -> Unit

      public open val emptyTaint: IAttribute<TaintProperty, Set<ITaintType>>
         public open get() {
            return this.delegate.getEmptyTaint();
         }


      public open val emptyVia: IAttribute<ViaProperty, Set<IViaType>>
         public open get() {
            return this.delegate.getEmptyVia();
         }


      public open val global: ILocalT<Any>
      public open val isConstant: IBoolExpr
      public open val isConstant: IBoolExpr
      public open val method: IMethodDecl<Any>
      public open val `return`: IReturnT<Any>
      public open val subFields: IWithSubFieldsT

      public open var taint: IAttribute<TaintProperty, Set<ITaintType>>
         internal final set

      public open var value: ILocalValue<T>
         internal final set

      public open var via: IAttribute<ViaProperty, Set<IViaType>>
         internal final set

      init {
         this.delegate = delegate;
      }

      public override fun <T : Any?> paramAt(index: Int): IParameterT<T> {
         return this.delegate.paramAt(index);
      }

      public override fun literal(string: String): IStringExpr {
         return this.delegate.literal(string);
      }

      public override fun literal(int: Int): IIntExpr {
         return this.delegate.literal(var1);
      }

      public override fun literal(long: Long): ILongExpr {
         return this.delegate.literal(var1);
      }

      public override fun literal(bool: Boolean): IBoolExpr {
         return this.delegate.literal(bool);
      }

      public override operator fun IBoolExpr.not(): IBoolExpr {
         return this.delegate.not(`$this$not`);
      }

      public override infix fun IBoolExpr.or(other: IBoolExpr): IBoolExpr {
         return this.delegate.or(`$this$or`, other);
      }

      public override infix fun IIntExpr.or(rhs: IIntExpr): IIntExpr {
         return this.delegate.or(`$this$or`, rhs);
      }

      public override infix fun IBoolExpr.and(other: IBoolExpr): IBoolExpr {
         return this.delegate.and(`$this$and`, other);
      }

      public override infix fun IIntExpr.and(rhs: IIntExpr): IIntExpr {
         return this.delegate.and(`$this$and`, rhs);
      }

      public override infix fun IIntExpr.lt(rhs: IIntExpr): IBoolExpr {
         return this.delegate.lt(`$this$lt`, rhs);
      }

      public override infix fun IIntExpr.le(rhs: IIntExpr): IBoolExpr {
         return this.delegate.le(`$this$le`, rhs);
      }

      public override infix fun IIntExpr.eq(rhs: IIntExpr): IBoolExpr {
         return this.delegate.eq(`$this$eq`, rhs);
      }

      public override infix fun IIntExpr.ge(rhs: IIntExpr): IBoolExpr {
         return this.delegate.ge(`$this$ge`, rhs);
      }

      public override infix fun IIntExpr.gt(rhs: IIntExpr): IBoolExpr {
         return this.delegate.gt(`$this$gt`, rhs);
      }

      public override infix fun IIntExpr.neq(rhs: IIntExpr): IBoolExpr {
         return this.delegate.neq(`$this$neq`, rhs);
      }

      public override infix fun IIntExpr.xor(rhs: IIntExpr): IIntExpr {
         return this.delegate.xor(`$this$xor`, rhs);
      }

      public override infix fun IIntExpr.shl(rhs: IIntExpr): IIntExpr {
         return this.delegate.shl(`$this$shl`, rhs);
      }

      public override infix fun IIntExpr.shr(rhs: IIntExpr): IIntExpr {
         return this.delegate.shr(`$this$shr`, rhs);
      }

      public override infix fun IIntExpr.lshr(rhs: IIntExpr): IIntExpr {
         return this.delegate.lshr(`$this$lshr`, rhs);
      }

      public override operator fun IIntExpr.plus(rhs: IIntExpr): IIntExpr {
         return this.delegate.plus(`$this$plus`, rhs);
      }

      public override infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.plus(set: IAttribute<T, V>): IAttribute<T, V> {
         return this.delegate.plus(`$this$plus`, set);
      }

      public override infix operator fun IAttribute<TaintProperty, Set<ITaintType>>.plus(single: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return this.delegate.plus(`$this$plus`, single);
      }

      public override infix operator fun IAttribute<ViaProperty, Set<IViaType>>.plus(single: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return this.delegate.plus(`$this$plus`, single);
      }

      public override operator fun IIntExpr.minus(rhs: IIntExpr): IIntExpr {
         return this.delegate.minus(`$this$minus`, rhs);
      }

      public override infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.minus(set: IAttribute<T, V>): IAttribute<T, V> {
         return this.delegate.minus(`$this$minus`, set);
      }

      public override infix operator fun IAttribute<TaintProperty, Set<ITaintType>>.minus(single: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return this.delegate.minus(`$this$minus`, single);
      }

      public override infix operator fun IAttribute<ViaProperty, Set<IViaType>>.minus(single: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return this.delegate.minus(`$this$minus`, single);
      }

      public override fun <T : Any?> ILocalT<T>.getBoolean(): IBoolExpr {
         return this.delegate.getBoolean(`$this$getBoolean`);
      }

      public override fun <T : Any?> ILocalT<T>.getString(): IStringExpr {
         return this.delegate.getString(`$this$getString`);
      }

      public override fun <T : Any?> ILocalT<T>.getInt(): IIntExpr {
         return this.delegate.getInt(`$this$getInt`);
      }

      public override fun <T : Any?> ILocalT<T>.getLong(): ILongExpr {
         return this.delegate.getLong(`$this$getLong`);
      }

      public override fun <T : Any?> ILocalT<T>.getEnumName(): IStringExpr {
         return this.delegate.getEnumName(`$this$getEnumName`);
      }

      public override fun <T : Any?> ILocalT<T>.isInstanceOf(parentType: String): IBoolExpr {
         return this.delegate.isInstanceOf(`$this$isInstanceOf`, parentType);
      }

      public override fun IStringExpr.toLowerCase(): IStringExpr {
         return this.delegate.toLowerCase(`$this$toLowerCase`);
      }

      public override fun IStringExpr.startsWith(str: IStringExpr): IBoolExpr {
         return this.delegate.startsWith(`$this$startsWith`, str);
      }

      public override fun IStringExpr.startsWith(str: String): IBoolExpr {
         return this.delegate.startsWith(`$this$startsWith`, str);
      }

      public override fun IStringExpr.endsWith(str: IStringExpr): IBoolExpr {
         return this.delegate.endsWith(`$this$endsWith`, str);
      }

      public override fun IStringExpr.endsWith(str: String): IBoolExpr {
         return this.delegate.endsWith(`$this$endsWith`, str);
      }

      public override fun IStringExpr.contains(str: IStringExpr): IBoolExpr {
         return this.delegate.contains(`$this$contains`, str);
      }

      public override fun IStringExpr.contains(str: String): IBoolExpr {
         return this.delegate.contains(`$this$contains`, str);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.contains(taint: ITaintType): IBoolExpr {
         return this.delegate.contains(`$this$contains`, taint);
      }

      public override fun IStringExpr.stringEquals(str: IStringExpr): IBoolExpr {
         return this.delegate.stringEquals(`$this$stringEquals`, str);
      }

      public override fun IStringExpr.stringEquals(str: String): IBoolExpr {
         return this.delegate.stringEquals(`$this$stringEquals`, str);
      }

      public override fun taintOf(vararg type: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return this.delegate.taintOf(type);
      }

      public override fun taintOf(types: Collection<ITaintType>): IAttribute<TaintProperty, Set<ITaintType>> {
         return this.delegate.taintOf(types);
      }

      public override fun viaOf(vararg via: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return this.delegate.viaOf(via);
      }

      public override fun <T : Any?> anyOf(vararg local: ILocalT<T>): ILocalValue<T> {
         return this.delegate.anyOf(local);
      }

      public override fun <T : Any?> `null`(): ILocalValue<T> {
         return this.delegate.null();
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.hasIntersection(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
         return this.delegate.hasIntersection(`$this$hasIntersection`, taint);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: ITaintType): IBoolExpr {
         return this.delegate.containsAll(`$this$containsAll`, taint);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
         return this.delegate.containsAll(`$this$containsAll`, taint);
      }

      public override infix fun <T1 : R, T2 : R, R : Any?> ILocalValue<T1>.anyOr(second: ILocalValue<T2>): ILocalValue<R> {
         return this.delegate.anyOr(`$this$anyOr`, second);
      }

      public override infix fun <T1 : R, T2 : R, R : Any?> ILocalT<T1>.anyOr(second: ILocalT<T2>): ILocalValue<R> {
         return this.delegate.anyOr(`$this$anyOr`, second);
      }

      public override fun <T : Any?> ILocalT<T>.field(declaringClass: String?, fieldName: String, fieldType: String?): IAccessPathT<Any> {
         return this.delegate.field(`$this$field`, declaringClass, fieldName, fieldType);
      }

      public override fun <T : Any?> ILocalT<T>.field(field: SootField): IAccessPathT<Any> {
         return this.delegate.field(`$this$field`, field);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(declaringClass: String?, fieldName: String, type: KClass<FieldType>): IAccessPathT<
            FieldType
         > {
         return this.delegate.field(`$this$field`, declaringClass, fieldName, type);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(declaringClass: KClass<*>?, fieldName: String, type: KClass<FieldType>): IAccessPathT<
            FieldType
         > {
         return this.delegate.field(`$this$field`, declaringClass, fieldName, type);
      }

      public override fun <T : Any?> ILocalT<T>.field(declaringClass: KClass<*>, fieldName: String, fieldType: String?): IAccessPathT<Any> {
         return this.delegate.field(`$this$field`, declaringClass, fieldName, fieldType);
      }

      public override fun <T : Any?> ILocalT<T>.field(field: IClassField): IAccessPathT<Any> {
         return this.delegate.field(`$this$field`, field);
      }

      public override fun <T : Any?, F : Any?> ILocalT<T>.field(field: KProperty<F>): IAccessPathT<F> {
         return this.delegate.field(`$this$field`, field);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(field: KProperty<*>, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return this.delegate.field(`$this$field`, field, type);
      }

      public override fun addStmt(stmt: IStmt) {
         this.delegate.addStmt(stmt);
      }

      public override fun check(expr: IBoolExpr, checkType: CheckType, env: (Env) -> Unit) {
         this.delegate.check(expr, checkType, env);
      }

      public override fun check(expr: ILocalT<Boolean>, checkType: CheckType, env: (Env) -> Unit) {
         this.delegate.check(expr, checkType, env);
      }

      public override fun eval(expr: IExpr, result: (Any) -> Unit) {
         this.delegate.eval(expr, result);
      }

      public override fun eval(expr: IBoolExpr, result: (Boolean) -> Unit) {
         this.delegate.eval(expr, result);
      }

      public override fun eval(expr: IStringExpr, result: (String) -> Unit) {
         this.delegate.eval(expr, result);
      }

      public override fun eval(expr: IIntExpr, result: (Int) -> Unit) {
         this.delegate.eval(expr, result);
      }
   }
}
