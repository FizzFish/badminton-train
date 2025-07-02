package cn.sast.framework.entries.component;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.Scene;
import soot.SootMethod;

/* compiled from: ComponentEntryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018�� \u00132\u00020\u0001:\u0001\u0013B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcn/sast/framework/entries/component/ComponentEntryProvider;", "Lcn/sast/framework/entries/IEntryPointProvider;", "ctx", "Lcn/sast/framework/SootCtx;", "entries", "", "", "<init>", "(Lcn/sast/framework/SootCtx;Ljava/util/Collection;)V", "method", "", "Lsoot/SootMethod;", "getMethod", "()Ljava/util/Set;", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "Companion", "corax-framework"})
/* loaded from: ComponentEntryProvider.class */
public final class ComponentEntryProvider implements IEntryPointProvider {

    @NotNull
    private final SootCtx ctx;

    @NotNull
    private final Collection<String> entries;

    @NotNull
    private final Flow<IEntryPointProvider.AnalyzeTask> iterator;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ComponentEntryProvider::logger$lambda$0);

    public ComponentEntryProvider(@NotNull SootCtx ctx, @NotNull Collection<String> collection) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(collection, "entries");
        this.ctx = ctx;
        this.entries = collection;
        this.iterator = FlowKt.flow(new ComponentEntryProvider$iterator$1(this, null));
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void startAnalyse() {
        IEntryPointProvider.DefaultImpls.startAnalyse(this);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void endAnalyse() {
        IEntryPointProvider.DefaultImpls.endAnalyse(this);
    }

    /* compiled from: ComponentEntryProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/entries/component/ComponentEntryProvider$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: ComponentEntryProvider$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final Set<SootMethod> getMethod() {
        ComponentEntryPointCreator entryPointCreator = new ComponentEntryPointCreator(this.entries);
        SootMethod dummyMainMethod = entryPointCreator.createDummyMain();
        Intrinsics.checkNotNullExpressionValue(dummyMainMethod, "createDummyMain(...)");
        if (!dummyMainMethod.getDeclaringClass().isInScene()) {
            Scene.v().addClass(dummyMainMethod.getDeclaringClass());
        }
        dummyMainMethod.getDeclaringClass().setApplicationClass();
        return SetsKt.setOf(dummyMainMethod);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    @NotNull
    public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
        return this.iterator;
    }
}
