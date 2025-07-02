package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Unit;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\b\u0018�� $2\u00020\u0001:\u0001$B=\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0001\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010#\u001a\u00020\u001dH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0012R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\"\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006%"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "node", "Lsoot/Unit;", "interproceduralPathMap", "", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "path", "container", "Lsoot/SootMethod;", "callee", "<init>", "(Lsoot/Unit;Ljava/util/Map;Lcn/sast/dataflow/interprocedural/check/IPath;Lsoot/SootMethod;Lsoot/SootMethod;)V", "getNode", "()Lsoot/Unit;", "getPath", "()Lcn/sast/dataflow/interprocedural/check/IPath;", "getContainer", "()Lsoot/SootMethod;", "getCallee", "getInterproceduralPathMap", "()Ljava/util/Map;", "toString", "", "equivTo", "", "other", "", "hash", "", "getHash", "()Ljava/lang/Integer;", "setHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "equivHashCode", "Companion", "corax-data-flow"})
/* loaded from: InvokeEdgePath.class */
public final class InvokeEdgePath extends IPath {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Unit node;

    @NotNull
    private final IPath path;

    @NotNull
    private final SootMethod container;

    @NotNull
    private final SootMethod callee;

    @NotNull
    private final Map<IPath, EntryPath> interproceduralPathMap;

    @Nullable
    private Integer hash;

    public /* synthetic */ InvokeEdgePath(Unit node, Map interproceduralPathMap, IPath path, SootMethod container, SootMethod callee, DefaultConstructorMarker $constructor_marker) {
        this(node, interproceduralPathMap, path, container, callee);
    }

    @Override // cn.sast.dataflow.interprocedural.check.IPath
    @NotNull
    public Unit getNode() {
        return this.node;
    }

    @NotNull
    public final IPath getPath() {
        return this.path;
    }

    @NotNull
    public final SootMethod getContainer() {
        return this.container;
    }

    @NotNull
    public final SootMethod getCallee() {
        return this.callee;
    }

    private InvokeEdgePath(Unit node, Map<IPath, EntryPath> map, IPath path, SootMethod container, SootMethod callee) {
        super(null);
        this.node = node;
        this.path = path;
        this.container = container;
        this.callee = callee;
        this.interproceduralPathMap = PathCompanionKt.getShort(map);
    }

    @NotNull
    public final Map<IPath, EntryPath> getInterproceduralPathMap() {
        return this.interproceduralPathMap;
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e¨\u0006\u0010"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "interproceduralPathMap", "", "Lcn/sast/dataflow/interprocedural/check/IPath;", "Lcn/sast/dataflow/interprocedural/check/EntryPath;", "path", "container", "Lsoot/SootMethod;", "callee", "corax-data-flow"})
    /* loaded from: InvokeEdgePath$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final InvokeEdgePath v(@NotNull HeapValuesEnv env, @NotNull Map<IPath, EntryPath> map, @NotNull IPath path, @NotNull SootMethod container, @NotNull SootMethod callee) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(map, "interproceduralPathMap");
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(container, "container");
            Intrinsics.checkNotNullParameter(callee, "callee");
            return (InvokeEdgePath) IPath.Companion.getInterner(new InvokeEdgePath(env.getNode(), map, path, container, callee, null));
        }
    }

    @NotNull
    public String toString() {
        return this.container + " " + getNode();
    }

    @Override // cn.sast.common.interner.InternerEquiv
    public boolean equivTo(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof InvokeEdgePath) && equivHashCode() == ((InvokeEdgePath) other).equivHashCode() && getNode() == ((InvokeEdgePath) other).getNode() && this.path == ((InvokeEdgePath) other).path && Intrinsics.areEqual(this.container, ((InvokeEdgePath) other).container) && Intrinsics.areEqual(this.callee, ((InvokeEdgePath) other).callee);
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
            result = Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf(System.identityHashCode(getNode())).intValue()) + this.path.hashCode()).intValue()) + this.container.hashCode()).intValue()) + this.callee.hashCode());
            this.hash = result;
        }
        return result.intValue();
    }
}
