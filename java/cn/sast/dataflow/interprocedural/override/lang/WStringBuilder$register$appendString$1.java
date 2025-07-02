package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WStringBuilder.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
/* loaded from: WStringBuilder$register$appendString$1.class */
/* synthetic */ class WStringBuilder$register$appendString$1 extends FunctionReferenceImpl implements Function2<StringBuilder, String, StringBuilder> {
    public static final WStringBuilder$register$appendString$1 INSTANCE = new WStringBuilder$register$appendString$1();

    WStringBuilder$register$appendString$1() {
        super(2, StringBuilder.class, "append", "append(Ljava/lang/String;)Ljava/lang/StringBuilder;", 0);
    }

    public final StringBuilder invoke(StringBuilder p0, String p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return p0.append(p1);
    }
}
