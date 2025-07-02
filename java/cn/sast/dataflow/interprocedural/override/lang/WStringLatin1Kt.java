package cn.sast.dataflow.interprocedural.override.lang;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;

/* compiled from: WStringLatin1.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n��\u001a\u000e\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"canEncode", "", "cp", "", "corax-data-flow"})
/* loaded from: WStringLatin1Kt.class */
public final class WStringLatin1Kt {
    public static final boolean canEncode(int cp) {
        return (cp >>> 8) == 0;
    }
}
