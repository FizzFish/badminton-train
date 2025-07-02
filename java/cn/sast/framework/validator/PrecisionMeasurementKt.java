package cn.sast.framework.validator;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;

/* compiled from: PrecisionMeasurement.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0014\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0007\n\u0002\b\u0003\"\u0015\u0010\u0003\u001a\u00020\u0002*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006*\u0018\b��\u0010��\"\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0007"}, d2 = {"ExpectBugType", "Lcn/sast/api/report/ExpectBugAnnotationData;", "", "fm", "", "getFm", "(F)Ljava/lang/String;", "corax-framework"})
/* loaded from: PrecisionMeasurementKt.class */
public final class PrecisionMeasurementKt {
    @NotNull
    public static final String getFm(float $this$fm) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Float.valueOf($this$fm)};
        String str = String.format("%.2f", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
