package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.BugMessage
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
import com.feysh.corax.config.api.IMethodMatch
import com.feysh.corax.config.api.IOperatorFactory
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.IReturnT
import com.feysh.corax.config.api.ISMethodDecl
import com.feysh.corax.config.api.ISMethodDecl9
import com.feysh.corax.config.api.ISootLocalVarDecl
import com.feysh.corax.config.api.IStmt
import com.feysh.corax.config.api.IStringExpr
import com.feysh.corax.config.api.ITaintType
import com.feysh.corax.config.api.ITypedExpr
import com.feysh.corax.config.api.IViaType
import com.feysh.corax.config.api.IWithSubFieldsT
import com.feysh.corax.config.api.MethodConfig
import com.feysh.corax.config.api.TaintProperty
import com.feysh.corax.config.api.ViaProperty
import com.feysh.corax.config.api.AIAnalysisApi.Error
import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.functions.Function1
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import soot.SootField

internal class StaticMethodDecl9<R, P0, P1, P2, P3, P4, P5, P6, P7, P8>(base: ISMethodDecl<Any>, argumentCnt: Int = 9) :
   ISMethodDecl9<R, P0, P1, P2, P3, P4, P5, P6, P7, P8>,
   ISMethodDecl<R> {
   private final val base: ISMethodDecl<Any>
   public open val argumentCnt: Int

   public open val actualType: KFunctionType?
      public open get() {
         return this.base.getActualType();
      }


   public open val error: Error
   public open val match: IMethodMatch

   init {
      this.base = base;
      this.argumentCnt = argumentCnt;
   }

   public override fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl9.CheckBuilder<Any, Any, Any, Any, Any, Any, Any, Any, Any, Any> {
      return new ISMethodDecl9.CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8>(this, config) {
         private final ISMethodDecl9<R, P0, P1, P2, P3, P4, P5, P6, P7, P8> method;

         {
            this.$$delegate_0 = StaticMethodDecl9.access$getBase$p(`$receiver`).checkBuilder(`$config`);
            this.method = `$receiver`;
         }

         @Override
         public ISMethodDecl9<R, P0, P1, P2, P3, P4, P5, P6, P7, P8> getMethod() {
            return this.method;
         }

         @Override
         public IParameterT<P0> getP0() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP0(this);
         }

         @Override
         public IParameterT<P1> getP1() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP1(this);
         }

         @Override
         public IParameterT<P2> getP2() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP2(this);
         }

         @Override
         public IParameterT<P3> getP3() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP3(this);
         }

         @Override
         public IParameterT<P4> getP4() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP4(this);
         }

         @Override
         public IParameterT<P5> getP5() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP5(this);
         }

         @Override
         public IParameterT<P6> getP6() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP6(this);
         }

         @Override
         public IParameterT<P7> getP7() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP7(this);
         }

         @Override
         public IParameterT<P8> getP8() {
            return ISMethodDecl9.CheckBuilder.DefaultImpls.getP8(this);
         }

         @Override
         public Function1<MethodConfig, Unit> getConfig() {
            return this.$$delegate_0.getConfig();
         }

         @Override
         public ILocalT<Object> getGlobal() {
            return this.$$delegate_0.getGlobal();
         }

         @Override
         public <T> IParameterT<T> paramAt(int index) {
            return this.$$delegate_0.paramAt(index);
         }

         @Override
         public IReturnT<R> getReturn() {
            return this.$$delegate_0.getReturn();
         }

         @Override
         public IWithSubFieldsT getSubFields(ILocalT<?> $this$subFields) {
            return this.$$delegate_0.getSubFields(`$this$subFields`);
         }

         @Override
         public <T> IOperatorFactory.IAttributeGetSet getAttr(ILocalT<T> $this$attr) {
            return this.$$delegate_0.getAttr(`$this$attr`);
         }

         @Override
         public <T> IAttribute<TaintProperty, java.util.Set<ITaintType>> getTaint(ILocalT<T> $this$taint) {
            return this.$$delegate_0.getTaint(`$this$taint`);
         }

         @Override
         public <T> void setTaint(ILocalT<T> $this$taint, IAttribute<TaintProperty, java.util.Set<ITaintType>> var2) {
            this.$$delegate_0.setTaint(`$this$taint`, var2);
         }

         @Override
         public <T> IAttribute<ViaProperty, java.util.Set<IViaType>> getVia(ILocalT<T> $this$via) {
            return this.$$delegate_0.getVia(`$this$via`);
         }

         @Override
         public <T> void setVia(ILocalT<T> $this$via, IAttribute<ViaProperty, java.util.Set<IViaType>> var2) {
            this.$$delegate_0.setVia(`$this$via`, var2);
         }

         @Override
         public <T> ILocalValue<T> getValue(ILocalT<T> $this$value) {
            return this.$$delegate_0.getValue(`$this$value`);
         }

         @Override
         public <T> void setValue(ILocalT<T> $this$value, ILocalValue<T> var2) {
            this.$$delegate_0.setValue(`$this$value`, var2);
         }

         @Override
         public IStringExpr literal(java.lang.String string) {
            return this.$$delegate_0.literal(string);
         }

         @Override
         public IIntExpr literal(int var1) {
            return this.$$delegate_0.literal(var1);
         }

         @Override
         public ILongExpr literal(long var1) {
            return this.$$delegate_0.literal(var1);
         }

         @Override
         public IBoolExpr literal(boolean bool) {
            return this.$$delegate_0.literal(bool);
         }

         @Override
         public <T> IBoolExpr isConstant(ILocalT<T> $this$isConstant) {
            return this.$$delegate_0.isConstant(`$this$isConstant`);
         }

         @Override
         public IBoolExpr isConstant(ITypedExpr $this$isConstant) {
            return this.$$delegate_0.isConstant(`$this$isConstant`);
         }

         @Override
         public IBoolExpr not(IBoolExpr $this$not) {
            return this.$$delegate_0.not(`$this$not`);
         }

         @Override
         public IBoolExpr or(IBoolExpr $this$or, IBoolExpr other) {
            return this.$$delegate_0.or(`$this$or`, other);
         }

         @Override
         public IIntExpr or(IIntExpr $this$or, IIntExpr rhs) {
            return this.$$delegate_0.or(`$this$or`, rhs);
         }

         @Override
         public IBoolExpr and(IBoolExpr $this$and, IBoolExpr other) {
            return this.$$delegate_0.and(`$this$and`, other);
         }

         @Override
         public IIntExpr and(IIntExpr $this$and, IIntExpr rhs) {
            return this.$$delegate_0.and(`$this$and`, rhs);
         }

         @Override
         public IBoolExpr lt(IIntExpr $this$lt, IIntExpr rhs) {
            return this.$$delegate_0.lt(`$this$lt`, rhs);
         }

         @Override
         public IBoolExpr le(IIntExpr $this$le, IIntExpr rhs) {
            return this.$$delegate_0.le(`$this$le`, rhs);
         }

         @Override
         public IBoolExpr eq(IIntExpr $this$eq, IIntExpr rhs) {
            return this.$$delegate_0.eq(`$this$eq`, rhs);
         }

         @Override
         public IBoolExpr ge(IIntExpr $this$ge, IIntExpr rhs) {
            return this.$$delegate_0.ge(`$this$ge`, rhs);
         }

         @Override
         public IBoolExpr gt(IIntExpr $this$gt, IIntExpr rhs) {
            return this.$$delegate_0.gt(`$this$gt`, rhs);
         }

         @Override
         public IBoolExpr neq(IIntExpr $this$neq, IIntExpr rhs) {
            return this.$$delegate_0.neq(`$this$neq`, rhs);
         }

         @Override
         public IIntExpr xor(IIntExpr $this$xor, IIntExpr rhs) {
            return this.$$delegate_0.xor(`$this$xor`, rhs);
         }

         @Override
         public IIntExpr shl(IIntExpr $this$shl, IIntExpr rhs) {
            return this.$$delegate_0.shl(`$this$shl`, rhs);
         }

         @Override
         public IIntExpr shr(IIntExpr $this$shr, IIntExpr rhs) {
            return this.$$delegate_0.shr(`$this$shr`, rhs);
         }

         @Override
         public IIntExpr lshr(IIntExpr $this$lshr, IIntExpr rhs) {
            return this.$$delegate_0.lshr(`$this$lshr`, rhs);
         }

         @Override
         public IIntExpr plus(IIntExpr $this$plus, IIntExpr rhs) {
            return this.$$delegate_0.plus(`$this$plus`, rhs);
         }

         @Override
         public <T extends IClassField, V extends java.util.Set<? extends Object>> IAttribute<T, V> plus(IAttribute<T, V> $this$plus, IAttribute<T, V> set) {
            return this.$$delegate_0.plus(`$this$plus`, set);
         }

         @Override
         public IAttribute<TaintProperty, java.util.Set<ITaintType>> plus(IAttribute<TaintProperty, java.util.Set<ITaintType>> $this$plus, ITaintType single) {
            return this.$$delegate_0.plus(`$this$plus`, single);
         }

         @Override
         public IAttribute<ViaProperty, java.util.Set<IViaType>> plus(IAttribute<ViaProperty, java.util.Set<IViaType>> $this$plus, IViaType single) {
            return this.$$delegate_0.plus(`$this$plus`, single);
         }

         @Override
         public IIntExpr minus(IIntExpr $this$minus, IIntExpr rhs) {
            return this.$$delegate_0.minus(`$this$minus`, rhs);
         }

         @Override
         public <T extends IClassField, V extends java.util.Set<? extends Object>> IAttribute<T, V> minus(IAttribute<T, V> $this$minus, IAttribute<T, V> set) {
            return this.$$delegate_0.minus(`$this$minus`, set);
         }

         @Override
         public IAttribute<TaintProperty, java.util.Set<ITaintType>> minus(IAttribute<TaintProperty, java.util.Set<ITaintType>> $this$minus, ITaintType single) {
            return this.$$delegate_0.minus(`$this$minus`, single);
         }

         @Override
         public IAttribute<ViaProperty, java.util.Set<IViaType>> minus(IAttribute<ViaProperty, java.util.Set<IViaType>> $this$minus, IViaType single) {
            return this.$$delegate_0.minus(`$this$minus`, single);
         }

         @Override
         public <T> IBoolExpr getBoolean(ILocalT<T> $this$getBoolean) {
            return this.$$delegate_0.getBoolean(`$this$getBoolean`);
         }

         @Override
         public <T> IStringExpr getString(ILocalT<T> $this$getString) {
            return this.$$delegate_0.getString(`$this$getString`);
         }

         @Override
         public <T> IIntExpr getInt(ILocalT<T> $this$getInt) {
            return this.$$delegate_0.getInt(`$this$getInt`);
         }

         @Override
         public <T> ILongExpr getLong(ILocalT<T> $this$getLong) {
            return this.$$delegate_0.getLong(`$this$getLong`);
         }

         @Override
         public <T> IStringExpr getEnumName(ILocalT<T> $this$getEnumName) {
            return this.$$delegate_0.getEnumName(`$this$getEnumName`);
         }

         @Override
         public <T> IBoolExpr isInstanceOf(ILocalT<T> $this$isInstanceOf, java.lang.String parentType) {
            return this.$$delegate_0.isInstanceOf(`$this$isInstanceOf`, parentType);
         }

         @Override
         public IStringExpr toLowerCase(IStringExpr $this$toLowerCase) {
            return this.$$delegate_0.toLowerCase(`$this$toLowerCase`);
         }

         @Override
         public IBoolExpr startsWith(IStringExpr $this$startsWith, IStringExpr str) {
            return this.$$delegate_0.startsWith(`$this$startsWith`, str);
         }

         @Override
         public IBoolExpr startsWith(IStringExpr $this$startsWith, java.lang.String str) {
            return this.$$delegate_0.startsWith(`$this$startsWith`, str);
         }

         @Override
         public IBoolExpr endsWith(IStringExpr $this$endsWith, IStringExpr str) {
            return this.$$delegate_0.endsWith(`$this$endsWith`, str);
         }

         @Override
         public IBoolExpr endsWith(IStringExpr $this$endsWith, java.lang.String str) {
            return this.$$delegate_0.endsWith(`$this$endsWith`, str);
         }

         @Override
         public IBoolExpr contains(IStringExpr $this$contains, IStringExpr str) {
            return this.$$delegate_0.contains(`$this$contains`, str);
         }

         @Override
         public IBoolExpr contains(IStringExpr $this$contains, java.lang.String str) {
            return this.$$delegate_0.contains(`$this$contains`, str);
         }

         @Override
         public IBoolExpr contains(IAttribute<TaintProperty, java.util.Set<ITaintType>> $this$contains, ITaintType taint) {
            return this.$$delegate_0.contains(`$this$contains`, taint);
         }

         @Override
         public IBoolExpr stringEquals(IStringExpr $this$stringEquals, IStringExpr str) {
            return this.$$delegate_0.stringEquals(`$this$stringEquals`, str);
         }

         @Override
         public IBoolExpr stringEquals(IStringExpr $this$stringEquals, java.lang.String str) {
            return this.$$delegate_0.stringEquals(`$this$stringEquals`, str);
         }

         @Override
         public IAttribute<TaintProperty, java.util.Set<ITaintType>> taintOf(ITaintType... type) {
            return this.$$delegate_0.taintOf(type);
         }

         @Override
         public IAttribute<TaintProperty, java.util.Set<ITaintType>> taintOf(java.util.Collection<? extends ITaintType> types) {
            return this.$$delegate_0.taintOf(types);
         }

         @Override
         public IAttribute<ViaProperty, java.util.Set<IViaType>> viaOf(IViaType... via) {
            return this.$$delegate_0.viaOf(via);
         }

         @Override
         public IAttribute<TaintProperty, java.util.Set<ITaintType>> getEmptyTaint() {
            return this.$$delegate_0.getEmptyTaint();
         }

         @Override
         public IAttribute<ViaProperty, java.util.Set<IViaType>> getEmptyVia() {
            return this.$$delegate_0.getEmptyVia();
         }

         @Override
         public <T> ILocalValue<T> anyOf(ILocalT<T>... local) {
            return this.$$delegate_0.anyOf(local);
         }

         @Override
         public <T> ILocalValue<T> null() {
            return this.$$delegate_0.null();
         }

         @Override
         public IBoolExpr hasIntersection(
            IAttribute<TaintProperty, java.util.Set<ITaintType>> $this$hasIntersection, IAttribute<TaintProperty, java.util.Set<ITaintType>> taint
         ) {
            return this.$$delegate_0.hasIntersection(`$this$hasIntersection`, taint);
         }

         @Override
         public IBoolExpr containsAll(IAttribute<TaintProperty, java.util.Set<ITaintType>> $this$containsAll, ITaintType taint) {
            return this.$$delegate_0.containsAll(`$this$containsAll`, taint);
         }

         @Override
         public IBoolExpr containsAll(
            IAttribute<TaintProperty, java.util.Set<ITaintType>> $this$containsAll, IAttribute<TaintProperty, java.util.Set<ITaintType>> taint
         ) {
            return this.$$delegate_0.containsAll(`$this$containsAll`, taint);
         }

         @Override
         public <T1 extends R, T2 extends R, R> ILocalValue<R> anyOr(ILocalValue<T1> $this$anyOr, ILocalValue<T2> second) {
            return this.$$delegate_0.anyOr(`$this$anyOr`, second);
         }

         @Override
         public <T1 extends R, T2 extends R, R> ILocalValue<R> anyOr(ILocalT<T1> $this$anyOr, ILocalT<T2> second) {
            return this.$$delegate_0.anyOr(`$this$anyOr`, second);
         }

         @Override
         public <T> IAccessPathT<Object> field(ILocalT<T> $this$field, java.lang.String declaringClass, java.lang.String fieldName, java.lang.String fieldType) {
            return this.$$delegate_0.field(`$this$field`, declaringClass, fieldName, fieldType);
         }

         @Override
         public <T> IAccessPathT<Object> field(ILocalT<T> $this$field, SootField field) {
            return this.$$delegate_0.field(`$this$field`, field);
         }

         @Override
         public <T, FieldType> IAccessPathT<FieldType> field(
            ILocalT<T> $this$field, java.lang.String declaringClass, java.lang.String fieldName, KClass<FieldType> type
         ) {
            return this.$$delegate_0.field(`$this$field`, declaringClass, fieldName, type);
         }

         @Override
         public <T, FieldType> IAccessPathT<FieldType> field(
            ILocalT<T> $this$field, KClass<?> declaringClass, java.lang.String fieldName, KClass<FieldType> type
         ) {
            return this.$$delegate_0.field(`$this$field`, declaringClass, fieldName, type);
         }

         @Override
         public <T> IAccessPathT<Object> field(ILocalT<T> $this$field, KClass<?> declaringClass, java.lang.String fieldName, java.lang.String fieldType) {
            return this.$$delegate_0.field(`$this$field`, declaringClass, fieldName, fieldType);
         }

         @Override
         public <T> IAccessPathT<Object> field(ILocalT<T> $this$field, IClassField field) {
            return this.$$delegate_0.field(`$this$field`, field);
         }

         @Override
         public <T, F> IAccessPathT<F> field(ILocalT<T> $this$field, KProperty<? extends F> field) {
            return this.$$delegate_0.field(`$this$field`, field);
         }

         @Override
         public <T, FieldType> IAccessPathT<FieldType> field(ILocalT<T> $this$field, KProperty<?> field, KClass<FieldType> type) {
            return this.$$delegate_0.field(`$this$field`, field, type);
         }

         @Override
         public void addStmt(IStmt stmt) {
            this.$$delegate_0.addStmt(stmt);
         }

         @Override
         public void check(IBoolExpr expr, CheckType checkType, Function1<? super BugMessage.Env, Unit> env) {
            this.$$delegate_0.check(expr, checkType, env);
         }

         @Override
         public void check(ILocalT<java.lang.Boolean> expr, CheckType checkType, Function1<? super BugMessage.Env, Unit> env) {
            this.$$delegate_0.check(expr, checkType, env);
         }

         @Override
         public void eval(IExpr expr, Function1<Object, Unit> result) {
            this.$$delegate_0.eval(expr, result);
         }

         @Override
         public void eval(IBoolExpr expr, Function1<? super java.lang.Boolean, Unit> result) {
            this.$$delegate_0.eval(expr, result);
         }

         @Override
         public void eval(IStringExpr expr, Function1<? super java.lang.String, Unit> result) {
            this.$$delegate_0.eval(expr, result);
         }

         @Override
         public void eval(IIntExpr expr, Function1<? super Integer, Unit> result) {
            this.$$delegate_0.eval(expr, result);
         }
      };
   }

   override fun model(
      config: (MethodConfig?) -> Unit,
      block: (ISMethodDecl9CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8>?, IParameterT<P0>?, IParameterT<P1>?, IParameterT<P2>?, IParameterT<P3>?, IParameterT<P4>?, IParameterT<P5>?, IParameterT<P6>?, IParameterT<P7>?, IParameterT<P8>?) -> Unit
   ): ISMethodDecl9<R, P0, P1, P2, P3, P4, P5, P6, P7, P8> {
      return ISMethodDecl9.DefaultImpls.model(this, config, block);
   }

   override fun modelNoArg(config: (MethodConfig?) -> Unit, block: (ISMethodDecl9CheckBuilder<R, P0, P1, P2, P3, P4, P5, P6, P7, P8>?) -> Unit): ISMethodDecl9<R, P0, P1, P2, P3, P4, P5, P6, P7, P8> {
      return ISMethodDecl9.DefaultImpls.modelNoArg(this, config, block);
   }

   public override fun eachLocalVar(block: (ISootLocalVarDecl<Any>) -> Unit) {
      this.base.eachLocalVar(block);
   }
}
