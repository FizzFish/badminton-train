package cn.sast.cli.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.CliktCommand;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.jimple.infoflow.sourcesSinks.definitions.ISourceSinkDefinitionProvider;

/* compiled from: FlowDroidSourceSinkTranslate.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018�� \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcn/sast/cli/config/FlowDroidSourceSinkTranslatorCli;", "Lcom/github/ajalt/clikt/core/CliktCommand;", "<init>", "()V", "sourceSinkFile", "", "getSourceSinkFile", "()Ljava/lang/String;", "sourceSinkFile$delegate", "Lkotlin/properties/ReadOnlyProperty;", "out", "getOut", "out$delegate", "run", "", "Companion", "corax-cli"})
@SourceDebugExtension({"SMAP\nFlowDroidSourceSinkTranslate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlowDroidSourceSinkTranslate.kt\ncn/sast/cli/config/FlowDroidSourceSinkTranslatorCli\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,143:1\n1#2:144\n*E\n"})
/* loaded from: FlowDroidSourceSinkTranslatorCli.class */
public final class FlowDroidSourceSinkTranslatorCli extends CliktCommand {

    @NotNull
    private final ReadOnlyProperty sourceSinkFile$delegate;

    @NotNull
    private final ReadOnlyProperty out$delegate;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(FlowDroidSourceSinkTranslatorCli.class, "sourceSinkFile", "getSourceSinkFile()Ljava/lang/String;", 0)), Reflection.property1(new PropertyReference1Impl(FlowDroidSourceSinkTranslatorCli.class, "out", "getOut()Ljava/lang/String;", 0))};

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(FlowDroidSourceSinkTranslatorCli::logger$lambda$4);

    public FlowDroidSourceSinkTranslatorCli() {
        super("help", (String) null, "Flow Droid Source Sink Translator", false, false, (Map) null, (String) null, false, false, false, 1002, (DefaultConstructorMarker) null);
        this.sourceSinkFile$delegate = OptionWithValuesKt.default$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], "sourceSinkFile", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), "DEFAULT", (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.out$delegate = OptionWithValuesKt.default$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], "sourceSinkFile", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), "out/flowdroid/Taint", (String) null, 2, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
    }

    /* compiled from: FlowDroidSourceSinkTranslate.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/cli/config/FlowDroidSourceSinkTranslatorCli$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-cli"})
    /* loaded from: FlowDroidSourceSinkTranslatorCli$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$4() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final String getSourceSinkFile() {
        return (String) this.sourceSinkFile$delegate.getValue(this, $$delegatedProperties[0]);
    }

    @NotNull
    public final String getOut() {
        return (String) this.out$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public void run() throws IOException {
        Path pathResolve;
        if (Intrinsics.areEqual(getSourceSinkFile(), "DEFAULT")) {
            logger.info(FlowDroidSourceSinkTranslatorCli::run$lambda$0);
            pathResolve = FlowDroidSourceSinkTranslateKt.getFlowDroidClass().resolve("SourcesAndSinks.txt");
        } else {
            pathResolve = Paths.get(getSourceSinkFile(), new String[0]);
        }
        Path path = pathResolve;
        File file = path.toFile();
        if (!file.exists()) {
            throw new IllegalArgumentException(("[" + path + "] not exists").toString());
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(("[" + path + "] not a file").toString());
        }
        Intrinsics.checkNotNull(file);
        String extension = FilesKt.getExtension(file);
        String canonicalPath = file.getCanonicalPath();
        Intrinsics.checkNotNullExpressionValue(canonicalPath, "getCanonicalPath(...)");
        ISourceSinkDefinitionProvider provider = FlowDroidSourceSinkTranslateKt.getFlowDroidSourceSinkProvider(extension, canonicalPath);
        if (!(provider != null)) {
            throw new IllegalArgumentException(("[" + path + "] not a valid flowdroid source sink file").toString());
        }
    }

    private static final Object run$lambda$0() {
        return "use default source sink file: " + FlowDroidSourceSinkTranslateKt.getFlowDroidLoc();
    }
}
