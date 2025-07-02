package com.feysh.corax.config.api.baseimpl

import com.feysh.corax.config.api.IClassDecl
import com.feysh.corax.config.api.IClassMatch
import com.feysh.corax.config.api.ISootClassDecl
import com.feysh.corax.config.api.ISootFieldDecl
import com.feysh.corax.config.api.ISootMethodDecl
import soot.SootClass
import soot.SootField
import soot.SootMethod
import soot.tagkit.AnnotationTag
import soot.tagkit.Tag

public class BaseSootClassDecl(delegate: IClassDecl, analyzeConfig: AIAnalysisBaseImpl, sootClass: SootClass) : ISootClassDecl, IClassDecl {
   public final val delegate: IClassDecl
   private final val analyzeConfig: AIAnalysisBaseImpl
   public open val sootClass: SootClass
   public open val match: IClassMatch

   init {
      this.delegate = delegate;
      this.analyzeConfig = analyzeConfig;
      this.sootClass = sootClass;
   }

   public override fun eachDeclaringMethod(block: (ISootMethodDecl<Any>) -> Unit) {
      if (this.getSootClass().resolvingLevel() >= 2) {
         for (SootMethod sm : this.getSootClass().getMethods()) {
            block.invoke(this.sootDeclaringMethod(sm));
         }
      }
   }

   public override fun <R> sootDeclaringMethod(method: SootMethod): ISootMethodDecl<R> {
      return new BaseSootMethodDecl(new MethodDeclBase(this.analyzeConfig, SootSignatureMatch.Companion.invoke(method), null, 4, null), this, method);
   }

   public override fun eachDeclaringField(block: (ISootFieldDecl<Any, Any>) -> Unit) {
      val var10000: java.util.Iterator = this.getSootClass().getFields().iterator();
      val var2: java.util.Iterator = var10000;

      while (var2.hasNext()) {
         val sf: SootField = var2.next() as SootField;
         val var10002: AIAnalysisBaseImpl = this.analyzeConfig;
         block.invoke(new BaseSootFieldDecl(new BaseFieldDecl(var10002, new SootFieldSignatureMatch(sf)), this, sf));
      }
   }

   public override fun anySuperClass(predicate: (SootClass) -> Boolean): Boolean {
      var clazz: SootClass = this.getSootClass();

      while (clazz.hasSuperclass()) {
         clazz = clazz.getSuperclass();
         if (predicate.invoke(clazz) as java.lang.Boolean) {
            return true;
         }
      }

      return false;
   }

   public override fun toString(): String {
      return "soot class decl: ${this.getSootClass()}";
   }

   override fun getClazzName(): java.lang.String {
      return ISootClassDecl.DefaultImpls.getClazzName(this);
   }

   override fun getClassTags(): MutableList<Tag> {
      return ISootClassDecl.DefaultImpls.getClassTags(this);
   }

   override fun getClassAnnotationTags(): MutableList<AnnotationTag> {
      return ISootClassDecl.DefaultImpls.getClassAnnotationTags(this);
   }

   override fun getDeclaringSootMethods(): MutableList<SootMethod> {
      return ISootClassDecl.DefaultImpls.getDeclaringSootMethods(this);
   }
}
