package cn.sast.dataflow.infoflow.manager;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import soot.Scene;
import soot.jimple.Stmt;
import soot.jimple.infoflow.InfoflowManager;
import soot.jimple.infoflow.data.AccessPath;
import soot.jimple.infoflow.sourcesSinks.manager.ISourceSinkManager;
import soot.jimple.infoflow.sourcesSinks.manager.SinkInfo;
import soot.jimple.infoflow.sourcesSinks.manager.SourceInfo;

/* compiled from: GrabSourceSinkManager.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016JF\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n2\u000e\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000e\u001a\n \u000b*\u0004\u0018\u00010\u000f0\u000f2\u000e\u0010\u0010\u001a\n \u000b*\u0004\u0018\u00010\u00110\u0011H\u0096\u0001¢\u0006\u0002\u0010\u0012J6\u0010\u0013\u001a\n \u000b*\u0004\u0018\u00010\u00140\u00142\u000e\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\r0\r2\u000e\u0010\u000e\u001a\n \u000b*\u0004\u0018\u00010\u000f0\u000fH\u0096\u0001¢\u0006\u0002\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n��\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcn/sast/dataflow/infoflow/manager/GrabSourceSinkManager;", "Lsoot/jimple/infoflow/sourcesSinks/manager/ISourceSinkManager;", "delegate", "<init>", "(Lsoot/jimple/infoflow/sourcesSinks/manager/ISourceSinkManager;)V", "getDelegate", "()Lsoot/jimple/infoflow/sourcesSinks/manager/ISourceSinkManager;", "initialize", "", "getSinkInfo", "Lsoot/jimple/infoflow/sourcesSinks/manager/SinkInfo;", "kotlin.jvm.PlatformType", "p0", "Lsoot/jimple/Stmt;", "p1", "Lsoot/jimple/infoflow/InfoflowManager;", "p2", "Lsoot/jimple/infoflow/data/AccessPath;", "(Lsoot/jimple/Stmt;Lsoot/jimple/infoflow/InfoflowManager;Lsoot/jimple/infoflow/data/AccessPath;)Lsoot/jimple/infoflow/sourcesSinks/manager/SinkInfo;", "getSourceInfo", "Lsoot/jimple/infoflow/sourcesSinks/manager/SourceInfo;", "(Lsoot/jimple/Stmt;Lsoot/jimple/infoflow/InfoflowManager;)Lsoot/jimple/infoflow/sourcesSinks/manager/SourceInfo;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nGrabSourceSinkManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GrabSourceSinkManager.kt\ncn/sast/dataflow/infoflow/manager/GrabSourceSinkManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,16:1\n1#2:17\n*E\n"})
/* loaded from: GrabSourceSinkManager.class */
public final class GrabSourceSinkManager implements ISourceSinkManager {

    @NotNull
    private final ISourceSinkManager delegate;

    public SourceInfo getSourceInfo(Stmt p0, InfoflowManager p1) {
        return this.delegate.getSourceInfo(p0, p1);
    }

    public SinkInfo getSinkInfo(Stmt p0, InfoflowManager p1, AccessPath p2) {
        return this.delegate.getSinkInfo(p0, p1, p2);
    }

    public GrabSourceSinkManager(@NotNull ISourceSinkManager delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    @NotNull
    public final ISourceSinkManager getDelegate() {
        return this.delegate;
    }

    public void initialize() {
        if (!Scene.v().hasCallGraph()) {
            throw new IllegalArgumentException("have no call graph".toString());
        }
        this.delegate.initialize();
    }
}
