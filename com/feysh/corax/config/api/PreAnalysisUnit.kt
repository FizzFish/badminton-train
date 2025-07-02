package com.feysh.corax.config.api

public abstract class PreAnalysisUnit : CheckerUnit {
   context(PreAnalysisApi)
   public abstract suspend fun config() {
   }
}
