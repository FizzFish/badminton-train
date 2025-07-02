package cn.sast.api.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018�� \u00102\b\u0012\u0004\u0012\u00020��0\u0001:\u0001\u0010B,\b\u0002\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0004\b\t\u0010\nR,\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcn/sast/api/report/CheckType2StringKind;", "", "convert", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/CheckType;", "Lkotlin/ParameterName;", "name", "type", "", "<init>", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;)V", "getConvert", "()Lkotlin/jvm/functions/Function1;", "RuleDotTYName", "RuleDotTYName2", "RuleName", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/CheckType2StringKind\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n1#2:452\n*E\n"})
/* loaded from: CheckType2StringKind.class */
public enum CheckType2StringKind {
    RuleDotTYName(CheckType2StringKind::_init_$lambda$0),
    RuleDotTYName2(CheckType2StringKind::_init_$lambda$1),
    RuleName(CheckType2StringKind::_init_$lambda$2);


    @NotNull
    private final Function1<CheckType, String> convert;

    @NotNull
    private static final String ruleNameKindEnv = "REPORT_RULE_KIND";

    @NotNull
    private static final CheckType2StringKind checkType2StringKind;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    @NotNull
    public static final Companion Companion = new Companion(null);

    CheckType2StringKind(Function1 convert) {
        this.convert = convert;
    }

    @NotNull
    public final Function1<CheckType, String> getConvert() {
        return this.convert;
    }

    private static final String _init_$lambda$0(CheckType t) {
        Intrinsics.checkNotNullParameter(t, "t");
        return t.getReport().getRealName() + "." + t.getClass().getSimpleName();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0073  */
    static {
        /*
            cn.sast.api.report.CheckType2StringKind r0 = new cn.sast.api.report.CheckType2StringKind
            r1 = r0
            java.lang.String r2 = "RuleDotTYName"
            r3 = 0
            void r4 = cn.sast.api.report.CheckType2StringKind::_init_$lambda$0
            r1.<init>(r4)
            cn.sast.api.report.CheckType2StringKind.RuleDotTYName = r0
            cn.sast.api.report.CheckType2StringKind r0 = new cn.sast.api.report.CheckType2StringKind
            r1 = r0
            java.lang.String r2 = "RuleDotTYName2"
            r3 = 1
            void r4 = cn.sast.api.report.CheckType2StringKind::_init_$lambda$1
            r1.<init>(r4)
            cn.sast.api.report.CheckType2StringKind.RuleDotTYName2 = r0
            cn.sast.api.report.CheckType2StringKind r0 = new cn.sast.api.report.CheckType2StringKind
            r1 = r0
            java.lang.String r2 = "RuleName"
            r3 = 2
            void r4 = cn.sast.api.report.CheckType2StringKind::_init_$lambda$2
            r1.<init>(r4)
            cn.sast.api.report.CheckType2StringKind.RuleName = r0
            cn.sast.api.report.CheckType2StringKind[] r0 = $values()
            cn.sast.api.report.CheckType2StringKind.$VALUES = r0
            cn.sast.api.report.CheckType2StringKind[] r0 = cn.sast.api.report.CheckType2StringKind.$VALUES
            java.lang.Enum[] r0 = (java.lang.Enum[]) r0
            kotlin.enums.EnumEntries r0 = kotlin.enums.EnumEntriesKt.enumEntries(r0)
            cn.sast.api.report.CheckType2StringKind.$ENTRIES = r0
            cn.sast.api.report.CheckType2StringKind$Companion r0 = new cn.sast.api.report.CheckType2StringKind$Companion
            r1 = r0
            r2 = 0
            r1.<init>(r2)
            cn.sast.api.report.CheckType2StringKind.Companion = r0
            java.lang.String r0 = "REPORT_RULE_KIND"
            java.lang.String r0 = java.lang.System.getenv(r0)
            r1 = r0
            if (r1 != 0) goto L62
        L5d:
            java.lang.String r0 = "REPORT_RULE_KIND"
            java.lang.String r0 = java.lang.System.getProperty(r0)
        L62:
            r1 = r0
            if (r1 == 0) goto L72
            r6 = r0
            r0 = 0
            r7 = r0
            r0 = r6
            cn.sast.api.report.CheckType2StringKind r0 = valueOf(r0)
            r1 = r0
            if (r1 != 0) goto L76
        L72:
        L73:
            cn.sast.api.report.CheckType2StringKind r0 = cn.sast.api.report.CheckType2StringKind.RuleDotTYName
        L76:
            cn.sast.api.report.CheckType2StringKind.checkType2StringKind = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.report.CheckType2StringKind.m39clinit():void");
    }

    private static final String _init_$lambda$1(CheckType t) {
        Intrinsics.checkNotNullParameter(t, "t");
        String realName = t.getReport().getRealName();
        String simpleName = t.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = simpleName.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return realName + "." + lowerCase;
    }

    private static final String _init_$lambda$2(CheckType t) {
        Intrinsics.checkNotNullParameter(t, "t");
        return t.getReport().getRealName();
    }

    /* compiled from: Report.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001a\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n��R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcn/sast/api/report/CheckType2StringKind$Companion;", "", "<init>", "()V", "ruleNameKindEnv", "", "checkType2StringKind", "Lcn/sast/api/report/CheckType2StringKind;", "getCheckType2StringKind", "()Lcn/sast/api/report/CheckType2StringKind;", "corax-api"})
    /* loaded from: CheckType2StringKind$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final CheckType2StringKind getCheckType2StringKind() {
            return CheckType2StringKind.checkType2StringKind;
        }
    }

    @NotNull
    public static EnumEntries<CheckType2StringKind> getEntries() {
        return $ENTRIES;
    }
}
