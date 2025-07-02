package cn.sast.framework.entries.allmethods;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import org.jetbrains.annotations.NotNull;
import soot.Scene;
import soot.SootClass;

/* compiled from: AllMethodsProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018��2\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/entries/allmethods/AllMethodsProvider;", "Lcn/sast/framework/entries/IEntryPointProvider;", "classes", "", "Lsoot/SootClass;", "<init>", "(Ljava/util/Collection;)V", "getClasses", "()Ljava/util/Collection;", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "corax-framework"})
/* loaded from: AllMethodsProvider.class */
public final class AllMethodsProvider implements IEntryPointProvider {

    @NotNull
    private final Collection<SootClass> classes;

    @NotNull
    private final Flow<IEntryPointProvider.AnalyzeTask> iterator;

    public AllMethodsProvider() {
        this(null, 1, null);
    }

    public AllMethodsProvider(@NotNull Collection<? extends SootClass> collection) {
        Intrinsics.checkNotNullParameter(collection, "classes");
        this.classes = collection;
        this.iterator = FlowKt.flow(new AllMethodsProvider$iterator$1(this, null));
    }

    public /* synthetic */ AllMethodsProvider(Collection collection, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Collection) Scene.v().getApplicationClasses() : collection);
    }

    @NotNull
    public final Collection<SootClass> getClasses() {
        return this.classes;
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void startAnalyse() {
        IEntryPointProvider.DefaultImpls.startAnalyse(this);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void endAnalyse() {
        IEntryPointProvider.DefaultImpls.endAnalyse(this);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    @NotNull
    public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
        return this.iterator;
    }
}
