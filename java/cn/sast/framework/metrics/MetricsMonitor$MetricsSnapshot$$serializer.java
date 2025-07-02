package cn.sast.framework.metrics;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.metrics.MetricsMonitor;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import org.jetbrains.annotations.NotNull;

/* compiled from: MetricsMonitor.kt */
@Deprecated(message = "This synthesized declaration should not be used directly", level = DeprecationLevel.HIDDEN)
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"cn/sast/framework/metrics/MetricsMonitor.MetricsSnapshot.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcn/sast/framework/metrics/MetricsMonitor$MetricsSnapshot;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "corax-framework"})
/* loaded from: MetricsMonitor$MetricsSnapshot$$serializer.class */
public /* synthetic */ class MetricsMonitor$MetricsSnapshot$$serializer implements GeneratedSerializer<MetricsMonitor.MetricsSnapshot> {

    @NotNull
    public static final MetricsMonitor$MetricsSnapshot$$serializer INSTANCE = new MetricsMonitor$MetricsSnapshot$$serializer();

    @NotNull
    private static final SerialDescriptor descriptor;

    private MetricsMonitor$MetricsSnapshot$$serializer() {
    }

    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(@NotNull Encoder encoder, @NotNull MetricsMonitor.MetricsSnapshot value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        MetricsMonitor.MetricsSnapshot.write$Self$corax_framework(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlinx.serialization.UnknownFieldException */
    @NotNull
    /* renamed from: deserialize, reason: merged with bridge method [inline-methods] */
    public final MetricsMonitor.MetricsSnapshot m302deserialize(@NotNull Decoder decoder) throws UnknownFieldException {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        boolean z = true;
        int i = 0;
        double dDecodeDoubleElement = 0.0d;
        Double d = null;
        Double d2 = null;
        Double d3 = null;
        Double d4 = null;
        Double d5 = null;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            dDecodeDoubleElement = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 0);
            d = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, DoubleSerializer.INSTANCE, (Object) null);
            d2 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, DoubleSerializer.INSTANCE, (Object) null);
            d3 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, DoubleSerializer.INSTANCE, (Object) null);
            d4 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, DoubleSerializer.INSTANCE, (Object) null);
            d5 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, DoubleSerializer.INSTANCE, (Object) null);
            i = 0 | 1 | 2 | 4 | 8 | 16 | 32;
        } else {
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case PointsToGraphKt.pathStrictMod /* 0 */:
                        dDecodeDoubleElement = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 0);
                        i |= 1;
                        break;
                    case PseudoTopologicalOrderer.REVERSE /* 1 */:
                        d = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 1, DoubleSerializer.INSTANCE, d);
                        i |= 2;
                        break;
                    case 2:
                        d2 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 2, DoubleSerializer.INSTANCE, d2);
                        i |= 4;
                        break;
                    case 3:
                        d3 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 3, DoubleSerializer.INSTANCE, d3);
                        i |= 8;
                        break;
                    case 4:
                        d4 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 4, DoubleSerializer.INSTANCE, d4);
                        i |= 16;
                        break;
                    case 5:
                        d5 = (Double) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 5, DoubleSerializer.INSTANCE, d5);
                        i |= 32;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new MetricsMonitor.MetricsSnapshot(i, dDecodeDoubleElement, d, d2, d3, d4, d5, null);
    }

    @NotNull
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{DoubleSerializer.INSTANCE, BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE), BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE), BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE), BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE), BuiltinSerializersKt.getNullable(DoubleSerializer.INSTANCE)};
    }

    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }

    static {
        SerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("cn.sast.framework.metrics.MetricsMonitor.MetricsSnapshot", INSTANCE, 6);
        pluginGeneratedSerialDescriptor.addElement("timeInSecond", false);
        pluginGeneratedSerialDescriptor.addElement("jvmMemoryUsed", false);
        pluginGeneratedSerialDescriptor.addElement("jvmMemoryUsedMax", false);
        pluginGeneratedSerialDescriptor.addElement("jvmMemoryCommitted", false);
        pluginGeneratedSerialDescriptor.addElement("freePhysicalSize", false);
        pluginGeneratedSerialDescriptor.addElement("cpuSystemCpuLoad", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }
}
