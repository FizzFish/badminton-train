package cn.sast.framework.report;

import cn.sast.api.report.BugPathPosition;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.report.Report;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.report.ReportConverter;
import com.feysh.corax.config.api.report.Region;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import me.tongfei.progressbar.ProgressBar;

/* compiled from: ReportConverter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\n"}, d2 = {"<anonymous>", "", "<destruct>", "Lkotlin/Pair;", "", "", "Lcn/sast/api/report/Report;"})
@DebugMetadata(f = "ReportConverter.kt", l = {332}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$4"}, n = {"fileName", "reportsNew", "consumer"}, m = "invokeSuspend", c = "cn.sast.framework.report.ReportConverter$flush$2$5$worker$1")
@SourceDebugExtension({"SMAP\nReportConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReportConverter.kt\ncn/sast/framework/report/ReportConverter$flush$2$5$worker$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,391:1\n1557#2:392\n1628#2,3:393\n1053#2:396\n1863#2,2:397\n*S KotlinDebug\n*F\n+ 1 ReportConverter.kt\ncn/sast/framework/report/ReportConverter$flush$2$5$worker$1\n*L\n308#1:392\n308#1:393,3\n327#1:396\n329#1:397,2\n*E\n"})
/* loaded from: ReportConverter$flush$2$5$worker$1.class */
final class ReportConverter$flush$2$5$worker$1 extends SuspendLambda implements Function2<Pair<? extends String, ? extends List<? extends Report>>, Continuation<? super Unit>, Object> {
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object L$0;
    final /* synthetic */ List<IReportConsumer> $consumers;
    final /* synthetic */ ProgressBar $progressBar;
    final /* synthetic */ ConcurrentMap<IBugResInfo, Unit> $deCompileUnitMap;
    final /* synthetic */ IProjectFileLocator $locatorNew;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    ReportConverter$flush$2$5$worker$1(List<? extends IReportConsumer> list, ProgressBar $progressBar, ConcurrentMap<IBugResInfo, Unit> concurrentMap, IProjectFileLocator $locatorNew, Continuation<? super ReportConverter$flush$2$5$worker$1> continuation) {
        super(2, continuation);
        this.$consumers = list;
        this.$progressBar = $progressBar;
        this.$deCompileUnitMap = concurrentMap;
        this.$locatorNew = $locatorNew;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> reportConverter$flush$2$5$worker$1 = new ReportConverter$flush$2$5$worker$1(this.$consumers, this.$progressBar, this.$deCompileUnitMap, this.$locatorNew, continuation);
        reportConverter$flush$2$5$worker$1.L$0 = value;
        return reportConverter$flush$2$5$worker$1;
    }

    public final Object invoke(Pair<String, ? extends List<Report>> pair, Continuation<? super Unit> continuation) {
        return create(pair, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x022a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x01ec -> B:13:0x014d). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0204 -> B:13:0x014d). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            Method dump skipped, instructions count: 584
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ReportConverter$flush$2$5$worker$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final IBugResInfo invokeSuspend$lambda$4$lambda$0(BugPathPosition data) {
        return data.getClassname();
    }

    private static final BugPathPosition invokeSuspend$lambda$4$lambda$2(BugPathPosition data, Unit deCompileUnit) {
        Region it;
        BugPathPosition bugPathPositionCopy$default = data;
        Region region = bugPathPositionCopy$default.getRegion();
        if (region != null && (it = ReportConverter.AnonymousClass2.invokeSuspend$toTextareaLine(region, deCompileUnit)) != null) {
            bugPathPositionCopy$default = BugPathPosition.copy$default(data, null, it, 1, null);
        }
        return bugPathPositionCopy$default;
    }

    private static final Object invokeSuspend$lambda$8$lambda$6(IReportConsumer $consumer, String $fileName) {
        return "OOM!. Failed to generate " + $consumer.getType() + " report file named " + $fileName;
    }

    private static final Object invokeSuspend$lambda$8$lambda$7(IReportConsumer $consumer, String $fileName) {
        return "Failed to generate " + $consumer.getType() + " report file named " + $fileName;
    }
}
