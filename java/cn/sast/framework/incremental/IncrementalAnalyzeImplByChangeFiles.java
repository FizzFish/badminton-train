package cn.sast.framework.incremental;

import cn.sast.api.config.MainConfig;
import cn.sast.api.config.MainConfigKt;
import cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles;
import cn.sast.api.incremental.ModifyInfoFactory;
import cn.sast.api.util.SootUtilsKt;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.AbstractFileIndexer;
import cn.sast.framework.report.ProjectFileLocator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.patch.Patch;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootClass;

/* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018�� @2\u00020\u0001:\u0001@B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ \u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u0017H\u0002J\u0018\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)J2\u0010*\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00170+j\u0002`,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00140.2\u0006\u0010/\u001a\u000200H\u0002J$\u00101\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00170+j\u0002`,2\u0006\u00102\u001a\u000203H\u0016J$\u00104\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00170+j\u0002`,2\u0006\u00105\u001a\u00020\u0014H\u0016J\u0010\u00106\u001a\u00020 2\u0006\u00107\u001a\u000208H\u0016J\u0010\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020\u0014H\u0002J\u0010\u0010;\u001a\u00020\u00142\u0006\u0010:\u001a\u00020\u0014H\u0002J\u0010\u0010<\u001a\u00020 2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u00020 2\u0006\u0010=\u001a\u00020>H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00170\u0016X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00190\u0016X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n��R \u0010\u001b\u001a\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n��¨\u0006A"}, d2 = {"Lcn/sast/framework/incremental/IncrementalAnalyzeImplByChangeFiles;", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "mappingDiffInArchive", "", "factory", "Lcn/sast/api/incremental/ModifyInfoFactory;", "simpleDeclAnalysisDependsGraph", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "interProceduralAnalysisDependsGraph", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;", "<init>", "(Lcn/sast/api/config/MainConfig;ZLcn/sast/api/incremental/ModifyInfoFactory;Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;)V", "getSimpleDeclAnalysisDependsGraph", "()Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "getInterProceduralAnalysisDependsGraph", "()Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;", "modifyFiles", "Ljava/util/LinkedHashSet;", "", "oldPath2Header", "Ljava/util/LinkedHashMap;", "Lorg/eclipse/jgit/diff/DiffEntry;", "newPath2Header", "Lorg/eclipse/jgit/patch/FileHeader;", "pathsInPatch", "name2Path", "", "", "ignoreCase", "visitChangedDecl", "", "target", "", "diffPath", "diff", "update", "scene", "Lsoot/Scene;", "locator", "Lcn/sast/framework/report/ProjectFileLocator;", "getChangeType", "Lkotlin/Pair;", "Lcn/sast/api/incremental/ChangeType;", "possibleSourceFiles", "", "mode", "Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;", "getChangeTypeOfClass", "cls", "Lsoot/SootClass;", "getChangeTypeOfFile", "file", "parseIncrementBaseFile", "base", "Lcn/sast/common/IResource;", "addOnePath", "p", "normalizePath", "parseChangeFiles", "diffFilePath", "Lcn/sast/common/IResFile;", "parseGitDiff", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nIncrementalAnalyzeImplByChangeFiles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/IncrementalAnalyzeImplByChangeFiles\n+ 2 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,395:1\n1317#2,2:396\n1#3:398\n1557#4:399\n1628#4,3:400\n381#5,7:403\n*S KotlinDebug\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/IncrementalAnalyzeImplByChangeFiles\n*L\n264#1:396,2\n299#1:399\n299#1:400,3\n337#1:403,7\n*E\n"})
/* loaded from: IncrementalAnalyzeImplByChangeFiles.class */
public final class IncrementalAnalyzeImplByChangeFiles implements IncrementalAnalyzeByChangeFiles {

    @NotNull
    private final MainConfig mainConfig;
    private final boolean mappingDiffInArchive;

    @NotNull
    private final ModifyInfoFactory factory;

    @NotNull
    private final IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph simpleDeclAnalysisDependsGraph;

    @NotNull
    private final IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph interProceduralAnalysisDependsGraph;

    @NotNull
    private final LinkedHashSet<String> modifyFiles;

    @NotNull
    private final LinkedHashMap<String, DiffEntry> oldPath2Header;

    @NotNull
    private final LinkedHashMap<String, FileHeader> newPath2Header;

    @NotNull
    private final LinkedHashSet<String> pathsInPatch;

    @NotNull
    private final Map<String, Set<String>> name2Path;
    private boolean ignoreCase;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(IncrementalAnalyzeImplByChangeFiles::logger$lambda$12);

    public IncrementalAnalyzeImplByChangeFiles(@NotNull MainConfig mainConfig, boolean mappingDiffInArchive, @NotNull ModifyInfoFactory factory, @NotNull IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph simpleDeclAnalysisDependsGraph, @NotNull IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph interProceduralAnalysisDependsGraph) {
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(factory, "factory");
        Intrinsics.checkNotNullParameter(simpleDeclAnalysisDependsGraph, "simpleDeclAnalysisDependsGraph");
        Intrinsics.checkNotNullParameter(interProceduralAnalysisDependsGraph, "interProceduralAnalysisDependsGraph");
        this.mainConfig = mainConfig;
        this.mappingDiffInArchive = mappingDiffInArchive;
        this.factory = factory;
        this.simpleDeclAnalysisDependsGraph = simpleDeclAnalysisDependsGraph;
        this.interProceduralAnalysisDependsGraph = interProceduralAnalysisDependsGraph;
        this.modifyFiles = new LinkedHashSet<>();
        this.oldPath2Header = new LinkedHashMap<>();
        this.newPath2Header = new LinkedHashMap<>();
        this.pathsInPatch = new LinkedHashSet<>();
        this.name2Path = new HashMap();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ IncrementalAnalyzeImplByChangeFiles(MainConfig mainConfig, boolean z, ModifyInfoFactory modifyInfoFactory, IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph simpleDeclAnalysisDependsGraph, IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph interProceduralAnalysisDependsGraph, int i, DefaultConstructorMarker defaultConstructorMarker) {
        z = (i & 2) != 0 ? true : z;
        modifyInfoFactory = (i & 4) != 0 ? new ModifyInfoFactoryImpl() : modifyInfoFactory;
        this(mainConfig, z, modifyInfoFactory, (i & 8) != 0 ? modifyInfoFactory.createSimpleDeclAnalysisDependsGraph() : simpleDeclAnalysisDependsGraph, (i & 16) != 0 ? modifyInfoFactory.createInterProceduralAnalysisDependsGraph() : interProceduralAnalysisDependsGraph);
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles
    @NotNull
    public IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph getSimpleDeclAnalysisDependsGraph() {
        return this.simpleDeclAnalysisDependsGraph;
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles
    @NotNull
    public IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph getInterProceduralAnalysisDependsGraph() {
        return this.interProceduralAnalysisDependsGraph;
    }

    private final void visitChangedDecl(Object target, String diffPath, DiffEntry diff) {
        if (!this.pathsInPatch.contains(diffPath)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Collection changed = this.factory.getPatchedDeclsByDiff(target, diff);
        getSimpleDeclAnalysisDependsGraph().visitChangedDecl(diffPath, changed);
        getInterProceduralAnalysisDependsGraph().visitChangedDecl(diffPath, changed);
    }

    public final void update(@NotNull Scene scene, @Nullable ProjectFileLocator locator) {
        Intrinsics.checkNotNullParameter(scene, "scene");
        Iterator itSnapshotIterator = scene.getClasses().snapshotIterator();
        Intrinsics.checkNotNullExpressionValue(itSnapshotIterator, "snapshotIterator(...)");
        while (itSnapshotIterator.hasNext()) {
            SootClass clazz = (SootClass) itSnapshotIterator.next();
            Intrinsics.checkNotNull(clazz);
            Pair<DiffEntry, DiffEntry> changeTypeOfClass = getChangeTypeOfClass(clazz);
            DiffEntry old = (DiffEntry) changeTypeOfClass.component1();
            DiffEntry diffEntry = (DiffEntry) changeTypeOfClass.component2();
            if (old != null) {
                String oldPath = old.getOldPath();
                Intrinsics.checkNotNullExpressionValue(oldPath, "getOldPath(...)");
                visitChangedDecl(clazz, oldPath, old);
            }
            if (diffEntry != null) {
                String newPath = diffEntry.getNewPath();
                Intrinsics.checkNotNullExpressionValue(newPath, "getNewPath(...)");
                visitChangedDecl(clazz, newPath, diffEntry);
            }
        }
        if (locator != null) {
            Iterator<String> it = this.pathsInPatch.iterator();
            Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
            while (it.hasNext()) {
                String next = it.next();
                Intrinsics.checkNotNullExpressionValue(next, "next(...)");
                String p = next;
                Sequence $this$forEach$iv = locator.findFromFileIndexMap(StringsKt.split$default(p, new String[]{"/"}, false, 0, 6, (Object) null), AbstractFileIndexer.CompareMode.Path);
                for (Object element$iv : $this$forEach$iv) {
                    IResFile it2 = (IResFile) element$iv;
                    if (this.mappingDiffInArchive || !MainConfigKt.skipResourceInArchive(this.mainConfig, it2)) {
                        DiffEntry old2 = this.oldPath2Header.get(p);
                        FileHeader fileHeader = this.newPath2Header.get(p);
                        if (old2 != null) {
                            if (!Intrinsics.areEqual(old2.getOldPath(), p)) {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                            visitChangedDecl(it2, p, old2);
                        }
                        if (fileHeader == null) {
                            continue;
                        } else {
                            if (!Intrinsics.areEqual(fileHeader.getNewPath(), p)) {
                                throw new IllegalStateException("Check failed.".toString());
                            }
                            visitChangedDecl(it2, p, (DiffEntry) fileHeader);
                        }
                    }
                }
            }
        }
    }

    private final Pair<DiffEntry, DiffEntry> getChangeType(Collection<String> collection, AbstractFileIndexer.CompareMode mode) {
        ArrayList arrayList;
        Pair<DiffEntry, DiffEntry> pair;
        AbstractFileIndexer<String> abstractFileIndexer = new AbstractFileIndexer<String>() { // from class: cn.sast.framework.incremental.IncrementalAnalyzeImplByChangeFiles$getChangeType$indexer$1
            @Override // cn.sast.framework.report.AbstractFileIndexer
            public List<String> getNames(String path, AbstractFileIndexer.CompareMode mode2) {
                Intrinsics.checkNotNullParameter(path, "path");
                Intrinsics.checkNotNullParameter(mode2, "mode");
                return StringsKt.split$default(StringsKt.removeSuffix(path, "/"), new String[]{"/"}, false, 0, 6, (Object) null);
            }

            @Override // cn.sast.framework.report.AbstractFileIndexer
            public Collection<String> getPathsByName(String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                Set set = (Set) this.this$0.name2Path.get(name);
                return set != null ? set : CollectionsKt.emptyList();
            }
        };
        if (this.ignoreCase) {
            Collection<String> $this$map$iv = collection;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String it = (String) item$iv$iv;
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String lowerCase = it.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                destination$iv$iv.add(lowerCase);
            }
            arrayList = (List) destination$iv$iv;
        } else {
            arrayList = collection;
        }
        List found = abstractFileIndexer.findFiles(arrayList, mode);
        Iterator<T> it2 = found.iterator();
        while (true) {
            if (!it2.hasNext()) {
                pair = null;
                break;
            }
            String it3 = (String) it2.next();
            Pair<DiffEntry, DiffEntry> pair2 = TuplesKt.to(this.oldPath2Header.get(it3), this.newPath2Header.get(it3));
            if (pair2 != null) {
                pair = pair2;
                break;
            }
        }
        return pair == null ? new Pair<>((Object) null, (Object) null) : pair;
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles
    @NotNull
    public Pair<DiffEntry, DiffEntry> getChangeTypeOfClass(@NotNull SootClass cls) {
        Intrinsics.checkNotNullParameter(cls, "cls");
        return getChangeType(SootUtilsKt.getPossibleSourceFiles(cls), AbstractFileIndexer.Companion.getDefaultClassCompareMode());
    }

    @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles
    @NotNull
    public Pair<DiffEntry, DiffEntry> getChangeTypeOfFile(@NotNull String file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return getChangeType(CollectionsKt.listOf(file), AbstractFileIndexer.CompareMode.Path);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0077, code lost:
    
        if (r0.equals("patch") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0091, code lost:
    
        if (r0.equals("diff") == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x009f, code lost:
    
        parseGitDiff(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
    
        return;
     */
    @Override // cn.sast.api.incremental.IncrementalAnalyze
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void parseIncrementBaseFile(@org.jetbrains.annotations.NotNull cn.sast.common.IResource r5) {
        /*
            r4 = this;
            r0 = r5
            java.lang.String r1 = "base"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r5
            boolean r0 = r0.getExists()
            if (r0 != 0) goto L21
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r5
            java.lang.String r2 = "The incremental base file: `" + r2 + "` not exists"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L21:
            r0 = r5
            boolean r0 = r0.isFile()
            if (r0 != 0) goto L3b
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r5
            java.lang.String r2 = "The incremental base file: `" + r2 + "` is not a file"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L3b:
            r0 = r5
            cn.sast.common.IResFile r0 = r0.toFile()
            r6 = r0
            r0 = r6
            java.lang.String r0 = r0.getExtension()
            r7 = r0
            r0 = r7
            int r0 = r0.hashCode()
            switch(r0) {
                case 115312: goto L7d;
                case 3083269: goto L8a;
                case 106438728: goto L70;
                default: goto La7;
            }
        L70:
            r0 = r7
            java.lang.String r1 = "patch"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L9f
            goto La7
        L7d:
            r0 = r7
            java.lang.String r1 = "txt"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L97
            goto La7
        L8a:
            r0 = r7
            java.lang.String r1 = "diff"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L9f
            goto La7
        L97:
            r0 = r4
            r1 = r6
            r0.parseChangeFiles(r1)
            goto Lb8
        L9f:
            r0 = r4
            r1 = r6
            r0.parseGitDiff(r1)
            goto Lb8
        La7:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r5
            java.lang.String r2 = "The incremental base file: `" + r2 + "` with a unsupported extension. Now only support .diff/.patch/.txt"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.incremental.IncrementalAnalyzeImplByChangeFiles.parseIncrementBaseFile(cn.sast.common.IResource):void");
    }

    private final void addOnePath(String p) {
        Object obj;
        if (!(StringsKt.indexOf$default(p, "\\", 0, false, 6, (Object) null) == -1)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.pathsInPatch.add(p);
        String name = StringsKt.substringAfterLast$default(StringsKt.removeSuffix(p, "/"), "/", (String) null, 2, (Object) null);
        Map $this$getOrPut$iv = this.name2Path;
        Object value$iv = $this$getOrPut$iv.get(name);
        if (value$iv == null) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            $this$getOrPut$iv.put(name, linkedHashSet);
            obj = linkedHashSet;
        } else {
            obj = value$iv;
        }
        ((Set) obj).add(p);
    }

    private final String normalizePath(String p) {
        String it = StringsKt.removeSuffix(StringsKt.removePrefix(StringsKt.replace$default(StringsKt.replace$default(p, "\\", "/", false, 4, (Object) null), "//", "/", false, 4, (Object) null), "/"), "\r");
        if (this.ignoreCase) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = it.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return lowerCase;
        }
        return it;
    }

    private final void parseChangeFiles(IResFile diffFilePath) throws IOException {
        OpenOption[] openOptionArr = new OpenOption[0];
        InputStream inputStreamNewInputStream = Files.newInputStream(diffFilePath.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        Reader inputStreamReader = new InputStreamReader(inputStreamNewInputStream, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        Throwable th = null;
        try {
            try {
                BufferedReader reader = bufferedReader;
                Stream<String> streamLines = reader.lines();
                final Function1 function1 = (v1) -> {
                    return parseChangeFiles$lambda$7$lambda$6(r1, v1);
                };
                streamLines.forEach(new Consumer(function1) { // from class: cn.sast.framework.incremental.IncrementalAnalyzeImplByChangeFiles$sam$java_util_function_Consumer$0
                    private final /* synthetic */ Function1 function;

                    {
                        Intrinsics.checkNotNullParameter(function1, "function");
                        this.function = function1;
                    }

                    @Override // java.util.function.Consumer
                    public final /* synthetic */ void accept(Object p0) {
                        this.function.invoke(p0);
                    }
                });
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(bufferedReader, (Throwable) null);
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(bufferedReader, th);
            throw th2;
        }
    }

    private static final Unit parseChangeFiles$lambda$7$lambda$6(IncrementalAnalyzeImplByChangeFiles this$0, String ln) {
        Intrinsics.checkNotNull(ln);
        final String file = this$0.normalizePath(StringsKt.removeSuffix(ln, "\n"));
        if (file.length() == 0) {
            return Unit.INSTANCE;
        }
        this$0.modifyFiles.add(file);
        this$0.addOnePath(file);
        AbstractMap abstractMap = this$0.oldPath2Header;
        Pair pair = TuplesKt.to(file, new DiffEntry(file) { // from class: cn.sast.framework.incremental.IncrementalAnalyzeImplByChangeFiles$parseChangeFiles$1$1$1
            {
                this.oldPath = file;
            }
        });
        abstractMap.put(pair.getFirst(), pair.getSecond());
        return Unit.INSTANCE;
    }

    private final void parseGitDiff(IResFile diffFilePath) throws IOException {
        Patch p = new Patch();
        OpenOption[] openOptionArr = new OpenOption[0];
        InputStream inputStreamNewInputStream = Files.newInputStream(diffFilePath.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        InputStream inputStream = inputStreamNewInputStream;
        try {
            InputStream stream = inputStream;
            p.parse(stream);
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(inputStream, (Throwable) null);
            if (p.getFiles().isEmpty()) {
                logger.error(() -> {
                    return parseGitDiff$lambda$9(r1);
                });
                return;
            }
            for (FileHeader handler : p.getFiles()) {
                String it = handler.getOldPath();
                if (it != null) {
                    String oldPath = normalizePath(it);
                    AbstractMap abstractMap = this.oldPath2Header;
                    Pair pair = TuplesKt.to(oldPath, handler);
                    abstractMap.put(pair.getFirst(), pair.getSecond());
                    addOnePath(oldPath);
                }
                String it2 = handler.getNewPath();
                if (it2 != null) {
                    String newPath = normalizePath(it2);
                    AbstractMap abstractMap2 = this.newPath2Header;
                    Pair pair2 = TuplesKt.to(newPath, handler);
                    abstractMap2.put(pair2.getFirst(), pair2.getSecond());
                    addOnePath(newPath);
                }
            }
        } catch (Throwable th) {
            CloseableKt.closeFinally(inputStream, (Throwable) null);
            throw th;
        }
    }

    private static final Object parseGitDiff$lambda$9(IResFile $diffFilePath) {
        return "The patch file " + $diffFilePath + " has no any change files";
    }

    /* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/incremental/IncrementalAnalyzeImplByChangeFiles$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: IncrementalAnalyzeImplByChangeFiles$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$12() {
        return Unit.INSTANCE;
    }
}
