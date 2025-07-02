package com.feysh.corax.config.api

import soot.Scene
import soot.SootField

public interface IFieldMatch {
   public val declaringClassType: String
   public val fieldName: String
   public val fieldType: String?
   public val isStatic: Boolean?

   public abstract fun matched(scene: Scene): List<SootField> {
   }
}
