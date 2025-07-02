package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Verify_rule_name.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��(\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\t\n��\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcn/sast/framework/report/sqldelight/Verify_rule_name;", "", "id", "", "rule_name", "", "<init>", "(JLjava/lang/String;)V", "getId", "()J", "getRule_name", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: Verify_rule_name.class */
public final class Verify_rule_name {
    private final long id;

    @NotNull
    private final String rule_name;

    public final long component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.rule_name;
    }

    @NotNull
    public final Verify_rule_name copy(long id, @NotNull String rule_name) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        return new Verify_rule_name(id, rule_name);
    }

    public static /* synthetic */ Verify_rule_name copy$default(Verify_rule_name verify_rule_name, long j, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            j = verify_rule_name.id;
        }
        if ((i & 2) != 0) {
            str = verify_rule_name.rule_name;
        }
        return verify_rule_name.copy(j, str);
    }

    @NotNull
    public String toString() {
        long j = this.id;
        String str = this.rule_name;
        return "Verify_rule_name(id=" + j + ", rule_name=" + j + ")";
    }

    public int hashCode() {
        int result = Long.hashCode(this.id);
        return (result * 31) + this.rule_name.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Verify_rule_name)) {
            return false;
        }
        Verify_rule_name verify_rule_name = (Verify_rule_name) other;
        return this.id == verify_rule_name.id && Intrinsics.areEqual(this.rule_name, verify_rule_name.rule_name);
    }

    public Verify_rule_name(long id, @NotNull String rule_name) {
        Intrinsics.checkNotNullParameter(rule_name, "rule_name");
        this.id = id;
        this.rule_name = rule_name;
    }

    public final long getId() {
        return this.id;
    }

    @NotNull
    public final String getRule_name() {
        return this.rule_name;
    }
}
