package cn.sast.framework.report.coverage;

import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.IProjectFileLocator;
import cn.sast.framework.report.coverage.Coverage;
import cn.sast.framework.report.coverage.SourceCoverage;
import com.github.benmanes.caffeine.cache.AsyncCacheLoader;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.time.Instant;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.future.FutureKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IBundleCoverage;
import org.jacoco.core.analysis.ICoverageVisitor;
import org.jacoco.core.analysis.ISourceFileCoverage;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.ExecutionDataWriter;
import org.jacoco.core.data.IExecutionDataVisitor;
import org.jacoco.core.data.SessionInfo;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.internal.analysis.ClassAnalyzer;
import org.jacoco.core.internal.analysis.ClassCoverageImpl;
import org.jacoco.core.internal.analysis.Instruction;
import org.jacoco.core.internal.analysis.StringPool;
import org.jacoco.core.internal.flow.ClassProbesAdapter;
import org.jacoco.core.internal.flow.MethodProbesVisitor;
import org.jacoco.report.DirectorySourceFileLocator;
import org.jacoco.report.FileMultiReportOutput;
import org.jacoco.report.IReportVisitor;
import org.jacoco.report.ISourceFileLocator;
import org.jacoco.report.InputStreamSourceFileLocator;
import org.jacoco.report.MultiSourceFileLocator;
import org.jacoco.report.html.HTMLFormatter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.MethodNode;
import soot.jimple.infoflow.collect.ConcurrentHashSet;

/* compiled from: Coverage.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018�� ]2\u00020\u0001:\u0003[\\]B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0019J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u001bJ\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018J \u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u0018\u0010&\u001a\u0004\u0018\u00010\r2\u0006\u0010'\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010(J\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020,J&\u0010-\u001a\u00020\u00142\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0086@¢\u0006\u0002\u00103J\u000e\u00104\u001a\u000205H\u0086@¢\u0006\u0002\u00106J\u001a\u0010>\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020908H\u0086@¢\u0006\u0002\u00106J6\u0010?\u001a\u00020\u00142\u0006\u0010@\u001a\u0002092\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u0002002\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0086@¢\u0006\u0002\u0010EJ<\u0010?\u001a\u00020\u00142\u0006\u0010@\u001a\u0002092\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u0002002\f\u0010F\u001a\b\u0012\u0004\u0012\u00020H0G2\u0006\u0010C\u001a\u00020DH\u0086@¢\u0006\u0002\u0010IJ6\u0010?\u001a\u00020\u00142\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u0002002\u0006\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020H2\u0006\u0010C\u001a\u00020MH\u0086@¢\u0006\u0002\u0010NJ\u000e\u0010O\u001a\u0002092\u0006\u0010/\u001a\u000200J\u0016\u0010P\u001a\u00020B2\u0006\u0010L\u001a\u00020Q2\u0006\u0010R\u001a\u00020SJ&\u0010T\u001a\u00020\u00142\u0006\u0010L\u001a\u00020Q2\u0006\u0010U\u001a\u00020D2\u0006\u0010R\u001a\u00020SH\u0096@¢\u0006\u0002\u0010VJ\u0016\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u0002092\u0006\u0010A\u001a\u00020BJ\u001e\u0010W\u001a\u00020X2\u0006\u0010L\u001a\u00020Q2\u0006\u0010R\u001a\u00020SH\u0086@¢\u0006\u0002\u0010ZR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0006\u0012\u0004\u0018\u00010\r0%X\u0082\u0004¢\u0006\u0002\n��R(\u00107\u001a\u0010\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u000209\u0018\u000108X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=¨\u0006^"}, d2 = {"Lcn/sast/framework/report/coverage/Coverage;", "", "<init>", "()V", "startTimestamp", "Ljava/time/Instant;", "stringPool", "Lorg/jacoco/core/internal/analysis/StringPool;", "getStringPool", "()Lorg/jacoco/core/internal/analysis/StringPool;", "classCoverageMap", "", "", "Lcn/sast/framework/report/coverage/Coverage$ClassCoverage;", "getClassCoverageMap", "()Ljava/util/Map;", "coverQueue", "Lsoot/jimple/infoflow/collect/ConcurrentHashSet;", "Lcn/sast/framework/report/coverage/Coverage$JacocoCover;", "cover", "", "clazz", "Lcn/sast/framework/report/coverage/ClassSourceInfo;", "line", "", "(Lcn/sast/framework/report/coverage/ClassSourceInfo;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "className", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "([BILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coverByQueue", "createAnalyzingVisitor", "Lorg/objectweb/asm/ClassVisitor;", "classCoverage", "classId", "", "cache", "Lcom/github/benmanes/caffeine/cache/AsyncLoadingCache;", "analyzeClass", "source", "(Lcn/sast/framework/report/coverage/ClassSourceInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSessionInfo", "Lorg/jacoco/core/data/SessionInfo;", "getSessionInfoStore", "Lorg/jacoco/core/data/SessionInfoStore;", "flushExecutionDataFile", "sessionInfoStore", "executionDataStore", "Lorg/jacoco/core/data/ExecutionDataStore;", "dumpFile", "Lcn/sast/common/IResFile;", "(Lorg/jacoco/core/data/SessionInfoStore;Lorg/jacoco/core/data/ExecutionDataStore;Lcn/sast/common/IResFile;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processCoverQueueData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coverageBuilderPair", "Lkotlin/Pair;", "Lorg/jacoco/core/analysis/CoverageBuilder;", "getCoverageBuilderPair", "()Lkotlin/Pair;", "setCoverageBuilderPair", "(Lkotlin/Pair;)V", "computeCoverageBuilder", "createReport", "coverageBuilder", "mLocator", "Lorg/jacoco/report/MultiSourceFileLocator;", "reportDirectory", "Lcn/sast/common/IResDirectory;", "(Lorg/jacoco/core/analysis/CoverageBuilder;Lorg/jacoco/core/data/SessionInfoStore;Lorg/jacoco/core/data/ExecutionDataStore;Lorg/jacoco/report/MultiSourceFileLocator;Lcn/sast/common/IResDirectory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sourceDirectory", "", "Lorg/jacoco/report/ISourceFileLocator;", "(Lorg/jacoco/core/analysis/CoverageBuilder;Lorg/jacoco/core/data/SessionInfoStore;Lorg/jacoco/core/data/ExecutionDataStore;Ljava/util/List;Lcn/sast/common/IResDirectory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bundleCoverage", "Lorg/jacoco/core/analysis/IBundleCoverage;", "locator", "Ljava/io/File;", "(Lorg/jacoco/core/data/SessionInfoStore;Lorg/jacoco/core/data/ExecutionDataStore;Lorg/jacoco/core/analysis/IBundleCoverage;Lorg/jacoco/report/ISourceFileLocator;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "makeCoverageBuilder", "getMultiSourceFileLocator", "Lcn/sast/framework/report/IProjectFileLocator;", "encoding", "Ljava/nio/charset/Charset;", "flushCoverage", "outputDir", "(Lcn/sast/framework/report/IProjectFileLocator;Lcn/sast/common/IResDirectory;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateSourceCoverage", "Lcn/sast/framework/report/coverage/SourceCoverage;", "coverage", "(Lcn/sast/framework/report/IProjectFileLocator;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "JacocoCover", "ClassCoverage", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nCoverage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Coverage.kt\ncn/sast/framework/report/coverage/Coverage\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,490:1\n1863#2,2:491\n1863#2,2:493\n*S KotlinDebug\n*F\n+ 1 Coverage.kt\ncn/sast/framework/report/coverage/Coverage\n*L\n363#1:491,2\n420#1:493,2\n*E\n"})
/* loaded from: Coverage.class */
public class Coverage {

    @Nullable
    private Instant startTimestamp;

    @NotNull
    private final StringPool stringPool = new StringPool();

    @NotNull
    private final Map<String, ClassCoverage> classCoverageMap = new ConcurrentHashMap(1000);

    @NotNull
    private final ConcurrentHashSet<JacocoCover> coverQueue = new ConcurrentHashSet<>();

    @NotNull
    private final AsyncLoadingCache<ClassSourceInfo, ClassCoverage> cache;

    @Nullable
    private Pair<ExecutionDataStore, ? extends CoverageBuilder> coverageBuilderPair;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(Coverage::logger$lambda$5);

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "Coverage.kt", l = {261}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"this", "source"}, m = "analyzeClass", c = "cn.sast.framework.report.coverage.Coverage")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$analyzeClass$1, reason: invalid class name */
    /* loaded from: Coverage$analyzeClass$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Coverage.this.analyzeClass(null, (Continuation) this);
        }
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "Coverage.kt", l = {478}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1", "L$2"}, n = {"this", "locator", "encoding"}, m = "calculateSourceCoverage", c = "cn.sast.framework.report.coverage.Coverage")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$calculateSourceCoverage$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: Coverage$calculateSourceCoverage$1.class */
    static final class C00361 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        /* synthetic */ Object result;
        int label;

        C00361(Continuation<? super C00361> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Coverage.this.calculateSourceCoverage(null, null, (Continuation) this);
        }
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "Coverage.kt", l = {315}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "L$1"}, n = {"this", "coverageBuilderPair"}, m = "computeCoverageBuilder", c = "cn.sast.framework.report.coverage.Coverage")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$computeCoverageBuilder$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: Coverage$computeCoverageBuilder$1.class */
    static final class C00371 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C00371(Continuation<? super C00371> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Coverage.this.computeCoverageBuilder((Continuation) this);
        }
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "Coverage.kt", l = {141}, i = {PointsToGraphKt.pathStrictMod}, s = {"I$0"}, n = {"line"}, m = "cover", c = "cn.sast.framework.report.coverage.Coverage")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$cover$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: Coverage$cover$1.class */
    static final class C00381 extends ContinuationImpl {
        int I$0;
        /* synthetic */ Object result;
        int label;

        C00381(Continuation<? super C00381> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Coverage.this.cover((ClassSourceInfo) null, 0, (Continuation<? super Unit>) this);
        }
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "Coverage.kt", l = {148, 149}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, s = {"L$0", "I$0"}, n = {"this", "line"}, m = "cover", c = "cn.sast.framework.report.coverage.Coverage")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$cover$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: Coverage$cover$2.class */
    static final class C00392 extends ContinuationImpl {
        Object L$0;
        int I$0;
        /* synthetic */ Object result;
        int label;

        C00392(Continuation<? super C00392> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Coverage.this.cover((String) null, 0, (Continuation<? super Unit>) this);
        }
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "Coverage.kt", l = {378}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"reportDirectory"}, m = "createReport", c = "cn.sast.framework.report.coverage.Coverage")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$createReport$4, reason: invalid class name */
    /* loaded from: Coverage$createReport$4.class */
    static final class AnonymousClass4 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Coverage.this.createReport((SessionInfoStore) null, (ExecutionDataStore) null, (IBundleCoverage) null, (ISourceFileLocator) null, (File) null, (Continuation<? super Unit>) this);
        }
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "Coverage.kt", l = {433, 435, 439}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"}, n = {"$this", "locator", "outputDir", "encoding", "$this", "locator", "outputDir", "encoding", "executionDataStore", "coverageBuilder", "sessionInfoStore"}, m = "flushCoverage$suspendImpl", c = "cn.sast.framework.report.coverage.Coverage")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$flushCoverage$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: Coverage$flushCoverage$1.class */
    static final class C00401 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        /* synthetic */ Object result;
        int label;

        C00401(Continuation<? super C00401> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Coverage.flushCoverage$suspendImpl(Coverage.this, null, null, null, (Continuation) this);
        }
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "Coverage.kt", l = {305}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"this"}, m = "processCoverQueueData", c = "cn.sast.framework.report.coverage.Coverage")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$processCoverQueueData$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: Coverage$processCoverQueueData$1.class */
    static final class C00421 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        /* synthetic */ Object result;
        int label;

        C00421(Continuation<? super C00421> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return Coverage.this.processCoverQueueData((Continuation) this);
        }
    }

    @Nullable
    public Object flushCoverage(@NotNull IProjectFileLocator locator, @NotNull IResDirectory outputDir, @NotNull Charset encoding, @NotNull Continuation<? super Unit> continuation) {
        return flushCoverage$suspendImpl(this, locator, outputDir, encoding, continuation);
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0080\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcn/sast/framework/report/coverage/Coverage$JacocoCover;", "", "className", "", "line", "", "<init>", "(Ljava/lang/String;I)V", "getClassName", "()Ljava/lang/String;", "getLine", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "corax-framework"})
    /* loaded from: Coverage$JacocoCover.class */
    public static final class JacocoCover {

        @NotNull
        private final String className;
        private final int line;

        @NotNull
        public final String component1() {
            return this.className;
        }

        public final int component2() {
            return this.line;
        }

        @NotNull
        public final JacocoCover copy(@NotNull String className, int line) {
            Intrinsics.checkNotNullParameter(className, "className");
            return new JacocoCover(className, line);
        }

        public static /* synthetic */ JacocoCover copy$default(JacocoCover jacocoCover, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = jacocoCover.className;
            }
            if ((i2 & 2) != 0) {
                i = jacocoCover.line;
            }
            return jacocoCover.copy(str, i);
        }

        @NotNull
        public String toString() {
            return "JacocoCover(className=" + this.className + ", line=" + this.line + ")";
        }

        public int hashCode() {
            int result = this.className.hashCode();
            return (result * 31) + Integer.hashCode(this.line);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof JacocoCover)) {
                return false;
            }
            JacocoCover jacocoCover = (JacocoCover) other;
            return Intrinsics.areEqual(this.className, jacocoCover.className) && this.line == jacocoCover.line;
        }

        public JacocoCover(@NotNull String className, int line) {
            Intrinsics.checkNotNullParameter(className, "className");
            this.className = className;
            this.line = line;
        }

        @NotNull
        public final String getClassName() {
            return this.className;
        }

        public final int getLine() {
            return this.line;
        }
    }

    public Coverage() {
        Caffeine caffeineNewBuilder = Caffeine.newBuilder();
        final Function2 function2 = (v1, v2) -> {
            return cache$lambda$0(r2, v1, v2);
        };
        AsyncLoadingCache<ClassSourceInfo, ClassCoverage> asyncLoadingCacheBuildAsync = caffeineNewBuilder.buildAsync(new AsyncCacheLoader(function2) { // from class: cn.sast.framework.report.coverage.Coverage$sam$com_github_benmanes_caffeine_cache_AsyncCacheLoader$0
            private final /* synthetic */ Function2 function;

            {
                Intrinsics.checkNotNullParameter(function2, "function");
                this.function = function2;
            }

            public final /* synthetic */ CompletableFuture asyncLoad(Object p0, Executor p1) {
                return (CompletableFuture) this.function.invoke(p0, p1);
            }
        });
        Intrinsics.checkNotNullExpressionValue(asyncLoadingCacheBuildAsync, "buildAsync(...)");
        this.cache = asyncLoadingCacheBuildAsync;
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0012\n��\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010#\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018��2\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020\u0012J\u000e\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00130\u0011X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0014\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a8F@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001f*\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u0006("}, d2 = {"Lcn/sast/framework/report/coverage/Coverage$ClassCoverage;", "", "className", "", "byteArray", "", "classId", "", "<init>", "(Ljava/lang/String;[BJ)V", "getClassName", "()Ljava/lang/String;", "getByteArray", "()[B", "getClassId", "()J", "lineMap", "", "", "", "count", "getCount", "()I", "setCount", "(I)V", "value", "Ljava/util/BitSet;", "probes", "getProbes", "()Ljava/util/BitSet;", "predecessor", "Lorg/jacoco/core/internal/analysis/Instruction;", "getPredecessor", "(Lorg/jacoco/core/internal/analysis/Instruction;)Lorg/jacoco/core/internal/analysis/Instruction;", "addProbe", "", "instruction", "probeId", "cover", "line", "corax-framework"})
    @SourceDebugExtension({"SMAP\nCoverage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Coverage.kt\ncn/sast/framework/report/coverage/Coverage$ClassCoverage\n+ 2 ReportConverter.kt\ncn/sast/framework/report/ReportConverterKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,490:1\n38#2,3:491\n381#3,7:494\n*S KotlinDebug\n*F\n+ 1 Coverage.kt\ncn/sast/framework/report/coverage/Coverage$ClassCoverage\n*L\n110#1:491,3\n118#1:494,7\n*E\n"})
    /* loaded from: Coverage$ClassCoverage.class */
    public static final class ClassCoverage {

        @NotNull
        private final String className;

        @NotNull
        private final byte[] byteArray;
        private final long classId;

        @NotNull
        private final Map<Integer, Set<Integer>> lineMap;
        private int count;

        @NotNull
        private BitSet probes;

        public ClassCoverage(@NotNull String className, @NotNull byte[] byteArray, long classId) {
            Intrinsics.checkNotNullParameter(className, "className");
            Intrinsics.checkNotNullParameter(byteArray, "byteArray");
            this.className = className;
            this.byteArray = byteArray;
            this.classId = classId;
            this.lineMap = new LinkedHashMap(100);
            this.probes = new BitSet(0);
        }

        @NotNull
        public final String getClassName() {
            return this.className;
        }

        @NotNull
        public final byte[] getByteArray() {
            return this.byteArray;
        }

        public final long getClassId() {
            return this.classId;
        }

        public final int getCount() {
            return this.count;
        }

        public final void setCount(int i) {
            this.count = i;
        }

        @NotNull
        public final BitSet getProbes() {
            if (this.probes.size() == 0 && this.count >= 0) {
                this.probes = new BitSet(this.count);
            }
            return this.probes;
        }

        private final Instruction getPredecessor(Instruction $this$predecessor) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            Field it$iv = $this$predecessor.getClass().getDeclaredField("predecessor");
            it$iv.setAccessible(true);
            Object obj = it$iv.get($this$predecessor);
            if (!(obj instanceof Instruction)) {
                obj = null;
            }
            return (Instruction) obj;
        }

        public final void addProbe(@NotNull Instruction instruction, int probeId) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
            Object obj;
            Intrinsics.checkNotNullParameter(instruction, "instruction");
            Instruction predecessor = instruction;
            while (true) {
                Instruction insn = predecessor;
                if (insn == null) {
                    return;
                }
                int line = insn.getLine();
                if (line >= 0) {
                    Map $this$getOrPut$iv = this.lineMap;
                    Integer numValueOf = Integer.valueOf(line);
                    Object value$iv = $this$getOrPut$iv.get(numValueOf);
                    if (value$iv == null) {
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        $this$getOrPut$iv.put(numValueOf, linkedHashSet);
                        obj = linkedHashSet;
                    } else {
                        obj = value$iv;
                    }
                    ((Set) obj).add(Integer.valueOf(probeId));
                }
                predecessor = getPredecessor(insn);
            }
        }

        public final void cover(int line) {
            Set probeIds = this.lineMap.get(Integer.valueOf(line));
            if (probeIds == null) {
                return;
            }
            Iterator<Integer> it = probeIds.iterator();
            while (it.hasNext()) {
                int probeId = it.next().intValue();
                getProbes().set(probeId);
            }
        }
    }

    @NotNull
    public final StringPool getStringPool() {
        return this.stringPool;
    }

    @NotNull
    public final Map<String, ClassCoverage> getClassCoverageMap() {
        return this.classCoverageMap;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object cover(@org.jetbrains.annotations.NotNull cn.sast.framework.report.coverage.ClassSourceInfo r7, int r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            r0 = r9
            boolean r0 = r0 instanceof cn.sast.framework.report.coverage.Coverage.C00381
            if (r0 == 0) goto L27
            r0 = r9
            cn.sast.framework.report.coverage.Coverage$cover$1 r0 = (cn.sast.framework.report.coverage.Coverage.C00381) r0
            r12 = r0
            r0 = r12
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r12
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            cn.sast.framework.report.coverage.Coverage$cover$1 r0 = new cn.sast.framework.report.coverage.Coverage$cover$1
            r1 = r0
            r2 = r6
            r3 = r9
            r1.<init>(r3)
            r12 = r0
        L32:
            r0 = r12
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r12
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L79;
                default: goto L9e;
            }
        L58:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r6
            r1 = r7
            r2 = r12
            r3 = r12
            r4 = r8
            r3.I$0 = r4
            r3 = r12
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = r0.analyzeClass(r1, r2)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto L86
            r1 = r13
            return r1
        L79:
            r0 = r12
            int r0 = r0.I$0
            r8 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r11
        L86:
            cn.sast.framework.report.coverage.Coverage$ClassCoverage r0 = (cn.sast.framework.report.coverage.Coverage.ClassCoverage) r0
            r1 = r0
            if (r1 != 0) goto L92
        L8e:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L92:
            r10 = r0
            r0 = r10
            r1 = r8
            r0.cover(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L9e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.Coverage.cover(cn.sast.framework.report.coverage.ClassSourceInfo, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object cover(@org.jetbrains.annotations.NotNull java.lang.String r8, int r9, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r7 = this;
            r0 = r10
            boolean r0 = r0 instanceof cn.sast.framework.report.coverage.Coverage.C00392
            if (r0 == 0) goto L27
            r0 = r10
            cn.sast.framework.report.coverage.Coverage$cover$2 r0 = (cn.sast.framework.report.coverage.Coverage.C00392) r0
            r13 = r0
            r0 = r13
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r13
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            cn.sast.framework.report.coverage.Coverage$cover$2 r0 = new cn.sast.framework.report.coverage.Coverage$cover$2
            r1 = r0
            r2 = r7
            r3 = r10
            r1.<init>(r3)
            r13 = r0
        L32:
            r0 = r13
            java.lang.Object r0 = r0.result
            r12 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r14 = r0
            r0 = r13
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto L95;
                case 2: goto Ld7;
                default: goto Le3;
            }
        L5c:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            com.feysh.corax.cache.AnalysisCache$G r0 = com.feysh.corax.cache.AnalysisCache.G.INSTANCE
            cn.sast.framework.report.coverage.ClassSourceOfSCKey r1 = new cn.sast.framework.report.coverage.ClassSourceOfSCKey
            r2 = r1
            r3 = r8
            r2.<init>(r3)
            com.feysh.corax.cache.AnalysisKey r1 = (com.feysh.corax.cache.AnalysisKey) r1
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            r3 = r13
            r4 = r13
            r5 = r7
            r4.L$0 = r5
            r4 = r13
            r5 = r9
            r4.I$0 = r5
            r4 = r13
            r5 = 1
            r4.label = r5
            java.lang.Object r0 = r0.getAsync(r1, r2, r3)
            r1 = r0
            r2 = r14
            if (r1 != r2) goto Lab
            r1 = r14
            return r1
        L95:
            r0 = r13
            int r0 = r0.I$0
            r9 = r0
            r0 = r13
            java.lang.Object r0 = r0.L$0
            cn.sast.framework.report.coverage.Coverage r0 = (cn.sast.framework.report.coverage.Coverage) r0
            r7 = r0
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r12
        Lab:
            cn.sast.framework.report.coverage.ClassSourceInfo r0 = (cn.sast.framework.report.coverage.ClassSourceInfo) r0
            r1 = r0
            if (r1 != 0) goto Lb7
        Lb3:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lb7:
            r11 = r0
            r0 = r7
            r1 = r11
            r2 = r9
            r3 = r13
            r4 = r13
            r5 = 0
            r4.L$0 = r5
            r4 = r13
            r5 = 2
            r4.label = r5
            java.lang.Object r0 = r0.cover(r1, r2, r3)
            r1 = r0
            r2 = r14
            if (r1 != r2) goto Lde
            r1 = r14
            return r1
        Ld7:
            r0 = r12
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r12
        Lde:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Le3:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.Coverage.cover(java.lang.String, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Object cover(@NotNull byte[] clazz, int line, @NotNull Continuation<? super Unit> continuation) {
        Object objCover = cover(ClassSourceInfo.Companion.invoke(clazz), line, continuation);
        return objCover == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCover : Unit.INSTANCE;
    }

    public final void coverByQueue(@NotNull String className, int line) {
        Intrinsics.checkNotNullParameter(className, "className");
        this.coverQueue.add(new JacocoCover(className, line));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v2, types: [cn.sast.framework.report.coverage.Coverage$createAnalyzingVisitor$builder$1] */
    public final ClassVisitor createAnalyzingVisitor(final ClassCoverage classCoverage, long classId, String className) {
        final ClassCoverageImpl coverage = new ClassCoverageImpl(className, classId, false);
        final ?? r0 = new InstructionsBuilder() { // from class: cn.sast.framework.report.coverage.Coverage$createAnalyzingVisitor$builder$1
            {
                super(null);
            }

            @Override // cn.sast.framework.report.coverage.InstructionsBuilder
            protected void addProbe(int probeId, int branch) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
                super.addProbe(probeId, branch);
                Coverage.ClassCoverage classCoverage2 = classCoverage;
                Instruction instruction = this.currentInsn;
                Intrinsics.checkNotNullExpressionValue(instruction, "currentInsn");
                classCoverage2.addProbe(instruction, probeId);
            }
        };
        final StringPool stringPool = this.stringPool;
        return new ClassProbesAdapter((ClassAnalyzer) new ClassAnalyzer(coverage, stringPool) { // from class: cn.sast.framework.report.coverage.Coverage$createAnalyzingVisitor$analyzer$1
            /* renamed from: visitMethod, reason: merged with bridge method [inline-methods] */
            public MethodProbesVisitor m353visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                final Coverage$createAnalyzingVisitor$builder$1 coverage$createAnalyzingVisitor$builder$1 = r0;
                return new MethodAnalyzer(coverage$createAnalyzingVisitor$builder$1) { // from class: cn.sast.framework.report.coverage.Coverage$createAnalyzingVisitor$analyzer$1$visitMethod$1
                    {
                        super(coverage$createAnalyzingVisitor$builder$1);
                    }

                    @Override // cn.sast.framework.report.coverage.MethodAnalyzer
                    public void accept(MethodNode methodNode, MethodVisitor methodVisitor) {
                        super.accept(methodNode, methodVisitor);
                    }
                };
            }

            public void visitTotalProbeCount(int count) {
                super.visitTotalProbeCount(count);
                classCoverage.setCount(count);
            }
        }, false);
    }

    private static final CompletableFuture cache$lambda$0(Coverage this$0, ClassSourceInfo source, Executor executor) {
        Intrinsics.checkNotNullParameter(executor, "executor");
        String className = source.getClassName();
        long classId = source.getJacocoClassId();
        ClassCoverage classCoverage = new ClassCoverage(className, source.getByteArray(), classId);
        return FutureKt.future$default(CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executor)), (CoroutineContext) null, (CoroutineStart) null, new Coverage$cache$1$1(source, this$0, classCoverage, null), 3, (Object) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object analyzeClass(@org.jetbrains.annotations.NotNull cn.sast.framework.report.coverage.ClassSourceInfo r6, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super cn.sast.framework.report.coverage.Coverage.ClassCoverage> r7) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.Coverage.analyzeClass(cn.sast.framework.report.coverage.ClassSourceInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final SessionInfo getSessionInfo() {
        Instant instantNow = this.startTimestamp;
        if (instantNow == null) {
            instantNow = Instant.now();
        }
        Instant startTimestamp = instantNow;
        Instant dumpTimestamp = Instant.now();
        return new SessionInfo(UUID.randomUUID().toString(), startTimestamp.getEpochSecond(), dumpTimestamp.getEpochSecond());
    }

    @NotNull
    public final SessionInfoStore getSessionInfoStore() {
        SessionInfo sessionInfo = getSessionInfo();
        SessionInfoStore sessionInfoStore = new SessionInfoStore();
        sessionInfoStore.visitSessionInfo(sessionInfo);
        return sessionInfoStore;
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "Coverage.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.coverage.Coverage$flushExecutionDataFile$2")
    @SourceDebugExtension({"SMAP\nCoverage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Coverage.kt\ncn/sast/framework/report/coverage/Coverage$flushExecutionDataFile$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,490:1\n1863#2,2:491\n*S KotlinDebug\n*F\n+ 1 Coverage.kt\ncn/sast/framework/report/coverage/Coverage$flushExecutionDataFile$2\n*L\n296#1:491,2\n*E\n"})
    /* renamed from: cn.sast.framework.report.coverage.Coverage$flushExecutionDataFile$2, reason: invalid class name and case insensitive filesystem */
    /* loaded from: Coverage$flushExecutionDataFile$2.class */
    static final class C00412 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ IResFile $dumpFile;
        final /* synthetic */ SessionInfoStore $sessionInfoStore;
        final /* synthetic */ ExecutionDataStore $executionDataStore;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C00412(IResFile $dumpFile, SessionInfoStore $sessionInfoStore, ExecutionDataStore $executionDataStore, Continuation<? super C00412> continuation) {
            super(2, continuation);
            this.$dumpFile = $dumpFile;
            this.$sessionInfoStore = $sessionInfoStore;
            this.$executionDataStore = $executionDataStore;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new C00412(this.$dumpFile, this.$sessionInfoStore, this.$executionDataStore, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) throws IOException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    OpenOption[] openOptionArr = new OpenOption[0];
                    OutputStream outputStreamNewOutputStream = Files.newOutputStream(this.$dumpFile.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
                    Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
                    OutputStream outputStream = outputStreamNewOutputStream;
                    SessionInfoStore sessionInfoStore = this.$sessionInfoStore;
                    ExecutionDataStore executionDataStore = this.$executionDataStore;
                    Throwable th = null;
                    try {
                        try {
                            OutputStream outStream = outputStream;
                            IExecutionDataVisitor executionDataWriter = new ExecutionDataWriter(outStream);
                            Iterable infos = sessionInfoStore.getInfos();
                            Intrinsics.checkNotNullExpressionValue(infos, "getInfos(...)");
                            Iterable $this$forEach$iv = infos;
                            for (Object element$iv : $this$forEach$iv) {
                                SessionInfo p0 = (SessionInfo) element$iv;
                                executionDataWriter.visitSessionInfo(p0);
                            }
                            executionDataStore.accept(executionDataWriter);
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(outputStream, (Throwable) null);
                            return Unit.INSTANCE;
                        } finally {
                        }
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(outputStream, th);
                        throw th2;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Nullable
    public final Object flushExecutionDataFile(@NotNull SessionInfoStore sessionInfoStore, @NotNull ExecutionDataStore executionDataStore, @NotNull IResFile dumpFile, @NotNull Continuation<? super Unit> continuation) {
        dumpFile.mkdirs();
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C00412(dumpFile, sessionInfoStore, executionDataStore, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object processCoverQueueData(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.Boolean> r8) {
        /*
            Method dump skipped, instructions count: 242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.Coverage.processCoverQueueData(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Nullable
    public final Pair<ExecutionDataStore, CoverageBuilder> getCoverageBuilderPair() {
        return this.coverageBuilderPair;
    }

    public final void setCoverageBuilderPair(@Nullable Pair<ExecutionDataStore, ? extends CoverageBuilder> pair) {
        this.coverageBuilderPair = pair;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object computeCoverageBuilder(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Pair<org.jacoco.core.data.ExecutionDataStore, ? extends org.jacoco.core.analysis.CoverageBuilder>> r13) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.Coverage.computeCoverageBuilder(kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object computeCoverageBuilder$lambda$1(ClassCoverage $classCoverage) {
        return "An error occurred: class=" + $classCoverage.getClassName();
    }

    @Nullable
    public final Object createReport(@NotNull CoverageBuilder coverageBuilder, @NotNull SessionInfoStore sessionInfoStore, @NotNull ExecutionDataStore executionDataStore, @NotNull MultiSourceFileLocator mLocator, @NotNull IResDirectory reportDirectory, @NotNull Continuation<? super Unit> continuation) throws IOException {
        IBundleCoverage bundleCoverage = coverageBuilder.getBundle("CoraxCoverage");
        Intrinsics.checkNotNull(bundleCoverage);
        Object objCreateReport = createReport(sessionInfoStore, executionDataStore, bundleCoverage, (ISourceFileLocator) mLocator, reportDirectory.getFile(), continuation);
        return objCreateReport == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCreateReport : Unit.INSTANCE;
    }

    @Nullable
    public final Object createReport(@NotNull CoverageBuilder coverageBuilder, @NotNull SessionInfoStore sessionInfoStore, @NotNull ExecutionDataStore executionDataStore, @NotNull List<? extends ISourceFileLocator> list, @NotNull IResDirectory reportDirectory, @NotNull Continuation<? super Unit> continuation) throws IOException {
        MultiSourceFileLocator mLocator = new MultiSourceFileLocator(4);
        List<? extends ISourceFileLocator> $this$forEach$iv = list;
        for (Object element$iv : $this$forEach$iv) {
            ISourceFileLocator p0 = (ISourceFileLocator) element$iv;
            mLocator.add(p0);
        }
        Object objCreateReport = createReport(coverageBuilder, sessionInfoStore, executionDataStore, mLocator, reportDirectory, continuation);
        return objCreateReport == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCreateReport : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object createReport(@org.jetbrains.annotations.NotNull org.jacoco.core.data.SessionInfoStore r11, @org.jetbrains.annotations.NotNull org.jacoco.core.data.ExecutionDataStore r12, @org.jetbrains.annotations.NotNull org.jacoco.core.analysis.IBundleCoverage r13, @org.jetbrains.annotations.NotNull org.jacoco.report.ISourceFileLocator r14, @org.jetbrains.annotations.NotNull java.io.File r15, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r16) throws java.io.IOException {
        /*
            r10 = this;
            r0 = r16
            boolean r0 = r0 instanceof cn.sast.framework.report.coverage.Coverage.AnonymousClass4
            if (r0 == 0) goto L29
            r0 = r16
            cn.sast.framework.report.coverage.Coverage$createReport$4 r0 = (cn.sast.framework.report.coverage.Coverage.AnonymousClass4) r0
            r18 = r0
            r0 = r18
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L29
            r0 = r18
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L35
        L29:
            cn.sast.framework.report.coverage.Coverage$createReport$4 r0 = new cn.sast.framework.report.coverage.Coverage$createReport$4
            r1 = r0
            r2 = r10
            r3 = r16
            r1.<init>(r3)
            r18 = r0
        L35:
            r0 = r18
            java.lang.Object r0 = r0.result
            r17 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r19 = r0
            r0 = r18
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto La2;
                default: goto Lc7;
            }
        L5c:
            r0 = r17
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r15
            boolean r0 = r0.exists()
            if (r0 != 0) goto L6f
            r0 = r15
            boolean r0 = r0.mkdir()
        L6f:
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
            cn.sast.framework.report.coverage.Coverage$createReport$5 r1 = new cn.sast.framework.report.coverage.Coverage$createReport$5
            r2 = r1
            r3 = r15
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r14
            r8 = 0
            r2.<init>(r3, r4, r5, r6, r7, r8)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r2 = r18
            r3 = r18
            r4 = r15
            r3.L$0 = r4
            r3 = r18
            r4 = 1
            r3.label = r4
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r1, r2)
            r1 = r0
            r2 = r19
            if (r1 != r2) goto Lb3
            r1 = r19
            return r1
        La2:
            r0 = r18
            java.lang.Object r0 = r0.L$0
            java.io.File r0 = (java.io.File) r0
            r15 = r0
            r0 = r17
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r17
        Lb3:
            mu.KLogger r0 = cn.sast.framework.report.coverage.Coverage.logger
            r1 = r15
            java.lang.Object r1 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return createReport$lambda$2(r1);
            }
            r0.info(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lc7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.Coverage.createReport(org.jacoco.core.data.SessionInfoStore, org.jacoco.core.data.ExecutionDataStore, org.jacoco.core.analysis.IBundleCoverage, org.jacoco.report.ISourceFileLocator, java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "Coverage.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.coverage.Coverage$createReport$5")
    /* renamed from: cn.sast.framework.report.coverage.Coverage$createReport$5, reason: invalid class name */
    /* loaded from: Coverage$createReport$5.class */
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ File $reportDirectory;
        final /* synthetic */ SessionInfoStore $sessionInfoStore;
        final /* synthetic */ ExecutionDataStore $executionDataStore;
        final /* synthetic */ IBundleCoverage $bundleCoverage;
        final /* synthetic */ ISourceFileLocator $locator;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass5(File $reportDirectory, SessionInfoStore $sessionInfoStore, ExecutionDataStore $executionDataStore, IBundleCoverage $bundleCoverage, ISourceFileLocator $locator, Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
            this.$reportDirectory = $reportDirectory;
            this.$sessionInfoStore = $sessionInfoStore;
            this.$executionDataStore = $executionDataStore;
            this.$bundleCoverage = $bundleCoverage;
            this.$locator = $locator;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return new AnonymousClass5(this.$reportDirectory, this.$sessionInfoStore, this.$executionDataStore, this.$bundleCoverage, this.$locator, continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    HTMLFormatter htmlFormatter = new HTMLFormatter();
                    IReportVisitor visitor = htmlFormatter.createVisitor(new FileMultiReportOutput(this.$reportDirectory));
                    visitor.visitInfo(this.$sessionInfoStore.getInfos(), this.$executionDataStore.getContents());
                    visitor.visitBundle(this.$bundleCoverage, this.$locator);
                    visitor.visitEnd();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    private static final Object createReport$lambda$2(File $reportDirectory) {
        File absoluteFile = FilesKt.resolve($reportDirectory, "index.html").getAbsoluteFile();
        Intrinsics.checkNotNullExpressionValue(absoluteFile, "getAbsoluteFile(...)");
        return "Jacoco coverage html reports: " + FilesKt.normalize(absoluteFile);
    }

    @NotNull
    public final CoverageBuilder makeCoverageBuilder(@NotNull ExecutionDataStore executionDataStore) {
        Intrinsics.checkNotNullParameter(executionDataStore, "executionDataStore");
        ICoverageVisitor coverageBuilder = new CoverageBuilder();
        Analyzer analyzer = new Analyzer(executionDataStore, coverageBuilder);
        Iterator<Map.Entry<String, ClassCoverage>> it = this.classCoverageMap.entrySet().iterator();
        while (it.hasNext()) {
            ClassCoverage classCoverage = it.next().getValue();
            analyzer.analyzeClass(classCoverage.getByteArray(), classCoverage.getClassName());
        }
        return coverageBuilder;
    }

    @NotNull
    public final MultiSourceFileLocator getMultiSourceFileLocator(@NotNull IProjectFileLocator locator, @NotNull Charset encoding) {
        Intrinsics.checkNotNullParameter(locator, "locator");
        Intrinsics.checkNotNullParameter(encoding, "encoding");
        Iterable jacocoLocators = CollectionsKt.listOf(new InputStreamSourceFileLocator[]{new DirectorySourceFileLocator(new File("."), encoding.name(), 4), new JacocoSourceLocator(locator, null, 0, 6, null)});
        MultiSourceFileLocator mLocator = new MultiSourceFileLocator(4);
        Iterable $this$forEach$iv = jacocoLocators;
        for (Object element$iv : $this$forEach$iv) {
            ISourceFileLocator p0 = (ISourceFileLocator) element$iv;
            mLocator.add(p0);
        }
        return mLocator;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object flushCoverage$suspendImpl(cn.sast.framework.report.coverage.Coverage r10, cn.sast.framework.report.IProjectFileLocator r11, cn.sast.common.IResDirectory r12, java.nio.charset.Charset r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instructions count: 501
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.Coverage.flushCoverage$suspendImpl(cn.sast.framework.report.coverage.Coverage, cn.sast.framework.report.IProjectFileLocator, cn.sast.common.IResDirectory, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @NotNull
    public final SourceCoverage calculateSourceCoverage(@NotNull CoverageBuilder coverage, @NotNull MultiSourceFileLocator mLocator) {
        Intrinsics.checkNotNullParameter(coverage, "coverage");
        Intrinsics.checkNotNullParameter(mLocator, "mLocator");
        Map sourceCoverage = new LinkedHashMap();
        for (ISourceFileCoverage srcCov : coverage.getSourceFiles()) {
            String sourceKey = srcCov.getPackageName() + "/" + srcCov.getName();
            try {
                Reader reader = mLocator.getSourceFile(srcCov.getPackageName(), srcCov.getName());
                if (reader != null) {
                    int lineCount = TextStreamsKt.readLines(reader).size();
                    Intrinsics.checkNotNull(srcCov);
                    sourceCoverage.put(sourceKey, new SourceCoverage.JavaSourceCoverage(lineCount, srcCov));
                }
            } catch (Exception e) {
                logger.error("Source file " + sourceKey + " cannot be read!", e);
            }
        }
        return new SourceCoverage(sourceCoverage);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object calculateSourceCoverage(@org.jetbrains.annotations.NotNull cn.sast.framework.report.IProjectFileLocator r6, @org.jetbrains.annotations.NotNull java.nio.charset.Charset r7, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super cn.sast.framework.report.coverage.SourceCoverage> r8) {
        /*
            r5 = this;
            r0 = r8
            boolean r0 = r0 instanceof cn.sast.framework.report.coverage.Coverage.C00361
            if (r0 == 0) goto L27
            r0 = r8
            cn.sast.framework.report.coverage.Coverage$calculateSourceCoverage$1 r0 = (cn.sast.framework.report.coverage.Coverage.C00361) r0
            r12 = r0
            r0 = r12
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L27
            r0 = r12
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L32
        L27:
            cn.sast.framework.report.coverage.Coverage$calculateSourceCoverage$1 r0 = new cn.sast.framework.report.coverage.Coverage$calculateSourceCoverage$1
            r1 = r0
            r2 = r5
            r3 = r8
            r1.<init>(r3)
            r12 = r0
        L32:
            r0 = r12
            java.lang.Object r0 = r0.result
            r11 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r13 = r0
            r0 = r12
            int r0 = r0.label
            switch(r0) {
                case 0: goto L58;
                case 1: goto L84;
                default: goto Lc2;
            }
        L58:
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r5
            r1 = r12
            r2 = r12
            r3 = r5
            r2.L$0 = r3
            r2 = r12
            r3 = r6
            r2.L$1 = r3
            r2 = r12
            r3 = r7
            r2.L$2 = r3
            r2 = r12
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = r0.computeCoverageBuilder(r1)
            r1 = r0
            r2 = r13
            if (r1 != r2) goto La6
            r1 = r13
            return r1
        L84:
            r0 = r12
            java.lang.Object r0 = r0.L$2
            java.nio.charset.Charset r0 = (java.nio.charset.Charset) r0
            r7 = r0
            r0 = r12
            java.lang.Object r0 = r0.L$1
            cn.sast.framework.report.IProjectFileLocator r0 = (cn.sast.framework.report.IProjectFileLocator) r0
            r6 = r0
            r0 = r12
            java.lang.Object r0 = r0.L$0
            cn.sast.framework.report.coverage.Coverage r0 = (cn.sast.framework.report.coverage.Coverage) r0
            r5 = r0
            r0 = r11
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r11
        La6:
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.component2()
            org.jacoco.core.analysis.CoverageBuilder r0 = (org.jacoco.core.analysis.CoverageBuilder) r0
            r9 = r0
            r0 = r5
            r1 = r6
            r2 = r7
            org.jacoco.report.MultiSourceFileLocator r0 = r0.getMultiSourceFileLocator(r1, r2)
            r10 = r0
            r0 = r5
            r1 = r9
            r2 = r10
            cn.sast.framework.report.coverage.SourceCoverage r0 = r0.calculateSourceCoverage(r1, r2)
            return r0
        Lc2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.coverage.Coverage.calculateSourceCoverage(cn.sast.framework.report.IProjectFileLocator, java.nio.charset.Charset, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/report/coverage/Coverage$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: Coverage$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$5() {
        return Unit.INSTANCE;
    }
}
