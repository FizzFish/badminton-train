package cn.sast.framework.report;

import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.FileResInfo;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.util.IMonitor;
import cn.sast.common.FileSystemLocator;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.AbstractFileIndexer;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.GlobalScope;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018�� :2\u00020\u0001:\u0001:B;\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u0016\u001a\u00020\u0015H\u0082@¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0016J$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\"H\u0016J\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020$J\u001a\u0010&\u001a\u0004\u0018\u00010\u001d2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0002J\u001a\u00100\u001a\u0004\u0018\u00010\u001d2\u0006\u0010'\u001a\u0002012\u0006\u0010)\u001a\u00020*H\u0002J\u001a\u00100\u001a\u0004\u0018\u00010\u001d2\u0006\u0010'\u001a\u0002022\u0006\u0010)\u001a\u00020*H\u0002J\u001a\u00100\u001a\u0004\u0018\u00010\u001d2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\u001c\u00103\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u00104\u001a\u00020 H\u0096@¢\u0006\u0002\u00105J\u001c\u00106\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u00107\u001a\u00020 H\u0096@¢\u0006\u0002\u00105J\u0014\u00108\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0096@¢\u0006\u0002\u0010\u0017J\b\u00109\u001a\u00020 H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n��R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n��R(\u0010+\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020*0-\u0012\u0006\u0012\u0004\u0018\u00010\u001d0,X\u0082\u0004¢\u0006\u0002\n��R(\u0010.\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020*0-\u0012\u0006\u0012\u0004\u0018\u00010\u001d0/X\u0082\u0004¢\u0006\u0002\n��¨\u0006;"}, d2 = {"Lcn/sast/framework/report/ProjectFileLocator;", "Lcn/sast/framework/report/IProjectFileLocator;", "monitor", "Lcn/sast/api/util/IMonitor;", "sourceDir", "", "Lcn/sast/common/IResource;", "fileWrapperOutPutDir", "Lcn/sast/common/IResDirectory;", "traverseMode", "Lcn/sast/common/FileSystemLocator$TraverseMode;", "enableInfo", "", "<init>", "(Lcn/sast/api/util/IMonitor;Ljava/util/Set;Lcn/sast/common/IResDirectory;Lcn/sast/common/FileSystemLocator$TraverseMode;Z)V", "getSourceDir", "()Ljava/util/Set;", "setSourceDir", "(Ljava/util/Set;)V", "updateJob", "Lkotlinx/coroutines/Deferred;", "Lcn/sast/framework/report/FileIndexer;", "indexer", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "indexerBlock", "update", "", "findFromFileIndexMap", "Lkotlin/sequences/Sequence;", "Lcn/sast/common/IResFile;", "parentSubPath", "", "", "mode", "Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;", "totalFiles", "", "totalJavaSrcFiles", "makeWrapperFile", "resInfo", "Lcn/sast/api/report/IBugResInfo;", "fileWrapperIfNotEExists", "Lcn/sast/framework/report/IWrapperFileGenerator;", "loader", "Lcom/github/benmanes/caffeine/cache/CacheLoader;", "Lkotlin/Pair;", "cache", "Lcom/github/benmanes/caffeine/cache/LoadingCache;", "get", "Lcn/sast/api/report/ClassResInfo;", "Lcn/sast/api/report/FileResInfo;", "getByFileExtension", "extension", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByFileName", "filename", "getAllFiles", "toString", "Companion", "corax-framework"})
/* loaded from: ProjectFileLocator.class */
public class ProjectFileLocator implements IProjectFileLocator {

    @Nullable
    private final IMonitor monitor;

    @NotNull
    private Set<? extends IResource> sourceDir;

    @Nullable
    private final IResDirectory fileWrapperOutPutDir;

    @NotNull
    private FileSystemLocator.TraverseMode traverseMode;
    private final boolean enableInfo;

    @Nullable
    private Deferred<FileIndexer> updateJob;

    @NotNull
    private final CacheLoader<Pair<IBugResInfo, IWrapperFileGenerator>, IResFile> loader;

    @NotNull
    private final LoadingCache<Pair<IBugResInfo, IWrapperFileGenerator>, IResFile> cache;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ProjectFileLocator::logger$lambda$2);

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "JavaSourceLocator.kt", l = {436}, i = {}, s = {}, n = {}, m = "getAllFiles$suspendImpl", c = "cn.sast.framework.report.ProjectFileLocator")
    /* renamed from: cn.sast.framework.report.ProjectFileLocator$getAllFiles$1, reason: invalid class name */
    /* loaded from: ProjectFileLocator$getAllFiles$1.class */
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
            return ProjectFileLocator.getAllFiles$suspendImpl(ProjectFileLocator.this, (Continuation) this);
        }
    }

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "JavaSourceLocator.kt", l = {428}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"extension"}, m = "getByFileExtension$suspendImpl", c = "cn.sast.framework.report.ProjectFileLocator")
    /* renamed from: cn.sast.framework.report.ProjectFileLocator$getByFileExtension$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ProjectFileLocator$getByFileExtension$1.class */
    static final class C00311 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C00311(Continuation<? super C00311> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ProjectFileLocator.getByFileExtension$suspendImpl(ProjectFileLocator.this, null, (Continuation) this);
        }
    }

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "JavaSourceLocator.kt", l = {432}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"filename"}, m = "getByFileName$suspendImpl", c = "cn.sast.framework.report.ProjectFileLocator")
    /* renamed from: cn.sast.framework.report.ProjectFileLocator$getByFileName$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ProjectFileLocator$getByFileName$1.class */
    static final class C00321 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C00321(Continuation<? super C00321> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return ProjectFileLocator.getByFileName$suspendImpl(ProjectFileLocator.this, null, (Continuation) this);
        }
    }

    @Override // cn.sast.framework.report.IProjectFileLocator
    @Nullable
    public Object getByFileExtension(@NotNull String extension, @NotNull Continuation<? super Sequence<? extends IResFile>> continuation) {
        return getByFileExtension$suspendImpl(this, extension, continuation);
    }

    @Override // cn.sast.framework.report.IProjectFileLocator
    @Nullable
    public Object getByFileName(@NotNull String filename, @NotNull Continuation<? super Sequence<? extends IResFile>> continuation) {
        return getByFileName$suspendImpl(this, filename, continuation);
    }

    @Override // cn.sast.framework.report.IProjectFileLocator
    @Nullable
    public Object getAllFiles(@NotNull Continuation<? super Sequence<? extends IResFile>> continuation) {
        return getAllFiles$suspendImpl(this, continuation);
    }

    public ProjectFileLocator(@Nullable IMonitor monitor, @NotNull Set<? extends IResource> set, @Nullable IResDirectory fileWrapperOutPutDir, @NotNull FileSystemLocator.TraverseMode traverseMode, boolean enableInfo) {
        Intrinsics.checkNotNullParameter(set, "sourceDir");
        Intrinsics.checkNotNullParameter(traverseMode, "traverseMode");
        this.monitor = monitor;
        this.sourceDir = set;
        this.fileWrapperOutPutDir = fileWrapperOutPutDir;
        this.traverseMode = traverseMode;
        this.enableInfo = enableInfo;
        this.loader = new CacheLoader() { // from class: cn.sast.framework.report.ProjectFileLocator$loader$1
            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
            public final IResFile load(Pair<? extends IBugResInfo, ? extends IWrapperFileGenerator> pair) throws NoWhenBranchMatchedException {
                IBugResInfo resInfo = (IBugResInfo) pair.component1();
                IWrapperFileGenerator fileWrapperIfNotEExists = (IWrapperFileGenerator) pair.component2();
                if (resInfo instanceof ClassResInfo) {
                    return this.this$0.get((ClassResInfo) resInfo, fileWrapperIfNotEExists);
                }
                if (resInfo instanceof FileResInfo) {
                    return this.this$0.get((FileResInfo) resInfo, fileWrapperIfNotEExists);
                }
                throw new NoWhenBranchMatchedException();
            }
        };
        Caffeine $this$cache_u24lambda_u240 = Caffeine.newBuilder().softValues();
        $this$cache_u24lambda_u240.maximumSize(8000L);
        LoadingCache<Pair<IBugResInfo, IWrapperFileGenerator>, IResFile> loadingCacheBuild = $this$cache_u24lambda_u240.build(this.loader);
        Intrinsics.checkNotNullExpressionValue(loadingCacheBuild, "build(...)");
        this.cache = loadingCacheBuild;
    }

    public /* synthetic */ ProjectFileLocator(IMonitor iMonitor, Set set, IResDirectory iResDirectory, FileSystemLocator.TraverseMode traverseMode, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iMonitor, set, iResDirectory, traverseMode, (i & 16) != 0 ? true : z);
    }

    public static final /* synthetic */ boolean access$getEnableInfo$p(ProjectFileLocator $this) {
        return $this.enableInfo;
    }

    public static final /* synthetic */ KLogger access$getLogger$cp() {
        return logger;
    }

    public static final /* synthetic */ FileSystemLocator.TraverseMode access$getTraverseMode$p(ProjectFileLocator $this) {
        return $this.traverseMode;
    }

    public static final /* synthetic */ IMonitor access$getMonitor$p(ProjectFileLocator $this) {
        return $this.monitor;
    }

    @Override // cn.sast.framework.report.IProjectFileLocator
    @NotNull
    public Set<IResource> getSourceDir() {
        return this.sourceDir;
    }

    @Override // cn.sast.framework.report.IProjectFileLocator
    public void setSourceDir(@NotNull Set<? extends IResource> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.sourceDir = set;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object indexer(Continuation<? super FileIndexer> continuation) {
        Deferred job = this.updateJob;
        if (job == null) {
            throw new IllegalStateException("update at first!".toString());
        }
        return job.await(continuation);
    }

    private final FileIndexer indexerBlock() {
        Deferred job = this.updateJob;
        if (job == null) {
            throw new IllegalStateException("update at first!".toString());
        }
        return job.isCompleted() ? (FileIndexer) job.getCompleted() : (FileIndexer) BuildersKt.runBlocking$default((CoroutineContext) null, new C00331(null), 1, (Object) null);
    }

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcn/sast/framework/report/FileIndexer;", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "JavaSourceLocator.kt", l = {342}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.ProjectFileLocator$indexerBlock$1")
    /* renamed from: cn.sast.framework.report.ProjectFileLocator$indexerBlock$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ProjectFileLocator$indexerBlock$1.class */
    static final class C00331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FileIndexer>, Object> {
        int label;

        C00331(Continuation<? super C00331> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return ProjectFileLocator.this.new C00331(continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super FileIndexer> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    Object objIndexer = ProjectFileLocator.this.indexer((Continuation) this);
                    return objIndexer == coroutine_suspended ? coroutine_suspended : objIndexer;
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    ResultKt.throwOnFailure($result);
                    return $result;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcn/sast/framework/report/FileIndexer;", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "JavaSourceLocator.kt", l = {358, 358}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$3", "L$4", "I$0", "L$0", "L$1", "L$3", "L$4", "L$5", "L$6", "I$0"}, n = {"$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "alreadyLogged$iv", "$this$bracket_u24default$iv", "msg$iv", "startTime$iv", "res$iv", "$this$bracket$iv", "s$iv", "alreadyLogged$iv"}, m = "invokeSuspend", c = "cn.sast.framework.report.ProjectFileLocator$update$1")
    @SourceDebugExtension({"SMAP\nJavaSourceLocator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/ProjectFileLocator$update$1\n+ 2 Logging.kt\norg/utbot/common/LoggingKt\n+ 3 Timer.kt\ncn/sast/api/util/TimerKt\n*L\n1#1,490:1\n49#2,13:491\n62#2,11:512\n16#3,8:504\n*S KotlinDebug\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/ProjectFileLocator$update$1\n*L\n356#1:491,13\n356#1:512,11\n357#1:504,8\n*E\n"})
    /* renamed from: cn.sast.framework.report.ProjectFileLocator$update$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ProjectFileLocator$update$1.class */
    static final class C00341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FileIndexer>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int I$0;
        int label;

        C00341(Continuation<? super C00341> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return ProjectFileLocator.this.new C00341(continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super FileIndexer> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: Types fix failed
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
            	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
            */
        /* JADX WARN: Failed to apply debug info
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
        	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
         */
        /* JADX WARN: Failed to calculate best type for var: r10v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r10v1 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r10v2 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r11v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r11v1 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r11v2 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r14v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r14v1 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r14v2 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Failed to calculate best type for var: r16v0 ??
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
         */
        /* JADX WARN: Finally extract failed */
        /* JADX WARN: Not initialized variable reg: 10, insn: 0x02ec: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:514), block:B:44:0x02ec */
        /* JADX WARN: Not initialized variable reg: 10, insn: 0x031e: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:520), block:B:51:0x031e */
        /* JADX WARN: Not initialized variable reg: 10, insn: 0x0338: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = 
  (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('$this$bracket_u24default$iv' org.utbot.common.LoggerWithLogMethod)])
 (LINE:522), block:B:52:0x0338 */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x02f6: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:44:0x02ec */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x0328: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:51:0x031e */
        /* JADX WARN: Not initialized variable reg: 11, insn: 0x0342: MOVE (r4 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r11 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('msg$iv' java.lang.String)]), block:B:52:0x0338 */
        /* JADX WARN: Not initialized variable reg: 14, insn: 0x02f4: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r14 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:44:0x02ec */
        /* JADX WARN: Not initialized variable reg: 14, insn: 0x0326: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r14 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:51:0x031e */
        /* JADX WARN: Not initialized variable reg: 14, insn: 0x0340: MOVE (r3 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r14 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('startTime$iv' java.time.LocalDateTime)]), block:B:52:0x0338 */
        /* JADX WARN: Not initialized variable reg: 15, insn: 0x030b: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r15 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('alreadyLogged$iv' boolean)]) A[TRY_LEAVE], block:B:47:0x030b */
        /* JADX WARN: Not initialized variable reg: 16, insn: 0x0310: MOVE (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r16 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('res$iv' kotlin.jvm.internal.Ref$ObjectRef)]) (LINE:519), block:B:49:0x0310 */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                Method dump skipped, instructions count: 860
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ProjectFileLocator.C00341.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        private static final Object invokeSuspend$lambda$0(ProjectFileLocator this$0, Set $srcTranslate) {
            return this$0 + " update indexes (traverseMode=" + this$0.traverseMode + ", from: " + $srcTranslate + ")";
        }

        private static final Object invokeSuspend$lambda$1(ProjectFileLocator this$0, Set $srcTranslate) {
            return this$0 + " update indexes (traverseMode=" + this$0.traverseMode + ", from: " + $srcTranslate + ")";
        }

        private static final Object invokeSuspend$lambda$5$lambda$4$lambda$3$lambda$2(ProjectFileLocator this$0, FileIndexer $it) {
            return this$0 + " file count: " + $it.getCount();
        }
    }

    @Override // cn.sast.framework.report.IProjectFileLocator
    public void update() {
        if (!(this.updateJob == null)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.updateJob = BuildersKt.async$default(GlobalScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new C00341(null), 3, (Object) null);
        Deferred<FileIndexer> deferred = this.updateJob;
        if (deferred != null) {
            deferred.start();
        }
    }

    @Override // cn.sast.framework.report.IProjectFileLocator
    @NotNull
    public Sequence<IResFile> findFromFileIndexMap(@NotNull List<String> list, @NotNull AbstractFileIndexer.CompareMode mode) {
        Intrinsics.checkNotNullParameter(list, "parentSubPath");
        Intrinsics.checkNotNullParameter(mode, "mode");
        return indexerBlock().findFromFileIndexMap(list, mode);
    }

    public final long totalFiles() {
        long c = 0;
        for (Map.Entry x : indexerBlock().getFileNameToPathMap$corax_framework().entrySet()) {
            c += x.getValue().size();
        }
        return c;
    }

    public final long totalJavaSrcFiles() {
        Map extensionToPathMap = indexerBlock().getExtensionToPathMap$corax_framework();
        long count = 0;
        for (String ext : ResourceKt.getJavaExtensions()) {
            Set<IResFile> set = extensionToPathMap.get(ext);
            int c = set != null ? set.size() : 0;
            count += c;
        }
        return count;
    }

    private final IResFile makeWrapperFile(IBugResInfo resInfo, IWrapperFileGenerator fileWrapperIfNotEExists) {
        if (this.fileWrapperOutPutDir == null) {
            return null;
        }
        return fileWrapperIfNotEExists.makeWrapperFile(this.fileWrapperOutPutDir, resInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IResFile get(ClassResInfo resInfo, IWrapperFileGenerator fileWrapperIfNotEExists) {
        IResFile found = indexerBlock().findAnyFile(resInfo.getSourceFile(), AbstractFileIndexer.Companion.getDefaultClassCompareMode());
        return found == null ? makeWrapperFile(resInfo, fileWrapperIfNotEExists) : found;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IResFile get(FileResInfo resInfo, IWrapperFileGenerator fileWrapperIfNotEExists) {
        return resInfo.getSourcePath().getExists() ? resInfo.getSourcePath() : makeWrapperFile(resInfo, fileWrapperIfNotEExists);
    }

    @Override // cn.sast.framework.report.IProjectFileLocator
    @Nullable
    public IResFile get(@NotNull IBugResInfo resInfo, @NotNull IWrapperFileGenerator fileWrapperIfNotEExists) {
        Intrinsics.checkNotNullParameter(resInfo, "resInfo");
        Intrinsics.checkNotNullParameter(fileWrapperIfNotEExists, "fileWrapperIfNotEExists");
        return (IResFile) this.cache.get(TuplesKt.to(resInfo, fileWrapperIfNotEExists));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object getByFileExtension$suspendImpl(cn.sast.framework.report.ProjectFileLocator r5, java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.sequences.Sequence<? extends cn.sast.common.IResFile>> r7) {
        /*
            r0 = r7
            boolean r0 = r0 instanceof cn.sast.framework.report.ProjectFileLocator.C00311
            if (r0 == 0) goto L29
            r0 = r7
            cn.sast.framework.report.ProjectFileLocator$getByFileExtension$1 r0 = (cn.sast.framework.report.ProjectFileLocator.C00311) r0
            r9 = r0
            r0 = r9
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L29
            r0 = r9
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L34
        L29:
            cn.sast.framework.report.ProjectFileLocator$getByFileExtension$1 r0 = new cn.sast.framework.report.ProjectFileLocator$getByFileExtension$1
            r1 = r0
            r2 = r5
            r3 = r7
            r1.<init>(r3)
            r9 = r0
        L34:
            r0 = r9
            java.lang.Object r0 = r0.result
            r8 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r10 = r0
            r0 = r9
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto L7b;
                default: goto Lab;
            }
        L5c:
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r5
            r1 = r9
            r2 = r9
            r3 = r6
            r2.L$0 = r3
            r2 = r9
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = r0.indexer(r1)
            r1 = r0
            r2 = r10
            if (r1 != r2) goto L89
            r1 = r10
            return r1
        L7b:
            r0 = r9
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            r6 = r0
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
        L89:
            cn.sast.framework.report.FileIndexer r0 = (cn.sast.framework.report.FileIndexer) r0
            java.util.Map r0 = r0.getExtensionToPathMap$corax_framework()
            r1 = r6
            java.lang.Object r0 = r0.get(r1)
            java.util.Set r0 = (java.util.Set) r0
            r1 = r0
            if (r1 == 0) goto La6
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.sequences.Sequence r0 = kotlin.collections.CollectionsKt.asSequence(r0)
            r1 = r0
            if (r1 != 0) goto Laa
        La6:
        La7:
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.emptySequence()
        Laa:
            return r0
        Lab:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ProjectFileLocator.getByFileExtension$suspendImpl(cn.sast.framework.report.ProjectFileLocator, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object getByFileName$suspendImpl(cn.sast.framework.report.ProjectFileLocator r5, java.lang.String r6, kotlin.coroutines.Continuation<? super kotlin.sequences.Sequence<? extends cn.sast.common.IResFile>> r7) {
        /*
            r0 = r7
            boolean r0 = r0 instanceof cn.sast.framework.report.ProjectFileLocator.C00321
            if (r0 == 0) goto L29
            r0 = r7
            cn.sast.framework.report.ProjectFileLocator$getByFileName$1 r0 = (cn.sast.framework.report.ProjectFileLocator.C00321) r0
            r9 = r0
            r0 = r9
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L29
            r0 = r9
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L34
        L29:
            cn.sast.framework.report.ProjectFileLocator$getByFileName$1 r0 = new cn.sast.framework.report.ProjectFileLocator$getByFileName$1
            r1 = r0
            r2 = r5
            r3 = r7
            r1.<init>(r3)
            r9 = r0
        L34:
            r0 = r9
            java.lang.Object r0 = r0.result
            r8 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r10 = r0
            r0 = r9
            int r0 = r0.label
            switch(r0) {
                case 0: goto L5c;
                case 1: goto L7b;
                default: goto Lab;
            }
        L5c:
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r5
            r1 = r9
            r2 = r9
            r3 = r6
            r2.L$0 = r3
            r2 = r9
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = r0.indexer(r1)
            r1 = r0
            r2 = r10
            if (r1 != r2) goto L89
            r1 = r10
            return r1
        L7b:
            r0 = r9
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            r6 = r0
            r0 = r8
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r8
        L89:
            cn.sast.framework.report.FileIndexer r0 = (cn.sast.framework.report.FileIndexer) r0
            java.util.Map r0 = r0.getFileNameToPathMap$corax_framework()
            r1 = r6
            java.lang.Object r0 = r0.get(r1)
            java.util.Set r0 = (java.util.Set) r0
            r1 = r0
            if (r1 == 0) goto La6
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.sequences.Sequence r0 = kotlin.collections.CollectionsKt.asSequence(r0)
            r1 = r0
            if (r1 != 0) goto Laa
        La6:
        La7:
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.emptySequence()
        Laa:
            return r0
        Lab:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ProjectFileLocator.getByFileName$suspendImpl(cn.sast.framework.report.ProjectFileLocator, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static /* synthetic */ java.lang.Object getAllFiles$suspendImpl(cn.sast.framework.report.ProjectFileLocator r5, kotlin.coroutines.Continuation<? super kotlin.sequences.Sequence<? extends cn.sast.common.IResFile>> r6) {
        /*
            r0 = r6
            boolean r0 = r0 instanceof cn.sast.framework.report.ProjectFileLocator.AnonymousClass1
            if (r0 == 0) goto L26
            r0 = r6
            cn.sast.framework.report.ProjectFileLocator$getAllFiles$1 r0 = (cn.sast.framework.report.ProjectFileLocator.AnonymousClass1) r0
            r8 = r0
            r0 = r8
            int r0 = r0.label
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r0 & r1
            if (r0 == 0) goto L26
            r0 = r8
            r1 = r0
            int r1 = r1.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            int r1 = r1 - r2
            r0.label = r1
            goto L30
        L26:
            cn.sast.framework.report.ProjectFileLocator$getAllFiles$1 r0 = new cn.sast.framework.report.ProjectFileLocator$getAllFiles$1
            r1 = r0
            r2 = r5
            r3 = r6
            r1.<init>(r3)
            r8 = r0
        L30:
            r0 = r8
            java.lang.Object r0 = r0.result
            r7 = r0
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r9 = r0
            r0 = r8
            int r0 = r0.label
            switch(r0) {
                case 0: goto L54;
                case 1: goto L6b;
                default: goto L82;
            }
        L54:
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r5
            r1 = r8
            r2 = r8
            r3 = 1
            r2.label = r3
            java.lang.Object r0 = r0.indexer(r1)
            r1 = r0
            r2 = r9
            if (r1 != r2) goto L70
            r1 = r9
            return r1
        L6b:
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r7
        L70:
            cn.sast.framework.report.FileIndexer r0 = (cn.sast.framework.report.FileIndexer) r0
            java.util.Map r0 = r0.getFileNameToPathMap$corax_framework()
            kotlin.sequences.Sequence r0 = kotlin.collections.MapsKt.asSequence(r0)
            java.lang.Object r1 = cn.sast.framework.report.ProjectFileLocator::getAllFiles$lambda$1
            kotlin.sequences.Sequence r0 = kotlin.sequences.SequencesKt.flatMapIterable(r0, r1)
            return r0
        L82:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ProjectFileLocator.getAllFiles$suspendImpl(cn.sast.framework.report.ProjectFileLocator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Iterable getAllFiles$lambda$1(Map.Entry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (Iterable) it.getValue();
    }

    @NotNull
    public String toString() {
        return "Source-Locator@" + System.identityHashCode(this);
    }

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\n"}, d2 = {"Lcn/sast/framework/report/ProjectFileLocator$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "findJdkSources", "", "Lcn/sast/common/IResFile;", "home", "corax-framework"})
    /* loaded from: ProjectFileLocator$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
            jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:16:0x00fe
            	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
            	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
            */
        @org.jetbrains.annotations.NotNull
        public final java.util.List<cn.sast.common.IResFile> findJdkSources(@org.jetbrains.annotations.NotNull cn.sast.common.IResFile r9) {
            /*
                Method dump skipped, instructions count: 377
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.ProjectFileLocator.Companion.findJdkSources(cn.sast.common.IResFile):java.util.List");
        }

        private static final Object findJdkSources$lambda$0(IOException $ex) {
            return $ex + ", findSources()";
        }

        private static final Object findJdkSources$lambda$1(IOException $ex) {
            return $ex + ", findSources()";
        }
    }

    private static final Unit logger$lambda$2() {
        return Unit.INSTANCE;
    }
}
