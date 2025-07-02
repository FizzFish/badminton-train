package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.BinOp
import com.feysh.corax.config.api.BugMessage
import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.ClassField
import com.feysh.corax.config.api.IAccessPathT
import com.feysh.corax.config.api.IAttribute
import com.feysh.corax.config.api.IBoolExpr
import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IFieldDecl
import com.feysh.corax.config.api.IFieldMatch
import com.feysh.corax.config.api.IIexConst
import com.feysh.corax.config.api.IIntExpr
import com.feysh.corax.config.api.ILocalT
import com.feysh.corax.config.api.ILocalValue
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
import com.feysh.corax.config.api.IFieldDecl.IBuilder
import com.feysh.corax.config.api.IFieldDecl.IGet
import com.feysh.corax.config.api.IFieldDecl.ISet
import com.feysh.corax.config.api.IOperatorFactory.IAttributeGetSet
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import soot.SootField

internal class BaseFieldDecl<This, T>(analyzeConfig: AIAnalysisBaseImpl, match: IFieldMatch) : IFieldDecl<This, T> {
   public final val analyzeConfig: AIAnalysisBaseImpl
   public final val match: IFieldMatch

   init {
      this.analyzeConfig = analyzeConfig;
      this.match = match;
   }

   public override fun atSet(config: (MethodConfig) -> Unit, block: (ISet<Any, Any>, IParameterT<Any>) -> Unit): IFieldDecl<Any, Any> {
      val setter: BaseFieldDecl.FieldSet = new BaseFieldDecl.FieldSet<>(this, new BaseFieldDecl.Builder(this, config));
      block.invoke(setter, setter.getValue());
      return this;
   }

   public override fun atGet(config: (MethodConfig) -> Unit, block: (IGet<Any, Any>) -> Unit): IFieldDecl<Any, Any> {
      block.invoke(new BaseFieldDecl.FieldGet<>(this, new BaseFieldDecl.Builder(this, config)));
      return this;
   }

   public override fun toString(): String {
      return "field decl: ${this.match}";
   }

   internal inner class Builder(config: (MethodConfig) -> Unit) : IFieldDecl.IBuilder<This, T>, IBaseOperatorFactory {
      public open val config: (MethodConfig) -> Unit
      public open val match: IFieldMatch
      public open val decl: IFieldDecl<Any, Any>
      public open val `this`: IParameterT<Any>
      public open val field: IAccessPathT<Any>

      init {
         thisx.this$0 = `this$0`;
         thisx.config = config;
         thisx.match = thisx.this$0.getMatch();
         thisx.decl = thisx.this$0;
         thisx.this = new Parameter(-1);
         thisx.field = new AccessPath<>(
            thisx.getThis().getExpr(),
            new ClassField(thisx.getMatch().getDeclaringClassType(), thisx.getMatch().getFieldName(), thisx.getMatch().getFieldType())
         );
      }

      public override fun addStmt(stmt: IStmt) {
         thisx.this$0.getAnalyzeConfig().addStmt(thisx.this$0, thisx.getConfig(), stmt);
      }

      public override fun check(expr: IBoolExpr, checkType: CheckType, env: (Env) -> Unit) {
         thisx.this$0.getAnalyzeConfig().check(thisx.this$0, thisx.getConfig(), expr, checkType, env);
      }

      public override fun eval(expr: IExpr, result: (Any) -> Unit) {
         thisx.this$0.getAnalyzeConfig().eval(thisx.this$0, thisx.getConfig(), expr, result);
      }

      override fun <T> ILocalT<T>.field(declaringClass: java.lang.String?, fieldName: java.lang.String, fieldType: java.lang.String?): IAccessPathT<Object> {
         return IBaseOperatorFactory.DefaultImpls.field(thisx, `$this$field`, declaringClass, fieldName, fieldType);
      }

      override fun <T> ILocalT<T>.field(field: SootField): IAccessPathT<Object> {
         return IFieldDecl.IBuilder.DefaultImpls.field(thisx, `$this$field`, field);
      }

      override fun <T, FieldType> ILocalT<T>.field(declaringClass: java.lang.String?, fieldName: java.lang.String, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return IBaseOperatorFactory.DefaultImpls.field(thisx, `$this$field`, declaringClass, fieldName, type);
      }

      override fun <T, FieldType> ILocalT<T>.field(declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return IFieldDecl.IBuilder.DefaultImpls.field(thisx, `$this$field`, declaringClass, fieldName, type);
      }

      override fun <T> ILocalT<T>.field(declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?): IAccessPathT<Object> {
         return IFieldDecl.IBuilder.DefaultImpls.field(thisx, `$this$field`, declaringClass, fieldName, fieldType);
      }

      override fun <T> ILocalT<T>.field(field: IClassField): IAccessPathT<Object> {
         return IBaseOperatorFactory.DefaultImpls.field(thisx, `$this$field`, field);
      }

      override fun <T, F> ILocalT<T>.field(field: KProperty<? extends F>): IAccessPathT<F> {
         return IBaseOperatorFactory.DefaultImpls.field(thisx, `$this$field`, field);
      }

      override fun <T, FieldType> ILocalT<T>.field(field: KProperty<?>, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return IBaseOperatorFactory.DefaultImpls.field(thisx, `$this$field`, field, type);
      }

      override fun ILocalT<?>.getSubFields(): IWithSubFieldsT {
         return IBaseOperatorFactory.DefaultImpls.getSubFields(thisx, `$this$subFields`);
      }

      override fun <T> ILocalT<T>.getAttr(): IOperatorFactory.IAttributeGetSet {
         return IBaseOperatorFactory.DefaultImpls.getAttr(thisx, `$this$attr`);
      }

      override fun <T> ILocalT<T>.getTaint(): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IBaseOperatorFactory.DefaultImpls.getTaint(thisx, `$this$taint`);
      }

      override fun <T> ILocalT<T>.setTaint(var2: IAttribute<TaintProperty, java.utilSet<ITaintType>>) {
         IBaseOperatorFactory.DefaultImpls.setTaint(thisx, `$this$taint`, var2);
      }

      override fun <T> ILocalT<T>.getVia(): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IBaseOperatorFactory.DefaultImpls.getVia(thisx, `$this$via`);
      }

      override fun <T> ILocalT<T>.setVia(var2: IAttribute<ViaProperty, java.utilSet<IViaType>>) {
         IBaseOperatorFactory.DefaultImpls.setVia(thisx, `$this$via`, var2);
      }

      override fun <T> ILocalT<T>.getValue(): ILocalValue<T> {
         return IBaseOperatorFactory.DefaultImpls.getValue(thisx, `$this$value`);
      }

      override fun <T> ILocalT<T>.setValue(var2: ILocalValue<T>) {
         IBaseOperatorFactory.DefaultImpls.setValue(thisx, `$this$value`, var2);
      }

      override fun literal(string: java.lang.String): IStringExpr {
         return IBaseOperatorFactory.DefaultImpls.literal(thisx, string);
      }

      override fun literal(var1: Int): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.literal(thisx, var1);
      }

      override fun literal(var1: Long): ILongExpr {
         return IBaseOperatorFactory.DefaultImpls.literal(thisx, var1);
      }

      override fun literal(bool: Boolean): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.literal(thisx, bool);
      }

      override fun <T> ILocalT<T>.isConstant(): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.isConstant(thisx, `$this$isConstant`);
      }

      override fun ITypedExpr.isConstant(): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.isConstant(thisx, `$this$isConstant`);
      }

      override fun IBoolExpr.not(): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.not(thisx, `$this$not`);
      }

      override fun IBoolExpr.or(other: IBoolExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.or(thisx, `$this$or`, other);
      }

      override fun IIntExpr.or(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.or(thisx, `$this$or`, rhs);
      }

      override fun IBoolExpr.and(other: IBoolExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.and(thisx, `$this$and`, other);
      }

      override fun IIntExpr.and(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.and(thisx, `$this$and`, rhs);
      }

      override fun IIntExpr.lt(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.lt(thisx, `$this$lt`, rhs);
      }

      override fun IIntExpr.le(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.le(thisx, `$this$le`, rhs);
      }

      override fun IIntExpr.eq(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.eq(thisx, `$this$eq`, rhs);
      }

      override fun IIntExpr.ge(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.ge(thisx, `$this$ge`, rhs);
      }

      override fun IIntExpr.gt(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.gt(thisx, `$this$gt`, rhs);
      }

      override fun IIntExpr.neq(rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.neq(thisx, `$this$neq`, rhs);
      }

      override fun IIntExpr.xor(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.xor(thisx, `$this$xor`, rhs);
      }

      override fun IIntExpr.shl(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.shl(thisx, `$this$shl`, rhs);
      }

      override fun IIntExpr.shr(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.shr(thisx, `$this$shr`, rhs);
      }

      override fun IIntExpr.lshr(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.lshr(thisx, `$this$lshr`, rhs);
      }

      override fun IIntExpr.plus(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.plus(thisx, `$this$plus`, rhs);
      }

      override fun <T extends IClassField, V extends java.util.Set<? extends Object>> IAttribute<T, V>.plus(set: IAttribute<T, V>): Attribute<T, V> {
         return IBaseOperatorFactory.DefaultImpls.plus(thisx, `$this$plus`, set);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.plus(single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IFieldDecl.IBuilder.DefaultImpls.plus(thisx, `$this$plus`, single);
      }

      override fun IAttribute<ViaProperty, java.utilSet<IViaType>>.plus(single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IFieldDecl.IBuilder.DefaultImpls.plus(thisx, `$this$plus`, single);
      }

      override fun IIntExpr.minus(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.minus(thisx, `$this$minus`, rhs);
      }

      override fun <T extends IClassField, V extends java.util.Set<? extends Object>> IAttribute<T, V>.minus(set: IAttribute<T, V>): Attribute<T, V> {
         return IBaseOperatorFactory.DefaultImpls.minus(thisx, `$this$minus`, set);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.minus(single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IFieldDecl.IBuilder.DefaultImpls.minus(thisx, `$this$minus`, single);
      }

      override fun IAttribute<ViaProperty, java.utilSet<IViaType>>.minus(single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IFieldDecl.IBuilder.DefaultImpls.minus(thisx, `$this$minus`, single);
      }

      override fun <T> ILocalT<T>.getBoolean(): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.getBoolean(thisx, `$this$getBoolean`);
      }

      override fun <T> ILocalT<T>.getString(): IStringExpr {
         return IBaseOperatorFactory.DefaultImpls.getString(thisx, `$this$getString`);
      }

      override fun <T> ILocalT<T>.getInt(): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.getInt(thisx, `$this$getInt`);
      }

      override fun <T> ILocalT<T>.getLong(): ILongExpr {
         return IBaseOperatorFactory.DefaultImpls.getLong(thisx, `$this$getLong`);
      }

      override fun <T> ILocalT<T>.getEnumName(): IStringExpr {
         return IBaseOperatorFactory.DefaultImpls.getEnumName(thisx, `$this$getEnumName`);
      }

      override fun <T> ILocalT<T>.isInstanceOf(parentType: java.lang.String): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.isInstanceOf(thisx, `$this$isInstanceOf`, parentType);
      }

      override fun IStringExpr.toLowerCase(): IStringExpr {
         return IBaseOperatorFactory.DefaultImpls.toLowerCase(thisx, `$this$toLowerCase`);
      }

      override fun IStringExpr.startsWith(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.startsWith(thisx, `$this$startsWith`, str);
      }

      override fun IStringExpr.startsWith(str: java.lang.String): IBoolExpr {
         return IFieldDecl.IBuilder.DefaultImpls.startsWith(thisx, `$this$startsWith`, str);
      }

      override fun IStringExpr.endsWith(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.endsWith(thisx, `$this$endsWith`, str);
      }

      override fun IStringExpr.endsWith(str: java.lang.String): IBoolExpr {
         return IFieldDecl.IBuilder.DefaultImpls.endsWith(thisx, `$this$endsWith`, str);
      }

      override fun IStringExpr.contains(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.contains(thisx, `$this$contains`, str);
      }

      override fun IStringExpr.contains(str: java.lang.String): IBoolExpr {
         return IFieldDecl.IBuilder.DefaultImpls.contains(thisx, `$this$contains`, str);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.contains(taint: ITaintType): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.contains(thisx, `$this$contains`, taint);
      }

      override fun IStringExpr.stringEquals(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.stringEquals(thisx, `$this$stringEquals`, str);
      }

      override fun IStringExpr.stringEquals(str: java.lang.String): IBoolExpr {
         return IFieldDecl.IBuilder.DefaultImpls.stringEquals(thisx, `$this$stringEquals`, str);
      }

      override fun taintOf(vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IFieldDecl.IBuilder.DefaultImpls.taintOf(thisx, type);
      }

      override fun taintOf(types: MutableCollection<ITaintType>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IBaseOperatorFactory.DefaultImpls.taintOf(thisx, types);
      }

      override fun viaOf(vararg via: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IBaseOperatorFactory.DefaultImpls.viaOf(thisx, via);
      }

      override fun getEmptyTaint(): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IFieldDecl.IBuilder.DefaultImpls.getEmptyTaint(thisx);
      }

      override fun getEmptyVia(): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IFieldDecl.IBuilder.DefaultImpls.getEmptyVia(thisx);
      }

      override fun <T> anyOf(vararg local: ILocalT<T>): ILocalValue<T> {
         return IBaseOperatorFactory.DefaultImpls.anyOf(thisx, local);
      }

      override fun <T> `null`(): ILocalValue<T> {
         return IBaseOperatorFactory.DefaultImpls.null(thisx);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.hasIntersection(taint: IAttribute<TaintProperty, java.utilSet<ITaintType>>): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.hasIntersection(thisx, `$this$hasIntersection`, taint);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.containsAll(taint: ITaintType): IBoolExpr {
         return IFieldDecl.IBuilder.DefaultImpls.containsAll(thisx, `$this$containsAll`, taint);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.containsAll(taint: IAttribute<TaintProperty, java.utilSet<ITaintType>>): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.containsAll(thisx, `$this$containsAll`, taint);
      }

      override fun <T1 extends R, T2 extends R, R> ILocalValue<T1>.anyOr(second: ILocalValue<T2>): ILocalValue<R> {
         return IBaseOperatorFactory.DefaultImpls.anyOr(thisx, `$this$anyOr`, second);
      }

      override fun <T1 extends R, T2 extends R, R> ILocalT<T1>.anyOr(second: ILocalT<T2>): ILocalValue<R> {
         return IFieldDecl.IBuilder.DefaultImpls.anyOr(thisx, `$this$anyOr`, second);
      }

      override fun check(expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
         IFieldDecl.IBuilder.DefaultImpls.check(thisx, expr, checkType, env);
      }

      override fun eval(expr: IBoolExpr, result: (java.lang.Boolean?) -> Unit) {
         IBaseOperatorFactory.DefaultImpls.eval(thisx, expr, result);
      }

      override fun eval(expr: IStringExpr, result: (java.lang.String?) -> Unit) {
         IBaseOperatorFactory.DefaultImpls.eval(thisx, expr, result);
      }

      override fun eval(expr: IIntExpr, result: (Int?) -> Unit) {
         IBaseOperatorFactory.DefaultImpls.eval(thisx, expr, result);
      }

      override fun const(var1: Any, type: IIexConst.Type): IExpr {
         return IBaseOperatorFactory.DefaultImpls.const(thisx, var1, type);
      }

      override fun IIntExpr.compareBinOp(op: BinOp, rhs: IIntExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.compareBinOp(thisx, `$this$compareBinOp`, op, rhs);
      }

      override fun IIntExpr.arithmeticBinOp(op: BinOp, rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.arithmeticBinOp(thisx, `$this$arithmeticBinOp`, op, rhs);
      }

      override fun IIntExpr.bitwiseBinOp(op: BinOp, rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.bitwiseBinOp(thisx, `$this$bitwiseBinOp`, op, rhs);
      }
   }

   internal inner class FieldGet<This, T>(delegate: IBuilder<Any, Any>) : IFieldDecl.IGet<This, T>, IFieldDecl.IBuilder<This, T> {
      public open val `return`: IReturnT<Any>
      public open val attr: IAttributeGetSet
      public open val config: (MethodConfig) -> Unit
      public open val decl: IFieldDecl<Any, Any>

      public open val emptyTaint: IAttribute<TaintProperty, Set<ITaintType>>
         public open get() {
            return this.$$delegate_0.getEmptyTaint();
         }


      public open val emptyVia: IAttribute<ViaProperty, Set<IViaType>>
         public open get() {
            return this.$$delegate_0.getEmptyVia();
         }


      public open val field: IAccessPathT<Any>
      public open val isConstant: IBoolExpr
      public open val isConstant: IBoolExpr
      public open val match: IFieldMatch
      public open val subFields: IWithSubFieldsT

      public open var taint: IAttribute<TaintProperty, Set<ITaintType>>
         internal final set

      public open val `this`: IParameterT<Any>

      public open var value: ILocalValue<T>
         internal final set

      public open var via: IAttribute<ViaProperty, Set<IViaType>>
         internal final set

      init {
         this.this$0 = `this$0`;
         this.$$delegate_0 = delegate;
         this.return = new Return<>();
      }

      public override fun <T : Any?> ILocalT<T>.field(declaringClass: String?, fieldName: String, fieldType: String?): IAccessPathT<Any> {
         return this.$$delegate_0.field(`$this$field`, declaringClass, fieldName, fieldType);
      }

      public override fun <T : Any?> ILocalT<T>.field(field: SootField): IAccessPathT<Any> {
         return this.$$delegate_0.field(`$this$field`, field);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(declaringClass: String?, fieldName: String, type: KClass<FieldType>): IAccessPathT<
            FieldType
         > {
         return this.$$delegate_0.field(`$this$field`, declaringClass, fieldName, type);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(declaringClass: KClass<*>?, fieldName: String, type: KClass<FieldType>): IAccessPathT<
            FieldType
         > {
         return this.$$delegate_0.field(`$this$field`, declaringClass, fieldName, type);
      }

      public override fun <T : Any?> ILocalT<T>.field(declaringClass: KClass<*>, fieldName: String, fieldType: String?): IAccessPathT<Any> {
         return this.$$delegate_0.field(`$this$field`, declaringClass, fieldName, fieldType);
      }

      public override fun <T : Any?> ILocalT<T>.field(field: IClassField): IAccessPathT<Any> {
         return this.$$delegate_0.field(`$this$field`, field);
      }

      public override fun <T : Any?, F : Any?> ILocalT<T>.field(field: KProperty<F>): IAccessPathT<F> {
         return this.$$delegate_0.field(`$this$field`, field);
      }

      public override fun <T : Any?, FieldType : Any> ILocalT<T>.field(field: KProperty<*>, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return this.$$delegate_0.field(`$this$field`, field, type);
      }

      public override fun literal(string: String): IStringExpr {
         return this.$$delegate_0.literal(string);
      }

      public override fun literal(int: Int): IIntExpr {
         return this.$$delegate_0.literal(var1);
      }

      public override fun literal(long: Long): ILongExpr {
         return this.$$delegate_0.literal(var1);
      }

      public override fun literal(bool: Boolean): IBoolExpr {
         return this.$$delegate_0.literal(bool);
      }

      public override operator fun IBoolExpr.not(): IBoolExpr {
         return this.$$delegate_0.not(`$this$not`);
      }

      public override infix fun IBoolExpr.or(other: IBoolExpr): IBoolExpr {
         return this.$$delegate_0.or(`$this$or`, other);
      }

      public override infix fun IIntExpr.or(rhs: IIntExpr): IIntExpr {
         return this.$$delegate_0.or(`$this$or`, rhs);
      }

      public override infix fun IBoolExpr.and(other: IBoolExpr): IBoolExpr {
         return this.$$delegate_0.and(`$this$and`, other);
      }

      public override infix fun IIntExpr.and(rhs: IIntExpr): IIntExpr {
         return this.$$delegate_0.and(`$this$and`, rhs);
      }

      public override infix fun IIntExpr.lt(rhs: IIntExpr): IBoolExpr {
         return this.$$delegate_0.lt(`$this$lt`, rhs);
      }

      public override infix fun IIntExpr.le(rhs: IIntExpr): IBoolExpr {
         return this.$$delegate_0.le(`$this$le`, rhs);
      }

      public override infix fun IIntExpr.eq(rhs: IIntExpr): IBoolExpr {
         return this.$$delegate_0.eq(`$this$eq`, rhs);
      }

      public override infix fun IIntExpr.ge(rhs: IIntExpr): IBoolExpr {
         return this.$$delegate_0.ge(`$this$ge`, rhs);
      }

      public override infix fun IIntExpr.gt(rhs: IIntExpr): IBoolExpr {
         return this.$$delegate_0.gt(`$this$gt`, rhs);
      }

      public override infix fun IIntExpr.neq(rhs: IIntExpr): IBoolExpr {
         return this.$$delegate_0.neq(`$this$neq`, rhs);
      }

      public override infix fun IIntExpr.xor(rhs: IIntExpr): IIntExpr {
         return this.$$delegate_0.xor(`$this$xor`, rhs);
      }

      public override infix fun IIntExpr.shl(rhs: IIntExpr): IIntExpr {
         return this.$$delegate_0.shl(`$this$shl`, rhs);
      }

      public override infix fun IIntExpr.shr(rhs: IIntExpr): IIntExpr {
         return this.$$delegate_0.shr(`$this$shr`, rhs);
      }

      public override infix fun IIntExpr.lshr(rhs: IIntExpr): IIntExpr {
         return this.$$delegate_0.lshr(`$this$lshr`, rhs);
      }

      public override operator fun IIntExpr.plus(rhs: IIntExpr): IIntExpr {
         return this.$$delegate_0.plus(`$this$plus`, rhs);
      }

      public override infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.plus(set: IAttribute<T, V>): IAttribute<T, V> {
         return this.$$delegate_0.plus(`$this$plus`, set);
      }

      public override infix operator fun IAttribute<TaintProperty, Set<ITaintType>>.plus(single: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return this.$$delegate_0.plus(`$this$plus`, single);
      }

      public override infix operator fun IAttribute<ViaProperty, Set<IViaType>>.plus(single: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return this.$$delegate_0.plus(`$this$plus`, single);
      }

      public override operator fun IIntExpr.minus(rhs: IIntExpr): IIntExpr {
         return this.$$delegate_0.minus(`$this$minus`, rhs);
      }

      public override infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.minus(set: IAttribute<T, V>): IAttribute<T, V> {
         return this.$$delegate_0.minus(`$this$minus`, set);
      }

      public override infix operator fun IAttribute<TaintProperty, Set<ITaintType>>.minus(single: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return this.$$delegate_0.minus(`$this$minus`, single);
      }

      public override infix operator fun IAttribute<ViaProperty, Set<IViaType>>.minus(single: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return this.$$delegate_0.minus(`$this$minus`, single);
      }

      public override fun <T : Any?> ILocalT<T>.getBoolean(): IBoolExpr {
         return this.$$delegate_0.getBoolean(`$this$getBoolean`);
      }

      public override fun <T : Any?> ILocalT<T>.getString(): IStringExpr {
         return this.$$delegate_0.getString(`$this$getString`);
      }

      public override fun <T : Any?> ILocalT<T>.getInt(): IIntExpr {
         return this.$$delegate_0.getInt(`$this$getInt`);
      }

      public override fun <T : Any?> ILocalT<T>.getLong(): ILongExpr {
         return this.$$delegate_0.getLong(`$this$getLong`);
      }

      public override fun <T : Any?> ILocalT<T>.getEnumName(): IStringExpr {
         return this.$$delegate_0.getEnumName(`$this$getEnumName`);
      }

      public override fun <T : Any?> ILocalT<T>.isInstanceOf(parentType: String): IBoolExpr {
         return this.$$delegate_0.isInstanceOf(`$this$isInstanceOf`, parentType);
      }

      public override fun IStringExpr.toLowerCase(): IStringExpr {
         return this.$$delegate_0.toLowerCase(`$this$toLowerCase`);
      }

      public override fun IStringExpr.startsWith(str: IStringExpr): IBoolExpr {
         return this.$$delegate_0.startsWith(`$this$startsWith`, str);
      }

      public override fun IStringExpr.startsWith(str: String): IBoolExpr {
         return this.$$delegate_0.startsWith(`$this$startsWith`, str);
      }

      public override fun IStringExpr.endsWith(str: IStringExpr): IBoolExpr {
         return this.$$delegate_0.endsWith(`$this$endsWith`, str);
      }

      public override fun IStringExpr.endsWith(str: String): IBoolExpr {
         return this.$$delegate_0.endsWith(`$this$endsWith`, str);
      }

      public override fun IStringExpr.contains(str: IStringExpr): IBoolExpr {
         return this.$$delegate_0.contains(`$this$contains`, str);
      }

      public override fun IStringExpr.contains(str: String): IBoolExpr {
         return this.$$delegate_0.contains(`$this$contains`, str);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.contains(taint: ITaintType): IBoolExpr {
         return this.$$delegate_0.contains(`$this$contains`, taint);
      }

      public override fun IStringExpr.stringEquals(str: IStringExpr): IBoolExpr {
         return this.$$delegate_0.stringEquals(`$this$stringEquals`, str);
      }

      public override fun IStringExpr.stringEquals(str: String): IBoolExpr {
         return this.$$delegate_0.stringEquals(`$this$stringEquals`, str);
      }

      public override fun taintOf(vararg type: ITaintType): IAttribute<TaintProperty, Set<ITaintType>> {
         return this.$$delegate_0.taintOf(type);
      }

      public override fun taintOf(types: Collection<ITaintType>): IAttribute<TaintProperty, Set<ITaintType>> {
         return this.$$delegate_0.taintOf(types);
      }

      public override fun viaOf(vararg via: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
         return this.$$delegate_0.viaOf(via);
      }

      public override fun <T : Any?> anyOf(vararg local: ILocalT<T>): ILocalValue<T> {
         return this.$$delegate_0.anyOf(local);
      }

      public override fun <T : Any?> `null`(): ILocalValue<T> {
         return this.$$delegate_0.null();
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.hasIntersection(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
         return this.$$delegate_0.hasIntersection(`$this$hasIntersection`, taint);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: ITaintType): IBoolExpr {
         return this.$$delegate_0.containsAll(`$this$containsAll`, taint);
      }

      public override fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
         return this.$$delegate_0.containsAll(`$this$containsAll`, taint);
      }

      public override infix fun <T1 : R, T2 : R, R : Any?> ILocalValue<T1>.anyOr(second: ILocalValue<T2>): ILocalValue<R> {
         return this.$$delegate_0.anyOr(`$this$anyOr`, second);
      }

      public override infix fun <T1 : R, T2 : R, R : Any?> ILocalT<T1>.anyOr(second: ILocalT<T2>): ILocalValue<R> {
         return this.$$delegate_0.anyOr(`$this$anyOr`, second);
      }

      public override fun addStmt(stmt: IStmt) {
         this.$$delegate_0.addStmt(stmt);
      }

      public override fun check(expr: IBoolExpr, checkType: CheckType, env: (Env) -> Unit) {
         this.$$delegate_0.check(expr, checkType, env);
      }

      public override fun check(expr: ILocalT<Boolean>, checkType: CheckType, env: (Env) -> Unit) {
         this.$$delegate_0.check(expr, checkType, env);
      }

      public override fun eval(expr: IExpr, result: (Any) -> Unit) {
         this.$$delegate_0.eval(expr, result);
      }

      public override fun eval(expr: IBoolExpr, result: (Boolean) -> Unit) {
         this.$$delegate_0.eval(expr, result);
      }

      public override fun eval(expr: IStringExpr, result: (String) -> Unit) {
         this.$$delegate_0.eval(expr, result);
      }

      public override fun eval(expr: IIntExpr, result: (Int) -> Unit) {
         this.$$delegate_0.eval(expr, result);
      }
   }

   public inner class FieldSet<This, T>(delegate: IBuilder<Any, Any>) : IFieldDecl.ISet<This, T>, IFieldDecl.IBuilder<This, T> {
      public final val delegate: IBuilder<Any, Any>
      public open val value: IParameterT<Any>
      public open val attr: IAttributeGetSet
      public open val config: (MethodConfig) -> Unit
      public open val decl: IFieldDecl<Any, Any>

      public open val emptyTaint: IAttribute<TaintProperty, Set<ITaintType>>
         public open get() {
            return this.delegate.getEmptyTaint();
         }


      public open val emptyVia: IAttribute<ViaProperty, Set<IViaType>>
         public open get() {
            return this.delegate.getEmptyVia();
         }


      public open val field: IAccessPathT<Any>
      public open val isConstant: IBoolExpr
      public open val isConstant: IBoolExpr
      public open val match: IFieldMatch
      public open val subFields: IWithSubFieldsT

      public open var taint: IAttribute<TaintProperty, Set<ITaintType>>
         internal final set

      public open val `this`: IParameterT<Any>

      public open var value: ILocalValue<T>
         internal final set

      public open var via: IAttribute<ViaProperty, Set<IViaType>>
         internal final set

      init {
         this.this$0 = `this$0`;
         this.delegate = delegate;
         this.value = new Parameter<>(0);
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
