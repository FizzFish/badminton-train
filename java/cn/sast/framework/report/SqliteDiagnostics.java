package cn.sast.framework.report;

import app.cash.sqldelight.ExecutableQuery;
import app.cash.sqldelight.Transacter;
import app.cash.sqldelight.TransactionWithoutReturn;
import cn.sast.api.AnalyzerEnv;
import cn.sast.api.config.ExtSettings;
import cn.sast.api.config.MainConfig;
import cn.sast.api.config.ProjectConfig;
import cn.sast.api.report.BugPathEvent;
import cn.sast.api.report.CheckType2StringKind;
import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.report.Report;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.common.ResourceImplKt;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.metrics.MetricsMonitor;
import cn.sast.framework.report.FileX;
import cn.sast.framework.report.ReportConsumer;
import cn.sast.framework.report.metadata.AnalysisMetadata;
import cn.sast.framework.report.metadata.Analyzer;
import cn.sast.framework.report.metadata.AnalyzerStatistics;
import cn.sast.framework.report.metadata.Tool;
import cn.sast.framework.report.sqldelight.AnalyzerResultFile;
import cn.sast.framework.report.sqldelight.ControlFlow;
import cn.sast.framework.report.sqldelight.ControlFlowPath;
import cn.sast.framework.report.sqldelight.Database;
import cn.sast.framework.report.sqldelight.Diagnostic;
import cn.sast.framework.report.sqldelight.File;
import cn.sast.framework.report.sqldelight.Note;
import cn.sast.framework.report.sqldelight.NotePath;
import cn.sast.framework.report.sqldelight.Region;
import cn.sast.framework.report.sqldelight.Rule;
import cn.sast.framework.result.OutputType;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.cache.analysis.SootLineToMethodMapFactory;
import com.feysh.corax.cache.analysis.SootMethodAndRange;
import com.feysh.corax.commons.NullableLateinit;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.report.Region;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.javaparser.ast.body.BodyDeclaration;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.optionals.OptionalsKt;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.StringUtilKt;

/* compiled from: SqliteDiagnostics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��º\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010%\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018�� \u0090\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\u0004\u008f\u0001\u0090\u0001B5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010A\u001a\u00020BH\u0096@¢\u0006\u0002\u0010CJ\u0010\u0010D\u001a\u00020B2\b\b\u0002\u0010E\u001a\u00020=J\b\u0010F\u001a\u00020BH\u0002J\u0018\u0010G\u001a\u00020B*\u0006\u0012\u0002\b\u00030H2\u0006\u0010I\u001a\u00020=H\u0002J\u0006\u0010G\u001a\u00020BJ\b\u0010J\u001a\u00020BH\u0016J,\u0010K\u001a\u00020B2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0M2\u0006\u0010O\u001a\u00020=2\u0006\u0010P\u001a\u00020QH\u0096@¢\u0006\u0002\u0010RJ\u0010\u0010S\u001a\u00020B2\u0006\u0010T\u001a\u00020UH\u0016J,\u0010V\u001a\u00020B2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020N0M2\u0006\u0010P\u001a\u00020Q2\u0006\u0010O\u001a\u00020=H\u0082@¢\u0006\u0002\u0010WJ\u001a\u0010X\u001a\u0004\u0018\u0001012\u0006\u0010P\u001a\u00020Q2\u0006\u0010Y\u001a\u00020ZH\u0002J\u001e\u0010[\u001a\b\u0018\u00010\\R\u0002062\u0006\u0010P\u001a\u00020Q2\u0006\u0010Y\u001a\u00020ZH\u0002J\u0016\u0010]\u001a\b\u0018\u00010\\R\u0002062\u0006\u0010^\u001a\u00020&H\u0002J\u0014\u0010_\u001a\b\u0018\u00010\\R\u0002062\u0006\u0010Y\u001a\u00020&J\u0010\u0010`\u001a\u0002062\u0006\u0010^\u001a\u00020&H\u0002J\u0016\u0010e\u001a\u0004\u0018\u00010=*\u0002062\u0006\u0010f\u001a\u00020gH\u0002J,\u0010h\u001a\n\u0012\u0004\u0012\u00020j\u0018\u00010i2\u0006\u0010P\u001a\u00020Q2\u0006\u0010k\u001a\u00020Z2\n\u0010l\u001a\u00060mj\u0002`nH\u0002J \u0010o\u001a\n\u0018\u00010mj\u0004\u0018\u0001`n2\u0006\u0010p\u001a\u00020Z2\u0006\u0010f\u001a\u00020gH\u0002J \u0010q\u001a\n\u0012\u0004\u0012\u00020r\u0018\u00010i2\u0006\u0010P\u001a\u00020Q2\u0006\u0010s\u001a\u00020tH\u0002J \u0010u\u001a\n\u0012\u0004\u0012\u00020v\u0018\u00010i2\u0006\u0010P\u001a\u00020Q2\u0006\u0010s\u001a\u00020tH\u0002J%\u0010z\u001a\u0004\u0018\u00010x2\u0006\u0010P\u001a\u00020Q2\f\u0010{\u001a\b\u0012\u0004\u0012\u00020t0|H\u0002¢\u0006\u0002\u0010}J%\u0010~\u001a\u0004\u0018\u00010x2\u0006\u0010P\u001a\u00020Q2\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020t0|H\u0002¢\u0006\u0002\u0010}J#\u0010\u0080\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0081\u0001\u0018\u00010i2\u0006\u0010P\u001a\u00020Q2\u0007\u0010\u0082\u0001\u001a\u00020NH\u0002J!\u0010\u0083\u0001\u001a\n\u0012\u0005\u0012\u00030\u0085\u00010\u0084\u00012\u000e\u0010\u0086\u0001\u001a\t\u0012\u0005\u0012\u00030\u0087\u00010MH\u0002J!\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u00012\u0006\u0010Y\u001a\u00020&2\u000b\b\u0002\u0010\u008a\u0001\u001a\u0004\u0018\u00010=H\u0002J\u0007\u0010\u008b\u0001\u001a\u00020BJ\u001b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0007\u0010\u008e\u0001\u001a\u00020U2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n��\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u000e¢\u0006\u0002\n��R+\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00188B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010!\u001a\u00020\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020&X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010'\u001a\u00020(8F¢\u0006\u0006\u001a\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n��R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n��R\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020100X\u0082\u0004¢\u0006\u0002\n��Rh\u00102\u001aZ\u0012\f\u0012\n 4*\u0004\u0018\u00010&0&\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u000206 4*\n\u0012\u0004\u0012\u000206\u0018\u00010505 4*,\u0012\f\u0012\n 4*\u0004\u0018\u00010&0&\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u000206 4*\n\u0012\u0004\u0012\u000206\u0018\u00010505\u0018\u00010303X\u0082\u0004¢\u0006\u0004\n\u0002\u00107R\u0018\u00108\u001a\u000209*\u00020&8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0018\u0010<\u001a\u00020=*\u00020>8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u001a\u0010a\u001a\u0004\u0018\u00010b*\u00020N8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bc\u0010dR\u001a\u0010w\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020x00X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010y\u001a\u000e\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020x00X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0091\u0001"}, d2 = {"Lcn/sast/framework/report/SqliteDiagnostics;", "Lcn/sast/framework/report/ReportConsumer;", "Lcn/sast/framework/report/IFileReportConsumer;", "Lcn/sast/framework/report/IMetadataVisitor;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "outputDir", "Lcn/sast/common/IResDirectory;", "monitor", "Lcn/sast/framework/metrics/MetricsMonitor;", "type", "Lcn/sast/framework/result/OutputType;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/common/IResDirectory;Lcn/sast/framework/metrics/MetricsMonitor;Lcn/sast/framework/result/OutputType;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "getMonitor", "()Lcn/sast/framework/metrics/MetricsMonitor;", "_sdb", "Lcom/feysh/corax/commons/NullableLateinit;", "Lcn/sast/framework/report/SQLiteDB;", "<set-?>", "sqLiteDB", "getSqLiteDB", "()Lcn/sast/framework/report/SQLiteDB;", "setSqLiteDB", "(Lcn/sast/framework/report/SQLiteDB;)V", "sqLiteDB$delegate", "Lcom/feysh/corax/commons/NullableLateinit;", "database", "Lcn/sast/framework/report/sqldelight/Database;", "getDatabase", "()Lcn/sast/framework/report/sqldelight/Database;", "sqliteReportDb", "Lcn/sast/common/IResFile;", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "getMetadata", "()Lcn/sast/framework/report/ReportConsumer$MetaData;", "writeDispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "ruleAndRuleMapping", "Lcn/sast/framework/report/RuleAndRuleMapping;", "fileIdMap", "", "Lcn/sast/framework/report/FileID;", "fileCache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "kotlin.jvm.PlatformType", "Ljava/util/Optional;", "Lcn/sast/framework/report/FileX;", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "sourceEncoding", "Ljava/nio/charset/Charset;", "getSourceEncoding", "(Lcn/sast/common/IResFile;)Ljava/nio/charset/Charset;", "id", "", "Lcom/feysh/corax/config/api/CheckType;", "getId", "(Lcom/feysh/corax/config/api/CheckType;)Ljava/lang/String;", "init", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "open", "journalMode", "createRuleAndRuleMapping", "verify", "Lapp/cash/sqldelight/ExecutableQuery;", "name", "close", "flush", "reports", "", "Lcn/sast/api/report/Report;", "filename", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "(Ljava/util/List;Ljava/lang/String;Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visit", "analysisMetadata", "Lcn/sast/framework/report/metadata/AnalysisMetadata;", "serializeReportsToDb", "(Ljava/util/List;Lcn/sast/framework/report/IProjectFileLocator;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createFileCached", "file", "Lcn/sast/api/report/IBugResInfo;", "createFileXCached", "Lcn/sast/framework/report/FileX$ID;", "createFileXCachedFromAbsFile", "absFile", "createFileXCachedFromFile", "createFile", "associateChecker", "Lcn/sast/framework/report/sqldelight/Rule;", "getAssociateChecker", "(Lcn/sast/api/report/Report;)Lcn/sast/framework/report/sqldelight/Rule;", "lineContent", "line", "", "createRegion", "Lcn/sast/framework/report/ValueWithId;", "Lcn/sast/framework/report/sqldelight/Region;", "res", "region", "Lcom/feysh/corax/config/api/report/Region;", "Lcn/sast/framework/report/AliasRegion;", "getFuncRange", "classInfo", "createNote", "Lcn/sast/framework/report/sqldelight/Note;", "event", "Lcn/sast/api/report/BugPathEvent;", "createControlFlow", "Lcn/sast/framework/report/sqldelight/ControlFlow;", "noteHashIdAutoIncrement", "", "ctrlFlowHashIdAutoIncrement", "createControlFlowPath", "path", "", "(Lcn/sast/framework/report/IProjectFileLocator;Ljava/util/List;)Ljava/lang/Long;", "createNotePath", "pathEvents", "createDiagnostic", "Lcn/sast/framework/report/sqldelight/Diagnostic;", "report", "getAnalyzerStatisticsSet", "", "Lcn/sast/framework/report/metadata/AnalyzerStatistics;", "tools", "Lcn/sast/framework/report/metadata/Tool;", "createAnalyzerResultFile", "Lcn/sast/framework/report/sqldelight/AnalyzerResultFile;", "fileName", "writeAnalyzerResultFiles", "createAnalyzerStatistics", "Lcn/sast/framework/report/sqldelight/AnalyzerStatistics;", "data", "ArrayHashIdGenerator", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nSqliteDiagnostics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SqliteDiagnostics.kt\ncn/sast/framework/report/SqliteDiagnostics\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Region.kt\ncom/feysh/corax/config/api/report/Region\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,813:1\n381#2,7:814\n381#2,7:821\n1#3:828\n1#3:830\n1#3:834\n59#4:829\n57#4:831\n60#4:832\n59#4:833\n57#4,2:835\n1279#5,2:837\n1293#5,4:839\n1863#5,2:843\n1279#5,2:845\n1293#5,4:847\n1863#5,2:851\n1863#5:853\n1863#5,2:854\n1864#5:856\n1863#5,2:857\n1863#5,2:859\n1628#5,3:861\n1863#5,2:864\n*S KotlinDebug\n*F\n+ 1 SqliteDiagnostics.kt\ncn/sast/framework/report/SqliteDiagnostics\n*L\n365#1:814,7\n379#1:821,7\n465#1:830\n474#1:834\n465#1:829\n465#1:831\n474#1:832\n474#1:833\n474#1:835,2\n573#1:837,2\n573#1:839,4\n593#1:843,2\n600#1:845,2\n600#1:847,4\n624#1:851,2\n708#1:853\n709#1:854,2\n708#1:856\n755#1:857,2\n762#1:859,2\n770#1:861,3\n738#1:864,2\n*E\n"})
/* loaded from: SqliteDiagnostics.class */
public class SqliteDiagnostics extends ReportConsumer implements IFileReportConsumer, IMetadataVisitor {

    @NotNull
    private final MainConfig mainConfig;

    @Nullable
    private final SootInfoCache info;

    @Nullable
    private final MetricsMonitor monitor;

    @NotNull
    private NullableLateinit<SQLiteDB> _sdb;

    @NotNull
    private final NullableLateinit sqLiteDB$delegate;

    @NotNull
    private IResFile sqliteReportDb;

    @NotNull
    private final ExecutorCoroutineDispatcher writeDispatcher;

    @Nullable
    private RuleAndRuleMapping ruleAndRuleMapping;

    @NotNull
    private final Map<IResFile, FileID> fileIdMap;
    private final LoadingCache<IResFile, Optional<FileX>> fileCache;

    @NotNull
    private final Map<String, Long> noteHashIdAutoIncrement;

    @NotNull
    private final Map<String, Long> ctrlFlowHashIdAutoIncrement;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(SqliteDiagnostics.class, "sqLiteDB", "getSqLiteDB()Lcn/sast/framework/report/SQLiteDB;", 0))};

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SqliteDiagnostics::logger$lambda$40);

    @NotNull
    private static final Json jsonFormat = JsonKt.Json$default((Json) null, SqliteDiagnostics::jsonFormat$lambda$41, 1, (Object) null);

    /* compiled from: SqliteDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SqliteDiagnostics.kt", l = {332}, i = {}, s = {}, n = {}, m = "flush$suspendImpl", c = "cn.sast.framework.report.SqliteDiagnostics")
    /* renamed from: cn.sast.framework.report.SqliteDiagnostics$flush$1, reason: invalid class name */
    /* loaded from: SqliteDiagnostics$flush$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return SqliteDiagnostics.flush$suspendImpl(SqliteDiagnostics.this, null, null, null, (Continuation) this);
        }
    }

    /* compiled from: SqliteDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "SqliteDiagnostics.kt", l = {262}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"$this"}, m = "init$suspendImpl", c = "cn.sast.framework.report.SqliteDiagnostics")
    /* renamed from: cn.sast.framework.report.SqliteDiagnostics$init$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: SqliteDiagnostics$init$1.class */
    static final class C00351 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C00351(Continuation<? super C00351> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return SqliteDiagnostics.init$suspendImpl(SqliteDiagnostics.this, (Continuation<? super Unit>) this);
        }
    }

    @Override // cn.sast.framework.report.ReportConsumer, cn.sast.framework.report.IReportConsumer
    @Nullable
    public Object init(@NotNull Continuation<? super Unit> continuation) {
        return init$suspendImpl(this, continuation);
    }

    @Override // cn.sast.framework.report.IFileReportConsumer
    @Nullable
    public Object flush(@NotNull List<Report> list, @NotNull String filename, @NotNull IProjectFileLocator locator, @NotNull Continuation<? super Unit> continuation) {
        return flush$suspendImpl(this, list, filename, locator, continuation);
    }

    public /* synthetic */ SqliteDiagnostics(MainConfig mainConfig, SootInfoCache sootInfoCache, IResDirectory iResDirectory, MetricsMonitor metricsMonitor, OutputType outputType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mainConfig, sootInfoCache, iResDirectory, metricsMonitor, (i & 16) != 0 ? OutputType.SQLITE : outputType);
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @Nullable
    public final SootInfoCache getInfo() {
        return this.info;
    }

    @Nullable
    public final MetricsMonitor getMonitor() {
        return this.monitor;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SqliteDiagnostics(@NotNull MainConfig mainConfig, @Nullable SootInfoCache info, @NotNull IResDirectory outputDir, @Nullable MetricsMonitor monitor, @NotNull OutputType type) {
        super(type, outputDir);
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(outputDir, "outputDir");
        Intrinsics.checkNotNullParameter(type, "type");
        this.mainConfig = mainConfig;
        this.info = info;
        this.monitor = monitor;
        this._sdb = new NullableLateinit<>("SQLiteDB is not initialized yet");
        this.sqLiteDB$delegate = this._sdb;
        this.sqliteReportDb = this.mainConfig.getSqlite_report_db();
        ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "newSingleThreadExecutor(...)");
        this.writeDispatcher = ExecutorsKt.from(executorServiceNewSingleThreadExecutor);
        Map<IResFile, FileID> mapSynchronizedMap = Collections.synchronizedMap(new LinkedHashMap());
        Intrinsics.checkNotNullExpressionValue(mapSynchronizedMap, "synchronizedMap(...)");
        this.fileIdMap = mapSynchronizedMap;
        this.fileCache = Caffeine.newBuilder().initialCapacity(1000).softValues().build(new CacheLoader() { // from class: cn.sast.framework.report.SqliteDiagnostics$fileCache$1
            public final Optional<FileX> load(IResFile absFile) {
                Optional<FileX> optionalEmpty;
                try {
                    SqliteDiagnostics sqliteDiagnostics = this.this$0;
                    Intrinsics.checkNotNull(absFile);
                    optionalEmpty = Optional.ofNullable(sqliteDiagnostics.createFile(absFile));
                } catch (Exception e) {
                    SqliteDiagnostics.logger.warn(() -> {
                        return load$lambda$0(r1, r2);
                    });
                    SqliteDiagnostics.logger.debug(e, () -> {
                        return load$lambda$1(r2, r3);
                    });
                    optionalEmpty = Optional.empty();
                }
                return optionalEmpty;
            }

            private static final Object load$lambda$0(IResFile $absFile, Exception $e) {
                return "Failed to read file: " + $absFile + ", e: " + $e.getMessage();
            }

            private static final Object load$lambda$1(IResFile $absFile, Exception $e) {
                return "Failed to read file: " + $absFile + ", e: " + $e.getMessage();
            }
        });
        this.noteHashIdAutoIncrement = new HashMap(1000);
        this.ctrlFlowHashIdAutoIncrement = new HashMap(1000);
    }

    private final SQLiteDB getSqLiteDB() {
        return (SQLiteDB) this.sqLiteDB$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final void setSqLiteDB(SQLiteDB sQLiteDB) {
        this.sqLiteDB$delegate.setValue(this, $$delegatedProperties[0], sQLiteDB);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Database getDatabase() {
        return getSqLiteDB().getDatabase();
    }

    @Override // cn.sast.framework.report.ReportConsumer
    @NotNull
    public final ReportConsumer.MetaData getMetadata() {
        return new ReportConsumer.MetaData("CoraxJava sqlite report", "1.0", "CoraxJava");
    }

    @NotNull
    public Charset getSourceEncoding(@NotNull IResFile $this$sourceEncoding) {
        Intrinsics.checkNotNullParameter($this$sourceEncoding, "<this>");
        return Charsets.UTF_8;
    }

    private final String getId(CheckType $this$id) {
        return (String) CheckType2StringKind.Companion.getCheckType2StringKind().getConvert().invoke($this$id);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object init$suspendImpl(cn.sast.framework.report.SqliteDiagnostics r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r0 = r6
            boolean r0 = r0 instanceof cn.sast.framework.report.SqliteDiagnostics.C00351
            if (r0 == 0) goto L24
            r0 = r6
            cn.sast.framework.report.SqliteDiagnostics$init$1 r0 = (cn.sast.framework.report.SqliteDiagnostics.C00351) r0
            r8 = r0
            r0 = r8
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L24
            r0 = r8
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L2e
        L24:
            cn.sast.framework.report.SqliteDiagnostics$init$1 r0 = new cn.sast.framework.report.SqliteDiagnostics$init$1
            r1 = r0
            r2 = r5
            r3 = r6
            r1.<init>(r3)
            r8 = r0
        L2e:
            r0 = r8
            java.lang.Object r0 = r0.result
            r7 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r9 = r0
            r0 = r8
            int r0 = r0.label
            switch(r0) {
                case 0: goto L54;
                case 1: goto L70;
                default: goto L8d;
            }
        L54:
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r5
            r1 = r8
            r2 = r8
            r3 = r5
            r2.L$0 = r3
            r2 = r8
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = super.init(r1)
            r1 = r0
            r2 = r9
            if (r1 != r2) goto L7d
            r1 = r9
            return r1
        L70:
            r0 = r8
            java.lang.Object r0 = r0.L$0
            cn.sast.framework.report.SqliteDiagnostics r0 = (cn.sast.framework.report.SqliteDiagnostics) r0
            r5 = r0
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
        L7d:
            r0 = r5
            r1 = 0
            r2 = 1
            r3 = 0
            open$default(r0, r1, r2, r3)
            r0 = r5
            r0.createRuleAndRuleMapping()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L8d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.SqliteDiagnostics.init$suspendImpl(cn.sast.framework.report.SqliteDiagnostics, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ void open$default(SqliteDiagnostics sqliteDiagnostics, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: open");
        }
        if ((i & 1) != 0) {
            str = ExtSettings.INSTANCE.getSqliteJournalMode();
        }
        sqliteDiagnostics.open(str);
    }

    public final void open(@NotNull String journalMode) {
        Intrinsics.checkNotNullParameter(journalMode, "journalMode");
        this.sqliteReportDb.mkdirs();
        setSqLiteDB(Companion.openDataBase(this.sqliteReportDb.getPathString(), journalMode));
        getSqLiteDB().createSchema();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void createRuleAndRuleMapping() {
        /*
            r6 = this;
            r0 = r6
            cn.sast.api.config.MainConfig r0 = r0.mainConfig
            cn.sast.common.IResFile r0 = r0.getRule_sort_yaml()
            r7 = r0
            r0 = r6
            cn.sast.api.config.MainConfig r0 = r0.mainConfig
            kotlin.Lazy r0 = r0.getCheckerInfo()
            r1 = r0
            if (r1 == 0) goto L1f
            java.lang.Object r0 = r0.getValue()
            cn.sast.api.config.CheckerInfoGenResult r0 = (cn.sast.api.config.CheckerInfoGenResult) r0
            r1 = r0
            if (r1 != 0) goto L37
        L1f:
        L20:
            r0 = r6
            cn.sast.framework.report.SqliteDiagnostics r0 = (cn.sast.framework.report.SqliteDiagnostics) r0
            r9 = r0
            r0 = 0
            r10 = r0
            mu.KLogger r0 = cn.sast.framework.report.SqliteDiagnostics.logger
            void r1 = cn.sast.framework.report.SqliteDiagnostics::createRuleAndRuleMapping$lambda$1$lambda$0
            r0.warn(r1)
            r0 = 0
        L37:
            r8 = r0
            r0 = r7
            if (r0 == 0) goto L40
            r0 = r8
            if (r0 != 0) goto L50
        L40:
            mu.KLogger r0 = cn.sast.framework.report.SqliteDiagnostics.logger
            void r1 = cn.sast.framework.report.SqliteDiagnostics::createRuleAndRuleMapping$lambda$2
            r0.warn(r1)
            goto L75
        L50:
            r0 = r6
            cn.sast.framework.report.RuleAndRuleMapping r1 = new cn.sast.framework.report.RuleAndRuleMapping
            r2 = r1
            r3 = r8
            r4 = r7
            java.nio.file.Path r4 = r4.getPath()
            r2.<init>(r3, r4)
            r0.ruleAndRuleMapping = r1
            r0 = r6
            cn.sast.framework.report.RuleAndRuleMapping r0 = r0.ruleAndRuleMapping
            r1 = r0
            if (r1 == 0) goto L74
            r1 = r6
            cn.sast.framework.report.sqldelight.Database r1 = r1.getDatabase()
            r0.insert(r1)
            goto L75
        L74:
        L75:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.SqliteDiagnostics.createRuleAndRuleMapping():void");
    }

    private static final Object createRuleAndRuleMapping$lambda$1$lambda$0() {
        return "The checkerInfo field of mainConfig is null";
    }

    private static final Object createRuleAndRuleMapping$lambda$2() {
        return "rule_sort.yaml is not exists";
    }

    private final void verify(ExecutableQuery<?> executableQuery, String name) {
        List it = executableQuery.executeAsList();
        if (!it.isEmpty()) {
            logger.error(() -> {
                return verify$lambda$4$lambda$3(r1, r2);
            });
        }
    }

    private static final Object verify$lambda$4$lambda$3(String $name, List $it) {
        return "reference of " + $name + ": " + $it + " is not exists in the parent table";
    }

    public final void verify() {
        verify(getDatabase().getRuleMappingQueries().verify_rule_name(), "RuleMapping.rule_name");
        verify(getDatabase().getDiagnosticQueries().verify_rule_name(), "diagnostic.rule_name");
        verify(getDatabase().getDiagnosticQueries().verify_file(), "diagnostic.__file_id");
        verify(getDatabase().getDiagnosticQueries().verify_note_path(), "diagnostic.__note_array_hash_id");
        verify(getDatabase().getDiagnosticQueries().verify_control_flow_path(), "diagnostic.__control_flow_array_hash_id");
        verify(getDatabase().getDiagnosticQueries().verify_macro(), "diagnostic.__macro_note_set_hash_id");
        verify(getDatabase().getNotePathQueries().verify_note(), "NotePath.__note_id");
        verify(getDatabase().getControlFlowPathQueries().verify_control_flow(), "ControlFlowPath.__control_flow_id");
        verify(getDatabase().getMacroExpansionQueries().verify_note(), "MacroExpansion.__macro_note_id");
        verify(getDatabase().getControlFlowQueries().verify_file(), "ControlFlow.__file_id");
        verify(getDatabase().getNoteQueries().verify_file(), "Note.__file_id");
        verify(getDatabase().getAnalyzeResultFileQueries().verify_file(), "AnalyzeResultFile.__file_id");
        verify(getDatabase().getAbsoluteFilePathQueries().verify_absolute_file_path(), "AbsoluteFilePath.__file_id");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        verify();
        getSqLiteDB().close();
        this._sdb.uninitialized();
        this.noteHashIdAutoIncrement.clear();
        this.ctrlFlowHashIdAutoIncrement.clear();
        this.fileCache.cleanUp();
        this.fileIdMap.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object flush$suspendImpl(cn.sast.framework.report.SqliteDiagnostics r8, java.util.List<cn.sast.api.report.Report> r9, java.lang.String r10, cn.sast.framework.report.IProjectFileLocator r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r0 = r12
            boolean r0 = r0 instanceof cn.sast.framework.report.SqliteDiagnostics.AnonymousClass1
            if (r0 == 0) goto L29
            r0 = r12
            cn.sast.framework.report.SqliteDiagnostics$flush$1 r0 = (cn.sast.framework.report.SqliteDiagnostics.AnonymousClass1) r0
            r15 = r0
            r0 = r15
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L29
            r0 = r15
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L35
        L29:
            cn.sast.framework.report.SqliteDiagnostics$flush$1 r0 = new cn.sast.framework.report.SqliteDiagnostics$flush$1
            r1 = r0
            r2 = r8
            r3 = r12
            r1.<init>(r3)
            r15 = r0
        L35:
            r0 = r15
            java.lang.Object r0 = r0.result
            r14 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r16 = r0
            r0 = r15
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto L7a;
                default: goto Laf;
            }
        L5c:
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
            r1 = r9
            r2 = r11
            r3 = r10
            r4 = r15
            r5 = r15
            r6 = 1
            r5.label = r6     // Catch: java.lang.Exception -> L86
            java.lang.Object r0 = r0.serializeReportsToDb(r1, r2, r3, r4)     // Catch: java.lang.Exception -> L86
            r1 = r0
            r2 = r16
            if (r1 != r2) goto L82
            r1 = r16
            return r1
        L7a:
            r0 = r14
            kotlin.ResultKt.throwOnFailure(r0)     // Catch: java.lang.Exception -> L86
            r0 = r14
        L82:
            goto Lab
        L86:
            r13 = move-exception
            mu.KLogger r0 = cn.sast.framework.report.SqliteDiagnostics.logger
            r1 = r13
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r2 = r13
            java.lang.Object r2 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return flush$lambda$5(r2);
            }
            r0.debug(r1, r2)
            mu.KLogger r0 = cn.sast.framework.report.SqliteDiagnostics.logger
            r1 = r13
            java.lang.Object r1 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return flush$lambda$6(r1);
            }
            r0.error(r1)
        Lab:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Laf:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.SqliteDiagnostics.flush$suspendImpl(cn.sast.framework.report.SqliteDiagnostics, java.util.List, java.lang.String, cn.sast.framework.report.IProjectFileLocator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object flush$lambda$5(Exception $e) {
        return "There are some errors when serialize data to sqlite: " + $e.getMessage();
    }

    private static final Object flush$lambda$6(Exception $e) {
        return "There are some errors when serialize data to sqlite: " + $e.getMessage();
    }

    @Override // cn.sast.framework.report.IMetadataVisitor
    public void visit(@NotNull AnalysisMetadata analysisMetadata) {
        Intrinsics.checkNotNullParameter(analysisMetadata, "analysisMetadata");
        if (this.monitor != null) {
            Transacter.DefaultImpls.transaction$default(getDatabase(), false, (v2) -> {
                return visit$lambda$7(r2, r3, v2);
            }, 1, (Object) null);
        }
    }

    private static final Unit visit$lambda$7(SqliteDiagnostics this$0, AnalysisMetadata $analysisMetadata, TransactionWithoutReturn $this$transaction) {
        Intrinsics.checkNotNullParameter($this$transaction, "$this$transaction");
        this$0.createAnalyzerStatistics($analysisMetadata, this$0.monitor);
        return Unit.INSTANCE;
    }

    /* compiled from: SqliteDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "SqliteDiagnostics.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.SqliteDiagnostics$serializeReportsToDb$2")
    /* renamed from: cn.sast.framework.report.SqliteDiagnostics$serializeReportsToDb$2, reason: invalid class name */
    /* loaded from: SqliteDiagnostics$serializeReportsToDb$2.class */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ List<Report> $reports;
        final /* synthetic */ IProjectFileLocator $locator;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(List<Report> list, IProjectFileLocator $locator, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$reports = list;
            this.$locator = $locator;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return SqliteDiagnostics.this.new AnonymousClass2(this.$reports, this.$locator, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    Database database = SqliteDiagnostics.this.getDatabase();
                    List<Report> list = this.$reports;
                    SqliteDiagnostics sqliteDiagnostics = SqliteDiagnostics.this;
                    IProjectFileLocator iProjectFileLocator = this.$locator;
                    Transacter.DefaultImpls.transaction$default(database, false, (v3) -> {
                        return invokeSuspend$lambda$0(r2, r3, r4, v3);
                    }, 1, (Object) null);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }

        private static final Unit invokeSuspend$lambda$0(List $reports, SqliteDiagnostics this$0, IProjectFileLocator $locator, TransactionWithoutReturn $this$transaction) {
            Iterator it = $reports.iterator();
            while (it.hasNext()) {
                Report report = (Report) it.next();
                this$0.createDiagnostic($locator, report);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object serializeReportsToDb(List<Report> list, IProjectFileLocator locator, String filename, Continuation<? super Unit> continuation) {
        Object objWithContext = BuildersKt.withContext(this.writeDispatcher, new AnonymousClass2(list, locator, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    private final FileID createFileCached(IProjectFileLocator locator, IBugResInfo file) {
        IResFile location;
        Object obj;
        IResFile iResFile = locator.get(file, EmptyWrapperFileGenerator.INSTANCE);
        if (iResFile == null) {
            return null;
        }
        IResFile absolute = iResFile.getAbsolute();
        if (absolute == null || (location = absolute.getNormalize()) == null) {
            return null;
        }
        Map $this$getOrPut$iv = this.fileIdMap;
        Object value$iv = $this$getOrPut$iv.get(location);
        if (value$iv == null) {
            Object obj2 = this.fileCache.get(location);
            Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
            FileX f = (FileX) OptionalsKt.getOrNull((Optional) obj2);
            if (f == null) {
                return null;
            }
            FileID fileID = new FileID(f.insert(getDatabase()).getId(), f.getAssociateAbsFile());
            $this$getOrPut$iv.put(location, fileID);
            obj = fileID;
        } else {
            obj = value$iv;
        }
        return (FileID) obj;
    }

    private final FileX.ID createFileXCached(IProjectFileLocator locator, IBugResInfo file) {
        IResFile location;
        IResFile iResFile = locator.get(file, EmptyWrapperFileGenerator.INSTANCE);
        if (iResFile == null) {
            return null;
        }
        IResFile absolute = iResFile.getAbsolute();
        if (absolute == null || (location = absolute.getNormalize()) == null) {
            return null;
        }
        return createFileXCachedFromAbsFile(location);
    }

    private final FileX.ID createFileXCachedFromAbsFile(IResFile absFile) {
        Object obj;
        Object obj2 = this.fileCache.get(absFile);
        Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
        FileX f = (FileX) OptionalsKt.getOrNull((Optional) obj2);
        if (f == null) {
            return null;
        }
        Map $this$getOrPut$iv = this.fileIdMap;
        Object value$iv = $this$getOrPut$iv.get(absFile);
        if (value$iv == null) {
            FileID fileID = new FileID(f.insert(getDatabase()).getId(), f.getAssociateAbsFile());
            $this$getOrPut$iv.put(absFile, fileID);
            obj = fileID;
        } else {
            obj = value$iv;
        }
        FileID id = (FileID) obj;
        return f.withId(id.getId());
    }

    @Nullable
    public final FileX.ID createFileXCachedFromFile(@NotNull IResFile file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return createFileXCachedFromAbsFile(file.getAbsolute().getNormalize());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FileX createFile(IResFile absFile) throws IOException {
        byte[] bytesContent = ResourceKt.readAllBytes(absFile);
        String hash = ResourceImplKt.calculate(bytesContent, "sha256");
        Charset encoding = getSourceEncoding(absFile);
        List lines = StringsKt.lines(new String(bytesContent, encoding));
        IResource original = Resource.INSTANCE.getOriginFileFromExpandAbsPath(absFile);
        MainConfig.RelativePath relativePath = this.mainConfig.tryGetRelativePathFromAbsolutePath(original);
        String strRemovePrefix = StringsKt.removePrefix(relativePath.getRelativePath(), "/");
        long size = lines.size();
        String strName = encoding.name();
        Intrinsics.checkNotNullExpressionValue(strName, "name(...)");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = strName.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        File it = new File(-1L, hash, strRemovePrefix, size, lowerCase, bytesContent.length, bytesContent);
        return new FileX(it, relativePath, absFile, lines);
    }

    private final Rule getAssociateChecker(Report $this$associateChecker) {
        RuleAndRuleMapping ruleAndRuleMapping = this.ruleAndRuleMapping;
        if (ruleAndRuleMapping != null) {
            Map<String, Rule> id2checkerMap = ruleAndRuleMapping.getId2checkerMap();
            if (id2checkerMap != null) {
                return id2checkerMap.get($this$associateChecker.getCheck_name());
            }
        }
        return null;
    }

    private final String lineContent(FileX $this$lineContent, int line) {
        String it = (String) CollectionsKt.getOrNull($this$lineContent.getLines(), line - 1);
        if (it == null) {
            return null;
        }
        if (it.length() <= 384) {
            return it;
        }
        String strSubstring = it.substring(0, 384);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    private final ValueWithId<Region> createRegion(IProjectFileLocator locator, IBugResInfo res, com.feysh.corax.config.api.report.Region region) {
        FileID file = createFileCached(locator, res);
        if (file == null) {
            return null;
        }
        Region it = new Region(0L, file.getId(), region.startLine, Long.valueOf(region.startColumn), Long.valueOf(region.getEndLine()), Long.valueOf(region.getEndColumn()));
        getDatabase().getRegionQueries().insert(it.get__file_id(), it.getStart_line(), it.getStart_column(), it.getEnd_line(), it.getEnd_column());
        long id = ((Number) CollectionsKt.first(getDatabase().getRegionQueries().id(it.get__file_id(), it.getStart_line(), it.getStart_column(), it.getEnd_line(), it.getEnd_column()).executeAsList())).longValue();
        return new ValueWithId<>(id, it);
    }

    private final com.feysh.corax.config.api.report.Region getFuncRange(IBugResInfo classInfo, int line) {
        BodyDeclaration memberAtLine;
        if (!(classInfo instanceof ClassResInfo)) {
            return null;
        }
        if (this.info != null) {
            SootInfoCache $this$getFuncRange_u24lambda_u2413 = this.info;
            memberAtLine = $this$getFuncRange_u24lambda_u2413.getMemberAtLine(((ClassResInfo) classInfo).getSc(), line);
        } else {
            memberAtLine = null;
        }
        BodyDeclaration member = memberAtLine;
        if (member != null) {
            Region.Companion companion = com.feysh.corax.config.api.report.Region.Companion;
            Optional range = member.getRange();
            Intrinsics.checkNotNullExpressionValue(range, "getRange(...)");
            com.feysh.corax.config.api.report.Region range2 = companion.invoke_Optional_Range(range);
            if (range2 != null) {
                return range2;
            }
        }
        SootMethodAndRange range3 = SootLineToMethodMapFactory.getSootMethodAtLine$default(SootLineToMethodMapFactory.INSTANCE, ((ClassResInfo) classInfo).getSc(), line, false, 4, (Object) null);
        if (range3 == null) {
            return null;
        }
        Pair it = range3.getRange();
        com.feysh.corax.config.api.report.Region this_$iv = new com.feysh.corax.config.api.report.Region(((Number) it.getFirst()).intValue() - 1, 0, ((Number) it.getSecond()).intValue() + 1, 0);
        if (this_$iv.startLine >= 0) {
            return this_$iv;
        }
        return null;
    }

    private final ValueWithId<Note> createNote(IProjectFileLocator locator, BugPathEvent event) {
        FileID file = createFileCached(locator, event.getClassname());
        if (file == null) {
            return null;
        }
        com.feysh.corax.config.api.report.Region this_$iv = event.getRegion();
        com.feysh.corax.config.api.report.Region it = this_$iv.startColumn >= 0 && this_$iv.getEndColumn() >= 0 ? this_$iv.startLine >= 0 ? this_$iv : null : null;
        ValueWithId noticesRegion = it != null ? createRegion(locator, event.getClassname(), it) : null;
        com.feysh.corax.config.api.report.Region it2 = getFuncRange(event.getClassname(), event.getRegion().startLine);
        ValueWithId funcRange = it2 != null ? createRegion(locator, event.getClassname(), it2) : null;
        long id = file.getId();
        String fileAbsPath = file.getFileAbsPath();
        long j = event.getRegion().startLine;
        Long lValueOf = Long.valueOf(event.getRegion().startColumn);
        String str = event.getMessage().get(Language.EN);
        if (str == null) {
            str = "";
        }
        String str2 = event.getMessage().get(Language.ZH);
        if (str2 == null) {
            str2 = "";
        }
        Note it3 = new Note(0L, "event", "Below", id, fileAbsPath, j, lValueOf, str, str2, noticesRegion != null ? Long.valueOf(noticesRegion.getId()) : null, funcRange != null ? Long.valueOf(funcRange.getId()) : null);
        getDatabase().getNoteQueries().insert(it3.getKind(), it3.getDisplay_hint(), it3.get__file_id(), it3.get_file_abs_path(), it3.getLine(), it3.getColumn(), it3.getMessage_en(), it3.getMessage_zh(), it3.get__notices_region_id(), it3.get__func_region_id());
        long id2 = ((Number) CollectionsKt.first(getDatabase().getNoteQueries().id(it3.getKind(), it3.getDisplay_hint(), it3.get__file_id(), it3.get_file_abs_path(), it3.getLine(), it3.getColumn(), it3.getMessage_en(), it3.getMessage_zh(), it3.get__notices_region_id(), it3.get__func_region_id()).executeAsList())).longValue();
        return new ValueWithId<>(id2, it3);
    }

    private final ValueWithId<ControlFlow> createControlFlow(IProjectFileLocator locator, BugPathEvent event) {
        return null;
    }

    /* compiled from: SqliteDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\n\u0018��2\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\n \r*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcn/sast/framework/report/SqliteDiagnostics$ArrayHashIdGenerator;", "", "hashIdAutoIncrement", "", "", "", "<init>", "(Ljava/util/Map;)V", "array", "", "getArray", "()Ljava/util/List;", "md5", "kotlin.jvm.PlatformType", "getMd5", "()Ljava/lang/String;", "arrayId", "getArrayId", "()Ljava/lang/Long;", "corax-framework"})
    @SourceDebugExtension({"SMAP\nSqliteDiagnostics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SqliteDiagnostics.kt\ncn/sast/framework/report/SqliteDiagnostics$ArrayHashIdGenerator\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,813:1\n381#2,7:814\n*S KotlinDebug\n*F\n+ 1 SqliteDiagnostics.kt\ncn/sast/framework/report/SqliteDiagnostics$ArrayHashIdGenerator\n*L\n566#1:814,7\n*E\n"})
    /* loaded from: SqliteDiagnostics$ArrayHashIdGenerator.class */
    public static final class ArrayHashIdGenerator {

        @NotNull
        private final Map<String, Long> hashIdAutoIncrement;

        @NotNull
        private final List<String> array;

        public ArrayHashIdGenerator(@NotNull Map<String, Long> map) {
            Intrinsics.checkNotNullParameter(map, "hashIdAutoIncrement");
            this.hashIdAutoIncrement = map;
            this.array = new ArrayList();
        }

        @NotNull
        public final List<String> getArray() {
            return this.array;
        }

        private final String getMd5() {
            MessageDigest md5 = StringUtilKt.getMd5();
            byte[] bytes = CollectionsKt.joinToString$default(this.array, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return new BigInteger(1, md5.digest(bytes)).toString(16);
        }

        @Nullable
        public final Long getArrayId() {
            Object obj;
            Long l;
            Long l2;
            synchronized (this.hashIdAutoIncrement) {
                if (this.array.isEmpty()) {
                    l = null;
                } else {
                    long id = this.hashIdAutoIncrement.size() + 1;
                    Map $this$getOrPut$iv = this.hashIdAutoIncrement;
                    String md5 = getMd5();
                    Object value$iv = $this$getOrPut$iv.get(md5);
                    if (value$iv == null) {
                        Long lValueOf = Long.valueOf(id);
                        $this$getOrPut$iv.put(md5, lValueOf);
                        obj = lValueOf;
                    } else {
                        obj = value$iv;
                    }
                    l = (Long) obj;
                }
                l2 = l;
            }
            return l2;
        }
    }

    private final Long createControlFlowPath(IProjectFileLocator locator, List<BugPathEvent> list) {
        List<BugPathEvent> $this$associateWith$iv = list;
        Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv : $this$associateWith$iv) {
            BugPathEvent it = (BugPathEvent) element$iv$iv;
            result$iv.put(element$iv$iv, createControlFlow(locator, it));
        }
        Map path2controlFlow = result$iv;
        ArrayHashIdGenerator arrayHashIdG = new ArrayHashIdGenerator(this.ctrlFlowHashIdAutoIncrement);
        long i = 0;
        Iterable array = (List) new ArrayList();
        Iterator it2 = path2controlFlow.entrySet().iterator();
        while (it2.hasNext()) {
            ValueWithId controlFlow = (ValueWithId) ((Map.Entry) it2.next()).getValue();
            if (controlFlow != null) {
                long j = i;
                i = j + 1;
                ControlFlowPath control = new ControlFlowPath(-1L, j, controlFlow.getId());
                ((Collection) array).add(control);
                List $this$createControlFlowPath_u24lambda_u2419 = arrayHashIdG.getArray();
                $this$createControlFlowPath_u24lambda_u2419.add(String.valueOf(control.get__control_flow_id()));
                $this$createControlFlowPath_u24lambda_u2419.add(String.valueOf(control.getControl_flow_sequence()));
            }
        }
        Long arrayId = arrayHashIdG.getArrayId();
        if (arrayId == null) {
            return null;
        }
        long arrayId2 = arrayId.longValue();
        Iterable $this$forEach$iv = array;
        for (Object element$iv : $this$forEach$iv) {
            ControlFlowPath it3 = (ControlFlowPath) element$iv;
            getDatabase().getControlFlowPathQueries().insert(ControlFlowPath.copy$default(it3, arrayId2, 0L, 0L, 6, null));
        }
        return Long.valueOf(arrayId2);
    }

    private final Long createNotePath(IProjectFileLocator locator, List<BugPathEvent> list) {
        List<BugPathEvent> $this$associateWith$iv = list;
        Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv : $this$associateWith$iv) {
            BugPathEvent it = (BugPathEvent) element$iv$iv;
            result$iv.put(element$iv$iv, createNote(locator, it));
        }
        Map event2note = result$iv;
        ArrayHashIdGenerator arrayHashIdG = new ArrayHashIdGenerator(this.noteHashIdAutoIncrement);
        long i = 0;
        Iterable array = (List) new ArrayList();
        for (Map.Entry entry : event2note.entrySet()) {
            BugPathEvent event = (BugPathEvent) entry.getKey();
            ValueWithId note = (ValueWithId) entry.getValue();
            if (note != null) {
                long j = i;
                i = j + 1;
                NotePath notePath = new NotePath(-1L, j, event.getStackDepth() != null ? Long.valueOf(r4.intValue()) : null, 0L, note.getId());
                ((Collection) array).add(notePath);
                List $this$createNotePath_u24lambda_u2422 = arrayHashIdG.getArray();
                $this$createNotePath_u24lambda_u2422.add(String.valueOf(notePath.getNote_sequence()));
                $this$createNotePath_u24lambda_u2422.add(String.valueOf(notePath.getNote_stack_depth()));
                $this$createNotePath_u24lambda_u2422.add(String.valueOf(notePath.getNote_is_key_event()));
                $this$createNotePath_u24lambda_u2422.add(String.valueOf(notePath.get__note_id()));
            }
        }
        Long arrayId = arrayHashIdG.getArrayId();
        if (arrayId == null) {
            return null;
        }
        long arrayId2 = arrayId.longValue();
        Iterable $this$forEach$iv = array;
        for (Object element$iv : $this$forEach$iv) {
            NotePath it2 = (NotePath) element$iv;
            getDatabase().getNotePathQueries().insert(NotePath.copy$default(it2, arrayId2, 0L, null, null, 0L, 30, null));
        }
        return Long.valueOf(arrayId2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ValueWithId<Diagnostic> createDiagnostic(IProjectFileLocator locator, Report report) {
        FileX.ID file = createFileXCached(locator, report.getBugResFile());
        if (file == null) {
            return null;
        }
        if (report.getPathEvents().isEmpty()) {
            logger.error(() -> {
                return createDiagnostic$lambda$24(r1);
            });
            return null;
        }
        BugPathEvent lastPathEvents = (BugPathEvent) CollectionsKt.last(report.getPathEvents());
        Long lCreateNotePath = createNotePath(locator, report.getPathEvents());
        if (lCreateNotePath == null) {
            logger.error(() -> {
                return createDiagnostic$lambda$26$lambda$25(r1);
            });
            return null;
        }
        long noteArrayHashId = lCreateNotePath.longValue();
        Long controlFlowArrayHashId = createControlFlowPath(locator, report.getNotes());
        int line = lastPathEvents.getRegion().startLine;
        String check_name = report.getCheck_name();
        Rule associateChecker = getAssociateChecker(report);
        String short_description_zh = associateChecker != null ? associateChecker.getShort_description_zh() : null;
        Long lValueOf = Long.valueOf(file.getId());
        String fileAbsPath = file.getFile().getFileAbsPath();
        long j = line;
        long j2 = lastPathEvents.getRegion().startColumn;
        String str = report.getMessage().get(Language.EN);
        if (str == null) {
            str = "";
        }
        String str2 = report.getMessage().get(Language.ZH);
        if (str2 == null) {
            str2 = "";
        }
        String severity = report.getSeverity();
        if (severity == null) {
            Rule associateChecker2 = getAssociateChecker(report);
            severity = associateChecker2 != null ? associateChecker2.getSeverity() : null;
            if (severity == null) {
                severity = "None";
            }
        }
        Diagnostic it = new Diagnostic(0L, check_name, short_description_zh, lValueOf, fileAbsPath, j, j2, str, str2, severity, null, null, null, null, null, lineContent(file.getFile(), line), noteArrayHashId, controlFlowArrayHashId, null);
        getDatabase().getDiagnosticQueries().insert(it.getRule_name(), it.get_rule_short_description_zh(), it.get__file_id(), it.get_file_abs_path(), it.get_line(), it.get_column(), it.get_message_en(), it.get_message_zh(), it.getSeverity(), it.getPrecision(), it.getLikelihood(), it.getImpact(), it.getTechnique(), it.getAnalysis_scope(), it.getLine_content(), it.get__note_array_hash_id(), it.get__control_flow_array_hash_id(), it.get__macro_note_set_hash_id());
        long id = ((Number) CollectionsKt.first(getDatabase().getDiagnosticQueries().id(it.getRule_name(), it.get_rule_short_description_zh(), it.get__file_id(), it.get_file_abs_path(), it.getSeverity(), it.getPrecision(), it.getLikelihood(), it.getImpact(), it.getTechnique(), it.getAnalysis_scope(), it.getLine_content(), it.get__note_array_hash_id(), it.get__control_flow_array_hash_id(), it.get__macro_note_set_hash_id()).executeAsList())).longValue();
        return new ValueWithId<>(id, it);
    }

    private static final Object createDiagnostic$lambda$24(Report $report) {
        return "Report.pathEvents is empty! report: " + $report;
    }

    private static final Object createDiagnostic$lambda$26$lambda$25(Report $report) {
        return "invalid report: " + $report;
    }

    private final Set<AnalyzerStatistics> getAnalyzerStatisticsSet(List<Tool> list) {
        Set analyzerStatisticsSet = new LinkedHashSet();
        List<Tool> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            Tool it = (Tool) element$iv;
            Iterable $this$forEach$iv2 = it.getAnalyzers().values();
            for (Object element$iv2 : $this$forEach$iv2) {
                Analyzer analyzer = (Analyzer) element$iv2;
                analyzerStatisticsSet.add(analyzer.getAnalyzerStatistics());
            }
        }
        return analyzerStatisticsSet;
    }

    static /* synthetic */ AnalyzerResultFile createAnalyzerResultFile$default(SqliteDiagnostics sqliteDiagnostics, IResFile iResFile, String str, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createAnalyzerResultFile");
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return sqliteDiagnostics.createAnalyzerResultFile(iResFile, str);
    }

    private final AnalyzerResultFile createAnalyzerResultFile(IResFile file, String fileName) {
        IResFile absFile = file.getAbsolute().getNormalize();
        FileX.ID fileX = createFileXCachedFromAbsFile(absFile);
        if (fileX == null) {
            return null;
        }
        String strRemovePrefix = fileName;
        if (strRemovePrefix == null) {
            strRemovePrefix = StringsKt.removePrefix(this.mainConfig.tryGetRelativePathFromAbsolutePath(absFile).getRelativePath(), "/");
        }
        AnalyzerResultFile it = new AnalyzerResultFile(strRemovePrefix, absFile.getPathString(), fileX.getId());
        getDatabase().getAnalyzeResultFileQueries().insert(it);
        return it;
    }

    public final void writeAnalyzerResultFiles() {
        Transacter.DefaultImpls.transaction$default(getDatabase(), false, (v1) -> {
            return writeAnalyzerResultFiles$lambda$32(r2, v1);
        }, 1, (Object) null);
    }

    private static final Unit writeAnalyzerResultFiles$lambda$32(SqliteDiagnostics this$0, TransactionWithoutReturn $this$transaction) {
        Intrinsics.checkNotNullParameter($this$transaction, "$this$transaction");
        IResDirectory out = this$0.mainConfig.getOutput_dir();
        Iterable $this$forEach$iv = CollectionsKt.listOf(new Pair[]{TuplesKt.to(AnalyzerEnv.INSTANCE.getLastLogFile(), "详细引擎日志"), TuplesKt.to(out.resolve("project-env/versions.txt"), "项目依赖库版本信息"), TuplesKt.to(out.resolve("metrics.yml"), "ProfileMetrics"), TuplesKt.to(out.resolve("phantom_dependence_classes.txt"), "phantom_dependence_classes"), TuplesKt.to(out.resolve("source_files_which_class_not_found.txt"), "source_files_which_class_not_found"), TuplesKt.to(out.resolve("undefined_summary_methods.txt"), "无Taint配置的方法"), TuplesKt.to(out.resolve(ProjectConfig.RECORD_FILE_NAME), "黑白名单分类结果"), TuplesKt.to(out.resolve("top.log"), "进程监控日志")});
        for (Object element$iv : $this$forEach$iv) {
            Pair pair = (Pair) element$iv;
            IResource path = (IResource) pair.component1();
            String name = (String) pair.component2();
            IResFile file = path.toFile();
            if (file.getExists()) {
                this$0.createAnalyzerResultFile(file, name);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02a5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final cn.sast.framework.report.sqldelight.AnalyzerStatistics createAnalyzerStatistics(cn.sast.framework.report.metadata.AnalysisMetadata r44, cn.sast.framework.metrics.MetricsMonitor r45) {
        /*
            Method dump skipped, instructions count: 799
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.SqliteDiagnostics.createAnalyzerStatistics(cn.sast.framework.report.metadata.AnalysisMetadata, cn.sast.framework.metrics.MetricsMonitor):cn.sast.framework.report.sqldelight.AnalyzerStatistics");
    }

    private static final CharSequence createAnalyzerStatistics$lambda$35(Tool it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getWorkingDirectory();
    }

    private static final CharSequence createAnalyzerStatistics$lambda$36(Tool it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getOutputPath();
    }

    private static final CharSequence createAnalyzerStatistics$lambda$37(Tool it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getProjectRoot();
    }

    /* compiled from: SqliteDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\r"}, d2 = {"Lcn/sast/framework/report/SqliteDiagnostics$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "jsonFormat", "Lkotlinx/serialization/json/Json;", "openDataBase", "Lcn/sast/framework/report/SQLiteDB;", "fullPath", "", "journalMode", "corax-framework"})
    /* loaded from: SqliteDiagnostics$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final SQLiteDB openDataBase(@NotNull String fullPath, @NotNull String journalMode) {
            Intrinsics.checkNotNullParameter(fullPath, "fullPath");
            Intrinsics.checkNotNullParameter(journalMode, "journalMode");
            return SQLiteDB.Companion.openDataBase(fullPath, journalMode);
        }

        public static /* synthetic */ SQLiteDB openDataBase$default(Companion companion, String str, String str2, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = ExtSettings.INSTANCE.getSqliteJournalMode();
            }
            return companion.openDataBase(str, str2);
        }
    }

    private static final Unit logger$lambda$40() {
        return Unit.INSTANCE;
    }

    private static final Unit jsonFormat$lambda$41(JsonBuilder $this$Json) {
        Intrinsics.checkNotNullParameter($this$Json, "$this$Json");
        $this$Json.setUseArrayPolymorphism(true);
        $this$Json.setPrettyPrint(true);
        $this$Json.setEncodeDefaults(false);
        return Unit.INSTANCE;
    }
}
