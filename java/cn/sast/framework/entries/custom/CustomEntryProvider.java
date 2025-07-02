package cn.sast.framework.entries.custom;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Arrays;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.SootMethod;

/* compiled from: CustomEntryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018�� \u000f2\u00020\u0001:\u0001\u000fB\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcn/sast/framework/entries/custom/CustomEntryProvider;", "Lcn/sast/framework/entries/IEntryPointProvider;", "entries", "", "Lsoot/SootMethod;", "<init>", "(Ljava/util/Set;)V", "method", "getMethod", "()Ljava/util/Set;", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "Companion", "corax-framework"})
/* loaded from: CustomEntryProvider.class */
public class CustomEntryProvider implements IEntryPointProvider {

    @NotNull
    private final Set<SootMethod> entries;

    @NotNull
    private final Flow<IEntryPointProvider.AnalyzeTask> iterator;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(CustomEntryProvider::logger$lambda$1);

    public CustomEntryProvider(@NotNull Set<? extends SootMethod> set) {
        Intrinsics.checkNotNullParameter(set, "entries");
        this.entries = set;
        this.iterator = FlowKt.flow(new CustomEntryProvider$iterator$1(this, null));
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void startAnalyse() {
        IEntryPointProvider.DefaultImpls.startAnalyse(this);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void endAnalyse() {
        IEntryPointProvider.DefaultImpls.endAnalyse(this);
    }

    @NotNull
    public final Set<SootMethod> getMethod() {
        if (this.entries.isEmpty()) {
            logger.warn("No entry points");
        }
        logger.info(() -> {
            return _get_method_$lambda$0(r1);
        });
        return this.entries;
    }

    private static final Object _get_method_$lambda$0(CustomEntryProvider this$0) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Integer.valueOf(this$0.entries.size())};
        String str = String.format("custom entry methods num :%d", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    @NotNull
    public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
        return this.iterator;
    }

    /* compiled from: CustomEntryProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/entries/custom/CustomEntryProvider$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: CustomEntryProvider$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }
}
