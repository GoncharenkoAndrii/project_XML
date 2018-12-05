package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXParserMy {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
       String printer = SAXParserMy.getText("test.xml");
        System.out.println(printer);
    }
    public static String getText (String FileName) throws ParserConfigurationException, SAXException, IOException {
         /*
        * this method implements COntentHandler interface methods, but only signatures
        * so we have to redefine methods we want to use while parsing the document
        * */

        DefaultHandler handler = new DefaultHandler() {
            /*@Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                String name = attributes.getValue("size");
                if(name != null && !name.isEmpty()){
                    System.out.println(name);
                }
            }*/
            String get = "";
            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String str = "";
                for (int i = 0; i < length; i++) {
                    str += ch[start + i];

                }
                //System.out.println(str);
                get = str ;
            }

            @Override
            public String toString() {
                return get;
            }
        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File(FileName), handler);
        //System.out.println(handler.toString());
     return handler.toString();
    }
}
