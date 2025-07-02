package com.feysh.corax.config.api

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import soot.SootClass
import soot.SootMethod
import soot.tagkit.AnnotationTag
import soot.tagkit.Tag

public interface ISootClassDecl : IClassDecl {
   public val sootClass: SootClass

   public open val clazzName: String
      public open get() {
      }


   public open val classTags: List<Tag>
      public open get() {
      }


   public open val classAnnotationTags: List<AnnotationTag>
      public open get() {
      }


   public open val declaringSootMethods: List<SootMethod>
      public open get() {
      }


   public abstract fun eachDeclaringMethod(block: (ISootMethodDecl<Any>) -> Unit) {
   }

   public abstract fun <R> sootDeclaringMethod(method: SootMethod): ISootMethodDecl<R> {
   }

   public abstract fun eachDeclaringField(block: (ISootFieldDecl<Any, Any>) -> Unit) {
   }

   public abstract fun anySuperClass(predicate: (SootClass) -> Boolean): Boolean {
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nAIAnalysisApi.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/ISootClassDecl$DefaultImpls\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,839:1\n808#2,11:840\n*S KotlinDebug\n*F\n+ 1 AIAnalysisApi.kt\ncom/feysh/corax/config/api/ISootClassDecl$DefaultImpls\n*L\n492#1:840,11\n*E\n"])
   internal class DefaultImpls {
      @JvmStatic
      fun getClazzName(`$this`: ISootClassDecl): java.lang.String {
         val var10000: java.lang.String = `$this`.getSootClass().getName();
         return var10000;
      }

      @JvmStatic
      fun getClassTags(`$this`: ISootClassDecl): MutableList<Tag> {
         val var10000: java.util.List = `$this`.getSootClass().getTags();
         return var10000;
      }

      @JvmStatic
      fun getClassAnnotationTags(`$this`: ISootClassDecl): MutableList<AnnotationTag> {
         val `$this$filterIsInstance$iv`: java.lang.Iterable = `$this`.getClassTags();
         val `destination$iv$iv`: java.util.Collection = new ArrayList();

         for (Object element$iv$iv : $this$filterIsInstance$iv) {
            if (`element$iv$iv` is AnnotationTag) {
               `destination$iv$iv`.add(`element$iv$iv`);
            }
         }

         return `destination$iv$iv` as MutableList<AnnotationTag>;
      }

      @JvmStatic
      fun getDeclaringSootMethods(`$this`: ISootClassDecl): MutableList<SootMethod> {
         val var10000: java.util.List = `$this`.getSootClass().getMethods();
         return var10000;
      }
   }
}
