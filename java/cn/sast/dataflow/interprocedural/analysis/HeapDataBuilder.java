package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IHeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.ReferenceContext;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HeapKVData.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��`\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018�� )*\b\b��\u0010\u0001*\u00020\u0002*\u0004\b\u0001\u0010\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0001)B1\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ+\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00072\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00142\u0006\u0010\u0015\u001a\u00028��H\u0016¢\u0006\u0002\u0010\u0016JE\u0010\u0017\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010\u00142\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0015\u001a\u0004\u0018\u00018��2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0016¢\u0006\u0002\u0010\u001eJ$\u0010\u001f\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010\"H\u0016J\"\u0010#\u001a\u00020\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00010 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010\"J\b\u0010$\u001a\u00020%H\u0016J\u0016\u0010&\u001a\u00020\u00182\f\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00010(H\u0016R#\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00070\u0006¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\"\u0010\b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006*"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/HeapDataBuilder;", "K", "", "V", "Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData$Builder;", "map", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "getMap", "()Lkotlinx/collections/immutable/PersistentMap$Builder;", "getUnreferenced", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "setUnreferenced", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "getValue", "hf", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "key", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "set", "", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "update", "append", "", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Z)V", "union", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "updateFrom", "toString", "", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "Companion", "corax-data-flow"})
/* loaded from: HeapDataBuilder.class */
public abstract class HeapDataBuilder<K, V> implements IHeapKVData.Builder<K, V> {

    @NotNull
    private final PersistentMap.Builder<K, IHeapValues<V>> map;

    @Nullable
    private IHeapValues.Builder<V> unreferenced;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(HeapDataBuilder::logger$lambda$1);

    public HeapDataBuilder(@NotNull PersistentMap.Builder<K, IHeapValues<V>> builder, @Nullable IHeapValues.Builder<V> builder2) {
        Intrinsics.checkNotNullParameter(builder, "map");
        this.map = builder;
        this.unreferenced = builder2;
    }

    @NotNull
    public final PersistentMap.Builder<K, IHeapValues<V>> getMap() {
        return this.map;
    }

    @Nullable
    public final IHeapValues.Builder<V> getUnreferenced() {
        return this.unreferenced;
    }

    public final void setUnreferenced(@Nullable IHeapValues.Builder<V> builder) {
        this.unreferenced = builder;
    }

    /* compiled from: HeapKVData.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/HeapDataBuilder$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: HeapDataBuilder$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getLogger() {
            return HeapDataBuilder.logger;
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }

    @Nullable
    public IHeapValues<V> getValue(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @NotNull K k) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        Intrinsics.checkNotNullParameter(k, "key");
        return (IHeapValues) this.map.get(k);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapKVData.Builder
    public void set(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @NotNull HeapValuesEnv env, @Nullable K k, @Nullable IHeapValues<V> iHeapValues, boolean append) {
        IHeapValues iHeapValuesPlus;
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        if (iHeapValues == null || iHeapValues.isEmpty()) {
            logger.debug(() -> {
                return set$lambda$0(r1);
            });
            return;
        }
        if (k == null) {
            if (this.unreferenced != null) {
                IHeapValues.Builder<V> builder = this.unreferenced;
                Intrinsics.checkNotNull(builder);
                builder.add(iHeapValues);
                return;
            }
            this.unreferenced = iHeapValues.builder();
            return;
        }
        IHeapValues exist = getValue(iHeapValuesFactory, k);
        if (append && exist != null) {
            iHeapValuesPlus = iHeapValues.plus(exist);
        } else {
            iHeapValuesPlus = iHeapValues;
        }
        this.map.put(k, iHeapValuesPlus);
    }

    private static final Object set$lambda$0(IHeapValues $update) {
        return "ignore update is " + $update;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    public void union(@NotNull AbstractHeapFactory<V> abstractHeapFactory, @NotNull IData<V> iData) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(iData, "that");
        if (!(iData instanceof HeapKVData)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.unreferenced == null) {
            if (((HeapKVData) iData).getUnreferenced() != null) {
                IHeapValues<V> unreferenced = ((HeapKVData) iData).getUnreferenced();
                Intrinsics.checkNotNull(unreferenced, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.analysis.IHeapValues<V of cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder>");
                this.unreferenced = unreferenced.builder();
            }
        } else if (((HeapKVData) iData).getUnreferenced() != null) {
            IHeapValues.Builder<V> builder = this.unreferenced;
            Intrinsics.checkNotNull(builder);
            IHeapValues<V> unreferenced2 = ((HeapKVData) iData).getUnreferenced();
            Intrinsics.checkNotNull(unreferenced2, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.analysis.IHeapValues<V of cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder>");
            builder.add(unreferenced2);
        }
        if (this.map != ((HeapKVData) iData).getMap()) {
            Map map = ((HeapKVData) iData).getMap();
            Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlinx.collections.immutable.PersistentMap<K of cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder, cn.sast.dataflow.interprocedural.analysis.IHeapValues<V of cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder>>");
            for (Map.Entry<K, V> entry : map.entrySet()) {
                K key = entry.getKey();
                IHeapValues v = (IHeapValues) entry.getValue();
                IHeapValues exist = (IHeapValues) this.map.get(key);
                if (exist == null) {
                    this.map.put(key, v);
                } else {
                    this.map.put(key, v.plus(exist));
                }
            }
        }
    }

    public final void updateFrom(@NotNull AbstractHeapFactory<V> abstractHeapFactory, @NotNull IData<V> iData) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(iData, "that");
        if (!(iData instanceof HeapKVData)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.unreferenced == null) {
            if (((HeapKVData) iData).getUnreferenced() != null) {
                IHeapValues<V> unreferenced = ((HeapKVData) iData).getUnreferenced();
                Intrinsics.checkNotNull(unreferenced, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.analysis.IHeapValues<V of cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder>");
                this.unreferenced = unreferenced.builder();
            }
        } else if (((HeapKVData) iData).getUnreferenced() != null) {
            IHeapValues.Builder<V> builder = this.unreferenced;
            Intrinsics.checkNotNull(builder);
            IHeapValues<V> unreferenced2 = ((HeapKVData) iData).getUnreferenced();
            Intrinsics.checkNotNull(unreferenced2, "null cannot be cast to non-null type cn.sast.dataflow.interprocedural.analysis.IHeapValues<V of cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder>");
            builder.add(unreferenced2);
        }
        if (this.map != ((HeapKVData) iData).getMap()) {
            Map map = ((HeapKVData) iData).getMap();
            Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlinx.collections.immutable.PersistentMap<K of cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder, cn.sast.dataflow.interprocedural.analysis.IHeapValues<V of cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder>>");
            for (Map.Entry<K, V> entry : map.entrySet()) {
                K key = entry.getKey();
                IHeapValues v = (IHeapValues) entry.getValue();
                this.map.put(key, v);
            }
        }
    }

    @NotNull
    public String toString() {
        return build().toString();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    public void cloneAndReNewObjects(@NotNull IReNew<V> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        for (Map.Entry<K, V> entry : this.map.build().entrySet()) {
            K key = entry.getKey();
            IHeapValues v = (IHeapValues) entry.getValue();
            this.map.put(key, v.cloneAndReNewObjects(iReNew.context(new ReferenceContext.KVPosition(key))));
        }
        IHeapValues.Builder<V> builder = this.unreferenced;
        if (builder != null) {
            builder.cloneAndReNewObjects(iReNew.context(ReferenceContext.KVUnreferenced.INSTANCE));
        }
    }
}
