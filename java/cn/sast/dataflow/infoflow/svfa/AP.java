package cn.sast.dataflow.infoflow.svfa;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Local;
import soot.NullType;
import soot.SootField;
import soot.Value;
import soot.jimple.ArrayRef;
import soot.jimple.FieldRef;
import soot.jimple.InstanceFieldRef;
import soot.jimple.LengthExpr;
import soot.jimple.NewArrayExpr;
import soot.jimple.StaticFieldRef;
import soot.jimple.infoflow.data.Abstraction;
import soot.jimple.infoflow.data.AccessPathFragment;
import soot.jimple.internal.JimpleLocal;

/* compiled from: SparsePropgrateAnalyze.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018�� \u00172\u00020\u0001:\u0001\u0017B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020��J\b\u0010\r\u001a\u00020\u000eH\u0016J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/AP;", "", "value", "Lsoot/Value;", "field", "Lsoot/SootField;", "<init>", "(Lsoot/Value;Lsoot/SootField;)V", "getValue", "()Lsoot/Value;", "getField", "()Lsoot/SootField;", "base", "toString", "", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "Companion", "corax-data-flow"})
/* loaded from: AP.class */
public final class AP {

    @NotNull
    private final Value value;

    @Nullable
    private final SootField field;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Value staticValue = new JimpleLocal("staticValueFake", NullType.v());

    @NotNull
    public final Value component1() {
        return this.value;
    }

    @Nullable
    public final SootField component2() {
        return this.field;
    }

    @NotNull
    public final AP copy(@NotNull Value value, @Nullable SootField field) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new AP(value, field);
    }

    public static /* synthetic */ AP copy$default(AP ap, Value value, SootField sootField, int i, Object obj) {
        if ((i & 1) != 0) {
            value = ap.value;
        }
        if ((i & 2) != 0) {
            sootField = ap.field;
        }
        return ap.copy(value, sootField);
    }

    public int hashCode() {
        int result = this.value.hashCode();
        return (result * 31) + (this.field == null ? 0 : this.field.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AP)) {
            return false;
        }
        AP ap = (AP) other;
        return Intrinsics.areEqual(this.value, ap.value) && Intrinsics.areEqual(this.field, ap.field);
    }

    /* compiled from: SparsePropgrateAnalyze.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0086\u0002J\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/infoflow/svfa/AP$Companion;", "", "<init>", "()V", "staticValue", "Lsoot/Value;", "get", "Lcn/sast/dataflow/infoflow/svfa/AP;", "value", "Lsoot/jimple/infoflow/data/Abstraction;", "corax-data-flow"})
    /* loaded from: AP$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @Nullable
        public final AP get(@NotNull Value value) {
            Intrinsics.checkNotNullParameter(value, "value");
            Value base = null;
            SootField field = null;
            if (value instanceof StaticFieldRef) {
                base = AP.staticValue;
                field = ((StaticFieldRef) value).getField();
            } else if (value instanceof Local) {
                base = value;
            } else if (value instanceof FieldRef) {
                if (value instanceof InstanceFieldRef) {
                    base = ((InstanceFieldRef) value).getBase();
                    field = ((InstanceFieldRef) value).getField();
                }
            } else if (value instanceof ArrayRef) {
                Local base2 = ((ArrayRef) value).getBase();
                Intrinsics.checkNotNull(base2, "null cannot be cast to non-null type soot.Local");
                base = (Value) base2;
            } else if (value instanceof LengthExpr) {
                base = ((LengthExpr) value).getOp();
            } else if (value instanceof NewArrayExpr) {
                base = ((NewArrayExpr) value).getSize();
            }
            if (base != null) {
                return new AP(base, field);
            }
            return null;
        }

        @NotNull
        public final AP get(@NotNull Abstraction value) {
            Intrinsics.checkNotNullParameter(value, "value");
            Value plainValue = value.getAccessPath().getPlainValue();
            Intrinsics.checkNotNullExpressionValue(plainValue, "getPlainValue(...)");
            Value value2 = plainValue;
            AccessPathFragment firstFragment = value.getAccessPath().getFirstFragment();
            return new AP(value2, firstFragment != null ? firstFragment.getField() : null);
        }
    }

    public AP(@NotNull Value value, @Nullable SootField field) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        this.field = field;
    }

    public /* synthetic */ AP(Value value, SootField sootField, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(value, (i & 2) != 0 ? null : sootField);
    }

    @NotNull
    public final Value getValue() {
        return this.value;
    }

    @Nullable
    public final SootField getField() {
        return this.field;
    }

    @NotNull
    public final AP base() {
        return this.field == null ? this : new AP(this.value, null, 2, null);
    }

    @NotNull
    public String toString() {
        return this.field != null ? this.value + "." + this.field.getName() : String.valueOf(this.value);
    }
}
