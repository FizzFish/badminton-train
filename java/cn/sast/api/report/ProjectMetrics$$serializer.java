package cn.sast.api.report;

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
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;

/* compiled from: ProjectMetrics.kt */
@Deprecated(message = "This synthesized declaration should not be used directly", level = DeprecationLevel.HIDDEN)
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"cn/sast/api/report/ProjectMetrics.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcn/sast/api/report/ProjectMetrics;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "corax-api"})
/* loaded from: ProjectMetrics$$serializer.class */
public /* synthetic */ class ProjectMetrics$$serializer implements GeneratedSerializer<ProjectMetrics> {

    @NotNull
    public static final ProjectMetrics$$serializer INSTANCE = new ProjectMetrics$$serializer();

    @NotNull
    private static final SerialDescriptor descriptor;

    private ProjectMetrics$$serializer() {
    }

    @NotNull
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    public final void serialize(@NotNull Encoder encoder, @NotNull ProjectMetrics value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder compositeEncoderBeginStructure = encoder.beginStructure(serialDescriptor);
        ProjectMetrics.write$Self$corax_api(value, compositeEncoderBeginStructure, serialDescriptor);
        compositeEncoderBeginStructure.endStructure(serialDescriptor);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlinx.serialization.UnknownFieldException */
    @NotNull
    /* renamed from: deserialize, reason: merged with bridge method [inline-methods] */
    public final ProjectMetrics m44deserialize(@NotNull Decoder decoder) throws UnknownFieldException {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        boolean z = true;
        int i = 0;
        List list = null;
        List list2 = null;
        int iDecodeIntElement = 0;
        int iDecodeIntElement2 = 0;
        int iDecodeIntElement3 = 0;
        int iDecodeIntElement4 = 0;
        int iDecodeIntElement5 = 0;
        int iDecodeIntElement6 = 0;
        float fDecodeFloatElement = 0.0f;
        int iDecodeIntElement7 = 0;
        float fDecodeFloatElement2 = 0.0f;
        int iDecodeIntElement8 = 0;
        int iDecodeIntElement9 = 0;
        int iDecodeIntElement10 = 0;
        long jDecodeLongElement = 0;
        long jDecodeLongElement2 = 0;
        long jDecodeLongElement3 = 0;
        int iDecodeIntElement11 = 0;
        int iDecodeIntElement12 = 0;
        int iDecodeIntElement13 = 0;
        float fDecodeFloatElement3 = 0.0f;
        int iDecodeIntElement14 = 0;
        float fDecodeFloatElement4 = 0.0f;
        int iDecodeIntElement15 = 0;
        CompositeDecoder compositeDecoderBeginStructure = decoder.beginStructure(serialDescriptor);
        DeserializationStrategy[] deserializationStrategyArr = ProjectMetrics.$childSerializers;
        if (compositeDecoderBeginStructure.decodeSequentially()) {
            list = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, deserializationStrategyArr[0], (Object) null);
            list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, deserializationStrategyArr[1], (Object) null);
            iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 2);
            iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
            iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 4);
            iDecodeIntElement4 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 5);
            iDecodeIntElement5 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 6);
            iDecodeIntElement6 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 7);
            fDecodeFloatElement = compositeDecoderBeginStructure.decodeFloatElement(serialDescriptor, 8);
            iDecodeIntElement7 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 9);
            fDecodeFloatElement2 = compositeDecoderBeginStructure.decodeFloatElement(serialDescriptor, 10);
            iDecodeIntElement8 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 11);
            iDecodeIntElement9 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 12);
            iDecodeIntElement10 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 13);
            jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 14);
            jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 15);
            jDecodeLongElement3 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 16);
            iDecodeIntElement11 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 17);
            iDecodeIntElement12 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 18);
            iDecodeIntElement13 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 19);
            fDecodeFloatElement3 = compositeDecoderBeginStructure.decodeFloatElement(serialDescriptor, 20);
            iDecodeIntElement14 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 21);
            fDecodeFloatElement4 = compositeDecoderBeginStructure.decodeFloatElement(serialDescriptor, 22);
            iDecodeIntElement15 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 23);
            i = 0 | 1 | 2 | 4 | 8 | 16 | 32 | 64 | 128 | 256 | 512 | 1024 | 2048 | 4096 | 8192 | 16384 | 32768 | 65536 | 131072 | 262144 | 524288 | 1048576 | 2097152 | 4194304 | 8388608;
        } else {
            while (z) {
                int iDecodeElementIndex = compositeDecoderBeginStructure.decodeElementIndex(serialDescriptor);
                switch (iDecodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case PointsToGraphKt.pathStrictMod /* 0 */:
                        list = (List) compositeDecoderBeginStructure.decodeNullableSerializableElement(serialDescriptor, 0, deserializationStrategyArr[0], list);
                        i |= 1;
                        break;
                    case PseudoTopologicalOrderer.REVERSE /* 1 */:
                        list2 = (List) compositeDecoderBeginStructure.decodeSerializableElement(serialDescriptor, 1, deserializationStrategyArr[1], list2);
                        i |= 2;
                        break;
                    case 2:
                        iDecodeIntElement = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 2);
                        i |= 4;
                        break;
                    case 3:
                        iDecodeIntElement2 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 3);
                        i |= 8;
                        break;
                    case 4:
                        iDecodeIntElement3 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 4);
                        i |= 16;
                        break;
                    case 5:
                        iDecodeIntElement4 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 5);
                        i |= 32;
                        break;
                    case 6:
                        iDecodeIntElement5 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 6);
                        i |= 64;
                        break;
                    case 7:
                        iDecodeIntElement6 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 7);
                        i |= 128;
                        break;
                    case 8:
                        fDecodeFloatElement = compositeDecoderBeginStructure.decodeFloatElement(serialDescriptor, 8);
                        i |= 256;
                        break;
                    case 9:
                        iDecodeIntElement7 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 9);
                        i |= 512;
                        break;
                    case 10:
                        fDecodeFloatElement2 = compositeDecoderBeginStructure.decodeFloatElement(serialDescriptor, 10);
                        i |= 1024;
                        break;
                    case 11:
                        iDecodeIntElement8 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 11);
                        i |= 2048;
                        break;
                    case 12:
                        iDecodeIntElement9 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 12);
                        i |= 4096;
                        break;
                    case 13:
                        iDecodeIntElement10 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 13);
                        i |= 8192;
                        break;
                    case 14:
                        jDecodeLongElement = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 14);
                        i |= 16384;
                        break;
                    case 15:
                        jDecodeLongElement2 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 15);
                        i |= 32768;
                        break;
                    case 16:
                        jDecodeLongElement3 = compositeDecoderBeginStructure.decodeLongElement(serialDescriptor, 16);
                        i |= 65536;
                        break;
                    case 17:
                        iDecodeIntElement11 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 17);
                        i |= 131072;
                        break;
                    case 18:
                        iDecodeIntElement12 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 18);
                        i |= 262144;
                        break;
                    case 19:
                        iDecodeIntElement13 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 19);
                        i |= 524288;
                        break;
                    case 20:
                        fDecodeFloatElement3 = compositeDecoderBeginStructure.decodeFloatElement(serialDescriptor, 20);
                        i |= 1048576;
                        break;
                    case 21:
                        iDecodeIntElement14 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 21);
                        i |= 2097152;
                        break;
                    case 22:
                        fDecodeFloatElement4 = compositeDecoderBeginStructure.decodeFloatElement(serialDescriptor, 22);
                        i |= 4194304;
                        break;
                    case 23:
                        iDecodeIntElement15 = compositeDecoderBeginStructure.decodeIntElement(serialDescriptor, 23);
                        i |= 8388608;
                        break;
                    default:
                        throw new UnknownFieldException(iDecodeElementIndex);
                }
            }
        }
        compositeDecoderBeginStructure.endStructure(serialDescriptor);
        return new ProjectMetrics(i, list, list2, iDecodeIntElement, iDecodeIntElement2, iDecodeIntElement3, iDecodeIntElement4, iDecodeIntElement5, iDecodeIntElement6, fDecodeFloatElement, iDecodeIntElement7, fDecodeFloatElement2, iDecodeIntElement8, iDecodeIntElement9, iDecodeIntElement10, jDecodeLongElement, jDecodeLongElement2, jDecodeLongElement3, iDecodeIntElement11, iDecodeIntElement12, iDecodeIntElement13, fDecodeFloatElement3, iDecodeIntElement14, fDecodeFloatElement4, iDecodeIntElement15, (SerializationConstructorMarker) null);
    }

    @NotNull
    public final KSerializer<?>[] childSerializers() {
        KSerializer<?>[] kSerializerArr = ProjectMetrics.$childSerializers;
        return new KSerializer[]{BuiltinSerializersKt.getNullable(kSerializerArr[0]), kSerializerArr[1], IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, FloatSerializer.INSTANCE, IntSerializer.INSTANCE, FloatSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, FloatSerializer.INSTANCE, IntSerializer.INSTANCE, FloatSerializer.INSTANCE, IntSerializer.INSTANCE};
    }

    @NotNull
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }

    static {
        SerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("cn.sast.api.report.ProjectMetrics", INSTANCE, 24);
        pluginGeneratedSerialDescriptor.addElement("command", true);
        pluginGeneratedSerialDescriptor.addElement("paths", true);
        pluginGeneratedSerialDescriptor.addElement("applicationClasses", true);
        pluginGeneratedSerialDescriptor.addElement("libraryClasses", true);
        pluginGeneratedSerialDescriptor.addElement("phantomClasses", true);
        pluginGeneratedSerialDescriptor.addElement("applicationMethods", true);
        pluginGeneratedSerialDescriptor.addElement("libraryMethods", true);
        pluginGeneratedSerialDescriptor.addElement("applicationMethodsHaveBody", true);
        pluginGeneratedSerialDescriptor.addElement("applicationMethodsHaveBodyRatio", true);
        pluginGeneratedSerialDescriptor.addElement("libraryMethodsHaveBody", true);
        pluginGeneratedSerialDescriptor.addElement("libraryMethodsHaveBodyRatio", true);
        pluginGeneratedSerialDescriptor.addElement("analyzedFiles", true);
        pluginGeneratedSerialDescriptor.addElement("appJavaFileCount", true);
        pluginGeneratedSerialDescriptor.addElement("appJavaLineCount", true);
        pluginGeneratedSerialDescriptor.addElement("totalFileNum", true);
        pluginGeneratedSerialDescriptor.addElement("totalAnySourceFileNum", true);
        pluginGeneratedSerialDescriptor.addElement("totalSourceFileNum", true);
        pluginGeneratedSerialDescriptor.addElement("analyzedClasses", true);
        pluginGeneratedSerialDescriptor.addElement("analyzedMethodEntries", true);
        pluginGeneratedSerialDescriptor.addElement("analyzedApplicationMethods", true);
        pluginGeneratedSerialDescriptor.addElement("analyzedApplicationMethodsRatio", true);
        pluginGeneratedSerialDescriptor.addElement("analyzedLibraryMethods", true);
        pluginGeneratedSerialDescriptor.addElement("analyzedLibraryMethodsRatio", true);
        pluginGeneratedSerialDescriptor.addElement("serializedReports", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }
}
