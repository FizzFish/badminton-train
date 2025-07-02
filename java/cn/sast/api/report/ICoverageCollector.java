package cn.sast.api.report;

import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.nio.charset.Charset;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jacoco.core.analysis.ICounter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ICoverageCollector.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��2\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH¦@¢\u0006\u0002\u0010\u000fJ$\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u000eH¦@¢\u0006\u0002\u0010\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0017"}, d2 = {"Lcn/sast/api/report/ICoverageCollector;", "", "enableCoveredTaint", "", "getEnableCoveredTaint", "()Z", "cover", "", "coverInfo", "Lcn/sast/api/report/CoverData;", "flush", "output", "Lcn/sast/common/IResDirectory;", "sourceEncoding", "Ljava/nio/charset/Charset;", "(Lcn/sast/common/IResDirectory;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCoveredLineCounter", "Lorg/jacoco/core/analysis/ICounter;", "allSourceFiles", "", "Lcn/sast/common/IResFile;", "encoding", "(Ljava/util/Set;Ljava/nio/charset/Charset;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "corax-api"})
/* loaded from: ICoverageCollector.class */
public interface ICoverageCollector {
    boolean getEnableCoveredTaint();

    void cover(@NotNull CoverData coverData);

    @Nullable
    Object flush(@NotNull IResDirectory iResDirectory, @NotNull Charset charset, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object getCoveredLineCounter(@NotNull Set<? extends IResFile> set, @NotNull Charset charset, @NotNull Continuation<? super ICounter> continuation);
}
