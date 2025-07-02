package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Unit;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\b\u0018�� \u00182\u00020\u0001:\u0001\u0018B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0019"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/MergePath;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "node", "Lsoot/Unit;", "all", "Lkotlinx/collections/immutable/PersistentSet;", "<init>", "(Lsoot/Unit;Lkotlinx/collections/immutable/PersistentSet;)V", "getNode", "()Lsoot/Unit;", "getAll", "()Lkotlinx/collections/immutable/PersistentSet;", "equivTo", "", "other", "", "hash", "", "getHash", "()Ljava/lang/Integer;", "setHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "equivHashCode", "Companion", "corax-data-flow"})
/* loaded from: MergePath.class */
public final class MergePath extends IPath {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Unit node;

    @NotNull
    private final PersistentSet<IPath> all;

    @Nullable
    private Integer hash;

    public /* synthetic */ MergePath(Unit node, PersistentSet all, DefaultConstructorMarker $constructor_marker) {
        this(node, all);
    }

    @Override // cn.sast.dataflow.interprocedural.check.IPath
    @NotNull
    public Unit getNode() {
        return this.node;
    }

    @NotNull
    public final PersistentSet<IPath> getAll() {
        return this.all;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private MergePath(Unit node, PersistentSet<? extends IPath> persistentSet) {
        super(null);
        this.node = node;
        this.all = persistentSet;
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bJ\u001e\u0010\u0004\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0010H\u0002¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/MergePath$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/check/IPath;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "a", "b", "paths", "", "Lcn/sast/dataflow/interprocedural/check/MergePath;", "node", "Lsoot/Unit;", "set", "Lkotlinx/collections/immutable/PersistentSet;", "corax-data-flow"})
    /* loaded from: MergePath$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final IPath v(@NotNull HeapValuesEnv env, @NotNull IPath a, @NotNull IPath b) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            if (a == b) {
                return a;
            }
            PersistentSet.Builder all = ExtensionsKt.persistentHashSetOf().builder();
            if (a instanceof MergePath) {
                all.addAll(((MergePath) a).getAll());
            } else {
                all.add(a);
            }
            if (b instanceof MergePath) {
                all.addAll(((MergePath) b).getAll());
            } else {
                all.add(b);
            }
            return v(env.getNode(), all.build());
        }

        @NotNull
        public final IPath v(@NotNull HeapValuesEnv env, @NotNull Set<? extends IPath> set) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(set, "paths");
            if (set.isEmpty()) {
                return UnknownPath.Companion.v(env);
            }
            if (set.size() == 1) {
                return (IPath) CollectionsKt.first(set);
            }
            PersistentSet.Builder all = ExtensionsKt.persistentHashSetOf().builder();
            for (IPath path : set) {
                if (path instanceof MergePath) {
                    all.addAll(((MergePath) path).getAll());
                } else {
                    all.add(path);
                }
            }
            return v(env.getNode(), all.build());
        }

        private final MergePath v(Unit node, PersistentSet<? extends IPath> persistentSet) {
            return (MergePath) IPath.Companion.getInterner(new MergePath(node, (PersistentSet) IPath.Companion.specialInterner(persistentSet), null));
        }
    }

    @Override // cn.sast.common.interner.InternerEquiv
    public boolean equivTo(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof MergePath) && equivHashCode() == ((MergePath) other).equivHashCode() && getNode() == ((MergePath) other).getNode() && this.all == ((MergePath) other).all;
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
            result = Integer.valueOf((31 * Integer.valueOf(System.identityHashCode(getNode())).intValue()) + System.identityHashCode(this.all));
            this.hash = result;
        }
        return result.intValue();
    }
}
