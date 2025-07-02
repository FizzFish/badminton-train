package cn.sast.cli.command.tools;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.plugin.ConfigPluginLoader;
import cn.sast.framework.plugin.PluginDefinitions;
import com.feysh.corax.config.api.CheckType;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.parameters.groups.OptionGroup;
import com.github.ajalt.clikt.parameters.options.FlagOptionKt;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import com.github.ajalt.clikt.parameters.types.BooleanKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.StringSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: SubToolsOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\u0018�� \u00172\u00020\u0001:\u0001\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001b\u0010\r\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\u000e\u0010\u0007¨\u0006\u0018²\u0006\n\u0010\u0019\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u008a\u0084\u0002"}, d2 = {"Lcn/sast/cli/command/tools/SubToolsOptions;", "Lcom/github/ajalt/clikt/parameters/groups/OptionGroup;", "<init>", "()V", "subtools", "", "getSubtools", "()Z", "subtools$delegate", "Lkotlin/properties/ReadOnlyProperty;", "listRules", "getListRules", "listRules$delegate", "listCheckTypes", "getListCheckTypes", "listCheckTypes$delegate", "run", "", "pl", "Lcn/sast/framework/plugin/ConfigPluginLoader;", "rules", "", "", "Companion", "corax-cli", "pluginDefinitions", "Lcn/sast/framework/plugin/PluginDefinitions;", "checkTypes", "", "Lcom/feysh/corax/config/api/CheckType;"})
@SourceDebugExtension({"SMAP\nSubToolsOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SubToolsOptions.kt\ncn/sast/cli/command/tools/SubToolsOptions\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 SerialFormat.kt\nkotlinx/serialization/SerialFormatKt\n*L\n1#1,56:1\n1557#2:57\n1628#2,3:58\n1557#2:62\n1628#2,3:63\n1557#2:67\n1628#2,3:68\n1053#2:71\n113#3:61\n113#3:66\n*S KotlinDebug\n*F\n+ 1 SubToolsOptions.kt\ncn/sast/cli/command/tools/SubToolsOptions\n*L\n39#1:57\n39#1:58,3\n42#1:62\n42#1:63,3\n36#1:67\n36#1:68,3\n36#1:71\n39#1:61\n42#1:66\n*E\n"})
/* loaded from: SubToolsOptions.class */
public final class SubToolsOptions extends OptionGroup {

    @NotNull
    private final ReadOnlyProperty subtools$delegate;

    @NotNull
    private final ReadOnlyProperty listRules$delegate;

    @NotNull
    private final ReadOnlyProperty listCheckTypes$delegate;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(SubToolsOptions.class, "subtools", "getSubtools()Z", 0)), Reflection.property1(new PropertyReference1Impl(SubToolsOptions.class, "listRules", "getListRules()Z", 0)), Reflection.property1(new PropertyReference1Impl(SubToolsOptions.class, "listCheckTypes", "getListCheckTypes()Z", 0))};

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(SubToolsOptions::logger$lambda$8);

    @NotNull
    private static final Json jsonFormat = JsonKt.Json$default((Json) null, SubToolsOptions::jsonFormat$lambda$9, 1, (Object) null);

    public SubToolsOptions() {
        super("Sub tools Options", (String) null, 2, (DefaultConstructorMarker) null);
        this.subtools$delegate = OptionWithValuesKt.required(BooleanKt.boolean(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"--subtools"}, "subtools", "", false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 504, (Object) null))).provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.listRules$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"--list-rules"}, "Print all the rules", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
        this.listCheckTypes$delegate = FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"--list-check-types"}, "Print all the rules", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), new String[0], false, (String) null, 6, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[2]);
    }

    private final boolean getSubtools() {
        return ((Boolean) this.subtools$delegate.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    private final boolean getListRules() {
        return ((Boolean) this.listRules$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    private final boolean getListCheckTypes() {
        return ((Boolean) this.listCheckTypes$delegate.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    public final void run(@NotNull ConfigPluginLoader pl, @Nullable Set<String> rules) {
        Intrinsics.checkNotNullParameter(pl, "pl");
        if (getSubtools()) {
            Lazy pluginDefinitions$delegate = LazyKt.lazy(() -> {
                return run$lambda$0(r0);
            });
            Lazy checkTypes$delegate = LazyKt.lazy(() -> {
                return run$lambda$4(r0);
            });
            if (getListRules()) {
                StringFormat $this$encodeToString$iv = jsonFormat;
                Iterable $this$map$iv = run$lambda$5(checkTypes$delegate);
                Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                for (Object item$iv$iv : $this$map$iv) {
                    CheckType it = (CheckType) item$iv$iv;
                    destination$iv$iv.add(CheckerInfoGeneratorKt.getId(it));
                }
                Object value$iv = (List) destination$iv$iv;
                $this$encodeToString$iv.getSerializersModule();
                System.out.println((Object) $this$encodeToString$iv.encodeToString(new ArrayListSerializer(StringSerializer.INSTANCE), value$iv));
            }
            if (getListCheckTypes()) {
                StringFormat $this$encodeToString$iv2 = jsonFormat;
                Iterable $this$map$iv2 = run$lambda$5(checkTypes$delegate);
                Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                for (Object item$iv$iv2 : $this$map$iv2) {
                    CheckType it2 = (CheckType) item$iv$iv2;
                    destination$iv$iv2.add(it2.toString());
                }
                Object value$iv2 = (List) destination$iv$iv2;
                $this$encodeToString$iv2.getSerializersModule();
                System.out.println((Object) $this$encodeToString$iv2.encodeToString(new ArrayListSerializer(StringSerializer.INSTANCE), value$iv2));
            }
        }
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    private static final PluginDefinitions run$lambda$1(Lazy<PluginDefinitions> $pluginDefinitions$delegate) {
        return (PluginDefinitions) $pluginDefinitions$delegate.getValue();
    }

    private static final PluginDefinitions run$lambda$0(ConfigPluginLoader $pl) {
        return PluginDefinitions.Companion.load($pl.getPluginManager());
    }

    private static final List<CheckType> run$lambda$5(Lazy<? extends List<? extends CheckType>> $checkTypes$delegate) {
        return (List) $checkTypes$delegate.getValue();
    }

    private static final List run$lambda$4(Lazy $pluginDefinitions$delegate) {
        Iterable $this$map$iv = run$lambda$1($pluginDefinitions$delegate).getCheckTypeDefinition(CheckType.class);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            PluginDefinitions.CheckTypeDefinition it = (PluginDefinitions.CheckTypeDefinition) item$iv$iv;
            destination$iv$iv.add(it.getSingleton());
        }
        Iterable $this$sortedBy$iv = (List) destination$iv$iv;
        return CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: cn.sast.cli.command.tools.SubToolsOptions$run$lambda$4$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                CheckType it2 = (CheckType) t;
                CheckType it3 = (CheckType) t2;
                return ComparisonsKt.compareValues(CheckerInfoGeneratorKt.getId(it2), CheckerInfoGeneratorKt.getId(it3));
            }
        });
    }

    /* compiled from: SubToolsOptions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"Lcn/sast/cli/command/tools/SubToolsOptions$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "jsonFormat", "Lkotlinx/serialization/json/Json;", "corax-cli"})
    /* loaded from: SubToolsOptions$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$8() {
        return Unit.INSTANCE;
    }

    private static final Unit jsonFormat$lambda$9(JsonBuilder $this$Json) {
        Intrinsics.checkNotNullParameter($this$Json, "$this$Json");
        $this$Json.setUseArrayPolymorphism(true);
        $this$Json.setPrettyPrint(true);
        return Unit.INSTANCE;
    }
}
