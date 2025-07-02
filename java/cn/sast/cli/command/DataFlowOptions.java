package cn.sast.cli.command;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.Context;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.core.UsageError;
import com.github.ajalt.clikt.parameters.groups.OptionGroup;
import com.github.ajalt.clikt.parameters.options.FlagOptionKt;
import com.github.ajalt.clikt.parameters.options.OptionCallTransformContext;
import com.github.ajalt.clikt.parameters.options.OptionKt;
import com.github.ajalt.clikt.parameters.options.OptionWithValues;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import com.github.ajalt.clikt.parameters.types.IntKt;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ReadOnlyProperty;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import kotlin.text.Regex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DataFlowOptions.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\n\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R\u001d\u0010\r\u001a\u0004\u0018\u00010\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\t\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0012\u001a\u0004\u0018\u00010\u000e8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\t\u001a\u0004\b\u0013\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcn/sast/cli/command/DataFlowOptions;", "Lcom/github/ajalt/clikt/parameters/groups/OptionGroup;", "<init>", "()V", "enableDataFlow", "", "getEnableDataFlow", "()Z", "enableDataFlow$delegate", "Lkotlin/properties/ReadOnlyProperty;", "enableCoverage", "getEnableCoverage", "enableCoverage$delegate", "factor1", "", "getFactor1", "()Ljava/lang/Integer;", "factor1$delegate", "dataFlowInterProceduralCalleeTimeOut", "getDataFlowInterProceduralCalleeTimeOut", "dataFlowInterProceduralCalleeTimeOut$delegate", "corax-cli"})
@SourceDebugExtension({"SMAP\nDataFlowOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DataFlowOptions.kt\ncn/sast/cli/command/DataFlowOptions\n+ 2 Convert.kt\ncom/github/ajalt/clikt/parameters/options/OptionWithValuesKt__ConvertKt\n*L\n1#1,23:1\n35#2,6:24\n70#2:30\n82#2,4:31\n*S KotlinDebug\n*F\n+ 1 DataFlowOptions.kt\ncn/sast/cli/command/DataFlowOptions\n*L\n10#1:24,6\n10#1:30\n10#1:31,4\n*E\n"})
/* loaded from: DataFlowOptions.class */
public final class DataFlowOptions extends OptionGroup {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(DataFlowOptions.class, "enableDataFlow", "getEnableDataFlow()Z", 0)), Reflection.property1(new PropertyReference1Impl(DataFlowOptions.class, "enableCoverage", "getEnableCoverage()Z", 0)), Reflection.property1(new PropertyReference1Impl(DataFlowOptions.class, "factor1", "getFactor1()Ljava/lang/Integer;", 0)), Reflection.property1(new PropertyReference1Impl(DataFlowOptions.class, "dataFlowInterProceduralCalleeTimeOut", "getDataFlowInterProceduralCalleeTimeOut()Ljava/lang/Integer;", 0))};

    @NotNull
    private final ReadOnlyProperty enableDataFlow$delegate;

    @NotNull
    private final ReadOnlyProperty enableCoverage$delegate;

    @NotNull
    private final ReadOnlyProperty factor1$delegate;

    @NotNull
    private final ReadOnlyProperty dataFlowInterProceduralCalleeTimeOut$delegate;

    public DataFlowOptions() {
        super("Data Flow Options", (String) null, 2, (DefaultConstructorMarker) null);
        final OptionWithValues $this$convert_u24default$iv = OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 510, (Object) null);
        final String metavar$iv = "BOOL";
        Function1 metavar$iv$iv$iv = new Function1<Context, String>() { // from class: cn.sast.cli.command.DataFlowOptions$special$$inlined$convert$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @NotNull
            public final String invoke(@NotNull Context $this$convert) {
                Intrinsics.checkNotNullParameter($this$convert, "$this$convert");
                return metavar$iv;
            }
        };
        Function2 valueTransform$iv$iv$iv = new Function2<OptionCallTransformContext, String, Boolean>() { // from class: cn.sast.cli.command.DataFlowOptions$special$$inlined$convert$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.github.ajalt.clikt.core.UsageError */
            /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
            @NotNull
            public final Boolean invoke(@NotNull OptionCallTransformContext $this$null, @NotNull String it) throws UsageError, KotlinNothingValueException {
                Intrinsics.checkNotNullParameter($this$null, "$this$null");
                Intrinsics.checkNotNullParameter(it, "it");
                try {
                    return Boolean.valueOf(Boolean.parseBoolean((String) $this$convert_u24default$iv.getTransformValue().invoke($this$null, it)));
                } catch (UsageError err) {
                    UsageError usageError = err;
                    String paramName = err.getParamName();
                    if (paramName == null) {
                        String n = $this$null.getName();
                        usageError = usageError;
                        paramName = !(n.length() == 0) ? n : null;
                        if (paramName == null) {
                            paramName = OptionKt.longestName($this$null.getOption());
                        }
                    }
                    usageError.setParamName(paramName);
                    throw err;
                } catch (Exception err2) {
                    String message = err2.getMessage();
                    if (message == null) {
                        message = "";
                    }
                    $this$null.fail(message);
                    throw new KotlinNothingValueException();
                }
            }
        };
        Function2 function2DefaultEachProcessor = OptionWithValuesKt.defaultEachProcessor();
        Function2 function2DefaultAllProcessor = OptionWithValuesKt.defaultAllProcessor();
        Function2 function2DefaultValidator = OptionWithValuesKt.defaultValidator();
        Function1 metavarGetter = $this$convert_u24default$iv.getMetavarGetter();
        metavarGetter = metavarGetter == null ? metavar$iv$iv$iv : metavarGetter;
        CompletionCandidates explicitCompletionCandidates = $this$convert_u24default$iv.getExplicitCompletionCandidates();
        this.enableDataFlow$delegate = OptionWithValuesKt.help(OptionWithValuesKt.required(OptionWithValues.DefaultImpls.copy$default($this$convert_u24default$iv, valueTransform$iv$iv$iv, function2DefaultEachProcessor, function2DefaultAllProcessor, function2DefaultValidator, (Set) null, metavarGetter, (IntRange) null, (Function1) null, false, (Map) null, (String) null, (String) null, (Regex) null, explicitCompletionCandidates == null ? null : explicitCompletionCandidates, (Set) null, false, false, false, 253904, (Object) null)), "Set if the DataFlow engine shall be enabled").provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.enableCoverage$delegate = OptionWithValuesKt.help(FlagOptionKt.flag$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 510, (Object) null), new String[0], false, (String) null, 6, (Object) null), "Turn on static analysis code coverage reporting with Jacoco").provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
        this.factor1$delegate = IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[0], (String) null, (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 502, (Object) null), false, 1, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[2]);
        this.dataFlowInterProceduralCalleeTimeOut$delegate = IntKt.int$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"--call-timeout"}, "TimeOut for data flow analysis interProcedural call TimeoutMillis (ms).", (String) null, true, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 500, (Object) null), false, 1, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[3]);
    }

    public final boolean getEnableDataFlow() {
        return ((Boolean) this.enableDataFlow$delegate.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final boolean getEnableCoverage() {
        return ((Boolean) this.enableCoverage$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    @Nullable
    public final Integer getFactor1() {
        return (Integer) this.factor1$delegate.getValue(this, $$delegatedProperties[2]);
    }

    @Nullable
    public final Integer getDataFlowInterProceduralCalleeTimeOut() {
        return (Integer) this.dataFlowInterProceduralCalleeTimeOut$delegate.getValue(this, $$delegatedProperties[3]);
    }
}
