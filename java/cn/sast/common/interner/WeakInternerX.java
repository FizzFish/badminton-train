package cn.sast.common.interner;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.commons.Kotlin_extKt;
import com.google.common.base.Equivalence;
import com.google.common.base.InternerEquals;
import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

/* compiled from: WeakInternerX.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0007\u001a\u0002H\b\"\b\b��\u0010\b*\u00020\u00062\u0006\u0010\t\u001a\u0002H\b¢\u0006\u0002\u0010\nR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u000b²\u0006\n\u0010\f\u001a\u00020\u0001X\u008a\u0084\u0002²\u0006\u0010\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u000eX\u008a\u008e\u0002"}, d2 = {"Lcn/sast/common/interner/WeakInternerX;", "", "<init>", "()V", "interner", "Lcom/google/common/collect/Interner;", "Lcn/sast/common/interner/InternerEquiv;", "intern", "E", "value", "(Lcn/sast/common/interner/InternerEquiv;)Lcn/sast/common/interner/InternerEquiv;", "corax-api", "map", "keyEquivalence", "Lcom/google/common/base/Equivalence;"})
/* loaded from: WeakInternerX.class */
public final class WeakInternerX {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property0(new PropertyReference0Impl(WeakInternerX.class, "map", "<v#0>", 0)), Reflection.mutableProperty0(new MutablePropertyReference0Impl(WeakInternerX.class, "keyEquivalence", "<v#1>", 0))};

    @NotNull
    private final Interner<InternerEquiv> interner;

    public WeakInternerX() {
        Interner<InternerEquiv> internerNewWeakInterner = Interners.newWeakInterner();
        Intrinsics.checkNotNullExpressionValue(internerNewWeakInterner, "newWeakInterner(...)");
        this.interner = internerNewWeakInterner;
        ReadWriteProperty map$delegate = (ReadWriteProperty) Kotlin_extKt.delegateField$default(this.interner, (Class) null, 1, (Object) null).provideDelegate((Object) null, $$delegatedProperties[0]);
        ReadWriteProperty keyEquivalence$delegate = (ReadWriteProperty) Kotlin_extKt.delegateField$default(_init_$lambda$0(map$delegate), (Class) null, 1, (Object) null).provideDelegate((Object) null, $$delegatedProperties[1]);
        _init_$lambda$2(keyEquivalence$delegate, new InternerEquals());
    }

    private static final Object _init_$lambda$0(ReadWriteProperty<Object, Object> readWriteProperty) {
        return readWriteProperty.getValue((Object) null, $$delegatedProperties[0]);
    }

    private static final Equivalence<InternerEquiv> _init_$lambda$1(ReadWriteProperty<Object, Equivalence<InternerEquiv>> readWriteProperty) {
        return (Equivalence) readWriteProperty.getValue((Object) null, $$delegatedProperties[1]);
    }

    private static final void _init_$lambda$2(ReadWriteProperty<Object, Equivalence<InternerEquiv>> readWriteProperty, Equivalence<InternerEquiv> equivalence) {
        readWriteProperty.setValue((Object) null, $$delegatedProperties[1], equivalence);
    }

    @NotNull
    public final <E extends InternerEquiv> E intern(@NotNull E e) {
        Intrinsics.checkNotNullParameter(e, "value");
        E e2 = (E) this.interner.intern(e);
        Intrinsics.checkNotNull(e2, "null cannot be cast to non-null type E of cn.sast.common.interner.WeakInternerX.intern");
        return e2;
    }
}
