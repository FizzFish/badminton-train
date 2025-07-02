package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import org.jetbrains.annotations.NotNull;

/* compiled from: MainConfig.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\bÆ\u0002\u0018��2\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0016J\u0014\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lcn/sast/api/config/ClassSerializer;", "Lkotlinx/serialization/KSerializer;", "Ljava/lang/Class;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "corax-api"})
/* loaded from: ClassSerializer.class */
public final class ClassSerializer implements KSerializer<Class<?>> {

    @NotNull
    public static final ClassSerializer INSTANCE = new ClassSerializer();

    @NotNull
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("ClassSerializer", PrimitiveKind.STRING.INSTANCE);

    private ClassSerializer() {
    }

    @NotNull
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public void serialize(@NotNull Encoder encoder, @NotNull Class<?> cls) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(cls, "value");
        String name = cls.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        encoder.encodeString(name);
    }

    @NotNull
    /* renamed from: deserialize, reason: merged with bridge method [inline-methods] */
    public Class<?> m11deserialize(@NotNull Decoder decoder) throws ClassNotFoundException {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        String className = decoder.decodeString();
        Class<?> cls = Class.forName(className);
        Intrinsics.checkNotNullExpressionValue(cls, "forName(...)");
        return cls;
    }
}
