package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import java.util.function.Consumer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileSystemsOperations.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
/* loaded from: FileSystemsOperations$sam$java_util_function_Consumer$0.class */
final class FileSystemsOperations$sam$java_util_function_Consumer$0 implements Consumer {
    private final /* synthetic */ Function1 function;

    FileSystemsOperations$sam$java_util_function_Consumer$0(Function1 function) {
        Intrinsics.checkNotNullParameter(function, "function");
        this.function = function;
    }

    @Override // java.util.function.Consumer
    public final /* synthetic */ void accept(Object p0) {
        this.function.invoke(p0);
    }
}
