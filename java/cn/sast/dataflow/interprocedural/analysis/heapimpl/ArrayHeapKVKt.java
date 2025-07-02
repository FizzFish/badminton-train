package cn.sast.dataflow.interprocedural.analysis.heapimpl;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* compiled from: ArrayHeapKV.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\b\n\u0002\b\u0003\u001a#\u0010��\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003H��¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"isValidKey", "", "key", "", "size", "(Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Boolean;", "corax-data-flow"})
/* loaded from: ArrayHeapKVKt.class */
public final class ArrayHeapKVKt {
    @Nullable
    public static final Boolean isValidKey(@Nullable Integer key, @Nullable Integer size) {
        if (key != null) {
            if (size != null) {
                if (key.intValue() >= size.intValue()) {
                    return false;
                }
                return Boolean.valueOf(key.intValue() >= 0);
            }
            if (key.intValue() < 0) {
                return false;
            }
            return null;
        }
        return null;
    }
}
