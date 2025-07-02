package cn.sast.framework.validator;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: PrecisionMeasurement.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b6\u0018��2\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0002\u0007\b¨\u0006\t"}, d2 = {"Lcn/sast/framework/validator/RowType;", "", "<init>", "()V", "type", "getType", "()Ljava/lang/Object;", "Lcn/sast/framework/validator/RowCheckType;", "Lcn/sast/framework/validator/RowUnknownType;", "corax-framework"})
/* loaded from: RowType.class */
public abstract class RowType {
    @NotNull
    public abstract Object getType();

    public /* synthetic */ RowType(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    private RowType() {
    }
}
