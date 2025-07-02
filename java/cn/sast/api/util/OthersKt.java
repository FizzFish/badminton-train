package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.IMethodMatch;
import com.feysh.corax.config.api.baseimpl.MatchUtilsKt;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootClass;
import soot.SootMethod;

/* compiled from: Others.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��8\n��\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\t\u001a\u00020\n*\u0006\u0012\u0002\b\u00030\u000b\u001a\u001c\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u001a\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u000f\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b��\u0010\u0003\"\u0015\u0010��\u001a\u00020\u0001*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b��\u0010\u0005\"\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0003\"\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0003¨\u0006\u0015"}, d2 = {"isDummy", "", "Lsoot/SootMethod;", "(Lsoot/SootMethod;)Z", "Lsoot/SootClass;", "(Lsoot/SootClass;)Z", "isSyntheticComponent", "skipPathSensitive", "getSkipPathSensitive", "asInputStream", "Ljava/io/InputStream;", "Lkotlin/reflect/KClass;", "printMilliseconds", "", "message", "", "body", "Lkotlin/Function0;", "methodSignatureToMatcher", "Lcom/feysh/corax/config/api/IMethodMatch;", "signature", "corax-api"})
@SourceDebugExtension({"SMAP\nOthers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Others.kt\ncn/sast/api/util/OthersKt\n+ 2 Timing.kt\nkotlin/system/TimingKt\n*L\n1#1,48:1\n17#2,6:49\n*S KotlinDebug\n*F\n+ 1 Others.kt\ncn/sast/api/util/OthersKt\n*L\n36#1:49,6\n*E\n"})
/* loaded from: OthersKt.class */
public final class OthersKt {
    public static final boolean isDummy(@NotNull SootMethod $this$isDummy) {
        Intrinsics.checkNotNullParameter($this$isDummy, "<this>");
        if (!Intrinsics.areEqual($this$isDummy.getName(), "dummyMainMethod")) {
            SootClass declaringClass = $this$isDummy.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            if (!isDummy(declaringClass)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isDummy(@NotNull SootClass $this$isDummy) {
        Intrinsics.checkNotNullParameter($this$isDummy, "<this>");
        String name = $this$isDummy.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return StringsKt.contains(name, "dummy", true);
    }

    public static final boolean isSyntheticComponent(@NotNull SootMethod $this$isSyntheticComponent) {
        Intrinsics.checkNotNullParameter($this$isSyntheticComponent, "<this>");
        String name = $this$isSyntheticComponent.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return StringsKt.contains(name, "synthetic", true) || isDummy($this$isSyntheticComponent);
    }

    public static final boolean isSyntheticComponent(@NotNull SootClass $this$isSyntheticComponent) {
        Intrinsics.checkNotNullParameter($this$isSyntheticComponent, "<this>");
        String name = $this$isSyntheticComponent.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return StringsKt.contains(name, "synthetic", true) || isDummy($this$isSyntheticComponent);
    }

    public static final boolean getSkipPathSensitive(@NotNull SootMethod $this$skipPathSensitive) {
        Intrinsics.checkNotNullParameter($this$skipPathSensitive, "<this>");
        return isDummy($this$skipPathSensitive) || isSyntheticComponent($this$skipPathSensitive);
    }

    @NotNull
    public static final InputStream asInputStream(@NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        InputStream resourceAsStream = JvmClassMappingKt.getJavaClass(kClass).getResourceAsStream("/" + StringsKt.replace$default(SootUtilsKt.getClassName(kClass), '.', '/', false, 4, (Object) null) + ".class");
        Intrinsics.checkNotNull(resourceAsStream);
        return resourceAsStream;
    }

    public static final void printMilliseconds(@NotNull String message, @NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(function0, "body");
        long start$iv = System.currentTimeMillis();
        function0.invoke();
        long time = System.currentTimeMillis() - start$iv;
        System.out.println((Object) (message + ": " + time + " ms"));
    }

    @Nullable
    public static final IMethodMatch methodSignatureToMatcher(@NotNull String signature) {
        Intrinsics.checkNotNullParameter(signature, "signature");
        if (StringsKt.startsWith$default(signature, "<", false, 2, (Object) null) && StringsKt.endsWith$default(signature, ">", false, 2, (Object) null)) {
            return MatchUtilsKt.matchSoot(signature);
        }
        if (StringsKt.contains$default(signature, ":", false, 2, (Object) null)) {
            return MatchUtilsKt.matchSimpleSig(signature);
        }
        return null;
    }
}
