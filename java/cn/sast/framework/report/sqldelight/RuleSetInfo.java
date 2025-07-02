package cn.sast.framework.report.sqldelight;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RuleSetInfo.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��*\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018��2\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003Jf\u0010!\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u000f¨\u0006)"}, d2 = {"Lcn/sast/framework/report/sqldelight/RuleSetInfo;", "", "name", "", "language", "description", "prefix", "id_pattern", "section_level", "", "version", "revision", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getLanguage", "getDescription", "getPrefix", "getId_pattern", "getSection_level", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getVersion", "getRevision", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lcn/sast/framework/report/sqldelight/RuleSetInfo;", "equals", "", "other", "hashCode", "", "toString", "corax-framework"})
/* loaded from: RuleSetInfo.class */
public final class RuleSetInfo {

    @NotNull
    private final String name;

    @NotNull
    private final String language;

    @Nullable
    private final String description;

    @Nullable
    private final String prefix;

    @Nullable
    private final String id_pattern;

    @Nullable
    private final Long section_level;

    @NotNull
    private final String version;

    @NotNull
    private final String revision;

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.language;
    }

    @Nullable
    public final String component3() {
        return this.description;
    }

    @Nullable
    public final String component4() {
        return this.prefix;
    }

    @Nullable
    public final String component5() {
        return this.id_pattern;
    }

    @Nullable
    public final Long component6() {
        return this.section_level;
    }

    @NotNull
    public final String component7() {
        return this.version;
    }

    @NotNull
    public final String component8() {
        return this.revision;
    }

    @NotNull
    public final RuleSetInfo copy(@NotNull String name, @NotNull String language, @Nullable String description, @Nullable String prefix, @Nullable String id_pattern, @Nullable Long section_level, @NotNull String version, @NotNull String revision) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(revision, "revision");
        return new RuleSetInfo(name, language, description, prefix, id_pattern, section_level, version, revision);
    }

    public static /* synthetic */ RuleSetInfo copy$default(RuleSetInfo ruleSetInfo, String str, String str2, String str3, String str4, String str5, Long l, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = ruleSetInfo.name;
        }
        if ((i & 2) != 0) {
            str2 = ruleSetInfo.language;
        }
        if ((i & 4) != 0) {
            str3 = ruleSetInfo.description;
        }
        if ((i & 8) != 0) {
            str4 = ruleSetInfo.prefix;
        }
        if ((i & 16) != 0) {
            str5 = ruleSetInfo.id_pattern;
        }
        if ((i & 32) != 0) {
            l = ruleSetInfo.section_level;
        }
        if ((i & 64) != 0) {
            str6 = ruleSetInfo.version;
        }
        if ((i & 128) != 0) {
            str7 = ruleSetInfo.revision;
        }
        return ruleSetInfo.copy(str, str2, str3, str4, str5, l, str6, str7);
    }

    @NotNull
    public String toString() {
        return "RuleSetInfo(name=" + this.name + ", language=" + this.language + ", description=" + this.description + ", prefix=" + this.prefix + ", id_pattern=" + this.id_pattern + ", section_level=" + this.section_level + ", version=" + this.version + ", revision=" + this.revision + ")";
    }

    public int hashCode() {
        int result = this.name.hashCode();
        return (((((((((((((result * 31) + this.language.hashCode()) * 31) + (this.description == null ? 0 : this.description.hashCode())) * 31) + (this.prefix == null ? 0 : this.prefix.hashCode())) * 31) + (this.id_pattern == null ? 0 : this.id_pattern.hashCode())) * 31) + (this.section_level == null ? 0 : this.section_level.hashCode())) * 31) + this.version.hashCode()) * 31) + this.revision.hashCode();
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RuleSetInfo)) {
            return false;
        }
        RuleSetInfo ruleSetInfo = (RuleSetInfo) other;
        return Intrinsics.areEqual(this.name, ruleSetInfo.name) && Intrinsics.areEqual(this.language, ruleSetInfo.language) && Intrinsics.areEqual(this.description, ruleSetInfo.description) && Intrinsics.areEqual(this.prefix, ruleSetInfo.prefix) && Intrinsics.areEqual(this.id_pattern, ruleSetInfo.id_pattern) && Intrinsics.areEqual(this.section_level, ruleSetInfo.section_level) && Intrinsics.areEqual(this.version, ruleSetInfo.version) && Intrinsics.areEqual(this.revision, ruleSetInfo.revision);
    }

    public RuleSetInfo(@NotNull String name, @NotNull String language, @Nullable String description, @Nullable String prefix, @Nullable String id_pattern, @Nullable Long section_level, @NotNull String version, @NotNull String revision) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(language, "language");
        Intrinsics.checkNotNullParameter(version, "version");
        Intrinsics.checkNotNullParameter(revision, "revision");
        this.name = name;
        this.language = language;
        this.description = description;
        this.prefix = prefix;
        this.id_pattern = id_pattern;
        this.section_level = section_level;
        this.version = version;
        this.revision = revision;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getLanguage() {
        return this.language;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final String getPrefix() {
        return this.prefix;
    }

    @Nullable
    public final String getId_pattern() {
        return this.id_pattern;
    }

    @Nullable
    public final Long getSection_level() {
        return this.section_level;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    @NotNull
    public final String getRevision() {
        return this.revision;
    }
}
