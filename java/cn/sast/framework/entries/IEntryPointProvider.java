package cn.sast.framework.entries;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;

/* compiled from: IEntryPointProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018��2\u00020\u0001:\u0001\nJ\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcn/sast/framework/entries/IEntryPointProvider;", "", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "startAnalyse", "", "endAnalyse", "AnalyzeTask", "corax-framework"})
/* loaded from: IEntryPointProvider.class */
public interface IEntryPointProvider {
    @NotNull
    Flow<AnalyzeTask> getIterator();

    void startAnalyse();

    void endAnalyse();

    /* compiled from: IEntryPointProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u001c\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR\u001c\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\n¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "", "name", "", "getName", "()Ljava/lang/String;", "entries", "", "Lsoot/SootMethod;", "getEntries", "()Ljava/util/Set;", "methodsMustAnalyze", "getMethodsMustAnalyze", "additionalEntries", "getAdditionalEntries", "components", "Lsoot/SootClass;", "getComponents", "needConstructCallGraph", "", "sootCtx", "Lcn/sast/framework/SootCtx;", "corax-framework"})
    /* loaded from: IEntryPointProvider$AnalyzeTask.class */
    public interface AnalyzeTask {
        @NotNull
        String getName();

        @NotNull
        Set<SootMethod> getEntries();

        @NotNull
        Set<SootMethod> getMethodsMustAnalyze();

        @Nullable
        Set<SootMethod> getAdditionalEntries();

        @Nullable
        /* renamed from: getComponents */
        Set<SootClass> mo276getComponents();

        void needConstructCallGraph(@NotNull SootCtx sootCtx);

        /* compiled from: IEntryPointProvider.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
        @SourceDebugExtension({"SMAP\nIEntryPointProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IEntryPointProvider.kt\ncn/sast/framework/entries/IEntryPointProvider$AnalyzeTask$DefaultImpls\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,25:1\n774#2:26\n865#2,2:27\n1454#2,5:29\n*S KotlinDebug\n*F\n+ 1 IEntryPointProvider.kt\ncn/sast/framework/entries/IEntryPointProvider$AnalyzeTask$DefaultImpls\n*L\n15#1:26\n15#1:27,2\n15#1:29,5\n*E\n"})
        /* loaded from: IEntryPointProvider$AnalyzeTask$DefaultImpls.class */
        public static final class DefaultImpls {
            @NotNull
            public static Set<SootMethod> getMethodsMustAnalyze(@NotNull AnalyzeTask $this) {
                Iterable applicationClasses = Scene.v().getApplicationClasses();
                Intrinsics.checkNotNullExpressionValue(applicationClasses, "getApplicationClasses(...)");
                Iterable $this$filter$iv = applicationClasses;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    SootClass it = (SootClass) element$iv$iv;
                    if (it.isInScene()) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                Iterable $this$flatMapTo$iv = (List) destination$iv$iv;
                Collection destination$iv = (Set) new LinkedHashSet();
                for (Object element$iv : $this$flatMapTo$iv) {
                    SootClass it2 = (SootClass) element$iv;
                    Iterable methods = it2.getMethods();
                    Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
                    Iterable list$iv = methods;
                    CollectionsKt.addAll(destination$iv, list$iv);
                }
                return (Set) destination$iv;
            }

            @Nullable
            public static Set<SootMethod> getAdditionalEntries(@NotNull AnalyzeTask $this) {
                return null;
            }

            @Nullable
            public static Set<SootClass> getComponents(@NotNull AnalyzeTask $this) {
                return null;
            }
        }
    }

    /* compiled from: IEntryPointProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IEntryPointProvider$DefaultImpls.class */
    public static final class DefaultImpls {
        public static void startAnalyse(@NotNull IEntryPointProvider $this) {
        }

        public static void endAnalyse(@NotNull IEntryPointProvider $this) {
        }
    }
}
