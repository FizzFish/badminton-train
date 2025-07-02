package cn.sast.api.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TimeRange.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��B\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n��\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010��\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0003\u0018�� *2\n\u0012\u0006\u0012\u0004\u0018\u00010��0\u0001:\u0001*B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010��H\u0096\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020��J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0003J\u0016\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020��J\u0013\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001a\u00020\fH\u0016J\u000e\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020��J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010&\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020��J\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020��0(2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020��0(R\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u001e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n��\u001a\u0004\b\n\u0010\tR\u001a\u0010\u001e\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"¨\u0006+"}, d2 = {"Lcn/sast/api/util/TimeRange;", "", "min", "", "max", "<init>", "(JJ)V", "value", "getMin", "()J", "getMax", "compareTo", "", "r", "setMin", "", "setMax", "contains", "", "time", "set", "intersects", "equals", "o", "", "hashCode", "overlaps", "rhs", "toString", "", "leftClose", "getLeftClose", "()Z", "setLeftClose", "(Z)V", "rightClose", "getRightClose", "setRightClose", "merge", "getRemains", "", "timeRangesPrev", "Companion", "corax-api"})
@SourceDebugExtension({"SMAP\nTimeRange.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeRange.kt\ncn/sast/api/util/TimeRange\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,331:1\n1#2:332\n*E\n"})
/* loaded from: TimeRange.class */
public final class TimeRange implements Comparable<TimeRange> {

    @NotNull
    public static final Companion Companion = new Companion(null);
    private long min;
    private long max;
    private boolean leftClose = true;
    private boolean rightClose = true;

    public TimeRange(long min, long max) {
        set(min, max);
    }

    public final long getMin() {
        return this.min;
    }

    public final long getMax() {
        return this.max;
    }

    @Override // java.lang.Comparable
    public int compareTo(@Nullable TimeRange r) {
        if (r == null) {
            throw new NullPointerException("The input cannot be null!");
        }
        if (this.min > r.min) {
            return 1;
        }
        if (this.min < r.min) {
            return -1;
        }
        if (this.max > r.max) {
            return 1;
        }
        if (this.max < r.max) {
            return -1;
        }
        return 0;
    }

    public final void setMin(long min) {
        if (!(min >= 0 && min <= this.max)) {
            throw new IllegalArgumentException("Invalid input!".toString());
        }
        this.min = min;
    }

    public final void setMax(long max) {
        if (!(max >= 0 && max >= this.min)) {
            throw new IllegalArgumentException("Invalid input!".toString());
        }
        this.max = max;
    }

    public final boolean contains(@NotNull TimeRange r) {
        Intrinsics.checkNotNullParameter(r, "r");
        return this.min <= r.min && this.max >= r.max;
    }

    public final boolean contains(long min, long max) {
        return (this.leftClose && this.rightClose) ? this.min <= min && this.max >= max : this.leftClose ? this.min <= min && this.max > max : this.rightClose ? this.min < min && this.max >= max : this.min < min && this.max > max;
    }

    public final boolean contains(long time) {
        return (this.leftClose && this.rightClose) ? time >= this.min && time <= this.max : this.leftClose ? time >= this.min && time < this.max : this.rightClose ? time > this.min && time <= this.max : time > this.min && time < this.max;
    }

    public final void set(long min, long max) {
        if (!(min <= max)) {
            throw new IllegalArgumentException(("min:" + min + " should not be larger than max: " + min).toString());
        }
        this.min = min;
        this.max = max;
    }

    public final boolean intersects(@NotNull TimeRange r) {
        Intrinsics.checkNotNullParameter(r, "r");
        if ((!this.leftClose || !r.rightClose) && r.max < this.min) {
            return false;
        }
        if (!this.leftClose && !r.rightClose && r.max <= this.min) {
            return false;
        }
        if (this.leftClose && r.rightClose && r.max <= this.min - 2) {
            return false;
        }
        if ((!this.rightClose || !r.leftClose) && r.min > this.max) {
            return false;
        }
        if (!this.rightClose && !r.leftClose && r.min >= this.max) {
            return false;
        }
        if (this.rightClose && r.leftClose && r.min >= this.max + 2) {
            return false;
        }
        return true;
    }

    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !Intrinsics.areEqual(getClass(), o.getClass())) {
            return false;
        }
        TimeRange that = (TimeRange) o;
        return this.min == that.min && this.max == that.max;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.min), Long.valueOf(this.max));
    }

    public final boolean overlaps(@NotNull TimeRange rhs) {
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        if ((!this.leftClose || !rhs.rightClose) && rhs.max <= this.min) {
            return false;
        }
        if (!this.leftClose && !rhs.rightClose && rhs.max <= this.min + 1) {
            return false;
        }
        if (this.leftClose && rhs.rightClose && rhs.max < this.min) {
            return false;
        }
        if ((!this.rightClose || !rhs.leftClose) && rhs.min >= this.max) {
            return false;
        }
        if (this.rightClose || rhs.leftClose || rhs.min + 1 < this.max) {
            return (this.rightClose && rhs.leftClose && rhs.min > this.max) ? false : true;
        }
        return false;
    }

    @NotNull
    public String toString() {
        StringBuilder res = new StringBuilder();
        if (this.leftClose) {
            res.append("[ ");
        } else {
            res.append("( ");
        }
        res.append(this.min).append(" : ").append(this.max);
        if (this.rightClose) {
            res.append(" ]");
        } else {
            res.append(" )");
        }
        String string = res.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final boolean getLeftClose() {
        return this.leftClose;
    }

    public final void setLeftClose(boolean z) {
        this.leftClose = z;
    }

    public final boolean getRightClose() {
        return this.rightClose;
    }

    public final void setRightClose(boolean z) {
        this.rightClose = z;
    }

    public final void merge(@NotNull TimeRange rhs) {
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        set(RangesKt.coerceAtMost(this.min, rhs.min), RangesKt.coerceAtLeast(this.max, rhs.max));
    }

    @NotNull
    public final List<TimeRange> getRemains(@NotNull List<TimeRange> list) {
        Intrinsics.checkNotNullParameter(list, "timeRangesPrev");
        List remains = new ArrayList();
        for (TimeRange prev : list) {
            if (prev.min >= this.max + 2) {
                break;
            }
            if (intersects(prev)) {
                if (prev.contains(this)) {
                    return remains;
                }
                if (contains(prev)) {
                    if (prev.min > this.min && prev.max == this.max) {
                        setMax(prev.min);
                        this.rightClose = false;
                        remains.add(this);
                        return remains;
                    }
                    if (prev.min == this.min) {
                        this.min = prev.max;
                        this.leftClose = false;
                    } else {
                        TimeRange r = new TimeRange(this.min, prev.min);
                        r.leftClose = this.leftClose;
                        r.rightClose = false;
                        remains.add(r);
                        this.min = prev.max;
                        this.leftClose = false;
                    }
                } else if (prev.min < this.min) {
                    this.min = prev.max;
                    this.leftClose = false;
                } else {
                    setMax(prev.min);
                    this.rightClose = false;
                    remains.add(this);
                    return remains;
                }
            }
        }
        remains.add(this);
        return remains;
    }

    /* compiled from: TimeRange.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\b"}, d2 = {"Lcn/sast/api/util/TimeRange$Companion;", "", "<init>", "()V", "sortAndMerge", "", "Lcn/sast/api/util/TimeRange;", "unionCandidates", "corax-api"})
    /* loaded from: TimeRange$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final List<TimeRange> sortAndMerge(@NotNull List<TimeRange> list) {
            Intrinsics.checkNotNullParameter(list, "unionCandidates");
            CollectionsKt.sort(list);
            LinkedList unionResult = new LinkedList();
            Iterator iterator = list.iterator();
            if (!iterator.hasNext()) {
                return unionResult;
            }
            TimeRange rangeCurr = iterator.next();
            while (iterator.hasNext()) {
                TimeRange rangeNext = iterator.next();
                if (rangeCurr.intersects(rangeNext)) {
                    rangeCurr.merge(rangeNext);
                } else {
                    unionResult.add(rangeCurr);
                    rangeCurr = rangeNext;
                }
            }
            unionResult.add(rangeCurr);
            return unionResult;
        }
    }
}
