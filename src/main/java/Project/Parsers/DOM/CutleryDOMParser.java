package Project.Parsers.DOM;

import Project.Models.Cutlery;
import Project.Parsers.CutleryXMLParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CutleryDOMParser implements CutleryXMLParser {

    @Override
    public List<Cutlery> parseCutlery(String fileName) throws IOException, SAXException, ParserConfigurationException {
        List<Cutlery> cutleryZ = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(fileName));
        document.getDocumentElement().normalize();
        NodeList nodeList = document.getElementsByTagName(Cutlery.getXmlNodeName());

        for (int j = 0; j < nodeList.getLength(); ++j) {
            Node node = nodeList.item(j);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Cutlery cutlery = new Cutlery();

                cutlery.setId(element.getAttribute("id"));
                cutlery.setType(Cutlery.Type.valueOf(
                        element.getElementsByTagName("type").item(0).getTextContent()));

                cutlery.setOrigin(Cutlery.Origin.valueOf(
                        element.getElementsByTagName("origin").item(0).getTextContent()));

                String length = ((Element) (element.getElementsByTagName("visual").item(0)))
                        .getElementsByTagName("length").item(0).getTextContent();
                String material = ((Element) (element.getElementsByTagName("visual").item(0)))
                        .getElementsByTagName("material").item(0).getTextContent();
                try {
                    cutlery.setVisual(new Cutlery.Visual(Integer.parseInt(length), Cutlery.Material.valueOf(material)));
                } catch (Exception ex) {
                    cutlery.setVisual(new Cutlery.Visual(10, Cutlery.Material.STEEL));
                }
                cutleryZ.add(cutlery);
            }
        }
        return cutleryZ;
    }
}
