package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: ResourceImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001e\n��\n\u0002\u0010\"\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0086\u0002J\u0017\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nH\u0086\u0002J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lcn/sast/common/GLB;", "", "<init>", "()V", "allTypes", "", "Lcom/feysh/corax/config/api/CheckType;", "plusAssign", "", "t", "", "get", "", "corax-api"})
@SourceDebugExtension({"SMAP\nResourceImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResourceImpl.kt\ncn/sast/common/GLB\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,730:1\n1#2:731\n*E\n"})
/* loaded from: GLB.class */
public final class GLB {

    @NotNull
    public static final GLB INSTANCE = new GLB();

    @NotNull
    private static final Set<CheckType> allTypes = new LinkedHashSet();

    private GLB() {
    }

    public final void plusAssign(@NotNull CheckType t) {
        Intrinsics.checkNotNullParameter(t, "t");
        synchronized (allTypes) {
            allTypes.add(t);
        }
    }

    public final void plusAssign(@NotNull Collection<? extends CheckType> collection) {
        Intrinsics.checkNotNullParameter(collection, "t");
        synchronized (allTypes) {
            allTypes.addAll(collection);
        }
    }

    @NotNull
    public final Set<CheckType> get() {
        return allTypes;
    }
}
