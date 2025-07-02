package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.ClassField;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootField;
import soot.Type;

/* compiled from: IFact.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\bJ\u0013\u0010\u0004\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0006\u001a\u00020\tH\u0086\bJ\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\fH\u0086\bJ\u0011\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\fH\u0086\b¨\u0006\u000f"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/FieldUtil;", "", "<init>", "()V", "of", "Lcn/sast/dataflow/interprocedural/analysis/JSootFieldType;", "field", "Lsoot/SootField;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldNameType;", "Lcom/feysh/corax/config/api/ClassField;", "typeOf", "Lsoot/Type;", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "nameOf", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nIFact.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IFact.kt\ncn/sast/dataflow/interprocedural/analysis/FieldUtil\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,507:1\n1#2:508\n*E\n"})
/* loaded from: FieldUtil.class */
public final class FieldUtil {

    @NotNull
    public static final FieldUtil INSTANCE = new FieldUtil();

    private FieldUtil() {
    }

    @NotNull
    public final JSootFieldType of(@NotNull SootField field) {
        Intrinsics.checkNotNullParameter(field, "field");
        return new JSootFieldType(field);
    }

    @Nullable
    public final JFieldNameType of(@NotNull ClassField field) {
        Intrinsics.checkNotNullParameter(field, "field");
        String it = field.getFieldType();
        Type ty = it != null ? Scene.v().getTypeUnsafe(it, true) : null;
        String fieldName = field.getFieldName();
        Type type = ty;
        if (type == null) {
            type = (Type) Scene.v().getObjectType();
        }
        Type type2 = type;
        Intrinsics.checkNotNull(type2);
        return new JFieldNameType(fieldName, type2);
    }

    @NotNull
    public final Type typeOf(@NotNull JFieldType field) {
        Intrinsics.checkNotNullParameter(field, "field");
        return field.getType();
    }

    @NotNull
    public final String nameOf(@NotNull JFieldType field) {
        Intrinsics.checkNotNullParameter(field, "field");
        return field.getName();
    }
}
