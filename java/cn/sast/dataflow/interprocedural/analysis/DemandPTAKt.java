package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import soot.Local;
import soot.PointsToAnalysis;
import soot.PointsToSet;
import soot.Scene;
import soot.Value;
import soot.jimple.ArrayRef;
import soot.jimple.InstanceFieldRef;
import soot.jimple.StaticFieldRef;
import soot.jimple.infoflow.data.AccessPath;

/* compiled from: DemandPTA.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0012\u0010��\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u001a\u0010��\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\"\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000b\u001a\u00020\u0007H\u0002¨\u0006\f"}, d2 = {"getPointsToSet", "Lsoot/PointsToSet;", "accessPath", "Lsoot/jimple/infoflow/data/AccessPath;", "pta", "Lsoot/PointsToAnalysis;", "targetValue", "Lsoot/Value;", "isAliasedAtStmt", "", "ptsTaint", "v", "corax-data-flow"})
/* loaded from: DemandPTAKt.class */
public final class DemandPTAKt {
    private static final PointsToSet getPointsToSet(AccessPath accessPath) {
        if (accessPath.isLocal()) {
            return Scene.v().getPointsToAnalysis().reachingObjects(accessPath.getPlainValue());
        }
        if (accessPath.isInstanceFieldRef()) {
            return Scene.v().getPointsToAnalysis().reachingObjects(accessPath.getPlainValue(), accessPath.getFirstField());
        }
        if (accessPath.isStaticFieldRef()) {
            return Scene.v().getPointsToAnalysis().reachingObjects(accessPath.getFirstField());
        }
        throw new RuntimeException("Unexepected access path type");
    }

    private static final PointsToSet getPointsToSet(PointsToAnalysis pta, Value targetValue) {
        PointsToSet pointsToSetReachingObjects;
        PointsToSet pointsToSet;
        PointsToAnalysis pta2 = Scene.v().getPointsToAnalysis();
        Intrinsics.checkNotNull(pta2);
        synchronized (pta2) {
            if (targetValue instanceof Local) {
                pointsToSetReachingObjects = pta2.reachingObjects((Local) targetValue);
            } else if (targetValue instanceof InstanceFieldRef) {
                Local base = ((InstanceFieldRef) targetValue).getBase();
                Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
                pointsToSetReachingObjects = pta2.reachingObjects(base, ((InstanceFieldRef) targetValue).getField());
            } else if (targetValue instanceof StaticFieldRef) {
                pointsToSetReachingObjects = pta2.reachingObjects(((StaticFieldRef) targetValue).getField());
            } else if (targetValue instanceof ArrayRef) {
                Local base2 = ((ArrayRef) targetValue).getBase();
                Intrinsics.checkNotNull(base2, "null cannot be cast to non-null type soot.Local");
                pointsToSetReachingObjects = pta2.reachingObjects(base2);
            } else {
                pointsToSetReachingObjects = null;
            }
            pointsToSet = pointsToSetReachingObjects;
        }
        return pointsToSet;
    }

    private static final boolean isAliasedAtStmt(PointsToAnalysis pta, PointsToSet ptsTaint, Value v) {
        PointsToSet ptsRight;
        return (ptsTaint == null || (ptsRight = getPointsToSet(pta, v)) == null || !ptsTaint.hasNonEmptyIntersection(ptsRight)) ? false : true;
    }
}
