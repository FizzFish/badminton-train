package cn.sast.framework.validator;

import cn.sast.api.config.ScanFilter;
import cn.sast.api.report.ExpectBugAnnotationData;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PrecisionMeasurement.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "PrecisionMeasurement.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.validator.AccuracyValidator$makeScore$2$1$1$1")
@SourceDebugExtension({"SMAP\nPrecisionMeasurement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PrecisionMeasurement.kt\ncn/sast/framework/validator/AccuracyValidator$makeScore$2$1$1$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,423:1\n774#2:424\n865#2,2:425\n*S KotlinDebug\n*F\n+ 1 PrecisionMeasurement.kt\ncn/sast/framework/validator/AccuracyValidator$makeScore$2$1$1$1\n*L\n109#1:424\n109#1:425,2\n*E\n"})
/* loaded from: AccuracyValidator$makeScore$2$1$1$1.class */
final class AccuracyValidator$makeScore$2$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ AccuracyValidator this$0;
    final /* synthetic */ IResFile $file;
    final /* synthetic */ Set<ExpectBugAnnotationData<String>> $expectedResults;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AccuracyValidator$makeScore$2$1$1$1(AccuracyValidator $receiver, IResFile $file, Set<ExpectBugAnnotationData<String>> set, Continuation<? super AccuracyValidator$makeScore$2$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
        this.$file = $file;
        this.$expectedResults = set;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new AccuracyValidator$makeScore$2$1$1$1(this.this$0, this.$file, this.$expectedResults, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                Iterable it = this.this$0.parseFile(this.$file);
                Set<ExpectBugAnnotationData<String>> set = this.$expectedResults;
                AccuracyValidator accuracyValidator = this.this$0;
                synchronized (set) {
                    Iterable $this$filter$iv = it;
                    Collection destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filter$iv) {
                        ExpectBugAnnotationData it2 = (ExpectBugAnnotationData) element$iv$iv;
                        if (ScanFilter.getActionOf$default(accuracyValidator.getMainConfig().getScanFilter(), accuracyValidator.rules, null, accuracyValidator.getMainConfig().getScanFilter().get(it2.getFile().getPath()), null, 8, null) != ProcessRule.ScanAction.Skip) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                    }
                    set.addAll((List) destination$iv$iv);
                    Unit unit = Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
