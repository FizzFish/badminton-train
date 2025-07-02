package cn.sast.framework.compiler;

import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.PersistentSet;
import mu.KLogger;
import mu.KotlinLogging;
import org.eclipse.jdt.core.compiler.CompilationProgress;
import org.eclipse.jdt.internal.compiler.batch.FileSystem;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.utbot.common.LoggerWithLogMethod;
import org.utbot.common.LoggingKt;
import org.utbot.common.Maybe;

/* compiled from: EcjCompiler.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n��\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010#\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018�� 62\u00020\u0001:\u00016B\u0083\u0001\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b\u0012\u0016\b\u0002\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0004\b\u0014\u0010\u0015J\u0018\u0010#\u001a\u0012\u0012\u0004\u0012\u00020%0$j\b\u0012\u0004\u0012\u00020%`&H\u0002Jt\u0010(\u001a\u00020)2\u001e\u0010*\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u00010$j\f\u0012\u0006\u0012\u0004\u0018\u00010%\u0018\u0001`&2\u0006\u0010+\u001a\u00020\u00062\u001e\u0010,\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010$j\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u0001`&2\b\u0010-\u001a\u0004\u0018\u00010\u00062\b\u0010.\u001a\u0004\u0018\u00010\u00062\u0006\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u00020\u000bH\u0014J$\u00101\u001a\u0016\u0012\u0004\u0012\u00020%\u0018\u00010$j\n\u0012\u0004\u0012\u00020%\u0018\u0001`&2\u0006\u00102\u001a\u00020\u0006H\u0002J\u0016\u00103\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u00104\u001a\u00020\u0006H\u0002J\u0006\u00105\u001a\u00020\u000bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n��\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\t¢\u0006\b\n��\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n��\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060 ¢\u0006\b\n��\u001a\u0004\b!\u0010\"R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00060 X\u0082\u0004¢\u0006\u0002\n��¨\u00067"}, d2 = {"Lcn/sast/framework/compiler/EcjCompiler;", "Lorg/eclipse/jdt/internal/compiler/batch/Main;", "sourcePath", "Lkotlinx/collections/immutable/PersistentSet;", "Lcn/sast/common/IResource;", "classpath", "", "class_opt", "customOptions", "", "useDefaultJava", "", "outWriter", "Ljava/io/PrintWriter;", "errWriter", "systemExitWhenFinished", "customDefaultOptions", "", "compilationProgress", "Lorg/eclipse/jdt/core/compiler/CompilationProgress;", "<init>", "(Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentSet;Lcn/sast/common/IResource;Ljava/util/List;ZLjava/io/PrintWriter;Ljava/io/PrintWriter;ZLjava/util/Map;Lorg/eclipse/jdt/core/compiler/CompilationProgress;)V", "getSourcePath", "()Lkotlinx/collections/immutable/PersistentSet;", "getClasspath", "getClass_opt", "()Lcn/sast/common/IResource;", "getCustomOptions", "()Ljava/util/List;", "getUseDefaultJava", "()Z", "collectClassPath", "", "getCollectClassPath", "()Ljava/util/Set;", "getDefaultClasspath", "Ljava/util/ArrayList;", "Lorg/eclipse/jdt/internal/compiler/batch/FileSystem$Classpath;", "Lkotlin/collections/ArrayList;", "currentClasspathNameHack", "addNewEntry", "", "paths", "currentClasspathName", "currentRuleSpecs", "customEncoding", "destPath", "isSourceOnly", "rejectDestinationPathOnJars", "getPathsFrom", "path", "replace", "ecjClasspathName", "compile", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nEcjCompiler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EcjCompiler.kt\ncn/sast/framework/compiler/EcjCompiler\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Logging.kt\norg/utbot/common/LoggingKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,150:1\n1279#2,2:151\n1293#2,4:153\n1557#2:157\n1628#2,3:158\n1863#2,2:161\n1863#2,2:163\n1#3:165\n49#4,13:166\n62#4,11:181\n37#5,2:179\n*S KotlinDebug\n*F\n+ 1 EcjCompiler.kt\ncn/sast/framework/compiler/EcjCompiler\n*L\n113#1:151,2\n113#1:153,4\n118#1:157\n118#1:158,3\n121#1:161,2\n130#1:163,2\n143#1:166,13\n143#1:181,11\n144#1:179,2\n*E\n"})
/* loaded from: EcjCompiler.class */
public final class EcjCompiler extends Main {

    @NotNull
    private final PersistentSet<IResource> sourcePath;

    @NotNull
    private final PersistentSet<String> classpath;

    @NotNull
    private final IResource class_opt;

    @NotNull
    private final List<String> customOptions;
    private final boolean useDefaultJava;

    @NotNull
    private final Set<String> collectClassPath;

    @NotNull
    private final Set<String> currentClasspathNameHack;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger kLogger = KotlinLogging.INSTANCE.logger(EcjCompiler::kLogger$lambda$9);

    public /* synthetic */ EcjCompiler(PersistentSet persistentSet, PersistentSet persistentSet2, IResource iResource, List list, boolean z, PrintWriter printWriter, PrintWriter printWriter2, boolean z2, Map map, CompilationProgress compilationProgress, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(persistentSet, persistentSet2, iResource, list, z, (i & 32) != 0 ? new PrintWriter(System.out) : printWriter, (i & 64) != 0 ? new PrintWriter(System.err) : printWriter2, (i & 128) != 0 ? false : z2, (i & 256) != 0 ? null : map, (i & 512) != 0 ? null : compilationProgress);
    }

    @NotNull
    public final PersistentSet<IResource> getSourcePath() {
        return this.sourcePath;
    }

    @NotNull
    public final PersistentSet<String> getClasspath() {
        return this.classpath;
    }

    @NotNull
    public final IResource getClass_opt() {
        return this.class_opt;
    }

    @NotNull
    public final List<String> getCustomOptions() {
        return this.customOptions;
    }

    public final boolean getUseDefaultJava() {
        return this.useDefaultJava;
    }

    /* compiled from: EcjCompiler.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcn/sast/framework/compiler/EcjCompiler$Companion;", "", "<init>", "()V", "kLogger", "Lmu/KLogger;", "getKLogger", "()Lmu/KLogger;", "corax-framework"})
    /* loaded from: EcjCompiler$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final KLogger getKLogger() {
            return EcjCompiler.kLogger;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public EcjCompiler(@NotNull PersistentSet<? extends IResource> persistentSet, @NotNull PersistentSet<String> persistentSet2, @NotNull IResource class_opt, @NotNull List<String> list, boolean useDefaultJava, @NotNull PrintWriter outWriter, @NotNull PrintWriter errWriter, boolean systemExitWhenFinished, @Nullable Map<String, String> map, @Nullable CompilationProgress compilationProgress) {
        super(outWriter, errWriter, systemExitWhenFinished, map, compilationProgress);
        Intrinsics.checkNotNullParameter(persistentSet, "sourcePath");
        Intrinsics.checkNotNullParameter(persistentSet2, "classpath");
        Intrinsics.checkNotNullParameter(class_opt, "class_opt");
        Intrinsics.checkNotNullParameter(list, "customOptions");
        Intrinsics.checkNotNullParameter(outWriter, "outWriter");
        Intrinsics.checkNotNullParameter(errWriter, "errWriter");
        this.sourcePath = persistentSet;
        this.classpath = persistentSet2;
        this.class_opt = class_opt;
        this.customOptions = list;
        this.useDefaultJava = useDefaultJava;
        this.collectClassPath = new LinkedHashSet();
        this.currentClasspathNameHack = new LinkedHashSet();
    }

    private static final Unit kLogger$lambda$9() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final Set<String> getCollectClassPath() {
        return this.collectClassPath;
    }

    private final ArrayList<FileSystem.Classpath> getDefaultClasspath() {
        ArrayList<FileSystem.Classpath> arrayListHandleClasspath = super.handleClasspath((ArrayList) null, (String) null);
        Intrinsics.checkNotNullExpressionValue(arrayListHandleClasspath, "handleClasspath(...)");
        return arrayListHandleClasspath;
    }

    protected void addNewEntry(@Nullable ArrayList<FileSystem.Classpath> arrayList, @NotNull String currentClasspathName, @Nullable ArrayList<String> arrayList2, @Nullable String customEncoding, @Nullable String destPath, boolean isSourceOnly, boolean rejectDestinationPathOnJars) {
        Intrinsics.checkNotNullParameter(currentClasspathName, "currentClasspathName");
        this.currentClasspathNameHack.add(currentClasspathName);
        super.addNewEntry(arrayList, currentClasspathName, arrayList2, customEncoding, destPath, isSourceOnly, rejectDestinationPathOnJars);
    }

    private final ArrayList<FileSystem.Classpath> getPathsFrom(String path) {
        this.currentClasspathNameHack.clear();
        ArrayList paths = new ArrayList();
        try {
            processPathEntries(4, paths, path, null, false, false);
            return paths;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private final List<String> replace(String ecjClasspathName) throws IOException {
        ArrayList path = getPathsFrom(ecjClasspathName);
        if (this.currentClasspathNameHack.size() != 1 || path == null || path.size() != 1) {
            this.collectClassPath.add(ecjClasspathName);
            return CollectionsKt.listOf(ecjClasspathName);
        }
        String origClassPathFileName = (String) CollectionsKt.first(this.currentClasspathNameHack);
        List res = new ArrayList();
        if (origClassPathFileName.length() > 0) {
            File cpf = new File(origClassPathFileName);
            if (!cpf.exists()) {
                Path path2 = cpf.getParentFile().toPath();
                Intrinsics.checkNotNullExpressionValue(path2, "toPath(...)");
                String name = cpf.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path2, name);
                Throwable th = null;
                try {
                    try {
                        DirectoryStream<Path> directoryStream = directoryStreamNewDirectoryStream;
                        Intrinsics.checkNotNull(directoryStream);
                        for (Path subClasspathName : directoryStream) {
                            res.add(StringsKt.replace(ecjClasspathName, origClassPathFileName, subClasspathName.toString(), false));
                            this.collectClassPath.add(subClasspathName.toString());
                        }
                        CloseableKt.closeFinally(directoryStreamNewDirectoryStream, (Throwable) null);
                        return res;
                    } finally {
                    }
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(directoryStreamNewDirectoryStream, th);
                    throw th2;
                }
            }
        }
        this.collectClassPath.add(ecjClasspathName);
        return CollectionsKt.listOf(ecjClasspathName);
    }

    public final boolean compile() {
        List args = new ArrayList();
        if (this.customOptions.isEmpty()) {
            Iterable classpath = CollectionsKt.toMutableSet(this.classpath);
            args.addAll(CollectionsKt.listOf(new String[]{"-source", "11", "-target", "11", "-proceedOnError", "-warn:none", "-g:lines,vars,source", "-preserveAllLocals"}));
            if (!((Collection) classpath).isEmpty()) {
                Iterable $this$associateWith$iv = classpath;
                Map result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault($this$associateWith$iv, 10)), 16));
                for (Object element$iv$iv : $this$associateWith$iv) {
                    String it = (String) element$iv$iv;
                    result$iv.put(element$iv$iv, replace(it));
                }
                Map replacedClassPathNames = result$iv;
                List classPathRule = CollectionsKt.toMutableList(CollectionsKt.flatten(replacedClassPathNames.values()));
                if (this.useDefaultJava) {
                    Iterable cps = getDefaultClasspath();
                    Iterable $this$map$iv = cps;
                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        FileSystem.Classpath it2 = (FileSystem.Classpath) item$iv$iv;
                        destination$iv$iv.add(it2.getPath());
                    }
                    classPathRule.addAll((List) destination$iv$iv);
                }
                List $this$forEach$iv = classPathRule;
                for (Object element$iv : $this$forEach$iv) {
                    String it3 = (String) element$iv;
                    args.add("-classpath");
                    args.add(it3);
                }
            }
            args.addAll(CollectionsKt.listOf(new String[]{"-d", this.class_opt.toString()}));
            if (!this.sourcePath.isEmpty()) {
                Iterable $this$forEach$iv2 = this.sourcePath;
                for (Object element$iv2 : $this$forEach$iv2) {
                    IResource it4 = (IResource) element$iv2;
                    String path = it4.getFile().getPath();
                    Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
                    args.add(path);
                }
            }
            kLogger.info(() -> {
                return compile$lambda$5(r1);
            });
        } else {
            if (!this.sourcePath.isEmpty()) {
                throw new IllegalStateException(("sourcePath: " + this.sourcePath + " must be empty while use customOptions: " + this.customOptions).toString());
            }
            if (!this.classpath.isEmpty()) {
                throw new IllegalStateException(("classpath: " + this.classpath + " must be empty while use customOptions: " + this.customOptions).toString());
            }
            args.addAll(this.customOptions);
        }
        LoggerWithLogMethod $this$bracket_u24default$iv = LoggingKt.info(kLogger);
        final String msg$iv = "compile java source";
        $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.compiler.EcjCompiler$compile$$inlined$bracket$default$1
            public final Object invoke() {
                return "Started: " + msg$iv;
            }
        });
        final LocalDateTime startTime$iv = LocalDateTime.now();
        final Ref.ObjectRef res$iv = new Ref.ObjectRef();
        res$iv.element = Maybe.Companion.empty();
        try {
            try {
                List $this$toTypedArray$iv = args;
                String[] commandLineArguments = (String[]) $this$toTypedArray$iv.toArray(new String[0]);
                res$iv.element = new Maybe(Boolean.valueOf(compile(commandLineArguments)));
                Object orThrow = ((Maybe) res$iv.element).getOrThrow();
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.compiler.EcjCompiler$compile$$inlined$bracket$default$2
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                            String str = msg$iv;
                            Result.Companion companion = Result.Companion;
                            Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                            return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                        }
                    });
                } else {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.compiler.EcjCompiler$compile$$inlined$bracket$default$3
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                        }
                    });
                }
                return ((Boolean) orThrow).booleanValue();
            } catch (Throwable t$iv) {
                $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.compiler.EcjCompiler$compile$$inlined$bracket$default$4
                    public final Object invoke() {
                        LocalDateTime localDateTime = startTime$iv;
                        Intrinsics.checkNotNull(localDateTime);
                        String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                        String str = msg$iv;
                        Result.Companion companion = Result.Companion;
                        Result.constructor-impl(ResultKt.createFailure(t$iv));
                        return "Finished (in " + strElapsedSecFrom + "): " + str + " :: EXCEPTION :: " + "";
                    }
                });
                throw t$iv;
            }
        } catch (Throwable th) {
            if (0 == 0) {
                if (((Maybe) res$iv.element).getHasValue()) {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.compiler.EcjCompiler$compile$$inlined$bracket$default$5
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            String strElapsedSecFrom = LoggingKt.elapsedSecFrom(localDateTime);
                            String str = msg$iv;
                            Result.Companion companion = Result.Companion;
                            Result.constructor-impl(((Maybe) res$iv.element).getOrThrow());
                            return "Finished (in " + strElapsedSecFrom + "): " + str + " " + "";
                        }
                    });
                } else {
                    $this$bracket_u24default$iv.getLogMethod().invoke(new Function0<Object>() { // from class: cn.sast.framework.compiler.EcjCompiler$compile$$inlined$bracket$default$6
                        public final Object invoke() {
                            LocalDateTime localDateTime = startTime$iv;
                            Intrinsics.checkNotNull(localDateTime);
                            return "Finished (in " + LoggingKt.elapsedSecFrom(localDateTime) + "): " + msg$iv + " <Nothing>";
                        }
                    });
                }
            }
            throw th;
        }
    }

    private static final Object compile$lambda$5(List $args) {
        return "ecj cmd args:\n[ " + CollectionsKt.joinToString$default($args, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) + " ]\n";
    }
}
