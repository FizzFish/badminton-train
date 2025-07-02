package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.rules.ProcessRule;
import com.feysh.corax.config.api.rules.ProcessRule$ClassMemberMatch$;
import com.feysh.corax.config.api.rules.ProcessRule$ClassPathMatch$;
import com.feysh.corax.config.api.rules.ProcessRule$FileMatch$;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ProjectConfig.kt */
@Serializable
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��T\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018�� +2\u00020\u0001:\u0002*+B7\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0004\b\t\u0010\nBK\b\u0010\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\t\u0010\u000fJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J9\u0010\u001b\u001a\u00020��2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\fHÖ\u0001J\t\u0010 \u001a\u00020!HÖ\u0001J%\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020��2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0001¢\u0006\u0002\b)R\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0013R\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00038\u0006X\u0087\u0004¢\u0006\u000e\n��\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0013¨\u0006,"}, d2 = {"Lcn/sast/api/config/ProcessRegex;", "", "clazzRules", "", "Lcom/feysh/corax/config/api/rules/ProcessRule$ClassMemberMatch;", "classpathRules", "Lcom/feysh/corax/config/api/rules/ProcessRule$ClassPathMatch;", "fileRules", "Lcom/feysh/corax/config/api/rules/ProcessRule$FileMatch;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/util/List;Ljava/util/List;Ljava/util/List;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getClazzRules$annotations", "()V", "getClazzRules", "()Ljava/util/List;", "getClasspathRules$annotations", "getClasspathRules", "getFileRules$annotations", "getFileRules", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$corax_api", "$serializer", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nProjectConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ProjectConfig.kt\ncn/sast/api/config/ProcessRegex\n+ 2 ProcessRule.kt\ncom/feysh/corax/config/api/rules/ProcessRule\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,45:1\n27#2:46\n24#2:55\n27#2:56\n24#2:64\n27#2:65\n24#2:73\n27#2:74\n11165#3:47\n11500#3,3:48\n11165#3:57\n11500#3,3:58\n11500#3,3:61\n11165#3:66\n11500#3,3:67\n11500#3,3:70\n11165#3:75\n11500#3,3:76\n1557#4:51\n1628#4,3:52\n*S KotlinDebug\n*F\n+ 1 ProjectConfig.kt\ncn/sast/api/config/ProcessRegex\n*L\n18#1:46\n20#1:55\n22#1:56\n20#1:64\n22#1:65\n20#1:73\n22#1:74\n18#1:47\n18#1:48,3\n22#1:57\n22#1:58,3\n18#1:61,3\n22#1:66\n22#1:67,3\n18#1:70,3\n22#1:75\n22#1:76,3\n20#1:51\n20#1:52,3\n*E\n"})
/* loaded from: ProcessRegex.class */
public final class ProcessRegex {

    @NotNull
    private final List<ProcessRule.ClassMemberMatch> clazzRules;

    @NotNull
    private final List<ProcessRule.ClassPathMatch> classpathRules;

    @NotNull
    private final List<ProcessRule.FileMatch> fileRules;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @JvmField
    @NotNull
    private static final KSerializer<Object>[] $childSerializers = {new ArrayListSerializer(ProcessRule$ClassMemberMatch$.serializer.INSTANCE), new ArrayListSerializer(ProcessRule$ClassPathMatch$.serializer.INSTANCE), new ArrayListSerializer(ProcessRule$FileMatch$.serializer.INSTANCE)};

    @SerialName("class")
    public static /* synthetic */ void getClazzRules$annotations() {
    }

    @SerialName("classpath")
    public static /* synthetic */ void getClasspathRules$annotations() {
    }

    @SerialName("file")
    public static /* synthetic */ void getFileRules$annotations() {
    }

    @NotNull
    public final List<ProcessRule.ClassMemberMatch> component1() {
        return this.clazzRules;
    }

    @NotNull
    public final List<ProcessRule.ClassPathMatch> component2() {
        return this.classpathRules;
    }

    @NotNull
    public final List<ProcessRule.FileMatch> component3() {
        return this.fileRules;
    }

    @NotNull
    public final ProcessRegex copy(@NotNull List<ProcessRule.ClassMemberMatch> list, @NotNull List<ProcessRule.ClassPathMatch> list2, @NotNull List<ProcessRule.FileMatch> list3) {
        Intrinsics.checkNotNullParameter(list, "clazzRules");
        Intrinsics.checkNotNullParameter(list2, "classpathRules");
        Intrinsics.checkNotNullParameter(list3, "fileRules");
        return new ProcessRegex(list, list2, list3);
    }

    public static /* synthetic */ ProcessRegex copy$default(ProcessRegex processRegex, List list, List list2, List list3, int i, Object obj) {
        if ((i & 1) != 0) {
            list = processRegex.clazzRules;
        }
        if ((i & 2) != 0) {
            list2 = processRegex.classpathRules;
        }
        if ((i & 4) != 0) {
            list3 = processRegex.fileRules;
        }
        return processRegex.copy(list, list2, list3);
    }

    @NotNull
    public String toString() {
        return "ProcessRegex(clazzRules=" + this.clazzRules + ", classpathRules=" + this.classpathRules + ", fileRules=" + this.fileRules + ")";
    }

    public int hashCode() {
        int result = this.clazzRules.hashCode();
        return (((result * 31) + this.classpathRules.hashCode()) * 31) + this.fileRules.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ProcessRegex)) {
            return false;
        }
        ProcessRegex processRegex = (ProcessRegex) other;
        return Intrinsics.areEqual(this.clazzRules, processRegex.clazzRules) && Intrinsics.areEqual(this.classpathRules, processRegex.classpathRules) && Intrinsics.areEqual(this.fileRules, processRegex.fileRules);
    }

    public ProcessRegex() {
        this((List) null, (List) null, (List) null, 7, (DefaultConstructorMarker) null);
    }

    /* compiled from: ProjectConfig.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0016\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcn/sast/api/config/ProcessRegex$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcn/sast/api/config/ProcessRegex;", "corax-api"})
    /* loaded from: ProcessRegex$Companion.class */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final KSerializer<ProcessRegex> serializer() {
            return ProcessRegex$$serializer.INSTANCE;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$corax_api(ProcessRegex self, CompositeEncoder output, SerialDescriptor serialDesc) {
        boolean z;
        boolean z2;
        boolean z3;
        SerializationStrategy[] serializationStrategyArr = $childSerializers;
        if (output.shouldEncodeElementDefault(serialDesc, 0)) {
            z = true;
        } else {
            List<ProcessRule.ClassMemberMatch> list = self.clazzRules;
            ProcessRule processRule = ProcessRule.INSTANCE;
            String[] ruleItems$iv = new String[0];
            Collection destination$iv$iv$iv = new ArrayList(ruleItems$iv.length);
            for (String str : ruleItems$iv) {
                destination$iv$iv$iv.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.ClassMemberMatch.class)), str));
            }
            z = !Intrinsics.areEqual(list, (List) destination$iv$iv$iv);
        }
        if (z) {
            output.encodeSerializableElement(serialDesc, 0, serializationStrategyArr[0], self.clazzRules);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1)) {
            z2 = true;
        } else {
            List<ProcessRule.ClassPathMatch> list2 = self.classpathRules;
            ProcessRule processRule2 = ProcessRule.INSTANCE;
            Iterable $this$map$iv = MainConfig.Companion.getExcludeFiles();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it = (String) item$iv$iv;
                destination$iv$iv.add("(-)path=" + it);
            }
            Iterable $this$map$iv$iv = (List) destination$iv$iv;
            Collection destination$iv$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
            for (Object item$iv$iv$iv : $this$map$iv$iv) {
                String it$iv = (String) item$iv$iv$iv;
                destination$iv$iv$iv2.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.ClassPathMatch.class)), it$iv));
            }
            z2 = !Intrinsics.areEqual(list2, (List) destination$iv$iv$iv2);
        }
        if (z2) {
            output.encodeSerializableElement(serialDesc, 1, serializationStrategyArr[1], self.classpathRules);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2)) {
            z3 = true;
        } else {
            List<ProcessRule.FileMatch> list3 = self.fileRules;
            ProcessRule processRule3 = ProcessRule.INSTANCE;
            String[] ruleItems$iv2 = new String[0];
            Collection destination$iv$iv$iv3 = new ArrayList(ruleItems$iv2.length);
            for (String str2 : ruleItems$iv2) {
                destination$iv$iv$iv3.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.FileMatch.class)), str2));
            }
            z3 = !Intrinsics.areEqual(list3, (List) destination$iv$iv$iv3);
        }
        if (z3) {
            output.encodeSerializableElement(serialDesc, 2, serializationStrategyArr[2], self.fileRules);
        }
    }

    public /* synthetic */ ProcessRegex(int seen0, List clazzRules, List classpathRules, List fileRules, SerializationConstructorMarker serializationConstructorMarker) {
        if ((0 & seen0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 0, ProcessRegex$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen0 & 1) == 0) {
            ProcessRule processRule = ProcessRule.INSTANCE;
            String[] ruleItems$iv = new String[0];
            Collection destination$iv$iv$iv = new ArrayList(ruleItems$iv.length);
            for (String str : ruleItems$iv) {
                destination$iv$iv$iv.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.ClassMemberMatch.class)), str));
            }
            this.clazzRules = (List) destination$iv$iv$iv;
        } else {
            this.clazzRules = clazzRules;
        }
        if ((seen0 & 2) == 0) {
            ProcessRule processRule2 = ProcessRule.INSTANCE;
            Iterable $this$map$iv = MainConfig.Companion.getExcludeFiles();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it = (String) item$iv$iv;
                destination$iv$iv.add("(-)path=" + it);
            }
            Iterable $this$map$iv$iv = (List) destination$iv$iv;
            Collection destination$iv$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
            for (Object item$iv$iv$iv : $this$map$iv$iv) {
                String it$iv = (String) item$iv$iv$iv;
                destination$iv$iv$iv2.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.ClassPathMatch.class)), it$iv));
            }
            this.classpathRules = (List) destination$iv$iv$iv2;
        } else {
            this.classpathRules = classpathRules;
        }
        if ((seen0 & 4) == 0) {
            ProcessRule processRule3 = ProcessRule.INSTANCE;
            String[] ruleItems$iv2 = new String[0];
            Collection destination$iv$iv$iv3 = new ArrayList(ruleItems$iv2.length);
            for (String str2 : ruleItems$iv2) {
                destination$iv$iv$iv3.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.FileMatch.class)), str2));
            }
            this.fileRules = (List) destination$iv$iv$iv3;
        } else {
            this.fileRules = fileRules;
        }
        ProjectConfigKt.validate(this.clazzRules);
        ProjectConfigKt.validate(this.classpathRules);
        ProjectConfigKt.validate(this.fileRules);
    }

    public ProcessRegex(@NotNull List<ProcessRule.ClassMemberMatch> list, @NotNull List<ProcessRule.ClassPathMatch> list2, @NotNull List<ProcessRule.FileMatch> list3) {
        Intrinsics.checkNotNullParameter(list, "clazzRules");
        Intrinsics.checkNotNullParameter(list2, "classpathRules");
        Intrinsics.checkNotNullParameter(list3, "fileRules");
        this.clazzRules = list;
        this.classpathRules = list2;
        this.fileRules = list3;
        ProjectConfigKt.validate(this.clazzRules);
        ProjectConfigKt.validate(this.classpathRules);
        ProjectConfigKt.validate(this.fileRules);
    }

    public /* synthetic */ ProcessRegex(List list, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            ProcessRule processRule = ProcessRule.INSTANCE;
            String[] ruleItems$iv = new String[0];
            Collection destination$iv$iv$iv = new ArrayList(ruleItems$iv.length);
            for (String str : ruleItems$iv) {
                destination$iv$iv$iv.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.ClassMemberMatch.class)), str));
            }
            list = (List) destination$iv$iv$iv;
        }
        if ((i & 2) != 0) {
            ProcessRule processRule2 = ProcessRule.INSTANCE;
            Iterable $this$map$iv = MainConfig.Companion.getExcludeFiles();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it = (String) item$iv$iv;
                destination$iv$iv.add("(-)path=" + it);
            }
            Iterable $this$map$iv$iv = (List) destination$iv$iv;
            Collection destination$iv$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv$iv, 10));
            for (Object item$iv$iv$iv : $this$map$iv$iv) {
                String it$iv = (String) item$iv$iv$iv;
                destination$iv$iv$iv2.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.ClassPathMatch.class)), it$iv));
            }
            list2 = (List) destination$iv$iv$iv2;
        }
        if ((i & 4) != 0) {
            ProcessRule processRule3 = ProcessRule.INSTANCE;
            String[] ruleItems$iv2 = new String[0];
            Collection destination$iv$iv$iv3 = new ArrayList(ruleItems$iv2.length);
            for (String str2 : ruleItems$iv2) {
                destination$iv$iv$iv3.add(ProcessRule.InlineRuleStringSerialize.INSTANCE.deserializeMatchFromLineString(SerializersKt.serializer(Reflection.getOrCreateKotlinClass(ProcessRule.FileMatch.class)), str2));
            }
            list3 = (List) destination$iv$iv$iv3;
        }
        this(list, list2, list3);
    }

    @NotNull
    public final List<ProcessRule.ClassMemberMatch> getClazzRules() {
        return this.clazzRules;
    }

    @NotNull
    public final List<ProcessRule.ClassPathMatch> getClasspathRules() {
        return this.classpathRules;
    }

    @NotNull
    public final List<ProcessRule.FileMatch> getFileRules() {
        return this.fileRules;
    }
}
