package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: Exceptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n\u0002\u0010\u0003\n��\u001a\n\u0010��\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"checkCritical", "", "", "corax-api"})
/* loaded from: ExceptionsKt.class */
public final class ExceptionsKt {
    public static final void checkCritical(@NotNull Throwable $this$checkCritical) {
        Intrinsics.checkNotNullParameter($this$checkCritical, "<this>");
        if ($this$checkCritical instanceof IOException) {
            String message = $this$checkCritical.getMessage();
            boolean z = message != null && StringsKt.contains(message, "no space left", true);
            if (z) {
                $this$checkCritical.printStackTrace(System.err);
                System.exit(2);
                throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
            }
            return;
        }
        if ($this$checkCritical instanceof OutOfMemoryError) {
            $this$checkCritical.printStackTrace(System.err);
            System.exit(10);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }
    }
}
