package cn.sast.dataflow.analysis.constant;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CPValue.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018�� \u000e2\u00020\u0001:\u0001\u000eB\t\b\u0012¢\u0006\u0004\b\u0002\u0010\u0003B\u0011\b\u0012\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\u0005J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000b\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/analysis/constant/CPValue;", "", "<init>", "()V", "val", "", "(I)V", "value", "equals", "", "other", "hashCode", "toString", "", "Companion", "corax-data-flow"})
/* loaded from: CPValue.class */
public class CPValue {
    private int value;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final CPValue nac = new CPValue() { // from class: cn.sast.dataflow.analysis.constant.CPValue$Companion$nac$1
        @Override // cn.sast.dataflow.analysis.constant.CPValue
        public int hashCode() {
            return Integer.MIN_VALUE;
        }
    };

    @NotNull
    private static final CPValue undef = new CPValue() { // from class: cn.sast.dataflow.analysis.constant.CPValue$Companion$undef$1
        @Override // cn.sast.dataflow.analysis.constant.CPValue
        public int hashCode() {
            return Integer.MAX_VALUE;
        }
    };

    public /* synthetic */ CPValue(int val, DefaultConstructorMarker $constructor_marker) {
        this(val);
    }

    public /* synthetic */ CPValue(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    private CPValue() {
    }

    private CPValue(int val) {
        this.value = val;
    }

    public final int value() {
        return this.value;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !Intrinsics.areEqual(getClass(), other.getClass())) {
            return false;
        }
        if (this == undef && other == nac) {
            return false;
        }
        if (this == nac && other == undef) {
            return false;
        }
        CPValue cpValue = (CPValue) other;
        return this.value == cpValue.value;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.value));
    }

    @NotNull
    public String toString() {
        if (this == nac) {
            return "NAC";
        }
        if (this == undef) {
            return "UNDEF";
        }
        return String.valueOf(this.value);
    }

    /* compiled from: CPValue.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n��\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\t\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcn/sast/dataflow/analysis/constant/CPValue$Companion;", "", "<init>", "()V", "nac", "Lcn/sast/dataflow/analysis/constant/CPValue;", "getNac", "()Lcn/sast/dataflow/analysis/constant/CPValue;", "undef", "getUndef", "makeConstant", "value", "", "val", "", "meetValue", "value1", "value2", "corax-data-flow"})
    /* loaded from: CPValue$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final CPValue getNac() {
            return CPValue.nac;
        }

        @NotNull
        public final CPValue getUndef() {
            return CPValue.undef;
        }

        @JvmStatic
        @NotNull
        public final CPValue makeConstant(int value) {
            return new CPValue(value, null);
        }

        @NotNull
        public final CPValue makeConstant(boolean val) {
            return val ? makeConstant(1) : makeConstant(0);
        }

        @NotNull
        public final CPValue meetValue(@NotNull CPValue value1, @NotNull CPValue value2) {
            Intrinsics.checkNotNullParameter(value1, "value1");
            Intrinsics.checkNotNullParameter(value2, "value2");
            if (value1 == getUndef()) {
                return value2;
            }
            if (value2 == getUndef()) {
                return value1;
            }
            if (value1 == getNac() || value2 == getNac()) {
                return getNac();
            }
            if (value1.value() == value2.value()) {
                return makeConstant(value1.value());
            }
            return getNac();
        }
    }

    @JvmStatic
    @NotNull
    public static final CPValue makeConstant(int value) {
        return Companion.makeConstant(value);
    }
}
