package Client__Server;

import SAX.SAXParserMy;
import SAX.XmlCreationSAX;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ServerInterface {
    public static String parseClientXML () throws IOException, SAXException, ParserConfigurationException {
        String getStringAfterParse = "";
        getStringAfterParse = SAXParserMy.getText("ClientXML.xml");
        return getStringAfterParse;
    }
    public static String operateWithParsedString (String operString){
        String newStr= " Your String was  - " + operString ;
        return newStr;
    }

    public static void boxIntoXML (String string,String xMLFileName) throws FileNotFoundException, XMLStreamException {
        XmlCreationSAX.createXMLWithStringParameter(string,xMLFileName);
    }
    public static void operationOnServer () throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        String oper = ServerInterface.parseClientXML();
        String newStr = ServerInterface.operateWithParsedString(oper);
        ServerInterface.boxIntoXML(newStr,"ServerXML.xml");

    }
}
