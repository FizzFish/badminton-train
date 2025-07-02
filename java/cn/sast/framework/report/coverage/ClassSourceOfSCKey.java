package cn.sast.framework.report.coverage;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.AnalysisKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClassSourceOfSC.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\t\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\n\u001a\u00020��2\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcn/sast/framework/report/coverage/ClassSourceOfSCKey;", "Lcom/feysh/corax/cache/AnalysisKey;", "Lcn/sast/framework/report/coverage/ClassSourceInfo;", "className", "", "<init>", "(Ljava/lang/String;)V", "getClassName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "corax-framework"})
/* loaded from: ClassSourceOfSCKey.class */
public final class ClassSourceOfSCKey extends AnalysisKey<ClassSourceInfo> {

    @NotNull
    private final String className;

    @NotNull
    public final String component1() {
        return this.className;
    }

    @NotNull
    public final ClassSourceOfSCKey copy(@NotNull String className) {
        Intrinsics.checkNotNullParameter(className, "className");
        return new ClassSourceOfSCKey(className);
    }

    public static /* synthetic */ ClassSourceOfSCKey copy$default(ClassSourceOfSCKey classSourceOfSCKey, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = classSourceOfSCKey.className;
        }
        return classSourceOfSCKey.copy(str);
    }

    @NotNull
    public String toString() {
        return "ClassSourceOfSCKey(className=" + this.className + ")";
    }

    public int hashCode() {
        return this.className.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ClassSourceOfSCKey) && Intrinsics.areEqual(this.className, ((ClassSourceOfSCKey) other).className);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassSourceOfSCKey(@NotNull String className) {
        super(ClassSourceOfSCFactory.INSTANCE.getKey());
        Intrinsics.checkNotNullParameter(className, "className");
        this.className = className;
    }

    @NotNull
    public final String getClassName() {
        return this.className;
    }
}
