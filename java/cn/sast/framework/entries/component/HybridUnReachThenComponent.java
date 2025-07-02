package cn.sast.framework.entries.component;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.entries.java.UnReachableEntryProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: HybridUnReachThenComponent.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcn/sast/framework/entries/component/HybridUnReachThenComponent;", "Lcn/sast/framework/entries/java/UnReachableEntryProvider;", "ctx", "Lcn/sast/framework/SootCtx;", "<init>", "(Lcn/sast/framework/SootCtx;)V", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "corax-framework"})
/* loaded from: HybridUnReachThenComponent.class */
public final class HybridUnReachThenComponent extends UnReachableEntryProvider {

    @NotNull
    private final Flow<IEntryPointProvider.AnalyzeTask> iterator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HybridUnReachThenComponent(@NotNull SootCtx ctx) {
        super(ctx, null, 2, null);
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        this.iterator = FlowKt.flow(new HybridUnReachThenComponent$iterator$1(this, ctx, null));
    }

    @Override // cn.sast.framework.entries.java.UnReachableEntryProvider, cn.sast.framework.entries.IEntryPointProvider
    @NotNull
    public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
        return this.iterator;
    }
}
