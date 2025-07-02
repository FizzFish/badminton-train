package cn.sast.dataflow.infoflow.provider;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.AttributeName;
import com.feysh.corax.config.api.BuiltInField;
import com.feysh.corax.config.api.ClassField;
import com.feysh.corax.config.api.IClassField;
import com.feysh.corax.config.api.SubFields;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootField;

/* compiled from: FieldFinder.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\"\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018�� \u000e2\u00020\u0001:\u0003\u000e\u000f\u0010B%\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tB!\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\nJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006J\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u0006R\u0016\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/FieldFinder;", "", "baseTypes", "", "", "acc", "", "Lcom/feysh/corax/config/api/IClassField;", "<init>", "(Ljava/util/Set;Ljava/util/List;)V", "(Ljava/lang/String;Ljava/util/List;)V", "sootFields", "Lcn/sast/dataflow/infoflow/provider/FieldFinder$AccessPath;", "find", "Companion", "AccessPath", "Task", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nFieldFinder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FieldFinder.kt\ncn/sast/dataflow/infoflow/provider/FieldFinder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,82:1\n1#2:83\n*E\n"})
/* loaded from: FieldFinder.class */
public final class FieldFinder {

    @Nullable
    private final Set<String> baseTypes;

    @NotNull
    private final List<IClassField> acc;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(FieldFinder::logger$lambda$2);

    public FieldFinder(@Nullable Set<String> set, @NotNull List<? extends IClassField> list) {
        Intrinsics.checkNotNullParameter(list, "acc");
        this.baseTypes = set;
        this.acc = list;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
        	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    public FieldFinder(@org.jetbrains.annotations.Nullable java.lang.String r5, @org.jetbrains.annotations.NotNull java.util.List<? extends com.feysh.corax.config.api.IClassField> r6) {
        /*
            r4 = this;
            r0 = r6
            java.lang.String r1 = "acc"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r4
            r1 = r5
            r2 = r1
            if (r2 == 0) goto L1c
            r7 = r1
            r9 = r0
            r0 = 0
            r8 = r0
            r0 = r7
            java.util.Set r0 = kotlin.collections.SetsKt.setOf(r0)
            r1 = r9
            r2 = r0; r0 = r1; r1 = r2; 
            goto L1e
        L1c:
            r1 = 0
        L1e:
            r2 = r6
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.infoflow.provider.FieldFinder.<init>(java.lang.String, java.util.List):void");
    }

    /* compiled from: FieldFinder.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/FieldFinder$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "corax-data-flow"})
    /* loaded from: FieldFinder$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }

    private static final Unit logger$lambda$2() {
        return Unit.INSTANCE;
    }

    /* compiled from: FieldFinder.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020��2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/FieldFinder$AccessPath;", "", "sootField", "", "Lsoot/SootField;", "subFields", "", "<init>", "(Ljava/util/List;Z)V", "getSootField", "()Ljava/util/List;", "getSubFields", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: FieldFinder$AccessPath.class */
    public static final class AccessPath {

        @NotNull
        private final List<SootField> sootField;
        private final boolean subFields;

        @NotNull
        public final List<SootField> component1() {
            return this.sootField;
        }

        public final boolean component2() {
            return this.subFields;
        }

        @NotNull
        public final AccessPath copy(@NotNull List<? extends SootField> list, boolean subFields) {
            Intrinsics.checkNotNullParameter(list, "sootField");
            return new AccessPath(list, subFields);
        }

        public static /* synthetic */ AccessPath copy$default(AccessPath accessPath, List list, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                list = accessPath.sootField;
            }
            if ((i & 2) != 0) {
                z = accessPath.subFields;
            }
            return accessPath.copy(list, z);
        }

        @NotNull
        public String toString() {
            return "AccessPath(sootField=" + this.sootField + ", subFields=" + this.subFields + ")";
        }

        public int hashCode() {
            int result = this.sootField.hashCode();
            return (result * 31) + Boolean.hashCode(this.subFields);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AccessPath)) {
                return false;
            }
            AccessPath accessPath = (AccessPath) other;
            return Intrinsics.areEqual(this.sootField, accessPath.sootField) && this.subFields == accessPath.subFields;
        }

        public AccessPath(@NotNull List<? extends SootField> list, boolean subFields) {
            Intrinsics.checkNotNullParameter(list, "sootField");
            this.sootField = list;
            this.subFields = subFields;
        }

        @NotNull
        public final List<SootField> getSootField() {
            return this.sootField;
        }

        public final boolean getSubFields() {
            return this.subFields;
        }
    }

    @NotNull
    public final List<AccessPath> sootFields() {
        return find();
    }

    /* compiled from: FieldFinder.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��0\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0082\b\u0018��2\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\u000e\u001a\u00020��2\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n��\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/FieldFinder$Task;", "", "left", "", "Lsoot/SootField;", "right", "Lcom/feysh/corax/config/api/IClassField;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getLeft", "()Ljava/util/List;", "getRight", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "corax-data-flow"})
    /* loaded from: FieldFinder$Task.class */
    private static final class Task {

        @NotNull
        private final List<SootField> left;

        @NotNull
        private final List<IClassField> right;

        @NotNull
        public final List<SootField> component1() {
            return this.left;
        }

        @NotNull
        public final List<IClassField> component2() {
            return this.right;
        }

        @NotNull
        public final Task copy(@NotNull List<? extends SootField> list, @NotNull List<? extends IClassField> list2) {
            Intrinsics.checkNotNullParameter(list, "left");
            Intrinsics.checkNotNullParameter(list2, "right");
            return new Task(list, list2);
        }

        public static /* synthetic */ Task copy$default(Task task, List list, List list2, int i, Object obj) {
            if ((i & 1) != 0) {
                list = task.left;
            }
            if ((i & 2) != 0) {
                list2 = task.right;
            }
            return task.copy(list, list2);
        }

        @NotNull
        public String toString() {
            return "Task(left=" + this.left + ", right=" + this.right + ")";
        }

        public int hashCode() {
            int result = this.left.hashCode();
            return (result * 31) + this.right.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Task)) {
                return false;
            }
            Task task = (Task) other;
            return Intrinsics.areEqual(this.left, task.left) && Intrinsics.areEqual(this.right, task.right);
        }

        public Task(@NotNull List<? extends SootField> list, @NotNull List<? extends IClassField> list2) {
            Intrinsics.checkNotNullParameter(list, "left");
            Intrinsics.checkNotNullParameter(list2, "right");
            this.left = list;
            this.right = list2;
        }

        @NotNull
        public final List<SootField> getLeft() {
            return this.left;
        }

        @NotNull
        public final List<IClassField> getRight() {
            return this.right;
        }
    }

    @NotNull
    public final List<AccessPath> find() {
        if (this.acc.isEmpty()) {
            return CollectionsKt.listOf(new AccessPath(CollectionsKt.emptyList(), false));
        }
        List res = new ArrayList();
        Queue workList = new LinkedList();
        workList.add(new Task(CollectionsKt.emptyList(), this.acc));
        while (!workList.isEmpty()) {
            Task cur = (Task) workList.poll();
            ClassField classField = (IClassField) CollectionsKt.first(cur.getRight());
            if (classField instanceof ClassField) {
                SootField sf = FieldFinderKt.getSootField(classField);
                if (sf != null) {
                    List newRight = CollectionsKt.drop(cur.getRight(), 1);
                    if (newRight.isEmpty()) {
                        res.add(new AccessPath(CollectionsKt.plus(cur.getLeft(), sf), false));
                    } else {
                        workList.add(new Task(CollectionsKt.plus(cur.getLeft(), sf), newRight));
                    }
                }
            } else if (classField instanceof SubFields) {
                if (!(cur.getRight().size() == 1)) {
                    throw new IllegalArgumentException(("oops: " + cur).toString());
                }
                res.add(new AccessPath(cur.getLeft(), true));
            } else if (classField instanceof BuiltInField) {
                res.add(new AccessPath(cur.getLeft(), false));
            } else if (classField instanceof AttributeName) {
                res.add(new AccessPath(cur.getLeft(), false));
            }
        }
        return res;
    }
}
