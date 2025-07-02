package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IValue;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Type;

/* compiled from: FactValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\b\u0016\u0018��*\b\b��\u0010\u0001*\u00020\u00022\u00020\u0002B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bB%\b\u0016\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\fJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028��0��2\u0006\u0010\u001c\u001a\u00020\tJ\u0013\u0010\u001d\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0096\u0002J\b\u0010 \u001a\u00020!H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028��0\u0006¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012¨\u0006\""}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/PhantomField;", "V", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "type", "Lsoot/Type;", "base", "Lcn/sast/dataflow/interprocedural/analysis/IFieldManager;", "accessPath", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "<init>", "(Lsoot/Type;Lcn/sast/dataflow/interprocedural/analysis/IFieldManager;Lkotlinx/collections/immutable/PersistentList;)V", "(Lcn/sast/dataflow/interprocedural/analysis/IFieldManager;Lkotlinx/collections/immutable/PersistentList;)V", "getType", "()Lsoot/Type;", "getBase", "()Lcn/sast/dataflow/interprocedural/analysis/IFieldManager;", "getAccessPath", "()Lkotlinx/collections/immutable/PersistentList;", "toString", "", "typeIsConcrete", "", "copy", "isNullConstant", "getKind", "Lcn/sast/dataflow/interprocedural/analysis/IValue$Kind;", "getPhantomField", "field", "equals", "other", "", "hashCode", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nFactValues.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FactValues.kt\ncn/sast/dataflow/interprocedural/analysis/PhantomField\n+ 2 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n*L\n1#1,589:1\n49#2:590\n50#2:591\n*S KotlinDebug\n*F\n+ 1 FactValues.kt\ncn/sast/dataflow/interprocedural/analysis/PhantomField\n*L\n295#1:590\n298#1:591\n*E\n"})
/* loaded from: PhantomField.class */
public class PhantomField<V extends IValue> implements IValue {

    @NotNull
    private final Type type;

    @NotNull
    private final IFieldManager<V> base;

    @NotNull
    private final PersistentList<JFieldType> accessPath;

    /* JADX WARN: Multi-variable type inference failed */
    public PhantomField(@NotNull Type type, @NotNull IFieldManager<V> iFieldManager, @NotNull PersistentList<? extends JFieldType> persistentList) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(iFieldManager, "base");
        Intrinsics.checkNotNullParameter(persistentList, "accessPath");
        this.type = type;
        this.base = iFieldManager;
        this.accessPath = persistentList;
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
    public Type getType() {
        return this.type;
    }

    @NotNull
    public final IFieldManager<V> getBase() {
        return this.base;
    }

    @NotNull
    public final PersistentList<JFieldType> getAccessPath() {
        return this.accessPath;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public PhantomField(@NotNull IFieldManager<V> iFieldManager, @NotNull PersistentList<? extends JFieldType> persistentList) {
        Intrinsics.checkNotNullParameter(iFieldManager, "base");
        Intrinsics.checkNotNullParameter(persistentList, "accessPath");
        FieldUtil fieldUtil = FieldUtil.INSTANCE;
        JFieldType field$iv = (JFieldType) CollectionsKt.last((List) persistentList);
        this(field$iv.getType(), iFieldManager, persistentList);
    }

    @NotNull
    public String toString() {
        return "PhantomObject_" + getType() + "_" + this.base + "." + CollectionsKt.joinToString$default(this.accessPath, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, PhantomField::toString$lambda$0, 31, (Object) null);
    }

    private static final CharSequence toString$lambda$0(JFieldType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FieldUtil fieldUtil = FieldUtil.INSTANCE;
        return it.getName();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean typeIsConcrete() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public IValue copy(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new PhantomField(type, this.base, this.accessPath);
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

    @NotNull
    public final PhantomField<V> getPhantomField(@NotNull JFieldType field) {
        Intrinsics.checkNotNullParameter(field, "field");
        return this.base.getPhantomField(this.accessPath.add(field));
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return other != null && (other instanceof PhantomField) && Intrinsics.areEqual(this.base, ((PhantomField) other).base) && Intrinsics.areEqual(this.accessPath, ((PhantomField) other).accessPath) && Intrinsics.areEqual(getType(), ((PhantomField) other).getType());
    }

    public int hashCode() {
        int result = (31 * 1) + this.base.hashCode();
        return (31 * ((31 * result) + this.accessPath.hashCode())) + getType().hashCode();
    }
}
