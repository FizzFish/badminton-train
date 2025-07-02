package cn.sast.dataflow.interprocedural.override.lang.util;

import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IReNew;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.ReferenceContext;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: WArrayList.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001B3\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ,\u0010\r\u001a\u00020\u000e2\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00102\u0010\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0012H\u0016J$\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00062\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0010H\u0002J\u001a\u0010\u0014\u001a\u00020\u000e2\u0010\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0016H\u0016J\u0012\u0010\u0017\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0012H\u0016J\u0018\u0010\u0018\u001a\u00020\u000e2\u0010\u0010\u0019\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0006J\u0018\u0010\u001a\u001a\u00020\u000e2\u0010\u0010\u0019\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0006J \u0010\u001b\u001a\u00020\u000e2\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00102\u0006\u0010\u001c\u001a\u00020\u001dJ1\u0010\u001e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00062\u0010\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\u0002\u0010!J\u0019\u0010\"\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u0005HÆ\u0003J\u0015\u0010#\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\bHÂ\u0003J9\u0010$\u001a\u00020��2\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\bHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020 HÖ\u0001J\t\u0010*\u001a\u00020+HÖ\u0001R!\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0007\u001a\u000e\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n��¨\u0006,"}, d2 = {"Lcn/sast/dataflow/interprocedural/override/lang/util/ListSpaceBuilder;", "Lcn/sast/dataflow/interprocedural/analysis/IData$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "list", "Lkotlinx/collections/immutable/PersistentList$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "unreferenced", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;", "<init>", "(Lkotlinx/collections/immutable/PersistentList$Builder;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues$Builder;)V", "getList", "()Lkotlinx/collections/immutable/PersistentList$Builder;", "union", "", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "that", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "getAllElement", "cloneAndReNewObjects", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "build", "add", "value", "clear", "addAll", "b", "Lcn/sast/dataflow/interprocedural/override/lang/util/ListSpace;", "remove", "index", "", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Ljava/lang/Integer;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nWArrayList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WArrayList.kt\ncn/sast/dataflow/interprocedural/override/lang/util/ListSpaceBuilder\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,344:1\n1863#2,2:345\n1#3:347\n*S KotlinDebug\n*F\n+ 1 WArrayList.kt\ncn/sast/dataflow/interprocedural/override/lang/util/ListSpaceBuilder\n*L\n281#1:345,2\n*E\n"})
/* loaded from: ListSpaceBuilder.class */
public final class ListSpaceBuilder implements IData.Builder<IValue> {

    @NotNull
    private final PersistentList.Builder<IHeapValues<IValue>> list;

    @Nullable
    private IHeapValues.Builder<IValue> unreferenced;

    @NotNull
    public final PersistentList.Builder<IHeapValues<IValue>> component1() {
        return this.list;
    }

    private final IHeapValues.Builder<IValue> component2() {
        return this.unreferenced;
    }

    @NotNull
    public final ListSpaceBuilder copy(@NotNull PersistentList.Builder<IHeapValues<IValue>> builder, @Nullable IHeapValues.Builder<IValue> builder2) {
        Intrinsics.checkNotNullParameter(builder, "list");
        return new ListSpaceBuilder(builder, builder2);
    }

    public static /* synthetic */ ListSpaceBuilder copy$default(ListSpaceBuilder listSpaceBuilder, PersistentList.Builder builder, IHeapValues.Builder builder2, int i, Object obj) {
        if ((i & 1) != 0) {
            builder = listSpaceBuilder.list;
        }
        if ((i & 2) != 0) {
            builder2 = listSpaceBuilder.unreferenced;
        }
        return listSpaceBuilder.copy(builder, builder2);
    }

    @NotNull
    public String toString() {
        return "ListSpaceBuilder(list=" + this.list + ", unreferenced=" + this.unreferenced + ")";
    }

    public int hashCode() {
        int result = this.list.hashCode();
        return (result * 31) + (this.unreferenced == null ? 0 : this.unreferenced.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ListSpaceBuilder)) {
            return false;
        }
        ListSpaceBuilder listSpaceBuilder = (ListSpaceBuilder) other;
        return Intrinsics.areEqual(this.list, listSpaceBuilder.list) && Intrinsics.areEqual(this.unreferenced, listSpaceBuilder.unreferenced);
    }

    public ListSpaceBuilder(@NotNull PersistentList.Builder<IHeapValues<IValue>> builder, @Nullable IHeapValues.Builder<IValue> builder2) {
        Intrinsics.checkNotNullParameter(builder, "list");
        this.list = builder;
        this.unreferenced = builder2;
    }

    @NotNull
    public final PersistentList.Builder<IHeapValues<IValue>> getList() {
        return this.list;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    public void union(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull IData<IValue> iData) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(iData, "that");
        if (!(iData instanceof ListSpace)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this.unreferenced != null) {
            if (((ListSpace) iData).getUnreferenced() != null) {
                IHeapValues.Builder<IValue> builder = this.unreferenced;
                Intrinsics.checkNotNull(builder);
                builder.add(((ListSpace) iData).getUnreferenced());
                return;
            } else {
                IHeapValues.Builder<IValue> builder2 = this.unreferenced;
                Intrinsics.checkNotNull(builder2);
                builder2.add(((ListSpace) iData).getAllElement(abstractHeapFactory));
                return;
            }
        }
        if (((ListSpace) iData).getUnreferenced() != null) {
            this.unreferenced = ((ListSpace) iData).getUnreferenced().builder();
            IHeapValues.Builder<IValue> builder3 = this.unreferenced;
            Intrinsics.checkNotNull(builder3);
            builder3.add(getAllElement(abstractHeapFactory));
            this.list.clear();
            return;
        }
        if (this.list.size() != ((ListSpace) iData).getList().size()) {
            IHeapValues.Builder unreferenced = getAllElement(abstractHeapFactory).builder();
            unreferenced.add(((ListSpace) iData).getAllElement(abstractHeapFactory));
            this.unreferenced = unreferenced;
            this.list.clear();
            return;
        }
        int i = 0;
        for (IHeapValues v : ((ListSpace) iData).getList()) {
            int i2 = i;
            i++;
            IHeapValues e = (IHeapValues) this.list.get(i2);
            this.list.set(i2, e.plus(v));
        }
    }

    private final IHeapValues<IValue> getAllElement(AbstractHeapFactory<IValue> abstractHeapFactory) {
        IHeapValues.Builder res = abstractHeapFactory.emptyBuilder();
        Iterable $this$forEach$iv = this.list;
        for (Object element$iv : $this$forEach$iv) {
            res.add((IHeapValues) element$iv);
        }
        IHeapValues.Builder it = this.unreferenced;
        if (it != null) {
            res.add(it.build());
        }
        return res.build();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    public void cloneAndReNewObjects(@NotNull IReNew<IValue> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        int i = 0;
        for (IHeapValues v : this.list.build()) {
            int k = i;
            i++;
            this.list.set(k, v.cloneAndReNewObjects(iReNew.context(new ReferenceContext.KVPosition(Integer.valueOf(k)))));
        }
        IHeapValues.Builder<IValue> builder = this.unreferenced;
        if (builder != null) {
            builder.cloneAndReNewObjects(iReNew.context(ReferenceContext.KVUnreferenced.INSTANCE));
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IData.Builder
    @NotNull
    /* renamed from: build */
    public IData<IValue> build2() {
        IHeapValues.Builder<IValue> builder = this.unreferenced;
        if (builder != null && builder.isEmpty()) {
            builder = null;
        }
        IHeapValues.Builder<IValue> builder2 = builder;
        return new ListSpace(this.list.build(), builder2 != null ? builder2.build() : null);
    }

    public final void add(@NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "value");
        if (this.unreferenced != null) {
            IHeapValues.Builder<IValue> builder = this.unreferenced;
            Intrinsics.checkNotNull(builder);
            builder.add(iHeapValues);
            return;
        }
        Boolean.valueOf(this.list.add(iHeapValues));
    }

    public final void clear(@NotNull IHeapValues<IValue> iHeapValues) {
        Intrinsics.checkNotNullParameter(iHeapValues, "value");
        this.unreferenced = null;
        this.list.clear();
    }

    public final void addAll(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull ListSpace b) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(b, "b");
        if (this.unreferenced != null || b.getUnreferenced() != null) {
            union(abstractHeapFactory, b);
        } else {
            this.list.addAll(b.getList());
        }
    }

    @NotNull
    public final IHeapValues<IValue> remove(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @Nullable Integer index) {
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        if (index == null) {
            if (this.unreferenced == null) {
                this.unreferenced = abstractHeapFactory.emptyBuilder();
            }
            IHeapValues.Builder<IValue> builder = this.unreferenced;
            Intrinsics.checkNotNull(builder);
            builder.add(getAllElement(abstractHeapFactory));
            this.list.clear();
            IHeapValues.Builder<IValue> builder2 = this.unreferenced;
            Intrinsics.checkNotNull(builder2);
            return builder2.build();
        }
        if (this.unreferenced == null) {
            if (index.intValue() >= this.list.size() || index.intValue() < 0) {
                return getAllElement(abstractHeapFactory);
            }
            return (IHeapValues) this.list.remove(index.intValue());
        }
        return getAllElement(abstractHeapFactory);
    }
}
