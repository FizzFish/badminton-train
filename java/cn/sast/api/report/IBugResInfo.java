package cn.sast.api.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b6\u0018��2\b\u0012\u0004\u0012\u00020��0\u00012\u00020\u0002B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b\u0082\u0001\u0002\u000b\f¨\u0006\r"}, d2 = {"Lcn/sast/api/report/IBugResInfo;", "", "Lcn/sast/api/report/IReportHashAble;", "<init>", "()V", "reportFileName", "", "getReportFileName", "()Ljava/lang/String;", "path", "getPath", "Lcn/sast/api/report/ClassResInfo;", "Lcn/sast/api/report/FileResInfo;", "corax-api"})
/* loaded from: IBugResInfo.class */
public abstract class IBugResInfo implements Comparable<IBugResInfo>, IReportHashAble {
    @Nullable
    public abstract String getReportFileName();

    @NotNull
    public abstract String getPath();

    public /* synthetic */ IBugResInfo(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    private IBugResInfo() {
    }
}
