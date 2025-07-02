package cn.sast.framework.entries.javaee;

import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.entries.java.UnReachableEntryProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.utbot.common.LoggerWithLogMethod;
import org.utbot.common.LoggingKt;
import org.utbot.common.Maybe;
import soot.SootMethod;

/* compiled from: JavaEeEntryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\u000e\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;"})
@DebugMetadata(f = "JavaEeEntryProvider.kt", l = {102}, i = {}, s = {}, n = {}, m = "invokeSuspend", c = "cn.sast.framework.entries.javaee.JavaEeEntryProvider$iterator$1")
@SourceDebugExtension({"SMAP\nJavaEeEntryProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaEeEntryProvider.kt\ncn/sast/framework/entries/javaee/JavaEeEntryProvider$iterator$1\n+ 2 Logging.kt\norg/utbot/common/LoggingKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,104:1\n49#2,13:105\n62#2,11:121\n1628#3,3:118\n*S KotlinDebug\n*F\n+ 1 JavaEeEntryProvider.kt\ncn/sast/framework/entries/javaee/JavaEeEntryProvider$iterator$1\n*L\n90#1:105,13\n90#1:121,11\n91#1:118,3\n*E\n"})
/* loaded from: JavaEeEntryProvider$iterator$1.class */
final class JavaEeEntryProvider$iterator$1 extends SuspendLambda implements Function2<FlowCollector<? super IEntryPointProvider.AnalyzeTask>, Continuation<? super Unit>, Object> {
    int label;
    private /* synthetic */ Object L$0;
    final /* synthetic */ JavaEeEntryProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JavaEeEntryProvider$iterator$1(JavaEeEntryProvider $receiver, Continuation<? super JavaEeEntryProvider$iterator$1> continuation) {
        super(2, continuation);
        this.this$0 = $receiver;
    }

    public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
        Continuation<Unit> javaEeEntryProvider$iterator$1 = new JavaEeEntryProvider$iterator$1(this.this$0, continuation);
        javaEeEntryProvider$iterator$1.L$0 = value;
        return javaEeEntryProvider$iterator$1;
    }

    public final Object invoke(FlowCollector<? super IEntryPointProvider.AnalyzeTask> flowCollector, Continuation<? super Unit> continuation) {
        return create(flowCollector, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case PointsToGraphKt.pathStrictMod /* 0 */:
                ResultKt.throwOnFailure($result);
                FlowCollector $this$flow = (FlowCollector) this.L$0;
                LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(JavaEeEntryProvider.logger);
                final String msg$iv = "construct Java EE component";
                JavaEeEntryProvider javaEeEntryProvider = this.this$0;
                $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.entries.javaee.JavaEeEntryProvider$iterator$1$invokeSuspend$$inlined$bracket$default$1
                    public final Object invoke() {
                        return "Started: " + msg$iv;
                    }
                });
                final LocalDateTime startTime$iv = LocalDateTime.now();
                final Ref.ObjectRef res$iv = new Ref.ObjectRef();
                res$iv.element = Maybe.Companion.empty();
                try {
                    try {
                        Iterable $this$mapTo$iv = javaEeEntryProvider.getBeanXmls();
                        Collection destination$iv = (Set) new LinkedHashSet();
                        for (Object item$iv : $this$mapTo$iv) {
                            IResFile it = (IResFile) item$iv;
                            destination$iv.add(it.getNormalize().expandRes(javaEeEntryProvider.ctx.getMainConfig().getOutput_dir()).toString());
                        }
                        res$iv.element = new Maybe(javaEeEntryProvider.createDummyMain((Set) destination$iv));
                        Object orThrow = ((Maybe) res$iv.element).getOrThrow();
                        if (((Maybe) res$iv.element).getHasValue()) {
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.entries.javaee.JavaEeEntryProvider$iterator$1$invokeSuspend$$inlined$bracket$default$2
                                public final Object invoke() {
                                    LocalDateTime localDateTime = startTime$iv;
                                    Intrinsics.checkNotNull(localDateTime);
                                    String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                    String str = msg$iv;
                                    Result.Companion companion = Result.Companion;
                                    Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                                    return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                                }
                            });
                        } else {
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.entries.javaee.JavaEeEntryProvider$iterator$1$invokeSuspend$$inlined$bracket$default$3
                                public final Object invoke() {
                                    LocalDateTime localDateTime = startTime$iv;
                                    Intrinsics.checkNotNull(localDateTime);
                                    return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                                }
                            });
                        }
                        SootMethod dummyMain = (SootMethod) orThrow;
                        UnReachableEntryProvider unReachableEntryProvider = new UnReachableEntryProvider(this.this$0.ctx, null, 2, null);
                        if (dummyMain != null) {
                            Set<String> exclude = unReachableEntryProvider.getExclude();
                            String signature = dummyMain.getSignature();
                            Intrinsics.checkNotNullExpressionValue(signature, "getSignature(...)");
                            exclude.add(signature);
                        }
                        this.label = 1;
                        if (FlowKt.emitAll($this$flow, unReachableEntryProvider.getIterator(), (Continuation) this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } catch (Throwable t$iv) {
                        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.entries.javaee.JavaEeEntryProvider$iterator$1$invokeSuspend$$inlined$bracket$default$4
                            public final Object invoke() {
                                LocalDateTime localDateTime = startTime$iv;
                                Intrinsics.checkNotNull(localDateTime);
                                String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                String str = msg$iv;
                                Result.Companion companion = Result.Companion;
                                Result.constructor-impl(ResultKt.createFailure(t$iv));
                                return "Finished (in " + strElapsedSecFrom + "): " + str + " :: EXCEPTION :: " + "";
                            }
                        });
                        throw t$iv;
                    }
                } catch (Throwable th) {
                    if (0 == 0) {
                        if (((Maybe) res$iv.element).getHasValue()) {
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.entries.javaee.JavaEeEntryProvider$iterator$1$invokeSuspend$$inlined$bracket$default$5
                                public final Object invoke() {
                                    LocalDateTime localDateTime = startTime$iv;
                                    Intrinsics.checkNotNull(localDateTime);
                                    String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                                    String str = msg$iv;
                                    Result.Companion companion = Result.Companion;
                                    Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                                    return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                                }
                            });
                        } else {
                            $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.entries.javaee.JavaEeEntryProvider$iterator$1$invokeSuspend$$inlined$bracket$default$6
                                public final Object invoke() {
                                    LocalDateTime localDateTime = startTime$iv;
                                    Intrinsics.checkNotNull(localDateTime);
                                    return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                                }
                            });
                        }
                    }
                    throw th;
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
}
