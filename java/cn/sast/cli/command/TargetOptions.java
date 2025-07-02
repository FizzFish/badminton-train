package cn.sast.cli.command;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.SootCtx;
import cn.sast.framework.entries.IEntryPointProvider;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.ProjectFileLocator;
import com.github.ajalt.clikt.parameters.groups.OptionGroup;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: TargetOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��2\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018��2\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcn/sast/cli/command/TargetOptions;", "Lcom/github/ajalt/clikt/parameters/groups/OptionGroup;", "name", "", "<init>", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getProvider", "Lcn/sast/framework/entries/IEntryPointProvider;", "sootCtx", "Lcn/sast/framework/SootCtx;", "locator", "Lcn/sast/framework/report/ProjectFileLocator;", "configureMainConfig", "", "mainConfig", "Lcn/sast/api/config/MainConfig;", "initSoot", "corax-cli"})
/* loaded from: TargetOptions.class */
public abstract class TargetOptions extends OptionGroup {

    @NotNull
    private final String name;

    @NotNull
    public abstract IEntryPointProvider getProvider(@NotNull SootCtx sootCtx, @NotNull ProjectFileLocator locator);

    public abstract void configureMainConfig(@NotNull MainConfig mainConfig);

    public abstract void initSoot(@NotNull SootCtx sootCtx, @NotNull ProjectFileLocator locator);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TargetOptions(@NotNull String name) {
        super(name, (String) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }
}
