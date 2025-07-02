package com.feysh.corax.config.api

import soot.SootMethod
import soot.SootMethodRef
import soot.Type
import soot.Unit
import soot.jimple.InvokeExpr

public interface IInvokeCheckPoint : IClassMemberCheckPoint {
   public val container: SootMethod
   public val callSite: Unit?
   public val invokeMethodRef: SootMethodRef?
   public val declaredReceiverType: Type?
   public val callee: SootMethod
   public val invokeExpr: InvokeExpr?
}
