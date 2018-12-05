package Client__Server;

import SAX.SAXParserMy;
import SAX.XmlCreationSAX;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientInterface {
    public static String InputStringFromConsole(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result ="Error in Input";
        try {

            result = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void boxIntoXML (String string, String xMLFileName) throws FileNotFoundException, XMLStreamException {
        XmlCreationSAX.createXMLWithStringParameter(string, xMLFileName);
    }
    public static void getAnswerFromServer () throws ParserConfigurationException, XMLStreamException, SAXException, IOException {
        ServerInterface.operationOnServer();
    }
    public static String parseServerXML (String FileName) throws IOException, SAXException, ParserConfigurationException {
        String getStringAfterParse = "";
        getStringAfterParse = SAXParserMy.getText(FileName);
        return getStringAfterParse;
    }

    public static void main(String[] args) throws IOException, XMLStreamException, SAXException, ParserConfigurationException {
        //OK
        /*String printer = Client__Server.ClientInterface.InputStringFromConsole();
        System.out.println(printer);*/
        String operation = ClientInterface.InputStringFromConsole();
        ClientInterface.boxIntoXML(operation,"ClientXML.xml");
        ClientInterface.getAnswerFromServer();
        String result = parseServerXML("ServerXML.xml");
        System.out.println(result);

    }
}
