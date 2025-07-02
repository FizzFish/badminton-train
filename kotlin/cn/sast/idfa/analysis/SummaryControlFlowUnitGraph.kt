// IntelliJ API Decompiler stub source generated from a class file
// Implementation of methods is not available

package cn.sast.idfa.analysis

import kotlin.collections.MutableIterator
import soot.toolkits.graph.DirectedGraph
import soot.toolkits.graph.UnitGraph
import soot.toolkits.graph.ExceptionalUnitGraphFactory
import soot.jimple.Jimple
import soot.jimple.JimpleBody
import soot.jimple.StringConstant
import soot.SootMethod
import soot.SootMethodRef
import soot.RefType
import soot.Scene
import soot.Type
import soot.Value
import soot.Unit
import soot.LocalGenerator

/**
 * A tiny wrapper graph used for summary edges in the interprocedural CFG.
 */
class SummaryControlFlowUnitGraph(
    val method: SootMethod,
    val icfg: InterproceduralCFG
) : DirectedGraph<Unit> {

    val jimp: Jimple = Jimple.v()
    val body: JimpleBody = jimp.newBody(method)
    var graph: UnitGraph

    init {
        require(!method.hasActiveBody()) { "$method hasActiveBody" }

        val units = body.units
        val lg: LocalGenerator = Scene.v().createLocalGenerator(body)
        val typeV: Type = RefType.v("java.lang.Error")
        val local = lg.generateLocal(typeV)
        val assign = jimp.newAssignStmt(local, jimp.newNewExpr(typeV))
        units.add(assign)
        val cref: SootMethodRef =
            typeV.sootClass.getMethod("<init>", listOf(RefType.v("java.lang.String"))).makeRef()
        val invoke = jimp.newInvokeStmt(jimp.newSpecialInvokeExpr(local, cref, StringConstant.v("phantom method body")))
        units.insertAfter(invoke, assign)
        units.insertAfter(jimp.newThrowStmt(local), invoke)
        for (it in units.nonPatchingChain) {
            icfg.setOwnerStatement(it, method)
        }
        graph = ExceptionalUnitGraphFactory.createExceptionalUnitGraph(body)
    }

    override fun iterator(): MutableIterator<Unit> = graph.iterator()
    override fun getHeads(): List<Unit> = graph.heads
    override fun getTails(): List<Unit> = graph.tails
    override fun getPredsOf(s: Unit): List<Unit> = graph.getPredsOf(s)
    override fun getSuccsOf(s: Unit): List<Unit> = graph.getSuccsOf(s)
    override fun size(): Int = graph.size()
}


