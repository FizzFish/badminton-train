package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WObject.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
/* loaded from: WObject$register$hashCodeSignature$1.class */
/* synthetic */ class WObject$register$hashCodeSignature$1 extends FunctionReferenceImpl implements Function1<Object, Integer> {
    public static final WObject$register$hashCodeSignature$1 INSTANCE = new WObject$register$hashCodeSignature$1();

    WObject$register$hashCodeSignature$1() {
        super(1, Object.class, "hashCode", "hashCode()I", 0);
    }

    /* renamed from: invoke, reason: merged with bridge method [inline-methods] */
    public final Integer m248invoke(Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return Integer.valueOf(p0.hashCode());
    }
}
