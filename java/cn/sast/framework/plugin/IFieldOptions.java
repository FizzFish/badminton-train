package cn.sast.framework.plugin;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.SAOptions;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

/* compiled from: SAConfiguration.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018��2\u00020\u0001R\u0019\u0010\u0002\u001a\t\u0018\u00010\u0003¢\u0006\u0002\b\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/plugin/IFieldOptions;", "", "options", "Lcom/feysh/corax/config/api/SAOptions;", "Lkotlinx/serialization/Polymorphic;", "getOptions", "()Lcom/feysh/corax/config/api/SAOptions;", "corax-framework"})
/* loaded from: IFieldOptions.class */
public interface IFieldOptions {
    @Nullable
    SAOptions getOptions();
}
