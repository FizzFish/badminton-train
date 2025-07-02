package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.LocalGenerator;
import soot.RefType;
import soot.Scene;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.Type;
import soot.Unit;
import soot.UnitPatchingChain;
import soot.Value;
import soot.jimple.Jimple;
import soot.jimple.JimpleBody;
import soot.jimple.StringConstant;
import soot.toolkits.graph.DirectedGraph;
import soot.toolkits.graph.ExceptionalUnitGraphFactory;
import soot.toolkits.graph.UnitGraph;

/* compiled from: SummaryControlFlowUnitGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010)\n��\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n��\b\u0016\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00020\u001eH\u0096\u0002J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020 H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00020 H\u0016J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020 2\u0006\u0010#\u001a\u00020\u0002H\u0016J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00020 2\u0006\u0010#\u001a\u00020\u0002H\u0016J\b\u0010%\u001a\u00020&H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006'"}, d2 = {"Lcn/sast/idfa/analysis/SummaryControlFlowUnitGraph;", "Lsoot/toolkits/graph/DirectedGraph;", "Lsoot/Unit;", "method", "Lsoot/SootMethod;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "<init>", "(Lsoot/SootMethod;Lcn/sast/idfa/analysis/InterproceduralCFG;)V", "getMethod", "()Lsoot/SootMethod;", "getIcfg", "()Lcn/sast/idfa/analysis/InterproceduralCFG;", "jimp", "Lsoot/jimple/Jimple;", "getJimp", "()Lsoot/jimple/Jimple;", "body", "Lsoot/jimple/JimpleBody;", "kotlin.jvm.PlatformType", "getBody", "()Lsoot/jimple/JimpleBody;", "Lsoot/jimple/JimpleBody;", "graph", "Lsoot/toolkits/graph/UnitGraph;", "getGraph", "()Lsoot/toolkits/graph/UnitGraph;", "setGraph", "(Lsoot/toolkits/graph/UnitGraph;)V", "iterator", "", "getHeads", "", "getTails", "getPredsOf", "s", "getSuccsOf", "size", "", "corax-idfa-framework"})
@SourceDebugExtension({"SMAP\nSummaryControlFlowUnitGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SummaryControlFlowUnitGraph.kt\ncn/sast/idfa/analysis/SummaryControlFlowUnitGraph\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,77:1\n1863#2,2:78\n*S KotlinDebug\n*F\n+ 1 SummaryControlFlowUnitGraph.kt\ncn/sast/idfa/analysis/SummaryControlFlowUnitGraph\n*L\n45#1:78,2\n*E\n"})
/* loaded from: SummaryControlFlowUnitGraph.class */
public class SummaryControlFlowUnitGraph implements DirectedGraph<Unit> {

    @NotNull
    private final SootMethod method;

    @NotNull
    private final InterproceduralCFG icfg;

    @NotNull
    private final Jimple jimp;
    private final JimpleBody body;

    @NotNull
    private UnitGraph graph;

    public SummaryControlFlowUnitGraph(@NotNull SootMethod method, @NotNull InterproceduralCFG icfg) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(icfg, "icfg");
        this.method = method;
        this.icfg = icfg;
        Jimple jimpleV = Jimple.v();
        Intrinsics.checkNotNullExpressionValue(jimpleV, "v(...)");
        this.jimp = jimpleV;
        this.body = this.jimp.newBody(this.method);
        if (!(!this.method.hasActiveBody())) {
            throw new IllegalStateException((this.method + " hasActiveBody").toString());
        }
        UnitPatchingChain units = this.body.getUnits();
        LocalGenerator lg = Scene.v().createLocalGenerator(this.body);
        Type typeV = RefType.v("java.lang.Error");
        Value valueGenerateLocal = lg.generateLocal(typeV);
        Unit unitNewAssignStmt = this.jimp.newAssignStmt(valueGenerateLocal, this.jimp.newNewExpr(typeV));
        Intrinsics.checkNotNullExpressionValue(unitNewAssignStmt, "newAssignStmt(...)");
        units.add(unitNewAssignStmt);
        SootMethodRef cref = typeV.getSootClass().getMethod("<init>", CollectionsKt.listOf(RefType.v("java.lang.String"))).makeRef();
        Unit unitNewInvokeStmt = this.jimp.newInvokeStmt(this.jimp.newSpecialInvokeExpr(valueGenerateLocal, cref, StringConstant.v("phantom method body")));
        Intrinsics.checkNotNullExpressionValue(unitNewInvokeStmt, "newInvokeStmt(...)");
        units.insertAfter(unitNewInvokeStmt, unitNewAssignStmt);
        units.insertAfter(this.jimp.newThrowStmt(valueGenerateLocal), unitNewInvokeStmt);
        Iterable nonPatchingChain = units.getNonPatchingChain();
        Intrinsics.checkNotNullExpressionValue(nonPatchingChain, "getNonPatchingChain(...)");
        Iterable $this$forEach$iv = nonPatchingChain;
        for (Object element$iv : $this$forEach$iv) {
            Unit it = (Unit) element$iv;
            InterproceduralCFG interproceduralCFG = this.icfg;
            Intrinsics.checkNotNull(it);
            interproceduralCFG.setOwnerStatement(it, this.method);
        }
        this.graph = ExceptionalUnitGraphFactory.createExceptionalUnitGraph(this.body);
    }

    @NotNull
    public final SootMethod getMethod() {
        return this.method;
    }

    @NotNull
    public final InterproceduralCFG getIcfg() {
        return this.icfg;
    }

    @NotNull
    public final Jimple getJimp() {
        return this.jimp;
    }

    public final JimpleBody getBody() {
        return this.body;
    }

    @NotNull
    public final UnitGraph getGraph() {
        return this.graph;
    }

    public final void setGraph(@NotNull UnitGraph unitGraph) {
        Intrinsics.checkNotNullParameter(unitGraph, "<set-?>");
        this.graph = unitGraph;
    }

    @NotNull
    public Iterator<Unit> iterator() {
        Iterator<Unit> it = this.graph.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        return it;
    }

    @NotNull
    public List<Unit> getHeads() {
        List<Unit> heads = this.graph.getHeads();
        Intrinsics.checkNotNullExpressionValue(heads, "getHeads(...)");
        return heads;
    }

    @NotNull
    public List<Unit> getTails() {
        List<Unit> tails = this.graph.getTails();
        Intrinsics.checkNotNullExpressionValue(tails, "getTails(...)");
        return tails;
    }

    @NotNull
    public List<Unit> getPredsOf(@NotNull Unit s) {
        Intrinsics.checkNotNullParameter(s, "s");
        List<Unit> predsOf = this.graph.getPredsOf(s);
        Intrinsics.checkNotNullExpressionValue(predsOf, "getPredsOf(...)");
        return predsOf;
    }

    @NotNull
    public List<Unit> getSuccsOf(@NotNull Unit s) {
        Intrinsics.checkNotNullParameter(s, "s");
        List<Unit> succsOf = this.graph.getSuccsOf(s);
        Intrinsics.checkNotNullExpressionValue(succsOf, "getSuccsOf(...)");
        return succsOf;
    }

    public int size() {
        return this.graph.size();
    }
}
