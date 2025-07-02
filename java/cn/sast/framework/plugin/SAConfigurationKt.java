package cn.sast.framework.plugin;

import cn.sast.api.report.CheckType2StringKind;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.CheckType;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: SAConfiguration.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u001a\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u000e\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\"$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\b\u0012\u0004\u0012\u00020\u00060\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"get1to1SpecialIdentifier", "", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "sort", "Ljava/util/LinkedHashSet;", "Lcn/sast/framework/plugin/ConfigSerializable;", "getSort", "(Ljava/util/LinkedHashSet;)Ljava/util/LinkedHashSet;", "corax-framework"})
/* loaded from: SAConfigurationKt.class */
public final class SAConfigurationKt {
    @NotNull
    public static final String get1to1SpecialIdentifier(@NotNull CheckType checkType) {
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        return (String) CheckType2StringKind.Companion.getCheckType2StringKind().getConvert().invoke(checkType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LinkedHashSet<ConfigSerializable> getSort(LinkedHashSet<ConfigSerializable> linkedHashSet) {
        return (LinkedHashSet) CollectionsKt.toCollection(CollectionsKt.sorted(linkedHashSet), new LinkedHashSet());
    }
}
