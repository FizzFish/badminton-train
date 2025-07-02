package cn.sast.dataflow.infoflow.manager;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.jimple.infoflow.sourcesSinks.definitions.ISourceSinkCategory;

/* compiled from: SourceSinkCategory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\n\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\b¨\u0006\r"}, d2 = {"Lcn/sast/dataflow/infoflow/manager/SourceSinkCategory;", "Lsoot/jimple/infoflow/sourcesSinks/definitions/ISourceSinkCategory;", "detector", "", "pattern", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getDetector", "()Ljava/lang/String;", "getPattern", "getHumanReadableDescription", "getID", "toString", "corax-data-flow"})
/* loaded from: SourceSinkCategory.class */
public final class SourceSinkCategory implements ISourceSinkCategory {

    @NotNull
    private final String detector;

    @NotNull
    private final String pattern;

    public SourceSinkCategory(@NotNull String detector, @NotNull String pattern) {
        Intrinsics.checkNotNullParameter(detector, "detector");
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        this.detector = detector;
        this.pattern = pattern;
    }

    @NotNull
    public final String getDetector() {
        return this.detector;
    }

    @NotNull
    public final String getPattern() {
        return this.pattern;
    }

    @NotNull
    public String getHumanReadableDescription() {
        return "";
    }

    @NotNull
    public String getID() {
        return this.pattern;
    }

    @NotNull
    public String toString() {
        return this.detector + "::" + this.pattern;
    }
}
