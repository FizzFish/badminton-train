package com.feysh.corax.config.api

import com.feysh.corax.cache.coroutines.FastCache
import kotlinx.coroutines.CoroutineScope

public interface AnalysisApi {
   public val fastCache: FastCache
   public val scope: CoroutineScope
}
