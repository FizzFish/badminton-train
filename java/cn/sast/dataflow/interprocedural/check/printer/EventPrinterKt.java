package cn.sast.dataflow.interprocedural.check.printer;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.utils.UtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import soot.Type;

/* compiled from: EventPrinter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010��\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"pname", "", "Lsoot/Type;", "getPname", "(Lsoot/Type;)Ljava/lang/String;", "corax-data-flow"})
/* loaded from: EventPrinterKt.class */
public final class EventPrinterKt {
    @NotNull
    public static final String getPname(@NotNull Type $this$pname) {
        Intrinsics.checkNotNullParameter($this$pname, "<this>");
        String name = UtilsKt.getTypename($this$pname);
        if (name != null) {
            return StringsKt.removePrefix(name, "java.lang.");
        }
        String string = $this$pname.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }
}
