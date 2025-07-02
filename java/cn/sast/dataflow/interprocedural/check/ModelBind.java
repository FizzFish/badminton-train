package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.StmtModelingEnv;
import cn.sast.dataflow.interprocedural.check.IPath;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Unit;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018�� &2\u00020\u0001:\u0001&BC\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\n\u0010\u0007\u001a\u00060\bj\u0002`\t\u0012\u0006\u0010\n\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010%\u001a\u00020\u001fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0007\u001a\u00060\bj\u0002`\t¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006'"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/ModelBind;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "node", "Lsoot/Unit;", "obj", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "mt", "", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "data", "info", "Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;", "prev", "<init>", "(Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IValue;Ljava/lang/Object;Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;Lcn/sast/dataflow/interprocedural/check/IPath;)V", "getNode", "()Lsoot/Unit;", "getObj", "()Lcn/sast/dataflow/interprocedural/analysis/IValue;", "getMt", "()Ljava/lang/Object;", "getData", "getInfo", "()Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;", "getPrev", "()Lcn/sast/dataflow/interprocedural/check/IPath;", "equivTo", "", "other", "hash", "", "getHash", "()Ljava/lang/Integer;", "setHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "equivHashCode", "Companion", "corax-data-flow"})
/* loaded from: ModelBind.class */
public final class ModelBind extends IPath {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Unit node;

    @NotNull
    private final IValue obj;

    @NotNull
    private final Object mt;

    @NotNull
    private final Object data;

    @Nullable
    private final ModelingStmtInfo info;

    @NotNull
    private final IPath prev;

    @Nullable
    private Integer hash;

    public /* synthetic */ ModelBind(Unit node, IValue obj, Object mt, Object data, ModelingStmtInfo info, IPath prev, DefaultConstructorMarker $constructor_marker) {
        this(node, obj, mt, data, info, prev);
    }

    @Override // cn.sast.dataflow.interprocedural.check.IPath
    @NotNull
    public Unit getNode() {
        return this.node;
    }

    @NotNull
    public final IValue getObj() {
        return this.obj;
    }

    @NotNull
    public final Object getMt() {
        return this.mt;
    }

    @NotNull
    public final Object getData() {
        return this.data;
    }

    @Nullable
    public final ModelingStmtInfo getInfo() {
        return this.info;
    }

    @NotNull
    public final IPath getPrev() {
        return this.prev;
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\n2\n\u0010\u000b\u001a\u00060\u0001j\u0002`\f2\u0010\u0010\r\u001a\f\u0012\b\u0012\u00060\tj\u0002`\n0\u000e2\u0006\u0010\u000f\u001a\u00020\u0010¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/ModelBind$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/check/ModelBind;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "obj", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "mt", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "data", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "prev", "Lcn/sast/dataflow/interprocedural/check/IPath;", "corax-data-flow"})
    /* loaded from: ModelBind$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final ModelBind v(@NotNull HeapValuesEnv env, @NotNull IValue obj, @NotNull Object mt, @NotNull IData<IValue> iData, @NotNull IPath prev) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(obj, "obj");
            Intrinsics.checkNotNullParameter(mt, "mt");
            Intrinsics.checkNotNullParameter(iData, "data");
            Intrinsics.checkNotNullParameter(prev, "prev");
            IPath.Companion companion = IPath.Companion;
            Unit node = env.getNode();
            StmtModelingEnv stmtModelingEnv = env instanceof StmtModelingEnv ? (StmtModelingEnv) env : null;
            return (ModelBind) companion.getInterner(new ModelBind(node, obj, mt, mt, stmtModelingEnv != null ? stmtModelingEnv.getInfo() : null, prev, null));
        }
    }

    private ModelBind(Unit node, IValue obj, Object mt, Object data, ModelingStmtInfo info, IPath prev) {
        super(null);
        this.node = node;
        this.obj = obj;
        this.mt = mt;
        this.data = data;
        this.info = info;
        this.prev = prev;
    }

    @Override // cn.sast.common.interner.InternerEquiv
    public boolean equivTo(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ModelBind) && equivHashCode() == ((ModelBind) other).equivHashCode() && getNode() == ((ModelBind) other).getNode() && Intrinsics.areEqual(this.obj, ((ModelBind) other).obj) && Intrinsics.areEqual(this.mt, ((ModelBind) other).mt) && Intrinsics.areEqual(this.data, ((ModelBind) other).data) && Intrinsics.areEqual(this.info, ((ModelBind) other).info) && this.prev == ((ModelBind) other).prev;
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
            int iIntValue = 31 * Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf(System.identityHashCode(getNode())).intValue()) + this.obj.hashCode()).intValue()) + this.mt.hashCode()).intValue()) + this.data.hashCode()).intValue();
            ModelingStmtInfo modelingStmtInfo = this.info;
            result = Integer.valueOf((31 * Integer.valueOf(iIntValue + (modelingStmtInfo != null ? modelingStmtInfo.hashCode() : 0)).intValue()) + this.prev.hashCode());
            this.hash = result;
        }
        return result.intValue();
    }
}
