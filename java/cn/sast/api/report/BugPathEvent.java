package cn.sast.api.report;

import cn.sast.api.util.ComparatorUtilsKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.report.Region;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n��\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0002\b\u0086\b\u0018��2\b\u0012\u0004\u0012\u00020��0\u00012\u00020\u0002B7\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eJ\u0011\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020��H\u0096\u0002J\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u000e\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u001cJ\b\u0010\u001e\u001a\u00020\u0006H\u0016J\u0015\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\nHÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0016JD\u0010#\u001a\u00020��2\u0014\b\u0002\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010\u0019\u001a\u0004\u0018\u00010'HÖ\u0003J\t\u0010(\u001a\u00020\fHÖ\u0001R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016¨\u0006)"}, d2 = {"Lcn/sast/api/report/BugPathEvent;", "", "Lcn/sast/api/report/IReportHashAble;", "message", "", "Lcom/feysh/corax/config/api/Language;", "", "classname", "Lcn/sast/api/report/IBugResInfo;", "region", "Lcom/feysh/corax/config/api/report/Region;", "stackDepth", "", "<init>", "(Ljava/util/Map;Lcn/sast/api/report/IBugResInfo;Lcom/feysh/corax/config/api/report/Region;Ljava/lang/Integer;)V", "getMessage", "()Ljava/util/Map;", "getClassname", "()Lcn/sast/api/report/IBugResInfo;", "getRegion", "()Lcom/feysh/corax/config/api/report/Region;", "getStackDepth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "compareTo", "other", "reportHash", "c", "Lcn/sast/api/report/IReportHashCalculator;", "reportHashWithMessage", "toString", "component1", "component2", "component3", "component4", "copy", "(Ljava/util/Map;Lcn/sast/api/report/IBugResInfo;Lcom/feysh/corax/config/api/report/Region;Ljava/lang/Integer;)Lcn/sast/api/report/BugPathEvent;", "equals", "", "", "hashCode", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/BugPathEvent\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1#2:452\n*E\n"})
/* loaded from: BugPathEvent.class */
public final class BugPathEvent implements Comparable<BugPathEvent>, IReportHashAble {

    @NotNull
    private final Map<Language, String> message;

    @NotNull
    private final IBugResInfo classname;

    @NotNull
    private final Region region;

    @Nullable
    private final Integer stackDepth;

    @NotNull
    public final Map<Language, String> component1() {
        return this.message;
    }

    @NotNull
    public final IBugResInfo component2() {
        return this.classname;
    }

    @NotNull
    public final Region component3() {
        return this.region;
    }

    @Nullable
    public final Integer component4() {
        return this.stackDepth;
    }

    @NotNull
    public final BugPathEvent copy(@NotNull Map<Language, String> map, @NotNull IBugResInfo classname, @NotNull Region region, @Nullable Integer stackDepth) {
        Intrinsics.checkNotNullParameter(map, "message");
        Intrinsics.checkNotNullParameter(classname, "classname");
        Intrinsics.checkNotNullParameter(region, "region");
        return new BugPathEvent(map, classname, region, stackDepth);
    }

    public static /* synthetic */ BugPathEvent copy$default(BugPathEvent bugPathEvent, Map map, IBugResInfo iBugResInfo, Region region, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            map = bugPathEvent.message;
        }
        if ((i & 2) != 0) {
            iBugResInfo = bugPathEvent.classname;
        }
        if ((i & 4) != 0) {
            region = bugPathEvent.region;
        }
        if ((i & 8) != 0) {
            num = bugPathEvent.stackDepth;
        }
        return bugPathEvent.copy(map, iBugResInfo, region, num);
    }

    public int hashCode() {
        int result = this.message.hashCode();
        return (((((result * 31) + this.classname.hashCode()) * 31) + this.region.hashCode()) * 31) + (this.stackDepth == null ? 0 : this.stackDepth.hashCode());
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BugPathEvent)) {
            return false;
        }
        BugPathEvent bugPathEvent = (BugPathEvent) other;
        return Intrinsics.areEqual(this.message, bugPathEvent.message) && Intrinsics.areEqual(this.classname, bugPathEvent.classname) && Intrinsics.areEqual(this.region, bugPathEvent.region) && Intrinsics.areEqual(this.stackDepth, bugPathEvent.stackDepth);
    }

    public BugPathEvent(@NotNull Map<Language, String> map, @NotNull IBugResInfo classname, @NotNull Region region, @Nullable Integer stackDepth) {
        Intrinsics.checkNotNullParameter(map, "message");
        Intrinsics.checkNotNullParameter(classname, "classname");
        Intrinsics.checkNotNullParameter(region, "region");
        this.message = map;
        this.classname = classname;
        this.region = region;
        this.stackDepth = stackDepth;
    }

    public /* synthetic */ BugPathEvent(Map map, IBugResInfo iBugResInfo, Region region, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(map, iBugResInfo, region, (i & 8) != 0 ? null : num);
    }

    @NotNull
    public final Map<Language, String> getMessage() {
        return this.message;
    }

    @NotNull
    public final IBugResInfo getClassname() {
        return this.classname;
    }

    @NotNull
    public final Region getRegion() {
        return this.region;
    }

    @Nullable
    public final Integer getStackDepth() {
        return this.stackDepth;
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull BugPathEvent other) {
        Intrinsics.checkNotNullParameter(other, "other");
        Integer numValueOf = Integer.valueOf(ComparatorUtilsKt.compareToMap(this.message, other.message));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num != null) {
            int it2 = num.intValue();
            return it2;
        }
        Integer numValueOf2 = Integer.valueOf(this.classname.compareTo(other.classname));
        int it3 = numValueOf2.intValue();
        Integer num2 = it3 != 0 ? numValueOf2 : null;
        if (num2 != null) {
            int it4 = num2.intValue();
            return it4;
        }
        Integer numValueOf3 = Integer.valueOf(this.region.compareTo(other.region));
        int it5 = numValueOf3.intValue();
        Integer num3 = it5 != 0 ? numValueOf3 : null;
        if (num3 == null) {
            return 0;
        }
        int it6 = num3.intValue();
        return it6;
    }

    @Override // cn.sast.api.report.IReportHashAble
    @NotNull
    public String reportHash(@NotNull IReportHashCalculator c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return this.classname.reportHash(c) + ":" + this.region;
    }

    @NotNull
    public final String reportHashWithMessage(@NotNull IReportHashCalculator c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return reportHash(c) + " " + CollectionsKt.toSortedSet(this.message.values());
    }

    @NotNull
    public String toString() {
        return this.classname + " at " + this.region + " " + this.message;
    }
}
