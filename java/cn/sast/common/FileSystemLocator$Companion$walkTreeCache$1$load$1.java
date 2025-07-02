package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FileSystemLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcn/sast/common/WalkFileTreeResult;", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "FileSystemLocator.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.common.FileSystemLocator$Companion$walkTreeCache$1$load$1")
/* loaded from: FileSystemLocator$Companion$walkTreeCache$1$load$1.class */
final class FileSystemLocator$Companion$walkTreeCache$1$load$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super WalkFileTreeResult>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ Path $path;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FileSystemLocator$Companion$walkTreeCache$1$load$1(Path $path, Continuation<? super FileSystemLocator$Companion$walkTreeCache$1$load$1> continuation) {
        super(2, continuation);
        this.$path = $path;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> fileSystemLocator$Companion$walkTreeCache$1$load$1 = new FileSystemLocator$Companion$walkTreeCache$1$load$1(this.$path, continuation);
        fileSystemLocator$Companion$walkTreeCache$1$load$1.L$0 = value;
        return fileSystemLocator$Companion$walkTreeCache$1$load$1;
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super WalkFileTreeResult> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:6:0x0047
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r0 = r6
            int r0 = r0.label
            switch(r0) {
                case 0: goto L1c;
                default: goto L78;
            }
        L1c:
            r0 = r7
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r6
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            r8 = r0
            r0 = r8
            r9 = r0
            r0 = r6
            java.nio.file.Path r0 = r0.$path
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r10
            r1 = 0
            java.nio.file.LinkOption[] r1 = new java.nio.file.LinkOption[r1]
            r2 = r1
            int r2 = r2.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)
            java.nio.file.LinkOption[] r1 = (java.nio.file.LinkOption[]) r1
            boolean r0 = java.nio.file.Files.exists(r0, r1)
            if (r0 == 0) goto L65
        L48:
            cn.sast.common.FileSystemLocator$Companion r0 = cn.sast.common.FileSystemLocator.Companion     // Catch: java.lang.Exception -> L51
            r1 = r10
            cn.sast.common.WalkFileTreeResult r0 = cn.sast.common.FileSystemLocator.Companion.access$visit(r0, r1)     // Catch: java.lang.Exception -> L51
            return r0
        L51:
            r12 = move-exception
            mu.KLogger r0 = cn.sast.common.FileSystemLocator.access$getLogger$cp()
            r1 = r10
            r2 = r12
            java.lang.Object r1 = () -> { // kotlin.jvm.functions.Function0.invoke():java.lang.Object
                return invokeSuspend$lambda$1$lambda$0(r1, r2);
            }
            r0.error(r1)
        L65:
            cn.sast.common.WalkFileTreeResult r0 = new cn.sast.common.WalkFileTreeResult
            r1 = r0
            r2 = r6
            java.nio.file.Path r2 = r2.$path
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
            r1.<init>(r2, r3, r4)
            return r0
        L78:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.common.FileSystemLocator$Companion$walkTreeCache$1$load$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    private static final Object invokeSuspend$lambda$1$lambda$0(Path $path, Exception $e) {
        return "failed to traverse path: `" + $path + "` " + $e.getMessage();
    }
}
