package cn.sast.framework.report;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JavaSourceLocator.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��8\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000b\n��\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b&\u0018�� \u001d*\u0004\b��\u0010\u00012\u00020\u0002:\u0002\u001c\u001dB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J#\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00028��2\u0006\u0010\u000b\u001a\u00020\fH&¢\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028��0\u000f2\u0006\u0010\u0010\u001a\u00020\tH&J\u0012\u0010\u0011\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\b*\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u0002J$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028��0\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\u000b\u001a\u00020\fJ#\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028��0\u00142\u0006\u0010\u0016\u001a\u00028��2\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\u0017J\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028��0\b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u000b\u001a\u00020\fJ#\u0010\u001a\u001a\u0004\u0018\u00018��2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u000f2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n��¨\u0006\u001e"}, d2 = {"Lcn/sast/framework/report/AbstractFileIndexer;", "PathType", "", "<init>", "()V", "errorMsgShow", "", "getNames", "", "", "path", "mode", "Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;", "(Ljava/lang/Object;Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;)Ljava/util/List;", "getPathsByName", "", "name", "hasDot", "normalizePathParts", "findFromFileIndexMap", "Lkotlin/sequences/Sequence;", "toFindNames", "find", "(Ljava/lang/Object;Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;)Lkotlin/sequences/Sequence;", "findFiles", "fileNames", "findAnyFile", "(Ljava/util/Collection;Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;)Ljava/lang/Object;", "CompareMode", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nJavaSourceLocator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/AbstractFileIndexer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,490:1\n1381#2:491\n1469#2,2:492\n1471#2,3:495\n1#3:494\n*S KotlinDebug\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/AbstractFileIndexer\n*L\n197#1:491\n197#1:492,2\n197#1:495,3\n*E\n"})
/* loaded from: AbstractFileIndexer.class */
public abstract class AbstractFileIndexer<PathType> {
    private boolean errorMsgShow;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static CompareMode defaultClassCompareMode = CompareMode.Class;

    @NotNull
    public abstract List<String> getNames(PathType pathtype, @NotNull CompareMode compareMode);

    @NotNull
    public abstract Collection<PathType> getPathsByName(@NotNull String str);

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n��\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018��2\b\u0012\u0004\u0012\u00020��0\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\u0002\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;", "", "isClass", "", "<init>", "(Ljava/lang/String;IZ)V", "()Z", "Path", "Class", "ClassNoPackageLayout", "corax-framework"})
    /* loaded from: AbstractFileIndexer$CompareMode.class */
    public enum CompareMode {
        Path(false),
        Class(true),
        ClassNoPackageLayout(true);

        private final boolean isClass;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        CompareMode(boolean isClass) {
            this.isClass = isClass;
        }

        public final boolean isClass() {
            return this.isClass;
        }

        @NotNull
        public static EnumEntries<CompareMode> getEntries() {
            return $ENTRIES;
        }
    }

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0014\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcn/sast/framework/report/AbstractFileIndexer$Companion;", "", "<init>", "()V", "defaultClassCompareMode", "Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;", "getDefaultClassCompareMode", "()Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;", "setDefaultClassCompareMode", "(Lcn/sast/framework/report/AbstractFileIndexer$CompareMode;)V", "corax-framework"})
    /* loaded from: AbstractFileIndexer$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final CompareMode getDefaultClassCompareMode() {
            return AbstractFileIndexer.defaultClassCompareMode;
        }

        public final void setDefaultClassCompareMode(@NotNull CompareMode compareMode) {
            Intrinsics.checkNotNullParameter(compareMode, "<set-?>");
            AbstractFileIndexer.defaultClassCompareMode = compareMode;
        }
    }

    private final boolean hasDot(List<String> list) {
        int sz = list.size();
        if (sz <= 1) {
            return false;
        }
        int i = 0;
        for (String e : list) {
            i++;
            if (i != sz && StringsKt.contains$default(e, ".", false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ List normalizePathParts$default(AbstractFileIndexer abstractFileIndexer, List list, CompareMode compareMode, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: normalizePathParts");
        }
        if ((i & 1) != 0) {
            compareMode = CompareMode.Path;
        }
        return abstractFileIndexer.normalizePathParts(list, compareMode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> normalizePathParts(List<String> list, CompareMode mode) {
        if (mode.isClass() && hasDot(list)) {
            int lastIndex = CollectionsKt.getLastIndex(list);
            ArrayList ret = new ArrayList(list.size() + 2);
            int i = 0;
            for (String e : list) {
                int i2 = i;
                i++;
                if (i2 != lastIndex && StringsKt.contains$default(e, ".", false, 2, (Object) null)) {
                    CollectionsKt.addAll(ret, StringsKt.split$default(e, new String[]{"."}, false, 0, 6, (Object) null));
                } else {
                    ret.add(e);
                }
            }
            return ret;
        }
        return list;
    }

    /* compiled from: JavaSourceLocator.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 3, xi = 48, d1 = {"��\f\n��\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\u0010��\u001a\u00020\u0001\"\u0004\b��\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "PathType", "Lkotlin/sequences/SequenceScope;"})
    @DebugMetadata(f = "JavaSourceLocator.kt", l = {180, 184}, i = {PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod, PseudoTopologicalOrderer.REVERSE, PseudoTopologicalOrderer.REVERSE}, s = {"L$0", "L$1", "L$0", "L$1"}, n = {"$this$sequence", "find", "$this$sequence", "find"}, m = "invokeSuspend", c = "cn.sast.framework.report.AbstractFileIndexer$findFromFileIndexMap$1")
    @SourceDebugExtension({"SMAP\nJavaSourceLocator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/AbstractFileIndexer$findFromFileIndexMap$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,490:1\n1863#2,2:491\n*S KotlinDebug\n*F\n+ 1 JavaSourceLocator.kt\ncn/sast/framework/report/AbstractFileIndexer$findFromFileIndexMap$1\n*L\n177#1:491,2\n*E\n"})
    /* renamed from: cn.sast.framework.report.AbstractFileIndexer$findFromFileIndexMap$1, reason: invalid class name */
    /* loaded from: AbstractFileIndexer$findFromFileIndexMap$1.class */
    static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super PathType>, Continuation<? super Unit>, Object> {
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        private /* synthetic */ Object L$0;
        final /* synthetic */ List<String> $toFindNames;
        final /* synthetic */ AbstractFileIndexer<PathType> this$0;
        final /* synthetic */ CompareMode $mode;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<String> list, AbstractFileIndexer<PathType> abstractFileIndexer, CompareMode $mode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$toFindNames = list;
            this.this$0 = abstractFileIndexer;
            this.$mode = $mode;
        }

        public final Continuation<Unit> create(Object value, Continuation<?> continuation) {
            Continuation<Unit> anonymousClass1 = new AnonymousClass1(this.$toFindNames, this.this$0, this.$mode, continuation);
            anonymousClass1.L$0 = value;
            return anonymousClass1;
        }

        public final Object invoke(SequenceScope<? super PathType> sequenceScope, Continuation<? super Unit> continuation) {
            return create(sequenceScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final Object invokeSuspend(Object $result) {
            Iterator it;
            CompareMode compareMode;
            AbstractFileIndexer<PathType> abstractFileIndexer;
            List find;
            SequenceScope $this$sequence;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case PointsToGraphKt.pathStrictMod /* 0 */:
                    ResultKt.throwOnFailure($result);
                    $this$sequence = (SequenceScope) this.L$0;
                    if (!this.$toFindNames.isEmpty()) {
                        find = this.this$0.normalizePathParts(this.$toFindNames, this.$mode);
                        Iterable $this$forEach$iv = this.this$0.getPathsByName((String) CollectionsKt.last(find));
                        abstractFileIndexer = this.this$0;
                        compareMode = this.$mode;
                        it = $this$forEach$iv.iterator();
                        break;
                    } else {
                        return Unit.INSTANCE;
                    }
                case PseudoTopologicalOrderer.REVERSE /* 1 */:
                    it = (Iterator) this.L$4;
                    compareMode = (CompareMode) this.L$3;
                    abstractFileIndexer = (AbstractFileIndexer) this.L$2;
                    find = (List) this.L$1;
                    $this$sequence = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                case 2:
                    it = (Iterator) this.L$4;
                    compareMode = (CompareMode) this.L$3;
                    abstractFileIndexer = (AbstractFileIndexer) this.L$2;
                    find = (List) this.L$1;
                    $this$sequence = (SequenceScope) this.L$0;
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            while (it.hasNext()) {
                Object element$iv = it.next();
                List checkNames = abstractFileIndexer.normalizePathParts(abstractFileIndexer.getNames(element$iv, compareMode), compareMode);
                if (compareMode == CompareMode.ClassNoPackageLayout) {
                    this.L$0 = $this$sequence;
                    this.L$1 = find;
                    this.L$2 = abstractFileIndexer;
                    this.L$3 = compareMode;
                    this.L$4 = it;
                    this.label = 1;
                    if ($this$sequence.yield(element$iv, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    ListSuffixRelation r = JavaSourceLocatorKt.listEndsWith(checkNames, find);
                    if (r == ListSuffixRelation.Equals || r == ListSuffixRelation.BIsSuffixOfA) {
                        this.L$0 = $this$sequence;
                        this.L$1 = find;
                        this.L$2 = abstractFileIndexer;
                        this.L$3 = compareMode;
                        this.L$4 = it;
                        this.label = 2;
                        if ($this$sequence.yield(element$iv, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    public static /* synthetic */ Sequence findFromFileIndexMap$default(AbstractFileIndexer abstractFileIndexer, List list, CompareMode compareMode, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findFromFileIndexMap");
        }
        if ((i & 2) != 0) {
            compareMode = CompareMode.Path;
        }
        return abstractFileIndexer.findFromFileIndexMap((List<String>) list, compareMode);
    }

    @NotNull
    public final Sequence<PathType> findFromFileIndexMap(@NotNull List<String> list, @NotNull CompareMode mode) {
        Intrinsics.checkNotNullParameter(list, "toFindNames");
        Intrinsics.checkNotNullParameter(mode, "mode");
        return SequencesKt.sequence(new AnonymousClass1(list, this, mode, null));
    }

    public static /* synthetic */ Sequence findFromFileIndexMap$default(AbstractFileIndexer abstractFileIndexer, Object obj, CompareMode compareMode, int i, Object obj2) {
        if (obj2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findFromFileIndexMap");
        }
        if ((i & 2) != 0) {
            compareMode = CompareMode.Path;
        }
        return abstractFileIndexer.findFromFileIndexMap((AbstractFileIndexer) obj, compareMode);
    }

    @NotNull
    public final Sequence<PathType> findFromFileIndexMap(PathType pathtype, @NotNull CompareMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        return findFromFileIndexMap(getNames(pathtype, mode), mode);
    }

    @NotNull
    public final List<PathType> findFiles(@NotNull Collection<String> collection, @NotNull CompareMode mode) {
        Intrinsics.checkNotNullParameter(collection, "fileNames");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Collection<String> $this$flatMap$iv = collection;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            String s = (String) element$iv$iv;
            if (!(StringsKt.indexOf$default(s, "\\", 0, false, 6, (Object) null) == -1)) {
                throw new IllegalArgumentException(("invalid source file name: " + s).toString());
            }
            Sequence list$iv$iv = findFromFileIndexMap(StringsKt.split$default(s, new String[]{"/"}, false, 0, 6, (Object) null), mode);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return (List) destination$iv$iv;
    }

    @Nullable
    public final PathType findAnyFile(@NotNull Collection<String> collection, @NotNull CompareMode compareMode) {
        Pair pair;
        Intrinsics.checkNotNullParameter(collection, "fileNames");
        Intrinsics.checkNotNullParameter(compareMode, "mode");
        Iterator<T> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                pair = null;
                break;
            }
            String str = (String) it.next();
            if (!(StringsKt.indexOf$default(str, "\\", 0, false, 6, (Object) null) == -1)) {
                throw new IllegalArgumentException(("invalid source file name: " + str + " in " + collection).toString());
            }
            Object objFirstOrNull = SequencesKt.firstOrNull(findFromFileIndexMap(StringsKt.split$default(str, new String[]{"/"}, false, 0, 6, (Object) null), compareMode));
            Pair pair2 = objFirstOrNull != null ? TuplesKt.to(str, objFirstOrNull) : null;
            if (pair2 != null) {
                pair = pair2;
                break;
            }
        }
        Pair pair3 = pair;
        if (pair3 != null) {
            return (PathType) pair3.getSecond();
        }
        return null;
    }
}
