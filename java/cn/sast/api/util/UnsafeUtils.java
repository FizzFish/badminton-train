package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.ClassWriter;

/* compiled from: UnsafeUtils.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00060\u0005\"\u0004\b��\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005¨\u0006\b"}, d2 = {"Lcn/sast/api/util/UnsafeUtils;", "", "<init>", "()V", "defineAnonymousConcreteSubclass", "Ljava/lang/Class;", "T", "abstractClass", "corax-api"})
@SourceDebugExtension({"SMAP\nUnsafeUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnsafeUtils.kt\ncn/sast/api/util/UnsafeUtils\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"})
/* loaded from: UnsafeUtils.class */
public final class UnsafeUtils {

    @NotNull
    public static final UnsafeUtils INSTANCE = new UnsafeUtils();

    private UnsafeUtils() {
    }

    @NotNull
    public final <T> Class<? extends T> defineAnonymousConcreteSubclass(@NotNull Class<T> cls) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<? extends T> clsAsSubclass;
        Intrinsics.checkNotNullParameter(cls, "abstractClass");
        if (!Modifier.isAbstract(cls.getModifiers())) {
            throw new IllegalArgumentException((cls + " is not abstract").toString());
        }
        ClassWriter cw = new ClassWriter(0);
        String name = cls.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        String superClassName = StringsKt.replace$default(name, '.', '/', false, 4, (Object) null);
        String name2 = UnsafeUtils.class.getPackage().getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        String className = StringsKt.replace$default(name2, '.', '/', false, 4, (Object) null) + "/Anonymous";
        cw.visit(52, 0, className, (String) null, superClassName, (String[]) null);
        cw.visitEnd();
        try {
            Stream stream = Arrays.stream(MethodHandles.Lookup.class.getMethods());
            final Function1 function1 = UnsafeUtils::defineAnonymousConcreteSubclass$lambda$1;
            Optional defineHiddenClass = stream.filter(new Predicate(function1) { // from class: cn.sast.api.util.UnsafeUtils$sam$java_util_function_Predicate$0
                private final /* synthetic */ Function1 function;

                {
                    Intrinsics.checkNotNullParameter(function1, "function");
                    this.function = function1;
                }

                @Override // java.util.function.Predicate
                public final /* synthetic */ boolean test(Object p0) {
                    return ((Boolean) this.function.invoke(p0)).booleanValue();
                }
            }).findFirst();
            Stream stream2 = Arrays.stream(MethodHandles.Lookup.class.getClasses());
            final Function1 function12 = UnsafeUtils::defineAnonymousConcreteSubclass$lambda$2;
            Optional classOption = stream2.filter(new Predicate(function12) { // from class: cn.sast.api.util.UnsafeUtils$sam$java_util_function_Predicate$0
                private final /* synthetic */ Function1 function;

                {
                    Intrinsics.checkNotNullParameter(function12, "function");
                    this.function = function12;
                }

                @Override // java.util.function.Predicate
                public final /* synthetic */ boolean test(Object p0) {
                    return ((Boolean) this.function.invoke(p0)).booleanValue();
                }
            }).findFirst();
            if (defineHiddenClass.isPresent() && classOption.isPresent()) {
                Object objInvoke = ((Method) defineHiddenClass.get()).invoke(MethodHandles.lookup(), cw.toByteArray(), true, Array.newInstance((Class<?>) classOption.get(), 0));
                Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type java.lang.invoke.MethodHandles.Lookup");
                clsAsSubclass = ((MethodHandles.Lookup) objInvoke).lookupClass().asSubclass(cls);
            } else {
                Object objInvoke2 = UnsafeProvider.INSTANCE.getUnsafe().getClass().getMethod("defineAnonymousClass", Class.class, byte[].class, Object[].class).invoke(UnsafeProvider.INSTANCE.getUnsafe(), UnsafeUtils.class, cw.toByteArray(), null);
                Intrinsics.checkNotNull(objInvoke2, "null cannot be cast to non-null type java.lang.Class<out T of cn.sast.api.util.UnsafeUtils.defineAnonymousConcreteSubclass>");
                clsAsSubclass = (Class) objInvoke2;
            }
            return clsAsSubclass;
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (NoSuchMethodException e2) {
            throw new IllegalStateException(e2);
        } catch (InvocationTargetException e3) {
            throw new IllegalStateException(e3);
        }
    }

    private static final boolean defineAnonymousConcreteSubclass$lambda$1(Method method) {
        Intrinsics.checkNotNullParameter(method, "method");
        return Intrinsics.areEqual(method.getName(), "defineHiddenClass");
    }

    private static final boolean defineAnonymousConcreteSubclass$lambda$2(Class clazz) {
        Intrinsics.checkNotNullParameter(clazz, "clazz");
        return Intrinsics.areEqual(clazz.getSimpleName(), "ClassOption");
    }
}
