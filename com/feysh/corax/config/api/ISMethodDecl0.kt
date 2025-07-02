package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface ISMethodDecl0<R> : ISMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISMethodDecl0.CheckBuilder<Any> {
   }

   public open fun model(config: (MethodConfig) -> Unit = ..., block: (ISMethodDecl0.CheckBuilder<Any>) -> Unit): ISMethodDecl0<Any> {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (ISMethodDecl0.CheckBuilder<Any>) -> Unit): ISMethodDecl0<Any> {
   }

   public interface CheckBuilder<R> : ISMethodDecl.CheckBuilder<R> {
      public val method: ISMethodDecl0<Any>

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R> plus(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> plus(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> startsWith(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> endsWith(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> contains(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> stringEquals(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> taintOf(`$this`: ISMethodDecl0CheckBuilder<R>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R> getEmptyTaint(`$this`: ISMethodDecl0CheckBuilder<R>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R> getEmptyVia(`$this`: ISMethodDecl0CheckBuilder<R>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R> containsAll(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return ISMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, T1 extends R, T2 extends R, R> anyOr(`$this`: ISMethodDecl0CheckBuilder<R_I1>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, T> field(`$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, T, FieldType> field(
            `$this`: ISMethodDecl0CheckBuilder<R>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, T> field(
            `$this`: ISMethodDecl0CheckBuilder<R>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return ISMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R> check(`$this`: ISMethodDecl0CheckBuilder<R>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            ISMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nStaticMethodInterface.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StaticMethodInterface.kt\ncom/feysh/corax/config/api/ISMethodDecl0$DefaultImpls\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,577:1\n1#2:578\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R> model(`$this`: ISMethodDecl0<R>, config: (MethodConfig?) -> Unit, block: (ISMethodDecl0CheckBuilder<R>?) -> Unit): ISMethodDecl0<R> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R> modelNoArg(`$this`: ISMethodDecl0<R>, config: (MethodConfig?) -> Unit, block: (ISMethodDecl0CheckBuilder<R>?) -> Unit): ISMethodDecl0<R> {
         block.invoke(`$this`.checkBuilder(config));
         return `$this`;
      }

      @JvmStatic
      fun <R> getArgumentCnt(`$this`: ISMethodDecl0<R>): Int? {
         return ISMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R> getActualType(`$this`: ISMethodDecl0<R>): KFunctionType? {
         return ISMethodDecl.DefaultImpls.getActualType(`$this`);
      }

      @JvmStatic
      fun `model$lambda$0`(var0: MethodConfig): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `modelNoArg$lambda$2`(var0: MethodConfig): Unit {
         return Unit.INSTANCE;
      }
   }
}
