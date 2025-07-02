package cn.sast.api.util;

import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.ResourceKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.cache.AnalysisCache;
import com.feysh.corax.cache.analysis.SootRangeKey;
import com.feysh.corax.config.api.utils.UtilsKt;
import com.google.common.base.Optional;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.reflect.KCallable;
import kotlin.reflect.KClass;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.KCallables;
import kotlin.reflect.jvm.KClassesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Body;
import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.G;
import soot.IntType;
import soot.IntegerType;
import soot.Local;
import soot.LongType;
import soot.Printer;
import soot.RefLikeType;
import soot.Scene;
import soot.ShortType;
import soot.SootClass;
import soot.SootMethod;
import soot.SootMethodRef;
import soot.SourceLocator;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.asm.AsmUtil;
import soot.jimple.AddExpr;
import soot.jimple.AndExpr;
import soot.jimple.ArithmeticConstant;
import soot.jimple.ClassConstant;
import soot.jimple.CmpExpr;
import soot.jimple.CmpgExpr;
import soot.jimple.CmplExpr;
import soot.jimple.Constant;
import soot.jimple.DefinitionStmt;
import soot.jimple.DivExpr;
import soot.jimple.DoubleConstant;
import soot.jimple.EqExpr;
import soot.jimple.Expr;
import soot.jimple.FloatConstant;
import soot.jimple.GeExpr;
import soot.jimple.GtExpr;
import soot.jimple.InstanceInvokeExpr;
import soot.jimple.IntConstant;
import soot.jimple.InvokeExpr;
import soot.jimple.JasminClass;
import soot.jimple.LeExpr;
import soot.jimple.LongConstant;
import soot.jimple.LtExpr;
import soot.jimple.MulExpr;
import soot.jimple.NeExpr;
import soot.jimple.NullConstant;
import soot.jimple.NumericConstant;
import soot.jimple.OrExpr;
import soot.jimple.RealConstant;
import soot.jimple.RemExpr;
import soot.jimple.ShlExpr;
import soot.jimple.ShrExpr;
import soot.jimple.SpecialInvokeExpr;
import soot.jimple.Stmt;
import soot.jimple.StringConstant;
import soot.jimple.SubExpr;
import soot.jimple.UshrExpr;
import soot.jimple.XorExpr;
import soot.jimple.toolkits.callgraph.VirtualCalls;
import soot.tagkit.SourceFileTag;
import soot.util.JasminOutputStream;
import soot.util.queue.ChunkedQueue;

/* compiled from: SootUtils.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��Ü\u0001\n��\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\t\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a&\u0010��\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0002\u0018\u00010\u0001\"\u0004\b��\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0002\u001a\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\n\u001a\u001e\u0010$\u001a\u00020%\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010&\u001a\u00020'\u001a\u001a\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b022\u0006\u00103\u001a\u00020)\u001a\u001a\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b022\u0006\u00104\u001a\u00020\u000b\u001a\f\u00105\u001a\u0004\u0018\u00010\u000b*\u00020)\u001a\u0014\u00106\u001a\u0004\u0018\u000107*\u0002082\u0006\u00109\u001a\u00020\u0014\u001a\u001c\u0010:\u001a\u0004\u0018\u000108*\u0002072\u0006\u0010;\u001a\u0002072\u0006\u0010<\u001a\u00020'\u001a \u0010=\u001a\u0004\u0018\u0001082\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u0002072\u0006\u0010A\u001a\u000207\u001a+\u0010M\u001a\u0004\u0018\u000108\"\b\b��\u0010N*\u00020\u0014*\u00020O2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u0002HN¢\u0006\u0002\u0010S\u001a+\u0010M\u001a\u0004\u0018\u000108\"\b\b��\u0010N*\u00020\u0014*\u00020\u000b2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u0002HN¢\u0006\u0002\u0010T\u001a\u001e\u0010U\u001a\u00020\u0014*\u0002072\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u00140WH\u0086\bø\u0001��\u001a\u0016\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020\u000b2\u0006\u0010[\u001a\u00020)\u001a\u0016\u0010\\\u001a\u00020]2\u0006\u0010[\u001a\u00020)2\u0006\u0010^\u001a\u00020_\u001a\u001a\u0010`\u001a\u000e\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u0014022\u0006\u0010a\u001a\u00020\u0002\u001a2\u0010b\u001a\b\u0012\u0004\u0012\u00020\u001c0c2\u0006\u0010R\u001a\u00020\u00142\n\b\u0002\u0010d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010e\u001a\u00020C2\b\b\u0002\u0010f\u001a\u00020'\u001a\u0012\u0010g\u001a\u00020Y*\u00020)2\u0006\u0010h\u001a\u00020Q\u001a\f\u0010i\u001a\u0004\u0018\u00010)*\u00020)\u001a\u0016\u0010j\u001a\b\u0012\u0004\u0012\u00020)0\n2\u0006\u0010k\u001a\u00020)H\u0002\u001a\u0018\u0010l\u001a\b\u0012\u0004\u0012\u00020\u001c0m*\u00020)2\u0006\u0010\u0011\u001a\u00020\u000b\u001a7\u0010l\u001a\u0004\u0018\u00010\u001c*\u00020)2\u0006\u0010\u0011\u001a\u00020\u000b2!\u0010n\u001a\u001d\u0012\u0013\u0012\u00110\u001c¢\u0006\f\bp\u0012\b\bq\u0012\u0004\b\b(r\u0012\u0004\u0012\u00020'0o\"%\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"'\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\f\u0010\r\"!\u0010\u000e\u001a\u00020\u000b\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"!\u0010\u0011\u001a\u00020\u000b\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010\"!\u0010\u0013\u001a\u00020\u0014\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\"!\u0010\u0017\u001a\u00020\u000b\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0010\"!\u0010\u0019\u001a\u00020\u000b\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0010\"#\u0010\u001b\u001a\u0004\u0018\u00010\u001c\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\"!\u0010\u001f\u001a\u00020\u001c\"\u0004\b��\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00058F¢\u0006\u0006\u001a\u0004\b \u0010\u001e\"\u0019\u0010(\u001a\u00020)*\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b*\u0010+\"\u0019\u0010,\u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b-\u0010.\"\u001b\u0010/\u001a\u0004\u0018\u00010)*\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b0\u0010+\"\u0018\u0010B\u001a\u0004\u0018\u00010C*\u00020D8Æ\u0002¢\u0006\u0006\u001a\u0004\bE\u0010F\"\u0018\u0010B\u001a\u0004\u0018\u00010C*\u00020G8Æ\u0002¢\u0006\u0006\u001a\u0004\bE\u0010H\"\u0018\u0010I\u001a\u0004\u0018\u00010J*\u00020D8Æ\u0002¢\u0006\u0006\u001a\u0004\bK\u0010L\"\u0015\u0010s\u001a\u00020Q*\u00020)8F¢\u0006\u0006\u001a\u0004\bt\u0010u\"\u0017\u0010v\u001a\u0004\u0018\u00010\u000b*\u00020)8F¢\u0006\u0006\u001a\u0004\bw\u0010x\"%\u0010y\u001a\u0012\u0012\u0004\u0012\u00020\u000b0zj\b\u0012\u0004\u0012\u00020\u000b`{*\u00020)8F¢\u0006\u0006\u001a\u0004\b|\u0010}\"\u0019\u0010~\u001a\u0004\u0018\u00010\u007f*\u00020\u001c8F¢\u0006\b\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0082\u0001"}, d2 = {"tryConstructor", "Lkotlin/reflect/KClass;", "", "R", "function", "Lkotlin/reflect/KCallable;", "KClass", "getKClass", "(Lkotlin/reflect/KCallable;)Lkotlin/reflect/KClass;", "paramStringList", "", "", "getParamStringList", "(Lkotlin/reflect/KCallable;)Ljava/util/List;", "paramSignature", "getParamSignature", "(Lkotlin/reflect/KCallable;)Ljava/lang/String;", "subSignature", "getSubSignature", "returnSootType", "Lsoot/Type;", "getReturnSootType", "(Lkotlin/reflect/KCallable;)Lsoot/Type;", "sootClassName", "getSootClassName", "sootSignature", "getSootSignature", "grabSootMethod", "Lsoot/SootMethod;", "getGrabSootMethod", "(Lkotlin/reflect/KCallable;)Lsoot/SootMethod;", "sootMethod", "getSootMethod", "convertParameterTypes", "paramTypes", "", "sootMethodRef", "Lsoot/SootMethodRef;", "isStatic", "", "sootClass", "Lsoot/SootClass;", "getSootClass", "(Lkotlin/reflect/KClass;)Lsoot/SootClass;", "className", "getClassName", "(Lkotlin/reflect/KClass;)Ljava/lang/String;", "sootClassUnsafe", "getSootClassUnsafe", "classSplit", "Lkotlin/Pair;", "cp", "cname", "getSourcePathFromAnnotation", "castTo", "Lsoot/jimple/Constant;", "Lsoot/jimple/NumericConstant;", "toType", "equalEqual", "b", "isEq", "evalConstantBinop", "expr", "Lsoot/jimple/Expr;", "c1", "c2", "invokeExprOrNull", "Lsoot/jimple/InvokeExpr;", "Lsoot/Unit;", "getInvokeExprOrNull", "(Lsoot/Unit;)Lsoot/jimple/InvokeExpr;", "Lsoot/jimple/Stmt;", "(Lsoot/jimple/Stmt;)Lsoot/jimple/InvokeExpr;", "leftOp", "Lsoot/Value;", "getLeftOp", "(Lsoot/Unit;)Lsoot/Value;", "cvtNumericConstant", "ToType", "Lsoot/jimple/StringConstant;", "radix", "", "type", "(Lsoot/jimple/StringConstant;ILsoot/Type;)Lsoot/jimple/NumericConstant;", "(Ljava/lang/String;ILsoot/Type;)Lsoot/jimple/NumericConstant;", "accurateType", "declareType", "Lkotlin/Function0;", "printToSootClass", "", "dir", "sClass", "sootClass2JasminClass", "Lcn/sast/common/IResFile;", "out", "Lcn/sast/common/IResDirectory;", "constOf", "v", "getCallTargets", "", "container", "ie", "appOnly", "adjustLevel", "level", "superClassOrNull", "findAncestors", "sc", "findMethodOrNull", "Lkotlin/sequences/Sequence;", "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "sm", "numCode", "getNumCode", "(Lsoot/SootClass;)I", "sourcePath", "getSourcePath", "(Lsoot/SootClass;)Ljava/lang/String;", "possibleSourceFiles", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "getPossibleSourceFiles", "(Lsoot/SootClass;)Ljava/util/LinkedHashSet;", "activeBodyOrNull", "Lsoot/Body;", "getActiveBodyOrNull", "(Lsoot/SootMethod;)Lsoot/Body;", "corax-api"})
@SourceDebugExtension({"SMAP\nSootUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SootUtils.kt\ncn/sast/api/util/SootUtilsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Sequences.kt\nkotlin/sequences/SequencesKt___SequencesKt\n*L\n1#1,512:1\n1557#2:513\n1628#2,3:514\n1557#2:517\n1628#2,3:518\n1368#2:522\n1454#2,5:523\n1368#2:528\n1454#2,5:529\n1454#2,2:536\n1557#2:538\n1628#2,3:539\n1456#2,3:542\n1368#2:545\n1454#2,5:546\n1#3:521\n183#4,2:534\n*S KotlinDebug\n*F\n+ 1 SootUtils.kt\ncn/sast/api/util/SootUtilsKt\n*L\n52#1:513\n52#1:514,3\n55#1:517\n55#1:518,3\n441#1:522\n441#1:523,5\n442#1:528\n442#1:529,5\n506#1:536,2\n507#1:538\n507#1:539,3\n506#1:542,3\n455#1:545\n455#1:546,5\n474#1:534,2\n*E\n"})
/* loaded from: SootUtilsKt.class */
public final class SootUtilsKt {
    /* JADX WARN: Removed duplicated region for block: B:11:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final <R> kotlin.reflect.KClass<? extends java.lang.Object> tryConstructor(kotlin.reflect.KCallable<? extends R> r3) {
        /*
            r0 = r3
            boolean r0 = r0 instanceof kotlin.reflect.KFunction
            if (r0 == 0) goto Le
            r0 = r3
            kotlin.reflect.KFunction r0 = (kotlin.reflect.KFunction) r0
            goto Lf
        Le:
            r0 = 0
        Lf:
            r1 = r0
            if (r1 == 0) goto L20
            java.lang.reflect.Constructor r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaConstructor(r0)
            r1 = r0
            if (r1 == 0) goto L20
            java.lang.Class r0 = r0.getDeclaringClass()
            goto L22
        L20:
            r0 = 0
        L22:
            r4 = r0
            r0 = r4
            r1 = r0
            if (r1 == 0) goto L2e
            kotlin.reflect.KClass r0 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r0)
            goto L30
        L2e:
            r0 = 0
        L30:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.util.SootUtilsKt.tryConstructor(kotlin.reflect.KCallable):kotlin.reflect.KClass");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <R> kotlin.reflect.KClass<?> getKClass(@org.jetbrains.annotations.NotNull kotlin.reflect.KCallable<? extends R> r4) {
        /*
            r0 = r4
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r0 = r4
            boolean r0 = r0 instanceof kotlin.jvm.internal.CallableReference
            if (r0 == 0) goto L27
            r0 = r4
            kotlin.jvm.internal.CallableReference r0 = (kotlin.jvm.internal.CallableReference) r0
            kotlin.reflect.KDeclarationContainer r0 = r0.getOwner()
            r5 = r0
            r0 = r5
            boolean r0 = r0 instanceof kotlin.reflect.KClass
            if (r0 == 0) goto L23
            r0 = r5
            kotlin.reflect.KClass r0 = (kotlin.reflect.KClass) r0
            goto L52
        L23:
            r0 = 0
            goto L52
        L27:
            r0 = r4
            kotlin.reflect.KParameter r0 = kotlin.reflect.full.KCallables.getInstanceParameter(r0)
            r1 = r0
            if (r1 == 0) goto L40
            kotlin.reflect.KType r0 = r0.getType()
            r1 = r0
            if (r1 == 0) goto L40
            kotlin.reflect.KClassifier r0 = r0.getClassifier()
            goto L42
        L40:
            r0 = 0
        L42:
            r5 = r0
            r0 = r5
            boolean r0 = r0 instanceof kotlin.reflect.KClass
            if (r0 == 0) goto L51
            r0 = r5
            kotlin.reflect.KClass r0 = (kotlin.reflect.KClass) r0
            goto L52
        L51:
            r0 = 0
        L52:
            r1 = r0
            if (r1 != 0) goto L71
        L57:
            r0 = r4
            kotlin.reflect.KClass r0 = tryConstructor(r0)
            r1 = r0
            if (r1 != 0) goto L71
        L60:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r4
            java.lang.String r2 = "Can't get parent class for " + r2
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L71:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.util.SootUtilsKt.getKClass(kotlin.reflect.KCallable):kotlin.reflect.KClass");
    }

    @NotNull
    public static final <R> List<String> getParamStringList(@NotNull KCallable<? extends R> kCallable) {
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        if (kCallable instanceof CallableReference) {
            String signature = ((CallableReference) kCallable).getSignature();
            Intrinsics.checkNotNullExpressionValue(signature, "getSignature(...)");
            List sigTypes = AsmUtil.toJimpleDesc(StringsKt.substringAfter$default(signature, "(", (String) null, 2, (Object) null), Optional.fromNullable((Object) null));
            sigTypes.remove(sigTypes.size() - 1);
            Intrinsics.checkNotNull(sigTypes);
            List $this$map$iv = sigTypes;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Type it = (Type) item$iv$iv;
                Intrinsics.checkNotNull(it);
                String typename = UtilsKt.getTypename(it);
                Intrinsics.checkNotNull(typename);
                destination$iv$iv.add(typename);
            }
            return (List) destination$iv$iv;
        }
        Iterable $this$map$iv2 = CollectionsKt.drop(kCallable.getParameters(), KCallables.getInstanceParameter(kCallable) != null ? 1 : 0);
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
        for (Object item$iv$iv2 : $this$map$iv2) {
            String typeName = ReflectJvmMapping.getJavaType(((KParameter) item$iv$iv2).getType()).getTypeName();
            Intrinsics.checkNotNullExpressionValue(typeName, "getTypeName(...)");
            destination$iv$iv2.add(StringsKt.substringBefore$default(typeName, '<', (String) null, 2, (Object) null));
        }
        return (List) destination$iv$iv2;
    }

    @NotNull
    public static final <R> String getParamSignature(@NotNull KCallable<? extends R> kCallable) {
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        return CollectionsKt.joinToString$default(getParamStringList(kCallable), ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    @NotNull
    public static final <R> String getSubSignature(@NotNull KCallable<? extends R> kCallable) {
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        if (kCallable instanceof CallableReference) {
            String signature = ((CallableReference) kCallable).getSignature();
            Intrinsics.checkNotNullExpressionValue(signature, "getSignature(...)");
            List sigTypes = AsmUtil.toJimpleDesc(StringsKt.substringAfter$default(signature, "(", (String) null, 2, (Object) null), Optional.fromNullable((Object) null));
            Type returnType = (Type) sigTypes.remove(sigTypes.size() - 1);
            Intrinsics.checkNotNull(sigTypes);
            String signature2 = CollectionsKt.joinToString$default(sigTypes, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, SootUtilsKt::_get_subSignature_$lambda$2, 30, (Object) null);
            return returnType + " " + ((CallableReference) kCallable).getName() + "(" + signature2 + ")";
        }
        return ReflectJvmMapping.getJavaType(kCallable.getReturnType()).getTypeName() + " " + kCallable.getName() + "(" + getParamSignature(kCallable) + ")";
    }

    private static final CharSequence _get_subSignature_$lambda$2(Type it) {
        Intrinsics.checkNotNull(it);
        String typename = UtilsKt.getTypename(it);
        Intrinsics.checkNotNull(typename);
        return typename;
    }

    @NotNull
    public static final <R> Type getReturnSootType(@NotNull KCallable<? extends R> kCallable) {
        String typeName;
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        if (kCallable instanceof CallableReference) {
            String signature = ((CallableReference) kCallable).getSignature();
            Intrinsics.checkNotNullExpressionValue(signature, "getSignature(...)");
            List sigTypes = AsmUtil.toJimpleDesc(StringsKt.substringAfter$default(signature, "(", (String) null, 2, (Object) null), Optional.fromNullable((Object) null));
            typeName = ((Type) sigTypes.remove(sigTypes.size() - 1)).toString();
        } else {
            typeName = ReflectJvmMapping.getJavaType(kCallable.getReturnType()).getTypeName();
        }
        String ty = typeName;
        Type typeUnsafe = Scene.v().getTypeUnsafe(ty, true);
        Intrinsics.checkNotNull(typeUnsafe);
        return typeUnsafe;
    }

    @NotNull
    public static final <R> String getSootClassName(@NotNull KCallable<? extends R> kCallable) {
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        ClassBasedDeclarationContainer kClass = getKClass(kCallable);
        if (kClass instanceof ClassBasedDeclarationContainer) {
            String name = kClass.getJClass().getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            return name;
        }
        String qualifiedName = kClass.getQualifiedName();
        Intrinsics.checkNotNull(qualifiedName);
        return qualifiedName;
    }

    @NotNull
    public static final <R> String getSootSignature(@NotNull KCallable<? extends R> kCallable) {
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        return "<" + getSootClassName(kCallable) + ": " + getSubSignature(kCallable) + ">";
    }

    @Nullable
    public static final <R> SootMethod getGrabSootMethod(@NotNull KCallable<? extends R> kCallable) {
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        return Scene.v().grabMethod(getSootSignature(kCallable));
    }

    @NotNull
    public static final <R> SootMethod getSootMethod(@NotNull KCallable<? extends R> kCallable) {
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        SootMethod method = Scene.v().getMethod(getSootSignature(kCallable));
        Intrinsics.checkNotNullExpressionValue(method, "getMethod(...)");
        return method;
    }

    @NotNull
    public static final List<Type> convertParameterTypes(@NotNull List<? extends CharSequence> list) {
        Intrinsics.checkNotNullParameter(list, "paramTypes");
        List parameterTypes = new ArrayList();
        for (CharSequence type : list) {
            Type typeUnsafe = Scene.v().getTypeUnsafe(type.toString(), true);
            Intrinsics.checkNotNull(typeUnsafe);
            parameterTypes.add(typeUnsafe);
        }
        return parameterTypes;
    }

    @NotNull
    public static final <R> SootMethodRef sootMethodRef(@NotNull KCallable<? extends R> kCallable, boolean isStatic) {
        Intrinsics.checkNotNullParameter(kCallable, "<this>");
        SootClass sc = Scene.v().getSootClass(getSootClassName(kCallable));
        Intrinsics.checkNotNull(sc);
        SootMethodRef sootMethodRefMakeMethodRef = Scene.v().makeMethodRef(sc, kCallable.getName(), convertParameterTypes(getParamStringList(kCallable)), getReturnSootType(kCallable), isStatic);
        Intrinsics.checkNotNullExpressionValue(sootMethodRefMakeMethodRef, "makeMethodRef(...)");
        return sootMethodRefMakeMethodRef;
    }

    @NotNull
    public static final SootClass getSootClass(@NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        SootClass sootClass = Scene.v().getSootClass(KClassesJvm.getJvmName(kClass));
        Intrinsics.checkNotNullExpressionValue(sootClass, "getSootClass(...)");
        return sootClass;
    }

    @NotNull
    public static final String getClassName(@NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return KClassesJvm.getJvmName(kClass);
    }

    @Nullable
    public static final SootClass getSootClassUnsafe(@NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return Scene.v().getSootClassUnsafe(KClassesJvm.getJvmName(kClass), false);
    }

    @NotNull
    public static final Pair<String, String> classSplit(@NotNull SootClass cp) {
        Intrinsics.checkNotNullParameter(cp, "cp");
        String name = cp.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        return classSplit(name);
    }

    @NotNull
    public static final Pair<String, String> classSplit(@NotNull String cname) {
        Intrinsics.checkNotNullParameter(cname, "cname");
        String packageName = StringsKt.substringBeforeLast(cname, ".", "");
        String className = StringsKt.substringAfterLast$default(cname, ".", (String) null, 2, (Object) null);
        return TuplesKt.to(packageName, className);
    }

    @Nullable
    public static final String getSourcePathFromAnnotation(@NotNull SootClass $this$getSourcePathFromAnnotation) {
        Intrinsics.checkNotNullParameter($this$getSourcePathFromAnnotation, "<this>");
        SourceFileTag tag = $this$getSourcePathFromAnnotation.getTag("SourceFileTag");
        SourceFileTag sourceFileTag = tag instanceof SourceFileTag ? tag : null;
        if (sourceFileTag == null) {
            return null;
        }
        SourceFileTag sft = sourceFileTag;
        String source = sft.getSourceFile();
        Intrinsics.checkNotNull(source);
        String fixed = StringsKt.substringBeforeLast$default(StringsKt.substringBeforeLast$default(StringsKt.substringBeforeLast$default(source, "..", (String) null, 2, (Object) null), "/", (String) null, 2, (Object) null), "\\", (String) null, 2, (Object) null);
        if (!ResourceKt.getJavaExtensions().contains(StringsKt.substringAfterLast$default(fixed, ".", (String) null, 2, (Object) null))) {
            return null;
        }
        String packageName = StringsKt.replace$default((String) classSplit($this$getSourcePathFromAnnotation).getFirst(), ".", "/", false, 4, (Object) null);
        if (packageName.length() == 0) {
            return fixed;
        }
        return packageName + "/" + fixed;
    }

    @Nullable
    public static final Constant castTo(@NotNull NumericConstant $this$castTo, @NotNull Type toType) {
        Intrinsics.checkNotNullParameter($this$castTo, "<this>");
        Intrinsics.checkNotNullParameter(toType, "toType");
        if (toType instanceof BooleanType) {
            if ($this$castTo instanceof IntConstant) {
                return IntConstant.v(((IntConstant) $this$castTo).value != 0 ? 1 : 0);
            }
            if ($this$castTo instanceof LongConstant) {
                return IntConstant.v(((int) ((LongConstant) $this$castTo).value) != 0 ? 1 : 0);
            }
            if ($this$castTo instanceof FloatConstant) {
                return IntConstant.v(((int) ((FloatConstant) $this$castTo).value) != 0 ? 1 : 0);
            }
            if ($this$castTo instanceof DoubleConstant) {
                return IntConstant.v(((int) ((DoubleConstant) $this$castTo).value) != 0 ? 1 : 0);
            }
            return null;
        }
        if (toType instanceof ByteType) {
            if ($this$castTo instanceof IntConstant) {
                return IntConstant.v((byte) ((IntConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof LongConstant) {
                return IntConstant.v((byte) ((LongConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof FloatConstant) {
                return IntConstant.v((byte) ((FloatConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof DoubleConstant) {
                return IntConstant.v((byte) ((DoubleConstant) $this$castTo).value);
            }
            return null;
        }
        if (toType instanceof CharType) {
            if ($this$castTo instanceof IntConstant) {
                return IntConstant.v((char) ((IntConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof LongConstant) {
                return IntConstant.v((char) ((LongConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof FloatConstant) {
                return IntConstant.v((char) ((FloatConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof DoubleConstant) {
                return IntConstant.v((char) ((DoubleConstant) $this$castTo).value);
            }
            return null;
        }
        if (toType instanceof ShortType) {
            if ($this$castTo instanceof IntConstant) {
                return IntConstant.v((short) ((IntConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof LongConstant) {
                return IntConstant.v((short) ((LongConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof FloatConstant) {
                return IntConstant.v((short) ((FloatConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof DoubleConstant) {
                return IntConstant.v((short) ((DoubleConstant) $this$castTo).value);
            }
            return null;
        }
        if (toType instanceof IntType) {
            if ($this$castTo instanceof IntConstant) {
                return (Constant) $this$castTo;
            }
            if ($this$castTo instanceof LongConstant) {
                return IntConstant.v((int) ((LongConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof FloatConstant) {
                return IntConstant.v((int) ((FloatConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof DoubleConstant) {
                return IntConstant.v((int) ((DoubleConstant) $this$castTo).value);
            }
            return null;
        }
        if (toType instanceof LongType) {
            if ($this$castTo instanceof IntConstant) {
                return LongConstant.v(((IntConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof LongConstant) {
                return (Constant) $this$castTo;
            }
            if ($this$castTo instanceof FloatConstant) {
                return LongConstant.v((long) ((FloatConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof DoubleConstant) {
                return LongConstant.v((long) ((DoubleConstant) $this$castTo).value);
            }
            return null;
        }
        if (toType instanceof FloatType) {
            if ($this$castTo instanceof IntConstant) {
                return FloatConstant.v(((IntConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof LongConstant) {
                return FloatConstant.v(((LongConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof FloatConstant) {
                return (Constant) $this$castTo;
            }
            if ($this$castTo instanceof DoubleConstant) {
                return FloatConstant.v((float) ((DoubleConstant) $this$castTo).value);
            }
            return null;
        }
        if (toType instanceof DoubleType) {
            if ($this$castTo instanceof IntConstant) {
                return DoubleConstant.v(((IntConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof LongConstant) {
                return DoubleConstant.v(((LongConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof FloatConstant) {
                return DoubleConstant.v(((FloatConstant) $this$castTo).value);
            }
            if ($this$castTo instanceof DoubleConstant) {
                return (Constant) $this$castTo;
            }
            return null;
        }
        return null;
    }

    @Nullable
    public static final NumericConstant equalEqual(@NotNull Constant $this$equalEqual, @NotNull Constant b, boolean isEq) {
        Intrinsics.checkNotNullParameter($this$equalEqual, "<this>");
        Intrinsics.checkNotNullParameter(b, "b");
        if ($this$equalEqual instanceof NumericConstant) {
            if (!(b instanceof NumericConstant)) {
                return IntConstant.v(0);
            }
            if (isEq) {
                return ((NumericConstant) $this$equalEqual).equalEqual((NumericConstant) b);
            }
            return ((NumericConstant) $this$equalEqual).notEqual((NumericConstant) b);
        }
        if (($this$equalEqual instanceof StringConstant) || ($this$equalEqual instanceof NullConstant) || ($this$equalEqual instanceof ClassConstant)) {
            boolean equality = Intrinsics.areEqual($this$equalEqual, b);
            boolean truth = isEq ? equality : !equality;
            return IntConstant.v(truth ? 1 : 0);
        }
        return null;
    }

    @Nullable
    public static final NumericConstant evalConstantBinop(@NotNull Expr expr, @NotNull Constant c1, @NotNull Constant c2) throws ArithmeticException, IllegalArgumentException {
        IntConstant intConstantCmpl;
        IntConstant intConstantCmp;
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(c1, "c1");
        Intrinsics.checkNotNullParameter(c2, "c2");
        if (expr instanceof AddExpr) {
            return ((NumericConstant) c1).add((NumericConstant) c2);
        }
        if (expr instanceof SubExpr) {
            return ((NumericConstant) c1).subtract((NumericConstant) c2);
        }
        if (expr instanceof MulExpr) {
            return ((NumericConstant) c1).multiply((NumericConstant) c2);
        }
        if (expr instanceof DivExpr) {
            return ((NumericConstant) c1).divide((NumericConstant) c2);
        }
        if (expr instanceof RemExpr) {
            return ((NumericConstant) c1).remainder((NumericConstant) c2);
        }
        if (expr instanceof EqExpr) {
            return equalEqual(c1, c2, true);
        }
        if (expr instanceof NeExpr) {
            return equalEqual(c1, c2, false);
        }
        if (expr instanceof GtExpr) {
            return ((NumericConstant) c1).greaterThan((NumericConstant) c2);
        }
        if (expr instanceof GeExpr) {
            return ((NumericConstant) c1).greaterThanOrEqual((NumericConstant) c2);
        }
        if (expr instanceof LtExpr) {
            return ((NumericConstant) c1).lessThan((NumericConstant) c2);
        }
        if (expr instanceof LeExpr) {
            return ((NumericConstant) c1).lessThanOrEqual((NumericConstant) c2);
        }
        if (expr instanceof AndExpr) {
            return ((ArithmeticConstant) c1).and((ArithmeticConstant) c2);
        }
        if (expr instanceof OrExpr) {
            return ((ArithmeticConstant) c1).or((ArithmeticConstant) c2);
        }
        if (expr instanceof XorExpr) {
            return ((ArithmeticConstant) c1).xor((ArithmeticConstant) c2);
        }
        if (expr instanceof ShlExpr) {
            return ((ArithmeticConstant) c1).shiftLeft((ArithmeticConstant) c2);
        }
        if (expr instanceof ShrExpr) {
            return ((ArithmeticConstant) c1).shiftRight((ArithmeticConstant) c2);
        }
        if (expr instanceof UshrExpr) {
            return ((ArithmeticConstant) c1).unsignedShiftRight((ArithmeticConstant) c2);
        }
        if (expr instanceof CmpExpr) {
            if ((c1 instanceof LongConstant) && (c2 instanceof LongConstant)) {
                intConstantCmp = ((LongConstant) c1).cmp((LongConstant) c2);
            } else {
                intConstantCmp = null;
            }
            return (NumericConstant) intConstantCmp;
        }
        if ((expr instanceof CmpgExpr) || (expr instanceof CmplExpr)) {
            if ((c1 instanceof RealConstant) && (c2 instanceof RealConstant)) {
                if (expr instanceof CmpgExpr) {
                    intConstantCmpl = ((RealConstant) c1).cmpg((RealConstant) c2);
                } else if (expr instanceof CmplExpr) {
                    intConstantCmpl = ((RealConstant) c1).cmpl((RealConstant) c2);
                } else {
                    intConstantCmpl = null;
                }
            } else {
                intConstantCmpl = null;
            }
            return (NumericConstant) intConstantCmpl;
        }
        return null;
    }

    @Nullable
    public static final InvokeExpr getInvokeExprOrNull(@NotNull Unit $this$invokeExprOrNull) {
        Intrinsics.checkNotNullParameter($this$invokeExprOrNull, "<this>");
        if (($this$invokeExprOrNull instanceof Stmt ? (Stmt) $this$invokeExprOrNull : null) == null || !((Stmt) $this$invokeExprOrNull).containsInvokeExpr()) {
            return null;
        }
        return ((Stmt) $this$invokeExprOrNull).getInvokeExpr();
    }

    @Nullable
    public static final InvokeExpr getInvokeExprOrNull(@NotNull Stmt $this$invokeExprOrNull) {
        Intrinsics.checkNotNullParameter($this$invokeExprOrNull, "<this>");
        if ($this$invokeExprOrNull.containsInvokeExpr()) {
            return $this$invokeExprOrNull.getInvokeExpr();
        }
        return null;
    }

    @Nullable
    public static final Value getLeftOp(@NotNull Unit $this$leftOp) {
        Intrinsics.checkNotNullParameter($this$leftOp, "<this>");
        DefinitionStmt definitionStmt = $this$leftOp instanceof DefinitionStmt ? (DefinitionStmt) $this$leftOp : null;
        if (definitionStmt != null) {
            return definitionStmt.getLeftOp();
        }
        return null;
    }

    @Nullable
    public static final <ToType extends Type> NumericConstant cvtNumericConstant(@NotNull StringConstant $this$cvtNumericConstant, int radix, @NotNull ToType totype) throws NumberFormatException {
        Intrinsics.checkNotNullParameter($this$cvtNumericConstant, "<this>");
        Intrinsics.checkNotNullParameter(totype, "type");
        String str = $this$cvtNumericConstant.value;
        Intrinsics.checkNotNullExpressionValue(str, "value");
        return cvtNumericConstant(str, radix, totype);
    }

    @Nullable
    public static final <ToType extends Type> NumericConstant cvtNumericConstant(@NotNull String $this$cvtNumericConstant, int radix, @NotNull ToType totype) throws NumberFormatException {
        Intrinsics.checkNotNullParameter($this$cvtNumericConstant, "<this>");
        Intrinsics.checkNotNullParameter(totype, "type");
        boolean z = 2 <= radix && radix < 37;
        if (!z) {
            return null;
        }
        if (totype instanceof IntegerType) {
            return IntConstant.v(Integer.parseInt($this$cvtNumericConstant, CharsKt.checkRadix(radix)));
        }
        if (totype instanceof LongType) {
            return LongConstant.v(Long.parseLong($this$cvtNumericConstant, CharsKt.checkRadix(radix)));
        }
        if (totype instanceof FloatType) {
            return FloatConstant.v(Float.parseFloat($this$cvtNumericConstant));
        }
        if (totype instanceof DoubleType) {
            return DoubleConstant.v(Double.parseDouble($this$cvtNumericConstant));
        }
        return null;
    }

    @NotNull
    public static final Type accurateType(@NotNull Constant $this$accurateType, @NotNull Function0<? extends Type> function0) {
        Intrinsics.checkNotNullParameter($this$accurateType, "<this>");
        Intrinsics.checkNotNullParameter(function0, "declareType");
        Type it = $this$accurateType.getType();
        if (it instanceof RefLikeType) {
            return it;
        }
        return (Type) function0.invoke();
    }

    public static final void printToSootClass(@NotNull String dir, @NotNull SootClass sClass) {
        Intrinsics.checkNotNullParameter(dir, "dir");
        Intrinsics.checkNotNullParameter(sClass, "sClass");
        String filename = SourceLocator.v().getFileNameFor(sClass, 1);
        File p = Paths.get(dir + File.separator + filename, new String[0]).toFile();
        if (!p.getParentFile().exists()) {
            p.getParentFile().mkdirs();
        }
        FileOutputStream streamOut = new FileOutputStream(p);
        PrintWriter writerOut = new PrintWriter(new OutputStreamWriter(streamOut));
        Printer.v().printTo(sClass, writerOut);
        writerOut.flush();
        writerOut.close();
    }

    @NotNull
    public static final IResFile sootClass2JasminClass(@NotNull SootClass sClass, @NotNull IResDirectory out) throws Exception {
        Intrinsics.checkNotNullParameter(sClass, "sClass");
        Intrinsics.checkNotNullParameter(out, "out");
        String fileName = SourceLocator.v().getFileNameFor(sClass, 14);
        Intrinsics.checkNotNull(fileName);
        IResFile outCLass = out.resolve(fileName).toFile();
        outCLass.mkdirs();
        OpenOption[] openOptionArr = new OpenOption[0];
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(outCLass.getPath(), (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        Intrinsics.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        JasminOutputStream jasminOutputStream = (Closeable) new JasminOutputStream(outputStreamNewOutputStream);
        Throwable th = null;
        try {
            try {
                PrintWriter writerOut = new PrintWriter(new OutputStreamWriter((OutputStream) jasminOutputStream));
                JasminClass jasminClass = new JasminClass(sClass);
                jasminClass.print(writerOut);
                writerOut.flush();
                kotlin.Unit unit = kotlin.Unit.INSTANCE;
                CloseableKt.closeFinally(jasminOutputStream, (Throwable) null);
                return outCLass;
            } finally {
            }
        } catch (Throwable th2) {
            CloseableKt.closeFinally(jasminOutputStream, th);
            throw th2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    @NotNull
    public static final Pair<Constant, Type> constOf(@NotNull Object v) throws NotImplementedError {
        Intrinsics.checkNotNullParameter(v, "v");
        if (v instanceof Constant) {
            return TuplesKt.to(v, ((Constant) v).getType());
        }
        if (v instanceof String) {
            return TuplesKt.to(StringConstant.v((String) v), Scene.v().getType("java.lang.String"));
        }
        if (v instanceof Boolean) {
            return TuplesKt.to(IntConstant.v(((Boolean) v).booleanValue() ? 1 : 0), G.v().soot_BooleanType());
        }
        if (v instanceof Number) {
            if (v instanceof Integer) {
                return TuplesKt.to(IntConstant.v(((Number) v).intValue()), G.v().soot_IntType());
            }
            if (v instanceof Long) {
                return TuplesKt.to(LongConstant.v(((Number) v).longValue()), G.v().soot_LongType());
            }
            if (v instanceof Double) {
                return TuplesKt.to(DoubleConstant.v(((Number) v).doubleValue()), G.v().soot_DoubleType());
            }
            if (v instanceof Float) {
                return TuplesKt.to(FloatConstant.v(((Number) v).floatValue()), G.v().soot_FloatType());
            }
            if (v instanceof Byte) {
                return TuplesKt.to(IntConstant.v(((Number) v).byteValue()), G.v().soot_ByteType());
            }
            if (v instanceof Short) {
                return TuplesKt.to(IntConstant.v(((Number) v).shortValue()), G.v().soot_ShortType());
            }
            throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
        }
        throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Iterator getCallTargets$default(Type type, SootMethod sootMethod, InvokeExpr invokeExpr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            sootMethod = null;
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return getCallTargets(type, sootMethod, invokeExpr, z);
    }

    @NotNull
    public static final Iterator<SootMethod> getCallTargets(@NotNull Type type, @Nullable SootMethod container, @NotNull InvokeExpr ie, boolean appOnly) {
        Type type2;
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(ie, "ie");
        SootMethodRef methodRef = ie.getMethodRef();
        VirtualCalls virtualCalls = VirtualCalls.v();
        ChunkedQueue targetsQueue = new ChunkedQueue();
        Iterator erVar = targetsQueue.reader();
        Intrinsics.checkNotNullExpressionValue(erVar, "reader(...)");
        Iterator iter = erVar;
        if (ie instanceof SpecialInvokeExpr) {
            SootMethod target = virtualCalls.resolveSpecial(methodRef, container, appOnly);
            if (target != null) {
                targetsQueue.add(target);
            }
            return iter;
        }
        if (ie instanceof InstanceInvokeExpr) {
            Local base = ((InstanceInvokeExpr) ie).getBase();
            Intrinsics.checkNotNull(base, "null cannot be cast to non-null type soot.Local");
            type2 = base.getType();
        } else {
            type2 = methodRef.getDeclaringClass().getType();
        }
        Type receiverType = type2;
        virtualCalls.resolve(type, receiverType, methodRef, container, targetsQueue, appOnly);
        return iter;
    }

    public static final void adjustLevel(@NotNull SootClass $this$adjustLevel, int level) {
        Intrinsics.checkNotNullParameter($this$adjustLevel, "<this>");
        if ($this$adjustLevel.resolvingLevel() < level) {
            $this$adjustLevel.setResolvingLevel(level);
        }
    }

    @Nullable
    public static final SootClass superClassOrNull(@NotNull SootClass $this$superClassOrNull) {
        Intrinsics.checkNotNullParameter($this$superClassOrNull, "<this>");
        if ($this$superClassOrNull.hasSuperclass()) {
            return $this$superClassOrNull.getSuperclass();
        }
        return null;
    }

    private static final List<SootClass> findAncestors(SootClass sc) {
        Iterable superClasses = (List) new ArrayList();
        List superInterfaces = new ArrayList();
        if (sc.isInterface()) {
            ((Collection) superClasses).add(Scene.v().getObjectType().getSootClass());
            List list = superInterfaces;
            List superinterfacesOfIncluding = Scene.v().getActiveHierarchy().getSuperinterfacesOfIncluding(sc);
            Intrinsics.checkNotNullExpressionValue(superinterfacesOfIncluding, "getSuperinterfacesOfIncluding(...)");
            CollectionsKt.addAll(list, superinterfacesOfIncluding);
        } else {
            ArrayList arrayList = (Collection) superClasses;
            List superclassesOfIncluding = Scene.v().getActiveHierarchy().getSuperclassesOfIncluding(sc);
            Intrinsics.checkNotNullExpressionValue(superclassesOfIncluding, "getSuperclassesOfIncluding(...)");
            CollectionsKt.addAll(arrayList, superclassesOfIncluding);
            List list2 = superInterfaces;
            Iterable $this$flatMap$iv = superClasses;
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$flatMap$iv) {
                SootClass it = (SootClass) element$iv$iv;
                Iterable interfaces = it.getInterfaces();
                Intrinsics.checkNotNullExpressionValue(interfaces, "getInterfaces(...)");
                Iterable list$iv$iv = interfaces;
                CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
            }
            Iterable $this$flatMap$iv2 = (List) destination$iv$iv;
            Collection destination$iv$iv2 = new ArrayList();
            for (Object element$iv$iv2 : $this$flatMap$iv2) {
                SootClass it2 = (SootClass) element$iv$iv2;
                Iterable superinterfacesOfIncluding2 = Scene.v().getActiveHierarchy().getSuperinterfacesOfIncluding(it2);
                Intrinsics.checkNotNullExpressionValue(superinterfacesOfIncluding2, "getSuperinterfacesOfIncluding(...)");
                Iterable list$iv$iv2 = superinterfacesOfIncluding2;
                CollectionsKt.addAll(destination$iv$iv2, list$iv$iv2);
            }
            CollectionsKt.addAll(list2, (List) destination$iv$iv2);
        }
        return CollectionsKt.plus((Collection) superClasses, superInterfaces);
    }

    @NotNull
    public static final Sequence<SootMethod> findMethodOrNull(@NotNull SootClass $this$findMethodOrNull, @NotNull String subSignature) {
        Intrinsics.checkNotNullParameter($this$findMethodOrNull, "<this>");
        Intrinsics.checkNotNullParameter(subSignature, "subSignature");
        adjustLevel($this$findMethodOrNull, 2);
        String params = StringsKt.substringAfter$default(subSignature, " ", (String) null, 2, (Object) null);
        Sequence classes = SequencesKt.generateSequence($this$findMethodOrNull, SootUtilsKt::findMethodOrNull$lambda$10);
        Sequence interfaces = SequencesKt.distinct(SequencesKt.flatMapIterable(SequencesKt.generateSequence($this$findMethodOrNull, SootUtilsKt::findMethodOrNull$lambda$11), SootUtilsKt::findMethodOrNull$lambda$13));
        return SequencesKt.filter(SequencesKt.flatMapIterable(SequencesKt.plus(classes, interfaces), SootUtilsKt::findMethodOrNull$lambda$14), (v2) -> {
            return findMethodOrNull$lambda$15(r1, r2, v2);
        });
    }

    private static final SootClass findMethodOrNull$lambda$10(SootClass it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return superClassOrNull(it);
    }

    private static final SootClass findMethodOrNull$lambda$11(SootClass it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return superClassOrNull(it);
    }

    private static final Iterable findMethodOrNull$lambda$13(SootClass sootClass) {
        Intrinsics.checkNotNullParameter(sootClass, "sootClass");
        Iterable interfaces = sootClass.getInterfaces();
        Intrinsics.checkNotNullExpressionValue(interfaces, "getInterfaces(...)");
        Iterable $this$flatMap$iv = interfaces;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$flatMap$iv) {
            SootClass it = (SootClass) element$iv$iv;
            Intrinsics.checkNotNull(it);
            Iterable list$iv$iv = findAncestors(it);
            CollectionsKt.addAll(destination$iv$iv, list$iv$iv);
        }
        return (List) destination$iv$iv;
    }

    private static final Iterable findMethodOrNull$lambda$14(SootClass it) {
        Intrinsics.checkNotNullParameter(it, "it");
        List methods = it.getMethods();
        Intrinsics.checkNotNullExpressionValue(methods, "getMethods(...)");
        return methods;
    }

    private static final boolean findMethodOrNull$lambda$15(SootClass $this_findMethodOrNull, String $params, SootMethod it) {
        if (!Intrinsics.areEqual($this_findMethodOrNull, it.getDeclaringClass()) && (it.isStatic() || it.isStaticInitializer() || it.isPrivate())) {
            return false;
        }
        String subSignature = it.getSubSignature();
        Intrinsics.checkNotNullExpressionValue(subSignature, "getSubSignature(...)");
        return Intrinsics.areEqual(StringsKt.substringAfter$default(subSignature, " ", (String) null, 2, (Object) null), $params);
    }

    @Nullable
    public static final SootMethod findMethodOrNull(@NotNull SootClass $this$findMethodOrNull, @NotNull String subSignature, @NotNull Function1<? super SootMethod, Boolean> function1) {
        Object obj;
        Intrinsics.checkNotNullParameter($this$findMethodOrNull, "<this>");
        Intrinsics.checkNotNullParameter(subSignature, "subSignature");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Sequence $this$firstOrNull$iv = findMethodOrNull($this$findMethodOrNull, subSignature);
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            Object element$iv = it.next();
            if (((Boolean) function1.invoke(element$iv)).booleanValue()) {
                obj = element$iv;
                break;
            }
        }
        return (SootMethod) obj;
    }

    public static final int getNumCode(@NotNull SootClass $this$numCode) {
        Intrinsics.checkNotNullParameter($this$numCode, "<this>");
        int loc = 0;
        for (SootMethod sm : $this$numCode.getMethods()) {
            if (sm.hasActiveBody()) {
                AnalysisCache.G g = AnalysisCache.G.INSTANCE;
                Intrinsics.checkNotNull(sm);
                Pair range = (Pair) g.get(new SootRangeKey(sm));
                if (range != null) {
                    int firstLineNumber = ((Number) range.component1()).intValue();
                    int lastLineNumber = ((Number) range.component2()).intValue();
                    loc = Math.max(Math.max(loc, firstLineNumber), lastLineNumber);
                }
            }
        }
        return loc;
    }

    @Nullable
    public static final String getSourcePath(@NotNull SootClass $this$sourcePath) {
        Intrinsics.checkNotNullParameter($this$sourcePath, "<this>");
        return ClassPathUtilKt.getSourcePathModule($this$sourcePath);
    }

    @NotNull
    public static final LinkedHashSet<String> getPossibleSourceFiles(@NotNull SootClass $this$possibleSourceFiles) {
        Intrinsics.checkNotNullParameter($this$possibleSourceFiles, "<this>");
        LinkedHashSet res = new LinkedHashSet();
        String it = getSourcePath($this$possibleSourceFiles);
        if (it != null) {
            res.add(it);
        }
        LinkedHashSet list = new LinkedHashSet();
        SourceLocator sourceLocatorV = SourceLocator.v();
        String name = $this$possibleSourceFiles.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        list.add(sourceLocatorV.getSourceForClass(StringsKt.replace$default(name, ".", "/", false, 4, (Object) null)));
        String name2 = $this$possibleSourceFiles.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        if (StringsKt.indexOf$default(name2, "$", 0, false, 6, (Object) null) != -1) {
            String name3 = $this$possibleSourceFiles.getName();
            Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
            list.add(CollectionsKt.joinToString$default(StringsKt.split$default(name3, new String[]{"."}, false, 0, 6, (Object) null), "/", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        }
        LinkedHashSet $this$flatMapTo$iv = list;
        for (Object element$iv : $this$flatMapTo$iv) {
            String src = (String) element$iv;
            Iterable $this$map$iv = ResourceKt.getJavaExtensions();
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                String ext = (String) item$iv$iv;
                destination$iv$iv.add(src + "." + ext);
            }
            Iterable list$iv = (List) destination$iv$iv;
            CollectionsKt.addAll(res, list$iv);
        }
        return res;
    }

    @Nullable
    public static final Body getActiveBodyOrNull(@NotNull SootMethod $this$activeBodyOrNull) {
        Intrinsics.checkNotNullParameter($this$activeBodyOrNull, "<this>");
        if ($this$activeBodyOrNull.hasActiveBody()) {
            return $this$activeBodyOrNull.getActiveBody();
        }
        return null;
    }
}
