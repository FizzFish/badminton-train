package cn.sast.framework.engine;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.report.ProjectFileLocator;
import cn.sast.framework.result.IMissingSummaryReporter;
import cn.sast.framework.result.ResultCollector;
import com.feysh.corax.cache.analysis.SootInfoCache;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: IPAnalysisEngine.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
@SourceDebugExtension({"SMAP\nIPAnalysisEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IPAnalysisEngine.kt\ncn/sast/framework/engine/IPAnalysisEngine$analyze$2$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Logging.kt\norg/utbot/common/LoggingKt\n*L\n1#1,344:1\n1#2:345\n49#3,24:346\n*S KotlinDebug\n*F\n+ 1 IPAnalysisEngine.kt\ncn/sast/framework/engine/IPAnalysisEngine$analyze$2$1\n*L\n289#1:346,24\n*E\n"})
/* loaded from: IPAnalysisEngine$analyze$2$1.class */
final class IPAnalysisEngine$analyze$2$1<T> implements FlowCollector {
    final /* synthetic */ SootCtx $soot;
    final /* synthetic */ IPAnalysisEngine this$0;
    final /* synthetic */ ProjectFileLocator $locator;
    final /* synthetic */ SootInfoCache $info;
    final /* synthetic */ ResultCollector $result;
    final /* synthetic */ IMissingSummaryReporter $missWrapper;

    IPAnalysisEngine$analyze$2$1(SootCtx $soot, IPAnalysisEngine $receiver, ProjectFileLocator $locator, SootInfoCache $info, ResultCollector $result, IMissingSummaryReporter $missWrapper) {
        this.$soot = $soot;
        this.this$0 = $receiver;
        this.$locator = $locator;
        this.$info = $info;
        this.$result = $result;
        this.$missWrapper = $missWrapper;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object emit(cn.sast.framework.entries.IEntryPointProvider.AnalyzeTask r17, kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            Method dump skipped, instructions count: 672
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.IPAnalysisEngine$analyze$2$1.emit(cn.sast.framework.entries.IEntryPointProvider$AnalyzeTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
        return emit((IEntryPointProvider.AnalyzeTask) value, (Continuation<? super Unit>) $completion);
    }
}
