package cn.sast.dataflow.interprocedural.analysis;

import cn.sast.dataflow.interprocedural.analysis.IData;
import cn.sast.dataflow.interprocedural.analysis.IFact;
import cn.sast.dataflow.interprocedural.analysis.IHeapKVData;
import cn.sast.dataflow.interprocedural.analysis.IHeapValues;
import cn.sast.dataflow.interprocedural.analysis.ReferenceContext;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.ArrayHeapKV;
import cn.sast.dataflow.interprocedural.analysis.heapimpl.IArrayHeapKV;
import cn.sast.dataflow.interprocedural.check.BuiltInModelT;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.interprocedural.check.heapimpl.FieldHeapKV;
import cn.sast.dataflow.util.Printer;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.ImmutableSet;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.ArrayType;
import soot.G;
import soot.Local;
import soot.RefType;
import soot.SootMethod;
import soot.Type;
import soot.Value;
import soot.jimple.Constant;
import soot.jimple.IntConstant;

/* compiled from: PointsToGraphAbstract.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��æ\u0001\n\u0002\u0018\u0002\n��\n\u0002\u0010��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n��\b&\u0018��*\b\b��\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0085\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u000e0\r\u0012(\u0010\u000f\u001a$\u0012\u0004\u0012\u00028��\u0012\u001a\u0012\u0018\u0012\b\u0012\u00060\u0002j\u0002`\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00120\u00100\r\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0004\b\u0016\u0010\u0017J \u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u0002H\u0016J.\u0010-\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\f\u0010.\u001a\b\u0012\u0004\u0012\u00028��0\u000e2\u0006\u0010/\u001a\u000200H\u0016J<\u00101\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u0010)\u001a\u00020*2\f\u00102\u001a\b\u0012\u0004\u0012\u00028��032\n\u00104\u001a\u00060\u0002j\u0002`\u00112\b\u00105\u001a\u0004\u0018\u00010\u0002H&J\u0010\u00106\u001a\u00020(2\u0006\u00107\u001a\u00020\u0002H\u0016J2\u00108\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00122\u0006\u0010)\u001a\u00020*2\f\u00109\u001a\b\u0012\u0004\u0012\u00028��032\n\u00104\u001a\u00060\u0002j\u0002`\u0011H&J¬\u0001\u0010:\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e\"\b\b\u0001\u0010;*\u00020\u00022\u0006\u0010)\u001a\u00020*2\n\u00104\u001a\u00060\u0002j\u0002`\u00112(\u0010<\u001a$\u0012\u0004\u0012\u00028��\u0012\u001a\u0012\u0018\u0012\b\u0012\u00060\u0002j\u0002`\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00120\u00100\u00102\u0006\u0010,\u001a\u00020\u00022\b\u00105\u001a\u0004\u0018\u0001H;2\u0006\u00101\u001a\u00020025\u0010=\u001a1\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028��03¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(A\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H;\u0012\u0004\u0012\u00028��\u0018\u00010B0>¢\u0006\u0002\u0010CJX\u0010D\u001a\u000200\"\u0006\b\u0001\u0010E\u0018\u00012\n\u00104\u001a\u00060\u0002j\u0002`\u00112\u0006\u0010,\u001a\u00020\u000221\b\u0004\u0010F\u001a+\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028��03¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(H\u0012\u0006\u0012\u0004\u0018\u0001HE\u0012\u0004\u0012\u00020(0GH\u0082\bJº\u0001\u0010I\u001a\u000200\"\b\b\u0001\u0010;*\u00020\u00022\u0006\u0010)\u001a\u00020*2\n\u00104\u001a\u00060\u0002j\u0002`\u00112(\u0010<\u001a$\u0012\u0004\u0012\u00028��\u0012\u001a\u0012\u0018\u0012\b\u0012\u00060\u0002j\u0002`\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00120\u00100\u00102\f\u0010+\u001a\b\u0012\u0004\u0012\u00028��032\b\u00105\u001a\u0004\u0018\u0001H;2\u000e\u0010J\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u0010/\u001a\u00020025\u0010=\u001a1\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028��03¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(A\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u0002H;\u0012\u0004\u0012\u00028��\u0018\u00010B0>¢\u0006\u0002\u0010KJ\u0016\u0010L\u001a\b\u0012\u0004\u0012\u00028��0M2\u0006\u0010N\u001a\u00020OH&J<\u0010P\u001a\b\u0012\u0004\u0012\u00028��0Q2\u0006\u0010)\u001a\u00020*2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028��0\u000e2\u0006\u0010N\u001a\u00020R2\u000e\u0010S\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000eH&J<\u0010T\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\f\u0010U\u001a\b\u0012\u0004\u0012\u00028��0\u000e2\u0006\u0010V\u001a\u00020W2\u000e\u0010J\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u0010/\u001a\u000200J6\u0010T\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010V\u001a\u00020W2\u000e\u0010J\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u0010/\u001a\u000200J0\u0010X\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010V\u001a\u00020W2\u0006\u0010,\u001a\u00020\u00022\u0006\u0010/\u001a\u000200H\u0016J.\u0010Y\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010V\u001a\u00020W2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028��0\u000eH\u0016J\u0010\u0010Z\u001a\u00020(2\u0006\u0010+\u001a\u00020\u0002H\u0016J0\u0010[\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u00022\u0006\u0010V\u001a\u00020W2\u0006\u0010\\\u001a\u000200H\u0016J\b\u0010]\u001a\u00020^H\u0016J\u0016\u0010_\u001a\u00020(2\f\u0010`\u001a\b\u0012\u0004\u0012\u00028��0aH\u0016J\b\u0010b\u001a\u00020(H\u0016J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020cH\u0016J\u0010\u0010d\u001a\u00020(2\u0006\u0010e\u001a\u00020\u0015H\u0016J\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150fH\u0016J\u0018\u0010g\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u00107\u001a\u00020\u0002H\u0016J\b\u0010h\u001a\u000200H\u0016J\b\u0010i\u001a\u000200H\u0016J\u0018\u0010j\u001a\u0004\u0018\u00010k2\f\u0010l\u001a\b\u0012\u0004\u0012\u00028��03H&J7\u0010m\u001a+\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028��03¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(A\u0012\f\u0012\n\u0012\u0004\u0012\u00028��\u0018\u00010Q0>2\u0006\u0010)\u001a\u00020*J/\u0010n\u001a+\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00028��03¢\u0006\f\b?\u0012\b\b@\u0012\u0004\b\b(A\u0012\f\u0012\n\u0012\u0004\u0012\u00028��\u0018\u00010M0>J8\u0010o\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\b\u0010p\u001a\u0004\u0018\u00010q2\u000e\u0010J\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u000e2\u0006\u0010/\u001a\u000200J0\u0010r\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010p\u001a\u00020q2\u0006\u0010,\u001a\u00020q2\u0006\u0010s\u001a\u00020kH\u0016J0\u0010t\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\b\u0010p\u001a\u0004\u0018\u00010q2\f\u0010.\u001a\b\u0012\u0004\u0012\u00028��0\u000eH\u0016J\u001a\u0010u\u001a\n\u0012\u0004\u0012\u00020w\u0018\u00010v2\b\u0010p\u001a\u0004\u0018\u00010qH\u0002J*\u0010x\u001a\u0002002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020\u00022\b\u0010p\u001a\u0004\u0018\u00010qH\u0016J*\u0010x\u001a\u0002002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020q2\b\u0010p\u001a\u0004\u0018\u00010qH\u0016J$\u0010y\u001a\u00020(2\f\u0010l\u001a\b\u0012\u0004\u0012\u00028��032\f\u0010z\u001a\b\u0012\u0004\u0012\u00028��0\u0012H\u0002J8\u0010{\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010V\u001a\u00020W2\u0006\u0010,\u001a\u00020q2\u0006\u0010s\u001a\u00020k2\u0006\u0010/\u001a\u000200H\u0016J(\u0010|\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\u0006\u0010,\u001a\u00020q2\u0006\u0010s\u001a\u00020kH\u0016J6\u0010}\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00022\f\u0010.\u001a\b\u0012\u0004\u0012\u00028��0\u000e2\u0006\u0010N\u001a\u00020R2\u0006\u0010~\u001a\u00020qH\u0016J*\u0010\u007f\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u00122\u0006\u0010A\u001a\u00028��2\n\u00104\u001a\u00060\u0002j\u0002`\u0011H\u0016¢\u0006\u0003\u0010\u0080\u0001J<\u0010\u0081\u0001\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010A\u001a\u00028��2\n\u00104\u001a\u00060\u0002j\u0002`\u00112\u000f\u0010\u0082\u0001\u001a\n\u0012\u0004\u0012\u00028��\u0018\u00010\u0012H\u0016¢\u0006\u0003\u0010\u0083\u0001J!\u0010\u0084\u0001\u001a\u00020(2\u0007\u0010\u0085\u0001\u001a\u00028��2\u0007\u0010\u0086\u0001\u001a\u00028��H\u0016¢\u0006\u0003\u0010\u0087\u0001J\u0017\u0010\u0088\u0001\u001a\u00020(2\u000e\u0010\u0089\u0001\u001a\t\u0012\u0004\u0012\u00028��0\u008a\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028��0\u0005¢\u0006\b\n��\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028��0\u0007X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\b\u001a\u00020\tX\u0084\u0004¢\u0006\b\n��\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\n\u001a\u00020\u000bX\u0096\u000e¢\u0006\u000e\n��\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R&\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u000e0\rX\u0084\u0004¢\u0006\b\n��\u001a\u0004\b\"\u0010#R6\u0010\u000f\u001a$\u0012\u0004\u0012\u00028��\u0012\u001a\u0012\u0018\u0012\b\u0012\u00060\u0002j\u0002`\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00028��0\u00120\u00100\rX\u0084\u0004¢\u0006\b\n��\u001a\u0004\b$\u0010#R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\b\n��\u001a\u0004\b%\u0010&¨\u0006\u008b\u0001"}, d2 = {"Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphBuilderAbstract;", "V", "", "Lcn/sast/dataflow/interprocedural/analysis/IFact$Builder;", "orig", "Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstract;", "hf", "Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "vg", "Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "callStack", "Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "slots", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "heap", "Lkotlinx/collections/immutable/PersistentMap;", "Lcn/sast/dataflow/interprocedural/check/ModelT;", "Lcn/sast/dataflow/interprocedural/analysis/IData;", "calledMethods", "Lkotlinx/collections/immutable/PersistentSet$Builder;", "Lsoot/SootMethod;", "<init>", "(Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstract;Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;Lkotlinx/collections/immutable/PersistentMap$Builder;Lkotlinx/collections/immutable/PersistentMap$Builder;Lkotlinx/collections/immutable/PersistentSet$Builder;)V", "getOrig", "()Lcn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstract;", "getHf", "()Lcn/sast/dataflow/interprocedural/analysis/AbstractHeapFactory;", "getVg", "()Lcn/sast/dataflow/interprocedural/analysis/IVGlobal;", "getCallStack", "()Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;", "setCallStack", "(Lcn/sast/dataflow/interprocedural/analysis/CallStackContext;)V", "getSlots", "()Lkotlinx/collections/immutable/PersistentMap$Builder;", "getHeap", "getCalledMethods", "()Lkotlinx/collections/immutable/PersistentSet$Builder;", "assignLocal", "", "env", "Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;", "lhs", "rhs", "assignNewExpr", "allocSite", "append", "", "newSummary", "src", "Lcn/sast/dataflow/interprocedural/analysis/CompanionV;", "mt", "key", "kill", "slot", "getConstantPoolObjectData", "cv", "getHeapKVData", "K", "oldHeap", "emptyIData", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "v", "Lcn/sast/dataflow/interprocedural/analysis/IHeapKVData;", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Object;Lkotlinx/collections/immutable/PersistentMap;Ljava/lang/Object;Ljava/lang/Object;ZLkotlin/jvm/functions/Function1;)Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;", "getHeapData", "DT", "transform", "Lkotlin/Function2;", "rhsV", "setHeapKVData", "update", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Object;Lkotlinx/collections/immutable/PersistentMap;Lcn/sast/dataflow/interprocedural/analysis/CompanionV;Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/IHeapValues;ZLkotlin/jvm/functions/Function1;)Z", "getEmptyFieldSpace", "Lcn/sast/dataflow/interprocedural/check/heapimpl/FieldHeapKV;", "type", "Lsoot/RefType;", "getEmptyArraySpace", "Lcn/sast/dataflow/interprocedural/analysis/heapimpl/ArrayHeapKV;", "Lsoot/ArrayType;", "arrayLength", "assignField", "lhsPointees", "field", "Lcn/sast/dataflow/interprocedural/analysis/JFieldType;", "setField", "setFieldNew", "summarizeTargetFields", "getField", "newSummaryField", "toString", "", "union", "that", "Lcn/sast/dataflow/interprocedural/analysis/IFact;", "gc", "", "addCalledMethod", "sm", "Lkotlinx/collections/immutable/ImmutableSet;", "getTargetsUnsafe", "isBottom", "isTop", "getType", "Lsoot/Type;", "value", "emptyArrayFx", "emptyFieldFx", "setArray", "index", "Lsoot/Value;", "setArraySootValue", "valueType", "setArrayValueNew", "getAllIndex", "", "", "getArrayValue", "setIData", "bindData", "assignFieldSootValue", "assignLocalSootValue", "assignNewArray", "size", "getValueData", "(Ljava/lang/Object;Ljava/lang/Object;)Lcn/sast/dataflow/interprocedural/analysis/IData;", "setValueData", "data", "(Lcn/sast/dataflow/interprocedural/analysis/HeapValuesEnv;Ljava/lang/Object;Ljava/lang/Object;Lcn/sast/dataflow/interprocedural/analysis/IData;)V", "copyValueData", "from", "to", "(Ljava/lang/Object;Ljava/lang/Object;)V", "apply", "re", "Lcn/sast/dataflow/interprocedural/analysis/IReNew;", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nPointsToGraphAbstract.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PointsToGraphAbstract.kt\ncn/sast/dataflow/interprocedural/analysis/PointsToGraphBuilderAbstract\n+ 2 PointsToGraphAbstract.kt\ncn/sast/dataflow/interprocedural/analysis/PointsToGraphAbstractKt\n*L\n1#1,1045:1\n119#2,3:1046\n119#2,3:1049\n119#2,3:1052\n119#2,3:1055\n119#2,3:1058\n*S KotlinDebug\n*F\n+ 1 PointsToGraphAbstract.kt\ncn/sast/dataflow/interprocedural/analysis/PointsToGraphBuilderAbstract\n*L\n580#1:1046,3\n610#1:1049,3\n650#1:1052,3\n865#1:1055,3\n979#1:1058,3\n*E\n"})
/* loaded from: PointsToGraphBuilderAbstract.class */
public abstract class PointsToGraphBuilderAbstract<V> implements IFact.Builder<V> {

    @NotNull
    private final PointsToGraphAbstract<V> orig;

    @NotNull
    private final AbstractHeapFactory<V> hf;

    @NotNull
    private final IVGlobal vg;

    @NotNull
    private CallStackContext callStack;

    @NotNull
    private final PersistentMap.Builder<Object, IHeapValues<V>> slots;

    @NotNull
    private final PersistentMap.Builder<V, PersistentMap<Object, IData<V>>> heap;

    @NotNull
    private final PersistentSet.Builder<SootMethod> calledMethods;

    @Nullable
    public abstract IHeapValues<V> newSummary(@NotNull HeapValuesEnv heapValuesEnv, @NotNull CompanionV<V> companionV, @NotNull Object obj, @Nullable Object obj2);

    @Nullable
    public abstract IData<V> getConstantPoolObjectData(@NotNull HeapValuesEnv heapValuesEnv, @NotNull CompanionV<V> companionV, @NotNull Object obj);

    @NotNull
    public abstract FieldHeapKV<V> getEmptyFieldSpace(@NotNull RefType refType);

    @NotNull
    public abstract ArrayHeapKV<V> getEmptyArraySpace(@NotNull HeapValuesEnv heapValuesEnv, @NotNull IHeapValues<V> iHeapValues, @NotNull ArrayType arrayType, @Nullable IHeapValues<V> iHeapValues2);

    @Nullable
    public abstract Type getType(@NotNull CompanionV<V> companionV);

    public PointsToGraphBuilderAbstract(@NotNull PointsToGraphAbstract<V> pointsToGraphAbstract, @NotNull AbstractHeapFactory<V> abstractHeapFactory, @NotNull IVGlobal vg, @NotNull CallStackContext callStack, @NotNull PersistentMap.Builder<Object, IHeapValues<V>> builder, @NotNull PersistentMap.Builder<V, PersistentMap<Object, IData<V>>> builder2, @NotNull PersistentSet.Builder<SootMethod> builder3) {
        Intrinsics.checkNotNullParameter(pointsToGraphAbstract, "orig");
        Intrinsics.checkNotNullParameter(abstractHeapFactory, "hf");
        Intrinsics.checkNotNullParameter(vg, "vg");
        Intrinsics.checkNotNullParameter(callStack, "callStack");
        Intrinsics.checkNotNullParameter(builder, "slots");
        Intrinsics.checkNotNullParameter(builder2, "heap");
        Intrinsics.checkNotNullParameter(builder3, "calledMethods");
        this.orig = pointsToGraphAbstract;
        this.hf = abstractHeapFactory;
        this.vg = vg;
        this.callStack = callStack;
        this.slots = builder;
        this.heap = builder2;
        this.calledMethods = builder3;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public IHeapValues<V> getTargets(@NotNull Object slot) {
        return IFact.Builder.DefaultImpls.getTargets(this, slot);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isValid() {
        return IFact.Builder.DefaultImpls.isValid(this);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IHeapValues<V> getArrayLength(@NotNull V v) {
        return IFact.Builder.DefaultImpls.getArrayLength(this, v);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IArrayHeapKV<Integer, V> getArray(@NotNull V v) {
        return IFact.Builder.DefaultImpls.getArray(this, v);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public IHeapValues<V> getOfSootValue(@NotNull HeapValuesEnv env, @NotNull Value value, @NotNull Type valueType) {
        return IFact.Builder.DefaultImpls.getOfSootValue(this, env, value, valueType);
    }

    @NotNull
    public final PointsToGraphAbstract<V> getOrig() {
        return this.orig;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public AbstractHeapFactory<V> getHf() {
        return this.hf;
    }

    @NotNull
    protected final IVGlobal getVg() {
        return this.vg;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    public CallStackContext getCallStack() {
        return this.callStack;
    }

    public void setCallStack(@NotNull CallStackContext callStackContext) {
        Intrinsics.checkNotNullParameter(callStackContext, "<set-?>");
        this.callStack = callStackContext;
    }

    @NotNull
    protected final PersistentMap.Builder<Object, IHeapValues<V>> getSlots() {
        return this.slots;
    }

    @NotNull
    protected final PersistentMap.Builder<V, PersistentMap<Object, IData<V>>> getHeap() {
        return this.heap;
    }

    @NotNull
    public final PersistentSet.Builder<SootMethod> getCalledMethods() {
        return this.calledMethods;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void assignLocal(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull Object rhs) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        IHeapValues rhsValue = (IHeapValues) this.slots.get(rhs);
        if (rhsValue == null || rhsValue.isEmpty()) {
            IIFact.Companion.getLogger().debug(() -> {
                return assignLocal$lambda$0(r1, r2, r3);
            });
            IFact.Builder.DefaultImpls.assignNewExpr$default(this, env, lhs, getHf().empty(), false, 8, null);
        } else {
            IFact.Builder.DefaultImpls.assignNewExpr$default(this, env, lhs, rhsValue, false, 8, null);
        }
    }

    private static final Object assignLocal$lambda$0(HeapValuesEnv $env, Object $rhs, IHeapValues $rhsValue) {
        return $env + " assignLocal rhs: " + $rhs + " is " + $rhsValue;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void assignNewExpr(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull IHeapValues<V> iHeapValues, boolean append) {
        IHeapValues iHeapValuesPlus;
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
        if (append) {
            IHeapValues<V> iHeapValuesEmpty = (IHeapValues) this.slots.get(lhs);
            if (iHeapValuesEmpty == null) {
                iHeapValuesEmpty = getHf().empty();
            }
            iHeapValuesPlus = iHeapValues.plus(iHeapValuesEmpty);
        } else {
            iHeapValuesPlus = iHeapValues;
        }
        IHeapValues values = iHeapValuesPlus;
        this.slots.put(lhs, getHf().push(env, values).assignLocal(lhs, values).pop());
        if (values.isEmpty()) {
            IIFact.Companion.getLogger().debug(() -> {
                return assignNewExpr$lambda$1(r1);
            });
        }
    }

    private static final Object assignNewExpr$lambda$1(HeapValuesEnv $env) {
        return $env + " allocSite is empty";
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void kill(@NotNull Object slot) {
        Intrinsics.checkNotNullParameter(slot, "slot");
        this.slots.remove(slot);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0091  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final <K> cn.sast.dataflow.interprocedural.analysis.IHeapValues<V> getHeapKVData(@org.jetbrains.annotations.NotNull cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv r11, @org.jetbrains.annotations.NotNull java.lang.Object r12, @org.jetbrains.annotations.NotNull kotlinx.collections.immutable.PersistentMap<V, ? extends kotlinx.collections.immutable.PersistentMap<java.lang.Object, ? extends cn.sast.dataflow.interprocedural.analysis.IData<V>>> r13, @org.jetbrains.annotations.NotNull java.lang.Object r14, @org.jetbrains.annotations.Nullable K r15, boolean r16, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super cn.sast.dataflow.interprocedural.analysis.CompanionV<V>, ? extends cn.sast.dataflow.interprocedural.analysis.IHeapKVData<K, V>> r17) {
        /*
            Method dump skipped, instructions count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.dataflow.interprocedural.analysis.PointsToGraphBuilderAbstract.getHeapKVData(cn.sast.dataflow.interprocedural.analysis.HeapValuesEnv, java.lang.Object, kotlinx.collections.immutable.PersistentMap, java.lang.Object, java.lang.Object, boolean, kotlin.jvm.functions.Function1):cn.sast.dataflow.interprocedural.analysis.IHeapValues");
    }

    private static final Object getHeapKVData$lambda$4$lambda$2(Object $mt, CompanionV $o, Object $key) {
        return "get modelT: " + $mt + " map: " + $o + " [" + $key + "] is not exist";
    }

    private final /* synthetic */ <DT> boolean getHeapData(Object mt, Object rhs, Function2<? super CompanionV<V>, ? super DT, Unit> function2) {
        IHeapValues rhsPointers = (IHeapValues) this.slots.get(rhs);
        if (rhsPointers == null) {
            return false;
        }
        for (CompanionV e$iv : rhsPointers) {
            CompanionV o = e$iv;
            PersistentMap heapData = (PersistentMap) this.heap.get(o.getValue());
            Object obj = heapData != null ? (IData) heapData.get(mt) : null;
            Intrinsics.reifiedOperationMarker(1, "DT?");
            Object data = obj;
            function2.invoke(o, data);
        }
        return true;
    }

    public final <K> boolean setHeapKVData(@NotNull HeapValuesEnv env, @NotNull Object mt, @NotNull PersistentMap<V, ? extends PersistentMap<Object, ? extends IData<V>>> persistentMap, @NotNull CompanionV<V> companionV, @Nullable K k, @Nullable IHeapValues<V> iHeapValues, boolean append, @NotNull Function1<? super CompanionV<V>, ? extends IHeapKVData<K, V>> function1) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(mt, "mt");
        Intrinsics.checkNotNullParameter(persistentMap, "oldHeap");
        Intrinsics.checkNotNullParameter(companionV, "lhs");
        Intrinsics.checkNotNullParameter(function1, "emptyIData");
        PersistentMap persistentMapPersistentHashMapOf = (PersistentMap) persistentMap.get(companionV.getValue());
        if (persistentMapPersistentHashMapOf == null) {
            persistentMapPersistentHashMapOf = ExtensionsKt.persistentHashMapOf();
        }
        PersistentMap oldData = persistentMapPersistentHashMapOf;
        IHeapKVData iHeapKVData = (IData) oldData.get(mt);
        if (iHeapKVData == null) {
            IHeapKVData iHeapKVData2 = (IHeapKVData) function1.invoke(companionV);
            if (iHeapKVData2 == null) {
                return false;
            }
            iHeapKVData = iHeapKVData2;
        }
        IData oldKV = (IHeapKVData) iHeapKVData;
        IHeapKVData.Builder<K, V> bdr = oldKV.builder2();
        if (iHeapValues == null) {
            bdr.set(getHf(), env, k, iHeapValues, append);
        } else {
            IHeapValues<V> update2 = getHf().push(env, (IHeapValues) iHeapValues).setKVValue(mt, companionV, k).pop();
            bdr.set(getHf(), env, k, update2, append);
        }
        IData newEdges = bdr.build2();
        if (newEdges != oldKV) {
            this.heap.put(companionV.getValue(), oldData.put(mt, newEdges));
            return true;
        }
        return true;
    }

    public final void assignField(@NotNull HeapValuesEnv env, @NotNull IHeapValues<V> iHeapValues, @NotNull JFieldType field, @Nullable IHeapValues<V> iHeapValues2, boolean append) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(iHeapValues, "lhsPointees");
        Intrinsics.checkNotNullParameter(field, "field");
        PersistentMap oldHeap = this.heap.build();
        boolean finalAppend = append || !iHeapValues.isSingle();
        for (CompanionV e$iv : iHeapValues) {
            setHeapKVData(env, BuiltInModelT.Field, oldHeap, e$iv, field, iHeapValues2, finalAppend, emptyFieldFx());
        }
    }

    public final void assignField(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull JFieldType field, @Nullable IHeapValues<V> iHeapValues, boolean append) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(field, "field");
        IHeapValues lhsPointees = (IHeapValues) this.slots.get(lhs);
        if (lhsPointees == null) {
            return;
        }
        assignField(env, lhsPointees, field, (IHeapValues) iHeapValues, append);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void setField(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull JFieldType field, @NotNull Object rhs, boolean append) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        IHeapValues rhsPointees = (IHeapValues) this.slots.get(rhs);
        assignField(env, lhs, field, rhsPointees, append);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void setFieldNew(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull JFieldType field, @NotNull IHeapValues<V> iHeapValues) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
        assignField(env, lhs, field, (IHeapValues) iHeapValues, false);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void summarizeTargetFields(@NotNull Object lhs) {
        Intrinsics.checkNotNullParameter(lhs, "lhs");
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void getField(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull Object rhs, @NotNull JFieldType field, boolean newSummaryField) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        Intrinsics.checkNotNullParameter(field, "field");
        PersistentMap oldHeap = this.heap.build();
        IHeapValues res = getHeapKVData(env, BuiltInModelT.Field, oldHeap, rhs, field, newSummaryField, emptyFieldFx());
        if (res != null) {
            IFact.Builder.DefaultImpls.assignNewExpr$default(this, env, lhs, res, false, 8, null);
        } else {
            IFact.Builder.DefaultImpls.assignNewExpr$default(this, env, lhs, getHf().empty(), false, 8, null);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public String toString() {
        StringBuffer sb = new StringBuffer();
        CallStackContext currCtx = getCallStack();
        sb.append("call stack: " + (currCtx != null ? Integer.valueOf(currCtx.getDeep()) : null) + "\n");
        while (currCtx != null) {
            sb.append(currCtx);
            currCtx = currCtx.getCaller();
        }
        sb.append("\nslot:\n");
        for (Object slot : this.slots.keySet()) {
            sb.append(slot).append(" -> ").append(String.valueOf(this.slots.get(slot)));
            sb.append("\n");
        }
        sb.append("\nheap:\n");
        for (Map.Entry entry : this.heap.entrySet()) {
            Object source = entry.getKey();
            Map map = (PersistentMap) entry.getValue();
            sb.append(Printer.Companion.node2String(source)).append(":\n");
            for (Map.Entry entry2 : map.entrySet()) {
                Object K = entry2.getKey();
                IData values = (IData) entry2.getValue();
                sb.append("\t").append(K).append(": ").append(values).append("\n");
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void union(@NotNull IFact<V> iFact) {
        Intrinsics.checkNotNullParameter(iFact, "that");
        if (!(iFact instanceof PointsToGraphAbstract)) {
            throw new IllegalArgumentException("union error of fact type: " + iFact.getClass() + " \n" + iFact);
        }
        for (Map.Entry entry : ((PointsToGraphAbstract) iFact).getSlots().entrySet()) {
            Object k = entry.getKey();
            IHeapValues<V> v = (IHeapValues) entry.getValue();
            IHeapValues iHeapValuesEmpty = (IHeapValues) this.slots.get(k);
            if (iHeapValuesEmpty == null) {
                iHeapValuesEmpty = getHf().empty();
            }
            IHeapValues targets = iHeapValuesEmpty;
            this.slots.put(k, targets.plus(v));
        }
        for (Map.Entry entry2 : ((PointsToGraphAbstract) iFact).getHeap().entrySet()) {
            Object thatSource = entry2.getKey();
            for (Map.Entry entry3 : ((PersistentMap) entry2.getValue()).entrySet()) {
                Object kind = entry3.getKey();
                IData<V> valuesR = (IData) entry3.getValue();
                PersistentMap persistentMapPersistentHashMapOf = (PersistentMap) this.heap.get(thatSource);
                if (persistentMapPersistentHashMapOf == null) {
                    persistentMapPersistentHashMapOf = ExtensionsKt.persistentHashMapOf();
                }
                PersistentMap pack = persistentMapPersistentHashMapOf;
                IData<V> valuesL = (IData) pack.get(kind);
                if (valuesL == null) {
                    this.heap.put(thatSource, pack.put(kind, valuesR));
                } else if (valuesL != valuesR) {
                    IData.Builder bdr = valuesL.builder2();
                    bdr.union(getHf(), valuesR);
                    IData unionData = bdr.build2();
                    this.heap.put(thatSource, pack.put(kind, unionData));
                }
            }
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void gc() {
        LinkedList workList = new LinkedList();
        for (IHeapValues nodes : this.slots.values()) {
            nodes.reference(workList);
        }
        HashSet reference = new HashSet();
        PersistentMap heapOld = this.heap.build();
        while (!workList.isEmpty()) {
            Object v = workList.remove();
            if (!reference.contains(v)) {
                reference.add(v);
                Map map = (PersistentMap) heapOld.get(v);
                if (map != null) {
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        IData next = (IData) ((Map.Entry) it.next()).getValue();
                        next.reference(workList);
                    }
                }
            }
        }
        for (Object k : heapOld.keySet()) {
            if (!reference.contains(k)) {
                this.heap.remove(k);
            }
        }
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    /* renamed from: getSlots */
    public Set<Object> mo173getSlots() {
        return new HashSet(this.slots.keySet());
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void addCalledMethod(@NotNull SootMethod sm) {
        Intrinsics.checkNotNullParameter(sm, "sm");
        this.calledMethods.add(sm);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @NotNull
    /* renamed from: getCalledMethods */
    public ImmutableSet<SootMethod> mo174getCalledMethods() {
        return this.calledMethods.build();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IHeapValues<V> getTargetsUnsafe(@NotNull Object slot) {
        Intrinsics.checkNotNullParameter(slot, "slot");
        if (!(slot instanceof String) && !(slot instanceof Local) && !(slot instanceof Number)) {
            IIFact.Companion.getLogger().error(() -> {
                return getTargetsUnsafe$lambda$7(r1);
            });
        }
        return (IHeapValues) this.slots.get(slot);
    }

    private static final Object getTargetsUnsafe$lambda$7(Object $slot) {
        return "error slot value: " + $slot;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isBottom() {
        return false;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    public boolean isTop() {
        return false;
    }

    @NotNull
    public final Function1<CompanionV<V>, ArrayHeapKV<V>> emptyArrayFx(@NotNull HeapValuesEnv env) {
        Intrinsics.checkNotNullParameter(env, "env");
        return (v2) -> {
            return emptyArrayFx$lambda$8(r0, r1, v2);
        };
    }

    private static final ArrayHeapKV emptyArrayFx$lambda$8(PointsToGraphBuilderAbstract this$0, HeapValuesEnv $env, CompanionV lhsV) {
        Intrinsics.checkNotNullParameter(lhsV, "lhsV");
        Type type = this$0.getType(lhsV);
        ArrayType arrayTy = type instanceof ArrayType ? (ArrayType) type : null;
        IHeapValues allocSite = this$0.getHf().single(lhsV);
        if (arrayTy != null) {
            return this$0.getEmptyArraySpace($env, allocSite, arrayTy, null);
        }
        return null;
    }

    @NotNull
    public final Function1<CompanionV<V>, FieldHeapKV<V>> emptyFieldFx() {
        return (v1) -> {
            return emptyFieldFx$lambda$9(r0, v1);
        };
    }

    private static final FieldHeapKV emptyFieldFx$lambda$9(PointsToGraphBuilderAbstract this$0, CompanionV lhsV) {
        Intrinsics.checkNotNullParameter(lhsV, "lhsV");
        Type type = this$0.getType(lhsV);
        RefType refTy = type instanceof RefType ? (RefType) type : null;
        if (refTy != null) {
            return this$0.getEmptyFieldSpace(refTy);
        }
        return null;
    }

    public final void setArray(@NotNull HeapValuesEnv env, @NotNull Object lhs, @Nullable Value index, @Nullable IHeapValues<V> iHeapValues, boolean append) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        IHeapValues lhsPointees = (IHeapValues) this.slots.get(lhs);
        if (lhsPointees == null) {
            return;
        }
        PersistentMap oldHeap = this.heap.build();
        Set allIndex = getAllIndex(index);
        boolean finalAppend = append || !lhsPointees.isSingle();
        for (CompanionV e$iv : lhsPointees) {
            if (allIndex == null || allIndex.isEmpty()) {
                setHeapKVData(env, BuiltInModelT.Array, oldHeap, e$iv, null, iHeapValues, true, emptyArrayFx(env));
            } else if (allIndex.size() == 1) {
                setHeapKVData(env, BuiltInModelT.Array, oldHeap, e$iv, CollectionsKt.first(allIndex), iHeapValues, finalAppend, emptyArrayFx(env));
            } else {
                Iterator<Integer> it = allIndex.iterator();
                while (it.hasNext()) {
                    int idx = it.next().intValue();
                    setHeapKVData(env, BuiltInModelT.Array, oldHeap, e$iv, Integer.valueOf(idx), iHeapValues, true, emptyArrayFx(env));
                }
            }
        }
        Unit unit = Unit.INSTANCE;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void setArraySootValue(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull Value index, @NotNull Value rhs, @NotNull Type valueType) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(index, "index");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        IHeapValues rhsPointers = getOfSootValue(env, rhs, valueType);
        setArray(env, lhs, index, rhsPointers, false);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void setArrayValueNew(@NotNull HeapValuesEnv env, @NotNull Object lhs, @Nullable Value index, @NotNull IHeapValues<V> iHeapValues) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
        setArray(env, lhs, index, iHeapValues, false);
    }

    private final Set<Integer> getAllIndex(Value index) {
        IHeapValues v;
        if (index == null) {
            return null;
        }
        if (index instanceof IntConstant) {
            return SetsKt.mutableSetOf(new Integer[]{Integer.valueOf(((IntConstant) index).value)});
        }
        if (!(index instanceof Local) || (v = (IHeapValues) this.slots.get(index)) == null) {
            return null;
        }
        return v.getAllIntValue(true);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public boolean getArrayValue(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull Object rhs, @Nullable Value index) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        IHeapValues.Builder rhsValues = getHf().empty().builder();
        Set allIndex = index == null ? null : getAllIndex(index);
        PersistentMap oldHeap = this.heap.build();
        if (allIndex == null || allIndex.isEmpty()) {
            IHeapValues it = getHeapKVData(env, BuiltInModelT.Array, oldHeap, rhs, null, false, emptyArrayFx(env));
            if (it != null) {
                rhsValues.add(it);
            }
        } else if (allIndex.size() == 1) {
            IHeapValues it2 = getHeapKVData(env, BuiltInModelT.Array, oldHeap, rhs, CollectionsKt.first(allIndex), false, emptyArrayFx(env));
            if (it2 != null) {
                rhsValues.add(it2);
            }
        } else {
            Iterator<Integer> it3 = allIndex.iterator();
            while (it3.hasNext()) {
                int eachIdx = it3.next().intValue();
                IHeapValues it4 = getHeapKVData(env, BuiltInModelT.Array, oldHeap, rhs, Integer.valueOf(eachIdx), false, emptyArrayFx(env));
                if (it4 != null) {
                    rhsValues.add(it4);
                }
            }
        }
        IFact.Builder.DefaultImpls.assignNewExpr$default(this, env, lhs, rhsValues.build(), false, 8, null);
        return rhsValues.isNotEmpty();
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public boolean getArrayValue(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull Value rhs, @Nullable Value index) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        if (rhs instanceof Constant) {
            return false;
        }
        if (rhs instanceof Local) {
            return getArrayValue(env, lhs, (Object) rhs, index);
        }
        Function0 function0 = () -> {
            return getArrayValue$lambda$14(r2);
        };
        throw new IllegalStateException(function0.toString());
    }

    private static final String getArrayValue$lambda$14(Value $rhs) {
        return "error soot.Value: " + $rhs;
    }

    private final void setIData(CompanionV<V> companionV, IData<V> iData) {
        PersistentMap persistentMapPersistentHashMapOf = (PersistentMap) this.heap.get(companionV.getValue());
        if (persistentMapPersistentHashMapOf == null) {
            persistentMapPersistentHashMapOf = ExtensionsKt.persistentHashMapOf();
        }
        PersistentMap oldData = persistentMapPersistentHashMapOf;
        this.heap.put(companionV.getValue(), oldData.put(BuiltInModelT.Field, iData));
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void assignFieldSootValue(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull JFieldType field, @NotNull Value rhs, @NotNull Type valueType, boolean append) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        IHeapValues value = getOfSootValue(env, rhs, valueType);
        assignField(env, lhs, field, value, append);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void assignLocalSootValue(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull Value rhs, @NotNull Type valueType) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(rhs, "rhs");
        Intrinsics.checkNotNullParameter(valueType, "valueType");
        IHeapValues value = getOfSootValue(env, rhs, valueType);
        IFact.Builder.DefaultImpls.assignNewExpr$default(this, env, lhs, value, false, 8, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void assignNewArray(@NotNull HeapValuesEnv env, @NotNull Object lhs, @NotNull IHeapValues<V> iHeapValues, @NotNull ArrayType type, @NotNull Value size) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(lhs, "lhs");
        Intrinsics.checkNotNullParameter(iHeapValues, "allocSite");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(size, "size");
        Type typeSoot_IntType = G.v().soot_IntType();
        Intrinsics.checkNotNullExpressionValue(typeSoot_IntType, "soot_IntType(...)");
        IHeapValues arrayLength = getOfSootValue(env, size, typeSoot_IntType);
        ArrayHeapKV res = getEmptyArraySpace(env, iHeapValues, type, arrayLength);
        for (CompanionV e$iv : iHeapValues) {
            PersistentMap data = ExtensionsKt.persistentHashMapOf();
            this.heap.put(e$iv.getValue(), data.put(BuiltInModelT.Array, res));
        }
        IFact.Builder.DefaultImpls.assignNewExpr$default(this, env, lhs, iHeapValues, false, 8, null);
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IIFact
    @Nullable
    public IData<V> getValueData(@NotNull V v, @NotNull Object mt) {
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(mt, "mt");
        PersistentMap persistentMap = (PersistentMap) this.heap.get(v);
        if (persistentMap != null) {
            return (IData) persistentMap.get(mt);
        }
        return null;
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void setValueData(@NotNull HeapValuesEnv env, @NotNull V v, @NotNull Object mt, @Nullable IData<V> iData) {
        Intrinsics.checkNotNullParameter(env, "env");
        Intrinsics.checkNotNullParameter(v, "v");
        Intrinsics.checkNotNullParameter(mt, "mt");
        if (iData == null) {
            PersistentMap persistentMap = (PersistentMap) this.heap.get(v);
            PersistentMap persistentMapRemove = persistentMap != null ? persistentMap.remove(mt) : null;
            if (persistentMapRemove != null) {
                this.heap.put(v, persistentMapRemove);
                return;
            }
            return;
        }
        PersistentMap persistentMapPersistentHashMapOf = (PersistentMap) this.heap.get(v);
        if (persistentMapPersistentHashMapOf == null) {
            persistentMapPersistentHashMapOf = ExtensionsKt.persistentHashMapOf();
        }
        PersistentMap map = persistentMapPersistentHashMapOf;
        IData newData = getHf().getPathFactory().setModelData(env, v, mt, iData);
        this.heap.put(v, map.put(mt, newData));
    }

    @Override // cn.sast.dataflow.interprocedural.analysis.IFact.Builder
    public void copyValueData(@NotNull V v, @NotNull V v2) {
        Intrinsics.checkNotNullParameter(v, "from");
        Intrinsics.checkNotNullParameter(v2, "to");
        Map map = this.heap;
        PersistentMap persistentMap = (PersistentMap) this.heap.get(v);
        if (persistentMap == null) {
            return;
        }
        map.put(v2, persistentMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void apply(@NotNull IReNew<V> iReNew) {
        Intrinsics.checkNotNullParameter(iReNew, "re");
        for (Map.Entry entry : this.heap.build().entrySet()) {
            Object v = entry.getKey();
            Map map = (PersistentMap) entry.getValue();
            Map mapBuilder = map.builder();
            for (Map.Entry entry2 : map.entrySet()) {
                Object mt = entry2.getKey();
                IData data = (IData) entry2.getValue();
                IData iDataCloneAndReNewObjects = data.cloneAndReNewObjects(iReNew.context(new ReferenceContext.PTG(v, mt)));
                if (iDataCloneAndReNewObjects != data) {
                    mapBuilder.put(mt, iDataCloneAndReNewObjects);
                }
            }
            Object rpVal = iReNew.checkNeedReplace((IReNew<V>) v);
            if (rpVal == null) {
                this.heap.put(v, mapBuilder.build());
            } else if (!Intrinsics.areEqual(v, rpVal)) {
                this.heap.put(rpVal, mapBuilder.build());
                this.heap.remove(v);
            }
        }
        for (Map.Entry entry3 : this.slots.build().entrySet()) {
            Object l = entry3.getKey();
            IHeapValues v2 = (IHeapValues) entry3.getValue();
            IHeapValues.Builder<V> vb = v2.builder();
            vb.cloneAndReNewObjects(iReNew.context(new ReferenceContext.Slot(l)));
            IHeapValues rpVal2 = vb.build();
            if (rpVal2 != v2) {
                this.slots.put(l, rpVal2);
            }
        }
    }
}
