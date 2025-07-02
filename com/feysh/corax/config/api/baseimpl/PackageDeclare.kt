package com.feysh.corax.config.api.baseimpl

public open class PackageDeclare(`package`: String) {
   public final val `package`: String

   init {
      this.package = var1;
   }

   public fun `class`(className: String, block: (ClassDecl) -> Unit) {
      block.invoke(new ClassDecl(this, className));
   }

   public override fun toString(): String {
      return "package match: ${this.package}";
   }
}
