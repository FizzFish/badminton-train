package cn.sast.cli.command.tools;

import cn.sast.common.IResFile;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.parameters.groups.OptionGroup;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import com.github.ajalt.clikt.parameters.types.FileKt;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;

/* compiled from: CheckerInfoCompareOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\u0018�� \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\r\u001a\u00020\u000eR\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007¨\u0006\u0010²\u0006\u0012\u0010\u0011\u001a\n \u0013*\u0004\u0018\u00010\u00120\u0012X\u008a\u0084\u0002"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoCompareOptions;", "Lcom/github/ajalt/clikt/parameters/groups/OptionGroup;", "<init>", "()V", "compareRight", "Ljava/io/File;", "getCompareRight", "()Ljava/io/File;", "compareRight$delegate", "Lkotlin/properties/ReadOnlyProperty;", "compareLeft", "getCompareLeft", "compareLeft$delegate", "run", "", "Companion", "corax-cli", "output", "Ljava/nio/file/Path;", "kotlin.jvm.PlatformType"})
/* loaded from: CheckerInfoCompareOptions.class */
public final class CheckerInfoCompareOptions extends OptionGroup {

    @NotNull
    private final ReadOnlyProperty compareRight$delegate;

    @NotNull
    private final ReadOnlyProperty compareLeft$delegate;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(CheckerInfoCompareOptions.class, "compareRight", "getCompareRight()Ljava/io/File;", 0)), Reflection.property1(new PropertyReference1Impl(CheckerInfoCompareOptions.class, "compareLeft", "getCompareLeft()Ljava/io/File;", 0))};

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(CheckerInfoCompareOptions::logger$lambda$5);

    public CheckerInfoCompareOptions() {
        super("Compare checker_info.json Options", (String) null, 2, (DefaultConstructorMarker) null);
        this.compareRight$delegate = OptionWithValuesKt.required(FileKt.file$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], "Compare and diff an other checker_info.json", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), true, false, false, false, false, false, 58, (Object) null)).provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.compareLeft$delegate = OptionWithValuesKt.required(FileKt.file$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], "Compare and diff an other checker_info.json", (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 508, (Object) null), true, false, false, false, false, false, 58, (Object) null)).provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
    }

    private final File getCompareRight() {
        return (File) this.compareRight$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final File getCompareLeft() {
        return (File) this.compareLeft$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final void run() {
        Lazy output$delegate = LazyKt.lazy(() -> {
            return run$lambda$3(r0);
        });
        CheckerInfoCompare checkerInfoCompare = new CheckerInfoCompare();
        Path pathRun$lambda$4 = run$lambda$4(output$delegate);
        Intrinsics.checkNotNullExpressionValue(pathRun$lambda$4, "run$lambda$4(...)");
        Resource resource = Resource.INSTANCE;
        String path = getCompareLeft().getPath();
        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
        IResFile iResFileFileOf = resource.fileOf(path);
        Resource resource2 = Resource.INSTANCE;
        String path2 = getCompareRight().getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
        checkerInfoCompare.compareWith(pathRun$lambda$4, iResFileFileOf, resource2.fileOf(path2));
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    private static final Path run$lambda$4(Lazy<? extends Path> $output$delegate) {
        return (Path) $output$delegate.getValue();
    }

    private static final Path run$lambda$3(CheckerInfoCompareOptions this$0) throws IOException {
        Path it = this$0.getCompareLeft().toPath().getParent().resolve("compare-result");
        Intrinsics.checkNotNull(it);
        LinkOption[] linkOptionArr = new LinkOption[0];
        if (!Files.exists(it, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length))) {
            Files.createDirectories(it, new FileAttribute[0]);
        }
        Path it2 = it.normalize();
        logger.info(() -> {
            return run$lambda$3$lambda$2$lambda$1(r1);
        });
        return it2;
    }

    private static final Object run$lambda$3$lambda$2$lambda$1(Path $it) {
        return "The compare output path is: " + $it;
    }

    /* compiled from: CheckerInfoCompareOptions.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/cli/command/tools/CheckerInfoCompareOptions$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-cli"})
    /* loaded from: CheckerInfoCompareOptions$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$5() {
        return Unit.INSTANCE;
    }
}
