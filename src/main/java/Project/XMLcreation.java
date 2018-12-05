package Project;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XMLcreation {
    public static void createXml() throws ParserConfigurationException, TransformerException, FileNotFoundException {
        // Creating empty document
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
        Document domDoc = domBuilder.newDocument();
        // Creating needed elements in document
        Element root = domDoc.createElement("Cutlery");
        Element Id = domDoc.createElement("Id");
        Element type = domDoc.createElement("type");
        Element origin = domDoc.createElement("origin");
        Element visual = domDoc.createElement("visual");
        Element lenght = domDoc.createElement("lenght");
        Element material = domDoc.createElement("material");
        // text elements

        Text textType = domDoc.createTextNode(" some type ");
        Text textOrigin = domDoc.createTextNode(" some origin ");
        Text textLenght = domDoc.createTextNode(" some lenght ");
        Text textMaterial = domDoc.createTextNode(" some material ");


        // defining the dependencies between elements
        //root
        domDoc.appendChild(root);
            //id
        root.appendChild(Id);

        Id.setAttribute("Id","0");
                //type
        Id.appendChild(type);
        type.appendChild(textType);
                //origin
        Id.appendChild(origin);
        origin.appendChild(textOrigin);
                //visual
        Id.appendChild(visual);
                    //lenght
        visual.appendChild(lenght);
        lenght.appendChild(textLenght);
                    //material
        visual.appendChild(material);
        material.appendChild(textMaterial);

        // saving the document -------- TRANSFORMER APPROACH
        Transformer t = TransformerFactory.newInstance().newTransformer();
        // attribute to allow element tree instead of line-ouput
        t.setOutputProperty(OutputKeys.INDENT,"yes");
        t.transform(new DOMSource(domDoc), new StreamResult(new FileOutputStream("xmlDOMexample.xml")));
        // To get a string version of XML - document, created with DOM
        DOMImplementation impl = domDoc.getImplementation();
        DOMImplementationLS implLS = (DOMImplementationLS)impl.getFeature("LS","3.0");
        LSSerializer ser = implLS.createLSSerializer();
        ser.getDomConfig().setParameter("format-pretty-print", true);
        String xmlToDOMasStr = ser.writeToString(domDoc);
        System.out.println(xmlToDOMasStr);
    }

    public static void main(String[] args) throws FileNotFoundException, TransformerException, ParserConfigurationException {
        XMLcreation.createXml();
    }

}
