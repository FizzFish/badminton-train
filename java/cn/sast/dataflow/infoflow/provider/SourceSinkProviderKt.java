package cn.sast.dataflow.infoflow.provider;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.MGlobal;
import com.feysh.corax.config.api.MLocal;
import com.feysh.corax.config.api.MParameter;
import com.feysh.corax.config.api.MReturn;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootMethod;
import soot.Type;

/* compiled from: SourceSinkProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u001c\n��\n\u0002\u0010!\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\u001a\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\"\u0016\u0010��\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"nullArg", "", "", "baseType", "Lsoot/Type;", "Lsoot/SootMethod;", "loc", "Lcom/feysh/corax/config/api/MLocal;", "corax-data-flow"})
/* loaded from: SourceSinkProviderKt.class */
public final class SourceSinkProviderKt {

    @Nullable
    private static final List<String> nullArg = null;

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    @Nullable
    public static final Type baseType(@NotNull SootMethod $this$baseType, @NotNull MLocal loc) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter($this$baseType, "<this>");
        Intrinsics.checkNotNullParameter(loc, "loc");
        if (loc instanceof MParameter) {
            if (((MParameter) loc).getIndex() == -1) {
                return $this$baseType.getDeclaringClass().getType();
            }
            if (((MParameter) loc).getIndex() >= $this$baseType.getParameterCount()) {
                return null;
            }
            return $this$baseType.getParameterType(((MParameter) loc).getIndex());
        }
        if (loc instanceof MReturn) {
            return $this$baseType.getReturnType();
        }
        if (Intrinsics.areEqual(loc, MGlobal.INSTANCE)) {
            return Scene.v().getObjectType();
        }
        throw new NoWhenBranchMatchedException();
    }
}
