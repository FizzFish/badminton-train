package cn.sast.framework.rewrite;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.AnalysisCache;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.cache.analysis.SootMethodExtend;
import com.feysh.corax.config.api.report.Region;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.nodeTypes.NodeWithRange;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import soot.Body;
import soot.BodyTransformer;
import soot.PatchingChain;
import soot.Unit;
import soot.jimple.IdentityStmt;
import soot.jimple.ParameterRef;
import soot.jimple.ThisRef;
import soot.options.Options;
import soot.tagkit.Host;
import soot.tagkit.SourceLnPosTag;

/* compiled from: IdentityStmt2MethodParamRegion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010$\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018�� \u00142\u00020\u0001:\u0001\u0014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\u000fH\u0014J\u0016\u0010\u0010\u001a\u00020\t*\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcn/sast/framework/rewrite/IdentityStmt2MethodParamRegion;", "Lsoot/BodyTransformer;", "info", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "<init>", "(Lcom/feysh/corax/cache/analysis/SootInfoCache;)V", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "internalTransform", "", "b", "Lsoot/Body;", "phaseName", "", "options", "", "addTag", "Lsoot/tagkit/Host;", "region", "Lcom/feysh/corax/config/api/report/Region;", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nIdentityStmt2MethodParamRegion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IdentityStmt2MethodParamRegion.kt\ncn/sast/framework/rewrite/IdentityStmt2MethodParamRegion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Region.kt\ncom/feysh/corax/config/api/report/Region\n*L\n1#1,61:1\n1#2:62\n1#2:64\n59#3:63\n57#3:65\n57#3:66\n*S KotlinDebug\n*F\n+ 1 IdentityStmt2MethodParamRegion.kt\ncn/sast/framework/rewrite/IdentityStmt2MethodParamRegion\n*L\n40#1:64\n40#1:63\n40#1:65\n47#1:66\n*E\n"})
/* loaded from: IdentityStmt2MethodParamRegion.class */
public final class IdentityStmt2MethodParamRegion extends BodyTransformer {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final SootInfoCache info;

    @NotNull
    public static final String phase = "jb.identityStmt2MethodParamRegion";

    @NotNull
    private static final Logger logger;

    public IdentityStmt2MethodParamRegion(@NotNull SootInfoCache info) {
        Intrinsics.checkNotNullParameter(info, "info");
        this.info = info;
    }

    @NotNull
    public final SootInfoCache getInfo() {
        return this.info;
    }

    protected void internalTransform(@NotNull Body b, @NotNull String phaseName, @NotNull Map<String, String> map) {
        CallableDeclaration decl;
        Region regionInvoke;
        NodeWithRange name;
        Region this_$iv;
        Intrinsics.checkNotNullParameter(b, "b");
        Intrinsics.checkNotNullParameter(phaseName, "phaseName");
        Intrinsics.checkNotNullParameter(map, "options");
        if (Options.v().verbose()) {
            logger.debug("[" + b.getMethod().getName() + "] Rewrite IdentityStmt region ...");
        }
        PatchingChain units = b.getUnits();
        Intrinsics.checkNotNullExpressionValue(units, "getUnits(...)");
        PatchingChain units2 = units;
        if (units2.isEmpty()) {
            return;
        }
        if (b.getMethod().isStatic() && b.getMethod().getParameterCount() == 0) {
            return;
        }
        AnalysisCache.G g = AnalysisCache.G.INSTANCE;
        Host method = b.getMethod();
        Intrinsics.checkNotNullExpressionValue(method, "getMethod(...)");
        SootMethodExtend method2 = g.sootHost2decl(method);
        if (method2 == null || (decl = method2.getDecl()) == null) {
            return;
        }
        Iterator it = units2.iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            IdentityStmt identityStmt = (Unit) it.next();
            if (identityStmt instanceof IdentityStmt) {
                ParameterRef rightOp = identityStmt.getRightOp();
                if (rightOp instanceof ThisRef) {
                    IdentityStmt2MethodParamRegion identityStmt2MethodParamRegion = this;
                    Host host = (Host) identityStmt;
                    NodeWithRange it2 = method2.getNameDecl();
                    if (it2 != null) {
                        identityStmt2MethodParamRegion = identityStmt2MethodParamRegion;
                        host = host;
                        regionInvoke = Region.Companion.invoke(it2);
                    } else {
                        regionInvoke = null;
                    }
                    identityStmt2MethodParamRegion.addTag(host, regionInvoke);
                } else if (rightOp instanceof ParameterRef) {
                    List parameters = decl.getParameters();
                    Intrinsics.checkNotNullExpressionValue(parameters, "getParameters(...)");
                    Parameter parameter = (Parameter) CollectionsKt.getOrNull(parameters, rightOp.getIndex());
                    if (parameter != null && (name = parameter.getName()) != null && (this_$iv = Region.Companion.invoke(name)) != null) {
                        Region region = this_$iv.startLine >= 0 ? this_$iv : null;
                        if (region != null) {
                            Region region2 = region;
                            addTag((Host) identityStmt, region2);
                        }
                    }
                }
            }
        }
    }

    private final void addTag(Host $this$addTag, Region region) {
        if (region != null) {
            if ((region.startLine >= 0) && Options.v().keep_line_number()) {
                $this$addTag.removeTag("SourceLnPosTag");
                $this$addTag.removeTag("SourceLnPosTag");
                $this$addTag.addTag(new SourceLnPosTag(region.startLine, region.getEndLine(), region.startColumn, region.getEndColumn()));
            }
        }
    }

    /* compiled from: IdentityStmt2MethodParamRegion.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n��¨\u0006\b"}, d2 = {"Lcn/sast/framework/rewrite/IdentityStmt2MethodParamRegion$Companion;", "", "<init>", "()V", "phase", "", "logger", "Lorg/slf4j/Logger;", "corax-framework"})
    /* loaded from: IdentityStmt2MethodParamRegion$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    static {
        Logger logger2 = LoggerFactory.getLogger(IdentityStmt2MethodParamRegion.class);
        Intrinsics.checkNotNullExpressionValue(logger2, "getLogger(...)");
        logger = logger2;
    }
}
