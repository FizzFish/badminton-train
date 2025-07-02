package cn.sast.framework.report;

import cn.sast.api.config.MainConfig;
import cn.sast.api.report.BugPathEvent;
import cn.sast.api.report.BugPathPosition;
import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.IBugResInfo;
import cn.sast.api.report.IReportHashCalculator;
import cn.sast.api.report.Report;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ReportConsumer;
import cn.sast.framework.report.metadata.AnalysisMetadata;
import cn.sast.framework.result.OutputType;
import com.dd.plist.NSArray;
import com.dd.plist.NSDictionary;
import com.dd.plist.NSNumber;
import com.dd.plist.NSObject;
import com.dd.plist.NSString;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.cache.analysis.SootLineToMethodMapFactory;
import com.feysh.corax.cache.analysis.SootMethodAndRange;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.report.Region;
import com.github.javaparser.Range;
import com.github.javaparser.ast.body.BodyDeclaration;
import java.io.IOException;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootClass;
import soot.SootMethod;

/* compiled from: PlistDiagnostics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018�� )2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002()B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ,\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0096@¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u001eH\u0002J\b\u0010'\u001a\u00020\u0019H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017¨\u0006*"}, d2 = {"Lcn/sast/framework/report/PlistDiagnostics;", "Lcn/sast/framework/report/ReportConsumer;", "Lcn/sast/framework/report/IFileReportConsumer;", "Lcn/sast/framework/report/IMetadataVisitor;", "mainConfig", "Lcn/sast/api/config/MainConfig;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "outputDir", "Lcn/sast/common/IResDirectory;", "<init>", "(Lcn/sast/api/config/MainConfig;Lcom/feysh/corax/cache/analysis/SootInfoCache;Lcn/sast/common/IResDirectory;)V", "getMainConfig", "()Lcn/sast/api/config/MainConfig;", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "getMetadata", "()Lcn/sast/framework/report/ReportConsumer$MetaData;", "hashCalculator", "Lcn/sast/api/report/IReportHashCalculator;", "getHashCalculator", "()Lcn/sast/api/report/IReportHashCalculator;", "flush", "", "reports", "", "Lcn/sast/api/report/Report;", "filename", "", "locator", "Lcn/sast/framework/report/IProjectFileLocator;", "(Ljava/util/List;Ljava/lang/String;Lcn/sast/framework/report/IProjectFileLocator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visit", "analysisMetadata", "Lcn/sast/framework/report/metadata/AnalysisMetadata;", "getReportFileName", "fileName", "close", "PlistDiagnosticImpl", "Companion", "corax-framework"})
/* loaded from: PlistDiagnostics.class */
public final class PlistDiagnostics extends ReportConsumer implements IFileReportConsumer, IMetadataVisitor {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final MainConfig mainConfig;

    @Nullable
    private final SootInfoCache info;

    @NotNull
    private final IReportHashCalculator hashCalculator;

    @NotNull
    private static final FileTime hardcodeModifiedTime;

    @NotNull
    private static final KLogger logger;

    /* compiled from: PlistDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    @DebugMetadata(f = "PlistDiagnostics.kt", l = {209}, i = {PointsToGraphKt.pathStrictMod}, s = {"L$0"}, n = {"fullPath"}, m = "flush", c = "cn.sast.framework.report.PlistDiagnostics")
    /* renamed from: cn.sast.framework.report.PlistDiagnostics$flush$1, reason: invalid class name */
    /* loaded from: PlistDiagnostics$flush$1.class */
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        /* synthetic */ Object result;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Nullable
        public final Object invokeSuspend(@NotNull Object $result) {
            this.result = $result;
            this.label |= Integer.MIN_VALUE;
            return PlistDiagnostics.this.flush(null, null, null, (Continuation) this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlistDiagnostics(@NotNull MainConfig mainConfig, @Nullable SootInfoCache info, @NotNull IResDirectory outputDir) {
        super(OutputType.PLIST, outputDir);
        Intrinsics.checkNotNullParameter(mainConfig, "mainConfig");
        Intrinsics.checkNotNullParameter(outputDir, "outputDir");
        this.mainConfig = mainConfig;
        this.info = info;
        this.hashCalculator = new IReportHashCalculator() { // from class: cn.sast.framework.report.PlistDiagnostics$hashCalculator$1
            @Override // cn.sast.api.report.IReportHashCalculator
            public String fromPath(IResource path) {
                return IReportHashCalculator.DefaultImpls.fromPath(this, path);
            }

            @Override // cn.sast.api.report.IReportHashCalculator
            public String from(SootClass clazz) {
                Intrinsics.checkNotNullParameter(clazz, "clazz");
                String name = clazz.getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                return name;
            }

            @Override // cn.sast.api.report.IReportHashCalculator
            public String from(SootMethod method) {
                Intrinsics.checkNotNullParameter(method, "method");
                String signature = method.getSignature();
                Intrinsics.checkNotNullExpressionValue(signature, "getSignature(...)");
                return signature;
            }

            @Override // cn.sast.api.report.IReportHashCalculator
            public String fromAbsPath(IResource absolutePath) {
                Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
                if (this.this$0.getMainConfig().getHashAbspathInPlist()) {
                    return absolutePath.toString();
                }
                return this.this$0.getMainConfig().tryGetRelativePathFromAbsolutePath(absolutePath).getRelativePath();
            }
        };
    }

    @NotNull
    public final MainConfig getMainConfig() {
        return this.mainConfig;
    }

    @Nullable
    public final SootInfoCache getInfo() {
        return this.info;
    }

    @Override // cn.sast.framework.report.ReportConsumer
    @NotNull
    public ReportConsumer.MetaData getMetadata() {
        return new ReportConsumer.MetaData("CoraxJava plist report", "1.0", "CoraxJava");
    }

    @NotNull
    public final IReportHashCalculator getHashCalculator() {
        return this.hashCalculator;
    }

    /* compiled from: PlistDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��b\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n��\b\u0086\u0004\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010\u000fJ\"\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0002J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00152\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000bH\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J*\u0010\u001c\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0017H\u0002J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\"\u001a\u0004\u0018\u00010\u00112\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002J\u0016\u0010'\u001a\u0004\u0018\u00010\u00112\f\u0010(\u001a\b\u0012\u0004\u0012\u00020&0)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u000e¢\u0006\u0002\n��¨\u0006*"}, d2 = {"Lcn/sast/framework/report/PlistDiagnostics$PlistDiagnosticImpl;", "", "metadata", "Lcn/sast/framework/report/ReportConsumer$MetaData;", "classToFileName", "Lcn/sast/framework/report/IProjectFileLocator;", "<init>", "(Lcn/sast/framework/report/PlistDiagnostics;Lcn/sast/framework/report/ReportConsumer$MetaData;Lcn/sast/framework/report/IProjectFileLocator;)V", "fileToIndex", "", "", "", "classToFileIndex", "classInfo", "Lcn/sast/api/report/IBugResInfo;", "(Lcn/sast/api/report/IBugResInfo;)Ljava/lang/Integer;", "getLocation", "Lcom/dd/plist/NSDictionary;", "line", "column", "getRange", "Lcom/dd/plist/NSArray;", "region", "Lcom/feysh/corax/config/api/report/Region;", "getFuncRange", "getNote", "event", "Lcn/sast/api/report/BugPathEvent;", "getControlEdge", "fromClass", "fromRange", "toClass", "toRange", "getBugPathEventRange", "getDiagnostic", "hashCalculator", "Lcn/sast/api/report/IReportHashCalculator;", "report", "Lcn/sast/api/report/Report;", "getRoot", "reports", "", "corax-framework"})
    @SourceDebugExtension({"SMAP\nPlistDiagnostics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PlistDiagnostics.kt\ncn/sast/framework/report/PlistDiagnostics$PlistDiagnosticImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 Region.kt\ncom/feysh/corax/config/api/report/Region\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 7 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,237:1\n1#2:238\n1#2:247\n1#2:251\n1#2:264\n1#2:281\n381#3,7:239\n59#4:246\n57#4:248\n1619#5:249\n1863#5:250\n1864#5:252\n1620#5:253\n1611#5,9:254\n1863#5:263\n1864#5:265\n1620#5:266\n1611#5,9:271\n1863#5:280\n1864#5:282\n1620#5:283\n37#6,2:267\n37#6,2:269\n37#6,2:284\n216#7,2:286\n*S KotlinDebug\n*F\n+ 1 PlistDiagnostics.kt\ncn/sast/framework/report/PlistDiagnostics$PlistDiagnosticImpl\n*L\n65#1:247\n138#1:251\n158#1:264\n182#1:281\n54#1:239,7\n65#1:246\n65#1:248\n138#1:249\n138#1:250\n138#1:252\n138#1:253\n158#1:254,9\n158#1:263\n158#1:265\n158#1:266\n182#1:271,9\n182#1:280\n182#1:282\n182#1:283\n173#1:267,2\n174#1:269,2\n186#1:284,2\n188#1:286,2\n*E\n"})
    /* loaded from: PlistDiagnostics$PlistDiagnosticImpl.class */
    public final class PlistDiagnosticImpl {

        @NotNull
        private final ReportConsumer.MetaData metadata;

        @NotNull
        private final IProjectFileLocator classToFileName;

        @NotNull
        private Map<String, Integer> fileToIndex;
        final /* synthetic */ PlistDiagnostics this$0;

        public PlistDiagnosticImpl(@NotNull PlistDiagnostics this$0, @NotNull ReportConsumer.MetaData metadata, IProjectFileLocator classToFileName) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            Intrinsics.checkNotNullParameter(classToFileName, "classToFileName");
            this.this$0 = this$0;
            this.metadata = metadata;
            this.classToFileName = classToFileName;
            this.fileToIndex = new LinkedHashMap();
        }

        private final Integer classToFileIndex(IBugResInfo classInfo) {
            Object obj;
            IResFile it = this.classToFileName.get(classInfo, EmptyWrapperFileGenerator.INSTANCE);
            if (it == null) {
                return null;
            }
            PlistDiagnostics plistDiagnostics = this.this$0;
            Map $this$getOrPut$iv = this.fileToIndex;
            String string = it.expandRes(plistDiagnostics.getOutputDir()).getAbsolute().getNormalize().getPath().toString();
            Object value$iv = $this$getOrPut$iv.get(string);
            if (value$iv == null) {
                Integer numValueOf = Integer.valueOf(this.fileToIndex.size());
                $this$getOrPut$iv.put(string, numValueOf);
                obj = numValueOf;
            } else {
                obj = value$iv;
            }
            return Integer.valueOf(((Number) obj).intValue());
        }

        private final NSDictionary getLocation(IBugResInfo classInfo, int line, int column) {
            Integer numClassToFileIndex = classToFileIndex(classInfo);
            if (numClassToFileIndex == null) {
                return null;
            }
            int it = numClassToFileIndex.intValue();
            Map nSDictionary = new NSDictionary();
            nSDictionary.put("line", new NSNumber(line));
            nSDictionary.put("col", new NSNumber(column));
            nSDictionary.put("file", new NSNumber(it));
            return nSDictionary;
        }

        private final NSArray getRange(IBugResInfo classInfo, Region region) {
            NSObject location;
            Region region2 = region.startLine >= 0 ? region : null;
            if (region2 == null) {
                return null;
            }
            Region it = region2;
            NSObject location2 = getLocation(classInfo, it.startLine, it.startColumn);
            if (location2 == null || (location = getLocation(classInfo, it.getEndLine(), it.getEndColumn())) == null) {
                return null;
            }
            return new NSArray(new NSObject[]{location2, location});
        }

        private static final NSArray getFuncRange$toRange(Pair<Integer, Integer> pair, PlistDiagnosticImpl this$0, IBugResInfo $classInfo) {
            NSObject location;
            NSObject location2;
            int startLn = ((Number) pair.component1()).intValue();
            int endLn = ((Number) pair.component2()).intValue();
            if (startLn < 0 || endLn < 0 || (location = this$0.getLocation($classInfo, startLn, -1)) == null || (location2 = this$0.getLocation($classInfo, endLn, -1)) == null) {
                return null;
            }
            return new NSArray(new NSObject[]{location, location2});
        }

        private final NSArray getFuncRange(IBugResInfo classInfo, int line) {
            BodyDeclaration memberAtLine;
            Range it;
            if (!(classInfo instanceof ClassResInfo)) {
                return null;
            }
            if (this.this$0.getInfo() != null) {
                SootInfoCache $this$getFuncRange_u24lambda_u249 = this.this$0.getInfo();
                memberAtLine = $this$getFuncRange_u24lambda_u249.getMemberAtLine(((ClassResInfo) classInfo).getSc(), line);
            } else {
                memberAtLine = null;
            }
            BodyDeclaration member = memberAtLine;
            if (member != null) {
                Optional it2 = member.getRange();
                Optional optional = it2.isPresent() ? it2 : null;
                Pair range = (optional == null || (it = (Range) optional.get()) == null) ? null : TuplesKt.to(Integer.valueOf(it.begin.line), Integer.valueOf(it.end.line));
                if (range != null) {
                    return getFuncRange$toRange(range, this, classInfo);
                }
            }
            SootMethodAndRange range2 = SootLineToMethodMapFactory.getSootMethodAtLine$default(SootLineToMethodMapFactory.INSTANCE, ((ClassResInfo) classInfo).getSc(), line, false, 4, (Object) null);
            if (range2 == null) {
                return null;
            }
            Pair it3 = range2.getRange();
            return getFuncRange$toRange(TuplesKt.to(Integer.valueOf(((Number) it3.getFirst()).intValue() - 1), Integer.valueOf(((Number) it3.getSecond()).intValue() + 1)), this, classInfo);
        }

        private final NSDictionary getNote(BugPathEvent event) {
            NSDictionary loc = getLocation(event.getClassname(), event.getRegion().startLine, event.getRegion().startColumn);
            if (loc == null) {
                return null;
            }
            Map nSDictionary = new NSDictionary();
            nSDictionary.put("kind", new NSString("event"));
            nSDictionary.put("location", loc);
            nSDictionary.put("depth", new NSNumber(0));
            nSDictionary.put("message", new NSString(event.getMessage().get(Language.EN)));
            nSDictionary.put("message_zh", new NSString(event.getMessage().get(Language.ZH)));
            NSObject funcRange = getFuncRange(event.getClassname(), event.getRegion().startLine);
            if (funcRange != null) {
                nSDictionary.put("ranges_of_func_source", new NSArray(new NSObject[]{funcRange}));
            }
            NSObject range = getRange(event.getClassname(), event.getRegion());
            if (range != null) {
                nSDictionary.put("ranges", new NSArray(new NSObject[]{range}));
            }
            return nSDictionary;
        }

        private final NSDictionary getControlEdge(IBugResInfo fromClass, Region fromRange, IBugResInfo toClass, Region toRange) {
            NSArray to;
            NSArray from = getRange(fromClass, fromRange);
            if (from == null || (to = getRange(toClass, toRange)) == null) {
                return null;
            }
            Map nSDictionary = new NSDictionary();
            nSDictionary.put("kind", new NSString("control"));
            Map map = nSDictionary;
            Map nSDictionary2 = new NSDictionary();
            nSDictionary2.put("start", from);
            nSDictionary2.put("end", to);
            Unit unit = Unit.INSTANCE;
            map.put("edges", new NSArray(new NSObject[]{nSDictionary2}));
            return nSDictionary;
        }

        private final Region getBugPathEventRange(BugPathEvent event) {
            return event.getRegion();
        }

        private final NSDictionary getDiagnostic(IReportHashCalculator hashCalculator, Report report) {
            NSDictionary it;
            Iterable $this$mapNotNullTo$iv = report.getPathEvents();
            Collection destination$iv = (List) new ArrayList();
            for (Object element$iv$iv : $this$mapNotNullTo$iv) {
                NSDictionary note = getNote((BugPathEvent) element$iv$iv);
                if (note != null) {
                    destination$iv.add(note);
                }
            }
            List path = (List) destination$iv;
            if (!report.getBug_path_positions().isEmpty()) {
                int size = report.getBug_path_positions().size() - 1;
                for (int i = 0; i < size; i++) {
                    BugPathPosition from = report.getBug_path_positions().get(i);
                    BugPathPosition to = report.getBug_path_positions().get(i + 1);
                    if (from.getRegion() != null && to.getRegion() != null) {
                        IBugResInfo classname = from.getClassname();
                        Region region = from.getRegion();
                        Intrinsics.checkNotNull(region);
                        IBugResInfo classname2 = to.getClassname();
                        Region region2 = to.getRegion();
                        Intrinsics.checkNotNull(region2);
                        NSDictionary it2 = getControlEdge(classname, region, classname2, region2);
                        if (it2 != null) {
                            path.add(it2);
                        }
                    }
                }
            } else if (report.getPathEvents().size() > 1) {
                int size2 = report.getPathEvents().size() - 1;
                for (int i2 = 0; i2 < size2; i2++) {
                    BugPathEvent from2 = report.getPathEvents().get(i2);
                    Region fromRange = getBugPathEventRange(from2);
                    BugPathEvent to2 = report.getPathEvents().get(i2 + 1);
                    Region toRange = getBugPathEventRange(to2);
                    if (!Intrinsics.areEqual(fromRange, toRange) && (it = getControlEdge(from2.getClassname(), fromRange, to2.getClassname(), toRange)) != null) {
                        path.add(it);
                    }
                }
            }
            Iterable $this$mapNotNull$iv = report.getNotes();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                NSDictionary note2 = getNote((BugPathEvent) element$iv$iv$iv);
                if (note2 != null) {
                    destination$iv$iv.add(note2);
                }
            }
            Collection notes = (List) destination$iv$iv;
            NSDictionary location = getLocation(report.getBugResFile(), report.getRegion().startLine, report.getRegion().startColumn);
            if (location == null) {
                return null;
            }
            Map nSDictionary = new NSDictionary();
            nSDictionary.put("location", location);
            nSDictionary.put("issue_hash_content_of_line_in_context", new NSString(report.reportHash(hashCalculator, Report.HashType.DIAGNOSTIC_MESSAGE)));
            nSDictionary.put("issue_hash_location_bug_type", new NSString(report.reportHash(hashCalculator, Report.HashType.CONTEXT_FREE)));
            nSDictionary.put("check_name", new NSString(report.getCheck_name()));
            nSDictionary.put("detector_name", new NSString(report.getDetector_name()));
            String str = report.getMessage().get(Language.EN);
            Intrinsics.checkNotNull(str);
            nSDictionary.put("description", new NSString(str));
            String str2 = report.getMessage().get(Language.ZH);
            Intrinsics.checkNotNull(str2);
            nSDictionary.put("description_zh", new NSString(str2));
            Map map = nSDictionary;
            String category = report.getCategory();
            if (category == null) {
                category = "unknown";
            }
            map.put("category", new NSString(category));
            nSDictionary.put("type", new NSString(report.getType()));
            Collection $this$toTypedArray$iv = notes;
            NSDictionary[] nSDictionaryArr = (NSDictionary[]) $this$toTypedArray$iv.toArray(new NSDictionary[0]);
            nSDictionary.put("notes", new NSArray((NSObject[]) Arrays.copyOf(nSDictionaryArr, nSDictionaryArr.length)));
            List $this$toTypedArray$iv2 = path;
            NSDictionary[] nSDictionaryArr2 = (NSDictionary[]) $this$toTypedArray$iv2.toArray(new NSDictionary[0]);
            nSDictionary.put("path", new NSArray((NSObject[]) Arrays.copyOf(nSDictionaryArr2, nSDictionaryArr2.length)));
            return nSDictionary;
        }

        @Nullable
        public final NSDictionary getRoot(@NotNull List<Report> list) {
            Intrinsics.checkNotNullParameter(list, "reports");
            Map nSDictionary = new NSDictionary();
            PlistDiagnostics plistDiagnostics = this.this$0;
            Map map = nSDictionary;
            List<Report> $this$mapNotNull$iv = list;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
                Report it = (Report) element$iv$iv$iv;
                NSDictionary d = getDiagnostic(plistDiagnostics.getHashCalculator(), it);
                if (d == null) {
                    PlistDiagnostics.logger.warn(() -> {
                        return getRoot$lambda$35$lambda$28$lambda$27$lambda$26(r1);
                    });
                }
                if (d != null) {
                    destination$iv$iv.add(d);
                }
            }
            Collection $this$toTypedArray$iv = (List) destination$iv$iv;
            if ($this$toTypedArray$iv.isEmpty()) {
                return null;
            }
            NSDictionary[] nSDictionaryArr = (NSDictionary[]) $this$toTypedArray$iv.toArray(new NSDictionary[0]);
            map.put("diagnostics", new NSArray((NSObject[]) Arrays.copyOf(nSDictionaryArr, nSDictionaryArr.length)));
            Map map2 = nSDictionary;
            NSArray $this$getRoot_u24lambda_u2435_u24lambda_u2431 = new NSArray(this.fileToIndex.size());
            Map $this$forEach$iv = this.fileToIndex;
            for (Map.Entry element$iv : $this$forEach$iv.entrySet()) {
                String file = element$iv.getKey();
                int index = element$iv.getValue().intValue();
                $this$getRoot_u24lambda_u2435_u24lambda_u2431.setValue(index, file);
            }
            map2.put("files", $this$getRoot_u24lambda_u2435_u24lambda_u2431);
            Map map3 = nSDictionary;
            Map nSDictionary2 = new NSDictionary();
            Map map4 = nSDictionary2;
            Map nSDictionary3 = new NSDictionary();
            nSDictionary3.put("name", new NSString(this.metadata.getToolName()));
            nSDictionary3.put("version", new NSString(this.metadata.getToolVersion()));
            map4.put("generated_by", nSDictionary3);
            Map map5 = nSDictionary2;
            Map nSDictionary4 = new NSDictionary();
            nSDictionary4.put("name", new NSString(this.metadata.getAnalyzerName()));
            map5.put("analyzer", nSDictionary4);
            map3.put("metadata", nSDictionary2);
            return nSDictionary;
        }

        private static final Object getRoot$lambda$35$lambda$28$lambda$27$lambda$26(Report $it) {
            return "Failed create plist report for: `" + $it + "`";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0029  */
    @Override // cn.sast.framework.report.IFileReportConsumer
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object flush(@org.jetbrains.annotations.NotNull java.util.List<cn.sast.api.report.Report> r8, @org.jetbrains.annotations.NotNull java.lang.String r9, @org.jetbrains.annotations.NotNull cn.sast.framework.report.IProjectFileLocator r10, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 273
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.report.PlistDiagnostics.flush(java.util.List, java.lang.String, cn.sast.framework.report.IProjectFileLocator, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final Object flush$lambda$1$lambda$0(IResource $fullPath) {
        return "Create/modify plist file: '" + $fullPath + "'";
    }

    @Override // cn.sast.framework.report.IMetadataVisitor
    public void visit(@NotNull AnalysisMetadata analysisMetadata) throws IOException {
        Intrinsics.checkNotNullParameter(analysisMetadata, "analysisMetadata");
        IResFile metadataFilePath = getOutputDir().resolve("metadata.json").toFile();
        ResourceKt.writeText$default(metadataFilePath, analysisMetadata.toJson(), null, 2, null);
    }

    private final String getReportFileName(String fileName) {
        String analyzerName = getMetadata().getAnalyzerName();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = analyzerName.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return fileName + "_" + lowerCase + ".plist";
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    /* compiled from: PlistDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"Lcn/sast/framework/report/PlistDiagnostics$Companion;", "", "<init>", "()V", "hardcodeModifiedTime", "Ljava/nio/file/attribute/FileTime;", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: PlistDiagnostics$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
        hardcodeModifiedTime = FileTime.from(sdf.parse("10/10/2100").toInstant());
        logger = KotlinLogging.INSTANCE.logger(PlistDiagnostics::logger$lambda$2);
    }

    private static final Unit logger$lambda$2() {
        return Unit.INSTANCE;
    }
}
