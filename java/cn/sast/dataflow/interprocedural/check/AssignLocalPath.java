package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Unit;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018�� \u001e2\u00020\u0001:\u0001\u001eB)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0001¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006\u001f"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/AssignLocalPath;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "node", "Lsoot/Unit;", "lhs", "", "rhsValue", "", "rhsValePath", "<init>", "(Lsoot/Unit;Ljava/lang/Object;Ljava/lang/String;Lcn/sast/dataflow/interprocedural/check/IPath;)V", "getNode", "()Lsoot/Unit;", "getLhs", "()Ljava/lang/Object;", "getRhsValue", "()Ljava/lang/String;", "getRhsValePath", "()Lcn/sast/dataflow/interprocedural/check/IPath;", "equivTo", "", "other", "hash", "", "getHash", "()Ljava/lang/Integer;", "setHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "equivHashCode", "Companion", "corax-data-flow"})
/* loaded from: AssignLocalPath.class */
public final class AssignLocalPath extends IPath {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Unit node;

    @NotNull
    private final Object lhs;

    @NotNull
    private final String rhsValue;

    @NotNull
    private final IPath rhsValePath;

    @Nullable
    private Integer hash;

    public /* synthetic */ AssignLocalPath(Unit node, Object lhs, String rhsValue, IPath rhsValePath, DefaultConstructorMarker $constructor_marker) {
        this(node, lhs, rhsValue, rhsValePath);
    }

    @Override // cn.sast.dataflow.interprocedural.check.IPath
    @NotNull
    public Unit getNode() {
        return this.node;
    }

    @NotNull
    public final Object getLhs() {
        return this.lhs;
    }

    @NotNull
    public final String getRhsValue() {
        return this.rhsValue;
    }

    @NotNull
    public final IPath getRhsValePath() {
        return this.rhsValePath;
    }

    private AssignLocalPath(Unit node, Object lhs, String rhsValue, IPath rhsValePath) {
        super(null);
        this.node = node;
        this.lhs = lhs;
        this.rhsValue = rhsValue;
        this.rhsValePath = rhsValePath;
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00012\n\u0010\t\u001a\u00060\nj\u0002`\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u000e"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/AssignLocalPath$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/check/AssignLocalPath;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "lhs", "rhsValue", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "rhsValePath", "Lcn/sast/dataflow/interprocedural/check/IPath;", "corax-data-flow"})
    /* loaded from: AssignLocalPath$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final AssignLocalPath v(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull IValue rhsValue, @NotNull IPath rhsValePath) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(lhs, "lhs");
            Intrinsics.checkNotNullParameter(rhsValue, "rhsValue");
            Intrinsics.checkNotNullParameter(rhsValePath, "rhsValePath");
            return (AssignLocalPath) IPath.Companion.getInterner(new AssignLocalPath(env.getNode(), lhs, rhsValue.toString(), rhsValePath, null));
        }
    }

    @Override // cn.sast.common.interner.InternerEquiv
    public boolean equivTo(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AssignLocalPath) && equivHashCode() == ((AssignLocalPath) other).equivHashCode() && getNode() == ((AssignLocalPath) other).getNode() && Intrinsics.areEqual(this.lhs, ((AssignLocalPath) other).lhs) && Intrinsics.areEqual(this.rhsValue, ((AssignLocalPath) other).rhsValue) && this.rhsValePath == ((AssignLocalPath) other).rhsValePath;
    }

    @Nullable
    public final Integer getHash() {
        return this.hash;
    }

    public final void setHash(@Nullable Integer num) {
        this.hash = num;
    }

    @Override // cn.sast.common.interner.InternerEquiv
    public int equivHashCode() {
        Integer result = this.hash;
        if (result == null) {
            result = Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf(System.identityHashCode(getNode())).intValue()) + this.lhs.hashCode()).intValue()) + this.rhsValue.hashCode()).intValue()) + this.rhsValePath.hashCode());
        }
        return result.intValue();
    }
}
