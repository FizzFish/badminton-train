package cn.sast.framework.report;

import cn.sast.api.report.IBugResInfo;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.AbstractFileIndexer;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��H\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n��\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\u000f\u001a\u00020\u0010H&J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u0014H¦@¢\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u0017\u001a\u00020\u0014H¦@¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012H¦@¢\u0006\u0002\u0010\u0019J$\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH&R\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u000e¢\u0006\f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u001f"}, d2 = {"Lcn/sast/framework/report/IProjectFileLocator;", "", "get", "Lcn/sast/common/IResFile;", "resInfo", "Lcn/sast/api/report/IBugResInfo;", "fileWrapperIfNotEExists", "Lcn/sast/framework/report/IWrapperFileGenerator;", "sourceDir", "", "Lcn/sast/common/IResource;", "getSourceDir", "()Ljava/util/Set;", "setSourceDir", "(Ljava/util/Set;)V", "update", "", "getByFileExtension", "Lkotlin/sequences/Sequence;", "extension", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByFileName", "filename", "getAllFiles", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findFromFileIndexMap", "parentSubPath", "", "mode", "Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;", "corax-framework"})
/* loaded from: IProjectFileLocator.class */
public interface IProjectFileLocator {
    @Nullable
    IResFile get(@NotNull IBugResInfo iBugResInfo, @NotNull IWrapperFileGenerator iWrapperFileGenerator);

    @NotNull
    Set<IResource> getSourceDir();

    void setSourceDir(@NotNull Set<? extends IResource> set);

    void update();

    @Nullable
    Object getByFileExtension(@NotNull String str, @NotNull Continuation<? super Sequence<? extends IResFile>> continuation);

    @Nullable
    Object getByFileName(@NotNull String str, @NotNull Continuation<? super Sequence<? extends IResFile>> continuation);

    @Nullable
    Object getAllFiles(@NotNull Continuation<? super Sequence<? extends IResFile>> continuation);

    @NotNull
    Sequence<IResFile> findFromFileIndexMap(@NotNull List<String> list, @NotNull AbstractFileIndexer.CompareMode compareMode);

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IProjectFileLocator$DefaultImpls.class */
    public static final class DefaultImpls {
        public static /* synthetic */ IResFile get$default(IProjectFileLocator iProjectFileLocator, IBugResInfo iBugResInfo, IWrapperFileGenerator iWrapperFileGenerator, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
            }
            if ((i & 2) != 0) {
                iWrapperFileGenerator = NullWrapperFileGenerator.INSTANCE;
            }
            return iProjectFileLocator.get(iBugResInfo, iWrapperFileGenerator);
        }
    }
}
