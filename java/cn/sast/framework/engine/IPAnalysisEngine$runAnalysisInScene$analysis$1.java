package cn.sast.framework.engine;

import cn.sast.api.report.ICoverageCollector;
import cn.sast.dataflow.interprocedural.analysis.AIContext;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IVGlobal;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.InterProceduralValueAnalysis;
import cn.sast.dataflow.interprocedural.check.KillEntry;
import cn.sast.dataflow.interprocedural.check.PointsToGraphBuilder;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.checker.IIPAnalysisResultCollector;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.Context;
import com.feysh.corax.cache.analysis.SootInfoCache;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import soot.SootMethod;
import soot.Unit;
import soot.toolkits.graph.DirectedGraph;

/* compiled from: IPAnalysisEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��?\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001��\b\n\u0018��2\u00020\u0001J@\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016JJ\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\n2\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0096@¢\u0006\u0002\u0010\u0017¨\u0006\u0018"}, d2 = {"cn/sast/framework/engine/IPAnalysisEngine$runAnalysisInScene$analysis$1", "Lcn/sast/dataflow/interprocedural/check/InterProceduralValueAnalysis;", "makeContext", "Lcn/sast/dataflow/interprocedural/analysis/AIContext;", "method", "Lsoot/SootMethod;", "cfg", "Lsoot/toolkits/graph/DirectedGraph;", "Lsoot/Unit;", "entryValue", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "reverse", "", "isAnalyzable", "normalFlowFunction", "context", "node", "succ", "inValue", "isNegativeBranch", "Ljava/util/concurrent/atomic/AtomicBoolean;", "(Lcn/sast/dataflow/interprocedural/analysis/AIContext;Lsoot/Unit;Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IFact;Ljava/util/concurrent/atomic/AtomicBoolean;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-framework"})
/* loaded from: IPAnalysisEngine$runAnalysisInScene$analysis$1.class */
public final class IPAnalysisEngine$runAnalysisInScene$analysis$1 extends InterProceduralValueAnalysis {
    final /* synthetic */ SootInfoCache $info;
    final /* synthetic */ IIPAnalysisResultCollector $result;
    final /* synthetic */ ICoverageCollector $coverage;
    final /* synthetic */ IPAnalysisEngine this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    IPAnalysisEngine$runAnalysisInScene$analysis$1(IVGlobal $vg, SootInfoCache $info, IIPAnalysisResultCollector $result, ICoverageCollector $coverage, IPAnalysisEngine $receiver) {
        super($vg, null, null, 6, null);
        this.$info = $info;
        this.$result = $result;
        this.$coverage = $coverage;
        this.this$0 = $receiver;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis, cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public /* bridge */ /* synthetic */ Object normalFlowFunction(Context context, Unit unit, Unit unit2, Object inValue, AtomicBoolean isNegativeBranch, Continuation $completion) {
        return normalFlowFunction((AIContext) context, unit, unit2, (IFact<IValue>) inValue, isNegativeBranch, (Continuation<? super IFact<IValue>>) $completion);
    }

    @Override // cn.sast.idfa.analysis.ForwardInterProceduralAnalysis
    public AIContext makeContext(SootMethod method, DirectedGraph<Unit> directedGraph, IFact<IValue> iFact, boolean reverse, boolean isAnalyzable) {
        Intrinsics.checkNotNullParameter(method, "method");
        Intrinsics.checkNotNullParameter(directedGraph, "cfg");
        Intrinsics.checkNotNullParameter(iFact, "entryValue");
        AbstractHeapFactory<IValue> hf = getHf();
        List heads = directedGraph.getHeads();
        Intrinsics.checkNotNullExpressionValue(heads, "getHeads(...)");
        Object objFirst = CollectionsKt.first(heads);
        Intrinsics.checkNotNullExpressionValue(objFirst, "first(...)");
        HeapValuesEnv env = hf.env((Unit) objFirst);
        if (iFact.isValid()) {
            AIContext it = new AIContext(this.$info, getIcfg(), this.$result, method, directedGraph, reverse, isAnalyzable);
            IFact.Builder entryBuilder = iFact.builder();
            KillEntry kill = new KillEntry(method, env);
            Intrinsics.checkNotNull(entryBuilder, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.PointsToGraphBuilder");
            ((PointsToGraphBuilder) entryBuilder).apply(kill.getFactory());
            it.setEntries(kill.getEntries());
            it.setEntryValue(((PointsToGraphBuilder) entryBuilder).build());
            return it;
        }
        AIContext it2 = new AIContext(this.$info, getIcfg(), this.$result, method, directedGraph, reverse, isAnalyzable);
        it2.setEntries(SetsKt.emptySet());
        it2.setEntryValue(iFact);
        return it2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @Override // cn.sast.dataflow.interprocedural.analysis.AJimpleInterProceduralAnalysis
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object normalFlowFunction(cn.sast.dataflow.interprocedural.analysis.AIContext r11, soot.Unit r12, soot.Unit r13, cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue> r14, java.util.concurrent.atomic.AtomicBoolean r15, kotlin.coroutines.Continuation<? super cn.sast.dataflow.interprocedural.analysis.IFact<cn.sast.dataflow.interprocedural.analysis.IValue>> r16) {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.IPAnalysisEngine$runAnalysisInScene$analysis$1.normalFlowFunction(cn.sast.dataflow.interprocedural.analysis.AIContext, soot.Unit, soot.Unit, cn.sast.dataflow.interprocedural.analysis.IFact, java.util.concurrent.atomic.AtomicBoolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
