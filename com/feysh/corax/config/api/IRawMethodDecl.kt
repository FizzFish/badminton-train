package com.feysh.corax.config.api

import com.feysh.corax.config.api.baseimpl.ConfigException
import com.feysh.corax.config.api.utils.KFunctionType
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootField

public interface IRawMethodDecl<R> : IMethodDecl<R> {
   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): IRawMethodDecl.CheckBuilder<Any> {
   }

   public open fun model(config: (MethodConfig) -> Unit = ..., block: (IRawMethodDecl.CheckBuilder<Any>, Array<IParameterT<Any>>) -> Unit): IRawMethodDecl<Any> {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (IRawMethodDecl.CheckBuilder<Any>) -> Unit): IRawMethodDecl<Any> {
   }

   public interface CheckBuilder<R> : IMethodDecl.CheckBuilder<R> {
      public open val `this`: IParameterT<Any>
         public open get() {
         }


      public open val p0: IParameterT<Any>
         public open get() {
         }


      public open val p1: IParameterT<Any>
         public open get() {
         }


      public open val p2: IParameterT<Any>
         public open get() {
         }


      public open val p3: IParameterT<Any>
         public open get() {
         }


      public open val p4: IParameterT<Any>
         public open get() {
         }


      public open val p5: IParameterT<Any>
         public open get() {
         }


      public open val p6: IParameterT<Any>
         public open get() {
         }


      public open val p7: IParameterT<Any>
         public open get() {
         }


      public open val p8: IParameterT<Any>
         public open get() {
         }


      public open val p9: IParameterT<Any>
         public open get() {
         }


      public open val p10: IParameterT<Any>
         public open get() {
         }


      public open val p11: IParameterT<Any>
         public open get() {
         }


      public open val p12: IParameterT<Any>
         public open get() {
         }


      public open val p13: IParameterT<Any>
         public open get() {
         }


      public open val p14: IParameterT<Any>
         public open get() {
         }


      public open val p15: IParameterT<Any>
         public open get() {
         }


      public open fun parameter(index: Int): IParameterT<Any> {
      }

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R> getThis(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(-1);
         }

         @JvmStatic
         fun <R> getP0(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R> getP1(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R> getP2(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R> getP3(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R> getP4(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(4);
         }

         @JvmStatic
         fun <R> getP5(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(5);
         }

         @JvmStatic
         fun <R> getP6(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(6);
         }

         @JvmStatic
         fun <R> getP7(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(7);
         }

         @JvmStatic
         fun <R> getP8(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(8);
         }

         @JvmStatic
         fun <R> getP9(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(9);
         }

         @JvmStatic
         fun <R> getP10(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(10);
         }

         @JvmStatic
         fun <R> getP11(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(11);
         }

         @JvmStatic
         fun <R> getP12(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(12);
         }

         @JvmStatic
         fun <R> getP13(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(13);
         }

         @JvmStatic
         fun <R> getP14(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(14);
         }

         @JvmStatic
         fun <R> getP15(`$this`: IRawMethodDeclCheckBuilder<R>): IParameterT<Object> {
            return `$this`.paramAt(15);
         }

         @JvmStatic
         fun <R> parameter(`$this`: IRawMethodDeclCheckBuilder<R>, index: Int): IParameterT<Object> {
            return `$this`.paramAt(index);
         }

         @JvmStatic
         fun <R> plus(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> plus(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> startsWith(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> endsWith(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> contains(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> stringEquals(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> taintOf(`$this`: IRawMethodDeclCheckBuilder<R>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R> getEmptyTaint(`$this`: IRawMethodDeclCheckBuilder<R>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R> getEmptyVia(`$this`: IRawMethodDeclCheckBuilder<R>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R> containsAll(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, T1 extends R, T2 extends R, R> anyOr(`$this`: IRawMethodDeclCheckBuilder<R_I1>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, T> field(`$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, T, FieldType> field(
            `$this`: IRawMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, T> field(
            `$this`: IRawMethodDeclCheckBuilder<R>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R> check(`$this`: IRawMethodDeclCheckBuilder<R>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nAIAnalysisApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/IRawMethodDecl$DefaultImpls\n+ 2 AIAnalysisApi.kt\ncom/feysh/corax/config/api/AIAnalysisApiKt\n*L\n1#1,839:1\n34#2,6:840\n34#2,6:846\n*S KotlinDebug\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/IRawMethodDecl$DefaultImpls\n*L\n687#1:840,6\n695#1:846,6\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R> model(`$this`: IRawMethodDecl<R>, config: (MethodConfig?) -> Unit, block: (IRawMethodDeclCheckBuilder<R>?, Array<IParameterT<Object>>?) -> Unit): IRawMethodDecl<R> {
         val cnt: Int = `$this`.getArgumentCnt();
         val var10000: IRawMethodDecl;
         if (cnt == null) {
            `$this`.getError()
               .error(
                  "The argumentCnt of this method match: ${`$this`.getMatch()} is unknown. Please use \".modelNoArg\". Or implemented argumentCnt method of ${`$this`.getMatch()
                     .getClass()}"
               );
            var10000 = `$this`;
         } else {
            val `$this$tryApply$iv`: Any = `$this`.checkBuilder(config);

            try {
               val `$this$model_u24lambda_u241`: IRawMethodDecl.CheckBuilder = (IRawMethodDecl.CheckBuilder)`$this$tryApply$iv`;
               var var8: Int = 0;

               val var9: Array<IParameterT>;
               for (var9 = new IParameterT[cnt]; var8 < cnt; var8++) {
                  var9[var8] = `$this$model_u24lambda_u241`.paramAt(var8);
               }

               block.invoke(`$this$tryApply$iv`, var9);
            } catch (var14: ConfigException) {
               System.err.println(var14);
            }

            var10000 = `$this`;
         }

         return var10000;
      }

      @JvmStatic
      fun <R> modelNoArg(`$this`: IRawMethodDecl<R>, config: (MethodConfig?) -> Unit, block: (IRawMethodDeclCheckBuilder<R>?) -> Unit): IRawMethodDecl<R> {
         val `$this$tryApply$iv`: Any = `$this`.checkBuilder(config);

         try {
            block.invoke(`$this$tryApply$iv`);
         } catch (var8: ConfigException) {
            System.err.println(var8);
         }

         return `$this`;
      }

      @JvmStatic
      fun <R> getArgumentCnt(`$this`: IRawMethodDecl<R>): Int? {
         return IMethodDecl.DefaultImpls.getArgumentCnt(`$this`);
      }

      @JvmStatic
      fun <R> getActualType(`$this`: IRawMethodDecl<R>): KFunctionType? {
         return IMethodDecl.DefaultImpls.getActualType(`$this`);
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
