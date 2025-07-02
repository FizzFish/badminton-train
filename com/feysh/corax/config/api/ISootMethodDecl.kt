package com.feysh.corax.config.api

import com.feysh.corax.config.api.baseimpl.ConfigException
import com.feysh.corax.config.api.baseimpl.SootParameter
import com.feysh.corax.config.api.baseimpl.SootReturn
import com.feysh.corax.config.api.utils.KFunctionType
import com.feysh.corax.config.api.utils.UtilsKt
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import soot.SootClass
import soot.SootField
import soot.SootMethod
import soot.tagkit.AnnotationTag
import soot.tagkit.Tag
import soot.tagkit.VisibilityAnnotationTag
import soot.tagkit.VisibilityParameterAnnotationTag

public interface ISootMethodDecl<R> : IMethodDecl<R> {
   public val clazz: ISootClassDecl
   public val sootMethod: SootMethod

   public open val sootClass: SootClass
      public open get() {
      }


   public open val argumentCnt: Int
      public open get() {
      }


   public open val actualType: KFunctionType
      public open get() {
      }


   public open val parameterAnnotationTag: VisibilityParameterAnnotationTag?
      public open get() {
      }


   public open val visibilityAnnotationTag: VisibilityAnnotationTag?
      public open get() {
      }


   public open val annotationTag: List<AnnotationTag>
      public open get() {
      }


   public open val tags: List<Tag>
      public open get() {
      }


   public open val annotationTag: AnnotationTag?
      public open get() {
      }


   public open val visibilityAnnotationTag: VisibilityAnnotationTag?
      public open get() {
      }


   public abstract fun checkBuilder(config: (MethodConfig) -> Unit): ISootMethodDecl.CheckBuilder<Any> {
   }

   public open fun model(config: (MethodConfig) -> Unit = ..., block: (ISootMethodDecl.CheckBuilder<Any>, Array<IParameterT<Any>>) -> Unit): ISootMethodDecl<
         Any
      > {
   }

   public open fun modelNoArg(config: (MethodConfig) -> Unit = ..., block: (ISootMethodDecl.CheckBuilder<Any>) -> Unit): ISootMethodDecl<Any> {
   }

   public interface CheckBuilder<R> : IMethodDecl.CheckBuilder<R> {
      public val method: ISootMethodDecl<Any>

      public open val `this`: SootParameter<Any>
         public open get() {
         }


      public open val p0: SootParameter<Any>
         public open get() {
         }


      public open val p1: SootParameter<Any>
         public open get() {
         }


      public open val p2: SootParameter<Any>
         public open get() {
         }


      public open val p3: SootParameter<Any>
         public open get() {
         }


      public open val p4: SootParameter<Any>
         public open get() {
         }


      public open val p5: SootParameter<Any>
         public open get() {
         }


      public open val p6: SootParameter<Any>
         public open get() {
         }


      public open val p7: SootParameter<Any>
         public open get() {
         }


      public open val p8: SootParameter<Any>
         public open get() {
         }


      public open val p9: SootParameter<Any>
         public open get() {
         }


      public open val p10: SootParameter<Any>
         public open get() {
         }


      public open val p11: SootParameter<Any>
         public open get() {
         }


      public open val p12: SootParameter<Any>
         public open get() {
         }


      public open val p13: SootParameter<Any>
         public open get() {
         }


      public open val p14: SootParameter<Any>
         public open get() {
         }


      public open val p15: SootParameter<Any>
         public open get() {
         }


      public val `return`: SootReturn<Any>

      public abstract fun <T> paramAt(index: Int): SootParameter<T> {
      }

      public open fun parameter(index: Int): SootParameter<Any> {
      }

      // $VF: Class flags could not be determined
      internal class DefaultImpls {
         @JvmStatic
         fun <R> getThis(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(-1);
         }

         @JvmStatic
         fun <R> getP0(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(0);
         }

         @JvmStatic
         fun <R> getP1(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(1);
         }

         @JvmStatic
         fun <R> getP2(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(2);
         }

         @JvmStatic
         fun <R> getP3(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(3);
         }

         @JvmStatic
         fun <R> getP4(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(4);
         }

         @JvmStatic
         fun <R> getP5(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(5);
         }

         @JvmStatic
         fun <R> getP6(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(6);
         }

         @JvmStatic
         fun <R> getP7(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(7);
         }

         @JvmStatic
         fun <R> getP8(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(8);
         }

         @JvmStatic
         fun <R> getP9(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(9);
         }

         @JvmStatic
         fun <R> getP10(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(10);
         }

         @JvmStatic
         fun <R> getP11(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(11);
         }

         @JvmStatic
         fun <R> getP12(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(12);
         }

         @JvmStatic
         fun <R> getP13(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(13);
         }

         @JvmStatic
         fun <R> getP14(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(14);
         }

         @JvmStatic
         fun <R> getP15(`$this`: ISootMethodDeclCheckBuilder<R>): SootParameter<Object> {
            return `$this`.paramAt(15);
         }

         @JvmStatic
         fun <R> parameter(`$this`: ISootMethodDeclCheckBuilder<R>, index: Int): SootParameter<Object> {
            return `$this`.paramAt(index);
         }

         @JvmStatic
         fun <R> plus(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> plus(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.plus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, single: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> minus(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<ViaProperty, java.utilSet<IViaType>>, single: IViaType): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.minus(`$this`, `$receiver`, single);
         }

         @JvmStatic
         fun <R> startsWith(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.startsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> endsWith(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.endsWith(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> contains(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.contains(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> stringEquals(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IStringExpr, str: java.lang.String): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.stringEquals(`$this`, `$receiver`, str);
         }

         @JvmStatic
         fun <R> taintOf(`$this`: ISootMethodDeclCheckBuilder<R>, vararg type: ITaintType): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.taintOf(`$this`, type);
         }

         @JvmStatic
         fun <R> getEmptyTaint(`$this`: ISootMethodDeclCheckBuilder<R>): IAttribute<TaintProperty, java.utilSet<ITaintType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyTaint(`$this`);
         }

         @JvmStatic
         fun <R> getEmptyVia(`$this`: ISootMethodDeclCheckBuilder<R>): IAttribute<ViaProperty, java.utilSet<IViaType>> {
            return IMethodDecl.CheckBuilder.DefaultImpls.getEmptyVia(`$this`);
         }

         @JvmStatic
         fun <R> containsAll(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: IAttribute<TaintProperty, java.utilSet<ITaintType>>, taint: ITaintType): IBoolExpr {
            return IMethodDecl.CheckBuilder.DefaultImpls.containsAll(`$this`, `$receiver`, taint);
         }

         @JvmStatic
         fun <R_I1, T1 extends R, T2 extends R, R> anyOr(`$this`: ISootMethodDeclCheckBuilder<R_I1>, `$receiver`: ILocalT<T1>, second: ILocalT<T2>): ILocalValue<R> {
            return IMethodDecl.CheckBuilder.DefaultImpls.anyOr(`$this`, `$receiver`, second);
         }

         @JvmStatic
         fun <R, T> field(`$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, field: SootField): IAccessPathT<Object> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, field);
         }

         @JvmStatic
         fun <R, T, FieldType> field(
            `$this`: ISootMethodDeclCheckBuilder<R>, `$receiver`: ILocalT<T>, declaringClass: KClass<?>?, fieldName: java.lang.String, type: KClass<FieldType>
         ): IAccessPathT<FieldType> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, type);
         }

         @JvmStatic
         fun <R, T> field(
            `$this`: ISootMethodDeclCheckBuilder<R>,
            `$receiver`: ILocalT<T>,
            declaringClass: KClass<?>,
            fieldName: java.lang.String,
            fieldType: java.lang.String?
         ): IAccessPathT<Object> {
            return IMethodDecl.CheckBuilder.DefaultImpls.field(`$this`, `$receiver`, declaringClass, fieldName, fieldType);
         }

         @JvmStatic
         fun <R> check(`$this`: ISootMethodDeclCheckBuilder<R>, expr: ILocalT<java.lang.Boolean>, checkType: CheckType, env: (BugMessage.Env?) -> Unit) {
            IMethodDecl.CheckBuilder.DefaultImpls.check(`$this`, expr, checkType, env);
         }
      }
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nAIAnalysisApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/ISootMethodDecl$DefaultImpls\n+ 2 AIAnalysisApi.kt\ncom/feysh/corax/config/api/AIAnalysisApiKt\n*L\n1#1,839:1\n34#2,6:840\n34#2,6:846\n*S KotlinDebug\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/ISootMethodDecl$DefaultImpls\n*L\n590#1:840,6\n597#1:846,6\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun <R> getSootClass(`$this`: ISootMethodDecl<R>): SootClass {
         val var10000: SootClass = `$this`.getSootMethod().getDeclaringClass();
         return var10000;
      }

      @JvmStatic
      fun <R> getArgumentCnt(`$this`: ISootMethodDecl<R>): Int {
         return `$this`.getSootMethod().getParameterCount();
      }

      @JvmStatic
      fun <R> getActualType(`$this`: ISootMethodDecl<R>): KFunctionType {
         return UtilsKt.getFunctionType(`$this`.getSootMethod());
      }

      @JvmStatic
      fun <R> getParameterAnnotationTag(`$this`: ISootMethodDecl<R>): VisibilityParameterAnnotationTag? {
         return `$this`.getSootMethod().getTag("VisibilityParameterAnnotationTag") as VisibilityParameterAnnotationTag;
      }

      @JvmStatic
      fun <R> getVisibilityAnnotationTag(`$this`: ISootMethodDecl<R>, `$receiver`: IParameterT<?>): VisibilityAnnotationTag? {
         val var3: Any;
         if (`$receiver`.getIndex() >= 0) {
            val var10000: VisibilityParameterAnnotationTag = `$this`.getParameterAnnotationTag();
            if (var10000 != null) {
               val var2: ArrayList = var10000.getVisibilityAnnotations();
               if (var2 != null) {
                  return var2.get(`$receiver`.getIndex()) as VisibilityAnnotationTag;
               }
            }

            var3 = null;
         } else {
            var3 = null;
         }

         return (VisibilityAnnotationTag)var3;
      }

      @JvmStatic
      fun <R> getAnnotationTag(`$this`: ISootMethodDecl<R>, `$receiver`: IParameterT<?>): MutableList<AnnotationTag> {
         val var10000: VisibilityAnnotationTag = `$this`.getVisibilityAnnotationTag(`$receiver`);
         if (var10000 != null) {
            val var2: ArrayList = var10000.getAnnotations();
            if (var2 != null) {
               return var2;
            }
         }

         return CollectionsKt.emptyList();
      }

      @JvmStatic
      fun <R> getTags(`$this`: ISootMethodDecl<R>): MutableList<Tag> {
         val var10000: java.util.List = `$this`.getSootMethod().getTags();
         return var10000;
      }

      @JvmStatic
      fun <R> getAnnotationTag(`$this`: ISootMethodDecl<R>): AnnotationTag? {
         return `$this`.getSootMethod().getTag("AnnotationTag") as AnnotationTag;
      }

      @JvmStatic
      fun <R> getVisibilityAnnotationTag(`$this`: ISootMethodDecl<R>): VisibilityAnnotationTag? {
         return UtilsKt.getVisibilityAnnotationTag(`$this`.getSootMethod());
      }

      @JvmStatic
      fun <R> model(`$this`: ISootMethodDecl<R>, config: (MethodConfig?) -> Unit, block: (ISootMethodDeclCheckBuilder<R>?, Array<IParameterT<Object>>?) -> Unit): ISootMethodDecl<R> {
         val cnt: Int = `$this`.getArgumentCnt();
         val `$this$tryApply$iv`: Any = `$this`.checkBuilder(config);

         try {
            val `$this$model_u24lambda_u241`: ISootMethodDecl.CheckBuilder = (ISootMethodDecl.CheckBuilder)`$this$tryApply$iv`;
            var var8: Int = 0;

            val var9: Array<IParameterT>;
            for (var9 = new IParameterT[cnt]; var8 < cnt; var8++) {
               var9[var8] = `$this$model_u24lambda_u241`.paramAt(var8);
            }

            block.invoke(`$this$tryApply$iv`, var9);
         } catch (var14: ConfigException) {
            System.err.println(var14);
         }

         return `$this`;
      }

      @JvmStatic
      fun <R> modelNoArg(`$this`: ISootMethodDecl<R>, config: (MethodConfig?) -> Unit, block: (ISootMethodDeclCheckBuilder<R>?) -> Unit): ISootMethodDecl<R> {
         val `$this$tryApply$iv`: Any = `$this`.checkBuilder(config);

         try {
            block.invoke(`$this$tryApply$iv`);
         } catch (var8: ConfigException) {
            System.err.println(var8);
         }

         return `$this`;
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
