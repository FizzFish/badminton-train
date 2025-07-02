package cn.sast.framework.entries.component;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.entries.utils.PhantomValueForType;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Body;
import soot.Local;
import soot.LocalGenerator;
import soot.SootClass;
import soot.Type;
import soot.Value;
import soot.jimple.infoflow.entryPointCreators.SequentialEntryPointCreator;

/* compiled from: ComponentEntryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\u0018��2\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006JJ\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000e2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u000e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/entries/component/ComponentEntryPointCreator;", "Lsoot/jimple/infoflow/entryPointCreators/SequentialEntryPointCreator;", "entry", "", "", "<init>", "(Ljava/util/Collection;)V", "p", "Lcn/sast/framework/entries/utils/PhantomValueForType;", "getValueForType", "Lsoot/Value;", "tp", "Lsoot/Type;", "constructionStack", "", "Lsoot/SootClass;", "parentClasses", "", "generatedLocals", "Lsoot/Local;", "ignoreExcludes", "", "corax-framework"})
/* loaded from: ComponentEntryPointCreator.class */
public final class ComponentEntryPointCreator extends SequentialEntryPointCreator {

    @NotNull
    private final PhantomValueForType p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComponentEntryPointCreator(@NotNull Collection<String> collection) {
        super(collection);
        Intrinsics.checkNotNullParameter(collection, "entry");
        this.p = new PhantomValueForType(null, 1, null);
    }

    @Nullable
    protected Value getValueForType(@NotNull Type tp, @Nullable Set<SootClass> set, @Nullable Set<? extends SootClass> set2, @Nullable Set<Local> set3, boolean ignoreExcludes) {
        Intrinsics.checkNotNullParameter(tp, "tp");
        PhantomValueForType phantomValueForType = this.p;
        Body body = this.body;
        Intrinsics.checkNotNullExpressionValue(body, "body");
        LocalGenerator localGenerator = this.generator;
        Intrinsics.checkNotNullExpressionValue(localGenerator, "generator");
        return phantomValueForType.getValueForType(body, localGenerator, tp);
    }
}
