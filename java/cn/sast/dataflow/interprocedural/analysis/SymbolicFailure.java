package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AJimpleInterProceduralAnalysis.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n��\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��*\u0004\b��\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B)\u0012\u0006\u0010\u0003\u001a\u00028��\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0013\u001a\u00028��HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J>\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028��0��2\b\b\u0002\u0010\u0003\u001a\u00028��2\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0013\u0010\u0003\u001a\u00028��¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u0011¨\u0006 "}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/SymbolicFailure;", "V", "Lcn/sast/dataflow/interprocedural/analysis/MethodResult;", "symbolic", "concrete", "", "explicit", "", "inNestedMethod", "<init>", "(Ljava/lang/Object;Ljava/lang/Throwable;ZZ)V", "getSymbolic", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getConcrete", "()Ljava/lang/Throwable;", "getExplicit", "()Z", "getInNestedMethod", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Object;Ljava/lang/Throwable;ZZ)Lcn/sast/dataflow/interprocedural/analysis/SymbolicFailure;", "equals", "other", "", "hashCode", "", "toString", "", "corax-data-flow"})
/* loaded from: SymbolicFailure.class */
public final class SymbolicFailure<V> extends MethodResult<V> {
    private final V symbolic;

    @Nullable
    private final Throwable concrete;
    private final boolean explicit;
    private final boolean inNestedMethod;

    public final V component1() {
        return this.symbolic;
    }

    @Nullable
    public final Throwable component2() {
        return this.concrete;
    }

    public final boolean component3() {
        return this.explicit;
    }

    public final boolean component4() {
        return this.inNestedMethod;
    }

    @NotNull
    public final SymbolicFailure<V> copy(V v, @Nullable Throwable concrete, boolean explicit, boolean inNestedMethod) {
        return new SymbolicFailure<>(v, concrete, explicit, inNestedMethod);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SymbolicFailure copy$default(SymbolicFailure symbolicFailure, Object obj, Throwable th, boolean z, boolean z2, int i, Object obj2) {
        V v = obj;
        if ((i & 1) != 0) {
            v = symbolicFailure.symbolic;
        }
        if ((i & 2) != 0) {
            th = symbolicFailure.concrete;
        }
        if ((i & 4) != 0) {
            z = symbolicFailure.explicit;
        }
        if ((i & 8) != 0) {
            z2 = symbolicFailure.inNestedMethod;
        }
        return symbolicFailure.copy(v, th, z, z2);
    }

    @NotNull
    public String toString() {
        return "SymbolicFailure(symbolic=" + this.symbolic + ", concrete=" + this.concrete + ", explicit=" + this.explicit + ", inNestedMethod=" + this.inNestedMethod + ")";
    }

    public int hashCode() {
        int result = this.symbolic == null ? 0 : this.symbolic.hashCode();
        return (((((result * 31) + (this.concrete == null ? 0 : this.concrete.hashCode())) * 31) + Boolean.hashCode(this.explicit)) * 31) + Boolean.hashCode(this.inNestedMethod);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SymbolicFailure)) {
            return false;
        }
        SymbolicFailure symbolicFailure = (SymbolicFailure) other;
        return Intrinsics.areEqual(this.symbolic, symbolicFailure.symbolic) && Intrinsics.areEqual(this.concrete, symbolicFailure.concrete) && this.explicit == symbolicFailure.explicit && this.inNestedMethod == symbolicFailure.inNestedMethod;
    }

    public final V getSymbolic() {
        return this.symbolic;
    }

    @Nullable
    public final Throwable getConcrete() {
        return this.concrete;
    }

    public final boolean getExplicit() {
        return this.explicit;
    }

    public final boolean getInNestedMethod() {
        return this.inNestedMethod;
    }

    public SymbolicFailure(V v, @Nullable Throwable concrete, boolean explicit, boolean inNestedMethod) {
        super(null);
        this.symbolic = v;
        this.concrete = concrete;
        this.explicit = explicit;
        this.inNestedMethod = inNestedMethod;
    }
}
