package cn.sast.framework.entries.custom;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;

/* compiled from: HybridCustomThenComponent.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/entries/custom/HybridCustomThenComponent;", "Lcn/sast/framework/entries/custom/CustomEntryProvider;", "ctx", "Lcn/sast/framework/SootCtx;", "entries", "", "Lsoot/SootMethod;", "<init>", "(Lcn/sast/framework/SootCtx;Ljava/util/Set;)V", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "corax-framework"})
/* loaded from: HybridCustomThenComponent.class */
public final class HybridCustomThenComponent extends CustomEntryProvider {

    @NotNull
    private final Flow<IEntryPointProvider.AnalyzeTask> iterator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HybridCustomThenComponent(@NotNull SootCtx ctx, @NotNull Set<? extends SootMethod> set) {
        super(set);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(set, "entries");
        this.iterator = FlowKt.flow(new HybridCustomThenComponent$iterator$1(this, ctx, set, null));
    }

    @Override // cn.sast.framework.entries.custom.CustomEntryProvider, cn.sast.framework.entries.IEntryPointProvider
    @NotNull
    public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
        return this.iterator;
    }
}
