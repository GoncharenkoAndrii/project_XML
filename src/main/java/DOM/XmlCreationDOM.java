package DOM;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XmlCreationDOM {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
        // Creating empty document
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
        Document domDoc = domBuilder.newDocument();
        // Creating 3 elements in document
        Element root = domDoc.createElement("root");
        Element font = domDoc.createElement("CalculatorExpression");
        Text text = domDoc.createTextNode("some text");
        // defining the dependencies between elements
        domDoc.appendChild(root);
        root.appendChild(font);
        font.appendChild(text);
        font.setAttribute("size","20");
        // saving the document -------- TRANSFORMER APPROACH
        Transformer t = TransformerFactory.newInstance().newTransformer();
        // attribute to allow element tree instead of line-ouput
        t.setOutputProperty(OutputKeys.INDENT,"yes");
        t.transform(new DOMSource(domDoc), new StreamResult(new FileOutputStream("xmlDOM.xml")));
        // To get a string version of XML - document, created with DOM
        DOMImplementation impl = domDoc.getImplementation();
        DOMImplementationLS implLS = (DOMImplementationLS)impl.getFeature("LS","3.0");
        LSSerializer ser = implLS.createLSSerializer();
        ser.getDomConfig().setParameter("format-pretty-print", true);
        String xmlToDOMasStr = ser.writeToString(domDoc);
        System.out.println(xmlToDOMasStr);
        //
        // Or we can create file according to defined implementation
        //
        /*LSOutput xmlToFile = implLS.createLSOutput();
        xmlToFile.setEncoding("UTF-8");
        xmlToFile.setByteStream(Files.newOutputStream(Paths.get("xmlDOM1.xml")));*/

    }


}
