package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IIexConst;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Unit;
import soot.jimple.Constant;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b��\u0018�� \u00172\u00020\u0001:\u0001\u0017B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0015\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u0018"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/LiteralPath;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "node", "Lsoot/Unit;", "const", "", "<init>", "(Lsoot/Unit;Ljava/lang/Object;)V", "getNode", "()Lsoot/Unit;", "getConst", "()Ljava/lang/Object;", "equivTo", "", "other", "hash", "", "getHash", "()Ljava/lang/Integer;", "setHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "equivHashCode", "Companion", "corax-data-flow"})
/* loaded from: LiteralPath.class */
public final class LiteralPath extends IPath {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Unit node;

    /* renamed from: const, reason: not valid java name */
    @NotNull
    private final Object f1const;

    @Nullable
    private Integer hash;

    public /* synthetic */ LiteralPath(Unit node, Object obj, DefaultConstructorMarker $constructor_marker) {
        this(node, obj);
    }

    @Override // cn.sast.dataflow.interprocedural.check.IPath
    @NotNull
    public Unit getNode() {
        return this.node;
    }

    @NotNull
    public final Object getConst() {
        return this.f1const;
    }

    private LiteralPath(Unit node, Object obj) {
        super(null);
        this.node = node;
        this.f1const = obj;
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/LiteralPath$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/check/LiteralPath;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "const", "Lsoot/jimple/Constant;", "info", "constIex", "Lcom/feysh/corax/config/api/IIexConst;", "corax-data-flow"})
    /* loaded from: LiteralPath$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final LiteralPath v(@NotNull HeapValuesEnv env, @NotNull Constant constant, @Nullable Object info) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(constant, "const");
            return (LiteralPath) IPath.Companion.getInterner(new LiteralPath(env.getNode(), constant, null));
        }

        @NotNull
        public final LiteralPath v(@NotNull HeapValuesEnv env, @NotNull IIexConst constIex) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(constIex, "constIex");
            return (LiteralPath) IPath.Companion.getInterner(new LiteralPath(env.getNode(), constIex, null));
        }
    }

    @Override // cn.sast.common.interner.InternerEquiv
    public boolean equivTo(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LiteralPath) && equivHashCode() == ((LiteralPath) other).equivHashCode() && getNode() == ((LiteralPath) other).getNode() && Intrinsics.areEqual(this.f1const, ((LiteralPath) other).f1const);
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
            result = Integer.valueOf((31 * Integer.valueOf(System.identityHashCode(getNode())).intValue()) + this.f1const.hashCode());
            this.hash = result;
        }
        return result.intValue();
    }
}
