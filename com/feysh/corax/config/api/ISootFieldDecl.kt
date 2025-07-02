package com.feysh.corax.config.api

import soot.SootField
import soot.tagkit.AnnotationTag
import soot.tagkit.Tag
import soot.tagkit.VisibilityAnnotationTag

public interface ISootFieldDecl<This, T> : IFieldDecl<This, T> {
   public val clazz: ISootClassDecl
   public val sootField: SootField

   public open val visibilityAnnotationTag: VisibilityAnnotationTag?
      public open get() {
      }


   public open val tags: List<Tag>
      public open get() {
      }


   public open val annotations: AnnotationTag?
      public open get() {
      }


   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun <This, T> getVisibilityAnnotationTag(`$this`: ISootFieldDecl<This, T>): VisibilityAnnotationTag? {
         return `$this`.getSootField().getTag("VisibilityAnnotationTag") as VisibilityAnnotationTag;
      }

      @JvmStatic
      fun <This, T> getTags(`$this`: ISootFieldDecl<This, T>): MutableList<Tag> {
         val var10000: java.util.List = `$this`.getSootField().getTags();
         return var10000;
      }

      @JvmStatic
      fun <This, T> getAnnotations(`$this`: ISootFieldDecl<This, T>): AnnotationTag? {
         return `$this`.getSootField().getTag("AnnotationTag") as AnnotationTag;
      }
   }
}
