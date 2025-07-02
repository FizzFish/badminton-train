package cn.sast.dataflow.interprocedural.check.printer;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.SootClass;
import soot.SootMethodInterface;
import soot.UnitPrinter;
import soot.Value;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.InvokeExpr;
import soot.jimple.StaticInvokeExpr;

/* compiled from: GrimpInline.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0012\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0012\u0010��\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"invokeToString", "", "Lsoot/jimple/InvokeExpr;", "up", "Lsoot/UnitPrinter;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nGrimpInline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GrimpInline.kt\ncn/sast/dataflow/interprocedural/check/printer/GrimpInlineKt\n+ 2 SimpleUnitPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinterKt\n*L\n1#1,312:1\n17#2:313\n*S KotlinDebug\n*F\n+ 1 GrimpInline.kt\ncn/sast/dataflow/interprocedural/check/printer/GrimpInlineKt\n*L\n20#1:313\n*E\n"})
/* loaded from: GrimpInlineKt.class */
public final class GrimpInlineKt {
    public static final void invokeToString(@NotNull InvokeExpr $this$invokeToString, @NotNull UnitPrinter up) {
        Intrinsics.checkNotNullParameter($this$invokeToString, "<this>");
        Intrinsics.checkNotNullParameter(up, "up");
        if ($this$invokeToString instanceof InstanceInvokeExpr) {
            ((InstanceInvokeExpr) $this$invokeToString).getBaseBox().toString(up);
            up.literal(".");
        }
        if ($this$invokeToString instanceof StaticInvokeExpr) {
            up.literal(((StaticInvokeExpr) $this$invokeToString).getMethodRef().getDeclaringClass().getShortName());
            up.literal(".");
        }
        SootMethodInterface methodRef = $this$invokeToString.getMethodRef();
        Intrinsics.checkNotNullExpressionValue(methodRef, "getMethodRef(...)");
        SootMethodInterface m$iv = methodRef;
        SootClass declaringClass = m$iv.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        String name = m$iv.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        up.literal(SimpleUnitPrinterKt.getPrettyMethodName(declaringClass, name));
        up.literal("(");
        List args = $this$invokeToString.getArgs();
        int e = args.size();
        for (int i = 0; i < e; i++) {
            if (i != 0) {
                up.literal(", ");
            }
            ((Value) args.get(i)).toString(up);
        }
        up.literal(")");
    }
}
