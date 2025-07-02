package cn.sast.framework.report;

import app.cash.sqldelight.db.SqlDriver;
import app.cash.sqldelight.driver.jdbc.ConnectionManager;
import app.cash.sqldelight.driver.jdbc.JdbcDriver;
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver;
import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import cn.sast.framework.report.sqldelight.Database;
import cn.sast.framework.report.sqlite.JdbcSqliteDriverDelegate;
import com.feysh.corax.commons.Kotlin_extKt;
import java.io.Closeable;
import java.util.Properties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

/* compiled from: SqliteDiagnostics.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n��\n\u0002\u0018\u0002\n��\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018�� \u00102\u00020\u0001:\u0001\u0010B)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0006\u0010\u000f\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n��R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n��\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n��¨\u0006\u0011"}, d2 = {"Lcn/sast/framework/report/SQLiteDB;", "Ljava/io/Closeable;", "dbPath", "", "driver", "Lapp/cash/sqldelight/driver/jdbc/JdbcDriver;", "database", "Lcn/sast/framework/report/sqldelight/Database;", "journalMode", "<init>", "(Ljava/lang/String;Lapp/cash/sqldelight/driver/jdbc/JdbcDriver;Lcn/sast/framework/report/sqldelight/Database;Ljava/lang/String;)V", "getDatabase", "()Lcn/sast/framework/report/sqldelight/Database;", "close", "", "createSchema", "Companion", "corax-framework"})
@SourceDebugExtension({"SMAP\nSqliteDiagnostics.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SqliteDiagnostics.kt\ncn/sast/framework/report/SQLiteDB\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,813:1\n1#2:814\n*E\n"})
/* loaded from: SQLiteDB.class */
public final class SQLiteDB implements Closeable {

    @NotNull
    public static final Companion Companion = new Companion(null);

    @NotNull
    private final String dbPath;

    @NotNull
    private final JdbcDriver driver;

    @NotNull
    private final Database database;

    @NotNull
    private final String journalMode;

    public /* synthetic */ SQLiteDB(String dbPath, JdbcDriver driver, Database database, String journalMode, DefaultConstructorMarker $constructor_marker) {
        this(dbPath, driver, database, journalMode);
    }

    private SQLiteDB(String dbPath, JdbcDriver driver, Database database, String journalMode) {
        this.dbPath = dbPath;
        this.driver = driver;
        this.database = database;
        this.journalMode = journalMode;
    }

    @NotNull
    public final Database getDatabase() {
        return this.database;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.driver.close();
        if (!Intrinsics.areEqual(this.journalMode, "OFF")) {
            SQLiteDB sQLiteDBOpenDataBase = SqliteDiagnostics.Companion.openDataBase(this.dbPath, "OFF");
            try {
                SQLiteDB sQLiteDB = sQLiteDBOpenDataBase;
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(sQLiteDBOpenDataBase, (Throwable) null);
            } catch (Throwable th) {
                CloseableKt.closeFinally(sQLiteDBOpenDataBase, (Throwable) null);
                throw th;
            }
        }
    }

    public final void createSchema() {
        Database.Companion.getSchema().create(this.driver);
    }

    /* compiled from: SqliteDiagnostics.kt */
    @Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u001e\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0086\u0003\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t²\u0006\n\u0010\n\u001a\u00020\u000bX\u008a\u0084\u0002"}, d2 = {"Lcn/sast/framework/report/SQLiteDB$Companion;", "", "<init>", "()V", "openDataBase", "Lcn/sast/framework/report/SQLiteDB;", "dbPath", "", "journalMode", "corax-framework", "$$delegate_0", "Lapp/cash/sqldelight/driver/jdbc/ConnectionManager;"})
    /* loaded from: SQLiteDB$Companion.class */
    public static final class Companion {
        static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property0(new PropertyReference0Impl(Companion.class, "$$delegate_0", "<v#0>", 0))};

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        private Companion() {
        }

        @NotNull
        public final SQLiteDB openDataBase(@NotNull String dbPath, @NotNull String journalMode) {
            Intrinsics.checkNotNullParameter(dbPath, "dbPath");
            Intrinsics.checkNotNullParameter(journalMode, "journalMode");
            String url = "jdbc:sqlite:" + dbPath;
            Properties $this$openDataBase_u24lambda_u240 = new Properties();
            $this$openDataBase_u24lambda_u240.put("SQLITE_THREADSAFE", "2");
            $this$openDataBase_u24lambda_u240.put("SQLITE_OPEN_FULLMUTEX", "true");
            $this$openDataBase_u24lambda_u240.put("journal_mode", journalMode);
            JdbcSqliteDriver baseDriver = new JdbcSqliteDriver(url, $this$openDataBase_u24lambda_u240);
            ReadWriteProperty $$delegate_0$delegate = (ReadWriteProperty) Kotlin_extKt.delegateField$default(baseDriver, (Class) null, 1, (Object) null).provideDelegate((Object) null, $$delegatedProperties[0]);
            JdbcSqliteDriverDelegate driver = new JdbcSqliteDriverDelegate(openDataBase$lambda$1($$delegate_0$delegate));
            Database database = Database.Companion.invoke((SqlDriver) driver);
            return new SQLiteDB(dbPath, driver, database, journalMode, null);
        }

        private static final ConnectionManager openDataBase$lambda$1(ReadWriteProperty<Object, ConnectionManager> readWriteProperty) {
            return (ConnectionManager) readWriteProperty.getValue((Object) null, $$delegatedProperties[0]);
        }
    }
}
