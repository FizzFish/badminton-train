package cn.sast.common;

import cn.sast.common.FileSystemLocator;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import mu.KLogger;

/* compiled from: FileSystemLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "FileSystemLocator.kt", l = {59}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.common.FileSystemLocator$visit$2$1$1")
/* loaded from: FileSystemLocator$visit$2$1$1.class */
final class FileSystemLocator$visit$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ FileSystemLocator.TraverseMode $traverseMode;
    final /* synthetic */ FileSystemLocator this$0;
    final /* synthetic */ IResFile $archiveLike;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FileSystemLocator$visit$2$1$1(FileSystemLocator.TraverseMode $traverseMode, FileSystemLocator $receiver, IResFile $archiveLike, Continuation<? super FileSystemLocator$visit$2$1$1> continuation) {
        super(2, continuation);
        this.$traverseMode = $traverseMode;
        this.this$0 = $receiver;
        this.$archiveLike = $archiveLike;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new FileSystemLocator$visit$2$1$1(this.$traverseMode, this.this$0, this.$archiveLike, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        WalkFileTreeResult walkFileTreeResult;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                FileSystemLocator.TraverseMode traverseModeIn = this.$traverseMode == FileSystemLocator.TraverseMode.IndexArchive ? FileSystemLocator.TraverseMode.Default : this.$traverseMode;
                try {
                    walkFileTreeResult = this.this$0.traverseArchive(this.$archiveLike);
                } catch (Exception e) {
                    KLogger kLogger = FileSystemLocator.logger;
                    IResFile iResFile = this.$archiveLike;
                    kLogger.error(() -> {
                        return invokeSuspend$lambda$0(r1, r2);
                    });
                    IResFile iResFile2 = this.$archiveLike;
                    FileSystemLocator.logger.debug(e, () -> {
                        return invokeSuspend$lambda$1(r2);
                    });
                    walkFileTreeResult = new WalkFileTreeResult(this.$archiveLike.getPath(), CollectionsKt.emptyList(), CollectionsKt.emptyList());
                }
                WalkFileTreeResult archiveFiles = walkFileTreeResult;
                this.label = 1;
                if (this.this$0.visit(archiveFiles, traverseModeIn, (Continuation) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    private static final Object invokeSuspend$lambda$0(IResFile $archiveLike, Exception $e) {
        return "invalid archive file: `" + $archiveLike + "`. e: " + $e.getMessage();
    }

    private static final Object invokeSuspend$lambda$1(IResFile $archiveLike) {
        return "invalid archive file: `" + $archiveLike + "`.";
    }
}
