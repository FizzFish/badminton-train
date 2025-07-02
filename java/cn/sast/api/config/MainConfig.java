package cn.sast.api.config;

import cn.sast.api.incremental.IncrementalAnalyze;
import cn.sast.api.incremental.IncrementalAnalyzeByChangeFiles;
import cn.sast.api.util.IMonitor;
import cn.sast.common.FileSystemLocator;
import cn.sast.common.IResDirectory;
import cn.sast.common.IResFile;
import cn.sast.common.IResource;
import cn.sast.common.OS;
import cn.sast.common.Resource;
import cn.sast.common.ResourceImplKt;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.IRelativePath;
import com.feysh.corax.config.api.Language;
import com.feysh.corax.config.api.rules.ProcessRule;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentSet;
import mu.KLogger;
import mu.KotlinLogging;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import soot.Scene;

/* compiled from: MainConfig.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0010��\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\"\n��\n\u0002\u0018\u0002\n��\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\bl\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u001e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018�� \u0089\u00022\u00020\u0001:\u0004\u0089\u0002\u008a\u0002B×\u0003\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0014\u0012\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u000e\b\u0002\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u000b\u0012\b\b\u0002\u0010 \u001a\u00020\u000b\u0012\b\b\u0002\u0010!\u001a\u00020\"\u0012\b\b\u0002\u0010#\u001a\u00020\"\u0012\b\b\u0002\u0010$\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020\r0&\u0012\b\b\u0002\u0010'\u001a\u00020(\u0012\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0*\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010-\u001a\u00020\u000b\u0012\b\b\u0002\u0010.\u001a\u00020\u000b\u0012\b\b\u0002\u0010/\u001a\u00020\u000b\u0012\b\b\u0002\u00100\u001a\u00020\u000b\u0012\b\b\u0002\u00101\u001a\u00020\u000b\u0012\b\b\u0002\u00102\u001a\u00020\u000b\u0012\b\b\u0002\u00103\u001a\u00020\u000b\u0012\b\b\u0002\u00104\u001a\u00020\u000b\u0012\b\b\u0002\u00105\u001a\u000206\u0012\b\b\u0002\u00107\u001a\u00020\r\u0012\b\b\u0002\u00108\u001a\u00020\r\u0012\b\b\u0002\u00109\u001a\u00020:¢\u0006\u0004\b;\u0010<J\u001d\u0010É\u0001\u001a\b\u0012\u0004\u0012\u00020\r0&2\u000e\u0010Ê\u0001\u001a\t\u0012\u0004\u0012\u00020\r0Ë\u0001J\r\u0010Ø\u0001\u001a\b\u0012\u0004\u0012\u00020\u00150&J\r\u0010Ù\u0001\u001a\b\u0012\u0004\u0012\u00020\r0&J\u0012\u0010Ú\u0001\u001a\u0004\u0018\u00010\r2\u0007\u0010Û\u0001\u001a\u00020\rJ\u0011\u0010Ü\u0001\u001a\u00020\u000b2\b\u0010Ý\u0001\u001a\u00030Þ\u0001J\u0010\u0010Ü\u0001\u001a\u00020\u000b2\u0007\u0010Ý\u0001\u001a\u00020\rJ\b\u0010ö\u0001\u001a\u00030÷\u0001J\u0011\u0010ø\u0001\u001a\u00020\u000b2\b\u0010ù\u0001\u001a\u00030ú\u0001J%\u0010û\u0001\u001a\u00020\u000b2\u0016\u0010ù\u0001\u001a\f\u0012\u0007\b\u0001\u0012\u00030ú\u00010ü\u0001\"\u00030ú\u0001¢\u0006\u0003\u0010ý\u0001J\u001b\u0010þ\u0001\u001a\u00020\r2\u0007\u0010ÿ\u0001\u001a\u00020\r2\u0007\u0010Ý\u0001\u001a\u00020\rH\u0002J\u0011\u0010\u0080\u0002\u001a\u00030\u0081\u00022\u0007\u0010\u0082\u0002\u001a\u00020\rJ\u0011\u0010\u0080\u0002\u001a\u00030\u0081\u00022\u0007\u0010\u0082\u0002\u001a\u00020\u0015J\u0011\u0010\u0083\u0002\u001a\u00030\u0081\u00022\u0007\u0010\u0084\u0002\u001a\u00020\u0015J\u0012\u0010\u0083\u0002\u001a\u00030\u0081\u00022\b\u0010\u0084\u0002\u001a\u00030Þ\u0001J+\u0010\u0085\u0002\u001a\n\u0012\u0005\u0012\u0003H\u0086\u00020Ë\u0001\"\t\b��\u0010\u0086\u0002*\u00020\u00012\u000f\u0010\u0087\u0002\u001a\n\u0012\u0005\u0012\u0003H\u0086\u00020Ë\u0001J+\u0010\u0088\u0002\u001a\n\u0012\u0005\u0012\u0003H\u0086\u00020Ë\u0001\"\t\b��\u0010\u0086\u0002*\u00020\u00012\u000f\u0010\u0087\u0002\u001a\n\u0012\u0005\u0012\u0003H\u0086\u00020Ë\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n��\u001a\u0004\b=\u0010>R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR\u001a\u0010\u000e\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bO\u0010L\"\u0004\bP\u0010NR\u001a\u0010\u000f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bQ\u0010L\"\u0004\bR\u0010NR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001a\u0010\u0012\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bW\u0010L\"\u0004\bX\u0010NR \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u0014X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b]\u0010Z\"\u0004\b^\u0010\\R \u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b_\u0010Z\"\u0004\b`\u0010\\R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\ba\u0010Z\"\u0004\bb\u0010\\R\u001a\u0010\u001a\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bc\u0010T\"\u0004\bd\u0010VR\u001a\u0010\u001b\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\be\u0010L\"\u0004\bf\u0010NR\u001a\u0010\u001c\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bg\u0010L\"\u0004\bh\u0010NR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001a\u0010\u001f\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bm\u0010L\"\u0004\bn\u0010NR\u001a\u0010 \u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bo\u0010L\"\u0004\bp\u0010NR\u001a\u0010!\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bq\u0010r\"\u0004\bs\u0010tR\u001a\u0010#\u001a\u00020\"X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bu\u0010r\"\u0004\bv\u0010tR\u001a\u0010$\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\bw\u0010L\"\u0004\bx\u0010NR \u0010%\u001a\b\u0012\u0004\u0012\u00020\r0&X\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u001b\u0010'\u001a\u00020(X\u0086\u000e¢\u0006\u000f\n��\u001a\u0004\b}\u0010~\"\u0005\b\u007f\u0010\u0080\u0001R$\u0010)\u001a\b\u0012\u0004\u0012\u00020\r0*X\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0006\b\u0083\u0001\u0010\u0084\u0001R \u0010+\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001\"\u0006\b\u0087\u0001\u0010\u0088\u0001R \u0010,\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\b\u0089\u0001\u0010\u0086\u0001\"\u0006\b\u008a\u0001\u0010\u0088\u0001R\u001c\u0010-\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b\u008b\u0001\u0010L\"\u0005\b\u008c\u0001\u0010NR\u001c\u0010.\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b\u008d\u0001\u0010L\"\u0005\b\u008e\u0001\u0010NR\u001c\u0010/\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b\u008f\u0001\u0010L\"\u0005\b\u0090\u0001\u0010NR\u001c\u00100\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b\u0091\u0001\u0010L\"\u0005\b\u0092\u0001\u0010NR\u001c\u00101\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b\u0093\u0001\u0010L\"\u0005\b\u0094\u0001\u0010NR\u001c\u00102\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b\u0095\u0001\u0010L\"\u0005\b\u0096\u0001\u0010NR\u001c\u00103\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b\u0097\u0001\u0010L\"\u0005\b\u0098\u0001\u0010NR\u001c\u00104\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b\u0099\u0001\u0010L\"\u0005\b\u009a\u0001\u0010NR\u001e\u00105\u001a\u000206X\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001\"\u0006\b\u009d\u0001\u0010\u009e\u0001R\u001e\u00109\u001a\u00020:X\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\b\u009f\u0001\u0010 \u0001\"\u0006\b¡\u0001\u0010¢\u0001R!\u0010£\u0001\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\b¤\u0001\u0010\u0086\u0001\"\u0006\b¥\u0001\u0010\u0088\u0001R+\u0010¦\u0001\u001a\u000e\u0012\u0007\u0012\u0005\u0018\u00010¨\u0001\u0018\u00010§\u0001X\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\b©\u0001\u0010ª\u0001\"\u0006\b«\u0001\u0010¬\u0001R\u001b\u0010\u00ad\u0001\u001a\t\u0012\u0004\u0012\u00020\u00150®\u0001¢\u0006\n\n��\u001a\u0006\b¯\u0001\u0010\u0082\u0001R\u0015\u0010°\u0001\u001a\u0004\u0018\u00010\t8F¢\u0006\u0007\u001a\u0005\b±\u0001\u0010HR\u0017\u0010²\u0001\u001a\u0005\u0018\u00010³\u00018F¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u0017\u0010¶\u0001\u001a\u0005\u0018\u00010³\u00018F¢\u0006\b\u001a\u0006\b·\u0001\u0010µ\u0001R\u001d\u0010¸\u0001\u001a\u00020\u000bX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b¹\u0001\u0010L\"\u0005\bº\u0001\u0010NR3\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\r\u0010»\u0001\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014@FX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\b¼\u0001\u0010Z\"\u0005\b½\u0001\u0010\\R/\u0010¿\u0001\u001a\t\u0012\u0005\u0012\u00030¾\u00010\u00142\u000e\u0010»\u0001\u001a\t\u0012\u0005\u0012\u00030¾\u00010\u0014@BX\u0086\u000e¢\u0006\t\n��\u001a\u0005\bÀ\u0001\u0010ZR6\u0010Á\u0001\u001a\b\u0012\u0004\u0012\u00020\u00150*2\r\u0010»\u0001\u001a\b\u0012\u0004\u0012\u00020\u00150*@FX\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\bÂ\u0001\u0010\u0082\u0001\"\u0006\bÃ\u0001\u0010\u0084\u0001R%\u0010Ä\u0001\u001a\b\u0012\u0004\u0012\u00020\r0*X\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\bÅ\u0001\u0010\u0082\u0001\"\u0006\bÆ\u0001\u0010\u0084\u0001R\u0015\u0010Ç\u0001\u001a\u00030³\u00018F¢\u0006\b\u001a\u0006\bÈ\u0001\u0010µ\u0001R)\u00107\u001a\u00020\r2\u0007\u0010»\u0001\u001a\u00020\r@FX\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\bÌ\u0001\u0010\u0086\u0001\"\u0006\bÍ\u0001\u0010\u0088\u0001R)\u00108\u001a\u00020\r2\u0007\u0010»\u0001\u001a\u00020\r@FX\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\bÎ\u0001\u0010\u0086\u0001\"\u0006\bÏ\u0001\u0010\u0088\u0001R$\u0010Ð\u0001\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u0015\n\u0003\u0010Ô\u0001\u001a\u0006\bÐ\u0001\u0010Ñ\u0001\"\u0006\bÒ\u0001\u0010Ó\u0001R*\u0010Õ\u0001\u001a\u00020\"2\u0007\u0010»\u0001\u001a\u00020\"8F@FX\u0086\u000e¢\u0006\u0010\n��\u001a\u0005\bÖ\u0001\u0010r\"\u0005\b×\u0001\u0010tR\u0019\u0010ß\u0001\u001a\b\u0012\u0004\u0012\u00020\r0&8F¢\u0006\u0007\u001a\u0005\bà\u0001\u0010zR\u0014\u0010á\u0001\u001a\u00020\u00158F¢\u0006\b\u001a\u0006\bâ\u0001\u0010ã\u0001R\"\u0010ä\u0001\u001a\u0004\u0018\u00010\u000b8VX\u0096\u0084\u0002¢\u0006\u0010\n\u0006\bæ\u0001\u0010ç\u0001\u001a\u0006\bå\u0001\u0010Ñ\u0001R/\u0010\f\u001a\u0004\u0018\u00010\r2\t\u0010»\u0001\u001a\u0004\u0018\u00010\r8F@FX\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\bè\u0001\u0010\u0086\u0001\"\u0006\bé\u0001\u0010\u0088\u0001R \u0010ê\u0001\u001a\u00030ë\u0001X\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\bì\u0001\u0010í\u0001\"\u0006\bî\u0001\u0010ï\u0001R,\u0010ñ\u0001\u001a\u00030ð\u00012\b\u0010»\u0001\u001a\u00030ð\u0001@FX\u0086\u000e¢\u0006\u0012\n��\u001a\u0006\bò\u0001\u0010ó\u0001\"\u0006\bô\u0001\u0010õ\u0001¨\u0006\u008b\u0002"}, d2 = {"Lcn/sast/api/config/MainConfig;", "", "sourceEncoding", "Ljava/nio/charset/Charset;", "monitor", "Lcn/sast/api/util/IMonitor;", "saConfig", "Lcn/sast/api/config/SaConfig;", "output_dir", "Lcn/sast/common/IResDirectory;", "dumpSootScene", "", "androidPlatformDir", "", "use_wrapper", "hideNoSource", "traverseMode", "Lcn/sast/common/FileSystemLocator$TraverseMode;", "useDefaultJavaClassPath", "processDir", "Lkotlinx/collections/immutable/PersistentSet;", "Lcn/sast/common/IResource;", "classpath", "sourcePath", "projectRoot", "autoAppClasses", "autoAppTraverseMode", "autoAppSrcInZipScheme", "skipClass", "incrementAnalyze", "Lcn/sast/api/incremental/IncrementalAnalyze;", "enableLineNumbers", "enableOriginalNames", "output_format", "", "throw_analysis", "process_multiple_dex", "appClasses", "", "src_precedence", "Lcn/sast/api/config/SrcPrecedence;", "ecj_options", "", "sunBootClassPath", "javaExtDirs", "hashAbspathInPlist", "deCompileIfNotExists", "enableCodeMetrics", "prepend_classpath", "whole_program", "no_bodies_for_excluded", "allow_phantom_refs", "enableReflection", "staticFieldTrackingMode", "Lcn/sast/api/config/StaticFieldTrackingMode;", "callGraphAlgorithm", "callGraphAlgorithmBuiltIn", "memoryThreshold", "", "<init>", "(Ljava/nio/charset/Charset;Lcn/sast/api/util/IMonitor;Lcn/sast/api/config/SaConfig;Lcn/sast/common/IResDirectory;ZLjava/lang/String;ZZLcn/sast/common/FileSystemLocator$TraverseMode;ZLkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentSet;Lcn/sast/common/FileSystemLocator$TraverseMode;ZZLcn/sast/api/incremental/IncrementalAnalyze;ZZIIZLjava/util/Set;Lcn/sast/api/config/SrcPrecedence;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZZZZZZZLcn/sast/api/config/StaticFieldTrackingMode;Ljava/lang/String;Ljava/lang/String;D)V", "getSourceEncoding", "()Ljava/nio/charset/Charset;", "getMonitor", "()Lcn/sast/api/util/IMonitor;", "setMonitor", "(Lcn/sast/api/util/IMonitor;)V", "getSaConfig", "()Lcn/sast/api/config/SaConfig;", "setSaConfig", "(Lcn/sast/api/config/SaConfig;)V", "getOutput_dir", "()Lcn/sast/common/IResDirectory;", "setOutput_dir", "(Lcn/sast/common/IResDirectory;)V", "getDumpSootScene", "()Z", "setDumpSootScene", "(Z)V", "getUse_wrapper", "setUse_wrapper", "getHideNoSource", "setHideNoSource", "getTraverseMode", "()Lcn/sast/common/FileSystemLocator$TraverseMode;", "setTraverseMode", "(Lcn/sast/common/FileSystemLocator$TraverseMode;)V", "getUseDefaultJavaClassPath", "setUseDefaultJavaClassPath", "getProcessDir", "()Lkotlinx/collections/immutable/PersistentSet;", "setProcessDir", "(Lkotlinx/collections/immutable/PersistentSet;)V", "getClasspath", "setClasspath", "getProjectRoot", "setProjectRoot", "getAutoAppClasses", "setAutoAppClasses", "getAutoAppTraverseMode", "setAutoAppTraverseMode", "getAutoAppSrcInZipScheme", "setAutoAppSrcInZipScheme", "getSkipClass", "setSkipClass", "getIncrementAnalyze", "()Lcn/sast/api/incremental/IncrementalAnalyze;", "setIncrementAnalyze", "(Lcn/sast/api/incremental/IncrementalAnalyze;)V", "getEnableLineNumbers", "setEnableLineNumbers", "getEnableOriginalNames", "setEnableOriginalNames", "getOutput_format", "()I", "setOutput_format", "(I)V", "getThrow_analysis", "setThrow_analysis", "getProcess_multiple_dex", "setProcess_multiple_dex", "getAppClasses", "()Ljava/util/Set;", "setAppClasses", "(Ljava/util/Set;)V", "getSrc_precedence", "()Lcn/sast/api/config/SrcPrecedence;", "setSrc_precedence", "(Lcn/sast/api/config/SrcPrecedence;)V", "getEcj_options", "()Ljava/util/List;", "setEcj_options", "(Ljava/util/List;)V", "getSunBootClassPath", "()Ljava/lang/String;", "setSunBootClassPath", "(Ljava/lang/String;)V", "getJavaExtDirs", "setJavaExtDirs", "getHashAbspathInPlist", "setHashAbspathInPlist", "getDeCompileIfNotExists", "setDeCompileIfNotExists", "getEnableCodeMetrics", "setEnableCodeMetrics", "getPrepend_classpath", "setPrepend_classpath", "getWhole_program", "setWhole_program", "getNo_bodies_for_excluded", "setNo_bodies_for_excluded", "getAllow_phantom_refs", "setAllow_phantom_refs", "getEnableReflection", "setEnableReflection", "getStaticFieldTrackingMode", "()Lcn/sast/api/config/StaticFieldTrackingMode;", "setStaticFieldTrackingMode", "(Lcn/sast/api/config/StaticFieldTrackingMode;)V", "getMemoryThreshold", "()D", "setMemoryThreshold", "(D)V", "version", "getVersion", "setVersion", "checkerInfo", "Lkotlin/Lazy;", "Lcn/sast/api/config/CheckerInfoGenResult;", "getCheckerInfo", "()Lkotlin/Lazy;", "setCheckerInfo", "(Lkotlin/Lazy;)V", "configDirs", "", "getConfigDirs", "checkerInfoDir", "getCheckerInfoDir", "checker_info_csv", "Lcn/sast/common/IResFile;", "getChecker_info_csv", "()Lcn/sast/common/IResFile;", "rule_sort_yaml", "getRule_sort_yaml", "apponly", "getApponly", "setApponly", "value", "getSourcePath", "setSourcePath", "Ljava/nio/file/FileSystem;", "sourcePathZFS", "getSourcePathZFS", "rootPathsForConvertRelativePath", "getRootPathsForConvertRelativePath", "setRootPathsForConvertRelativePath", "allResourcePathNormalized", "getAllResourcePathNormalized", "setAllResourcePathNormalized", "sqlite_report_db", "getSqlite_report_db", "skipInvalidClassPaths", "paths", "", "getCallGraphAlgorithm", "setCallGraphAlgorithm", "getCallGraphAlgorithmBuiltIn", "setCallGraphAlgorithmBuiltIn", "isAndroidScene", "()Ljava/lang/Boolean;", "setAndroidScene", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "parallelsNum", "getParallelsNum", "setParallelsNum", "get_expand_class_path", "get_soot_classpath", "getAndroidJarClasspath", "targetAPKFile", "isSkipClassSource", "path", "Ljava/nio/file/Path;", "soot_process_dir", "getSoot_process_dir", "soot_output_dir", "getSoot_output_dir", "()Lcn/sast/common/IResource;", "forceAndroidJar", "getForceAndroidJar", "forceAndroidJar$delegate", "Lkotlin/Lazy;", "getAndroidPlatformDir", "setAndroidPlatformDir", "scanFilter", "Lcn/sast/api/config/ScanFilter;", "getScanFilter", "()Lcn/sast/api/config/ScanFilter;", "setScanFilter", "(Lcn/sast/api/config/ScanFilter;)V", "Lcn/sast/api/config/ProjectConfig;", "projectConfig", "getProjectConfig", "()Lcn/sast/api/config/ProjectConfig;", "setProjectConfig", "(Lcn/sast/api/config/ProjectConfig;)V", "validate", "", "isEnable", "type", "Lcom/feysh/corax/config/api/CheckType;", "isAnyEnable", "", "([Lcom/feysh/corax/config/api/CheckType;)Z", "getRelative", "src", "tryGetRelativePathFromAbsolutePath", "Lcn/sast/api/config/MainConfig$RelativePath;", "abs", "tryGetRelativePath", "p", "simpleDeclIncrementalAnalysisFilter", "E", "targets", "InterProceduralIncrementalAnalysisFilter", "Companion", "RelativePath", "corax-api"})
@SourceDebugExtension({"SMAP\nMainConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MainConfig.kt\ncn/sast/api/config/MainConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,463:1\n1611#2,9:464\n1863#2:473\n1864#2:475\n1620#2:476\n774#2:477\n865#2,2:478\n1557#2:480\n1628#2,3:481\n1619#2:484\n1863#2:485\n1864#2:488\n1620#2:489\n1454#2,5:490\n1628#2,3:495\n1557#2:499\n1628#2,3:500\n1755#2,3:503\n1755#2,3:506\n1755#2,3:509\n774#2:514\n865#2,2:515\n774#2:517\n865#2,2:518\n1#3:474\n1#3:486\n1#3:487\n1#3:498\n12574#4,2:512\n*S KotlinDebug\n*F\n+ 1 MainConfig.kt\ncn/sast/api/config/MainConfig\n*L\n182#1:464,9\n182#1:473\n182#1:475\n182#1:476\n197#1:477\n197#1:478,2\n197#1:480\n197#1:481,3\n224#1:484\n224#1:485\n224#1:488\n224#1:489\n262#1:490,5\n270#1:495,3\n303#1:499\n303#1:500,3\n342#1:503,3\n343#1:506,3\n344#1:509,3\n405#1:514\n405#1:515,2\n412#1:517\n412#1:518,2\n182#1:474\n224#1:487\n352#1:512,2\n*E\n"})
/* loaded from: MainConfig.class */
public final class MainConfig {

    @NotNull
    private final Charset sourceEncoding;

    @Nullable
    private IMonitor monitor;

    @Nullable
    private SaConfig saConfig;

    @NotNull
    private IResDirectory output_dir;
    private boolean dumpSootScene;
    private boolean use_wrapper;
    private boolean hideNoSource;

    @NotNull
    private FileSystemLocator.TraverseMode traverseMode;
    private boolean useDefaultJavaClassPath;

    @NotNull
    private PersistentSet<? extends IResource> processDir;

    @NotNull
    private PersistentSet<String> classpath;

    @NotNull
    private PersistentSet<? extends IResource> projectRoot;

    @NotNull
    private PersistentSet<? extends IResource> autoAppClasses;

    @NotNull
    private FileSystemLocator.TraverseMode autoAppTraverseMode;
    private boolean autoAppSrcInZipScheme;
    private boolean skipClass;

    @Nullable
    private IncrementalAnalyze incrementAnalyze;
    private boolean enableLineNumbers;
    private boolean enableOriginalNames;
    private int output_format;
    private int throw_analysis;
    private boolean process_multiple_dex;

    @NotNull
    private Set<String> appClasses;

    @NotNull
    private SrcPrecedence src_precedence;

    @NotNull
    private List<String> ecj_options;

    @Nullable
    private String sunBootClassPath;

    @Nullable
    private String javaExtDirs;
    private boolean hashAbspathInPlist;
    private boolean deCompileIfNotExists;
    private boolean enableCodeMetrics;
    private boolean prepend_classpath;
    private boolean whole_program;
    private boolean no_bodies_for_excluded;
    private boolean allow_phantom_refs;
    private boolean enableReflection;

    @NotNull
    private StaticFieldTrackingMode staticFieldTrackingMode;
    private double memoryThreshold;

    @Nullable
    private String version;

    @Nullable
    private Lazy<CheckerInfoGenResult> checkerInfo;

    @NotNull
    private final List<IResource> configDirs;
    private boolean apponly;

    @NotNull
    private PersistentSet<? extends IResource> sourcePath;

    @NotNull
    private PersistentSet<? extends FileSystem> sourcePathZFS;

    @NotNull
    private List<? extends IResource> rootPathsForConvertRelativePath;

    @NotNull
    private List<String> allResourcePathNormalized;

    @NotNull
    private String callGraphAlgorithm;

    @NotNull
    private String callGraphAlgorithmBuiltIn;

    @Nullable
    private Boolean isAndroidScene;
    private int parallelsNum;

    @NotNull
    private final Lazy forceAndroidJar$delegate;

    @Nullable
    private String androidPlatformDir;

    @NotNull
    private ScanFilter scanFilter;

    @NotNull
    private ProjectConfig projectConfig;

    @NotNull
    public static final String CHECKER_INFO_CSV_NAME = "checker_info.csv";

    @NotNull
    public static final String RULE_SORT_YAML = "rule_sort.yaml";

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private static List<? extends Language> preferredLanguages = CollectionsKt.listOf(new Language[]{Language.ZH, Language.EN});

    @NotNull
    private static final KLogger logger = KotlinLogging.INSTANCE.logger(MainConfig::logger$lambda$22);

    @NotNull
    private static final Set<String> excludeFiles = SetsKt.setOf(new String[]{"classes-header.jar", "classes-turbine.jar"});

    public MainConfig() {
        this(null, null, null, null, false, null, false, false, null, false, null, null, null, null, null, null, false, false, null, false, false, 0, 0, false, null, null, null, null, null, false, false, false, false, false, false, false, false, null, null, null, 0.0d, -1, 511, null);
    }

    public MainConfig(@NotNull Charset sourceEncoding, @Nullable IMonitor monitor, @Nullable SaConfig saConfig, @NotNull IResDirectory output_dir, boolean dumpSootScene, @Nullable String androidPlatformDir, boolean use_wrapper, boolean hideNoSource, @NotNull FileSystemLocator.TraverseMode traverseMode, boolean useDefaultJavaClassPath, @NotNull PersistentSet<? extends IResource> persistentSet, @NotNull PersistentSet<String> persistentSet2, @NotNull PersistentSet<? extends IResource> persistentSet3, @NotNull PersistentSet<? extends IResource> persistentSet4, @NotNull PersistentSet<? extends IResource> persistentSet5, @NotNull FileSystemLocator.TraverseMode autoAppTraverseMode, boolean autoAppSrcInZipScheme, boolean skipClass, @Nullable IncrementalAnalyze incrementAnalyze, boolean enableLineNumbers, boolean enableOriginalNames, int output_format, int throw_analysis, boolean process_multiple_dex, @NotNull Set<String> set, @NotNull SrcPrecedence src_precedence, @NotNull List<String> list, @Nullable String sunBootClassPath, @Nullable String javaExtDirs, boolean hashAbspathInPlist, boolean deCompileIfNotExists, boolean enableCodeMetrics, boolean prepend_classpath, boolean whole_program, boolean no_bodies_for_excluded, boolean allow_phantom_refs, boolean enableReflection, @NotNull StaticFieldTrackingMode staticFieldTrackingMode, @NotNull String callGraphAlgorithm, @NotNull String callGraphAlgorithmBuiltIn, double memoryThreshold) {
        Intrinsics.checkNotNullParameter(sourceEncoding, "sourceEncoding");
        Intrinsics.checkNotNullParameter(output_dir, "output_dir");
        Intrinsics.checkNotNullParameter(traverseMode, "traverseMode");
        Intrinsics.checkNotNullParameter(persistentSet, "processDir");
        Intrinsics.checkNotNullParameter(persistentSet2, "classpath");
        Intrinsics.checkNotNullParameter(persistentSet3, "sourcePath");
        Intrinsics.checkNotNullParameter(persistentSet4, "projectRoot");
        Intrinsics.checkNotNullParameter(persistentSet5, "autoAppClasses");
        Intrinsics.checkNotNullParameter(autoAppTraverseMode, "autoAppTraverseMode");
        Intrinsics.checkNotNullParameter(set, "appClasses");
        Intrinsics.checkNotNullParameter(src_precedence, "src_precedence");
        Intrinsics.checkNotNullParameter(list, "ecj_options");
        Intrinsics.checkNotNullParameter(staticFieldTrackingMode, "staticFieldTrackingMode");
        Intrinsics.checkNotNullParameter(callGraphAlgorithm, "callGraphAlgorithm");
        Intrinsics.checkNotNullParameter(callGraphAlgorithmBuiltIn, "callGraphAlgorithmBuiltIn");
        this.sourceEncoding = sourceEncoding;
        this.monitor = monitor;
        this.saConfig = saConfig;
        this.output_dir = output_dir;
        this.dumpSootScene = dumpSootScene;
        this.use_wrapper = use_wrapper;
        this.hideNoSource = hideNoSource;
        this.traverseMode = traverseMode;
        this.useDefaultJavaClassPath = useDefaultJavaClassPath;
        this.processDir = persistentSet;
        this.classpath = persistentSet2;
        this.projectRoot = persistentSet4;
        this.autoAppClasses = persistentSet5;
        this.autoAppTraverseMode = autoAppTraverseMode;
        this.autoAppSrcInZipScheme = autoAppSrcInZipScheme;
        this.skipClass = skipClass;
        this.incrementAnalyze = incrementAnalyze;
        this.enableLineNumbers = enableLineNumbers;
        this.enableOriginalNames = enableOriginalNames;
        this.output_format = output_format;
        this.throw_analysis = throw_analysis;
        this.process_multiple_dex = process_multiple_dex;
        this.appClasses = set;
        this.src_precedence = src_precedence;
        this.ecj_options = list;
        this.sunBootClassPath = sunBootClassPath;
        this.javaExtDirs = javaExtDirs;
        this.hashAbspathInPlist = hashAbspathInPlist;
        this.deCompileIfNotExists = deCompileIfNotExists;
        this.enableCodeMetrics = enableCodeMetrics;
        this.prepend_classpath = prepend_classpath;
        this.whole_program = whole_program;
        this.no_bodies_for_excluded = no_bodies_for_excluded;
        this.allow_phantom_refs = allow_phantom_refs;
        this.enableReflection = enableReflection;
        this.staticFieldTrackingMode = staticFieldTrackingMode;
        this.memoryThreshold = memoryThreshold;
        this.configDirs = new ArrayList();
        this.sourcePath = ExtensionsKt.persistentSetOf();
        this.sourcePathZFS = ExtensionsKt.persistentSetOf();
        this.rootPathsForConvertRelativePath = CollectionsKt.emptyList();
        this.allResourcePathNormalized = CollectionsKt.emptyList();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = callGraphAlgorithm.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this.callGraphAlgorithm = lowerCase;
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
        String lowerCase2 = callGraphAlgorithmBuiltIn.toLowerCase(locale2);
        Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
        this.callGraphAlgorithmBuiltIn = lowerCase2;
        this.isAndroidScene = false;
        this.parallelsNum = Math.max(OS.INSTANCE.getMaxThreadNum() - 1, 1);
        this.forceAndroidJar$delegate = LazyKt.lazy(() -> {
            return forceAndroidJar_delegate$lambda$12(r1);
        });
        this.androidPlatformDir = androidPlatformDir;
        this.scanFilter = new ScanFilter(this, null, 2, null);
        this.projectConfig = new ProjectConfig((ProcessRegex) null, 1, (DefaultConstructorMarker) null);
        setSourcePath(persistentSet3);
        this.scanFilter.update(this.projectConfig);
    }

    public /* synthetic */ MainConfig(Charset charset, IMonitor iMonitor, SaConfig saConfig, IResDirectory iResDirectory, boolean z, String str, boolean z2, boolean z3, FileSystemLocator.TraverseMode traverseMode, boolean z4, PersistentSet persistentSet, PersistentSet persistentSet2, PersistentSet persistentSet3, PersistentSet persistentSet4, PersistentSet persistentSet5, FileSystemLocator.TraverseMode traverseMode2, boolean z5, boolean z6, IncrementalAnalyze incrementalAnalyze, boolean z7, boolean z8, int i, int i2, boolean z9, Set set, SrcPrecedence srcPrecedence, List list, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, StaticFieldTrackingMode staticFieldTrackingMode, String str4, String str5, double d, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? Charsets.UTF_8 : charset, (i3 & 2) != 0 ? null : iMonitor, (i3 & 4) != 0 ? null : saConfig, (i3 & 8) != 0 ? Resource.INSTANCE.dirOf("out/test-out") : iResDirectory, (i3 & 16) != 0 ? false : z, (i3 & 32) != 0 ? null : str, (i3 & 64) != 0 ? true : z2, (i3 & 128) != 0 ? false : z3, (i3 & 256) != 0 ? FileSystemLocator.TraverseMode.RecursivelyIndexArchive : traverseMode, (i3 & 512) != 0 ? true : z4, (i3 & 1024) != 0 ? ExtensionsKt.persistentSetOf() : persistentSet, (i3 & 2048) != 0 ? ExtensionsKt.persistentSetOf() : persistentSet2, (i3 & 4096) != 0 ? ExtensionsKt.persistentSetOf() : persistentSet3, (i3 & 8192) != 0 ? ExtensionsKt.persistentSetOf() : persistentSet4, (i3 & 16384) != 0 ? ExtensionsKt.persistentSetOf() : persistentSet5, (i3 & 32768) != 0 ? FileSystemLocator.TraverseMode.RecursivelyIndexArchive : traverseMode2, (i3 & 65536) != 0 ? true : z5, (i3 & 131072) != 0 ? false : z6, (i3 & 262144) != 0 ? null : incrementalAnalyze, (i3 & 524288) != 0 ? true : z7, (i3 & 1048576) != 0 ? true : z8, (i3 & 2097152) != 0 ? 14 : i, (i3 & 4194304) != 0 ? 3 : i2, (i3 & 8388608) != 0 ? true : z9, (i3 & 16777216) != 0 ? SetsKt.emptySet() : set, (i3 & 33554432) != 0 ? SrcPrecedence.prec_apk_class_jimple : srcPrecedence, (i3 & 67108864) != 0 ? CollectionsKt.emptyList() : list, (i3 & 134217728) != 0 ? System.getProperty("sun.boot.class.path") : str2, (i3 & 268435456) != 0 ? System.getProperty("java.ext.dirs") : str3, (i3 & 536870912) != 0 ? false : z10, (i3 & 1073741824) != 0 ? true : z11, (i3 & Integer.MIN_VALUE) != 0 ? true : z12, (i4 & 1) != 0 ? false : z13, (i4 & 2) != 0 ? true : z14, (i4 & 4) != 0 ? true : z15, (i4 & 8) != 0 ? true : z16, (i4 & 16) != 0 ? true : z17, (i4 & 32) != 0 ? StaticFieldTrackingMode.ContextFlowSensitive : staticFieldTrackingMode, (i4 & 64) != 0 ? "insens" : str4, (i4 & 128) != 0 ? "cha" : str5, (i4 & 256) != 0 ? 0.9d : d);
    }

    @NotNull
    public final Charset getSourceEncoding() {
        return this.sourceEncoding;
    }

    @Nullable
    public final IMonitor getMonitor() {
        return this.monitor;
    }

    public final void setMonitor(@Nullable IMonitor iMonitor) {
        this.monitor = iMonitor;
    }

    @Nullable
    public final SaConfig getSaConfig() {
        return this.saConfig;
    }

    public final void setSaConfig(@Nullable SaConfig saConfig) {
        this.saConfig = saConfig;
    }

    @NotNull
    public final IResDirectory getOutput_dir() {
        return this.output_dir;
    }

    public final void setOutput_dir(@NotNull IResDirectory iResDirectory) {
        Intrinsics.checkNotNullParameter(iResDirectory, "<set-?>");
        this.output_dir = iResDirectory;
    }

    public final boolean getDumpSootScene() {
        return this.dumpSootScene;
    }

    public final void setDumpSootScene(boolean z) {
        this.dumpSootScene = z;
    }

    public final boolean getUse_wrapper() {
        return this.use_wrapper;
    }

    public final void setUse_wrapper(boolean z) {
        this.use_wrapper = z;
    }

    public final boolean getHideNoSource() {
        return this.hideNoSource;
    }

    public final void setHideNoSource(boolean z) {
        this.hideNoSource = z;
    }

    @NotNull
    public final FileSystemLocator.TraverseMode getTraverseMode() {
        return this.traverseMode;
    }

    public final void setTraverseMode(@NotNull FileSystemLocator.TraverseMode traverseMode) {
        Intrinsics.checkNotNullParameter(traverseMode, "<set-?>");
        this.traverseMode = traverseMode;
    }

    public final boolean getUseDefaultJavaClassPath() {
        return this.useDefaultJavaClassPath;
    }

    public final void setUseDefaultJavaClassPath(boolean z) {
        this.useDefaultJavaClassPath = z;
    }

    @NotNull
    public final PersistentSet<IResource> getProcessDir() {
        return this.processDir;
    }

    public final void setProcessDir(@NotNull PersistentSet<? extends IResource> persistentSet) {
        Intrinsics.checkNotNullParameter(persistentSet, "<set-?>");
        this.processDir = persistentSet;
    }

    @NotNull
    public final PersistentSet<String> getClasspath() {
        return this.classpath;
    }

    public final void setClasspath(@NotNull PersistentSet<String> persistentSet) {
        Intrinsics.checkNotNullParameter(persistentSet, "<set-?>");
        this.classpath = persistentSet;
    }

    @NotNull
    public final PersistentSet<IResource> getProjectRoot() {
        return this.projectRoot;
    }

    public final void setProjectRoot(@NotNull PersistentSet<? extends IResource> persistentSet) {
        Intrinsics.checkNotNullParameter(persistentSet, "<set-?>");
        this.projectRoot = persistentSet;
    }

    @NotNull
    public final PersistentSet<IResource> getAutoAppClasses() {
        return this.autoAppClasses;
    }

    public final void setAutoAppClasses(@NotNull PersistentSet<? extends IResource> persistentSet) {
        Intrinsics.checkNotNullParameter(persistentSet, "<set-?>");
        this.autoAppClasses = persistentSet;
    }

    @NotNull
    public final FileSystemLocator.TraverseMode getAutoAppTraverseMode() {
        return this.autoAppTraverseMode;
    }

    public final void setAutoAppTraverseMode(@NotNull FileSystemLocator.TraverseMode traverseMode) {
        Intrinsics.checkNotNullParameter(traverseMode, "<set-?>");
        this.autoAppTraverseMode = traverseMode;
    }

    public final boolean getAutoAppSrcInZipScheme() {
        return this.autoAppSrcInZipScheme;
    }

    public final void setAutoAppSrcInZipScheme(boolean z) {
        this.autoAppSrcInZipScheme = z;
    }

    public final boolean getSkipClass() {
        return this.skipClass;
    }

    public final void setSkipClass(boolean z) {
        this.skipClass = z;
    }

    @Nullable
    public final IncrementalAnalyze getIncrementAnalyze() {
        return this.incrementAnalyze;
    }

    public final void setIncrementAnalyze(@Nullable IncrementalAnalyze incrementalAnalyze) {
        this.incrementAnalyze = incrementalAnalyze;
    }

    public final boolean getEnableLineNumbers() {
        return this.enableLineNumbers;
    }

    public final void setEnableLineNumbers(boolean z) {
        this.enableLineNumbers = z;
    }

    public final boolean getEnableOriginalNames() {
        return this.enableOriginalNames;
    }

    public final void setEnableOriginalNames(boolean z) {
        this.enableOriginalNames = z;
    }

    public final int getOutput_format() {
        return this.output_format;
    }

    public final void setOutput_format(int i) {
        this.output_format = i;
    }

    public final int getThrow_analysis() {
        return this.throw_analysis;
    }

    public final void setThrow_analysis(int i) {
        this.throw_analysis = i;
    }

    public final boolean getProcess_multiple_dex() {
        return this.process_multiple_dex;
    }

    public final void setProcess_multiple_dex(boolean z) {
        this.process_multiple_dex = z;
    }

    @NotNull
    public final Set<String> getAppClasses() {
        return this.appClasses;
    }

    public final void setAppClasses(@NotNull Set<String> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.appClasses = set;
    }

    @NotNull
    public final SrcPrecedence getSrc_precedence() {
        return this.src_precedence;
    }

    public final void setSrc_precedence(@NotNull SrcPrecedence srcPrecedence) {
        Intrinsics.checkNotNullParameter(srcPrecedence, "<set-?>");
        this.src_precedence = srcPrecedence;
    }

    @NotNull
    public final List<String> getEcj_options() {
        return this.ecj_options;
    }

    public final void setEcj_options(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.ecj_options = list;
    }

    @Nullable
    public final String getSunBootClassPath() {
        return this.sunBootClassPath;
    }

    public final void setSunBootClassPath(@Nullable String str) {
        this.sunBootClassPath = str;
    }

    @Nullable
    public final String getJavaExtDirs() {
        return this.javaExtDirs;
    }

    public final void setJavaExtDirs(@Nullable String str) {
        this.javaExtDirs = str;
    }

    public final boolean getHashAbspathInPlist() {
        return this.hashAbspathInPlist;
    }

    public final void setHashAbspathInPlist(boolean z) {
        this.hashAbspathInPlist = z;
    }

    public final boolean getDeCompileIfNotExists() {
        return this.deCompileIfNotExists;
    }

    public final void setDeCompileIfNotExists(boolean z) {
        this.deCompileIfNotExists = z;
    }

    public final boolean getEnableCodeMetrics() {
        return this.enableCodeMetrics;
    }

    public final void setEnableCodeMetrics(boolean z) {
        this.enableCodeMetrics = z;
    }

    public final boolean getPrepend_classpath() {
        return this.prepend_classpath;
    }

    public final void setPrepend_classpath(boolean z) {
        this.prepend_classpath = z;
    }

    public final boolean getWhole_program() {
        return this.whole_program;
    }

    public final void setWhole_program(boolean z) {
        this.whole_program = z;
    }

    public final boolean getNo_bodies_for_excluded() {
        return this.no_bodies_for_excluded;
    }

    public final void setNo_bodies_for_excluded(boolean z) {
        this.no_bodies_for_excluded = z;
    }

    public final boolean getAllow_phantom_refs() {
        return this.allow_phantom_refs;
    }

    public final void setAllow_phantom_refs(boolean z) {
        this.allow_phantom_refs = z;
    }

    public final boolean getEnableReflection() {
        return this.enableReflection;
    }

    public final void setEnableReflection(boolean z) {
        this.enableReflection = z;
    }

    @NotNull
    public final StaticFieldTrackingMode getStaticFieldTrackingMode() {
        return this.staticFieldTrackingMode;
    }

    public final void setStaticFieldTrackingMode(@NotNull StaticFieldTrackingMode staticFieldTrackingMode) {
        Intrinsics.checkNotNullParameter(staticFieldTrackingMode, "<set-?>");
        this.staticFieldTrackingMode = staticFieldTrackingMode;
    }

    public final double getMemoryThreshold() {
        return this.memoryThreshold;
    }

    public final void setMemoryThreshold(double d) {
        this.memoryThreshold = d;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(@Nullable String str) {
        this.version = str;
    }

    @Nullable
    public final Lazy<CheckerInfoGenResult> getCheckerInfo() {
        return this.checkerInfo;
    }

    public final void setCheckerInfo(@Nullable Lazy<CheckerInfoGenResult> lazy) {
        this.checkerInfo = lazy;
    }

    @NotNull
    public final List<IResource> getConfigDirs() {
        return this.configDirs;
    }

    @Nullable
    public final IResDirectory getCheckerInfoDir() {
        return MainConfigKt.checkerInfoDir(this.configDirs, false);
    }

    @Nullable
    public final IResFile getChecker_info_csv() {
        IResDirectory checkerInfoDir = getCheckerInfoDir();
        if (checkerInfoDir != null) {
            IResource iResourceResolve = checkerInfoDir.resolve(CHECKER_INFO_CSV_NAME);
            if (iResourceResolve != null) {
                return iResourceResolve.toFile();
            }
        }
        return null;
    }

    @Nullable
    public final IResFile getRule_sort_yaml() {
        IResDirectory checkerInfoDir = getCheckerInfoDir();
        if (checkerInfoDir != null) {
            IResource iResourceResolve = checkerInfoDir.resolve(RULE_SORT_YAML);
            if (iResourceResolve != null) {
                return iResourceResolve.toFile();
            }
        }
        return null;
    }

    public final boolean getApponly() {
        return this.apponly;
    }

    public final void setApponly(boolean z) {
        this.apponly = z;
    }

    @NotNull
    public final PersistentSet<IResource> getSourcePath() {
        return this.sourcePath;
    }

    public final void setSourcePath(@NotNull PersistentSet<? extends IResource> persistentSet) {
        FileSystem zipFileSystem;
        Intrinsics.checkNotNullParameter(persistentSet, "value");
        this.sourcePath = persistentSet;
        Iterable $this$mapNotNull$iv = (Iterable) persistentSet;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            IResource it = (IResource) element$iv$iv$iv;
            if (it.getZipLike()) {
                try {
                    zipFileSystem = Resource.INSTANCE.getZipFileSystem(it.getPath());
                } catch (Exception e) {
                    zipFileSystem = null;
                }
            } else {
                zipFileSystem = null;
            }
            if (zipFileSystem != null) {
                destination$iv$iv.add(zipFileSystem);
            }
        }
        this.sourcePathZFS = ExtensionsKt.toPersistentSet((List) destination$iv$iv);
    }

    @NotNull
    public final PersistentSet<FileSystem> getSourcePathZFS() {
        return this.sourcePathZFS;
    }

    @NotNull
    public final List<IResource> getRootPathsForConvertRelativePath() {
        return this.rootPathsForConvertRelativePath;
    }

    public final void setRootPathsForConvertRelativePath(@NotNull List<? extends IResource> list) {
        Intrinsics.checkNotNullParameter(list, "value");
        List<? extends IResource> $this$filter$iv = list;
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            IResource it = (IResource) element$iv$iv;
            if (it.isDirectory() || it.getZipLike()) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $this$map$iv = (List) destination$iv$iv;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv2.add(((IResource) item$iv$iv).getAbsolute().getNormalize().toString());
        }
        this.allResourcePathNormalized = CollectionsKt.toList(new LinkedHashSet((List) destination$iv$iv2));
        this.rootPathsForConvertRelativePath = list;
    }

    @NotNull
    public final List<String> getAllResourcePathNormalized() {
        return this.allResourcePathNormalized;
    }

    public final void setAllResourcePathNormalized(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.allResourcePathNormalized = list;
    }

    @NotNull
    public final IResFile getSqlite_report_db() {
        return this.output_dir.resolve("sqlite").resolve("sqlite_report_coraxjava.db").toFile();
    }

    /* compiled from: MainConfig.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��.\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n��\n\u0002\u0010\"\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��R0\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b@FX\u0086\u000e¢\u0006\u000e\n��\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n��R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012¢\u0006\b\n��\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcn/sast/api/config/MainConfig$Companion;", "", "<init>", "()V", "CHECKER_INFO_CSV_NAME", "", "RULE_SORT_YAML", "value", "", "Lcom/feysh/corax/config/api/Language;", "preferredLanguages", "getPreferredLanguages", "()Ljava/util/List;", "setPreferredLanguages", "(Ljava/util/List;)V", "logger", "Lmu/KLogger;", "excludeFiles", "", "getExcludeFiles", "()Ljava/util/Set;", "corax-api"})
    @SourceDebugExtension({"SMAP\nMainConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MainConfig.kt\ncn/sast/api/config/MainConfig$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,463:1\n1#2:464\n*E\n"})
    /* loaded from: MainConfig$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final List<Language> getPreferredLanguages() {
            return MainConfig.preferredLanguages;
        }

        public final void setPreferredLanguages(@NotNull List<? extends Language> list) {
            Intrinsics.checkNotNullParameter(list, "value");
            List<? extends Language> list2 = list;
            MainConfig.preferredLanguages = list2.isEmpty() ? CollectionsKt.listOf(new Language[]{Language.ZH, Language.EN}) : list2;
        }

        @NotNull
        public final Set<String> getExcludeFiles() {
            return MainConfig.excludeFiles;
        }
    }

    private static final Unit logger$lambda$22() {
        return Unit.INSTANCE;
    }

    @NotNull
    public final Set<String> skipInvalidClassPaths(@NotNull Collection<String> collection) {
        String str;
        Intrinsics.checkNotNullParameter(collection, "paths");
        Collection<String> $this$mapNotNullTo$iv = collection;
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object element$iv$iv : $this$mapNotNullTo$iv) {
            String path = (String) element$iv$iv;
            if (isSkipClassSource(path)) {
                logger.info("Exclude class path: " + path);
                str = null;
            } else {
                try {
                    IResource it = Resource.INSTANCE.of(path);
                    IResource zipLikeFile = it.isFile() && it.getZipLike() ? it : null;
                    if (zipLikeFile == null) {
                        str = path;
                    } else {
                        try {
                            Resource.INSTANCE.getEntriesUnsafe(zipLikeFile.getPath());
                            str = path;
                        } catch (Exception e) {
                            logger.error("skip the invalid archive file: " + zipLikeFile + " e: " + e.getMessage());
                            str = null;
                        }
                    }
                } catch (Exception e2) {
                    str = path;
                }
            }
            if (str != null) {
                destination$iv.add(str);
            }
        }
        return (Set) destination$iv;
    }

    @NotNull
    public final String getCallGraphAlgorithm() {
        return this.callGraphAlgorithm;
    }

    public final void setCallGraphAlgorithm(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = value.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this.callGraphAlgorithm = lowerCase;
    }

    @NotNull
    public final String getCallGraphAlgorithmBuiltIn() {
        return this.callGraphAlgorithmBuiltIn;
    }

    public final void setCallGraphAlgorithmBuiltIn(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = value.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        this.callGraphAlgorithmBuiltIn = lowerCase;
    }

    @Nullable
    public final Boolean isAndroidScene() {
        return this.isAndroidScene;
    }

    public final void setAndroidScene(@Nullable Boolean bool) {
        this.isAndroidScene = bool;
    }

    public final void setParallelsNum(int value) {
        if (value > 0) {
            this.parallelsNum = value;
        }
    }

    public final int getParallelsNum() {
        return this.parallelsNum <= 0 ? OS.INSTANCE.getMaxThreadNum() : this.parallelsNum;
    }

    @NotNull
    public final Set<IResource> get_expand_class_path() throws IOException {
        if (!this.apponly) {
            Iterable $this$flatMapTo$iv = this.classpath;
            Collection destination$iv = (Set) new LinkedHashSet();
            for (Object element$iv : $this$flatMapTo$iv) {
                String it = (String) element$iv;
                Iterable iterableGlobPaths = ResourceImplKt.globPaths(it);
                if (iterableGlobPaths == null) {
                    throw new IllegalStateException(("classpath option: \"" + it + "\" is invalid or target not exists").toString());
                }
                Iterable list$iv = iterableGlobPaths;
                CollectionsKt.addAll(destination$iv, list$iv);
            }
            return (Set) destination$iv;
        }
        return SetsKt.emptySet();
    }

    @NotNull
    public final Set<String> get_soot_classpath() throws IOException {
        Iterable $this$mapTo$iv = get_expand_class_path();
        Collection destination$iv = (Set) new LinkedHashSet();
        for (Object item$iv : $this$mapTo$iv) {
            IResource it = (IResource) item$iv;
            destination$iv.add(it.expandRes(this.output_dir).getAbsolutePath());
        }
        Set cps = (Set) destination$iv;
        String javaClasspath = Scene.defaultJavaClassPath();
        if (this.useDefaultJavaClassPath) {
            Intrinsics.checkNotNull(javaClasspath);
            if (javaClasspath.length() > 0) {
                cps.addAll(StringsKt.split$default(javaClasspath, new char[]{File.pathSeparatorChar}, false, 0, 6, (Object) null));
            }
        }
        return skipInvalidClassPaths(cps);
    }

    @Nullable
    public final String getAndroidJarClasspath(@NotNull String targetAPKFile) {
        Intrinsics.checkNotNullParameter(targetAPKFile, "targetAPKFile");
        Boolean forceAndroidJar = getForceAndroidJar();
        if (forceAndroidJar == null) {
            return null;
        }
        boolean it = forceAndroidJar.booleanValue();
        String androidJar = it ? getAndroidPlatformDir() : Scene.v().getAndroidJarPath(getAndroidPlatformDir(), targetAPKFile);
        String str = androidJar;
        if (!(str == null || str.length() == 0)) {
            return androidJar;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public final boolean isSkipClassSource(@NotNull Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return this.scanFilter.getActionOfClassPath("Process", path, null) == ProcessRule.ScanAction.Skip;
    }

    public final boolean isSkipClassSource(@NotNull String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        try {
            Path it = Path.of(path, new String[0]);
            Intrinsics.checkNotNull(it);
            LinkOption[] linkOptionArr = new LinkOption[0];
            Path path2 = Files.exists(it, (LinkOption[]) Arrays.copyOf(linkOptionArr, linkOptionArr.length)) ? it : null;
            if (path2 == null) {
                return false;
            }
            Path cp = path2;
            return isSkipClassSource(cp);
        } catch (Exception e) {
            return false;
        }
    }

    @NotNull
    public final Set<String> getSoot_process_dir() {
        Iterable $this$map$iv = SetsKt.plus(this.processDir, this.autoAppClasses);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            IResource it = (IResource) item$iv$iv;
            destination$iv$iv.add(it.expandRes(this.output_dir).getAbsolutePath());
        }
        return CollectionsKt.toSet(skipInvalidClassPaths((List) destination$iv$iv));
    }

    @NotNull
    public final IResource getSoot_output_dir() {
        return this.output_dir.resolve("sootOutPut");
    }

    @Nullable
    public Boolean getForceAndroidJar() {
        return (Boolean) this.forceAndroidJar$delegate.getValue();
    }

    private static final Boolean forceAndroidJar_delegate$lambda$12(MainConfig this$0) {
        String platformDir = this$0.getAndroidPlatformDir();
        if (platformDir == null) {
            return null;
        }
        if (platformDir.length() == 0) {
            throw new RuntimeException("Android platform directory is empty");
        }
        File f = new File(platformDir);
        if (!f.exists()) {
            throw new IllegalArgumentException("androidPlatformDir not exist".toString());
        }
        return Boolean.valueOf(f.isFile());
    }

    @Nullable
    public final String getAndroidPlatformDir() {
        return Intrinsics.areEqual(this.androidPlatformDir, "ANDROID_JARS") ? System.getenv("ANDROID_JARS") : this.androidPlatformDir;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setAndroidPlatformDir(@org.jetbrains.annotations.Nullable java.lang.String r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r0.androidPlatformDir = r1
            r0 = r5
            if (r0 == 0) goto L1d
            r0 = r5
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L19
            r0 = 1
            goto L1a
        L19:
            r0 = 0
        L1a:
            if (r0 == 0) goto L21
        L1d:
            r0 = 1
            goto L22
        L21:
            r0 = 0
        L22:
            if (r0 != 0) goto L35
            java.lang.String r0 = "Check failed."
            r6 = r0
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r1 = r0
            r2 = r6
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r0
        L35:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sast.api.config.MainConfig.setAndroidPlatformDir(java.lang.String):void");
    }

    @NotNull
    public final ScanFilter getScanFilter() {
        return this.scanFilter;
    }

    public final void setScanFilter(@NotNull ScanFilter scanFilter) {
        Intrinsics.checkNotNullParameter(scanFilter, "<set-?>");
        this.scanFilter = scanFilter;
    }

    @NotNull
    public final ProjectConfig getProjectConfig() {
        return this.projectConfig;
    }

    public final void setProjectConfig(@NotNull ProjectConfig value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.projectConfig = value;
        this.scanFilter.update(value);
    }

    public final void validate() {
        boolean z;
        boolean z2;
        boolean z3;
        Iterable $this$any$iv = this.classpath;
        if (!($this$any$iv instanceof Collection) || !((Collection) $this$any$iv).isEmpty()) {
            Iterator it = $this$any$iv.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                Object element$iv = it.next();
                String it2 = (String) element$iv;
                if (it2.length() == 0) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
        }
        if (!(!z)) {
            throw new IllegalStateException("classpath has empty string".toString());
        }
        Iterable $this$any$iv2 = this.processDir;
        if (!($this$any$iv2 instanceof Collection) || !((Collection) $this$any$iv2).isEmpty()) {
            Iterator it3 = $this$any$iv2.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z2 = false;
                    break;
                }
                Object element$iv2 = it3.next();
                IResource it4 = (IResource) element$iv2;
                if (it4.toString().length() == 0) {
                    z2 = true;
                    break;
                }
            }
        } else {
            z2 = false;
        }
        if (!(!z2)) {
            throw new IllegalStateException("processDir has empty string".toString());
        }
        Iterable $this$any$iv3 = this.sourcePath;
        if (!($this$any$iv3 instanceof Collection) || !((Collection) $this$any$iv3).isEmpty()) {
            Iterator it5 = $this$any$iv3.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    z3 = false;
                    break;
                }
                Object element$iv3 = it5.next();
                IResource it6 = (IResource) element$iv3;
                if (it6.toString().length() == 0) {
                    z3 = true;
                    break;
                }
            }
        } else {
            z3 = false;
        }
        if (!(!z3)) {
            throw new IllegalStateException("sourcePath has empty string".toString());
        }
    }

    public final boolean isEnable(@NotNull CheckType type) {
        Intrinsics.checkNotNullParameter(type, "type");
        SaConfig saConfig = this.saConfig;
        boolean z = (saConfig == null || saConfig.isEnable(type)) ? false : true;
        return !z;
    }

    public final boolean isAnyEnable(@NotNull CheckType... type) {
        Intrinsics.checkNotNullParameter(type, "type");
        for (CheckType checkType : type) {
            if (isEnable(checkType)) {
                return true;
            }
        }
        return false;
    }

    private final String getRelative(String src, String path) {
        String strSubstring;
        String pathString = path.substring(src.length());
        Intrinsics.checkNotNullExpressionValue(pathString, "substring(...)");
        String pathString2 = StringsKt.replace$default(StringsKt.replace$default(pathString, "\\", "/", false, 4, (Object) null), "//", "/", false, 4, (Object) null);
        if (!StringsKt.startsWith$default(pathString2, "/", false, 2, (Object) null)) {
            if (StringsKt.startsWith$default(pathString2, "!/", false, 2, (Object) null)) {
                strSubstring = pathString2.substring(1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            } else {
                strSubstring = "/" + pathString2;
            }
            pathString2 = strSubstring;
        }
        return pathString2;
    }

    /* compiled from: MainConfig.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n��\n\u0002\u0010��\n��\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018�� \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0010\u001a\u00020��2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n��\u001a\u0004\b\t\u0010\bR\u0011\u0010\n\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u0011\u0010\f\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\r\u0010\b¨\u0006\u0019"}, d2 = {"Lcn/sast/api/config/MainConfig$RelativePath;", "Lcom/feysh/corax/config/api/IRelativePath;", "prefix", "", "relativePath", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getPrefix", "()Ljava/lang/String;", "getRelativePath", "identifier", "getIdentifier", "absoluteNormalizePath", "getAbsoluteNormalizePath", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "corax-api"})
    /* loaded from: MainConfig$RelativePath.class */
    public static final class RelativePath implements IRelativePath {

        @NotNull
        public static final Companion Companion = new Companion(null);

        @NotNull
        private final String prefix;

        @NotNull
        private final String relativePath;

        @NotNull
        private static final Set<String> prefixes;

        @NotNull
        public final String component1() {
            return this.prefix;
        }

        @NotNull
        public final String component2() {
            return this.relativePath;
        }

        @NotNull
        public final RelativePath copy(@NotNull String prefix, @NotNull String relativePath) {
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            return new RelativePath(prefix, relativePath);
        }

        public static /* synthetic */ RelativePath copy$default(RelativePath relativePath, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = relativePath.prefix;
            }
            if ((i & 2) != 0) {
                str2 = relativePath.relativePath;
            }
            return relativePath.copy(str, str2);
        }

        @NotNull
        public String toString() {
            return "RelativePath(prefix=" + this.prefix + ", relativePath=" + this.relativePath + ")";
        }

        public int hashCode() {
            int result = this.prefix.hashCode();
            return (result * 31) + this.relativePath.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RelativePath)) {
                return false;
            }
            RelativePath relativePath = (RelativePath) other;
            return Intrinsics.areEqual(this.prefix, relativePath.prefix) && Intrinsics.areEqual(this.relativePath, relativePath.relativePath);
        }

        public RelativePath(@NotNull String prefix, @NotNull String relativePath) {
            Intrinsics.checkNotNullParameter(prefix, "prefix");
            Intrinsics.checkNotNullParameter(relativePath, "relativePath");
            this.prefix = prefix;
            this.relativePath = relativePath;
            if (getPrefix().length() > 0) {
                prefixes.add(getPrefix());
            }
        }

        @NotNull
        public String getPrefix() {
            return this.prefix;
        }

        @NotNull
        public String getRelativePath() {
            return this.relativePath;
        }

        @NotNull
        public final String getIdentifier() {
            return Companion.m18short(getPrefix()) + getRelativePath();
        }

        @NotNull
        public final String getAbsoluteNormalizePath() {
            return getPrefix() + getRelativePath();
        }

        /* compiled from: MainConfig.kt */
        @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001c\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0003\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n��\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcn/sast/api/config/MainConfig$RelativePath$Companion;", "", "<init>", "()V", "short", "", "prefix", "prefixes", "", "getPrefixes", "()Ljava/util/Set;", "corax-api"})
        /* loaded from: MainConfig$RelativePath$Companion.class */
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
                this();
            }

            private Companion() {
            }

            @NotNull
            /* renamed from: short, reason: not valid java name */
            public final String m18short(@NotNull String prefix) {
                Intrinsics.checkNotNullParameter(prefix, "prefix");
                int i = StringsKt.lastIndexOfAny$default(prefix, CollectionsKt.listOf(new String[]{"/", "\\"}), 0, false, 6, (Object) null);
                if (i == -1) {
                    return prefix;
                }
                String strSubstring = prefix.substring(i + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                return strSubstring;
            }

            @NotNull
            public final Set<String> getPrefixes() {
                return RelativePath.prefixes;
            }
        }

        static {
            Set<String> setSynchronizedSet = Collections.synchronizedSet(new LinkedHashSet());
            Intrinsics.checkNotNullExpressionValue(setSynchronizedSet, "synchronizedSet(...)");
            prefixes = setSynchronizedSet;
        }
    }

    @NotNull
    public final RelativePath tryGetRelativePathFromAbsolutePath(@NotNull String abs) {
        Intrinsics.checkNotNullParameter(abs, "abs");
        for (String src : this.allResourcePathNormalized) {
            if (StringsKt.startsWith$default(abs, src, false, 2, (Object) null)) {
                String nSrc = StringsKt.removeSuffix(StringsKt.replace$default(StringsKt.replace$default(src, "\\", "/", false, 4, (Object) null), "//", "/", false, 4, (Object) null), "/");
                return new RelativePath(nSrc, getRelative(src, abs));
            }
        }
        return new RelativePath("", StringsKt.replace$default(StringsKt.replace$default(abs, "\\", "/", false, 4, (Object) null), "//", "/", false, 4, (Object) null));
    }

    @NotNull
    public final RelativePath tryGetRelativePathFromAbsolutePath(@NotNull IResource abs) {
        Intrinsics.checkNotNullParameter(abs, "abs");
        return tryGetRelativePathFromAbsolutePath(abs.toString());
    }

    @NotNull
    public final RelativePath tryGetRelativePath(@NotNull IResource p) {
        Intrinsics.checkNotNullParameter(p, "p");
        return tryGetRelativePathFromAbsolutePath(p.getAbsolute().getNormalize());
    }

    @NotNull
    public final RelativePath tryGetRelativePath(@NotNull Path p) {
        Intrinsics.checkNotNullParameter(p, "p");
        return tryGetRelativePath(Resource.INSTANCE.of(p));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final <E> Collection<E> simpleDeclIncrementalAnalysisFilter(@NotNull Collection<? extends E> collection) {
        IncrementalAnalyzeByChangeFiles.SimpleDeclAnalysisDependsGraph dg;
        Intrinsics.checkNotNullParameter(collection, "targets");
        IncrementalAnalyze incrementalAnalyze = this.incrementAnalyze;
        IncrementalAnalyzeByChangeFiles changeFileBasedIncAnalysis = incrementalAnalyze instanceof IncrementalAnalyzeByChangeFiles ? (IncrementalAnalyzeByChangeFiles) incrementalAnalyze : null;
        if (changeFileBasedIncAnalysis == null || (dg = changeFileBasedIncAnalysis.getSimpleDeclAnalysisDependsGraph()) == null) {
            return collection;
        }
        Collection<? extends E> $this$filter$iv = collection;
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (dg.shouldReAnalyzeTarget(element$iv$iv) != ProcessRule.ScanAction.Skip) {
                arrayList.add(element$iv$iv);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final <E> Collection<E> InterProceduralIncrementalAnalysisFilter(@NotNull Collection<? extends E> collection) {
        IncrementalAnalyzeByChangeFiles.InterProceduralAnalysisDependsGraph dg;
        Intrinsics.checkNotNullParameter(collection, "targets");
        IncrementalAnalyze incrementalAnalyze = this.incrementAnalyze;
        IncrementalAnalyzeByChangeFiles changeFileBasedIncAnalysis = incrementalAnalyze instanceof IncrementalAnalyzeByChangeFiles ? (IncrementalAnalyzeByChangeFiles) incrementalAnalyze : null;
        if (changeFileBasedIncAnalysis == null || (dg = changeFileBasedIncAnalysis.getInterProceduralAnalysisDependsGraph()) == null) {
            return collection;
        }
        Collection<? extends E> $this$filter$iv = collection;
        ArrayList arrayList = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            if (dg.shouldReAnalyzeTarget(element$iv$iv) != ProcessRule.ScanAction.Skip) {
                arrayList.add(element$iv$iv);
            }
        }
        return arrayList;
    }
}
