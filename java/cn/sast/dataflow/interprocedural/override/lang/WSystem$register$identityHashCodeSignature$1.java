package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: WSystem.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
/* loaded from: WSystem$register$identityHashCodeSignature$1.class */
/* synthetic */ class WSystem$register$identityHashCodeSignature$1 extends FunctionReferenceImpl implements Function1<Object, Integer> {
    public static final WSystem$register$identityHashCodeSignature$1 INSTANCE = new WSystem$register$identityHashCodeSignature$1();

    WSystem$register$identityHashCodeSignature$1() {
        super(1, System.class, "identityHashCode", "identityHashCode(Ljava/lang/Object;)I", 0);
    }

    /* renamed from: invoke, reason: merged with bridge method [inline-methods] */
    public final Integer m263invoke(Object p0) {
        return Integer.valueOf(System.identityHashCode(p0));
    }
}
