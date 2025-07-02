package cn.sast.api.config;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.utbot.common.AbstractSettings;

/* compiled from: ExtSettings.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��4\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\bA\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010\u0006\u001a\u00020\u0005H\u0007R+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR+\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u000f\u001a\u0004\b\u0011\u0010\u000b\"\u0004\b\u0012\u0010\rR+\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u000f\u001a\u0004\b\u0015\u0010\u000b\"\u0004\b\u0016\u0010\rR+\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u0019\u0010\u000b\"\u0004\b\u001a\u0010\rR+\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\"\u0010\u000f\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R+\u0010#\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010\u000f\u001a\u0004\b$\u0010\u001f\"\u0004\b%\u0010!R+\u0010(\u001a\u00020'2\u0006\u0010\u0007\u001a\u00020'8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b-\u0010\u000f\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R+\u0010.\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b1\u0010\u000f\u001a\u0004\b/\u0010\u000b\"\u0004\b0\u0010\rR+\u00102\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b5\u0010\u000f\u001a\u0004\b3\u0010\u001f\"\u0004\b4\u0010!R+\u00106\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b9\u0010\u000f\u001a\u0004\b7\u0010\u000b\"\u0004\b8\u0010\rR+\u0010:\u001a\u00020\u001c2\u0006\u0010\u0007\u001a\u00020\u001c8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b=\u0010\u000f\u001a\u0004\b;\u0010\u001f\"\u0004\b<\u0010!R+\u0010?\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bD\u0010\u000f\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR+\u0010E\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010\u000f\u001a\u0004\bF\u0010A\"\u0004\bG\u0010CR+\u0010I\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bL\u0010\u000f\u001a\u0004\bJ\u0010A\"\u0004\bK\u0010CR+\u0010M\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bP\u0010\u000f\u001a\u0004\bN\u0010\u000b\"\u0004\bO\u0010\rR+\u0010Q\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bT\u0010\u000f\u001a\u0004\bR\u0010A\"\u0004\bS\u0010CR+\u0010U\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bX\u0010\u000f\u001a\u0004\bV\u0010A\"\u0004\bW\u0010CR+\u0010Y\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\\\u0010\u000f\u001a\u0004\bZ\u0010A\"\u0004\b[\u0010CR+\u0010]\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bb\u0010\u000f\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR+\u0010c\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bf\u0010\u000f\u001a\u0004\bd\u0010\u000b\"\u0004\be\u0010\rR+\u0010g\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bj\u0010\u000f\u001a\u0004\bh\u0010\u000b\"\u0004\bi\u0010\rR+\u0010k\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bn\u0010\u000f\u001a\u0004\bl\u0010\u000b\"\u0004\bm\u0010\rR+\u0010o\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\br\u0010\u000f\u001a\u0004\bp\u0010A\"\u0004\bq\u0010CR+\u0010s\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bv\u0010\u000f\u001a\u0004\bt\u0010A\"\u0004\bu\u0010CR+\u0010w\u001a\u00020>2\u0006\u0010\u0007\u001a\u00020>8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bz\u0010\u000f\u001a\u0004\bx\u0010A\"\u0004\by\u0010CR+\u0010{\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b~\u0010\u000f\u001a\u0004\b|\u0010\u000b\"\u0004\b}\u0010\r¨\u0006\u007f"}, d2 = {"Lcn/sast/api/config/ExtSettings;", "Lorg/utbot/common/AbstractSettings;", "<init>", "()V", "defaultSettingsPath", "", "getPath", "<set-?>", "", "dataFlowIteratorCountForAppClasses", "getDataFlowIteratorCountForAppClasses", "()I", "setDataFlowIteratorCountForAppClasses", "(I)V", "dataFlowIteratorCountForAppClasses$delegate", "Lkotlin/properties/ReadWriteProperty;", "dataFlowIteratorCountForLibClasses", "getDataFlowIteratorCountForLibClasses", "setDataFlowIteratorCountForLibClasses", "dataFlowIteratorCountForLibClasses$delegate", "dataFlowIteratorIsFixPointSizeLimit", "getDataFlowIteratorIsFixPointSizeLimit", "setDataFlowIteratorIsFixPointSizeLimit", "dataFlowIteratorIsFixPointSizeLimit$delegate", "dataFlowMethodUnitsSizeLimit", "getDataFlowMethodUnitsSizeLimit", "setDataFlowMethodUnitsSizeLimit", "dataFlowMethodUnitsSizeLimit$delegate", "", "dataFlowCacheExpireAfterAccess", "getDataFlowCacheExpireAfterAccess", "()J", "setDataFlowCacheExpireAfterAccess", "(J)V", "dataFlowCacheExpireAfterAccess$delegate", "dataFlowCacheMaximumWeight", "getDataFlowCacheMaximumWeight", "setDataFlowCacheMaximumWeight", "dataFlowCacheMaximumWeight$delegate", "", "dataFlowCacheMaximumSizeFactor", "getDataFlowCacheMaximumSizeFactor", "()D", "setDataFlowCacheMaximumSizeFactor", "(D)V", "dataFlowCacheMaximumSizeFactor$delegate", "calleeDepChainMaxNumForLibClassesInInterProceduraldataFlow", "getCalleeDepChainMaxNumForLibClassesInInterProceduraldataFlow", "setCalleeDepChainMaxNumForLibClassesInInterProceduraldataFlow", "calleeDepChainMaxNumForLibClassesInInterProceduraldataFlow$delegate", "dataFlowInterProceduralCalleeDepChainMaxNum", "getDataFlowInterProceduralCalleeDepChainMaxNum", "setDataFlowInterProceduralCalleeDepChainMaxNum", "dataFlowInterProceduralCalleeDepChainMaxNum$delegate", "dataFlowInterProceduralCalleeTimeOut", "getDataFlowInterProceduralCalleeTimeOut", "setDataFlowInterProceduralCalleeTimeOut", "dataFlowInterProceduralCalleeTimeOut$delegate", "dataFlowResolveTargetsMaxNum", "getDataFlowResolveTargetsMaxNum", "setDataFlowResolveTargetsMaxNum", "dataFlowResolveTargetsMaxNum$delegate", "", "dataFlowResultPathOnlyStmt", "getDataFlowResultPathOnlyStmt", "()Z", "setDataFlowResultPathOnlyStmt", "(Z)V", "dataFlowResultPathOnlyStmt$delegate", "enableProcessBar", "getEnableProcessBar", "setEnableProcessBar", "enableProcessBar$delegate", "showMetadata", "getShowMetadata", "setShowMetadata", "showMetadata$delegate", "tabSize", "getTabSize", "setTabSize", "tabSize$delegate", "dumpCompleteDotCg", "getDumpCompleteDotCg", "setDumpCompleteDotCg", "dumpCompleteDotCg$delegate", "prettyPrintJsonReport", "getPrettyPrintJsonReport", "setPrettyPrintJsonReport", "prettyPrintJsonReport$delegate", "prettyPrintPlistReport", "getPrettyPrintPlistReport", "setPrettyPrintPlistReport", "prettyPrintPlistReport$delegate", "sqliteJournalMode", "getSqliteJournalMode", "()Ljava/lang/String;", "setSqliteJournalMode", "(Ljava/lang/String;)V", "sqliteJournalMode$delegate", "jdCoreDecompileTimeOut", "getJdCoreDecompileTimeOut", "setJdCoreDecompileTimeOut", "jdCoreDecompileTimeOut$delegate", "skip_large_class_by_maximum_methods", "getSkip_large_class_by_maximum_methods", "setSkip_large_class_by_maximum_methods", "skip_large_class_by_maximum_methods$delegate", "skip_large_class_by_maximum_fields", "getSkip_large_class_by_maximum_fields", "setSkip_large_class_by_maximum_fields", "skip_large_class_by_maximum_fields$delegate", "castNeverFailsOfPhantomClass", "getCastNeverFailsOfPhantomClass", "setCastNeverFailsOfPhantomClass", "castNeverFailsOfPhantomClass$delegate", "printAliasInfo", "getPrintAliasInfo", "setPrintAliasInfo", "printAliasInfo$delegate", "UseRoaringPointsToSet", "getUseRoaringPointsToSet", "setUseRoaringPointsToSet", "UseRoaringPointsToSet$delegate", "hashVersion", "getHashVersion", "setHashVersion", "hashVersion$delegate", "corax-api"})
/* loaded from: ExtSettings.class */
public final class ExtSettings extends AbstractSettings {

    @NotNull
    private static final ReadWriteProperty dataFlowIteratorCountForAppClasses$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowIteratorCountForLibClasses$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowIteratorIsFixPointSizeLimit$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowMethodUnitsSizeLimit$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowCacheExpireAfterAccess$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowCacheMaximumWeight$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowCacheMaximumSizeFactor$delegate;

    @NotNull
    private static final ReadWriteProperty calleeDepChainMaxNumForLibClassesInInterProceduraldataFlow$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowInterProceduralCalleeDepChainMaxNum$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowInterProceduralCalleeTimeOut$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowResolveTargetsMaxNum$delegate;

    @NotNull
    private static final ReadWriteProperty dataFlowResultPathOnlyStmt$delegate;

    @NotNull
    private static final ReadWriteProperty enableProcessBar$delegate;

    @NotNull
    private static final ReadWriteProperty showMetadata$delegate;

    @NotNull
    private static final ReadWriteProperty tabSize$delegate;

    @NotNull
    private static final ReadWriteProperty dumpCompleteDotCg$delegate;

    @NotNull
    private static final ReadWriteProperty prettyPrintJsonReport$delegate;

    @NotNull
    private static final ReadWriteProperty prettyPrintPlistReport$delegate;

    @NotNull
    private static final ReadWriteProperty sqliteJournalMode$delegate;

    @NotNull
    private static final ReadWriteProperty jdCoreDecompileTimeOut$delegate;

    @NotNull
    private static final ReadWriteProperty skip_large_class_by_maximum_methods$delegate;

    @NotNull
    private static final ReadWriteProperty skip_large_class_by_maximum_fields$delegate;

    @NotNull
    private static final ReadWriteProperty castNeverFailsOfPhantomClass$delegate;

    @NotNull
    private static final ReadWriteProperty printAliasInfo$delegate;

    @NotNull
    private static final ReadWriteProperty UseRoaringPointsToSet$delegate;

    @NotNull
    private static final ReadWriteProperty hashVersion$delegate;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowIteratorCountForAppClasses", "getDataFlowIteratorCountForAppClasses()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowIteratorCountForLibClasses", "getDataFlowIteratorCountForLibClasses()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowIteratorIsFixPointSizeLimit", "getDataFlowIteratorIsFixPointSizeLimit()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowMethodUnitsSizeLimit", "getDataFlowMethodUnitsSizeLimit()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowCacheExpireAfterAccess", "getDataFlowCacheExpireAfterAccess()J", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowCacheMaximumWeight", "getDataFlowCacheMaximumWeight()J", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowCacheMaximumSizeFactor", "getDataFlowCacheMaximumSizeFactor()D", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "calleeDepChainMaxNumForLibClassesInInterProceduraldataFlow", "getCalleeDepChainMaxNumForLibClassesInInterProceduraldataFlow()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowInterProceduralCalleeDepChainMaxNum", "getDataFlowInterProceduralCalleeDepChainMaxNum()J", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowInterProceduralCalleeTimeOut", "getDataFlowInterProceduralCalleeTimeOut()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowResolveTargetsMaxNum", "getDataFlowResolveTargetsMaxNum()J", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dataFlowResultPathOnlyStmt", "getDataFlowResultPathOnlyStmt()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "enableProcessBar", "getEnableProcessBar()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "showMetadata", "getShowMetadata()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "tabSize", "getTabSize()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "dumpCompleteDotCg", "getDumpCompleteDotCg()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "prettyPrintJsonReport", "getPrettyPrintJsonReport()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "prettyPrintPlistReport", "getPrettyPrintPlistReport()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "sqliteJournalMode", "getSqliteJournalMode()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "jdCoreDecompileTimeOut", "getJdCoreDecompileTimeOut()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "skip_large_class_by_maximum_methods", "getSkip_large_class_by_maximum_methods()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "skip_large_class_by_maximum_fields", "getSkip_large_class_by_maximum_fields()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "castNeverFailsOfPhantomClass", "getCastNeverFailsOfPhantomClass()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "printAliasInfo", "getPrintAliasInfo()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "UseRoaringPointsToSet", "getUseRoaringPointsToSet()Z", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ExtSettings.class, "hashVersion", "getHashVersion()I", 0))};

    @NotNull
    public static final ExtSettings INSTANCE = new ExtSettings();

    private ExtSettings() {
        super(ExtSettingsKt.logger, "corax.settings.path", ExtSettingsKt.defaultSettingsPath);
    }

    private static final Object _init_$lambda$0() {
        return "ExtSettingsPath: " + getPath();
    }

    @NotNull
    public final String defaultSettingsPath() {
        return ExtSettingsKt.defaultSettingsPath;
    }

    @JvmStatic
    @NotNull
    public static final String getPath() {
        String property = System.getProperty("corax.settings.path");
        return property == null ? ExtSettingsKt.defaultSettingsPath : property;
    }

    public final int getDataFlowIteratorCountForAppClasses() {
        return ((Number) dataFlowIteratorCountForAppClasses$delegate.getValue(this, $$delegatedProperties[0])).intValue();
    }

    public final void setDataFlowIteratorCountForAppClasses(int i) {
        dataFlowIteratorCountForAppClasses$delegate.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    static {
        ExtSettingsKt.logger.info(ExtSettings::_init_$lambda$0);
        dataFlowIteratorCountForAppClasses$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(12, 1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[0]);
        dataFlowIteratorCountForLibClasses$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(8, 1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[1]);
        dataFlowIteratorIsFixPointSizeLimit$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(4, 1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[2]);
        dataFlowMethodUnitsSizeLimit$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(1000, -1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[3]);
        dataFlowCacheExpireAfterAccess$delegate = (ReadWriteProperty) INSTANCE.getLongProperty(30000L, 1L, Long.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[4]);
        dataFlowCacheMaximumWeight$delegate = (ReadWriteProperty) INSTANCE.getLongProperty(10000L, 1L, Long.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[5]);
        dataFlowCacheMaximumSizeFactor$delegate = (ReadWriteProperty) INSTANCE.getProperty(Double.valueOf(5.0d), new Triple(Double.valueOf(1.0E-4d), Double.valueOf(Double.MAX_VALUE), new Comparator() { // from class: cn.sast.api.config.ExtSettings$dataFlowCacheMaximumSizeFactor$2
            public final int compare(double p0, double p1) {
                return Double.compare(p0, p1);
            }

            @Override // java.util.Comparator
            public /* bridge */ /* synthetic */ int compare(Object p0, Object p1) {
                return compare(((Number) p0).doubleValue(), ((Number) p1).doubleValue());
            }
        }), ExtSettings$dataFlowCacheMaximumSizeFactor$3.INSTANCE).provideDelegate(INSTANCE, $$delegatedProperties[6]);
        calleeDepChainMaxNumForLibClassesInInterProceduraldataFlow$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(5, -1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[7]);
        dataFlowInterProceduralCalleeDepChainMaxNum$delegate = (ReadWriteProperty) INSTANCE.getLongProperty(30L, -1L, Long.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[8]);
        dataFlowInterProceduralCalleeTimeOut$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(30000, -1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[9]);
        dataFlowResolveTargetsMaxNum$delegate = (ReadWriteProperty) INSTANCE.getLongProperty(8L, -1L, Long.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[10]);
        dataFlowResultPathOnlyStmt$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(true).provideDelegate(INSTANCE, $$delegatedProperties[11]);
        enableProcessBar$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(true).provideDelegate(INSTANCE, $$delegatedProperties[12]);
        showMetadata$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(true).provideDelegate(INSTANCE, $$delegatedProperties[13]);
        tabSize$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(4).provideDelegate(INSTANCE, $$delegatedProperties[14]);
        dumpCompleteDotCg$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(false).provideDelegate(INSTANCE, $$delegatedProperties[15]);
        prettyPrintJsonReport$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(true).provideDelegate(INSTANCE, $$delegatedProperties[16]);
        prettyPrintPlistReport$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(false).provideDelegate(INSTANCE, $$delegatedProperties[17]);
        sqliteJournalMode$delegate = (ReadWriteProperty) INSTANCE.getStringProperty("WAL").provideDelegate(INSTANCE, $$delegatedProperties[18]);
        jdCoreDecompileTimeOut$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(20000, -1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[19]);
        skip_large_class_by_maximum_methods$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(2000, -1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[20]);
        skip_large_class_by_maximum_fields$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(2000, -1, Integer.MAX_VALUE).provideDelegate(INSTANCE, $$delegatedProperties[21]);
        castNeverFailsOfPhantomClass$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(false).provideDelegate(INSTANCE, $$delegatedProperties[22]);
        printAliasInfo$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(false).provideDelegate(INSTANCE, $$delegatedProperties[23]);
        UseRoaringPointsToSet$delegate = (ReadWriteProperty) INSTANCE.getBooleanProperty(false).provideDelegate(INSTANCE, $$delegatedProperties[24]);
        hashVersion$delegate = (ReadWriteProperty) INSTANCE.getIntProperty(2).provideDelegate(INSTANCE, $$delegatedProperties[25]);
    }

    public final int getDataFlowIteratorCountForLibClasses() {
        return ((Number) dataFlowIteratorCountForLibClasses$delegate.getValue(this, $$delegatedProperties[1])).intValue();
    }

    public final void setDataFlowIteratorCountForLibClasses(int i) {
        dataFlowIteratorCountForLibClasses$delegate.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    public final int getDataFlowIteratorIsFixPointSizeLimit() {
        return ((Number) dataFlowIteratorIsFixPointSizeLimit$delegate.getValue(this, $$delegatedProperties[2])).intValue();
    }

    public final void setDataFlowIteratorIsFixPointSizeLimit(int i) {
        dataFlowIteratorIsFixPointSizeLimit$delegate.setValue(this, $$delegatedProperties[2], Integer.valueOf(i));
    }

    public final int getDataFlowMethodUnitsSizeLimit() {
        return ((Number) dataFlowMethodUnitsSizeLimit$delegate.getValue(this, $$delegatedProperties[3])).intValue();
    }

    public final void setDataFlowMethodUnitsSizeLimit(int i) {
        dataFlowMethodUnitsSizeLimit$delegate.setValue(this, $$delegatedProperties[3], Integer.valueOf(i));
    }

    public final long getDataFlowCacheExpireAfterAccess() {
        return ((Number) dataFlowCacheExpireAfterAccess$delegate.getValue(this, $$delegatedProperties[4])).longValue();
    }

    public final void setDataFlowCacheExpireAfterAccess(long j) {
        dataFlowCacheExpireAfterAccess$delegate.setValue(this, $$delegatedProperties[4], Long.valueOf(j));
    }

    public final long getDataFlowCacheMaximumWeight() {
        return ((Number) dataFlowCacheMaximumWeight$delegate.getValue(this, $$delegatedProperties[5])).longValue();
    }

    public final void setDataFlowCacheMaximumWeight(long j) {
        dataFlowCacheMaximumWeight$delegate.setValue(this, $$delegatedProperties[5], Long.valueOf(j));
    }

    public final double getDataFlowCacheMaximumSizeFactor() {
        return ((Number) dataFlowCacheMaximumSizeFactor$delegate.getValue(this, $$delegatedProperties[6])).doubleValue();
    }

    public final void setDataFlowCacheMaximumSizeFactor(double d) {
        dataFlowCacheMaximumSizeFactor$delegate.setValue(this, $$delegatedProperties[6], Double.valueOf(d));
    }

    public final int getCalleeDepChainMaxNumForLibClassesInInterProceduraldataFlow() {
        return ((Number) calleeDepChainMaxNumForLibClassesInInterProceduraldataFlow$delegate.getValue(this, $$delegatedProperties[7])).intValue();
    }

    public final void setCalleeDepChainMaxNumForLibClassesInInterProceduraldataFlow(int i) {
        calleeDepChainMaxNumForLibClassesInInterProceduraldataFlow$delegate.setValue(this, $$delegatedProperties[7], Integer.valueOf(i));
    }

    public final long getDataFlowInterProceduralCalleeDepChainMaxNum() {
        return ((Number) dataFlowInterProceduralCalleeDepChainMaxNum$delegate.getValue(this, $$delegatedProperties[8])).longValue();
    }

    public final void setDataFlowInterProceduralCalleeDepChainMaxNum(long j) {
        dataFlowInterProceduralCalleeDepChainMaxNum$delegate.setValue(this, $$delegatedProperties[8], Long.valueOf(j));
    }

    public final int getDataFlowInterProceduralCalleeTimeOut() {
        return ((Number) dataFlowInterProceduralCalleeTimeOut$delegate.getValue(this, $$delegatedProperties[9])).intValue();
    }

    public final void setDataFlowInterProceduralCalleeTimeOut(int i) {
        dataFlowInterProceduralCalleeTimeOut$delegate.setValue(this, $$delegatedProperties[9], Integer.valueOf(i));
    }

    public final long getDataFlowResolveTargetsMaxNum() {
        return ((Number) dataFlowResolveTargetsMaxNum$delegate.getValue(this, $$delegatedProperties[10])).longValue();
    }

    public final void setDataFlowResolveTargetsMaxNum(long j) {
        dataFlowResolveTargetsMaxNum$delegate.setValue(this, $$delegatedProperties[10], Long.valueOf(j));
    }

    public final boolean getDataFlowResultPathOnlyStmt() {
        return ((Boolean) dataFlowResultPathOnlyStmt$delegate.getValue(this, $$delegatedProperties[11])).booleanValue();
    }

    public final void setDataFlowResultPathOnlyStmt(boolean z) {
        dataFlowResultPathOnlyStmt$delegate.setValue(this, $$delegatedProperties[11], Boolean.valueOf(z));
    }

    public final boolean getEnableProcessBar() {
        return ((Boolean) enableProcessBar$delegate.getValue(this, $$delegatedProperties[12])).booleanValue();
    }

    public final void setEnableProcessBar(boolean z) {
        enableProcessBar$delegate.setValue(this, $$delegatedProperties[12], Boolean.valueOf(z));
    }

    public final boolean getShowMetadata() {
        return ((Boolean) showMetadata$delegate.getValue(this, $$delegatedProperties[13])).booleanValue();
    }

    public final void setShowMetadata(boolean z) {
        showMetadata$delegate.setValue(this, $$delegatedProperties[13], Boolean.valueOf(z));
    }

    public final int getTabSize() {
        return ((Number) tabSize$delegate.getValue(this, $$delegatedProperties[14])).intValue();
    }

    public final void setTabSize(int i) {
        tabSize$delegate.setValue(this, $$delegatedProperties[14], Integer.valueOf(i));
    }

    public final boolean getDumpCompleteDotCg() {
        return ((Boolean) dumpCompleteDotCg$delegate.getValue(this, $$delegatedProperties[15])).booleanValue();
    }

    public final void setDumpCompleteDotCg(boolean z) {
        dumpCompleteDotCg$delegate.setValue(this, $$delegatedProperties[15], Boolean.valueOf(z));
    }

    public final boolean getPrettyPrintJsonReport() {
        return ((Boolean) prettyPrintJsonReport$delegate.getValue(this, $$delegatedProperties[16])).booleanValue();
    }

    public final void setPrettyPrintJsonReport(boolean z) {
        prettyPrintJsonReport$delegate.setValue(this, $$delegatedProperties[16], Boolean.valueOf(z));
    }

    public final boolean getPrettyPrintPlistReport() {
        return ((Boolean) prettyPrintPlistReport$delegate.getValue(this, $$delegatedProperties[17])).booleanValue();
    }

    public final void setPrettyPrintPlistReport(boolean z) {
        prettyPrintPlistReport$delegate.setValue(this, $$delegatedProperties[17], Boolean.valueOf(z));
    }

    @NotNull
    public final String getSqliteJournalMode() {
        return (String) sqliteJournalMode$delegate.getValue(this, $$delegatedProperties[18]);
    }

    public final void setSqliteJournalMode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        sqliteJournalMode$delegate.setValue(this, $$delegatedProperties[18], str);
    }

    public final int getJdCoreDecompileTimeOut() {
        return ((Number) jdCoreDecompileTimeOut$delegate.getValue(this, $$delegatedProperties[19])).intValue();
    }

    public final void setJdCoreDecompileTimeOut(int i) {
        jdCoreDecompileTimeOut$delegate.setValue(this, $$delegatedProperties[19], Integer.valueOf(i));
    }

    public final int getSkip_large_class_by_maximum_methods() {
        return ((Number) skip_large_class_by_maximum_methods$delegate.getValue(this, $$delegatedProperties[20])).intValue();
    }

    public final void setSkip_large_class_by_maximum_methods(int i) {
        skip_large_class_by_maximum_methods$delegate.setValue(this, $$delegatedProperties[20], Integer.valueOf(i));
    }

    public final int getSkip_large_class_by_maximum_fields() {
        return ((Number) skip_large_class_by_maximum_fields$delegate.getValue(this, $$delegatedProperties[21])).intValue();
    }

    public final void setSkip_large_class_by_maximum_fields(int i) {
        skip_large_class_by_maximum_fields$delegate.setValue(this, $$delegatedProperties[21], Integer.valueOf(i));
    }

    public final boolean getCastNeverFailsOfPhantomClass() {
        return ((Boolean) castNeverFailsOfPhantomClass$delegate.getValue(this, $$delegatedProperties[22])).booleanValue();
    }

    public final void setCastNeverFailsOfPhantomClass(boolean z) {
        castNeverFailsOfPhantomClass$delegate.setValue(this, $$delegatedProperties[22], Boolean.valueOf(z));
    }

    public final boolean getPrintAliasInfo() {
        return ((Boolean) printAliasInfo$delegate.getValue(this, $$delegatedProperties[23])).booleanValue();
    }

    public final void setPrintAliasInfo(boolean z) {
        printAliasInfo$delegate.setValue(this, $$delegatedProperties[23], Boolean.valueOf(z));
    }

    public final boolean getUseRoaringPointsToSet() {
        return ((Boolean) UseRoaringPointsToSet$delegate.getValue(this, $$delegatedProperties[24])).booleanValue();
    }

    public final void setUseRoaringPointsToSet(boolean z) {
        UseRoaringPointsToSet$delegate.setValue(this, $$delegatedProperties[24], Boolean.valueOf(z));
    }

    public final int getHashVersion() {
        return ((Number) hashVersion$delegate.getValue(this, $$delegatedProperties[25])).intValue();
    }

    public final void setHashVersion(int i) {
        hashVersion$delegate.setValue(this, $$delegatedProperties[25], Integer.valueOf(i));
    }
}
