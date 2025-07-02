package cn.sast.framework.incremental;

import cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles;
import cn.sast.api.incremental.ModifyInfoFactory;
import cn.sast.api.util.OthersKt;
import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.XDecl;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import org.eclipse.jgit.diff.DiffEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\u001e\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018��2\u00020\u0001:\u0004\u0014\u0015\u0016\u0017B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\r\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016¨\u0006\u0018"}, d2 = {"Lcn/sast/framework/incremental/ModifyInfoFactoryImpl;", "Lcn/sast/api/incremental/ModifyInfoFactory;", "<init>", "()V", "toDecl", "Lcom/feysh/corax/config/api/XDecl;", "target", "", "getPatchedDeclsByDiff", "", "diff", "Lorg/eclipse/jgit/diff/DiffEntry;", "getSubDecls", "decl", "createInterProceduralAnalysisDependsGraph", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;", "getScanAction", "Lcom/feysh/corax/config/api/rules/ProcessRule$ScanAction;", "createSimpleDeclAnalysisDependsGraph", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "FileDecl", "ClassDecl", "MethodDecl", "FieldDecl", "corax-framework"})
@SourceDebugExtension({"SMAP\nIncrementalAnalyzeImplByChangeFiles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/ModifyInfoFactoryImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,395:1\n1628#2,3:396\n1628#2,3:399\n*S KotlinDebug\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/ModifyInfoFactoryImpl\n*L\n86#1:396,3\n87#1:399,3\n*E\n"})
/* loaded from: ModifyInfoFactoryImpl.class */
public final class ModifyInfoFactoryImpl implements ModifyInfoFactory {

    /* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\u0004\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\t¢\u0006\u0004\b\u0004\u0010\nJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/incremental/ModifyInfoFactoryImpl$FileDecl;", "Lcom/feysh/corax/config/api/XDecl;", "target", "", "<init>", "(Ljava/lang/String;)V", "path", "Lcn/sast/common/IResource;", "(Lcn/sast/common/IResource;)V", "Ljava/nio/file/Path;", "(Ljava/nio/file/Path;)V", "getTarget", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "corax-framework"})
    /* loaded from: ModifyInfoFactoryImpl$FileDecl.class */
    public static final class FileDecl extends XDecl {

        @NotNull
        private final String target;

        @NotNull
        public final String component1() {
            return this.target;
        }

        @NotNull
        public final FileDecl copy(@NotNull String target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return new FileDecl(target);
        }

        public static /* synthetic */ FileDecl copy$default(FileDecl fileDecl, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = fileDecl.target;
            }
            return fileDecl.copy(str);
        }

        @NotNull
        public String toString() {
            return "FileDecl(target=" + this.target + ")";
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FileDecl) && Intrinsics.areEqual(this.target, ((FileDecl) other).target);
        }

        public FileDecl(@NotNull String target) {
            Intrinsics.checkNotNullParameter(target, "target");
            this.target = target;
        }

        @NotNull
        public final String getTarget() {
            return this.target;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public FileDecl(@NotNull IResource path) {
            this(path.getAbsolute().getNormalize().toString());
            Intrinsics.checkNotNullParameter(path, "path");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public FileDecl(@NotNull Path path) {
            this(Resource.INSTANCE.of(path));
            Intrinsics.checkNotNullParameter(path, "path");
        }
    }

    /* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcn/sast/framework/incremental/ModifyInfoFactoryImpl$ClassDecl;", "Lcom/feysh/corax/config/api/XDecl;", "target", "Lsoot/SootClass;", "<init>", "(Lsoot/SootClass;)V", "getTarget", "()Lsoot/SootClass;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-framework"})
    /* loaded from: ModifyInfoFactoryImpl$ClassDecl.class */
    public static final class ClassDecl extends XDecl {

        @NotNull
        private final SootClass target;

        @NotNull
        public final SootClass component1() {
            return this.target;
        }

        @NotNull
        public final ClassDecl copy(@NotNull SootClass target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return new ClassDecl(target);
        }

        public static /* synthetic */ ClassDecl copy$default(ClassDecl classDecl, SootClass sootClass, int i, Object obj) {
            if ((i & 1) != 0) {
                sootClass = classDecl.target;
            }
            return classDecl.copy(sootClass);
        }

        @NotNull
        public String toString() {
            return "ClassDecl(target=" + this.target + ")";
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ClassDecl) && Intrinsics.areEqual(this.target, ((ClassDecl) other).target);
        }

        public ClassDecl(@NotNull SootClass target) {
            Intrinsics.checkNotNullParameter(target, "target");
            this.target = target;
        }

        @NotNull
        public final SootClass getTarget() {
            return this.target;
        }
    }

    /* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcn/sast/framework/incremental/ModifyInfoFactoryImpl$MethodDecl;", "Lcom/feysh/corax/config/api/XDecl;", "target", "Lsoot/SootMethod;", "<init>", "(Lsoot/SootMethod;)V", "getTarget", "()Lsoot/SootMethod;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-framework"})
    /* loaded from: ModifyInfoFactoryImpl$MethodDecl.class */
    public static final class MethodDecl extends XDecl {

        @NotNull
        private final SootMethod target;

        @NotNull
        public final SootMethod component1() {
            return this.target;
        }

        @NotNull
        public final MethodDecl copy(@NotNull SootMethod target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return new MethodDecl(target);
        }

        public static /* synthetic */ MethodDecl copy$default(MethodDecl methodDecl, SootMethod sootMethod, int i, Object obj) {
            if ((i & 1) != 0) {
                sootMethod = methodDecl.target;
            }
            return methodDecl.copy(sootMethod);
        }

        @NotNull
        public String toString() {
            return "MethodDecl(target=" + this.target + ")";
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof MethodDecl) && Intrinsics.areEqual(this.target, ((MethodDecl) other).target);
        }

        public MethodDecl(@NotNull SootMethod target) {
            Intrinsics.checkNotNullParameter(target, "target");
            this.target = target;
        }

        @NotNull
        public final SootMethod getTarget() {
            return this.target;
        }
    }

    /* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0010\u000e\n��\b\u0086\b\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcn/sast/framework/incremental/ModifyInfoFactoryImpl$FieldDecl;", "Lcom/feysh/corax/config/api/XDecl;", "target", "Lsoot/SootField;", "<init>", "(Lsoot/SootField;)V", "getTarget", "()Lsoot/SootField;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "corax-framework"})
    /* loaded from: ModifyInfoFactoryImpl$FieldDecl.class */
    public static final class FieldDecl extends XDecl {

        @NotNull
        private final SootField target;

        @NotNull
        public final SootField component1() {
            return this.target;
        }

        @NotNull
        public final FieldDecl copy(@NotNull SootField target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return new FieldDecl(target);
        }

        public static /* synthetic */ FieldDecl copy$default(FieldDecl fieldDecl, SootField sootField, int i, Object obj) {
            if ((i & 1) != 0) {
                sootField = fieldDecl.target;
            }
            return fieldDecl.copy(sootField);
        }

        @NotNull
        public String toString() {
            return "FieldDecl(target=" + this.target + ")";
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FieldDecl) && Intrinsics.areEqual(this.target, ((FieldDecl) other).target);
        }

        public FieldDecl(@NotNull SootField target) {
            Intrinsics.checkNotNullParameter(target, "target");
            this.target = target;
        }

        @NotNull
        public final SootField getTarget() {
            return this.target;
        }
    }

    @Override // cn.sast.api.incremental.ModifyInfoFactory
    @NotNull
    public XDecl toDecl(@NotNull Object target) {
        Intrinsics.checkNotNullParameter(target, "target");
        if (target instanceof SootClass) {
            return new ClassDecl((SootClass) target);
        }
        if (target instanceof SootMethod) {
            return new MethodDecl((SootMethod) target);
        }
        if (target instanceof SootField) {
            return new FieldDecl((SootField) target);
        }
        if (target instanceof Path) {
            return new FileDecl((Path) target);
        }
        if (target instanceof File) {
            Path path = ((File) target).toPath();
            Intrinsics.checkNotNullExpressionValue(path, "toPath(...)");
            return new FileDecl(path);
        }
        if (target instanceof IResource) {
            return new FileDecl((IResource) target);
        }
        throw new IllegalStateException("not support yet".toString());
    }

    @Override // cn.sast.api.incremental.ModifyInfoFactory
    @NotNull
    public Collection<XDecl> getPatchedDeclsByDiff(@NotNull Object target, @NotNull DiffEntry diff) {
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(diff, "diff");
        return getSubDecls(toDecl(target));
    }

    @Override // cn.sast.api.incremental.ModifyInfoFactory
    @NotNull
    public Collection<XDecl> getSubDecls(@NotNull XDecl decl) {
        Intrinsics.checkNotNullParameter(decl, "decl");
        if (decl instanceof FileDecl) {
            return CollectionsKt.listOf(decl);
        }
        if (decl instanceof ClassDecl) {
            SootClass t = ((ClassDecl) decl).getTarget();
            List methods = t.getMethods();
            Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
            int size = methods.size();
            Collection fields = t.getFields();
            Intrinsics.checkNotNullExpressionValue(fields, "getFields(...)");
            ArrayList mutableSet = new ArrayList(size + fields.size() + 1);
            mutableSet.add(decl);
            Iterable methods2 = t.getMethods();
            Intrinsics.checkNotNullExpressionValue(methods2, "getMethods(...)");
            Iterable $this$mapTo$iv = methods2;
            for (Object item$iv : $this$mapTo$iv) {
                SootMethod it = (SootMethod) item$iv;
                Intrinsics.checkNotNull(it);
                mutableSet.add(toDecl(it));
            }
            Iterable fields2 = t.getFields();
            Intrinsics.checkNotNullExpressionValue(fields2, "getFields(...)");
            Iterable $this$mapTo$iv2 = fields2;
            for (Object item$iv2 : $this$mapTo$iv2) {
                SootField it2 = (SootField) item$iv2;
                Intrinsics.checkNotNull(it2);
                mutableSet.add(toDecl(it2));
            }
            return mutableSet;
        }
        if (!(decl instanceof MethodDecl) && !(decl instanceof FieldDecl)) {
            throw new IllegalStateException(("invalid type of " + decl).toString());
        }
        return CollectionsKt.listOf(decl);
    }

    /* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��O\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001��\b\n\u0018��2\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0015\u0010\u0007\u001a\u00020\u0004*\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0096\u0005J!\u0010\u0007\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\b0\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0096\u0005J\u0011\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\bH\u0096\u0001J\u0011\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0010H\u0096\u0001J\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\u0006\u0010\u000e\u001a\u00020\bH\u0096\u0001J\u001d\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0096\u0001J\u0011\u0010\u0015\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0010H\u0096\u0001J\u001f\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0096\u0001R\u0012\u0010\u001a\u001a\u00020\u001bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"cn/sast/framework/incremental/ModifyInfoFactoryImpl$createInterProceduralAnalysisDependsGraph$1", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$InterProceduralAnalysisDependsGraph;", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$IDependsGraph;", "update", "", "cg", "Lsoot/jimple/toolkits/callgraph/CallGraph;", "dependsOn", "Lcom/feysh/corax/config/api/XDecl;", "dep", "", "deps", "shouldReAnalyzeDecl", "Lcom/feysh/corax/config/api/rules/ProcessRule$ScanAction;", "target", "shouldReAnalyzeTarget", "", "targetRelate", "Lkotlin/sequences/Sequence;", "targetsRelate", "targets", "toDecl", "visitChangedDecl", "diffPath", "", "changed", "factory", "Lcn/sast/api/incremental/ModifyInfoFactory;", "getFactory", "()Lcn/sast/api/incremental/ModifyInfoFactory;", "corax-framework"})
    @SourceDebugExtension({"SMAP\nIncrementalAnalyzeImplByChangeFiles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IncrementalAnalyzeImplByChangeFiles.kt\ncn/sast/framework/incremental/ModifyInfoFactoryImpl$createInterProceduralAnalysisDependsGraph$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,395:1\n1#2:396\n*E\n"})
    /* renamed from: cn.sast.framework.incremental.ModifyInfoFactoryImpl$createInterProceduralAnalysisDependsGraph$1, reason: invalid class name */
    /* loaded from: ModifyInfoFactoryImpl$createInterProceduralAnalysisDependsGraph$1.class */
    public static final class AnonymousClass1 implements IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph, IncrementalAnalyzeByChangeFiles.IDependsGraph {
        private final /* synthetic */ DependsGraph $$delegate_0;

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public void visitChangedDecl(String diffPath, Collection<? extends XDecl> collection) {
            Intrinsics.checkNotNullParameter(diffPath, "diffPath");
            Intrinsics.checkNotNullParameter(collection, "changed");
            this.$$delegate_0.visitChangedDecl(diffPath, collection);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public ProcessRule.ScanAction shouldReAnalyzeDecl(XDecl target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return this.$$delegate_0.shouldReAnalyzeDecl(target);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public ProcessRule.ScanAction shouldReAnalyzeTarget(Object target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return this.$$delegate_0.shouldReAnalyzeTarget(target);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public Sequence<XDecl> targetRelate(XDecl target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return this.$$delegate_0.targetRelate(target);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public Sequence<XDecl> targetsRelate(Collection<? extends XDecl> collection) {
            Intrinsics.checkNotNullParameter(collection, "targets");
            return this.$$delegate_0.targetsRelate(collection);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public ModifyInfoFactory getFactory() {
            return this.$$delegate_0.getFactory();
        }

        public XDecl toDecl(Object target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return this.$$delegate_0.toDecl(target);
        }

        public void dependsOn(XDecl $this$dependsOn, XDecl dep) {
            Intrinsics.checkNotNullParameter($this$dependsOn, "<this>");
            Intrinsics.checkNotNullParameter(dep, "dep");
            this.$$delegate_0.dependsOn($this$dependsOn, dep);
        }

        public void dependsOn(Collection<? extends XDecl> collection, Collection<? extends XDecl> collection2) {
            Intrinsics.checkNotNullParameter(collection, "<this>");
            Intrinsics.checkNotNullParameter(collection2, "deps");
            this.$$delegate_0.dependsOn(collection, collection2);
        }

        AnonymousClass1(DependsGraph $d) {
            this.$$delegate_0 = $d;
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0070  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x009a  */
        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void update(soot.jimple.toolkits.callgraph.CallGraph r6) {
            /*
                Method dump skipped, instructions count: 270
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.incremental.ModifyInfoFactoryImpl.AnonymousClass1.update(soot.jimple.toolkits.callgraph.CallGraph):void");
        }
    }

    @Override // cn.sast.api.incremental.ModifyInfoFactory
    @NotNull
    public IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph createInterProceduralAnalysisDependsGraph() {
        DependsGraph d = new DependsGraph(this);
        return new AnonymousClass1(d);
    }

    @Override // cn.sast.api.incremental.ModifyInfoFactory
    @NotNull
    public ProcessRule.ScanAction getScanAction(@NotNull XDecl target) {
        Intrinsics.checkNotNullParameter(target, "target");
        return ((target instanceof MethodDecl) && OthersKt.isDummy(((MethodDecl) target).getTarget())) ? ProcessRule.ScanAction.Skip : ProcessRule.ScanAction.Keep;
    }

    /* compiled from: IncrementalAnalyzeImplByChangeFiles.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��G\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001��\b\n\u0018��2\u00020\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0096\u0005J!\u0010\u0003\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00050\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0096\u0005J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005H\u0096\u0001J\u0011\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH\u0096\u0001J\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u000b\u001a\u00020\u0005H\u0096\u0001J\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0096\u0001J\u0011\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\rH\u0096\u0001J\u001f\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0096\u0001R\u0012\u0010\u0017\u001a\u00020\u0018X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"cn/sast/framework/incremental/ModifyInfoFactoryImpl$createSimpleDeclAnalysisDependsGraph$1", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$SimpleDeclAnalysisDependsGraph;", "Lcn/sast/api/incremental/IncrementalAnalyzeByChangeFiles$IDependsGraph;", "dependsOn", "", "Lcom/feysh/corax/config/api/XDecl;", "dep", "", "deps", "shouldReAnalyzeDecl", "Lcom/feysh/corax/config/api/rules/ProcessRule$ScanAction;", "target", "shouldReAnalyzeTarget", "", "targetRelate", "Lkotlin/sequences/Sequence;", "targetsRelate", "targets", "toDecl", "visitChangedDecl", "diffPath", "", "changed", "factory", "Lcn/sast/api/incremental/ModifyInfoFactory;", "getFactory", "()Lcn/sast/api/incremental/ModifyInfoFactory;", "corax-framework"})
    /* renamed from: cn.sast.framework.incremental.ModifyInfoFactoryImpl$createSimpleDeclAnalysisDependsGraph$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: ModifyInfoFactoryImpl$createSimpleDeclAnalysisDependsGraph$1.class */
    public static final class C00301 implements IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph, IncrementalAnalyzeByChangeFiles.IDependsGraph {
        private final /* synthetic */ DependsGraph $$delegate_0;

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public void visitChangedDecl(String diffPath, Collection<? extends XDecl> collection) {
            Intrinsics.checkNotNullParameter(diffPath, "diffPath");
            Intrinsics.checkNotNullParameter(collection, "changed");
            this.$$delegate_0.visitChangedDecl(diffPath, collection);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public ProcessRule.ScanAction shouldReAnalyzeDecl(XDecl target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return this.$$delegate_0.shouldReAnalyzeDecl(target);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public ProcessRule.ScanAction shouldReAnalyzeTarget(Object target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return this.$$delegate_0.shouldReAnalyzeTarget(target);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public Sequence<XDecl> targetRelate(XDecl target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return this.$$delegate_0.targetRelate(target);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public Sequence<XDecl> targetsRelate(Collection<? extends XDecl> collection) {
            Intrinsics.checkNotNullParameter(collection, "targets");
            return this.$$delegate_0.targetsRelate(collection);
        }

        @Override // cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles.IDependsGraph
        public ModifyInfoFactory getFactory() {
            return this.$$delegate_0.getFactory();
        }

        public XDecl toDecl(Object target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return this.$$delegate_0.toDecl(target);
        }

        public void dependsOn(XDecl $this$dependsOn, XDecl dep) {
            Intrinsics.checkNotNullParameter($this$dependsOn, "<this>");
            Intrinsics.checkNotNullParameter(dep, "dep");
            this.$$delegate_0.dependsOn($this$dependsOn, dep);
        }

        public void dependsOn(Collection<? extends XDecl> collection, Collection<? extends XDecl> collection2) {
            Intrinsics.checkNotNullParameter(collection, "<this>");
            Intrinsics.checkNotNullParameter(collection2, "deps");
            this.$$delegate_0.dependsOn(collection, collection2);
        }

        C00301(DependsGraph $d) {
            this.$$delegate_0 = $d;
        }
    }

    @Override // cn.sast.api.incremental.ModifyInfoFactory
    @NotNull
    public IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph createSimpleDeclAnalysisDependsGraph() {
        DependsGraph d = new DependsGraph(this);
        return new C00301(d);
    }
}
