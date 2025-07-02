package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.Unit;
import soot.jimple.internal.JNopStmt;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b��\u0018�� \t2\u00020\u0001:\u0001\tB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\b¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/HeapValuesEnvImpl;", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "node", "Lsoot/Unit;", "<init>", "(Lsoot/Unit;)V", "p", "Lcn/sast/dataflow/interprocedural/check/IPath;", "(Lcn/sast/dataflow/interprocedural/check/IPath;)V", "Companion", "corax-data-flow"})
/* loaded from: HeapValuesEnvImpl.class */
public final class HeapValuesEnvImpl extends HeapValuesEnv {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final JNopStmt phantomUnit = new JNopStmt();

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeapValuesEnvImpl(@NotNull Unit node) {
        super(node);
        Intrinsics.checkNotNullParameter(node, "node");
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/HeapValuesEnvImpl$Companion;", "", "<init>", "()V", "phantomUnit", "Lsoot/jimple/internal/JNopStmt;", "getPhantomUnit", "()Lsoot/jimple/internal/JNopStmt;", "corax-data-flow"})
    /* loaded from: HeapValuesEnvImpl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final JNopStmt getPhantomUnit() {
            return HeapValuesEnvImpl.phantomUnit;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public HeapValuesEnvImpl(@NotNull IPath p) {
        this(p.getNode());
        Intrinsics.checkNotNullParameter(p, "p");
    }
}
