package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.annotations.NotNull;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b��\u0018�� \u000b2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001\u000bB+\u0012\"\b\u0002\u0010\u0004\u001a\u001c\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016¨\u0006\f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/HeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapValues;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "map", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "<init>", "(Lkotlinx/collections/immutable/PersistentMap;)V", "builder", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesBuilder;", "Companion", "corax-data-flow"})
/* loaded from: HeapValues.class */
public final class HeapValues extends AbstractHeapValues<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    public HeapValues() {
        this(null, 1, null);
    }

    public /* synthetic */ HeapValues(PersistentMap persistentMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ExtensionsKt.persistentHashMapOf() : persistentMap);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeapValues(@NotNull PersistentMap<IValue, ? extends CompanionV<IValue>> persistentMap) {
        super(persistentMap, null);
        Intrinsics.checkNotNullParameter(persistentMap, "map");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IHeapValues
    @NotNull
    public HeapValuesBuilder builder() {
        return new HeapValuesBuilder(this, null, 2, null);
    }

    /* compiled from: PointsToGraphAbstract.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0004\u001a\u00020\u0005H��¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/HeapValues$Companion;", "", "<init>", "()V", "empty", "Lcn/sast/dataflow/interprocedural/analysis/HeapValues;", "empty$corax_data_flow", "corax-data-flow"})
    /* loaded from: HeapValues$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final HeapValues empty$corax_data_flow() {
            return new HeapValues(ExtensionsKt.persistentHashMapOf());
        }
    }
}
