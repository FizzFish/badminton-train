package com.feysh.corax.config.api

import java.util.LinkedHashSet
import org.pf4j.ExtensionPoint

public interface IConfigPluginExtension : ExtensionPoint {
   public val units: LinkedHashSet<CheckerUnit>
   public val sootConfig: ISootInitializeHandler
   public val name: String
}
