package cn.sast.cli;

import cn.sast.cli.command.FySastCli;
import cn.sast.common.OS;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread;
import java.util.Properties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Application.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\"\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u0006\u0010��\u001a\u00020\u0001\u001a\u0019\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\u0010\n\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��\"\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u000b"}, d2 = {"getVersion", "", "logger", "Lmu/KLogger;", "lastResort", "", "main", "", "args", "", "([Ljava/lang/String;)V", "corax-cli"})
/* loaded from: ApplicationKt.class */
public final class ApplicationKt {

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ApplicationKt::logger$lambda$1);

    @Nullable
    private static byte[] lastResort = new byte[20971520];

    @NotNull
    public static final String getVersion() {
        Properties prop = new Properties();
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("version.properties");
        try {
            InputStream stream = resourceAsStream;
            prop.load(stream);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(resourceAsStream, (Throwable) null);
            String property = prop.getProperty("version");
            Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
            return property;
        } catch (Throwable th) {
            CloseableKt.closeFinally(resourceAsStream, (Throwable) null);
            throw th;
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }

    public static final void main(@NotNull String[] args) {
        Intrinsics.checkNotNullParameter(args, "args");
        OS.INSTANCE.setArgs(args);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: cn.sast.cli.ApplicationKt.main.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread t, Throwable e) {
                String str;
                Intrinsics.checkNotNullParameter(t, "t");
                Intrinsics.checkNotNullParameter(e, "e");
                if (e instanceof OutOfMemoryError) {
                    ApplicationKt.lastResort = null;
                }
                try {
                    System.err.println("Uncaught exception: " + e.getClass() + " in thread " + t.getName() + ": e: " + e.getMessage());
                    if (e instanceof IOException) {
                        String message = e.getMessage();
                        boolean z = message != null && StringsKt.contains(message, "no space left", true);
                        if (z) {
                            e.printStackTrace(System.err);
                            System.exit(2);
                            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
                        }
                    } else {
                        if (e instanceof OutOfMemoryError) {
                            System.exit(10);
                            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
                        }
                        e.printStackTrace(System.err);
                    }
                    throw new RuntimeException(str);
                } finally {
                    System.exit(-1);
                    RuntimeException runtimeException = new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
                }
            }

            public String toString() {
                return "Corax UncaughtExceptionHandler";
            }
        });
        try {
            new FySastCli().main(args);
            System.exit(0);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        } catch (Throwable t) {
            logger.error(t, () -> {
                return main$lambda$2(r2);
            });
            System.exit(1);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }
    }

    private static final Object main$lambda$2(Throwable $t) {
        return "An error occurred: " + $t;
    }
}
