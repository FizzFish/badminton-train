package com.feysh.corax.config.api

import com.feysh.corax.config.api.utils.KFunctionType
import soot.Scene
import soot.SootMethod

public interface IMethodMatch {
   public val actualType: KFunctionType?
   public val argumentCnt: Int?
   public val exception: Exception

   public abstract fun matched(scene: Scene): List<SootMethod> {
   }
}
