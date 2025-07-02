package cn.sast.framework.engine;

import cn.sast.api.report.ClassResInfo;
import cn.sast.api.report.DefaultEnv;
import cn.sast.api.report.IBugResInfo;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.AnalysisCache;
import com.feysh.corax.cache.AnalysisDataFactory;
import com.feysh.corax.cache.analysis.SootHostExtend;
import com.feysh.corax.cache.analysis.SootInfoCache;
import com.feysh.corax.config.api.IClassCheckPoint;
import com.feysh.corax.config.api.IFieldCheckPoint;
import com.feysh.corax.config.api.IMethodCheckPoint;
import com.feysh.corax.config.api.report.Region;
import com.github.javaparser.ast.body.BodyDeclaration;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.tagkit.AbstractHost;
import soot.tagkit.Host;

/* compiled from: PreAnalysisImpl.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018��2\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ!\u0010\u0011\u001a\u00020\u00122\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u0016H\u0016J!\u0010\u0017\u001a\u00020\u00122\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00120\u0014¢\u0006\u0002\b\u0016H\u0016J\u001b\u0010'\u001a\b\u0012\u0002\b\u0003\u0018\u00010(*\u00020\u00052\u0006\u0010)\u001a\u00020*H\u0096\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0019\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001eX\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u001f\u0010 R\u001b\u0010!\u001a\u00020\"8PX\u0090\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b#\u0010$R\u0012\u0010+\u001a\u00020,X\u0096\u0005¢\u0006\u0006\u001a\u0004\b-\u0010.R\u001a\u0010/\u001a\u0004\u0018\u000100*\u0002018VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b2\u00103R\u001e\u00104\u001a\u000e\u0012\u0006\u0012\u0004\u0018\u00010005j\u0002`6X\u0096\u0005¢\u0006\u0006\u001a\u0004\b7\u00108R\u0018\u00109\u001a\u00020**\u00020:8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b;\u0010<R\u0018\u0010=\u001a\u00020**\u00020:8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b>\u0010<R\u0018\u0010?\u001a\u00020**\u00020:8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b@\u0010<R\u0018\u0010A\u001a\u00020**\u00020:8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bB\u0010<¨\u0006C"}, d2 = {"Lcn/sast/framework/engine/ClassCheckPoint;", "Lcom/feysh/corax/config/api/IClassCheckPoint;", "Lcn/sast/framework/engine/CheckPoint;", "Lcom/feysh/corax/cache/analysis/SootInfoCache;", "sootClass", "Lsoot/SootClass;", "info", "<init>", "(Lsoot/SootClass;Lcom/feysh/corax/cache/analysis/SootInfoCache;)V", "getSootClass", "()Lsoot/SootClass;", "getInfo", "()Lcom/feysh/corax/cache/analysis/SootInfoCache;", "className", "", "getClassName", "()Ljava/lang/String;", "eachMethod", "", "block", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/IMethodCheckPoint;", "Lkotlin/ExtensionFunctionType;", "eachField", "Lcom/feysh/corax/config/api/IFieldCheckPoint;", "region", "Lcom/feysh/corax/config/api/report/Region;", "getRegion", "()Lcom/feysh/corax/config/api/report/Region;", "file", "Lcn/sast/api/report/IBugResInfo;", "getFile", "()Lcn/sast/api/report/IBugResInfo;", "env", "Lcn/sast/api/report/DefaultEnv;", "getEnv$corax_framework", "()Lcn/sast/api/report/DefaultEnv;", "env$delegate", "Lkotlin/Lazy;", "getMemberAtLine", "Lcom/github/javaparser/ast/body/BodyDeclaration;", "ln", "", "cache", "Lcom/feysh/corax/cache/AnalysisCache;", "getCache", "()Lcom/feysh/corax/cache/AnalysisCache;", "ext", "Lcom/feysh/corax/cache/analysis/SootHostExtend;", "Lsoot/tagkit/Host;", "getExt", "(Lsoot/tagkit/Host;)Lcom/feysh/corax/cache/analysis/SootHostExtend;", "hostKey", "Lcom/feysh/corax/cache/AnalysisDataFactory$Key;", "Lcom/feysh/corax/cache/analysis/SootHostExtInfoFKey;", "getHostKey", "()Lcom/feysh/corax/cache/AnalysisDataFactory$Key;", "javaNameSourceEndColumnNumber", "Lsoot/tagkit/AbstractHost;", "getJavaNameSourceEndColumnNumber", "(Lsoot/tagkit/AbstractHost;)I", "javaNameSourceEndLineNumber", "getJavaNameSourceEndLineNumber", "javaNameSourceStartColumnNumber", "getJavaNameSourceStartColumnNumber", "javaNameSourceStartLineNumber", "getJavaNameSourceStartLineNumber", "corax-framework"})
/* loaded from: ClassCheckPoint.class */
public final class ClassCheckPoint extends CheckPoint implements IClassCheckPoint, SootInfoCache {

    @NotNull
    private final SootClass sootClass;

    @NotNull
    private final SootInfoCache info;

    @NotNull
    private final IBugResInfo file;

    @NotNull
    private final Lazy env$delegate;

    @Nullable
    public BodyDeclaration<?> getMemberAtLine(@NotNull SootClass $this$getMemberAtLine, int ln) {
        Intrinsics.checkNotNullParameter($this$getMemberAtLine, "<this>");
        return this.info.getMemberAtLine($this$getMemberAtLine, ln);
    }

    @NotNull
    public AnalysisCache getCache() {
        return this.info.getCache();
    }

    @NotNull
    public AnalysisDataFactory.Key<SootHostExtend> getHostKey() {
        return this.info.getHostKey();
    }

    @Nullable
    public SootHostExtend getExt(@NotNull Host $this$ext) {
        Intrinsics.checkNotNullParameter($this$ext, "<this>");
        return this.info.getExt($this$ext);
    }

    public int getJavaNameSourceStartLineNumber(@NotNull AbstractHost $this$javaNameSourceStartLineNumber) {
        Intrinsics.checkNotNullParameter($this$javaNameSourceStartLineNumber, "<this>");
        return this.info.getJavaNameSourceStartLineNumber($this$javaNameSourceStartLineNumber);
    }

    public int getJavaNameSourceStartColumnNumber(@NotNull AbstractHost $this$javaNameSourceStartColumnNumber) {
        Intrinsics.checkNotNullParameter($this$javaNameSourceStartColumnNumber, "<this>");
        return this.info.getJavaNameSourceStartColumnNumber($this$javaNameSourceStartColumnNumber);
    }

    public int getJavaNameSourceEndLineNumber(@NotNull AbstractHost $this$javaNameSourceEndLineNumber) {
        Intrinsics.checkNotNullParameter($this$javaNameSourceEndLineNumber, "<this>");
        return this.info.getJavaNameSourceEndLineNumber($this$javaNameSourceEndLineNumber);
    }

    public int getJavaNameSourceEndColumnNumber(@NotNull AbstractHost $this$javaNameSourceEndColumnNumber) {
        Intrinsics.checkNotNullParameter($this$javaNameSourceEndColumnNumber, "<this>");
        return this.info.getJavaNameSourceEndColumnNumber($this$javaNameSourceEndColumnNumber);
    }

    @NotNull
    public Sequence<SootClass> getSuperClasses() {
        return IClassCheckPoint.DefaultImpls.getSuperClasses(this);
    }

    @NotNull
    public Sequence<SootClass> getSuperInterfaces() {
        return IClassCheckPoint.DefaultImpls.getSuperInterfaces(this);
    }

    @NotNull
    public SootClass getSootClass() {
        return this.sootClass;
    }

    @NotNull
    public final SootInfoCache getInfo() {
        return this.info;
    }

    public ClassCheckPoint(@NotNull SootClass sootClass, @NotNull SootInfoCache info) {
        Intrinsics.checkNotNullParameter(sootClass, "sootClass");
        Intrinsics.checkNotNullParameter(info, "info");
        this.sootClass = sootClass;
        this.info = info;
        this.file = new ClassResInfo(getSootClass());
        this.env$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, () -> {
            return env_delegate$lambda$0(r2);
        });
    }

    @NotNull
    public String getClassName() {
        String name = getSootClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return name;
    }

    public void eachMethod(@NotNull Function1<? super IMethodCheckPoint, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        for (SootMethod method : getSootClass().getMethods()) {
            Intrinsics.checkNotNull(method);
            MethodCheckPoint methodCheckPoint = new MethodCheckPoint(method, this.info);
            function1.invoke(methodCheckPoint);
        }
    }

    public void eachField(@NotNull Function1<? super IFieldCheckPoint, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        Iterator it = getSootClass().getFields().iterator();
        Intrinsics.checkNotNullExpressionValue(it, "iterator(...)");
        while (it.hasNext()) {
            SootField field = (SootField) it.next();
            Intrinsics.checkNotNull(field);
            FieldCheckPoint fieldCheckPoint = new FieldCheckPoint(field, this.info);
            function1.invoke(fieldCheckPoint);
        }
    }

    @NotNull
    public Region getRegion() {
        Region regionInvoke = Region.Companion.invoke(this, getSootClass());
        return regionInvoke == null ? Region.Companion.getERROR() : regionInvoke;
    }

    @Override // cn.sast.framework.engine.CheckPoint
    @NotNull
    public IBugResInfo getFile() {
        return this.file;
    }

    @Override // cn.sast.framework.engine.CheckPoint
    @NotNull
    public DefaultEnv getEnv$corax_framework() {
        return (DefaultEnv) this.env$delegate.getValue();
    }

    private static final DefaultEnv env_delegate$lambda$0(ClassCheckPoint this$0) {
        return new DefaultEnv(this$0.getRegion().getMutable(), null, null, null, null, null, this$0.getSootClass(), null, null, 446, null);
    }
}
