package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IAccessPathT
import com.feysh.corax.config.api.IAttribute
import com.feysh.corax.config.api.IBoolExpr
import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IIMethodDecl
import com.feysh.corax.config.api.IIntExpr
import com.feysh.corax.config.api.ILocalT
import com.feysh.corax.config.api.ILocalValue
import com.feysh.corax.config.api.ILongExpr
import com.feysh.corax.config.api.IMethodDecl
import com.feysh.corax.config.api.IMethodMatch
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.IReturnT
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

public class InstanceMethodDecl<R, This>(delegate: IMethodDecl<Any>) : IMethodDecl<R>, IIMethodDecl<R, This> {
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

   public override fun checkBuilder(config: (MethodConfig) -> Unit): com.feysh.corax.config.api.IIMethodDecl.CheckBuilder<Any, Any> {
      return new InstanceMethodDecl.CheckBuilder<>(this.delegate.checkBuilder(config));
   }

   public override fun eachLocalVar(block: (ISootLocalVarDecl<Any>) -> Unit) {
      this.delegate.eachLocalVar(block);
   }

   public class CheckBuilder<R, This>(delegate: IMethodDecl.CheckBuilder<Any>) : IIMethodDecl.CheckBuilder<R, This>, IMethodDecl.CheckBuilder<R> {
      private final val delegate: IMethodDecl.CheckBuilder<Any>
      public open val `this`: IParameterT<Any>
      public open val attr: IAttributeGetSet
      public open val config: (MethodConfig) -> Unit

      public open val emptyTaint: IAttribute<TaintProperty, Set<ITaintType>>
         public open get() {
            return thisx.delegate.getEmptyTaint();
         }


      public open val emptyVia: IAttribute<ViaProperty, Set<IViaType>>
         public open get() {
            return thisx.delegate.getEmptyVia();
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
         thisx.delegate = delegate;
         thisx.this = thisx.delegate.paramAt(-1);
      }

      public override fun <T : Any?> paramAt(index: Int): IParameterT<T> {
         return thisx.delegate.paramAt(index);
      }

      public override fun literal(string: String): IStringExpr {
         return thisx.delegate.literal(string);
      }

      public override fun literal(int: Int): IIntExpr {
         return thisx.delegate.literal(var1);
      }

      public override fun literal(long: Long): ILongExpr {
         return thisx.delegate.literal(var1);
      }

      public override fun literal(bool: Boolean): IBoolExpr {
         return thisx.delegate.literal(bool);
      }

      public override operator fun IBoolExpr.not(): IBoolExpr {
         return thisx.delegate.not(`$this$not`);
      }

      public override infix fun IBoolExpr.or(other: IBoolExpr): IBoolExpr {
         return thisx.delegate.or(`$this$or`, other);
      }

      public override infix fun IIntExpr.or(rhs: IIntExpr): IIntExpr {
         return thisx.delegate.or(`$this$or`, rhs);
      }

      public override infix fun IBoolExpr.and(other: IBoolExpr): IBoolExpr {
         return thisx.delegate.and(`$this$and`, other);
      }

      public override infix fun IIntExpr.and(rhs: IIntExpr): IIntExpr {
         return thisx.delegate.and(`$this$and`, rhs);
      }

      public override infix fun IIntExpr.lt(rhs: IIntExpr): IBoolExpr {
         return thisx.delegate.lt(`$this$lt`, rhs);
      }

      public override infix fun IIntExpr.le(rhs: IIntExpr): IBoolExpr {
         return thisx.delegate.le(`$this$le`, rhs);
      }

      public override infix fun IIntExpr.eq(rhs: IIntExpr): IBoolExpr {
         return thisx.delegate.eq(`$this$eq`, rhs);
      }

      public override infix fun IIntExpr.ge(rhs: IIntExpr): IBoolExpr {
         return thisx.delegate.ge(`$this$ge`, rhs);
      }

      public override infix fun IIntExpr.gt(rhs: IIntExpr): IBoolExpr {
         return thisx.delegate.gt(`$this$gt`, rhs);
      }

      public override infix fun IIntExpr.neq(rhs: IIntExpr): IBoolExpr {
         return thisx.delegate.neq(`$this$neq`, rhs);
      }

      public override infix fun IIntExpr.xor(rhs: IIntExpr): IIntExpr {
         return thisx.delegate.xor(`$this$xor`, rhs);
      }

      public override infix fun IIntExpr.shl(rhs: IIntExpr): IIntExpr {
         return thisx.delegate.shl(`$this$shl`, rhs);
      }

      public override infix fun IIntExpr.shr(rhs: IIntExpr): IIntExpr {
         return thisx.delegate.shr(`$this$shr`, rhs);
      }

      public override infix fun IIntExpr.lshr(rhs: IIntExpr): IIntExpr {
         return thisx.delegate.lshr(`$this$lshr`, rhs);
      }

      public override operator fun IIntExpr.plus(rhs: IIntExpr): IIntExpr {
         return thisx.delegate.plus(`$this$plus`, rhs);
      }

      public override infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.plus(set: IAttribute<T, V>): IAttribute<T, V> {
         return thisx.delegate.plus(`$this$plus`, set);
      }

      public override infix operator fun IAttribute<TaintProperty, Set<ITaintType>>.plus(single: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return thisx.delegate.plus(`$this$plus`, single);
      }

      public override infix operator fun IAttribute<ViaProperty, Set<IViaType>>.plus(single: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return thisx.delegate.plus(`$this$plus`, single);
      }

      public override operator fun IIntExpr.minus(rhs: IIntExpr): IIntExpr {
         return thisx.delegate.minus(`$this$minus`, rhs);
      }

      public override infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.minus(set: IAttribute<T, V>): IAttribute<T, V> {
         return thisx.delegate.minus(`$this$minus`, set);
      }

      public override infix operator fun IAttribute<TaintProperty, Set<ITaintType>>.minus(single: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return thisx.delegate.minus(`$this$minus`, single);
      }

      public override infix operator fun IAttribute<ViaProperty, Set<IViaType>>.minus(single: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return thisx.delegate.minus(`$this$minus`, single);
      }

      public override fun <T : Any?> ILocalT<T>.getBoolean(): IBoolExpr {
         return thisx.delegate.getBoolean(`$this$getBoolean`);
      }

      public override fun <T : Any?> ILocalT<T>.getString(): IStringExpr {
         return thisx.delegate.getString(`$this$getString`);
      }

      public override fun <T : Any?> ILocalT<T>.getInt(): IIntExpr {
         return thisx.delegate.getInt(`$this$getInt`);
      }

      public override fun <T : Any?> ILocalT<T>.getLong(): ILongExpr {
         return thisx.delegate.getLong(`$this$getLong`);
      }

      public override fun <T : Any?> ILocalT<T>.getEnumName(): IStringExpr {
         return thisx.delegate.getEnumName(`$this$getEnumName`);
      }

      public override fun <T : Any?> ILocalT<T>.isInstanceOf(parentType: String): IBoolExpr {
         return thisx.delegate.isInstanceOf(`$this$isInstanceOf`, parentType);
      }

      public override fun IStringExpr.toLowerCase(): IStringExpr {
         return thisx.delegate.toLowerCase(`$this$toLowerCase`);
      }

      public override fun IStringExpr.startsWith(str: IStringExpr): IBoolExpr {
         return thisx.delegate.startsWith(`$this$startsWith`, str);
      }

      public override fun IStringExpr.startsWith(str: String): IBoolExpr {
         return thisx.delegate.startsWith(`$this$startsWith`, str);
      }

      public override fun IStringExpr.endsWith(str: IStringExpr): IBoolExpr {
         return thisx.delegate.endsWith(`$this$endsWith`, str);
      }

      public override fun IStringExpr.endsWith(str: String): IBoolExpr {
         return thisx.delegate.endsWith(`$this$endsWith`, str);
      }

      public override fun IStringExpr.contains(str: IStringExpr): IBoolExpr {
         return thisx.delegate.contains(`$this$contains`, str);
      }

      public override fun IStringExpr.contains(str: String): IBoolExpr {
         return thisx.delegate.contains(`$this$contains`, str);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.contains(taint: ITaintType): IBoolExpr {
         return thisx.delegate.contains(`$this$contains`, taint);
      }

      public override fun IStringExpr.stringEquals(str: IStringExpr): IBoolExpr {
         return thisx.delegate.stringEquals(`$this$stringEquals`, str);
      }

      public override fun IStringExpr.stringEquals(str: String): IBoolExpr {
         return thisx.delegate.stringEquals(`$this$stringEquals`, str);
      }

      public override fun taintOf(vararg type: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return thisx.delegate.taintOf(type);
      }

      public override fun taintOf(types: Collection<ITaintType>): IAttribute<TaintProperty, Set<ITaintType>> {
         return thisx.delegate.taintOf(types);
      }

      public override fun viaOf(vararg via: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return thisx.delegate.viaOf(via);
      }

      public override fun <T : Any?> anyOf(vararg local: ILocalT<T>): ILocalValue<T> {
         return thisx.delegate.anyOf(local);
      }

      public override fun <T : Any?> `null`(): ILocalValue<T> {
         return thisx.delegate.null();
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.hasIntersection(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
         return thisx.delegate.hasIntersection(`$this$hasIntersection`, taint);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: ITaintType): IBoolExpr {
         return thisx.delegate.containsAll(`$this$containsAll`, taint);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
         return thisx.delegate.containsAll(`$this$containsAll`, taint);
      }

      public override infix fun <T1 : R, T2 : R, R : Any?> ILocalValue<T1>.anyOr(second: ILocalValue<T2>): ILocalValue<R> {
         return thisx.delegate.anyOr(`$this$anyOr`, second);
      }

      public override infix fun <T1 : R, T2 : R, R : Any?> ILocalT<T1>.anyOr(second: ILocalT<T2>): ILocalValue<R> {
         return thisx.delegate.anyOr(`$this$anyOr`, second);
      }

      public override fun <T : Any?> ILocalT<T>.field(declaringClass: String?, fieldName: String, fieldType: String?): IAccessPathT<Any> {
         return thisx.delegate.field(`$this$field`, declaringClass, fieldName, fieldType);
      }

      public override fun <T : Any?> ILocalT<T>.field(field: SootField): IAccessPathT<Any> {
         return thisx.delegate.field(`$this$field`, field);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(declaringClass: String?, fieldName: String, type: KClass<FieldType>): IAccessPathT<
            FieldType
         > {
         return thisx.delegate.field(`$this$field`, declaringClass, fieldName, type);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(declaringClass: KClass<*>?, fieldName: String, type: KClass<FieldType>): IAccessPathT<
            FieldType
         > {
         return thisx.delegate.field(`$this$field`, declaringClass, fieldName, type);
      }

      public override fun <T : Any?> ILocalT<T>.field(declaringClass: KClass<*>, fieldName: String, fieldType: String?): IAccessPathT<Any> {
         return thisx.delegate.field(`$this$field`, declaringClass, fieldName, fieldType);
      }

      public override fun <T : Any?> ILocalT<T>.field(field: IClassField): IAccessPathT<Any> {
         return thisx.delegate.field(`$this$field`, field);
      }

      public override fun <T : Any?, F : Any?> ILocalT<T>.field(field: KProperty<F>): IAccessPathT<F> {
         return thisx.delegate.field(`$this$field`, field);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(field: KProperty<*>, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return thisx.delegate.field(`$this$field`, field, type);
      }

      public override fun addStmt(stmt: IStmt) {
         thisx.delegate.addStmt(stmt);
      }

      public override fun check(expr: IBoolExpr, checkType: CheckType, env: (Env) -> Unit) {
         thisx.delegate.check(expr, checkType, env);
      }

      public override fun check(expr: ILocalT<Boolean>, checkType: CheckType, env: (Env) -> Unit) {
         thisx.delegate.check(expr, checkType, env);
      }

      public override fun eval(expr: IExpr, result: (Any) -> Unit) {
         thisx.delegate.eval(expr, result);
      }

      public override fun eval(expr: IBoolExpr, result: (Boolean) -> Unit) {
         thisx.delegate.eval(expr, result);
      }

      public override fun eval(expr: IStringExpr, result: (String) -> Unit) {
         thisx.delegate.eval(expr, result);
      }

      public override fun eval(expr: IIntExpr, result: (Int) -> Unit) {
         thisx.delegate.eval(expr, result);
      }
   }
}
