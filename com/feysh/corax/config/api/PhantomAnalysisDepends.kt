package com.feysh.corax.config.api

public object PhantomAnalysisDepends : IAnalysisDepends {
   public override fun toDecl(target: Any): XDecl {
      return new XDecl() {};
   }

   public override infix fun XDecl.dependsOn(dep: XDecl) {
   }

   public override infix fun Collection<XDecl>.dependsOn(deps: Collection<XDecl>) {
   }
}
