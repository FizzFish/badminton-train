package cn.sast.dataflow.interprocedural.check.checker;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.TaintProperty;
import com.feysh.corax.config.api.ViaProperty;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: CheckerModeling.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��2\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0015\u0010��\u001a\u00060\u0001j\u0002`\u0002¢\u0006\b\n��\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00060\u0001j\u0002`\u0002¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0013\u001a\u00060\u0001j\u0002`\u0002¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0004*\u0016\u0010\u0007\"\b\u0012\u0004\u0012\u00020\t0\b2\b\u0012\u0004\u0012\u00020\t0\b*\u0016\u0010\n\"\b\u0012\u0004\u0012\u00020\u000b0\b2\b\u0012\u0004\u0012\u00020\u000b0\b*&\u0010\f\"\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u0002`\u000f0\r2\u0012\u0012\u0004\u0012\u00020\u000e\u0012\b\u0012\u00060\u0010j\u0002`\u000f0\r*\u0014\b\u0002\u0010\u0011\"\u0006\u0012\u0002\b\u00030\b2\u0006\u0012\u0002\b\u00030\b*\u0016\u0010\u0012\"\b\u0012\u0004\u0012\u00020\u00010\b2\b\u0012\u0004\u0012\u00020\u00010\b¨\u0006\u0015"}, d2 = {"KeyTaintProperty", "", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "getKeyTaintProperty", "()Ljava/lang/Object;", "KeyViaProperty", "getKeyViaProperty", "ViaPropertyType", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementSet;", "Lcom/feysh/corax/config/api/IViaType;", "TaintPropertyType", "Lcom/feysh/corax/config/api/ITaintType;", "AttributePropertyType", "Lcn/sast/dataflow/interprocedural/check/heapimpl/ImmutableElementHashMap;", "", "Lcn/sast/dataflow/interprocedural/analysis/V;", "Lcn/sast/dataflow/interprocedural/analysis/IValue;", "SetType", "SetTypeAny", "KeyAttribute", "getKeyAttribute", "corax-data-flow"})
/* loaded from: CheckerModelingKt.class */
public final class CheckerModelingKt {

    @NotNull
    private static final Object KeyTaintProperty = TaintProperty.INSTANCE;

    @NotNull
    private static final Object KeyViaProperty = ViaProperty.INSTANCE;

    @NotNull
    private static final Object KeyAttribute = new Object() { // from class: cn.sast.dataflow.interprocedural.check.checker.CheckerModelingKt$KeyAttribute$1
        public String toString() {
            return "Attribute";
        }
    };

    @NotNull
    public static final Object getKeyTaintProperty() {
        return KeyTaintProperty;
    }

    @NotNull
    public static final Object getKeyViaProperty() {
        return KeyViaProperty;
    }

    @NotNull
    public static final Object getKeyAttribute() {
        return KeyAttribute;
    }
}
