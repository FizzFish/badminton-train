package cn.sast.framework.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcn/sast/framework/report/ListSuffixRelation;", "", "neitherSuffix", "", "<init>", "(Ljava/lang/String;IZ)V", "getNeitherSuffix", "()Z", "Equals", "AIsSuffixOfB", "BIsSuffixOfA", "NeitherSuffix", "corax-framework"})
/* loaded from: ListSuffixRelation.class */
public enum ListSuffixRelation {
    Equals(false),
    AIsSuffixOfB(false),
    BIsSuffixOfA(false),
    NeitherSuffix(true);

    private final boolean neitherSuffix;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    ListSuffixRelation(boolean neitherSuffix) {
        this.neitherSuffix = neitherSuffix;
    }

    public final boolean getNeitherSuffix() {
        return this.neitherSuffix;
    }

    @NotNull
    public static EnumEntries<ListSuffixRelation> getEntries() {
        return $ENTRIES;
    }
}
