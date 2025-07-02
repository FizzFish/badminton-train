package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CustomRepeatingTimer.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0016\u0018��2\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0006J\b\u0010\u0011\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcn/sast/common/CustomRepeatingTimer;", "", "interval", "", "action", "Lkotlin/Function0;", "", "<init>", "(JLkotlin/jvm/functions/Function0;)V", "timer", "Ljava/util/Timer;", "isRepeats", "", "()Z", "setRepeats", "(Z)V", "start", "stop", "corax-api"})
/* loaded from: CustomRepeatingTimer.class */
public class CustomRepeatingTimer {
    private final long interval;

    @NotNull
    private final Function0<Unit> action;

    @Nullable
    private Timer timer;
    private boolean isRepeats;

    public CustomRepeatingTimer(long interval, @NotNull Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "action");
        this.interval = interval;
        this.action = function0;
        this.isRepeats = true;
    }

    public final boolean isRepeats() {
        return this.isRepeats;
    }

    public final void setRepeats(boolean z) {
        this.isRepeats = z;
    }

    public final void start() {
        Timer $this$start_u24lambda_u240 = new Timer();
        $this$start_u24lambda_u240.scheduleAtFixedRate(new TimerTask() { // from class: cn.sast.common.CustomRepeatingTimer$start$1$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                this.this$0.action.invoke();
                if (!this.this$0.isRepeats()) {
                    cancel();
                }
            }
        }, this.interval, this.interval);
        this.timer = $this$start_u24lambda_u240;
    }

    public void stop() {
        Timer it = this.timer;
        if (it != null) {
            it.cancel();
            it.purge();
            this.timer = null;
        }
    }
}
