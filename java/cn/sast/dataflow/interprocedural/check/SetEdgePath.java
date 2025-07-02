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
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018�� (2\u00020\u0001:\u0001(BY\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0001\u0012\n\u0010\b\u001a\u00060\tj\u0002`\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\u0010\f\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\r\u001a\u00020\u0001\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010'\u001a\u00020!H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\b\u001a\u00060\tj\u0002`\n¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u0018R\u0015\u0010\f\u001a\u00060\u0005j\u0002`\u0006¢\u0006\b\n��\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u0016R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\t¢\u0006\b\n��\u001a\u0004\b\u001c\u0010\u0018R\u001e\u0010 \u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006)"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/SetEdgePath;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "node", "Lsoot/Unit;", "heapObject", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "heapObjectPath", "mt", "", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "key", "value", "valuePath", "info", "<init>", "(Lsoot/Unit;Lcn/sast/dataflow/interprocedural/analysis/IValue;Lcn/sast/dataflow/interprocedural/check/IPath;Ljava/lang/Object;Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/IValue;Lcn/sast/dataflow/interprocedural/check/IPath;Ljava/lang/Object;)V", "getNode", "()Lsoot/Unit;", "getHeapObject", "()Lcn/sast/dataflow/interprocedural/analysis/IValue;", "getHeapObjectPath", "()Lcn/sast/dataflow/interprocedural/check/IPath;", "getMt", "()Ljava/lang/Object;", "getKey", "getValue", "getValuePath", "getInfo", "equivTo", "", "other", "hash", "", "getHash", "()Ljava/lang/Integer;", "setHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "equivHashCode", "Companion", "corax-data-flow"})
/* loaded from: SetEdgePath.class */
public final class SetEdgePath extends IPath {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Unit node;

    @NotNull
    private final IValue heapObject;

    @NotNull
    private final IPath heapObjectPath;

    @NotNull
    private final Object mt;

    @Nullable
    private final Object key;

    @NotNull
    private final IValue value;

    @NotNull
    private final IPath valuePath;

    @Nullable
    private final Object info;

    @Nullable
    private Integer hash;

    public /* synthetic */ SetEdgePath(Unit node, IValue heapObject, IPath heapObjectPath, Object mt, Object key, IValue value, IPath valuePath, Object info, DefaultConstructorMarker $constructor_marker) {
        this(node, heapObject, heapObjectPath, mt, key, value, valuePath, info);
    }

    @Override // cn.sast.dataflow.interprocedural.check.IPath
    @NotNull
    public Unit getNode() {
        return this.node;
    }

    @NotNull
    public final IValue getHeapObject() {
        return this.heapObject;
    }

    @NotNull
    public final IPath getHeapObjectPath() {
        return this.heapObjectPath;
    }

    @NotNull
    public final Object getMt() {
        return this.mt;
    }

    @Nullable
    public final Object getKey() {
        return this.key;
    }

    @NotNull
    public final IValue getValue() {
        return this.value;
    }

    @NotNull
    public final IPath getValuePath() {
        return this.valuePath;
    }

    @Nullable
    public final Object getInfo() {
        return this.info;
    }

    private SetEdgePath(Unit node, IValue heapObject, IPath heapObjectPath, Object mt, Object key, IValue value, IPath valuePath, Object info) {
        super(null);
        this.node = node;
        this.heapObject = heapObject;
        this.heapObjectPath = heapObjectPath;
        this.mt = mt;
        this.key = key;
        this.value = value;
        this.valuePath = valuePath;
        this.info = info;
    }

    /* compiled from: PathCompanion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JX\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u00060\tj\u0002`\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u0001j\u0002`\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00012\n\u0010\u0004\u001a\u00060\tj\u0002`\n2\u0006\u0010\u0010\u001a\u00020\f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0001¨\u0006\u0012"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/SetEdgePath$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/check/SetEdgePath;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "heapObject", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "heapObjectPath", "Lcn/sast/dataflow/interprocedural/check/IPath;", "mt", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "key", "value", "info", "corax-data-flow"})
    /* loaded from: SetEdgePath$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ SetEdgePath v$default(Companion companion, HeapValuesEnv heapValuesEnv, IValue iValue, IPath iPath, Object obj, Object obj2, IValue iValue2, IPath iPath2, Object obj3, int i, Object obj4) {
            if ((i & 128) != 0) {
                obj3 = null;
            }
            return companion.v(heapValuesEnv, iValue, iPath, obj, obj2, iValue2, iPath2, obj3);
        }

        @NotNull
        public final SetEdgePath v(@NotNull HeapValuesEnv env, @NotNull IValue heapObject, @NotNull IPath heapObjectPath, @NotNull Object mt, @Nullable Object key, @NotNull IValue v, @NotNull IPath value, @Nullable Object info) {
            Intrinsics.checkNotNullParameter(env, "env");
            Intrinsics.checkNotNullParameter(heapObject, "heapObject");
            Intrinsics.checkNotNullParameter(heapObjectPath, "heapObjectPath");
            Intrinsics.checkNotNullParameter(mt, "mt");
            Intrinsics.checkNotNullParameter(v, "v");
            Intrinsics.checkNotNullParameter(value, "value");
            return (SetEdgePath) IPath.Companion.getInterner(new SetEdgePath(env.getNode(), heapObject, heapObjectPath, mt, key, v, value, info, null));
        }
    }

    @Override // cn.sast.common.interner.InternerEquiv
    public boolean equivTo(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof SetEdgePath) && equivHashCode() == ((SetEdgePath) other).equivHashCode() && getNode() == ((SetEdgePath) other).getNode() && Intrinsics.areEqual(this.heapObject, ((SetEdgePath) other).heapObject) && this.heapObjectPath == ((SetEdgePath) other).heapObjectPath && Intrinsics.areEqual(this.mt, ((SetEdgePath) other).mt) && Intrinsics.areEqual(this.key, ((SetEdgePath) other).key) && Intrinsics.areEqual(this.value, ((SetEdgePath) other).value) && this.valuePath == ((SetEdgePath) other).valuePath && Intrinsics.areEqual(this.info, ((SetEdgePath) other).info);
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
            int iIntValue = 31 * Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf(System.identityHashCode(getNode())).intValue()) + this.heapObject.hashCode()).intValue()) + this.heapObjectPath.hashCode()).intValue()) + this.mt.hashCode()).intValue();
            Object obj = this.key;
            int iIntValue2 = 31 * Integer.valueOf((31 * Integer.valueOf((31 * Integer.valueOf(iIntValue + (obj != null ? obj.hashCode() : 0)).intValue()) + this.value.hashCode()).intValue()) + this.valuePath.hashCode()).intValue();
            Object obj2 = this.info;
            result = Integer.valueOf(iIntValue2 + (obj2 != null ? obj2.hashCode() : 0));
            this.hash = result;
        }
        return result.intValue();
    }
}
