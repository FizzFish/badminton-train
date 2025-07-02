package cn.sast.api.report;

import cn.sast.api.util.SootUtilsKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ClassMember;
import soot.SootClass;
import soot.SootMethod;
import soot.SourceLocator;
import soot.tagkit.Host;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010��\n\u0002\b\u0004\b\u0086\b\u0018�� (2\u00020\u0001:\u0001(B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0011\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0001H\u0096\u0002J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 H\u0016J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\"\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010\u001d\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\rHÖ\u0001J\t\u0010'\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0012\u001a\u0004\u0018\u00010\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0013\u0010\u000bR!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\u00168FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u000b¨\u0006)"}, d2 = {"Lcn/sast/api/report/ClassResInfo;", "Lcn/sast/api/report/IBugResInfo;", "sc", "Lsoot/SootClass;", "<init>", "(Lsoot/SootClass;)V", "getSc", "()Lsoot/SootClass;", "path", "", "getPath", "()Ljava/lang/String;", "maxLine", "", "getMaxLine", "()I", "maxLine$delegate", "Lkotlin/Lazy;", "sourcePath", "getSourcePath", "sourcePath$delegate", "sourceFile", "Ljava/util/LinkedHashSet;", "getSourceFile", "()Ljava/util/LinkedHashSet;", "sourceFile$delegate", "reportFileName", "getReportFileName", "compareTo", "other", "reportHash", "c", "Lcn/sast/api/report/IReportHashCalculator;", "component1", "copy", "equals", "", "", "hashCode", "toString", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/ClassResInfo\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1#2:452\n*E\n"})
/* loaded from: ClassResInfo.class */
public final class ClassResInfo extends IBugResInfo {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final SootClass sc;

    @NotNull
    private final Lazy maxLine$delegate;

    @NotNull
    private final Lazy sourcePath$delegate;

    @NotNull
    private final Lazy sourceFile$delegate;

    @NotNull
    public final SootClass component1() {
        return this.sc;
    }

    @NotNull
    public final ClassResInfo copy(@NotNull SootClass sc) {
        Intrinsics.checkNotNullParameter(sc, "sc");
        return new ClassResInfo(sc);
    }

    public static /* synthetic */ ClassResInfo copy$default(ClassResInfo classResInfo, SootClass sootClass, int i, Object obj) {
        if ((i & 1) != 0) {
            sootClass = classResInfo.sc;
        }
        return classResInfo.copy(sootClass);
    }

    @NotNull
    public String toString() {
        return "ClassResInfo(sc=" + this.sc + ")";
    }

    public int hashCode() {
        return this.sc.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ClassResInfo) && Intrinsics.areEqual(this.sc, ((ClassResInfo) other).sc);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClassResInfo(@NotNull SootClass sc) {
        super(null);
        Intrinsics.checkNotNullParameter(sc, "sc");
        this.sc = sc;
        this.maxLine$delegate = LazyKt.lazy(() -> {
            return maxLine_delegate$lambda$0(r1);
        });
        this.sourcePath$delegate = LazyKt.lazy(() -> {
            return sourcePath_delegate$lambda$1(r1);
        });
        this.sourceFile$delegate = LazyKt.lazy(() -> {
            return sourceFile_delegate$lambda$2(r1);
        });
    }

    @NotNull
    public final SootClass getSc() {
        return this.sc;
    }

    @Override // cn.sast.api.report.IBugResInfo
    @NotNull
    public String getPath() {
        return ((String) CollectionsKt.first(getSourceFile())).toString();
    }

    public final int getMaxLine() {
        return ((Number) this.maxLine$delegate.getValue()).intValue();
    }

    private static final int maxLine_delegate$lambda$0(ClassResInfo this$0) {
        return SootUtilsKt.getNumCode(this$0.sc);
    }

    @Nullable
    public final String getSourcePath() {
        return (String) this.sourcePath$delegate.getValue();
    }

    private static final String sourcePath_delegate$lambda$1(ClassResInfo this$0) {
        return SootUtilsKt.getSourcePath(this$0.sc);
    }

    @NotNull
    public final LinkedHashSet<String> getSourceFile() {
        return (LinkedHashSet) this.sourceFile$delegate.getValue();
    }

    private static final LinkedHashSet sourceFile_delegate$lambda$2(ClassResInfo this$0) {
        return SootUtilsKt.getPossibleSourceFiles(this$0.sc);
    }

    @Override // cn.sast.api.report.IBugResInfo
    @NotNull
    public String getReportFileName() {
        String sourcePath = getSourcePath();
        if (sourcePath != null) {
            List listSplit$default = StringsKt.split$default(sourcePath, new String[]{"/"}, false, 0, 6, (Object) null);
            if (listSplit$default != null) {
                String str = (String) CollectionsKt.lastOrNull(listSplit$default);
                if (str != null) {
                    return str;
                }
            }
        }
        return SourceLocator.v().getSourceForClass(this.sc.getShortJavaStyleName()) + ".java";
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull IBugResInfo other) {
        Intrinsics.checkNotNullParameter(other, "other");
        if (!(other instanceof ClassResInfo)) {
            String simpleName = getClass().getSimpleName();
            String simpleName2 = other.getClass().getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName2, "getSimpleName(...)");
            return simpleName.compareTo(simpleName2);
        }
        String name = this.sc.getName();
        String name2 = ((ClassResInfo) other).sc.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        Integer numValueOf = Integer.valueOf(name.compareTo(name2));
        int it = numValueOf.intValue();
        Integer num = it != 0 ? numValueOf : null;
        if (num == null) {
            return 0;
        }
        int it2 = num.intValue();
        return it2;
    }

    /* compiled from: Report.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��$\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lcn/sast/api/report/ClassResInfo$Companion;", "", "<init>", "()V", "of", "Lcn/sast/api/report/ClassResInfo;", "sc", "Lsoot/SootClass;", "sm", "Lsoot/SootMethod;", "sootDecl", "Lsoot/tagkit/Host;", "corax-api"})
    /* loaded from: ClassResInfo$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final ClassResInfo of(@NotNull SootClass sc) {
            Intrinsics.checkNotNullParameter(sc, "sc");
            return new ClassResInfo(sc);
        }

        @NotNull
        public final ClassResInfo of(@NotNull SootMethod sm) {
            Intrinsics.checkNotNullParameter(sm, "sm");
            SootClass declaringClass = sm.getDeclaringClass();
            Intrinsics.checkNotNullExpressionValue(declaringClass, "getDeclaringClass(...)");
            return of(declaringClass);
        }

        @NotNull
        public final ClassResInfo of(@NotNull Host sootDecl) {
            SootClass declaringClass;
            Intrinsics.checkNotNullParameter(sootDecl, "sootDecl");
            if (sootDecl instanceof SootClass) {
                declaringClass = (SootClass) sootDecl;
            } else {
                if (!(sootDecl instanceof ClassMember)) {
                    throw new IllegalStateException(("Unsupported sootDecl type: " + sootDecl + " " + sootDecl.getClass()).toString());
                }
                declaringClass = ((ClassMember) sootDecl).getDeclaringClass();
            }
            SootClass sc = declaringClass;
            Intrinsics.checkNotNull(sc);
            return new ClassResInfo(sc);
        }
    }

    @Override // cn.sast.api.report.IReportHashAble
    @NotNull
    public String reportHash(@NotNull IReportHashCalculator c) {
        Intrinsics.checkNotNullParameter(c, "c");
        return c.from(this.sc);
    }
}
