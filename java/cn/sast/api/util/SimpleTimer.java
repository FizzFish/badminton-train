package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Arrays;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SimpleTimer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018�� \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\t\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0010\u001a\u00020\u0005J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u000eJ\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\n\u0010\bR\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��R\u001e\u0010\f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\r\u0010\b¨\u0006\u0017"}, d2 = {"Lcn/sast/api/util/SimpleTimer;", "", "<init>", "()V", "value", "", "elapsedTime", "getElapsedTime", "()J", "start", "getStart", "startTime", "end", "getEnd", "", "stop", "currentElapsedTime", "inSecond", "", "clear", "toString", "", "Companion", "corax-api"})
/* loaded from: SimpleTimer.class */
public final class SimpleTimer {
    private long elapsedTime;
    private long start;
    private long startTime;
    private long end;

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final Logger logger = LogManager.getLogger(SimpleTimer.class);

    public final long getElapsedTime() {
        return this.elapsedTime;
    }

    public final long getStart() {
        return this.start;
    }

    public final long getEnd() {
        return this.end;
    }

    public final void start() {
        this.startTime = PhaseIntervalTimerKt.currentNanoTime();
        if (this.start == 0) {
            this.start = this.startTime;
        }
    }

    public final void stop() {
        long cur = PhaseIntervalTimerKt.currentNanoTime();
        this.elapsedTime += cur - this.startTime;
        this.startTime = 0L;
        this.end = cur;
    }

    public final long currentElapsedTime() {
        return this.elapsedTime + (PhaseIntervalTimerKt.currentNanoTime() - this.startTime);
    }

    public final float inSecond() {
        return this.elapsedTime / 1000.0f;
    }

    public final void clear() {
        this.elapsedTime = 0L;
    }

    @NotNull
    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {Float.valueOf(inSecond())};
        String str = String.format("elapsed time: %.2fs", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    /* compiled from: SimpleTimer.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J1\u0010\b\u001a\u0002H\t\"\u0004\b��\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J$\u0010\b\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007R\u0018\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcn/sast/api/util/SimpleTimer$Companion;", "", "<init>", "()V", "logger", "Lorg/apache/logging/log4j/Logger;", "kotlin.jvm.PlatformType", "Lorg/apache/logging/log4j/Logger;", "runAndCount", "T", "task", "Ljava/util/function/Supplier;", "taskName", "", "level", "Lorg/apache/logging/log4j/Level;", "(Ljava/util/function/Supplier;Ljava/lang/String;Lorg/apache/logging/log4j/Level;)Ljava/lang/Object;", "", "Ljava/lang/Runnable;", "corax-api"})
    /* loaded from: SimpleTimer$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final <T> T runAndCount(@NotNull Supplier<T> supplier, @NotNull String taskName, @Nullable Level level) {
            Intrinsics.checkNotNullParameter(supplier, "task");
            Intrinsics.checkNotNullParameter(taskName, "taskName");
            SimpleTimer.logger.info("{} starts ...", taskName);
            SimpleTimer timer = new SimpleTimer();
            timer.start();
            T t = supplier.get();
            timer.stop();
            Logger logger = SimpleTimer.logger;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = {Float.valueOf(timer.inSecond())};
            String str = String.format("%.2fs", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            logger.log(level, "{} finishes, elapsed time: {}", taskName, str);
            return t;
        }

        public static /* synthetic */ void runAndCount$default(Companion companion, Runnable runnable, String str, Level level, int i, Object obj) {
            if ((i & 4) != 0) {
                level = Level.INFO;
            }
            companion.runAndCount(runnable, str, level);
        }

        @JvmOverloads
        public final void runAndCount(@NotNull final Runnable task, @NotNull String taskName, @Nullable Level level) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(taskName, "taskName");
            runAndCount(new Supplier() { // from class: cn.sast.api.util.SimpleTimer$Companion$runAndCount$1
                @Override // java.util.function.Supplier
                public final Object get() {
                    task.run();
                    return null;
                }
            }, taskName, level);
        }

        @JvmOverloads
        public final void runAndCount(@NotNull Runnable task, @NotNull String taskName) {
            Intrinsics.checkNotNullParameter(task, "task");
            Intrinsics.checkNotNullParameter(taskName, "taskName");
            runAndCount$default(this, task, taskName, null, 4, null);
        }
    }
}
