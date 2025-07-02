package cn.sast.framework.report;

import cn.sast.common.FileSystemLocator;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcn/sast/framework/report/FileIndexer;", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "JavaSourceLocator.kt", l = {300}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"indexer"}, m = "invokeSuspend", c = "cn.sast.framework.report.FileSystemCacheLocator$cache$1$1")
/* loaded from: FileSystemCacheLocator$cache$1$1.class */
final class FileSystemCacheLocator$cache$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FileIndexer>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ IResource $root;
    final /* synthetic */ FileSystemLocator.TraverseMode $traverseMode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FileSystemCacheLocator$cache$1$1(IResource $root, FileSystemLocator.TraverseMode $traverseMode, Continuation<? super FileSystemCacheLocator$cache$1$1> continuation) {
        super(2, continuation);
        this.$root = $root;
        this.$traverseMode = $traverseMode;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new FileSystemCacheLocator$cache$1$1(this.$root, this.$traverseMode, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super FileIndexer> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        final FileIndexerBuilder indexer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                indexer = new FileIndexerBuilder();
                this.L$0 = indexer;
                this.label = 1;
                if (new FileSystemLocator() { // from class: cn.sast.framework.report.FileSystemCacheLocator$cache$1$1$locator$1
                    @Override // cn.sast.common.FileSystemLocator
                    public void visitFile(IResFile file) {
                        Intrinsics.checkNotNullParameter(file, "file");
                        indexer.addIndexMap(file);
                    }
                }.process(this.$root.getPath(), this.$traverseMode, (Continuation) this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case PseudoTopologicalOrderer.REVERSE /* 1 */:
                indexer = (FileIndexerBuilder) this.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return indexer.build();
    }
}
