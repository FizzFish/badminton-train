package cn.sast.framework.validator;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.Closeable;
import java.io.Flushable;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: PrettyTable.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n��\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0010��\n\u0002\b\u0005\u0018��2\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0006J\u0006\u0010\u0013\u001a\u00020\u0010J\b\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\u001a\u0010\u0016\u001a\u00020\u0010*\u00020\u00042\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u000bX\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n��R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u0017"}, d2 = {"Lcn/sast/framework/validator/PrettyTable;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "out", "Ljava/io/PrintWriter;", "head", "", "", "<init>", "(Ljava/io/PrintWriter;Ljava/util/List;)V", "table", "", "columns", "", "blockSize", "addLine", "", "line", "", "dump", "flush", "close", "printLine", "corax-framework"})
@SourceDebugExtension({"SMAP\nPrettyTable.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PrettyTable.kt\ncn/sast/framework/validator/PrettyTable\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,61:1\n1557#2:62\n1628#2,3:63\n1557#2:66\n1628#2,3:67\n1557#2:70\n1628#2,3:71\n1567#2:74\n1598#2,4:75\n*S KotlinDebug\n*F\n+ 1 PrettyTable.kt\ncn/sast/framework/validator/PrettyTable\n*L\n17#1:62\n17#1:63,3\n21#1:66\n21#1:67,3\n24#1:70\n24#1:71,3\n53#1:74\n53#1:75,4\n*E\n"})
/* loaded from: PrettyTable.class */
public final class PrettyTable implements Closeable, Flushable {

    @NotNull
    private final PrintWriter out;

    @NotNull
    private final List<List<String>> table;
    private final int columns;

    @NotNull
    private List<Integer> blockSize;

    public PrettyTable(@NotNull PrintWriter out, @NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(list, "head");
        this.out = out;
        this.table = new ArrayList();
        this.table.add(list);
        this.columns = list.size();
        List<String> $this$map$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            String it = (String) item$iv$iv;
            destination$iv$iv.add(Integer.valueOf(it.length()));
        }
        this.blockSize = (List) destination$iv$iv;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void addLine(@org.jetbrains.annotations.NotNull java.util.List<? extends java.lang.Object> r6) {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.framework.validator.PrettyTable.addLine(java.util.List):void");
    }

    public final void dump() {
        int lines = this.table.size();
        String normalBar = "+" + CollectionsKt.joinToString$default(this.blockSize, "+", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (v0) -> {
            return dump$lambda$3(v0);
        }, 30, (Object) null) + "+";
        String doubleBar = "+" + CollectionsKt.joinToString$default(this.blockSize, "+", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (v0) -> {
            return dump$lambda$4(v0);
        }, 30, (Object) null) + "+";
        this.out.println(normalBar);
        printLine(this.out, this.table.get(0));
        this.out.println(doubleBar);
        for (int i = 1; i < lines; i++) {
            printLine(this.out, this.table.get(i));
            this.out.println(normalBar);
        }
    }

    private static final CharSequence dump$lambda$3(int it) {
        return StringsKt.repeat("-", it + 2);
    }

    private static final CharSequence dump$lambda$4(int it) {
        return StringsKt.repeat("=", it + 2);
    }

    @Override // java.io.Flushable
    public void flush() {
        this.out.flush();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out.close();
    }

    private final void printLine(PrintWriter $this$printLine, List<String> list) {
        $this$printLine.print("|");
        List<String> $this$mapIndexed$iv = list;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$mapIndexed$iv, 10));
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexed$iv) {
            int i = index$iv$iv;
            index$iv$iv++;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String block = (String) item$iv$iv;
            int totalSize = (this.blockSize.get(i).intValue() - block.length()) + 2;
            int leftSize = totalSize / 2;
            int rightSize = totalSize - leftSize;
            destination$iv$iv.add(StringsKt.repeat(" ", leftSize) + block + StringsKt.repeat(" ", rightSize));
        }
        $this$printLine.print(CollectionsKt.joinToString$default((List) destination$iv$iv, "|", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        $this$printLine.println("|");
    }
}
