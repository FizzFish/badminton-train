package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ExtSettings.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
/* loaded from: ExtSettings$dataFlowCacheMaximumSizeFactor$3.class */
/* synthetic */ class ExtSettings$dataFlowCacheMaximumSizeFactor$3 extends FunctionReferenceImpl implements Function1<String, Double> {
    public static final ExtSettings$dataFlowCacheMaximumSizeFactor$3 INSTANCE = new ExtSettings$dataFlowCacheMaximumSizeFactor$3();

    ExtSettings$dataFlowCacheMaximumSizeFactor$3() {
        super(1, StringsKt.class, "toDouble", "toDouble(Ljava/lang/String;)D", 1);
    }

    public final Double invoke(String p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Double.valueOf(Double.parseDouble(p0));
    }
}
