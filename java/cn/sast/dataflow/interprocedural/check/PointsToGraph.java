package cn.sast.dataflow.interprocedural.check;

import cn.sast.api.config.ExtSettings;
import cn.sast.dataflow.interprocedural.analysis.AbstractHeapFactory;
import cn.sast.dataflow.interprocedural.analysis.CallStackContext;
import cn.sast.dataflow.interprocedural.analysis.ConstVal;
import cn.sast.dataflow.interprocedural.analysis.FactValuesHashingStrategy;
import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.IProblemIteratorTerminal;
import cn.sast.dataflow.interprocedural.analysis.IVGlobal;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.analysis.PointsToGraphAbstract;
import cn.sast.dataflow.interprocedural.analysis.SummaryValue;
import cn.sast.dataflow.interprocedural.analysis.WideningPrimitive;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.Context;
import cn.sast.idfa.analysis.FixPointStatus;
import gnu.trove.set.hash.TCustomHashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.annotations.NotNull;
import soot.PrimType;
import soot.SootMethod;
import soot.Unit;

/* compiled from: PointsToGraph.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018��2\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0001:\u0001 B\u0087\u0001\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\r0\u000b\u00120\u0010\u000e\u001a,\u0012\b\u0012\u00060\u0002j\u0002`\u0003\u0012\u001e\u0012\u001c\u0012\b\u0012\u00060\fj\u0002`\u000f\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u00100\u000b0\u000b\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u0017H\u0016J>\u0010\u0018\u001a\u00020\u00192\"\u0010\u001a\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u000e\u0012\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001d0\u001b2\u0010\u0010\u001e\u001a\f\u0012\b\u0012\u00060\u0002j\u0002`\u00030\u001fH\u0016¨\u0006!"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PointsToGraph;", "Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstract;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "callStack", "Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "slots", "Lkotlinx/collections/immutable/PersistentMap;", "", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "heap", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "calledMethods", "Lkotlinx/collections/immutable/PersistentSet;", "Lsoot/SootMethod;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentSet;)V", "builder", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "hasChange", "Lcn/sast/idfa/analysis/FixPointStatus;", "context", "Lcn/sast/idfa/analysis/Context;", "Lsoot/Unit;", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "new", "Lcn/sast/dataflow/interprocedural/analysis/IProblemIteratorTerminal;", "IntegerInterval", "corax-data-flow"})
/* loaded from: PointsToGraph.class */
public class PointsToGraph extends PointsToGraphAbstract<IValue> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PointsToGraph(@NotNull AbstractHeapFactory<IValue> abstractHeapFactory, @NotNull IVGlobal vg, @NotNull CallStackContext callStack, @NotNull PersistentMap<Object, ? extends IHeapValues<IValue>> persistentMap, @NotNull PersistentMap<IValue, ? extends PersistentMap<Object, ? extends IData<IValue>>> persistentMap2, @NotNull PersistentSet<? extends SootMethod> persistentSet) {
        super(abstractHeapFactory, vg, callStack, persistentMap, persistentMap2, persistentSet);
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(vg, "vg");
        Intrinsics.checkNotNullParameter(callStack, "callStack");
        Intrinsics.checkNotNullParameter(persistentMap, "slots");
        Intrinsics.checkNotNullParameter(persistentMap2, "heap");
        Intrinsics.checkNotNullParameter(persistentSet, "calledMethods");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact
    @NotNull
    public IFact.Builder<IValue> builder() {
        return new PointsToGraphBuilder(this, getHf(), getVg(), getCallStack(), getSlots().builder(), getHeap().builder(), getCalledMethods().builder());
    }

    /* compiled from: PointsToGraph.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\b\b\u0002\u0018�� \r2\u00020\u0001:\u0001\rB\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PointsToGraph$IntegerInterval;", "", "constants", "", "Lcn/sast/dataflow/interprocedural/analysis/ConstVal;", "widening", "", "<init>", "(Ljava/util/Collection;Z)V", "getConstants", "()Ljava/util/Collection;", "getWidening", "()Z", "Companion", "corax-data-flow"})
    /* loaded from: PointsToGraph$IntegerInterval.class */
    private static final class IntegerInterval {

        @NotNull
        public static final Companion Companion = new Companion(null);

        @NotNull
        private final Collection<ConstVal> constants;
        private final boolean widening;

        public IntegerInterval(@NotNull Collection<ConstVal> collection, boolean widening) {
            Intrinsics.checkNotNullParameter(collection, "constants");
            this.constants = collection;
            this.widening = widening;
        }

        @NotNull
        public final Collection<ConstVal> getConstants() {
            return this.constants;
        }

        public final boolean getWidening() {
            return this.widening;
        }

        /* compiled from: PointsToGraph.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u00060\bj\u0002`\t0\u0007H\u0086\u0002¨\u0006\n"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PointsToGraph$IntegerInterval$Companion;", "", "<init>", "()V", "invoke", "Lcn/sast/dataflow/interprocedural/check/PointsToGraph$IntegerInterval;", "numbers", "", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/V;", "corax-data-flow"})
        @SourceDebugExtension({"SMAP\nPointsToGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/PointsToGraph$IntegerInterval$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,612:1\n808#2,11:613\n1755#2,3:624\n*S KotlinDebug\n*F\n+ 1 PointsToGraph.kt\ncn/sast/dataflow/interprocedural/check/PointsToGraph$IntegerInterval$Companion\n*L\n63#1:613,11\n63#1:624,3\n*E\n"})
        /* loaded from: PointsToGraph$IntegerInterval$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @NotNull
            public final IntegerInterval invoke(@NotNull Collection<? extends IValue> collection) {
                boolean z;
                Intrinsics.checkNotNullParameter(collection, "numbers");
                Collection<? extends IValue> $this$filterIsInstance$iv = collection;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filterIsInstance$iv) {
                    if (element$iv$iv instanceof ConstVal) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                ArrayList arrayList = (List) destination$iv$iv;
                Collection<? extends IValue> $this$any$iv = collection;
                if (!$this$any$iv.isEmpty()) {
                    Iterator it = $this$any$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        Object element$iv = it.next();
                        IValue it2 = (IValue) element$iv;
                        SummaryValue summaryValue = it2 instanceof SummaryValue ? (SummaryValue) it2 : null;
                        if (Intrinsics.areEqual(summaryValue != null ? summaryValue.getSpecial() : null, WideningPrimitive.INSTANCE)) {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = false;
                }
                return new IntegerInterval(arrayList, z);
            }
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IProblemIteratorTerminal
    @NotNull
    public FixPointStatus hasChange(@NotNull Context<SootMethod, Unit, IFact<IValue>> context, @NotNull IProblemIteratorTerminal<IValue> iProblemIteratorTerminal) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(iProblemIteratorTerminal, "new");
        if (!(iProblemIteratorTerminal instanceof IFact)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!((IFact) iProblemIteratorTerminal).isValid()) {
            return FixPointStatus.HasChange;
        }
        if (!(iProblemIteratorTerminal instanceof PointsToGraphAbstract)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (this == iProblemIteratorTerminal) {
            return FixPointStatus.Fixpoint;
        }
        PersistentMap<Object, IHeapValues<IValue>> slots = getSlots();
        Set newSlots = ((PointsToGraphAbstract) iProblemIteratorTerminal).mo173getSlots();
        if (!Intrinsics.areEqual(slots, newSlots)) {
            return FixPointStatus.HasChange;
        }
        boolean needWiden = false;
        for (Object local : slots) {
            IHeapValues oldTarget = getTargets(local);
            IHeapValues newTarget = ((PointsToGraphAbstract) iProblemIteratorTerminal).getTargets(local);
            Collection valueOld = oldTarget.getValues();
            Collection valueNew = newTarget.getValues();
            IValue iValue = (IValue) CollectionsKt.firstOrNull(valueOld);
            if ((iValue != null ? iValue.getType() : null) instanceof PrimType) {
                IntegerInterval oldInterval = IntegerInterval.Companion.invoke(valueOld);
                IntegerInterval newInterval = IntegerInterval.Companion.invoke(valueNew);
                if (oldInterval.getWidening() != newInterval.getWidening()) {
                    return FixPointStatus.HasChange;
                }
                if (oldInterval.getWidening()) {
                    continue;
                } else {
                    int max = ExtSettings.INSTANCE.getDataFlowIteratorIsFixPointSizeLimit();
                    if (max > 0 && (newInterval.getConstants().size() > max || oldInterval.getConstants().size() > max)) {
                        needWiden = true;
                    }
                }
            }
            TCustomHashSet hashSet = new TCustomHashSet(FactValuesHashingStrategy.Companion.getINSTANCE());
            hashSet.addAll(valueOld);
            boolean change = hashSet.addAll(valueNew);
            if (change) {
                if (needWiden) {
                    return FixPointStatus.NeedWideningOperators;
                }
                return FixPointStatus.HasChange;
            }
        }
        return FixPointStatus.Fixpoint;
    }
}
