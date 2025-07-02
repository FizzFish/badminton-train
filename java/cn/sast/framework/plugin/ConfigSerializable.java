package cn.sast.framework.plugin;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.lang.annotation.Annotation;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SealedClassSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: SAConfiguration.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b7\u0018�� \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020��0\u0002:\u0001\u0017B\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004B\u001b\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0003\u0010\tJ\u0011\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020��H\u0096\u0002J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020��2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007R\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r\u0082\u0001\u0003\u0018\u0019\u001a¨\u0006\u001b"}, d2 = {"Lcn/sast/framework/plugin/ConfigSerializable;", "Lcn/sast/framework/plugin/IConfig;", "", "<init>", "()V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILkotlinx/serialization/internal/SerializationConstructorMarker;)V", "name", "", "getName", "()Ljava/lang/String;", "compareTo", "other", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "Companion", "Lcn/sast/framework/plugin/CheckerUnitOptionalConfig;", "Lcn/sast/framework/plugin/CheckersConfig;", "Lcn/sast/framework/plugin/SootOptionsConfig;", "corax-framework"})
/* loaded from: ConfigSerializable.class */
public abstract class ConfigSerializable implements IConfig, Comparable<ConfigSerializable> {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, () -> {
        return new SealedClassSerializer("cn.sast.framework.plugin.ConfigSerializable", Reflection.getOrCreateKotlinClass(ConfigSerializable.class), new KClass[]{Reflection.getOrCreateKotlinClass(CheckerUnitOptionalConfig.class), Reflection.getOrCreateKotlinClass(CheckersConfig.class), Reflection.getOrCreateKotlinClass(SootOptionsConfig.class)}, new KSerializer[]{CheckerUnitOptionalConfig$$serializer.INSTANCE, CheckersConfig$$serializer.INSTANCE, SootOptionsConfig$$serializer.INSTANCE}, new Annotation[0]);
    });

    @NotNull
    public abstract String getName();

    @JvmStatic
    public static final /* synthetic */ void write$Self(ConfigSerializable self, CompositeEncoder output, SerialDescriptor serialDesc) {
    }

    public /* synthetic */ ConfigSerializable(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    /* compiled from: SAConfiguration.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/plugin/ConfigSerializable$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/plugin/ConfigSerializable;", "corax-framework"})
    /* loaded from: ConfigSerializable$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<ConfigSerializable> serializer() {
            return get$cachedSerializer();
        }

        private final /* synthetic */ KSerializer get$cachedSerializer() {
            return (KSerializer) ConfigSerializable.$cachedSerializer$delegate.getValue();
        }
    }

    private ConfigSerializable() {
    }

    public /* synthetic */ ConfigSerializable(int seen0, SerializationConstructorMarker serializationConstructorMarker) {
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull ConfigSerializable other) {
        Intrinsics.checkNotNullParameter(other, "other");
        String name = other.getClass().getName();
        String name2 = getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        int compare = name.compareTo(name2);
        if (compare == 0) {
            return getName().compareTo(other.getName());
        }
        return 0;
    }
}
