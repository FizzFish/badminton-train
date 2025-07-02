package cn.sast.api.report;

import cn.sast.api.util.ComparatorUtilsKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0003\b\u0086\b\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u0011\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020��H\u0096\u0002J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\nHÆ\u0003J=\u0010\u001d\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0017\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u0007HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcn/sast/api/report/MacroExpansion;", "", "message", "", "classname", "Lcn/sast/api/report/IBugResInfo;", "line", "", "column", "range", "Lcn/sast/api/report/Range;", "<init>", "(Ljava/lang/String;Lcn/sast/api/report/IBugResInfo;IILcn/sast/api/report/Range;)V", "getMessage", "()Ljava/lang/String;", "getClassname", "()Lcn/sast/api/report/IBugResInfo;", "getLine", "()I", "getColumn", "getRange", "()Lcn/sast/api/report/Range;", "compareTo", "other", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "", "hashCode", "toString", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/MacroExpansion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1#2:452\n*E\n"})
/* loaded from: MacroExpansion.class */
public final class MacroExpansion implements Comparable<MacroExpansion> {

    @NotNull
    private final String message;

    @NotNull
    private final IBugResInfo classname;
    private final int line;
    private final int column;

    @Nullable
    private final Range range;

    @NotNull
    public final String component1() {
        return this.message;
    }

    @NotNull
    public final IBugResInfo component2() {
        return this.classname;
    }

    public final int component3() {
        return this.line;
    }

    public final int component4() {
        return this.column;
    }

    @Nullable
    public final Range component5() {
        return this.range;
    }

    @NotNull
    public final MacroExpansion copy(@NotNull String message, @NotNull IBugResInfo classname, int line, int column, @Nullable Range range) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(classname, "classname");
        return new MacroExpansion(message, classname, line, column, range);
    }

    public static /* synthetic */ MacroExpansion copy$default(MacroExpansion macroExpansion, String str, IBugResInfo iBugResInfo, int i, int i2, Range range, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = macroExpansion.message;
        }
        if ((i3 & 2) != 0) {
            iBugResInfo = macroExpansion.classname;
        }
        if ((i3 & 4) != 0) {
            i = macroExpansion.line;
        }
        if ((i3 & 8) != 0) {
            i2 = macroExpansion.column;
        }
        if ((i3 & 16) != 0) {
            range = macroExpansion.range;
        }
        return macroExpansion.copy(str, iBugResInfo, i, i2, range);
    }

    @NotNull
    public String toString() {
        return "MacroExpansion(message=" + this.message + ", classname=" + this.classname + ", line=" + this.line + ", column=" + this.column + ", range=" + this.range + ")";
    }

    public int hashCode() {
        int result = this.message.hashCode();
        return (((((((result * 31) + this.classname.hashCode()) * 31) + Integer.hashCode(this.line)) * 31) + Integer.hashCode(this.column)) * 31) + (this.range == null ? 0 : this.range.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MacroExpansion)) {
            return false;
        }
        MacroExpansion macroExpansion = (MacroExpansion) other;
        return Intrinsics.areEqual(this.message, macroExpansion.message) && Intrinsics.areEqual(this.classname, macroExpansion.classname) && this.line == macroExpansion.line && this.column == macroExpansion.column && Intrinsics.areEqual(this.range, macroExpansion.range);
    }

    public MacroExpansion(@NotNull String message, @NotNull IBugResInfo classname, int line, int column, @Nullable Range range) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(classname, "classname");
        this.message = message;
        this.classname = classname;
        this.line = line;
        this.column = column;
        this.range = range;
    }

    public /* synthetic */ MacroExpansion(String str, IBugResInfo iBugResInfo, int i, int i2, Range range, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, iBugResInfo, i, i2, (i3 & 16) != 0 ? null : range);
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    public final IBugResInfo getClassname() {
        return this.classname;
    }

    public final int getLine() {
        return this.line;
    }

    public final int getColumn() {
        return this.column;
    }

    @Nullable
    public final Range getRange() {
        return this.range;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull MacroExpansion other) {
        Intrinsics.checkNotNullParameter(other, "other");
        Integer numValueOf = Integer.valueOf(this.message.compareTo(other.message));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num != null) {
            int it2 = num.intValue();
            return it2;
        }
        Integer numValueOf2 = Integer.valueOf(this.classname.compareTo(other.classname));
        int it3 = numValueOf2.intValue();
        Integer num2 = it3 != 0 ? numValueOf2 : null;
        if (num2 != null) {
            int it4 = num2.intValue();
            return it4;
        }
        Integer numValueOf3 = Integer.valueOf(Intrinsics.compare(this.line, other.line));
        int it5 = numValueOf3.intValue();
        Integer num3 = it5 != 0 ? numValueOf3 : null;
        if (num3 != null) {
            int it6 = num3.intValue();
            return it6;
        }
        Integer numValueOf4 = Integer.valueOf(Intrinsics.compare(this.column, other.column));
        int it7 = numValueOf4.intValue();
        Integer num4 = it7 != 0 ? numValueOf4 : null;
        if (num4 != null) {
            int it8 = num4.intValue();
            return it8;
        }
        Integer numValueOf5 = Integer.valueOf(ComparatorUtilsKt.compareToNullable(this.range, other.range));
        int it9 = numValueOf5.intValue();
        Integer num5 = it9 != 0 ? numValueOf5 : null;
        if (num5 == null) {
            return 0;
        }
        int it10 = num5.intValue();
        return it10;
    }
}
