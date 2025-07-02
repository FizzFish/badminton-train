package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.Context;
import cn.sast.idfa.analysis.FixPointStatus;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.ImmutableSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.Value;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J6\u0010\u0005\u001a\u00020\u00062\u001e\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028��0\fH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0018\u0010\u0010\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014H\u0016J)\u0010\u0015\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00028��2\n\u0010\u0018\u001a\u00060\u000fj\u0002`\u0019H\u0016¢\u0006\u0002\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/InValidFact;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "<init>", "()V", "hasChange", "Lcn/sast/idfa/analysis/FixPointStatus;", "context", "Lcn/sast/idfa/analysis/Context;", "Lsoot/SootMethod;", "Lsoot/Unit;", "new", "Lcn/sast/dataflow/interprocedural/analysis/IProblemIteratorTerminal;", "getSlots", "", "", "getTargetsUnsafe", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "slot", "getCalledMethods", "Lkotlinx/collections/immutable/ImmutableSet;", "getValueData", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "v", "mt", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "(Ljava/lang/Object;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/IData;", "callStack", "Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "getCallStack", "()Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nIFact.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/InValidFact\n+ 2 FixPointStatus.kt\ncn/sast/idfa/analysis/FixPointStatus$Companion\n*L\n1#1,507:1\n10#2:508\n*S KotlinDebug\n*F\n+ 1 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/InValidFact\n*L\n230#1:508\n*E\n"})
/* loaded from: InValidFact.class */
public abstract class InValidFact<V> implements IFact<V> {
    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public IHeapValues<V> getTargets(@NotNull Object slot) {
        return IFact.DefaultImpls.getTargets(this, slot);
    }

    public boolean isValid() {
        return IFact.DefaultImpls.isValid(this);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IHeapValues<V> getArrayLength(V v) {
        return IFact.DefaultImpls.getArrayLength(this, v);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IArrayHeapKV<Integer, V> getArray(V v) {
        return IFact.DefaultImpls.getArray(this, v);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public IHeapValues<V> getOfSootValue(@NotNull HeapValuesEnv env, @NotNull Value value, @NotNull Type valueType) {
        return IFact.DefaultImpls.getOfSootValue(this, env, value, valueType);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IProblemIteratorTerminal
    @NotNull
    public FixPointStatus hasChange(@NotNull Context<SootMethod, Unit, IFact<V>> context, @NotNull IProblemIteratorTerminal<V> iProblemIteratorTerminal) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(iProblemIteratorTerminal, "new");
        FixPointStatus.Companion companion = FixPointStatus.Companion;
        boolean hasChange$iv = !Intrinsics.areEqual(this, iProblemIteratorTerminal);
        return hasChange$iv ? FixPointStatus.HasChange : FixPointStatus.Fixpoint;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    /* renamed from: getSlots */
    public Set<Object> mo173getSlots() {
        return SetsKt.emptySet();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IHeapValues<V> getTargetsUnsafe(@NotNull Object slot) {
        Intrinsics.checkNotNullParameter(slot, "slot");
        return null;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    /* renamed from: getCalledMethods */
    public ImmutableSet<SootMethod> mo174getCalledMethods() {
        return ExtensionsKt.persistentHashSetOf();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IData<V> getValueData(V v, @NotNull Object mt) {
        Intrinsics.checkNotNullParameter(mt, "mt");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public CallStackContext getCallStack() throws NotImplementedError {
        throw new NotImplementedError("An operation is not implemented: Not yet implemented");
    }
}
