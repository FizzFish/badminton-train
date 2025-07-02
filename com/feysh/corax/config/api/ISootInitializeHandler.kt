package com.feysh.corax.config.api

import soot.Main
import soot.Scene
import soot.jimple.toolkits.callgraph.CallGraph
import soot.options.Options

public interface ISootInitializeHandler {
   public open fun configure(options: Options) {
   }

   public open fun configure(scene: Scene) {
   }

   public open fun configureAfterSceneInit(scene: Scene, options: Options) {
   }

   public open fun configure(main: Main) {
   }

   public open fun onBeforeCallGraphConstruction(scene: Scene, options: Options) {
   }

   public open fun onAfterCallGraphConstruction(cg: CallGraph, scene: Scene, options: Options) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @JvmStatic
      fun configure(`$this`: ISootInitializeHandler, options: Options) {
      }

      @JvmStatic
      fun configure(`$this`: ISootInitializeHandler, scene: Scene) {
      }

      @JvmStatic
      fun configureAfterSceneInit(`$this`: ISootInitializeHandler, scene: Scene, options: Options) {
      }

      @JvmStatic
      fun configure(`$this`: ISootInitializeHandler, main: Main) {
      }

      @JvmStatic
      fun onBeforeCallGraphConstruction(`$this`: ISootInitializeHandler, scene: Scene, options: Options) {
      }

      @JvmStatic
      fun onAfterCallGraphConstruction(`$this`: ISootInitializeHandler, cg: CallGraph, scene: Scene, options: Options) {
      }
   }
}
