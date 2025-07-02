package cn.sast.graph;

import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.util.dot.DotGraph;

/* compiled from: GraphPlot.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0012\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"dump", "", "Lsoot/util/dot/DotGraph;", "output", "Lcn/sast/common/IResFile;", "corax-api"})
/* loaded from: GraphPlotKt.class */
public final class GraphPlotKt {
    public static final void dump(@NotNull DotGraph $this$dump, @NotNull IResFile output) throws IOException {
        Intrinsics.checkNotNullParameter($this$dump, "<this>");
        Intrinsics.checkNotNullParameter(output, "output");
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(output.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        Throwable th = null;
        try {
            try {
                OutputStream out = outputStream;
                $this$dump.render(out, 0);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, (Throwable) null);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(outputStream, th);
            throw th2;
        }
    }
}
