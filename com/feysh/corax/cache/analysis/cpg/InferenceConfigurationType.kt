package com.feysh.corax.cache.analysis.cpg

import de.fraunhofer.aisec.cpg.InferenceConfiguration
import kotlin.enums.EnumEntries

public enum class InferenceConfigurationType(configuration: InferenceConfiguration) {
   DEFAULT(InferenceConfiguration.Companion.builder().build()),
   DISABLE_ALL(InferenceConfiguration.Companion.builder().enabled(false).build()),
   ENABLE_ALL(InferenceConfiguration.Companion.builder().build())
   public final val configuration: InferenceConfiguration

   init {
      this.configuration = configuration;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<InferenceConfigurationType> {
      return $ENTRIES;
   }
}
