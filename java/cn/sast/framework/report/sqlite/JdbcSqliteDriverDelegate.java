package cn.sast.framework.report.sqlite;

import app.cash.sqldelight.Query;
import app.cash.sqldelight.driver.jdbc.ConnectionManager;
import app.cash.sqldelight.driver.jdbc.JdbcDriver;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: JdbcSqliteDriverDelegate.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u0002\n��\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018�� %2\u00020\u00012\u00020\u0002:\u0001%B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\u000e\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0011\"\u00020\n2\u0006\u0010\u0012\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0013J)\u0010\u0014\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0011\"\u00020\n2\u0006\u0010\u0012\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\u0013J!\u0010\u0015\u001a\u00020\u000f2\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\u0011\"\u00020\nH\u0016¢\u0006\u0002\u0010\u0016J\f\u0010\u0017\u001a\u00020\u000f*\u00020\u0018H\u0016J\r\u0010\u0019\u001a\u00020\u000f*\u00020\u0018H\u0096\u0001J\t\u0010\u001a\u001a\u00020\u000fH\u0096\u0001J\u0011\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u0018H\u0096\u0001J\t\u0010\u001d\u001a\u00020\u0018H\u0096\u0001J\r\u0010\u001e\u001a\u00020\u000f*\u00020\u0018H\u0096\u0001R\u0014\u0010\u0003\u001a\u00020\u0002X\u0084\u0004¢\u0006\b\n��\u001a\u0004\b\u0006\u0010\u0007R6\u0010\b\u001a*\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\tj\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b`\rX\u0082\u0004¢\u0006\u0002\n��R\u001a\u0010\u001f\u001a\u0004\u0018\u00010 X\u0096\u000f¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006&"}, d2 = {"Lcn/sast/framework/report/sqlite/JdbcSqliteDriverDelegate;", "Lapp/cash/sqldelight/driver/jdbc/JdbcDriver;", "Lapp/cash/sqldelight/driver/jdbc/ConnectionManager;", "delegate", "<init>", "(Lapp/cash/sqldelight/driver/jdbc/ConnectionManager;)V", "getDelegate", "()Lapp/cash/sqldelight/driver/jdbc/ConnectionManager;", "listeners", "Ljava/util/LinkedHashMap;", "", "", "Lapp/cash/sqldelight/Query$Listener;", "Lkotlin/collections/LinkedHashMap;", "addListener", "", "queryKeys", "", "listener", "([Ljava/lang/String;Lapp/cash/sqldelight/Query$Listener;)V", "removeListener", "notifyListeners", "([Ljava/lang/String;)V", "endTransaction", "Ljava/sql/Connection;", "beginTransaction", "close", "closeConnection", "connection", "getConnection", "rollbackTransaction", "transaction", "Lapp/cash/sqldelight/driver/jdbc/ConnectionManager$Transaction;", "getTransaction", "()Lapp/cash/sqldelight/driver/jdbc/ConnectionManager$Transaction;", "setTransaction", "(Lapp/cash/sqldelight/driver/jdbc/ConnectionManager$Transaction;)V", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nJdbcSqliteDriverDelegate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JdbcSqliteDriverDelegate.kt\ncn/sast/framework/report/sqlite/JdbcSqliteDriverDelegate\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,49:1\n13409#2:50\n13410#2:58\n13409#2,2:59\n13409#2:61\n13410#2:63\n381#3,7:51\n1#4:62\n1863#5,2:64\n*S KotlinDebug\n*F\n+ 1 JdbcSqliteDriverDelegate.kt\ncn/sast/framework/report/sqlite/JdbcSqliteDriverDelegate\n*L\n17#1:50\n17#1:58\n25#1:59,2\n34#1:61\n34#1:63\n18#1:51,7\n36#1:64,2\n*E\n"})
/* loaded from: JdbcSqliteDriverDelegate.class */
public class JdbcSqliteDriverDelegate extends JdbcDriver implements ConnectionManager {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final ConnectionManager delegate;

    @NotNull
    private final LinkedHashMap<String, Set<Query.Listener>> listeners;

    @NotNull
    public static final String IN_MEMORY = "jdbc:sqlite:";

    public void close() {
        this.delegate.close();
    }

    public void beginTransaction(@NotNull Connection $this$beginTransaction) {
        Intrinsics.checkNotNullParameter($this$beginTransaction, "<this>");
        this.delegate.beginTransaction($this$beginTransaction);
    }

    public void rollbackTransaction(@NotNull Connection $this$rollbackTransaction) {
        Intrinsics.checkNotNullParameter($this$rollbackTransaction, "<this>");
        this.delegate.rollbackTransaction($this$rollbackTransaction);
    }

    @Nullable
    public ConnectionManager.Transaction getTransaction() {
        return this.delegate.getTransaction();
    }

    public void setTransaction(@Nullable ConnectionManager.Transaction value) {
        this.delegate.setTransaction(value);
    }

    public void closeConnection(@NotNull Connection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        this.delegate.closeConnection(connection);
    }

    @NotNull
    public Connection getConnection() {
        return this.delegate.getConnection();
    }

    @NotNull
    protected final ConnectionManager getDelegate() {
        return this.delegate;
    }

    public JdbcSqliteDriverDelegate(@NotNull ConnectionManager delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        this.listeners = new LinkedHashMap<>();
    }

    public void addListener(@NotNull String[] queryKeys, @NotNull Query.Listener listener) {
        Object obj;
        Intrinsics.checkNotNullParameter(queryKeys, "queryKeys");
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.listeners) {
            for (String str : queryKeys) {
                Map $this$getOrPut$iv = this.listeners;
                Object value$iv = $this$getOrPut$iv.get(str);
                if (value$iv == null) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    $this$getOrPut$iv.put(str, linkedHashSet);
                    obj = linkedHashSet;
                } else {
                    obj = value$iv;
                }
                ((Set) obj).add(listener);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public void removeListener(@NotNull String[] queryKeys, @NotNull Query.Listener listener) {
        Intrinsics.checkNotNullParameter(queryKeys, "queryKeys");
        Intrinsics.checkNotNullParameter(listener, "listener");
        synchronized (this.listeners) {
            for (String str : queryKeys) {
                Set<Query.Listener> set = this.listeners.get(str);
                if (set != null) {
                    set.remove(listener);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public void notifyListeners(@NotNull String... queryKeys) {
        Intrinsics.checkNotNullParameter(queryKeys, "queryKeys");
        LinkedHashSet listenersToNotify = new LinkedHashSet();
        synchronized (this.listeners) {
            for (String str : queryKeys) {
                Set<Query.Listener> set = this.listeners.get(str);
                if (set != null) {
                    listenersToNotify.addAll(set);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        LinkedHashSet $this$forEach$iv = listenersToNotify;
        for (Object element$iv : $this$forEach$iv) {
            Query.Listener p0 = (Query.Listener) element$iv;
            p0.queryResultsChanged();
        }
    }

    public void endTransaction(@NotNull Connection $this$endTransaction) {
        Intrinsics.checkNotNullParameter($this$endTransaction, "<this>");
        ConnectionManager $this$endTransaction_u24lambda_u248 = this.delegate;
        $this$endTransaction_u24lambda_u248.endTransaction($this$endTransaction);
        this.delegate.setTransaction((ConnectionManager.Transaction) null);
        closeConnection($this$endTransaction);
    }

    /* compiled from: JdbcSqliteDriverDelegate.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u000e\n��\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n��¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/report/sqlite/JdbcSqliteDriverDelegate$Companion;", "", "<init>", "()V", "IN_MEMORY", "", "corax-framework"})
    /* loaded from: JdbcSqliteDriverDelegate$Companion.class */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }
    }
}
