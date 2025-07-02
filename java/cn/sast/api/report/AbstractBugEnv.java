package cn.sast.api.report;

import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.report.Region;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.tagkit.Host;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b&\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J.\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102\u0006\u0010\u0013\u001a\u00020\u00172\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R.\u0010\u0004\u001a\u001f\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0006j\u0002`\n¢\u0006\u0002\b\t0\u0005¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcn/sast/api/report/AbstractBugEnv;", "Lcom/feysh/corax/config/api/BugMessage$Env;", "<init>", "()V", "appendEvents", "", "Lkotlin/Function1;", "Lcn/sast/api/report/BugPathEventEnvironment;", "Lcn/sast/api/report/BugPathEvent;", "Lkotlin/ExtensionFunctionType;", "Lcn/sast/api/report/BugPathEventLazyGen;", "getAppendEvents", "()Ljava/util/List;", "appendPathEvent", "", "message", "", "Lcom/feysh/corax/config/api/Language;", "", "loc", "Ljava/nio/file/Path;", "region", "Lcom/feysh/corax/config/api/report/Region;", "Lsoot/tagkit/Host;", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/AbstractBugEnv\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1#2:452\n*E\n"})
/* loaded from: AbstractBugEnv.class */
public abstract class AbstractBugEnv implements BugMessage.Env {

    @NotNull
    private final List<Function1<BugPathEventEnvironment, BugPathEvent>> appendEvents = new ArrayList();

    @NotNull
    public final List<Function1<BugPathEventEnvironment, BugPathEvent>> getAppendEvents() {
        return this.appendEvents;
    }

    public void appendPathEvent(@NotNull Map<Language, String> map, @NotNull Path loc, @NotNull Region region) {
        Intrinsics.checkNotNullParameter(map, "message");
        Intrinsics.checkNotNullParameter(loc, "loc");
        Intrinsics.checkNotNullParameter(region, "region");
        this.appendEvents.add((v3) -> {
            return appendPathEvent$lambda$0(r1, r2, r3, v3);
        });
    }

    private static final BugPathEvent appendPathEvent$lambda$0(Map $message, Path $loc, Region $region, BugPathEventEnvironment bugPathEventEnvironment) {
        Intrinsics.checkNotNullParameter(bugPathEventEnvironment, "<this>");
        return new BugPathEvent($message, new FileResInfo(Resource.INSTANCE.fileOf($loc)), $region, null, 8, null);
    }

    public void appendPathEvent(@NotNull Map<Language, String> map, @NotNull Host loc, @Nullable Region region) {
        Intrinsics.checkNotNullParameter(map, "message");
        Intrinsics.checkNotNullParameter(loc, "loc");
        this.appendEvents.add((v3) -> {
            return appendPathEvent$lambda$2(r1, r2, r3, v3);
        });
    }

    private static final BugPathEvent appendPathEvent$lambda$2(Region $region, Map $message, Host $loc, BugPathEventEnvironment $this$ret) {
        Intrinsics.checkNotNullParameter($this$ret, "$this$ret");
        Region regionInvoke = $region;
        if (regionInvoke == null) {
            SootInfoCache it = $this$ret.getSootInfoCache();
            regionInvoke = it != null ? Region.Companion.invoke(it, $loc) : null;
            if (regionInvoke == null) {
                regionInvoke = Region.Companion.getERROR();
            }
        }
        Region selectRegion = regionInvoke;
        return new BugPathEvent($message, ClassResInfo.Companion.of($loc), selectRegion, null, 8, null);
    }
}
