package com.feysh.corax.config.api

import soot.Scene
import soot.SootClass

public interface IClassMatch {
   public val simpleName: List<String>

   public abstract fun matched(scene: Scene): List<SootClass> {
   }
}
