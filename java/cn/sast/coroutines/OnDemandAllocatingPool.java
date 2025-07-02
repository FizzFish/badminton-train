package cn.sast.coroutines;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlinx.atomicfu.AtomicArray;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicFU_commonKt;
import kotlinx.atomicfu.AtomicInt;
import org.jetbrains.annotations.NotNull;

/* compiled from: OnDemandAllocatingPool.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��>\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n��\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018��*\u0004\b��\u0010\u00012\u00020\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028��0\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004H\u0082\bJ\r\u0010\u000e\u001a\u00020\u000f*\u00020\u0004H\u0082\bJ\u0006\u0010\u0010\u001a\u00020\u000fJ\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028��0\u0012J\r\u0010\u0013\u001a\u00020\u0014H��¢\u0006\u0002\b\u0015J\b\u0010\u0016\u001a\u00020\u0014H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028��0\u0006X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n��R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018��0\fX\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0017"}, d2 = {"Lcn/sast/coroutines/OnDemandAllocatingPool;", "T", "", "maxCapacity", "", "create", "Lkotlin/Function1;", "<init>", "(ILkotlin/jvm/functions/Function1;)V", "controlState", "Lkotlinx/atomicfu/AtomicInt;", "elements", "Lkotlinx/atomicfu/AtomicArray;", "tryForbidNewElements", "isClosed", "", "allocate", "close", "", "stateRepresentation", "", "stateRepresentation$corax_api", "toString", "corax-api"})
@SourceDebugExtension({"SMAP\nOnDemandAllocatingPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnDemandAllocatingPool.kt\ncn/sast/coroutines/OnDemandAllocatingPool\n+ 2 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 OnDemandAllocatingPool.kt\ncn/sast/coroutines/OnDemandAllocatingPoolKt\n*L\n1#1,108:1\n43#1:111\n43#1:114\n36#1:115\n37#1,7:118\n43#1:135\n350#2,2:109\n350#2,2:112\n350#2,2:116\n1557#3:125\n1628#3,2:126\n1630#3:130\n1557#3:131\n1628#3,3:132\n103#4,2:128\n*S KotlinDebug\n*F\n+ 1 OnDemandAllocatingPool.kt\ncn/sast/coroutines/OnDemandAllocatingPool\n*L\n37#1:111\n56#1:114\n78#1:115\n78#1:118,7\n94#1:135\n36#1:109,2\n55#1:112,2\n78#1:116,2\n79#1:125\n79#1:126,2\n79#1:130\n93#1:131\n93#1:132,3\n81#1:128,2\n*E\n"})
/* loaded from: OnDemandAllocatingPool.class */
public final class OnDemandAllocatingPool<T> {
    private final int maxCapacity;

    @NotNull
    private final Function1<Integer, T> create;

    @NotNull
    private final AtomicInt controlState;

    @NotNull
    private final AtomicArray<T> elements;

    /* JADX WARN: Multi-variable type inference failed */
    public OnDemandAllocatingPool(int maxCapacity, @NotNull Function1<? super Integer, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(function1, "create");
        this.maxCapacity = maxCapacity;
        this.create = function1;
        this.controlState = AtomicFU.atomic(0);
        this.elements = AtomicFU_commonKt.atomicArrayOfNulls(this.maxCapacity);
    }

    private final int tryForbidNewElements() {
        int it;
        AtomicInt $this$loop$iv = this.controlState;
        do {
            it = $this$loop$iv.getValue();
            if ((it & Integer.MIN_VALUE) != 0) {
                return 0;
            }
        } while (!this.controlState.compareAndSet(it, it | Integer.MIN_VALUE));
        return it;
    }

    private final boolean isClosed(int $this$isClosed) {
        return ($this$isClosed & Integer.MIN_VALUE) != 0;
    }

    public final boolean allocate() {
        int ctl;
        AtomicInt $this$loop$iv = this.controlState;
        do {
            ctl = $this$loop$iv.getValue();
            if ((ctl & Integer.MIN_VALUE) != 0) {
                return false;
            }
            if (ctl >= this.maxCapacity) {
                return true;
            }
        } while (!this.controlState.compareAndSet(ctl, ctl + 1));
        this.elements.get(ctl).setValue(this.create.invoke(Integer.valueOf(ctl)));
        return true;
    }

    @NotNull
    public final List<T> close() {
        int i;
        Object element;
        AtomicInt $this$loop$iv$iv = this.controlState;
        while (true) {
            int it$iv = $this$loop$iv$iv.getValue();
            if ((it$iv & Integer.MIN_VALUE) != 0) {
                i = 0;
                break;
            }
            if (this.controlState.compareAndSet(it$iv, it$iv | Integer.MIN_VALUE)) {
                i = it$iv;
                break;
            }
        }
        int elementsExisting = i;
        Iterable $this$map$iv = RangesKt.until(0, elementsExisting);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        IntIterator it = $this$map$iv.iterator();
        while (it.hasNext()) {
            int item$iv$iv = it.nextInt();
            do {
                element = this.elements.get(item$iv$iv).getAndSet((Object) null);
            } while (element == null);
            destination$iv$iv.add(element);
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public final String stateRepresentation$corax_api() {
        int ctl = this.controlState.getValue();
        Iterable $this$map$iv = RangesKt.until(0, ctl & Integer.MAX_VALUE);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        IntIterator it = $this$map$iv.iterator();
        while (it.hasNext()) {
            int item$iv$iv = it.nextInt();
            destination$iv$iv.add(this.elements.get(item$iv$iv).getValue());
        }
        String elementsStr = ((List) destination$iv$iv).toString();
        String closedStr = (ctl & Integer.MIN_VALUE) != 0 ? "[closed]" : "";
        return elementsStr + closedStr;
    }

    @NotNull
    public String toString() {
        return "OnDemandAllocatingPool(" + stateRepresentation$corax_api() + ")";
    }
}
