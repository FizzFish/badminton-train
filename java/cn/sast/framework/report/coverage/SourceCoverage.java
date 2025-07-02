package cn.sast.framework.report.coverage;

import cn.sast.common.IResFile;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.AbstractFileIndexer;
import cn.sast.framework.report.FileIndexer;
import cn.sast.framework.report.FileIndexerBuilder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jacoco.core.analysis.ISourceFileCoverage;
import org.jacoco.core.internal.analysis.CounterImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Coverage.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� \u000f2\u00020\u0001:\u0002\u000f\u0010B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lcn/sast/framework/report/coverage/SourceCoverage;", "", "sourceCoverage", "", "", "Lcn/sast/framework/report/coverage/SourceCoverage$JavaSourceCoverage;", "<init>", "(Ljava/util/Map;)V", "getSourceCoverage", "()Ljava/util/Map;", "calculateCoveredRatio", "Lorg/jacoco/core/internal/analysis/CounterImpl;", "targetSources", "", "Lcn/sast/common/IResFile;", "Companion", "JavaSourceCoverage", "corax-framework"})
@SourceDebugExtension({"SMAP\nCoverage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Coverage.kt\ncn/sast/framework/report/coverage/SourceCoverage\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,490:1\n1863#2,2:491\n1279#2,2:493\n1293#2,4:495\n*S KotlinDebug\n*F\n+ 1 Coverage.kt\ncn/sast/framework/report/coverage/SourceCoverage\n*L\n59#1:491,2\n63#1:493,2\n63#1:495,4\n*E\n"})
/* loaded from: SourceCoverage.class */
public final class SourceCoverage {

    @NotNull
    private final Map<String, JavaSourceCoverage> sourceCoverage;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SourceCoverage::logger$lambda$1);

    public SourceCoverage(@NotNull Map<String, JavaSourceCoverage> map) {
        Intrinsics.checkNotNullParameter(map, "sourceCoverage");
        this.sourceCoverage = map;
    }

    @NotNull
    public final Map<String, JavaSourceCoverage> getSourceCoverage() {
        return this.sourceCoverage;
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/report/coverage/SourceCoverage$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: SourceCoverage$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }

    /* compiled from: Coverage.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcn/sast/framework/report/coverage/SourceCoverage$JavaSourceCoverage;", "", "lineCount", "", "sourceCoverage", "Lorg/jacoco/core/analysis/ISourceFileCoverage;", "<init>", "(ILorg/jacoco/core/analysis/ISourceFileCoverage;)V", "getLineCount", "()I", "getSourceCoverage", "()Lorg/jacoco/core/analysis/ISourceFileCoverage;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "corax-framework"})
    /* loaded from: SourceCoverage$JavaSourceCoverage.class */
    public static final class JavaSourceCoverage {
        private final int lineCount;

        @NotNull
        private final ISourceFileCoverage sourceCoverage;

        public final int component1() {
            return this.lineCount;
        }

        @NotNull
        public final ISourceFileCoverage component2() {
            return this.sourceCoverage;
        }

        @NotNull
        public final JavaSourceCoverage copy(int lineCount, @NotNull ISourceFileCoverage sourceCoverage) {
            Intrinsics.checkNotNullParameter(sourceCoverage, "sourceCoverage");
            return new JavaSourceCoverage(lineCount, sourceCoverage);
        }

        public static /* synthetic */ JavaSourceCoverage copy$default(JavaSourceCoverage javaSourceCoverage, int i, ISourceFileCoverage iSourceFileCoverage, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = javaSourceCoverage.lineCount;
            }
            if ((i2 & 2) != 0) {
                iSourceFileCoverage = javaSourceCoverage.sourceCoverage;
            }
            return javaSourceCoverage.copy(i, iSourceFileCoverage);
        }

        @NotNull
        public String toString() {
            return "JavaSourceCoverage(lineCount=" + this.lineCount + ", sourceCoverage=" + this.sourceCoverage + ")";
        }

        public int hashCode() {
            int result = Integer.hashCode(this.lineCount);
            return (result * 31) + this.sourceCoverage.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof JavaSourceCoverage)) {
                return false;
            }
            JavaSourceCoverage javaSourceCoverage = (JavaSourceCoverage) other;
            return this.lineCount == javaSourceCoverage.lineCount && Intrinsics.areEqual(this.sourceCoverage, javaSourceCoverage.sourceCoverage);
        }

        public JavaSourceCoverage(int lineCount, @NotNull ISourceFileCoverage sourceCoverage) {
            Intrinsics.checkNotNullParameter(sourceCoverage, "sourceCoverage");
            this.lineCount = lineCount;
            this.sourceCoverage = sourceCoverage;
            if (!(this.lineCount >= 0)) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }

        public final int getLineCount() {
            return this.lineCount;
        }

        @NotNull
        public final ISourceFileCoverage getSourceCoverage() {
            return this.sourceCoverage;
        }
    }

    @NotNull
    public final CounterImpl calculateCoveredRatio(@NotNull Set<? extends IResFile> set) {
        int iCount;
        int lineCount;
        Intrinsics.checkNotNullParameter(set, "targetSources");
        int allLineCount = 0;
        int missedCount = 0;
        FileIndexerBuilder fileIndexerBuilder = new FileIndexerBuilder();
        Set<? extends IResFile> $this$forEach$iv = set;
        for (Object element$iv : $this$forEach$iv) {
            IResFile p0 = (IResFile) element$iv;
            fileIndexerBuilder.addIndexMap(p0);
        }
        FileIndexer fileIndexer = fileIndexerBuilder.build();
        Iterable $this$associateWith$iv = MapsKt.toList(this.sourceCoverage);
        Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv : $this$associateWith$iv) {
            Pair it = (Pair) element$iv$iv;
            result$iv.put(element$iv$iv, (IResFile) SequencesKt.firstOrNull(fileIndexer.findFromFileIndexMap(StringsKt.split$default((CharSequence) it.getFirst(), new String[]{"/", "\\"}, false, 0, 6, (Object) null), AbstractFileIndexer.Companion.getDefaultClassCompareMode())));
        }
        Map sourceMap = result$iv;
        Iterator it2 = sourceMap.entrySet().iterator();
        while (it2.hasNext()) {
            Pair coveragePair = (Pair) ((Map.Entry) it2.next()).getKey();
            JavaSourceCoverage coverage = (JavaSourceCoverage) coveragePair.component2();
            allLineCount += coverage.getLineCount();
            int missed = coverage.getSourceCoverage().getLineCounter().getMissedCount();
            int i = missedCount;
            if (missed <= coverage.getLineCount()) {
                lineCount = missed;
            } else {
                lineCount = coverage.getLineCount();
            }
            missedCount = i + lineCount;
        }
        Set<IResFile> missSource = SetsKt.minus(set, CollectionsKt.toSet(CollectionsKt.filterNotNull(sourceMap.values())));
        for (IResFile source : missSource) {
            try {
                iCount = SequencesKt.count(StringsKt.lineSequence(ResourceKt.readText$default(source, null, 1, null)));
            } catch (Exception e) {
                logger.error("File " + source + " cannot be read!", e);
                iCount = 0;
            }
            int lineCount2 = iCount;
            allLineCount += lineCount2;
            missedCount += lineCount2;
        }
        CounterImpl counterImpl = CounterImpl.getInstance(missedCount, allLineCount - missedCount);
        Intrinsics.checkNotNullExpressionValue(counterImpl, "getInstance(...)");
        return counterImpl;
    }
}
