package cn.sast.framework.util;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import java.io.StringReader;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import org.dom4j.io.SAXReader;
import org.jetbrains.annotations.NotNull;

/* compiled from: SAXReaderBugTest.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0012\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0010\u0002\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcn/sast/framework/util/SAXReaderBugTest;", "", "<init>", "()V", "test", "", "corax-framework"})
/* loaded from: SAXReaderBugTest.class */
public final class SAXReaderBugTest {

    @NotNull
    public static final SAXReaderBugTest INSTANCE = new SAXReaderBugTest();

    private SAXReaderBugTest() {
    }

    public final void test() {
        SAXReader reader = new SAXReader();
        reader.read(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<MessageCollection xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n  xsi:noNamespaceSchemaLocation=\"messagecollection.xsd\">\n  <Plugin>\n  <![CDATA[\n" + StringsKt.repeat("1", 1124) + "\n]]>\n  </Plugin>\n</MessageCollection>"));
    }
}
