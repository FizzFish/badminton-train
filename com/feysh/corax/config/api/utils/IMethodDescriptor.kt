package com.feysh.corax.config.api.utils

public interface IMethodDescriptor {
   public val clazz: String
   public val name: String
   public val returnType: String
   public val parameters: List<String>
}
