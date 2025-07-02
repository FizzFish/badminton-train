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
import com.feysh.corax.config.api.IMethodDecl
import com.feysh.corax.config.api.IMethodMatch
import com.feysh.corax.config.api.IOperatorFactory
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.IReturnT
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
import com.feysh.corax.config.api.BugMessage.Env
import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import soot.Body
import soot.Local
import soot.Scene
import soot.SootField
import soot.SootMethod
import soot.jimple.JimpleBody

public open class MethodDeclBase<R>(analyzeConfig: AIAnalysisBaseImpl, match: IMethodMatch, error: Error = analyzeConfig.getError()) : IMethodDecl<R> {
   public open val analyzeConfig: AIAnalysisBaseImpl
   public open val match: IMethodMatch
   public open val error: Error

   init {
      this.analyzeConfig = analyzeConfig;
      this.match = match;
      this.error = error;
   }

   public override fun eachLocalVar(block: (ISootLocalVarDecl<Any>) -> Unit) {
      val var10000: IMethodMatch = this.getMatch();
      val var10001: Scene = Scene.v();

      for (SootMethod sm : var10000.matched(var10001)) {
         if (sm.hasActiveBody()) {
            val local: Body = sm.getActiveBody();
            val var10: JimpleBody = local as? JimpleBody;
            if ((local as? JimpleBody) != null) {
               val var11: java.util.Iterator = var10.getLocals().iterator();
               val var5: java.util.Iterator = var11;

               while (var5.hasNext()) {
                  val var9: Local = var5.next() as Local;
                  val var10002: ILocalVarDecl = new BaseLocalVarDecl(this.getAnalyzeConfig(), new ILocalVarMatch(var9) {
                     {
                        this.$local = `$local`;
                     }

                     @Override
                     public java.util.List<Local> matched(Scene scene) {
                        return CollectionsKt.listOf(this.$local);
                     }
                  });
                  block.invoke(new BaseSootLocalVarDecl(var10002, var9));
               }
            }
         }
      }
   }

   public open fun checkBuilder(config: (MethodConfig) -> Unit): MethodDeclBase.CheckBuilder<Any> {
      return new MethodDeclBase.CheckBuilder<>(this, config, this);
   }

   public override fun toString(): String {
      return "method decl: ${this.getMatch()}";
   }

   override fun getArgumentCnt(): Int? {
      return IMethodDecl.DefaultImpls.getArgumentCnt(this);
   }

   override fun getActualType(): KFunctionType? {
      return IMethodDecl.DefaultImpls.getActualType(this);
   }

   public inner class CheckBuilder<R>(config: (MethodConfig) -> Unit, method: IMethodDecl<Any>) : IMethodDecl.CheckBuilder<R>, IBaseOperatorFactory {
      public open val config: (MethodConfig) -> Unit
      public open val method: IMethodDecl<Any>
      public open val `return`: IReturnT<Any>
      public open val global: ILocalT<Any>

      init {
         this.this$0 = `this$0`;
         this.config = config;
         this.method = method;
         this.return = new Return<>();
         this.global = Global.INSTANCE;
      }

      public override fun <T> paramAt(index: Int): IParameterT<T> {
         val argumentCnt: Int = this.this$0.getArgumentCnt();
         if (argumentCnt != null && index >= argumentCnt) {
            this.this$0.getError().error("parameter index \"$index\" out of range. argument count is $argumentCnt of method: ${this.this$0.getMatch()}.");
         }

         return new Parameter(index);
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
         return IMethodDecl.CheckBuilder.DefaultImpls.plus(this, `$this$plus`, single);
      }

      override fun IAttribute<ViaProperty, java.utilSet<IViaType>>.plus(single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IMethodDecl.CheckBuilder.DefaultImpls.plus(this, `$this$plus`, single);
      }

      override fun IIntExpr.minus(rhs: IIntExpr): IIntExpr {
         return IBaseOperatorFactory.DefaultImpls.minus(this, `$this$minus`, rhs);
      }

      override fun <T extends IClassField, V extends java.util.Set<? extends Object>> IAttribute<T, V>.minus(set: IAttribute<T, V>): Attribute<T, V> {
         return IBaseOperatorFactory.DefaultImpls.minus(this, `$this$minus`, set);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.minus(single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IMethodDecl.CheckBuilder.DefaultImpls.minus(this, `$this$minus`, single);
      }

      override fun IAttribute<ViaProperty, java.utilSet<IViaType>>.minus(single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IMethodDecl.CheckBuilder.DefaultImpls.minus(this, `$this$minus`, single);
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
         return IMethodDecl.CheckBuilder.DefaultImpls.startsWith(this, `$this$startsWith`, str);
      }

      override fun IStringExpr.endsWith(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.endsWith(this, `$this$endsWith`, str);
      }

      override fun IStringExpr.endsWith(str: java.lang.String): IBoolExpr {
         return IMethodDecl.CheckBuilder.DefaultImpls.endsWith(this, `$this$endsWith`, str);
      }

      override fun IStringExpr.contains(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.contains(this, `$this$contains`, str);
      }

      override fun IStringExpr.contains(str: java.lang.String): IBoolExpr {
         return IMethodDecl.CheckBuilder.DefaultImpls.contains(this, `$this$contains`, str);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.contains(taint: ITaintType): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.contains(this, `$this$contains`, taint);
      }

      override fun IStringExpr.stringEquals(str: IStringExpr): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.stringEquals(this, `$this$stringEquals`, str);
      }

      override fun IStringExpr.stringEquals(str: java.lang.String): IBoolExpr {
         return IMethodDecl.CheckBuilder.DefaultImpls.stringEquals(this, `$this$stringEquals`, str);
      }

      override fun taintOf(vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IMethodDecl.CheckBuilder.DefaultImpls.taintOf(this, type);
      }

      override fun taintOf(types: MutableCollection<ITaintType>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IBaseOperatorFactory.DefaultImpls.taintOf(this, types);
      }

      override fun viaOf(vararg via: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IBaseOperatorFactory.DefaultImpls.viaOf(this, via);
      }

      override fun getEmptyTaint(): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(this);
      }

      override fun getEmptyVia(): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(this);
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
         return IMethodDecl.CheckBuilder.DefaultImpls.containsAll(this, `$this$containsAll`, taint);
      }

      override fun IAttribute<TaintProperty, java.utilSet<ITaintType>>.containsAll(taint: IAttribute<TaintProperty, java.utilSet<ITaintType>>): IBoolExpr {
         return IBaseOperatorFactory.DefaultImpls.containsAll(this, `$this$containsAll`, taint);
      }

      override fun <T1 extends R, T2 extends R, R> ILocalValue<T1>.anyOr(second: ILocalValue<T2>): ILocalValue<R> {
         return IBaseOperatorFactory.DefaultImpls.anyOr(this, `$this$anyOr`, second);
      }

      override fun <T1 extends R, T2 extends R, R> ILocalT<T1>.anyOr(second: ILocalT<T2>): ILocalValue<R> {
         return IMethodDecl.CheckBuilder.DefaultImpls.anyOr(this, `$this$anyOr`, second);
      }

      override fun <T> ILocalT<T>.field(declaringClass: java.lang.String?, fieldName: java.lang.String, fieldType: java.lang.String?): IAccessPathT<Object> {
         return IBaseOperatorFactory.DefaultImpls.field(this, `$this$field`, declaringClass, fieldName, fieldType);
      }

      override fun <T> ILocalT<T>.field(field: SootField): IAccessPathT<Object> {
         return IMethodDecl.CheckBuilder.DefaultImpls.field(this, `$this$field`, field);
      }

      override fun <T, FieldType> ILocalT<T>.field(declaringClass: java.lang.String?, fieldName: java.lang.String, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return IBaseOperatorFactory.DefaultImpls.field(this, `$this$field`, declaringClass, fieldName, type);
      }

      override fun <T, FieldType> ILocalT<T>.field(declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return IMethodDecl.CheckBuilder.DefaultImpls.field(this, `$this$field`, declaringClass, fieldName, type);
      }

      override fun <T> ILocalT<T>.field(declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?): IAccessPathT<Object> {
         return IMethodDecl.CheckBuilder.DefaultImpls.field(this, `$this$field`, declaringClass, fieldName, fieldType);
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
         IMethodDecl.CheckBuilder.DefaultImpls.check(this, expr, checkType, env);
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
}
