package com.feysh.corax.config.api

import soot.Local
import soot.Scene

public interface ILocalVarMatch {
   public abstract fun matched(scene: Scene): List<Local> {
   }
}
