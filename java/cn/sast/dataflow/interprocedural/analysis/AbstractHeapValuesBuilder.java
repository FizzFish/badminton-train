package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.ReferenceContext;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.util.Printer;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n��\b6\u0018��*\b\b��\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B1\b\u0004\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028��0\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028��0\u0014H\u0016J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028��0\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028��0\bH\u0016J\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028��0\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028��\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\b0\u0007¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000e\u0082\u0001\u0001\u001c¨\u0006\u001d"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapValuesBuilder;", "V", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "orig", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapValues;", "map", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapValues;Lkotlinx/collections/immutable/PersistentMap$Builder;)V", "getOrig", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapValues;", "getMap", "()Lkotlinx/collections/immutable/PersistentMap$Builder;", "isNotEmpty", "", "isEmpty", "add", "elements", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "element", "cloneAndReNewObjects", "", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "toString", "", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesBuilder;", "corax-data-flow"})
/* loaded from: AbstractHeapValuesBuilder.class */
public abstract class AbstractHeapValuesBuilder<V> implements IHeapValues.Builder<V> {

    @NotNull
    private final AbstractHeapValues<V> orig;

    @NotNull
    private final PersistentMap.Builder<V, CompanionV<V>> map;

    public /* synthetic */ AbstractHeapValuesBuilder(AbstractHeapValues orig, PersistentMap.Builder map, DefaultConstructorMarker $constructor_marker) {
        this(orig, map);
    }

    private AbstractHeapValuesBuilder(AbstractHeapValues<V> abstractHeapValues, PersistentMap.Builder<V, CompanionV<V>> builder) {
        this.orig = abstractHeapValues;
        this.map = builder;
    }

    @NotNull
    public AbstractHeapValues<V> getOrig() {
        return this.orig;
    }

    @NotNull
    public final PersistentMap.Builder<V, CompanionV<V>> getMap() {
        return this.map;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues.Builder
    public boolean isNotEmpty() {
        return !this.map.isEmpty();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues.Builder
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues.Builder
    @NotNull
    public IHeapValues.Builder<V> add(@NotNull IHeapValues<V> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "elements");
        if (iHeapValues instanceof AbstractHeapValues) {
            Iterator<CompanionV<V>> it = ((AbstractHeapValues) iHeapValues).iterator();
            while (it.hasNext()) {
                CompanionV e = it.next();
                add(e);
            }
        }
        return this;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues.Builder
    @NotNull
    public IHeapValues.Builder<V> add(@NotNull CompanionV<V> companionV) {
        Intrinsics.checkNotNullParameter(companionV, "element");
        V value = companionV.getValue();
        CompanionV existV = (CompanionV) this.map.get(value);
        if (existV == null) {
            this.map.put(value, companionV);
        } else {
            this.map.put(value, existV.union(companionV));
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v40, types: [cn.sast.dataflow.interprocedural.analysis.CompanionV] */
    /* JADX WARN: Type inference failed for: r13v0, types: [cn.sast.dataflow.interprocedural.analysis.CompanionV] */
    /* JADX WARN: Type inference failed for: r6v0, types: [cn.sast.dataflow.interprocedural.analysis.IReNew, cn.sast.dataflow.interprocedural.analysis.IReNew<V>, java.lang.Object] */
    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues.Builder
    public void cloneAndReNewObjects(@NotNull IReNew<V> iReNew) {
        Intrinsics.checkNotNullParameter((Object) iReNew, "re");
        for (Map.Entry entry : this.map.build().entrySet()) {
            Object key = entry.getKey();
            CompanionV<V> companionV = (CompanionV) entry.getValue();
            Object objCheckNeedReplace = iReNew.checkNeedReplace(key);
            if (objCheckNeedReplace == null) {
                objCheckNeedReplace = key;
            }
            Object obj = objCheckNeedReplace;
            CompanionV<V> companionVCheckNeedReplace = iReNew.context(new ReferenceContext.ObjectValues(key)).checkNeedReplace((CompanionV) companionV);
            if (companionVCheckNeedReplace == null) {
                companionVCheckNeedReplace = companionV;
            }
            ?? r13 = companionVCheckNeedReplace;
            if (!Intrinsics.areEqual(key, obj) || r13 != companionV) {
                boolean zAreEqual = Intrinsics.areEqual(r13.getValue(), obj);
                V vCopy = r13;
                if (!zAreEqual) {
                    vCopy = r13.copy(obj);
                }
                this.map.put(obj, vCopy);
                if (!Intrinsics.areEqual(key, obj)) {
                    this.map.remove(key);
                }
            }
        }
    }

    @NotNull
    public String toString() {
        return Printer.Companion.nodes2String(this.map.values());
    }
}
