package cn.sast.framework.report.metadata;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.List;
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
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;

/* compiled from: AnalysisMetadata.kt */
@Deprecated(message = "This synthesized declaration should not be used directly", level = DeprecationLevel.HIDDEN)
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"cn/sast/framework/report/metadata/AnalysisMetadata.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcn/sast/framework/report/metadata/AnalysisMetadata;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "corax-framework"})
/* loaded from: AnalysisMetadata$$serializer.class */
public /* synthetic */ class AnalysisMetadata$$serializer implements GeneratedSerializer<AnalysisMetadata> {

    @NotNull
    public static final AnalysisMetadata$$serializer INSTANCE = new AnalysisMetadata$$serializer();

    @NotNull
    private static final SerialDescriptor descriptor;

    private AnalysisMetadata$$serializer() {
    }

    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(@NotNull Encoder encoder, @NotNull AnalysisMetadata value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        AnalysisMetadata.write$Self$corax_framework(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlinx.serialization.UnknownFieldException */
    @NotNull
    /* renamed from: deserialize, reason: merged with bridge method [inline-methods] */
    public final AnalysisMetadata m357deserialize(@NotNull Decoder decoder) throws UnknownFieldException {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        boolean z = true;
        int i = 0;
        int iDecodeIntElement = 0;
        int iDecodeIntElement2 = 0;
        Counter counter = null;
        int iDecodeIntElement3 = 0;
        List list = null;
        String strDecodeStringElement = null;
        List list2 = null;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        DeserializationStrategy[] deserializationStrategyArr = AnalysisMetadata.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
            iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 1);
            counter = (Counter) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, Counter$$serializer.INSTANCE, (Object) null);
            iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
            list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 4, deserializationStrategyArr[4], (Object) null);
            strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 5);
            list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, deserializationStrategyArr[6], (Object) null);
            i = 0 | 1 | 2 | 4 | 8 | 16 | 32 | 64;
        } else {
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case PointsToGraphKt.pathStrictMod /* 0 */:
                        iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 0);
                        i |= 1;
                        break;
                    case PseudoTopologicalOrderer.REVERSE /* 1 */:
                        iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 1);
                        i |= 2;
                        break;
                    case 2:
                        counter = (Counter) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 2, Counter$$serializer.INSTANCE, counter);
                        i |= 4;
                        break;
                    case 3:
                        iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
                        i |= 8;
                        break;
                    case 4:
                        list = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 4, deserializationStrategyArr[4], list);
                        i |= 16;
                        break;
                    case 5:
                        strDecodeStringElement = compositeDecoderBeginStructure.decodeStringElement(serialDescriptor, 5);
                        i |= 32;
                        break;
                    case 6:
                        list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 6, deserializationStrategyArr[6], list2);
                        i |= 64;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new AnalysisMetadata(i, iDecodeIntElement, iDecodeIntElement2, counter, iDecodeIntElement3, list, strDecodeStringElement, list2, null);
    }

    @NotNull
    public final KSerializer<?>[] childSerializers() {
        KSerializer<?>[] kSerializerArr = AnalysisMetadata.$childSerializers;
        return new KSerializer[]{IntSerializer.INSTANCE, IntSerializer.INSTANCE, Counter$$serializer.INSTANCE, IntSerializer.INSTANCE, kSerializerArr[4], StringSerializer.INSTANCE, kSerializerArr[6]};
    }

    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }

    static {
        SerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("cn.sast.framework.report.metadata.AnalysisMetadata", INSTANCE, 7);
        pluginGeneratedSerialDescriptor.addElement("file_count", false);
        pluginGeneratedSerialDescriptor.addElement("line_count", false);
        pluginGeneratedSerialDescriptor.addElement("code_coverage", false);
        pluginGeneratedSerialDescriptor.addElement("num_of_report_dir", false);
        pluginGeneratedSerialDescriptor.addElement("source_paths", false);
        pluginGeneratedSerialDescriptor.addElement("os_name", false);
        pluginGeneratedSerialDescriptor.addElement("tools", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }
}
