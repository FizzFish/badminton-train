package cn.sast.dataflow.interprocedural.check;

import cn.sast.api.config.ExtSettings;
import cn.sast.api.report.BugPathEvent;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.idfa.analysis.InterproceduralCFG;
import com.feysh.corax.cache.analysis.SootInfoCache;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: PathGenerator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\f\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0004\u0018�� \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PathGeneratorImpl;", "", "<init>", "()V", "Companion", "corax-data-flow"})
/* loaded from: PathGeneratorImpl.class */
public final class PathGeneratorImpl {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final boolean dataFlowResultPathOnlyStmt = ExtSettings.INSTANCE.getDataFlowResultPathOnlyStmt();

    /* compiled from: PathGenerator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001:\u0001\u0014B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ2\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion;", "", "<init>", "()V", "dataFlowResultPathOnlyStmt", "", "getDataFlowResultPathOnlyStmt", "()Z", "getPathGenerator", "Lcn/sast/dataflow/interprocedural/check/PathGenerator;", "Lcn/sast/dataflow/interprocedural/check/IPath;", "generateEvents", "Lkotlin/sequences/Sequence;", "", "Lcn/sast/api/report/BugPathEvent;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "events", "EventGenerator", "corax-data-flow"})
    /* loaded from: PathGeneratorImpl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final boolean getDataFlowResultPathOnlyStmt() {
            return PathGeneratorImpl.dataFlowResultPathOnlyStmt;
        }

        @NotNull
        public final PathGenerator<IPath> getPathGenerator() {
            return new PathGenerator<IPath>() { // from class: cn.sast.dataflow.interprocedural.check.PathGeneratorImpl$Companion$getPathGenerator$g$1
                @Override // cn.sast.dataflow.interprocedural.check.PathGenerator
                public boolean getShouldExplore(IPath $this$shouldExplore) {
                    Intrinsics.checkNotNullParameter($this$shouldExplore, "<this>");
                    return !($this$shouldExplore instanceof UnknownPath);
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
                @Override // cn.sast.dataflow.interprocedural.check.PathGenerator
                public Collection<IPath> getPreds(IPath $this$preds) throws NoWhenBranchMatchedException {
                    Intrinsics.checkNotNullParameter($this$preds, "<this>");
                    if ($this$preds instanceof ModelBind) {
                        return CollectionsKt.listOf(((ModelBind) $this$preds).getPrev());
                    }
                    if ($this$preds instanceof MergePath) {
                        return ((MergePath) $this$preds).getAll();
                    }
                    if ($this$preds instanceof AssignLocalPath) {
                        return CollectionsKt.listOf(((AssignLocalPath) $this$preds).getRhsValePath());
                    }
                    if (!($this$preds instanceof EntryPath) && !($this$preds instanceof UnknownPath) && !($this$preds instanceof LiteralPath)) {
                        if ($this$preds instanceof GetEdgePath) {
                            return CollectionsKt.listOf(((GetEdgePath) $this$preds).getValuePath());
                        }
                        if ($this$preds instanceof SetEdgePath) {
                            return CollectionsKt.listOf(((SetEdgePath) $this$preds).getValuePath());
                        }
                        if ($this$preds instanceof InvokeEdgePath) {
                            return ((InvokeEdgePath) $this$preds).getInterproceduralPathMap().keySet();
                        }
                        throw new NoWhenBranchMatchedException();
                    }
                    return CollectionsKt.emptyList();
                }
            };
        }

        /* compiled from: PathGenerator.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��N\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018�� #2\u00020\u0001:\u0002\"#B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J(\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u00132\u0006\u0010\u0016\u001a\u00020\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0014J \u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001c*\u00020 2\u0006\u0010\u0016\u001a\u00020\rH\u0002J \u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013*\u00020 2\u0006\u0010\u0016\u001a\u00020\rH\u0002R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R)\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001c0\u001a¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u001e¨\u0006$"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion$EventGenerator;", "", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "icfg", "Lcn/sast/idfa/analysis/InterproceduralCFG;", "<init>", "(Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/idfa/analysis/InterproceduralCFG;)V", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "getIcfg", "()Lcn/sast/idfa/analysis/InterproceduralCFG;", "invokeCount", "", "getInvokeCount", "()I", "setInvokeCount", "(I)V", "gen", "Lkotlin/sequences/Sequence;", "", "Lcn/sast/api/report/BugPathEvent;", "deep", "events", "Lcn/sast/dataflow/interprocedural/check/IPath;", "cache", "", "Lcn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion$EventGenerator$CacheKey;", "", "getCache", "()Ljava/util/Map;", "diagnosticsCached", "Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "diagnostics", "CacheKey", "Companion", "corax-data-flow"})
        @SourceDebugExtension({"SMAP\nPathGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathGenerator.kt\ncn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion$EventGenerator\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,213:1\n381#2,3:214\n384#2,4:219\n1317#3,2:217\n*S KotlinDebug\n*F\n+ 1 PathGenerator.kt\ncn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion$EventGenerator\n*L\n172#1:214,3\n172#1:219,4\n174#1:217,2\n*E\n"})
        /* loaded from: PathGeneratorImpl$Companion$EventGenerator.class */
        public static final class EventGenerator {

            @Nullable
            private final SootInfoCache info;

            @NotNull
            private final InterproceduralCFG icfg;
            private int invokeCount;

            @NotNull
            private final Map<CacheKey, Set<List<BugPathEvent>>> cache;

            @NotNull
            public static final C0000Companion Companion = new C0000Companion(null);

            @NotNull
            private static final KLogger logger = KotlinLogging.INSTANCE.logger(EventGenerator::logger$lambda$3);

            public EventGenerator(@Nullable SootInfoCache info, @NotNull InterproceduralCFG icfg) {
                Intrinsics.checkNotNullParameter(icfg, "icfg");
                this.info = info;
                this.icfg = icfg;
                this.cache = new LinkedHashMap();
            }

            @Nullable
            public final SootInfoCache getInfo() {
                return this.info;
            }

            @NotNull
            public final InterproceduralCFG getIcfg() {
                return this.icfg;
            }

            public final int getInvokeCount() {
                return this.invokeCount;
            }

            public final void setInvokeCount(int i) {
                this.invokeCount = i;
            }

            @NotNull
            public final Sequence<List<BugPathEvent>> gen(int deep, @NotNull List<? extends IPath> list) {
                Intrinsics.checkNotNullParameter(list, "events");
                return SequencesKt.sequence(new PathGeneratorImpl$Companion$EventGenerator$gen$1(list, this, deep, null));
            }

            /* compiled from: PathGenerator.kt */
            @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion$EventGenerator$CacheKey;", "", "callee", "Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "deep", "", "<init>", "(Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;I)V", "getCallee", "()Lcn/sast/dataflow/interprocedural/check/InvokeEdgePath;", "getDeep", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "corax-data-flow"})
            /* loaded from: PathGeneratorImpl$Companion$EventGenerator$CacheKey.class */
            public static final class CacheKey {

                @NotNull
                private final InvokeEdgePath callee;
                private final int deep;

                @NotNull
                public final InvokeEdgePath component1() {
                    return this.callee;
                }

                public final int component2() {
                    return this.deep;
                }

                @NotNull
                public final CacheKey copy(@NotNull InvokeEdgePath callee, int deep) {
                    Intrinsics.checkNotNullParameter(callee, "callee");
                    return new CacheKey(callee, deep);
                }

                public static /* synthetic */ CacheKey copy$default(CacheKey cacheKey, InvokeEdgePath invokeEdgePath, int i, int i2, Object obj) {
                    if ((i2 & 1) != 0) {
                        invokeEdgePath = cacheKey.callee;
                    }
                    if ((i2 & 2) != 0) {
                        i = cacheKey.deep;
                    }
                    return cacheKey.copy(invokeEdgePath, i);
                }

                @NotNull
                public String toString() {
                    return "CacheKey(callee=" + this.callee + ", deep=" + this.deep + ")";
                }

                public int hashCode() {
                    int result = this.callee.hashCode();
                    return (result * 31) + Integer.hashCode(this.deep);
                }

                public boolean equals(@Nullable Object other) {
                    if (this == other) {
                        return true;
                    }
                    if (!(other instanceof CacheKey)) {
                        return false;
                    }
                    CacheKey cacheKey = (CacheKey) other;
                    return Intrinsics.areEqual(this.callee, cacheKey.callee) && this.deep == cacheKey.deep;
                }

                public CacheKey(@NotNull InvokeEdgePath callee, int deep) {
                    Intrinsics.checkNotNullParameter(callee, "callee");
                    this.callee = callee;
                    this.deep = deep;
                }

                @NotNull
                public final InvokeEdgePath getCallee() {
                    return this.callee;
                }

                public final int getDeep() {
                    return this.deep;
                }
            }

            @NotNull
            public final Map<CacheKey, Set<List<BugPathEvent>>> getCache() {
                return this.cache;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public final Set<List<BugPathEvent>> diagnosticsCached(InvokeEdgePath $this$diagnosticsCached, int deep) {
                Object obj;
                CacheKey k = new CacheKey($this$diagnosticsCached, deep);
                Map $this$getOrPut$iv = this.cache;
                Object value$iv = $this$getOrPut$iv.get(k);
                if (value$iv == null) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    Sequence $this$forEach$iv = SequencesKt.takeWhile(diagnostics($this$diagnosticsCached, deep), (v1) -> {
                        return diagnosticsCached$lambda$2$lambda$0(r1, v1);
                    });
                    for (Object element$iv : $this$forEach$iv) {
                        List it = (List) element$iv;
                        if (!it.isEmpty()) {
                            linkedHashSet.add(it);
                        }
                    }
                    $this$getOrPut$iv.put(k, linkedHashSet);
                    obj = linkedHashSet;
                } else {
                    obj = value$iv;
                }
                return (Set) obj;
            }

            private static final boolean diagnosticsCached$lambda$2$lambda$0(Set $set, List it) {
                Intrinsics.checkNotNullParameter(it, "it");
                return $set.size() < 20;
            }

            /* compiled from: PathGenerator.kt */
            @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PathGeneratorImpl$Companion$EventGenerator$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "getLogger", "()Lmu/KLogger;", "corax-data-flow"})
            /* renamed from: cn.sast.dataflow.interprocedural.check.PathGeneratorImpl$Companion$EventGenerator$Companion, reason: collision with other inner class name */
            /* loaded from: PathGeneratorImpl$Companion$EventGenerator$Companion.class */
            public static final class C0000Companion {
                public /* synthetic */ C0000Companion(DefaultConstructorMarker $constructor_marker) {
                    this();
                }

                private C0000Companion() {
                }

                @NotNull
                public final KLogger getLogger() {
                    return EventGenerator.logger;
                }
            }

            private static final Unit logger$lambda$3() {
                return Unit.INSTANCE;
            }

            private final Sequence<List<BugPathEvent>> diagnostics(InvokeEdgePath $this$diagnostics, int deep) {
                return SequencesKt.sequence(new PathGeneratorImpl$Companion$EventGenerator$diagnostics$1(this, $this$diagnostics, deep, null));
            }
        }

        @NotNull
        public final Sequence<List<BugPathEvent>> generateEvents(@Nullable SootInfoCache info, @NotNull InterproceduralCFG icfg, @NotNull List<? extends IPath> list) {
            Intrinsics.checkNotNullParameter(icfg, "icfg");
            Intrinsics.checkNotNullParameter(list, "events");
            EventGenerator g = new EventGenerator(info, icfg);
            return SequencesKt.map(g.gen(0, list), Companion::generateEvents$lambda$0);
        }

        private static final List generateEvents$lambda$0(List it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return PathGeneratorKt.getRemoveAdjacentDuplicates(it);
        }
    }
}
