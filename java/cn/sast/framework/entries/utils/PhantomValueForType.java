package cn.sast.framework.entries.utils;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.Body;
import soot.Local;
import soot.LocalGenerator;
import soot.PrimType;
import soot.RefType;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.jimple.Jimple;
import utils.BaseBodyGenerator;
import utils.INewUnits;
import utils.NewUnitsAtLastImmediately;

/* compiled from: PhantomValueForType.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��R\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u0004\u0018\u00010\u00032\u0006\u0010\r\u001a\u00020\u000eJ \u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u0013J \u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eJ \u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001a\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000eJ \u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcn/sast/framework/entries/utils/PhantomValueForType;", "", "dummyClassName", "", "<init>", "(Ljava/lang/String;)V", "logger", "Lmu/KLogger;", "summaryClass", "Lsoot/SootClass;", "getSummaryClass", "()Lsoot/SootClass;", "getName", "tp", "Lsoot/Type;", "getOrMakeSootMethodForType", "Lsoot/SootMethod;", "name", "number", "", "getValueForType", "Lsoot/Local;", "units", "Lutils/INewUnits;", "generator", "Lsoot/LocalGenerator;", "body", "Lsoot/Body;", "Lutils/BaseBodyGenerator;", "newUnits", "corax-framework"})
/* loaded from: PhantomValueForType.class */
public final class PhantomValueForType {

    @NotNull
    private final String dummyClassName;

    @NotNull
    private final KLogger logger;

    public PhantomValueForType() {
        this(null, 1, null);
    }

    public PhantomValueForType(@NotNull String dummyClassName) {
        Intrinsics.checkNotNullParameter(dummyClassName, "dummyClassName");
        this.dummyClassName = dummyClassName;
        this.logger = KotlinLogging.INSTANCE.logger(PhantomValueForType::logger$lambda$0);
    }

    public /* synthetic */ PhantomValueForType(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "dummyMainClass" : str);
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }

    private final SootClass getSummaryClass() {
        SootClass sootClassUnsafe = Scene.v().getSootClassUnsafe(this.dummyClassName, false);
        if (sootClassUnsafe != null) {
            return sootClassUnsafe;
        }
        SootClass it = Scene.v().makeSootClass(this.dummyClassName);
        it.setResolvingLevel(3);
        it.setApplicationClass();
        Intrinsics.checkNotNullExpressionValue(it, "also(...)");
        return it;
    }

    @Nullable
    public final String getName(@NotNull Type tp) {
        Intrinsics.checkNotNullParameter(tp, "tp");
        if (tp instanceof PrimType) {
            return tp.toString();
        }
        if (tp instanceof RefType) {
            String className = ((RefType) tp).getClassName();
            Intrinsics.checkNotNullExpressionValue(className, "getClassName(...)");
            return StringsKt.substringAfterLast$default(className, ".", (String) null, 2, (Object) null);
        }
        if (tp instanceof ArrayType) {
            Type elementType = ((ArrayType) tp).getElementType();
            Intrinsics.checkNotNullExpressionValue(elementType, "getElementType(...)");
            return getName(elementType) + "Array";
        }
        this.logger.warn("Unsupported parameter type: {}", tp.toString());
        return null;
    }

    public static /* synthetic */ SootMethod getOrMakeSootMethodForType$default(PhantomValueForType phantomValueForType, Type type, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 1;
        }
        return phantomValueForType.getOrMakeSootMethodForType(type, str, i);
    }

    @NotNull
    public final SootMethod getOrMakeSootMethodForType(@NotNull Type tp, @NotNull String name, int number) {
        Intrinsics.checkNotNullParameter(tp, "tp");
        Intrinsics.checkNotNullParameter(name, "name");
        SootMethod methodByNameUnsafe = getSummaryClass().getMethodByNameUnsafe(name);
        if (methodByNameUnsafe == null) {
            SootMethod it = Scene.v().makeSootMethod(name, CollectionsKt.emptyList(), tp, 8);
            getSummaryClass().addMethod(it);
            it.setPhantom(true);
            methodByNameUnsafe = it;
        }
        SootMethod getter = methodByNameUnsafe;
        if (Intrinsics.areEqual(getter.getReturnType(), tp)) {
            Intrinsics.checkNotNull(getter);
            return getter;
        }
        return getOrMakeSootMethodForType(tp, name + number, number + 1);
    }

    @Nullable
    public final Local getValueForType(@NotNull INewUnits units, @NotNull LocalGenerator generator, @NotNull Type tp) {
        Intrinsics.checkNotNullParameter(units, "units");
        Intrinsics.checkNotNullParameter(generator, "generator");
        Intrinsics.checkNotNullParameter(tp, "tp");
        String name = getName(tp);
        if (name == null) {
            return null;
        }
        SootMethod getter = getOrMakeSootMethodForType$default(this, tp, "get" + name, 0, 4, null);
        Value valueGenerateLocal = generator.generateLocal(tp);
        units.add(Jimple.v().newAssignStmt(valueGenerateLocal, Jimple.v().newStaticInvokeExpr(getter.makeRef())));
        return valueGenerateLocal;
    }

    @Nullable
    public final Local getValueForType(@NotNull Body body, @NotNull LocalGenerator generator, @NotNull Type tp) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(generator, "generator");
        Intrinsics.checkNotNullParameter(tp, "tp");
        return getValueForType((INewUnits) new NewUnitsAtLastImmediately(body.getUnits()), generator, tp);
    }

    @Nullable
    public final Local getValueForType(@NotNull BaseBodyGenerator body, @NotNull Type tp) {
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(tp, "tp");
        INewUnits iNewUnits = (INewUnits) new NewUnitsAtLastImmediately(body.getUnits());
        LocalGenerator generator = body.getGenerator();
        Intrinsics.checkNotNullExpressionValue(generator, "getGenerator(...)");
        return getValueForType(iNewUnits, generator, tp);
    }

    @Nullable
    public final Local getValueForType(@NotNull INewUnits newUnits, @NotNull BaseBodyGenerator body, @NotNull Type tp) {
        Intrinsics.checkNotNullParameter(newUnits, "newUnits");
        Intrinsics.checkNotNullParameter(body, "body");
        Intrinsics.checkNotNullParameter(tp, "tp");
        LocalGenerator generator = body.getGenerator();
        Intrinsics.checkNotNullExpressionValue(generator, "getGenerator(...)");
        return getValueForType(newUnits, generator, tp);
    }
}
