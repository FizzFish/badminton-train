// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.check

public open class CallBackManager public constructor() {
    public final val callBacksOfMethod: kotlin.collections.MutableMap<java.lang.Class<out cn.sast.idfa.check.IStmtCB>, kotlin.collections.MutableMap<soot.SootMethod, kotlin.collections.MutableList<kotlin.Any>>> /* compiled code */

    public final val callBacksOfUnit: kotlin.collections.MutableMap<java.lang.Class<out cn.sast.idfa.check.IStmtCB>, kotlin.collections.MutableMap<soot.Unit, kotlin.collections.MutableList<kotlin.Any>>> /* compiled code */

    private final val jimpleOverride: kotlin.collections.MutableMap<soot.SootMethod, soot.toolkits.graph.UnitGraph> /* compiled code */

    public final val miss: kotlin.collections.MutableMap<java.lang.Class<out cn.sast.idfa.check.IStmtCB>, kotlin.collections.MutableSet<soot.SootMethod>> /* compiled code */

    public final val hit: kotlin.collections.MutableMap<java.lang.Class<out cn.sast.idfa.check.IStmtCB>, kotlin.collections.MutableSet<kotlin.Pair<soot.SootMethod, soot.SootMethod>>> /* compiled code */

    public final inline fun <reified typeCB : cn.sast.idfa.check.IStmtCB> put(key: soot.SootMethod, noinline cb: suspend typeCB.() -> kotlin.Unit): kotlin.Unit { /* compiled code */ }

    public final fun <typeCB> put(x: java.lang.Class<out cn.sast.idfa.check.IStmtCB>, key: soot.SootMethod, cb: suspend typeCB.() -> kotlin.Unit): kotlin.Unit { /* compiled code */ }

    public open fun <typeCB> get(x: java.lang.Class<out cn.sast.idfa.check.IStmtCB>, method: soot.SootMethod): kotlin.collections.List<suspend typeCB.() -> kotlin.Unit>? { /* compiled code */ }

    public open fun <typeCB> getRaw(x: java.lang.Class<out cn.sast.idfa.check.IStmtCB>, key: soot.SootMethod): kotlin.collections.List<suspend typeCB.() -> kotlin.Unit>? { /* compiled code */ }

    public final inline fun <reified typeCB : cn.sast.idfa.check.IStmtCB> put(key: soot.Unit, noinline cb: typeCB.() -> kotlin.Unit): kotlin.Unit { /* compiled code */ }

    public final fun <typeCB> put(x: java.lang.Class<out cn.sast.idfa.check.IStmtCB>, key: soot.Unit, cb: typeCB.() -> kotlin.Unit): kotlin.Unit { /* compiled code */ }

    public open fun <typeCB> get(x: java.lang.Class<out cn.sast.idfa.check.IStmtCB>, key: soot.Unit): kotlin.collections.List<suspend typeCB.() -> kotlin.Unit>? { /* compiled code */ }

    public final inline fun <reified typeCB : cn.sast.idfa.check.IStmtCB> get(key: kotlin.Any): kotlin.collections.List<suspend typeCB.() -> kotlin.Unit>? { /* compiled code */ }

    public final inline fun <reified typeCB : cn.sast.idfa.check.IStmtCB> get(vararg keys: kotlin.Any): kotlin.collections.List<suspend typeCB.() -> kotlin.Unit>? { /* compiled code */ }

    public final fun putUnitGraphOverride(key: soot.SootMethod, override: soot.toolkits.graph.UnitGraph): soot.toolkits.graph.UnitGraph? { /* compiled code */ }

    public final fun getUnitGraphOverride(key: soot.SootMethod): soot.toolkits.graph.UnitGraph? { /* compiled code */ }

    public final fun reportMissSummaryMethod(reportMissingMethod: (soot.SootMethod) -> kotlin.Unit): kotlin.Unit { /* compiled code */ }
}

