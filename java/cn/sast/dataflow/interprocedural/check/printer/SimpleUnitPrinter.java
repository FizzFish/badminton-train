package cn.sast.dataflow.interprocedural.check.printer;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.AbstractUnitPrinter;
import soot.SootClass;
import soot.SootFieldRef;
import soot.SootMethod;
import soot.SootMethodInterface;
import soot.SootMethodRef;
import soot.Type;
import soot.Unit;
import soot.UnitPrinter;
import soot.Value;
import soot.jimple.CaughtExceptionRef;
import soot.jimple.Constant;
import soot.jimple.IdentityRef;
import soot.jimple.InvokeExpr;
import soot.jimple.ParameterRef;
import soot.jimple.StringConstant;
import soot.jimple.ThisRef;

/* compiled from: SimpleUnitPrinter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018��  2\u00020\u0001:\u0001 B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0005H\u0016J\b\u0010\u001f\u001a\u00020\bH\u0014¨\u0006!"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinter;", "Lsoot/AbstractUnitPrinter;", "<init>", "()V", "getQuotedStringOf", "", "fromString", "literal", "", "s", "type", "t", "Lsoot/Type;", "constant", "c", "Lsoot/jimple/Constant;", "methodRef", "m", "Lsoot/SootMethodRef;", "fieldRef", "f", "Lsoot/SootFieldRef;", "unitRef", "u", "Lsoot/Unit;", "branchTarget", "", "identityRef", "r", "Lsoot/jimple/IdentityRef;", "toString", "handleIndent", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nSimpleUnitPrinter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpleUnitPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinter\n+ 2 SimpleUnitPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinterKt\n*L\n1#1,144:1\n17#2:145\n*S KotlinDebug\n*F\n+ 1 SimpleUnitPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinter\n*L\n57#1:145\n*E\n"})
/* loaded from: SimpleUnitPrinter.class */
public final class SimpleUnitPrinter extends AbstractUnitPrinter {

    @NotNull
    public static final Companion Companion = new Companion(null);

    private final String getQuotedStringOf(String fromString) {
        int fromStringLen = fromString.length();
        StringBuilder toStringBuffer = new StringBuilder(fromStringLen + 20);
        toStringBuffer.append("\"");
        for (int i = 0; i < fromStringLen; i++) {
            char ch = fromString.charAt(i);
            switch (ch) {
                case '\t':
                    toStringBuffer.append("\\t");
                    break;
                case '\n':
                    toStringBuffer.append("\\n");
                    break;
                case '\r':
                    toStringBuffer.append("\\r");
                    break;
                case '\"':
                    toStringBuffer.append("\\\"");
                    break;
                case '\\':
                    toStringBuffer.append("\\\\");
                    break;
                default:
                    toStringBuffer.append(ch);
                    break;
            }
        }
        toStringBuffer.append("\"");
        String string = toStringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public void literal(@NotNull String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        this.output.append(s);
    }

    public void type(@NotNull Type t) {
        Intrinsics.checkNotNullParameter(t, "t");
        this.output.append(EventPrinterKt.getPname(t));
    }

    public void constant(@Nullable Constant c) {
        if (c instanceof StringConstant) {
            handleIndent();
            StringBuffer stringBuffer = this.output;
            String str = ((StringConstant) c).value;
            Intrinsics.checkNotNullExpressionValue(str, "value");
            stringBuffer.append(getQuotedStringOf(str));
            return;
        }
        super.constant(c);
    }

    public void methodRef(@NotNull SootMethodRef m) {
        Intrinsics.checkNotNullParameter(m, "m");
        StringBuffer stringBuffer = this.output;
        SootClass declaringClass = ((SootMethodInterface) m).getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
        String name = ((SootMethodInterface) m).getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        stringBuffer.append(SimpleUnitPrinterKt.getPrettyMethodName(declaringClass, name));
    }

    public void fieldRef(@NotNull SootFieldRef f) {
        Intrinsics.checkNotNullParameter(f, "f");
        this.output.append(f.name());
    }

    public void unitRef(@Nullable Unit u, boolean branchTarget) {
    }

    public void identityRef(@NotNull IdentityRef r) {
        Intrinsics.checkNotNullParameter(r, "r");
        if (r instanceof ThisRef) {
            literal("@this: ");
            Type type = ((ThisRef) r).getType();
            Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
            type(type);
            return;
        }
        if (r instanceof ParameterRef) {
            literal("@parameter" + ((ParameterRef) r).getIndex() + ": ");
            Type type2 = ((ParameterRef) r).getType();
            Intrinsics.checkNotNullExpressionValue(type2, "getType(...)");
            type(type2);
            return;
        }
        if (r instanceof CaughtExceptionRef) {
            literal("@caughtexception");
            return;
        }
        throw new RuntimeException();
    }

    @NotNull
    public String toString() {
        String string = this.output.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    protected void handleIndent() {
        this.startOfLine = false;
    }

    /* compiled from: SimpleUnitPrinter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J.\u0010\u0004\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f¨\u0006\u0010"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinter$Companion;", "", "<init>", "()V", "getStringOf", "", "unit", "Lsoot/Unit;", "m", "Lsoot/SootMethod;", "lhs", "Lsoot/Value;", "invokeExpr", "Lsoot/jimple/InvokeExpr;", "short", "", "corax-data-flow"})
    @SourceDebugExtension({"SMAP\nSimpleUnitPrinter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimpleUnitPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinter$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 SimpleUnitPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinterKt\n*L\n1#1,144:1\n1#2:145\n17#3:146\n17#3:147\n*S KotlinDebug\n*F\n+ 1 SimpleUnitPrinter.kt\ncn/sast/dataflow/interprocedural/check/printer/SimpleUnitPrinter$Companion\n*L\n115#1:146\n137#1:147\n*E\n"})
    /* loaded from: SimpleUnitPrinter$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final String getStringOf(@NotNull Unit unit) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            UnitPrinter simpleUnitPrinter = new SimpleUnitPrinter();
            unit.toString(simpleUnitPrinter);
            return simpleUnitPrinter.toString();
        }

        public static /* synthetic */ String getStringOf$default(Companion companion, SootMethod sootMethod, Value value, InvokeExpr invokeExpr, boolean z, int i, Object obj) {
            if ((i & 8) != 0) {
                z = true;
            }
            return companion.getStringOf(sootMethod, value, invokeExpr, z);
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0077  */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String getStringOf(@org.jetbrains.annotations.Nullable soot.SootMethod r7, @org.jetbrains.annotations.Nullable soot.Value r8, @org.jetbrains.annotations.Nullable soot.jimple.InvokeExpr r9, boolean r10) {
            /*
                Method dump skipped, instructions count: 454
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.printer.SimpleUnitPrinter.Companion.getStringOf(soot.SootMethod, soot.Value, soot.jimple.InvokeExpr, boolean):java.lang.String");
        }
    }
}
