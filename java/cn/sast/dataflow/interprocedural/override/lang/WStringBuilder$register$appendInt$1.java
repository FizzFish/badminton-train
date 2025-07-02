package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WStringBuilder.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
/* loaded from: WStringBuilder$register$appendInt$1.class */
/* synthetic */ class WStringBuilder$register$appendInt$1 extends FunctionReferenceImpl implements Function2<StringBuilder, Integer, StringBuilder> {
    public static final WStringBuilder$register$appendInt$1 INSTANCE = new WStringBuilder$register$appendInt$1();

    WStringBuilder$register$appendInt$1() {
        super(2, StringBuilder.class, "append", "append(I)Ljava/lang/StringBuilder;", 0);
    }

    public final StringBuilder invoke(StringBuilder p0, int p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return p0.append(p1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
        return invoke((StringBuilder) p1, ((Number) p2).intValue());
    }
}
