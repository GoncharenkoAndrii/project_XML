package XMLtask.Builder;

import XMLtask.Models.Beer;

import XMLtask.Models.BeerType;
import XMLtask.Models.Lagger;
import XMLtask.Models.LaggerKind;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

public class BeerDomBuilder extends AbstractBeerBuilder {
    private final Logger LOG = LogManager.getLogger();
    private DocumentBuilder docBuilder;

    // Constructor with logger (parser exception)
    public BeerDomBuilder() {
        beerSet = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOG.error(e);
        }

    }

    @Override
    public void buildSetBeer(String filename) {
        Document doc;
        try {
            doc = docBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList beerList = root.getElementsByTagName("beer");
            NodeList laggerList = root.getElementsByTagName("lagger");
            for (int i = 0; i < beerList.getLength(); i++) {
                Element beerElement = (Element) beerList.item(i);
                Beer beer = buildBeer(beerElement, false);
                beerSet.add(beer);
            }
            for (int i = 0; i < laggerList.getLength(); i++) {
                Element laggerElement = (Element) laggerList.item(i);
                Lagger lagger = (Lagger) buildBeer(laggerElement, true);
                beerSet.add(lagger);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error" + e);
        } catch (SAXException e) {
            System.err.println("Parsing exception" + e);
        }

    }

    private Beer buildBeer(Element beerElement, boolean isLagger) {
        Beer beer;
        //
        if (isLagger) {
            beer = new Lagger();
            ((Lagger) beer).setKind(LaggerKind.valueOf(beerElement.getAttribute("kind").toUpperCase()));
            ((Lagger) beer).setAlcoholPercent(Integer.valueOf(getElementTextContent(beerElement, "alcoholPercent")));
            Lagger.Additives additives = ((Lagger) beer).getAdd();
            Element additiveElements = (Element) beerElement.getElementsByTagName("Additives").item(0);
            //
            if (!getElementTextContent(additiveElements, "ginger").isEmpty()) {
                additives.setGinger(Integer.valueOf(getElementTextContent(additiveElements, "ginger")));
            } else {
                additives.setGinger(0);
            }
            //
            if (!getElementTextContent(additiveElements, "honey").isEmpty()) {
                additives.setHoney(Integer.valueOf(getElementTextContent(additiveElements, "honey")));
            } else {
                additives.setHoney(0);
            }


        } else {
            beer = new Beer();
        }
        beer.setBeerId(beerElement.getAttribute("id"));
        //
        if (!beerElement.getAttribute("type").isEmpty()) {
            beer.setType(BeerType.valueOf(beerElement.getAttribute("type").toUpperCase()));
        } else {
            beer.setType(BeerType.LAGGER);
        }
        //
        beer.setName(getElementTextContent(beerElement, "name"));
        beer.setProduction(getElementTextContent(beerElement, "production"));
        //
        Beer.Ingredients ingredients = beer.getIngredients();
        Element ingridientsElement = (Element) beerElement.getElementsByTagName("ingridients").item(0);
        ingredients.setWater(Integer.valueOf(getElementTextContent(ingridientsElement, "water")));
        ingredients.setHops(Integer.valueOf(getElementTextContent(ingridientsElement, "hops")));
        ingredients.setSugar(Integer.valueOf(getElementTextContent(ingridientsElement, "sugar")));
        ingredients.setMalt(Integer.valueOf(getElementTextContent(ingridientsElement, "malt")));
        //
        Beer.AboutTheBeer aboutTheBeer = beer.getAboutTheBeer();
        Element aboutTheBeerElement = (Element) beerElement.getElementsByTagName("aboutTheBeer").item(0);
        aboutTheBeer.setEnergyAmount(Integer.valueOf(getElementTextContent(aboutTheBeerElement, "energyAmount")));
        aboutTheBeer.setIsFiltered(Integer.valueOf(getElementTextContent(aboutTheBeerElement, "isFiltered")));
        aboutTheBeer.setVolOfAlcohol(Integer.valueOf(getElementTextContent(aboutTheBeerElement, "volOfAlcohol")));
        return beer;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = "";
        if (node != null) {
            text = node.getTextContent();
        }
        return text;
    }
}
