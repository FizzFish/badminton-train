package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;

/* compiled from: ExtSettings.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0010\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0006\"\u000e\u0010��\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n��\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n��\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��\"\u000e\u0010\b\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n��¨\u0006\t"}, d2 = {"logger", "Lmu/KLogger;", "coraxHomePath", "", "getCoraxHomePath", "()Ljava/lang/String;", "SETTING_FILE_NAME", "defaultSettingsPath", "defaultKeyForSettingsPath", "corax-api"})
@SourceDebugExtension({"SMAP\nExtSettings.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ExtSettings.kt\ncn/sast/api/config/ExtSettingsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,118:1\n1#2:119\n*E\n"})
/* loaded from: ExtSettingsKt.class */
public final class ExtSettingsKt {

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ExtSettingsKt::logger$lambda$0);

    @NotNull
    private static final String coraxHomePath = System.getProperty("user.home") + File.separatorChar + ".corax";

    @NotNull
    private static final String SETTING_FILE_NAME = "settings.properties";

    @NotNull
    private static final String defaultSettingsPath;

    @NotNull
    private static final String defaultKeyForSettingsPath = "corax.settings.path";

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00be  */
    static {
        /*
            mu.KotlinLogging r0 = mu.KotlinLogging.INSTANCE
            void r1 = cn.sast.api.config.ExtSettingsKt::logger$lambda$0
            mu.KLogger r0 = r0.logger(r1)
            cn.sast.api.config.ExtSettingsKt.logger = r0
            java.lang.String r0 = "user.home"
            java.lang.String r0 = java.lang.System.getProperty(r0)
            char r1 = java.io.File.separatorChar
            java.lang.String r0 = r0 + r1 + ".corax"
            cn.sast.api.config.ExtSettingsKt.coraxHomePath = r0
            r0 = 2
            java.nio.file.Path[] r0 = new java.nio.file.Path[r0]
            r7 = r0
            r0 = r7
            r1 = 0
            java.lang.String r2 = "."
            r3 = 0
            java.lang.String[] r3 = new java.lang.String[r3]
            java.nio.file.Path r2 = java.nio.file.Paths.get(r2, r3)
            r3 = r2
            java.lang.String r4 = "get(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r0[r1] = r2
            r0 = r7
            r1 = 1
            cn.sast.common.OS r2 = cn.sast.common.OS.INSTANCE
            java.nio.file.Path r2 = r2.getJarBinPath()
            r3 = r2
            if (r3 == 0) goto L49
            java.nio.file.Path r2 = r2.getParent()
            goto L4b
        L49:
            r2 = 0
        L4b:
            r0[r1] = r2
            r0 = r7
            java.util.List r0 = kotlin.collections.CollectionsKt.listOfNotNull(r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
            r8 = r0
        L59:
            r0 = r8
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto Laa
            r0 = r8
            java.lang.Object r0 = r0.next()
            java.nio.file.Path r0 = (java.nio.file.Path) r0
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r9
            java.lang.String r1 = "settings.properties"
            java.nio.file.Path r0 = r0.resolve(r1)
            r11 = r0
            r0 = r11
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r12
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r0 = r12
            r1 = 0
            java.nio.file.LinkOption[] r1 = new java.nio.file.LinkOption[r1]
            r2 = r1
            int r2 = r2.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)
            java.nio.file.LinkOption[] r1 = (java.nio.file.LinkOption[]) r1
            boolean r0 = java.nio.file.Files.exists(r0, r1)
            if (r0 == 0) goto L9f
            r0 = r11
            goto La0
        L9f:
            r0 = 0
        La0:
            r9 = r0
            r0 = r9
            if (r0 == 0) goto L59
            r0 = r9
            goto Lab
        Laa:
            r0 = 0
        Lab:
            r6 = r0
            r0 = r6
            if (r0 == 0) goto Lbe
        Lb1:
            r0 = r6
            java.lang.String r0 = r0.toString()
            r7 = r0
            r0 = r7
            if (r0 == 0) goto Lbe
            r0 = r7
            goto Lde
        Lbe:
            java.lang.String r0 = cn.sast.api.config.ExtSettingsKt.coraxHomePath
            r1 = 0
            java.lang.String[] r1 = new java.lang.String[r1]
            java.nio.file.Path r0 = java.nio.file.Paths.get(r0, r1)
            r1 = r0
            java.lang.String r2 = "get(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r1 = "settings.properties"
            java.nio.file.Path r0 = r0.resolve(r1)
            r1 = r0
            java.lang.String r2 = "resolve(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.String r0 = r0.toString()
        Lde:
            cn.sast.api.config.ExtSettingsKt.defaultSettingsPath = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.config.ExtSettingsKt.m15clinit():void");
    }

    @NotNull
    public static final String getCoraxHomePath() {
        return coraxHomePath;
    }
}
