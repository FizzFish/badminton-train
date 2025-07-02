package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IFieldManager;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Type;
import soot.Unit;

/* compiled from: FactValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0016\u0018�� *2\u00020\u00012\b\u0012\u0004\u0012\u00020��0\u0002:\u0001*B#\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0016\u001a\u00020��H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J \u0010\u001f\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020��0\u00150\u0012H\u0016J\u0006\u0010'\u001a\u00020!J\u0013\u0010(\u001a\u00020\u001a2\b\u0010)\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R&\u0010\u0011\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020��0\u00150\u0012X\u0082\u0004¢\u0006\u0002\n��R\u001e\u0010 \u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006+"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/SummaryValue;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/IFieldManager;", "type", "Lsoot/Type;", "unit", "Lsoot/Unit;", "special", "", "<init>", "(Lsoot/Type;Lsoot/Unit;Ljava/lang/Object;)V", "getType", "()Lsoot/Type;", "getUnit", "()Lsoot/Unit;", "getSpecial", "()Ljava/lang/Object;", "fieldObjects", "", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "Lcn/sast/dataflow/interprocedural/analysis/PhantomField;", "clone", "toString", "", "typeIsConcrete", "", "copy", "isNullConstant", "getKind", "Lcn/sast/dataflow/interprocedural/analysis/IValue$Kind;", "getPhantomFieldMap", "hashCode", "", "getHashCode", "()Ljava/lang/Integer;", "setHashCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "hash", "equals", "other", "Companion", "corax-data-flow"})
/* loaded from: SummaryValue.class */
public class SummaryValue implements IValue, IFieldManager<SummaryValue> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Type type;

    @NotNull
    private final Unit unit;

    @Nullable
    private final Object special;

    @NotNull
    private final Map<PersistentList<JFieldType>, PhantomField<SummaryValue>> fieldObjects;

    @Nullable
    private Integer hashCode;

    public /* synthetic */ SummaryValue(Type type, Unit unit, Object special, DefaultConstructorMarker $constructor_marker) {
        this(type, unit, special);
    }

    private SummaryValue(Type type, Unit unit, Object special) {
        this.type = type;
        this.unit = unit;
        this.special = special;
        this.fieldObjects = new LinkedHashMap();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean isNormal() {
        return IValue.DefaultImpls.isNormal(this);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @Nullable
    public Boolean objectEqual(@NotNull IValue b) {
        return IValue.DefaultImpls.objectEqual(this, b);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public PhantomField<SummaryValue> getPhantomField(@NotNull JFieldType field) {
        return IFieldManager.DefaultImpls.getPhantomField(this, field);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public PhantomField<SummaryValue> getPhantomField(@NotNull PersistentList<? extends JFieldType> persistentList) {
        return IFieldManager.DefaultImpls.getPhantomField(this, persistentList);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public Type getType() {
        return this.type;
    }

    @NotNull
    public final Unit getUnit() {
        return this.unit;
    }

    @Nullable
    public final Object getSpecial() {
        return this.special;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public SummaryValue clone() {
        return new SummaryValue(getType(), this.unit, this.special);
    }

    @NotNull
    public String toString() {
        return "Summary_" + getType() + "_" + this.unit + "_" + this.special + "_(" + hashCode() + ")";
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean typeIsConcrete() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue copy(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new SummaryValue(type, this.unit, this.special);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean isNullConstant() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue.Kind getKind() {
        return IValue.Kind.Summary;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public Map<PersistentList<JFieldType>, PhantomField<SummaryValue>> getPhantomFieldMap() {
        return this.fieldObjects;
    }

    /* compiled from: FactValues.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001¨\u0006\u000b"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/SummaryValue$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/analysis/SummaryValue;", "ty", "Lsoot/Type;", "unit", "Lsoot/Unit;", "special", "corax-data-flow"})
    /* loaded from: SummaryValue$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final SummaryValue v(@NotNull Type ty, @NotNull Unit unit, @Nullable Object special) {
            Intrinsics.checkNotNullParameter(ty, "ty");
            Intrinsics.checkNotNullParameter(unit, "unit");
            return new SummaryValue(ty, unit, special, null);
        }
    }

    @Nullable
    public final Integer getHashCode() {
        return this.hashCode;
    }

    public final void setHashCode(@Nullable Integer num) {
        this.hashCode = num;
    }

    public final int hash() {
        if (!FactValuesKt.getLeastExpr()) {
            return System.identityHashCode(this);
        }
        int result = (31 * 1) + this.unit.hashCode();
        int i = 31 * result;
        Object obj = this.special;
        int result2 = i + (obj != null ? obj.hashCode() : 0);
        return (31 * result2) + getType().hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (!FactValuesKt.getLeastExpr()) {
            return this == other;
        }
        if (this == other) {
            return true;
        }
        if (other != null && (other instanceof SummaryValue)) {
            return (this.hashCode == null || ((SummaryValue) other).hashCode == null || Intrinsics.areEqual(this.hashCode, ((SummaryValue) other).hashCode)) && Intrinsics.areEqual(this.unit, ((SummaryValue) other).unit) && Intrinsics.areEqual(this.special, ((SummaryValue) other).special) && Intrinsics.areEqual(getType(), ((SummaryValue) other).getType());
        }
        return false;
    }

    public int hashCode() {
        Integer h = this.hashCode;
        if (h == null) {
            h = Integer.valueOf(hash());
            this.hashCode = h;
        }
        return h.intValue();
    }
}
