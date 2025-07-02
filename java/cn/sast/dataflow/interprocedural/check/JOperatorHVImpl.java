package cn.sast.dataflow.interprocedural.check;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.JOperatorHV;
import cn.sast.dataflow.interprocedural.check.GetEdgePath;
import cn.sast.dataflow.interprocedural.check.SetEdgePath;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: OperatorPathFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0080\b\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B3\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\u0010\b\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\t¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\tH\u0016JE\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001\"\u0004\b��\u0010\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0010\u0010\u001a\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001b2\b\u0010\u001c\u001a\u0004\u0018\u0001H\u0016H\u0016¢\u0006\u0002\u0010\u001dJE\u0010\u001e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001\"\u0004\b��\u0010\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u00192\u0010\u0010\u001f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001b2\b\u0010\u001c\u001a\u0004\u0018\u0001H\u0016H\u0016¢\u0006\u0002\u0010\u001dJ,\u0010 \u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0006\u0010\u001a\u001a\u00020\u00182\u0010\u0010!\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\tH\u0016J$\u0010\"\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010\u001f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001bH\u0016J \u0010#\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00020\tH\u0016J\u0013\u0010%\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\u0013\u0010'\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\tHÂ\u0003J;\u0010(\u001a\u00020��2\u0012\b\u0002\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0012\b\u0002\u0010\b\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\tHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010,\u001a\u00020-HÖ\u0001J\t\u0010.\u001a\u00020/HÖ\u0001R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\b\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\tX\u0082\u0004¢\u0006\u0002\n��R\u001b\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0011¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u00060"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/JOperatorHVImpl;", "Lcn/sast/dataflow/interprocedural/analysis/JOperatorHV;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "heapFactory", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "value", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "getHeapFactory", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getEnv", "()Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "pf", "Lcn/sast/dataflow/interprocedural/check/PathFactory;", "getPf", "()Lcn/sast/dataflow/interprocedural/check/PathFactory;", "pop", "setKVValue", "K", "mt", "", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "lhs", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "key", "(Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/JOperatorHV;", "getKVValue", "rhs", "assignLocal", "rhsValue", "markOfArrayLength", "dataElementCopyToSequenceElement", "sourceElement", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
/* loaded from: JOperatorHVImpl.class */
public final class JOperatorHVImpl implements JOperatorHV<IValue> {

    @NotNull
    private final AbstractHeapFactory<IValue> heapFactory;

    @NotNull
    private final HeapValuesEnv env;

    @NotNull
    private final IHeapValues<IValue> value;

    @NotNull
    private final PathFactory<IValue> pf;

    @NotNull
    public final AbstractHeapFactory<IValue> component1() {
        return this.heapFactory;
    }

    @NotNull
    public final HeapValuesEnv component2() {
        return this.env;
    }

    private final IHeapValues<IValue> component3() {
        return this.value;
    }

    @NotNull
    public final JOperatorHVImpl copy(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "heapFactory");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "value");
        return new JOperatorHVImpl(abstractHeapFactory, env, iHeapValues);
    }

    public static /* synthetic */ JOperatorHVImpl copy$default(JOperatorHVImpl jOperatorHVImpl, AbstractHeapFactory abstractHeapFactory, HeapValuesEnv heapValuesEnv, IHeapValues iHeapValues, int i, Object obj) {
        if ((i & 1) != 0) {
            abstractHeapFactory = jOperatorHVImpl.heapFactory;
        }
        if ((i & 2) != 0) {
            heapValuesEnv = jOperatorHVImpl.env;
        }
        if ((i & 4) != 0) {
            iHeapValues = jOperatorHVImpl.value;
        }
        return jOperatorHVImpl.copy(abstractHeapFactory, heapValuesEnv, iHeapValues);
    }

    @NotNull
    public String toString() {
        return "JOperatorHVImpl(heapFactory=" + this.heapFactory + ", env=" + this.env + ", value=" + this.value + ")";
    }

    public int hashCode() {
        int result = this.heapFactory.hashCode();
        return (((result * 31) + this.env.hashCode()) * 31) + this.value.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JOperatorHVImpl)) {
            return false;
        }
        JOperatorHVImpl jOperatorHVImpl = (JOperatorHVImpl) other;
        return Intrinsics.areEqual(this.heapFactory, jOperatorHVImpl.heapFactory) && Intrinsics.areEqual(this.env, jOperatorHVImpl.env) && Intrinsics.areEqual(this.value, jOperatorHVImpl.value);
    }

    public JOperatorHVImpl(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "heapFactory");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "value");
        this.heapFactory = abstractHeapFactory;
        this.env = env;
        this.value = iHeapValues;
        this.pf = this.heapFactory.getPathFactory();
    }

    @NotNull
    public final AbstractHeapFactory<IValue> getHeapFactory() {
        return this.heapFactory;
    }

    @NotNull
    public final HeapValuesEnv getEnv() {
        return this.env;
    }

    @NotNull
    public final PathFactory<IValue> getPf() {
        return this.pf;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorHV
    @NotNull
    public IHeapValues<IValue> pop() {
        return this.value;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorHV
    @NotNull
    public <K> JOperatorHV<IValue> setKVValue(@NotNull Object mt, @NotNull CompanionV<IValue> companionV, @Nullable K k) {
        Intrinsics.checkNotNullParameter(mt, "mt");
        Intrinsics.checkNotNullParameter(companionV, "lhs");
        return copy$default(this, null, null, this.pf.updatePath(this.env, this.value, (v4, v5) -> {
            return setKVValue$lambda$0(r6, r7, r8, r9, v4, v5);
        }), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final IPath setKVValue$lambda$0(JOperatorHVImpl this$0, CompanionV companionV, Object $mt, Object $key, IValue v, IPath valuePath) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(valuePath, "valuePath");
        SetEdgePath.Companion companion = SetEdgePath.Companion;
        HeapValuesEnv heapValuesEnv = this$0.env;
        IValue iValue = (IValue) companionV.getValue();
        Intrinsics.checkNotNull(companionV, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.PathCompanionV");
        return SetEdgePath.Companion.v$default(companion, heapValuesEnv, iValue, ((PathCompanionV) companionV).getPath(), $mt, $key, v, valuePath, null, 128, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorHV
    @NotNull
    public <K> JOperatorHV<IValue> getKVValue(@NotNull Object mt, @NotNull CompanionV<IValue> companionV, @Nullable K k) {
        Intrinsics.checkNotNullParameter(mt, "mt");
        Intrinsics.checkNotNullParameter(companionV, "rhs");
        return copy$default(this, null, null, this.pf.updatePath(this.env, this.value, (v4, v5) -> {
            return getKVValue$lambda$1(r6, r7, r8, r9, v4, v5);
        }), 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final IPath getKVValue$lambda$1(JOperatorHVImpl this$0, CompanionV companionV, Object $mt, Object $key, IValue v, IPath valuePath) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(valuePath, "valuePath");
        GetEdgePath.Companion companion = GetEdgePath.Companion;
        HeapValuesEnv heapValuesEnv = this$0.env;
        IValue iValue = (IValue) companionV.getValue();
        Intrinsics.checkNotNull(companionV, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.check.PathCompanionV");
        return GetEdgePath.Companion.v$default(companion, heapValuesEnv, iValue, ((PathCompanionV) companionV).getPath(), $mt, $key, v, valuePath, null, 128, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorHV
    @NotNull
    public JOperatorHV<IValue> assignLocal(@NotNull Object lhs, @NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(iHeapValues, "rhsValue");
        return copy$default(this, null, null, this.pf.updatePath(this.env, this.value, (v2, v3) -> {
            return assignLocal$lambda$2(r6, r7, v2, v3);
        }), 3, null);
    }

    private static final IPath assignLocal$lambda$2(JOperatorHVImpl this$0, Object $lhs, IValue v, IPath valuePath) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(valuePath, "valuePath");
        return AssignLocalPath.Companion.v(this$0.env, $lhs, v, valuePath);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorHV
    @NotNull
    public JOperatorHV<IValue> markOfArrayLength(@NotNull CompanionV<IValue> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "rhs");
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.JOperatorHV
    @NotNull
    public JOperatorHV<IValue> dataElementCopyToSequenceElement(@NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "sourceElement");
        return this;
    }
}
