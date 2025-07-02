package cn.sast.api.report;

import cn.sast.api.config.MainConfig;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import com.feysh.corax.config.api.BugMessage;
import com.feysh.corax.config.api.CheckType;
import com.feysh.corax.config.api.Language;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* compiled from: Report.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = 2, xi = 48, d1 = {"��H\n��\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0012\n��\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\n\u0010\t\u001a\u00020\b*\u00020\u0006\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\u0006\u001a\u001a\u0010\f\u001a\u00020\b*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001e\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b0\u0012*\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011\u001a/\u0010\u0016\u001a\u0002H\u0017\"\u0004\b��\u0010\u0017*\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u0002H\u00170\u00122\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0019¢\u0006\u0002\u0010\u001a\"\u0015\u0010\u0013\u001a\u00020\b*\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015*0\u0010��\"\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001¢\u0006\u0002\b\u00042\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001¢\u0006\u0002\b\u0004¨\u0006\u001b"}, d2 = {"BugPathEventLazyGen", "Lkotlin/Function1;", "Lcn/sast/api/report/BugPathEventEnvironment;", "Lcn/sast/api/report/BugPathEvent;", "Lkotlin/ExtensionFunctionType;", "md5", "", "str", "", "toHex", "xor2Int", "", "bugMessage", "Lcom/feysh/corax/config/api/CheckType;", "lang", "Lcom/feysh/corax/config/api/Language;", "env", "Lcom/feysh/corax/config/api/BugMessage$Env;", "", "perfectName", "getPerfectName", "(Lcom/feysh/corax/config/api/CheckType;)Ljava/lang/String;", "preferredMessage", "T", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Map;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "corax-api"})
@SourceDebugExtension({"SMAP\nReport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Report.kt\ncn/sast/api/report/ReportKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,451:1\n12834#2,3:452\n9326#2,2:456\n9476#2,4:458\n1#3:455\n*S KotlinDebug\n*F\n+ 1 Report.kt\ncn/sast/api/report/ReportKt\n*L\n236#1:452,3\n410#1:456,2\n410#1:458,4\n*E\n"})
/* loaded from: ReportKt.class */
public final class ReportKt {
    @NotNull
    public static final byte[] md5(@NotNull String str) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(str, "str");
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return bArrDigest;
    }

    @NotNull
    public static final String toHex(@NotNull byte[] $this$toHex) {
        Intrinsics.checkNotNullParameter($this$toHex, "<this>");
        return ArraysKt.joinToString$default($this$toHex, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (v0) -> {
            return toHex$lambda$0(v0);
        }, 30, (Object) null);
    }

    private static final CharSequence toHex$lambda$0(byte b) {
        Object[] objArr = {Byte.valueOf(b)};
        String str = String.format("%02x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public static final int xor2Int(@NotNull byte[] $this$xor2Int) {
        Intrinsics.checkNotNullParameter($this$xor2Int, "<this>");
        int accumulator$iv = 123;
        for (byte element$iv : $this$xor2Int) {
            int acc = accumulator$iv;
            accumulator$iv = (acc << 8) ^ element$iv;
        }
        return accumulator$iv;
    }

    @NotNull
    public static final String bugMessage(@NotNull CheckType $this$bugMessage, @NotNull Language lang, @NotNull BugMessage.Env env) {
        Function1 it;
        Intrinsics.checkNotNullParameter($this$bugMessage, "<this>");
        Intrinsics.checkNotNullParameter(lang, "lang");
        Intrinsics.checkNotNullParameter(env, "env");
        BugMessage bugMessage = (BugMessage) $this$bugMessage.getBugMessage().get(lang);
        if (bugMessage != null && (it = bugMessage.getMsg()) != null) {
            String str = (String) it.invoke(env);
            if (str != null) {
                return str;
            }
        }
        return lang + " not exists of checkType: " + $this$bugMessage;
    }

    @NotNull
    public static final Map<Language, String> bugMessage(@NotNull CheckType $this$bugMessage, @NotNull BugMessage.Env env) {
        Intrinsics.checkNotNullParameter($this$bugMessage, "<this>");
        Intrinsics.checkNotNullParameter(env, "env");
        Language[] languageArrValues = Language.values();
        LinkedHashMap result$iv = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(languageArrValues.length), 16));
        for (Language language : languageArrValues) {
            result$iv.put(language, bugMessage($this$bugMessage, language, env));
        }
        return result$iv;
    }

    @NotNull
    public static final String getPerfectName(@NotNull CheckType $this$perfectName) {
        Intrinsics.checkNotNullParameter($this$perfectName, "<this>");
        return $this$perfectName.getChecker().getClass().getSimpleName() + "." + $this$perfectName.getClass().getSimpleName();
    }

    public static final <T> T preferredMessage(@NotNull Map<Language, ? extends T> map, @NotNull Function0<? extends T> function0) {
        T t;
        Intrinsics.checkNotNullParameter(map, "<this>");
        Intrinsics.checkNotNullParameter(function0, "defaultValue");
        Iterator<T> it = MainConfig.Companion.getPreferredLanguages().iterator();
        while (true) {
            if (!it.hasNext()) {
                t = null;
                break;
            }
            T t2 = map.get((Language) it.next());
            if (t2 != null) {
                t = t2;
                break;
            }
        }
        return t == null ? (T) function0.invoke() : t;
    }
}
