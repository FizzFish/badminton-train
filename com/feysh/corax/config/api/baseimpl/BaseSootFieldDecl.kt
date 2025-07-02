package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IFieldDecl
import com.feysh.corax.config.api.IParameterT
import com.feysh.corax.config.api.ISootClassDecl
import com.feysh.corax.config.api.ISootFieldDecl
import com.feysh.corax.config.api.MethodConfig
import com.feysh.corax.config.api.IFieldDecl.IGet
import com.feysh.corax.config.api.IFieldDecl.ISet
import soot.SootField
import soot.tagkit.AnnotationTag
import soot.tagkit.Tag
import soot.tagkit.VisibilityAnnotationTag

public class BaseSootFieldDecl<This, T>(delegate: IFieldDecl<Any, Any>, clazz: ISootClassDecl, sootField: SootField) :
   ISootFieldDecl<This, T>,
   IFieldDecl<This, T> {
   public final val delegate: IFieldDecl<Any, Any>
   public open val clazz: ISootClassDecl
   public open val sootField: SootField

   init {
      this.delegate = delegate;
      this.clazz = clazz;
      this.sootField = sootField;
   }

   public override fun toString(): String {
      return "soot decl: ${this.getSootField()}";
   }

   override fun getVisibilityAnnotationTag(): VisibilityAnnotationTag? {
      return ISootFieldDecl.DefaultImpls.getVisibilityAnnotationTag(this);
   }

   override fun getTags(): MutableList<Tag> {
      return ISootFieldDecl.DefaultImpls.getTags(this);
   }

   override fun getAnnotations(): AnnotationTag? {
      return ISootFieldDecl.DefaultImpls.getAnnotations(this);
   }

   public override fun atGet(config: (MethodConfig) -> Unit, block: (IGet<Any, Any>) -> Unit): IFieldDecl<Any, Any> {
      return this.delegate.atGet(config, block);
   }

   public override fun atSet(config: (MethodConfig) -> Unit, block: (ISet<Any, Any>, IParameterT<Any>) -> Unit): IFieldDecl<Any, Any> {
      return this.delegate.atSet(config, block);
   }
}
