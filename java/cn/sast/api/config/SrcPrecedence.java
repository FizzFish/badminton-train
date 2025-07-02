package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: MainConfig.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0013"}, d2 = {"Lcn/sast/api/config/SrcPrecedence;", "", "sootFlag", "", "<init>", "(Ljava/lang/String;II)V", "getSootFlag", "()I", "prec_class", "prec_only_class", "prec_jimple", "prec_java", "prec_java_soot", "prec_apk", "prec_apk_class_jimple", "prec_dotnet", "isSootJavaSourcePrec", "", "()Z", "corax-api"})
/* loaded from: SrcPrecedence.class */
public enum SrcPrecedence {
    prec_class(1),
    prec_only_class(2),
    prec_jimple(3),
    prec_java(6),
    prec_java_soot(4),
    prec_apk(5),
    prec_apk_class_jimple(6),
    prec_dotnet(7);

    private final int sootFlag;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    SrcPrecedence(int sootFlag) {
        this.sootFlag = sootFlag;
    }

    public final int getSootFlag() {
        return this.sootFlag;
    }

    public final boolean isSootJavaSourcePrec() {
        return this == prec_java_soot;
    }

    @NotNull
    public static EnumEntries<SrcPrecedence> getEntries() {
        return $ENTRIES;
    }
}
