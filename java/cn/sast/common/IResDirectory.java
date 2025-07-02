package cn.sast.common;

import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: Resource.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n��\n\u0002\u0010\u000e\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u0007\u001a\u00020��2\u0006\u0010\b\u001a\u00020��H&J\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH&R\u0012\u0010\u0002\u001a\u00020��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0012\u0010\u0005\u001a\u00020��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004¨\u0006\r"}, d2 = {"Lcn/sast/common/IResDirectory;", "Lcn/sast/common/IResource;", "normalize", "getNormalize", "()Lcn/sast/common/IResDirectory;", "absolute", "getAbsolute", "expandRes", "outPut", "listPathEntries", "", "glob", "", "corax-api"})
/* loaded from: IResDirectory.class */
public interface IResDirectory extends IResource {
    @Override // cn.sast.common.IResource
    @NotNull
    IResDirectory getNormalize();

    @Override // cn.sast.common.IResource
    @NotNull
    IResDirectory getAbsolute();

    @Override // cn.sast.common.IResource
    @NotNull
    IResDirectory expandRes(@NotNull IResDirectory iResDirectory);

    @NotNull
    List<IResource> listPathEntries(@NotNull String str);

    /* compiled from: Resource.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IResDirectory$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean getZipLike(@NotNull IResDirectory $this) {
            return IResource.DefaultImpls.getZipLike($this);
        }

        public static void deleteDirectoryRecursively(@NotNull IResDirectory $this) throws IOException {
            IResource.DefaultImpls.deleteDirectoryRecursively($this);
        }

        public static void deleteDirectoryContents(@NotNull IResDirectory $this) throws IOException {
            IResource.DefaultImpls.deleteDirectoryContents($this);
        }

        public static /* synthetic */ List listPathEntries$default(IResDirectory iResDirectory, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listPathEntries");
            }
            if ((i & 1) != 0) {
                str = "*";
            }
            return iResDirectory.listPathEntries(str);
        }
    }
}
