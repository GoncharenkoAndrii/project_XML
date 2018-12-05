package SAX;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XmlCreationSAX {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        /*XMLOutputFactory factory = XMLOutputFactory.newFactory();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream("xmlSAX.xml"));
        writer.writeStartDocument();
        writer.writeStartElement("root");
        writer.writeStartElement("font");
        writer.writeAttribute("size","20");
        writer.writeCharacters("TimesNewRoman");
        writer.writeEndDocument();*/
        XmlCreationSAX.createXMLWithStringParameter("1+2","test.xml");
    }
    public static void createXMLWithStringParameter(String string,String name) throws FileNotFoundException, XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newFactory();

        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(name));
        writer.writeStartDocument();
        writer.writeStartElement("root");
        writer.writeStartElement("expression");
        //writer.writeAttribute("size","20");
        writer.writeCharacters(string);
        writer.writeEndDocument();

    }
}
