package cn.sast.idfa.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: InterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��,\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010��\n��\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018��*\u0004\b��\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00028\u00022\u0006\u0010\f\u001a\u00028��H&¢\u0006\u0002\u0010\rJ\u0015\u0010\u000e\u001a\u00028\u00022\u0006\u0010\u000f\u001a\u00028\u0002H&¢\u0006\u0002\u0010\rJ\u0016\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028��0\u0013H&J\u001d\u0010\u0014\u001a\u00028\u00022\u0006\u0010\u0015\u001a\u00028\u00022\u0006\u0010\u0016\u001a\u00028\u0002H&¢\u0006\u0002\u0010\u0017J\u001d\u0010\u0018\u001a\u00028\u00022\u0006\u0010\u0015\u001a\u00028\u00022\u0006\u0010\u0016\u001a\u00028\u0002H&¢\u0006\u0002\u0010\u0017J\u001d\u0010\u0019\u001a\u00028\u00022\u0006\u0010\u001a\u001a\u00028\u00022\u0006\u0010\u001b\u001a\u00028\u0002H&¢\u0006\u0002\u0010\u0017J\u0014\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028��\u0012\u0004\u0012\u00028\u00010\u001dH&J\r\u0010\u001e\u001a\u00028\u0002H&¢\u0006\u0002\u0010\u001fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006 "}, d2 = {"Lcn/sast/idfa/analysis/InterProceduralAnalysis;", "M", "N", "A", "", "reverse", "", "<init>", "(Z)V", "getReverse", "()Z", "boundaryValue", "entryPoint", "(Ljava/lang/Object;)Ljava/lang/Object;", "copy", "src", "doAnalysis", "", "entries", "", "meet", "op1", "op2", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "shallowMeet", "merge", "local", "ret", "programRepresentation", "Lcn/sast/idfa/analysis/ProgramRepresentation;", "bottomValue", "()Ljava/lang/Object;", "corax-idfa-framework"})
/* loaded from: InterProceduralAnalysis.class */
public abstract class InterProceduralAnalysis<M, N, A> {
    private final boolean reverse;

    public abstract A boundaryValue(M m);

    public abstract A copy(A a);

    public abstract void doAnalysis(@NotNull Collection<? extends M> collection);

    public abstract A meet(A a, A a2);

    public abstract A shallowMeet(A a, A a2);

    public abstract A merge(A a, A a2);

    @NotNull
    public abstract ProgramRepresentation<M, N> programRepresentation();

    public abstract A bottomValue();

    public InterProceduralAnalysis(boolean reverse) {
        this.reverse = reverse;
        Context.Companion.reset();
    }

    protected final boolean getReverse() {
        return this.reverse;
    }
}
