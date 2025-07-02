package cn.sast.dataflow.interprocedural.check.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ImmutableCollections.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0016\u0018��2\u0016\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001BO\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005\u0012 \u0010\u0006\u001a\u001c\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u0005¢\u0006\u0004\b\n\u0010\u000bJN\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u00032\u0012\u0010\u0015\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016JN\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00182\u0012\u0010\u0015\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u0017JN\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0010\u0010\u0014\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\b2\u0012\u0010\u0015\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\b2\u0006\u0010\u0016\u001a\u00020\u0017J\u001c\u0010\u0019\u001a\u0016\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001aH\u0016R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/heapimpl/ObjectKeyHashMapBuilder;", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMapBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "keys", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "fields", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "getKeys", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "set", "", "hf", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "key", "update", "append", "", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "build", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMap;", "corax-data-flow"})
/* loaded from: ObjectKeyHashMapBuilder.class */
public class ObjectKeyHashMapBuilder extends ImmutableElementHashMapBuilder<IValue, IValue> {

    @NotNull
    private final IHeapValues.Builder<IValue> keys;

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder, cn.sast.dataflow.interprocedural.analysis.IHeapKVData.Builder
    public /* bridge */ /* synthetic */ void set(IHeapValuesFactory hf, HeapValuesEnv env, Object key, IHeapValues update, boolean append) {
        set((IHeapValuesFactory<IValue>) hf, env, (IValue) key, (IHeapValues<IValue>) update, append);
    }

    @NotNull
    public final IHeapValues.Builder<IValue> getKeys() {
        return this.keys;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ObjectKeyHashMapBuilder(@NotNull IHeapValues.Builder<IValue> builder, @NotNull PersistentMap.Builder<IValue, IHeapValues<IValue>> builder2, @Nullable IHeapValues.Builder<IValue> builder3) {
        super(builder2, builder3);
        Intrinsics.checkNotNullParameter(builder, "keys");
        Intrinsics.checkNotNullParameter(builder2, "fields");
        this.keys = builder;
    }

    public void set(@NotNull IHeapValuesFactory<IValue> iHeapValuesFactory, @NotNull HeapValuesEnv env, @Nullable IValue key, @Nullable IHeapValues<IValue> iHeapValues, boolean append) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        throw new IllegalStateException("key must be CompanionV".toString());
    }

    public final void set(@NotNull IHeapValuesFactory<IValue> iHeapValuesFactory, @NotNull HeapValuesEnv env, @NotNull CompanionV<IValue> companionV, @Nullable IHeapValues<IValue> iHeapValues, boolean append) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(companionV, "key");
        super.set((IHeapValuesFactory) iHeapValuesFactory, env, (HeapValuesEnv) companionV.getValue(), (IHeapValues) iHeapValues, append);
        this.keys.add(companionV);
    }

    public final void set(@NotNull IHeapValuesFactory<IValue> iHeapValuesFactory, @NotNull HeapValuesEnv env, @NotNull IHeapValues<IValue> iHeapValues, @Nullable IHeapValues<IValue> iHeapValues2, boolean append) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "key");
        for (CompanionV k : iHeapValues) {
            set(iHeapValuesFactory, env, (CompanionV<IValue>) k, iHeapValues2, append);
        }
    }

    @Override // cn.sast.dataflow.interprocedural.check.heapimpl.ImmutableElementHashMapBuilder, cn.sast.dataflow.interprocedural.analysis.IData.Builder
    @NotNull
    /* renamed from: build */
    public ImmutableElementHashMap<IValue, IValue> build2() {
        IHeapValues<IValue> iHeapValuesBuild = this.keys.build();
        PersistentMap persistentMapBuild = getMap().build();
        IHeapValues.Builder<IValue> unreferenced = getUnreferenced();
        return new ObjectKeyHashMap(iHeapValuesBuild, persistentMapBuild, unreferenced != null ? unreferenced.build() : null);
    }
}
