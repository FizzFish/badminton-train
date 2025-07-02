package com.feysh.corax.config.builtin.soot

import com.feysh.corax.config.api.ISootInitializeHandler
import com.feysh.corax.config.api.SAOptions
import java.util.LinkedList
import java.util.Map.Entry
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import soot.Main
import soot.Scene
import soot.jimple.toolkits.callgraph.CallGraph
import soot.options.Options

public object DefaultSootConfiguration : ISootInitializeHandler {
   public final var options: com.feysh.corax.config.builtin.soot.DefaultSootConfiguration.CustomOptions = new DefaultSootConfiguration.CustomOptions()
      internal set

   public override fun configure(options: Options) {
      val excludeList: java.util.List = new LinkedList();
      excludeList.addAll(options.getExcludeList());
      options.set_exclude(excludeList);

      for (Entry var4 : options.getSootPhaseNameToOption().entrySet()) {
         options.setPhaseOption(var4.getKey() as java.lang.String, var4.getValue() as java.lang.String);
      }
   }

   override fun configure(scene: Scene) {
      ISootInitializeHandler.DefaultImpls.configure(this, scene);
   }

   override fun configure(main: Main) {
      ISootInitializeHandler.DefaultImpls.configure(this, main);
   }

   override fun configureAfterSceneInit(scene: Scene, options: Options) {
      ISootInitializeHandler.DefaultImpls.configureAfterSceneInit(this, scene, options);
   }

   override fun onBeforeCallGraphConstruction(scene: Scene, options: Options) {
      ISootInitializeHandler.DefaultImpls.onBeforeCallGraphConstruction(this, scene, options);
   }

   override fun onAfterCallGraphConstruction(cg: CallGraph, scene: Scene, options: Options) {
      ISootInitializeHandler.DefaultImpls.onAfterCallGraphConstruction(this, cg, scene, options);
   }

   @Serializable
   public class CustomOptions : SAOptions {
      public final val excludeList: List<String> =
         CollectionsKt.listOf(
            new java.lang.String[]{
               "java.*",
               "javax.*",
               "jdk.*",
               "com.apple.*",
               "apple.awt.*",
               "org.w3c.*",
               "org.xml.*",
               "com.sun.*",
               "sun.*",
               "android.*",
               "androidx.*",
               "org.slf4j.*",
               "org.apache.log4j.*",
               "org.apache.logging.*",
               "java.util.logging.*",
               "ch.qos.logback.*",
               "com.mysql.*",
               "org.eclipse.*",
               "soot.*"
            }
         )
         public final val sootPhaseNameToOption: Map<String, String> =
         MapsKt.mapOf(new Pair[]{TuplesKt.to("jb.sils", "enabled:false"), TuplesKt.to("jb.tr", "ignore-nullpointer-dereferences:true")})

      public companion object {
         public fun serializer(): KSerializer<com.feysh.corax.config.builtin.soot.DefaultSootConfiguration.CustomOptions> {
            return DefaultSootConfiguration.CustomOptions.$serializer.INSTANCE as KSerializer<DefaultSootConfiguration.CustomOptions>;
         }
      }
   }
}
