package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: SparsePropgrateAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010��\u001a\u00020\u0001*\u00020\u0002H��\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H��¨\u0006\u0004"}, d2 = {"isLeft", "", "Lcn/sast/dataflow/infoflow/svfa/ValueLocation;", "isRight", "corax-data-flow"})
/* loaded from: SparsePropgrateAnalyzeKt.class */
public final class SparsePropgrateAnalyzeKt {
    public static final boolean isLeft(@NotNull ValueLocation $this$isLeft) {
        Intrinsics.checkNotNullParameter($this$isLeft, "<this>");
        return $this$isLeft == ValueLocation.Left;
    }

    public static final boolean isRight(@NotNull ValueLocation $this$isRight) {
        Intrinsics.checkNotNullParameter($this$isRight, "<this>");
        return $this$isRight != ValueLocation.Left;
    }
}
