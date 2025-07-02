package com.feysh.corax.config.api

import com.feysh.corax.config.api.report.Region
import com.feysh.corax.config.api.report.Region.Mutable
import java.nio.file.Path
import soot.SootClass
import soot.SootField
import soot.SootMethod
import soot.Unit
import soot.jimple.InvokeExpr
import soot.tagkit.Host

public data class BugMessage(msg: (com.feysh.corax.config.api.BugMessage.Env) -> String) {
   public final val msg: (com.feysh.corax.config.api.BugMessage.Env) -> String

   init {
      this.msg = msg;
   }

   public operator fun component1(): (com.feysh.corax.config.api.BugMessage.Env) -> String {
      return this.msg;
   }

   public fun copy(msg: (com.feysh.corax.config.api.BugMessage.Env) -> String = this.msg): BugMessage {
      return new BugMessage(msg);
   }

   public override fun toString(): String {
      return "BugMessage(msg=${this.msg})";
   }

   public override fun hashCode(): Int {
      return this.msg.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is BugMessage) {
         return false;
      } else {
         return this.msg == (other as BugMessage).msg;
      }
   }

   public interface Env {
      public var container: SootMethod?
         internal final set

      public var callSite: Unit?
         internal final set

      public var invokeExpr: InvokeExpr?
         internal final set

      public var callee: SootMethod?
         internal final set

      public var clazz: SootClass?
         internal final set

      public var method: SootMethod?
         internal final set

      public var field: SootField?
         internal final set

      public var fileName: String?
         internal final set

      public var region: Mutable
         internal final set

      public val args: MutableMap<Any, Any>

      public abstract fun appendPathEvent(message: Map<Language, String>, loc: Host, region: Region? = ...) {
      }

      public abstract fun appendPathEvent(message: Map<Language, String>, loc: Path, region: Region) {
      }

      // $VF: Class flags could not be determined
      internal class DefaultImpls
   }
}
