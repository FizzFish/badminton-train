package cn.sast.framework.entries.javaee;

import analysis.Config;
import analysis.CreateEdge;
import analysis.Implement;
import cn.sast.common.IResFile;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.entries.utils.PhantomValueForType;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import mock.MockObjectImpl;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Body;
import soot.Local;
import soot.RefType;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.jimple.JimpleBody;
import utils.BaseBodyGenerator;
import utils.BaseBodyGeneratorFactory;
import utils.INewUnits;

/* compiled from: JavaEeEntryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018�� \u00152\u00020\u0001:\u0002\u0015\u0016B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcn/sast/framework/entries/javaee/JavaEeEntryProvider;", "Lcn/sast/framework/entries/IEntryPointProvider;", "ctx", "Lcn/sast/framework/SootCtx;", "beanXmls", "", "Lcn/sast/common/IResFile;", "<init>", "(Lcn/sast/framework/SootCtx;Ljava/util/Set;)V", "getBeanXmls", "()Ljava/util/Set;", "createDummyMain", "Lsoot/SootMethod;", "beanXmlPaths", "", "", "iterator", "Lkotlinx/coroutines/flow/Flow;", "Lcn/sast/framework/entries/IEntryPointProvider$AnalyzeTask;", "getIterator", "()Lkotlinx/coroutines/flow/Flow;", "Companion", "SummaryTypeValueBaseBodyGenerator", "corax-framework"})
/* loaded from: JavaEeEntryProvider.class */
public final class JavaEeEntryProvider implements IEntryPointProvider {

    @NotNull
    private final SootCtx ctx;

    @NotNull
    private final Set<IResFile> beanXmls;

    @NotNull
    private final Flow<IEntryPointProvider.AnalyzeTask> iterator;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(JavaEeEntryProvider::logger$lambda$1);

    public JavaEeEntryProvider(@NotNull SootCtx ctx, @NotNull Set<IResFile> set) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intrinsics.checkNotNullParameter(set, "beanXmls");
        this.ctx = ctx;
        this.beanXmls = set;
        this.iterator = FlowKt.flow(new JavaEeEntryProvider$iterator$1(this, null));
    }

    public /* synthetic */ JavaEeEntryProvider(SootCtx sootCtx, Set set, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sootCtx, (i & 2) != 0 ? new LinkedHashSet() : set);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void startAnalyse() {
        IEntryPointProvider.DefaultImpls.startAnalyse(this);
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    public void endAnalyse() {
        IEntryPointProvider.DefaultImpls.endAnalyse(this);
    }

    @NotNull
    public final Set<IResFile> getBeanXmls() {
        return this.beanXmls;
    }

    /* compiled from: JavaEeEntryProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/entries/javaee/JavaEeEntryProvider$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-framework"})
    /* loaded from: JavaEeEntryProvider$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }

    /* compiled from: JavaEeEntryProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n��\n\u0002\u0018\u0002\n��\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007JF\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u00122\u000e\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u000fH\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0015"}, d2 = {"Lcn/sast/framework/entries/javaee/JavaEeEntryProvider$SummaryTypeValueBaseBodyGenerator;", "Lutils/BaseBodyGenerator;", "p", "Lcn/sast/framework/entries/utils/PhantomValueForType;", "body", "Lsoot/Body;", "<init>", "(Lcn/sast/framework/entries/utils/PhantomValueForType;Lsoot/Body;)V", "getValueForType", "Lsoot/Value;", "newUnits", "Lutils/INewUnits;", "tp", "Lsoot/Type;", "constructionStack", "", "Lsoot/SootClass;", "parentClasses", "", "generatedLocals", "Lsoot/Local;", "corax-framework"})
    /* loaded from: JavaEeEntryProvider$SummaryTypeValueBaseBodyGenerator.class */
    public static final class SummaryTypeValueBaseBodyGenerator extends BaseBodyGenerator {

        @NotNull
        private final PhantomValueForType p;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SummaryTypeValueBaseBodyGenerator(@NotNull PhantomValueForType p, @NotNull Body body) {
            super(body);
            Intrinsics.checkNotNullParameter(p, "p");
            Intrinsics.checkNotNullParameter(body, "body");
            this.p = p;
        }

        @Nullable
        protected Value getValueForType(@NotNull INewUnits newUnits, @NotNull Type tp, @NotNull Set<SootClass> set, @NotNull Set<? extends SootClass> set2, @Nullable Set<Local> set3) {
            Intrinsics.checkNotNullParameter(newUnits, "newUnits");
            Intrinsics.checkNotNullParameter(tp, "tp");
            Intrinsics.checkNotNullParameter(set, "constructionStack");
            Intrinsics.checkNotNullParameter(set2, "parentClasses");
            return this.p.getValueForType(newUnits, this, tp);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SootMethod createDummyMain(Set<String> set) {
        SootMethod sootMethod;
        final PhantomValueForType p = new PhantomValueForType(null, 1, null);
        BaseBodyGeneratorFactory.instance = new BaseBodyGeneratorFactory() { // from class: cn.sast.framework.entries.javaee.JavaEeEntryProvider.createDummyMain.1
            public BaseBodyGenerator create(Body body) {
                Intrinsics.checkNotNullParameter(body, "body");
                return new SummaryTypeValueBaseBodyGenerator(p, body);
            }
        };
        Implement.mockObject = new MockObjectImpl() { // from class: cn.sast.framework.entries.javaee.JavaEeEntryProvider.createDummyMain.2
            public Local mockBean(JimpleBody body, BaseBodyGenerator units, SootClass sootClass, SootMethod toCall) {
                Intrinsics.checkNotNullParameter(body, "body");
                Intrinsics.checkNotNullParameter(units, "units");
                Intrinsics.checkNotNullParameter(sootClass, "sootClass");
                Intrinsics.checkNotNullParameter(toCall, "toCall");
                PhantomValueForType phantomValueForType = p;
                RefType type = sootClass.getType();
                Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                Local valueForType = phantomValueForType.getValueForType(units, (Type) type);
                if (valueForType != null) {
                    return valueForType;
                }
                Local localMockBean = super.mockBean(body, units, sootClass, toCall);
                Intrinsics.checkNotNullExpressionValue(localMockBean, "mockBean(...)");
                return localMockBean;
            }
        };
        CreateEdge edge = new CreateEdge();
        try {
            try {
                Config config = new Config();
                Config.linkMainAndController = false;
                Config.linkSpringCGLIB_CallEntrySyntheticAndRequestMappingMethods = false;
                config.bean_xml_paths = set;
                edge.initCallGraph(config);
                SootMethod dummy = edge.projectMainMethod;
                logger.info(() -> {
                    return createDummyMain$lambda$0(r1);
                });
                sootMethod = dummy;
                edge.clear();
                BaseBodyGeneratorFactory.instance = null;
            } catch (Exception e) {
                logger.error("create JavaEE dummy main failed!", e);
                sootMethod = null;
                edge.clear();
                BaseBodyGeneratorFactory.instance = null;
            }
            return sootMethod;
        } catch (Throwable th) {
            edge.clear();
            BaseBodyGeneratorFactory.instance = null;
            throw th;
        }
    }

    private static final Object createDummyMain$lambda$0(SootMethod $dummy) {
        return "JavaEE dummy main is " + $dummy;
    }

    @Override // cn.sast.framework.entries.IEntryPointProvider
    @NotNull
    public Flow<IEntryPointProvider.AnalyzeTask> getIterator() {
        return this.iterator;
    }
}
