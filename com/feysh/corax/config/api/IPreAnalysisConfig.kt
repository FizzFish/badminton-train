package com.feysh.corax.config.api

import com.feysh.corax.config.api.rules.ProcessRule.IMatchItem

public interface IPreAnalysisConfig {
   public var ignoreProjectConfigProcessFilter: Boolean
      internal final set

   public var processRules: List<IMatchItem>
      internal final set

   public var incrementalAnalyze: Boolean
      internal final set
}
