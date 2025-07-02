package cn.sast.cli.command.tools;

import cn.sast.cli.ApplicationKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.github.ajalt.clikt.completion.CompletionCandidates;
import com.github.ajalt.clikt.core.CliktCommand;
import com.github.ajalt.clikt.core.CliktCommandKt;
import com.github.ajalt.clikt.core.Context;
import com.github.ajalt.clikt.core.ParameterHolder;
import com.github.ajalt.clikt.core.PrintMessage;
import com.github.ajalt.clikt.output.MordantHelpFormatter;
import com.github.ajalt.clikt.parameters.options.EagerOptionKt;
import com.github.ajalt.clikt.parameters.options.OptionTransformContext;
import com.github.ajalt.clikt.parameters.options.OptionWithValuesKt;
import com.github.ajalt.clikt.parameters.types.FileKt;
import com.github.doyaaaaaken.kotlincsv.client.CsvReader;
import com.github.doyaaaaaken.kotlincsv.client.CsvWriter;
import com.github.doyaaaaaken.kotlincsv.client.ICsvFileWriter;
import com.github.doyaaaaaken.kotlincsv.dsl.CsvReaderDslKt;
import com.github.doyaaaaaken.kotlincsv.dsl.CsvWriterDslKt;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: UtilsCli.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n��\u0018��2\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u001d\u0010\u0004\u001a\u0004\u0018\u00010\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\n\u001a\u0004\u0018\u00010\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\u000b\u0010\u0007R!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Lcn/sast/cli/command/tools/UtilsCli;", "Lcom/github/ajalt/clikt/core/CliktCommand;", "<init>", "()V", "input", "Ljava/io/File;", "getInput", "()Ljava/io/File;", "input$delegate", "Lkotlin/properties/ReadOnlyProperty;", "output", "getOutput", "output$delegate", "csvDeleteColumns", "", "", "getCsvDeleteColumns", "()Ljava/util/List;", "csvDeleteColumns$delegate", "run", "", "corax-cli"})
@SourceDebugExtension({"SMAP\nUtilsCli.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UtilsCli.kt\ncn/sast/cli/command/tools/UtilsCli\n+ 2 EagerOption.kt\ncom/github/ajalt/clikt/parameters/options/EagerOptionKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,59:1\n65#2,10:60\n1368#3:70\n1454#3,5:71\n2632#3,3:76\n1628#3,3:80\n1863#3:83\n1797#3,3:84\n1864#3:87\n1#4:79\n*S KotlinDebug\n*F\n+ 1 UtilsCli.kt\ncn/sast/cli/command/tools/UtilsCli\n*L\n18#1:60,10\n28#1:70\n28#1:71,5\n29#1:76,3\n34#1:80,3\n38#1:83\n39#1:84,3\n38#1:87\n*E\n"})
/* loaded from: UtilsCli.class */
public final class UtilsCli extends CliktCommand {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(UtilsCli.class, "input", "getInput()Ljava/io/File;", 0)), Reflection.property1(new PropertyReference1Impl(UtilsCli.class, "output", "getOutput()Ljava/io/File;", 0)), Reflection.property1(new PropertyReference1Impl(UtilsCli.class, "csvDeleteColumns", "getCsvDeleteColumns()Ljava/util/List;", 0))};

    @NotNull
    private final ReadOnlyProperty input$delegate;

    @NotNull
    private final ReadOnlyProperty output$delegate;

    @NotNull
    private final ReadOnlyProperty csvDeleteColumns$delegate;

    public UtilsCli() {
        super((String) null, (String) null, "Utils", false, false, (Map) null, (String) null, false, false, false, 1019, (DefaultConstructorMarker) null);
        final UtilsCli $this$versionOption_u24default$iv = this;
        final String version$iv = ApplicationKt.getVersion();
        Set names$iv = SetsKt.setOf("--version");
        EagerOptionKt.eagerOption$default($this$versionOption_u24default$iv, names$iv, "Show the version and exit", false, (Map) null, (String) null, new Function1<OptionTransformContext, Unit>() { // from class: cn.sast.cli.command.tools.UtilsCli$special$$inlined$versionOption$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.github.ajalt.clikt.core.PrintMessage */
            public final void invoke(@NotNull OptionTransformContext $this$eagerOption) throws PrintMessage {
                Intrinsics.checkNotNullParameter($this$eagerOption, "$this$eagerOption");
                String it = version$iv;
                throw new PrintMessage($this$versionOption_u24default$iv.getCommandName() + " version " + it, 0, false, 6, (DefaultConstructorMarker) null);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object p1) throws PrintMessage {
                invoke((OptionTransformContext) p1);
                return Unit.INSTANCE;
            }
        }, 28, (Object) null);
        CliktCommandKt.context(this, UtilsCli::_init_$lambda$1);
        this.input$delegate = FileKt.file$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"-i"}, (String) null, (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 510, (Object) null), true, false, false, false, true, false, 46, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[0]);
        this.output$delegate = FileKt.file$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"-o"}, (String) null, (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 510, (Object) null), false, false, false, false, false, false, 63, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[1]);
        this.csvDeleteColumns$delegate = OptionWithValuesKt.multiple$default(OptionWithValuesKt.option$default((ParameterHolder) this, new String[]{"--csv-d-c"}, (String) null, (String) null, false, (String) null, (Map) null, (CompletionCandidates) null, (String) null, false, 510, (Object) null), (List) null, false, 3, (Object) null).provideDelegate((ParameterHolder) this, $$delegatedProperties[2]);
    }

    private static final MordantHelpFormatter lambda$1$lambda$0(Context it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new MordantHelpFormatter(it, (String) null, true, true, 2, (DefaultConstructorMarker) null);
    }

    private static final Unit _init_$lambda$1(Context.Builder $this$context) {
        Intrinsics.checkNotNullParameter($this$context, "$this$context");
        $this$context.setHelpFormatter(UtilsCli::lambda$1$lambda$0);
        return Unit.INSTANCE;
    }

    private final File getInput() {
        return (File) this.input$delegate.getValue(this, $$delegatedProperties[0]);
    }

    private final File getOutput() {
        return (File) this.output$delegate.getValue(this, $$delegatedProperties[1]);
    }

    private final List<String> getCsvDeleteColumns() {
        return (List) this.csvDeleteColumns$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public void run() {
        boolean z;
        if (!getCsvDeleteColumns().isEmpty()) {
            Iterable $this$flatMap$iv = getCsvDeleteColumns();
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                String it = (String) element$iv$iv;
                Iterable list$iv$iv = StringsKt.split$default(it, new String[]{","}, false, 0, 6, (Object) null);
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            }
            Iterable csvDeleteColumnNames = (List) destination$iv$iv;
            Iterable $this$none$iv = csvDeleteColumnNames;
            if (!($this$none$iv instanceof Collection) || !((Collection) $this$none$iv).isEmpty()) {
                Iterator it2 = $this$none$iv.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    Object element$iv = it2.next();
                    String it3 = (String) element$iv;
                    if (it3.length() == 0) {
                        z = false;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (!z) {
                throw new IllegalStateException((csvDeleteColumnNames + " has a empty string element").toString());
            }
            if (!(getInput() != null)) {
                throw new IllegalStateException("input is required".toString());
            }
            if (!(getOutput() != null)) {
                throw new IllegalStateException("output is required".toString());
            }
            CsvReader csvReaderCsvReader$default = CsvReaderDslKt.csvReader$default((Function1) null, 1, (Object) null);
            File input = getInput();
            Intrinsics.checkNotNull(input);
            List rows = csvReaderCsvReader$default.readAll(input);
            List header = (List) CollectionsKt.first(rows);
            Iterable $this$mapTo$iv = csvDeleteColumnNames;
            Collection destination$iv = (Set) new LinkedHashSet();
            for (Object item$iv : $this$mapTo$iv) {
                String c = (String) item$iv;
                Integer numValueOf = Integer.valueOf(header.indexOf(c));
                int it4 = numValueOf.intValue();
                Integer num = it4 >= 0 ? numValueOf : null;
                if (num == null) {
                    throw new IllegalStateException((c + " not exists in header: " + header).toString());
                }
                destination$iv.add(Integer.valueOf(num.intValue()));
            }
            List columnIdxToDelete = CollectionsKt.reversed(CollectionsKt.sorted(destination$iv));
            CsvWriter csvWriterCsvWriter$default = CsvWriterDslKt.csvWriter$default((Function1) null, 1, (Object) null);
            File output = getOutput();
            Intrinsics.checkNotNull(output);
            CsvWriter.open$default(csvWriterCsvWriter$default, output, false, (v2) -> {
                return run$lambda$11(r3, r4, v2);
            }, 2, (Object) null);
        }
    }

    private static final Unit run$lambda$11(List $rows, List $columnIdxToDelete, ICsvFileWriter $this$open) {
        Intrinsics.checkNotNullParameter($this$open, "$this$open");
        List $this$forEach$iv = $rows;
        for (Object element$iv : $this$forEach$iv) {
            List it = (List) element$iv;
            List $this$fold$iv = $columnIdxToDelete;
            List mutableList = CollectionsKt.toMutableList(it);
            for (Object element$iv2 : $this$fold$iv) {
                List acc = mutableList;
                int idx = ((Number) element$iv2).intValue();
                acc.remove(idx);
                mutableList = acc;
            }
            $this$open.writeRow(mutableList);
        }
        return Unit.INSTANCE;
    }
}
