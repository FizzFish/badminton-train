package cn.sast.framework.report.metadata;

import cn.sast.api.report.ClassResInfo;
import cn.sast.common.IResFile;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.report.IProjectFileLocator;
import cn.sast.framework.report.NullWrapperFileGenerator;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import soot.SootClass;
import soot.jimple.infoflow.collect.ConcurrentHashSet;

/* compiled from: MetadataGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "appClass", "Lsoot/SootClass;"})
@DebugMetadata(f = "MetadataGenerator.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.metadata.MetadataGenerator$getMetadata$task$1")
/* loaded from: MetadataGenerator$getMetadata$task$1.class */
final class MetadataGenerator$getMetadata$task$1 extends SuspendLambda implements Function2<SootClass, Continuation<? super Unit>, Object> {
    int label;
    /* synthetic */ Object L$0;
    final /* synthetic */ IProjectFileLocator $locator;
    final /* synthetic */ ConcurrentHashSet<String> $visited;
    final /* synthetic */ AtomicInteger $fileCount;
    final /* synthetic */ AtomicInteger $lineCount;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MetadataGenerator$getMetadata$task$1(IProjectFileLocator $locator, ConcurrentHashSet<String> concurrentHashSet, AtomicInteger $fileCount, AtomicInteger $lineCount, Continuation<? super MetadataGenerator$getMetadata$task$1> continuation) {
        super(2, continuation);
        this.$locator = $locator;
        this.$visited = concurrentHashSet;
        this.$fileCount = $fileCount;
        this.$lineCount = $lineCount;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> metadataGenerator$getMetadata$task$1 = new MetadataGenerator$getMetadata$task$1(this.$locator, this.$visited, this.$fileCount, this.$lineCount, continuation);
        metadataGenerator$getMetadata$task$1.L$0 = value;
        return metadataGenerator$getMetadata$task$1;
    }

    public final Object invoke(SootClass p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                SootClass appClass = (SootClass) this.L$0;
                IResFile file = this.$locator.get(new ClassResInfo(appClass), NullWrapperFileGenerator.INSTANCE);
                if (file != null) {
                    ConcurrentHashSet<String> concurrentHashSet = this.$visited;
                    AtomicInteger atomicInteger = this.$fileCount;
                    AtomicInteger atomicInteger2 = this.$lineCount;
                    if (concurrentHashSet.add(file.getAbsolutePath())) {
                        atomicInteger.incrementAndGet();
                        atomicInteger2.addAndGet(((Number) ResourceKt.lineSequence(file, MetadataGenerator$getMetadata$task$1::invokeSuspend$lambda$1$lambda$0)).intValue());
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private static final int invokeSuspend$lambda$1$lambda$0(Sequence it) {
        return SequencesKt.count(it);
    }
}
