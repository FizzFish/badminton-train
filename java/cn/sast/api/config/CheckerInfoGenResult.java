package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CheckerInfo.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001Bg\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005\u0012\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0003j\b\u0012\u0004\u0012\u00020\u0007`\u0005\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005\u0012\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J\u0019\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0003j\b\u0012\u0004\u0012\u00020\u0007`\u0005HÆ\u0003J\u0019\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005HÆ\u0003J\u0019\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005HÆ\u0003Jq\u0010\u001b\u001a\u00020��2\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u00052\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0003j\b\u0012\u0004\u0012\u00020\u0007`\u00052\u0018\b\u0002\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u00052\u0018\b\u0002\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\tHÖ\u0001R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR!\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0003j\b\u0012\u0004\u0012\u00020\u0007`\u0005¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u000eR!\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u000eR!\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\t0\u0003j\b\u0012\u0004\u0012\u00020\t`\u0005¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u000eR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\""}, d2 = {"Lcn/sast/api/config/CheckerInfoGenResult;", "", "checkerInfoList", "Ljava/util/LinkedHashSet;", "Lcn/sast/api/config/CheckerInfo;", "Lkotlin/collections/LinkedHashSet;", "existsCheckTypes", "Lcom/feysh/corax/config/api/CheckType;", "existsCheckerIds", "", "checkerIdInCsv", "<init>", "(Ljava/util/LinkedHashSet;Ljava/util/LinkedHashSet;Ljava/util/LinkedHashSet;Ljava/util/LinkedHashSet;)V", "getCheckerInfoList", "()Ljava/util/LinkedHashSet;", "getExistsCheckTypes", "getExistsCheckerIds", "getCheckerIdInCsv", "chapters", "", "Lcn/sast/api/config/ChapterFlat;", "getChapters", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-api"})
@SourceDebugExtension({"SMAP\nCheckerInfo.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerInfo.kt\ncn/sast/api/config/CheckerInfoGenResult\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,49:1\n1611#2,9:50\n1863#2:59\n1864#2:61\n1620#2:62\n1#3:60\n*S KotlinDebug\n*F\n+ 1 CheckerInfo.kt\ncn/sast/api/config/CheckerInfoGenResult\n*L\n48#1:50,9\n48#1:59\n48#1:61\n48#1:62\n48#1:60\n*E\n"})
/* loaded from: CheckerInfoGenResult.class */
public final class CheckerInfoGenResult {

    @NotNull
    private final LinkedHashSet<CheckerInfo> checkerInfoList;

    @NotNull
    private final LinkedHashSet<CheckType> existsCheckTypes;

    @NotNull
    private final LinkedHashSet<String> existsCheckerIds;

    @NotNull
    private final LinkedHashSet<String> checkerIdInCsv;

    @NotNull
    public final LinkedHashSet<CheckerInfo> component1() {
        return this.checkerInfoList;
    }

    @NotNull
    public final LinkedHashSet<CheckType> component2() {
        return this.existsCheckTypes;
    }

    @NotNull
    public final LinkedHashSet<String> component3() {
        return this.existsCheckerIds;
    }

    @NotNull
    public final LinkedHashSet<String> component4() {
        return this.checkerIdInCsv;
    }

    @NotNull
    public final CheckerInfoGenResult copy(@NotNull LinkedHashSet<CheckerInfo> linkedHashSet, @NotNull LinkedHashSet<CheckType> linkedHashSet2, @NotNull LinkedHashSet<String> linkedHashSet3, @NotNull LinkedHashSet<String> linkedHashSet4) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "checkerInfoList");
        Intrinsics.checkNotNullParameter(linkedHashSet2, "existsCheckTypes");
        Intrinsics.checkNotNullParameter(linkedHashSet3, "existsCheckerIds");
        Intrinsics.checkNotNullParameter(linkedHashSet4, "checkerIdInCsv");
        return new CheckerInfoGenResult(linkedHashSet, linkedHashSet2, linkedHashSet3, linkedHashSet4);
    }

    public static /* synthetic */ CheckerInfoGenResult copy$default(CheckerInfoGenResult checkerInfoGenResult, LinkedHashSet linkedHashSet, LinkedHashSet linkedHashSet2, LinkedHashSet linkedHashSet3, LinkedHashSet linkedHashSet4, int i, Object obj) {
        if ((i & 1) != 0) {
            linkedHashSet = checkerInfoGenResult.checkerInfoList;
        }
        if ((i & 2) != 0) {
            linkedHashSet2 = checkerInfoGenResult.existsCheckTypes;
        }
        if ((i & 4) != 0) {
            linkedHashSet3 = checkerInfoGenResult.existsCheckerIds;
        }
        if ((i & 8) != 0) {
            linkedHashSet4 = checkerInfoGenResult.checkerIdInCsv;
        }
        return checkerInfoGenResult.copy(linkedHashSet, linkedHashSet2, linkedHashSet3, linkedHashSet4);
    }

    @NotNull
    public String toString() {
        return "CheckerInfoGenResult(checkerInfoList=" + this.checkerInfoList + ", existsCheckTypes=" + this.existsCheckTypes + ", existsCheckerIds=" + this.existsCheckerIds + ", checkerIdInCsv=" + this.checkerIdInCsv + ")";
    }

    public int hashCode() {
        int result = this.checkerInfoList.hashCode();
        return (((((result * 31) + this.existsCheckTypes.hashCode()) * 31) + this.existsCheckerIds.hashCode()) * 31) + this.checkerIdInCsv.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckerInfoGenResult)) {
            return false;
        }
        CheckerInfoGenResult checkerInfoGenResult = (CheckerInfoGenResult) other;
        return Intrinsics.areEqual(this.checkerInfoList, checkerInfoGenResult.checkerInfoList) && Intrinsics.areEqual(this.existsCheckTypes, checkerInfoGenResult.existsCheckTypes) && Intrinsics.areEqual(this.existsCheckerIds, checkerInfoGenResult.existsCheckerIds) && Intrinsics.areEqual(this.checkerIdInCsv, checkerInfoGenResult.checkerIdInCsv);
    }

    public CheckerInfoGenResult(@NotNull LinkedHashSet<CheckerInfo> linkedHashSet, @NotNull LinkedHashSet<CheckType> linkedHashSet2, @NotNull LinkedHashSet<String> linkedHashSet3, @NotNull LinkedHashSet<String> linkedHashSet4) {
        Intrinsics.checkNotNullParameter(linkedHashSet, "checkerInfoList");
        Intrinsics.checkNotNullParameter(linkedHashSet2, "existsCheckTypes");
        Intrinsics.checkNotNullParameter(linkedHashSet3, "existsCheckerIds");
        Intrinsics.checkNotNullParameter(linkedHashSet4, "checkerIdInCsv");
        this.checkerInfoList = linkedHashSet;
        this.existsCheckTypes = linkedHashSet2;
        this.existsCheckerIds = linkedHashSet3;
        this.checkerIdInCsv = linkedHashSet4;
    }

    @NotNull
    public final LinkedHashSet<CheckerInfo> getCheckerInfoList() {
        return this.checkerInfoList;
    }

    @NotNull
    public final LinkedHashSet<CheckType> getExistsCheckTypes() {
        return this.existsCheckTypes;
    }

    @NotNull
    public final LinkedHashSet<String> getExistsCheckerIds() {
        return this.existsCheckerIds;
    }

    @NotNull
    public final LinkedHashSet<String> getCheckerIdInCsv() {
        return this.checkerIdInCsv;
    }

    @NotNull
    public final List<ChapterFlat> getChapters() {
        Iterable $this$mapNotNull$iv = this.checkerInfoList;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            CheckerInfo it = (CheckerInfo) element$iv$iv$iv;
            ChapterFlat chapterFlat = it.getChapterFlat();
            if (chapterFlat != null) {
                destination$iv$iv.add(chapterFlat);
            }
        }
        return (List) destination$iv$iv;
    }
}
