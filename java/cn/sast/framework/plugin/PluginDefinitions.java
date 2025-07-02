package cn.sast.framework.plugin;

import cn.sast.api.AnalyzerEnv;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.plugin.CheckersConfig;
import cn.sast.framework.plugin.ConfigPluginLoader;
import com.feysh.corax.config.api.AIAnalysisUnit;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.CheckerUnit;
import com.feysh.corax.config.api.ISootInitializeHandler;
import com.feysh.corax.config.api.ITaintType;
import com.feysh.corax.config.api.PreAnalysisUnit;
import com.feysh.corax.config.api.SAOptions;
import com.feysh.corax.config.api.utils.UtilsKt;
import com.feysh.corax.config.builtin.checkers.DeadCodeChecker;
import com.feysh.corax.config.builtin.checkers.DeadStoreChecker;
import com.feysh.corax.config.builtin.checkers.DefineUnusedChecker;
import com.feysh.corax.config.builtin.soot.DefaultSootConfiguration;
import com.feysh.corax.config.builtin.soot.EmptySootConfiguration;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.PolymorphicModuleBuilder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuilder;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.pf4j.PluginWrapper;
import org.reflections.Reflections;
import org.utbot.common.ReflectionUtilKt;
import soot.G;

/* compiled from: PluginDefinitions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��v\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018�� ,2\u00020\u0001:\u0005()*+,B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0007¢\u0006\u0002\b\u0012J+\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0005j\b\u0012\u0004\u0012\u00020\u0013`\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00140\u0010H\u0007¢\u0006\u0002\b\u0015J+\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0005j\b\u0012\u0004\u0012\u00020\u0016`\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00170\u0010H\u0007¢\u0006\u0002\b\u0018J+\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0005j\b\u0012\u0004\u0012\u00020\u0016`\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00190\u0010H\u0007¢\u0006\u0002\b\u001aJ+\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0005j\b\u0012\u0004\u0012\u00020\u0016`\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0010H\u0007¢\u0006\u0002\b\u001cJ\u0016\u0010\u001d\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u001f0\u001eJ(\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\n2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u00102\n\u0010$\u001a\u0006\u0012\u0002\b\u00030\u0010H\u0002J&\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\n2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u00102\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u0002J&\u0010 \u001a\u00020!2\u0006\u0010&\u001a\u00020'2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030\u00102\n\u0010$\u001a\u0006\u0012\u0002\b\u00030\u0010R&\u0010\u0004\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007X\u0082\u000e¢\u0006\u0002\n��R2\u0010\b\u001a&\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\tj\u0012\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u000bX\u0082\u000e¢\u0006\u0002\n��R2\u0010\f\u001a&\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\n0\tj\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\n`\u000bX\u0082\u000e¢\u0006\u0002\n��¨\u0006-"}, d2 = {"Lcn/sast/framework/plugin/PluginDefinitions;", "", "<init>", "()V", "definitions", "Ljava/util/LinkedHashSet;", "Lcn/sast/framework/plugin/PluginDefinitions$Definition;", "Lkotlin/collections/LinkedHashSet;", "identifiers", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "identifiersReverse", "get", "Lcn/sast/framework/plugin/PluginDefinitions$ISootInitializeHandlerDefinition;", "clz", "Ljava/lang/Class;", "Lcom/feysh/corax/config/api/ISootInitializeHandler;", "getISootInitializeHandlerDefinition", "Lcn/sast/framework/plugin/PluginDefinitions$CheckTypeDefinition;", "Lcom/feysh/corax/config/api/CheckType;", "getCheckTypeDefinition", "Lcn/sast/framework/plugin/PluginDefinitions$CheckerUnitDefinition;", "Lcom/feysh/corax/config/api/CheckerUnit;", "getCheckerUnitDefinition", "Lcom/feysh/corax/config/api/PreAnalysisUnit;", "getPreAnalysisUnit", "Lcom/feysh/corax/config/api/AIAnalysisUnit;", "getAIAnalysisUnit", "getDefaultConfigs", "", "Lcn/sast/framework/plugin/IConfig;", "register", "", "prefix", "type", "definition", "singleton", "plugin", "Lorg/pf4j/PluginWrapper;", "Definition", "CheckerUnitDefinition", "ISootInitializeHandlerDefinition", "CheckTypeDefinition", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nPluginDefinitions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginDefinitions.kt\ncn/sast/framework/plugin/PluginDefinitions\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,302:1\n817#2,2:303\n817#2,2:305\n817#2,2:307\n865#2,2:309\n865#2,2:311\n1279#2,2:313\n1293#2,4:315\n1#3:319\n*S KotlinDebug\n*F\n+ 1 PluginDefinitions.kt\ncn/sast/framework/plugin/PluginDefinitions\n*L\n152#1:303,2\n157#1:305,2\n162#1:307,2\n168#1:309,2\n174#1:311,2\n178#1:313,2\n178#1:315,4\n*E\n"})
/* loaded from: PluginDefinitions.class */
public final class PluginDefinitions {

    @NotNull
    private LinkedHashSet<Definition<?>> definitions = new LinkedHashSet<>();

    @NotNull
    private LinkedHashMap<String, Definition<?>> identifiers = new LinkedHashMap<>();

    @NotNull
    private LinkedHashMap<Definition<?>, String> identifiersReverse = new LinkedHashMap<>();

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(PluginDefinitions::logger$lambda$5);

    @NotNull
    private static final Set<String> commercialKeywords = SetsKt.setOf(new String[]{"commercial", "business"});

    public PluginDefinitions() {
        register("builtin", ISootInitializeHandler.class, DefaultSootConfiguration.class);
        register("builtin", ISootInitializeHandler.class, EmptySootConfiguration.class);
        register("builtin", CheckType.class, DefineUnusedChecker.UrfUnreadField.INSTANCE);
        register("builtin", CheckType.class, DefineUnusedChecker.UnusedMethod.INSTANCE);
        register("builtin", CheckType.class, DeadCodeChecker.DeadCode.INSTANCE);
        register("builtin", CheckType.class, DeadCodeChecker.UnreachableBranch.INSTANCE);
        register("builtin", CheckType.class, DeadStoreChecker.DeadLocalStore.INSTANCE);
    }

    /* compiled from: PluginDefinitions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��X\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\b6\u0018�� '*\b\b��\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001'B5\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00028��\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u001aJ\u001f\u0010\u001f\u001a\u00020 2\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\t\u0012\u00070\u001a¢\u0006\u0002\b\"0\u0015R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0007\u001a\u00028��X\u0096\u0004¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n��R%\u0010\u0014\u001a\u0016\u0012\f\u0012\n \u0016*\u0004\u0018\u00010\u00040\u0004\u0012\u0004\u0012\u00020\n0\u00158F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001a0\u00158F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0018R\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u001a8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0012\u0010#\u001a\u00020$X¦\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0001\u0003()*¨\u0006+"}, d2 = {"Lcn/sast/framework/plugin/PluginDefinitions$Definition;", "T", "", "name", "", "type", "Ljava/lang/Class;", "singleton", "optionFields", "", "Ljava/lang/reflect/Field;", "<init>", "(Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Object;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getType", "()Ljava/lang/Class;", "getSingleton", "()Ljava/lang/Object;", "Ljava/lang/Object;", "fieldName2Field", "", "kotlin.jvm.PlatformType", "getFieldName2Field", "()Ljava/util/Map;", "optionNameMap", "Lcom/feysh/corax/config/api/SAOptions;", "getOptionNameMap", "option", "getOption", "()Lcom/feysh/corax/config/api/SAOptions;", "setOptions", "", "options", "Lkotlinx/serialization/Polymorphic;", "defaultConfig", "Lcn/sast/framework/plugin/IConfig;", "getDefaultConfig", "()Lcn/sast/framework/plugin/IConfig;", "Companion", "Lcn/sast/framework/plugin/PluginDefinitions$CheckTypeDefinition;", "Lcn/sast/framework/plugin/PluginDefinitions$CheckerUnitDefinition;", "Lcn/sast/framework/plugin/PluginDefinitions$ISootInitializeHandlerDefinition;", "corax-framework"})
    @SourceDebugExtension({"SMAP\nPluginDefinitions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginDefinitions.kt\ncn/sast/framework/plugin/PluginDefinitions$Definition\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,302:1\n1202#2,2:303\n1230#2,4:305\n1611#2,9:309\n1863#2:318\n1864#2:320\n1620#2:321\n1#3:319\n*S KotlinDebug\n*F\n+ 1 PluginDefinitions.kt\ncn/sast/framework/plugin/PluginDefinitions$Definition\n*L\n34#1:303,2\n34#1:305,4\n37#1:309,9\n37#1:318\n37#1:320\n37#1:321\n37#1:319\n*E\n"})
    /* loaded from: PluginDefinitions$Definition.class */
    public static abstract class Definition<T> {

        @NotNull
        private final String name;

        @NotNull
        private final Class<?> type;

        @NotNull
        private final T singleton;

        @NotNull
        private final List<Field> optionFields;

        @NotNull
        public static final Companion Companion = new Companion(null);

        @NotNull
        private static final Set<Class<? extends Object>> definitions = SetsKt.setOf(new Class[]{PreAnalysisUnit.class, AIAnalysisUnit.class, ISootInitializeHandler.class, CheckType.class});

        @NotNull
        public abstract IConfig getDefaultConfig();

        public /* synthetic */ Definition(String name, Class type, Object singleton, List optionFields, DefaultConstructorMarker $constructor_marker) {
            this(name, type, singleton, optionFields);
        }

        private Definition(String name, Class<?> cls, T t, List<Field> list) {
            this.name = name;
            this.type = cls;
            this.singleton = t;
            this.optionFields = list;
        }

        public /* synthetic */ Definition(String str, Class cls, Object obj, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, cls, obj, (i & 8) != 0 ? Companion.optionFieldsOf(obj) : list, null);
        }

        @NotNull
        public String getName() {
            return this.name;
        }

        @NotNull
        public Class<?> getType() {
            return this.type;
        }

        @NotNull
        public T getSingleton() {
            return this.singleton;
        }

        @NotNull
        public final Map<String, Field> getFieldName2Field() {
            Iterable $this$associateBy$iv = this.optionFields;
            int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateBy$iv, 10)), 16);
            Map destination$iv$iv = new LinkedHashMap(capacity$iv);
            for (Object element$iv$iv : $this$associateBy$iv) {
                Field it = (Field) element$iv$iv;
                destination$iv$iv.put(it.getName(), element$iv$iv);
            }
            return destination$iv$iv;
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x00ab A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x002a A[SYNTHETIC] */
        @org.jetbrains.annotations.NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.Map<java.lang.String, com.feysh.corax.config.api.SAOptions> getOptionNameMap() throws java.lang.IllegalAccessException, java.lang.IllegalArgumentException {
            /*
                r4 = this;
                r0 = r4
                java.util.List<java.lang.reflect.Field> r0 = r0.optionFields
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                r5 = r0
                r0 = 0
                r6 = r0
                r0 = r5
                r7 = r0
                java.util.ArrayList r0 = new java.util.ArrayList
                r1 = r0
                r1.<init>()
                java.util.Collection r0 = (java.util.Collection) r0
                r8 = r0
                r0 = 0
                r9 = r0
                r0 = r7
                r10 = r0
                r0 = 0
                r11 = r0
                r0 = r10
                java.util.Iterator r0 = r0.iterator()
                r12 = r0
            L2a:
                r0 = r12
                boolean r0 = r0.hasNext()
                if (r0 == 0) goto Lc2
                r0 = r12
                java.lang.Object r0 = r0.next()
                r13 = r0
                r0 = r13
                r14 = r0
                r0 = 0
                r15 = r0
                r0 = r14
                java.lang.reflect.Field r0 = (java.lang.reflect.Field) r0
                r16 = r0
                r0 = 0
                r17 = r0
                r0 = r16
                r1 = 1
                r0.setAccessible(r1)     // Catch: java.lang.Exception -> L8c
                r0 = r16
                java.lang.String r0 = r0.getName()     // Catch: java.lang.Exception -> L8c
                r1 = r16
                r2 = r4
                java.lang.Object r2 = r2.getSingleton()     // Catch: java.lang.Exception -> L8c
                java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Exception -> L8c
                r18 = r1
                r1 = r18
                boolean r1 = r1 instanceof com.feysh.corax.config.api.SAOptions     // Catch: java.lang.Exception -> L8c
                if (r1 == 0) goto L75
                r1 = r18
                com.feysh.corax.config.api.SAOptions r1 = (com.feysh.corax.config.api.SAOptions) r1     // Catch: java.lang.Exception -> L8c
                goto L76
            L75:
                r1 = 0
            L76:
                r2 = r1
                if (r2 != 0) goto L84
            L7b:
                r1 = 0
                r19 = r1
                r0 = r19
                goto La7
            L84:
                kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r1)     // Catch: java.lang.Exception -> L8c
                r20 = r0
                goto La4
            L8c:
                r18 = move-exception
                mu.KLogger r0 = cn.sast.framework.plugin.PluginDefinitions.access$getLogger$cp()
                r1 = r4
                java.lang.String r1 = "failed to get option Field of " + r1
                r2 = r18
                java.lang.Throwable r2 = (java.lang.Throwable) r2
                r0.error(r1, r2)
                r0 = 0
                r20 = r0
            La4:
                r0 = r20
            La7:
                r1 = r0
                if (r1 == 0) goto Lbd
                r21 = r0
                r0 = 0
                r22 = r0
                r0 = r8
                r1 = r21
                boolean r0 = r0.add(r1)
                goto Lbe
            Lbd:
            Lbe:
                goto L2a
            Lc2:
                r0 = r8
                java.util.List r0 = (java.util.List) r0
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.Map r0 = kotlin.collections.MapsKt.toMap(r0)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.plugin.PluginDefinitions.Definition.getOptionNameMap():java.util.Map");
        }

        @Nullable
        public final SAOptions getOption() {
            SAOptions sAOptions = getOptionNameMap().get("options");
            return sAOptions == null ? getOptionNameMap().get("option") : sAOptions;
        }

        public final void setOptions(@NotNull SAOptions option) throws IllegalAccessException, IllegalArgumentException {
            Intrinsics.checkNotNullParameter(option, "option");
            Field field = getFieldName2Field().get("options");
            if (field == null) {
                field = getFieldName2Field().get("option");
                if (field == null) {
                    return;
                }
            }
            Field field2 = field;
            field2.setAccessible(true);
            field2.set(getSingleton(), option);
        }

        public final void setOptions(@NotNull Map<String, ? extends SAOptions> map) throws IllegalAccessException, IllegalArgumentException {
            Intrinsics.checkNotNullParameter(map, "options");
            for (Map.Entry<String, ? extends SAOptions> entry : map.entrySet()) {
                String fieldName = entry.getKey();
                SAOptions option = entry.getValue();
                Field field = getFieldName2Field().get(fieldName);
                if (field != null) {
                    field.setAccessible(true);
                    field.set(getSingleton(), option);
                } else {
                    PluginDefinitions.logger.error(() -> {
                        return setOptions$lambda$2(r1);
                    });
                }
            }
        }

        private static final Object setOptions$lambda$2(Definition this$0) {
            return "error fieldName2Field: " + this$0.getFieldName2Field();
        }

        /* compiled from: PluginDefinitions.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000e\u001a\u00020\u0001H\u0086\u0002J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u000e\u001a\u00020\u0001R\u001f\u0010\u0004\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcn/sast/framework/plugin/PluginDefinitions$Definition$Companion;", "", "<init>", "()V", "definitions", "", "Ljava/lang/Class;", "getDefinitions", "()Ljava/util/Set;", "invoke", "Lcn/sast/framework/plugin/PluginDefinitions$Definition;", "prefix", "", "type", "singleton", "optionFieldsOf", "", "Ljava/lang/reflect/Field;", "corax-framework"})
        @SourceDebugExtension({"SMAP\nPluginDefinitions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginDefinitions.kt\ncn/sast/framework/plugin/PluginDefinitions$Definition$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,302:1\n3829#2:303\n4344#2,2:304\n*S KotlinDebug\n*F\n+ 1 PluginDefinitions.kt\ncn/sast/framework/plugin/PluginDefinitions$Definition$Companion\n*L\n97#1:303\n97#1:304,2\n*E\n"})
        /* loaded from: PluginDefinitions$Definition$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @NotNull
            public final Set<Class<? extends Object>> getDefinitions() {
                return Definition.definitions;
            }

            @NotNull
            public final Definition<?> invoke(@NotNull String prefix, @NotNull Class<?> cls, @NotNull Object singleton) {
                Intrinsics.checkNotNullParameter(prefix, "prefix");
                Intrinsics.checkNotNullParameter(cls, "type");
                Intrinsics.checkNotNullParameter(singleton, "singleton");
                if (Intrinsics.areEqual(cls, PreAnalysisUnit.class) || Intrinsics.areEqual(cls, AIAnalysisUnit.class)) {
                    String name = prefix + ":" + UtilsKt.getSootTypeName(singleton.getClass());
                    return new CheckerUnitDefinition(name, cls, (CheckerUnit) singleton);
                }
                if (Intrinsics.areEqual(cls, ISootInitializeHandler.class)) {
                    String name2 = prefix + ":" + UtilsKt.getSootTypeName(singleton.getClass());
                    return new ISootInitializeHandlerDefinition(name2, cls, (ISootInitializeHandler) singleton);
                }
                if (Intrinsics.areEqual(cls, CheckType.class)) {
                    String name3 = prefix + ":" + UtilsKt.getSootTypeName(singleton.getClass());
                    return new CheckTypeDefinition(name3, cls, (CheckType) singleton);
                }
                throw new IllegalStateException("xxx".toString());
            }

            @NotNull
            public final List<Field> optionFieldsOf(@NotNull Object singleton) {
                Intrinsics.checkNotNullParameter(singleton, "singleton");
                Field[] declaredFields = singleton.getClass().getDeclaredFields();
                Intrinsics.checkNotNullExpressionValue(declaredFields, "getDeclaredFields(...)");
                Field[] fieldArr = declaredFields;
                Collection destination$iv$iv = new ArrayList();
                for (Field field : fieldArr) {
                    Field it = field;
                    Class clazzTy = it.getType();
                    if (clazzTy == null ? false : SAOptions.class.isAssignableFrom(clazzTy)) {
                        destination$iv$iv.add(field);
                    }
                }
                return (List) destination$iv$iv;
            }
        }
    }

    /* compiled from: PluginDefinitions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0004HÆ\u0003J\r\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0002HÆ\u0003J+\u0010\u0017\u001a\u00020��2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\f\b\u0002\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0002HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcn/sast/framework/plugin/PluginDefinitions$CheckerUnitDefinition;", "Lcn/sast/framework/plugin/PluginDefinitions$Definition;", "Lcom/feysh/corax/config/api/CheckerUnit;", "name", "", "type", "Ljava/lang/Class;", "singleton", "<init>", "(Ljava/lang/String;Ljava/lang/Class;Lcom/feysh/corax/config/api/CheckerUnit;)V", "getName", "()Ljava/lang/String;", "getType", "()Ljava/lang/Class;", "getSingleton", "()Lcom/feysh/corax/config/api/CheckerUnit;", "defaultConfig", "Lcn/sast/framework/plugin/CheckerUnitOptionalConfig;", "getDefaultConfig", "()Lcn/sast/framework/plugin/CheckerUnitOptionalConfig;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "corax-framework"})
    /* loaded from: PluginDefinitions$CheckerUnitDefinition.class */
    public static final class CheckerUnitDefinition extends Definition<CheckerUnit> {

        @NotNull
        private final String name;

        @NotNull
        private final Class<?> type;

        @NotNull
        private final CheckerUnit singleton;

        @NotNull
        private final CheckerUnitOptionalConfig defaultConfig;

        @NotNull
        public final String component1() {
            return this.name;
        }

        @NotNull
        public final Class<?> component2() {
            return this.type;
        }

        @NotNull
        public final CheckerUnit component3() {
            return this.singleton;
        }

        @NotNull
        public final CheckerUnitDefinition copy(@NotNull String name, @NotNull Class<?> cls, @NotNull CheckerUnit singleton) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(cls, "type");
            Intrinsics.checkNotNullParameter(singleton, "singleton");
            return new CheckerUnitDefinition(name, cls, singleton);
        }

        public static /* synthetic */ CheckerUnitDefinition copy$default(CheckerUnitDefinition checkerUnitDefinition, String str, Class cls, CheckerUnit checkerUnit, int i, Object obj) {
            if ((i & 1) != 0) {
                str = checkerUnitDefinition.name;
            }
            if ((i & 2) != 0) {
                cls = checkerUnitDefinition.type;
            }
            if ((i & 4) != 0) {
                checkerUnit = checkerUnitDefinition.singleton;
            }
            return checkerUnitDefinition.copy(str, cls, checkerUnit);
        }

        @NotNull
        public String toString() {
            return "CheckerUnitDefinition(name=" + this.name + ", type=" + this.type + ", singleton=" + this.singleton + ")";
        }

        public int hashCode() {
            int result = this.name.hashCode();
            return (((result * 31) + this.type.hashCode()) * 31) + this.singleton.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CheckerUnitDefinition)) {
                return false;
            }
            CheckerUnitDefinition checkerUnitDefinition = (CheckerUnitDefinition) other;
            return Intrinsics.areEqual(this.name, checkerUnitDefinition.name) && Intrinsics.areEqual(this.type, checkerUnitDefinition.type) && Intrinsics.areEqual(this.singleton, checkerUnitDefinition.singleton);
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public String getName() {
            return this.name;
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public Class<?> getType() {
            return this.type;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public CheckerUnit getSingleton() {
            return this.singleton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CheckerUnitDefinition(@NotNull String name, @NotNull Class<?> cls, @NotNull CheckerUnit singleton) {
            super(name, cls, singleton, null, 8, null);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(cls, "type");
            Intrinsics.checkNotNullParameter(singleton, "singleton");
            this.name = name;
            this.type = cls;
            this.singleton = singleton;
            this.defaultConfig = new CheckerUnitOptionalConfig(getName(), getSingleton().getEnableDefault(), getOption());
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public CheckerUnitOptionalConfig getDefaultConfig() {
            return this.defaultConfig;
        }
    }

    /* compiled from: PluginDefinitions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0004HÆ\u0003J\r\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0002HÆ\u0003J+\u0010\u0017\u001a\u00020��2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\f\b\u0002\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0002HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcn/sast/framework/plugin/PluginDefinitions$ISootInitializeHandlerDefinition;", "Lcn/sast/framework/plugin/PluginDefinitions$Definition;", "Lcom/feysh/corax/config/api/ISootInitializeHandler;", "name", "", "type", "Ljava/lang/Class;", "singleton", "<init>", "(Ljava/lang/String;Ljava/lang/Class;Lcom/feysh/corax/config/api/ISootInitializeHandler;)V", "getName", "()Ljava/lang/String;", "getType", "()Ljava/lang/Class;", "getSingleton", "()Lcom/feysh/corax/config/api/ISootInitializeHandler;", "defaultConfig", "Lcn/sast/framework/plugin/SootOptionsConfig;", "getDefaultConfig", "()Lcn/sast/framework/plugin/SootOptionsConfig;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "corax-framework"})
    /* loaded from: PluginDefinitions$ISootInitializeHandlerDefinition.class */
    public static final class ISootInitializeHandlerDefinition extends Definition<ISootInitializeHandler> {

        @NotNull
        private final String name;

        @NotNull
        private final Class<?> type;

        @NotNull
        private final ISootInitializeHandler singleton;

        @NotNull
        private final SootOptionsConfig defaultConfig;

        @NotNull
        public final String component1() {
            return this.name;
        }

        @NotNull
        public final Class<?> component2() {
            return this.type;
        }

        @NotNull
        public final ISootInitializeHandler component3() {
            return this.singleton;
        }

        @NotNull
        public final ISootInitializeHandlerDefinition copy(@NotNull String name, @NotNull Class<?> cls, @NotNull ISootInitializeHandler singleton) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(cls, "type");
            Intrinsics.checkNotNullParameter(singleton, "singleton");
            return new ISootInitializeHandlerDefinition(name, cls, singleton);
        }

        public static /* synthetic */ ISootInitializeHandlerDefinition copy$default(ISootInitializeHandlerDefinition iSootInitializeHandlerDefinition, String str, Class cls, ISootInitializeHandler iSootInitializeHandler, int i, Object obj) {
            if ((i & 1) != 0) {
                str = iSootInitializeHandlerDefinition.name;
            }
            if ((i & 2) != 0) {
                cls = iSootInitializeHandlerDefinition.type;
            }
            if ((i & 4) != 0) {
                iSootInitializeHandler = iSootInitializeHandlerDefinition.singleton;
            }
            return iSootInitializeHandlerDefinition.copy(str, cls, iSootInitializeHandler);
        }

        @NotNull
        public String toString() {
            return "ISootInitializeHandlerDefinition(name=" + this.name + ", type=" + this.type + ", singleton=" + this.singleton + ")";
        }

        public int hashCode() {
            int result = this.name.hashCode();
            return (((result * 31) + this.type.hashCode()) * 31) + this.singleton.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ISootInitializeHandlerDefinition)) {
                return false;
            }
            ISootInitializeHandlerDefinition iSootInitializeHandlerDefinition = (ISootInitializeHandlerDefinition) other;
            return Intrinsics.areEqual(this.name, iSootInitializeHandlerDefinition.name) && Intrinsics.areEqual(this.type, iSootInitializeHandlerDefinition.type) && Intrinsics.areEqual(this.singleton, iSootInitializeHandlerDefinition.singleton);
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public String getName() {
            return this.name;
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public Class<?> getType() {
            return this.type;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public ISootInitializeHandler getSingleton() {
            return this.singleton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ISootInitializeHandlerDefinition(@NotNull String name, @NotNull Class<?> cls, @NotNull ISootInitializeHandler singleton) {
            super(name, cls, singleton, null, 8, null);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(cls, "type");
            Intrinsics.checkNotNullParameter(singleton, "singleton");
            this.name = name;
            this.type = cls;
            this.singleton = singleton;
            this.defaultConfig = new SootOptionsConfig(getName(), Intrinsics.areEqual(getSingleton(), DefaultSootConfiguration.INSTANCE), getOption());
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public SootOptionsConfig getDefaultConfig() {
            return this.defaultConfig;
        }
    }

    /* compiled from: PluginDefinitions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0014\u001a\u00020\u0004HÆ\u0003J\r\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0002HÆ\u0003J+\u0010\u0017\u001a\u00020��2\b\b\u0002\u0010\u0003\u001a\u00020\u00042\f\b\u0002\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0002HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\u0002X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcn/sast/framework/plugin/PluginDefinitions$CheckTypeDefinition;", "Lcn/sast/framework/plugin/PluginDefinitions$Definition;", "Lcom/feysh/corax/config/api/CheckType;", "name", "", "type", "Ljava/lang/Class;", "singleton", "<init>", "(Ljava/lang/String;Ljava/lang/Class;Lcom/feysh/corax/config/api/CheckType;)V", "getName", "()Ljava/lang/String;", "getType", "()Ljava/lang/Class;", "getSingleton", "()Lcom/feysh/corax/config/api/CheckType;", "defaultConfig", "Lcn/sast/framework/plugin/CheckersConfig$CheckTypeConfig;", "getDefaultConfig", "()Lcn/sast/framework/plugin/CheckersConfig$CheckTypeConfig;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "corax-framework"})
    /* loaded from: PluginDefinitions$CheckTypeDefinition.class */
    public static final class CheckTypeDefinition extends Definition<CheckType> {

        @NotNull
        private final String name;

        @NotNull
        private final Class<?> type;

        @NotNull
        private final CheckType singleton;

        @NotNull
        private final CheckersConfig.CheckTypeConfig defaultConfig;

        @NotNull
        public final String component1() {
            return this.name;
        }

        @NotNull
        public final Class<?> component2() {
            return this.type;
        }

        @NotNull
        public final CheckType component3() {
            return this.singleton;
        }

        @NotNull
        public final CheckTypeDefinition copy(@NotNull String name, @NotNull Class<?> cls, @NotNull CheckType singleton) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(cls, "type");
            Intrinsics.checkNotNullParameter(singleton, "singleton");
            return new CheckTypeDefinition(name, cls, singleton);
        }

        public static /* synthetic */ CheckTypeDefinition copy$default(CheckTypeDefinition checkTypeDefinition, String str, Class cls, CheckType checkType, int i, Object obj) {
            if ((i & 1) != 0) {
                str = checkTypeDefinition.name;
            }
            if ((i & 2) != 0) {
                cls = checkTypeDefinition.type;
            }
            if ((i & 4) != 0) {
                checkType = checkTypeDefinition.singleton;
            }
            return checkTypeDefinition.copy(str, cls, checkType);
        }

        @NotNull
        public String toString() {
            return "CheckTypeDefinition(name=" + this.name + ", type=" + this.type + ", singleton=" + this.singleton + ")";
        }

        public int hashCode() {
            int result = this.name.hashCode();
            return (((result * 31) + this.type.hashCode()) * 31) + this.singleton.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CheckTypeDefinition)) {
                return false;
            }
            CheckTypeDefinition checkTypeDefinition = (CheckTypeDefinition) other;
            return Intrinsics.areEqual(this.name, checkTypeDefinition.name) && Intrinsics.areEqual(this.type, checkTypeDefinition.type) && Intrinsics.areEqual(this.singleton, checkTypeDefinition.singleton);
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public String getName() {
            return this.name;
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public Class<?> getType() {
            return this.type;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public CheckType getSingleton() {
            return this.singleton;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CheckTypeDefinition(@NotNull String name, @NotNull Class<?> cls, @NotNull CheckType singleton) {
            super(name, cls, singleton, null, 8, null);
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(cls, "type");
            Intrinsics.checkNotNullParameter(singleton, "singleton");
            this.name = name;
            this.type = cls;
            this.singleton = singleton;
            this.defaultConfig = new CheckersConfig.CheckTypeConfig(getSingleton());
        }

        @Override // cn.sast.framework.plugin.PluginDefinitions.Definition
        @NotNull
        public CheckersConfig.CheckTypeConfig getDefaultConfig() {
            return this.defaultConfig;
        }
    }

    @JvmName(name = "getISootInitializeHandlerDefinition")
    @NotNull
    public final LinkedHashSet<ISootInitializeHandlerDefinition> getISootInitializeHandlerDefinition(@NotNull Class<ISootInitializeHandler> cls) {
        Intrinsics.checkNotNullParameter(cls, "clz");
        Iterable $this$filterIsInstanceTo$iv = this.definitions;
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$filterIsInstanceTo$iv) {
            if (element$iv instanceof ISootInitializeHandlerDefinition) {
                destination$iv.add(element$iv);
            }
        }
        return (LinkedHashSet) destination$iv;
    }

    @JvmName(name = "getCheckTypeDefinition")
    @NotNull
    public final LinkedHashSet<CheckTypeDefinition> getCheckTypeDefinition(@NotNull Class<CheckType> cls) {
        Intrinsics.checkNotNullParameter(cls, "clz");
        Iterable $this$filterIsInstanceTo$iv = this.definitions;
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$filterIsInstanceTo$iv) {
            if (element$iv instanceof CheckTypeDefinition) {
                destination$iv.add(element$iv);
            }
        }
        return (LinkedHashSet) destination$iv;
    }

    @JvmName(name = "getCheckerUnitDefinition")
    @NotNull
    public final LinkedHashSet<CheckerUnitDefinition> getCheckerUnitDefinition(@NotNull Class<CheckerUnit> cls) {
        Intrinsics.checkNotNullParameter(cls, "clz");
        Iterable $this$filterIsInstanceTo$iv = this.definitions;
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$filterIsInstanceTo$iv) {
            if (element$iv instanceof CheckerUnitDefinition) {
                destination$iv.add(element$iv);
            }
        }
        return (LinkedHashSet) destination$iv;
    }

    @JvmName(name = "getPreAnalysisUnit")
    @NotNull
    public final LinkedHashSet<CheckerUnitDefinition> getPreAnalysisUnit(@NotNull Class<PreAnalysisUnit> cls) {
        Intrinsics.checkNotNullParameter(cls, "clz");
        Iterable $this$filterTo$iv = getCheckerUnitDefinition(CheckerUnit.class);
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$filterTo$iv) {
            CheckerUnitDefinition it = (CheckerUnitDefinition) element$iv;
            if (Intrinsics.areEqual(it.getType(), cls)) {
                destination$iv.add(element$iv);
            }
        }
        return (LinkedHashSet) destination$iv;
    }

    @JvmName(name = "getAIAnalysisUnit")
    @NotNull
    public final LinkedHashSet<CheckerUnitDefinition> getAIAnalysisUnit(@NotNull Class<AIAnalysisUnit> cls) {
        Intrinsics.checkNotNullParameter(cls, "clz");
        Iterable $this$filterTo$iv = getCheckerUnitDefinition(CheckerUnit.class);
        Collection destination$iv = new LinkedHashSet();
        for (Object element$iv : $this$filterTo$iv) {
            CheckerUnitDefinition it = (CheckerUnitDefinition) element$iv;
            if (Intrinsics.areEqual(it.getType(), cls)) {
                destination$iv.add(element$iv);
            }
        }
        return (LinkedHashSet) destination$iv;
    }

    @NotNull
    public final Map<Definition<?>, IConfig> getDefaultConfigs() {
        Iterable $this$associateWith$iv = this.definitions;
        LinkedHashMap result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
        for (Object element$iv$iv : $this$associateWith$iv) {
            Definition it = (Definition) element$iv$iv;
            result$iv.put(element$iv$iv, it.getDefaultConfig());
        }
        return result$iv;
    }

    private final void register(String prefix, Class<?> cls, Class<?> cls2) {
        register(prefix, cls, Companion.singleton(cls2));
    }

    private final void register(String prefix, Class<?> cls, Object singleton) {
        if (singleton == null) {
            return;
        }
        Definition def = Definition.Companion.invoke(prefix, cls, singleton);
        String identifier = prefix + ":" + UtilsKt.getSootTypeName(cls) + UtilsKt.getSootTypeName(singleton.getClass());
        Definition exists = this.identifiers.get(identifier);
        if (exists != null) {
            String sootTypeName = UtilsKt.getSootTypeName(singleton.getClass());
            URL urlLocateClass = Resource.INSTANCE.locateClass(singleton.getClass());
            Object singleton2 = exists.getSingleton();
            Class<?> cls2 = singleton2 != null ? singleton2.getClass() : null;
            Object it = exists.getSingleton();
            throw new IllegalStateException(("When adding " + sootTypeName + ": " + urlLocateClass + ", there is already a " + cls2 + " " + Resource.INSTANCE.locateClass(it.getClass()) + " with the same name.").toString());
        }
        if (!cls.isInstance(singleton)) {
            logger.error(() -> {
                return register$lambda$4(r1, r2);
            });
            return;
        }
        this.identifiers.put(identifier, def);
        this.identifiersReverse.put(def, identifier);
        this.definitions.add(def);
    }

    private static final Object register$lambda$4(Object $singleton, Class $type) {
        return $singleton.getClass() + ": " + $singleton + " is not instance of type: " + $type;
    }

    public final void register(@NotNull PluginWrapper plugin, @NotNull Class<?> cls, @NotNull Class<?> cls2) {
        Intrinsics.checkNotNullParameter(plugin, "plugin");
        Intrinsics.checkNotNullParameter(cls, "type");
        Intrinsics.checkNotNullParameter(cls2, "definition");
        String pluginId = plugin.getPluginId();
        Intrinsics.checkNotNullExpressionValue(pluginId, "getPluginId(...)");
        register(pluginId, cls, cls2);
    }

    /* compiled from: PluginDefinitions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��V\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0010\u000e\n��\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bJk\u0010\f\u001a\u00020\r\"\u0004\b��\u0010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00122D\u0010\u0013\u001a@\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u0016\u0012\b\b\u000b\u0012\u0004\b\b(\u0017\u0012!\u0012\u001f\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u000e0\u00120\u0007¢\u0006\f\b\u0016\u0012\b\b\u000b\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\r0\u0014H\u0082\bJ\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u0010J!\u0010\u001b\u001a\u0004\u0018\u0001H\u000e\"\u0004\b��\u0010\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0012¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001f"}, d2 = {"Lcn/sast/framework/plugin/PluginDefinitions$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "commercialKeywords", "", "", "checkCommercial", "", "name", "getSubTypesOf", "", "T", "pluginManager", "Lcn/sast/framework/plugin/ConfigPluginLoader$PluginManager;", "clz", "Ljava/lang/Class;", "res", "Lkotlin/Function2;", "Lorg/pf4j/PluginWrapper;", "Lkotlin/ParameterName;", "plugin", "types", "load", "Lcn/sast/framework/plugin/PluginDefinitions;", "singleton", "(Ljava/lang/Class;)Ljava/lang/Object;", "getSerializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "corax-framework"})
    @SourceDebugExtension({"SMAP\nPluginDefinitions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PluginDefinitions.kt\ncn/sast/framework/plugin/PluginDefinitions$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuildersKt\n+ 4 PolymorphicModuleBuilder.kt\nkotlinx/serialization/modules/PolymorphicModuleBuilderKt\n*L\n1#1,302:1\n224#1,5:307\n224#1,5:354\n1755#2,3:303\n1863#2:306\n1864#2:312\n1863#2,2:359\n31#3,2:313\n254#3,7:315\n261#3,2:324\n254#3,9:326\n254#3,9:335\n254#3,9:344\n33#3:353\n118#4:322\n118#4:323\n*S KotlinDebug\n*F\n+ 1 PluginDefinitions.kt\ncn/sast/framework/plugin/PluginDefinitions$Companion\n*L\n234#1:307,5\n273#1:354,5\n217#1:303,3\n233#1:306\n233#1:312\n277#1:359,2\n263#1:313,2\n267#1:315,7\n267#1:324,2\n287#1:326,9\n291#1:335,9\n294#1:344,9\n263#1:353\n268#1:322\n269#1:323\n*E\n"})
    /* loaded from: PluginDefinitions$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        public final boolean checkCommercial(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            Iterable $this$any$iv = PluginDefinitions.commercialKeywords;
            if (($this$any$iv instanceof Collection) && ((Collection) $this$any$iv).isEmpty()) {
                return false;
            }
            for (Object element$iv : $this$any$iv) {
                String it = (String) element$iv;
                if (StringsKt.contains(name, it, true)) {
                    return true;
                }
            }
            return false;
        }

        private final <T> void getSubTypesOf(ConfigPluginLoader.PluginManager pluginManager, Class<T> cls, Function2<? super PluginWrapper, ? super Set<? extends Class<? extends T>>, Unit> function2) {
            for (Map.Entry<PluginWrapper, Reflections> entry : pluginManager.getPluginToReflections().entrySet()) {
                PluginWrapper pluginWrapper = entry.getKey();
                Reflections reflections = entry.getValue();
                Set subs = reflections.getSubTypesOf(cls);
                Intrinsics.checkNotNull(subs);
                function2.invoke(pluginWrapper, subs);
            }
        }

        @NotNull
        public final PluginDefinitions load(@NotNull ConfigPluginLoader.PluginManager pluginManager) {
            Intrinsics.checkNotNullParameter(pluginManager, "pluginManager");
            G.reset();
            PluginDefinitions checkerConfig = new PluginDefinitions();
            Iterable $this$forEach$iv = Definition.Companion.getDefinitions();
            for (Object element$iv : $this$forEach$iv) {
                Class clz = (Class) element$iv;
                Companion companion = PluginDefinitions.Companion;
                for (Map.Entry<PluginWrapper, Reflections> entry : pluginManager.getPluginToReflections().entrySet()) {
                    PluginWrapper pluginWrapper$iv = entry.getKey();
                    Reflections reflections$iv = entry.getValue();
                    Set<Class> subs$iv = reflections$iv.getSubTypesOf(clz);
                    Intrinsics.checkNotNull(subs$iv);
                    for (Class sub : subs$iv) {
                        checkerConfig.register(pluginWrapper$iv, (Class<?>) clz, (Class<?>) sub);
                    }
                }
            }
            G.reset();
            return checkerConfig;
        }

        @Nullable
        public final <T> T singleton(@NotNull Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "clz");
            try {
                String name = cls.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                if (checkCommercial(name)) {
                    AnalyzerEnv.INSTANCE.getBvs1n3ss().getAndAdd(1);
                }
                T t = (T) cls.getField("INSTANCE").get(null);
                if (t == null) {
                    return null;
                }
                return t;
            } catch (NoSuchFieldException e) {
                PluginDefinitions.logger.debug("{} is not a singleton", cls, e);
                return null;
            } catch (SecurityException e2) {
                PluginDefinitions.logger.debug("Failed to get INSTANCE field of {}", cls, e2);
                return null;
            }
        }

        @NotNull
        public final SerializersModule getSerializersModule(@NotNull ConfigPluginLoader.PluginManager pluginManager) {
            Intrinsics.checkNotNullParameter(pluginManager, "pluginManager");
            SerializersModuleBuilder builder$iv = new SerializersModuleBuilder();
            KClass baseClass$iv = Reflection.getOrCreateKotlinClass(Object.class);
            PolymorphicModuleBuilder builder$iv2 = new PolymorphicModuleBuilder(baseClass$iv, (KSerializer) null);
            KClass clazz$iv = Reflection.getOrCreateKotlinClass(CheckerUnitOptionalConfig.class);
            builder$iv2.subclass(clazz$iv, CheckerUnitOptionalConfig.Companion.serializer());
            KClass clazz$iv2 = Reflection.getOrCreateKotlinClass(CheckerUnitOptionalConfig.class);
            builder$iv2.subclass(clazz$iv2, CheckerUnitOptionalConfig.Companion.serializer());
            builder$iv2.buildTo(builder$iv);
            KClass baseClass$iv2 = Reflection.getOrCreateKotlinClass(SAOptions.class);
            PolymorphicModuleBuilder builder$iv3 = new PolymorphicModuleBuilder(baseClass$iv2, (KSerializer) null);
            getSerializersModule$lambda$11$addAllSUbTypesOf(builder$iv3, pluginManager, Reflection.getOrCreateKotlinClass(SAOptions.class));
            builder$iv3.subclass(Reflection.getOrCreateKotlinClass(DefaultSootConfiguration.CustomOptions.class), DefaultSootConfiguration.CustomOptions.Companion.serializer());
            builder$iv3.buildTo(builder$iv);
            KClass baseClass$iv3 = Reflection.getOrCreateKotlinClass(ITaintType.class);
            PolymorphicModuleBuilder builder$iv4 = new PolymorphicModuleBuilder(baseClass$iv3, (KSerializer) null);
            getSerializersModule$lambda$11$addAllSUbTypesOf(builder$iv4, pluginManager, Reflection.getOrCreateKotlinClass(ITaintType.class));
            builder$iv4.buildTo(builder$iv);
            KClass baseClass$iv4 = Reflection.getOrCreateKotlinClass(CheckType.class);
            PolymorphicModuleBuilder builder$iv5 = new PolymorphicModuleBuilder(baseClass$iv4, (KSerializer) null);
            getSerializersModule$lambda$11$addAllSUbTypesOf(builder$iv5, pluginManager, Reflection.getOrCreateKotlinClass(CheckType.class));
            builder$iv5.buildTo(builder$iv);
            return builder$iv.build();
        }

        private static final <T> void getSerializersModule$lambda$11$addAllSUbTypesOf(PolymorphicModuleBuilder<? super T> polymorphicModuleBuilder, ConfigPluginLoader.PluginManager $pluginManager, KClass<T> kClass) {
            Iterable subClasses = (Set) new LinkedHashSet();
            Companion companion = PluginDefinitions.Companion;
            Class clz$iv = JvmClassMappingKt.getJavaClass(kClass);
            for (Map.Entry<PluginWrapper, Reflections> entry : $pluginManager.getPluginToReflections().entrySet()) {
                entry.getKey();
                Reflections reflections$iv = entry.getValue();
                Set subs$iv = reflections$iv.getSubTypesOf(clz$iv);
                Intrinsics.checkNotNull(subs$iv);
                CollectionsKt.addAll((Collection) subClasses, subs$iv);
            }
            Iterable $this$forEach$iv = subClasses;
            for (Object element$iv : $this$forEach$iv) {
                Class sub = (Class) element$iv;
                if (!ReflectionUtilKt.isAbstract(sub)) {
                    KSerializer kSerializerSerializerOrNull = SerializersKt.serializerOrNull(sub);
                    KSerializer kSerializer = kSerializerSerializerOrNull instanceof KSerializer ? kSerializerSerializerOrNull : null;
                    if (kSerializer != null) {
                        KClass kotlinClass = JvmClassMappingKt.getKotlinClass(sub);
                        Intrinsics.checkNotNull(kotlinClass, "null cannot be cast to non-null type kotlin.reflect.KClass<T of cn.sast.framework.plugin.PluginDefinitions.Companion.getSerializersModule.<anonymous>.addAllSUbTypesOf>");
                        polymorphicModuleBuilder.subclass(kotlinClass, kSerializer);
                    } else {
                        PluginDefinitions.logger.error(() -> {
                            return getSerializersModule$lambda$11$addAllSUbTypesOf$lambda$7$lambda$6(r1);
                        });
                    }
                }
            }
        }

        private static final Object getSerializersModule$lambda$11$addAllSUbTypesOf$lambda$7$lambda$6(Class $sub) {
            return "The class " + $sub + " does not have the @Serializable annotation.";
        }
    }

    private static final Unit logger$lambda$5() {
        return Unit.INSTANCE;
    }
}
