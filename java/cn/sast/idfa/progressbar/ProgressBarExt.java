package cn.sast.idfa.progressbar;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.ProcessInfoView;
import java.text.DecimalFormat;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarBuilder;
import me.tongfei.progressbar.ProgressBarRenderer;
import me.tongfei.progressbar.ProgressBarStyle;
import me.tongfei.progressbar.ProgressState;
import org.jetbrains.annotations.NotNull;

/* compiled from: ProgressBarExt.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001:\u0001\u001aB\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J;\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u0019R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u001b"}, d2 = {"Lcn/sast/idfa/progressbar/ProgressBarExt;", "", "updateIntervalMillis", "", "maxProgressLength", "<init>", "(II)V", "getUpdateIntervalMillis", "()I", "setUpdateIntervalMillis", "(I)V", "getMaxProgressLength", "setMaxProgressLength", "getProgressBar", "Lme/tongfei/progressbar/ProgressBar;", "unitName", "", "initialMax", "", "style", "Lme/tongfei/progressbar/ProgressBarStyle;", "builder", "Lkotlin/Function1;", "Lme/tongfei/progressbar/ProgressBarBuilder;", "", "Lkotlin/ExtensionFunctionType;", "DefaultProcessInfoRenderer", "corax-idfa-framework"})
/* loaded from: ProgressBarExt.class */
public final class ProgressBarExt {
    private int updateIntervalMillis;
    private int maxProgressLength;

    public ProgressBarExt() {
        this(0, 0, 3, null);
    }

    public ProgressBarExt(int updateIntervalMillis, int maxProgressLength) {
        this.updateIntervalMillis = updateIntervalMillis;
        this.maxProgressLength = maxProgressLength;
    }

    public /* synthetic */ ProgressBarExt(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 500 : i, (i3 & 2) != 0 ? 120 : i2);
    }

    public final int getUpdateIntervalMillis() {
        return this.updateIntervalMillis;
    }

    public final void setUpdateIntervalMillis(int i) {
        this.updateIntervalMillis = i;
    }

    public final int getMaxProgressLength() {
        return this.maxProgressLength;
    }

    public final void setMaxProgressLength(int i) {
        this.maxProgressLength = i;
    }

    /* compiled from: ProgressBarExt.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018��2\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\u0005\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u0006\u0010\u001f\u001a\u00020\u001dR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0005\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006 "}, d2 = {"Lcn/sast/idfa/progressbar/ProgressBarExt$DefaultProcessInfoRenderer;", "Lcn/sast/idfa/analysis/ProcessInfoView;", "Lme/tongfei/progressbar/ProgressBarRenderer;", "progressBar", "Lme/tongfei/progressbar/ProgressBar;", "render", "<init>", "(Lme/tongfei/progressbar/ProgressBar;Lme/tongfei/progressbar/ProgressBarRenderer;)V", "getProgressBar", "()Lme/tongfei/progressbar/ProgressBar;", "splitLines", "", "getSplitLines", "()J", "setSplitLines", "(J)V", "counter", "Ljava/util/concurrent/atomic/AtomicLong;", "getCounter", "()Ljava/util/concurrent/atomic/AtomicLong;", "extraMessage", "", "getExtraMessage", "()Ljava/lang/String;", "progressState", "Lme/tongfei/progressbar/ProgressState;", "maxLength", "", "step", "", "refresh", "close", "corax-idfa-framework"})
    /* loaded from: ProgressBarExt$DefaultProcessInfoRenderer.class */
    public static class DefaultProcessInfoRenderer extends ProcessInfoView implements ProgressBarRenderer {

        @NotNull
        private final ProgressBar progressBar;

        @NotNull
        private final ProgressBarRenderer render;
        private long splitLines;

        @NotNull
        private final AtomicLong counter;

        @NotNull
        public final ProgressBar getProgressBar() {
            return this.progressBar;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultProcessInfoRenderer(@NotNull ProgressBar progressBar, @NotNull ProgressBarRenderer render) {
            super(null, 1, null);
            Intrinsics.checkNotNullParameter(progressBar, "progressBar");
            Intrinsics.checkNotNullParameter(render, "render");
            this.progressBar = progressBar;
            this.render = render;
            this.counter = new AtomicLong(-1L);
        }

        public final long getSplitLines() {
            return this.splitLines;
        }

        public final void setSplitLines(long j) {
            this.splitLines = j;
        }

        @NotNull
        public final AtomicLong getCounter() {
            return this.counter;
        }

        @NotNull
        public String getExtraMessage() {
            return " " + getJvmMemoryUsageText() + " " + getFreeMemoryText() + " " + getCpuLoadText();
        }

        @NotNull
        public String render(@NotNull ProgressState progressState, int maxLength) {
            String strRender;
            Intrinsics.checkNotNullParameter(progressState, "progressState");
            synchronized (this.progressBar) {
                this.progressBar.setExtraMessage(getExtraMessage() + this.progressBar.getExtraMessage());
                strRender = this.render.render(progressState, maxLength);
                this.progressBar.setExtraMessage("");
            }
            Intrinsics.checkNotNullExpressionValue(strRender, "synchronized(...)");
            return strRender;
        }

        public final void step() {
            if (this.splitLines <= 0) {
                this.progressBar.step();
                return;
            }
            long progressCount = this.progressBar.getMax() / this.splitLines;
            long curCount = this.counter.incrementAndGet();
            boolean needRefresh = false;
            synchronized (this.progressBar) {
                this.progressBar.step();
                if ((progressCount != 0 && curCount % progressCount == 0) || curCount == this.progressBar.getMax()) {
                    this.progressBar.setExtraMessage("\n");
                    needRefresh = true;
                }
                Unit unit = Unit.INSTANCE;
            }
            if (needRefresh) {
                this.progressBar.refresh();
            }
        }

        public final void refresh() {
            this.progressBar.refresh();
        }

        public final void close() {
            this.progressBar.pause();
            this.progressBar.close();
        }
    }

    public static /* synthetic */ ProgressBar getProgressBar$default(ProgressBarExt progressBarExt, String str, long j, ProgressBarStyle progressBarStyle, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            progressBarStyle = ProgressBarStyle.COLORFUL_UNICODE_BLOCK;
        }
        if ((i & 8) != 0) {
            function1 = ProgressBarExt::getProgressBar$lambda$0;
        }
        return progressBarExt.getProgressBar(str, j, progressBarStyle, function1);
    }

    private static final Unit getProgressBar$lambda$0(ProgressBarBuilder progressBarBuilder) {
        Intrinsics.checkNotNullParameter(progressBarBuilder, "<this>");
        return Unit.INSTANCE;
    }

    @NotNull
    public final ProgressBar getProgressBar(@NotNull String unitName, long initialMax, @NotNull ProgressBarStyle style, @NotNull Function1<? super ProgressBarBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(unitName, "unitName");
        Intrinsics.checkNotNullParameter(style, "style");
        Intrinsics.checkNotNullParameter(function1, "builder");
        ProgressBarBuilder pbb = new ProgressBarBuilder().setTaskName(">").showSpeed(new DecimalFormat("#.##")).setStyle(style).showSpeed().setInitialMax(initialMax).setUnit(unitName, 1L).setSpeedUnit(ChronoUnit.SECONDS).setMaxRenderedLength(this.maxProgressLength).continuousUpdate().setUpdateIntervalMillis(this.updateIntervalMillis);
        function1.invoke(pbb);
        ProgressBar progressBarBuild = pbb.build();
        Intrinsics.checkNotNullExpressionValue(progressBarBuild, "build(...)");
        return progressBarBuild;
    }
}
