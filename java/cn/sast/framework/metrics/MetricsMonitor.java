package cn.sast.framework.metrics;

import cn.sast.api.config.MainConfigKt;
import cn.sast.api.report.ProjectMetrics;
import cn.sast.api.report.ProjectMetrics$$serializer;
import cn.sast.api.report.Report;
import cn.sast.api.util.IMonitor;
import cn.sast.api.util.PhaseIntervalTimerKt;
import cn.sast.api.util.Timer;
import cn.sast.common.CustomRepeatingTimer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.result.ResultCollector;
import cn.sast.framework.result.ResultCounter;
import cn.sast.idfa.analysis.UsefulMetrics;
import com.charleskorn.kaml.Yaml;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.LongUnaryOperator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.ContextualSerializer;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.Transient;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.DoubleSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.modules.SerializersModuleBuilder;
import org.eclipse.microprofile.metrics.Gauge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MetricsMonitor.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0010!\n\u0002\u0018\u0002\n��\n\u0002\u0010%\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0004\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018�� Z2\u00020\u0001:\u0004XYZ[B\u0007¢\u0006\u0004\b\u0002\u0010\u0003B±\u0001\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014\u0012\u0014\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017\u0012\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0014\u0012\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u0014\u0012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b\u0002\u0010\u001fJ\u0010\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020\u0007H\u0016J\u0006\u0010;\u001a\u00020<J\u0006\u0010?\u001a\u00020<J\u0006\u0010@\u001a\u00020<J)\u0010A\u001a\u00020<\"\b\b��\u0010B*\u00020C2\u0006\u0010D\u001a\u00020\u00072\u0006\u0010E\u001a\u0002HBH\u0007¢\u0006\u0004\bF\u0010GJ\u0016\u0010A\u001a\u00020<2\u0006\u0010D\u001a\u00020\u00072\u0006\u0010E\u001a\u00020\u0007J\u000e\u0010H\u001a\u00020<2\u0006\u0010I\u001a\u00020JJ\u000e\u0010K\u001a\u00020<2\u0006\u0010L\u001a\u00020MJ\u000e\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020/J\u0006\u0010P\u001a\u00020<J%\u0010Q\u001a\u00020<2\u0006\u0010R\u001a\u00020��2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VH\u0001¢\u0006\u0002\bWR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n��\u001a\u0004\b \u0010!R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n��R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\"\u0010#R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u0004¢\u0006\u0002\n��R\u001f\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\t\u0012\u00070\u0018¢\u0006\u0002\b$0\u0017X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0014X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0014X\u0082\u0004¢\u0006\u0002\n��R\u001c\u0010%\u001a\u00020\t8\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b&\u0010\u0003\u001a\u0004\b'\u0010!R\"\u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020*0)8\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b+\u0010\u0003R\u001c\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00148\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b0\u0010\u0003R\u0018\u00101\u001a\u00020\u000b*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R \u00101\u001a\u00020\u000b*\n\u0012\u0004\u0012\u00020\t\u0018\u0001048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00105R\u001c\u00106\u001a\u0002078\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b8\u0010\u0003\u001a\u0004\b9\u0010:R\u0016\u0010,\u001a\u00020=8\u0002X\u0083\u0004¢\u0006\b\n��\u0012\u0004\b>\u0010\u0003¨\u0006\\"}, d2 = {"Lcn/sast/framework/metrics/MetricsMonitor;", "Lcn/sast/api/util/IMonitor;", "<init>", "()V", "seen0", "", "beginDate", "", "beginMillis", "", "elapsedSeconds", "", "elapsedTime", "endDate", "endTime", "jvmMemoryUsedMax", "jvmMemoryMax", "projectMetrics", "Lcn/sast/api/report/ProjectMetrics;", "phaseTimer", "", "Lcn/sast/framework/metrics/MetricsMonitor$PhaseTimer;", "final", "", "", "reports", "Lcn/sast/framework/metrics/ReportKey;", "snapshot", "Lcn/sast/framework/metrics/MetricsMonitor$MetricsSnapshot;", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;JDLjava/lang/String;Ljava/lang/String;JDDLcn/sast/api/report/ProjectMetrics;Ljava/util/List;Ljava/util/Map;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getBeginMillis", "()J", "getProjectMetrics", "()Lcn/sast/api/report/ProjectMetrics;", "Lkotlinx/serialization/Contextual;", "beginNanoTime", "getBeginNanoTime$annotations", "getBeginNanoTime", "allPhaseTimer", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcn/sast/api/util/Timer;", "getAllPhaseTimer$annotations", "timer", "phase", "analyzeFinishHook", "Ljava/lang/Thread;", "getAnalyzeFinishHook$annotations", "g", "getG", "(J)D", "Lorg/eclipse/microprofile/metrics/Gauge;", "(Lorg/eclipse/microprofile/metrics/Gauge;)D", "maxUsedMemory", "Ljava/util/concurrent/atomic/AtomicLong;", "getMaxUsedMemory$annotations", "getMaxUsedMemory", "()Ljava/util/concurrent/atomic/AtomicLong;", "record", "", "Lcn/sast/common/CustomRepeatingTimer;", "getTimer$annotations", "start", "stop", "put", "T", "", "name", "value", "putNumber", "(Ljava/lang/String;Ljava/lang/Number;)V", "take", "result", "Lcn/sast/framework/result/ResultCollector;", "serialize", "out", "Lcn/sast/common/IResDirectory;", "addAnalyzeFinishHook", "t", "runAnalyzeFinishHook", "write$Self", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "MetricsSnapshot", "PhaseTimer", "Companion", "$serializer", "corax-framework"})
@SourceDebugExtension({"SMAP\nMetricsMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MetricsMonitor.kt\ncn/sast/framework/metrics/MetricsMonitor\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 6 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuildersKt\n*L\n1#1,249:1\n72#2,2:250\n1#3:252\n1#3:253\n1485#4:254\n1510#4,3:255\n1513#4,3:265\n1246#4,4:270\n1246#4,4:276\n808#4,11:280\n1863#4,2:291\n381#5,7:258\n462#5:268\n412#5:269\n477#5:274\n423#5:275\n31#6,3:293\n*S KotlinDebug\n*F\n+ 1 MetricsMonitor.kt\ncn/sast/framework/metrics/MetricsMonitor\n*L\n92#1:250,2\n92#1:252\n159#1:254\n159#1:255,3\n159#1:265,3\n159#1:270,4\n160#1:276,4\n165#1:280,11\n218#1:291,2\n159#1:258,7\n159#1:268\n159#1:269\n160#1:274\n160#1:275\n228#1:293,3\n*E\n"})
/* loaded from: MetricsMonitor.class */
public final class MetricsMonitor implements IMonitor {

    @NotNull
    private String beginDate;
    private final long beginMillis;
    private double elapsedSeconds;

    @NotNull
    private String elapsedTime;

    @NotNull
    private String endDate;
    private long endTime;
    private double jvmMemoryUsedMax;
    private final double jvmMemoryMax;

    @NotNull
    private final ProjectMetrics projectMetrics;

    @NotNull
    private final List<PhaseTimer> phaseTimer;

    /* renamed from: final, reason: not valid java name */
    @NotNull
    private final Map<String, Object> f3final;

    @NotNull
    private final List<ReportKey> reports;

    @NotNull
    private final List<MetricsSnapshot> snapshot;
    private final long beginNanoTime;

    @NotNull
    private final ConcurrentHashMap<String, Timer> allPhaseTimer;

    @NotNull
    private final List<Thread> analyzeFinishHook;

    @NotNull
    private final AtomicLong maxUsedMemory;

    @NotNull
    private final CustomRepeatingTimer timer;

    @NotNull
    private static final Yaml yamlFormat;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {null, null, null, null, null, null, null, null, null, new ArrayListSerializer(MetricsMonitor$PhaseTimer$$serializer.INSTANCE), new LinkedHashMapSerializer(StringSerializer.INSTANCE, new ContextualSerializer(Reflection.getOrCreateKotlinClass(Object.class), (KSerializer) null, new KSerializer[0])), new ArrayListSerializer(ReportKey$$serializer.INSTANCE), new ArrayListSerializer(MetricsMonitor$MetricsSnapshot$$serializer.INSTANCE)};

    @Transient
    public static /* synthetic */ void getBeginNanoTime$annotations() {
    }

    @Transient
    private static /* synthetic */ void getAllPhaseTimer$annotations() {
    }

    @Transient
    private static /* synthetic */ void getAnalyzeFinishHook$annotations() {
    }

    @Transient
    public static /* synthetic */ void getMaxUsedMemory$annotations() {
    }

    @Transient
    private static /* synthetic */ void getTimer$annotations() {
    }

    public MetricsMonitor() {
        this.beginDate = "";
        this.beginMillis = System.currentTimeMillis();
        this.elapsedSeconds = -1.0d;
        this.elapsedTime = "";
        this.endDate = "";
        this.jvmMemoryUsedMax = -1.0d;
        Gauge<Long> jvmMemoryMax = UsefulMetrics.Companion.getMetrics().getJvmMemoryMax();
        this.jvmMemoryMax = MetricsMonitorKt.inMemGB$default(jvmMemoryMax != null ? Double.valueOf(getG(jvmMemoryMax)) : null, 0, 1, null);
        this.projectMetrics = new ProjectMetrics((List) null, (List) null, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0f, 0, 0, 0, 0L, 0L, 0L, 0, 0, 0, 0.0f, 0, 0.0f, 0, 16777215, (DefaultConstructorMarker) null);
        this.phaseTimer = new ArrayList();
        this.f3final = new LinkedHashMap();
        this.reports = new ArrayList();
        this.snapshot = new ArrayList();
        this.beginNanoTime = PhaseIntervalTimerKt.currentNanoTime();
        this.allPhaseTimer = new ConcurrentHashMap<>();
        this.analyzeFinishHook = new ArrayList();
        this.maxUsedMemory = new AtomicLong(0L);
        CustomRepeatingTimer $this$timer_u24lambda_u246 = new CustomRepeatingTimer(2000L, () -> {
            return timer$lambda$5(r4);
        });
        $this$timer_u24lambda_u246.setRepeats(true);
        record();
        this.timer = $this$timer_u24lambda_u246;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_framework(MetricsMonitor self, CompositeEncoder output, SerialDescriptor serialDesc) {
        boolean z;
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        boolean z2 = output.shouldEncodeElementDefault(serialDesc, 0) || !Intrinsics.areEqual(self.beginDate, "");
        if (z2) {
            output.encodeStringElement(serialDesc, 0, self.beginDate);
        }
        boolean z3 = output.shouldEncodeElementDefault(serialDesc, 1) || self.beginMillis != System.currentTimeMillis();
        if (z3) {
            output.encodeLongElement(serialDesc, 1, self.beginMillis);
        }
        boolean z4 = output.shouldEncodeElementDefault(serialDesc, 2) || Double.compare(self.elapsedSeconds, -1.0d) != 0;
        if (z4) {
            output.encodeDoubleElement(serialDesc, 2, self.elapsedSeconds);
        }
        boolean z5 = output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.elapsedTime, "");
        if (z5) {
            output.encodeStringElement(serialDesc, 3, self.elapsedTime);
        }
        boolean z6 = output.shouldEncodeElementDefault(serialDesc, 4) || !Intrinsics.areEqual(self.endDate, "");
        if (z6) {
            output.encodeStringElement(serialDesc, 4, self.endDate);
        }
        boolean z7 = output.shouldEncodeElementDefault(serialDesc, 5) || self.endTime != 0;
        if (z7) {
            output.encodeLongElement(serialDesc, 5, self.endTime);
        }
        boolean z8 = output.shouldEncodeElementDefault(serialDesc, 6) || Double.compare(self.jvmMemoryUsedMax, -1.0d) != 0;
        if (z8) {
            output.encodeDoubleElement(serialDesc, 6, self.jvmMemoryUsedMax);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7)) {
            z = true;
        } else {
            double d = self.jvmMemoryMax;
            Gauge<Long> jvmMemoryMax = UsefulMetrics.Companion.getMetrics().getJvmMemoryMax();
            z = Double.compare(d, MetricsMonitorKt.inMemGB$default(jvmMemoryMax != null ? Double.valueOf(self.getG(jvmMemoryMax)) : null, 0, 1, null)) != 0;
        }
        if (z) {
            output.encodeDoubleElement(serialDesc, 7, self.jvmMemoryMax);
        }
        boolean z9 = output.shouldEncodeElementDefault(serialDesc, 8) || !Intrinsics.areEqual(self.getProjectMetrics(), new ProjectMetrics((List) null, (List) null, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0f, 0, 0, 0, 0L, 0L, 0L, 0, 0, 0, 0.0f, 0, 0.0f, 0, 16777215, (DefaultConstructorMarker) null));
        if (z9) {
            output.encodeSerializableElement(serialDesc, 8, ProjectMetrics$$serializer.INSTANCE, self.getProjectMetrics());
        }
        boolean z10 = output.shouldEncodeElementDefault(serialDesc, 9) || !Intrinsics.areEqual(self.phaseTimer, new ArrayList());
        if (z10) {
            output.encodeSerializableElement(serialDesc, 9, serializationStrategyArr[9], self.phaseTimer);
        }
        boolean z11 = output.shouldEncodeElementDefault(serialDesc, 10) || !Intrinsics.areEqual(self.f3final, new LinkedHashMap());
        if (z11) {
            output.encodeSerializableElement(serialDesc, 10, serializationStrategyArr[10], self.f3final);
        }
        boolean z12 = output.shouldEncodeElementDefault(serialDesc, 11) || !Intrinsics.areEqual(self.reports, new ArrayList());
        if (z12) {
            output.encodeSerializableElement(serialDesc, 11, serializationStrategyArr[11], self.reports);
        }
        boolean z13 = output.shouldEncodeElementDefault(serialDesc, 12) || !Intrinsics.areEqual(self.snapshot, new ArrayList());
        if (z13) {
            output.encodeSerializableElement(serialDesc, 12, serializationStrategyArr[12], self.snapshot);
        }
    }

    public /* synthetic */ MetricsMonitor(int seen0, String beginDate, long beginMillis, double elapsedSeconds, String elapsedTime, String endDate, long endTime, double jvmMemoryUsedMax, double jvmMemoryMax, ProjectMetrics projectMetrics, List phaseTimer, Map map, List reports, List snapshot, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & seen0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 0, MetricsMonitor$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen0 & 1) == 0) {
            this.beginDate = "";
        } else {
            this.beginDate = beginDate;
        }
        if ((seen0 & 2) == 0) {
            this.beginMillis = System.currentTimeMillis();
        } else {
            this.beginMillis = beginMillis;
        }
        if ((seen0 & 4) == 0) {
            this.elapsedSeconds = -1.0d;
        } else {
            this.elapsedSeconds = elapsedSeconds;
        }
        if ((seen0 & 8) == 0) {
            this.elapsedTime = "";
        } else {
            this.elapsedTime = elapsedTime;
        }
        if ((seen0 & 16) == 0) {
            this.endDate = "";
        } else {
            this.endDate = endDate;
        }
        if ((seen0 & 32) == 0) {
            this.endTime = 0L;
        } else {
            this.endTime = endTime;
        }
        if ((seen0 & 64) == 0) {
            this.jvmMemoryUsedMax = -1.0d;
        } else {
            this.jvmMemoryUsedMax = jvmMemoryUsedMax;
        }
        if ((seen0 & 128) == 0) {
            Gauge<Long> jvmMemoryMax2 = UsefulMetrics.Companion.getMetrics().getJvmMemoryMax();
            this.jvmMemoryMax = MetricsMonitorKt.inMemGB$default(jvmMemoryMax2 != null ? Double.valueOf(getG(jvmMemoryMax2)) : null, 0, 1, null);
        } else {
            this.jvmMemoryMax = jvmMemoryMax;
        }
        if ((seen0 & 256) == 0) {
            this.projectMetrics = new ProjectMetrics((List) null, (List) null, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0f, 0, 0, 0, 0L, 0L, 0L, 0, 0, 0, 0.0f, 0, 0.0f, 0, 16777215, (DefaultConstructorMarker) null);
        } else {
            this.projectMetrics = projectMetrics;
        }
        if ((seen0 & 512) == 0) {
            this.phaseTimer = new ArrayList();
        } else {
            this.phaseTimer = phaseTimer;
        }
        if ((seen0 & 1024) == 0) {
            this.f3final = new LinkedHashMap();
        } else {
            this.f3final = map;
        }
        if ((seen0 & 2048) == 0) {
            this.reports = new ArrayList();
        } else {
            this.reports = reports;
        }
        if ((seen0 & 4096) == 0) {
            this.snapshot = new ArrayList();
        } else {
            this.snapshot = snapshot;
        }
        this.beginNanoTime = PhaseIntervalTimerKt.currentNanoTime();
        this.allPhaseTimer = new ConcurrentHashMap<>();
        this.analyzeFinishHook = new ArrayList();
        this.maxUsedMemory = new AtomicLong(0L);
        CustomRepeatingTimer $this$_init__u24lambda_u2419 = new CustomRepeatingTimer(2000L, () -> {
            return _init_$lambda$18(r4);
        });
        $this$_init__u24lambda_u2419.setRepeats(true);
        record();
        this.timer = $this$_init__u24lambda_u2419;
    }

    public final long getBeginMillis() {
        return this.beginMillis;
    }

    @Override // cn.sast.api.util.IMonitor
    @NotNull
    public ProjectMetrics getProjectMetrics() {
        return this.projectMetrics;
    }

    /* compiled from: MetricsMonitor.kt */
    @Serializable
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� 02\u00020\u0001:\u0002/0BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nBU\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013JT\u0010\u001f\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\fHÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001J%\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020��2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0001¢\u0006\u0002\b.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0015\u0010\u0013R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0016\u0010\u0013R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0017\u0010\u0013R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0018\u0010\u0013¨\u00061"}, d2 = {"Lcn/sast/framework/metrics/MetricsMonitor$MetricsSnapshot;", "", "timeInSecond", "", "jvmMemoryUsed", "jvmMemoryUsedMax", "jvmMemoryCommitted", "freePhysicalSize", "cpuSystemCpuLoad", "<init>", "(DLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IDLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getTimeInSecond", "()D", "getJvmMemoryUsed", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getJvmMemoryUsedMax", "getJvmMemoryCommitted", "getFreePhysicalSize", "getCpuSystemCpuLoad", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(DLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)Lcn/sast/framework/metrics/MetricsMonitor$MetricsSnapshot;", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
    /* loaded from: MetricsMonitor$MetricsSnapshot.class */
    public static final class MetricsSnapshot {

        @NotNull
        public static final Companion Companion = new Companion(null);
        private final double timeInSecond;

        @Nullable
        private final Double jvmMemoryUsed;

        @Nullable
        private final Double jvmMemoryUsedMax;

        @Nullable
        private final Double jvmMemoryCommitted;

        @Nullable
        private final Double freePhysicalSize;

        @Nullable
        private final Double cpuSystemCpuLoad;

        public final double component1() {
            return this.timeInSecond;
        }

        @Nullable
        public final Double component2() {
            return this.jvmMemoryUsed;
        }

        @Nullable
        public final Double component3() {
            return this.jvmMemoryUsedMax;
        }

        @Nullable
        public final Double component4() {
            return this.jvmMemoryCommitted;
        }

        @Nullable
        public final Double component5() {
            return this.freePhysicalSize;
        }

        @Nullable
        public final Double component6() {
            return this.cpuSystemCpuLoad;
        }

        @NotNull
        public final MetricsSnapshot copy(double timeInSecond, @Nullable Double jvmMemoryUsed, @Nullable Double jvmMemoryUsedMax, @Nullable Double jvmMemoryCommitted, @Nullable Double freePhysicalSize, @Nullable Double cpuSystemCpuLoad) {
            return new MetricsSnapshot(timeInSecond, jvmMemoryUsed, jvmMemoryUsedMax, jvmMemoryCommitted, freePhysicalSize, cpuSystemCpuLoad);
        }

        public static /* synthetic */ MetricsSnapshot copy$default(MetricsSnapshot metricsSnapshot, double d, Double d2, Double d3, Double d4, Double d5, Double d6, int i, Object obj) {
            if ((i & 1) != 0) {
                d = metricsSnapshot.timeInSecond;
            }
            if ((i & 2) != 0) {
                d2 = metricsSnapshot.jvmMemoryUsed;
            }
            if ((i & 4) != 0) {
                d3 = metricsSnapshot.jvmMemoryUsedMax;
            }
            if ((i & 8) != 0) {
                d4 = metricsSnapshot.jvmMemoryCommitted;
            }
            if ((i & 16) != 0) {
                d5 = metricsSnapshot.freePhysicalSize;
            }
            if ((i & 32) != 0) {
                d6 = metricsSnapshot.cpuSystemCpuLoad;
            }
            return metricsSnapshot.copy(d, d2, d3, d4, d5, d6);
        }

        @NotNull
        public String toString() {
            double d = this.timeInSecond;
            Double d2 = this.jvmMemoryUsed;
            Double d3 = this.jvmMemoryUsedMax;
            Double d4 = this.jvmMemoryCommitted;
            Double d5 = this.freePhysicalSize;
            Double d6 = this.cpuSystemCpuLoad;
            return "MetricsSnapshot(timeInSecond=" + d + ", jvmMemoryUsed=" + d + ", jvmMemoryUsedMax=" + d2 + ", jvmMemoryCommitted=" + d3 + ", freePhysicalSize=" + d4 + ", cpuSystemCpuLoad=" + d5 + ")";
        }

        public int hashCode() {
            int result = Double.hashCode(this.timeInSecond);
            return (((((((((result * 31) + (this.jvmMemoryUsed == null ? 0 : this.jvmMemoryUsed.hashCode())) * 31) + (this.jvmMemoryUsedMax == null ? 0 : this.jvmMemoryUsedMax.hashCode())) * 31) + (this.jvmMemoryCommitted == null ? 0 : this.jvmMemoryCommitted.hashCode())) * 31) + (this.freePhysicalSize == null ? 0 : this.freePhysicalSize.hashCode())) * 31) + (this.cpuSystemCpuLoad == null ? 0 : this.cpuSystemCpuLoad.hashCode());
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MetricsSnapshot)) {
                return false;
            }
            MetricsSnapshot metricsSnapshot = (MetricsSnapshot) other;
            return Double.compare(this.timeInSecond, metricsSnapshot.timeInSecond) == 0 && Intrinsics.areEqual(this.jvmMemoryUsed, metricsSnapshot.jvmMemoryUsed) && Intrinsics.areEqual(this.jvmMemoryUsedMax, metricsSnapshot.jvmMemoryUsedMax) && Intrinsics.areEqual(this.jvmMemoryCommitted, metricsSnapshot.jvmMemoryCommitted) && Intrinsics.areEqual(this.freePhysicalSize, metricsSnapshot.freePhysicalSize) && Intrinsics.areEqual(this.cpuSystemCpuLoad, metricsSnapshot.cpuSystemCpuLoad);
        }

        /* compiled from: MetricsMonitor.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/metrics/MetricsMonitor$MetricsSnapshot$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/metrics/MetricsMonitor$MetricsSnapshot;", "corax-framework"})
        /* loaded from: MetricsMonitor$MetricsSnapshot$Companion.class */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            @NotNull
            public final KSerializer<MetricsSnapshot> serializer() {
                return MetricsMonitor$MetricsSnapshot$$serializer.INSTANCE;
            }
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$corax_framework(MetricsSnapshot self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeDoubleElement(serialDesc, 0, self.timeInSecond);
            output.encodeNullableSerializableElement(serialDesc, 1, DoubleSerializer.INSTANCE, self.jvmMemoryUsed);
            output.encodeNullableSerializableElement(serialDesc, 2, DoubleSerializer.INSTANCE, self.jvmMemoryUsedMax);
            output.encodeNullableSerializableElement(serialDesc, 3, DoubleSerializer.INSTANCE, self.jvmMemoryCommitted);
            output.encodeNullableSerializableElement(serialDesc, 4, DoubleSerializer.INSTANCE, self.freePhysicalSize);
            output.encodeNullableSerializableElement(serialDesc, 5, DoubleSerializer.INSTANCE, self.cpuSystemCpuLoad);
        }

        public /* synthetic */ MetricsSnapshot(int seen0, double timeInSecond, Double jvmMemoryUsed, Double jvmMemoryUsedMax, Double jvmMemoryCommitted, Double freePhysicalSize, Double cpuSystemCpuLoad, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (63 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException(seen0, 63, MetricsMonitor$MetricsSnapshot$$serializer.INSTANCE.getDescriptor());
            }
            this.timeInSecond = timeInSecond;
            this.jvmMemoryUsed = jvmMemoryUsed;
            this.jvmMemoryUsedMax = jvmMemoryUsedMax;
            this.jvmMemoryCommitted = jvmMemoryCommitted;
            this.freePhysicalSize = freePhysicalSize;
            this.cpuSystemCpuLoad = cpuSystemCpuLoad;
        }

        public MetricsSnapshot(double timeInSecond, @Nullable Double jvmMemoryUsed, @Nullable Double jvmMemoryUsedMax, @Nullable Double jvmMemoryCommitted, @Nullable Double freePhysicalSize, @Nullable Double cpuSystemCpuLoad) {
            this.timeInSecond = timeInSecond;
            this.jvmMemoryUsed = jvmMemoryUsed;
            this.jvmMemoryUsedMax = jvmMemoryUsedMax;
            this.jvmMemoryCommitted = jvmMemoryCommitted;
            this.freePhysicalSize = freePhysicalSize;
            this.cpuSystemCpuLoad = cpuSystemCpuLoad;
        }

        public final double getTimeInSecond() {
            return this.timeInSecond;
        }

        @Nullable
        public final Double getJvmMemoryUsed() {
            return this.jvmMemoryUsed;
        }

        @Nullable
        public final Double getJvmMemoryUsedMax() {
            return this.jvmMemoryUsedMax;
        }

        @Nullable
        public final Double getJvmMemoryCommitted() {
            return this.jvmMemoryCommitted;
        }

        @Nullable
        public final Double getFreePhysicalSize() {
            return this.freePhysicalSize;
        }

        @Nullable
        public final Double getCpuSystemCpuLoad() {
            return this.cpuSystemCpuLoad;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MetricsMonitor.kt */
    @Serializable
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��F\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018�� /2\u00020\u0001:\u0002./B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fBM\b\u0010\u0012\u0006\u0010\r\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u000b\u0010\u0010J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003JE\u0010 \u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\bHÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020��2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u0014¨\u00060"}, d2 = {"Lcn/sast/framework/metrics/MetricsMonitor$PhaseTimer;", "", "name", "", "start", "", "elapsedTime", "phaseStartCount", "", "averageElapsedTime", "end", "<init>", "(Ljava/lang/String;DDIDD)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;DDIDDLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getName", "()Ljava/lang/String;", "getStart", "()D", "getElapsedTime", "getPhaseStartCount", "()I", "getAverageElapsedTime", "getEnd", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_framework", "$serializer", "Companion", "corax-framework"})
    /* loaded from: MetricsMonitor$PhaseTimer.class */
    static final class PhaseTimer {

        @NotNull
        public static final Companion Companion = new Companion(null);

        @NotNull
        private final String name;
        private final double start;
        private final double elapsedTime;
        private final int phaseStartCount;
        private final double averageElapsedTime;
        private final double end;

        @NotNull
        public final String component1() {
            return this.name;
        }

        public final double component2() {
            return this.start;
        }

        public final double component3() {
            return this.elapsedTime;
        }

        public final int component4() {
            return this.phaseStartCount;
        }

        public final double component5() {
            return this.averageElapsedTime;
        }

        public final double component6() {
            return this.end;
        }

        @NotNull
        public final PhaseTimer copy(@NotNull String name, double start, double elapsedTime, int phaseStartCount, double averageElapsedTime, double end) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new PhaseTimer(name, start, elapsedTime, phaseStartCount, averageElapsedTime, end);
        }

        public static /* synthetic */ PhaseTimer copy$default(PhaseTimer phaseTimer, String str, double d, double d2, int i, double d3, double d4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = phaseTimer.name;
            }
            if ((i2 & 2) != 0) {
                d = phaseTimer.start;
            }
            if ((i2 & 4) != 0) {
                d2 = phaseTimer.elapsedTime;
            }
            if ((i2 & 8) != 0) {
                i = phaseTimer.phaseStartCount;
            }
            if ((i2 & 16) != 0) {
                d3 = phaseTimer.averageElapsedTime;
            }
            if ((i2 & 32) != 0) {
                d4 = phaseTimer.end;
            }
            return phaseTimer.copy(str, d, d2, i, d3, d4);
        }

        @NotNull
        public String toString() {
            String str = this.name;
            double d = this.start;
            double d2 = this.elapsedTime;
            int i = this.phaseStartCount;
            double d3 = this.averageElapsedTime;
            double d4 = this.end;
            return "PhaseTimer(name=" + str + ", start=" + d + ", elapsedTime=" + str + ", phaseStartCount=" + d2 + ", averageElapsedTime=" + str + ", end=" + i + ")";
        }

        public int hashCode() {
            int result = this.name.hashCode();
            return (((((((((result * 31) + Double.hashCode(this.start)) * 31) + Double.hashCode(this.elapsedTime)) * 31) + Integer.hashCode(this.phaseStartCount)) * 31) + Double.hashCode(this.averageElapsedTime)) * 31) + Double.hashCode(this.end);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PhaseTimer)) {
                return false;
            }
            PhaseTimer phaseTimer = (PhaseTimer) other;
            return Intrinsics.areEqual(this.name, phaseTimer.name) && Double.compare(this.start, phaseTimer.start) == 0 && Double.compare(this.elapsedTime, phaseTimer.elapsedTime) == 0 && this.phaseStartCount == phaseTimer.phaseStartCount && Double.compare(this.averageElapsedTime, phaseTimer.averageElapsedTime) == 0 && Double.compare(this.end, phaseTimer.end) == 0;
        }

        /* compiled from: MetricsMonitor.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/framework/metrics/MetricsMonitor$PhaseTimer$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/metrics/MetricsMonitor$PhaseTimer;", "corax-framework"})
        /* loaded from: MetricsMonitor$PhaseTimer$Companion.class */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            @NotNull
            public final KSerializer<PhaseTimer> serializer() {
                return MetricsMonitor$PhaseTimer$$serializer.INSTANCE;
            }
        }

        @JvmStatic
        public static final /* synthetic */ void write$Self$corax_framework(PhaseTimer self, CompositeEncoder output, SerialDescriptor serialDesc) {
            output.encodeStringElement(serialDesc, 0, self.name);
            output.encodeDoubleElement(serialDesc, 1, self.start);
            output.encodeDoubleElement(serialDesc, 2, self.elapsedTime);
            output.encodeIntElement(serialDesc, 3, self.phaseStartCount);
            output.encodeDoubleElement(serialDesc, 4, self.averageElapsedTime);
            output.encodeDoubleElement(serialDesc, 5, self.end);
        }

        public /* synthetic */ PhaseTimer(int seen0, String name, double start, double elapsedTime, int phaseStartCount, double averageElapsedTime, double end, SerializationConstructorMarker serializationConstructorMarker) {
            if (63 != (63 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException(seen0, 63, MetricsMonitor$PhaseTimer$$serializer.INSTANCE.getDescriptor());
            }
            this.name = name;
            this.start = start;
            this.elapsedTime = elapsedTime;
            this.phaseStartCount = phaseStartCount;
            this.averageElapsedTime = averageElapsedTime;
            this.end = end;
        }

        public PhaseTimer(@NotNull String name, double start, double elapsedTime, int phaseStartCount, double averageElapsedTime, double end) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.name = name;
            this.start = start;
            this.elapsedTime = elapsedTime;
            this.phaseStartCount = phaseStartCount;
            this.averageElapsedTime = averageElapsedTime;
            this.end = end;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public final double getStart() {
            return this.start;
        }

        public final double getElapsedTime() {
            return this.elapsedTime;
        }

        public final int getPhaseStartCount() {
            return this.phaseStartCount;
        }

        public final double getAverageElapsedTime() {
            return this.averageElapsedTime;
        }

        public final double getEnd() {
            return this.end;
        }
    }

    public final long getBeginNanoTime() {
        return this.beginNanoTime;
    }

    @Override // cn.sast.api.util.IMonitor
    @NotNull
    public Timer timer(@NotNull String phase) {
        Intrinsics.checkNotNullParameter(phase, "phase");
        ConcurrentMap $this$getOrPut$iv = this.allPhaseTimer;
        Timer timerPutIfAbsent = $this$getOrPut$iv.get(phase);
        if (timerPutIfAbsent == null) {
            Timer timer = new Timer(phase);
            timerPutIfAbsent = $this$getOrPut$iv.putIfAbsent(phase, timer);
            if (timerPutIfAbsent == null) {
                timerPutIfAbsent = timer;
            }
        }
        Intrinsics.checkNotNullExpressionValue(timerPutIfAbsent, "getOrPut(...)");
        return timerPutIfAbsent;
    }

    private final double getG(long $this$g) {
        return (($this$g / 1024.0d) / 1024.0d) / 1024.0d;
    }

    private final double getG(Gauge<Long> gauge) {
        if (gauge == null) {
            return -1.0d;
        }
        Object value = gauge.getValue();
        Long it = (Long) value;
        Long l = (Long) ((it.longValue() > 0L ? 1 : (it.longValue() == 0L ? 0 : -1)) >= 0 ? value : null);
        if (l == null) {
            return -1.0d;
        }
        long v = l.longValue();
        return getG(v);
    }

    @NotNull
    public final AtomicLong getMaxUsedMemory() {
        return this.maxUsedMemory;
    }

    public final void record() {
        Double dValueOf;
        Double d;
        Long l;
        UsefulMetrics m = UsefulMetrics.Companion.getMetrics();
        synchronized (this) {
            Gauge<Long> jvmMemoryUsed = m.getJvmMemoryUsed();
            if (jvmMemoryUsed != null && (l = (Long) jvmMemoryUsed.getValue()) != null) {
                final long usedMemory = l.longValue();
                this.maxUsedMemory.updateAndGet(new LongUnaryOperator() { // from class: cn.sast.framework.metrics.MetricsMonitor$record$1$1$1
                    @Override // java.util.function.LongUnaryOperator
                    public final long applyAsLong(long it) {
                        return it < usedMemory ? usedMemory : it;
                    }
                });
            }
            List<MetricsSnapshot> list = this.snapshot;
            double dNanoTimeInSeconds$default = PhaseIntervalTimerKt.nanoTimeInSeconds$default(MetricsMonitorKt.timeSub(Long.valueOf(PhaseIntervalTimerKt.currentNanoTime()), this.beginNanoTime), 0, 1, null);
            Gauge<Long> jvmMemoryUsed2 = m.getJvmMemoryUsed();
            Double dValueOf2 = Double.valueOf(MetricsMonitorKt.inMemGB$default(jvmMemoryUsed2 != null ? Double.valueOf(getG(jvmMemoryUsed2)) : null, 0, 1, null));
            Double dValueOf3 = Double.valueOf(MetricsMonitorKt.inMemGB$default(Double.valueOf(getG(this.maxUsedMemory.get())), 0, 1, null));
            Gauge<Long> jvmMemoryCommitted = m.getJvmMemoryCommitted();
            Double dValueOf4 = Double.valueOf(MetricsMonitorKt.inMemGB$default(jvmMemoryCommitted != null ? Double.valueOf(getG(jvmMemoryCommitted)) : null, 0, 1, null));
            Gauge<Long> freePhysicalSize = m.getFreePhysicalSize();
            Double dValueOf5 = Double.valueOf(MetricsMonitorKt.inMemGB$default(freePhysicalSize != null ? Double.valueOf(getG(freePhysicalSize)) : null, 0, 1, null));
            Gauge<Double> cpuSystemCpuLoad = m.getCpuSystemCpuLoad();
            if (cpuSystemCpuLoad == null || (d = (Double) cpuSystemCpuLoad.getValue()) == null) {
                dValueOf = null;
            } else {
                double it = d.doubleValue();
                dNanoTimeInSeconds$default = dNanoTimeInSeconds$default;
                dValueOf2 = dValueOf2;
                dValueOf3 = dValueOf3;
                dValueOf4 = dValueOf4;
                dValueOf5 = dValueOf5;
                dValueOf = Double.valueOf(PhaseIntervalTimerKt.retainDecimalPlaces$default(it, 2, null, 4, null));
            }
            Double d2 = dValueOf2;
            double d3 = dNanoTimeInSeconds$default;
            list.add(new MetricsSnapshot(d3, d2, dValueOf3, dValueOf4, dValueOf5, dValueOf));
            Unit unit = Unit.INSTANCE;
        }
    }

    private static final Unit timer$lambda$5(MetricsMonitor this$0) {
        this$0.record();
        return Unit.INSTANCE;
    }

    private static final Unit _init_$lambda$18(MetricsMonitor this$0) {
        this$0.record();
        return Unit.INSTANCE;
    }

    public final void start() {
        this.timer.start();
    }

    public final void stop() {
        this.timer.stop();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmName(name = "putNumber")
    public final <T extends Number> void putNumber(@NotNull String name, @NotNull T t) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(t, "value");
        synchronized (this) {
            Map<String, Object> map = this.f3final;
            Pair pair = TuplesKt.to(name, t);
            map.put(pair.getFirst(), pair.getSecond());
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void put(@NotNull String name, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        synchronized (this) {
            Map<String, Object> map = this.f3final;
            Pair pair = TuplesKt.to(name, value);
            map.put(pair.getFirst(), pair.getSecond());
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void take(@NotNull ResultCollector result) {
        Object obj;
        Intrinsics.checkNotNullParameter(result, "result");
        synchronized (this) {
            getProjectMetrics().setSerializedReports(result.getReports().size());
            List<ReportKey> list = this.reports;
            Iterable $this$groupBy$iv = result.getReports();
            Map destination$iv$iv = new LinkedHashMap();
            for (Object element$iv$iv : $this$groupBy$iv) {
                Report it = (Report) element$iv$iv;
                ReportKey reportKey = new ReportKey(it.getCategory(), it.getType(), 0, 4, (DefaultConstructorMarker) null);
                Object value$iv$iv$iv = destination$iv$iv.get(reportKey);
                if (value$iv$iv$iv == null) {
                    ArrayList arrayList = new ArrayList();
                    destination$iv$iv.put(reportKey, arrayList);
                    obj = arrayList;
                } else {
                    obj = value$iv$iv$iv;
                }
                List list$iv$iv = (List) obj;
                list$iv$iv.add(element$iv$iv);
            }
            Map destination$iv$iv2 = new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv.size()));
            Iterable $this$associateByTo$iv$iv$iv = destination$iv$iv.entrySet();
            for (Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                Map.Entry it$iv$iv = (Map.Entry) element$iv$iv$iv;
                destination$iv$iv2.put(it$iv$iv.getKey(), Integer.valueOf(((Collection) ((Map.Entry) element$iv$iv$iv).getValue()).size()));
            }
            Map destination$iv$iv3 = new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv2.size()));
            Iterable $this$associateByTo$iv$iv$iv2 = destination$iv$iv2.entrySet();
            for (Object element$iv$iv$iv2 : $this$associateByTo$iv$iv$iv2) {
                Map.Entry it2 = (Map.Entry) element$iv$iv$iv2;
                ((ReportKey) it2.getKey()).setSize(((Number) it2.getValue()).intValue());
                Map.Entry it$iv$iv2 = (Map.Entry) element$iv$iv$iv2;
                destination$iv$iv3.put((ReportKey) it2.getKey(), it$iv$iv2.getValue());
            }
            CollectionsKt.addAll(list, destination$iv$iv3.keySet());
            Iterable $this$filterIsInstance$iv = result.getCollectors();
            Collection destination$iv$iv4 = new ArrayList();
            for (Object element$iv$iv2 : $this$filterIsInstance$iv) {
                if (element$iv$iv2 instanceof ResultCounter) {
                    destination$iv$iv4.add(element$iv$iv2);
                }
            }
            ResultCounter counter = (ResultCounter) CollectionsKt.firstOrNull((List) destination$iv$iv4);
            if (counter != null) {
                putNumber("infoflow.results", Integer.valueOf(counter.getInfoflowResCount().get()));
                putNumber("infoflow.abstraction", Integer.valueOf(counter.getInfoflowAbsAtSinkCount().get()));
                putNumber("symbolic.execution", Integer.valueOf(counter.getSymbolicUTbotCount().get()));
                putNumber("PreAnalysis.results", Integer.valueOf(counter.getPreAnalysisResultCount().get()));
                putNumber("built-in.Analysis.results", Integer.valueOf(counter.getBuiltInAnalysisCount().get()));
                putNumber("AbstractInterpretationAnalysis.results", Integer.valueOf(counter.getDataFlowCount().get()));
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0131  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void serialize(@org.jetbrains.annotations.NotNull cn.sast.common.IResDirectory r17) {
        /*
            Method dump skipped, instructions count: 467
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.metrics.MetricsMonitor.serialize(cn.sast.common.IResDirectory):void");
    }

    public final void addAnalyzeFinishHook(@NotNull Thread t) {
        Intrinsics.checkNotNullParameter(t, "t");
        this.analyzeFinishHook.add(t);
    }

    public final void runAnalyzeFinishHook() {
        synchronized (this.analyzeFinishHook) {
            Iterable $this$forEach$iv = this.analyzeFinishHook;
            for (Object element$iv : $this$forEach$iv) {
                Thread it = (Thread) element$iv;
                it.start();
                it.join();
            }
            this.analyzeFinishHook.clear();
            Unit unit = Unit.INSTANCE;
        }
    }

    /* compiled from: MetricsMonitor.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nR\u0017\u0010\u0004\u001a\u00020\u0005¢\u0006\u000e\n��\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcn/sast/framework/metrics/MetricsMonitor$Companion;", "", "<init>", "()V", "yamlFormat", "Lcom/charleskorn/kaml/Yaml;", "getYamlFormat$annotations", "getYamlFormat", "()Lcom/charleskorn/kaml/Yaml;", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/framework/metrics/MetricsMonitor;", "corax-framework"})
    /* loaded from: MetricsMonitor$Companion.class */
    public static final class Companion {
        public static /* synthetic */ void getYamlFormat$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KSerializer<MetricsMonitor> serializer() {
            return MetricsMonitor$$serializer.INSTANCE;
        }

        @NotNull
        public final Yaml getYamlFormat() {
            return MetricsMonitor.yamlFormat;
        }
    }

    static {
        SerializersModuleBuilder builder$iv = new SerializersModuleBuilder();
        builder$iv.contextual(Reflection.getOrCreateKotlinClass(Object.class), new DynamicLookupSerializer());
        yamlFormat = new Yaml(builder$iv.build(), MainConfigKt.getYamlConfiguration());
    }
}
