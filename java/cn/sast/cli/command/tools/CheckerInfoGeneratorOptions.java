package cn.sast.cli.command.tools;

import cn.sast.api.config.CheckerInfoGenResult;
import cn.sast.cli.command.tools.CheckerInfoGenerator;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.plugin.ConfigPluginLoader;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.parameters.groups.OptionGroup;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import com.github.ajalt.clikt.parameters.types.BooleanKt;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: CheckerInfoGeneratorOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\b\u0002\u0018�� \u00162\u00020\u0001:\u0001\u0016B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0015R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\t\u001a\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoGeneratorOptions;", "Lcom/github/ajalt/clikt/parameters/groups/OptionGroup;", "<init>", "()V", "genCheckerInfoJson", "", "getGenCheckerInfoJson", "()Z", "genCheckerInfoJson$delegate", "Lkotlin/properties/ReadOnlyProperty;", "language", "", "", "getLanguage", "()Ljava/util/List;", "language$delegate", "run", "", "pl", "Lcn/sast/framework/plugin/ConfigPluginLoader;", "rules", "", "Companion", "corax-cli"})
/* loaded from: CheckerInfoGeneratorOptions.class */
public final class CheckerInfoGeneratorOptions extends OptionGroup {

    @NotNull
    private final ReadOnlyProperty genCheckerInfoJson$delegate;

    @NotNull
    private final ReadOnlyProperty language$delegate;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(CheckerInfoGeneratorOptions.class, "genCheckerInfoJson", "getGenCheckerInfoJson()Z", 0)), Reflection.property1(new PropertyReference1Impl(CheckerInfoGeneratorOptions.class, "language", "getLanguage()Ljava/util/List;", 0))};

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(CheckerInfoGeneratorOptions::logger$lambda$0);

    public CheckerInfoGeneratorOptions() {
        super("Generate checker_info.json for CoraxServer Options", (String) null, 2, (DefaultConstructorMarker) null);
        List listListOf = CollectionsKt.listOf(new String[]{"CoraxUnCompressDir", "Corax", "Plugins", "Canary"});
        String str = File.pathSeparator;
        Intrinsics.checkNotNullExpressionValue(str, "pathSeparator");
        this.genCheckerInfoJson$delegate = OptionWithValuesKt.required(BooleanKt.boolean(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"--gen-checker-info"}, "Generate checker_info.json from Corax/Corax/Plugins/Canary/analysis-config", "the \"" + CollectionsKt.joinToString$default(listListOf, str, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + "\" directory path", false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 504, (Object) null))).provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.language$delegate = OptionWithValuesKt.multiple$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"--info-lang"}, "", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), CollectionsKt.listOf(new String[]{"zh-CN", "en-US"}), false, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
    }

    private final boolean getGenCheckerInfoJson() {
        return ((Boolean) this.genCheckerInfoJson$delegate.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    private final List<String> getLanguage() {
        return (List) this.language$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final void run(@NotNull ConfigPluginLoader pl, @Nullable Set<String> rules) {
        Intrinsics.checkNotNullParameter(pl, "pl");
        if (getGenCheckerInfoJson()) {
            CheckerInfoGenerator gen = CheckerInfoGenerator.Companion.createCheckerInfoGenerator$default(CheckerInfoGenerator.Companion, pl, getLanguage(), false, 4, null);
            if (gen == null) {
                System.exit(2);
                throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
            }
            CheckerInfoGenResult checkerInfo = CheckerInfoGenerator.getCheckerInfo$default(gen, false, 1, null);
            gen.dumpCheckerInfoJson(checkerInfo, true);
            gen.dumpRuleAndRuleMappingDB(checkerInfo, rules);
        }
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    /* compiled from: CheckerInfoGeneratorOptions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoGeneratorOptions$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-cli"})
    /* loaded from: CheckerInfoGeneratorOptions$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }
}
