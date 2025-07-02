package com.feysh.corax.config.builtin.soot

import com.feysh.corax.config.api.ISootInitializeHandler
import soot.Main
import soot.Scene
import soot.jimple.toolkits.callgraph.CallGraph
import soot.options.Options

public object EmptySootConfiguration : ISootInitializeHandler {
   override fun configure(options: Options) {
      ISootInitializeHandler.DefaultImpls.configure(this, options);
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
}
