package cn.sast.cli.command;

import cn.sast.common.CustomRepeatingTimer;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: FySastCli.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0011\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��*\u0001��\b\n\u0018��2\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"cn/sast/cli/command/FySastCli$top$topLogWriter$1", "Lcn/sast/common/CustomRepeatingTimer;", "stop", "", "corax-cli"})
/* loaded from: FySastCli$top$topLogWriter$1.class */
public final class FySastCli$top$topLogWriter$1 extends CustomRepeatingTimer {
    final /* synthetic */ OutputStreamWriter $io;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FySastCli$top$topLogWriter$1(OutputStreamWriter $io, Function0<Unit> $super_call_param$1) {
        super(1000L, $super_call_param$1);
        this.$io = $io;
    }

    @Override // cn.sast.common.CustomRepeatingTimer
    public void stop() throws IOException {
        super.stop();
        this.$io.write("\n" + LocalDateTime.now() + ": --------------------finish--------------------\n\n\n");
        this.$io.flush();
        this.$io.close();
    }
}
