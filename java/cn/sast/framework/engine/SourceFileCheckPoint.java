package cn.sast.framework.engine;

import cn.sast.api.config.MainConfig;
import cn.sast.api.report.DefaultEnv;
import cn.sast.api.report.FileResInfo;
import cn.sast.api.report.IBugResInfo;
import cn.sast.common.IResFile;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.ISourceFileCheckPoint;
import com.feysh.corax.config.api.report.Region;
import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018�� /2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001/B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000e\u0010+\u001a\u00020%H\u0096@¢\u0006\u0002\u0010,J\u0010\u0010&\u001a\u0004\u0018\u00010'H\u0096@¢\u0006\u0002\u0010,J\u001a\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0*0)H\u0096@¢\u0006\u0002\u0010,J\b\u0010-\u001a\u00020.H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0018\u001a\u0004\u0018\u00010\r8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0019\u0010\u000fR\u0014\u0010\u001c\u001a\u00020\u001dX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!8PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n��R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n��R\u001c\u0010(\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020'0*\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n��¨\u00060"}, d2 = {"Lcn/sast/framework/engine/SourceFileCheckPoint;", "Lcom/feysh/corax/config/api/ISourceFileCheckPoint;", "Lcn/sast/framework/engine/CheckPoint;", "Ljava/io/Closeable;", "sFile", "Lcn/sast/common/IResFile;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "<init>", "(Lcn/sast/common/IResFile;Lcn/sast/api/config/MainConfig;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "path", "Ljava/nio/file/Path;", "getPath", "()Ljava/nio/file/Path;", "relativePath", "Lcn/sast/api/config/MainConfig$RelativePath;", "getRelativePath", "()Lcn/sast/api/config/MainConfig$RelativePath;", "uri", "Ljava/net/URI;", "getUri", "()Ljava/net/URI;", "archiveFile", "getArchiveFile", "archiveFile$delegate", "Lkotlin/Lazy;", "file", "Lcn/sast/api/report/IBugResInfo;", "getFile", "()Lcn/sast/api/report/IBugResInfo;", "env", "Lcn/sast/api/report/DefaultEnv;", "getEnv$corax_framework", "()Lcn/sast/api/report/DefaultEnv;", "bytes", "", "text", "", "lines", "", "Lkotlin/collections/IndexedValue;", "readAllBytes", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nPreAnalysisImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/SourceFileCheckPoint\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,760:1\n1#2:761\n1557#3:762\n1628#3,3:763\n*S KotlinDebug\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/SourceFileCheckPoint\n*L\n747#1:762\n747#1:763,3\n*E\n"})
/* loaded from: SourceFileCheckPoint.class */
public final class SourceFileCheckPoint extends CheckPoint implements ISourceFileCheckPoint, Closeable {

    @NotNull
    private final IResFile sFile;

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final Path path;

    @NotNull
    private final Lazy archiveFile$delegate;

    @NotNull
    private final IBugResInfo file;

    @Nullable
    private byte[] bytes;

    @Nullable
    private String text;

    @Nullable
    private List<IndexedValue<String>> lines;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SourceFileCheckPoint::logger$lambda$7);

    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "PreAnalysisImpl.kt", l = {747}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"this"}, m = "lines", c = "cn.sast.framework.engine.SourceFileCheckPoint")
    /* renamed from: cn.sast.framework.engine.SourceFileCheckPoint$lines$1, reason: invalid class name */
    /* loaded from: SourceFileCheckPoint$lines$1.class */
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
            return SourceFileCheckPoint.this.lines((Continuation) this);
        }
    }

    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "PreAnalysisImpl.kt", l = {737}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"this"}, m = "text", c = "cn.sast.framework.engine.SourceFileCheckPoint")
    /* renamed from: cn.sast.framework.engine.SourceFileCheckPoint$text$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: SourceFileCheckPoint$text$1.class */
    static final class C00291 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        C00291(Continuation<? super C00291> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return SourceFileCheckPoint.this.text((Continuation) this);
        }
    }

    @NotNull
    public String getFilename() {
        return ISourceFileCheckPoint.DefaultImpls.getFilename(this);
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    public SourceFileCheckPoint(@NotNull IResFile sFile, @NotNull MainConfig mainConfig) {
        Intrinsics.checkNotNullParameter(sFile, "sFile");
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        this.sFile = sFile;
        this.mainConfig = mainConfig;
        this.path = this.sFile.getPath();
        this.archiveFile$delegate = LazyKt.lazy(() -> {
            return archiveFile_delegate$lambda$0(r1);
        });
        this.file = new FileResInfo(this.sFile);
    }

    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/engine/SourceFileCheckPoint$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: SourceFileCheckPoint$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$7() {
        return Unit.INSTANCE;
    }

    @NotNull
    public Path getPath() {
        return this.path;
    }

    @NotNull
    /* renamed from: getRelativePath, reason: merged with bridge method [inline-methods] */
    public MainConfig.RelativePath m285getRelativePath() {
        return this.mainConfig.tryGetRelativePath(this.sFile);
    }

    @NotNull
    public URI getUri() {
        return this.sFile.getUri();
    }

    @Nullable
    public Path getArchiveFile() {
        return (Path) this.archiveFile$delegate.getValue();
    }

    private static final Path archiveFile_delegate$lambda$0(SourceFileCheckPoint this$0) {
        if (!this$0.sFile.isJarScheme()) {
            return null;
        }
        Path absolutePath = this$0.sFile.getSchemePath().toAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "toAbsolutePath(...)");
        return absolutePath;
    }

    @Override // cn.sast.framework.engine.CheckPoint
    @NotNull
    public IBugResInfo getFile() {
        return this.file;
    }

    @Override // cn.sast.framework.engine.CheckPoint
    @NotNull
    public DefaultEnv getEnv$corax_framework() {
        return new DefaultEnv(Region.Companion.getERROR().getMutable(), null, null, null, null, null, null, null, null, 510, null);
    }

    /* compiled from: PreAnalysisImpl.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
    @DebugMetadata(f = "PreAnalysisImpl.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.engine.SourceFileCheckPoint$readAllBytes$3")
    @SourceDebugExtension({"SMAP\nPreAnalysisImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PreAnalysisImpl.kt\ncn/sast/framework/engine/SourceFileCheckPoint$readAllBytes$3\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,760:1\n1#2:761\n*E\n"})
    /* renamed from: cn.sast.framework.engine.SourceFileCheckPoint$readAllBytes$3, reason: invalid class name */
    /* loaded from: SourceFileCheckPoint$readAllBytes$3.class */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            return SourceFileCheckPoint.this.new AnonymousClass3(continuation);
        }

        public final Object invoke(CoroutineScope p1, Continuation<? super byte[]> continuation) {
            return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) throws IOException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure(obj);
                    byte[] it = ResourceKt.readAllBytes(SourceFileCheckPoint.this.sFile);
                    SourceFileCheckPoint.this.bytes = it;
                    return it;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Nullable
    public Object readAllBytes(@NotNull Continuation<? super byte[]> continuation) {
        byte[] it = this.bytes;
        byte[] bArr = it != null ? it : null;
        return bArr == null ? BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass3(null), continuation) : bArr;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:19:0x007d
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @org.jetbrains.annotations.Nullable
    public java.lang.Object text(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r6) {
        /*
            Method dump skipped, instructions count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.SourceFileCheckPoint.text(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0027  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object lines(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.util.List<kotlin.collections.IndexedValue<java.lang.String>>> r8) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.engine.SourceFileCheckPoint.lines(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.text = null;
        this.lines = null;
        this.bytes = null;
    }
}
