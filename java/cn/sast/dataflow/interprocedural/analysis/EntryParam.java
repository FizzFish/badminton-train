package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IFieldManager;
import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;
import soot.Type;

/* compiled from: FactValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\b\u0016\u0018��2\u00020\u00012\b\u0012\u0004\u0012\u00020��0\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB\u0019\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\u000bJ \u0010\u0017\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020��0\u00160\u0013H\u0016J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\b\u0010\u001c\u001a\u00020\bH\u0016J\b\u0010\u001d\u001a\u00020\u0019H\u0016J\u0010\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u001f\u001a\u00020\u0019H\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R&\u0010\u0012\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020��0\u00160\u0013X\u0082\u0004¢\u0006\u0002\n��¨\u0006$"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/EntryParam;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/IFieldManager;", "type", "Lsoot/Type;", "method", "Lsoot/SootMethod;", "paramIndex", "", "<init>", "(Lsoot/Type;Lsoot/SootMethod;I)V", "(Lsoot/SootMethod;I)V", "getType", "()Lsoot/Type;", "getMethod", "()Lsoot/SootMethod;", "getParamIndex", "()I", "fieldObjects", "", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "Lcn/sast/dataflow/interprocedural/analysis/PhantomField;", "getPhantomFieldMap", "equals", "", "other", "", "hashCode", "typeIsConcrete", "copy", "isNullConstant", "getKind", "Lcn/sast/dataflow/interprocedural/analysis/IValue$Kind;", "toString", "", "corax-data-flow"})
/* loaded from: EntryParam.class */
public class EntryParam implements IValue, IFieldManager<EntryParam> {

    @NotNull
    private final Type type;

    @NotNull
    private final SootMethod method;
    private final int paramIndex;

    @NotNull
    private final Map<PersistentList<JFieldType>, PhantomField<EntryParam>> fieldObjects;

    public EntryParam(@NotNull Type type, @NotNull SootMethod method, int paramIndex) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(method, "method");
        this.type = type;
        this.method = method;
        this.paramIndex = paramIndex;
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

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue clone() {
        return IValue.DefaultImpls.clone(this);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public PhantomField<EntryParam> getPhantomField(@NotNull JFieldType field) {
        return IFieldManager.DefaultImpls.getPhantomField(this, field);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public PhantomField<EntryParam> getPhantomField(@NotNull PersistentList<? extends JFieldType> persistentList) {
        return IFieldManager.DefaultImpls.getPhantomField(this, persistentList);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public Type getType() {
        return this.type;
    }

    @NotNull
    public final SootMethod getMethod() {
        return this.method;
    }

    public final int getParamIndex() {
        return this.paramIndex;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public EntryParam(@NotNull SootMethod method, int paramIndex) {
        Type type;
        Intrinsics.checkNotNullParameter(method, "method");
        if (paramIndex != -1) {
            type = method.getParameterType(paramIndex);
        } else {
            type = method.getDeclaringClass().getType();
        }
        Type type2 = type;
        Intrinsics.checkNotNull(type2);
        this(type2, method, paramIndex);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public Map<PersistentList<JFieldType>, PhantomField<EntryParam>> getPhantomFieldMap() {
        return this.fieldObjects;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return other != null && (other instanceof EntryParam) && Intrinsics.areEqual(this.method, ((EntryParam) other).method) && this.paramIndex == ((EntryParam) other).paramIndex && Intrinsics.areEqual(getType(), ((EntryParam) other).getType());
    }

    public int hashCode() {
        int result = (31 * 1) + this.method.hashCode();
        return (31 * ((31 * result) + Integer.hashCode(this.paramIndex))) + getType().hashCode();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean typeIsConcrete() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue copy(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new EntryParam(type, this.method, this.paramIndex);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean isNullConstant() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue.Kind getKind() {
        return IValue.Kind.Entry;
    }

    @NotNull
    public String toString() {
        return "param<" + this.paramIndex + ">_" + getType();
    }
}
