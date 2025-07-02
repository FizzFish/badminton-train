package cn.sast.framework.report.coverage;

import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jacoco.core.internal.data.CRC64;
import org.jacoco.core.internal.instr.InstrSupport;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.ClassReader;

/* compiled from: ClassSourceOfSC.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0012\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0006\u0018�� \u00162\u00020\u0001:\u0001\u0016B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcn/sast/framework/report/coverage/ClassSourceInfo;", "", "className", "", "byteArray", "", "classSource", "Lcn/sast/common/IResFile;", "<init>", "(Ljava/lang/String;[BLcn/sast/common/IResFile;)V", "getClassName", "()Ljava/lang/String;", "getByteArray", "()[B", "getClassSource", "()Lcn/sast/common/IResFile;", "jacocoClassId", "", "getJacocoClassId", "()J", "jacocoClassId$delegate", "Lkotlin/Lazy;", "Companion", "corax-framework"})
/* loaded from: ClassSourceInfo.class */
public final class ClassSourceInfo {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String className;

    @NotNull
    private final byte[] byteArray;

    @Nullable
    private final IResFile classSource;

    @NotNull
    private final Lazy jacocoClassId$delegate;

    public ClassSourceInfo(@NotNull String className, @NotNull byte[] byteArray, @Nullable IResFile classSource) {
        Intrinsics.checkNotNullParameter(className, "className");
        Intrinsics.checkNotNullParameter(byteArray, "byteArray");
        this.className = className;
        this.byteArray = byteArray;
        this.classSource = classSource;
        this.jacocoClassId$delegate = LazyKt.lazy(() -> {
            return jacocoClassId_delegate$lambda$0(r1);
        });
    }

    @NotNull
    public final String getClassName() {
        return this.className;
    }

    @NotNull
    public final byte[] getByteArray() {
        return this.byteArray;
    }

    @Nullable
    public final IResFile getClassSource() {
        return this.classSource;
    }

    public final long getJacocoClassId() {
        return ((Number) this.jacocoClassId$delegate.getValue()).longValue();
    }

    private static final long jacocoClassId_delegate$lambda$0(ClassSourceInfo this$0) {
        return CRC64.classId(this$0.byteArray);
    }

    /* compiled from: ClassSourceOfSC.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0002¨\u0006\b"}, d2 = {"Lcn/sast/framework/report/coverage/ClassSourceInfo$Companion;", "", "<init>", "()V", "invoke", "Lcn/sast/framework/report/coverage/ClassSourceInfo;", "byteArray", "", "corax-framework"})
    /* loaded from: ClassSourceInfo$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final ClassSourceInfo invoke(@NotNull byte[] byteArray) {
            Intrinsics.checkNotNullParameter(byteArray, "byteArray");
            ClassReader reader = InstrSupport.classReaderFor(byteArray);
            String className = reader.getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "getClassName(...)");
            return new ClassSourceInfo(StringsKt.replace$default(className, "/", ".", false, 4, (Object) null), byteArray, null);
        }
    }
}
