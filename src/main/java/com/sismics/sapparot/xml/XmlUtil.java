package com.sismics.sapparot.xml;

import org.w3c.dom.Document;

import javax.xml.transform.Result;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author jtremeaux
 */
public class XmlUtil {
    public static InputStream toInputStream(Document document) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            DOMSource domSource = new DOMSource(document);
            Result outputTarget = new StreamResult(outputStream);
            TransformerFactory.newInstance().newTransformer().transform(domSource, outputTarget);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("Error rendering XML document to input stream", e);
        }
    }
}
