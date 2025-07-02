package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.ImmutableSet;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Type;
import soot.Value;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��z\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002Bw\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\f0\n\u0012(\u0010\r\u001a$\u0012\u0004\u0012\u00028��\u0012\u001a\u0012\u0018\u0012\b\u0012\u00060\u000bj\u0002`\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u000f0\n0\n\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u0010'\u001a\u00020!J\b\u0010 \u001a\u00020!H\u0016J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u000bH\u0096\u0002J\b\u0010+\u001a\u00020,H\u0016J)\u0010-\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000f2\u0006\u0010.\u001a\u00028��2\n\u0010/\u001a\u00060\u000bj\u0002`\u000eH\u0016¢\u0006\u0002\u00100J\u0018\u00101\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\f2\u0006\u00102\u001a\u00020\u000bH\u0016J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b03H\u0016J\u000e\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001204H\u0016J\b\u00105\u001a\u00020)H\u0016J\b\u00106\u001a\u00020)H\u0016J\b\u00107\u001a\u00020)H\u0016J \u00108\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\f2\u0006\u00109\u001a\u00020:2\u0006\u00102\u001a\u00020\u000bH\u0016J$\u0010;\u001a\u00020<2\f\u0010=\u001a\b\u0012\u0004\u0012\u00028��0>2\f\u0010?\u001a\b\u0012\u0004\u0012\u00028��0\u0002H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028��0\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR#\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\f0\n¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR3\u0010\r\u001a$\u0012\u0004\u0012\u00028��\u0012\u001a\u0012\u0018\u0012\b\u0012\u00060\u000bj\u0002`\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u000f0\n0\n¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u001cR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n��\u001a\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006@"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstract;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "callStack", "Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "slots", "Lkotlinx/collections/immutable/PersistentMap;", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "heap", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "calledMethods", "Lkotlinx/collections/immutable/PersistentSet;", "Lsoot/SootMethod;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentSet;)V", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getVg", "()Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "getCallStack", "()Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "getSlots", "()Lkotlinx/collections/immutable/PersistentMap;", "getHeap", "getCalledMethods", "()Lkotlinx/collections/immutable/PersistentSet;", "hashCode", "", "getHashCode", "()Ljava/lang/Integer;", "setHashCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "computeHash", "equals", "", "other", "toString", "", "getValueData", "v", "mt", "(Ljava/lang/Object;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/IData;", "getTargetsUnsafe", "slot", "", "Lkotlinx/collections/immutable/ImmutableSet;", "isBottom", "isTop", "isValid", "getOfSlot", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "diff", "", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "corax-data-flow"})
/* loaded from: PointsToGraphAbstract.class */
public abstract class PointsToGraphAbstract<V> implements IFact<V> {

    @NotNull
    private final AbstractHeapFactory<V> hf;

    @NotNull
    private final IVGlobal vg;

    @NotNull
    private final CallStackContext callStack;

    @NotNull
    private final PersistentMap<Object, IHeapValues<V>> slots;

    @NotNull
    private final PersistentMap<V, PersistentMap<Object, IData<V>>> heap;

    @NotNull
    private final PersistentSet<SootMethod> calledMethods;

    @Nullable
    private Integer hashCode;

    /* JADX WARN: Multi-variable type inference failed */
    public PointsToGraphAbstract(@NotNull AbstractHeapFactory<V> abstractHeapFactory, @NotNull IVGlobal vg, @NotNull CallStackContext callStack, @NotNull PersistentMap<Object, ? extends IHeapValues<V>> persistentMap, @NotNull PersistentMap<V, ? extends PersistentMap<Object, ? extends IData<V>>> persistentMap2, @NotNull PersistentSet<? extends SootMethod> persistentSet) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(vg, "vg");
        Intrinsics.checkNotNullParameter(callStack, "callStack");
        Intrinsics.checkNotNullParameter(persistentMap, "slots");
        Intrinsics.checkNotNullParameter(persistentMap2, "heap");
        Intrinsics.checkNotNullParameter(persistentSet, "calledMethods");
        this.hf = abstractHeapFactory;
        this.vg = vg;
        this.callStack = callStack;
        this.slots = persistentMap;
        this.heap = persistentMap2;
        this.calledMethods = persistentSet;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public IHeapValues<V> getTargets(@NotNull Object slot) {
        return IFact.DefaultImpls.getTargets(this, slot);
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

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public AbstractHeapFactory<V> getHf() {
        return this.hf;
    }

    @NotNull
    public final IVGlobal getVg() {
        return this.vg;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public CallStackContext getCallStack() {
        return this.callStack;
    }

    @NotNull
    public final PersistentMap<Object, IHeapValues<V>> getSlots() {
        return this.slots;
    }

    @NotNull
    public final PersistentMap<V, PersistentMap<Object, IData<V>>> getHeap() {
        return this.heap;
    }

    @NotNull
    public final PersistentSet<SootMethod> getCalledMethods() {
        return this.calledMethods;
    }

    @Nullable
    public final Integer getHashCode() {
        return this.hashCode;
    }

    public final void setHashCode(@Nullable Integer num) {
        this.hashCode = num;
    }

    public final int computeHash() {
        int result = (31 * 1) + this.slots.hashCode();
        return (31 * result) + this.heap.hashCode();
    }

    public int hashCode() {
        Integer h = this.hashCode;
        if (h == null) {
            h = Integer.valueOf(computeHash());
            this.hashCode = h;
        }
        return h.intValue();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if ((other instanceof PointsToGraphAbstract) && hashCode() == ((PointsToGraphAbstract) other).hashCode() && Intrinsics.areEqual(this.slots, ((PointsToGraphAbstract) other).slots)) {
            return Intrinsics.areEqual(this.heap, ((PointsToGraphAbstract) other).heap);
        }
        return false;
    }

    @NotNull
    public String toString() {
        return builder().toString();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IData<V> getValueData(V v, @NotNull Object mt) {
        Intrinsics.checkNotNullParameter(mt, "mt");
        PersistentMap persistentMap = (PersistentMap) this.heap.get(v);
        if (persistentMap != null) {
            return (IData) persistentMap.get(mt);
        }
        return null;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IHeapValues<V> getTargetsUnsafe(@NotNull Object slot) {
        Intrinsics.checkNotNullParameter(slot, "slot");
        return (IHeapValues) this.slots.get(slot);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    /* renamed from: getSlots, reason: collision with other method in class */
    public Set<Object> mo173getSlots() {
        return this.slots.keySet();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    /* renamed from: getCalledMethods, reason: collision with other method in class */
    public ImmutableSet<SootMethod> mo174getCalledMethods() {
        return this.calledMethods;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isBottom() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isTop() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isValid() {
        return true;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IHeapValues<V> getOfSlot(@NotNull HeapValuesEnv env, @NotNull Object slot) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(slot, "slot");
        return getTargets(slot);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact
    public void diff(@NotNull IDiff<V> iDiff, @NotNull IFact<V> iFact) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iFact, "that");
        if (!(iFact instanceof PointsToGraphAbstract)) {
            throw new IllegalArgumentException("union error of fact type: " + iFact.getClass() + " \n" + iFact);
        }
        for (Map.Entry entry : ((PointsToGraphAbstract) iFact).slots.entrySet()) {
            Object k = entry.getKey();
            IHeapValues v = (IHeapValues) entry.getValue();
            IHeapValues iHeapValues = (IHeapValues) this.slots.get(k);
            if (iHeapValues != null) {
                iHeapValues.diff(iDiff, v);
            }
        }
        for (Map.Entry entry2 : ((PointsToGraphAbstract) iFact).heap.entrySet()) {
            Object thatSource = entry2.getKey();
            Map map = (PersistentMap) entry2.getValue();
            PersistentMap persistentMapPersistentHashMapOf = (PersistentMap) this.heap.get(thatSource);
            if (persistentMapPersistentHashMapOf == null) {
                persistentMapPersistentHashMapOf = ExtensionsKt.persistentHashMapOf();
            }
            PersistentMap pack = persistentMapPersistentHashMapOf;
            for (Map.Entry entry3 : map.entrySet()) {
                Object kind = entry3.getKey();
                IData valuesR = (IData) entry3.getValue();
                IData valuesL = (IData) pack.get(kind);
                if (valuesL != null && valuesL != valuesR) {
                    valuesL.diff(iDiff, valuesR);
                }
            }
        }
    }
}
