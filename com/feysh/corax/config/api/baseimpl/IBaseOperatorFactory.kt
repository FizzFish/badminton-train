package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.BinOp
import com.feysh.corax.config.api.BugMessage
import com.feysh.corax.config.api.CheckType
import com.feysh.corax.config.api.ClassField
import com.feysh.corax.config.api.CustomAttributeID
import com.feysh.corax.config.api.IAccessPathT
import com.feysh.corax.config.api.IAttribute
import com.feysh.corax.config.api.IBoolExpr
import com.feysh.corax.config.api.IClassField
import com.feysh.corax.config.api.IExpr
import com.feysh.corax.config.api.IIexConst
import com.feysh.corax.config.api.IIntExpr
import com.feysh.corax.config.api.ILocalT
import com.feysh.corax.config.api.ILocalValue
import com.feysh.corax.config.api.ILongExpr
import com.feysh.corax.config.api.IOperatorFactory
import com.feysh.corax.config.api.IStringExpr
import com.feysh.corax.config.api.ITaintType
import com.feysh.corax.config.api.ITypedExpr
import com.feysh.corax.config.api.IViaType
import com.feysh.corax.config.api.IWithSubFieldsT
import com.feysh.corax.config.api.IexConstNull
import com.feysh.corax.config.api.TaintProperty
import com.feysh.corax.config.api.UnOp
import com.feysh.corax.config.api.ViaProperty
import com.feysh.corax.config.api.IIexConst.Type
import com.feysh.corax.config.api.IOperatorFactory.IAttributeGetSet
import com.feysh.corax.config.api.utils.UtilsKt
import kotlin.jvm.functions.Function1
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import soot.SootField

public interface IBaseOperatorFactory : IOperatorFactory {
   public open val attr: IAttributeGetSet
      public open get() {
      }


   public open var taint: IAttribute<TaintProperty, Set<ITaintType>>
      public open get() {
      }

      public open set(value) {
      }


   public open var value: ILocalValue<T>
      public open get() {
      }

      public open set(value) {
      }


   public open var via: IAttribute<ViaProperty, Set<IViaType>>
      public open get() {
      }

      public open set(value) {
      }


   public open val subFields: IWithSubFieldsT
      public open get() {
      }


   public open val isConstant: IBoolExpr
      public open get() {
      }


   public open val isConstant: IBoolExpr
      public open get() {
      }


   public override fun viaOf(vararg via: IViaType): IAttribute<ViaProperty, Set<IViaType>> {
   }

   public override fun <T> `null`(): ILocalValue<T> {
   }

   public override fun <T> anyOf(vararg local: ILocalT<T>): ILocalValue<T> {
   }

   public override fun taintOf(types: Collection<ITaintType>): IAttribute<TaintProperty, Set<ITaintType>> {
   }

   public open fun const(const: Any, type: Type): IExpr {
   }

   public override fun literal(bool: Boolean): IBoolExpr {
   }

   public override fun literal(string: String): IStringExpr {
   }

   public override fun literal(int: Int): IIntExpr {
   }

   public override fun literal(long: Long): ILongExpr {
   }

   public open infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.plus(set: IAttribute<T, V>): Attribute<T, V> {
   }

   public open infix operator fun <T : IClassField, V : Set<Any>> IAttribute<T, V>.minus(set: IAttribute<T, V>): Attribute<T, V> {
   }

   public override infix fun <T1 : R, T2 : R, R> ILocalValue<T1>.anyOr(second: ILocalValue<T2>): ILocalValue<R> {
   }

   public override operator fun IBoolExpr.not(): IBoolExpr {
   }

   public override infix fun IBoolExpr.or(other: IBoolExpr): IBoolExpr {
   }

   public override infix fun IBoolExpr.and(other: IBoolExpr): IBoolExpr {
   }

   public open fun IIntExpr.compareBinOp(op: BinOp, rhs: IIntExpr): IBoolExpr {
   }

   public open fun IIntExpr.arithmeticBinOp(op: BinOp, rhs: IIntExpr): IIntExpr {
   }

   public open fun IIntExpr.bitwiseBinOp(op: BinOp, rhs: IIntExpr): IIntExpr {
   }

   public override infix fun IIntExpr.lt(rhs: IIntExpr): IBoolExpr {
   }

   public override infix fun IIntExpr.le(rhs: IIntExpr): IBoolExpr {
   }

   public override infix fun IIntExpr.eq(rhs: IIntExpr): IBoolExpr {
   }

   public override infix fun IIntExpr.ge(rhs: IIntExpr): IBoolExpr {
   }

   public override infix fun IIntExpr.gt(rhs: IIntExpr): IBoolExpr {
   }

   public override infix fun IIntExpr.neq(rhs: IIntExpr): IBoolExpr {
   }

   public override infix fun IIntExpr.and(rhs: IIntExpr): IIntExpr {
   }

   public override infix fun IIntExpr.or(rhs: IIntExpr): IIntExpr {
   }

   public override infix fun IIntExpr.xor(rhs: IIntExpr): IIntExpr {
   }

   public override infix fun IIntExpr.shl(rhs: IIntExpr): IIntExpr {
   }

   public override infix fun IIntExpr.shr(rhs: IIntExpr): IIntExpr {
   }

   public override infix fun IIntExpr.lshr(rhs: IIntExpr): IIntExpr {
   }

   public override infix operator fun IIntExpr.plus(rhs: IIntExpr): IIntExpr {
   }

   public override infix operator fun IIntExpr.minus(rhs: IIntExpr): IIntExpr {
   }

   public override fun <T> ILocalT<T>.getBoolean(): IBoolExpr {
   }

   public override fun <T> ILocalT<T>.getString(): IStringExpr {
   }

   public override fun <T> ILocalT<T>.getInt(): IIntExpr {
   }

   public override fun <T> ILocalT<T>.getLong(): ILongExpr {
   }

   public override fun <T> ILocalT<T>.getEnumName(): IStringExpr {
   }

   public override fun <T> ILocalT<T>.isInstanceOf(parentType: String): IBoolExpr {
   }

   public override fun IStringExpr.toLowerCase(): IStringExpr {
   }

   public override fun IStringExpr.startsWith(str: IStringExpr): IBoolExpr {
   }

   public override fun IStringExpr.endsWith(str: IStringExpr): IBoolExpr {
   }

   public override fun IStringExpr.contains(str: IStringExpr): IBoolExpr {
   }

   public override fun IStringExpr.stringEquals(str: IStringExpr): IBoolExpr {
   }

   public override fun IAttribute<TaintProperty, Set<ITaintType>>.hasIntersection(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
   }

   public override fun IAttribute<TaintProperty, Set<ITaintType>>.containsAll(taint: IAttribute<TaintProperty, Set<ITaintType>>): IBoolExpr {
   }

   public override fun IAttribute<TaintProperty, Set<ITaintType>>.contains(taint: ITaintType): IBoolExpr {
   }

   public override fun <T> ILocalT<T>.field(declaringClass: String?, fieldName: String, fieldType: String?): IAccessPathT<Any> {
   }

   public override fun <T, FieldType : Any> ILocalT<T>.field(declaringClass: String?, fieldName: String, type: KClass<FieldType>): IAccessPathT<FieldType> {
   }

   public override fun <T, F> ILocalT<T>.field(field: KProperty<F>): IAccessPathT<F> {
   }

   public override fun <T> ILocalT<T>.field(field: IClassField): IAccessPathT<Any> {
   }

   public override fun <T, FieldType : Any> ILocalT<T>.field(field: KProperty<*>, type: KClass<FieldType>): IAccessPathT<FieldType> {
   }

   public override fun eval(expr: IBoolExpr, result: (Boolean) -> Unit) {
   }

   public override fun eval(expr: IIntExpr, result: (Int) -> Unit) {
   }

   public override fun eval(expr: IStringExpr, result: (String) -> Unit) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <T> getAttr(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): IOperatorFactory.IAttributeGetSet {
         return new IOperatorFactory.IAttributeGetSet(`$receiver`, `$this`) {
            {
               this.$this_attr = `$receiver`;
               this.this$0 = `$receiver`;
            }

            @Override
            public <T> IAccessPathT<T> get(CustomAttributeID<T> id) {
               return new AccessPath<>(this.$this_attr.getExpr(), id.getAttributeName());
            }

            public final <T> void setAny(CustomAttributeID<T> id, IExpr value) {
               this.this$0.addStmt(new IstSetField(this.$this_attr.getExpr(), id.getAttributeName(), value));
            }

            @Override
            public <T> void set(CustomAttributeID<T> id, ILocalValue<T> value) {
               this.setAny(id, value.getRvalue());
            }

            @Override
            public void set(CustomAttributeID<java.lang.Boolean> id, boolean value) {
               this.setAny(id, this.this$0.const(value, IIexConst.Type.Boolean));
            }

            @Override
            public void set(CustomAttributeID<java.lang.Short> id, short value) {
               this.setAny(id, this.this$0.const(value, IIexConst.Type.Short));
            }

            @Override
            public void set(CustomAttributeID<Integer> id, int value) {
               this.setAny(id, this.this$0.const(value, IIexConst.Type.Int));
            }

            @Override
            public void set(CustomAttributeID<java.lang.Long> id, long value) {
               this.setAny(id, this.this$0.const(value, IIexConst.Type.Long));
            }

            @Override
            public void set(CustomAttributeID<java.lang.String> id, java.lang.String value) {
               this.setAny(id, this.this$0.const(value, IIexConst.Type.String));
            }

            @Override
            public <T> void set(CustomAttributeID<T> id, ITypedExpr value) {
               this.setAny(id, value.getExpr());
            }
         };
      }

      @JvmStatic
      fun <T> getTaint(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return new TaintSet(new UnOpExpr(UnOp.GetSet, IexGetFieldExpr.Companion.invoke(`$receiver`.getExpr(), TaintProperty.INSTANCE)));
      }

      @JvmStatic
      fun <T> setTaint(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, value: IAttribute<TaintProperty, java.utilSet<ITaintType>>) {
         `$this`.addStmt(new IstSetField(`$receiver`.getExpr(), TaintProperty.INSTANCE, value.getValue()));
      }

      @JvmStatic
      fun <T> getValue(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): ILocalValue<T> {
         return new LocalValue<>(`$receiver`);
      }

      @JvmStatic
      fun <T> setValue(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, value: ILocalValue<T>) {
         `$this`.addStmt(new LocalValue(`$receiver`).setValue(value.getRvalue()));
      }

      @JvmStatic
      fun <T> getVia(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return new ViaSet(new UnOpExpr(UnOp.GetSet, IexGetFieldExpr.Companion.invoke(`$receiver`.getExpr(), ViaProperty.INSTANCE)));
      }

      @JvmStatic
      fun <T> setVia(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, value: IAttribute<ViaProperty, java.utilSet<IViaType>>) {
         `$this`.addStmt(new IstSetField(`$receiver`.getExpr(), ViaProperty.INSTANCE, value.getValue()));
      }

      @JvmStatic
      fun getSubFields(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<?>): IWithSubFieldsT {
         return new WithSubFields(`$receiver`.getExpr());
      }

      @JvmStatic
      fun viaOf(`$this`: IBaseOperatorFactory, vararg via: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return new ViaSet(`$this`.const(ArraysKt.toSet(via), IIexConst.Type.ViaSet));
      }

      @JvmStatic
      fun <T> `null`(`$this`: IBaseOperatorFactory): ILocalValue<T> {
         return new RValue<>(`$this`.const(SetsKt.setOf(IexConstNull.INSTANCE), IIexConst.Type.NULL));
      }

      @JvmStatic
      fun <T> anyOf(`$this`: IBaseOperatorFactory, vararg local: ILocalT<T>): ILocalValue<T> {
         var expr: IExpr = null;

         for (IndexedValue i : ArraysKt.withIndex(local)) {
            val var10000: IExpr;
            if (i.getIndex() != 0) {
               val var10002: BinOp = BinOp.AnyOf;
               var10000 = new BinOpExpr(var10002, expr, (i.getValue() as ILocalT).getExpr());
            } else {
               var10000 = (ArraysKt.first(local) as ILocalT).getExpr();
            }

            expr = var10000;
         }

         if (expr == null) {
            expr = `$this`.const(SetsKt.emptySet(), IIexConst.Type.EmptyElement);
         }

         return new RValue<>(expr);
      }

      @JvmStatic
      fun taintOf(`$this`: IBaseOperatorFactory, types: MutableCollection<ITaintType>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return new TaintSet(`$this`.const(CollectionsKt.toSet(types), IIexConst.Type.TaintSet));
      }

      @JvmStatic
      fun const(`$this`: IBaseOperatorFactory, var1: Any, type: IIexConst.Type): IExpr {
         return new IexConst(var1, type);
      }

      @JvmStatic
      fun literal(`$this`: IBaseOperatorFactory, bool: Boolean): IBoolExpr {
         return new BoolExpr(`$this`.const(bool, IIexConst.Type.Boolean));
      }

      @JvmStatic
      fun literal(`$this`: IBaseOperatorFactory, string: java.lang.String): IStringExpr {
         return new StringExpr(`$this`.const(string, IIexConst.Type.String));
      }

      @JvmStatic
      fun literal(`$this`: IBaseOperatorFactory, var1: Int): IIntExpr {
         return new IntExpr(`$this`.const(var1, IIexConst.Type.Int));
      }

      @JvmStatic
      fun literal(`$this`: IBaseOperatorFactory, var1: Long): ILongExpr {
         return new LongExpr(`$this`.const(var1, IIexConst.Type.Long));
      }

      @JvmStatic
      fun <T> isConstant(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): IBoolExpr {
         return new BoolExpr(new UnOpExpr(UnOp.IsConstant, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun isConstant(`$this`: IBaseOperatorFactory, `$receiver`: ITypedExpr): IBoolExpr {
         return new BoolExpr(new UnOpExpr(UnOp.IsConstant, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun <T extends IClassField, V extends java.util.Set<? extends Object>> plus(
         `$this`: IBaseOperatorFactory, `$receiver`: IAttribute<T, V>, set: IAttribute<T, V>
      ): Attribute<T, V> {
         return new Attribute<>(new BinOpExpr(BinOp.OrSet, `$receiver`.getValue(), set.getValue()));
      }

      @JvmStatic
      fun <T extends IClassField, V extends java.util.Set<? extends Object>> minus(
         `$this`: IBaseOperatorFactory, `$receiver`: IAttribute<T, V>, set: IAttribute<T, V>
      ): Attribute<T, V> {
         return new Attribute<>(new BinOpExpr(BinOp.RemoveSet, `$receiver`.getValue(), set.getValue()));
      }

      @JvmStatic
      fun <T1 extends R, T2 extends R, R> anyOr(`$this`: IBaseOperatorFactory, `$receiver`: ILocalValue<T1>, second: ILocalValue<T2>): ILocalValue<R> {
         return new RValue<>(new BinOpExpr(BinOp.AnyOf, `$receiver`.getRvalue(), second.getRvalue()));
      }

      @JvmStatic
      fun not(`$this`: IBaseOperatorFactory, `$receiver`: IBoolExpr): IBoolExpr {
         return new BoolExpr(new UnOpExpr(UnOp.Not, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun or(`$this`: IBaseOperatorFactory, `$receiver`: IBoolExpr, other: IBoolExpr): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.Or, `$receiver`.getExpr(), other.getExpr()));
      }

      @JvmStatic
      fun and(`$this`: IBaseOperatorFactory, `$receiver`: IBoolExpr, other: IBoolExpr): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.And, `$receiver`.getExpr(), other.getExpr()));
      }

      @JvmStatic
      fun compareBinOp(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, op: BinOp, rhs: IIntExpr): IBoolExpr {
         return new BoolExpr(new BinOpExpr(op, `$receiver`.getExpr(), rhs.getExpr()));
      }

      @JvmStatic
      fun arithmeticBinOp(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, op: BinOp, rhs: IIntExpr): IIntExpr {
         return new IntExpr(new BinOpExpr(op, `$receiver`.getExpr(), rhs.getExpr()));
      }

      @JvmStatic
      fun bitwiseBinOp(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, op: BinOp, rhs: IIntExpr): IIntExpr {
         return new IntExpr(new BinOpExpr(op, `$receiver`.getExpr(), rhs.getExpr()));
      }

      @JvmStatic
      fun lt(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IBoolExpr {
         return `$this`.compareBinOp(`$receiver`, BinOp.LT, rhs);
      }

      @JvmStatic
      fun le(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IBoolExpr {
         return `$this`.compareBinOp(`$receiver`, BinOp.LE, rhs);
      }

      @JvmStatic
      fun eq(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IBoolExpr {
         return `$this`.compareBinOp(`$receiver`, BinOp.EQ, rhs);
      }

      @JvmStatic
      fun ge(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IBoolExpr {
         return `$this`.compareBinOp(`$receiver`, BinOp.GE, rhs);
      }

      @JvmStatic
      fun gt(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IBoolExpr {
         return `$this`.compareBinOp(`$receiver`, BinOp.GT, rhs);
      }

      @JvmStatic
      fun neq(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IBoolExpr {
         return `$this`.not(`$this`.compareBinOp(`$receiver`, BinOp.EQ, rhs));
      }

      @JvmStatic
      fun and(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IIntExpr {
         return `$this`.bitwiseBinOp(`$receiver`, BinOp.BvAnd, rhs);
      }

      @JvmStatic
      fun or(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IIntExpr {
         return `$this`.bitwiseBinOp(`$receiver`, BinOp.BvOr, rhs);
      }

      @JvmStatic
      fun xor(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IIntExpr {
         return `$this`.bitwiseBinOp(`$receiver`, BinOp.BvXor, rhs);
      }

      @JvmStatic
      fun shl(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IIntExpr {
         return `$this`.bitwiseBinOp(`$receiver`, BinOp.BvShl, rhs);
      }

      @JvmStatic
      fun shr(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IIntExpr {
         return `$this`.bitwiseBinOp(`$receiver`, BinOp.BvShr, rhs);
      }

      @JvmStatic
      fun lshr(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IIntExpr {
         return `$this`.bitwiseBinOp(`$receiver`, BinOp.BvLShr, rhs);
      }

      @JvmStatic
      fun plus(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IIntExpr {
         return `$this`.arithmeticBinOp(`$receiver`, BinOp.Add, rhs);
      }

      @JvmStatic
      fun minus(`$this`: IBaseOperatorFactory, `$receiver`: IIntExpr, rhs: IIntExpr): IIntExpr {
         return `$this`.arithmeticBinOp(`$receiver`, BinOp.Sub, rhs);
      }

      @JvmStatic
      fun <T> getBoolean(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): IBoolExpr {
         return new BoolExpr(new UnOpExpr(UnOp.GetBoolean, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun <T> getString(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): IStringExpr {
         return new StringExpr(new UnOpExpr(UnOp.GetString, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun <T> getInt(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): IIntExpr {
         return new IntExpr(new UnOpExpr(UnOp.GetInt, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun <T> getLong(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): ILongExpr {
         return new LongExpr(new UnOpExpr(UnOp.GetLong, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun <T> getEnumName(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>): IStringExpr {
         return new StringExpr(new UnOpExpr(UnOp.GetEnumName, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun <T> isInstanceOf(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, parentType: java.lang.String): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.IsInstanceOf, `$receiver`.getExpr(), `$this`.const(parentType, IIexConst.Type.Class)));
      }

      @JvmStatic
      fun toLowerCase(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr): IStringExpr {
         return new StringExpr(new UnOpExpr(UnOp.ToLowerCase, `$receiver`.getExpr()));
      }

      @JvmStatic
      fun startsWith(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr, str: IStringExpr): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.StartsWith, `$receiver`.getExpr(), str.getExpr()));
      }

      @JvmStatic
      fun endsWith(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr, str: IStringExpr): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.EndsWith, `$receiver`.getExpr(), str.getExpr()));
      }

      @JvmStatic
      fun contains(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr, str: IStringExpr): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.Contains, `$receiver`.getExpr(), str.getExpr()));
      }

      @JvmStatic
      fun stringEquals(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr, str: IStringExpr): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.StringEquals, `$receiver`.getExpr(), str.getExpr()));
      }

      @JvmStatic
      fun hasIntersection(
         `$this`: IBaseOperatorFactory,
         `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
         taint: IAttribute<TaintProperty, java.utilSet<ITaintType>>
      ): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.HasIntersectionSet, `$receiver`.getValue(), taint.getValue()));
      }

      @JvmStatic
      fun containsAll(
         `$this`: IBaseOperatorFactory,
         `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>,
         taint: IAttribute<TaintProperty, java.utilSet<ITaintType>>
      ): IBoolExpr {
         return new BoolExpr(new BinOpExpr(BinOp.ContainsSet, `$receiver`.getValue(), taint.getValue()));
      }

      @JvmStatic
      fun contains(`$this`: IBaseOperatorFactory, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
         return `$this`.containsAll(`$receiver`, `$this`.taintOf(new ITaintType[]{taint}));
      }

      @JvmStatic
      fun <T> field(
         `$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, declaringClass: java.lang.String?, fieldName: java.lang.String, fieldType: java.lang.String?
      ): IAccessPathT<Object> {
         return new AccessPath<>(`$receiver`.getExpr(), new ClassField(declaringClass, fieldName, fieldType));
      }

      @JvmStatic
      fun <T, FieldType> field(
         `$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, declaringClass: java.lang.String?, fieldName: java.lang.String, type: KClass<FieldType>
      ): IAccessPathT<FieldType> {
         return new AccessPath(`$receiver`.getExpr(), new ClassField(declaringClass, fieldName, UtilsKt.getSootTypeName(type)));
      }

      @JvmStatic
      fun <T, F> field(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, field: KProperty<? extends F>): IAccessPathT<F> {
         return new AccessPath(`$receiver`.getExpr(), new ClassField(field));
      }

      @JvmStatic
      fun <T> field(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, field: IClassField): IAccessPathT<Object> {
         return new AccessPath<>(`$receiver`.getExpr(), field);
      }

      @JvmStatic
      fun <T, FieldType> field(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, field: KProperty<?>, type: KClass<FieldType>): IAccessPathT<FieldType> {
         return new AccessPath(
            `$receiver`.getExpr(), new ClassField(UtilsKt.getDeclaringClassName(field as KCallable<?>), field.getName(), UtilsKt.getSootTypeName(type))
         );
      }

      @JvmStatic
      fun eval(`$this`: IBaseOperatorFactory, expr: IBoolExpr, result: (java.lang.Boolean?) -> Unit) {
         `$this`.eval(expr.getExpr(), IBaseOperatorFactory.DefaultImpls::eval$lambda$0);
      }

      @JvmStatic
      fun eval(`$this`: IBaseOperatorFactory, expr: IIntExpr, result: (Int?) -> Unit) {
         `$this`.eval(expr.getExpr(), IBaseOperatorFactory.DefaultImpls::eval$lambda$1);
      }

      @JvmStatic
      fun eval(`$this`: IBaseOperatorFactory, expr: IStringExpr, result: (java.lang.String?) -> Unit) {
         `$this`.eval(expr.getExpr(), IBaseOperatorFactory.DefaultImpls::eval$lambda$2);
      }

      @JvmStatic
      fun plus(`$this`: IBaseOperatorFactory, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IOperatorFactory.DefaultImpls.plus(`$this`, `$receiver`, single);
      }

      @JvmStatic
      fun plus(`$this`: IBaseOperatorFactory, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IOperatorFactory.DefaultImpls.plus(`$this`, `$receiver`, single);
      }

      @JvmStatic
      fun minus(`$this`: IBaseOperatorFactory, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IOperatorFactory.DefaultImpls.minus(`$this`, `$receiver`, single);
      }

      @JvmStatic
      fun minus(`$this`: IBaseOperatorFactory, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IOperatorFactory.DefaultImpls.minus(`$this`, `$receiver`, single);
      }

      @JvmStatic
      fun startsWith(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
         return IOperatorFactory.DefaultImpls.startsWith(`$this`, `$receiver`, str);
      }

      @JvmStatic
      fun endsWith(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
         return IOperatorFactory.DefaultImpls.endsWith(`$this`, `$receiver`, str);
      }

      @JvmStatic
      fun contains(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
         return IOperatorFactory.DefaultImpls.contains(`$this`, `$receiver`, str);
      }

      @JvmStatic
      fun stringEquals(`$this`: IBaseOperatorFactory, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
         return IOperatorFactory.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
      }

      @JvmStatic
      fun taintOf(`$this`: IBaseOperatorFactory, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IOperatorFactory.DefaultImpls.taintOf(`$this`, type);
      }

      @JvmStatic
      fun getEmptyTaint(`$this`: IBaseOperatorFactory): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
         return IOperatorFactory.DefaultImpls.getEmptyTaint(`$this`);
      }

      @JvmStatic
      fun getEmptyVia(`$this`: IBaseOperatorFactory): IAttribute<ViaProperty, java.utilSet<IViaType>> {
         return IOperatorFactory.DefaultImpls.getEmptyVia(`$this`);
      }

      @JvmStatic
      fun containsAll(`$this`: IBaseOperatorFactory, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
         return IOperatorFactory.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
      }

      @JvmStatic
      fun <T1 extends R, T2 extends R, R> anyOr(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
         return IOperatorFactory.DefaultImpls.anyOr(`$this`, `$receiver`, second);
      }

      @JvmStatic
      fun <T> field(`$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
         return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, field);
      }

      @JvmStatic
      fun <T, FieldType> field(
         `$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
      ): IAccessPathT<FieldType> {
         return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
      }

      @JvmStatic
      fun <T> field(
         `$this`: IBaseOperatorFactory, `$receiver`: ILocalT<T>, declaringClass: KClass<?>, fieldName: java.lang.String, fieldType: java.lang.String?
      ): IAccessPathT<Object> {
         return IOperatorFactory.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
      }

      @JvmStatic
      fun check(`$this`: IBaseOperatorFactory, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
         IOperatorFactory.DefaultImpls.check(`$this`, expr, checkType, env);
      }

      @JvmStatic
      fun `eval$lambda$0`(`$result`: Function1, it: Any): Unit {
         val var10000: java.lang.Boolean = it as? java.lang.Boolean;
         if ((it as? java.lang.Boolean) != null) {
            `$result`.invoke(var10000);
         }

         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `eval$lambda$1`(`$result`: Function1, it: Any): Unit {
         val var10000: Int = it as? Int;
         if ((it as? Int) != null) {
            `$result`.invoke(var10000);
         }

         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `eval$lambda$2`(`$result`: Function1, it: Any): Unit {
         val var10000: java.lang.String = it as? java.lang.String;
         if ((it as? java.lang.String) != null) {
            `$result`.invoke(var10000);
         }

         return Unit.INSTANCE;
      }
   }
}
