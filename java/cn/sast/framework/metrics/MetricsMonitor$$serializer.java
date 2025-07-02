package cn.sast.framework.metrics;

import cn.sast.api.report.ProjectMetrics;
import cn.sast.api.report.ProjectMetrics$$serializer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* compiled from: MetricsMonitor.kt */
@Deprecated(message = "This synthesized declaration should not be used directly", level = DeprecationLevel.HIDDEN)
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"cn/sast/framework/metrics/MetricsMonitor.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcn/sast/framework/metrics/MetricsMonitor;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "corax-framework"})
/* loaded from: MetricsMonitor$$serializer.class */
public /* synthetic */ class MetricsMonitor$$serializer implements GeneratedSerializer<MetricsMonitor> {

    @NotNull
    public static final MetricsMonitor$$serializer INSTANCE = new MetricsMonitor$$serializer();

    @NotNull
    private static final SerialDescriptor descriptor;

    private MetricsMonitor$$serializer() {
    }

    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(@NotNull Encoder encoder, @NotNull MetricsMonitor value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        MetricsMonitor.write$Self$corax_framework(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlinx.serialization.UnknownFieldException */
    @NotNull
    /* renamed from: deserialize, reason: merged with bridge method [inline-methods] */
    public final MetricsMonitor m300deserialize(@NotNull Decoder decoder) throws UnknownFieldException {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        boolean z = true;
        int i = 0;
        String strDecodeStringElement = null;
        long jDecodeLongElement = 0;
        double dDecodeDoubleElement = 0.0d;
        String strDecodeStringElement2 = null;
        String strDecodeStringElement3 = null;
        long jDecodeLongElement2 = 0;
        double dDecodeDoubleElement2 = 0.0d;
        double dDecodeDoubleElement3 = 0.0d;
        ProjectMetrics projectMetrics = null;
        List list = null;
        Map map = null;
        List list2 = null;
        List list3 = null;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        DeserializationStrategy[] deserializationStrategyArr = MetricsMonitor.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
            jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 1);
            dDecodeDoubleElement = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 2);
            strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
            strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 4);
            jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 5);
            dDecodeDoubleElement2 = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 6);
            dDecodeDoubleElement3 = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 7);
            projectMetrics = (ProjectMetrics) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, ProjectMetrics$$serializer.INSTANCE, (Object) null);
            list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 9, deserializationStrategyArr[9], (Object) null);
            map = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 10, deserializationStrategyArr[10], (Object) null);
            list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 11, deserializationStrategyArr[11], (Object) null);
            list3 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 12, deserializationStrategyArr[12], (Object) null);
            i = 0 | 1 | 2 | 4 | 8 | 16 | 32 | 64 | 128 | 256 | 512 | 1024 | 2048 | 4096;
        } else {
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case PointsToGraphKt.pathStrictMod /* 0 */:
                        strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 0);
                        i |= 1;
                        break;
                    case PseudoTopologicalOrderer.REVERSE /* 1 */:
                        jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 1);
                        i |= 2;
                        break;
                    case 2:
                        dDecodeDoubleElement = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 2);
                        i |= 4;
                        break;
                    case 3:
                        strDecodeStringElement2 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 3);
                        i |= 8;
                        break;
                    case 4:
                        strDecodeStringElement3 = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 4);
                        i |= 16;
                        break;
                    case 5:
                        jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 5);
                        i |= 32;
                        break;
                    case 6:
                        dDecodeDoubleElement2 = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 6);
                        i |= 64;
                        break;
                    case 7:
                        dDecodeDoubleElement3 = compositeDecoderBeginStructure.decodeDoubleElement(serialDescriptor, 7);
                        i |= 128;
                        break;
                    case 8:
                        projectMetrics = (ProjectMetrics) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 8, ProjectMetrics$$serializer.INSTANCE, projectMetrics);
                        i |= 256;
                        break;
                    case 9:
                        list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 9, deserializationStrategyArr[9], list);
                        i |= 512;
                        break;
                    case 10:
                        map = (Map) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 10, deserializationStrategyArr[10], map);
                        i |= 1024;
                        break;
                    case 11:
                        list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 11, deserializationStrategyArr[11], list2);
                        i |= 2048;
                        break;
                    case 12:
                        list3 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 12, deserializationStrategyArr[12], list3);
                        i |= 4096;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new MetricsMonitor(i, strDecodeStringElement, jDecodeLongElement, dDecodeDoubleElement, strDecodeStringElement2, strDecodeStringElement3, jDecodeLongElement2, dDecodeDoubleElement2, dDecodeDoubleElement3, projectMetrics, list, map, list2, list3, null);
    }

    @NotNull
    public final KSerializer<?>[] childSerializers() {
        KSerializer<?>[] kSerializerArr = MetricsMonitor.$childSerializers;
        return new KSerializer[]{StringSerializer.INSTANCE, LongSerializer.INSTANCE, DoubleSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, LongSerializer.INSTANCE, DoubleSerializer.INSTANCE, DoubleSerializer.INSTANCE, ProjectMetrics$$serializer.INSTANCE, kSerializerArr[9], kSerializerArr[10], kSerializerArr[11], kSerializerArr[12]};
    }

    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }

    static {
        SerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("cn.sast.framework.metrics.MetricsMonitor", INSTANCE, 13);
        pluginGeneratedSerialDescriptor.addElement("beginDate", true);
        pluginGeneratedSerialDescriptor.addElement("beginMillis", true);
        pluginGeneratedSerialDescriptor.addElement("elapsedSeconds", true);
        pluginGeneratedSerialDescriptor.addElement("elapsedTime", true);
        pluginGeneratedSerialDescriptor.addElement("endDate", true);
        pluginGeneratedSerialDescriptor.addElement("endTime", true);
        pluginGeneratedSerialDescriptor.addElement("jvmMemoryUsedMax", true);
        pluginGeneratedSerialDescriptor.addElement("jvmMemoryMax", true);
        pluginGeneratedSerialDescriptor.addElement("projectMetrics", true);
        pluginGeneratedSerialDescriptor.addElement("phaseTimer", true);
        pluginGeneratedSerialDescriptor.addElement("final", true);
        pluginGeneratedSerialDescriptor.addElement("reports", true);
        pluginGeneratedSerialDescriptor.addElement("snapshot", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }
}
