package cn.sast.dataflow.infoflow.provider;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.ClassField;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootClass;
import soot.SootField;
import soot.Type;

/* compiled from: FieldFinder.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"sootField", "Lsoot/SootField;", "Lcom/feysh/corax/config/api/ClassField;", "getSootField", "(Lcom/feysh/corax/config/api/ClassField;)Lsoot/SootField;", "corax-data-flow"})
/* loaded from: FieldFinderKt.class */
public final class FieldFinderKt {
    @Nullable
    public static final SootField getSootField(@NotNull ClassField $this$sootField) {
        SootClass sc;
        Intrinsics.checkNotNullParameter($this$sootField, "<this>");
        String declaringClassType = $this$sootField.getDeclaringClassType();
        if (declaringClassType == null || (sc = Scene.v().getSootClassUnsafe(declaringClassType, false)) == null) {
            return null;
        }
        if ($this$sootField.getFieldType() == null) {
            return sc.getFieldByNameUnsafe($this$sootField.getFieldName());
        }
        Type type = Scene.v().getTypeUnsafe($this$sootField.getFieldType());
        return type == null ? sc.getFieldByNameUnsafe($this$sootField.getFieldName()) : sc.getFieldUnsafe($this$sootField.getFieldName(), type);
    }
}
