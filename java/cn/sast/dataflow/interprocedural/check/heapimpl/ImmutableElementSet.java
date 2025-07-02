package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ImmutableCollections.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n��\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\u0018��*\b\b��\u0010\u0001*\u00020\u00022\u0012\u0012\u0004\u0012\u0002H\u0001\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003B=\u0012\u001e\b\u0002\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00028��\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\b0\u0007\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\u0006\u0010\u0012\u001a\u00020\u0013J\u0012\u0010\u0014\u001a\u00020\u00132\n\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030��J4\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028��0��2\u0010\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028��0��J4\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028��0��2\u0010\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028��0��J4\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028��0��2\u0010\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028��0��J\u0013\u0010\u001d\u001a\u00020\u00132\b\u0010\u001e\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\u001f\u001a\u00020 H\u0016J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00028��0\"H\u0016R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028��0\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006#"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;", "E", "", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMap;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "fields", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "getName", "", "element", "", "getElement", "()Ljava/util/Set;", "isEmpty", "", "containsAll", "rhs", "intersect", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "plus", "minus", "equals", "other", "hashCode", "", "builder", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSetBuilder;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nImmutableCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ImmutableCollections.kt\ncn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,314:1\n1#2:315\n*E\n"})
/* loaded from: ImmutableElementSet.class */
public final class ImmutableElementSet<E> extends ImmutableElementHashMap<E, IValue> {
    public ImmutableElementSet() {
        this(null, null, 3, null);
    }

    public /* synthetic */ ImmutableElementSet(PersistentMap persistentMap, IHeapValues iHeapValues, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ExtensionsKt.persistentHashMapOf() : persistentMap, (i & 2) != 0 ? null : iHeapValues);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImmutableElementSet(@NotNull PersistentMap<E, ? extends IHeapValues<IValue>> persistentMap, @Nullable IHeapValues<IValue> iHeapValues) {
        super(persistentMap, iHeapValues);
        Intrinsics.checkNotNullParameter(persistentMap, "fields");
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap, cn.sast.dataflow.interprocedural.analysis.HeapKVData
    @NotNull
    public String getName() {
        return "ImmHashSet";
    }

    @NotNull
    public final Set<E> getElement() {
        return getMap().keySet();
    }

    public final boolean isEmpty() {
        if (getMap().isEmpty()) {
            IHeapValues<IValue> unreferenced = getUnreferenced();
            if (unreferenced != null ? unreferenced.isEmpty() : true) {
                return true;
            }
        }
        return false;
    }

    public final boolean containsAll(@NotNull ImmutableElementSet<?> immutableElementSet) {
        Intrinsics.checkNotNullParameter(immutableElementSet, "rhs");
        return getMap().keySet().containsAll(immutableElementSet.getMap().keySet());
    }

    @NotNull
    public final ImmutableElementSet<E> intersect(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull ImmutableElementSet<E> immutableElementSet) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(immutableElementSet, "rhs");
        if (immutableElementSet.isEmpty()) {
            return immutableElementSet;
        }
        if (isEmpty()) {
            return this;
        }
        Set set = CollectionsKt.intersect(getMap().keySet(), immutableElementSet.getMap().keySet());
        ImmutableElementSetBuilder r = new ImmutableElementSet(null, null, 3, null).builder2();
        for (E e : set) {
            IHeapValues it = get(abstractHeapFactory, e instanceof Object ? e : null);
            if (it != null) {
                r.set(abstractHeapFactory, env, e, it, true);
            }
            IHeapValues it2 = immutableElementSet.get(abstractHeapFactory, e);
            if (it2 != null) {
                r.set(abstractHeapFactory, env, e, it2, true);
            }
        }
        return r.build2();
    }

    @NotNull
    public final ImmutableElementSet<E> plus(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull ImmutableElementSet<E> immutableElementSet) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(immutableElementSet, "rhs");
        if (isEmpty()) {
            return immutableElementSet;
        }
        if (immutableElementSet.isEmpty()) {
            return this;
        }
        Set set = SetsKt.plus(getMap().keySet(), immutableElementSet.getMap().keySet());
        ImmutableElementSetBuilder r = new ImmutableElementSet(null, null, 3, null).builder2();
        for (E e : set) {
            IHeapValues it = get(abstractHeapFactory, e instanceof Object ? e : null);
            if (it != null) {
                r.set(abstractHeapFactory, env, e, it, true);
            }
            IHeapValues it2 = immutableElementSet.get(abstractHeapFactory, e);
            if (it2 != null) {
                r.set(abstractHeapFactory, env, e, it2, true);
            }
        }
        return r.build2();
    }

    @NotNull
    public final ImmutableElementSet<E> minus(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull HeapValuesEnv env, @NotNull ImmutableElementSet<E> immutableElementSet) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(immutableElementSet, "rhs");
        if (!isEmpty() && !immutableElementSet.isEmpty()) {
            ImmutableElementSetBuilder r = builder2();
            for (E e : immutableElementSet.getElement()) {
                IHeapValues v = get(abstractHeapFactory, e);
                if (v != null && v.isSingle()) {
                    r.getMap().remove(e);
                }
            }
            return r.build2();
        }
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap, cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public boolean equals(@Nullable Object other) {
        return super.equals(other) && (other instanceof ImmutableElementSet);
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap, cn.sast.dataflow.interprocedural.analysis.HeapKVData
    public int hashCode() {
        return super.hashCode();
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMap, cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    /* renamed from: builder */
    public ImmutableElementSetBuilder<E> builder2() {
        PersistentMap.Builder builder = getMap().builder();
        IHeapValues<IValue> unreferenced = getUnreferenced();
        return new ImmutableElementSetBuilder<>(builder, unreferenced != null ? unreferenced.builder() : null);
    }
}
