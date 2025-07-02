package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Type;
import soot.jimple.Constant;
import soot.jimple.NullConstant;

/* compiled from: FactValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \u001e2\u00020\u0001:\u0001\u001eB\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0013\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u000fH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ConstVal;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "v", "Lsoot/jimple/Constant;", "type", "Lsoot/Type;", "<init>", "(Lsoot/jimple/Constant;Lsoot/Type;)V", "getV", "()Lsoot/jimple/Constant;", "getType", "()Lsoot/Type;", "toString", "", "typeIsConcrete", "", "equals", "other", "", "hash", "", "getHash", "()Ljava/lang/Integer;", "setHash", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "hashCode", "isNullConstant", "getKind", "Lcn/sast/dataflow/interprocedural/analysis/IValue$Kind;", "Companion", "corax-data-flow"})
/* loaded from: ConstVal.class */
public final class ConstVal implements IValue {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final Constant v;

    @NotNull
    private final Type type;

    @Nullable
    private Integer hash;

    public /* synthetic */ ConstVal(Constant v, Type type, DefaultConstructorMarker $constructor_marker) {
        this(v, type);
    }

    private ConstVal(Constant v, Type type) {
        this.v = v;
        this.type = type;
    }

    /* synthetic */ ConstVal(Constant constant, Type type, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(constant, (i & 2) != 0 ? constant.getType() : type);
    }

    @NotNull
    public final Constant getV() {
        return this.v;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public Type getType() {
        return this.type;
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

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue clone() {
        return IValue.DefaultImpls.clone(this);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue copy(@NotNull Type type) {
        return IValue.DefaultImpls.copy(this, type);
    }

    @NotNull
    public String toString() {
        return "const_" + getType() + "_" + this.v;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean typeIsConcrete() {
        return true;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return other != null && (other instanceof ConstVal) && Intrinsics.areEqual(this.v, ((ConstVal) other).v) && Intrinsics.areEqual(getType(), ((ConstVal) other).getType());
    }

    @Nullable
    public final Integer getHash() {
        return this.hash;
    }

    public final void setHash(@Nullable Integer num) {
        this.hash = num;
    }

    public int hashCode() {
        Integer result = this.hash;
        if (result == null) {
            result = Integer.valueOf((31 * Integer.valueOf(this.v.hashCode()).intValue()) + getType().hashCode());
            this.hash = result;
        }
        return result.intValue();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean isNullConstant() {
        return this.v instanceof NullConstant;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue.Kind getKind() {
        return IValue.Kind.Normal;
    }

    /* compiled from: FactValues.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/ConstVal$Companion;", "", "<init>", "()V", "v", "Lcn/sast/dataflow/interprocedural/analysis/ConstVal;", "Lsoot/jimple/Constant;", "ty", "Lsoot/Type;", "corax-data-flow"})
    /* loaded from: ConstVal$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final ConstVal v(@NotNull Constant v, @NotNull Type ty) {
            Intrinsics.checkNotNullParameter(v, "v");
            Intrinsics.checkNotNullParameter(ty, "ty");
            return new ConstVal(v, ty, null);
        }
    }
}
