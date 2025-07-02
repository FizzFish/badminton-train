package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import sun.misc.Unsafe;

/* compiled from: UnsafeProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcn/sast/api/util/UnsafeProvider;", "", "<init>", "()V", "unsafe", "Lsun/misc/Unsafe;", "getUnsafe", "()Lsun/misc/Unsafe;", "unsafeInternal", "getUnsafeInternal", "corax-api"})
/* loaded from: UnsafeProvider.class */
public final class UnsafeProvider {

    @NotNull
    public static final UnsafeProvider INSTANCE = new UnsafeProvider();

    @NotNull
    private static final Unsafe unsafe = INSTANCE.getUnsafeInternal();

    private UnsafeProvider() {
    }

    @NotNull
    public final Unsafe getUnsafe() {
        return unsafe;
    }

    private final Unsafe getUnsafeInternal() throws IllegalAccessException, IllegalArgumentException {
        try {
            Unsafe unsafe2 = Unsafe.getUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe2, "getUnsafe(...)");
            return unsafe2;
        } catch (Throwable th) {
            Iterator it = ArrayIteratorKt.iterator(Unsafe.class.getDeclaredFields());
            while (it.hasNext()) {
                Field f = (Field) it.next();
                if (Intrinsics.areEqual(f.getType(), Unsafe.class)) {
                    f.setAccessible(true);
                    try {
                        Object obj = f.get(null);
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type sun.misc.Unsafe");
                        return (Unsafe) obj;
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException("Failed to access Unsafe member on Unsafe class", e);
                    }
                }
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[0];
            String str = String.format("Failed to find Unsafe member on Unsafe class, have: " + ArraysKt.contentDeepToString(Unsafe.class.getDeclaredFields()), Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            throw new IllegalStateException(str);
        }
    }
}
