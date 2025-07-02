package cn.sast.cli.command.tools;

import cn.sast.api.report.CheckType2StringKind;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.CheckType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: CheckerInfoGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010��\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"id", "", "Lcom/feysh/corax/config/api/CheckType;", "getId", "(Lcom/feysh/corax/config/api/CheckType;)Ljava/lang/String;", "corax-cli"})
/* loaded from: CheckerInfoGeneratorKt.class */
public final class CheckerInfoGeneratorKt {
    @NotNull
    public static final String getId(@NotNull CheckType $this$id) {
        Intrinsics.checkNotNullParameter($this$id, "<this>");
        return (String) CheckType2StringKind.Companion.getCheckType2StringKind().getConvert().invoke($this$id);
    }
}
