package cn.sast.framework.report;

import cn.sast.api.config.MainConfig;
import cn.sast.api.report.BugPathEvent;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.report.Report;
import cn.sast.api.report.ReportKt;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.common.OS;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ReportConsumer;
import cn.sast.framework.report.sarif.ArtifactLocation;
import cn.sast.framework.report.sarif.CodeFlow;
import cn.sast.framework.report.sarif.IndexedMessage;
import cn.sast.framework.report.sarif.Location;
import cn.sast.framework.report.sarif.Message;
import cn.sast.framework.report.sarif.MessageStrings;
import cn.sast.framework.report.sarif.PhysicalLocation;
import cn.sast.framework.report.sarif.Result;
import cn.sast.framework.report.sarif.Rule;
import cn.sast.framework.report.sarif.Run;
import cn.sast.framework.report.sarif.SarifLog;
import cn.sast.framework.report.sarif.Tool;
import cn.sast.framework.report.sarif.ToolComponent;
import cn.sast.framework.report.sarif.TranslationToolComponent;
import cn.sast.framework.result.OutputType;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.report.Region;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SarifDiagnostics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018�� \u001f2\u00020\u00012\u00020\u0002:\u0004\u001c\u001d\u001e\u001fB\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001c\u0010\r\u001a\u00060\u000eR\u00020��2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J,\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u0017H\u0002J\b\u0010\u001b\u001a\u00020\u0012H\u0016R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006 "}, d2 = {"Lcn/sast/framework/report/SarifDiagnostics;", "Lcn/sast/framework/report/ReportConsumer;", "Lcn/sast/framework/report/IFileReportConsumer;", "outputDir", "Lcn/sast/common/IResDirectory;", "type", "Lcn/sast/framework/result/OutputType;", "<init>", "(Lcn/sast/common/IResDirectory;Lcn/sast/framework/result/OutputType;)V", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "getMetadata", "()Lcn/sast/framework/report/ReportConsumer$MetaData;", "getSarifDiagnosticsImpl", "Lcn/sast/framework/report/SarifDiagnostics$SarifDiagnosticsImpl;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "flush", "", "reports", "", "Lcn/sast/api/report/Report;", "filename", "", "(Ljava/util/List;Ljava/lang/String;Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getReportFileName", "fileName", "close", "SarifMetadata", "MultiLangRule", "SarifDiagnosticsImpl", "Companion", "corax-framework"})
/* loaded from: SarifDiagnostics.class */
public class SarifDiagnostics extends ReportConsumer implements IFileReportConsumer {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SarifDiagnostics::logger$lambda$1);

    /* compiled from: SarifDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SarifDiagnostics.kt", l = {133}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"fullPath"}, m = "flush$suspendImpl", c = "cn.sast.framework.report.SarifDiagnostics")
    /* renamed from: cn.sast.framework.report.SarifDiagnostics$flush$1, reason: invalid class name */
    /* loaded from: SarifDiagnostics$flush$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return SarifDiagnostics.flush$suspendImpl(SarifDiagnostics.this, null, null, null, (Continuation) this);
        }
    }

    @Override // cn.sast.framework.report.IFileReportConsumer
    @Nullable
    public Object flush(@NotNull List<Report> list, @NotNull String filename, @NotNull IProjectFileLocator locator, @NotNull Continuation<? super Unit> continuation) {
        return flush$suspendImpl(this, list, filename, locator, continuation);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SarifDiagnostics(@NotNull IResDirectory outputDir, @NotNull OutputType type) {
        super(type, outputDir);
        Intrinsics.checkNotNullParameter(outputDir, "outputDir");
        Intrinsics.checkNotNullParameter(type, "type");
    }

    public /* synthetic */ SarifDiagnostics(IResDirectory iResDirectory, OutputType outputType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iResDirectory, (i & 2) != 0 ? OutputType.SARIF : outputType);
    }

    @Override // cn.sast.framework.report.ReportConsumer
    @NotNull
    public ReportConsumer.MetaData getMetadata() {
        return new ReportConsumer.MetaData("corax", "1.0", "CoraxJava");
    }

    /* compiled from: SarifDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bÂ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/report/SarifDiagnostics$SarifMetadata;", "", "<init>", "()V", "schema", "", "version", "corax-framework"})
    /* loaded from: SarifDiagnostics$SarifMetadata.class */
    private static final class SarifMetadata {

        @NotNull
        public static final SarifMetadata INSTANCE = new SarifMetadata();

        @NotNull
        public static final String schema = "https://raw.githubusercontent.com/oasis-tcs/sarif-spec/master/Schemata/sarif-schema-2.1.0.json";

        @NotNull
        public static final String version = "2.1.0";

        private SarifMetadata() {
        }
    }

    /* compiled from: SarifDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0003J3\u0010\u0013\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\r\u0010\fR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcn/sast/framework/report/SarifDiagnostics$MultiLangRule;", "", "id", "", "name", "messageStrings", "", "Lcom/feysh/corax/config/api/Language;", "Lcn/sast/framework/report/sarif/MessageStrings;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;)V", "getId", "()Ljava/lang/String;", "getName", "getMessageStrings", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
    /* loaded from: SarifDiagnostics$MultiLangRule.class */
    public static final class MultiLangRule {

        @NotNull
        private final String id;

        @NotNull
        private final String name;

        @NotNull
        private final Map<Language, MessageStrings> messageStrings;

        @NotNull
        public final String component1() {
            return this.id;
        }

        @NotNull
        public final String component2() {
            return this.name;
        }

        @NotNull
        public final Map<Language, MessageStrings> component3() {
            return this.messageStrings;
        }

        @NotNull
        public final MultiLangRule copy(@NotNull String id, @NotNull String name, @NotNull Map<Language, MessageStrings> map) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(map, "messageStrings");
            return new MultiLangRule(id, name, map);
        }

        public static /* synthetic */ MultiLangRule copy$default(MultiLangRule multiLangRule, String str, String str2, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                str = multiLangRule.id;
            }
            if ((i & 2) != 0) {
                str2 = multiLangRule.name;
            }
            if ((i & 4) != 0) {
                map = multiLangRule.messageStrings;
            }
            return multiLangRule.copy(str, str2, map);
        }

        @NotNull
        public String toString() {
            return "MultiLangRule(id=" + this.id + ", name=" + this.name + ", messageStrings=" + this.messageStrings + ")";
        }

        public int hashCode() {
            int result = this.id.hashCode();
            return (((result * 31) + this.name.hashCode()) * 31) + this.messageStrings.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MultiLangRule)) {
                return false;
            }
            MultiLangRule multiLangRule = (MultiLangRule) other;
            return Intrinsics.areEqual(this.id, multiLangRule.id) && Intrinsics.areEqual(this.name, multiLangRule.name) && Intrinsics.areEqual(this.messageStrings, multiLangRule.messageStrings);
        }

        public MultiLangRule(@NotNull String id, @NotNull String name, @NotNull Map<Language, MessageStrings> map) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(map, "messageStrings");
            this.id = id;
            this.name = name;
            this.messageStrings = map;
        }

        @NotNull
        public final String getId() {
            return this.id;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final Map<Language, MessageStrings> getMessageStrings() {
            return this.messageStrings;
        }
    }

    /* compiled from: SarifDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\b\n��\n\u0002\u0010 \n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0096\u0004\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u000e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\rH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0010H\u0016J\u001a\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010'\u001a\u00020(H\u0002J\u0012\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010)\u001a\u00020*H\u0002J\u0016\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020*0\rH\u0002J\u0012\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010'\u001a\u00020(H\u0002J\u001e\u00100\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010/0\r2\f\u00101\u001a\b\u0012\u0004\u0012\u00020(0\rH\u0002J\u0016\u00102\u001a\u0002032\f\u00101\u001a\b\u0012\u0004\u0012\u00020(0\rH\u0016J\u0014\u00104\u001a\u0002052\f\u00101\u001a\b\u0012\u0004\u0012\u00020(0\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u000e¢\u0006\u0002\n��R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\rX\u0082.¢\u0006\u0002\n��R\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u00020\u000f*\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012R$\u0010\u0015\u001a\u00020\u000f*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000f0\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u00066"}, d2 = {"Lcn/sast/framework/report/SarifDiagnostics$SarifDiagnosticsImpl;", "", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "<init>", "(Lcn/sast/framework/report/SarifDiagnostics;Lcn/sast/framework/report/ReportConsumer$MetaData;Lcn/sast/framework/report/IProjectFileLocator;)V", "ruleToIndex", "", "Lcn/sast/framework/report/SarifDiagnostics$MultiLangRule;", "", "sortedRuleToIndex", "", "file2uri", "", "Lcn/sast/common/IResFile;", "getFile2uri", "(Lcn/sast/common/IResFile;)Ljava/lang/String;", "absPathMapToFolder", "getAbsPathMapToFolder", "preferredMessage", "", "Lcom/feysh/corax/config/api/Language;", "getPreferredMessage", "(Ljava/util/Map;)Ljava/lang/String;", "getTool", "Lcn/sast/framework/report/sarif/Tool;", "getTranslations", "Lcn/sast/framework/report/sarif/TranslationToolComponent;", "getArtifactLocation", "Lcn/sast/framework/report/sarif/ArtifactLocation;", "file", "getPhysicalLocation", "Lcn/sast/framework/report/sarif/PhysicalLocation;", "classInfo", "Lcn/sast/api/report/IBugResInfo;", "region", "Lcom/feysh/corax/config/api/report/Region;", "report", "Lcn/sast/api/report/Report;", "event", "Lcn/sast/api/report/BugPathEvent;", "getThreadFlow", "Lcn/sast/framework/report/sarif/ThreadFlow;", "events", "getResult", "Lcn/sast/framework/report/sarif/Result;", "getResults", "reports", "getRun", "Lcn/sast/framework/report/sarif/Run;", "getSarifLog", "Lcn/sast/framework/report/sarif/SarifLog;", "corax-framework"})
    @SourceDebugExtension({"SMAP\nSarifDiagnostics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SarifDiagnostics.kt\ncn/sast/framework/report/SarifDiagnostics$SarifDiagnosticsImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,146:1\n1557#2:147\n1628#2,2:148\n1630#2:151\n1797#2,3:152\n1246#2,4:157\n1557#2:168\n1628#2,3:169\n1053#2:172\n1557#2:173\n1628#2,3:174\n1#3:150\n462#4:155\n412#4:156\n381#4,7:161\n*S KotlinDebug\n*F\n+ 1 SarifDiagnostics.kt\ncn/sast/framework/report/SarifDiagnostics$SarifDiagnosticsImpl\n*L\n59#1:147\n59#1:148,2\n59#1:151\n92#1:152,3\n103#1:157,4\n114#1:168\n114#1:169,3\n118#1:172\n118#1:173\n118#1:174,3\n103#1:155\n103#1:156\n104#1:161,7\n*E\n"})
    /* loaded from: SarifDiagnostics$SarifDiagnosticsImpl.class */
    public class SarifDiagnosticsImpl {

        @NotNull
        private final ReportConsumer.MetaData metadata;

        @NotNull
        private final IProjectFileLocator locator;

        @NotNull
        private Map<MultiLangRule, Integer> ruleToIndex;
        private List<MultiLangRule> sortedRuleToIndex;
        final /* synthetic */ SarifDiagnostics this$0;

        public SarifDiagnosticsImpl(@NotNull SarifDiagnostics this$0, @NotNull ReportConsumer.MetaData metadata, IProjectFileLocator locator) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            Intrinsics.checkNotNullParameter(locator, "locator");
            this.this$0 = this$0;
            this.metadata = metadata;
            this.locator = locator;
            this.ruleToIndex = new LinkedHashMap();
        }

        @NotNull
        public String getFile2uri(@NotNull IResFile $this$file2uri) {
            Intrinsics.checkNotNullParameter($this$file2uri, "<this>");
            String string = $this$file2uri.expandRes(this.this$0.getOutputDir()).getAbsolute().getNormalize().getPath().toUri().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        @NotNull
        public String getAbsPathMapToFolder(@NotNull IResFile $this$absPathMapToFolder) {
            Intrinsics.checkNotNullParameter($this$absPathMapToFolder, "<this>");
            String it = StringsKt.removePrefix(StringsKt.removePrefix($this$absPathMapToFolder.getAbsolute().getNormalize().toString(), "/"), "\\");
            return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(OS.INSTANCE.isWindows() ? StringsKt.replace$default(it, ":", "", false, 4, (Object) null) : it, "\\", "/", false, 4, (Object) null), "//", "/", false, 4, (Object) null), "!", "", false, 4, (Object) null);
        }

        private final String getPreferredMessage(Map<Language, String> map) {
            return (String) ReportKt.preferredMessage(map, SarifDiagnosticsImpl::_get_preferredMessage_$lambda$1);
        }

        private static final String _get_preferredMessage_$lambda$1() {
            return "Engine error: no such message of preferred languages " + MainConfig.Companion.getPreferredLanguages();
        }

        private final Tool getTool() {
            String analyzerName = this.metadata.getAnalyzerName();
            String toolName = this.metadata.getToolName();
            String toolVersion = this.metadata.getToolVersion();
            Iterable iterable = this.sortedRuleToIndex;
            if (iterable == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortedRuleToIndex");
                iterable = null;
            }
            Iterable $this$map$iv = iterable;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                MultiLangRule it = (MultiLangRule) item$iv$iv;
                destination$iv$iv.add(new Rule(it.getId(), it.getName(), (MessageStrings) ReportKt.preferredMessage(it.getMessageStrings(), SarifDiagnosticsImpl::getTool$lambda$4$lambda$3$lambda$2)));
            }
            return new Tool(new ToolComponent(analyzerName, toolName, toolVersion, (List) destination$iv$iv));
        }

        private static final MessageStrings getTool$lambda$4$lambda$3$lambda$2() {
            return new MessageStrings(new Message("Engine error: no such message of preferred languages " + MainConfig.Companion.getPreferredLanguages(), (String) null, 2, (DefaultConstructorMarker) null));
        }

        private final List<TranslationToolComponent> getTranslations() {
            return CollectionsKt.emptyList();
        }

        @NotNull
        public ArtifactLocation getArtifactLocation(@NotNull IResFile file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return new ArtifactLocation(getFile2uri(file), (String) null, 2, (DefaultConstructorMarker) null);
        }

        @Nullable
        public PhysicalLocation getPhysicalLocation(@NotNull IBugResInfo classInfo, @NotNull Region region) {
            Intrinsics.checkNotNullParameter(classInfo, "classInfo");
            Intrinsics.checkNotNullParameter(region, "region");
            IResFile it = this.locator.get(classInfo, EmptyWrapperFileGenerator.INSTANCE);
            if (it != null) {
                return new PhysicalLocation(getArtifactLocation(it), new cn.sast.framework.report.sarif.Region(region));
            }
            return null;
        }

        private final PhysicalLocation getPhysicalLocation(Report report) {
            return getPhysicalLocation(report.getBugResFile(), report.getRegion());
        }

        private final PhysicalLocation getPhysicalLocation(BugPathEvent event) {
            return getPhysicalLocation(event.getClassname(), event.getRegion());
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0078  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final cn.sast.framework.report.sarif.ThreadFlow getThreadFlow(java.util.List<cn.sast.api.report.BugPathEvent> r13) {
            /*
                r12 = this;
                r0 = r13
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                r14 = r0
                java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
                r15 = r0
                r0 = 0
                r16 = r0
                r0 = r15
                r17 = r0
                r0 = r14
                java.util.Iterator r0 = r0.iterator()
                r18 = r0
            L17:
                r0 = r18
                boolean r0 = r0.hasNext()
                if (r0 == 0) goto L7f
                r0 = r18
                java.lang.Object r0 = r0.next()
                r19 = r0
                r0 = r17
                r1 = r19
                cn.sast.api.report.BugPathEvent r1 = (cn.sast.api.report.BugPathEvent) r1
                r20 = r1
                r21 = r0
                r0 = 0
                r22 = r0
                r0 = r12
                r1 = r20
                cn.sast.framework.report.sarif.PhysicalLocation r0 = r0.getPhysicalLocation(r1)
                r1 = r0
                if (r1 == 0) goto L77
                r23 = r0
                r0 = 0
                r24 = r0
                r0 = r21
                java.util.Collection r0 = (java.util.Collection) r0
                cn.sast.framework.report.sarif.FlowLocationWrapper r1 = new cn.sast.framework.report.sarif.FlowLocationWrapper
                r2 = r1
                cn.sast.framework.report.sarif.FlowLocation r3 = new cn.sast.framework.report.sarif.FlowLocation
                r4 = r3
                cn.sast.framework.report.sarif.Message r5 = new cn.sast.framework.report.sarif.Message
                r6 = r5
                r7 = r12
                r8 = r20
                java.util.Map r8 = r8.getMessage()
                java.lang.String r7 = r7.getPreferredMessage(r8)
                r8 = 0
                r9 = 2
                r10 = 0
                r6.<init>(r7, r8, r9, r10)
                r6 = r23
                r4.<init>(r5, r6)
                r2.<init>(r3)
                java.util.List r0 = kotlin.collections.CollectionsKt.plus(r0, r1)
                r1 = r0
                if (r1 != 0) goto L7a
            L77:
            L78:
                r0 = r21
            L7a:
                r17 = r0
                goto L17
            L7f:
                r0 = r17
                r25 = r0
                cn.sast.framework.report.sarif.ThreadFlow r0 = new cn.sast.framework.report.sarif.ThreadFlow
                r1 = r0
                r2 = r25
                r1.<init>(r2)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.SarifDiagnostics.SarifDiagnosticsImpl.getThreadFlow(java.util.List):cn.sast.framework.report.sarif.ThreadFlow");
        }

        private final Result getResult(Report report) {
            Object obj;
            String check_name = report.getCheck_name();
            Map $this$mapValues$iv = report.getMessage();
            Map destination$iv$iv = new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size()));
            Iterable $this$associateByTo$iv$iv$iv = $this$mapValues$iv.entrySet();
            for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
                Map.Entry it = (Map.Entry) element$iv$iv$iv;
                destination$iv$iv.put(it$iv$iv.getKey(), new MessageStrings(new Message((String) it.getValue(), (String) null, 2, (DefaultConstructorMarker) null)));
            }
            MultiLangRule rule = new MultiLangRule(check_name, "", destination$iv$iv);
            Map $this$getOrPut$iv = this.ruleToIndex;
            Object value$iv = $this$getOrPut$iv.get(rule);
            if (value$iv == null) {
                Integer numValueOf = Integer.valueOf(this.ruleToIndex.size());
                $this$getOrPut$iv.put(rule, numValueOf);
                obj = numValueOf;
            } else {
                obj = value$iv;
            }
            int ruleIndex = ((Number) obj).intValue();
            String check_name2 = report.getCheck_name();
            PhysicalLocation physicalLocation = getPhysicalLocation(report);
            if (physicalLocation == null) {
                return null;
            }
            return new Result(check_name2, ruleIndex, (IndexedMessage) null, CollectionsKt.listOf(new Location(physicalLocation)), CollectionsKt.listOf(new CodeFlow(CollectionsKt.listOf(getThreadFlow(report.getPathEvents())))), 4, (DefaultConstructorMarker) null);
        }

        private final List<Result> getResults(List<Report> list) {
            List<Report> $this$map$iv = list;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Report it = (Report) item$iv$iv;
                destination$iv$iv.add(getResult(it));
            }
            return (List) destination$iv$iv;
        }

        @NotNull
        public Run getRun(@NotNull List<Report> list) {
            Intrinsics.checkNotNullParameter(list, "reports");
            List results = CollectionsKt.filterNotNull(getResults(list));
            Iterable $this$sortedBy$iv = MapsKt.toList(this.ruleToIndex);
            Iterable $this$map$iv = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.framework.report.SarifDiagnostics$SarifDiagnosticsImpl$getRun$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Pair it = (Pair) t;
                    Pair it2 = (Pair) t2;
                    return ComparisonsKt.compareValues((Integer) it.getSecond(), (Integer) it2.getSecond());
                }
            });
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Pair it = (Pair) item$iv$iv;
                destination$iv$iv.add((MultiLangRule) it.getFirst());
            }
            this.sortedRuleToIndex = (List) destination$iv$iv;
            return new Run(getTool(), (Map) null, results, getTranslations(), 2, (DefaultConstructorMarker) null);
        }

        @NotNull
        public final SarifLog getSarifLog(@NotNull List<Report> list) {
            Intrinsics.checkNotNullParameter(list, "reports");
            return new SarifLog(SarifMetadata.schema, SarifMetadata.version, CollectionsKt.listOf(getRun(list)));
        }
    }

    @NotNull
    public SarifDiagnosticsImpl getSarifDiagnosticsImpl(@NotNull ReportConsumer.MetaData metadata, @NotNull IProjectFileLocator locator) {
        Intrinsics.checkNotNullParameter(metadata, "metadata");
        Intrinsics.checkNotNullParameter(locator, "locator");
        return new SarifDiagnosticsImpl(this, metadata, locator);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object flush$suspendImpl(cn.sast.framework.report.SarifDiagnostics r9, java.util.List<cn.sast.api.report.Report> r10, java.lang.String r11, cn.sast.framework.report.IProjectFileLocator r12, kotlin.coroutines.Continuation<? super kotlin.Unit> r13) {
        /*
            r0 = r13
            boolean r0 = r0 instanceof cn.sast.framework.report.SarifDiagnostics.AnonymousClass1
            if (r0 == 0) goto L29
            r0 = r13
            cn.sast.framework.report.SarifDiagnostics$flush$1 r0 = (cn.sast.framework.report.SarifDiagnostics.AnonymousClass1) r0
            r17 = r0
            r0 = r17
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L29
            r0 = r17
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L35
        L29:
            cn.sast.framework.report.SarifDiagnostics$flush$1 r0 = new cn.sast.framework.report.SarifDiagnostics$flush$1
            r1 = r0
            r2 = r9
            r3 = r13
            r1.<init>(r3)
            r17 = r0
        L35:
            r0 = r17
            java.lang.Object r0 = r0.result
            r16 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r18 = r0
            r0 = r17
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto La6;
                default: goto Lcb;
            }
        L5c:
            r0 = r16
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r9
            r1 = r11
            java.lang.String r0 = r0.getReportFileName(r1)
            r14 = r0
            r0 = r9
            cn.sast.common.IResDirectory r0 = r0.getOutputDir()
            r1 = r14
            cn.sast.common.IResource r0 = r0.resolve(r1)
            r15 = r0
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
            cn.sast.framework.report.SarifDiagnostics$flush$2 r1 = new cn.sast.framework.report.SarifDiagnostics$flush$2
            r2 = r1
            r3 = r15
            r4 = r9
            r5 = r12
            r6 = r10
            r7 = 0
            r2.<init>(r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r2 = r17
            r3 = r17
            r4 = r15
            r3.L$0 = r4
            r3 = r17
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r1, r2)
            r1 = r0
            r2 = r18
            if (r1 != r2) goto Lb7
            r1 = r18
            return r1
        La6:
            r0 = r17
            java.lang.Object r0 = r0.L$0
            cn.sast.common.IResource r0 = (cn.sast.common.IResource) r0
            r15 = r0
            r0 = r16
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r16
        Lb7:
            mu.KLogger r0 = cn.sast.framework.report.SarifDiagnostics.logger
            r1 = r15
            java.lang.Object r1 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return flush$lambda$0(r1);
            }
            r0.trace(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lcb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.SarifDiagnostics.flush$suspendImpl(cn.sast.framework.report.SarifDiagnostics, java.util.List, java.lang.String, cn.sast.framework.report.IProjectFileLocator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: SarifDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "SarifDiagnostics.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.SarifDiagnostics$flush$2")
    /* renamed from: cn.sast.framework.report.SarifDiagnostics$flush$2, reason: invalid class name */
    /* loaded from: SarifDiagnostics$flush$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ IResource $fullPath;
        final /* synthetic */ SarifDiagnostics this$0;
        final /* synthetic */ IProjectFileLocator $locator;
        final /* synthetic */ List<Report> $reports;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(IResource $fullPath, SarifDiagnostics $receiver, IProjectFileLocator $locator, List<Report> list, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$fullPath = $fullPath;
            this.this$0 = $receiver;
            this.$locator = $locator;
            this.$reports = list;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new AnonymousClass2(this.$fullPath, this.this$0, this.$locator, this.$reports, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    FilesKt.writeText$default(this.$fullPath.getFile(), this.this$0.getSarifDiagnosticsImpl(this.this$0.getMetadata(), this.$locator).getSarifLog(this.$reports).toJson(), (Charset) null, 2, (Object) null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private static final Object flush$lambda$0(IResource $fullPath) {
        return "Create/modify plist file: '" + $fullPath + "'";
    }

    private final String getReportFileName(String fileName) {
        String analyzerName = getMetadata().getAnalyzerName();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = analyzerName.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return fileName + "_" + lowerCase + ".sarif";
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    /* compiled from: SarifDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/report/SarifDiagnostics$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: SarifDiagnostics$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }
}
