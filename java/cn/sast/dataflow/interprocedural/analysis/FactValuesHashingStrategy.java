package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import gnu.trove.strategy.HashingStrategy;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: FactValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018�� \f2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\fB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0019\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0096\u0002¨\u0006\r"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/FactValuesHashingStrategy;", "Lgnu/trove/strategy/HashingStrategy;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "<init>", "()V", "computeHashCode", "", "v", "equals", "", "a", "b", "Companion", "corax-data-flow"})
/* loaded from: FactValuesHashingStrategy.class */
public class FactValuesHashingStrategy implements HashingStrategy<IValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final FactValuesHashingStrategy INSTANCE = new FactValuesHashingStrategy();

    public int computeHashCode(@NotNull IValue v) {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v instanceof AnyNewValue) {
            return Objects.hash(((AnyNewValue) v).getNewExpr());
        }
        if (v instanceof ConstVal) {
            return Objects.hash(((ConstVal) v).getV());
        }
        if (v instanceof SummaryValue) {
            return Objects.hash(((SummaryValue) v).getType(), ((SummaryValue) v).getUnit(), ((SummaryValue) v).getSpecial());
        }
        if (v instanceof EntryParam) {
            return Objects.hash(((EntryParam) v).getMethod(), Integer.valueOf(((EntryParam) v).getParamIndex()));
        }
        if (v instanceof GlobalStaticObject) {
            return ((GlobalStaticObject) v).hashCode();
        }
        if (v instanceof PhantomField) {
            return Objects.hash(Integer.valueOf(computeHashCode((IValue) ((PhantomField) v).getBase())), ((PhantomField) v).getAccessPath());
        }
        throw new IllegalStateException(("error type of " + v.getClass() + ": " + v).toString());
    }

    public boolean equals(@NotNull IValue a, @NotNull IValue b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        if (a == b) {
            return true;
        }
        if (computeHashCode(a) != computeHashCode(b)) {
            return false;
        }
        if ((a instanceof AnyNewValue) && (b instanceof AnyNewValue)) {
            return Intrinsics.areEqual(((AnyNewValue) a).getNewExpr(), ((AnyNewValue) b).getNewExpr());
        }
        if ((a instanceof ConstVal) && (b instanceof ConstVal)) {
            return Intrinsics.areEqual(((ConstVal) a).getV(), ((ConstVal) b).getV());
        }
        if ((a instanceof SummaryValue) && (b instanceof SummaryValue)) {
            return Intrinsics.areEqual(((SummaryValue) a).getType(), ((SummaryValue) b).getType()) && Intrinsics.areEqual(((SummaryValue) a).getUnit(), ((SummaryValue) b).getUnit()) && Intrinsics.areEqual(((SummaryValue) a).getSpecial(), ((SummaryValue) b).getSpecial());
        }
        if ((a instanceof EntryParam) && (b instanceof EntryParam)) {
            return Intrinsics.areEqual(((EntryParam) a).getMethod(), ((EntryParam) b).getMethod()) && ((EntryParam) a).getParamIndex() == ((EntryParam) b).getParamIndex();
        }
        if ((a instanceof GlobalStaticObject) && (b instanceof GlobalStaticObject)) {
            return Intrinsics.areEqual(a, b);
        }
        if ((a instanceof PhantomField) && (b instanceof PhantomField)) {
            return equals((IValue) ((PhantomField) a).getBase(), (IValue) ((PhantomField) b).getBase()) && Intrinsics.areEqual(((PhantomField) a).getAccessPath(), ((PhantomField) b).getAccessPath());
        }
        throw new IllegalStateException(("error type of a: \n" + a.getClass() + ": " + a + "    b:\n" + b.getClass() + ": " + b).toString());
    }

    /* compiled from: FactValues.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/FactValuesHashingStrategy$Companion;", "", "<init>", "()V", "INSTANCE", "Lcn/sast/dataflow/interprocedural/analysis/FactValuesHashingStrategy;", "getINSTANCE", "()Lcn/sast/dataflow/interprocedural/analysis/FactValuesHashingStrategy;", "corax-data-flow"})
    /* loaded from: FactValuesHashingStrategy$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final FactValuesHashingStrategy getINSTANCE() {
            return FactValuesHashingStrategy.INSTANCE;
        }
    }
}
