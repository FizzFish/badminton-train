package cn.sast.api.config;

import cn.sast.common.IResDirectory;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import kotlinx.serialization.json.JvmStreamsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/* compiled from: ScanFilter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� 92\u00020\u0001:\u00029:B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J6\u0010\u0018\u001a\u00020\u00192\u0010\u0010\u001a\u001a\f\u0012\u0004\u0012\u00020\u00150\u001bj\u0002`\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0014J$\u0010!\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010\"\u001a\u00020#2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0014J$\u0010\u0018\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010$\u001a\u00020#2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0014J$\u0010\u0018\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010%\u001a\u00020&2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0014J$\u0010\u0018\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010'\u001a\u00020(2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0014J$\u0010\u0018\u001a\u00020\u00192\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\u0006\u0010)\u001a\u00020*2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0014J\u0016\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020\u0014J\u000e\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u000203J\u0011\u00104\u001a\u0002052\u0006\u0010$\u001a\u00020#H\u0096\u0001J\u0011\u00104\u001a\u0002062\u0006\u0010%\u001a\u00020&H\u0096\u0001J\u0011\u00104\u001a\u0002062\u0006\u0010)\u001a\u00020*H\u0096\u0001J\u0011\u00104\u001a\u0002062\u0006\u0010'\u001a\u00020(H\u0096\u0001J\u0011\u00107\u001a\u0002082\u0006\u0010\"\u001a\u00020#H\u0096\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0013\u001a\u00020\u0014*\u0004\u0018\u00010\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R \u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140-0,X\u0082\u0004¢\u0006\u0002\n��¨\u0006;"}, d2 = {"Lcn/sast/api/config/ScanFilter;", "Lcn/sast/api/config/MatchContentProvider;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "matchContentProvider", "<init>", "(Lcn/sast/api/config/MainConfig;Lcn/sast/api/config/MatchContentProvider;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "processRegex", "Lcn/sast/api/config/ProcessRegex;", "getProcessRegex", "()Lcn/sast/api/config/ProcessRegex;", "setProcessRegex", "(Lcn/sast/api/config/ProcessRegex;)V", "update", "", "value", "Lcn/sast/api/config/ProjectConfig;", "show", "", "Lcom/feysh/corax/config/api/rules/ProcessRule$IMatchItem;", "getShow", "(Lcom/feysh/corax/config/api/rules/ProcessRule$IMatchItem;)Ljava/lang/String;", "getActionOf", "Lcom/feysh/corax/config/api/rules/ProcessRule$ScanAction;", "rule", "", "Lcom/feysh/corax/config/api/rules/ProcessRulesType;", "origAction", "matchTarget", "Lcom/feysh/corax/config/api/rules/ProcessRule$IMatchTarget;", "prefix", "getActionOfClassPath", "classpath", "Ljava/nio/file/Path;", "file", "sc", "Lsoot/SootClass;", "sm", "Lsoot/SootMethod;", "sf", "Lsoot/SootField;", "filterDiagnostics", "Ljava/util/concurrent/ConcurrentHashMap;", "", "add", "key", "item", "dump", "dir", "Lcn/sast/common/IResDirectory;", "get", "Lcom/feysh/corax/config/api/rules/ProcessRule$FileMatch$MatchTarget;", "Lcom/feysh/corax/config/api/rules/ProcessRule$ClassMemberMatch$MatchTarget;", "getClassPath", "Lcom/feysh/corax/config/api/rules/ProcessRule$ClassPathMatch$MatchTarget;", "Companion", "ClassFilerRecord", "corax-api"})
@SourceDebugExtension({"SMAP\nScanFilter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ScanFilter.kt\ncn/sast/api/config/ScanFilter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n*L\n1#1,106:1\n1#2:107\n1#2:110\n72#3,2:108\n*S KotlinDebug\n*F\n+ 1 ScanFilter.kt\ncn/sast/api/config/ScanFilter\n*L\n96#1:110\n96#1:108,2\n*E\n"})
/* loaded from: ScanFilter.class */
public final class ScanFilter implements MatchContentProvider {

    @NotNull
    private final MainConfig mainConfig;

    @NotNull
    private final MatchContentProvider matchContentProvider;
    public ProcessRegex processRegex;

    @NotNull
    private final ConcurrentHashMap<String, Set<String>> filterDiagnostics;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final Json jsonFormat = JsonKt.Json$default((Json) null, ScanFilter::jsonFormat$lambda$3, 1, (Object) null);

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.FileMatch.MatchTarget get(@NotNull Path file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return this.matchContentProvider.get(file);
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootField sf) {
        Intrinsics.checkNotNullParameter(sf, "sf");
        return this.matchContentProvider.get(sf);
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootMethod sm) {
        Intrinsics.checkNotNullParameter(sm, "sm");
        return this.matchContentProvider.get(sm);
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.ClassMemberMatch.MatchTarget get(@NotNull SootClass sc) {
        Intrinsics.checkNotNullParameter(sc, "sc");
        return this.matchContentProvider.get(sc);
    }

    @Override // cn.sast.api.config.MatchContentProvider
    @NotNull
    public ProcessRule.ClassPathMatch.MatchTarget getClassPath(@NotNull Path classpath) {
        Intrinsics.checkNotNullParameter(classpath, "classpath");
        return this.matchContentProvider.getClassPath(classpath);
    }

    public ScanFilter(@NotNull MainConfig mainConfig, @NotNull MatchContentProvider matchContentProvider) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(matchContentProvider, "matchContentProvider");
        this.mainConfig = mainConfig;
        this.matchContentProvider = matchContentProvider;
        this.filterDiagnostics = new ConcurrentHashMap<>();
    }

    public /* synthetic */ ScanFilter(MainConfig mainConfig, MatchContentProvider matchContentProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mainConfig, (i & 2) != 0 ? new MatchContentProviderImpl(mainConfig) : matchContentProvider);
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @NotNull
    public final ProcessRegex getProcessRegex() {
        ProcessRegex processRegex = this.processRegex;
        if (processRegex != null) {
            return processRegex;
        }
        Intrinsics.throwUninitializedPropertyAccessException("processRegex");
        return null;
    }

    public final void setProcessRegex(@NotNull ProcessRegex processRegex) {
        Intrinsics.checkNotNullParameter(processRegex, "<set-?>");
        this.processRegex = processRegex;
    }

    public final void update(@NotNull ProjectConfig value) {
        Intrinsics.checkNotNullParameter(value, "value");
        setProcessRegex(value.getProcessRegex());
    }

    private final String getShow(ProcessRule.IMatchItem $this$show) {
        if ($this$show != null) {
            String string = $this$show.toString();
            if (string != null) {
                return string;
            }
        }
        return "{no matched rule}";
    }

    public static /* synthetic */ ProcessRule.ScanAction getActionOf$default(ScanFilter scanFilter, List list, String str, ProcessRule.IMatchTarget iMatchTarget, String str2, int i, Object obj) {
        if ((i & 8) != 0) {
            str2 = null;
        }
        return scanFilter.getActionOf(list, str, iMatchTarget, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0054  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.feysh.corax.config.api.rules.ProcessRule.ScanAction getActionOf(@org.jetbrains.annotations.NotNull java.util.List<? extends com.feysh.corax.config.api.rules.ProcessRule.IMatchItem> r7, @org.jetbrains.annotations.Nullable java.lang.String r8, @org.jetbrains.annotations.NotNull com.feysh.corax.config.api.rules.ProcessRule.IMatchTarget r9, @org.jetbrains.annotations.Nullable java.lang.String r10) {
        /*
            r6 = this;
            r0 = r7
            java.lang.String r1 = "rule"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r9
            java.lang.String r1 = "matchTarget"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            com.feysh.corax.config.api.rules.ProcessRule r0 = com.feysh.corax.config.api.rules.ProcessRule.INSTANCE
            r1 = r7
            r2 = r9
            kotlin.Pair r0 = r0.matches(r1, r2)
            r11 = r0
            r0 = r11
            r12 = r0
            r0 = 0
            r13 = r0
            r0 = r12
            java.lang.Object r0 = r0.component1()
            com.feysh.corax.config.api.rules.ProcessRule$IMatchItem r0 = (com.feysh.corax.config.api.rules.ProcessRule.IMatchItem) r0
            r14 = r0
            r0 = r12
            java.lang.Object r0 = r0.component2()
            com.feysh.corax.config.api.rules.ProcessRule$ScanAction r0 = (com.feysh.corax.config.api.rules.ProcessRule.ScanAction) r0
            r15 = r0
            r0 = r8
            if (r0 == 0) goto L68
            r0 = r6
            r1 = r8
            r2 = r15
            r3 = r6
            r4 = r14
            java.lang.String r3 = r3.getShow(r4)
            java.lang.String r1 = r1 + " -> " + r2 + ". rule= " + r3
            r2 = r14
            r3 = r2
            if (r3 == 0) goto L53
            com.feysh.corax.config.api.rules.ProcessRule$OP r2 = r2.getOp()
            r3 = r2
            if (r3 != 0) goto L56
        L53:
        L54:
            java.lang.String r2 = "Keep"
        L56:
            r3 = r10
            r4 = r3
            if (r4 != 0) goto L5f
        L5d:
            java.lang.String r3 = ""
        L5f:
            r4 = r9
            java.lang.String r2 = r2 + ": " + r3 + " " + r4
            r0.add(r1, r2)
        L68:
            r0 = r11
            java.lang.Object r0 = r0.getSecond()
            com.feysh.corax.config.api.rules.ProcessRule$ScanAction r0 = (com.feysh.corax.config.api.rules.ProcessRule.ScanAction) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.config.ScanFilter.getActionOf(java.util.List, java.lang.String, com.feysh.corax.config.api.rules.ProcessRule$IMatchTarget, java.lang.String):com.feysh.corax.config.api.rules.ProcessRule$ScanAction");
    }

    public static /* synthetic */ ProcessRule.ScanAction getActionOfClassPath$default(ScanFilter scanFilter, String str, Path path, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return scanFilter.getActionOfClassPath(str, path, str2);
    }

    @NotNull
    public final ProcessRule.ScanAction getActionOfClassPath(@Nullable String origAction, @NotNull Path classpath, @Nullable String prefix) {
        Intrinsics.checkNotNullParameter(classpath, "classpath");
        return getActionOf(getProcessRegex().getClasspathRules(), origAction, (ProcessRule.IMatchTarget) getClassPath(classpath), prefix);
    }

    public static /* synthetic */ ProcessRule.ScanAction getActionOf$default(ScanFilter scanFilter, String str, Path path, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return scanFilter.getActionOf(str, path, str2);
    }

    @NotNull
    public final ProcessRule.ScanAction getActionOf(@Nullable String origAction, @NotNull Path file, @Nullable String prefix) {
        Intrinsics.checkNotNullParameter(file, "file");
        return getActionOf(getProcessRegex().getFileRules(), origAction, (ProcessRule.IMatchTarget) get(file), prefix);
    }

    public static /* synthetic */ ProcessRule.ScanAction getActionOf$default(ScanFilter scanFilter, String str, SootClass sootClass, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return scanFilter.getActionOf(str, sootClass, str2);
    }

    @NotNull
    public final ProcessRule.ScanAction getActionOf(@Nullable String origAction, @NotNull SootClass sc, @Nullable String prefix) {
        Intrinsics.checkNotNullParameter(sc, "sc");
        return getActionOf(getProcessRegex().getClazzRules(), origAction, (ProcessRule.IMatchTarget) get(sc), prefix);
    }

    public static /* synthetic */ ProcessRule.ScanAction getActionOf$default(ScanFilter scanFilter, String str, SootMethod sootMethod, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return scanFilter.getActionOf(str, sootMethod, str2);
    }

    @NotNull
    public final ProcessRule.ScanAction getActionOf(@Nullable String origAction, @NotNull SootMethod sm, @Nullable String prefix) {
        Intrinsics.checkNotNullParameter(sm, "sm");
        return getActionOf(getProcessRegex().getClazzRules(), origAction, (ProcessRule.IMatchTarget) get(sm), prefix);
    }

    public static /* synthetic */ ProcessRule.ScanAction getActionOf$default(ScanFilter scanFilter, String str, SootField sootField, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        return scanFilter.getActionOf(str, sootField, str2);
    }

    @NotNull
    public final ProcessRule.ScanAction getActionOf(@Nullable String origAction, @NotNull SootField sf, @Nullable String prefix) {
        Intrinsics.checkNotNullParameter(sf, "sf");
        return getActionOf(getProcessRegex().getClazzRules(), origAction, (ProcessRule.IMatchTarget) get(sf), prefix);
    }

    /* compiled from: ScanFilter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/api/config/ScanFilter$Companion;", "", "<init>", "()V", "jsonFormat", "Lkotlinx/serialization/json/Json;", "corax-api"})
    /* loaded from: ScanFilter$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit jsonFormat$lambda$3(JsonBuilder $this$Json) {
        Intrinsics.checkNotNullParameter($this$Json, "$this$Json");
        $this$Json.setUseArrayPolymorphism(true);
        $this$Json.setPrettyPrint(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ScanFilter.kt */
    @Serializable
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018�� \u00162\u00020\u0001:\u0002\u0015\u0016B!\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007B7\b\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0005\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0006\u0010\fJ%\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020��2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0001¢\u0006\u0002\b\u0014R \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00050\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0017"}, d2 = {"Lcn/sast/api/config/ScanFilter$ClassFilerRecord;", "", "filterDiagnostics", "", "", "", "<init>", "(Ljava/util/Map;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/Map;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_api", "$serializer", "Companion", "corax-api"})
    /* loaded from: ScanFilter$ClassFilerRecord.class */
    static final class ClassFilerRecord {

        @NotNull
        private final Map<String, Set<String>> filterDiagnostics;

        @NotNull
        public static final Companion Companion = new Companion(null);

        @JvmField
        @NotNull
        private static final KSerializer<Object>[] $childSerializers = {new LinkedHashMapSerializer(StringSerializer.INSTANCE, new LinkedHashSetSerializer(StringSerializer.INSTANCE))};

        /* compiled from: ScanFilter.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/api/config/ScanFilter$ClassFilerRecord$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/api/config/ScanFilter$ClassFilerRecord;", "corax-api"})
        /* loaded from: ScanFilter$ClassFilerRecord$Companion.class */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            @NotNull
            public final KSerializer<ClassFilerRecord> serializer() {
                return ScanFilter$ClassFilerRecord$$serializer.INSTANCE;
            }
        }

        public /* synthetic */ ClassFilerRecord(int seen0, Map filterDiagnostics, SerializationConstructorMarker serializationConstructorMarker) {
            if (1 != (1 & seen0)) {
                PluginExceptionsKt.throwMissingFieldException(seen0, 1, ScanFilter$ClassFilerRecord$$serializer.INSTANCE.getDescriptor());
            }
            this.filterDiagnostics = filterDiagnostics;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ClassFilerRecord(@NotNull Map<String, ? extends Set<String>> map) {
            Intrinsics.checkNotNullParameter(map, "filterDiagnostics");
            this.filterDiagnostics = map;
        }
    }

    public final void add(@NotNull String key, @NotNull String item) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(item, "item");
        ConcurrentMap $this$getOrPut$iv = this.filterDiagnostics;
        Set<String> setPutIfAbsent = $this$getOrPut$iv.get(key);
        if (setPutIfAbsent == null) {
            Set<String> setSynchronizedSet = Collections.synchronizedSet(new TreeSet());
            setPutIfAbsent = $this$getOrPut$iv.putIfAbsent(key, setSynchronizedSet);
            if (setPutIfAbsent == null) {
                setPutIfAbsent = setSynchronizedSet;
            }
        }
        setPutIfAbsent.add(item);
    }

    public final void dump(@NotNull IResDirectory dir) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        dir.mkdirs();
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(dir.resolve(ProjectConfig.RECORD_FILE_NAME).getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        OutputStream outputStream = outputStreamNewOutputStream;
        try {
            OutputStream it = outputStream;
            JvmStreamsKt.encodeToStream(jsonFormat, ClassFilerRecord.Companion.serializer(), new ClassFilerRecord(new TreeMap(this.filterDiagnostics)), it);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(outputStream, (Throwable) null);
        } catch (Throwable th) {
            CloseableKt.closeFinally(outputStream, (Throwable) null);
            throw th;
        }
    }
}
