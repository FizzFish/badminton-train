package cn.sast.dataflow.interprocedural.check.printer;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.SootClass;
import soot.SootMethodInterface;

/* compiled from: SimpleUnitPrinter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0016\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\u001a\u0016\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0011\u0010��\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\b¨\u0006\u0007"}, d2 = {"getPrettyMethodName", "", "declaringClass", "Lsoot/SootClass;", "name", "m", "Lsoot/SootMethodInterface;", "corax-data-flow"})
/* loaded from: SimpleUnitPrinterKt.class */
public final class SimpleUnitPrinterKt {
    @NotNull
    public static final String getPrettyMethodName(@NotNull SootClass declaringClass, @NotNull String name) {
        Intrinsics.checkNotNullParameter(declaringClass, "declaringClass");
        Intrinsics.checkNotNullParameter(name, "name");
        if (Intrinsics.areEqual(name, "<init>")) {
            String shortName = declaringClass.getShortName();
            Intrinsics.checkNotNullExpressionValue(shortName, "getShortName(...)");
            return shortName;
        }
        if (Intrinsics.areEqual(name, "<clinit>")) {
            String shortName2 = declaringClass.getShortName();
            Intrinsics.checkNotNullExpressionValue(shortName2, "getShortName(...)");
            return shortName2;
        }
        return name;
    }

    @NotNull
    public static final String getPrettyMethodName(@NotNull SootMethodInterface m) {
        Intrinsics.checkNotNullParameter(m, "m");
        SootClass declaringClass = m.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        String name = m.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return getPrettyMethodName(declaringClass, name);
    }
}
