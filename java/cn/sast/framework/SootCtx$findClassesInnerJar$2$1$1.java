package cn.sast.framework;

import cn.sast.api.util.Kotlin_extKt;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SootCtx.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\n\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"})
@DebugMetadata(f = "SootCtx.kt", l = {}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.SootCtx$findClassesInnerJar$2$1$1")
@SourceDebugExtension({"SMAP\nSootCtx.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootCtx.kt\ncn/sast/framework/SootCtx$findClassesInnerJar$2$1$1\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,731:1\n72#2,2:732\n1#3:734\n*S KotlinDebug\n*F\n+ 1 SootCtx.kt\ncn/sast/framework/SootCtx$findClassesInnerJar$2$1$1\n*L\n294#1:732,2\n294#1:734\n*E\n"})
/* loaded from: SootCtx$findClassesInnerJar$2$1$1.class */
final class SootCtx$findClassesInnerJar$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ConcurrentHashMap<String, Set<IResFile>> $md5Group;
    final /* synthetic */ ConcurrentHashMap<IResFile, String> $md5Map;
    final /* synthetic */ IResFile $jar;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SootCtx$findClassesInnerJar$2$1$1(ConcurrentHashMap<String, Set<IResFile>> concurrentHashMap, ConcurrentHashMap<IResFile, String> concurrentHashMap2, IResFile $jar, Continuation<? super SootCtx$findClassesInnerJar$2$1$1> continuation) {
        super(2, continuation);
        this.$md5Group = concurrentHashMap;
        this.$md5Map = concurrentHashMap2;
        this.$jar = $jar;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        return new SootCtx$findClassesInnerJar$2$1$1(this.$md5Group, this.$md5Map, this.$jar, continuation);
    }

    public final Object invoke(CoroutineScope p1, Continuation<? super Unit> continuation) {
        return create(p1, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure(obj);
                ConcurrentMap $this$getOrPut$iv = this.$md5Group;
                ConcurrentHashMap<IResFile, String> concurrentHashMap = this.$md5Map;
                IResFile iResFile = this.$jar;
                IResFile iResFile2 = this.$jar;
                final Function1 function1 = (v1) -> {
                    return invokeSuspend$lambda$0(r2, v1);
                };
                String strComputeIfAbsent = concurrentHashMap.computeIfAbsent(iResFile, new Function(function1) { // from class: cn.sast.framework.SootCtxKt$sam$java_util_function_Function$0
                    private final /* synthetic */ Function1 function;

                    {
                        Intrinsics.checkNotNullParameter(function1, "function");
                        this.function = function1;
                    }

                    @Override // java.util.function.Function
                    public final /* synthetic */ Object apply(Object p0) {
                        return this.function.invoke(p0);
                    }
                });
                Object objPutIfAbsent = $this$getOrPut$iv.get(strComputeIfAbsent);
                if (objPutIfAbsent == null) {
                    Set setConcurrentHashSetOf = Kotlin_extKt.concurrentHashSetOf();
                    objPutIfAbsent = $this$getOrPut$iv.putIfAbsent(strComputeIfAbsent, setConcurrentHashSetOf);
                    if (objPutIfAbsent == null) {
                        objPutIfAbsent = setConcurrentHashSetOf;
                    }
                }
                ((Set) objPutIfAbsent).add(this.$jar);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private static final String invokeSuspend$lambda$0(IResFile $jar, IResFile it) {
        return $jar.getMd5();
    }
}
