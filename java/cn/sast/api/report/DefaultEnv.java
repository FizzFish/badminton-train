package cn.sast.api.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.report.Region;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.InvokeExpr;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010%\n\u0002\u0010��\n\u0002\b\u0003\b\u0016\u0018��2\u00020\u0001Bo\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\n\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b2\u0010!\"\u0004\b3\u0010#R \u00104\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020605X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b7\u00108¨\u00069"}, d2 = {"Lcn/sast/api/report/DefaultEnv;", "Lcn/sast/api/report/AbstractBugEnv;", "region", "Lcom/feysh/corax/config/api/report/Region$Mutable;", "fileName", "", "callSite", "Lsoot/Unit;", "callee", "Lsoot/SootMethod;", "container", "invokeExpr", "Lsoot/jimple/InvokeExpr;", "clazz", "Lsoot/SootClass;", "field", "Lsoot/SootField;", "method", "<init>", "(Lcom/feysh/corax/config/api/report/Region$Mutable;Ljava/lang/String;Lsoot/Unit;Lsoot/SootMethod;Lsoot/SootMethod;Lsoot/jimple/InvokeExpr;Lsoot/SootClass;Lsoot/SootField;Lsoot/SootMethod;)V", "getRegion", "()Lcom/feysh/corax/config/api/report/Region$Mutable;", "setRegion", "(Lcom/feysh/corax/config/api/report/Region$Mutable;)V", "getFileName", "()Ljava/lang/String;", "setFileName", "(Ljava/lang/String;)V", "getCallSite", "()Lsoot/Unit;", "setCallSite", "(Lsoot/Unit;)V", "getCallee", "()Lsoot/SootMethod;", "setCallee", "(Lsoot/SootMethod;)V", "getContainer", "setContainer", "getInvokeExpr", "()Lsoot/jimple/InvokeExpr;", "setInvokeExpr", "(Lsoot/jimple/InvokeExpr;)V", "getClazz", "()Lsoot/SootClass;", "setClazz", "(Lsoot/SootClass;)V", "getField", "()Lsoot/SootField;", "setField", "(Lsoot/SootField;)V", "getMethod", "setMethod", "args", "", "", "getArgs", "()Ljava/util/Map;", "corax-api"})
/* loaded from: DefaultEnv.class */
public class DefaultEnv extends AbstractBugEnv {

    @NotNull
    private Region.Mutable region;

    @Nullable
    private String fileName;

    @Nullable
    private Unit callSite;

    @Nullable
    private SootMethod callee;

    @Nullable
    private SootMethod container;

    @Nullable
    private InvokeExpr invokeExpr;

    @Nullable
    private SootClass clazz;

    @Nullable
    private SootField field;

    @Nullable
    private SootMethod method;

    @NotNull
    private final Map<Object, Object> args;

    public /* synthetic */ DefaultEnv(Region.Mutable mutable, String str, Unit unit, SootMethod sootMethod, SootMethod sootMethod2, InvokeExpr invokeExpr, SootClass sootClass, SootField sootField, SootMethod sootMethod3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutable, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : unit, (i & 8) != 0 ? null : sootMethod, (i & 16) != 0 ? null : sootMethod2, (i & 32) != 0 ? null : invokeExpr, (i & 64) != 0 ? null : sootClass, (i & 128) != 0 ? null : sootField, (i & 256) != 0 ? null : sootMethod3);
    }

    @NotNull
    public Region.Mutable getRegion() {
        return this.region;
    }

    public void setRegion(@NotNull Region.Mutable mutable) {
        Intrinsics.checkNotNullParameter(mutable, "<set-?>");
        this.region = mutable;
    }

    @Nullable
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(@Nullable String str) {
        this.fileName = str;
    }

    @Nullable
    public Unit getCallSite() {
        return this.callSite;
    }

    public void setCallSite(@Nullable Unit unit) {
        this.callSite = unit;
    }

    @Nullable
    public SootMethod getCallee() {
        return this.callee;
    }

    public void setCallee(@Nullable SootMethod sootMethod) {
        this.callee = sootMethod;
    }

    @Nullable
    public SootMethod getContainer() {
        return this.container;
    }

    public void setContainer(@Nullable SootMethod sootMethod) {
        this.container = sootMethod;
    }

    @Nullable
    public InvokeExpr getInvokeExpr() {
        return this.invokeExpr;
    }

    public void setInvokeExpr(@Nullable InvokeExpr invokeExpr) {
        this.invokeExpr = invokeExpr;
    }

    @Nullable
    public SootClass getClazz() {
        return this.clazz;
    }

    public void setClazz(@Nullable SootClass sootClass) {
        this.clazz = sootClass;
    }

    @Nullable
    public SootField getField() {
        return this.field;
    }

    public void setField(@Nullable SootField sootField) {
        this.field = sootField;
    }

    @Nullable
    public SootMethod getMethod() {
        return this.method;
    }

    public void setMethod(@Nullable SootMethod sootMethod) {
        this.method = sootMethod;
    }

    public DefaultEnv(@NotNull Region.Mutable region, @Nullable String fileName, @Nullable Unit callSite, @Nullable SootMethod callee, @Nullable SootMethod container, @Nullable InvokeExpr invokeExpr, @Nullable SootClass clazz, @Nullable SootField field, @Nullable SootMethod method) {
        Intrinsics.checkNotNullParameter(region, "region");
        this.region = region;
        this.fileName = fileName;
        this.callSite = callSite;
        this.callee = callee;
        this.container = container;
        this.invokeExpr = invokeExpr;
        this.clazz = clazz;
        this.field = field;
        this.method = method;
        this.args = new LinkedHashMap();
    }

    @NotNull
    public Map<Object, Object> getArgs() {
        return this.args;
    }
}
