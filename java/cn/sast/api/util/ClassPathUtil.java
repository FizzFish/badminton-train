package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.File;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ClassPathUtil.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0003\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005J\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcn/sast/api/util/ClassPathUtil;", "", "<init>", "()V", "findCodeBaseInClassPath", "", "codeBaseName", "classPath", "codeBaseNamePattern", "Ljava/util/regex/Pattern;", "javaClassPath", "", "getJavaClassPath", "()[Ljava/lang/String;", "corax-api"})
@SourceDebugExtension({"SMAP\nClassPathUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClassPathUtil.kt\ncn/sast/api/util/ClassPathUtil\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,81:1\n37#2,2:82\n*S KotlinDebug\n*F\n+ 1 ClassPathUtil.kt\ncn/sast/api/util/ClassPathUtil\n*L\n67#1:82,2\n*E\n"})
/* loaded from: ClassPathUtil.class */
public final class ClassPathUtil {

    @NotNull
    public static final ClassPathUtil INSTANCE = new ClassPathUtil();

    private ClassPathUtil() {
    }

    @Nullable
    public final String findCodeBaseInClassPath(@Nonnull @NotNull String codeBaseName, @Nullable String classPath) {
        Intrinsics.checkNotNullParameter(codeBaseName, "codeBaseName");
        if (classPath == null) {
            return null;
        }
        StringTokenizer tok = new StringTokenizer(classPath, File.pathSeparator);
        while (tok.hasMoreTokens()) {
            String t = tok.nextToken();
            File f = new File(t);
            if (Intrinsics.areEqual(f.getName(), codeBaseName)) {
                return t;
            }
        }
        return null;
    }

    @Nullable
    public final String findCodeBaseInClassPath(@NotNull Pattern codeBaseNamePattern, @Nullable String classPath) {
        Intrinsics.checkNotNullParameter(codeBaseNamePattern, "codeBaseNamePattern");
        if (classPath == null) {
            return null;
        }
        StringTokenizer tok = new StringTokenizer(classPath, File.pathSeparator);
        while (tok.hasMoreTokens()) {
            String t = tok.nextToken();
            File f = new File(t);
            Matcher m = codeBaseNamePattern.matcher(f.getName());
            if (m.matches()) {
                return t;
            }
        }
        return null;
    }

    @NotNull
    public final String[] getJavaClassPath() {
        String property = System.getProperty("java.class.path");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
        Collection $this$toTypedArray$iv = StringsKt.split$default(property, new String[]{":"}, false, 0, 6, (Object) null);
        return (String[]) $this$toTypedArray$iv.toArray(new String[0]);
    }
}
