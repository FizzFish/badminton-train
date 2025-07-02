package cn.sast.framework.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\"\u0010��\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0006"}, d2 = {"listEndsWith", "Lcn/sast/framework/report/ListSuffixRelation;", "array1", "", "", "array2", "corax-framework"})
/* loaded from: JavaSourceLocatorKt.class */
public final class JavaSourceLocatorKt {
    @NotNull
    public static final ListSuffixRelation listEndsWith(@NotNull List<String> list, @NotNull List<String> list2) {
        Intrinsics.checkNotNullParameter(list, "array1");
        Intrinsics.checkNotNullParameter(list2, "array2");
        int minSize = Math.min(list.size(), list2.size());
        if (minSize == 0) {
            return ListSuffixRelation.NeitherSuffix;
        }
        for (int i = 0; i < minSize; i++) {
            if (!Intrinsics.areEqual(list.get((list.size() - minSize) + i), list2.get((list2.size() - minSize) + i))) {
                return ListSuffixRelation.NeitherSuffix;
            }
        }
        return list.size() < list2.size() ? ListSuffixRelation.AIsSuffixOfB : list.size() > list2.size() ? ListSuffixRelation.BIsSuffixOfA : ListSuffixRelation.Equals;
    }
}
