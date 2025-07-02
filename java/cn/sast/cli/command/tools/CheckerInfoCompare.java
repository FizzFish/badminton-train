package cn.sast.cli.command.tools;

import cn.sast.api.config.CheckerInfo;
import cn.sast.cli.command.FySastCliKt;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JvmStreamsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;

/* compiled from: CheckerInfoCompare.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"�� \n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t¨\u0006\f"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoCompare;", "", "<init>", "()V", "compareWith", "", "output", "Ljava/nio/file/Path;", "left", "Lcn/sast/common/IResFile;", "right", "Companion", "corax-cli"})
@SourceDebugExtension({"SMAP\nCheckerInfoCompare.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CheckerInfoCompare.kt\ncn/sast/cli/command/tools/CheckerInfoCompare\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,59:1\n1#2:60\n774#3:61\n865#3,2:62\n1557#3:64\n1628#3,3:65\n774#3:68\n865#3,2:69\n774#3:71\n865#3,2:72\n1557#3:74\n1628#3,3:75\n1187#3,2:78\n1261#3,4:80\n1628#3,3:84\n774#3:87\n865#3,2:88\n*S KotlinDebug\n*F\n+ 1 CheckerInfoCompare.kt\ncn/sast/cli/command/tools/CheckerInfoCompare\n*L\n28#1:61\n28#1:62,2\n28#1:64\n28#1:65,3\n29#1:68\n29#1:69,2\n31#1:71\n31#1:72,2\n34#1:74\n34#1:75,3\n44#1:78,2\n44#1:80,4\n47#1:84,3\n48#1:87\n48#1:88,2\n*E\n"})
/* loaded from: CheckerInfoCompare.class */
public final class CheckerInfoCompare {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(CheckerInfoCompare::logger$lambda$17);

    @NotNull
    private static final Json jsonFormat = CheckerInfoGenerator.Companion.getJsonFormat();

    @NotNull
    private static final KSerializer<List<CheckerInfo>> infoSerializer = CheckerInfoGenerator.Companion.getInfoSerializer();

    public final void compareWith(@NotNull Path output, @NotNull IResFile left, @NotNull IResFile right) throws IOException {
        Path out;
        List checkIds;
        Iterable compareChecks;
        Iterable deleted;
        OutputStream outputStream;
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(left, "left");
        Intrinsics.checkNotNullParameter(right, "right");
        OpenOption[] openOptionArr = new OpenOption[0];
        InputStream inputStreamNewInputStream = Files.newInputStream(right.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        OutputStream outputStream2 = inputStreamNewInputStream;
        Throwable th = null;
        try {
            try {
                InputStream inputStream = outputStream2;
                Iterable checkerInfoList = (List) JvmStreamsKt.decodeFromStream(jsonFormat, infoSerializer, inputStream);
                CloseableKt.closeFinally(outputStream2, (Throwable) null);
                OpenOption[] openOptionArr2 = new OpenOption[0];
                InputStream inputStreamNewInputStream2 = Files.newInputStream(left.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr2, openOptionArr2.length));
                Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream2, "newInputStream(...)");
                InputStream inputStream2 = inputStreamNewInputStream2;
                Throwable th2 = null;
                try {
                    try {
                        InputStream inputStream3 = inputStream2;
                        Iterable compare = (List) JvmStreamsKt.decodeFromStream(jsonFormat, infoSerializer, inputStream3);
                        CloseableKt.closeFinally(inputStream2, (Throwable) null);
                        out = output.resolve("compare-" + StringsKt.substringBeforeLast$default(left.getName(), ".", (String) null, 2, (Object) null));
                        Intrinsics.checkNotNull(out);
                        LinkOption[] linkOptionArr = new LinkOption[0];
                        if (!Files.exists(out, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
                            Files.createDirectories(out, new FileAttribute[0]);
                        }
                        logger.info(() -> {
                            return compareWith$lambda$2(r1, r2, r3);
                        });
                        Iterable $this$filter$iv = checkerInfoList;
                        Collection destination$iv$iv = new ArrayList();
                        for (Object element$iv$iv : $this$filter$iv) {
                            CheckerInfo it = (CheckerInfo) element$iv$iv;
                            if (Intrinsics.areEqual(it.getAnalyzer(), "Java(canary)")) {
                                destination$iv$iv.add(element$iv$iv);
                            }
                        }
                        Iterable $this$map$iv = (List) destination$iv$iv;
                        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                        for (Object item$iv$iv : $this$map$iv) {
                            CheckerInfo it2 = (CheckerInfo) item$iv$iv;
                            destination$iv$iv2.add(it2.getChecker_id());
                        }
                        checkIds = (List) destination$iv$iv2;
                        Iterable $this$filter$iv2 = compare;
                        Collection destination$iv$iv3 = new ArrayList();
                        for (Object element$iv$iv2 : $this$filter$iv2) {
                            CheckerInfo it3 = (CheckerInfo) element$iv$iv2;
                            if (Intrinsics.areEqual(it3.getAnalyzer(), "Java(canary)")) {
                                destination$iv$iv3.add(element$iv$iv2);
                            }
                        }
                        compareChecks = (List) destination$iv$iv3;
                        Iterable $this$filter$iv3 = compareChecks;
                        Collection destination$iv$iv4 = new ArrayList();
                        for (Object element$iv$iv3 : $this$filter$iv3) {
                            CheckerInfo it4 = (CheckerInfo) element$iv$iv3;
                            if (!checkIds.contains(it4.getChecker_id())) {
                                destination$iv$iv4.add(element$iv$iv3);
                            }
                        }
                        deleted = (List) destination$iv$iv4;
                        Path pathResolve = out.resolve("deleted-checker-ids.json");
                        Intrinsics.checkNotNullExpressionValue(pathResolve, "resolve(...)");
                        OpenOption[] openOptionArr3 = new OpenOption[0];
                        OutputStream outputStreamNewOutputStream = Files.newOutputStream(pathResolve, (OpenOption[]) Arrays.copyOf(openOptionArr3, openOptionArr3.length));
                        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
                        OutputStream outputStream3 = outputStreamNewOutputStream;
                        Throwable th3 = null;
                        try {
                            try {
                                OutputStream outputStream4 = outputStream3;
                                Iterable $this$map$iv2 = deleted;
                                Collection destination$iv$iv5 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                                for (Object item$iv$iv2 : $this$map$iv2) {
                                    CheckerInfo it5 = (CheckerInfo) item$iv$iv2;
                                    destination$iv$iv5.add(it5.getChecker_id());
                                }
                                List deletedIds = (List) destination$iv$iv5;
                                logger.warn(() -> {
                                    return compareWith$lambda$9$lambda$8(r1);
                                });
                                JvmStreamsKt.encodeToStream(jsonFormat, BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)), deletedIds, outputStream4);
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(outputStream3, (Throwable) null);
                                Path pathResolve2 = out.resolve("deleted.json");
                                Intrinsics.checkNotNullExpressionValue(pathResolve2, "resolve(...)");
                                OpenOption[] openOptionArr4 = new OpenOption[0];
                                OutputStream outputStreamNewOutputStream2 = Files.newOutputStream(pathResolve2, (OpenOption[]) Arrays.copyOf(openOptionArr4, openOptionArr4.length));
                                Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream2, "newOutputStream(...)");
                                outputStream = outputStreamNewOutputStream2;
                            } finally {
                            }
                        } finally {
                            CloseableKt.closeFinally(outputStream3, th3);
                        }
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } finally {
        }
        try {
            OutputStream outputStream5 = outputStream;
            JvmStreamsKt.encodeToStream(jsonFormat, infoSerializer, deleted, outputStream5);
            Unit unit2 = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, (Throwable) null);
            Path pathResolve3 = out.resolve(FySastCliKt.MAPPING_FILE_NAME);
            Intrinsics.checkNotNullExpressionValue(pathResolve3, "resolve(...)");
            OpenOption[] openOptionArr5 = new OpenOption[0];
            OutputStream outputStreamNewOutputStream3 = Files.newOutputStream(pathResolve3, (OpenOption[]) Arrays.copyOf(openOptionArr5, openOptionArr5.length));
            Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream3, "newOutputStream(...)");
            OutputStream outputStream6 = outputStreamNewOutputStream3;
            Throwable th4 = null;
            try {
                try {
                    OutputStream outputStream7 = outputStream6;
                    Json json = jsonFormat;
                    SerializationStrategy serializationStrategyMapSerializer = BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE), BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE));
                    Iterable<CheckerInfo> $this$associate$iv = deleted;
                    int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associate$iv, 10)), 16);
                    Map destination$iv$iv6 = new LinkedHashMap(capacity$iv);
                    for (CheckerInfo it6 : $this$associate$iv) {
                        Pair pair = TuplesKt.to(it6.getChecker_id(), "");
                        destination$iv$iv6.put(pair.getFirst(), pair.getSecond());
                    }
                    JvmStreamsKt.encodeToStream(json, serializationStrategyMapSerializer, destination$iv$iv6, outputStream7);
                    Unit unit3 = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStream6, (Throwable) null);
                    Iterable $this$mapTo$iv = compareChecks;
                    Collection destination$iv = (Set) new LinkedHashSet();
                    for (Object item$iv : $this$mapTo$iv) {
                        CheckerInfo it7 = (CheckerInfo) item$iv;
                        destination$iv.add(it7.getChecker_id());
                    }
                    Set checkerIdAll = (Set) destination$iv;
                    List $this$filter$iv4 = checkIds;
                    Collection destination$iv$iv7 = new ArrayList();
                    for (Object element$iv$iv4 : $this$filter$iv4) {
                        String it8 = (String) element$iv$iv4;
                        if (!checkerIdAll.contains(it8)) {
                            destination$iv$iv7.add(element$iv$iv4);
                        }
                    }
                    List list = (List) destination$iv$iv7;
                    Path pathResolve4 = out.resolve("new.json");
                    Intrinsics.checkNotNullExpressionValue(pathResolve4, "resolve(...)");
                    OpenOption[] openOptionArr6 = new OpenOption[0];
                    OutputStream outputStreamNewOutputStream4 = Files.newOutputStream(pathResolve4, (OpenOption[]) Arrays.copyOf(openOptionArr6, openOptionArr6.length));
                    Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream4, "newOutputStream(...)");
                    outputStream2 = outputStreamNewOutputStream4;
                    Throwable th5 = null;
                    try {
                        try {
                            OutputStream outputStream8 = outputStream2;
                            logger.info(() -> {
                                return compareWith$lambda$16$lambda$15(r1);
                            });
                            JvmStreamsKt.encodeToStream(jsonFormat, BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE)), list, outputStream8);
                            Unit unit4 = Unit.INSTANCE;
                            CloseableKt.closeFinally(outputStream2, (Throwable) null);
                        } finally {
                        }
                    } finally {
                        CloseableKt.closeFinally(outputStream2, th5);
                    }
                } finally {
                    CloseableKt.closeFinally(outputStream6, th4);
                }
            } finally {
            }
        } catch (Throwable th6) {
            CloseableKt.closeFinally(outputStream, (Throwable) null);
            throw th6;
        }
    }

    private static final Object compareWith$lambda$2(IResFile $left, IResFile $right, Path $out) {
        return "The computed diff between '" + $left + "' and '" + $right + "' yields the following result: " + $out;
    }

    private static final Object compareWith$lambda$9$lambda$8(List $deletedIds) {
        return "[-] Deleted " + $deletedIds.size() + " checker ids. " + CollectionsKt.joinToString$default($deletedIds, "\n\t", "[\n\t", "\n]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
    }

    private static final Object compareWith$lambda$16$lambda$15(List $new) {
        return "[+] New " + $new.size() + " checker ids: " + CollectionsKt.joinToString$default($new, "\n\t", "[\n\t", "\n]", 0, (CharSequence) null, (Function1) null, 56, (Object) null);
    }

    /* compiled from: CheckerInfoCompare.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004¢\u0006\u0002\n��¨\u0006\f"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoCompare$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "jsonFormat", "Lkotlinx/serialization/json/Json;", "infoSerializer", "Lkotlinx/serialization/KSerializer;", "", "Lcn/sast/api/config/CheckerInfo;", "corax-cli"})
    /* loaded from: CheckerInfoCompare$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$17() {
        return Unit.INSTANCE;
    }
}
