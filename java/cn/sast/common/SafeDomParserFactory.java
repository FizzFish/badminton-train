package cn.sast.common;

import cn.sast.dataflow.interprocedural.check.PointsToGraphKt;
import cn.sast.framework.graph.PseudoTopologicalOrderer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.jetbrains.annotations.NotNull;

/* compiled from: SafeDomParserFactory.kt */
@Metadata(mv = {2, PointsToGraphKt.pathStrictMod, PointsToGraphKt.pathStrictMod}, k = PseudoTopologicalOrderer.REVERSE, xi = 48, d1 = {"��\u0018\n\u0002\u0018\u0002\n\u0002\u0010��\n\u0002\b\u0003\n\u0002\u0018\u0002\n��\n\u0002\u0010\u000b\n��\bÆ\u0002\u0018��2\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcn/sast/common/SafeDomParserFactory;", "", "<init>", "()V", "createDocumentBuilder", "Ljavax/xml/parsers/DocumentBuilder;", "namespaceAware", "", "corax-api"})
/* loaded from: SafeDomParserFactory.class */
public final class SafeDomParserFactory {

    @NotNull
    public static final SafeDomParserFactory INSTANCE = new SafeDomParserFactory();

    private SafeDomParserFactory() {
    }

    @NotNull
    public final DocumentBuilder createDocumentBuilder(boolean namespaceAware) throws ParserConfigurationException {
        try {
            DocumentBuilderFactory documentBuilderFactory = new DocumentBuilderFactoryImpl();
            documentBuilderFactory.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-general-entities", false);
            documentBuilderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/dom/create-entity-ref-nodes", false);
            documentBuilderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", false);
            documentBuilderFactory.setValidating(false);
            documentBuilderFactory.setExpandEntityReferences(false);
            documentBuilderFactory.setNamespaceAware(namespaceAware);
            documentBuilderFactory.setXIncludeAware(false);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            documentBuilder.setErrorHandler(null);
            Intrinsics.checkNotNull(documentBuilder);
            return documentBuilder;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException(e);
        }
    }
}
