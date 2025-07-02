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
import soot.Scene;
import soot.Type;

/* compiled from: FactValues.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\b\u0016\u0018��2\u00020\u00012\b\u0012\u0004\u0012\u00020��0\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J \u0010\n\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020��0\t0\u0006H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0017\u001a\u00020\u000eH\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016R&\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020��0\t0\u0006X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/GlobalStaticObject;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "Lcn/sast/dataflow/interprocedural/analysis/IFieldManager;", "<init>", "()V", "fieldObjects", "", "Lkotlinx/collections/immutable/PersistentList;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "Lcn/sast/dataflow/interprocedural/analysis/PhantomField;", "getPhantomFieldMap", "toString", "", "equals", "", "other", "", "hashCode", "", "type", "Lsoot/Type;", "getType", "()Lsoot/Type;", "typeIsConcrete", "isNullConstant", "getKind", "Lcn/sast/dataflow/interprocedural/analysis/IValue$Kind;", "corax-data-flow"})
/* loaded from: GlobalStaticObject.class */
public class GlobalStaticObject implements IValue, IFieldManager<GlobalStaticObject> {

    @NotNull
    private final Map<PersistentList<JFieldType>, PhantomField<GlobalStaticObject>> fieldObjects = new LinkedHashMap();

    @NotNull
    private final Type type;

    public GlobalStaticObject() {
        Type objectType = Scene.v().getObjectType();
        Intrinsics.checkNotNullExpressionValue(objectType, "getObjectType(...)");
        this.type = objectType;
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

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public PhantomField<GlobalStaticObject> getPhantomField(@NotNull JFieldType field) {
        return IFieldManager.DefaultImpls.getPhantomField(this, field);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public PhantomField<GlobalStaticObject> getPhantomField(@NotNull PersistentList<? extends JFieldType> persistentList) {
        return IFieldManager.DefaultImpls.getPhantomField(this, persistentList);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFieldManager
    @NotNull
    public Map<PersistentList<JFieldType>, PhantomField<GlobalStaticObject>> getPhantomFieldMap() {
        return this.fieldObjects;
    }

    @NotNull
    public String toString() {
        return "GlobalStaticObject";
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return other != null && (other instanceof GlobalStaticObject);
    }

    public int hashCode() {
        return 30864;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    @NotNull
    public Type getType() {
        return this.type;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IValue
    public boolean typeIsConcrete() {
        return true;
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
}
