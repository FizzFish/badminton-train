package com.feysh.corax.config.api.baseimpl

public open class ClassDecl(`package`: PackageDeclare, className: String) {
   public final val `package`: PackageDeclare
   public final val className: String
   public final val clazz: String

   init {
      this.package = var1;
      this.className = className;
      this.clazz = "${this.package.getPackage()}.${this.className}";
   }

   public fun matchSimpleSubSig(subSignature: String): RawSignatureMatch {
      return MatchUtilsKt.matchSimpleSig("${this.clazz}: $subSignature");
   }

   public fun matchSubSig(subSignature: String): SootSignatureMatch {
      return SootSignatureMatch.Companion.invoke("<${this.clazz}: $subSignature>");
   }

   public fun matchName(name: String): RawSignatureMatch {
      return RawSignatureMatch.Companion.invoke$default(RawSignatureMatch.Companion, this.clazz, name, null, null, null, 16, null);
   }

   public override fun toString(): String {
      return "class match: ${this.className}";
   }
}
