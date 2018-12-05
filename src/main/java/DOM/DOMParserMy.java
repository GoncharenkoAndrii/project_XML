package DOM;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMParserMy {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        // opening the document
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("xmlDOM.xml"));
        // getting the root element
        Element element = document.getDocumentElement();
        System.out.println(element.getTagName());

        // Getting node list
        printElements(element.getChildNodes(), 1);


    }

    static void printElements(NodeList nodelist, int tabs) {
        // Printing the whole tree of node names
        for (int i = 0; i < nodelist.getLength(); i++) {
            if (nodelist.item(i) instanceof Element) {
                //getting a text field
                String value = "";
                if (!nodelist.item(i).getTextContent().trim().isEmpty()
                        &&!((Text)nodelist.item(i).getFirstChild()).getData().trim().isEmpty()
                        &&!((Text)nodelist.item(i).getFirstChild()).getData().trim().equals("\n")

                        )
                {
                    Text text = (Text)nodelist.item(i).getFirstChild();
                    value+= " = " + text.getData().trim();
                }
                System.out.println(tabulation(tabs) + nodelist.item(i).getNodeName() + value);
                // printing element
                System.out.println(tabulation(tabs++) + ((Element) nodelist.item(i)).getTagName());
                //GETTING AN ATTRIBUTE
                if (((Element) nodelist.item(i)).hasAttribute("size")) {
                    System.out.println(((Element) nodelist.item(i)).getAttribute("size"));
                }
                // getting a node tree
                if (nodelist.item(i).hasChildNodes()) {
                    printElements(nodelist.item(i).getChildNodes(), ++tabs);
                }
                // Getting an attribute list
                NamedNodeMap attributes = nodelist.item(i).getAttributes();
                for(int j =0;j < attributes.getLength();j++){
                    Node attribute = attributes.item(j);
                    String name = attribute.getNodeName();
                    String valueL = attribute.getNodeValue();
                    System.out.println("Attributes - name : " + name + " + value : " + valueL);
                }
            }
        }

    }

    static String tabulation(int tabs) {
        String str = "";
        for (int i = 0; i < tabs; i++) {
            str += "\t";
        }
        return str;
    }
}
