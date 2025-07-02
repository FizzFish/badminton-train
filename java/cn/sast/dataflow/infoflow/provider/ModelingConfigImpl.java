package cn.sast.dataflow.infoflow.provider;

import cn.sast.coroutines.caffine.impl.FastCacheImpl;
import cn.sast.dataflow.infoflow.provider.FieldFinder;
import cn.sast.dataflow.infoflow.provider.ModelingConfigImpl;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.dataflow.util.ConfigInfoLogger;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.cache.coroutines.FastCache;
import com.feysh.corax.config.api.AttributeName;
import com.feysh.corax.config.api.BinOp;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.BuiltInField;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.ClassField;
import com.feysh.corax.config.api.Elements;
import com.feysh.corax.config.api.IBoolExpr;
import com.feysh.corax.config.api.IClassDecl;
import com.feysh.corax.config.api.IClassField;
import com.feysh.corax.config.api.IExpr;
import com.feysh.corax.config.api.IFieldDecl;
import com.feysh.corax.config.api.IIexConst;
import com.feysh.corax.config.api.IIexGetField;
import com.feysh.corax.config.api.IIexLoad;
import com.feysh.corax.config.api.IIstSetField;
import com.feysh.corax.config.api.IIstStoreLocal;
import com.feysh.corax.config.api.IJDecl;
import com.feysh.corax.config.api.ILocalVarDecl;
import com.feysh.corax.config.api.IMethodDecl;
import com.feysh.corax.config.api.IMethodMatch;
import com.feysh.corax.config.api.IModelStmtVisitor;
import com.feysh.corax.config.api.IStmt;
import com.feysh.corax.config.api.IUnOpExpr;
import com.feysh.corax.config.api.MLocal;
import com.feysh.corax.config.api.MParameter;
import com.feysh.corax.config.api.MReturn;
import com.feysh.corax.config.api.MapKeys;
import com.feysh.corax.config.api.MapValues;
import com.feysh.corax.config.api.MethodConfig;
import com.feysh.corax.config.api.PreAnalysisApi;
import com.feysh.corax.config.api.SubFields;
import com.feysh.corax.config.api.TaintProperty;
import com.feysh.corax.config.api.UnOp;
import com.feysh.corax.config.api.ViaProperty;
import com.feysh.corax.config.api.baseimpl.AIAnalysisBaseImpl;
import com.feysh.corax.config.api.baseimpl.BinOpExpr;
import com.feysh.corax.config.api.utils.UtilsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.jimple.infoflow.methodSummary.data.sourceSink.FlowClear;
import soot.jimple.infoflow.methodSummary.data.sourceSink.FlowSink;
import soot.jimple.infoflow.methodSummary.data.sourceSink.FlowSource;
import soot.jimple.infoflow.methodSummary.data.summary.ClassMethodSummaries;
import soot.jimple.infoflow.methodSummary.data.summary.GapDefinition;
import soot.jimple.infoflow.methodSummary.data.summary.MethodClear;
import soot.jimple.infoflow.methodSummary.data.summary.MethodFlow;
import soot.jimple.infoflow.methodSummary.data.summary.MethodSummaries;
import soot.jimple.infoflow.methodSummary.data.summary.SourceSinkType;
import soot.jimple.infoflow.methodSummary.taintWrappers.AccessPathFragment;

/* compiled from: MethodSummaryProvider.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010��\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018�� H2\u00020\u0001:\u0001HB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J@\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0!H\u0002JH\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0!H\u0002J8\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0!H\u0002J\u0011\u0010(\u001a\u0004\u0018\u00010\u001b*\u00020)¢\u0006\u0002\u0010*J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0002J9\u00101\u001a\u00020,2\u0006\u00102\u001a\u0002032\u001f\u00104\u001a\u001b\u0012\b\u0012\u000606j\u0002`7\u0012\u0004\u0012\u00020,05j\u0002`9¢\u0006\u0002\b82\u0006\u0010/\u001a\u000200H\u0016JZ\u0010:\u001a\u00020,2\u0006\u00102\u001a\u0002032\u001f\u00104\u001a\u001b\u0012\b\u0012\u000606j\u0002`7\u0012\u0004\u0012\u00020,05j\u0002`9¢\u0006\u0002\b82\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0017\u0010?\u001a\u0013\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020,05¢\u0006\u0002\b8H\u0016J\\\u0010A\u001a\u00020,2\u0006\u00102\u001a\u0002032\u001f\u00104\u001a\u001b\u0012\b\u0012\u000606j\u0002`7\u0012\u0004\u0012\u00020,05j\u0002`9¢\u0006\u0002\b82\u0006\u0010;\u001a\u00020)2!\u0010B\u001a\u001d\u0012\u0013\u0012\u00110C¢\u0006\f\bD\u0012\b\bE\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020,05H\u0016J\b\u0010G\u001a\u00020,H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0016\u0010\u0017¨\u0006I"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/ModelingConfigImpl;", "Lcom/feysh/corax/config/api/baseimpl/AIAnalysisBaseImpl;", "provider", "Lcn/sast/dataflow/infoflow/provider/MethodSummaryProvider;", "preAnalysis", "Lcom/feysh/corax/config/api/PreAnalysisApi;", "<init>", "(Lcn/sast/dataflow/infoflow/provider/MethodSummaryProvider;Lcom/feysh/corax/config/api/PreAnalysisApi;)V", "getProvider", "()Lcn/sast/dataflow/infoflow/provider/MethodSummaryProvider;", "getPreAnalysis", "()Lcom/feysh/corax/config/api/PreAnalysisApi;", "fastCache", "Lcom/feysh/corax/cache/coroutines/FastCache;", "getFastCache", "()Lcom/feysh/corax/cache/coroutines/FastCache;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "error", "Lcn/sast/dataflow/util/ConfigInfoLogger;", "getError", "()Lcn/sast/dataflow/util/ConfigInfoLogger;", "createSource", "Lsoot/jimple/infoflow/methodSummary/data/sourceSink/FlowSource;", "matchStrict", "", "loc", "Lcom/feysh/corax/config/api/MLocal;", "baseType", "", "fields", "", "fieldTypes", "createSink", "Lsoot/jimple/infoflow/methodSummary/data/sourceSink/FlowSink;", "taintSubFields", "createClear", "Lsoot/jimple/infoflow/methodSummary/data/sourceSink/FlowClear;", "isEmptySetValue", "Lcom/feysh/corax/config/api/IExpr;", "(Lcom/feysh/corax/config/api/IExpr;)Ljava/lang/Boolean;", "addSummaryFromStmt", "", "method", "Lsoot/SootMethod;", "stmt", "Lcom/feysh/corax/config/api/IStmt;", "addStmt", "decl", "Lcom/feysh/corax/config/api/IJDecl;", "config", "Lkotlin/Function1;", "Lcom/feysh/corax/config/api/MethodConfig;", "Lcom/feysh/corax/config/api/MethodConfigType;", "Lkotlin/ExtensionFunctionType;", "Lcom/feysh/corax/config/api/MethodConfigBlockType;", "check", "expr", "Lcom/feysh/corax/config/api/IBoolExpr;", "checkType", "Lcom/feysh/corax/config/api/CheckType;", "env", "Lcom/feysh/corax/config/api/BugMessage$Env;", "eval", "accept", "", "Lkotlin/ParameterName;", "name", "value", "validate", "Companion", "corax-data-flow"})
@SourceDebugExtension({"SMAP\nMethodSummaryProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MethodSummaryProvider.kt\ncn/sast/dataflow/infoflow/provider/ModelingConfigImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,449:1\n1863#2,2:450\n*S KotlinDebug\n*F\n+ 1 MethodSummaryProvider.kt\ncn/sast/dataflow/infoflow/provider/ModelingConfigImpl\n*L\n420#1:450,2\n*E\n"})
/* loaded from: ModelingConfigImpl.class */
public final class ModelingConfigImpl extends AIAnalysisBaseImpl {

    @NotNull
    private final MethodSummaryProvider provider;

    @NotNull
    private final PreAnalysisApi preAnalysis;

    @NotNull
    private final ConfigInfoLogger error;

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(ModelingConfigImpl::logger$lambda$1);

    public ModelingConfigImpl(@NotNull MethodSummaryProvider provider, @NotNull PreAnalysisApi preAnalysis) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        Intrinsics.checkNotNullParameter(preAnalysis, "preAnalysis");
        this.provider = provider;
        this.preAnalysis = preAnalysis;
        this.error = new ConfigInfoLogger();
    }

    @NotNull
    public final MethodSummaryProvider getProvider() {
        return this.provider;
    }

    @NotNull
    public PreAnalysisApi getPreAnalysis() {
        return this.preAnalysis;
    }

    @NotNull
    public FastCache getFastCache() {
        return FastCacheImpl.INSTANCE;
    }

    @NotNull
    public CoroutineScope getScope() {
        return GlobalScope.INSTANCE;
    }

    @NotNull
    /* renamed from: getError, reason: merged with bridge method [inline-methods] */
    public ConfigInfoLogger m121getError() {
        return this.error;
    }

    /* compiled from: MethodSummaryProvider.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��L\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0010 \n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JY\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b29\u0010\r\u001a5\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u000b\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00070\u000eJ{\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00152\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0016\u001a\u00020\u00172S\u0010\u0018\u001aO\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001a\u0012\u0015\u0012\u0013\u0018\u00010\t¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00070\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u001b"}, d2 = {"Lcn/sast/dataflow/infoflow/provider/ModelingConfigImpl$Companion;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "transFields", "", "baseType", "", "fields", "", "Lcom/feysh/corax/config/api/IClassField;", "res", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "subFields", "getAccessPath", "method", "Lsoot/SootMethod;", "expr", "Lcom/feysh/corax/config/api/IExpr;", "cb", "Lcom/feysh/corax/config/api/MLocal;", "p", "corax-data-flow"})
    @SourceDebugExtension({"SMAP\nMethodSummaryProvider.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MethodSummaryProvider.kt\ncn/sast/dataflow/infoflow/provider/ModelingConfigImpl$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,449:1\n774#2:450\n865#2,2:451\n1557#2:453\n1628#2,3:454\n1557#2:457\n1628#2,3:458\n*S KotlinDebug\n*F\n+ 1 MethodSummaryProvider.kt\ncn/sast/dataflow/infoflow/provider/ModelingConfigImpl$Companion\n*L\n145#1:450\n145#1:451,2\n147#1:453\n147#1:454,3\n148#1:457\n148#1:458,3\n*E\n"})
    /* loaded from: ModelingConfigImpl$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
        public final void transFields(@Nullable String baseType, @NotNull List<? extends IClassField> list, @NotNull Function3<? super List<String>, ? super List<String>, ? super Boolean, Unit> function3) throws NotImplementedError {
            List listSootFields;
            Intrinsics.checkNotNullParameter(list, "fields");
            Intrinsics.checkNotNullParameter(function3, "res");
            if (!list.isEmpty()) {
                IClassField last = (IClassField) CollectionsKt.last(list);
                if ((last instanceof ClassField) || (last instanceof SubFields)) {
                    listSootFields = new FieldFinder(baseType, list).sootFields();
                } else if (Intrinsics.areEqual(last, MapValues.INSTANCE) || Intrinsics.areEqual(last, MapKeys.INSTANCE) || Intrinsics.areEqual(last, Elements.INSTANCE) || (last instanceof AttributeName) || (last instanceof ViaProperty) || Intrinsics.areEqual(last, TaintProperty.INSTANCE)) {
                    listSootFields = new FieldFinder(baseType, (List<? extends IClassField>) CollectionsKt.dropLast(list, 1)).sootFields();
                } else {
                    throw new NotImplementedError((String) null, 1, (DefaultConstructorMarker) null);
                }
                List sootFields = listSootFields;
                for (FieldFinder.AccessPath sf : sootFields) {
                    Iterable $this$filter$iv = sf.getSootField();
                    Collection destination$iv$iv = new ArrayList();
                    for (Object element$iv$iv : $this$filter$iv) {
                        SootField it = (SootField) element$iv$iv;
                        Type type = it.getType();
                        Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                        if (UtilsKt.getTypename(type) != null) {
                            destination$iv$iv.add(element$iv$iv);
                        }
                    }
                    Iterable nonnullTypeNameFields = (List) destination$iv$iv;
                    Iterable $this$map$iv = nonnullTypeNameFields;
                    Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        SootField it2 = (SootField) item$iv$iv;
                        destination$iv$iv2.add(it2.getSignature());
                    }
                    ArrayList arrayList = (List) destination$iv$iv2;
                    Iterable $this$map$iv2 = nonnullTypeNameFields;
                    Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv2, 10));
                    for (Object item$iv$iv2 : $this$map$iv2) {
                        SootField it3 = (SootField) item$iv$iv2;
                        Type type2 = it3.getType();
                        Intrinsics.checkNotNullExpressionValue(type2, "getType(...)");
                        String typename = UtilsKt.getTypename(type2);
                        Intrinsics.checkNotNull(typename);
                        destination$iv$iv3.add(typename);
                    }
                    function3.invoke(arrayList, (List) destination$iv$iv3, Boolean.valueOf(sf.getSubFields()));
                }
                return;
            }
            function3.invoke(CollectionsKt.emptyList(), CollectionsKt.emptyList(), true);
        }

        public static /* synthetic */ void getAccessPath$default(Companion companion, SootMethod sootMethod, List list, IExpr iExpr, Function3 function3, int i, Object obj) throws NoWhenBranchMatchedException {
            if ((i & 2) != 0) {
                list = CollectionsKt.emptyList();
            }
            companion.getAccessPath(sootMethod, list, iExpr, function3);
        }

        private static final void getAccessPath$add(SootMethod $method, Function3<? super MLocal, ? super String, ? super List<? extends IClassField>, Unit> function3, IExpr e, List<? extends IClassField> list) throws NoWhenBranchMatchedException {
            if (e instanceof IIexLoad) {
                MLocal op = ((IIexLoad) e).getOp();
                Type typeBaseType = SourceSinkProviderKt.baseType($method, op);
                String baseType = typeBaseType != null ? typeBaseType.toString() : null;
                function3.invoke(op, baseType, list);
                return;
            }
            if (e instanceof IIexGetField) {
                List newFields = CollectionsKt.plus(((IIexGetField) e).getAccessPath(), list);
                getAccessPath$add($method, function3, ((IIexGetField) e).getBase(), newFields);
            }
        }

        private static final void getAccessPath$resolveExpr(SootMethod $method, Function3<? super MLocal, ? super String, ? super List<? extends IClassField>, Unit> function3, IExpr expr, List<? extends IClassField> list) throws NoWhenBranchMatchedException {
            if (!(expr instanceof IUnOpExpr) || ((IUnOpExpr) expr).getOp() != UnOp.GetSet) {
                if ((expr instanceof BinOpExpr) && (((BinOpExpr) expr).getOp() == BinOp.OrSet || ((BinOpExpr) expr).getOp() == BinOp.AndSet || ((BinOpExpr) expr).getOp() == BinOp.RemoveSet || ((BinOpExpr) expr).getOp() == BinOp.AnyOf)) {
                    getAccessPath$resolveExpr($method, function3, ((BinOpExpr) expr).getOp1(), list);
                    getAccessPath$resolveExpr($method, function3, ((BinOpExpr) expr).getOp2(), list);
                    return;
                } else {
                    getAccessPath$add($method, function3, expr, list);
                    return;
                }
            }
            getAccessPath$add($method, function3, ((IUnOpExpr) expr).getOp1(), list);
        }

        public final void getAccessPath(@NotNull SootMethod method, @NotNull List<? extends IClassField> list, @NotNull IExpr expr, @NotNull Function3<? super MLocal, ? super String, ? super List<? extends IClassField>, Unit> function3) throws NoWhenBranchMatchedException {
            Intrinsics.checkNotNullParameter(method, "method");
            Intrinsics.checkNotNullParameter(list, "fields");
            Intrinsics.checkNotNullParameter(expr, "expr");
            Intrinsics.checkNotNullParameter(function3, "cb");
            getAccessPath$resolveExpr(method, function3, expr, list);
        }
    }

    private static final Unit logger$lambda$1() {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FlowSource createSource(boolean matchStrict, MLocal loc, String baseType, List<String> list, List<String> list2) {
        AccessPathFragment acp = list.isEmpty() ? null : new AccessPathFragment(CollectionsKt.toMutableList(list), CollectionsKt.toMutableList(list2));
        if (loc instanceof MReturn) {
            return null;
        }
        if ((loc instanceof MParameter) && ((MParameter) loc).getIndex() >= 0) {
            return new FlowSource(SourceSinkType.Parameter, ((MParameter) loc).getIndex(), baseType, acp, (GapDefinition) null, matchStrict);
        }
        if ((loc instanceof MParameter) && ((MParameter) loc).getIndex() == -1) {
            return new FlowSource(SourceSinkType.Field, baseType, acp, (GapDefinition) null, matchStrict);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FlowSink createSink(boolean matchStrict, boolean taintSubFields, MLocal loc, String baseType, List<String> list, List<String> list2) {
        AccessPathFragment acp = list.isEmpty() ? null : new AccessPathFragment(CollectionsKt.toMutableList(list), CollectionsKt.toMutableList(list2));
        if (loc instanceof MReturn) {
            return new FlowSink(SourceSinkType.Return, baseType, acp, taintSubFields, (GapDefinition) null, matchStrict);
        }
        if ((loc instanceof MParameter) && ((MParameter) loc).getIndex() >= 0) {
            return new FlowSink(SourceSinkType.Parameter, ((MParameter) loc).getIndex(), baseType, acp, taintSubFields, (GapDefinition) null, matchStrict);
        }
        if ((loc instanceof MParameter) && ((MParameter) loc).getIndex() == -1) {
            return new FlowSink(SourceSinkType.Field, baseType, acp, taintSubFields, (GapDefinition) null, matchStrict);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FlowClear createClear(MLocal loc, String baseType, List<String> list, List<String> list2) {
        AccessPathFragment acp = list.isEmpty() ? null : new AccessPathFragment(CollectionsKt.toMutableList(list), CollectionsKt.toMutableList(list2));
        if (!(loc instanceof MReturn)) {
            if ((loc instanceof MParameter) && ((MParameter) loc).getIndex() >= 0) {
                return new FlowClear(SourceSinkType.Parameter, ((MParameter) loc).getIndex(), baseType, acp, (GapDefinition) null);
            }
            if ((loc instanceof MParameter) && ((MParameter) loc).getIndex() >= -1) {
                return new FlowClear(SourceSinkType.Field, baseType, acp, (GapDefinition) null);
            }
            return null;
        }
        return null;
    }

    @Nullable
    public final Boolean isEmptySetValue(@NotNull IExpr $this$isEmptySetValue) {
        Intrinsics.checkNotNullParameter($this$isEmptySetValue, "<this>");
        if ($this$isEmptySetValue instanceof IIexConst) {
            Object obj = ((IIexConst) $this$isEmptySetValue).getConst();
            Set set = obj instanceof Set ? (Set) obj : null;
            if (set == null) {
                return false;
            }
            Set set2 = set;
            return Boolean.valueOf(set2.isEmpty());
        }
        return null;
    }

    private final void addSummaryFromStmt(final SootMethod method, IStmt stmt) {
        ClassMethodSummaries summaries = new ClassMethodSummaries(method.getDeclaringClass().getName());
        final MethodSummaries summary = summaries.getMethodSummaries();
        Intrinsics.checkNotNullExpressionValue(summary, "getMethodSummaries(...)");
        final List from = new ArrayList();
        final List to = new ArrayList();
        final String subSignature = method.getSubSignature();
        final Ref.ObjectRef typeChecking = new Ref.ObjectRef();
        typeChecking.element = false;
        final Ref.ObjectRef ignoreTypes = new Ref.ObjectRef();
        final Ref.ObjectRef cutSubfields = new Ref.ObjectRef();
        final Ref.BooleanRef matchStrict = new Ref.BooleanRef();
        final boolean taintSubFields = false;
        stmt.accept(new IModelStmtVisitor<Object>() { // from class: cn.sast.dataflow.infoflow.provider.ModelingConfigImpl$addSummaryFromStmt$stmtVisitor$1
            /* renamed from: visit, reason: collision with other method in class */
            public /* bridge */ /* synthetic */ Object m123visit(IIstSetField stmt2) throws NoWhenBranchMatchedException {
                visit(stmt2);
                return Unit.INSTANCE;
            }

            /* renamed from: default, reason: not valid java name */
            public Object m122default(IStmt stmt2) {
                Intrinsics.checkNotNullParameter(stmt2, "stmt");
                return Unit.INSTANCE;
            }

            public final void initFlowSource(List<FlowSource> list, MethodSummaries summary2, SootMethod method2, IExpr expr, boolean matchStrict2) throws NoWhenBranchMatchedException {
                Intrinsics.checkNotNullParameter(list, "from");
                Intrinsics.checkNotNullParameter(summary2, "summary");
                Intrinsics.checkNotNullParameter(method2, "method");
                Intrinsics.checkNotNullParameter(expr, "expr");
                ModelingConfigImpl.Companion companion = ModelingConfigImpl.Companion;
                List<? extends IClassField> listEmptyList = CollectionsKt.emptyList();
                ModelingConfigImpl modelingConfigImpl = this.this$0;
                companion.getAccessPath(method2, listEmptyList, expr, (v6, v7, v8) -> {
                    return initFlowSource$lambda$2(r4, r5, r6, r7, r8, r9, v6, v7, v8);
                });
            }

            private static final Unit initFlowSource$lambda$2(ModelingConfigImpl this$0, boolean $matchStrict, IExpr $expr, SootMethod $method, MethodSummaries $summary, List $from, MLocal op, String baseType, List fields) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(op, "op");
                Intrinsics.checkNotNullParameter(fields, "fields");
                ModelingConfigImpl.Companion.transFields(baseType, fields, (v8, v9, v10) -> {
                    return initFlowSource$lambda$2$lambda$1(r3, r4, r5, r6, r7, r8, r9, r10, v8, v9, v10);
                });
                return Unit.INSTANCE;
            }

            private static final Unit initFlowSource$lambda$2$lambda$1(ModelingConfigImpl this$0, boolean $matchStrict, MLocal $op, String $baseType, IExpr $expr, SootMethod $method, MethodSummaries $summary, List $from, List fieldSigs, List fieldTypes, boolean subFields) {
                Intrinsics.checkNotNullParameter(fieldSigs, "fieldSigs");
                Intrinsics.checkNotNullParameter(fieldTypes, "fieldTypes");
                FlowSource it = this$0.createSource($matchStrict, $op, $baseType, fieldSigs, fieldTypes);
                if (it != null) {
                    $from.add(it);
                }
                if (Intrinsics.areEqual(this$0.isEmptySetValue($expr), true)) {
                    MethodClear clear = new MethodClear($method.getSubSignature(), this$0.createClear($op, $baseType, fieldSigs, fieldTypes));
                    $summary.addClear(clear);
                }
                return Unit.INSTANCE;
            }

            public void visit(IIstSetField stmt2) throws NoWhenBranchMatchedException {
                Intrinsics.checkNotNullParameter(stmt2, "stmt");
                IClassField field = stmt2.getField();
                if ((field instanceof ClassField) || Intrinsics.areEqual(field, TaintProperty.INSTANCE) || Intrinsics.areEqual(field, MapKeys.INSTANCE) || Intrinsics.areEqual(field, MapValues.INSTANCE) || Intrinsics.areEqual(field, Elements.INSTANCE)) {
                    initFlowSource(from, summary, method, stmt2.getValue(), matchStrict.element);
                    ModelingConfigImpl.Companion companion = ModelingConfigImpl.Companion;
                    SootMethod sootMethod = method;
                    List<? extends IClassField> listListOf = CollectionsKt.listOf(stmt2.getField());
                    IExpr base = stmt2.getBase();
                    ModelingConfigImpl modelingConfigImpl = this.this$0;
                    Ref.BooleanRef booleanRef = matchStrict;
                    List<FlowSink> list = to;
                    companion.getAccessPath(sootMethod, listListOf, base, (v3, v4, v5) -> {
                        return visit$lambda$5(r4, r5, r6, v3, v4, v5);
                    });
                    boolean isAlias = !(field instanceof TaintProperty);
                    for (FlowSource fromE : from) {
                        for (FlowSink toE : to) {
                            MethodFlow flow = new MethodFlow(subSignature, fromE, toE, isAlias, (Boolean) typeChecking.element, (Boolean) ignoreTypes.element, (Boolean) cutSubfields.element);
                            summary.addFlow(flow);
                        }
                    }
                    return;
                }
                if (field instanceof BuiltInField) {
                }
            }

            private static final Unit visit$lambda$5(ModelingConfigImpl this$0, Ref.BooleanRef $matchStrict, List $to, MLocal op, String baseType, List fields) throws NotImplementedError {
                Intrinsics.checkNotNullParameter(op, "op");
                Intrinsics.checkNotNullParameter(fields, "fields");
                ModelingConfigImpl.Companion.transFields(baseType, fields, (v5, v6, v7) -> {
                    return visit$lambda$5$lambda$4(r3, r4, r5, r6, r7, v5, v6, v7);
                });
                return Unit.INSTANCE;
            }

            private static final Unit visit$lambda$5$lambda$4(ModelingConfigImpl this$0, Ref.BooleanRef $matchStrict, MLocal $op, String $baseType, List $to, List fieldSigs, List fieldTypes, boolean subFields) {
                Intrinsics.checkNotNullParameter(fieldSigs, "fieldSigs");
                Intrinsics.checkNotNullParameter(fieldTypes, "fieldTypes");
                FlowSink it = this$0.createSink($matchStrict.element, subFields, $op, $baseType, fieldSigs, fieldTypes);
                if (it != null) {
                    $to.add(it);
                }
                return Unit.INSTANCE;
            }

            public Object visit(IIstStoreLocal stmt2) throws NoWhenBranchMatchedException {
                Intrinsics.checkNotNullParameter(stmt2, "stmt");
                initFlowSource(from, summary, method, stmt2.getValue(), matchStrict.element);
                MLocal toLocal = stmt2.getLocal();
                Type typeBaseType = SourceSinkProviderKt.baseType(method, toLocal);
                String baseType = typeBaseType != null ? typeBaseType.toString() : null;
                List fieldSigs = CollectionsKt.emptyList();
                List fieldTypes = CollectionsKt.emptyList();
                FlowSink it = this.this$0.createSink(matchStrict.element, taintSubFields, toLocal, baseType, fieldSigs, fieldTypes);
                if (it != null) {
                    to.add(it);
                }
                for (FlowSource fromE : from) {
                    for (FlowSink toE : to) {
                        MethodFlow flow = new MethodFlow(subSignature, fromE, toE, true, (Boolean) typeChecking.element, (Boolean) ignoreTypes.element, (Boolean) cutSubfields.element);
                        summary.addFlow(flow);
                    }
                }
                return Unit.INSTANCE;
            }
        });
        this.provider.addMethodSummaries(summaries);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NoWhenBranchMatchedException */
    public void addStmt(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IStmt stmt) throws NoWhenBranchMatchedException {
        Intrinsics.checkNotNullParameter(decl, "decl");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(stmt, "stmt");
        if (decl instanceof IMethodDecl) {
            IMethodMatch match = ((IMethodDecl) decl).getMatch();
            Scene sceneV = Scene.v();
            Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
            Iterable sootMethods = match.matched(sceneV);
            Iterable $this$forEach$iv = sootMethods;
            for (Object element$iv : $this$forEach$iv) {
                SootMethod method = (SootMethod) element$iv;
                Set<SootMethod> allImpls = SetsKt.plus(MethodSummaryProviderKt.findAllOverrideMethodsOfMethod(method), method);
                for (SootMethod impl : allImpls) {
                    addSummaryFromStmt(impl, stmt);
                }
            }
            return;
        }
        if (!(decl instanceof IClassDecl) && !(decl instanceof IFieldDecl) && !(decl instanceof ILocalVarDecl)) {
            throw new NoWhenBranchMatchedException();
        }
    }

    public void check(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IBoolExpr expr, @NotNull CheckType checkType, @NotNull Function1<? super BugMessage.Env, Unit> function12) {
        Intrinsics.checkNotNullParameter(decl, "decl");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(checkType, "checkType");
        Intrinsics.checkNotNullParameter(function12, "env");
    }

    public void eval(@NotNull IJDecl decl, @NotNull Function1<? super MethodConfig, Unit> function1, @NotNull IExpr expr, @NotNull Function1<Object, Unit> function12) {
        Intrinsics.checkNotNullParameter(decl, "decl");
        Intrinsics.checkNotNullParameter(function1, "config");
        Intrinsics.checkNotNullParameter(expr, "expr");
        Intrinsics.checkNotNullParameter(function12, "accept");
    }

    public void validate() {
    }
}
