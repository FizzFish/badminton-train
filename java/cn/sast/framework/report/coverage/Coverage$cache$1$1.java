package cn.sast.framework.report.coverage;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.report.coverage.Coverage;
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
import org.jacoco.core.internal.instr.InstrSupport;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

/* compiled from: Coverage.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcn/sast/framework/report/coverage/Coverage$ClassCoverage;", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "Coverage.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.report.coverage.Coverage$cache$1$1")
/* loaded from: Coverage$cache$1$1.class */
final class Coverage$cache$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Coverage.ClassCoverage>, Object> {
    int label;
    final /* synthetic */ ClassSourceInfo $source;
    final /* synthetic */ Coverage this$0;
    final /* synthetic */ Coverage.ClassCoverage $classCoverage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Coverage$cache$1$1(ClassSourceInfo $source, Coverage $receiver, Coverage.ClassCoverage $classCoverage, Continuation<? super Coverage$cache$1$1> continuation) {
        super(2, continuation);
        this.$source = $source;
        this.this$0 = $receiver;
        this.$classCoverage = $classCoverage;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new Coverage$cache$1$1(this.$source, this.this$0, this.$classCoverage, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Coverage.ClassCoverage> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Coverage.ClassCoverage classCoverage;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                try {
                    ClassReader reader = InstrSupport.classReaderFor(this.$source.getByteArray());
                    Coverage coverage = this.this$0;
                    Coverage.ClassCoverage classCoverage2 = this.$classCoverage;
                    long jacocoClassId = this.$source.getJacocoClassId();
                    String className = reader.getClassName();
                    Intrinsics.checkNotNullExpressionValue(className, "getClassName(...)");
                    ClassVisitor visitor = coverage.createAnalyzingVisitor(classCoverage2, jacocoClassId, className);
                    reader.accept(visitor, 0);
                    classCoverage = this.$classCoverage;
                } catch (Exception e) {
                    classCoverage = null;
                }
                return classCoverage;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
