package cn.sast.dataflow.interprocedural.analysis.heapimpl;

import cn.sast.dataflow.interprocedural.analysis.CompanionV;
import cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder;
import cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.ReferenceContext;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;

/* compiled from: ArrayHeapKV.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018��*\u0004\b��\u0010\u00012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00010\u0002Ba\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00060\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028��0\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010J&\u0010\u001c\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028��0\u001e2\u0006\u0010\u001f\u001a\u00020\u0003H\u0016JE\u0010 \u001a\u00020!2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028��0\u001e2\u0006\u0010\"\u001a\u00020#2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00032\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00062\u0006\u0010%\u001a\u00020&H\u0016¢\u0006\u0002\u0010'J\u0016\u0010(\u001a\u00020!2\f\u0010)\u001a\b\u0012\u0004\u0012\u00028��0*H\u0016J\u0006\u0010+\u001a\u00020!R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028��0\b¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\"\u0010\r\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006,"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/heapimpl/ArrayHeapBuilder;", "V", "Lcn/sast/dataflow/interprocedural/analysis/HeapDataBuilder;", "", "element", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "type", "Lsoot/ArrayType;", "allSize", "size", "initializedValue", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;Lsoot/ArrayType;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;Ljava/lang/Integer;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)V", "getType", "()Lsoot/ArrayType;", "getAllSize", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "getSize", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getInitializedValue", "()Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "setInitializedValue", "(Lcn/sast/dataflow/interprocedural/analysis/CompanionV;)V", "getValue", "hf", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;", "key", "set", "", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "update", "append", "", "(Lcn/sast/dataflow/interprocedural/analysis/IHeapValuesFactory;Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Integer;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;Z)V", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "clearAllIndex", "corax-data-flow"})
/* loaded from: ArrayHeapBuilder.class */
public abstract class ArrayHeapBuilder<V> extends HeapDataBuilder<Integer, V> {

    @NotNull
    private final ArrayType type;

    @NotNull
    private final IHeapValues.Builder<V> allSize;

    @Nullable
    private final Integer size;

    @Nullable
    private CompanionV<V> initializedValue;

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder
    public /* bridge */ /* synthetic */ IHeapValues getValue(IHeapValuesFactory hf, Integer num) {
        return getValue(hf, num.intValue());
    }

    @NotNull
    public final ArrayType getType() {
        return this.type;
    }

    @NotNull
    public final IHeapValues.Builder<V> getAllSize() {
        return this.allSize;
    }

    @Nullable
    public final Integer getSize() {
        return this.size;
    }

    @Nullable
    public final CompanionV<V> getInitializedValue() {
        return this.initializedValue;
    }

    public final void setInitializedValue(@Nullable CompanionV<V> companionV) {
        this.initializedValue = companionV;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ArrayHeapBuilder(@NotNull PersistentMap.Builder<Integer, IHeapValues<V>> builder, @Nullable IHeapValues.Builder<V> builder2, @NotNull ArrayType type, @NotNull IHeapValues.Builder<V> builder3, @Nullable Integer size, @Nullable CompanionV<V> companionV) {
        super(builder, builder2);
        Intrinsics.checkNotNullParameter(builder, "element");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(builder3, "allSize");
        this.type = type;
        this.allSize = builder3;
        this.size = size;
        this.initializedValue = companionV;
        if (!this.allSize.isNotEmpty()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public cn.sast.dataflow.interprocedural.analysis.IHeapValues<V> getValue(@org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory<V> r5, int r6) {
        /*
            r4 = this;
            r0 = r5
            java.lang.String r1 = "hf"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r4
            cn.sast.dataflow.interprocedural.analysis.CompanionV<V> r0 = r0.initializedValue
            r7 = r0
            r0 = r4
            r1 = r5
            r2 = r6
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            cn.sast.dataflow.interprocedural.analysis.IHeapValues r0 = super.getValue(r1, r2)
            r1 = r0
            if (r1 != 0) goto L4e
        L19:
            r0 = r4
            java.lang.Integer r0 = r0.size
            if (r0 == 0) goto L3f
            r0 = r4
            java.lang.Integer r0 = r0.size
            r1 = r4
            kotlinx.collections.immutable.PersistentMap$Builder r1 = r1.getMap()
            int r1 = r1.size()
            r8 = r1
            r1 = r0
            if (r1 != 0) goto L37
        L34:
            goto L3f
        L37:
            int r0 = r0.intValue()
            r1 = r8
            if (r0 == r1) goto L4d
        L3f:
            r0 = r7
            if (r0 == 0) goto L4d
            r0 = r5
            r1 = r7
            cn.sast.dataflow.interprocedural.analysis.IHeapValues r0 = r0.single(r1)
            goto L4e
        L4d:
            r0 = 0
        L4e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.heapimpl.ArrayHeapBuilder.getValue(cn.sast.dataflow.interprocedural.analysis.IHeapValuesFactory, int):cn.sast.dataflow.interprocedural.analysis.IHeapValues");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder, cn.sast.dataflow.interprocedural.analysis.IHeapKVData.Builder
    public void set(@NotNull IHeapValuesFactory<V> iHeapValuesFactory, @NotNull HeapValuesEnv env, @Nullable Integer key, @Nullable IHeapValues<V> iHeapValues, boolean append) {
        Intrinsics.checkNotNullParameter(iHeapValuesFactory, "hf");
        Intrinsics.checkNotNullParameter(env, "env");
        if (Intrinsics.areEqual(ArrayHeapKVKt.isValidKey(key, this.size), false)) {
            return;
        }
        super.set((IHeapValuesFactory) iHeapValuesFactory, env, (HeapValuesEnv) key, (IHeapValues) iHeapValues, append);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.sast.dataflow.interprocedural.analysis.HeapDataBuilder, cn.sast.dataflow.interprocedural.analysis.IData.Builder
    public void cloneAndReNewObjects(@NotNull IReNew<V> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        super.cloneAndReNewObjects(iReNew.context(ReferenceContext.ArrayElement.INSTANCE));
        this.allSize.cloneAndReNewObjects(iReNew.context(ReferenceContext.ArraySize.INSTANCE));
        CompanionV<V> companionV = this.initializedValue;
        if (companionV != null) {
            V value = companionV.getValue();
            V vCheckNeedReplace = iReNew.checkNeedReplace((IReNew<V>) value);
            V v = vCheckNeedReplace;
            if (vCheckNeedReplace == null) {
                v = value;
            }
            V v2 = v;
            CompanionV<V> companionVCheckNeedReplace = iReNew.context(ReferenceContext.ArrayInitialized.INSTANCE).checkNeedReplace((CompanionV) companionV);
            if (companionVCheckNeedReplace == null) {
                companionVCheckNeedReplace = companionV;
            }
            CompanionV<V> companionVCopy = companionVCheckNeedReplace;
            if (Intrinsics.areEqual(value, v2) && companionVCopy == companionV) {
                return;
            }
            if (!Intrinsics.areEqual(companionVCopy.getValue(), v2)) {
                companionVCopy = companionVCopy.copy(v2);
            }
            this.initializedValue = companionVCopy;
        }
    }

    public final void clearAllIndex() {
    }
}
