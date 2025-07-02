package cn.sast.framework;

import cn.sast.api.util.OthersKt;
import cn.sast.common.IResource;
import cn.sast.common.Resource;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.IMethodMatch;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.SourceLocator;

/* compiled from: EntryPointCreatorFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010#\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0016"}, d2 = {"Lcn/sast/framework/EntryPointCreatorFactory;", "", "<init>", "()V", "logger", "Lmu/KLogger;", "lookFromDir", "", "res", "", "Lsoot/SootClass;", "direction", "Lcn/sast/common/IResource;", "loadClass", "className", "", "getEntryPointFromArgs", "Lkotlin/Function0;", "", "Lsoot/SootMethod;", "args", "", "corax-framework"})
@SourceDebugExtension({"SMAP\nEntryPointCreatorFactory.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EntryPointCreatorFactory.kt\ncn/sast/framework/EntryPointCreatorFactory\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Strings.kt\nkotlin/text/StringsKt__StringsKt\n*L\n1#1,73:1\n1863#2:74\n1864#2:98\n108#3:75\n80#3,22:76\n*S KotlinDebug\n*F\n+ 1 EntryPointCreatorFactory.kt\ncn/sast/framework/EntryPointCreatorFactory\n*L\n32#1:74\n32#1:98\n52#1:75\n52#1:76,22\n*E\n"})
/* loaded from: EntryPointCreatorFactory.class */
public final class EntryPointCreatorFactory {

    @NotNull
    public static final EntryPointCreatorFactory INSTANCE = new EntryPointCreatorFactory();

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(EntryPointCreatorFactory::logger$lambda$0);

    private EntryPointCreatorFactory() {
    }

    private static final Unit logger$lambda$0() {
        return Unit.INSTANCE;
    }

    private final void lookFromDir(Set<SootClass> set, IResource direction) {
        Scene scene = Scene.v();
        for (String cl : SourceLocator.v().getClassesUnder(direction.getAbsolutePath())) {
            SootClass theClass = scene.loadClass(cl, 2);
            Intrinsics.checkNotNullExpressionValue(theClass, "loadClass(...)");
            set.add(theClass);
        }
    }

    private final void loadClass(String className) {
        Scene.v().forceResolve(className, 3);
        Scene.v().loadClassAndSupport(className);
    }

    @NotNull
    public final Function0<Set<SootMethod>> getEntryPointFromArgs(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "args");
        return () -> {
            return getEntryPointFromArgs$lambda$5(r0);
        };
    }

    private static final Set getEntryPointFromArgs$lambda$5(List $args) {
        Set mSet = new LinkedHashSet();
        List $this$forEach$iv = $args;
        for (Object element$iv : $this$forEach$iv) {
            String arg = (String) element$iv;
            IMethodMatch match = OthersKt.methodSignatureToMatcher(arg);
            if (match != null) {
                Scene sceneV = Scene.v();
                Intrinsics.checkNotNullExpressionValue(sceneV, "v(...)");
                List ms = match.matched(sceneV);
                if (ms.isEmpty()) {
                    throw new IllegalStateException(("method: " + match + " not exists").toString());
                }
                mSet.addAll(ms);
            } else {
                IResource res = Resource.INSTANCE.of(arg);
                if (!res.getExists()) {
                    throw new IllegalStateException(("invalidate " + arg).toString());
                }
                if (res.isFile()) {
                    Path path = res.getPath();
                    OpenOption[] openOptionArr = new OpenOption[0];
                    InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length)), Charsets.UTF_8);
                    Throwable th = null;
                    try {
                        try {
                            InputStreamReader sd = inputStreamReader;
                            BufferedReader reader = new BufferedReader(sd);
                            while (true) {
                                Ref.ObjectRef line = new Ref.ObjectRef();
                                String line2 = reader.readLine();
                                if (line2 == null) {
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(inputStreamReader, (Throwable) null);
                                    break;
                                }
                                line.element = line2;
                                CharSequence $this$trim$iv = (String) line.element;
                                CharSequence $this$trim$iv$iv = $this$trim$iv;
                                int startIndex$iv$iv = 0;
                                int endIndex$iv$iv = $this$trim$iv$iv.length() - 1;
                                boolean startFound$iv$iv = false;
                                while (startIndex$iv$iv <= endIndex$iv$iv) {
                                    int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
                                    char it = $this$trim$iv$iv.charAt(index$iv$iv);
                                    boolean match$iv$iv = Intrinsics.compare(it, 32) <= 0;
                                    if (!startFound$iv$iv) {
                                        if (!match$iv$iv) {
                                            startFound$iv$iv = true;
                                        } else {
                                            startIndex$iv$iv++;
                                        }
                                    } else {
                                        if (!match$iv$iv) {
                                            break;
                                        }
                                        endIndex$iv$iv--;
                                    }
                                }
                                line.element = $this$trim$iv$iv.subSequence(startIndex$iv$iv, endIndex$iv$iv + 1).toString();
                                if (!(((CharSequence) line.element).length() == 0) && !StringsKt.startsWith$default((String) line.element, "-", false, 2, (Object) null)) {
                                    EntryPointCreatorFactory entryPointCreatorFactory = INSTANCE;
                                    String strSignatureToClass = Scene.signatureToClass((String) line.element);
                                    Intrinsics.checkNotNullExpressionValue(strSignatureToClass, "signatureToClass(...)");
                                    entryPointCreatorFactory.loadClass(strSignatureToClass);
                                    SootMethod m = Scene.v().grabMethod((String) line.element);
                                    if (m == null) {
                                        Function0 function0 = () -> {
                                            return getEntryPointFromArgs$lambda$5$lambda$4$lambda$3$lambda$2(r2);
                                        };
                                        throw new IllegalStateException(function0.toString());
                                    }
                                    mSet.add(m);
                                }
                            }
                        } finally {
                        }
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(inputStreamReader, th);
                        throw th2;
                    }
                } else {
                    continue;
                }
            }
        }
        return mSet;
    }

    private static final String getEntryPointFromArgs$lambda$5$lambda$4$lambda$3$lambda$2(Ref.ObjectRef $line) {
        return "soot method: " + $line.element + " not exists";
    }
}
