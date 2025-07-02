package cn.sast.dataflow.interprocedural.check;

import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IStmt;
import com.feysh.corax.config.api.MLocal;
import com.feysh.corax.config.api.MParameter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootMethod;

/* compiled from: PathCompanion.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��6\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u000e\n\u0002\u0010\b\n��\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00112\u0006\u0010\f\u001a\u00020\u0012H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, d2 = {"Lcn/sast/dataflow/interprocedural/check/PrevCallStmtInfo;", "Lcn/sast/dataflow/interprocedural/check/ModelingStmtInfo;", "stmt", "Lcom/feysh/corax/config/api/IStmt;", "method", "Lsoot/SootMethod;", "<init>", "(Lcom/feysh/corax/config/api/IStmt;Lsoot/SootMethod;)V", "getMethod", "()Lsoot/SootMethod;", "getParameterNameByIndex", "", "index", "Lcom/feysh/corax/config/api/MLocal;", "filter", "Lkotlin/Function1;", "", "", "", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPathCompanion.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PathCompanion.kt\ncn/sast/dataflow/interprocedural/check/PrevCallStmtInfo\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,994:1\n808#2,11:995\n1#3:1006\n*S KotlinDebug\n*F\n+ 1 PathCompanion.kt\ncn/sast/dataflow/interprocedural/check/PrevCallStmtInfo\n*L\n472#1:995,11\n*E\n"})
/* loaded from: PrevCallStmtInfo.class */
public final class PrevCallStmtInfo extends ModelingStmtInfo {

    @NotNull
    private final SootMethod method;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrevCallStmtInfo(@NotNull IStmt stmt, @NotNull SootMethod method) {
        super(stmt);
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        Intrinsics.checkNotNullParameter(method, "method");
        this.method = method;
    }

    @NotNull
    public final SootMethod getMethod() {
        return this.method;
    }

    @Override // cn.sast.dataflow.interprocedural.check.ModelingStmtInfo
    @Nullable
    public Object getParameterNameByIndex(@NotNull MLocal index, @NotNull Function1<Object, Boolean> function1) {
        Intrinsics.checkNotNullParameter(index, "index");
        Intrinsics.checkNotNullParameter(function1, "filter");
        if (!(index instanceof MParameter)) {
            return String.valueOf(((Boolean) function1.invoke(index)).booleanValue() ? index : null);
        }
        String parameterNameByIndex = getParameterNameByIndex(((MParameter) index).getIndex());
        if (parameterNameByIndex == null) {
            return null;
        }
        if (getFirstParamIndex() == null) {
            setFirstParamIndex(Integer.valueOf(((MParameter) index).getIndex()));
        }
        if (((Boolean) function1.invoke(parameterNameByIndex)).booleanValue()) {
            return parameterNameByIndex;
        }
        return null;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockSplitter
        jadx.core.utils.exceptions.JadxRuntimeException: Unexpected missing predecessor for block: B:24:0x00a5
        	at jadx.core.dex.visitors.blocks.BlockSplitter.addTempConnectionsForExcHandlers(BlockSplitter.java:280)
        	at jadx.core.dex.visitors.blocks.BlockSplitter.visit(BlockSplitter.java:79)
        */
    @Override // cn.sast.dataflow.interprocedural.check.ModelingStmtInfo
    @org.jetbrains.annotations.Nullable
    public java.lang.String getParameterNameByIndex(int r5) {
        /*
            r4 = this;
            r0 = r5
            r1 = -1
            if (r0 != r1) goto L16
            r0 = r4
            soot.SootMethod r0 = r0.method
            soot.SootClass r0 = r0.getDeclaringClass()
            java.lang.String r0 = r0.getShortName()
            java.lang.String r0 = r0 + ".this"
            return r0
        L16:
            r0 = r5
            if (r0 < 0) goto Ld4
            r0 = r5
            r1 = r4
            soot.SootMethod r1 = r1.method
            int r1 = r1.getParameterCount()
            if (r0 >= r1) goto Ld4
            r0 = r4
            soot.SootMethod r0 = r0.method
            java.util.List r0 = r0.getTags()
            r1 = r0
            java.lang.String r2 = "getTags(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            r7 = r0
            r0 = 0
            r8 = r0
            r0 = r7
            r9 = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = r0
            r1.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r9
            java.util.Iterator r0 = r0.iterator()
            r12 = r0
        L54:
            r0 = r12
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L7c
            r0 = r12
            java.lang.Object r0 = r0.next()
            r13 = r0
            r0 = r13
            boolean r0 = r0 instanceof soot.tagkit.ParamNamesTag
            if (r0 == 0) goto L54
            r0 = r10
            r1 = r13
            boolean r0 = r0.add(r1)
            goto L54
        L7c:
            r0 = r10
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = kotlin.collections.CollectionsKt.firstOrNull(r0)
            soot.tagkit.ParamNamesTag r0 = (soot.tagkit.ParamNamesTag) r0
            r1 = r0
            if (r1 == 0) goto L93
            java.util.List r0 = r0.getNames()
            r1 = r0
            if (r1 != 0) goto L97
        L93:
        L94:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L97:
            r6 = r0
            r0 = r6
            r1 = r5
            java.lang.Object r0 = kotlin.collections.CollectionsKt.getOrNull(r0, r1)
            java.lang.String r0 = (java.lang.String) r0
            r1 = r0
            if (r1 != 0) goto Ld3
        La6:
            r0 = r4
            soot.SootMethod r0 = r0.method     // Catch: java.lang.RuntimeException -> Lce
            soot.Body r0 = cn.sast.api.util.SootUtilsKt.getActiveBodyOrNull(r0)     // Catch: java.lang.RuntimeException -> Lce
            r1 = r0
            if (r1 == 0) goto Lc8
            r1 = r5
            soot.Local r0 = r0.getParameterLocal(r1)     // Catch: java.lang.RuntimeException -> Lce
            r1 = r0
            if (r1 == 0) goto Lc8
            r10 = r0
            r0 = 0
            r11 = r0
            r0 = r10
            java.lang.String r0 = "local var " + r0     // Catch: java.lang.RuntimeException -> Lce
            goto Lca
        Lc8:
            r0 = 0
        Lca:
            r7 = r0
            goto Ld2
        Lce:
            r8 = move-exception
            r0 = 0
            r7 = r0
        Ld2:
            r0 = r7
        Ld3:
            return r0
        Ld4:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.check.PrevCallStmtInfo.getParameterNameByIndex(int):java.lang.String");
    }
}
