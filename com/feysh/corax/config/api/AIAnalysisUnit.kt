package com.feysh.corax.config.api

public abstract class AIAnalysisUnit : CheckerUnit {
   context(AIAnalysisApi)
   public abstract suspend fun config() {
   }
}
