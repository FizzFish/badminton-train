package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018��*\u0004\b��\u0010\u00012\u00020\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00018��2\u0006\u0010\u0004\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u0005J\u001e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028��0\u0006H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "V", "", "checkNeedReplace", "old", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "c", "context", "value", "corax-data-flow"})
/* loaded from: IReNew.class */
public interface IReNew<V> {
    @Nullable
    V checkNeedReplace(V v);

    @Nullable
    CompanionV<V> checkNeedReplace(@NotNull CompanionV<V> companionV);

    @NotNull
    IReNew<V> context(@NotNull Object obj);

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IReNew$DefaultImpls.class */
    public static final class DefaultImpls {
        @Nullable
        public static <V> V checkNeedReplace(@NotNull IReNew<V> iReNew, V v) {
            return null;
        }

        @Nullable
        public static <V> CompanionV<V> checkNeedReplace(@NotNull IReNew<V> iReNew, @NotNull CompanionV<V> companionV) {
            Intrinsics.checkNotNullParameter(companionV, "c");
            return null;
        }

        @NotNull
        public static <V> IReNew<V> context(@NotNull IReNew<V> iReNew, @NotNull Object value) {
            Intrinsics.checkNotNullParameter(value, "value");
            return iReNew;
        }
    }
}
