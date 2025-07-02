package com.feysh.corax.config.api

import com.feysh.corax.config.api.BugMessage.Env
import com.feysh.corax.config.api.utils.UtilsKt
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import soot.SootField
import soot.Type

public interface IOperatorFactory {
   public val subFields: IWithSubFieldsT
   public val attr: com.feysh.corax.config.api.IOperatorFactory.IAttributeGetSet

   public var taint: IAttribute<TaintProperty, Set<ITaintType>>
      internal final set

   public var via: IAttribute<ViaProperty, Set<IViaType>>
      internal final set

   public var value: ILocalValue<T>
      internal final set

   public val isConstant: IBoolExpr
   public val isConstant: IBoolExpr

   public open val emptyTaint: IAttribute<TaintProperty, Set<ITaintType>>
      public open get() {
      }


   public open val emptyVia: IAttribute<ViaProperty, Set<IViaType>>
      public open get() {
      }


   public abstract fun literal(string: String): IStringExpr {
   }

   public abstract fun literal(int: Int): IIntExpr {
   }

   public abstract fun literal(long: Long): ILongExpr {
   }

   public abstract fun literal(bool: Boolean): IBoolExpr {
   }

   public abstract operator fun IBoolExpr.not(): IBoolExpr {
   }

   public abstract infix fun IBoolExpr.or(other: IBoolExpr): IBoolExpr {
   }

   public abstract infix fun IBoolExpr.and(other: IBoolExpr): IBoolExpr {
   }

   public abstract infix fun IIntExpr.lt(rhs: IIntExpr): IBoolExpr {
   }

   public abstract infix fun IIntExpr.le(rhs: IIntExpr): IBoolExpr {
   }

   public abstract infix fun IIntExpr.eq(rhs: IIntExpr): IBoolExpr {
   }

   public abstract infix fun IIntExpr.ge(rhs: IIntExpr): IBoolExpr {
   }

   public abstract infix fun IIntExpr.gt(rhs: IIntExpr): IBoolExpr {
   }

   public abstract infix fun IIntExpr.neq(rhs: IIntExpr): IBoolExpr {
   }

   public abstract infix fun IIntExpr.and(rhs: IIntExpr): IIntExpr {
   }

   public abstract infix fun IIntExpr.or(rhs: IIntExpr): IIntExpr {
   }

   public abstract infix fun IIntExpr.xor(rhs: IIntExpr): IIntExpr {
   }

   public abstract infix fun IIntExpr.shl(rhs: IIntExpr): IIntExpr {
   }

   public abstract infix fun IIntExpr.shr(rhs: IIntExpr): IIntExpr {
   }

   public abstract infix fun IIntExpr.lshr(rhs: IIntExpr): IIntExpr {
   }

   public abstract operator fun IIntExpr.plus(rhs: IIntExpr): IIntExpr {
   }

   public abstract operator fun IIntExpr.minus(rhs: IIntExpr): IIntExpr {
   }

   public abstract fun <T> ILocalT<T>.getBoolean(): IBoolExpr {
   }

   public abstract fun <T> ILocalT<T>.getString(): IStringExpr {
   }

   public abstract fun <T> ILocalT<T>.getInt(): IIntExpr {
   }

   public abstract fun <T> ILocalT<T>.getLong(): ILongExpr {
   }

   public abstract fun <T> ILocalT<T>.getEnumName(): IStringExpr {
   }

   public abstract fun <T> ILocalT<T>.isInstanceOf(parentType: String): IBoolExpr {
   }

   public abstract fun IStringExpr.toLowerCase(): IStringExpr {
   }

   public abstract fun IStringExpr.startsWith(str: IStringExpr): IBoolExpr {
   }

   public open fun IStringExpr.startsWith(str: String): IBoolExpr {
   }

   public abstract fun IStringExpr.endsWith(str: IStringExpr): IBoolExpr {
   }

   public open fun IStringExpr.endsWith(str: String): IBoolExpr {
   }

   public abstract fun IStringExpr.contains(str: IStringExpr): IBoolExpr {
   }

   public open fun IStringExpr.contains(str: String): IBoolExpr {
   }

   public abstract fun IStringExpr.stringEquals(str: IStringExpr): IBoolExpr {
   }

   public open fun IStringExpr.stringEquals(str: String): IBoolExpr {
   }

   public open fun taintOf(vararg type: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
   }

   public abstract fun taintOf(types: Collection<ITaintType>): IAttribute<TaintProperty, Set<ITaintType>> {
   }

   public abstract fun viaOf(vararg via: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
   }

   public abstract fun <T> anyOf(vararg local: ILocalT<T>): ILocalValue<T> {
   }

   public abstract fun <T> `null`(): ILocalValue<T> {
   }

   public abstract fun IAttribute<TaintProperty, Set<ITaintType>>.hasIntersection(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
   }

   public abstract fun IAttribute<TaintProperty, Set<ITaintType>>.contains(taint: ITaintType): IBoolExpr {
   }

   public open fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: ITaintType): IBoolExpr {
   }

   public abstract fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
   }

   public abstract infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.plus(set: IAttribute<T, V>): IAttribute<T, V> {
   }

   public abstract infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.minus(set: IAttribute<T, V>): IAttribute<T, V> {
   }

   public open infix operator fun IAttribute<TaintProperty, Set<ITaintType>>.plus(single: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
   }

   public open infix operator fun IAttribute<TaintProperty, Set<ITaintType>>.minus(single: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
   }

   public open infix operator fun IAttribute<ViaProperty, Set<IViaType>>.plus(single: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
   }

   public open infix operator fun IAttribute<ViaProperty, Set<IViaType>>.minus(single: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
   }

   public abstract infix fun <T1 : R, T2 : R, R> ILocalValue<T1>.anyOr(second: ILocalValue<T2>): ILocalValue<R> {
   }

   public open infix fun <T1 : R, T2 : R, R> ILocalT<T1>.anyOr(second: ILocalT<T2>): ILocalValue<R> {
   }

   public abstract fun <T> ILocalT<T>.field(declaringClass: String?, fieldName: String, fieldType: String? = ...): IAccessPathT<Any> {
   }

   public open fun <T> ILocalT<T>.field(field: SootField): IAccessPathT<Any> {
   }

   public abstract fun <T, FieldType : Any> ILocalT<T>.field(declaringClass: String?, fieldName: String, type: KClass<FieldType>): IAccessPathT<FieldType> {
   }

   public open fun <T, FieldType : Any> ILocalT<T>.field(declaringClass: KClass<*>?, fieldName: String, type: KClass<FieldType>): IAccessPathT<FieldType> {
   }

   public open fun <T> ILocalT<T>.field(declaringClass: KClass<*>, fieldName: String, fieldType: String? = ...): IAccessPathT<Any> {
   }

   public abstract fun <T> ILocalT<T>.field(field: IClassField): IAccessPathT<Any> {
   }

   public abstract fun <T, F> ILocalT<T>.field(field: KProperty<F>): IAccessPathT<F> {
   }

   public abstract fun <T, FieldType : Any> ILocalT<T>.field(field: KProperty<*>, type: KClass<FieldType>): IAccessPathT<FieldType> {
   }

   public abstract fun addStmt(stmt: IStmt) {
   }

   public abstract fun check(expr: IBoolExpr, checkType: CheckType, env: (Env) -> Unit = ...) {
   }

   public open fun check(expr: ILocalT<Boolean>, checkType: CheckType, env: (Env) -> Unit = ...) {
   }

   public abstract fun eval(expr: IExpr, result: (Any) -> Unit) {
   }

   public abstract fun eval(expr: IBoolExpr, result: (Boolean) -> Unit) {
   }

   public abstract fun eval(expr: IStringExpr, result: (String) -> Unit) {
   }

   public abstract fun eval(expr: IIntExpr, result: (Int) -> Unit) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun startsWith(`$this`: IOperatorFactory, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
         return `$this`.startsWith(`$receiver`, `$this`.literal(str));
      }

      @JvmStatic
      fun endsWith(`$this`: IOperatorFactory, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
         return `$this`.endsWith(`$receiver`, `$this`.literal(str));
      }

      @JvmStatic
      fun contains(`$this`: IOperatorFactory, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
         return `$this`.contains(`$receiver`, `$this`.literal(str));
      }

      @JvmStatic
      fun stringEquals(`$this`: IOperatorFactory, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
         return `$this`.stringEquals(`$receiver`, `$this`.literal(str));
      }

      @JvmStatic
      fun taintOf(`$this`: IOperatorFactory, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return `$this`.taintOf(ArraysKt.asList(type));
      }

      @JvmStatic
      fun getEmptyTaint(`$this`: IOperatorFactory): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return `$this`.taintOf();
      }

      @JvmStatic
      fun getEmptyVia(`$this`: IOperatorFactory): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return `$this`.viaOf();
      }

      @JvmStatic
      fun containsAll(`$this`: IOperatorFactory, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
         return `$this`.containsAll(`$receiver`, `$this`.taintOf(taint));
      }

      @JvmStatic
      fun plus(`$this`: IOperatorFactory, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return `$this`.plus(`$receiver`, `$this`.taintOf(single));
      }

      @JvmStatic
      fun minus(`$this`: IOperatorFactory, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return `$this`.minus(`$receiver`, `$this`.taintOf(single));
      }

      @JvmStatic
      fun plus(`$this`: IOperatorFactory, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return `$this`.plus(`$receiver`, `$this`.viaOf(single));
      }

      @JvmStatic
      fun minus(`$this`: IOperatorFactory, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return `$this`.minus(`$receiver`, `$this`.viaOf(single));
      }

      @JvmStatic
      fun <T1 extends R, T2 extends R, R> anyOr(`$this`: IOperatorFactory, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
         return `$this`.anyOr(`$this`.getValue(`$receiver`), `$this`.getValue(second));
      }

      @JvmStatic
      fun <T> field(`$this`: IOperatorFactory, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
         val var10002: java.lang.String = field.getDeclaringClass().getName();
         val var10003: java.lang.String = field.getName();
         val var10004: Type = field.getType();
         return `$this`.field(`$receiver`, var10002, var10003, UtilsKt.getTypename(var10004));
      }

      @JvmStatic
      fun <T, FieldType> field(
         `$this`: IOperatorFactory, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
      ): IAccessPathT<FieldType> {
         return `$this`.field(`$receiver`, if (declaringClass != null) UtilsKt.getSootTypeName(declaringClass) else null, fieldName, type);
      }

      @JvmStatic
      fun <T> field(`$this`: IOperatorFactory, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?): IAccessPathT<Object> {
         return `$this`.field(`$receiver`, UtilsKt.getSootTypeName(declaringClass), fieldName, fieldType);
      }

      @JvmStatic
      fun check(`$this`: IOperatorFactory, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
         `$this`.check(`$this`.getBoolean(expr), checkType, env);
      }

      @JvmStatic
      fun `check$lambda$0`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `check$lambda$1`(var0: BugMessage.Env): Unit {
         return Unit.INSTANCE;
      }
   }

   public interface IAttributeGetSet {
      public abstract operator fun <T> get(id: CustomAttributeID<T>): IAccessPathT<T> {
      }

      public abstract operator fun <T> set(id: CustomAttributeID<T>, value: ILocalValue<T>) {
      }

      public abstract operator fun set(id: CustomAttributeID<Boolean>, value: Boolean) {
      }

      public abstract operator fun set(id: CustomAttributeID<Short>, value: Short) {
      }

      public abstract operator fun set(id: CustomAttributeID<Int>, value: Int) {
      }

      public abstract operator fun set(id: CustomAttributeID<Long>, value: Long) {
      }

      public abstract operator fun set(id: CustomAttributeID<String>, value: String) {
      }

      public abstract operator fun <T> set(id: CustomAttributeID<T>, value: ITypedExpr) {
      }
   }
}
