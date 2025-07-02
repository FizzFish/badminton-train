package cn.sast.common;

import cn.sast.common.IResource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.IOException;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* compiled from: Resource.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J\u0010\u0010\u000f\u001a\u00020��2\u0006\u0010\u0010\u001a\u00020\u0011H&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\r\u001a\u00020��X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u0012"}, d2 = {"Lcn/sast/common/IResFile;", "Lcn/sast/common/IResource;", "entries", "", "", "getEntries", "()Ljava/util/Set;", "md5", "getMd5", "()Ljava/lang/String;", "absolute", "getAbsolute", "()Lcn/sast/common/IResFile;", "normalize", "getNormalize", "expandRes", "outPut", "Lcn/sast/common/IResDirectory;", "corax-api"})
/* loaded from: IResFile.class */
public interface IResFile extends IResource {
    @NotNull
    Set<String> getEntries();

    @NotNull
    String getMd5();

    @Override // cn.sast.common.IResource
    @NotNull
    IResFile getAbsolute();

    @Override // cn.sast.common.IResource
    @NotNull
    IResFile getNormalize();

    @Override // cn.sast.common.IResource
    @NotNull
    IResFile expandRes(@NotNull IResDirectory iResDirectory);

    /* compiled from: Resource.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IResFile$DefaultImpls.class */
    public static final class DefaultImpls {
        public static boolean getZipLike(@NotNull IResFile $this) {
            return IResource.DefaultImpls.getZipLike($this);
        }

        public static void deleteDirectoryRecursively(@NotNull IResFile $this) throws IOException {
            IResource.DefaultImpls.deleteDirectoryRecursively($this);
        }

        public static void deleteDirectoryContents(@NotNull IResFile $this) throws IOException {
            IResource.DefaultImpls.deleteDirectoryContents($this);
        }
    }
}
