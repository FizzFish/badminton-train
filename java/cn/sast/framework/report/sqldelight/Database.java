package cn.sast.framework.report.sqldelight;

import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.db.QueryResult;
import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.db.SqlSchema;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.sqldelight.coraxframework.DatabaseImplKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;

/* compiled from: Database.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018�� F2\u00020\u0001:\u0001FR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0012\u0010\"\u001a\u00020#X¦\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0012\u0010&\u001a\u00020'X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0012\u0010*\u001a\u00020+X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0012\u0010.\u001a\u00020/X¦\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0012\u00102\u001a\u000203X¦\u0004¢\u0006\u0006\u001a\u0004\b4\u00105R\u0012\u00106\u001a\u000207X¦\u0004¢\u0006\u0006\u001a\u0004\b8\u00109R\u0012\u0010:\u001a\u00020;X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0012\u0010>\u001a\u00020?X¦\u0004¢\u0006\u0006\u001a\u0004\b@\u0010AR\u0012\u0010B\u001a\u00020CX¦\u0004¢\u0006\u0006\u001a\u0004\bD\u0010E¨\u0006G"}, d2 = {"Lcn/sast/framework/report/sqldelight/Database;", "Lapp/cash/sqldelight/Transacter;", "absoluteFilePathQueries", "Lcn/sast/framework/report/sqldelight/AbsoluteFilePathQueries;", "getAbsoluteFilePathQueries", "()Lcn/sast/framework/report/sqldelight/AbsoluteFilePathQueries;", "analyzeResultFileQueries", "Lcn/sast/framework/report/sqldelight/AnalyzeResultFileQueries;", "getAnalyzeResultFileQueries", "()Lcn/sast/framework/report/sqldelight/AnalyzeResultFileQueries;", "analyzerStatisticsQueries", "Lcn/sast/framework/report/sqldelight/AnalyzerStatisticsQueries;", "getAnalyzerStatisticsQueries", "()Lcn/sast/framework/report/sqldelight/AnalyzerStatisticsQueries;", "controlFlowQueries", "Lcn/sast/framework/report/sqldelight/ControlFlowQueries;", "getControlFlowQueries", "()Lcn/sast/framework/report/sqldelight/ControlFlowQueries;", "controlFlowPathQueries", "Lcn/sast/framework/report/sqldelight/ControlFlowPathQueries;", "getControlFlowPathQueries", "()Lcn/sast/framework/report/sqldelight/ControlFlowPathQueries;", "diagnosticQueries", "Lcn/sast/framework/report/sqldelight/DiagnosticQueries;", "getDiagnosticQueries", "()Lcn/sast/framework/report/sqldelight/DiagnosticQueries;", "diagnosticExtQueries", "Lcn/sast/framework/report/sqldelight/DiagnosticExtQueries;", "getDiagnosticExtQueries", "()Lcn/sast/framework/report/sqldelight/DiagnosticExtQueries;", "fileQueries", "Lcn/sast/framework/report/sqldelight/FileQueries;", "getFileQueries", "()Lcn/sast/framework/report/sqldelight/FileQueries;", "macroExpansionQueries", "Lcn/sast/framework/report/sqldelight/MacroExpansionQueries;", "getMacroExpansionQueries", "()Lcn/sast/framework/report/sqldelight/MacroExpansionQueries;", "noteQueries", "Lcn/sast/framework/report/sqldelight/NoteQueries;", "getNoteQueries", "()Lcn/sast/framework/report/sqldelight/NoteQueries;", "noteExtQueries", "Lcn/sast/framework/report/sqldelight/NoteExtQueries;", "getNoteExtQueries", "()Lcn/sast/framework/report/sqldelight/NoteExtQueries;", "notePathQueries", "Lcn/sast/framework/report/sqldelight/NotePathQueries;", "getNotePathQueries", "()Lcn/sast/framework/report/sqldelight/NotePathQueries;", "regionQueries", "Lcn/sast/framework/report/sqldelight/RegionQueries;", "getRegionQueries", "()Lcn/sast/framework/report/sqldelight/RegionQueries;", "ruleQueries", "Lcn/sast/framework/report/sqldelight/RuleQueries;", "getRuleQueries", "()Lcn/sast/framework/report/sqldelight/RuleQueries;", "ruleMappingQueries", "Lcn/sast/framework/report/sqldelight/RuleMappingQueries;", "getRuleMappingQueries", "()Lcn/sast/framework/report/sqldelight/RuleMappingQueries;", "ruleSetInfoQueries", "Lcn/sast/framework/report/sqldelight/RuleSetInfoQueries;", "getRuleSetInfoQueries", "()Lcn/sast/framework/report/sqldelight/RuleSetInfoQueries;", "schemaInfoQueries", "Lcn/sast/framework/report/sqldelight/SchemaInfoQueries;", "getSchemaInfoQueries", "()Lcn/sast/framework/report/sqldelight/SchemaInfoQueries;", "Companion", "corax-framework"})
/* loaded from: Database.class */
public interface Database extends Transacter {

    @NotNull
    public static final Companion Companion = Companion.$$INSTANCE;

    @NotNull
    AbsoluteFilePathQueries getAbsoluteFilePathQueries();

    @NotNull
    AnalyzeResultFileQueries getAnalyzeResultFileQueries();

    @NotNull
    AnalyzerStatisticsQueries getAnalyzerStatisticsQueries();

    @NotNull
    ControlFlowQueries getControlFlowQueries();

    @NotNull
    ControlFlowPathQueries getControlFlowPathQueries();

    @NotNull
    DiagnosticQueries getDiagnosticQueries();

    @NotNull
    DiagnosticExtQueries getDiagnosticExtQueries();

    @NotNull
    FileQueries getFileQueries();

    @NotNull
    MacroExpansionQueries getMacroExpansionQueries();

    @NotNull
    NoteQueries getNoteQueries();

    @NotNull
    NoteExtQueries getNoteExtQueries();

    @NotNull
    NotePathQueries getNotePathQueries();

    @NotNull
    RegionQueries getRegionQueries();

    @NotNull
    RuleQueries getRuleQueries();

    @NotNull
    RuleMappingQueries getRuleMappingQueries();

    @NotNull
    RuleSetInfoQueries getRuleSetInfoQueries();

    @NotNull
    SchemaInfoQueries getSchemaInfoQueries();

    /* compiled from: Database.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0086\u0002R\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lcn/sast/framework/report/sqldelight/Database$Companion;", "", "<init>", "()V", "Schema", "Lapp/cash/sqldelight/db/SqlSchema;", "Lapp/cash/sqldelight/db/QueryResult$Value;", "", "getSchema", "()Lapp/cash/sqldelight/db/SqlSchema;", "invoke", "Lcn/sast/framework/report/sqldelight/Database;", "driver", "Lapp/cash/sqldelight/db/SqlDriver;", "corax-framework"})
    /* loaded from: Database$Companion.class */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @NotNull
        public final SqlSchema<QueryResult.Value<Unit>> getSchema() {
            return DatabaseImplKt.getSchema(Reflection.getOrCreateKotlinClass(Database.class));
        }

        @NotNull
        public final Database invoke(@NotNull SqlDriver driver) {
            Intrinsics.checkNotNullParameter(driver, "driver");
            return DatabaseImplKt.newInstance(Reflection.getOrCreateKotlinClass(Database.class), driver);
        }
    }
}
