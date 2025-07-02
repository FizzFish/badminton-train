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
import com.feysh.corax.config.api.ISootClassDecl
import com.feysh.corax.config.api.ISootLocalVarDecl
import com.feysh.corax.config.api.ISootMethodDecl
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
import com.feysh.corax.config.api.utils.UtilsKt
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import soot.SootClass
import soot.SootField
import soot.SootMethod
import soot.tagkit.AnnotationTag
import soot.tagkit.Tag
import soot.tagkit.VisibilityAnnotationTag
import soot.tagkit.VisibilityParameterAnnotationTag

public class BaseSootMethodDecl<R>(delegate: IMethodDecl<Any>, clazz: ISootClassDecl, sootMethod: SootMethod) : ISootMethodDecl<R>, IMethodDecl<R> {
   public final val delegate: IMethodDecl<Any>
   public open val clazz: ISootClassDecl
   public open val sootMethod: SootMethod

   public open val actualType: KFunctionType
      public open get() {
         return UtilsKt.getFunctionType(this.getSootMethod());
      }


   public open val argumentCnt: Int
      public open get() {
         return this.getSootMethod().getParameterCount();
      }


   public open val error: Error
   public open val match: IMethodMatch

   init {
      this.delegate = delegate;
      this.clazz = clazz;
      this.sootMethod = sootMethod;
   }

   public override fun toString(): String {
      return "soot decl: ${this.getSootMethod()}";
   }

   public open fun checkBuilder(config: (MethodConfig) -> Unit): BaseSootMethodDecl.CheckBuilder<Any> {
      return new BaseSootMethodDecl.CheckBuilder<>(this, this as ISootMethodDecl, this.delegate.checkBuilder(config));
   }

   override fun getSootClass(): SootClass {
      return ISootMethodDecl.DefaultImpls.getSootClass(this);
   }

   override fun getParameterAnnotationTag(): VisibilityParameterAnnotationTag? {
      return ISootMethodDecl.DefaultImpls.getParameterAnnotationTag(this);
   }

   override fun IParameterT<?>.getVisibilityAnnotationTag(): VisibilityAnnotationTag? {
      return ISootMethodDecl.DefaultImpls.getVisibilityAnnotationTag(this, `$this$visibilityAnnotationTag`);
   }

   override fun getVisibilityAnnotationTag(): VisibilityAnnotationTag? {
      return ISootMethodDecl.DefaultImpls.getVisibilityAnnotationTag(this);
   }

   override fun IParameterT<?>.getAnnotationTag(): MutableList<AnnotationTag> {
      return ISootMethodDecl.DefaultImpls.getAnnotationTag(this, `$this$annotationTag`);
   }

   override fun getAnnotationTag(): AnnotationTag? {
      return ISootMethodDecl.DefaultImpls.getAnnotationTag(this);
   }

   override fun getTags(): MutableList<Tag> {
      return ISootMethodDecl.DefaultImpls.getTags(this);
   }

   override fun model(config: (MethodConfig?) -> Unit, block: (ISootMethodDeclCheckBuilder<R>?, Array<IParameterT<Object>>?) -> Unit): ISootMethodDecl<R> {
      return ISootMethodDecl.DefaultImpls.model(this, config, block);
   }

   override fun modelNoArg(config: (MethodConfig?) -> Unit, block: (ISootMethodDeclCheckBuilder<R>?) -> Unit): ISootMethodDecl<R> {
      return ISootMethodDecl.DefaultImpls.modelNoArg(this, config, block);
   }

   public override fun eachLocalVar(block: (ISootLocalVarDecl<Any>) -> Unit) {
      this.delegate.eachLocalVar(block);
   }

   public inner class CheckBuilder<R>(method: ISootMethodDecl<Any>, delegate: com.feysh.corax.config.api.IMethodDecl.CheckBuilder<Any>) :
      ISootMethodDecl.CheckBuilder<R>,
      IMethodDecl.CheckBuilder<R> {
      public open val method: ISootMethodDecl<Any>
      public open val `return`: SootReturn<Any>
      public open val attr: IAttributeGetSet
      public open val config: (MethodConfig) -> Unit

      public open val emptyTaint: IAttribute<TaintProperty, Set<ITaintType>>
         public open get() {
            return this.$$delegate_0.getEmptyTaint();
         }


      public open val emptyVia: IAttribute<ViaProperty, Set<IViaType>>
         public open get() {
            return this.$$delegate_0.getEmptyVia();
         }


      public open val global: ILocalT<Any>
      public open val isConstant: IBoolExpr
      public open val isConstant: IBoolExpr
      public open val subFields: IWithSubFieldsT

      public open var taint: IAttribute<TaintProperty, Set<ITaintType>>
         internal final set

      public open var value: ILocalValue<T>
         internal final set

      public open var via: IAttribute<ViaProperty, Set<IViaType>>
         internal final set

      init {
         this.this$0 = `this$0`;
         this.$$delegate_0 = delegate;
         this.method = method;
         this.return = new SootReturn<>(this.this$0.getSootMethod());
      }

      public override fun <T> paramAt(index: Int): SootParameter<T> {
         val argumentCnt: Int = this.this$0.getArgumentCnt();
         if (index >= argumentCnt) {
            this.this$0.getError().error("parameter index \"$index\" out of range. argument count is $argumentCnt of method: ${this.this$0.getSootMethod()}.");
         }

         return new SootParameter(index, this.this$0.getSootMethod());
      }

      override fun getThis(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getThis(this);
      }

      override fun getP0(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP0(this);
      }

      override fun getP1(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP1(this);
      }

      override fun getP2(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP2(this);
      }

      override fun getP3(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP3(this);
      }

      override fun getP4(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP4(this);
      }

      override fun getP5(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP5(this);
      }

      override fun getP6(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP6(this);
      }

      override fun getP7(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP7(this);
      }

      override fun getP8(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP8(this);
      }

      override fun getP9(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP9(this);
      }

      override fun getP10(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP10(this);
      }

      override fun getP11(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP11(this);
      }

      override fun getP12(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP12(this);
      }

      override fun getP13(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP13(this);
      }

      override fun getP14(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP14(this);
      }

      override fun getP15(): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.getP15(this);
      }

      override fun parameter(index: Int): SootParameter<Object> {
         return ISootMethodDecl.CheckBuilder.DefaultImpls.parameter(this, index);
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
}
