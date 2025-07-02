package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.BinOp
import com.feysh.corax.config.api.BugMessage
import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.IAccessPathT
import com.feysh.corax.config.api.IAttribute
import com.feysh.corax.config.api.IBoolExpr
import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IIexConst
import com.feysh.corax.config.api.IIntExpr
import com.feysh.corax.config.api.ILocalT
import com.feysh.corax.config.api.ILocalValue
import com.feysh.corax.config.api.ILocalVarDecl
import com.feysh.corax.config.api.ILocalVarMatch
import com.feysh.corax.config.api.ILongExpr
import com.feysh.corax.config.api.IOperatorFactory
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.IReturnT
import com.feysh.corax.config.api.IStmt
import com.feysh.corax.config.api.IStringExpr
import com.feysh.corax.config.api.ITaintType
import com.feysh.corax.config.api.ITypedExpr
import com.feysh.corax.config.api.IViaType
import com.feysh.corax.config.api.IWithSubFieldsT
import com.feysh.corax.config.api.MethodConfig
import com.feysh.corax.config.api.TaintProperty
import com.feysh.corax.config.api.ViaProperty
import com.feysh.corax.config.api.BugMessage.Env
import com.feysh.corax.config.api.ILocalVarDecl.IBuilder
import com.feysh.corax.config.api.ILocalVarDecl.IGet
import com.feysh.corax.config.api.ILocalVarDecl.ISet
import com.feysh.corax.config.api.IOperatorFactory.IAttributeGetSet
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import soot.SootField

internal class BaseLocalVarDecl<T>(analyzeConfig: AIAnalysisBaseImpl, match: ILocalVarMatch) : ILocalVarDecl<T> {
   public final val analyzeConfig: AIAnalysisBaseImpl
   public final val match: ILocalVarMatch

   init {
      this.analyzeConfig = analyzeConfig;
      this.match = match;
   }

   public override fun atGet(config: (MethodConfig) -> Unit, block: (IGet<Any>) -> Unit): ILocalVarDecl<Any> {
      block.invoke(new BaseLocalVarDecl.LocalVarGet<>(new BaseLocalVarDecl.Builder(this, config)));
      return this;
   }

   public override fun atSet(config: (MethodConfig) -> Unit, block: (ISet<Any>, IParameterT<Any>) -> Unit): ILocalVarDecl<Any> {
      val setter: BaseLocalVarDecl.LocalVarSet = new BaseLocalVarDecl.LocalVarSet<>(new BaseLocalVarDecl.Builder(this, config));
      block.invoke(setter, setter.getValue());
      return this;
   }

   internal inner class Builder(config: (MethodConfig) -> Unit) : ILocalVarDecl.IBuilder<T>, IBaseOperatorFactory {
      public open val config: (MethodConfig) -> Unit
      public open val match: ILocalVarMatch
      public open val decl: ILocalVarDecl<Any>

      init {
         this.this$0 = `this$0`;
         this.config = config;
         this.match = this.this$0.getMatch();
         this.decl = this.this$0;
      }

      public override fun addStmt(stmt: IStmt) {
         this.this$0.getAnalyzeConfig().addStmt(this.this$0, this.getConfig(), stmt);
      }

      public override fun check(expr: IBoolExpr, checkType: CheckType, env: (Env) -> Unit) {
         this.this$0.getAnalyzeConfig().check(this.this$0, this.getConfig(), expr, checkType, env);
      }

      public override fun eval(expr: IExpr, result: (Any) -> Unit) {
         this.this$0.getAnalyzeConfig().eval(this.this$0, this.getConfig(), expr, result);
      }

      override fun ILocalT<?>.getSubFields(): IWithSubFieldsT {
         return IBaseOperatorFactory.DefaultImpls.getSubFields(this, `$this$subFields`);
      }

      override fun <T> ILocalT<T>.getAttr(): IOperatorFactory.IAttributeGetSet {
         return IBaseOperatorFactory.DefaultImpls.getAttr(this, `$this$attr`);
      }

      override fun <T> ILocalT<T>.getTaint(): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IBaseOperatorFactory.DefaultImpls.getTaint(this, `$this$taint`);
      }

      override fun <T> ILocalT<T>.setTaint(var2: IAttribute<TaintProperty, java.utilSet<ITaintType>>) {
         IBaseOperatorFactory.DefaultImpls.setTaint(this, `$this$taint`, var2);
      }

      override fun <T> ILocalT<T>.getVia(): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IBaseOperatorFactory.DefaultImpls.getVia(this, `$this$via`);
      }

      override fun <T> ILocalT<T>.setVia(var2: IAttribute<ViaProperty, java.utilSet<IViaType>>) {
         IBaseOperatorFactory.DefaultImpls.setVia(this, `$this$via`, var2);
      }

      override fun <T> ILocalT<T>.getValue(): ILocalValue<T> {
         return IBaseOperatorFactory.DefaultImpls.getValue(this, `$this$value`);
      }

      override fun <T> ILocalT<T>.setValue(var2: ILocalValue<T>) {
         IBaseOperatorFactory.DefaultImpls.setValue(this, `$this$value`, var2);
      }

      override fun literal(string: java.lang.String): IStringExpr {
         return IBaseOperatorFactory.DefaultImpls.literal(this, string);
      }

      override fun literal(var1: Int): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.literal(this, var1);
      }

      override fun literal(var1: Long): ILongExpr {
         return IBaseOperatorFactory.DefaultImpls.literal(this, var1);
      }

      override fun literal(bool: Boolean): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.literal(this, bool);
      }

      override fun <T> ILocalT<T>.isConstant(): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.isConstant(this, `$this$isConstant`);
      }

      override fun ITypedExpr.isConstant(): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.isConstant(this, `$this$isConstant`);
      }

      override fun IBoolExpr.not(): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.not(this, `$this$not`);
      }

      override fun IBoolExpr.or(other: IBoolExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.or(this, `$this$or`, other);
      }

      override fun IIntExpr.or(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.or(this, `$this$or`, rhs);
      }

      override fun IBoolExpr.and(other: IBoolExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.and(this, `$this$and`, other);
      }

      override fun IIntExpr.and(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.and(this, `$this$and`, rhs);
      }

      override fun IIntExpr.lt(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.lt(this, `$this$lt`, rhs);
      }

      override fun IIntExpr.le(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.le(this, `$this$le`, rhs);
      }

      override fun IIntExpr.eq(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.eq(this, `$this$eq`, rhs);
      }

      override fun IIntExpr.ge(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.ge(this, `$this$ge`, rhs);
      }

      override fun IIntExpr.gt(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.gt(this, `$this$gt`, rhs);
      }

      override fun IIntExpr.neq(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.neq(this, `$this$neq`, rhs);
      }

      override fun IIntExpr.xor(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.xor(this, `$this$xor`, rhs);
      }

      override fun IIntExpr.shl(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.shl(this, `$this$shl`, rhs);
      }

      override fun IIntExpr.shr(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.shr(this, `$this$shr`, rhs);
      }

      override fun IIntExpr.lshr(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.lshr(this, `$this$lshr`, rhs);
      }

      override fun IIntExpr.plus(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.plus(this, `$this$plus`, rhs);
      }

      override fun <T extends IClassField, V extends java.util.Set<? extends Object>> IAttribute<T, V>.plus(set: IAttribute<T, V>): Attribute<T, V> {
         return IBaseOperatorFactory.DefaultImpls.plus(this, `$this$plus`, set);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.plus(single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return ILocalVarDecl.IBuilder.DefaultImpls.plus(this, `$this$plus`, single);
      }

      override fun IAttribute<ViaProperty, java.utilSet<IViaType>>.plus(single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return ILocalVarDecl.IBuilder.DefaultImpls.plus(this, `$this$plus`, single);
      }

      override fun IIntExpr.minus(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.minus(this, `$this$minus`, rhs);
      }

      override fun <T extends IClassField, V extends java.util.Set<? extends Object>> IAttribute<T, V>.minus(set: IAttribute<T, V>): Attribute<T, V> {
         return IBaseOperatorFactory.DefaultImpls.minus(this, `$this$minus`, set);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.minus(single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return ILocalVarDecl.IBuilder.DefaultImpls.minus(this, `$this$minus`, single);
      }

      override fun IAttribute<ViaProperty, java.utilSet<IViaType>>.minus(single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return ILocalVarDecl.IBuilder.DefaultImpls.minus(this, `$this$minus`, single);
      }

      override fun <T> ILocalT<T>.getBoolean(): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.getBoolean(this, `$this$getBoolean`);
      }

      override fun <T> ILocalT<T>.getString(): IStringExpr {
         return IBaseOperatorFactory.DefaultImpls.getString(this, `$this$getString`);
      }

      override fun <T> ILocalT<T>.getInt(): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.getInt(this, `$this$getInt`);
      }

      override fun <T> ILocalT<T>.getLong(): ILongExpr {
         return IBaseOperatorFactory.DefaultImpls.getLong(this, `$this$getLong`);
      }

      override fun <T> ILocalT<T>.getEnumName(): IStringExpr {
         return IBaseOperatorFactory.DefaultImpls.getEnumName(this, `$this$getEnumName`);
      }

      override fun <T> ILocalT<T>.isInstanceOf(parentType: java.lang.String): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.isInstanceOf(this, `$this$isInstanceOf`, parentType);
      }

      override fun IStringExpr.toLowerCase(): IStringExpr {
         return IBaseOperatorFactory.DefaultImpls.toLowerCase(this, `$this$toLowerCase`);
      }

      override fun IStringExpr.startsWith(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.startsWith(this, `$this$startsWith`, str);
      }

      override fun IStringExpr.startsWith(str: java.lang.String): IBoolExpr {
         return ILocalVarDecl.IBuilder.DefaultImpls.startsWith(this, `$this$startsWith`, str);
      }

      override fun IStringExpr.endsWith(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.endsWith(this, `$this$endsWith`, str);
      }

      override fun IStringExpr.endsWith(str: java.lang.String): IBoolExpr {
         return ILocalVarDecl.IBuilder.DefaultImpls.endsWith(this, `$this$endsWith`, str);
      }

      override fun IStringExpr.contains(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.contains(this, `$this$contains`, str);
      }

      override fun IStringExpr.contains(str: java.lang.String): IBoolExpr {
         return ILocalVarDecl.IBuilder.DefaultImpls.contains(this, `$this$contains`, str);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.contains(taint: ITaintType): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.contains(this, `$this$contains`, taint);
      }

      override fun IStringExpr.stringEquals(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.stringEquals(this, `$this$stringEquals`, str);
      }

      override fun IStringExpr.stringEquals(str: java.lang.String): IBoolExpr {
         return ILocalVarDecl.IBuilder.DefaultImpls.stringEquals(this, `$this$stringEquals`, str);
      }

      override fun taintOf(vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return ILocalVarDecl.IBuilder.DefaultImpls.taintOf(this, type);
      }

      override fun taintOf(types: MutableCollection<ITaintType>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IBaseOperatorFactory.DefaultImpls.taintOf(this, types);
      }

      override fun viaOf(vararg via: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IBaseOperatorFactory.DefaultImpls.viaOf(this, via);
      }

      override fun getEmptyTaint(): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return ILocalVarDecl.IBuilder.DefaultImpls.getEmptyTaint(this);
      }

      override fun getEmptyVia(): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return ILocalVarDecl.IBuilder.DefaultImpls.getEmptyVia(this);
      }

      override fun <T> anyOf(vararg local: ILocalT<T>): ILocalValue<T> {
         return IBaseOperatorFactory.DefaultImpls.anyOf(this, local);
      }

      override fun <T> `null`(): ILocalValue<T> {
         return IBaseOperatorFactory.DefaultImpls.null(this);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.hasIntersection(taint: IAttribute<TaintProperty, java.utilSet<ITaintType>>): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.hasIntersection(this, `$this$hasIntersection`, taint);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.containsAll(taint: ITaintType): IBoolExpr {
         return ILocalVarDecl.IBuilder.DefaultImpls.containsAll(this, `$this$containsAll`, taint);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.containsAll(taint: IAttribute<TaintProperty, java.utilSet<ITaintType>>): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.containsAll(this, `$this$containsAll`, taint);
      }

      override fun <T1 extends R, T2 extends R, R> ILocalValue<T1>.anyOr(second: ILocalValue<T2>): ILocalValue<R> {
         return IBaseOperatorFactory.DefaultImpls.anyOr(this, `$this$anyOr`, second);
      }

      override fun <T1 extends R, T2 extends R, R> ILocalT<T1>.anyOr(second: ILocalT<T2>): ILocalValue<R> {
         return ILocalVarDecl.IBuilder.DefaultImpls.anyOr(this, `$this$anyOr`, second);
      }

      override fun <T> ILocalT<T>.field(declaringClass: java.lang.String?, fieldName: java.lang.String, fieldType: java.lang.String?): IAccessPathT<Object> {
         return IBaseOperatorFactory.DefaultImpls.field(this, `$this$field`, declaringClass, fieldName, fieldType);
      }

      override fun <T> ILocalT<T>.field(field: SootField): IAccessPathT<Object> {
         return ILocalVarDecl.IBuilder.DefaultImpls.field(this, `$this$field`, field);
      }

      override fun <T, FieldType> ILocalT<T>.field(declaringClass: java.lang.String?, fieldName: java.lang.String, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return IBaseOperatorFactory.DefaultImpls.field(this, `$this$field`, declaringClass, fieldName, type);
      }

      override fun <T, FieldType> ILocalT<T>.field(declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return ILocalVarDecl.IBuilder.DefaultImpls.field(this, `$this$field`, declaringClass, fieldName, type);
      }

      override fun <T> ILocalT<T>.field(declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?): IAccessPathT<Object> {
         return ILocalVarDecl.IBuilder.DefaultImpls.field(this, `$this$field`, declaringClass, fieldName, fieldType);
      }

      override fun <T> ILocalT<T>.field(field: IClassField): IAccessPathT<Object> {
         return IBaseOperatorFactory.DefaultImpls.field(this, `$this$field`, field);
      }

      override fun <T, F> ILocalT<T>.field(field: KProperty<? extends F>): IAccessPathT<F> {
         return IBaseOperatorFactory.DefaultImpls.field(this, `$this$field`, field);
      }

      override fun <T, FieldType> ILocalT<T>.field(field: KProperty<?>, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return IBaseOperatorFactory.DefaultImpls.field(this, `$this$field`, field, type);
      }

      override fun check(expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
         ILocalVarDecl.IBuilder.DefaultImpls.check(this, expr, checkType, env);
      }

      override fun eval(expr: IBoolExpr, result: (java.lang.Boolean?) -> Unit) {
         IBaseOperatorFactory.DefaultImpls.eval(this, expr, result);
      }

      override fun eval(expr: IStringExpr, result: (java.lang.String?) -> Unit) {
         IBaseOperatorFactory.DefaultImpls.eval(this, expr, result);
      }

      override fun eval(expr: IIntExpr, result: (Int?) -> Unit) {
         IBaseOperatorFactory.DefaultImpls.eval(this, expr, result);
      }

      override fun const(var1: Any, type: IIexConst.Type): IExpr {
         return IBaseOperatorFactory.DefaultImpls.const(this, var1, type);
      }

      override fun IIntExpr.compareBinOp(op: BinOp, rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.compareBinOp(this, `$this$compareBinOp`, op, rhs);
      }

      override fun IIntExpr.arithmeticBinOp(op: BinOp, rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.arithmeticBinOp(this, `$this$arithmeticBinOp`, op, rhs);
      }

      override fun IIntExpr.bitwiseBinOp(op: BinOp, rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.bitwiseBinOp(this, `$this$bitwiseBinOp`, op, rhs);
      }
   }

   public class LocalVarGet<T>(delegate: IBuilder<Any>) : ILocalVarDecl.IGet<T>, ILocalVarDecl.IBuilder<T> {
      public final val delegate: IBuilder<Any>
      public open val `return`: IReturnT<Any>
      public open val attr: IAttributeGetSet
      public open val config: (MethodConfig) -> Unit
      public open val decl: ILocalVarDecl<Any>

      public open val emptyTaint: IAttribute<TaintProperty, Set<ITaintType>>
         public open get() {
            return this.delegate.getEmptyTaint();
         }


      public open val emptyVia: IAttribute<ViaProperty, Set<IViaType>>
         public open get() {
            return this.delegate.getEmptyVia();
         }


      public open val isConstant: IBoolExpr
      public open val isConstant: IBoolExpr
      public open val match: ILocalVarMatch
      public open val subFields: IWithSubFieldsT

      public open var taint: IAttribute<TaintProperty, Set<ITaintType>>
         internal final set

      public open var value: ILocalValue<T>
         internal final set

      public open var via: IAttribute<ViaProperty, Set<IViaType>>
         internal final set

      init {
         this.delegate = delegate;
         this.return = new Return<>();
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

   public class LocalVarSet<T>(delegate: IBuilder<Any>) : ILocalVarDecl.ISet<T>, ILocalVarDecl.IBuilder<T> {
      public final val delegate: IBuilder<Any>
      public open val value: IParameterT<Any>
      public open val attr: IAttributeGetSet
      public open val config: (MethodConfig) -> Unit
      public open val decl: ILocalVarDecl<Any>

      public open val emptyTaint: IAttribute<TaintProperty, Set<ITaintType>>
         public open get() {
            return this.delegate.getEmptyTaint();
         }


      public open val emptyVia: IAttribute<ViaProperty, Set<IViaType>>
         public open get() {
            return this.delegate.getEmptyVia();
         }


      public open val isConstant: IBoolExpr
      public open val isConstant: IBoolExpr
      public open val match: ILocalVarMatch
      public open val subFields: IWithSubFieldsT

      public open var taint: IAttribute<TaintProperty, Set<ITaintType>>
         internal final set

      public open var value: ILocalValue<T>
         internal final set

      public open var via: IAttribute<ViaProperty, Set<IViaType>>
         internal final set

      init {
         this.delegate = delegate;
         this.value = new Parameter<>(0);
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
