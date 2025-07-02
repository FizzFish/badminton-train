package cn.sast.dataflow.analysis;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.report.Region;
import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;

/* compiled from: IBugReporter.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\bf\u0018��2\u00020\u0001J;\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\rH&J;\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\rH&J3\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\rH&J3\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\rH&J;\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u00172\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\u0002\b\rH&¨\u0006\u0018"}, d2 = {"Lcn/sast/dataflow/analysis/IBugReporter;", "", "report", "", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "atClass", "Lsoot/SootClass;", "region", "Lcom/feysh/corax/config/api/report/Region;", "env", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/BugMessage$Env;", "Lkotlin/ExtensionFunctionType;", "file", "Ljava/nio/file/Path;", "ct", "field", "Lsoot/SootField;", "method", "Lsoot/SootMethod;", "clazz", "unit", "Lsoot/Unit;", "corax-data-flow"})
/* loaded from: IBugReporter.class */
public interface IBugReporter {
    void report(@NotNull CheckType checkType, @NotNull SootClass sootClass, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1);

    void report(@NotNull CheckType checkType, @NotNull Path path, @NotNull Region region, @NotNull Function1<? super BugMessage.Env, Unit> function1);

    void report(@NotNull CheckType checkType, @NotNull SootField sootField, @NotNull Function1<? super BugMessage.Env, Unit> function1);

    void report(@NotNull CheckType checkType, @NotNull SootMethod sootMethod, @NotNull Function1<? super BugMessage.Env, Unit> function1);

    void report(@NotNull CheckType checkType, @NotNull SootClass sootClass, @NotNull soot.Unit unit, @NotNull Function1<? super BugMessage.Env, Unit> function1);

    /* compiled from: IBugReporter.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48)
    /* loaded from: IBugReporter$DefaultImpls.class */
    public static final class DefaultImpls {
        public static /* synthetic */ void report$default(IBugReporter iBugReporter, CheckType checkType, SootClass sootClass, Region region, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: report");
            }
            if ((i & 8) != 0) {
                function1 = DefaultImpls::report$lambda$0;
            }
            iBugReporter.report(checkType, sootClass, region, (Function1<? super BugMessage.Env, Unit>) function1);
        }

        private static Unit report$lambda$0(BugMessage.Env env) {
            Intrinsics.checkNotNullParameter(env, "<this>");
            return Unit.INSTANCE;
        }

        public static /* synthetic */ void report$default(IBugReporter iBugReporter, CheckType checkType, Path path, Region region, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: report");
            }
            if ((i & 8) != 0) {
                function1 = DefaultImpls::report$lambda$1;
            }
            iBugReporter.report(checkType, path, region, (Function1<? super BugMessage.Env, Unit>) function1);
        }

        private static Unit report$lambda$1(BugMessage.Env env) {
            Intrinsics.checkNotNullParameter(env, "<this>");
            return Unit.INSTANCE;
        }

        public static /* synthetic */ void report$default(IBugReporter iBugReporter, CheckType checkType, SootField sootField, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: report");
            }
            if ((i & 4) != 0) {
                function1 = DefaultImpls::report$lambda$2;
            }
            iBugReporter.report(checkType, sootField, (Function1<? super BugMessage.Env, Unit>) function1);
        }

        private static Unit report$lambda$2(BugMessage.Env env) {
            Intrinsics.checkNotNullParameter(env, "<this>");
            return Unit.INSTANCE;
        }

        public static /* synthetic */ void report$default(IBugReporter iBugReporter, CheckType checkType, SootMethod sootMethod, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: report");
            }
            if ((i & 4) != 0) {
                function1 = DefaultImpls::report$lambda$3;
            }
            iBugReporter.report(checkType, sootMethod, (Function1<? super BugMessage.Env, Unit>) function1);
        }

        private static Unit report$lambda$3(BugMessage.Env env) {
            Intrinsics.checkNotNullParameter(env, "<this>");
            return Unit.INSTANCE;
        }

        public static /* synthetic */ void report$default(IBugReporter iBugReporter, CheckType checkType, SootClass sootClass, soot.Unit unit, Function1 function1, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: report");
            }
            if ((i & 8) != 0) {
                function1 = DefaultImpls::report$lambda$4;
            }
            iBugReporter.report(checkType, sootClass, unit, (Function1<? super BugMessage.Env, Unit>) function1);
        }

        private static Unit report$lambda$4(BugMessage.Env env) {
            Intrinsics.checkNotNullParameter(env, "<this>");
            return Unit.INSTANCE;
        }
    }
}
