package cn.sast.dataflow.interprocedural.override.lang.util;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IDiff;
import cn.sast.dataflow.interprocedural.analysis.IDiffAble;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: WArrayList.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001f\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B7\u0012\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u0005\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J,\u0010\u0016\u001a\u00020\u000f2\u0010\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00182\u0010\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001b0\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u0015H\u0016J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\"\u0010!\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00062\u0010\u0010\"\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030#J6\u0010$\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u00062\u0010\u0010\"\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030#2\b\u0010%\u001a\u0004\u0018\u00010\u0015H\u0086\u0002¢\u0006\u0002\u0010&J$\u0010'\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00012\u0010\u0010(\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030)H\u0016J\u0019\u0010*\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u0005HÆ\u0003J\u0015\u0010+\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u0006HÆ\u0003J9\u0010,\u001a\u00020��2\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u0006HÆ\u0001J\t\u0010-\u001a\u00020.HÖ\u0001R!\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0007\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\u0006¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001d¨\u0006/"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/util/ListSpace;", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "list", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "<init>", "(Lkotlinx/collections/immutable/PersistentList;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;)V", "getList", "()Lkotlinx/collections/immutable/PersistentList;", "getUnreferenced", "()Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "reference", "", "res", "", "builder", "Lcn/sast/dataflow/interprocedural/override/lang/util/ListSpaceBuilder;", "computeHash", "", "diff", "cmp", "Lcn/sast/dataflow/interprocedural/analysis/IDiff;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IDiffAble;", "", "hashCode", "Ljava/lang/Integer;", "equals", "", "other", "getAllElement", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "get", "index", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Ljava/lang/Integer;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "component1", "component2", "copy", "toString", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nWArrayList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WArrayList.kt\ncn/sast/dataflow/interprocedural/override/lang/util/ListSpace\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,344:1\n1#2:345\n1863#3,2:346\n*S KotlinDebug\n*F\n+ 1 WArrayList.kt\ncn/sast/dataflow/interprocedural/override/lang/util/ListSpace\n*L\n219#1:346,2\n*E\n"})
/* loaded from: ListSpace.class */
public final class ListSpace implements IData<IValue> {

    @NotNull
    private final PersistentList<IHeapValues<IValue>> list;

    @Nullable
    private final IHeapValues<IValue> unreferenced;

    @Nullable
    private Integer hashCode;

    @NotNull
    public final PersistentList<IHeapValues<IValue>> component1() {
        return this.list;
    }

    @Nullable
    public final IHeapValues<IValue> component2() {
        return this.unreferenced;
    }

    @NotNull
    public final ListSpace copy(@NotNull PersistentList<? extends IHeapValues<IValue>> persistentList, @Nullable IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(persistentList, "list");
        return new ListSpace(persistentList, iHeapValues);
    }

    public static /* synthetic */ ListSpace copy$default(ListSpace listSpace, PersistentList persistentList, IHeapValues iHeapValues, int i, Object obj) {
        if ((i & 1) != 0) {
            persistentList = listSpace.list;
        }
        if ((i & 2) != 0) {
            iHeapValues = listSpace.unreferenced;
        }
        return listSpace.copy(persistentList, iHeapValues);
    }

    @NotNull
    public String toString() {
        return "ListSpace(list=" + this.list + ", unreferenced=" + this.unreferenced + ")";
    }

    public ListSpace() {
        this(null, null, 3, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ListSpace(@NotNull PersistentList<? extends IHeapValues<IValue>> persistentList, @Nullable IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(persistentList, "list");
        this.list = persistentList;
        this.unreferenced = iHeapValues;
        if (!(this.unreferenced == null || this.unreferenced.isNotEmpty())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public /* synthetic */ ListSpace(PersistentList persistentList, IHeapValues iHeapValues, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ExtensionsKt.persistentListOf() : persistentList, (i & 2) != 0 ? null : iHeapValues);
    }

    @NotNull
    public final PersistentList<IHeapValues<IValue>> getList() {
        return this.list;
    }

    @Nullable
    public final IHeapValues<IValue> getUnreferenced() {
        return this.unreferenced;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    public void reference(@NotNull Collection<IValue> collection) {
        Intrinsics.checkNotNullParameter(collection, "res");
        Iterator it = this.list.iterator();
        while (it.hasNext()) {
            IHeapValues e = (IHeapValues) it.next();
            e.reference(collection);
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    /* renamed from: builder */
    public IData.Builder<IValue> builder2() {
        PersistentList.Builder builder = this.list.builder();
        IHeapValues<IValue> iHeapValues = this.unreferenced;
        return new ListSpaceBuilder(builder, iHeapValues != null ? iHeapValues.builder() : null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    public int computeHash() {
        int result = (31 * 1) + this.list.hashCode();
        int i = 31 * result;
        IHeapValues<IValue> iHeapValues = this.unreferenced;
        int result2 = i + (iHeapValues != null ? iHeapValues.hashCode() : 0);
        return result2 + 1231;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IDiffAble
    public void diff(@NotNull IDiff<IValue> iDiff, @NotNull IDiffAble<? extends Object> iDiffAble) {
        Intrinsics.checkNotNullParameter(iDiff, "cmp");
        Intrinsics.checkNotNullParameter(iDiffAble, "that");
        if (this != iDiffAble && (iDiffAble instanceof ListSpace)) {
            int i = 0;
            for (IHeapValues l : this.list) {
                int i2 = i;
                i++;
                if (i2 >= ((ListSpace) iDiffAble).list.size()) {
                    break;
                } else {
                    l.diff(iDiff, (IDiffAble) ((ListSpace) iDiffAble).list.get(i2));
                }
            }
            IHeapValues it = ((ListSpace) iDiffAble).unreferenced;
            if (it != null) {
                IHeapValues<IValue> iHeapValues = this.unreferenced;
                if (iHeapValues != null) {
                    iHeapValues.diff(iDiff, it);
                }
            }
        }
    }

    public int hashCode() {
        Integer hash = this.hashCode;
        if (hash == null) {
            hash = Integer.valueOf(computeHash());
            this.hashCode = hash;
        }
        return hash.intValue();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ListSpace) && hashCode() == ((ListSpace) other).hashCode() && Intrinsics.areEqual(this.list, ((ListSpace) other).list) && Intrinsics.areEqual(this.unreferenced, ((ListSpace) other).unreferenced);
    }

    @NotNull
    public final IHeapValues<IValue> getAllElement(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        IHeapValues.Builder res = abstractHeapFactory.emptyBuilder();
        Iterable $this$forEach$iv = this.list;
        for (Object element$iv : $this$forEach$iv) {
            res.add((IHeapValues) element$iv);
        }
        IHeapValues it = this.unreferenced;
        if (it != null) {
            res.add(it);
        }
        return res.build();
    }

    @Nullable
    public final IHeapValues<IValue> get(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @Nullable Integer index) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        if (index == null || this.unreferenced != null) {
            return getAllElement(abstractHeapFactory);
        }
        if (index.intValue() >= this.list.size() || index.intValue() < 0) {
            return null;
        }
        return (IHeapValues) this.list.get(index.intValue());
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData
    @NotNull
    public IData<IValue> cloneAndReNewObjects(@NotNull IReNew<IValue> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        IData.Builder<IValue> builderBuilder2 = builder2();
        builderBuilder2.cloneAndReNewObjects(iReNew);
        return builderBuilder2.build2();
    }
}
