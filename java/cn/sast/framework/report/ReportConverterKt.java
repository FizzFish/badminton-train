package cn.sast.framework.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.lang.reflect.Field;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReportConverter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\u0018\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a$\u0010\u0002\u001a\u0004\u0018\u0001H\u0003\"\u0006\b��\u0010\u0003\u0018\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0086\b¢\u0006\u0002\u0010\u0007*\n\u0010��\"\u00020\u00012\u00020\u0001¨\u0006\b"}, d2 = {"JavaDecompileUnit", "", "getField", "T", "", "name", "", "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;", "corax-framework"})
/* loaded from: ReportConverterKt.class */
public final class ReportConverterKt {
    public static final /* synthetic */ <T> T getField(Object obj, String str) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        Intrinsics.checkNotNullParameter(str, "name");
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        Object obj2 = declaredField.get(obj);
        Intrinsics.reifiedOperationMarker(2, "T");
        return (T) obj2;
    }
}
