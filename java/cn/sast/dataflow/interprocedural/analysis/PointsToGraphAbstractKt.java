package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��*\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n��\n\u0002\u0010&\n��\u001a6\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\bø\u0001��\u001ap\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b��\u0010\t\"\u0004\b\u0001\u0010\u0002*\u0016\u0012\u0006\b\u0001\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00030\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\b2*\u0010\u0004\u001a&\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00030\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00030\u0005H\u0086\bø\u0001��\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"foreach", "", "V", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "transform", "Lkotlin/Function1;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "mapTo", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "K", "", "destination", "", "corax-data-flow"})
/* loaded from: PointsToGraphAbstractKt.class */
public final class PointsToGraphAbstractKt {
    public static final <V> void foreach(@NotNull IHeapValues<V> iHeapValues, @NotNull Function1<? super CompanionV<V>, Unit> function1) {
        Intrinsics.checkNotNullParameter(iHeapValues, "<this>");
        Intrinsics.checkNotNullParameter(function1, "transform");
        for (CompanionV e : iHeapValues) {
            function1.invoke(e);
        }
    }

    @NotNull
    public static final <K, V> IHeapValues.Builder<V> mapTo(@NotNull Map<? extends K, ? extends IHeapValues<V>> map, @NotNull IHeapValues.Builder<V> builder, @NotNull Function1<? super Map.Entry<? extends K, ? extends IHeapValues<V>>, ? extends IHeapValues<V>> function1) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(builder, "destination");
        Intrinsics.checkNotNullParameter(function1, "transform");
        for (Map.Entry item : map.entrySet()) {
            builder.add((IHeapValues) function1.invoke(item));
        }
        return builder;
    }
}
