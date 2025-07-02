package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.ReferenceContext;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Unit;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\b\u0018�� \u001b2\u00020\u0001:\u0001\u001bB\u001f\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/EntryPath;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "special", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "node", "Lsoot/Unit;", "<init>", "(Lkotlinx/collections/immutable/PersistentList;Lsoot/Unit;)V", "getNode", "()Lsoot/Unit;", "getSpecial", "()Lkotlinx/collections/immutable/PersistentList;", "toString", "", "equivTo", "", "other", "", "hash", "", "getHash", "()Ljava/lang/Integer;", "setHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "equivHashCode", "Companion", "corax-data-flow"})
/* loaded from: EntryPath.class */
public final class EntryPath extends IPath {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Unit node;

    @NotNull
    private final PersistentList<ReferenceContext> special;

    @Nullable
    private Integer hash;

    public /* synthetic */ EntryPath(PersistentList special, Unit node, DefaultConstructorMarker $constructor_marker) {
        this(special, node);
    }

    @Override // cn.sast.dataflow.interprocedural.check.IPath
    @NotNull
    public Unit getNode() {
        return this.node;
    }

    private EntryPath(PersistentList<? extends ReferenceContext> persistentList, Unit node) {
        super(null);
        this.node = node;
        this.special = (PersistentList) IPath.Companion.specialInterner(persistentList);
    }

    @NotNull
    public final PersistentList<ReferenceContext> getSpecial() {
        return this.special;
    }

    @NotNull
    public String toString() {
        return CollectionsKt.joinToString$default(this.special, ":", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + " " + getNode();
    }

    @Override // cn.sast.common.interner.InternerEquiv
    public boolean equivTo(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof EntryPath) && equivHashCode() == ((EntryPath) other).equivHashCode() && getNode() == ((EntryPath) other).getNode() && this.special == ((EntryPath) other).special;
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
            result = Integer.valueOf((31 * Integer.valueOf(System.identityHashCode(getNode())).intValue()) + System.identityHashCode(this.special));
            this.hash = result;
        }
        return result.intValue();
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/EntryPath$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "special", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/ReferenceContext;", "method", "Lsoot/SootMethod;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "corax-data-flow"})
    /* loaded from: EntryPath$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final EntryPath v(@NotNull PersistentList<? extends ReferenceContext> persistentList, @NotNull SootMethod method, @NotNull HeapValuesEnv env) {
            Intrinsics.checkNotNullParameter(persistentList, "special");
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(env, "env");
            return (EntryPath) IPath.Companion.getInterner(new EntryPath(persistentList, env.getNode(), null));
        }
    }
}
