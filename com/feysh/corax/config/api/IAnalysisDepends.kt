package com.feysh.corax.config.api

public interface IAnalysisDepends {
   public abstract fun toDecl(target: Any): XDecl {
   }

   public abstract infix fun XDecl.dependsOn(dep: XDecl) {
   }

   public abstract infix fun Collection<XDecl>.dependsOn(deps: Collection<XDecl>) {
   }
}
