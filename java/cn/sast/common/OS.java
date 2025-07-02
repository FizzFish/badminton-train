package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.PathsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.optionals.OptionalsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.PathUtil;

/* compiled from: OS.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J/\u0010#\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010$2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\b\b\u0002\u0010%\u001a\u00020\u000b¢\u0006\u0002\u0010&R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n��\u001a\u0004\b\n\u0010\fR\u001d\u0010\r\u001a\u0004\u0018\u00010\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0013\u001a\u0004\u0018\u00010\u00148FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0012\u001a\u0004\b\u0015\u0010\u0016R\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0013\u0010 \u001a\u0004\u0018\u00010\u001a8F¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0019\u0010'\u001a\n\u0012\u0004\u0012\u00020)\u0018\u00010(¢\u0006\b\n��\u001a\u0004\b*\u0010+¨\u0006,"}, d2 = {"Lcn/sast/common/OS;", "", "<init>", "()V", "maxThreadNum", "", "getMaxThreadNum", "()I", "setMaxThreadNum", "(I)V", "isWindows", "", "()Z", "binaryUrl", "Ljava/net/URL;", "getBinaryUrl", "()Ljava/net/URL;", "binaryUrl$delegate", "Lkotlin/Lazy;", "jarBinPath", "Ljava/nio/file/Path;", "getJarBinPath", "()Ljava/nio/file/Path;", "jarBinPath$delegate", "args", "", "", "getArgs", "()[Ljava/lang/String;", "setArgs", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "javaExecutableFilePath", "getJavaExecutableFilePath", "()Ljava/lang/String;", "getCommandLine", "", "jvmOptions", "([Ljava/lang/String;Z)Ljava/util/List;", "posixFilePermissions", "", "Ljava/nio/file/attribute/PosixFilePermission;", "getPosixFilePermissions", "()Ljava/util/Set;", "corax-api"})
@SourceDebugExtension({"SMAP\nOS.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OS.kt\ncn/sast/common/OS\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,63:1\n1#2:64\n*E\n"})
/* loaded from: OS.class */
public final class OS {

    @NotNull
    public static final OS INSTANCE = new OS();
    private static int maxThreadNum = Runtime.getRuntime().availableProcessors();
    private static final boolean isWindows;

    @NotNull
    private static final Lazy binaryUrl$delegate;

    @NotNull
    private static final Lazy jarBinPath$delegate;

    @NotNull
    private static String[] args;

    @Nullable
    private static final Set<PosixFilePermission> posixFilePermissions;

    private OS() {
    }

    public final int getMaxThreadNum() {
        return maxThreadNum;
    }

    public final void setMaxThreadNum(int i) {
        maxThreadNum = i;
    }

    static {
        String property = System.getProperty("os.name");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = property.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        isWindows = StringsKt.contains$default(lowerCase, "windows", false, 2, (Object) null);
        binaryUrl$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, OS::binaryUrl_delegate$lambda$0);
        jarBinPath$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, OS::jarBinPath_delegate$lambda$3);
        args = new String[0];
        OS os = INSTANCE;
        posixFilePermissions = isWindows ? null : PosixFilePermissions.fromString("rwxr--r--");
    }

    public final boolean isWindows() {
        return isWindows;
    }

    @Nullable
    public final URL getBinaryUrl() {
        return (URL) binaryUrl$delegate.getValue();
    }

    private static final URL binaryUrl_delegate$lambda$0() {
        URL loc;
        ProtectionDomain protectionDomain = OS.class.getProtectionDomain();
        if (protectionDomain == null) {
            return null;
        }
        CodeSource codeSource = protectionDomain.getCodeSource();
        if (codeSource == null || (loc = codeSource.getLocation()) == null) {
            return null;
        }
        String locString = loc.toString();
        Intrinsics.checkNotNullExpressionValue(locString, "toString(...)");
        if (StringsKt.endsWith$default(locString, "BOOT-INF/classes!/", false, 2, (Object) null) || StringsKt.endsWith$default(locString, "BOOT-INF/classes", false, 2, (Object) null)) {
            IResource classLoc = Resource.INSTANCE.of(ResourceKt.uriOf(StringsKt.removeSuffix(locString, "!/")));
            return PathUtil.INSTANCE.toURL(classLoc.getSchemePath());
        }
        return loc;
    }

    @Nullable
    public final Path getJarBinPath() {
        return (Path) jarBinPath$delegate.getValue();
    }

    private static final Path jarBinPath_delegate$lambda$3() {
        URL url = INSTANCE.getBinaryUrl();
        if (url == null) {
            return null;
        }
        Path it = Path.of(url.toURI());
        Intrinsics.checkNotNull(it);
        if (PathExtensionsKt.isRegularFile(it) && StringsKt.endsWith$default(PathsKt.getName(it), ".jar", false, 2, (Object) null)) {
            return it;
        }
        return null;
    }

    @NotNull
    public final String[] getArgs() {
        return args;
    }

    public final void setArgs(@NotNull String[] strArr) {
        Intrinsics.checkNotNullParameter(strArr, "<set-?>");
        args = strArr;
    }

    @Nullable
    public final String getJavaExecutableFilePath() {
        Optional optionalCommand = ProcessHandle.current().info().command();
        Intrinsics.checkNotNullExpressionValue(optionalCommand, "command(...)");
        return (String) OptionalsKt.getOrNull(optionalCommand);
    }

    public static /* synthetic */ List getCommandLine$default(OS os, String[] strArr, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            strArr = null;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return os.getCommandLine(strArr, z);
    }

    @Nullable
    public final List<String> getCommandLine(@Nullable String[] args2, boolean jvmOptions) {
        List list;
        String it;
        List it2;
        String javaExecutableFilePath = getJavaExecutableFilePath();
        if (javaExecutableFilePath == null) {
            return null;
        }
        try {
            List r = new ArrayList();
            r.add(javaExecutableFilePath);
            if (jvmOptions) {
                RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
                if (runtimeMXBean != null && (it2 = runtimeMXBean.getInputArguments()) != null) {
                    r.addAll(it2);
                }
            }
            URL binaryUrl = getBinaryUrl();
            if (binaryUrl != null && (it = binaryUrl.getPath()) != null) {
                r.add("-cp");
                r.add(it);
                List list2 = r;
                String[] strArr = args2;
                if (strArr == null) {
                    OS os = INSTANCE;
                    strArr = args;
                }
                CollectionsKt.addAll(list2, strArr);
            }
            list = r;
        } catch (Throwable th) {
            list = null;
        }
        return list;
    }

    @Nullable
    public final Set<PosixFilePermission> getPosixFilePermissions() {
        return posixFilePermissions;
    }
}
